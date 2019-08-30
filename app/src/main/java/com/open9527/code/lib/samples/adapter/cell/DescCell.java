package com.open9527.code.lib.samples.adapter.cell;

import android.widget.TextView;

import androidx.annotation.NonNull;

import com.open9527.code.common.recycleview.BaseItem;
import com.open9527.code.common.recycleview.ItemViewHolder;
import com.open9527.code.lib.R;
import com.open9527.code.lib.model.PhotoBean;

/**
 * Created by     : open9527
 * Created times  : on 2019/8/26 10:29.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class DescCell extends BaseItem<DescCell> {

    private PhotoBean photoBean;

    public DescCell(PhotoBean photoBean) {
        super(R.layout.item_desc);
        this.photoBean = photoBean;
    }


    @Override
    public void bind(@NonNull ItemViewHolder holder, int position) {
        TextView desc = holder.findViewById(R.id.tv_desc);
        desc.setText(photoBean.getDesc());
    }
}
