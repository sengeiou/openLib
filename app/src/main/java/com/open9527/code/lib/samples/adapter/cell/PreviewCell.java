package com.open9527.code.lib.samples.adapter.cell;

import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.open9527.code.common.recycleview.BaseItem;
import com.open9527.code.common.recycleview.ItemViewHolder;
import com.open9527.code.lib.R;
import com.open9527.code.lib.utils.CommonUtils;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/4 17:39.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class PreviewCell extends BaseItem<PreviewCell> {

    public String url;

    public PreviewCell(int layout, String url) {
        super(layout);
        this.url = url;
    }

    @Override
    public void bind(@NonNull ItemViewHolder holder, int position) {
        ImageView icon = holder.findViewById(R.id.iv_photo);
        CommonUtils.imageLoad(holder.itemView.getContext(), icon, url, ImageView.ScaleType.FIT_XY);
    }
}
