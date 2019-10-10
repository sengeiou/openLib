package com.open9527.code.lib;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.open9527.code.common.databinding.BindingBaseCell;
import com.open9527.code.common.databinding.BindingBaseCellAdapter;
import com.open9527.code.common.databinding.CommonBindingActivity;
import com.open9527.code.common.recycleview.interfaces.ICellClickListener;
import com.open9527.code.lib.cell.LaunchCell;
import com.open9527.code.lib.databinding.ActivityLaunchBinding;
import com.open9527.code.lib.model.LaunchModel;
import com.open9527.code.lib.module.image.compression.CompressImageActivity;
import com.open9527.code.lib.module.image.load.ImageLoadActivity;
import com.open9527.code.lib.module.other.OtherActivity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/9 17:01.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class LaunchActivity extends CommonBindingActivity<ActivityLaunchBinding> {

    private List<BindingBaseCell> cellList = new LinkedList<>();

    @Override
    public CharSequence bindTitle() {
        return "LaunchActivity";
    }

    @Override
    public void initData(@Nullable Bundle bundle) {
        cellList.add(new LaunchCell(new LaunchModel("图片压缩", CompressImageActivity.class), cellInterfaces));
        cellList.add(new LaunchCell(new LaunchModel("图片加载", ImageLoadActivity.class), cellInterfaces));
        cellList.add(new LaunchCell(new LaunchModel("other", OtherActivity.class), cellInterfaces));

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_launch;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        initAdapter();
    }


    @Override
    public void doBusiness() {

    }


    private void initAdapter() {
        mBinding.includeRecycleview.commonRv.setLayoutManager(new LinearLayoutManager(this));
//        if (mBinding.includeRecycleview.commonRv.getItemDecorationCount() == 0) {
//            mBinding.includeRecycleview.commonRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL_LIST,
//                    AdaptScreenUtils.pt2Px(2), ColorUtils.getColor(R.color.color_eee), AdaptScreenUtils.pt2Px(50), AdaptScreenUtils.pt2Px(50)));
//        }
        mBinding.includeRecycleview.commonRv.setHasFixedSize(true);
        BindingBaseCellAdapter mAdapter = new BindingBaseCellAdapter<>();
        mBinding.includeRecycleview.commonRv.setAdapter(mAdapter);
        mAdapter.setItems(cellList);
    }


    @Override
    public void onDebouncingClick(@NonNull View view) {

    }

    private ICellClickListener cellInterfaces = new ICellClickListener() {
        @Override
        public void onItemClick(View view, int position, Object... objects) {
            if (objects.length > 0) {
                LaunchModel launchModel = (LaunchModel) objects[0];
                if (launchModel != null) {
                    ActivityUtils.startActivity(launchModel.getClazz());
                } else {
                    ToastUtils.showShort("objects is null !");
                }

            }
        }
    };
}
