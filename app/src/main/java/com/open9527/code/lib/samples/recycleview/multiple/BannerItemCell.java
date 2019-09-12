package com.open9527.code.lib.samples.recycleview.multiple;

import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.open9527.code.common.recycleview.BaseCell;
import com.open9527.code.common.recycleview.holder.ItemViewHolder;
import com.open9527.code.lib.R;
import com.open9527.code.lib.utils.CommonUtils;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/12 10:08.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class BannerItemCell extends BaseCell<BannerItemCell> {
    private String url;

    public BannerItemCell(String url) {
        super(R.layout.item_banner_item);
    }

    @Override
    public void bind(@NonNull ItemViewHolder holder, int position) {
        ImageView ivBanner = holder.findViewById(R.id.iv_banner);
        CommonUtils.imageLoad(holder.itemView.getContext(), ivBanner, url);
    }
}
