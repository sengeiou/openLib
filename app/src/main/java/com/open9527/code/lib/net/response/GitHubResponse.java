package com.open9527.code.lib.net.response;

import com.open9527.code.network.response.BaseResponse;

/**
 * Created by     : Mr.kk.
 * Created times  : on 2019/7/23 19:30.
 * E-Mail Address ：open_9527@163.com.
 * DESC :GitHubResponse.
 */
public class GitHubResponse<T> extends BaseResponse<T> {
    private static int GITHUB_SUCCESS_CODE = 200;//成功的code

    @Override
    public boolean success() {
        return this.code == GITHUB_SUCCESS_CODE;
    }
    @Override
    public T data() {
        return this.data;
    }


    @Override
    public boolean timeOut() {
        return this.code == TOKEN_CODE;
    }
}
