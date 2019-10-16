package com.ycz.service;

import com.ycz.dao.UserMapper;
import com.ycz.pojo.User;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<User> selectAllUsers() {
        return userMapper.queryAllUsers();
    }

    @Override
    public Integer insertUser(User user) {
        // 加盐——加密 注册
        String salt = UUID.randomUUID().toString();
        user.setSalt(salt);
        String s = new Sha256Hash(user.getPassword(), salt, 10000).toBase64();
        user.setPassword(s);
        return userMapper.insertUser(user);
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    public User queryUserByUsername(String username) {
        return userMapper.queryUserByUsername(username);
    }

}
