package com.open9527.code.shareelement.transition;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

/**
 * Created by     : open9527
 * Created times  : on 2019/8/22 17:47.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class ViewStateSaver implements Parcelable {

    public ViewStateSaver() {
    }


    protected void captureViewInfo(View view, Bundle bundle) {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    protected ViewStateSaver(Parcel in) {
    }

    public static final Creator<ViewStateSaver> CREATOR = new Creator<ViewStateSaver>() {
        @Override
        public ViewStateSaver createFromParcel(Parcel source) {
            return new ViewStateSaver(source);
        }

        @Override
        public ViewStateSaver[] newArray(int size) {
            return new ViewStateSaver[size];
        }
    };
}
