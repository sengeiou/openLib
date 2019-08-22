package com.open9527.code.network.exception;

import com.google.gson.JsonParseException;
import com.open9527.code.network.BuildConfig;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;

import retrofit2.HttpException;

/**
 * Created by     : Mr.kk.
 * Created times  : on 2019/7/23 18:59.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class NetError {
    public static String getErrorMessage(Throwable e) {
        NetException netException = new NetException(e);
        if (e instanceof ConnectException
                || e instanceof UnknownHostException
                || e instanceof UnknownServiceException
                || e instanceof HttpException) {
            netException.setShowMessage("网络已断开，请稍后连接网络。");
        } else if (e instanceof SocketTimeoutException) {
            netException.setShowMessage("请求超时, 请稍后再试。");
        } else if (e instanceof JsonParseException) {
            netException.setShowMessage("数据异常，请联系管理人员。");
        } else if (e instanceof NetException) {
            netException = (NetException) e;
        } else {
            netException.setShowMessage("请求失败, 请稍后再试。");
        }
        // showMessage 显示给用户的信息
        // message debug时打印的信息
        return BuildConfig.DEBUG ? netException.getMessage() : netException.getShowMessage();
//        return netException.getShowMessage();
    }
}
