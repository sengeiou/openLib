package com.open9527.code.common.databinding.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.IntRange;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.open9527.code.common.databinding.adapter.interfaces.Function;
import com.open9527.code.common.databinding.adapter.interfaces.LongFunction;
import com.open9527.code.common.databinding.adapter.interfaces.ViewMap;
import com.open9527.code.common.recycleview.holder.ItemViewHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by     : Mr.kk.
 * Created times  : on 2018/6/2 15:35.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */

public class DataBindRvAdapter<T> extends RecyclerView.Adapter<DataBindRvAdapter.RVViewHolder> {
    private int variableId;
    private int layoutId;

    public interface BindCreate<R extends ViewDataBinding> {
        void change(R t);
    }

    public interface BindTo<R extends ViewDataBinding, T> {
        void bindTo(R r, T t);
    }

    public interface PayloadsBindTo<R extends ViewDataBinding, T> {
        void payloadsBindTo(R r, T t, Object o);
    }


    private SparseArray<Function<T>> event;
    private SparseArray<LongFunction<T>> longEvent;
    private SparseArray<Object> data;
    private Context context;
    private List<T> list;
    private ViewMap<T> viewMap;

    public void addEvent(int variableId, Function<T> function) {
        event.put(variableId, function);
    }

    public void addLongEvent(int variableId, LongFunction<T> function) {
        longEvent.put(variableId, function);
    }


    private BindCreate bindCreate;
    private BindTo bindTo;
    private PayloadsBindTo payloadsbindTo;

    public <R extends ViewDataBinding> void setBindTo(BindTo<R, T> bindTo) {
        this.bindTo = bindTo;
    }

    public <R extends ViewDataBinding> void setPayloadsbindTo(PayloadsBindTo<R, T> payloadsbindTo) {
        this.payloadsbindTo = payloadsbindTo;
    }

    public <R extends ViewDataBinding> void setBindCreate(BindCreate<R> bindCreate) {
        this.bindCreate = bindCreate;
    }

    public DataBindRvAdapter(Context context, @LayoutRes int layoutId, int variableId) {
        this.context = context;
        this.list = new ArrayList<>();
        event = new SparseArray<>();
        longEvent = new SparseArray<>();
        data = new SparseArray<>();
        this.layoutId = layoutId;
        this.variableId = variableId;
    }

    public DataBindRvAdapter(Context context, int variableId, ViewMap<T> viewMap) {
        this.context = context;
        this.list = new ArrayList<>();
        event = new SparseArray<>();
        longEvent = new SparseArray<>();
        data = new SparseArray<>();
        this.viewMap = viewMap;
        this.variableId = variableId;
    }

    public void addNewList(@NonNull Collection<? extends T> newList) {
        if (newList.size() > 0) {
            list.clear();
            list.addAll(newList);
            notifyItemRangeInserted(0, newList.size());
        }
    }

    public void addList(@IntRange(from = 0) int position, @NonNull Collection<? extends T> add) {
        if (add.size() > 0) {
            list.addAll(position, add);
            notifyItemRangeInserted(position, add.size());
        }
    }

    public void addList(@NonNull Collection<? extends T> add) {
        if (add.size() > 0) {
            list.addAll(add);
            notifyItemRangeInserted(list.size() - add.size(), add.size());
        }
    }

    public void addData(int index, T add) {
        list.add(index, add);
        notifyItemInserted(index);
    }

    public void addData(T add) {
        list.add(add);
        notifyItemInserted(list.size());
    }


    public void remove(int index) {
        list.remove(index);
        notifyItemRemoved(index);
        notifyItemRangeChanged(index, list.size() - index);
    }

    public void remove(@NonNull T t) {
        if (list.contains(t)) {
            remove(list.indexOf(t));
        }
    }

    public List<T> getList() {
        return list;
    }


    @Override
    public DataBindRvAdapter.RVViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RVViewHolder hold = new RVViewHolder(DataBindingUtil.inflate(LayoutInflater.from(context), viewType, parent, false));
        if (bindCreate != null) {
            bindCreate.change(hold.binding);
        }
        return hold;
    }

    public void onBindViewHolder(@NonNull DataBindRvAdapter.RVViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (payloads.size() > 0) {
            holder.payloadsBindTo(list.get(position));
            if (payloadsbindTo != null) {
                payloadsbindTo.payloadsBindTo(holder.binding, list.get(position), payloads.get(position));
            }
        } else {
            onBindViewHolder(holder, position);
        }
    }

    public void onBindViewHolder(@NonNull DataBindRvAdapter.RVViewHolder holder, int position) {
        holder.bindTo(list.get(position));
        if (bindTo != null) {
            bindTo.bindTo(holder.binding, list.get(position));
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (viewMap != null) {
            return viewMap.layoutId(list.get(position));
        }
        return layoutId;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RVViewHolder extends ItemViewHolder {
        ViewDataBinding binding;

        public RVViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bindTo(Object value) {
            binding.setVariable(variableId, value);
            if (event != null && event.size() > 0) {
                for (int i = 0; i < event.size(); i++) {
                    binding.setVariable(event.keyAt(i), event.valueAt(i));
                }
            }
            if (longEvent != null && longEvent.size() > 0) {
                for (int i = 0; i < longEvent.size(); i++) {
                    binding.setVariable(longEvent.keyAt(i), longEvent.valueAt(i));
                }
            }
            if (data != null && data.size() > 0) {
                for (int i = 0; i < data.size(); i++) {
                    binding.setVariable(data.keyAt(i), data.valueAt(i));
                }
            }
            binding.executePendingBindings();
        }

        void payloadsBindTo(Object value) {
            binding.setVariable(variableId, value);
            if (event != null && event.size() > 0) {
                for (int i = 0; i < event.size(); i++) {
                    binding.setVariable(event.keyAt(i), event.valueAt(i));
                }
            }
            if (longEvent != null && longEvent.size() > 0) {
                for (int i = 0; i < longEvent.size(); i++) {
                    binding.setVariable(longEvent.keyAt(i), longEvent.valueAt(i));
                }
            }
            if (data != null && data.size() > 0) {
                for (int i = 0; i < data.size(); i++) {
                    binding.setVariable(data.keyAt(i), data.valueAt(i));
                }
            }
            binding.executePendingBindings();
        }
    }
}