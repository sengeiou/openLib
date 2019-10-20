package com.open9527.code.lib;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.open9527.code.common.databinding.BindingBaseCell;
import com.open9527.code.common.databinding.BindingBaseCellAdapter;
import com.open9527.code.common.databinding.CommonBindingFragment;
import com.open9527.code.common.databinding.interfaces.IBindingCellClickListener;
import com.open9527.code.lib.cell.EmptyCell;
import com.open9527.code.lib.cell.LaunchCell;
import com.open9527.code.lib.databinding.FragmentMainBinding;
import com.open9527.code.lib.model.LaunchModel;
import com.open9527.code.lib.module.bglib.BackgroundLibTitleActivity;
import com.open9527.code.lib.module.browser.BrowserTitleActivity;
import com.open9527.code.lib.module.customview.CoordinatorLayoutTitleActivity;
import com.open9527.code.lib.module.image.compression.CompressImageTitleActivity;
import com.open9527.code.lib.module.image.load.ImageLoadTitleActivity;
import com.open9527.code.lib.module.other.OtherTitleActivity;
import com.open9527.code.lib.module.other.RecycleViewActivity;
import com.open9527.code.lib.module.statelayout.StateTitleActivity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/17 16:48.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class MainFragment extends CommonBindingFragment<FragmentMainBinding> implements IBindingCellClickListener {
    private List<BindingBaseCell> cellList = new LinkedList<>();
    private BindingBaseCellAdapter mAdapter;

    @Override
    public void initData(@Nullable Bundle bundle) {
        mActivity.getWindow().setBackgroundDrawableResource(R.color.color_fff);
        BarUtils.setStatusBarLightMode(mActivity, true);
        cellList.add(new LaunchCell(new LaunchModel("图片压缩", CompressImageTitleActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("图片加载/预览", ImageLoadTitleActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("StateLayout", StateTitleActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("x5WebView", BrowserTitleActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("BackgroundLib", BackgroundLibTitleActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("CoordinatorLayout", CoordinatorLayoutTitleActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("other", OtherTitleActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("other--RecycleView", RecycleViewActivity.class)));
        cellList.add(new EmptyCell("空布局"));
    }

    @Override
    public void doLazyBusiness() {

    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_main;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        initAdapter();
    }

    private void initAdapter() {
        mBinding.rvList.setLayoutManager(new LinearLayoutManager(mActivity));
        mBinding.rvList.setHasFixedSize(true);
        mAdapter = new BindingBaseCellAdapter<>();
        mBinding.rvList.setAdapter(mAdapter);
        mAdapter.setItems(cellList);
        mAdapter.setOnBindingCellClickListener(this);
    }

    @Override
    public void onItemClick(View view, int position, BindingBaseCell... bindingBaseCells) {
        if (bindingBaseCells[0] instanceof LaunchCell) {
            LaunchCell cell1 = (LaunchCell) bindingBaseCells[0];
            LaunchModel launchModel = cell1.descObservableField.get();
            if (launchModel != null) {
                ActivityUtils.startActivity(launchModel.getClazz());
            } else {
                ToastUtils.showShort("cell is null !");
            }
        } else if (bindingBaseCells[0] instanceof EmptyCell) {
            EmptyCell cell1 = (EmptyCell) bindingBaseCells[0];
            if (view.getId() == R.id.tv_empty) {
                ToastUtils.showShort("文本内容" + position);
            } else {
                ToastUtils.showShort(cell1.msgObservable.get());
                mAdapter.addItem(new EmptyCell("添加的空布局"), true);
            }
        } else {
            LogUtils.i(TAG, "onItemClick");
        }
    }
}
