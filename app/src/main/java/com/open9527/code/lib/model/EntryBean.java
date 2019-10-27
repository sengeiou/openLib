package com.open9527.code.lib.model;

import java.io.Serializable;

/**
 * Created by     : open9527
 * Created times  : on 2019/8/23 14:02.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class EntryBean implements Serializable {
    private String icon;
    private String title;
    private String desc;
    private String avatars;


    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAvatars() {
        return avatars;
    }

    public void setAvatars(String avatars) {
        this.avatars = avatars;
    }

    @Override
    public String toString() {
        return "EntryBean{" +
                "icon='" + icon + '\'' +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", avatars='" + avatars + '\'' +
                '}';
    }
}
