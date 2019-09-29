package com.open9527.code.lib.samples.adapter.cell;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.open9527.code.common.databinding.BindingBaseCell;
import com.open9527.code.common.databinding.BindingItemViewHolder;
import com.open9527.code.common.recycleview.BaseCell;
import com.open9527.code.lib.R;
import com.open9527.code.lib.databinding.ItemBindImageBinding;
import com.open9527.code.lib.utils.CommonUtils;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/29 16:50.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class BindingImageCell extends BindingBaseCell<BindingImageCell> {
    public ObservableField<String> urlObservable = new ObservableField<>();
    public ObservableField<View> viewObservableField = new ObservableField<>();

    public BindingImageCell(String url) {
        super(R.layout.item_bind_image);
        urlObservable.set(url);
    }

    @Override
    public void bind(@NonNull BindingItemViewHolder holder, int position) {
        ItemBindImageBinding binding = (ItemBindImageBinding) holder.mBinding;
        Glide.with(holder.itemView.getContext())
                .load(urlObservable.get())
                .into(binding.ivPhoto);
        viewObservableField.set(binding.ivPhoto);
        binding.ivPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnCellClickListener != null) {
                    mOnCellClickListener.onCellClick(view, position);
                }

            }
        });

    }

    private OnCellClickListener mOnCellClickListener;

    public void setOnCellClickListener(OnCellClickListener onCellClickListener) {
        this.mOnCellClickListener = onCellClickListener;
    }

    public interface OnCellClickListener {
        void onCellClick(View view, int position);
    }
}
