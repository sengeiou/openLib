package com.open9527.code.lib.cell;


import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.open9527.code.lib.R;
import com.open9527.code.lib.databinding.ItemCellLaunchBinding;
import com.open9527.code.lib.model.LaunchModel;
import com.open9527.recycleview.ICellClick;
import com.open9527.recycleview.adapter.BaseBindingCell;
import com.open9527.recycleview.adapter.BaseBindingCellViewHolder;
import com.open9527.recycleview.adapter.IBaseCellClick;


/**
 * Created by     : open9527
 * Created times  : on 2019/10/9 17:08.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class LaunchCell extends BaseBindingCell<LaunchCell> implements IBaseCellClick<LaunchCell> {
    public ObservableField<LaunchModel> descObservableField = new ObservableField<>();

    public LaunchCell(LaunchModel launchModel) {
        super(R.layout.item_cell_launch);
        descObservableField.set(launchModel);
    }

    @Override
    public void bind(@NonNull BaseBindingCellViewHolder holder, int position) {
        ItemCellLaunchBinding mBinding = (ItemCellLaunchBinding) holder.mBinding;
        mBinding.setCell(this);
        mBinding.tvDesc.setText(descObservableField.get().getDesc());
    }

    @Override
    public void onItemClick() {
        LaunchModel launchModel = descObservableField.get();
        if (launchModel != null) {
            if (launchModel.getClazz() != null) {
                ActivityUtils.startActivity(launchModel.getClazz());
            } else {
                ToastUtils.showShort("clazz is  null !");
            }
        } else {
            ToastUtils.showShort("cell is null !");
        }
    }


    @Override
    public void onClick(View v) {
        LogUtils.i(TAG,"onClick");
        onItemClick();
    }
}
