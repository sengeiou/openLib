package com.open9527.code.lib.cell;

import android.app.Activity;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.blankj.utilcode.util.LogUtils;
import com.open9527.code.lib.R;
import com.open9527.code.lib.databinding.ItemCellPhotoviewBinding;
import com.open9527.code.lib.utils.ImageLoadUtils;
import com.open9527.recycleview.adapter.BaseBindingCell;
import com.open9527.recycleview.adapter.BaseBindingCellViewHolder;
import com.open9527.recycleview.adapter.IBaseCellClick;


public class PhotoViewCell extends BaseBindingCell<GalleryCell> implements IBaseCellClick {
    public ObservableField<String> stringUrl = new ObservableField<String>();
    private Activity mActivity;

    public PhotoViewCell(String url) {
        super(R.layout.item_cell_photoview);
        stringUrl.set(url);
    }

    @Override
    public void bind(@NonNull BaseBindingCellViewHolder holder, int position) {
        mActivity = (Activity) holder.itemView.getContext();
        ItemCellPhotoviewBinding cellPhotoViewBinding = (ItemCellPhotoviewBinding) holder.mBinding;
        cellPhotoViewBinding.setCell(this);
        ImageLoadUtils.imageLoad(holder.itemView.getContext(), cellPhotoViewBinding.photoView, stringUrl.get());
    }

    @Override
    public void onItemClick() {
        LogUtils.i(TAG, "onItemClick-->" + stringUrl.get());
        if (mActivity == null) return;
        // 单击图片退出当前的 Activity
        if (!mActivity.isFinishing()) {
            mActivity.finish();
        }
    }

    @Override
    public void onClick(View v) {
        onItemClick();
    }
}