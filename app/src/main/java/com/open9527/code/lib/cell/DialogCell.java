package com.open9527.code.lib.cell;

import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.blankj.utilcode.util.ShadowUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.open9527.code.common.databinding.BindingBaseCell;
import com.open9527.code.common.databinding.BindingItemViewHolder;
import com.open9527.code.lib.R;
import com.open9527.code.lib.databinding.ItemCellDialogBinding;

/**
 * Created by     : open9527
 * Created times  : on 2019/11/1 17:17.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class DialogCell extends BindingBaseCell<DialogCell> {
    public ObservableField<String> observableField = new ObservableField<>();

    public DialogCell(String string) {
        super(R.layout.item_cell_dialog);
        observableField.set(string);
    }

    @Override
    public void bind(@NonNull BindingItemViewHolder holder, int position) {

        ShadowUtils.apply(holder.itemView, new ShadowUtils.Config().setShadowRadius(0.01f).setShadowColor(Color.BLUE, Color.GREEN));
//        ShadowUtils.apply(holder.itemView, new ShadowUtils.Config().setShadowRadius(SizeUtils.dp2px(16f)).setShadowColor(Color.RED, Color.BLUE));
//        ShadowUtils.apply(holder.itemView, new ShadowUtils.Config().setCircle().setShadowColor(Color.GREEN, Color.BLUE));
//

        ItemCellDialogBinding binding = (ItemCellDialogBinding) holder.mBinding;
        binding.setCell(this);
    }


}
