package com.open9527.code.lib.model;

import java.io.Serializable;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/6 13:58.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class IntentBean implements Serializable {
    private String key;
    private Object value;

    public IntentBean(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
