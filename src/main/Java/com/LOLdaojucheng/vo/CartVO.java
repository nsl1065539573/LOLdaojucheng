package com.LOLdaojucheng.vo;

import java.math.BigDecimal;
import java.util.List;

/***
 *
 * 购物车实体类
 */
public class CartVO {
    //购物信息集合
    private List<CartProductVO> cartProductVOList;
    //是否全选
    private boolean isAllChecked;
    //总价格
    private BigDecimal carttotalprice;

    public List<CartProductVO> getCartProductVOList() {
        return cartProductVOList;
    }

    public void setCartProductVOList(List<CartProductVO> cartProductVOList) {
        this.cartProductVOList = cartProductVOList;
    }


    public boolean isAllChecked() {
        return isAllChecked;
    }

    public void setAllChecked(boolean allChecked) {
        isAllChecked = allChecked;
    }

    public BigDecimal getCarttotalprice() {
        return carttotalprice;
    }

    public void setCarttotalprice(BigDecimal carttotalprice) {
        this.carttotalprice = carttotalprice;
    }
}
