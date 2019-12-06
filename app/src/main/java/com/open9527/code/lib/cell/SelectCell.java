package com.open9527.code.lib.cell;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.open9527.code.common.databinding.BindingBaseCell;
import com.open9527.code.common.databinding.BindingItemViewHolder;
import com.open9527.code.lib.R;
import com.open9527.code.lib.databinding.ItemCellSelectBinding;
import com.open9527.code.lib.model.RadioBean;

/**
 * Created by     : open9527
 * Created times  : on 2019/11/15 14:51.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class SelectCell extends BindingBaseCell<SelectCell> {
    public static int checkIndex = -1;

    //title
    public ObservableField<String> titleObservableField = new ObservableField<>();
    public ObservableField<Boolean> selectObservableField = new ObservableField<>();


    public SelectCell(RadioBean radioBean) {
        super(R.layout.item_cell_select);
        titleObservableField.set(radioBean.getTitle());
        selectObservableField.set(radioBean.hasSelected());
    }

    @Override
    public void bind(@NonNull BindingItemViewHolder holder, int position) {
        ItemCellSelectBinding binding = (ItemCellSelectBinding) holder.mBinding;
        binding.setCell(this);
    }
}
