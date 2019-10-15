package com.open9527.code.lib.module.bglib;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.AdaptScreenUtils;
import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.noober.background.drawable.DrawableCreator;
import com.open9527.code.common.databinding.CommonBindingActivity;
import com.open9527.code.lib.R;
import com.open9527.code.lib.databinding.ActivityBgLibBinding;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/12 15:18.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class BackgroundLibActivity extends CommonBindingActivity<ActivityBgLibBinding> {

    private int i = 1;
    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_bg_lib;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        //添加点击事件
        applyDebouncingClickListener(mBinding.btLikeLeft, mBinding.btLikeRight);
    }

    @Override
    public void doBusiness() {
        //设置button圆角背景
        Drawable drawable = new DrawableCreator.Builder().setCornersRadius(AdaptScreenUtils.pt2Px(20))
                .setGradientAngle(0).setGradientColor(ColorUtils.getColor(R.color.color_00aaae), ColorUtils.getColor(R.color.color_d81b60)).build();
        mBinding.btRadius.setBackground(drawable);

        //文字点击变色
        //由于Android源码的原因，必须调用，否则不生效
        mBinding.tvChangeColor.setClickable(true);
        ColorStateList colors = new DrawableCreator.Builder().setPressedTextColor(Color.RED).setUnPressedTextColor(Color.BLUE).buildTextColor();
        mBinding.tvChangeColor.setTextColor(colors);

    }

    @Override
    public void onDebouncingClick(@NonNull View view) {
        switch (view.getId()) {
            case R.id.bt_like_left:
                //点赞
                onLike((Button) view);
                break;
            case R.id.bt_like_right:
                onPressedLike((Button) view);
                break;
            default:
                break;
        }
    }

    /**
     * 点赞
     *
     * @param view
     */
    private void onPressedLike(Button view) {
        view.setText(String.valueOf("点赞  +" + (i++)));
    }

    /**
     * 点赞
     *
     * @param view
     */
    private void onLike(Button view) {
        if (view.isSelected()) {
            view.setText("未点赞");
        } else {
            view.setText("已点赞");
        }
        view.setSelected(!view.isSelected());
    }

    /**
     * 事件
     */
    public void function() {
        ToastUtils.showShort("响应事件");
    }
}
