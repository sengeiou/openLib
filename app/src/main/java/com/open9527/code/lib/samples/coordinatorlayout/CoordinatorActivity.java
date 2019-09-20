package com.open9527.code.lib.samples.coordinatorlayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.AdaptScreenUtils;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ViewUtils;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.open9527.code.common.activity.CommonScreenActivity;
import com.open9527.code.common.recycleview.BaseCell;
import com.open9527.code.common.recycleview.BaseCellAdapter;
import com.open9527.code.lib.R;
import com.open9527.code.lib.samples.recycleview.multiple.TextCell;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/19 17:51.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class CoordinatorActivity extends CommonScreenActivity {
    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_coordinator;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        RecyclerView recyclerView = findViewById(R.id.rv_list);
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.CollapsingToolbarLayout);
        View statusBar = findViewById(R.id.view_status_bar);
        AppBarLayout appBarLayout = findViewById(R.id.appBarLayout);

        ImageView imageView = findViewById(R.id.iv_head);
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView title = findViewById(R.id.tv_title);
        TextView name = findViewById(R.id.tv_name);
        ImageView avatar = findViewById(R.id.iv_avatar);
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) avatar.getLayoutParams();
        toolbar.post(new Runnable() {
            @Override
            public void run() {
                layoutParams.setMargins(AdaptScreenUtils.pt2Px(200), toolbar.getHeight(), 0, 0);//4个参数按顺序分别是左上右下
                avatar.setLayoutParams(layoutParams);
            }
        });
        BarUtils.setStatusBarLightMode(this, true);
        BarUtils.setStatusBarColor(statusBar, ColorUtils.getColor(R.color.color_00_000));
//        BarUtils.setStatusBarColor(this,ColorUtils.getColor(R.color.color_00_000));
        List<BaseCell> list = new LinkedList<>();
        BaseCellAdapter mAdapter = new BaseCellAdapter<BaseCell>();
        for (int i = 0; i < 20; i++) {
            list.add(new TextCell());
        }
        mAdapter.setHasStableIds(true);
        mAdapter.setItems(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
                if (offset == 0) {
                    //展开状态
                    name.setAlpha(1.0f);
                    avatar.setAlpha(1.0f);
                    avatar.setVisibility(View.VISIBLE);
                    name.setVisibility(View.VISIBLE);


                    imageView.setAlpha(1.0f);
                    toolbar.setAlpha(0f);



                } else if (Math.abs(offset) >= appBarLayout.getTotalScrollRange()) {
                    //折叠状态
                    name.setAlpha(0f);
                    avatar.setAlpha(0f);
                    avatar.setVisibility(View.INVISIBLE);
                    name.setVisibility(View.INVISIBLE);


                    imageView.setAlpha(0f);
                    toolbar.setAlpha(1.0f);
                } else {
                    //中间状态
                    imageView.setAlpha(0.5f);
                    toolbar.setAlpha(0.5f);
                    name.setAlpha(0.5f);
                    avatar.setAlpha(0.5f);
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
