package com.LOLdaojucheng.dao;

import com.LOLdaojucheng.pojo.Order;
import com.LOLdaojucheng.pojo.OrderItem;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_order
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_order
     *
     * @mbg.generated
     */
    int insert(Order record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_order
     *
     * @mbg.generated
     */
    Order selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_order
     *
     * @mbg.generated
     */
    List<Order> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_order
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Order record);
    /***
     * 根据userId&orderNo查询订单
     */
    Order findOrderByuserIdAndOrderNo(@Param("userId") Integer userId,
                                      @Param("orderNo") Long orderNo);
    /***
     * 按照用户ID查询订单
     */
    List<Order> findOrderByUserId(Integer userId);
    /***
     * 根据订单号查询订单
     */
    Order findOrderByOrderNo(Long orderNo);

    /***
     * 按照创建时间查询订单
     */
    List<Order> findOrderByCreateTime(@Param("status") Integer status,
                                      @Param("time") String time);
}