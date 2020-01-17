package com.LOLdaojucheng.controller.protal;

import com.LOLdaojucheng.common.Const;
import com.LOLdaojucheng.common.ServerResponse;
import com.LOLdaojucheng.pojo.Shopping;
import com.LOLdaojucheng.pojo.UserInfo;
import com.LOLdaojucheng.service.IShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.beans.IntrospectionException;


@RestController
@RequestMapping("/shipping")
public class ShippingController {
    @Autowired
    IShippingService iShippingService;

    /***
     * 添加收货地址
     * @param shopping
     * @return
     */
    @RequestMapping("/add_shipping")
    public ServerResponse  add_shipping(HttpSession session,Shopping shopping){
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return ServerResponse.serverResponseByError("没有登录无法添加地址");
        }
        shopping.setUserId(userInfo.getId());
        ServerResponse serverResponse = iShippingService.add_Shipping(userInfo.getId(),shopping);
        return  serverResponse;
    }

    /***
     * 更新地址信息
     */
    @RequestMapping("/updateShipping")
    public ServerResponse updateShipping(HttpSession session,Shopping shopping){
        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return ServerResponse.serverResponseByError("没有登录无法更新地址");
        }
        shopping.setUserId(userInfo.getId());
        ServerResponse serverResponse = iShippingService.updateShipping(shopping);
        return serverResponse;
    }

    /***
     * 查询当前登录的用户是否有收货地址，如果有，返回收货地址的集合
     */
    @RequestMapping("/selectByUserId")
    public ServerResponse selectByUserId(HttpSession session){
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return ServerResponse.serverResponseByError("未登录无法查询收货地址");
        }else {
            return iShippingService.selectById(userInfo.getId());
        }
    }

    /***
     * 删除一条地址
     */
    @RequestMapping("/deleteAddress")
    public ServerResponse deleteAddress(HttpSession session,Integer id){
        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return ServerResponse.serverResponseByError("未登录");
        }
        return iShippingService.deleteAddress(userInfo.getId(),id);
    }

    /***
     * 查询登录用户的所有地址
     */
    @RequestMapping("/selectAllByUserId")
    public ServerResponse selectAllByUserId(HttpSession session){
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return ServerResponse.serverResponseByError("未登录无法查询所有地址");
        }else {
            return iShippingService.selectAllByUserId(userInfo.getId());
        }
    }
    /***
     * 选中收货地址，并且将被选中的地址变为不被选中
     */
    @RequestMapping("/changeChecked")
    public ServerResponse changeChecked(HttpSession session,Integer id){
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return ServerResponse.serverResponseByError("未登录无法更改默认地址");
        }
        Integer userID = userInfo.getId();
        return iShippingService.changeChecked(userID,id);
    }
}
