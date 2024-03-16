package com.example.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.vo.req.RegisterDTO;

import java.util.List;

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

    void deleteBatch(List<Integer> ids);

    void deleteById(Integer id);

    User selectById(Integer id);

    List<User> selectAll(User user);

    Page<User> selectPage(User user, Integer pageNum, Integer pageSize);
}
