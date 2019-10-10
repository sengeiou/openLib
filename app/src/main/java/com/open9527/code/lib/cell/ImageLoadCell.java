package com.open9527.code.lib.cell;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.open9527.code.common.databinding.BindingBaseCell;
import com.open9527.code.common.databinding.BindingItemViewHolder;
import com.open9527.code.image.imageload.ImageLoadProcessInterface;
import com.open9527.code.lib.R;
import com.open9527.code.lib.databinding.ItemCellImageloadBinding;
import com.open9527.code.lib.utils.ImageLoadUtils;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/10 16:23.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class ImageLoadCell extends BindingBaseCell<ImageLoadCell> {
    private ObservableField<String> urlObservableField = new ObservableField<>();

    public ImageLoadCell(String url) {
        super(R.layout.item_cell_imageload);
        urlObservableField.set(url);
    }

    @Override
    public void bind(@NonNull BindingItemViewHolder holder, int position) {
        ItemCellImageloadBinding binding = (ItemCellImageloadBinding) holder.mBinding;
        ImageLoadUtils.imageLoad(holder.itemView.getContext(), binding.ivImages, urlObservableField.get(), new ImageLoadProcessInterface() {
            @Override
            public void onLoadStarted() {
                binding.pbLoading.setVisibility(View.VISIBLE);
            }

            @Override
            public void onResourceReady() {
                binding.pbLoading.setVisibility(View.GONE);
            }

            @Override
            public void onLoadFailed() {
                binding.pbLoading.setVisibility(View.GONE);
            }
        });

    }
}
