package com.open9527.code.lib.module.image.preview;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.open9527.code.common.databinding.CommonBindingTitleActivity;
import com.open9527.code.lib.R;
import com.open9527.code.lib.cell.PreviewCell;
import com.open9527.code.lib.databinding.ActivityPreviewBinding;
import com.open9527.code.lib.model.CommonKey;
import com.open9527.code.lib.model.GalleryBean;
import com.open9527.recycleview.TopSmoothScroller;
import com.open9527.recycleview.adapter.BaseBindingCell;
import com.open9527.recycleview.adapter.BaseBindingCellAdapter;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/10 15:57.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class PreviewActivity extends CommonBindingTitleActivity<ActivityPreviewBinding> {

    private BaseBindingCellAdapter<BaseBindingCell> mAdapter;
    private GalleryBean galleryBean;

    @Override
    public void initData(@Nullable Bundle bundle) {
        super.initData(bundle);
        if (bundle != null) {
            galleryBean = bundle.getParcelable(CommonKey.IBundleKey.COMMON_BUNDLE_KEY);
        }


    }

    @Override
    public int bindLayout() {
        return R.layout.activity_preview;
    }

    @Override
    protected void setStatusBar() {
        mToolbar.setVisibility(View.GONE);
        BarUtils.setStatusBarColor(this, ColorUtils.getColor(R.color.color_transparent));
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false);
        mBinding.setLayoutManager(linearLayoutManager);
        PagerSnapHelper snapHelper = new PagerSnapHelper();
//        An instance of OnFlingListener already set. 异常
//        mBinding.rvList.setOnFlingListener(null);
        snapHelper.attachToRecyclerView(mBinding.rvList);
        mBinding.setAdapter(mAdapter = new BaseBindingCellAdapter<>());

        List<BaseBindingCell> cellList = new LinkedList<>();
        List<String> list = galleryBean.getList();
        for (int i = 0; i < list.size(); i++) {
            cellList.add(new PreviewCell(list.get(i)));
        }
        mAdapter.addItems(cellList, true);
        //闪到指定位置
        linearLayoutManager.scrollToPositionWithOffset(galleryBean.getIndex(), 0);
    }

}
