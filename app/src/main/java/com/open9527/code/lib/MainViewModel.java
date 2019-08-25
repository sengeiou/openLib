package com.open9527.code.lib;

import androidx.lifecycle.ViewModel;

import com.open9527.code.lib.model.EntryBean;
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
 * Created times  : on 2019/8/25 13:40.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class MainViewModel extends ViewModel {
    public DataLoadRepository<List<EntryBean>> mEntryInfoRepository = new GitHubSingleDataLoadRepository<>(new GitHubSingleDataLoader<List<EntryBean>>() {
        @Override
        public Single<GitHubResponse<List<EntryBean>>> getLoader() {
            return GitHubApiClient.getApiService(GitHubApi.class).getEntry();
        }
    });
    public void getEntryInfo() {
        mEntryInfoRepository.loadData(true);
    }

}
