package com.example.service;

import com.example.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.vo.req.RegisterDTO;

/**
* @author arrebol
* @description 针对表【user(用户信息)】的数据库操作Service
* @createDate 2024-03-16 16:25:40
*/
public interface UserService extends IService<User> {

    /**
     * 用户注册
     */
    void registerUser(RegisterDTO registerDTO);
}
