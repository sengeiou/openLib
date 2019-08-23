package com.open9527.code.lib;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.AdaptScreenUtils;
import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.ResourceUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.google.gson.reflect.TypeToken;
import com.open9527.code.common.activity.CommonScreenActivity;
import com.open9527.code.common.recycleview.DividerItemDecoration;
import com.open9527.code.common.recycleview.ItemViewHolder;
import com.open9527.code.image.ImageLoad.ImageLoadConfig;
import com.open9527.code.image.ImageLoad.ImageLoadManger;
import com.open9527.code.image.ImageLoad.ImageLoadProcessInterface;
import com.open9527.code.lib.model.EntryBean;
import com.open9527.code.lib.utils.CommonUtils;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by     : open9527
 * Created times  : on 2019/8/22 17:47.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class MainActivity extends CommonScreenActivity {
    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.actiivty_main;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        String string = ResourceUtils.readAssets2String("json/entry.json");
        List<EntryBean> list = GsonUtils.fromJson(string, new TypeToken<List<EntryBean>>() {
        }.getType());
        RecyclerView recyclerView = findViewById(R.id.rv_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.BOTH_SET,
                2, ColorUtils.getColor(R.color.color_eee), AdaptScreenUtils.pt2Px(30), AdaptScreenUtils.pt2Px(30)));
        recyclerView.setAdapter(new MainAdapter(list));
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onDebouncingClick(@NonNull View view) {

    }

    @Override
    public boolean isSwipeBack() {
        return false;
    }

    private class MainAdapter extends RecyclerView.Adapter<ItemViewHolder> {
        private List<EntryBean> list;

        public MainAdapter(List<EntryBean> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View item = LayoutInflater.from(mActivity).inflate(R.layout.item_main_entry, parent, false);
//            View item = LayoutInflater.from(mActivity).inflate(R.layout.item_main_image, parent, false);
            return new ItemViewHolder(item);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
            final EntryBean bean = list.get(position);
            ImageView icon = holder.findViewById(R.id.iv_icon);
            ImageView into = holder.findViewById(R.id.iv_into);
            TextView title = holder.findViewById(R.id.tv_title);
            TextView desc = holder.findViewById(R.id.tv_desc);
//            icon.setBackgroundColor(ColorUtils.getColor(R.color.colorAccent));
            ImageLoadManger.display(holder.itemView.getContext(),
                    icon,
                    CommonUtils.getUrl(bean.getAvatars()),
                    new ImageLoadConfig(R.mipmap.ic_launcher, R.mipmap.ic_launcher_round));
            ImageLoadManger.display(holder.itemView.getContext(),
                    into,
                    CommonUtils.getUrl(bean.getIcon()),
                    new ImageLoadConfig(R.mipmap.ic_launcher, R.mipmap.ic_launcher_round));
            title.setText(bean.getTitle());
            desc.setText(bean.getDesc());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

}
