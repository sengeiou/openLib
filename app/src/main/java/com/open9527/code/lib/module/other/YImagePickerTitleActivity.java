package com.open9527.code.lib.module.other;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.open9527.code.common.databinding.CommonBindingTitleActivity;
import com.open9527.code.lib.R;
import com.open9527.code.lib.databinding.ActivityYimagepickerBinding;
import com.ypx.imagepicker.ImagePicker;
import com.ypx.imagepicker.bean.ImageItem;
import com.ypx.imagepicker.bean.MimeType;
import com.ypx.imagepicker.data.OnImagePickCompleteListener;

import java.util.ArrayList;
import java.util.List;

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
//                RedBook();
                selectPhoto();
                break;
            default:
                break;
        }
    }

    private void selectPhoto() {
        PictureSelector.create(YImagePickerTitleActivity.this)
                .openGallery(PictureMimeType.ofAll())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
//                .theme()//主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
//                .setPictureStyle(mPictureParameterStyle)// 动态自定义相册主题  注意：此方法最好不要与.theme();同时存在， 二选一
//                .setPictureCropStyle(mCropParameterStyle)// 动态自定义裁剪主题 注意：此方法最好不要与.theme();同时存在， 二选一
                .loadImageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项   参考Demo MainActivity中代码
                .maxSelectNum(9)// 最大图片选择数量 int
                .minSelectNum(1)// 最小选择数量 int
                .imageSpanCount(4)// 每行显示个数 int
                .isNotPreviewDownload(true)// 预览图片长按是否可以下载
                .queryMaxFileSize(10)// 只查多少M以内的图片、视频、音频  单位M
                .querySpecifiedFormatSuffix(PictureMimeType.ofPNG())// 查询指定后缀格式资源
                .cameraFileName("")// 使用相机时保存至本地的文件名称,注意这个只在拍照时可以使用，选图时不要用
                .isSingleDirectReturn(false)// 单选模式下是否直接返回，PictureConfig.SINGLE模式下有效
//                .setTitleBarBackgroundColor(titleBarBackgroundColor)//相册标题栏背景色
//                .isChangeStatusBarFontColor(isChangeStatusBarFontColor)// 是否关闭白色状态栏字体颜色
//                .setStatusBarColorPrimaryDark(statusBarColorPrimaryDark)// 状态栏背景色
//                .setUpArrowDrawable(upResId)// 设置标题栏右侧箭头图标
//                .setDownArrowDrawable(downResId)// 设置标题栏右侧箭头图标
//                .isOpenStyleCheckNumMode(isOpenStyleCheckNumMode)// 是否开启数字选择模式 类似QQ相册
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片 true or false
                .previewVideo(true)// 是否可预览视频 true or false
                .enablePreviewAudio(true) // 是否可播放音频 true or false
                .isCamera(true)// 是否显示拍照按钮 true or false
                .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .setOutputCameraPath("/CustomPath")// 自定义拍照保存路径,可不填
                .enableCrop(true)// 是否裁剪 true or false
                .compress(false)// 是否压缩 true or false
//                .glideOverride()// int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
//                .withAspectRatio()// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
//                .hideBottomControls()// 是否显示uCrop工具栏，默认不显示 true or false
//                .isGif()// 是否显示gif图片 true or false
//                .compressSavePath(getPath())//压缩图片保存地址
//                .freeStyleCropEnabled()// 裁剪框是否可拖拽 true or false
//                .circleDimmedLayer()// 是否圆形裁剪 true or false
//                .showCropFrame()// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
//                .showCropGrid()// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
//                .openClickSound()// 是否开启点击声音 true or false
//                .selectionMedia()// 是否传入已选图片 List<LocalMedia> list
//                .previewEggs()// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
                .cropCompressQuality(90)// 废弃 改用cutOutQuality()
                .cutOutQuality(90)// 裁剪输出质量 默认100
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
//                .cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效 int
//                .rotateEnabled() // 裁剪是否可旋转图片 true or false
//                .scaleEnabled()// 裁剪是否可放大缩小图片 true or false
//                .videoQuality()// 视频录制质量 0 or 1 int
                .videoMaxSecond(20)// 显示多少秒以内的视频or音频也可适用 int
                .videoMinSecond(1)// 显示多少秒以内的视频or音频也可适用 int
//                .recordVideoSecond()//视频秒数录制 默认60s int
                .isDragFrame(false)// 是否可拖动裁剪框(固定)
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的
                    // 4.media.getAndroidQToPath();为Android Q版本特有返回的字段，此字段有值就用来做上传使用
                    LogUtils.i(TAG, GsonUtils.toJson(selectList));
                    break;
            }
        }
    }


}
