package com.LOLdaojucheng.dao;

import com.LOLdaojucheng.pojo.Shopping;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShoppingMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_shopping
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_shopping
     *
     * @mbg.generated
     */
    int insert(Shopping record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_shopping
     *
     * @mbg.generated
     */
    Shopping selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_shopping
     *
     * @mbg.generated
     */
    List<Shopping> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_shopping
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Shopping record);

    /***
     * 查询当前登录的用户的收货地址
     */
    Shopping selectByUserId(Integer userId);

    /***
     * 检查相同的大区只能有一个角色名
     */
    int checkJueSeName(@Param("daquName") String daquName,
                       @Param("jueseName") String jueseName);

    /***
     * 查询已登录用户的所有地址
     */
    List<Shopping> SelectAllByUserId(Integer userId);

    /***
     * 将被选中的地址设为不选中
     */
    int revokeChecked(Integer id);

    /***
     * 将被选中的地址设置为默认地址
     */
    int checked(Integer id);
    /***
     * 获得用户的第一条收货地址
     */
    Shopping getFirst(Integer userId);
}