package com.open9527.code.lib.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/6 17:03.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class ShareElementBean implements Parcelable {
    public String url;
    public int width;
    public int height;

    public ShareElementBean(String url, int width, int height) {
        this.url = url;
        this.width = width;
        this.height = height;
    }


    public static final Creator<ShareElementBean> CREATOR = new Creator<ShareElementBean>() {
        @Override
        public ShareElementBean createFromParcel(Parcel in) {
            return new ShareElementBean(in);
        }

        @Override
        public ShareElementBean[] newArray(int size) {
            return new ShareElementBean[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ShareElementBean baseData = (ShareElementBean) o;
        return width == baseData.width &&
                height == baseData.height &&
                Objects.equals(url, baseData.url);
    }

    @Override
    public int hashCode() {

        return Objects.hash(url, width, height);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeInt(this.width);
        dest.writeInt(this.height);
    }

    protected ShareElementBean(Parcel in) {
        this.url = in.readString();
        this.width = in.readInt();
        this.height = in.readInt();
    }

}
