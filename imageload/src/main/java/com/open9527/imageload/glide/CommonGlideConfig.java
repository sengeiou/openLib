package com.open9527.imageload.glide;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.ExternalPreferredCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;
import com.open9527.imageload.Utils;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by     : Mr.kk.
 * Created times  : on 2019/1/21 14:52.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
@GlideModule
public class CommonGlideConfig extends AppGlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        super.applyOptions(context, builder);
        //获取系统分配给应用的总内存大小
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        //设置图片内存缓存占用比
        int memoryCacheSize = maxMemory / 16;
        //设置内存缓存大小
        builder.setMemoryCache(new LruResourceCache(memoryCacheSize));
        builder.setBitmapPool(new LruBitmapPool(memoryCacheSize));
        builder.setDefaultRequestOptions(RequestOptions.formatOf(DecodeFormat.PREFER_RGB_565));
        //根据SD卡是否可用选择是在内部缓存还是SD卡缓存
        //配置磁盘缓存大小
        long diskCacheSizeBytes = (long) 1024 * 1024 * 1024; // 1G
        if (Utils.isSDCardEnableByEnvironment()) {
            builder.setDiskCache(new ExternalPreferredCacheDiskCacheFactory(context, "GlideConfigCache", diskCacheSizeBytes));
        } else {
            builder.setDiskCache(new InternalCacheDiskCacheFactory(context, "GlideConfigCache", diskCacheSizeBytes));
        }
    }

    //针对V4用户可以提升速度
    @Override
    public boolean isManifestParsingEnabled() {
//        return super.isManifestParsingEnabled();
        return false;
    }


    /**
     * 修改 glide 加载图片超时时间
     */
    OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build();

    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {
        OkHttpUrlLoader.Factory factory = new OkHttpUrlLoader.Factory(client);
        registry.replace(GlideUrl.class, InputStream.class, factory);
    }


}
