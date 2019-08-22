package com.open9527.code.network.livedata;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.open9527.code.network.status.NetStatus;

/**
 * Created by     : Mr.kk.
 * Created times  : on 2019/7/23 18:53.
 * E-Mail Address ：open_9527@163.com.
 * DESC :请求状态.
 */
public abstract class NetStatusLiveData extends MediatorLiveData<NetStatus> {
    public void postError(String error, int code) {
        postValue(NetStatus.error(error,code));
    }

    public void postLoading() {
        postValue(NetStatus.loading());
    }

    public void postSuccess(String msg, int code) {
        postValue(NetStatus.success(msg,code));
    }

    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super NetStatus> observer) {
        super.observe(owner, status -> {
            observer.onChanged(status);
            if (status == null || status.isError() || status.isSuccess()) {
                postValue(NetStatus.nul());
            }
        });

    }

    @Override
    protected void onInactive() {
        super.onInactive();
        if (!hasObservers()) {
            dispose();
        }
    }

    protected abstract void dispose();
}
