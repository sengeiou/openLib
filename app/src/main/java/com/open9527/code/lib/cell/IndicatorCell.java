package com.open9527.code.lib.cell;


import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.open9527.code.common.databinding.BindingBaseCell;
import com.open9527.code.common.databinding.BindingItemViewHolder;
import com.open9527.code.lib.R;
import com.open9527.code.lib.databinding.ItemCellIndicatorBinding;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/30 16:25.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class IndicatorCell extends BindingBaseCell<IndicatorCell> {
    public ObservableField<Boolean> observableField = new ObservableField<>();

    public IndicatorCell(boolean hasSelected) {
        super(R.layout.item_cell_indicator);
        observableField.set(hasSelected);
    }

    @Override
    public void bind(@NonNull BindingItemViewHolder holder, int position) {
        ItemCellIndicatorBinding binding = (ItemCellIndicatorBinding) holder.mBinding;
        binding.setCell(this);
    }
}
