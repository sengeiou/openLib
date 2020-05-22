package com.open9527.code.lib.module.rv.click;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.open9527.code.common.databinding.BindingBaseCell;
import com.open9527.code.common.databinding.BindingBaseCellAdapter;
import com.open9527.code.common.databinding.CommonBindingTitleActivity;
import com.open9527.code.common.recycleview.GridSpaceItemDecoration;
import com.open9527.code.common.recycleview.SpacesItemDecoration;
import com.open9527.code.lib.R;
import com.open9527.code.lib.databinding.ActivityRecycleviewBinding;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewClickActivity extends CommonBindingTitleActivity<ActivityRecycleviewBinding> implements ICellClick {

    private BindingBaseCellAdapter<BindingBaseCell> mAdapter;
    //    private List<String> stringList = new ArrayList<>();
    private List<BindingBaseCell> baseItemList = new ArrayList<>();

    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_recycleview;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
//        setSpacesItemDecoration();
        setGridSpaceItemDecoration();
        mAdapter = new BindingBaseCellAdapter<>();
        mBinding.rvList.setAdapter(mAdapter);


        for (int i = 0; i < 20; i++) {
//            stringList.add(String.valueOf("这是一段文字" + i));
            baseItemList.add(new ClickItemCell(String.valueOf("这是一段文字" + i)));
        }
        mAdapter.setItems(baseItemList);
    }

    private void setGridSpaceItemDecoration() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity, 4);
        //配置分割线
        mBinding.rvList.setLayoutManager(gridLayoutManager);

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                //TODO:需要区分所占的位置,
                if (position % 5 == 0) {
                    return 4;
                }
                return 1;
            }
        });

        if (mBinding.rvList.getItemDecorationCount() == 0) {
            GridSpaceItemDecoration gridSpaceItemDecoration = new GridSpaceItemDecoration(SizeUtils.dp2px(10), true).setNoShowSpace(0, 0);
            mBinding.rvList.addItemDecoration(gridSpaceItemDecoration);
        }

    }

    private void setSpacesItemDecoration() {
        //配置分割线
        mBinding.rvList.setLayoutManager(new LinearLayoutManager(mActivity));
        if (mBinding.rvList.getItemDecorationCount() == 0) {
            SpacesItemDecoration spacesItemDecoration = new SpacesItemDecoration(this, SpacesItemDecoration.VERTICAL).setNoShowDivider(0, 0);
            spacesItemDecoration.setParam(R.color.color_222, SizeUtils.dp2px(10));
            mBinding.rvList.addItemDecoration(spacesItemDecoration);
        }
    }

    @Override
    public void onItemClick() {
        ToastUtils.showLong("onItemClick");
    }

    @Override
    public void onItemClick(BindingBaseCell cell) {
        if (cell instanceof ClickItemCell) {
            ClickItemCell clickItemCell = (ClickItemCell) cell;
            ToastUtils.showLong(clickItemCell.observableField.get());
        }

    }


}
