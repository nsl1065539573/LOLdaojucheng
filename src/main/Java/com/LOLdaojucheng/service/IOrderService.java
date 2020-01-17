package com.LOLdaojucheng.service;

import com.LOLdaojucheng.common.ServerResponse;
import com.google.common.collect.Maps;

import java.math.BigDecimal;
import java.util.Map;

public interface IOrderService {
    /***
     * 创建订单
     */
    ServerResponse createOrder(Integer userId, Integer shippingId, BigDecimal price);

    /***
     * 取消订单
     * @param id
     * @param orderNo
     * @return
     */
    ServerResponse cancel(Integer userId, Long orderNo);

    /***
     * 获取购物车中商品详情
     * @param id
     * @return
     */
    ServerResponse get_order_cart_product(Integer id);

    /***
     * 订单列表

     */
    ServerResponse list(Integer userId,Integer pageNum,Integer pageSize);

    /***
     * 订单详情
     * @param orderNo
     * @return
     */
    ServerResponse detail(Long orderNo);

    /***
     * 后台--- 订单列表
     */
    ServerResponse list(Integer pageNum,Integer pageSize);



    /***
     * 订单发货
     * @param orderNo
     * @return
     */
    ServerResponse send_goods(Long orderNo);

    /***
     * 支付接口
     */
    ServerResponse pay(Long orderNo);

    /***
     * 支付宝回调接口
     */
    ServerResponse alipay_callback(Map<String,String> map);

    /***
     * 根据创建时间查询订单
     */
    void closeOrder(String time);
}
