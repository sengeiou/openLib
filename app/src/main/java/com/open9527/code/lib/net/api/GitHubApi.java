package com.open9527.code.lib.net.api;

import com.open9527.code.lib.model.EntryBean;
import com.open9527.code.lib.model.PhotoBean;
import com.open9527.code.lib.net.response.GitHubResponse;
import com.open9527.code.lib.samples.module.im.room.user.UserBean;
import com.open9527.code.network.response.BaseResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Url;

/**
 * Created by     : open9527
 * Created times  : on 2019/8/25 10:47.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public interface GitHubApi {

    /**
     * entry
     *
     * @return
     */
    @GET("json/entry.json")
    Single<GitHubResponse<List<EntryBean>>> getEntry();

    /**
     * photo
     *
     * @return
     */
    @GET("json/photo.json")
    Single<GitHubResponse<List<PhotoBean>>> getPhoto();


    /**
     * 获取user
     *
     * @param url
     * @param header
     * @return
     */
    @GET()
    Single<GitHubResponse<List<UserBean>>> getUsers(@Url String url, @Header("HC-ACCESS-TOKEN") String header);
}