package com.open9527.code.lib.net.response;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.open9527.code.network.exception.NetError;
import com.open9527.code.network.livedata.NetStatusLiveData;
import com.open9527.code.network.repository.DataLoadRepository;

import com.open9527.code.network.status.NetStatus;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * Created by     : Mr.kk.
 * Created times  : on 2018/8/2 16:48.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class GitHubSingleDataLoadRepository<T> implements DataLoadRepository<T> {
    private GitHubSingleDataLoader<T> singleDataLoader;

    public GitHubSingleDataLoadRepository(GitHubSingleDataLoader<T> singleDataLoader) {
        this.singleDataLoader = singleDataLoader;
    }

    private MutableLiveData<T> tLiveData = new MutableLiveData<T>();

    private NetStatusLiveData netStatusLiveData = new NetStatusLiveData() {
        @Override
        protected void dispose() {
            if (disposable != null && !disposable.isDisposed()) {
                disposable.dispose();
            }
        }
    };
    private MutableLiveData<Boolean> nLiveData = new MutableLiveData<>();
    private Disposable disposable;

    @Override
    public LiveData<NetStatus> getStatus() {
        return netStatusLiveData;
    }

    @Override
    public LiveData<T> getData() {
        return tLiveData;
    }

    @Override
    public LiveData<Boolean> noData() {
        return nLiveData;
    }

    @Override
    public void loadData(boolean reLoad) {
        if (!reLoad && netStatusLiveData.getValue() != null && netStatusLiveData.getValue().isLoading()) {
            return;
        }
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        netStatusLiveData.postLoading();
        singleDataLoader.getLoader().subscribe(new SingleObserver<GitHubResponse<T>>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onSuccess(GitHubResponse<T> tResponse) {
                if (tResponse.timeOut()) {//超时

                } else if (tResponse.success()) {
                    netStatusLiveData.postSuccess(tResponse.message(),tResponse.code());
                    tLiveData.postValue(tResponse.data());
                } else {
                    netStatusLiveData.postError(tResponse.message(),tResponse.code());
                }
            }

            @Override
            public void onError(Throwable e) {
                netStatusLiveData.postError(NetError.getErrorMessage(e),0);
            }
        });

    }
}
