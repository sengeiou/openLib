package com.open9527.code.lib.cell;


import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.open9527.code.common.databinding.BindingBaseCell;
import com.open9527.code.common.databinding.BindingItemViewHolder;
import com.open9527.code.lib.R;
import com.open9527.code.lib.databinding.ItemCellLaunchBinding;
import com.open9527.code.lib.model.LaunchModel;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/9 17:08.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class LaunchCell extends BindingBaseCell<LaunchCell> {
    public ObservableField<LaunchModel> descObservableField = new ObservableField<>();


    public LaunchCell(LaunchModel launchModel) {
        super(R.layout.item_cell_launch);
        descObservableField.set(launchModel);

    }

    @Override
    public void bind(@NonNull BindingItemViewHolder holder, int position) {
        ItemCellLaunchBinding mBinding = (ItemCellLaunchBinding) holder.mBinding;
        mBinding.tvDesc.setText(descObservableField.get().getDesc());
//        holder.itemView.setOnClickListener(view -> {
//            if (iCellClickListener != null) {
//                iCellClickListener.onItemClick(view, position, descObservableField.get());
//            }
//        });
    }

}
