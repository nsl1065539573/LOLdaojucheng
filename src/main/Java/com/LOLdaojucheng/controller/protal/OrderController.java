package com.LOLdaojucheng.controller.protal;

import com.LOLdaojucheng.common.Const;
import com.LOLdaojucheng.common.ServerResponse;
import com.LOLdaojucheng.pojo.UserInfo;
import com.LOLdaojucheng.service.IOrderService;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.demo.trade.config.Configs;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;

@RestController
@RequestMapping("/Order")
public class OrderController {
    @Autowired
    IOrderService iOrderService;

    /***
     * 创建订单
     * @param session
     * @param shippingId
     * @return
     */
    @RequestMapping("/createOrder")
    public ServerResponse createOrder(HttpSession session, Integer shippingId, BigDecimal totalPrice){
        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return ServerResponse.serverResponseBySuccess("未登录");
        }
        return  iOrderService.createOrder(userInfo.getId(),shippingId,totalPrice);
    }
    /***
     * 创建订单Restful
     * @param session
     * @param shippingId
     * @return
     */
    @RequestMapping("/createOrder/{shippingId}")
    public ServerResponse createOrderRestful(HttpSession session,
                                             @PathVariable("shippingId") Integer shippingId,BigDecimal totalPrice){
        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return ServerResponse.serverResponseBySuccess("未登录");
        }
        return  iOrderService.createOrder(userInfo.getId(),shippingId,totalPrice);
    }
    /***
     * 取消订单
     */
    @RequestMapping("/cancel.do")
    public ServerResponse cancel(HttpSession session,Long orderNo){
        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return ServerResponse.serverResponseBySuccess("未登录");
        }
        return  iOrderService.cancel(userInfo.getId(),orderNo);
    }
    /***
     * 取消订单Restful
     */
    @RequestMapping("/cancel/{orderNo}")
    public ServerResponse cancelRestful(HttpSession session,
                                 @PathVariable("orderNo") Long orderNo){
        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return ServerResponse.serverResponseBySuccess("未登录");
        }
        return  iOrderService.cancel(userInfo.getId(),orderNo);
    }

    /***
     * 订单明细
     */
    @RequestMapping("/get_order_cart_product.do")
    public ServerResponse get_order_cart_product(HttpSession session){
        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return ServerResponse.serverResponseBySuccess("未登录");
        }
        return  iOrderService.get_order_cart_product(userInfo.getId());
    }

    /***
     * 订单list
     */
    @RequestMapping("/list.do")
    public ServerResponse list(HttpSession session,
                               @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                               @RequestParam(required = false,defaultValue = "10") Integer pageSize){
        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return ServerResponse.serverResponseBySuccess("未登录");
        }
        return  iOrderService.list(userInfo.getId(),pageNum,pageSize);
    }

    /***
     * 订单详情
     */
    @RequestMapping("/detail.do")
    public ServerResponse detail(HttpSession session,Long orderNo){
        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return ServerResponse.serverResponseBySuccess("未登录");
        }
        return  iOrderService.detail(orderNo);
    }
    /***
     * 订单详情Restful
     */
    @RequestMapping("/detail/{orderNo}")
    public ServerResponse detailRestful(HttpSession session,
                                        @PathVariable("orderNo") Long orderNo){
        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return ServerResponse.serverResponseBySuccess("未登录");
        }
        return  iOrderService.detail(orderNo);
    }


    /***
     * 支付接口
     */
    @RequestMapping("/pay")
    public ServerResponse pay(HttpSession session,Long orderNo){
        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return ServerResponse.serverResponseBySuccess("未登录");
        }
        return  iOrderService.pay(orderNo);
    }

    /***
     * 支付接口Restful
     */
    @RequestMapping("/pay/{orderNo}")
    public ServerResponse payRestful(HttpSession session,
                                     @PathVariable("orderNo") Long orderNo){
        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return ServerResponse.serverResponseBySuccess("未登录");
        }
        return  iOrderService.pay(orderNo);
    }

    /***
     * 支付宝服务器回调应用服务器接口
     */
    @RequestMapping("/alipay_callback.do")
    public  ServerResponse callback(HttpServletRequest request){
        System.out.println("=======支付宝回调应用服务器======");
       Map<String,String[]> param =  request.getParameterMap();
        Map<String,String> map = Maps.newHashMap();
       Iterator<String> iterator = param.keySet().iterator();
       while (iterator.hasNext()){
           String key = iterator.next();
           String[] strArr = param.get(key);
           String value = "";
           for (int i=0;i<strArr.length;i++){
               value = (i==strArr.length-1)?value+strArr[i]:value+strArr[i]+",";
           }
           map.put(key,value);
       }
        //支付宝的验证签名
        boolean result;
        try {
           map.remove("sign_type");
            result = AlipaySignature.rsaCheckV2(map,Configs.getAlipayPublicKey(),"utf-8",Configs.getSignType());
            System.out.println("支付宝回调接口");
            if (!result){
                return  ServerResponse.serverResponseByError("非法请求");
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        return  iOrderService.alipay_callback(map);
    }
}
