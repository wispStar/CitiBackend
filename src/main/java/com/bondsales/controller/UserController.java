package com.bondsales.controller;

import com.bondsales.ResponseResult;
import com.bondsales.entity.User;
import com.bondsales.service.UserService;
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

    @GetMapping("/profile")
    public ResponseResult getUserProfile(@RequestParam String username) {
        return userService.getUserProfile(username);
    }
}