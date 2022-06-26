package com.example.summervacationproject.service;

import java.util.Map;

public interface UserService {
    String getName(Integer id);
    Map<String,String> addUser(String username, String password);
    Map<String,String> handleLogin(String username, String password);
}
