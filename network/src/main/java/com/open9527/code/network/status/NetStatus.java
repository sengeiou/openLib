package com.open9527.code.network.status;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by     : Mr.kk.
 * Created times  : on 2019/7/23 18:46.
 * E-Mail Address ：open_9527@163.com.
 * DESC :请求状态.
 */
public class NetStatus {
    private final static int LOADING = 0;
    private final static int ERROR = -1;
    private final static int SUCCESS = 1;
    private final static int NULL = -2;//重置状态
    private final static int CODE = -3;//请求状态码

    public boolean isLoading() {
        return status == LOADING;
    }

    public boolean isError() {
        return status == ERROR;
    }

    public boolean isSuccess() {
        return status == SUCCESS;
    }

    @Nullable
    public String getMessage() {
        return message;
    }

    @NonNull
    public int getStatus() {
        return status;
    }

    @NonNull
    private final int status;
    @NonNull
    private final int code;

    public int getCode() {
        return code;
    }

    @Nullable
    private final String message;

    private NetStatus(@NonNull int status, @NonNull int code, @Nullable String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public static NetStatus success(String msg, int code) {
        return new NetStatus(SUCCESS, code, msg);
    }

    public static NetStatus error(String msg, int code) {
        return new NetStatus(ERROR, code, msg);
    }

    public static NetStatus loading() {
        return new NetStatus(LOADING, CODE, null);
    }

    public static NetStatus nul() {
        return new NetStatus(NULL, CODE, null);
    }
}
