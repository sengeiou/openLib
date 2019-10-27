package com.open9527.code.lib.cell;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.blankj.utilcode.util.ToastUtils;
import com.open9527.code.common.databinding.BindingBaseCell;
import com.open9527.code.common.databinding.BindingItemViewHolder;
import com.open9527.code.common.databinding.click.BindingAction;
import com.open9527.code.common.databinding.click.BindingClick;
import com.open9527.code.lib.R;
import com.open9527.code.lib.databinding.ItemDescBinding;

/**
 * Created by     : open9527
 * Created times  : on 2019/8/26 10:29.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class DescCell extends BindingBaseCell<DescCell> {
    public ObservableField<String> stringObservableField = new ObservableField<>();
    public ObservableField<String> stringObservableField1 = new ObservableField<>();

    public DescCell(String string) {
        super(R.layout.item_desc);
        stringObservableField.set(string);
        stringObservableField1.set("这是第二段描述文件:" + string);
    }

    @Override
    public void bind(@NonNull BindingItemViewHolder holder, int position) {
        ItemDescBinding binding = (ItemDescBinding) holder.mBinding;
        binding.setCell(this);
    }

    public BindingClick bindingDescClick = new BindingClick(new BindingAction() {
        @Override
        public void call() {
            ToastUtils.showShort(stringObservableField1.get());
        }
    });

}
