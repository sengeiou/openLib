package com.open9527.code.lib;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.CollectionUtils;
import com.blankj.utilcode.util.GsonUtils;
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
import com.open9527.code.lib.module.browser.WebViewAndRecycleView;
import com.open9527.code.lib.module.customview.BannerTitleActivity;
import com.open9527.code.lib.module.customview.CoordinatorLayoutTabLayoutActivity;
import com.open9527.code.lib.module.customview.CoordinatorLayoutTitleActivity;
import com.open9527.code.lib.module.customview.RadioRecycleViewActivity;
import com.open9527.code.lib.module.customview.TabLayoutTitleActivity;
import com.open9527.code.lib.module.dialog.DialogActivity;
import com.open9527.code.lib.module.fragment.common.LazyFragmentActivity;
import com.open9527.code.lib.module.image.compression.CompressImageTitleActivity;
import com.open9527.code.lib.module.image.load.ImageLoadTitleActivity;
import com.open9527.code.lib.module.customview.BottomNavigationActivity;
import com.open9527.code.lib.module.other.DrawableTitleActivity;
import com.open9527.code.lib.module.other.OtherTitleActivity;
import com.open9527.code.lib.module.customview.RecycleViewActivity;
import com.open9527.code.lib.module.other.YImagePickerTitleActivity;
import com.open9527.code.lib.module.rv.demo.RecycleViewDemoActivity;
import com.open9527.code.lib.module.smartswipe.SmartSwipeActivity;
import com.open9527.code.lib.module.statelayout.StateTitleActivity;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

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
        BarUtils.setStatusBarLightMode(mActivity, true);
        cellList.add(new LaunchCell(new LaunchModel("图片压缩", CompressImageTitleActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("图片加载/预览", ImageLoadTitleActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("StateLayout", StateTitleActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("WebView---x5WebView", BrowserTitleActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("WebView---WebViewAndRecycleView", WebViewAndRecycleView.class)));
        cellList.add(new LaunchCell(new LaunchModel("BackgroundLib", BackgroundLibTitleActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("customview--CoordinatorLayout", CoordinatorLayoutTitleActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("customview--RecycleView", RecycleViewActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("customview--RadioRecycleView", RadioRecycleViewActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("customview--RecycleViewDemoActivity", RecycleViewDemoActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("customview--BottomNavigation", BottomNavigationActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("customview--TabLayout", TabLayoutTitleActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("customview--Banner", BannerTitleActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("customview--CoordinatorLayoutTabLayout", CoordinatorLayoutTabLayoutActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("SmartSwipe", SmartSwipeActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("Dialog", DialogActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("other", OtherTitleActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("other--Drawables", DrawableTitleActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("other--小红书", YImagePickerTitleActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("other-- LazyFragment", LazyFragmentActivity.class)));
        cellList.add(new EmptyCell(new LaunchModel("空布局")));

//        cellList = CollectionUtils.newArrayList(
//
//        );
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
        mBinding.RefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMoreWithNoMoreData();
                refreshLayout.setEnableFooterFollowWhenNoMoreData(true);
            }
        });
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
            EmptyCell emptyCell = (EmptyCell) bindingBaseCells[0];
            if (view.getId() == R.id.tv_empty) {
                ToastUtils.showShort("文本内容" + position);
                emptyCell.stringObservableField.set("点击文本内容" + position);
            } else {
                ToastUtils.showShort(emptyCell.stringObservableField.get());
                mAdapter.addItem(new EmptyCell(new LaunchModel("添加的空布局")), true);
            }
        } else {
            LogUtils.i(TAG, "onItemClick");
        }
    }
}
