package com.open9527.recycleview.adapter;

import androidx.databinding.ViewDataBinding;

/**
 * Created by     : open9527
 * Created times  : on  2020/2/18.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class BaseBindingCellViewHolder<binding extends ViewDataBinding> extends BaseCellViewHolder {

    public binding mBinding;

    public BaseBindingCellViewHolder(binding binding) {
        super(binding.getRoot());
        this.mBinding = binding;
    }
}
