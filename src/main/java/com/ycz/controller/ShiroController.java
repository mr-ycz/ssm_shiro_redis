package com.ycz.controller;

import com.ycz.pojo.User;
import com.ycz.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/shiro")
public class ShiroController {

    @Autowired
    private UserService userService;

    @GetMapping("/regist")
    public String regist(){
        return "regist";
    }

    @PostMapping("/regist")
    public String registLogic(String username,String password){
        User user=new User(null, username, password, null);
        userService.insertUser(user);
        return "index";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "forward:/WEB-INF/login.jsp";
    }

    @PostMapping("/login")
    public String loginLogic(String username,String password){
        User user = userService.queryUserByUsername(username);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(true);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);

        return "index";
    }

    @RequiresAuthentication
    @RequiresPermissions("fight")
    @RequestMapping("/fight")
    public String fight(){
        System.out.println("可以打秦源狗...");
        return "index";
    }

    @RequiresUser
    @RequestMapping("/logout")
    public String lougout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/shiro/login";
    }

    @GetMapping("/find")
    public String findAll(Model model){
        List<User> users = userService.selectAllUsers();
        model.addAttribute(model);

        return "forward:/WEB-INF/users.jsp";

    }
}
