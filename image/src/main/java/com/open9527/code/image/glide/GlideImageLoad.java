package com.open9527.code.image.glide;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.transition.Transition;
import com.open9527.code.image.GlideApp;
import com.open9527.code.image.imageload.ImageLoadConfig;
import com.open9527.code.image.imageload.ImageLoadInterface;
import com.open9527.code.image.imageload.ImageLoadProcessInterface;
import com.open9527.code.image.utils.CommonImageUtils;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * Created by     : Mr.kk.
 * Created times  : on 2019/7/30 13:47.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class GlideImageLoad implements ImageLoadInterface {
    private static final String TAG = "GlideImageLoad";

    /**
     * glide加载 url图片
     *
     * @param mContext
     * @param imageView
     * @param url
     * @param config                    配置参数
     * @param imageLoadProcessInterface 加载过程监听
     */
    @SuppressLint("CheckResult")
    public void display(Context mContext, final ImageView imageView, final Object url, final ImageLoadConfig config, final ImageLoadProcessInterface imageLoadProcessInterface) {
        if (mContext == null) {
            CommonImageUtils.showLogInfo(TAG, " mContext is null");
            return;
        }
        if (imageView == null) {
            CommonImageUtils.showLogInfo(TAG, " imageView is null");
            return;
        }
        Context context = imageView.getContext();
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {//activity是否结束
                return;
            }
        }
        try {
            if ((config == null || config.defaultRes <= 0) && url == null) {
                CommonImageUtils.showLogInfo(TAG, " url is null and config is null");
                return;
            }
            RequestOptions requestOptions = new RequestOptions();
            if (config != null) {
                if (config.defaultRes > 0) {
                    requestOptions.placeholder(config.defaultRes);
                }
                if (config.failRes > 0) {
                    requestOptions.error(config.failRes);
                }
                //
                // requestOptions.fallback(new ColorDrawable(Color.GRAY));

//                if (config.scaleType != null) {
//                    if (ImageView.ScaleType.FIT_CENTER == config.scaleType) {
//                        requestOptions.fitCenter();
//                    } else {
//                        requestOptions.centerCrop();
//                    }
//                } else {
//                    requestOptions.centerCrop();
//                }
                if (config.radius > 0) {
                    requestOptions.apply(bitmapTransform(new MultiTransformation<>(new CenterCrop(), new RoundedCornersTransformation(config.radius, 0, config.cornerType))));
                }
                //配置缓存模式
                requestOptions.diskCacheStrategy(config.diskCacheStrategy);
                //是否跳过缓存
                requestOptions.skipMemoryCache(config.skipMemoryCache);
                //仅从缓存加载图片
                requestOptions.onlyRetrieveFromCache(config.onlyRetrieveFromCache);

//                requestOptions.override(config.width, config.height);
            } else {
                //fallback
                requestOptions.fallback(new ColorDrawable(Color.GRAY));
            }
            ImageViewTarget simpleTarget = new BitmapImageViewTarget(imageView) {
                @Override
                public void onLoadStarted(Drawable placeholder) {
                    super.onLoadStarted(placeholder);
//                    Log.i(TAG, " onLoadStarted");
                    if (imageLoadProcessInterface != null) {
                        imageLoadProcessInterface.onLoadStarted();
                    }
                }

                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    super.onResourceReady(resource, transition);
//                    Log.i(TAG, " onResourceReady");
                    if (imageLoadProcessInterface != null) {
                        imageLoadProcessInterface.onResourceReady();
                    }
                }

                @Override
                public void onLoadFailed(@Nullable Drawable errorDrawable) {
                    super.onLoadFailed(errorDrawable);
                    CommonImageUtils.showLogInfo(TAG, " onLoadFailed");
                    if (imageLoadProcessInterface != null) {
                        imageLoadProcessInterface.onLoadFailed();
                    }
                }

                @Override
                public void onLoadCleared(Drawable placeholder) {
                    super.onLoadCleared(placeholder);
//                    Log.i(TAG, " onLoadCleared");
                    if (imageLoadProcessInterface != null) {
                        imageLoadProcessInterface.onLoadCleared();
                    }
                }

                @Override
                public void getSize(@NonNull SizeReadyCallback sizeReadyCallback) {
                    if (config != null && config.width >= 0 && config.height >= 0)
                        sizeReadyCallback.onSizeReady(config.width, config.height);
                    else {
                        super.getSize(sizeReadyCallback);
                    }
                }
            };
            load(context, url, simpleTarget, requestOptions);
        } catch (Exception e) {
            e.printStackTrace();
            CommonImageUtils.showLogInfo(TAG, "Exception-->" + e.getMessage());
        }

    }

    /**
     * Glide 加载图片
     *
     * @param context
     * @param object
     * @param simpleTarget
     * @param requestOptions
     */
    private void load(Context context, Object object, ImageViewTarget simpleTarget, RequestOptions requestOptions) {
        GlideApp.with(context)
                .asBitmap()
                .load(object)
                .apply(requestOptions)
//                .thumbnail(0.5f)
                .into(simpleTarget);
    }

    /**
     * 恢复加载图片
     *
     * @param context
     * @param url
     */

    public void resumeLoad(Context context, String url) {
        if (context != null)
            GlideApp.with(context).resumeRequests();
    }

    /**
     * 清除一个资源的加载
     *
     * @param context
     * @param imageView
     * @param url
     */

    public void clearImageView(Context context, ImageView imageView, String url) {
        if (context != null && imageView != null)
            GlideApp.with(context).clear(imageView);
    }

    /**
     * 暂停加载
     *
     * @param context
     * @param url
     */

    public void pauseLoad(Context context, String url) {
        if (context != null)
            GlideApp.with(context).pauseRequests();
    }
}
