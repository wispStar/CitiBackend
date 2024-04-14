package com.bondsales.controller;

import com.bondsales.ResponseResult;
import com.bondsales.entity.User;
import com.bondsales.service.UserService;
import com.bondsales.vo.UserNameVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseResult register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user) {
        return userService.login(user);
    }

    @GetMapping("/profile/{username}")
    public ResponseResult getUserProfile(@PathVariable String username) {
        return userService.getUserProfile(username);
    }

    @PostMapping("/logout")
    public ResponseResult logout(@RequestBody UserNameVo userNameVo) {
        return userService.logout(userNameVo);
    }
}