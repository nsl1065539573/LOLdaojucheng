package com.LOLdaojucheng.controller.protal;

import com.LOLdaojucheng.common.ServerResponse;
import com.LOLdaojucheng.service.IproductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/product")
public class ProductController_protal {
    @Autowired
    IproductService iproductService;
    /***
     * 前台--商品详情
     */
    @RequestMapping("/detail_protal/{productId}")
    public ServerResponse detail_portal(@PathVariable("productId") Integer productId){
        ServerResponse serverResponse = iproductService.detail_portal(productId);
        return  serverResponse;
    }

    /***
     * 前台---商品搜索并排序
     */
    @RequestMapping("/list_protal")
    public ServerResponse list(@RequestParam(required = false) Integer categoryId,
                               @RequestParam(required = false) String keyword,
                               @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                               @RequestParam(required = false,defaultValue = "8")Integer pageSize,
                               @RequestParam(required = false,defaultValue = "")String orderBy){

        if (categoryId ==0||categoryId==null){
            ServerResponse serverResponse = iproductService.list_protal(pageNum,pageSize,orderBy);
            return serverResponse;
        }else {
            ServerResponse serverResponse = iproductService.list_protal(categoryId,keyword,pageNum,pageSize,orderBy);
            return serverResponse;
        }

    }
    /***
     * 前台--将所有商品排序
     */
    @RequestMapping("/list_protal_all")
    public ServerResponse list_all(
                               @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                               @RequestParam(required = false,defaultValue = "8")Integer pageSize,
                               @RequestParam(required = false,defaultValue = "")String orderBy){

        ServerResponse serverResponse = iproductService.list_protal(pageNum,pageSize,orderBy);
        return serverResponse;
    }
    /***
     * 热门商品搜索与返回
     */
    @RequestMapping("/hotproduct")
    public ServerResponse hotProduct(){
        ServerResponse serverResponse = iproductService.hotProduct();
        return serverResponse;
    }

    /***
     * 打折商品搜索与返回
     */
    @RequestMapping("/rebate")
    public ServerResponse rebate(){
        ServerResponse serverResponse = iproductService.rebate();
        return serverResponse;
    }
    /***
     * 热门排行那一列
     */
    @RequestMapping("/selectHot")
    public ServerResponse selectHot(){
        ServerResponse serverResponse = iproductService.selectHot();
        return serverResponse;
    }

    /***
     * 搜索框模糊查询
     * @param word
     * @return
     */
    @RequestMapping("/mohuchaxun")
    public ServerResponse mohuchaxun(@RequestParam String word){
        ServerResponse serverResponse = iproductService.mohuchaxun(word);
        System.out.print(word);
        return serverResponse;
    }
    /***
     * 手办查询与返回
     */
    @RequestMapping("/shouban")
    public ServerResponse shouban(){
        ServerResponse serverResponse = iproductService.shouban();
        return serverResponse;
    }

    /***
     * 根据商品ID返回商品详情
     */
    @RequestMapping("/details")
    public ServerResponse details(Integer productId){
        ServerResponse serverResponse = iproductService.detail_portal(productId);
        return serverResponse;
    }



}
