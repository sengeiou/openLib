package com.open9527.code.network.response;

/**
 * Created by     : Mr.kk.
 * Created times  : on 2019/7/23 17:02.
 * E-Mail Address ：open_9527@163.com.
 * DESC :Response.
 */
public interface IResponse<T> {

    int code();//状态码

    String message();//请求信息

    T data();//请求结果

    boolean success();//成功

    boolean timeOut();//超时
}
