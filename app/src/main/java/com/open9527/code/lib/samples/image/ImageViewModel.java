package com.open9527.code.lib.samples.image;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.open9527.code.lib.BuildConfig;
import com.open9527.code.lib.model.GitHubFileBean;
import com.open9527.code.lib.model.RequestGitHubBean;
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
 * Created times  : on 2019/9/29 9:51.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class ImageViewModel extends ViewModel {

    private ObservableField<RequestGitHubBean> requestGitHubBeanObservableField = new ObservableField<>();
    private ObservableField<String> fileNameObservableField = new ObservableField<>();

    DataLoadRepository<Object> mUploadFileRepository = new GitHubSingleDataLoadRepository<>(new GitHubSingleDataLoader<Object>() {
        @Override
        public Single<GitHubResponse<Object>> getLoader() {
            return GitHubApiClient.getApiService(GitHubApi.class).uploadFile(BuildConfig.GITHUB_UPLOAD + "upload/" + fileNameObservableField.get(), requestGitHubBeanObservableField.get());
        }
    });

    void uploadFile(RequestGitHubBean requestGitHubBean, String fileName) {
        requestGitHubBeanObservableField.set(requestGitHubBean);
        fileNameObservableField.set(fileName);
        mUploadFileRepository.loadData(true);
    }


    DataLoadRepository<List<GitHubFileBean>> mFileListRepository = new GitHubSingleDataLoadRepository<>(new GitHubSingleDataLoader<List<GitHubFileBean>>() {
        @Override
        public Single<GitHubResponse<List<GitHubFileBean>>> getLoader() {
            return GitHubApiClient.getApiService(GitHubApi.class).getFileList(BuildConfig.GITHUB_UPLOAD + "upload/");
        }
    });

    void getFileList() {
        mFileListRepository.loadData(true);
    }
}
