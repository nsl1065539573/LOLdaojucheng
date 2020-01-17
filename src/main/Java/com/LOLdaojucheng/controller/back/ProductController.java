package com.LOLdaojucheng.controller.back;

import com.LOLdaojucheng.common.Const;
import com.LOLdaojucheng.common.ServerResponse;
import com.LOLdaojucheng.pojo.Product;
import com.LOLdaojucheng.pojo.UserInfo;
import com.LOLdaojucheng.service.IproductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/manage/product")
public class ProductController {
    @Autowired
    IproductService iproductService;
    /***
     * 更新或者添加商品
     */
    @RequestMapping("/saveOrUpdateProduct")
    public ServerResponse saveOrUpdateProduct(HttpSession session, Product product){
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return  ServerResponse.serverResponseByError("未登录");
        }
        if (userInfo.getRole()!=Const.RoleEnum.ROLE_ADMIN.getCode()){
            return ServerResponse.serverResponseByError("用户权限不够");
        }
        ServerResponse serverResponse = iproductService.saveOrUpdateProduct(product);
        return  serverResponse;
    }

    /***
     * 更改商品上下架信息  修改status可以完成这项功能
     */
    @RequestMapping("/set_sale_status")
    public ServerResponse set_sale_status(HttpSession session,Integer productId,Integer status){

        ServerResponse serverResponse = iproductService.set_sale_status(productId,status);
        return serverResponse;
    }
    /***
     * 更改商品上下架信息  修改status可以完成这项功能Restful
     */
    @RequestMapping("set_sale_status/{productId}/{status}")
    public ServerResponse set_sale_statusRestful(HttpSession session,
                                                 @PathVariable("productId") Integer productId,
                                                 @PathVariable("status") Integer status){

        ServerResponse serverResponse = iproductService.set_sale_status(productId,status);
        return serverResponse;
    }
    /***
     * 商品详情
     */
    @RequestMapping("/detail")
    public ServerResponse detail(HttpSession session,Integer productId){

        ServerResponse serverResponse = iproductService.detail(productId);
        return serverResponse;
    }
    /***
     * 商品详情
     */
    @RequestMapping("/detail/{productId}")
    public ServerResponse detailRestful(HttpSession session,
                                        @PathVariable("productId") Integer productId){

        ServerResponse serverResponse = iproductService.detail(productId);
        return serverResponse;
    }

    /***
     * 查看商品列表
     */
    @RequestMapping("/list.do")
    public ServerResponse list(HttpSession session,
                               @RequestParam(value="pageNum",required=false,defaultValue = "1")Integer pageNum,
                               @RequestParam(value="pageSize",required=false,defaultValue = "10")Integer pageSize){

        ServerResponse serverResponse = iproductService.list(pageNum,pageSize);
        return  serverResponse;
    }

    /***
     * 查询商品
     */
    @RequestMapping("/search")
    public ServerResponse search(HttpSession session,
                               @RequestParam(value="pageNum",required=false)String productName,
                               @RequestParam(value="pageSize",required=false)Integer productId,
                               @RequestParam(value="pageNum",required=false,defaultValue = "1")Integer pageNum,
                               @RequestParam(value="pageSize",required=false,defaultValue = "10")Integer pageSize){

        ServerResponse serverResponse = iproductService.serach(productName,productId, pageNum,pageSize);
        return  serverResponse;
    }
    /***
     * 更新商品数量
     */
    @RequestMapping("/updateStock")
    public ServerResponse updateStock(HttpSession session,
                                      Integer productId,
                                      Integer stock){
        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return ServerResponse.serverResponseByError("请进行登录");
        }else if (userInfo.getRole()!=Const.RoleEnum.ROLE_ADMIN.getCode()){
            return ServerResponse.serverResponseByError("权限不够");
        }else {
            return iproductService.updateStock(productId,stock);
        }
    }

    /***
     * 更新商品描述
     */
    @RequestMapping("/updateDetail")
    public ServerResponse updateDetail(HttpSession session,
                                       Integer productId,
                                       String detail){
        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return ServerResponse.serverResponseByError("请xian进行登录");
        }else if (userInfo.getRole()!=Const.RoleEnum.ROLE_ADMIN.getCode()){
            return ServerResponse.serverResponseByError("权限不够");
        }else {
            return iproductService.updateDetail(detail,productId);
        }
    }
}
