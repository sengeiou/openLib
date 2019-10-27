package com.open9527.code.lib.cell;


import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.open9527.code.common.databinding.BindingBaseCell;
import com.open9527.code.common.databinding.BindingItemViewHolder;
import com.open9527.code.lib.R;
import com.open9527.code.lib.databinding.ItemCellEmptyBinding;
import com.open9527.code.lib.model.LaunchModel;
import com.open9527.code.network.livedata.SingleLiveEvent;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/15 15:07.
 * E-Mail Address ：open_9527@163.com.
 * DESC :Empty.
 */
public class EmptyCell extends BindingBaseCell<EmptyCell> {
    public ObservableField<LaunchModel> descObservableField = new ObservableField<>();
    public SingleLiveEvent<LaunchModel> launchModelSingleLiveEvent = new SingleLiveEvent<>();
    public ObservableField<String> stringObservableField = new ObservableField<>();

    public EmptyCell(LaunchModel launchModel) {
        super(R.layout.item_cell_empty);
//        descObservableField.set(launchModel);
        stringObservableField.set(launchModel.getDesc());
    }

    @Override
    public void bind(@NonNull BindingItemViewHolder holder, int position) {
        ItemCellEmptyBinding emptyBinding = (ItemCellEmptyBinding) holder.mBinding;
        emptyBinding.setCell(this);
        holder.setViewsClickListener(emptyBinding.tvEmpty);
    }
}
