package com.open9527.code.lib.module.other;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.open9527.code.common.databinding.CommonBindingActivity;
import com.open9527.code.common.entity.Contacts;
import com.open9527.code.common.entity.LocalMediaFolder;
import com.open9527.code.common.interfaces.ILoadData;
import com.open9527.code.common.utils.MediaUtils;
import com.open9527.code.image.compression.Constants;
import com.open9527.code.lib.R;
import com.open9527.code.lib.databinding.ActivityOtherBinding;

import java.util.List;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/10 12:24.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class OtherActivity extends CommonBindingActivity<ActivityOtherBinding> {
    @Override
    public CharSequence bindTitle() {
        return "OtherActivity";
    }

    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_other;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {

    }

    @Override
    public void doBusiness() {
        //添加点击事件
        applyDebouncingClickListener(mBinding.tvAlbum, mBinding.tvContacts);

    }

    @Override
    public void onDebouncingClick(@NonNull View view) {
        switch (view.getId()) {
            case R.id.tv_album:
                requesPermission(Constants.ALBUM_CODE);
                break;
            case R.id.tv_contacts:
                requesPermission(Constants.OTHERS_CODE);
                break;
            default:
                break;
        }
    }


    /**
     * 获取权限(文件读取,通讯录)
     */
    private void requesPermission(int code) {
        PermissionUtils.permission(PermissionConstants.STORAGE, PermissionConstants.CONTACTS)//设置请求权限
                .rationale(shouldRequest -> {
                    //拒绝再次请求
                    ToastUtils.showShort("拒绝再次请求");
//                    shouldRequest.again(true);
                })
                .callback(new PermissionUtils.FullCallback() {//设置回调
                    @Override
                    public void onGranted(List<String> permissionsGranted) {
                        //权限确认
                        if (code == Constants.ALBUM_CODE) {
                            getMediaList();
                        } else {
                            getContacts();
                        }
                        ToastUtils.showShort("权限确认");
                    }

                    @Override
                    public void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied) {
                        //权限拒绝
                        ToastUtils.showShort("权限拒绝");
                    }
                })
                .theme(ScreenUtils::setFullScreen)
                .request();//触发请求

    }

    /**
     * 获取手机资源文件
     */
    private void getMediaList() {
        String durationCondition = MediaUtils.getDurationCondition(0, 0, 60, 3);
        String mediaCondition = MediaUtils.getAllMediaCondition(durationCondition, true);
        MediaUtils.getMediaList(this, mediaCondition, MediaUtils.SELECTION_ALL_ARGS, MediaUtils.TYPE_ALL, listILoadData);

    }

    /**
     * 获取通讯录
     */
    private void getContacts() {
        MediaUtils.getContacts(this, listILoadContacts);
    }

    private ILoadData<List<LocalMediaFolder>> listILoadData = new ILoadData<List<LocalMediaFolder>>() {
        @Override
        public void loadComplete(List<LocalMediaFolder> localMediaFolders) {
            LogUtils.i(TAG, GsonUtils.toJson(localMediaFolders));
            mBinding.tvDesc.setText(String.valueOf("获取手机资源文件: " + GsonUtils.toJson(localMediaFolders)));
        }
    };

    private ILoadData<List<Contacts>> listILoadContacts = new ILoadData<List<Contacts>>() {
        @Override
        public void loadComplete(List<Contacts> contactsList) {
            LogUtils.i(TAG, GsonUtils.toJson(contactsList));
            mBinding.tvDesc.setText(String.valueOf("获取通讯录: " + GsonUtils.toJson(contactsList)));
        }
    };
}
