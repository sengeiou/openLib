package com.open9527.code.network.exception;

/**
 * Created by     : Mr.kk.
 * Created times  : on 2019/7/23 18:58.
 * E-Mail Address ：open_9527@163.com.
 * DESC :异常处理.
 */
public class NetException extends Throwable {
    private String showMessage;

    public NetException(Throwable e) {
        super(e.getMessage());
        showMessage = e.getMessage();
    }

    public NetException(Throwable e, String showMessage) {
        this(e);
        this.showMessage = showMessage;
    }

    public NetException(String message) {
        this(message, message);
    }

    public NetException(String message, String showMessage) {
        super(message);
        this.showMessage = showMessage;
    }

    public void setShowMessage(String showMessage) {
        this.showMessage = showMessage;
    }

    public String getShowMessage() {
        return showMessage;
    }
}