package com.example.summervacationproject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.summervacationproject.bean.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    String getNameById(Integer id);

    String getPwdById(Integer id);

    int addUser(User user);

    Integer getIdByName(String username);
}