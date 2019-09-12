package com.open9527.code.lib.samples.adapter.cell;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.open9527.code.common.recycleview.BaseCell;
import com.open9527.code.common.recycleview.holder.ItemViewHolder;
import com.open9527.code.image.picture.DragCloseHelper;
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
        DragCloseHelper dragCloseHelper = new DragCloseHelper(holder.itemView.getContext());
        dragCloseHelper.setShareElementMode(true);
        dragCloseHelper.setDragCloseViews(holder.itemView, icon);
        CommonUtils.imageLoad(holder.itemView.getContext(), icon, shareElementBean.url, ImageView.ScaleType.FIT_XY);
//        holder.setOnClickListener(R.id.iv_photo, view -> {
//            if (mOnCellClickListener != null) {
//                mOnCellClickListener.onCellClick(PreviewCell.this, position);
//            }
//        });

        dragCloseHelper.setDragCloseListener(new DragCloseHelper.DragCloseListener() {
            @Override
            public boolean intercept() {
                //默认false 不拦截。比如图片在放大状态，是不需要执行拖拽动画的等等。
                return false;
            }

            @Override
            public void dragStart() {
                //拖拽开始。可以在此额外处理一些逻辑
            }

            @Override
            public void dragging(float percent) {
                //拖拽中。percent当前的进度，取值0-1，可以在此额外处理一些逻辑
            }

            @Override
            public void dragCancel() {
                //拖拽取消，会自动复原。可以在此额外处理一些逻辑
            }

            @Override
            public void dragClose(boolean isShareElementMode) {
                //拖拽关闭，如果是共享元素的页面，需要执行activity的onBackPressed方法，注意如果使用finish方法，则返回的时候没有共享元素的返回动画
                if (isShareElementMode) {
//                    onBackPressed();
                }
            }
        });
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
