package com.open9527.permissions;

/**
 * Created by     : open9527
 * Created times  : on 2019/12/30 13:34.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
final class ManifestRegisterException extends RuntimeException {

    ManifestRegisterException(String permission) {
        super(permission == null ?
                "No permissions are registered in the manifest file" :
                (permission + ": PermissionsManager are not registered in the manifest file"));
    }
}