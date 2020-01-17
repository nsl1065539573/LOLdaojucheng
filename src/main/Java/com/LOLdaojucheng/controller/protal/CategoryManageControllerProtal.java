package com.LOLdaojucheng.controller.protal;

import com.LOLdaojucheng.common.ServerResponse;

import com.LOLdaojucheng.service.CategoryManageControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/category")
public class CategoryManageControllerProtal {
    @Autowired
    CategoryManageControllerService categoryManageControllerService;
    /**
     * 前台查询所有的分类
     */
    @RequestMapping("/selectAll")
    public ServerResponse selectAll(HttpSession session){
        ServerResponse serverResponse = categoryManageControllerService.selectAll();
        return serverResponse;
    }
}
