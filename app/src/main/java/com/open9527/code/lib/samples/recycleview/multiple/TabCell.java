package com.open9527.code.lib.samples.recycleview.multiple;

import android.view.View;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.ToastUtils;
import com.open9527.code.common.recycleview.BaseCell;
import com.open9527.code.common.recycleview.holder.ItemViewHolder;
import com.open9527.code.lib.R;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/11 17:19.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class TabCell extends BaseCell<TabCell> {
    public TabCell() {
        super(R.layout.item_tab);
    }

    @Override
    public void bind(@NonNull ItemViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showShort("TabCell-->"+position);
            }
        });
    }
}
