package com.LOLdaojucheng.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

public class DateUtils {
    private static  final String STANDARD_FOMART = "yyyy-MM-dd HH:mm:ss";

    /***
     * 将时间转换成字符串
     */
    public static String DateToString(Date date,String fomart){
        DateTime dateTime = new DateTime(date);
        return  dateTime.toString(fomart);
    }
    public  static  String DateToString(Date date){
        DateTime dateTime = new DateTime(date);
        return  dateTime.toString(STANDARD_FOMART);
    }
    /***
     * 将字符串类型转换成时间类型
     */
    public static Date StringToDate(String s){
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(STANDARD_FOMART);
        DateTime dateTime = dateTimeFormatter.parseDateTime(s);
        return  dateTime.toDate();
    }
    public static Date StringToDate(String s,String format){
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(format);
        DateTime dateTime = dateTimeFormatter.parseDateTime(s);
        return  dateTime.toDate();
    }

    public static void main(String[] args){
        System.out.println(DateToString(new Date()));
        System.out.println(StringToDate("2018-11-02 17:13:01"));
    }
}
