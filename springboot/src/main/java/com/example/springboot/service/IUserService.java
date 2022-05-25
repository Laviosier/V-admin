package com.example.springboot.service;

import com.example.springboot.controller.dto.UserDTO;
import com.example.springboot.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xch
 * @since 2022-05-08
 */
public interface IUserService extends IService<User> {
    UserDTO login(UserDTO userDTO);

    User register(UserDTO userDTO);
}
