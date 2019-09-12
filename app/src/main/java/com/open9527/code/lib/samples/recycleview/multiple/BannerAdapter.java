package com.open9527.code.lib.samples.recycleview.multiple;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.open9527.code.common.recycleview.holder.ItemViewHolder;
import com.open9527.code.lib.R;
import com.open9527.code.lib.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/12 12:16.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class BannerAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    private List<String> urlList = new ArrayList<>();
    private Context mContext;

    public BannerAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<String> list) {
        urlList.clear();
        this.urlList = list;
    }

    public List<String> getData() {
        return urlList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_banner_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        final ImageView ivBanner = holder.findViewById(R.id.iv_banner);
        if (urlList.size() <= 0) {
            CommonUtils.imageLoad(holder.itemView.getContext(), ivBanner, "", ImageView.ScaleType.FIT_XY);
        } else {
            //表示position对图片列表list取余，这样list.get(position%list.size())才能按顺序循环展示图片。
            String url = urlList.get(position % urlList.size());
            CommonUtils.imageLoad(holder.itemView.getContext(), ivBanner, url, ImageView.ScaleType.FIT_XY);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnCellClickListener != null && urlList.size() > 0) {
                        mOnCellClickListener.onCellClick(url, position % urlList.size());
                    }

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (urlList.size() != 1) {
            return Integer.MAX_VALUE; // 无限轮播
        } else {
            return urlList.size();
        }
    }

    private OnCellClickListener mOnCellClickListener;

    public void setOnCellClickListener(OnCellClickListener onCellClickListener) {
        this.mOnCellClickListener = onCellClickListener;
    }

    public interface OnCellClickListener {
        void onCellClick(String url, int position);
    }
}
