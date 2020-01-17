package com.LOLdaojucheng.controller.protal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/protalView")
public class protal_showPageController {
    @RequestMapping("/login")
    public String login(){
            return "login";
    }
    @RequestMapping("/details")
    public  String details(){
        return "details";
    }
    @RequestMapping("/cart")
    public  String cart(){
        return "cart";
    }
    @RequestMapping("/homepage")
    public String homepage(){
        return "homepage";
    }
    @RequestMapping("product_list")
    public String product_list(){
        return "product_list";
    }
    @RequestMapping("/registe")
    public  String registe(){
        return "registe";
    }
    @RequestMapping("/sousuo_list")
    public  String sousuo_list(){
        return "sousuo_list";
    }
    @RequestMapping("/userInfo")
    public String userInfo(){
        return "userInfo";
    }
    @RequestMapping("/Order")
    public  String Order(){
        return "Order";
    }
}
