package com.open9527.code.lib.samples.coordinatorlayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ColorUtils;
import com.google.android.material.appbar.AppBarLayout;
import com.open9527.code.common.databinding.CommonBindingActivity;
import com.open9527.code.common.recycleview.BaseCell;
import com.open9527.code.common.recycleview.BaseCellAdapter;
import com.open9527.code.lib.R;
import com.open9527.code.lib.databinding.ActivityCoordinatorLayoutBinding;
import com.open9527.code.lib.samples.recycleview.multiple.TextCell;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/19 12:37.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class CoordinatorLayoutActivity extends CommonBindingActivity<ActivityCoordinatorLayoutBinding> {
    @Override
    public CharSequence bindTitle() {
        return "";
    }

    @Override
    protected void setStatusBar() {
        mToolbar.setBackgroundColor(ColorUtils.getColor(R.color.color_00aaae));
        BarUtils.setStatusBarLightMode(this, true);
        BarUtils.setStatusBarColor(this, Color.argb(0, 0, 0, 0), true);
    }

    @Override
    public void initData(@Nullable Bundle bundle) {
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_coordinator_layout;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        List<BaseCell> list = new LinkedList<>();
        BaseCellAdapter mAdapter = new BaseCellAdapter<BaseCell>();
        for (int i = 0; i < 20; i++) {
            list.add(new TextCell());
        }
        mAdapter.setHasStableIds(true);
        mAdapter.setItems(list);
        mBinding.rvList.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rvList.setAdapter(mAdapter);
        mBinding.appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
                if (offset == 0) {
                    //展开状态
                    mToolbar.setBackgroundColor(ColorUtils.getColor(R.color.color_00aaae));
                    mBinding.CollapsingToolbarLayout.setTitle("");
                } else if (Math.abs(offset) >= appBarLayout.getTotalScrollRange()) {
                    //折叠状态
                    mToolbar.setBackgroundColor(ColorUtils.getColor(R.color.colorAccent));
                    mBinding.CollapsingToolbarLayout.setTitle("CoordinatorLayoutActivity");
                } else {
                    //中间状态
                    mBinding.CollapsingToolbarLayout.setTitle("");
                }
            }
        });
    }

    @Override
    public void doBusiness() {


    }

    @Override
    public void onDebouncingClick(@NonNull View view) {

    }
}
