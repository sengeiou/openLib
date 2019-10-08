package com.open9527.code.lib.samples.shareelement;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.open9527.code.common.activity.CommonTitleActivity;
import com.open9527.code.common.recycleview.holder.ItemViewHolder;
import com.open9527.code.image.imageload.ImageLoadConfig;
import com.open9527.code.image.imageload.ImageLoadManger;
import com.open9527.code.lib.R;
import com.open9527.code.lib.utils.CommonUtils;
import com.open9527.code.shareelement.ShareElementHelper;
import com.open9527.code.shareelement.transition.IShareElementSelector;
import com.open9527.code.shareelement.transition.ShareElementInfo;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by     : open9527
 * Created times  : on 2019/9/4 17:32.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class NinePicActivity extends CommonTitleActivity {

    private RecyclerView mRecyclerView;

    private GridLayoutManager gridLayoutManager;
    private PhotoAdapter mPhotoAdapter;
    private ArrayList<String> photoList;

    @Override
    public CharSequence bindTitle() {
        return "九宫格";
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
        gridLayoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        photoList = new ArrayList<>();
        photoList.add("http://img1.imgtn.bdimg.com/it/u=4206294871,879077254&fm=26&gp=0.jpg");
        photoList.add("http://img1.imgtn.bdimg.com/it/u=1901690610,3955011377&fm=200&gp=0.jpg");
        photoList.add("http://img3.imgtn.bdimg.com/it/u=1546158593,2358526642&fm=200&gp=0.jpg");
        photoList.add("http://img0.imgtn.bdimg.com/it/u=3792909229,2321547963&fm=200&gp=0.jpg");
        photoList.add("http://img4.imgtn.bdimg.com/it/u=1621655683,865218969&fm=200&gp=0.jpg");
        photoList.add("http://img5.imgtn.bdimg.com/it/u=551944592,1654216059&fm=26&gp=0.jpg");
        photoList.add("http://img4.imgtn.bdimg.com/it/u=1621655683,865218969&fm=200&gp=0.jpg");
        photoList.add("http://img1.imgtn.bdimg.com/it/u=2550323596,2167297465&fm=200&gp=0.jpg");
        photoList.add("http://img4.imgtn.bdimg.com/it/u=952962361,1269259737&fm=26&gp=0.jpg");
        mPhotoAdapter = new PhotoAdapter(photoList);
        mPhotoAdapter.setHasStableIds(true);
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
            View item = LayoutInflater.from(mActivity).inflate(R.layout.item_nine_pic, parent, false);
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
                    new ImageLoadConfig(R.mipmap.ic_launcher, R.mipmap.ic_launcher_round));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

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

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
        ShareElementHelper.onActivityReenter(this, resultCode, data, new IShareElementSelector() {
            @Override
            public void selectShareElements(List<ShareElementInfo> list) {
                final ShareElementInfo shareElementInfo = list.get(0);
            }
        });
    }


}
