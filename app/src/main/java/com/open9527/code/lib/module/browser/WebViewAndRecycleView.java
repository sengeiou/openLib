package com.open9527.code.lib.module.browser;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.open9527.code.common.databinding.BindingBaseCell;
import com.open9527.code.common.databinding.BindingBaseCellAdapter;
import com.open9527.code.common.databinding.BindingItemViewHolder;
import com.open9527.code.common.databinding.CommonBindingTitleActivity;
import com.open9527.code.lib.R;
import com.open9527.code.lib.cell.DescCell;
import com.open9527.code.lib.databinding.ActivityWebRecycleBinding;
import com.open9527.code.lib.databinding.ItemCellWebviewBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/31 16:29.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class WebViewAndRecycleView extends CommonBindingTitleActivity<ActivityWebRecycleBinding> {

    private List<BindingBaseCell> list = new ArrayList<>();

    @Override
    public void initData(@Nullable Bundle bundle) {
        list.add(0, new WebViewCell());
        for (int i = 0; i < 10; i++) {
            list.add(new DescCell("这是一条评论"+i));
        }
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_web_recycle;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        initAdapter();
    }

    private void initAdapter() {
        mBinding.rvList.setLayoutManager(new LinearLayoutManager(this));
        BindingBaseCellAdapter<BindingBaseCell> mAdapter = new BindingBaseCellAdapter<>();
        mBinding.rvList.setAdapter(mAdapter);
        mAdapter.addItems(list);
    }

    private View itemView;

    private class WebViewCell extends BindingBaseCell {
        public WebViewCell() {
            super(R.layout.item_cell_webview);
        }

        @Override
        public void bind(@NonNull BindingItemViewHolder holder, int position) {
            itemView = holder.itemView;
            ItemCellWebviewBinding binding = (ItemCellWebviewBinding) holder.mBinding;
            String url = "https://github.com/open9527";
            binding.x5Web.loadUrl(url);
        }
    }
}
