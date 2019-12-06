package com.open9527.code.lib.module.customview;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.open9527.code.common.databinding.BindingBaseCell;
import com.open9527.code.common.databinding.BindingBaseCellAdapter;
import com.open9527.code.common.databinding.CommonBindingTitleActivity;
import com.open9527.code.common.databinding.interfaces.IBindingCellClickListener;
import com.open9527.code.lib.R;
import com.open9527.code.lib.cell.SelectCell;
import com.open9527.code.lib.databinding.ActivityRadioRecycleviewBinding;
import com.open9527.code.lib.model.RadioBean;
import com.open9527.code.lib.module.rv.common.SingleCheckItem;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by     : open9527
 * Created times  : on 2019/11/15 14:45.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class RadioRecycleViewActivity extends CommonBindingTitleActivity<ActivityRadioRecycleviewBinding> implements IBindingCellClickListener {
    private List<BindingBaseCell> cellList = new LinkedList<>();
    private BindingBaseCellAdapter<BindingBaseCell> mAdapter;

    @Override
    public void initData(@Nullable Bundle bundle) {
        for (int i = 0; i < 20; i++) {
            cellList.add(new SelectCell(new RadioBean("数据" + i)));
        }
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_radio_recycleview;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        mBinding.rvList.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rvList.setHasFixedSize(true);
        initBindingAdapter();
    }


    private void initBindingAdapter() {
        mAdapter = new BindingBaseCellAdapter<>();
        mBinding.rvList.setAdapter(mAdapter);
        mAdapter.setItems(cellList);
        mAdapter.setOnBindingCellClickListener(this);
    }

    @Override
    public void onItemClick(View view, int position, BindingBaseCell... bindingBaseCells) {
        SelectCell selectCell = (SelectCell) bindingBaseCells[0];
        ToastUtils.showShort(selectCell.titleObservableField.get());
        if (SelectCell.checkIndex >= 0) {
            SelectCell oldSelectCell = selectCell.getAdapter().getItem(SelectCell.checkIndex);
            oldSelectCell.selectObservableField.set(false);
            oldSelectCell.getAdapter().updateItem(SelectCell.checkIndex);
        }
        selectCell.selectObservableField.set(true);
        selectCell.getAdapter().updateItem(position);
        SelectCell.checkIndex = position;
    }
}
