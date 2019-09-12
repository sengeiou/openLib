package com.open9527.code.lib.samples.shareelement;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.open9527.code.base.BaseFragment;
import com.open9527.code.common.recycleview.BaseCell;
import com.open9527.code.common.recycleview.BaseCellAdapter;
import com.open9527.code.lib.R;
import com.open9527.code.lib.model.ImageBean;
import com.open9527.code.lib.model.ShareElementBean;
import com.open9527.code.lib.samples.adapter.cell.ImageCell;
import com.open9527.code.lib.samples.adapter.cell.PreviewCell;
import com.open9527.code.shareelement.ShareElementHelper;
import com.open9527.code.shareelement.transition.IShareElements;
import com.open9527.code.shareelement.transition.ShareElementInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/4 18:19.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class PreviewFragment extends BaseFragment implements IShareElements, ImageCell.OnCellClickListener {


    private RecyclerView mRecyclerView;
    private BaseCellAdapter mImageAdapter;
    private PreviewCell previewCell;

    @Override
    public void initData(@Nullable Bundle bundle) {
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_preview;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        mRecyclerView = findViewById(R.id.rv_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(mRecyclerView);
        initCell();
        ShareElementHelper.postStartTransition(mActivity);
    }

    private void initCell() {
        mImageAdapter = new BaseCellAdapter<PreviewCell>();
        assert getArguments() != null;
        ArrayList<Parcelable> mDataList = getArguments().getParcelableArrayList(ShareElementFragment.KEY_DATA);
        int select = getArguments().getInt(ShareElementFragment.KEY_SELECT, 0);
        List<PreviewCell> previewCells = new ArrayList<>(mDataList.size());
        mImageAdapter.setHasStableIds(true);
        for (int i = 0; i < mDataList.size(); i++) {
            final Parcelable data = mDataList.get(i);
            final PreviewCell previewCell = new PreviewCell((ShareElementBean) data);
            previewCell.setOnCellClickListener(this);
            previewCells.add(previewCell);
        }
        mRecyclerView.setAdapter(mImageAdapter);
        mImageAdapter.setItems(previewCells);
        previewCell = previewCells.get(select);
        mRecyclerView.scrollToPosition(select);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onDebouncingClick(@NonNull View view) {

    }

    @Override
    public void onCellClick(BaseCell cell, int position) {
        ToastUtils.showShort("数据->" + position);

    }


    public ShareElementInfo[] getShareElements() {
        PreviewCell item = previewCell;
        if (item != null) {
            return new ShareElementInfo[]{new ShareElementInfo(item.getShareElement(), item.getData())};
        }
        return new ShareElementInfo[0];
    }
}
