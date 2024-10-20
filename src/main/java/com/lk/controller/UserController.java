package com.lk.controller;

import com.lk.domain.Result;
import com.lk.domain.User;
import com.lk.service.UserService;
import com.lk.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {//testchange
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{2,20}$",message = "id wrong") String id,
                           @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{2,20}$",message = "password wrong") String password){
        System.out.println("register:"+id+" "+password);
        return userService.register(id,password);
    }

    @PostMapping("/login")
    public Result login(String id,String password){
        System.out.println("login:"+id+" "+password);
        return userService.login(id,password);
    }

    @GetMapping("/favorite")
    public Result favorite(){
        return userService.getFavorite(ThreadLocalUtil.getId());
    }


}
