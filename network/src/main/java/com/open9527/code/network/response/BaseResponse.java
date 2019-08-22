package com.open9527.code.network.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by     : Mr.kk.
 * Created times  : on 2019/7/23 19:30.
 * E-Mail Address ：open_9527@163.com.
 * DESC :RmtResponse.
 */
public class BaseResponse<T> implements IResponse<T> {
    private static int SUCCESS_CODE = 0;//成功的code
    private static int TOKEN_CODE = 401;//token的code

    @SerializedName("code")
    private int code;
    @SerializedName("msg")
    private String message;
    @SerializedName("data")
    private T data;


    public BaseResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    @Override
    public int code() {
        return this.code;
    }

    @Override
    public String message() {
        return this.message;
    }

    @Override
    public T data() {
        return this.data;
    }

    @Override
    public boolean success() {
        return this.code == SUCCESS_CODE;
    }

    @Override
    public boolean timeOut() {
        return this.code == TOKEN_CODE;
    }
}
