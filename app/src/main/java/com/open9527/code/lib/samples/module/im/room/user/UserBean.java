package com.open9527.code.lib.samples.module.im.room.user;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by     : open9527
 * Created times  : on 2019/8/29 17:28.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
@Entity(tableName = "user_table", indices = {@Index(value = {"uid"}, unique = true)})
public class UserBean {
    @PrimaryKey(autoGenerate = true)
    public Long _id;
    @SerializedName("uid")
    public int uid;
    @SerializedName("full_name")
    public String name;
    @SerializedName("mobile")
    public String mobile;
    @SerializedName("avatar")
    public String avatar;
    @SerializedName("label_name")
    public String label;
    @SerializedName("sex")
    public int sex;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
