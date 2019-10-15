package com.open9527.code.common.recycleview;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ClickUtils;
import com.open9527.code.common.recycleview.holder.ItemViewHolder;
import com.open9527.code.common.recycleview.interfaces.ICellClickListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by     : open9527
 * Created times  : on 2019/8/22 17:55.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class BaseCellAdapter<Item extends BaseCell> extends RecyclerView.Adapter<ItemViewHolder> {

    public List<Item> mItems = new ArrayList<>();
    ;

    private boolean hasClick = true;

    public BaseCellAdapter() {
        this(false, true);
    }

    public BaseCellAdapter(boolean hasClick) {
        this(false, hasClick);
    }

    public BaseCellAdapter(boolean... booleans) {
        if (booleans.length > 0) {
            setHasStableIds(booleans[0]);
            if (booleans.length > 1) {
                this.hasClick = booleans[1];
            }
        }
    }

    @Override
    public final int getItemViewType(int position) {
        Item item = mItems.get(position);
        item.mAdapter = this;
        return item.getViewType();
    }

    @Override
    public long getItemId(int position) {
        return mItems.get(position).getItemId();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return Item.onCreateViewHolder(parent, viewType);
    }

    @Override
    public final void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        mItems.get(position).bind(holder, position);
        //配置点击事件
        if (hasClick) {
            ClickUtils.applyGlobalDebouncing(holder.itemView, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (iCellClickListener != null)
                        iCellClickListener.onItemClick(view, position, mItems.get(position));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public void onViewRecycled(@NonNull ItemViewHolder holder) {
        super.onViewRecycled(holder);
        int position = holder.getAdapterPosition();
        if (position < 0 || position >= mItems.size()) {
            return;
        }
        mItems.get(position).onViewRecycled(holder, position);
    }

    public void setItems(@NonNull final List<Item> items) {
        mItems = items;
    }

    public List<Item> getItems() {
        return Collections.unmodifiableList(mItems);
    }

    public Item getItem(@IntRange(from = 0) final int position) {
        return mItems.get(position);
    }

    public boolean isEmpty() {
        return mItems.isEmpty();
    }

    ///////////////////////////////////////////////////////////////////////////
    // id
    ///////////////////////////////////////////////////////////////////////////

    public Item getItemById(final long id) {
        int itemIndex = getItemIndexById(id);
        if (itemIndex != -1) {
            return mItems.get(itemIndex);
        } else {
            return null;
        }
    }

    public int getItemIndexById(final long id) {
        for (int i = 0; i < mItems.size(); i++) {
            if (getItemId(i) == id) {
                return i;
            }
        }
        return -1;
    }

    public boolean hasItemWithId(final long id) {
        return getItemIndexById(id) != -1;
    }

    public int replaceItemById(final long id, @NonNull final Item item) {
        return replaceItemById(id, item, false);
    }

    public int replaceItemById(final long id, @NonNull final Item item, boolean notifyChanged) {
        int itemIndex = getItemIndexById(id);
        if (itemIndex != -1) {
            replaceItem(itemIndex, item, notifyChanged);
        }
        return itemIndex;
    }

    public int removeItemById(final long id) {
        return removeItemById(id, false);
    }

    public int removeItemById(final long id, boolean notifyRemoved) {
        for (int i = 0; i < mItems.size(); i++) {
            if (getItemId(i) == id) {
                removeItem(i, notifyRemoved);
                return i;
            }
        }
        return -1;
    }

    ///////////////////////////////////////////////////////////////////////////
    // operate
    ///////////////////////////////////////////////////////////////////////////

    public void addItem(@NonNull final Item item) {
        addItem(item, false);
    }

    public void addItem(@NonNull final Item item, boolean notifyInserted) {
        mItems.add(item);
        if (notifyInserted) notifyItemInserted(mItems.size() - 1);
    }

    public void addItem(@IntRange(from = 0) final int index, @NonNull final Item item) {
        addItem(index, item, false);
    }

    public void addItem(@IntRange(from = 0) final int index, @NonNull final Item item, boolean notifyInserted) {
        mItems.add(index, item);
        if (notifyInserted) notifyItemInserted(index);
    }

    public void addItems(@NonNull final List<Item> items) {
        addItems(items, false);
    }

    public void addItems(@NonNull final List<Item> items, boolean notifyInserted) {
        mItems.addAll(items);
        if (notifyInserted) notifyItemRangeInserted(mItems.size() - items.size() - 1, items.size());
    }

    public void addItems(@IntRange(from = 0) final int index, @NonNull final List<Item> items) {
        addItems(index, items, false);
    }

    public void addItems(@IntRange(from = 0) final int index, @NonNull final List<Item> items, boolean notifyInserted) {
        mItems.addAll(index, items);
        if (notifyInserted) notifyItemRangeInserted(index, items.size());
    }

    public void swapItem(@IntRange(from = 0) final int firstIndex, @IntRange(from = 0) final int secondIndex) {
        swapItem(firstIndex, secondIndex, false);
    }

    public void swapItem(@IntRange(from = 0) final int firstIndex,
                         @IntRange(from = 0) final int secondIndex, boolean notifyMoved) {
        Collections.swap(mItems, firstIndex, secondIndex);
        if (notifyMoved) notifyItemMoved(firstIndex, secondIndex);
    }

    public Item replaceItem(@IntRange(from = 0) final int index, @NonNull final Item item) {
        return replaceItem(index, item, false);
    }

    public Item replaceItem(@IntRange(from = 0) final int index, @NonNull final Item item, boolean notifyChanged) {
        Item prevItem = mItems.set(index, item);
        if (notifyChanged) notifyItemChanged(index);
        return prevItem;
    }

    public boolean replaceItems(@NonNull final List<Item> items) {
        return replaceItems(items, false);
    }

    public boolean replaceItems(@NonNull final List<Item> items, boolean notifyDataSetChanged) {
        mItems.clear();
        boolean added = mItems.addAll(items);
        if (notifyDataSetChanged) notifyDataSetChanged();
        return added;
    }

    public Item removeItem(@IntRange(from = 0) final int index) {
        return removeItem(index, false);
    }

    public Item removeItem(@IntRange(from = 0) final int index, boolean notifyRemoved) {
        Item removedItem = mItems.remove(index);
        if (notifyRemoved) notifyItemRemoved(index);
        return removedItem;
    }

    public int removeItem(@NonNull final Item object) {
        return removeItem(object, false);
    }

    public int removeItem(@NonNull final Item object, boolean notifyRemoved) {
        int itemIndex = mItems.indexOf(object);
        if (itemIndex != -1) {
            mItems.remove(itemIndex);
            if (notifyRemoved) notifyItemRemoved(itemIndex);
        }
        return itemIndex;
    }

    public void clear() {
        clear(false);
    }

    public void clear(boolean notifyDataSetChanged) {
        mItems.clear();
        if (notifyDataSetChanged) notifyDataSetChanged();
    }

    public void sortItems(@NonNull final Comparator<Item> comparator) {
        sortItems(comparator, false);
    }

    public void sortItems(@NonNull final Comparator<Item> comparator, boolean notifyDataSetChanged) {
        Collections.sort(mItems, comparator);
        if (notifyDataSetChanged) notifyDataSetChanged();
    }

    /*配置点击事件*/

    private ICellClickListener iCellClickListener;

    public void setOnCellClickListener(ICellClickListener iCellClickListener) {
        this.iCellClickListener = iCellClickListener;
    }

}
