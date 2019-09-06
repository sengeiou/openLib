package com.open9527.code.lib.samples.shareelement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.open9527.code.common.activity.CommonTitleActivity;
import com.open9527.code.common.recycleview.ItemViewHolder;
import com.open9527.code.image.imageload.ImageLoadConfig;
import com.open9527.code.image.imageload.ImageLoadManger;
import com.open9527.code.image.imageload.ImageLoadProcessInterface;
import com.open9527.code.lib.R;
import com.open9527.code.lib.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/4 17:32.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class PreviewActivity extends CommonTitleActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<String> photoList = new ArrayList<>();
    private PhotoAdapter mPhotoAdapter;

    @Override
    public CharSequence bindTitle() {
        return "图片预览";
    }

    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_preview;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        mRecyclerView = findViewById(R.id.rv_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        PagerSnapHelper snapHelper = new PagerSnapHelper();
//        PagerSnapHelper snapHelper = new PagerSnapHelper() {
//            @Override
//            public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
//                currentPosition = super.findTargetSnapPosition(layoutManager, velocityX, velocityY);
//                return currentPosition;
//            }
//        };
        snapHelper.attachToRecyclerView(mRecyclerView);
        mPhotoAdapter = new PhotoAdapter(photoList);
        mRecyclerView.setAdapter(mPhotoAdapter);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onDebouncingClick(@NonNull View view) {

    }

    private class PhotoAdapter extends RecyclerView.Adapter<ItemViewHolder> {
        private List<String> mData;
        private ImageView icon;

        public PhotoAdapter(List<String> list) {
            this.mData = list;
        }

        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View item = LayoutInflater.from(mActivity).inflate(R.layout.item_preview, parent, false);
            return new ItemViewHolder(item);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
            final String bean = mData.get(position);
            icon = holder.findViewById(R.id.iv_photo);
            icon.setTag(R.id.iv_photo, position);
            ImageLoadManger.display(holder.itemView.getContext(),
                    icon,
                    CommonUtils.getUrl(bean),
                    new ImageLoadConfig(R.mipmap.ic_launcher, R.mipmap.ic_launcher_round), new ImageLoadProcessInterface() {
                        @Override
                        public void onResourceReady() {
                            startPostponedEnterTransition();
                        }
                    });
        }

        @Nullable
        public View getViewByPosition(RecyclerView recyclerView, int position, @IdRes int viewId) {
            if (recyclerView == null) {
                return null;
            }
            ItemViewHolder viewHolder = (ItemViewHolder) recyclerView.findViewHolderForLayoutPosition(position);
            if (viewHolder == null) {
                return null;
            }
            return viewHolder.findViewById(viewId);
        }


        @Override
        public int getItemCount() {
            return mData.size();
        }
    }


}
