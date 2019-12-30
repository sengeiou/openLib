package com.open9527.code.common;

import androidx.lifecycle.ViewModel;

import com.open9527.code.network.livedata.UnPeekLiveData;

/**
 * Created by     : open9527
 * Created times  : on 2019/12/25/025 14:10.
 * E-Mail Address ：open_9527@163.com.
 * DESC :配置一个app全局的ViewModel.
 */
public class AppViewModel extends ViewModel {

    //通过 UnPeekLiveData 配合 AppViewModel 来发送 生命周期安全的、事件源可追溯的 通知。

    public final UnPeekLiveData<String> openOrCloseDrawer = new UnPeekLiveData<>();

}
