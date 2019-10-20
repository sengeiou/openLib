package com.open9527.code.lib.cell;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.blankj.utilcode.util.ClickUtils;
import com.open9527.code.common.databinding.BindingBaseCell;
import com.open9527.code.common.databinding.BindingItemViewHolder;
import com.open9527.code.common.databinding.interfaces.IBindingCellClickListener;
import com.open9527.code.lib.R;
import com.open9527.code.lib.databinding.ItemCellEmptyBinding;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/15 15:07.
 * E-Mail Address ：open_9527@163.com.
 * DESC :Empty.
 */
public class EmptyCell extends BindingBaseCell<EmptyCell> {
    public ObservableField<String> msgObservable = new ObservableField<>();
    public ObservableField<String> urlObservable = new ObservableField<>();


    public EmptyCell(String... strings) {
        super(R.layout.item_cell_empty);
        if (strings.length > 0) {
            msgObservable.set(strings[0]);
            if (strings.length > 1) {
                urlObservable.set(strings[1]);
            }
        }
    }

    @Override
    public void bind(@NonNull BindingItemViewHolder holder, int position) {
        ItemCellEmptyBinding emptyBinding = (ItemCellEmptyBinding) holder.mBinding;
        String msg = msgObservable.get();
        if (msg != null) {
            emptyBinding.tvEmpty.setText(msg);
        }
//        addViewClickListener(emptyBinding.tvEmpty);
    }
}
