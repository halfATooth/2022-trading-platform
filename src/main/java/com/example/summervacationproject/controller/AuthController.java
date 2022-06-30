package com.example.summervacationproject.controller;

import com.example.summervacationproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@CrossOrigin(origins = "*")
@Controller
public class AuthController {
    @Autowired
    UserService userService;

    @ResponseBody
    @PostMapping("/login")
    public Map<String,String> login(String username,String password){
        return userService.handleLogin(username,password);
    }

    @PostMapping("/signup")
    @ResponseBody
    public Map<String,String> signup(String username, String password){
        return userService.addUser(username,password);
    }
}