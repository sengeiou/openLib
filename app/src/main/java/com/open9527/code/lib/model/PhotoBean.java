package com.open9527.code.lib.model;

/**
 * Created by     : open9527
 * Created times  : on 2019/8/25 13:39.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class PhotoBean {
    private String name;
    private String url;
    private String desc;

    public PhotoBean(String name, String url, String desc) {
        this.name = name;
        this.url = url;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
