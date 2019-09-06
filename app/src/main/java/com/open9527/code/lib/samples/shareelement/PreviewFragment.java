package com.open9527.code.lib.samples.shareelement;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.open9527.code.base.BaseFragment;
import com.open9527.code.common.recycleview.ItemViewHolder;
import com.open9527.code.image.imageload.ImageLoadConfig;
import com.open9527.code.image.imageload.ImageLoadManger;
import com.open9527.code.image.imageload.ImageLoadProcessInterface;
import com.open9527.code.lib.R;
import com.open9527.code.lib.model.ShareElementBean;
import com.open9527.code.lib.utils.CommonUtils;
import com.open9527.code.shareelement.ShareElementHelper;
import com.open9527.code.shareelement.transition.IShareElements;
import com.open9527.code.shareelement.transition.ShareElementInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/4 18:19.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class PreviewFragment extends BaseFragment implements IShareElements {
    public static final String KEY_DATA = "data";
    public static final String KEY_SELECT = "select";
    static final int REQUEST_CONTENT = 223;
    private RecyclerView mRecyclerView;
    private PhotoAdapter mPhotoAdapter;
    private List<ShareElementBean> photoList;
    private GridLayoutManager gridLayoutManager;

    @Override
    public void initData(@Nullable Bundle bundle) {
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_preview;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        mRecyclerView = findViewById(R.id.rv_list);
        gridLayoutManager = new GridLayoutManager(mActivity, 3);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        photoList = new ArrayList<>();
        photoList.add(new ShareElementBean("http://img1.imgtn.bdimg.com/it/u=4206294871,879077254&fm=26&gp=0.jpg", 0, 0));
        photoList.add(new ShareElementBean("http://img1.imgtn.bdimg.com/it/u=1901690610,3955011377&fm=200&gp=0.jpg", 0, 0));
        photoList.add(new ShareElementBean("http://img3.imgtn.bdimg.com/it/u=1546158593,2358526642&fm=200&gp=0.jpg", 0, 0));
        photoList.add(new ShareElementBean("http://img0.imgtn.bdimg.com/it/u=3792909229,2321547963&fm=200&gp=0.jpg", 0, 0));
        photoList.add(new ShareElementBean("http://img4.imgtn.bdimg.com/it/u=1621655683,865218969&fm=200&gp=0.jpg", 0, 0));
        photoList.add(new ShareElementBean("http://img5.imgtn.bdimg.com/it/u=551944592,1654216059&fm=26&gp=0.jpg", 0, 0));
        photoList.add(new ShareElementBean("http://img4.imgtn.bdimg.com/it/u=1621655683,865218969&fm=200&gp=0.jpg", 0, 0));
        photoList.add(new ShareElementBean("http://img1.imgtn.bdimg.com/it/u=2550323596,2167297465&fm=200&gp=0.jpg", 0, 0));
        photoList.add(new ShareElementBean("http://img4.imgtn.bdimg.com/it/u=952962361,1269259737&fm=26&gp=0.jpg", 0, 0));
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
        private List<ShareElementBean> mData;
        private ImageView icon;

        public PhotoAdapter(List<ShareElementBean> list) {
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
            final ShareElementBean bean = mData.get(position);
            icon = holder.findViewById(R.id.iv_photo);
            icon.setTag(R.id.iv_photo, position);
            ImageLoadManger.display(holder.itemView.getContext(),
                    icon,
                    CommonUtils.getUrl(bean.url),
                    new ImageLoadConfig(R.mipmap.ic_launcher, R.mipmap.ic_launcher_round), new ImageLoadProcessInterface() {
                        @Override
                        public void onResourceReady() {
                            startPostponedEnterTransition();
                        }
                    });

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mActivity, PreviewActivity.class);
                    intent.putParcelableArrayListExtra(KEY_DATA, (ArrayList<? extends Parcelable>) mData);
                    intent.putExtra(KEY_SELECT, position);
//                    Bundle options = ShareElementHelper.buildOptionsBundle(mActivity, this);
//                    startActivityForResult(intent, REQUEST_CONTENT, options);
                }
            });
        }


        @Override
        public int getItemCount() {
            return mData.size();
        }


    }

    @Override
    public ShareElementInfo[] getShareElements() {
        return new ShareElementInfo[0];
    }

    public void selectShareElement(ShareElementInfo shareElementInfo) {
        ShareElementBean data = (ShareElementBean) shareElementInfo.getData();
        for (int i = 0; i < photoList.size(); i++) {
            final ShareElementBean bean = photoList.get(i);
            if (bean.equals(data)) {
                GridLayoutManager layoutManager = (GridLayoutManager) mRecyclerView.getLayoutManager();
                layoutManager.scrollToPosition(i);
                return;
            }
        }

    }

}
