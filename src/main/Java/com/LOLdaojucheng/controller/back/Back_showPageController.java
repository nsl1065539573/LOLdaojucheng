package com.LOLdaojucheng.controller.back;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class Back_showPageController {
    @RequestMapping("/login")
    public String login(){
        return "back_login";
    }
    @RequestMapping("/user")
    public String user(){
        return "back_user";
    }
    @RequestMapping("/categoryFirst")
    public String categoryFirst(){
        return "back_catrgoryFirst";
    }
    @RequestMapping("/categorySecond")
    public  String categorySecond(){
        return "back_categorySecond";
    }
    @RequestMapping("/product")
    public String product(){
        return "back_product";
    }
}
