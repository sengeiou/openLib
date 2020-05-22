package com.open9527.code.lib.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class GalleryBean implements Parcelable {
    private int index;
    private ArrayList<String> list;

    public GalleryBean(int index, ArrayList<String> list) {
        this.index = index;
        this.list = list;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.index);
        dest.writeStringList(this.list);
    }

    protected GalleryBean(Parcel in) {
        this.index = in.readInt();
        this.list = in.createStringArrayList();
    }

    public static final Parcelable.Creator<GalleryBean> CREATOR = new Parcelable.Creator<GalleryBean>() {
        @Override
        public GalleryBean createFromParcel(Parcel source) {
            return new GalleryBean(source);
        }

        @Override
        public GalleryBean[] newArray(int size) {
            return new GalleryBean[size];
        }
    };
}
