package com.open9527.code.lib.samples;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.open9527.code.common.activity.CommonTitleActivity;
import com.open9527.code.common.recycleview.BaseCellAdapter;
import com.open9527.code.lib.R;
import com.open9527.code.lib.model.PhotoBean;
import com.open9527.code.lib.samples.adapter.cell.PhotoCell;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by     : open9527
 * Created times  : on 2019/8/23 13:33.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class SamplesActivity extends CommonTitleActivity {

    private SamolesViewModel mViewModel;

    @Override
    public void initData(@Nullable Bundle bundle) {
        mViewModel = ViewModelProviders.of(this).get(SamolesViewModel.class);
        mViewModel.getPhotoInfo();
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_samples;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        RecyclerView recyclerView = findViewById(R.id.rv_samples);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));

        BaseCellAdapter mPhotoAdapter = new BaseCellAdapter<PhotoCell>();
        mPhotoAdapter.setHasStableIds(true);
        mViewModel.mPhotoInfoRepository.getData().observe(this, new Observer<List<PhotoBean>>() {
            @Override
            public void onChanged(List<PhotoBean> photoBeans) {
                LogUtils.i(TAG, GsonUtils.toJson(photoBeans));
                List<PhotoCell> photoCells = new ArrayList<>();
                for (int i = 0; i < photoBeans.size(); i++) {
                    final PhotoBean bean = photoBeans.get(i);
                    photoCells.add(new PhotoCell(R.layout.item_photo, bean));
                }
                mPhotoAdapter.setItems(photoCells);
                recyclerView.setAdapter(mPhotoAdapter);
            }
        });
    }

    @Override
    public void doBusiness() {
    }

    @Override
    public void onDebouncingClick(@NonNull View view) {
    }

    @Override
    public CharSequence bindTitle() {
        return "SamplesActivity";
    }
}
