package com.LOLdaojucheng.common;

public class Const {
    public static final String CURRENTUSER = "currentuser";
    public static final String TRADE_SUCCESS="TRADE_SUCCESS";
    public static final String AUTOLOGINTOKEN="autoLoginToken";
    public static final String URL = "http://heswpz.natappfree.cc";
    public  enum  ResponseCode{
        NEED_LOGIN(5,"需要权限"),
        REEOR(100,"出错")
        ;
        private  int code;
        private  String desc;

        private ResponseCode(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    /***
     * 支付平台
     */
    public  enum  PaymentPlatForEnum{
        ALIPAY(1,"支付宝")
        ;
        private  Integer code;
        private  String desc;

        private PaymentPlatForEnum(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
    /***
     * 支付类型
     */
    public  enum  PAY_MENT{
        ONLINE(1,"线上支付")
        ;
        private  int code;
        private  String desc;

        private PAY_MENT(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
        public static PAY_MENT codeOf(Integer code){
            for (PAY_MENT PAY_MENT:
                    values()) {
                if (code==PAY_MENT.getCode()){
                    return PAY_MENT;
                }
            }
            return null;
        }

    }

    public enum  RoleEnum{
        ROLE_ADMIN (0,"管理员"),
        ROLE_CUSTOMER(1,"普通用户")
        ;
        private int code;
        private String desc;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        private RoleEnum(int code, String desc){
            this.code = code;
            this.desc = desc;

        }
    }
    public enum Category_Id{
        STATE_SHOUBAN(7,"手办");
        private int code;
        private String desc;
        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        private Category_Id(int code, String desc){
            this.code = code;
            this.desc = desc;

        }
    }
    public enum Product_state{
        STATE_ONLINE(1,"正常"),
        STATE_OFFLINE(2,"已下架"),
        STATE_DELECT(3,"已被删除"),
        STATE_HOTPRODUCT(4,"热门商品"),
        STATE_REBATEPRODUCT(5,"打折商品"),
        STATE_HOT(6,"热门排行那一列")
        ;
        private int code;
        private String desc;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        private Product_state(int code, String desc){
            this.code = code;
            this.desc = desc;

        }
    }
    public enum CART_CHECK{
        CATE_CHECKED(1,"已勾选"),
        CARE_UNCHECKED(0,"未勾选")

        ;
        private int code;
        private String desc;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        private CART_CHECK(int code, String desc){
            this.code = code;
            this.desc = desc;

        }
    }
    public enum ORDER_STATUS{
        ORDER_CANCELED(0,"已取消"),
        ORDER_UN_PAY(10,"未付款"),
        ORDER_PAYED(20,"已付款"),
        ORDER_SEND(40,"已发货"),
        ORDER_SUCCESS(50,"交易成功"),
        ORDER_CLOSEED(60,"交易关闭")

        ;
        private int code;
        private String desc;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        private ORDER_STATUS(int code, String desc){
            this.code = code;
            this.desc = desc;

        }
        public static ORDER_STATUS codeOf(Integer code){
            for (ORDER_STATUS ORDER_STATUS:
                 values()) {
                if (code==ORDER_STATUS.getCode()){
                    return ORDER_STATUS;
                }
            }
            return null;
        }
    }
}
