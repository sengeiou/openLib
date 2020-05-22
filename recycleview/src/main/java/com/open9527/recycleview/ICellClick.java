package com.open9527.recycleview;

import com.open9527.recycleview.adapter.IBaseCellClick;

public interface ICellClick extends IBaseCellClick {
    @Override
    default void onItemClick() {

    }
}
