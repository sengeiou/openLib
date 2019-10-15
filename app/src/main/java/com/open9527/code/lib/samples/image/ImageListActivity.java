package com.open9527.code.lib.samples.image;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.open9527.code.common.databinding.BindingBaseCell;
import com.open9527.code.common.databinding.BindingBaseCellAdapter;
import com.open9527.code.common.databinding.CommonBindingActivity;
import com.open9527.code.image.dragimageview.DraggableImageViewerHelper;
import com.open9527.code.image.dragimageview.core.ImageInfo;
import com.open9527.code.lib.R;
import com.open9527.code.lib.databinding.ActivityImagelistBinding;
import com.open9527.code.lib.samples.adapter.cell.BindingImageCell;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/29 16:47.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class ImageListActivity extends CommonBindingActivity<ActivityImagelistBinding> {
    List<ImageInfo> listUrl = new LinkedList<>();
    List<View> listView = new LinkedList<>();

    @Override
    public CharSequence bindTitle() {
        return "ImageListActivity";
    }

    @Override
    public void initData(@Nullable Bundle bundle) {
        listUrl.add(new ImageInfo("https://upload-bbs.mihoyo.com/upload/2019/08/21/73766616/4d09b6b94491d3921344be906aa7971a_4136353673894269217.png"));
        listUrl.add(new ImageInfo("https://upload-bbs.mihoyo.com/upload/2019/08/12/50600998/1543e13e5cba414a1e4d396d8e6bdbb0_4959259236143228277.jpg"));
        listUrl.add(new ImageInfo("https://upload-bbs.mihoyo.com/upload/2019/02/03/74582189/ede10255b2a99cfcf33868d1afd81a92_6059341049122226062.png"));
        listUrl.add(new ImageInfo("https://upload-bbs.mihoyo.com/upload/2019/08/06/75158229/53c6eb0e1c4bb8db97cbd9c8db631423_3306524819178217982.jpg"));
        listUrl.add(new ImageInfo("https://upload-bbs.mihoyo.com/upload/2019/08/08/10982654/fe2e9243c4e6ea7e489f81ae3814ed08_3279663480817048245.jpg"));
        listUrl.add(new ImageInfo("https://upload-bbs.mihayo.com/upload/2019/03/01/73565430/82a40083d95800c553d036b8c0689323_4849126433310918291.png"));
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_imagelist;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        mBinding.rvList.setLayoutManager(new LinearLayoutManager(this));
        BindingBaseCellAdapter mAdapter = new BindingBaseCellAdapter();
        mBinding.rvList.setAdapter(mAdapter);
        List<BindingBaseCell> list = new LinkedList<>();
        for (int i = 0; i < listUrl.size(); i++) {
            final BindingImageCell bindingImageCell = new BindingImageCell(listUrl.get(i).getThumbnailUrl());
            listView.add(bindingImageCell.viewObservableField.get());
            bindingImageCell.setOnCellClickListener(onCellClickListener);
            list.add(bindingImageCell);
        }
        mAdapter.addItems(list);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onDebouncingClick(@NonNull View view) {

    }

    private BindingImageCell.OnCellClickListener onCellClickListener = new BindingImageCell.OnCellClickListener() {
        @Override
        public void onCellClick(View view, int position) {
            showImages(view, position);
        }
    };


    private void showImages(View view, int index) {
//        DraggableImageViewerHelper.showImages(this, mBinding.rvList, R.id.iv_photo, listUrl, index);
    }
}
