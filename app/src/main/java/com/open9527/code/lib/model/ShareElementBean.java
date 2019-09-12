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

    public ShareElementBean() {
    }

    protected ShareElementBean(Parcel in) {
        this.url = in.readString();
        this.width = in.readInt();
        this.height = in.readInt();
    }

    public static final Parcelable.Creator<ShareElementBean> CREATOR = new Parcelable.Creator<ShareElementBean>() {
        @Override
        public ShareElementBean createFromParcel(Parcel source) {
            return new ShareElementBean(source);
        }

        @Override
        public ShareElementBean[] newArray(int size) {
            return new ShareElementBean[size];
        }
    };
}
