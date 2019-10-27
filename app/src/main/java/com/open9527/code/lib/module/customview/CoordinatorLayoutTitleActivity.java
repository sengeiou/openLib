package com.open9527.code.lib.module.customview;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.utilcode.util.AdaptScreenUtils;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.android.material.appbar.AppBarLayout;
import com.open9527.code.common.databinding.BindingBaseCell;
import com.open9527.code.common.databinding.BindingBaseCellAdapter;
import com.open9527.code.common.databinding.CommonBindingActivity;
import com.open9527.code.common.databinding.interfaces.IBindingCellClickListener;
import com.open9527.code.lib.R;
import com.open9527.code.lib.cell.EmptyCell;
import com.open9527.code.lib.databinding.ActivityCoordinatorLayoutBinding;
import com.open9527.code.lib.model.LaunchModel;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/17 10:00.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class CoordinatorLayoutTitleActivity extends CommonBindingActivity<ActivityCoordinatorLayoutBinding> implements IBindingCellClickListener {
    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_coordinator_layout;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        //处理ivAvatar位置,需要获取到toolbar高度
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) mBinding.ivAvatar.getLayoutParams();
        mBinding.toolbar.post(new Runnable() {
            @Override
            public void run() {
                //4个参数按顺序分别是左上右下
                layoutParams.setMargins(AdaptScreenUtils.pt2Px(200), mBinding.toolbar.getHeight(), 0, 0);
                mBinding.ivAvatar.setLayoutParams(layoutParams);
            }
        });
        BarUtils.setStatusBarLightMode(this, true);
        BarUtils.setStatusBarColor(mBinding.viewStatusBar, ColorUtils.getColor(R.color.color_00_000));
        initAdapter();
        //144
        mBinding.appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
                LogUtils.i(TAG, "offset-->" + offset);
                LogUtils.i(TAG, "TotalScrollRange()-->" + appBarLayout.getTotalScrollRange());
                if (offset == 0) {
                    //展开状态
                    mBinding.clExpand.setVisibility(View.VISIBLE);
                    mBinding.clExpand.setAlpha(1.0f);
                    mBinding.toolbar.setAlpha(0f);
                } else if (Math.abs(offset) >= appBarLayout.getTotalScrollRange()) {
                    //折叠状态
                    mBinding.clExpand.setVisibility(View.INVISIBLE);
                    mBinding.clExpand.setAlpha(0f);
                    mBinding.toolbar.setAlpha(1.0f);
                } else {
                    //中间状态
                    mBinding.clExpand.setAlpha(0.5f);
                    mBinding.toolbar.setAlpha(0.5f);
                }
            }
        });


    }

    private void initAdapter() {
        mBinding.rvList.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rvList.setHasFixedSize(true);
        BindingBaseCellAdapter mAdapter = new BindingBaseCellAdapter();
        mBinding.rvList.setAdapter(mAdapter);
        List<BindingBaseCell> cellList = new LinkedList<>();
        for (int i = 0; i < 20; i++) {
            cellList.add(new EmptyCell(new LaunchModel("空布局" + i)));
        }
        mAdapter.setItems(cellList);
        mAdapter.setOnBindingCellClickListener(this);
    }

    @Override
    public void onItemClick(View view, int position, BindingBaseCell... bindingBaseCells) {
        final BindingBaseCell baseCell = bindingBaseCells[0];
        if (baseCell instanceof EmptyCell) {
            EmptyCell emptyCell = (EmptyCell) baseCell;
            ToastUtils.showShort(emptyCell.descObservableField.get().getDesc());
        }
    }
}
