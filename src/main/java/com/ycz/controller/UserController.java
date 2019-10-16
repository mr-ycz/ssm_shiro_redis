package com.ycz.controller;

import com.ycz.pojo.User;
import com.ycz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findAll")
    public String findAll(){
        List<User> users = userService.selectAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
        return "index";
    }

    @RequestMapping("/save")
    public String save(){
        User user=new User(null, "李振川", "123456", "aaa");
        userService.insertUser(user);
        return "index";
    }

}
