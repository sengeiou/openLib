package com.open9527.code.lib.module.other;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.open9527.code.common.databinding.CommonBindingTitleActivity;
import com.open9527.code.lib.R;
import com.open9527.code.lib.databinding.ActivityYimagepickerBinding;
import com.ypx.imagepicker.ImagePicker;
import com.ypx.imagepicker.bean.ImageItem;
import com.ypx.imagepicker.bean.MimeType;
import com.ypx.imagepicker.data.OnImagePickCompleteListener;

import java.util.ArrayList;

/**
 * Created by     : open9527
 * Created times  : on 2019/11/5 16:47.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class YImagePickerTitleActivity extends CommonBindingTitleActivity<ActivityYimagepickerBinding> {
    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_yimagepicker;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        applyDebouncingClickListener(mBinding.tvPhoto);
    }

    @Override
    public void onDebouncingClick(@NonNull View view) {
        switch (view.getId()) {
            case R.id.tv_photo:
                RedBook();
                break;
            default:
                break;
        }
    }

    private void RedBook() {
        ImagePicker.withCrop(new RedBookCropPresenter())//设置presenter
                .setMaxCount(9)//设置选择数量
                .showCamera(true)//设置显示拍照
                .setColumnCount(4)//设置列数
                .mimeType(MimeType.ofAll())//设置需要加载的文件类型
//                .filterMimeType(MimeType.GIF)//设置需要过滤掉的文件类型
                .setFirstImageItem(null)//设置上一次选中的图片
                .setFirstImageUrl(null)//设置上一次选中的图片地址
                .setVideoSinglePick(false)//设置视频单选
                .setCropPicSaveFilePath("剪裁图片保存路径")
                .setMaxVideoDuration(9999999L)//设置可选取的最大视频时长
                .setMinVideoDuration(1000L)//设置视频可选取的最小时长
                .pick(this, new OnImagePickCompleteListener() {
                    @Override
                    public void onImagePickComplete(ArrayList<ImageItem> items) {
                        //图片剪裁回调，主线程
                        //注意：剪裁回调里的ImageItem中getCropUrl()才是剪裁过后的图片地址

                        LogUtils.i(TAG, GsonUtils.toJson(items));
                    }
                });
    }
}
