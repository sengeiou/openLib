package com.open9527.code.lib.samples;

import androidx.lifecycle.ViewModel;

import com.open9527.code.lib.model.EntryBean;
import com.open9527.code.lib.model.PhotoBean;
import com.open9527.code.lib.net.api.GitHubApi;
import com.open9527.code.lib.net.client.GitHubApiClient;
import com.open9527.code.lib.net.response.GitHubResponse;
import com.open9527.code.lib.net.response.GitHubSingleDataLoadRepository;
import com.open9527.code.lib.net.response.GitHubSingleDataLoader;
import com.open9527.code.network.repository.DataLoadRepository;

import java.util.List;

import io.reactivex.Single;


/**
 * Created by     : open9527
 * Created times  : on 2019/8/25 10:55.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class SamolesViewModel extends ViewModel {

    public DataLoadRepository<List<PhotoBean>> mPhotoInfoRepository = new GitHubSingleDataLoadRepository<>(new GitHubSingleDataLoader<List<PhotoBean>>() {
        @Override
        public Single<GitHubResponse<List<PhotoBean>>> getLoader() {
            return GitHubApiClient.getApiService(GitHubApi.class).getPhoto();
        }
    });
    public void getPhotoInfo() {
        mPhotoInfoRepository.loadData(true);
    }
}
