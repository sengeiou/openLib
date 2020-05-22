package com.open9527.code.lib.cell;


import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.open9527.code.common.databinding.BindingBaseCell;
import com.open9527.code.common.databinding.BindingItemViewHolder;
import com.open9527.code.lib.MainActivity;
import com.open9527.code.lib.MainFragment;
import com.open9527.code.lib.R;
import com.open9527.code.lib.databinding.ItemCellLaunchBinding;
import com.open9527.code.lib.model.LaunchModel;
import com.open9527.code.lib.module.rv.click.RecycleViewClickActivity;
import com.open9527.recycleview.adapter.BaseBindingCell;
import com.open9527.recycleview.adapter.BaseBindingCellViewHolder;
import com.open9527.recycleview.adapter.IBaseCellClick;

import io.reactivex.internal.operators.observable.ObservableNever;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/9 17:08.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class LaunchCell extends BaseBindingCell<LaunchCell> implements IBaseCellClick {
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
    public void onItemClick(BaseBindingCell cell) {
        if (cell instanceof LaunchCell) {
            LaunchCell launchCell = (LaunchCell) cell;
            LaunchModel launchModel = launchCell.descObservableField.get();
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
    }
}
