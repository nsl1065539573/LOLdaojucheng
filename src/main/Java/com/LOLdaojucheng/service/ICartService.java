package com.LOLdaojucheng.service;

import com.LOLdaojucheng.common.ServerResponse;

public interface ICartService {
    /***
     * 购物车添加商品
     */
    public ServerResponse add(Integer userId,Integer productId,Integer count);

    /***
     * 购物车列表
     * @return
     */
    ServerResponse list(Integer userId);

    /***
     * 更新商品数量
     * @param userId
     * @param productId
     * @param count
     * @return
     */
    ServerResponse update(Integer userId, Integer productId, Integer count);

    /***
     * 删除购物车中某些商品
     * @param userId
     * @param productIds
     * @return
     */
    ServerResponse delectProducts(Integer userId, String productIds);

    /***
     * 选中某个商品
     */
    ServerResponse select(Integer id, Integer productId,Integer check);

    /***
     * 查询购物车中产品的数量
     * @param id
     * @return
     */
    ServerResponse get_cart_product_count(Integer id);
    ServerResponse get_quanti(Integer userId,Integer productId);
    /***
     * 购物车全选
     */
    ServerResponse select_all(Integer userId, Integer productId, Integer check);
    /***
     * 获得购物车中商品数量
     */
    ServerResponse get_count(Integer userId);

}
