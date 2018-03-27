package com.mrbeard.controller;

import com.mrbeard.entites.Msg;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RedirectionController {

    @RequestMapping("/")
    public String index(Model model){
        Msg msg =  new Msg("测试标题","测试内容","额外信息，只对管理员显示");
        model.addAttribute("msg", msg);
        return "home";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/login_error")
    public String error(){
        return "error";
    }
}
