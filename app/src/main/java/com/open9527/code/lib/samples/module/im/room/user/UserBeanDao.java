package com.open9527.code.lib.samples.module.im.room.user;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * Created by     : open9527
 * Created times  : on 2019/8/29 17:34.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
@Dao
public interface UserBeanDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(UserBean userBean);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(List<UserBean> userBeanList);

    @Update()
    int update(UserBean userBean);

    @Update()
    int updateAll(List<UserBean> userBeanList);

    @Delete
    int deleteAll(List<UserBean> userBeanList);

    /**********************************************查询*******************************************************/
    @Query("SELECT * FROM user_table ")
    List<UserBean> getAll();

    @Query("DELETE  FROM user_table ")
    void deleteAll();
}
