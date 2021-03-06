package com.open9527.code.common.recycleview;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.open9527.code.common.recycleview.holder.ItemViewHolder;

/**
 * Created by     : open9527
 * Created times  : on 2019/8/22 17:55.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public abstract class BaseCell<T extends BaseCell> {
    protected final String TAG = getClass().getSimpleName();
    private static final SparseIntArray LAYOUT_SPARSE_ARRAY = new SparseIntArray();
    private static final SparseArray<View> VIEW_SPARSE_ARRAY = new SparseArray<>();

    static ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutByType = LAYOUT_SPARSE_ARRAY.get(viewType, -1);
        if (layoutByType != -1) {
            return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(layoutByType, parent, false));
        }
        View viewByType = VIEW_SPARSE_ARRAY.get(viewType);
        if (viewByType != null) {
            return new ItemViewHolder(viewByType);
        }
        throw new RuntimeException("onCreateViewHolder: get holder from view type failed.");
    }

    public abstract void bind(@NonNull final ItemViewHolder holder, final int position);

    public void onViewRecycled(@NonNull final ItemViewHolder holder, final int position) {/**/}

    public long getItemId() {
        return RecyclerView.NO_ID;
    }

    private int viewType;
    BaseCellAdapter<T> mAdapter;

    public BaseCell(@LayoutRes int layoutId) {
        viewType = getViewTypeByLayoutId(layoutId);
        LAYOUT_SPARSE_ARRAY.put(viewType, layoutId);
    }

    public BaseCell(@NonNull View view) {
        viewType = getViewTypeByView(view);
        VIEW_SPARSE_ARRAY.put(viewType, view);
    }

    public int getViewType() {
        return viewType;
    }

    public BaseCellAdapter<T> getAdapter() {
        return mAdapter;
    }

    public boolean isViewType(@LayoutRes int layoutId) {
        return viewType == getViewTypeByLayoutId(layoutId);
    }

    public boolean isViewType(@NonNull View view) {
        return viewType == getViewTypeByView(view);
    }

    private int getViewTypeByLayoutId(@LayoutRes int layoutId) {
        return layoutId + getClass().hashCode();
    }

    private int getViewTypeByView(@NonNull View view) {
        return view.hashCode() + getClass().hashCode();
    }
}
