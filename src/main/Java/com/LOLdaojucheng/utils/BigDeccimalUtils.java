package com.LOLdaojucheng.utils;

import org.junit.jupiter.api.Test;
import sun.awt.geom.AreaOp;

import java.math.BigDecimal;

public class BigDeccimalUtils {
    //执行加法运算
    public  static BigDecimal add(double d1,double d2){
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(d1));
        BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(d2));
        return bigDecimal.add(bigDecimal1);
    }
    //执行减法运算
    public  static BigDecimal sub(double d1,double d2){
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(d1));
        BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(d2));
        return bigDecimal.subtract(bigDecimal1);
    }
    //执行乘法运算
    public  static BigDecimal mul(double d1,double d2){
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(d1));
        BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(d2));
        return bigDecimal.multiply(bigDecimal1);
    }
    //执行除法运算
    public  static BigDecimal div(double d1,double d2){
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(d1));
        BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(d2));
        return bigDecimal.divide(bigDecimal1,2,BigDecimal.ROUND_HALF_UP);
    }
    @Test
    public void test(){
        System.out.println(add(0.01,0.02));
        System.out.println(sub(1,0.04));
        System.out.println(mul(0.05,0.05));
        System.out.println(div(0.05,0.01));
    }
}
