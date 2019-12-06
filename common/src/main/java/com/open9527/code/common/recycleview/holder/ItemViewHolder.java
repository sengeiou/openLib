package com.open9527.code.common.recycleview.holder;

import android.util.SparseArray;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ClickUtils;
import com.blankj.utilcode.util.LogUtils;
import com.open9527.code.common.R;
import com.open9527.code.common.databinding.interfaces.IBindingCellClickListener;
import com.open9527.code.common.recycleview.interfaces.ICellClickListener;

/**
 * Created by     : open9527
 * Created times  : on 2019/8/22 17:55.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
    private static final String TAG = "ItemViewHolder";
    private SparseArray<View> viewArray = new SparseArray<>();

    public ItemViewHolder(View itemView) {
        super(itemView);
        // fix databinding tag问题
        itemView.setTag(R.id.rv_holder_item_id, this);
        //添加点击事件
        ClickUtils.applyGlobalDebouncing(itemView, this);
        //添加长按事件{TODO:用的较少暂时注释}
//        itemView.setOnLongClickListener(this);
    }

    public ItemViewHolder(View itemView, boolean hasClick) {
        super(itemView);
        // fix databinding tag问题
        itemView.setTag(R.id.rv_holder_item_id, this);
        //添加点击事件
        ClickUtils.applyGlobalDebouncing(itemView, this);
        //添加长按事件{TODO:用的较少暂时注释}
//            itemView.setOnLongClickListener(this);
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

    /*配置点击事件*/
    private ICellClickListener iCellClickListener;

    public void setItemViewListener(@Nullable ICellClickListener iCellClickListener) {
        this.iCellClickListener = iCellClickListener;
    }

    private IBindingCellClickListener iBindingCellClickListener;

    public void setItemViewListener(@Nullable IBindingCellClickListener iBindingCellClickListener) {
        this.iBindingCellClickListener = iBindingCellClickListener;
    }


    /**
     * Item 点击事件
     */
    @Override
    public void onClick(View view) {
        if (iCellClickListener != null) {
            iCellClickListener.onItemClick(view, getAdapterPosition());
        }
        if (iBindingCellClickListener != null) {
            iBindingCellClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    /**
     * Item 长按事件
     *
     * @param view
     * @return
     */
    @Override
    public boolean onLongClick(View view) {
        if (iCellClickListener != null) {
            return iCellClickListener.onItemLongClick(view, getAdapterPosition());
        }

        if (iBindingCellClickListener != null) {
            return iBindingCellClickListener.onItemLongClick(view, getAdapterPosition());
        }

        return false;
    }

    /**
     * 子View 的点击事件
     *
     * @param views
     */
    public void setViewsClickListener(View... views) {
        if (views.length > 0) {
            for (int i = 0; i < views.length; i++) {
                final View view = views[i];
                ClickUtils.applyGlobalDebouncing(view, this);
            }
        }
    }

    /**
     * 子View 的长按事件
     *
     * @param views
     */
    public void setViewsLongClickListener(View... views) {
        if (views.length > 0) {
            for (int i = 0; i < views.length; i++) {
                final View view = views[i];
                view.setOnLongClickListener(this);
            }
        }
    }

}
