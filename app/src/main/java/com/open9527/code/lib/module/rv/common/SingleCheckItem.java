package com.open9527.code.lib.module.rv.common;


import android.widget.TextView;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;

import com.open9527.code.lib.R;
import com.open9527.code.lib.model.RadioBean;
import com.open9527.code.lib.module.rv.ItemViewHolder;

/**
 * Created by     : open9527
 * Created times  : on 2019/11/22 10:46.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class SingleCheckItem extends CommonItem<SingleCheckItem> {
    private static int checkIndex = -1;
    private RadioBean radioBean;

    public SingleCheckItem(RadioBean radioBean) {
        super(R.layout.item_select);
        this.radioBean = radioBean;
        setOnItemClickListener((holder, item, position) -> {
            if (checkIndex >= 0) {
                SingleCheckItem singleCheckItem = getAdapter().getItem(checkIndex);
                singleCheckItem.radioBean.setHasSelected(false);
                getAdapter().updateItem(checkIndex);
            }
            radioBean.setHasSelected(true);
            getAdapter().updateItem(position);
            checkIndex = position;

        });
    }

    @Override
    public void bind(@NonNull ItemViewHolder holder, int position) {
        super.bind(holder, position);
        TextView title = holder.findViewById(R.id.tv_title);
        title.setText(radioBean.getTitle());
    }
}
