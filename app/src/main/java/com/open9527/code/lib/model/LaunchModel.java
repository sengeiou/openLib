package com.open9527.code.lib.model;

import android.app.Activity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/9 17:28.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class LaunchModel implements Serializable {
    private String desc;
    private int id;
    private Class<? extends Activity>  clazz;

    public LaunchModel(String desc, Class<? extends Activity> clazz) {
        this.desc = desc;
        this.clazz = clazz;
    }

    public LaunchModel(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Class<? extends Activity> getClazz() {
        return clazz;
    }

    public void setClazz(Class<? extends Activity> clazz) {
        this.clazz = clazz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LaunchModel that = (LaunchModel) o;
        return id == that.id &&
                Objects.equals(desc, that.desc) &&
                Objects.equals(clazz, that.clazz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(desc, id, clazz);
    }
}
