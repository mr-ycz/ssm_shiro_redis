package com.ycz.dao;

import com.ycz.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    List<User> queryAllUsers();
    Integer insertUser(User user);
    User queryUserByUsername(@Param("username") String username);

}
