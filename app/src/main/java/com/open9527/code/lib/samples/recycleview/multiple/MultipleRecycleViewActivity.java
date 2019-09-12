package com.open9527.code.lib.samples.recycleview.multiple;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.open9527.code.common.activity.CommonTitleActivity;
import com.open9527.code.common.recycleview.BaseCell;
import com.open9527.code.common.recycleview.BaseCellAdapter;
import com.open9527.code.lib.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/11 17:03.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class MultipleRecycleViewActivity extends CommonTitleActivity {

    private BaseCellAdapter multipleAdapter;
    private List<BaseCell> list = new LinkedList<>();
    private ArrayList<String> imagelist = new ArrayList<String>(Arrays.asList(
            "https://github.com/open9527/Images/blob/master/icon/unsplash/cat/1.jpg",
            "https://github.com/open9527/Images/blob/master/icon/unsplash/cat/2.jpg",
            "https://github.com/open9527/Images/blob/master/icon/unsplash/cat/3.jpg",
            "https://github.com/open9527/Images/blob/master/icon/unsplash/cat/4.jpg",
            "https://github.com/open9527/Images/blob/master/icon/unsplash/cat/5.jpg",
            "https://github.com/open9527/Images/blob/master/icon/unsplash/cat/6.jpg",
            "https://github.com/open9527/Images/blob/master/icon/unsplash/cat/7.jpg",
            "https://github.com/open9527/Images/blob/master/icon/unsplash/cat/8.jpg",
            "https://github.com/open9527/Images/blob/master/icon/unsplash/cat/9.jpg"
    )) {
    };

    @Override
    public CharSequence bindTitle() {
        return "MultipleRecycleViewActivity";
    }

    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_multiple_recycleview;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        RecyclerView recyclerView = findViewById(R.id.rv_list);
        multipleAdapter = new BaseCellAdapter<BaseCell>();
        multipleAdapter.setHasStableIds(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 12);
        list.add(new BannerCell(imagelist));
        list.add(new TabCell());
        list.add(new TabCell());
        list.add(new TabCell());
        list.add(new TabCell());
        list.add(new TabCell());
        list.add(new TabCell());
        list.add(new MarqueeCell(imagelist));
        list.add(new TextCell());
        list.add(new LandscapeCell(imagelist));
        multipleAdapter.setItems(list);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (multipleAdapter.getItem(position) instanceof BannerCell) {
                    return 12;
                } else if (multipleAdapter.getItem(position) instanceof TabCell) {
                    return 4;
                } else if (multipleAdapter.getItem(position) instanceof TextCell) {
                    return 12;
                } else if (multipleAdapter.getItem(position) instanceof MarqueeCell) {
                    return 12;
                } else if (multipleAdapter.getItem(position) instanceof LandscapeCell) {
                    return 12;
                }
                return 12;
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(multipleAdapter);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onDebouncingClick(@NonNull View view) {

    }
}
