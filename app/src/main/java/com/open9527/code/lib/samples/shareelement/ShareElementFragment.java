package com.open9527.code.lib.samples.shareelement;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.open9527.code.base.BaseFragment;
import com.open9527.code.common.recycleview.BaseCell;
import com.open9527.code.common.recycleview.BaseCellAdapter;
import com.open9527.code.lib.R;
import com.open9527.code.lib.model.ImageBean;
import com.open9527.code.lib.model.ShareElementBean;
import com.open9527.code.lib.samples.adapter.cell.ImageCell;
import com.open9527.code.shareelement.ShareElementHelper;
import com.open9527.code.shareelement.transition.IShareElements;
import com.open9527.code.shareelement.transition.ShareElementInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/10 13:22.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class ShareElementFragment extends BaseFragment implements IShareElements, ImageCell.OnCellClickListener {
    public static final String KEY_DATA = "data";
    public static final String KEY_SELECT = "select";
    static final int REQUEST_CONTENT = 223;
    private RecyclerView mRecyclerView;
    private GridLayoutManager gridLayoutManager;
    private ArrayList<Parcelable> mDataList = new ArrayList<>();
    private ImageCell imageCell;
    private BaseCellAdapter mImageAdapter;

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
        gridLayoutManager = new GridLayoutManager(mActivity, 3);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        initCell();
    }

    private void initCell() {
        List<ImageCell> imageCells = new ArrayList<>();
        mImageAdapter = new BaseCellAdapter<ImageCell>();
        mImageAdapter.setHasStableIds(true);
        //https://github.com/open9527/Images/blob/master/icon/unsplash/cat/1.jpg
        mDataList.add(new ImageBean("http://img1.imgtn.bdimg.com/it/u=4206294871,879077254&fm=26&gp=0.jpg", 0, 0));
        mDataList.add(new ImageBean("http://img1.imgtn.bdimg.com/it/u=4206294871,879077254&fm=26&gp=0.jpg", 0, 0));
        mDataList.add(new ImageBean("http://img1.imgtn.bdimg.com/it/u=4206294871,879077254&fm=26&gp=0.jpg", 0, 0));
        mDataList.add(new ImageBean("http://img1.imgtn.bdimg.com/it/u=4206294871,879077254&fm=26&gp=0.jpg", 0, 0));
        mDataList.add(new ImageBean("http://img1.imgtn.bdimg.com/it/u=4206294871,879077254&fm=26&gp=0.jpg", 0, 0));
        mDataList.add(new ImageBean("http://img1.imgtn.bdimg.com/it/u=4206294871,879077254&fm=26&gp=0.jpg", 0, 0));
        mDataList.add(new ImageBean("http://img1.imgtn.bdimg.com/it/u=4206294871,879077254&fm=26&gp=0.jpg", 0, 0));
        mDataList.add(new ImageBean("http://img1.imgtn.bdimg.com/it/u=4206294871,879077254&fm=26&gp=0.jpg", 0, 0));
        mDataList.add(new ImageBean("http://img1.imgtn.bdimg.com/it/u=4206294871,879077254&fm=26&gp=0.jpg", 0, 0));
        for (int i = 0; i < mDataList.size(); i++) {
            final Parcelable data = mDataList.get(i);
            final ImageCell imageCell = new ImageCell((ImageBean) data);
            imageCell.setOnCellClickListener(this);
            imageCells.add(imageCell);
        }
        mRecyclerView.setAdapter(mImageAdapter);
        mImageAdapter.setItems(imageCells);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onDebouncingClick(@NonNull View view) {

    }

    @Override
    public void onCellClick(BaseCell cell, int position) {
        //ToastUtils.showShort("数据->" + position);
        imageCell = (ImageCell) cell;
        Intent intent = new Intent(mActivity, PreviewActivity.class);
        intent.putParcelableArrayListExtra(KEY_DATA, mDataList);
        intent.putExtra(KEY_SELECT, mDataList.indexOf(imageCell.getData()));
        Bundle options = ShareElementHelper.buildOptionsBundle(mActivity, this);
        startActivityForResult(intent, REQUEST_CONTENT, options);
    }

    @Override
    public ShareElementInfo[] getShareElements() {
        ShareElementBean data = imageCell.getData();
        ShareElementInfo info = new ShareElementInfo(imageCell.getShareElement(), data);
        return new ShareElementInfo[]{info};
    }

    public void selectShareElement(ShareElementInfo shareElementInfo) {
        ShareElementBean data = (ShareElementBean) shareElementInfo.getData();
        for (int i = 0; i < mImageAdapter.getItemCount(); i++) {
            final BaseCell baseCell = mImageAdapter.getItem(i);
            final ImageCell cell = (ImageCell) baseCell;
            if (imageCell.getData().equals(data)) {
                imageCell = cell;
                GridLayoutManager layoutManager = (GridLayoutManager) mRecyclerView.getLayoutManager();
                layoutManager.scrollToPosition(i);
                return;
            }
        }
    }

}