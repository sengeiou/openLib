package com.open9527.code.lib.module.rv.common;


import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;

import com.blankj.utilcode.util.ColorUtils;
import com.open9527.code.lib.R;
import com.open9527.code.lib.module.rv.BaseItem;
import com.open9527.code.lib.module.rv.ItemViewHolder;

/**
 * <pre>
 *     author: blankj
 *     blog  : http://blankj.com
 *     time  : 2019/10/25
 *     desc  :
 * </pre>
 */
public class CommonItem<T extends BaseItem> extends BaseItem<T> {

    private int backgroundColor = ColorUtils.getColor(R.color.color_f0f0f0);

    public CommonItem(int layoutId) {
        super(layoutId);
    }

    @CallSuper
    @Override
    public void bind(@NonNull final ItemViewHolder holder, int position) {
//        holder.itemView.setBackgroundColor(backgroundColor);
    }

    public CommonItem<T> setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }
}
