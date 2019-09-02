package com.open9527.code.lib;

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

import com.billy.android.swipe.SmartSwipeRefresh;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.AdaptScreenUtils;
import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.open9527.code.common.activity.CommonScreenActivity;
import com.open9527.code.common.recycleview.DividerItemDecoration;
import com.open9527.code.common.recycleview.ItemViewHolder;
import com.open9527.code.image.imageload.ImageLoadConfig;
import com.open9527.code.image.imageload.ImageLoadManger;
import com.open9527.code.lib.model.EntryBean;
import com.open9527.code.lib.samples.ExpandTextViewActivity;
import com.open9527.code.lib.samples.SamplesActivity;
import com.open9527.code.lib.samples.module.im.room.user.UserBean;
import com.open9527.code.lib.samples.module.im.room.user.UserBeanDao;
import com.open9527.code.lib.samples.module.im.room.user.UserBeanDatabase;
import com.open9527.code.lib.utils.CommonUtils;
import com.open9527.code.network.status.NetStatus;

import java.util.List;

/**
 * Created by     : open9527
 * Created times  : on 2019/8/22 17:47.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class MainActivity extends CommonScreenActivity {

    private MainViewModel mViewModel;

    @Override
    public void initData(@Nullable Bundle bundle) {
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mViewModel.getEntryInfo();
//        mViewModel.getUserList();
    }

    @Override
    public int bindLayout() {
        return R.layout.actiivty_main;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {

        RecyclerView recyclerView = findViewById(R.id.rv_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.BOTH_SET,
                2, ColorUtils.getColor(R.color.color_eee), AdaptScreenUtils.pt2Px(30), AdaptScreenUtils.pt2Px(30)));

        mViewModel.mEntryInfoRepository.getStatus().observe(this, new Observer<NetStatus>() {
            @Override
            public void onChanged(NetStatus netStatus) {
                LogUtils.i(TAG, netStatus.getMessage());
            }
        });
        mViewModel.mEntryInfoRepository.getData().observe(this, new Observer<List<EntryBean>>() {
            @Override
            public void onChanged(List<EntryBean> entryBeans) {
                recyclerView.setAdapter(new MainAdapter(entryBeans));
            }
        });

        mViewModel.mUserListRepository.getData().observe(this, new Observer<List<UserBean>>() {
            @Override
            public void onChanged(List<UserBean> userBeanList) {
                UserBeanDao userBeanDao = UserBeanDatabase.getDatabse().userBeanDao();
                ThreadUtils.executeByIo(new ThreadUtils.SimpleTask<List<Long>>() {
                    @Nullable
                    @Override
                    public List<Long> doInBackground() throws Throwable {
                        return userBeanDao.insertAll(userBeanList);
                    }

                    @Override
                    public void onSuccess(@Nullable List<Long> result) {
                        LogUtils.i(TAG, result);
                    }
                });
            }
        });

        SmartSwipeRefresh.SmartSwipeRefreshDataLoader loader = new SmartSwipeRefresh.SmartSwipeRefreshDataLoader() {
            @Override
            public void onRefresh(final SmartSwipeRefresh swipeRefresh) {
                mViewModel.getEntryInfo();
                swipeRefresh.finished(true);
            }

            @Override
            public void onLoadMore(final SmartSwipeRefresh swipeRefresh) {
                //加载下一页数据
                mViewModel.getEntryInfo();
                swipeRefresh.finished(true);
            }
        };
        SmartSwipeRefresh.translateMode(mContentView, false)
                .setDataLoader(loader);
    }

    @Override
    public void doBusiness() {
//        Bitmap bitmap = ImageUtils.getBitmap(R.drawable.cat);
//        LogUtils.i(TAG,"bitmap-->"+bitmap.toString());

    }

    @Override
    public void onDebouncingClick(@NonNull View view) {

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
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (position == 0) {
                        ActivityUtils.startActivity(SamplesActivity.class);
                    } else if (position == 1) {
                        ActivityUtils.startActivity(ExpandTextViewActivity.class);
                    } else {
                        ToastUtils.showShort(bean.getDesc());
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

}