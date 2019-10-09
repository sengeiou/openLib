package com.open9527.code.lib.databinding;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.open9527.code.common.databinding.BindingBaseCell;
import com.open9527.code.common.databinding.BindingItemViewHolder;
import com.open9527.code.common.recycleview.holder.ItemViewHolder;
import com.open9527.code.lib.R;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/26 15:08.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class BindingTxtCell extends BindingBaseCell<BindingTxtCell> {
    public ObservableField<String> observableField = new ObservableField<>();
    private String string;

    public BindingTxtCell(String string) {
        super(R.layout.item_bind_txt);
        this.string = string;
    }


    @Override
    public void bind(@NonNull BindingItemViewHolder holder, int position) {
        ItemBindTxtBinding itemBindTxtBinding = (ItemBindTxtBinding) holder.mBinding;
        observableField.set(string);
        itemBindTxtBinding.tvTitle.setText(observableField.get());
        itemBindTxtBinding.setCell(this);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                observableField.set("点击");
            }
        });
//        holder.setOnItemClickListener(new ItemViewHolder.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                observableField.set("点击");
//            }
//        });

    }
}
