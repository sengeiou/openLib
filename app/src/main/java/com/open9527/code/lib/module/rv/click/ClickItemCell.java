package com.open9527.code.lib.module.rv.click;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.open9527.code.common.databinding.BindingBaseCell;
import com.open9527.code.common.databinding.BindingItemViewHolder;
import com.open9527.code.lib.R;
import com.open9527.code.lib.databinding.ItemCellClickBinding;

public class ClickItemCell extends BindingBaseCell<ClickItemCell> {
    public ObservableField<String> observableField = new ObservableField<>();

    public ClickItemCell(String string) {
        super(R.layout.item_cell_click);
        observableField.set(string);
    }

    @Override
    public void bind(@NonNull BindingItemViewHolder holder, int position) {
        ItemCellClickBinding cellClickBinding = (ItemCellClickBinding) holder.mBinding;
        cellClickBinding.setCell(this);
        cellClickBinding.setClick((RecycleViewClickActivity) holder.itemView.getContext());
        cellClickBinding.tvTitle.setText(observableField.get());
    }

}
