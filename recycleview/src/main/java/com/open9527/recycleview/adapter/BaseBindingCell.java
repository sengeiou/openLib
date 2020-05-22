package com.open9527.recycleview.adapter;

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


/**
 * Created by     : open9527
 * Created times  : on  2020/2/18.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public abstract class BaseBindingCell<T extends BaseBindingCell> {

    public static final String TAG = BaseBindingCell.class.getSimpleName();

    private static final SparseIntArray LAYOUT_SPARSE_ARRAY = new SparseIntArray();
    private static final SparseArray<View> VIEW_SPARSE_ARRAY = new SparseArray<>();

    static BaseBindingCellViewHolder<ViewDataBinding> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutByType = LAYOUT_SPARSE_ARRAY.get(viewType, -1);

        if (layoutByType != -1) {
            return new BaseBindingCellViewHolder<>(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layoutByType, parent, false));
        }
        View viewByType = VIEW_SPARSE_ARRAY.get(viewType);
        if (viewByType != null) {
            ViewDataBinding viewDataBinding = DataBindingUtil.bind(viewByType);
            if (viewDataBinding != null) {
                return new BaseBindingCellViewHolder(viewDataBinding);
            }
        }
        throw new RuntimeException("onCreateViewHolder: get holder from view type failed.");
    }

    public abstract void bind(@NonNull final BaseBindingCellViewHolder holder, final int position);

    void bindViewHolder(@NonNull final BaseBindingCellViewHolder holder, final int position) {
        bind(holder, position);
    }

    public void onViewRecycled(@NonNull final BaseBindingCellViewHolder holder, final int position) {/**/}

    public long getItemId() {
        return RecyclerView.NO_ID;
    }

    private int viewType;
    BaseBindingCellAdapter<T> mAdapter;

    public BaseBindingCell(@LayoutRes int layoutId) {
        viewType = getViewTypeByLayoutId(layoutId);
        LAYOUT_SPARSE_ARRAY.put(viewType, layoutId);
    }

    public BaseBindingCell(@NonNull View view) {
        viewType = getViewTypeByView(view);
        VIEW_SPARSE_ARRAY.put(viewType, view);
    }

    public int getViewType() {
        return viewType;
    }

    public BaseBindingCellAdapter<T> getAdapter() {
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

    public void update() {
        //noinspection unchecked
        getAdapter().updateItem((T) this);
    }

    public int getIndex() {
        //noinspection SuspiciousMethodCalls
        return getAdapter().getItems().indexOf(this);
    }
}
