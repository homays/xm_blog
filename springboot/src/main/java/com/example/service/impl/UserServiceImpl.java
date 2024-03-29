package com.example.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.Constants;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.entity.User;
import com.example.exception.CustomException;
import com.example.service.UserService;
import com.example.mapper.UserMapper;
import com.example.vo.req.RegisterDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author lenovo
* @description 针对表【user(用户信息)】的数据库操作Service实现
* @createDate 2024-03-16 16:25:40
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    @Override
    public void registerUser(RegisterDTO registerDTO) {
        String username = registerDTO.getUsername();
        String password = registerDTO.getPassword();
        if (StrUtil.isBlank(password) || StrUtil.isBlank(username)) {
            throw new CustomException(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery()
                .eq(User::getUsername, username);
        User dbUser = this.getOne(wrapper);
        if (ObjectUtil.isNotNull(dbUser)) {
            throw new CustomException(ResultCodeEnum.USER_EXIST_ERROR);
        }
        registerDTO.setRole(RoleEnum.USER.name());
        User user = BeanUtil.copyProperties(registerDTO, User.class);
        save(user);
    }

    @Override
    public void deleteBatch(List<Integer> ids) {
        deleteBatch(ids);
    }

    @Override
    public void deleteById(Integer id) {
        deleteById(id);
    }

    @Override
    public User selectById(Integer id) {
        return getOne(Wrappers.<User>lambdaQuery()
                .eq(User::getId, id));
    }

    @Override
    public List<User> selectAll(User user) {
        return list();
    }

    @Override
    public Page<User> selectPage(User user, Integer pageNum, Integer pageSize) {
        Page<User> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(user.getUsername())) {
            wrapper.like(User::getUsername, user.getUsername());
        }
        wrapper.like(StrUtil.isBlank(user.getPhone()), User::getPhone, user.getPhone());
        wrapper.like(StrUtil.isBlank(user.getEmail()), User::getEmail, user.getEmail());
        wrapper.like(StrUtil.isBlank(user.getInfo()), User::getInfo, user.getInfo());
        return page(page, wrapper);
    }
}




