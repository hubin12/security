package com.mrbeard.controller;

import com.mrbeard.entites.SysUser;
import com.mrbeard.entites.User;
import com.mrbeard.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestfulController {

    @Autowired
   private UserServices userServices;


    @RequestMapping("/getUsers")
    public List<User> getAllUser() {
        List<User> users = userServices.getAll();
        return users;
    }

    @RequestMapping(value = "/getOneById/{id}")
    public User getOneUser(@PathVariable("id") Long id){
        User user = userServices.getOne(id);
        return user;
    }

    @RequestMapping(value = "/updateUser")
    public User updateUser(){
        return userServices.updateUser();
    }


    @RequestMapping("/getuser")
    //必须有这个权限才可以使用
    @Secured("ROLE_USER")
    @ResponseBody
    public SysUser getSysUser() {
        SysUser sysUser = new SysUser();
        sysUser.setUsername("test");
        return sysUser;
    }
}
