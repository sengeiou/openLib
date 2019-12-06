package com.open9527.code.common.databinding.bindingadapter.imageview;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.blankj.utilcode.util.LogUtils;
import com.open9527.code.common.R;
import com.open9527.code.image.imageload.ImageLoadConfig;
import com.open9527.code.image.imageload.ImageLoadManger;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/21 19:43.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class ViewAdapter {
    private static final String TAG = "ViewAdapter";

    @BindingAdapter(value = {"bindingImage", "bindingImageRadius"}, requireAll = false)
    public static void bindingImage(ImageView imageView, String url, int imageRadius) {
//        LogUtils.i(TAG, url);
        ImageLoadManger.display(imageView.getContext(), imageView, url, new ImageLoadConfig(0, R.drawable.image_placeholder, imageRadius), null);

    }


    @BindingAdapter(value = {"bindingResId"}, requireAll = false)
    public static void bindingResId(ImageView imageView, int resId) {
        imageView.setImageResource(resId);
    }
}
