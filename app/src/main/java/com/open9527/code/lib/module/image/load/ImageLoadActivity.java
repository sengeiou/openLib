package com.open9527.code.lib.module.image.load;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import com.blankj.utilcode.util.AdaptScreenUtils;
import com.blankj.utilcode.util.ColorUtils;
import com.open9527.code.common.databinding.BindingBaseCell;
import com.open9527.code.common.databinding.BindingBaseCellAdapter;
import com.open9527.code.common.databinding.CommonBindingActivity;
import com.open9527.code.common.recycleview.decoration.DividerItemDecoration;
import com.open9527.code.lib.R;
import com.open9527.code.lib.cell.ImageLoadCell;
import com.open9527.code.lib.databinding.ActivityImageloadBinding;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/10 14:26.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class ImageLoadActivity extends CommonBindingActivity<ActivityImageloadBinding> {
    private List<BindingBaseCell> cellList = new LinkedList<>();

    @Override
    public CharSequence bindTitle() {
        return "ImageLoadActivity";
    }

    @Override
    public void initData(@Nullable Bundle bundle) {
        cellList.add(new ImageLoadCell("https://github.com/open9527/Images/raw/master/icon/unsplash/cat/1.jpg"));
        cellList.add(new ImageLoadCell("https://github.com/open9527/Images/raw/master/icon/unsplash/cat/2.jpg"));
        cellList.add(new ImageLoadCell("https://github.com/open9527/Images/raw/master/icon/unsplash/cat/3.jpg"));
        cellList.add(new ImageLoadCell("https://github.com/open9527/Images/raw/master/icon/unsplash/cat/4.jpg"));
        cellList.add(new ImageLoadCell("https://github.com/open9527/Images/raw/master/icon/unsplash/cat/5.jpg"));
        cellList.add(new ImageLoadCell("https://github.com/open9527/Images/raw/master/icon/unsplash/cat/6.jpg"));
        cellList.add(new ImageLoadCell("https://github.com/open9527/Images/raw/master/icon/unsplash/cat/7.jpg"));
        cellList.add(new ImageLoadCell("https://github.com/open9527/Images/raw/master/icon/unsplash/cat/8.jpg"));
        cellList.add(new ImageLoadCell("https://github.com/open9527/Images/raw/master/icon/unsplash/cat/9.jpg"));
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_imageload;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        mBinding.includeRecycleview.commonRv.setLayoutManager(new GridLayoutManager(this, 2));
        mBinding.includeRecycleview.commonRv.setHasFixedSize(true);
        if (mBinding.includeRecycleview.commonRv.getItemDecorationCount() == 0) {
            mBinding.includeRecycleview.commonRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.BOTH_SET,
                    AdaptScreenUtils.pt2Px(10), ColorUtils.getColor(R.color.color_eee)));
        }
        BindingBaseCellAdapter mAdapter = new BindingBaseCellAdapter<>();
        mBinding.includeRecycleview.commonRv.setAdapter(mAdapter);
        mAdapter.setItems(cellList);

    }

    @Override
    public void doBusiness() {
        
    }

    @Override
    public void onDebouncingClick(@NonNull View view) {

    }
}
