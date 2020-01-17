package com.LOLdaojucheng.controller.back;


import com.LOLdaojucheng.common.ServerResponse;

import com.LOLdaojucheng.service.CategoryManageControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/manage/category")
public class CategoryManageController {
    @Autowired
    CategoryManageControllerService categoryManageControllerService;


    /***
     * 获取品类子节点
     *
     */
    @RequestMapping("/get_category")
    public ServerResponse get_category(HttpSession session,int categoryid){

        ServerResponse serverResponse = categoryManageControllerService.get_category(categoryid);
        return  serverResponse;
    }
    /***
     * 获取品类子节点Restful
     *
     */
    @RequestMapping("/get_category/{categoryid}")
    public ServerResponse get_categoryRestful(HttpSession session,
                                              @PathVariable("categoryid") int categoryid){

        ServerResponse serverResponse = categoryManageControllerService.get_category(categoryid);
        return  serverResponse;
    }

    /***
     * 添加节点
     *
     */
    @RequestMapping("/add_category")
    public ServerResponse add_category(HttpSession session,
                                       @RequestParam(required = false,defaultValue = "0") Integer parentId,
                                       String categoryName){
        ServerResponse serverResponse = categoryManageControllerService.add_category(parentId,categoryName);
        return  serverResponse;
    }
    /***
     * 添加节点Restful
     *
     */
    @RequestMapping("/add_category/{parentId}/{categoryName}")
    public ServerResponse add_categoryRestful(HttpSession session,
                                              @PathVariable("parentId") Integer parentId,
                                              @PathVariable("categoryName") String categoryName){
        ServerResponse serverResponse = categoryManageControllerService.add_category(parentId,categoryName);
        return  serverResponse;
    }

    /***
     * 更改节点名称
     */
    @RequestMapping("/change_category")
    public ServerResponse change_category(HttpSession session,
                                          Integer categorytId,
                                          String categoryName){
        ServerResponse serverResponse = categoryManageControllerService.change_category(categorytId,categoryName);
        return  serverResponse;
    }
    /***
     * 更改节点名称Restful
     */
    @RequestMapping("/change_category/{categoryId}/{categoryName}")
    public ServerResponse change_categoryRestful(HttpSession session,
                                          @PathVariable("categoryId") Integer categorytId,
                                          @PathVariable("categoryName") String categoryName){
        ServerResponse serverResponse = categoryManageControllerService.change_category(categorytId,categoryName);
        return  serverResponse;
    }

    /***
     * 查询所有的递归子节点
     */
    @RequestMapping("/get_every_category")
    public ServerResponse get_every_category(HttpSession session,
                                          @PathVariable("categorytId") Integer categorytId
                                          ){
        ServerResponse serverResponse = categoryManageControllerService.get_every_category(categorytId);
        return  serverResponse;
    }
    /***
     * 查询所有的递归子节点Restful
     */
    @RequestMapping("/get_every_category/{categorytId}")
    public ServerResponse get_every_categoryRestful(HttpSession session,
                                             @PathVariable("categorytId") Integer categorytId
    ){
        ServerResponse serverResponse = categoryManageControllerService.get_every_category(categorytId);
        return  serverResponse;
    }

}
