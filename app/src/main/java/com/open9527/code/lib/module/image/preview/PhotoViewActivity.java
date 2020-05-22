package com.open9527.code.lib.module.image.preview;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ColorUtils;
import com.open9527.code.common.databinding.CommonBindingTitleActivity;
import com.open9527.code.lib.R;
import com.open9527.code.lib.cell.PhotoViewCell;
import com.open9527.code.lib.databinding.ActivityPhotoviewBinding;
import com.open9527.code.lib.databinding.ActivityPreviewBinding;
import com.open9527.code.lib.model.CommonKey;
import com.open9527.code.lib.model.GalleryBean;
import com.open9527.code.lib.module.image.PhotoViewPagerAdapter;
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
public class PhotoViewActivity extends CommonBindingTitleActivity<ActivityPhotoviewBinding> {

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
        return R.layout.activity_photoview;
    }

    @Override
    protected void setStatusBar() {
        mToolbar.setVisibility(View.GONE);
        BarUtils.setStatusBarColor(this, ColorUtils.getColor(R.color.color_transparent));
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {

        List<BaseBindingCell> cellList = new LinkedList<>();
        List<String> list = galleryBean.getList();
        int index = galleryBean.getIndex();

        if (list != null && list.size() > 0) {
            mBinding.viewPager.setAdapter(new PhotoViewPagerAdapter(this, list));
            if (index != 0 && index <= list.size()) {
                mBinding.viewPager.setCurrentItem(index);
            }
        } else {
            finish();
        }

    }

}
