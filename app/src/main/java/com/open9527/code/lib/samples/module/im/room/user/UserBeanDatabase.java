/*
package com.open9527.code.lib.samples.module.im.room.user;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.blankj.utilcode.util.Utils;

*/
/**
 * Created by     : open9527
 * Created times  : on 2019/8/29 17:38.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 *//*

@Database(entities = {UserBean.class}, version = 1, exportSchema = false)
public abstract class UserBeanDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "user.db";

    public abstract UserBeanDao userBeanDao();

    */
/**
     * 创建单例模式
     *//*

    private static UserBeanDatabase INSTANCE;

    public static UserBeanDatabase getDatabse() {
        if (INSTANCE == null) {
            INSTANCE = Room.
                    databaseBuilder(Utils.getApp().getApplicationContext(), UserBeanDatabase.class, DATABASE_NAME)
                    .build();
        }
        return INSTANCE;
    }

    */
/**
     * 销毁
     *//*

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
*/
