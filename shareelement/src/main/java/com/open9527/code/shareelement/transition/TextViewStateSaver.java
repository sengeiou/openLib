package com.open9527.code.shareelement.transition;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by     : open9527
 * Created times  : on 2019/8/22 17:47.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class TextViewStateSaver extends ViewStateSaver {


    public float getTextSize(Bundle bundle){
        return bundle.getFloat("textSize");
    }

    public int getTextColor(Bundle bundle){
        return bundle.getInt("textColor");
    }

    @Override
    protected void captureViewInfo(View view, Bundle bundle) {
        super.captureViewInfo(view, bundle);
        if (view instanceof TextView) {
            bundle.putFloat("textSize", ((TextView) view).getTextSize());
            bundle.putInt("textColor", ((TextView) view).getCurrentTextColor());
        }
    }
}
