package com.open9527.code.lib.cell;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.blankj.utilcode.util.ToastUtils;
import com.open9527.code.lib.R;
import com.open9527.code.lib.databinding.ItemCellPreviewBinding;
import com.open9527.code.lib.utils.ImageLoadUtils;
import com.open9527.recycleview.adapter.BaseBindingCell;
import com.open9527.recycleview.adapter.BaseBindingCellViewHolder;
import com.open9527.recycleview.adapter.IBaseCellClick;

public class PreviewCell extends BaseBindingCell<PreviewCell> implements IBaseCellClick {
    public ObservableField<String> stringUrl = new ObservableField<String>();

    public PreviewCell(String url) {
        super(R.layout.item_cell_preview);
        stringUrl.set(url);
    }

    @Override
    public void bind(@NonNull BaseBindingCellViewHolder holder, int position) {
        ItemCellPreviewBinding cellPreviewBinding = (ItemCellPreviewBinding) holder.mBinding;
        cellPreviewBinding.setCell(this);
        ImageLoadUtils.imageLoad(holder.itemView.getContext(), cellPreviewBinding.ivImage, stringUrl.get());
    }

    @Override
    public void onItemClick() {
        ToastUtils.showShort("onItemClick");
    }
}
