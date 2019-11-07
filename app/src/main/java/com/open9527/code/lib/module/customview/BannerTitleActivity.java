package com.open9527.code.lib.module.customview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Message;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.open9527.code.common.databinding.BindingBaseCell;
import com.open9527.code.common.databinding.BindingBaseCellAdapter;
import com.open9527.code.common.databinding.CommonBindingTitleActivity;
import com.open9527.code.customview.banner.BannerLayoutManager;
import com.open9527.code.customview.banner.BannerPageSnapHelper;
import com.open9527.code.image.utils.LifecycleHandler;
import com.open9527.code.lib.R;
import com.open9527.code.lib.cell.BannerCell;
import com.open9527.code.lib.cell.IndicatorCell;
import com.open9527.code.lib.databinding.ActivityBannerBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/30 14:20.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class BannerTitleActivity extends CommonBindingTitleActivity<ActivityBannerBinding> {
    List<BindingBaseCell> cellList = new ArrayList<>();
    private List<BindingBaseCell> bindingBaseCells;
    private BindingBaseCellAdapter<BindingBaseCell> mIndicatorAdapter;

    @Override
    public void initData(@Nullable Bundle bundle) {
        for (int i = 0; i < 10; i++) {
            cellList.add(new BannerCell("http://pic1.ytqmx.com:82/2015/0305/07/002.jpg%21960.jpg", "海贼王" + i));
        }
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_banner;
    }

    @Override
    public boolean hasContentScroll() {
        return true;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        initAdapter();
        initAdapter1();
        initAdapter2();
        initIndicatorAdapter();
        applyDebouncingClickListener(mBinding.tvStart);
    }


    private void initAdapter() {
        mBinding.rvList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mBinding.rvList);
        BindingBaseCellAdapter<BindingBaseCell> mAdapter = new BindingBaseCellAdapter<>();
        mBinding.rvList.setAdapter(mAdapter);
        mAdapter.addItems(cellList);
    }

    private void initAdapter1() {
        BannerLayoutManager bannerLayoutManager = new BannerLayoutManager();
        bannerLayoutManager.setHeightScale(1.0f);
        bannerLayoutManager.setWidthScale(1.0f);
        mBinding.rvList1.setLayoutManager(bannerLayoutManager);
        BannerPageSnapHelper bannerPageSnapHelper = new BannerPageSnapHelper();
        bannerPageSnapHelper.attachToRecyclerView(mBinding.rvList1);
        BindingBaseCellAdapter<BindingBaseCell> mAdapter = new BindingBaseCellAdapter<>();
        mBinding.rvList1.setAdapter(mAdapter);
        mAdapter.addItems(cellList);
    }

    private void initAdapter2() {
        BannerLayoutManager bannerLayoutManager = new BannerLayoutManager();
        bannerLayoutManager.setHeightScale(1.0f);
        bannerLayoutManager.setWidthScale(0.8f);
        mBinding.rvList2.setLayoutManager(bannerLayoutManager);
        BannerPageSnapHelper bannerPageSnapHelper = new BannerPageSnapHelper();
        bannerPageSnapHelper.attachToRecyclerView(mBinding.rvList2);
        BindingBaseCellAdapter<BindingBaseCell> mAdapter = new BindingBaseCellAdapter<>();
        mBinding.rvList2.setAdapter(mAdapter);
        mAdapter.addItems(cellList);
    }

    private void initIndicatorAdapter() {
        mBinding.rvIndicator.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        mIndicatorAdapter = new BindingBaseCellAdapter<>();
        mBinding.rvIndicator.setAdapter(mIndicatorAdapter);
        bindingBaseCells = new ArrayList<>(cellList.size());
        for (int i = 0; i < cellList.size(); i++) {
            bindingBaseCells.add(new IndicatorCell(i == 0));
        }
        mIndicatorAdapter.addItems(bindingBaseCells);
    }


    @Override
    public void onDebouncingClick(@NonNull View view) {
        switch (view.getId()) {
            case R.id.tv_start:
                startScroll(1);
                break;
            default:
                break;
        }
    }

    private void startScroll(int pos) {
        int size = cellList.size();
        if (size <= 1) return;
        if (pos < size) {
            lifecycleHandler.sendEmptyMessageDelayed(pos, 2500);
        } else {
            lifecycleHandler.sendEmptyMessageDelayed(0, 2500);
        }
    }

    @SuppressLint("HandlerLeak")
    private LifecycleHandler lifecycleHandler = new LifecycleHandler(this) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            LogUtils.i(TAG, "msg-->" + msg.what);
            mBinding.rvList2.smoothScrollToPosition(msg.what);
            setIndicator(msg.what);

            startScroll(msg.what + 1);
        }
    };

    private void setIndicator(int pos) {
        for (int i = 0; i < bindingBaseCells.size(); i++) {
            mIndicatorAdapter.replaceItem(i, new IndicatorCell(pos == i), true);
        }
    }
}
