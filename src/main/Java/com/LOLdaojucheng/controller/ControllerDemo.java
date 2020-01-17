package com.LOLdaojucheng.controller;

import com.LOLdaojucheng.pojo.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "user")
public class ControllerDemo {
    @RequestMapping(value = "domain")
    public UserInfo domain(){
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1);
        userInfo.setAnswer("nansongling");
        userInfo.setEmail("11111");
        return userInfo;
    }
}
