package com.open9527.code.lib.model;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/29 12:35.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class CommitterBean {
    //author和committer参数具有相同的键：
    private String name;//需要。提交的作者或提交者的名称。如果省略名称，您将收到422状态代码。
    private String email;//需要。提交的作者或提交者的电子邮件。如果省略名称，您将收到422状态代码。

    public CommitterBean(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
