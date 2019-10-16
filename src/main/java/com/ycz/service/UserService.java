package com.ycz.service;

import com.ycz.pojo.User;

import java.util.List;

public interface UserService {
    List<User> selectAllUsers();
    Integer insertUser(User user);
    User queryUserByUsername(String username);
}
