package com.open9527.code.common.databinding.interfaces;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.open9527.code.common.databinding.BindingItemViewHolder;

import java.util.List;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/14 16:33.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public interface IBindingViewHolder {
    /**
     * 绑定 holder
     *
     * @param holder
     * @param position
     */
    default void bind(@NonNull final BindingItemViewHolder holder, final int position) {

    }

    /**
     * 绑定 holder
     *
     * @param holder
     * @param position
     * @param payloads (payloads size 始终为1)
     */
    default void bind(@NonNull final BindingItemViewHolder holder, final int position, final List<Object> payloads) {

    }

    /**
     * 资源回收
     *
     * @param holder
     * @param position
     */

    default void onViewRecycled(@NonNull final BindingItemViewHolder holder, final int position) {
    }


    /**
     * 配置id
     *
     * @return
     */
    default long getItemId() {
        return RecyclerView.NO_ID;
    }
}
