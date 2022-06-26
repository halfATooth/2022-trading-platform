package com.example.summervacationproject.service.Impl;

import com.example.summervacationproject.bean.User;
import com.example.summervacationproject.mapper.UserMapper;
import com.example.summervacationproject.service.UserService;
import com.example.summervacationproject.util.JwtUtils;
import com.example.summervacationproject.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public String getName(Integer id) {
        return userMapper.getNameById(id);
    }

    @Override
    public Map<String,String> addUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(SecurityUtils.encodePassword(password));
        Map<String,String> res = new HashMap<>();
        try {
            userMapper.addUser(user);
            res.put("code","0");
            res.put("msg","注册成功");
        }catch (Exception e){
            res.put("code","1");
            res.put("msg","添加失败，用户名已存在");
        }
        return res;
    }

    @Override
    public Map<String, String> handleLogin(String username, String password) {
        Map<String,String> responseData = new HashMap<>();
        Integer id = userMapper.getIdByName(username);
        if(SecurityUtils.matchesPassword(password, userMapper.getPwdById(id))){
            JwtUtils jwtUitls = new JwtUtils();
            String token = jwtUitls.createToken(id+"",username);
            responseData.put("code","0");
            responseData.put("msg","登录成功");
            responseData.put("token",token);
        }else {
            responseData.put("code","1");
            responseData.put("msg","登录失败");
        }
        return responseData;
    }
}
