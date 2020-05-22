package com.open9527.permissions;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by     : open9527
 * Created times  : on 2019/12/30 13:34.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public final class PermissionsManager {

    private Activity mActivity;
    private List<String> mPermissions;
    private boolean mConstant;

    /**
     * 私有化构造函数
     */
    private PermissionsManager(Activity activity) {
        mActivity = activity;
    }

    /**
     * 设置请求的对象
     */
    public static PermissionsManager with(Activity activity) {
        return new PermissionsManager(activity);
    }

    /**
     * 设置权限组
     */
    public PermissionsManager permission(String... permissions) {
        if (mPermissions == null) {
            mPermissions = new ArrayList<>(permissions.length);
        }
        mPermissions.addAll(Arrays.asList(permissions));
        return this;
    }

    /**
     * 设置权限组
     */
    public PermissionsManager permission(String[]... permissions) {
        if (mPermissions == null) {
            int length = 0;
            for (String[] permission : permissions) {
                length += permission.length;
            }
            mPermissions = new ArrayList<>(length);
        }
        for (String[] group : permissions) {
            mPermissions.addAll(Arrays.asList(group));
        }
        return this;
    }

    /**
     * 设置权限组
     */
    public PermissionsManager permission(List<String> permissions) {
        if (mPermissions == null) {
            mPermissions = permissions;
        }else {
            mPermissions.addAll(permissions);
        }
        return this;
    }

    /**
     * 被拒绝后继续申请，直到授权或者永久拒绝
     */
    public PermissionsManager constantRequest() {
        mConstant = true;
        return this;
    }

    /**
     * 请求权限
     */
    public void request(IPermission call) {
        // 如果没有指定请求的权限，就使用清单注册的权限进行请求
        if (mPermissions == null || mPermissions.isEmpty()) {
            mPermissions = PermissionUtils.getManifestPermissions(mActivity);
        }
        if (mPermissions == null || mPermissions.isEmpty()) {
            throw new IllegalArgumentException("The requested permission cannot be empty");
        }
        // 使用isFinishing方法 Activity 在熄屏状态下会导致崩溃
//        if (mActivity == null || mActivity.isFinishing()) {
//            throw new IllegalArgumentException("Illegal Activity was passed in");
//        }
        if (mActivity == null) {
            throw new IllegalArgumentException("The activity is empty");
        }
        if (call == null) {
            throw new IllegalArgumentException("The permission request callback interface must be implemented");
        }

        PermissionUtils.checkTargetSdkVersion(mActivity, mPermissions);

        ArrayList<String> failPermissions = PermissionUtils.getFailPermissions(mActivity, mPermissions);

        if (failPermissions == null || failPermissions.isEmpty()) {
            // 证明权限已经全部授予过
            call.hasPermission(mPermissions, true);
        } else {
            // 检测权限有没有在清单文件中注册
            PermissionUtils.checkPermissions(mActivity, mPermissions);
            // 申请没有授予过的权限
            PermissionFragment.newInstance((new ArrayList<>(mPermissions)), mConstant).prepareRequest(mActivity, call);
        }
    }

    /**
     * 检查某些权限是否全部授予了
     *
     * @param context     上下文对象
     * @param permissions 需要请求的权限组
     */
    public static boolean isHasPermission(Context context, String... permissions) {
        return isHasPermission(context, Arrays.asList(permissions));
    }

    public static boolean isHasPermission(Context context, List<String> permissions) {
        ArrayList<String> failPermissions = PermissionUtils.getFailPermissions(context, permissions);
        return failPermissions == null || failPermissions.isEmpty();
    }

    /**
     * 检查某些权限是否全部授予了
     *
     * @param context     上下文对象
     * @param permissions 需要请求的权限组
     */
    public static boolean isHasPermission(Context context, String[]... permissions) {
        List<String> permissionList = new ArrayList<>();
        for (String[] group : permissions) {
            permissionList.addAll(Arrays.asList(group));
        }
        ArrayList<String> failPermissions = PermissionUtils.getFailPermissions(context, permissionList);
        return failPermissions == null || failPermissions.isEmpty();
    }

    /**
     * 跳转到应用权限设置页面
     *
     * @param context 上下文对象
     */
    public static void gotoPermissionSettings(Context context) {
        PermissionSettingPage.start(context, false);
    }

    /**
     * 跳转到应用权限设置页面
     *
     * @param context 上下文对象
     * @param newTask 是否使用新的任务栈启动
     */
    public static void gotoPermissionSettings(Context context, boolean newTask) {
        PermissionSettingPage.start(context, newTask);
    }
}