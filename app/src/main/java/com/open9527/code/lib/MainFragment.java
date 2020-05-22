package com.open9527.code.lib;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.open9527.code.common.databinding.BindingBaseCell;
import com.open9527.code.common.databinding.BindingBaseCellAdapter;
import com.open9527.code.common.databinding.CommonBindingFragment;
import com.open9527.code.common.databinding.interfaces.IBindingCellClickListener;
import com.open9527.code.common.recycleview.GridSpaceItemDecoration;
import com.open9527.code.common.recycleview.SpacesItemDecoration;
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
import com.open9527.code.lib.module.empty.EmptyActivity;
import com.open9527.code.lib.module.fragment.common.LazyFragmentActivity;
import com.open9527.code.lib.module.image.compression.CompressImageTitleActivity;
import com.open9527.code.lib.module.image.load.ImageLoadTitleActivity;
import com.open9527.code.lib.module.customview.BottomNavigationActivity;
import com.open9527.code.lib.module.image.preview.GalleryActivity;
import com.open9527.code.lib.module.other.DrawableTitleActivity;
import com.open9527.code.lib.module.other.OtherTitleActivity;
import com.open9527.code.lib.module.customview.RecycleViewActivity;
import com.open9527.code.lib.module.other.YImagePickerTitleActivity;
import com.open9527.code.lib.module.permission.PermissionActivity;
import com.open9527.code.lib.module.rv.click.ClickItemCell;
import com.open9527.code.lib.module.rv.click.ICellClick;
import com.open9527.code.lib.module.rv.click.RecycleViewClickActivity;
import com.open9527.code.lib.module.rv.demo.RecycleViewDemoActivity;
import com.open9527.code.lib.module.smartswipe.SmartSwipeActivity;
import com.open9527.code.lib.utils.WhitelistUtils;
import com.open9527.recycleview.adapter.BaseBindingCell;
import com.open9527.recycleview.adapter.BaseBindingCellAdapter;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/17 16:48.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class MainFragment extends CommonBindingFragment<FragmentMainBinding> {
    private List<BaseBindingCell> cellList = new LinkedList<>();
    private MainViewModel mViewModel;
    private BaseBindingCellAdapter<BaseBindingCell> mAdapter;

    @Override
    public void initData(@Nullable Bundle bundle) {
        BarUtils.setStatusBarLightMode(mActivity, true);
        mViewModel = getFragmentViewModel(MainViewModel.class);

        cellList.add(new LaunchCell(new LaunchModel("自定义相册", GalleryActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("图片压缩", CompressImageTitleActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("图片加载/预览", ImageLoadTitleActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("WebView---x5WebView", BrowserTitleActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("WebView---WebViewAndRecycleView", WebViewAndRecycleView.class)));
        cellList.add(new LaunchCell(new LaunchModel("BackgroundLib", BackgroundLibTitleActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("customview--CoordinatorLayout", CoordinatorLayoutTitleActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("customview--RecycleView", RecycleViewActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("customview--RadioRecycleView", RadioRecycleViewActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("customview--RecycleViewDemoActivity", RecycleViewDemoActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("customview--RecycleViewItemClick", RecycleViewClickActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("customview--BottomNavigation", BottomNavigationActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("customview--TabLayout", TabLayoutTitleActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("customview--BannerView", BannerTitleActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("customview--CoordinatorLayoutTabLayout", CoordinatorLayoutTabLayoutActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("SmartSwipe", SmartSwipeActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("Dialog", DialogActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("other", OtherTitleActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("other--Drawables", DrawableTitleActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("other--小红书", YImagePickerTitleActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("other-- LazyFragment", LazyFragmentActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("PermissionActivity-权限获取", PermissionActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("EmptyActivity-默认布局", EmptyActivity.class)));
        cellList.add(new LaunchCell(new LaunchModel("other-- 配置白名单", null)));
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
        mBinding.setVm(mViewModel);
        mBinding.setLayoutManager(new GridLayoutManager(mActivity, 1));
        mBinding.setItemDecoration(getGridSpaceItemDecoration());
        mBinding.setAdapter(mAdapter = new BaseBindingCellAdapter<>());
        mAdapter.setItems(cellList);
    }

    private RecyclerView.ItemDecoration getSpacesItemDecoration() {
        SpacesItemDecoration spacesItemDecoration = new SpacesItemDecoration(mActivity, SpacesItemDecoration.VERTICAL).setNoShowDivider(0, 0);
        spacesItemDecoration.setParam(R.color.color_222, SizeUtils.dp2px(10));
        return spacesItemDecoration;
    }

    private RecyclerView.ItemDecoration getGridSpaceItemDecoration() {
        return new GridSpaceItemDecoration(SizeUtils.dp2px(10), true).setNoShowSpace(0, 0);

    }


    /**
     * 测试  白名单 小米,华为
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void startSetting() {
        if (WhitelistUtils.isIgnoringBatteryOptimizations()) {
            if (WhitelistUtils.isHuawei()) {//华为
                WhitelistUtils.goHuaweiSetting();
            } else if (WhitelistUtils.isXiaomi()) {//小米
                WhitelistUtils.goXiaomiSetting();
            } else if (WhitelistUtils.isOPPO()) {//oppo
                WhitelistUtils.goOPPOSetting();
            } else if (WhitelistUtils.isVIVO()) {//vivo
                WhitelistUtils.goVIVOSetting();
            } else if (WhitelistUtils.isMeizu()) {//魅族
                WhitelistUtils.goMeizuSetting();
            } else if (WhitelistUtils.isSamsung()) {//三星
                WhitelistUtils.goSamsungSetting();
            } else if (WhitelistUtils.isLeTV()) {//乐视
                WhitelistUtils.goSamsungSetting();
            }
        } else {
            WhitelistUtils.requestIgnoreBatteryOptimizations();
        }
    }

}
