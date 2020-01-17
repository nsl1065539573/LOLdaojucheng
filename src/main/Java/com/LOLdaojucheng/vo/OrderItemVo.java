package com.LOLdaojucheng.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderItemVo implements Serializable {
    private Long orderNo;
    private  Integer productId;
    private String productName;
    private String productImage;
    private BigDecimal currenUniPrice;
    private Integer quantity;
    private BigDecimal totalPrice;
    private String createTimel;

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public BigDecimal getCurrenUniPrice() {
        return currenUniPrice;
    }

    public void setCurrenUniPrice(BigDecimal currenUniPrice) {
        this.currenUniPrice = currenUniPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCreateTimel() {
        return createTimel;
    }

    public void setCreateTimel(String createTimel) {
        this.createTimel = createTimel;
    }
}
