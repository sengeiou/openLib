package com.open9527.code.common.databinding;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ClickUtils;
import com.open9527.code.common.databinding.interfaces.IBindingViewHolder;
import com.open9527.code.common.recycleview.interfaces.ICellClickListener;


/**
 * Created by     : open9527
 * Created times  : on 2019/8/22 17:55.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public abstract class BindingBaseCell<T extends BindingBaseCell> implements IBindingViewHolder {
    protected final String TAG = getClass().getSimpleName();
    private static final SparseIntArray LAYOUT_SPARSE_ARRAY = new SparseIntArray();
    private static final SparseArray<View> VIEW_SPARSE_ARRAY = new SparseArray<>();

    static BindingItemViewHolder<ViewDataBinding> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutByType = LAYOUT_SPARSE_ARRAY.get(viewType, -1);
        if (layoutByType != -1) {
            return new BindingItemViewHolder<>(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layoutByType, parent, false));
        }
        View viewByType = VIEW_SPARSE_ARRAY.get(viewType);
        if (viewByType != null) {
            ViewDataBinding viewDataBinding = DataBindingUtil.bind(viewByType);
            if (viewDataBinding != null) {
                return new BindingItemViewHolder(viewDataBinding);
            }
        }
        throw new RuntimeException("onCreateViewHolder: get holder from view type failed.");
    }

    private int viewType;
    BindingBaseCellAdapter<T> mAdapter;

    public BindingBaseCellAdapter<T> getAdapter() {
        return mAdapter;
    }

    public BindingBaseCell(@LayoutRes int layoutId) {
        viewType = getViewTypeByLayoutId(layoutId);
        LAYOUT_SPARSE_ARRAY.put(viewType, layoutId);
    }

    public BindingBaseCell(@NonNull View view) {
        viewType = getViewTypeByView(view);
        VIEW_SPARSE_ARRAY.put(viewType, view);
    }

    public int getViewType() {
        return viewType;
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
