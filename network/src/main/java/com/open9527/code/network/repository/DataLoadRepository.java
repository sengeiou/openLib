package com.open9527.code.network.repository;

import androidx.lifecycle.LiveData;

import com.open9527.code.network.status.NetStatus;

/**
 * Created by     : Mr.kk.
 * Created times  : on 2019/7/23 18:41.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public interface DataLoadRepository<T> {

    /**
     * 获取数据
     *
     * @return
     */
    LiveData<T> getData();

    LiveData<Boolean> noData();

    /**
     * 获取状态
     *
     * @return
     */
    LiveData<NetStatus> getStatus();

    /**
     * 重新加载数据
     *
     * @param reLoad
     */
    void loadData(boolean reLoad);

    default void loadData() {
        loadData(false);
    }

}
