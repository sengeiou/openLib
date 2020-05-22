package com.open9527.code.lib.module.permission;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.open9527.code.common.databinding.CommonBindingTitleActivity;
import com.open9527.code.lib.R;
import com.open9527.code.lib.databinding.ActivityPermissionBinding;
import com.open9527.permissions.IPermission;
import com.open9527.permissions.Permission;
import com.open9527.permissions.PermissionsManager;

import java.util.List;

/**
 * 权限处理
 */
public class PermissionActivity extends CommonBindingTitleActivity<ActivityPermissionBinding> {
    @Override
    public int bindLayout() {
        return R.layout.activity_permission;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {

        PermissionsManager.with(this)
                // 可设置被拒绝后继续申请，直到用户授权或者永久拒绝
                //.constantRequest()
                // 支持请求6.0悬浮窗权限8.0请求安装权限
                //.permission(Permission.SYSTEM_ALERT_WINDOW, Permission.REQUEST_INSTALL_PACKAGES)
                // 不指定权限则自动获取清单中的危险权限
                .permission(Permission.Group.STORAGE, Permission.Group.CALENDAR)
                .request(new IPermission() {

                    @Override
                    public void hasPermission(List<String> granted, boolean all) {

                    }

                    @Override
                    public void noPermission(List<String> denied, boolean quick) {
                        PermissionsManager.gotoPermissionSettings(mActivity);
                    }
                });

    }
}
