package com.open9527.code.lib.module.other;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.utilcode.util.ToastUtils;
import com.open9527.code.common.databinding.BindingBaseCell;
import com.open9527.code.common.databinding.BindingBaseCellAdapter;
import com.open9527.code.common.databinding.CommonBindingTitleActivity;
import com.open9527.code.common.recycleview.BaseCell;
import com.open9527.code.common.recycleview.BaseCellAdapter;
import com.open9527.code.common.recycleview.interfaces.ICellClickListener;
import com.open9527.code.lib.R;
import com.open9527.code.lib.cell.DescCell;
import com.open9527.code.lib.databinding.ActivityRecycleviewBinding;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/20 13:47.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class RecycleViewActivity extends CommonBindingTitleActivity<ActivityRecycleviewBinding> implements ICellClickListener {

    private List<BindingBaseCell> cellList = new LinkedList<>();
    private BindingBaseCellAdapter<BindingBaseCell> mAdapter;


    @Override
    public void initData(@Nullable Bundle bundle) {
        for (int i = 0; i < 20; i++) {
            cellList.add(new DescCell("数据" + i));
        }
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_recycleview;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        initAdapter();
    }

    private void initAdapter() {
        mBinding.rvList.setLayoutManager(new LinearLayoutManager(mActivity));
        mBinding.rvList.setHasFixedSize(true);
        mAdapter = new BindingBaseCellAdapter<>();
        mBinding.rvList.setAdapter(mAdapter);
        mAdapter.setItems(cellList);
//        mAdapter.setOnCellClickListener(this);
    }

//    @Override
//    public void onItemClick(View view, int position, BaseCell... baseCells) {
//        final DescCell descCell = (DescCell) baseCells[0];
//        if (view.getId() == R.id.tv_desc) {
//            ToastUtils.showShort("onItemClick:" + descCell.stringObservableField.get());
//        } else if (view.getId() == R.id.tv_desc_1) {
//            ToastUtils.showShort("onItemClick:" + descCell.stringObservableField1.get());
//        } else {
//            ToastUtils.showShort("onItemClick-- item");
//        }
//    }

//    @Override
//    public boolean onItemLongClick(View view, int position, BaseCell... baseCells) {
//        final DescCell descCell = (DescCell) baseCells[0];
//        if (view.getId() == R.id.tv_desc) {
//            ToastUtils.showShort("onItemLongClick:" + descCell.stringObservableField.get());
//        } else if (view.getId() == R.id.tv_desc_1) {
//            ToastUtils.showShort("onItemLongClick:" + descCell.stringObservableField1.get());
//        } else {
//            ToastUtils.showShort(" onItemLongClick--item");
//        }
//        return true;
//    }
}
