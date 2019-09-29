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

    private String message;//提交信息
    private String content;//提交文件 base64
        private String sha;//如果要更新文件，则为必需。被替换文件的Blob SHA
//    private String branch;//分支名称。默认值：存储库的默认分支（通常是主分支）
    private CommitterBean author;//文件的作者。默认值：提交者或经过身份验证的用户（如果您省略提交者）。
    private CommitterBean committer;//提交文件的人。默认值：经过身份验证的用户。(name,email)


    public RequestGitHubBean(String message, String content,String sha , CommitterBean committer, CommitterBean author) {
        this.message = message;
        this.content = content;
        this.sha = sha;
        this.committer = committer;
        this.author = author;
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

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public CommitterBean getCommitter() {
        return committer;
    }

    public void setCommitter(CommitterBean committer) {
        this.committer = committer;
    }

    public CommitterBean getAuthor() {
        return author;
    }

    public void setAuthor(CommitterBean author) {
        this.author = author;
    }
}
