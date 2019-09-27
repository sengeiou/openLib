/*
package com.open9527.code.lib.samples.module.im.room.user;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ThreadUtils;

import java.util.List;

*/
/**
 * Created by     : open9527
 * Created times  : on 2019/8/29 17:42.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 *//*

public class UserBeanManager {
    private static final String TAG = "UserBeanManager";
    private final UserBeanDao userBeanDao;

    UserBeanManager() {
        userBeanDao = UserBeanDatabase.getDatabse().userBeanDao();
    }

    */
/**
     * 查询所有用户
     *
     * @return
     *//*

    public void getAll() {
        ThreadUtils.executeByIo(new ThreadUtils.SimpleTask<List<UserBean>>() {
            @Nullable
            @Override
            public List<UserBean> doInBackground() throws Throwable {
                return userBeanDao.getAll();
            }

            @Override
            public void onSuccess(@Nullable List<UserBean> result) {

            }
        });
    }


}
*/
