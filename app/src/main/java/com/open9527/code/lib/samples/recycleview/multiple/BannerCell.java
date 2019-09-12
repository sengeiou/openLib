package com.open9527.code.lib.samples.recycleview.multiple;

import android.util.SparseArray;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.open9527.code.common.recycleview.BaseCell;
import com.open9527.code.common.recycleview.holder.ItemViewHolder;
import com.open9527.code.customview.banner.GalleryRecyclerView;
import com.open9527.code.lib.R;

import java.util.List;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/11 17:16.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class BannerCell extends BaseCell<BannerCell> {
    private SparseArray<BannerAdapter> adapterArray = new SparseArray<>();
    private List<String> list;
    private GalleryRecyclerView rvBanner;

    public BannerCell(List<String> list) {
        super(R.layout.item_banner);
        this.list = list;
    }

    @Override
    public void bind(@NonNull ItemViewHolder holder, int position) {
        rvBanner = holder.findViewById(R.id.rv_banner);
        BannerAdapter bannerAdapter = findAdapterById(holder, R.id.rv_banner);
        bannerAdapter.setData(list);
//        rvBanner.setAdapter(bannerAdapter);
//        rvBanner.setFlingSpeed(8000);
//        GalleryLayoutManager manager = new GalleryLayoutManager(holder.itemView.getContext(), LinearLayoutManager.HORIZONTAL);
//        //attach，绑定recyclerView，并且设置默认选中索引的位置
//        manager.attach(100);
//        //设置缩放比例因子，在0到1.0之间即可
//        manager.setItemTransformer(new GalleryScaleTransformer(0.5f, 20));
//        rvBanner.setLayoutManager(manager);
//        GalleryLinearSnapHelper mSnapHelper = new GalleryLinearSnapHelper(rvBanner);
//        mSnapHelper.attachToRecyclerView(rvBanner);
//        rvBanner.onStart();
        rvBanner.setDelayTime(3000)
                .setFlingSpeed(10000)
                .setDataAdapter(bannerAdapter)
                .setSelectedPosition(100)
                .setCallbackInFling(false)
                .setOnItemSelectedListener(new GalleryRecyclerView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(RecyclerView recyclerView, View item, int position) {
                        LogUtils.i("onItemSelected-----", position + "");
                    }
                })
                .setSize(bannerAdapter.getData().size())
                .create();
        bannerAdapter.setOnCellClickListener(new BannerAdapter.OnCellClickListener() {
            @Override
            public void onCellClick(String url, int position) {
                ToastUtils.showShort("BannerCell-->"+position);

            }
        });


    }


    /**
     * 根据 RecyclerView ID 缓存Adapter
     *
     * @param holder
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends RecyclerView.Adapter> T findAdapterById(ItemViewHolder holder, @IdRes final int viewId) {
        BannerAdapter adapter = adapterArray.get(viewId);
        if (adapter == null) {
            adapter = new BannerAdapter(holder.itemView.getContext());
            adapterArray.put(viewId, adapter);
        }
        return (T) adapter;
    }
}
