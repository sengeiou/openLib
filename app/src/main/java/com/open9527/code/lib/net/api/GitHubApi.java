package com.open9527.code.lib.net.api;

import com.open9527.code.lib.model.EntryBean;
import com.open9527.code.lib.model.PhotoBean;
import com.open9527.code.lib.model.RequestGitHubBean;
import com.open9527.code.lib.net.response.GitHubResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
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

    //传输方法：PUT
    //访问路径：https://api.github.com/repos/用户名/仓库名/contents/文件路径
    //JSON格式：

    @POST()
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Single<GitHubResponse<Object>> uploadFile(@Url String url, @Body RequestGitHubBean requestGitHubBean);


    /**
     * 获取user
     *
     * @param url
     * @param header
     * @return
     */
//    @GET()
//    Single<GitHubResponse<List<UserBean>>> getUsers(@Url String url, @Header("HC-ACCESS-TOKEN") String header);
}
