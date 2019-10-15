package com.open9527.code.network.dataloader;


import com.open9527.code.network.response.BaseResponse;

import io.reactivex.Single;

/**
 * Created by     : Mr.kk.
 * Created times  : on 2019/7/23 19:04.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public interface SingleDataLoader<T> {

    Single<BaseResponse<T>> getLoader();
}

