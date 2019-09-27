package com.open9527.code.lib.model;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/27 17:28.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class RequestGitHubBean {


    /**
     * message : commit from INSOMNIA
     * content : bXkgbmV3IGZpbGUgY29udGVudHM=
     */

    private String message;
    private String content;

    public RequestGitHubBean(String message, String content) {
        this.message = message;
        this.content = content;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
