package com.open9527.code.network.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by     : Mr.kk.
 * Created times  : on 2019/7/23 19:30.
 * E-Mail Address ：open_9527@163.com.
 * DESC :RmtResponse.
 */
public class BaseResponse<T> implements IResponse<T> {
    protected static int SUCCESS_CODE = 0;//成功的code
    protected static int TOKEN_CODE = 401;//token的code

    @SerializedName("code")
    protected int code;
    @SerializedName("msg")
    protected String message;
    @SerializedName("data")
    protected T data;


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
