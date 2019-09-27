package com.open9527.code.lib.samples.adapter.cell;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.open9527.code.common.recycleview.BaseCell;
import com.open9527.code.common.recycleview.holder.ItemViewHolder;
import com.open9527.code.lib.R;
import com.open9527.code.lib.model.ImageBean;
import com.open9527.code.lib.model.PhotoBean;
import com.open9527.code.lib.utils.CommonUtils;

/**
 * Created by     : open9527
 * Created times  : on 2019/8/26 9:53.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class ImageCell extends BaseCell<ImageCell> {

    private ImageBean imageBean;
    private ItemViewHolder holder;


    public ImageCell(ImageBean imageBean) {
        super(R.layout.item_nine_pic);
        this.imageBean = imageBean;
    }

    @Override
    public void bind(@NonNull ItemViewHolder holder, int position) {
        this.holder = holder;
        ImageView icon = holder.findViewById(R.id.iv_photo);
        CommonUtils.imageLoad(holder.itemView.getContext(), icon, imageBean.url);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnCellClickListener != null) {
                    mOnCellClickListener.onCellClick(ImageCell.this, position);
                }
            }
        });
    }

    public ImageBean getData() {
        return imageBean;
    }

    public View getShareElement() {
        return holder == null ? null : holder.findViewById(R.id.iv_photo);
    }

    private OnCellClickListener mOnCellClickListener;

    public void setOnCellClickListener(OnCellClickListener onCellClickListener) {
        this.mOnCellClickListener = onCellClickListener;
    }

    public interface OnCellClickListener {
        void onCellClick(BaseCell cell, int position);
    }
}
