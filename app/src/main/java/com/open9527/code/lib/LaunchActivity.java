package com.open9527.code.lib;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.open9527.code.common.databinding.BindingBaseCell;
import com.open9527.code.common.databinding.BindingBaseCellAdapter;
import com.open9527.code.common.databinding.CommonBindingActivity;
import com.open9527.code.common.databinding.interfaces.IBindingCellClickListener;
import com.open9527.code.common.recycleview.interfaces.ICellClickListener;
import com.open9527.code.lib.cell.LaunchCell;
import com.open9527.code.lib.databinding.ActivityLaunchBinding;
import com.open9527.code.lib.model.LaunchModel;
import com.open9527.code.lib.module.bglib.BackgroundLibActivity;
import com.open9527.code.lib.module.browser.BrowserActivity;
import com.open9527.code.lib.module.image.compression.CompressImageActivity;
import com.open9527.code.lib.module.image.load.ImageLoadActivity;
import com.open9527.code.lib.module.other.OtherActivity;
import com.open9527.code.lib.module.statelayout.StateActivity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/9 17:01.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class LaunchActivity extends CommonBindingActivity<ActivityLaunchBinding> implements IBindingCellClickListener {

    private List<BindingBaseCell> cellList = new LinkedList<>();

    @Override
    public CharSequence bindTitle() {
        return "LaunchActivity";
    }

    @Override
    public void initData(@Nullable Bundle bundle) {
        cellList.add(new LaunchCell(new LaunchModel("图片压缩", CompressImageActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("图片加载/预览", ImageLoadActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("StateLayout", StateActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("x5WebView", BrowserActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("BackgroundLib", BackgroundLibActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("other", OtherActivity.class)));
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_launch;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        initAdapter();
    }

    private void initAdapter() {
        mBinding.includeRecycleview.commonRv.setLayoutManager(new LinearLayoutManager(this));
        mBinding.includeRecycleview.commonRv.setHasFixedSize(true);
        BindingBaseCellAdapter mAdapter = new BindingBaseCellAdapter<>();
        mBinding.includeRecycleview.commonRv.setAdapter(mAdapter);
        mAdapter.setItems(cellList);
        mAdapter.setOnCellClickListener(this);
    }

    @Override
    public void onItemClick(View view, int position, BindingBaseCell cell) {
        if (cell instanceof LaunchCell) {
            LaunchCell cell1 = (LaunchCell) cell;
            LaunchModel launchModel = cell1.descObservableField.get();
            if (launchModel != null) {
                ActivityUtils.startActivity(launchModel.getClazz());
            } else {
                ToastUtils.showShort("cell is null !");
            }
        }
    }
}
