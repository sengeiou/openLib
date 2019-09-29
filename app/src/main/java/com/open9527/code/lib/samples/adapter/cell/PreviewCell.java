package com.open9527.code.lib.samples.adapter.cell;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.open9527.code.common.recycleview.BaseCell;
import com.open9527.code.common.recycleview.holder.ItemViewHolder;
import com.open9527.code.lib.R;
import com.open9527.code.lib.model.ShareElementBean;
import com.open9527.code.lib.utils.CommonUtils;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/4 17:39.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class PreviewCell extends BaseCell<PreviewCell> {
    private ShareElementBean shareElementBean;
    private ItemViewHolder holder;

    public PreviewCell() {
        super(R.layout.item_preview);
    }

    public PreviewCell(ShareElementBean shareElementBean) {
        super(R.layout.item_preview);
        this.shareElementBean = shareElementBean;

    }

    @Override
    public void bind(@NonNull ItemViewHolder holder, int position) {
        this.holder = holder;
        ImageView icon = holder.findViewById(R.id.iv_photo);
        CommonUtils.imageLoad(holder.itemView.getContext(), icon, shareElementBean.url, ImageView.ScaleType.FIT_XY);
//        holder.setOnClickListener(R.id.iv_photo, view -> {
//            if (mOnCellClickListener != null) {
//                mOnCellClickListener.onCellClick(PreviewCell.this, position);
//            }
//        });
    }

    public ShareElementBean getData() {
        return shareElementBean;
    }

    public View getShareElement() {
        return holder == null ? null : holder.findViewById(R.id.iv_photo);
    }

    private ImageCell.OnCellClickListener mOnCellClickListener;

    public void setOnCellClickListener(ImageCell.OnCellClickListener onCellClickListener) {
        this.mOnCellClickListener = onCellClickListener;
    }

    public interface OnCellClickListener {
        void onCellClick(BaseCell cell, int position);
    }
}
