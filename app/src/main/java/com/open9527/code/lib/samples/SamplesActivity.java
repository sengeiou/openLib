package com.open9527.code.lib.samples;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.open9527.code.common.activity.CommonScreenActivity;
import com.open9527.code.common.recycleview.BaseItem;
import com.open9527.code.common.recycleview.BaseItemAdapter;
import com.open9527.code.common.recycleview.ItemViewHolder;
import com.open9527.code.image.ImageLoad.ImageLoadConfig;
import com.open9527.code.image.ImageLoad.ImageLoadManger;
import com.open9527.code.lib.R;
import com.open9527.code.lib.model.EntryBean;
import com.open9527.code.lib.model.PhotoBean;
import com.open9527.code.lib.utils.CommonUtils;

import java.util.List;

/**
 * Created by     : open9527
 * Created times  : on 2019/8/23 13:33.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class SamplesActivity extends CommonScreenActivity {

    private SamolesViewModel mViewModel;
    private BaseItemAdapter photoAdapter;

    @Override
    public boolean isSwipeBack() {
        return false;
    }

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
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS));
        mViewModel.mPhotoInfoRepository.getData().observe(this, new Observer<List<PhotoBean>>() {
            @Override
            public void onChanged(List<PhotoBean> photoBeans) {
                LogUtils.i(TAG, GsonUtils.toJson(photoBeans));
                recyclerView.setAdapter(new PhotoAdapter(photoBeans));
            }
        });
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onDebouncingClick(@NonNull View view) {

    }

    private class PhotoCell extends BaseItem<PhotoCell> {

        public PhotoCell() {
            super(R.layout.item_photo);
        }

        @Override
        public void bind(@NonNull ItemViewHolder holder, int position) {

        }
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
