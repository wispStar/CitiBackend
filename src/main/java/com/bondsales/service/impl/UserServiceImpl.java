package com.bondsales.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bondsales.ResponseResult;
import com.bondsales.constants.SystemConstants;
import com.bondsales.entity.User;
import com.bondsales.enums.AppHttpCodeEnum;
import com.bondsales.exception.SystemException;
import com.bondsales.mapper.UserMapper;
import com.bondsales.service.UserService;
import com.bondsales.utils.BeanCopyUtils;
import com.bondsales.utils.JwtUtil;
import com.bondsales.utils.PasswordEncoder;
import com.bondsales.vo.UserLoginVo;
import com.bondsales.vo.UserProfileVo;
import com.bondsales.vo.UserRegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2024-04-09 23:20:31
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponseResult register(User user) {
        // 对数据进行进行非空判断
        if (!StringUtils.hasText(user.getUsername())) {
            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
        }
        if (!StringUtils.hasText(user.getPassword())) {
            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        }
        String email, phoneNumber;
        if (StringUtils.hasText(user.getEmail())) {
            email = user.getEmail();
            user.setEmail(email);
        }
        if (StringUtils.hasText(user.getPhoneNumber())) {
            phoneNumber = user.getPhoneNumber();
            user.setPhoneNumber(phoneNumber);
        }

        // 获取用户输入的密码
        String password = user.getPassword();
        // 获取用户的用户名作为盐值
        String salt = user.getUsername();
        // 对密码进行加密（加盐的MD5）
        String encryptedPassword = PasswordEncoder.encodePassword(password, salt);
        user.setPassword(encryptedPassword);


        if (userNameExist(user.getUsername())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.USER_EXISTS);
        } else {
            // 用户不存在，将用户信息存入数据库
            save(user);

            // 封装返回数据
            UserRegisterVo userRegisterVo = BeanCopyUtils.copyBean(user, UserRegisterVo.class);
            return ResponseResult.okResult(userRegisterVo);
        }
    }

    @Override
    public ResponseResult login(User user) {
        // 对数据进行非空判断
        if (!StringUtils.hasText(user.getUsername())) {
            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
        }
        if (!StringUtils.hasText(user.getPassword())) {
            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        }

        // 获取用户输入的密码
        String password = user.getPassword();
        // 获取用户的用户名作为盐值
        String salt = user.getUsername();
        // 对输入密码进行加密（加盐的MD5）
        String encryptedPassword = PasswordEncoder.encodePassword(password, salt);

        // 查询数据库中的用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, user.getUsername());
        User dbUser = userMapper.selectOne(queryWrapper);

        if (dbUser == null) {
            throw new SystemException(AppHttpCodeEnum.LOGIN_ERROR);
        }

        // 比较数据库中存储的加密密码和用户输入的加密密码
        if (!dbUser.getPassword().equals(encryptedPassword)) {
            throw new SystemException(AppHttpCodeEnum.LOGIN_ERROR);
        }

        // 生成JWT令牌
        String userId = dbUser.getId().toString();
        String jwt = JwtUtil.createJWT(userId);

        // 赋值: jwt, status为"1"
        if (dbUser.getStatus().equals(SystemConstants.ALREADY_LOGGED)) {
            throw new SystemException(AppHttpCodeEnum.LOGIN_ERROR);
        }
        dbUser.setToken(jwt);
        dbUser.setStatus(SystemConstants.ALREADY_LOGGED);

        // 存入数据库
        updateById(dbUser);

        // 封装数据返回
        UserLoginVo userLoginVo = new UserLoginVo(user.getUsername(), jwt);
        return ResponseResult.okResult(userLoginVo);
    }


    private boolean userNameExist(String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        return  count(queryWrapper) > 0;
    }

    @Override
    public ResponseResult getUserProfile(String username) {
        User user = getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        if (user == null) {
            return new ResponseResult(-109, "User profile not found", null);
        }
        UserProfileVo userProfileVo = BeanCopyUtils.copyBean(user, UserProfileVo.class);
        return ResponseResult.okResult(userProfileVo);
    }
}
