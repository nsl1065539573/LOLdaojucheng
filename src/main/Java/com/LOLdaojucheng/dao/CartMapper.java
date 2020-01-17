package com.LOLdaojucheng.dao;

import com.LOLdaojucheng.pojo.Cart;
import com.LOLdaojucheng.pojo.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_cart
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_cart
     *
     * @mbg.generated
     */
    int insert(Cart record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_cart
     *
     * @mbg.generated
     */
    Cart selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_cart
     *
     * @mbg.generated
     */
    List<Cart> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_cart
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Cart record);
    /***
     * 根据用户ID和商品ID查询购物车
     */
    Cart selectByUserIdAndProductId(@Param("userId") Integer userId,
                                    @Param("productId") Integer productId);

    /***
     * 根据userId查询购物车信息
     * @param userId
     * @return
     */
    List<Cart> selectByUserId(Integer userId);
    /***
     * 检查购物车是否全选
     */
    int isCheckedAll(Integer userId);
    /***
     * 删除购物车的某些商品
     */
    int delectByUserIdAndProductIdList(@Param("userId") Integer userId,
                                       @Param("productIdList") List<Integer> productIdList);
    /***
     * 操作购物车商品是否选中
     * @Param check 1 选中 0 未选中
     */
    int selectOrUnSelectProduct(@Param("userId") Integer userId,
                                @Param("productId") Integer productId,
                                @Param("check") Integer check);
    /***
     * 统计用户购物车中产品的数量
     */
    Integer get_cart_product_count(Integer userId);
    /***
     * 查询购物车中有几件商品
     */
    Integer get_count(Integer userId);
    /***
     * 查询购物车中用户已选中的商品
     */
    Integer get_quanti(@Param("userId") Integer userId,
                        @Param("productId") Integer productId);
    List<Cart> findCartListByUserId(Integer userId);
    /***
     * 批量删除购物车中商品
     */
    int batchDelete(List<Cart> cartList);
}