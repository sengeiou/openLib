package com.open9527.code.common.recycleview.holder;

import android.util.SparseArray;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.recyclerview.widget.RecyclerView;

import com.open9527.code.common.R;
import com.open9527.code.common.recycleview.interfaces.ICellClickListener;

/**
 * Created by     : open9527
 * Created times  : on 2019/8/22 17:55.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class ItemViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> viewArray = new SparseArray<>();

    public ItemViewHolder(View itemView) {
        super(itemView);
        // fix databinding tag问题
        itemView.setTag(R.id.rv_holder_item_id, this);
    }

    /**
     * 配置是否添加点击事件
     *
     * @param itemView
     */
    public ItemViewHolder(View itemView, OnItemClickListener onItemClickListener) {
        super(itemView);
        // fix databinding tag问题
        itemView.setTag(R.id.rv_holder_item_id, this);
    }

    protected OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        default void onItemClick(View view, int position) {
        }

        default void onItemLongClick(View view, int position) {
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T findViewById(@IdRes final int viewId) {
        View view = viewArray.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            viewArray.put(viewId, view);
        }
        return (T) view;
    }
}
