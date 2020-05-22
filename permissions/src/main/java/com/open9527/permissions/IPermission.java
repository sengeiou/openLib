package com.open9527.permissions;

import java.util.List;

/**
 * Created by     : open9527
 * Created times  : on 2019/12/30 13:34.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public interface IPermission {
    /**
     * 有权限被同意授予时回调
     *
     * @param granted           请求成功的权限组
     * @param isAll             是否全部授予了
     */
    void hasPermission(List<String> granted, boolean isAll);

    /**
     * 有权限被拒绝授予时回调
     *
     * @param denied            请求失败的权限组
     * @param quick             是否有某个权限被永久拒绝了
     */
    void noPermission(List<String> denied, boolean quick);
}
