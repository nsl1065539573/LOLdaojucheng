package com.LOLdaojucheng.controller.back;


import com.LOLdaojucheng.common.ServerResponse;
import com.LOLdaojucheng.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/manage/order")
public class OrderController_back {
    @Autowired
    IOrderService iOrderService;
    /***
     * 后台商品列表
     */
    @RequestMapping("/list.do")
    public ServerResponse list(HttpSession session,
                               @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                               @RequestParam(required = false,defaultValue = "10") Integer pageSize){

        return  iOrderService.list(pageNum,pageSize);
    }
    /***
     * .按订单号查询
     */
    @RequestMapping("/search.do")
    public ServerResponse search(HttpSession session, Long orderNo){

        return  iOrderService.detail(orderNo);
    }
    /***
     * .按订单号查询Restful
     */
    @RequestMapping("/search/{orderNo}")
    public ServerResponse searchRestful(HttpSession session,
                                 @PathVariable("orderNo") Long orderNo){

        return  iOrderService.detail(orderNo);
    }

    /***
     *按订单号查询订单详情
     */
    @RequestMapping("/detail.do")
    public ServerResponse detail(HttpSession session,Long orderNo){

        return  iOrderService.detail(orderNo);
    }
    /***
     *按订单号查询订单详情
     */
    @RequestMapping("/detail/{orderNo}")
    public ServerResponse detailRestful(HttpSession session,
                                        @PathVariable("orderNo") Long orderNo){

        return  iOrderService.detail(orderNo);
    }
    /***
     * 订单发货
     */
    @RequestMapping("/send_goods.do")
    public ServerResponse send_goods(HttpSession session,Long orderNo){

        return  iOrderService.send_goods(orderNo);
    }
    /***
     * 订单发货
     */
    @RequestMapping("/send_goods/{orderNo}")
    public ServerResponse send_goodsRestful(HttpSession session,
                                            @PathVariable("orderNo") Long orderNo){

        return  iOrderService.send_goods(orderNo);
    }
}
