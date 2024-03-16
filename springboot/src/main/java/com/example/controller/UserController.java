package com.example.controller;

import com.example.common.Result;
import com.example.service.UserService;
import com.example.vo.req.RegisterDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户 controller
 *
 * @author Arrebol
 * @date 2024/3/16
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/add")
    public Result addUser(@RequestBody RegisterDTO registerDTO) {
        userService.registerUser(registerDTO);
        return Result.success();
    }

}
