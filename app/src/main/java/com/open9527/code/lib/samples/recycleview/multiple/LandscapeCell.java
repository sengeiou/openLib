package com.open9527.code.lib.samples.recycleview.multiple;

import android.util.SparseArray;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.open9527.code.common.recycleview.BaseCell;
import com.open9527.code.common.recycleview.BaseCellAdapter;
import com.open9527.code.common.recycleview.holder.ItemViewHolder;
import com.open9527.code.lib.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/12 13:15.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class LandscapeCell extends BaseCell<LandscapeCell> {
    private SparseArray<BaseCellAdapter> cellArray = new SparseArray<>();
    private List<String> list;


    public LandscapeCell(List<String> list) {
        super(R.layout.item_recycle_view);
        this.list = list;
    }

    @Override
    public void bind(@NonNull ItemViewHolder holder, int position) {
        List<BaseCell> listCell = new LinkedList<>();
        RecyclerView recyclerView = holder.findViewById(R.id.rv_list);
        BaseCellAdapter recycleAdapter = findCellAdapterById(R.id.rv_list);
        recycleAdapter.setHasStableIds(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(holder.itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recycleAdapter);
        for (int i = 0; i < list.size(); i++) {
            listCell.add(new CardCell());
        }
        recycleAdapter.setItems(listCell);
    }

    public <T extends BaseCellAdapter> T findCellAdapterById(@IdRes final int viewId) {
        BaseCellAdapter cellAdapter = cellArray.get(viewId);
        if (cellAdapter == null) {
            cellAdapter = new BaseCellAdapter();
            cellArray.put(viewId, cellAdapter);
        }
        return (T) cellAdapter;
    }
}
