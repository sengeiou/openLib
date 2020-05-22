package com.open9527.recycleview.adapter;


import android.view.View;

public interface IBaseCellClick<CELL> extends View.OnClickListener {

    default void onItemClick() {

    }

    default void onItemClick(View view) {

    }

    default void onItemClick(CELL cell) {

    }

    default void onItemClick(CELL cell, int postion) {

    }
}
