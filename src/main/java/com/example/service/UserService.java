package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ResponseResult;
import com.example.entity.User;


/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2024-04-09 23:20:29
 */
public interface UserService extends IService<User> {
    ResponseResult register(User user);
}
