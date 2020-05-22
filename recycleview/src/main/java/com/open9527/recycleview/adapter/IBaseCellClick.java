package com.open9527.recycleview.adapter;


public interface IBaseCellClick {

    default void onItemClick() {

    }

    default void onItemClick(BaseBindingCell cell) {

    }

    default void onItemClick(BaseBindingCell cell, int postion) {

    }
}
