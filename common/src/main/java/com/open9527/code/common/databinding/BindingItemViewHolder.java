package com.open9527.code.common.databinding;


import androidx.databinding.ViewDataBinding;

import com.open9527.code.common.recycleview.holder.ItemViewHolder;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/26 14:30.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class BindingItemViewHolder<binding extends ViewDataBinding> extends ItemViewHolder {

    public binding mBinding;

    public BindingItemViewHolder(binding binding) {
        super(binding.getRoot());
        this.mBinding = binding;
    }

}
