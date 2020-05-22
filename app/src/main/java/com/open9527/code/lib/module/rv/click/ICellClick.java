package com.open9527.code.lib.module.rv.click;

import com.open9527.code.common.databinding.BindingBaseCell;

public interface ICellClick {

    default void onItemClick() {

    }

    default void onItemClick(BindingBaseCell cell) {

    }


}
