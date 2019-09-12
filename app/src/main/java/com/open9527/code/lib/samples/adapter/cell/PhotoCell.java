package com.open9527.code.lib.samples.adapter.cell;


import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.open9527.code.common.recycleview.BaseCell;
import com.open9527.code.common.recycleview.holder.ItemViewHolder;
import com.open9527.code.lib.R;
import com.open9527.code.lib.model.PhotoBean;
import com.open9527.code.lib.utils.CommonUtils;

/**
 * Created by     : open9527
 * Created times  : on 2019/8/26 9:53.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class PhotoCell extends BaseCell<PhotoCell> {

    private PhotoBean photoBean;
    private String string;
    public PhotoCell(int layout, PhotoBean photoBean) {
        super(layout);
        this.photoBean = photoBean;
    }


    @Override
    public void bind(@NonNull ItemViewHolder holder, int position) {
        if (isViewType(R.layout.item_photo)) {
            ImageView icon = holder.findViewById(R.id.iv_photo);
            CommonUtils.imageLoad(holder.itemView.getContext(), icon, photoBean.getUrl());
        } else {
            TextView desc = holder.findViewById(R.id.tv_desc);
            desc.setText(string + position);
        }
    }
}
