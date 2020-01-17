package com.LOLdaojucheng.common;

import org.junit.jupiter.api.Test;

public class  ServerResponse<T> {
    private int status;//响应回来的状态码，判断是前台用户还是后台用户
    private String msg;//提示信息
    private T data;//响应回来的数据 可能是集合，字符串或者一个类 所以使用泛型
    //无参构造
    private ServerResponse(){

    }
    //只响应回来一个状态码
    private ServerResponse(int status){
        this.status = status;
    }
    //响应回来状态码以及数字
    private ServerResponse(int status,T data){
        this.status = status;
        this.data = data;
    }
    private ServerResponse(int status,T data,String msg){
        this.status = status;
        this.data  = data;
        this.msg = msg;
    }
    private  ServerResponse(int status,String msg){
        this.msg = msg;
        this.status = status;
    }

    //调用接口成功
    public static ServerResponse serverResponseBySuccess(){
        return new ServerResponse(ServerResponseCode.SUCCESS);
    }
    //调用接口成功且返回data
    public static ServerResponse serverResponseBySuccess(String msg){
        return new ServerResponse(ServerResponseCode.SUCCESS,msg);
    }
    public static <T> ServerResponse serverResponseBySuccess(T data){
        return new ServerResponse(ServerResponseCode.SUCCESS,data);
    }
    //调用接口成功且返回状态码 数据 以及提示信息
    public static <T>ServerResponse serverResponseBySucess(T data,String msg){
        return new ServerResponse(ServerResponseCode.SUCCESS,data,msg);
    }
    //调用接口失败 返回状态码
    public static ServerResponse serverResponseByError(){
        return  new ServerResponse(ServerResponseCode.ERROR);
    }
    //调用接口失败 返回数据
    public static <T>ServerResponse serverResponseByError(T data){
        return new ServerResponse(ServerResponseCode.ERROR,data);
    }
    //调用接口失败  返回状态码和提示信息
    public static ServerResponse serverResponseByError(String msg){
        return new ServerResponse(ServerResponseCode.ERROR,msg);
    }

    //判断接口是否调用成功
    public  boolean isSuccess(){
        return this.status == ServerResponseCode.SUCCESS;
    }
    @Override
    public String toString() {
        return "ServerResponse{" +
                "status=" + status +
                ", data=" + data+
                ", msg='" + msg + '\''+
                '}';
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

//    public static void main(String[] args){
//        ServerResponse serverResponse = ServerResponse.serverResponseBySucess(new Object(),"cao");
//        ServerResponse serverResponse1 = ServerResponse.serverResponseByError("失败");
//        System.out.print(serverResponse1);
//    }

}
