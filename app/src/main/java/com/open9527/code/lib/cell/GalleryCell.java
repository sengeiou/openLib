package com.open9527.code.lib.cell;


import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.open9527.code.lib.R;
import com.open9527.code.lib.databinding.ItemCellGalleryBinding;
import com.open9527.code.lib.model.CommonKey;
import com.open9527.code.lib.model.GalleryBean;
import com.open9527.code.lib.module.image.preview.PhotoViewActivity;
import com.open9527.code.lib.module.image.preview.PreviewActivity;
import com.open9527.code.lib.utils.ImageLoadUtils;
import com.open9527.recycleview.adapter.BaseBindingCell;
import com.open9527.recycleview.adapter.BaseBindingCellViewHolder;
import com.open9527.recycleview.adapter.IBaseCellClick;

import java.util.ArrayList;

/**
 * 相册cell
 */
public class GalleryCell extends BaseBindingCell<GalleryCell> implements IBaseCellClick {
    public ObservableField<String> stringUrl = new ObservableField<String>();
    public ObservableField<ArrayList<String>> stringUrlList = new ObservableField<ArrayList<String>>();


    public GalleryCell(ArrayList<String> urlList, String url) {
        super(R.layout.item_cell_gallery);
        stringUrlList.set(urlList);
        stringUrl.set(url);
    }

    @Override
    public void bind(@NonNull BaseBindingCellViewHolder holder, int position) {
        ItemCellGalleryBinding cellGalleryBinding = (ItemCellGalleryBinding) holder.mBinding;
        cellGalleryBinding.setCell(this);
        cellGalleryBinding.setPosition(position);
        ImageLoadUtils.imageLoad(holder.itemView.getContext(), cellGalleryBinding.ivImage, stringUrl.get());
    }

    @Override
    public void onItemClick() {
        LogUtils.i(TAG, "onItemClick-->" + stringUrl.get());
//        ActivityUtils.startActivity(CommonKey.createBundle(new GalleryBean(postion, stringUrlList.get())), PreviewActivity.class);
        ActivityUtils.startActivity(CommonKey.createBundle(new GalleryBean(this.getIndex(), stringUrlList.get())), PhotoViewActivity.class);
    }

    @Override
    public void onClick(View v) {
        onItemClick();
    }
}
