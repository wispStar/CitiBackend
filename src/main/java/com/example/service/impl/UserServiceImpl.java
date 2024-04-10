package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ResponseResult;
import com.example.entity.User;
import com.example.enums.AppHttpCodeEnum;
import com.example.exception.SystemException;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import com.example.utils.BeanCopyUtils;
import com.example.utils.PasswordEncoder;
import com.example.vo.UserRegisterVo;
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

    private boolean userNameExist(String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        return  count(queryWrapper) > 0;
    }
}
