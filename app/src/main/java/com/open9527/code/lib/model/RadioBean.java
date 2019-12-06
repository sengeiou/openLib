package com.open9527.code.lib.model;

/**
 * Created by     : open9527
 * Created times  : on 2019/11/15 14:43.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class RadioBean extends SelectedBean {
    private String title;

    public RadioBean(String title) {
        this.title = title;
    }

    public RadioBean(String title, boolean hasSelected) {
        this.title = title;
        setHasSelected(hasSelected);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
