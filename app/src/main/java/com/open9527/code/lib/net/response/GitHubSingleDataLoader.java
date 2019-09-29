package com.open9527.code.lib.net.response;


import io.reactivex.Single;

/**
 * Created by     : Mr.kk.
 * Created times  : on 2019/7/23 19:04.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public interface GitHubSingleDataLoader<T> {
    Single<GitHubResponse<T>> getLoader();

}
