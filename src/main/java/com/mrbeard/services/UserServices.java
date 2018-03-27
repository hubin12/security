package com.mrbeard.services;

import com.mrbeard.entites.User;
import com.mrbeard.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices  {

    @Autowired
    private UserMapper userMapper;

    public List<User> getAll(){
        return userMapper.getAllUser();
    }

    public User updateUser(){
        User user = new User(2l,"李死","234","江西");
        userMapper.updateUser(user);
        return user;
    }

    public User getOne(Long id){
       return  userMapper.getOneUser(id);
    }
}
