package com.open9527.code.lib.samples;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.open9527.code.common.activity.CommonScreenActivity;
import com.open9527.code.common.activity.CommonTitleActivity;
import com.open9527.code.common.recycleview.BaseItemAdapter;
import com.open9527.code.common.recycleview.ItemViewHolder;
import com.open9527.code.image.imageload.ImageLoadConfig;
import com.open9527.code.image.imageload.ImageLoadManger;
import com.open9527.code.lib.BuildConfig;
import com.open9527.code.lib.R;
import com.open9527.code.lib.model.PhotoBean;
import com.open9527.code.lib.samples.adapter.cell.PhotoCell;
import com.open9527.code.lib.utils.CommonUtils;

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
    private BaseItemAdapter photoAdapter;

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

        BaseItemAdapter mPhotoAdapter = new BaseItemAdapter<PhotoCell>();
        mPhotoAdapter.setHasStableIds(true);

        mViewModel.mPhotoInfoRepository.getData().observe(this, new Observer<List<PhotoBean>>() {
            @Override
            public void onChanged(List<PhotoBean> photoBeans) {
                LogUtils.i(TAG, GsonUtils.toJson(photoBeans));
                List<PhotoCell> photoCells = new ArrayList<>();
                for (int i = 0; i < photoBeans.size(); i++) {
                    final PhotoBean bean = photoBeans.get(i);
//                    if (i % 2 == 0) {
//                        photoCells.add(new PhotoCell(R.layout.item_desc, bean.getDesc()));
//                    } else {
                    photoCells.add(new PhotoCell(R.layout.item_photo, bean));
//                    }
                }
                mPhotoAdapter.setItems(photoCells);
//                mPhotoAdapter.setItems(descCells);
                recyclerView.setAdapter(mPhotoAdapter);
//                recyclerView.setAdapter(new PhotoAdapter(photoBeans));
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

    private class PhotoAdapter extends RecyclerView.Adapter<ItemViewHolder> {
        private List<PhotoBean> list;

        public PhotoAdapter(List<PhotoBean> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View item = LayoutInflater.from(mActivity).inflate(R.layout.item_photo, parent, false);
            return new ItemViewHolder(item);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
            final PhotoBean bean = list.get(position);
            ImageView icon = holder.findViewById(R.id.iv_photo);
            ImageLoadManger.display(holder.itemView.getContext(),
                    icon,
                    CommonUtils.getUrl(bean.getUrl()),
                    new ImageLoadConfig(R.mipmap.ic_launcher, R.mipmap.ic_launcher_round));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }


}
