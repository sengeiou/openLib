package com.open9527.code.lib;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.app.TaskStackBuilder;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.open9527.code.common.databinding.CommonBindingActivity;
import com.open9527.code.lib.databinding.ActivityMainBinding;
import com.open9527.code.lib.model.EntryBean;
import com.open9527.code.lib.module.customview.RadioRecycleViewActivity;
import com.open9527.code.lib.module.splash.SplashFragment;

import java.util.List;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/17 16:29.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class MainActivity extends CommonBindingActivity<ActivityMainBinding> {

    private MainViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setBackgroundDrawable(null);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData(@Nullable Bundle bundle) {
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        initScheme();
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }


    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        mAppViewModel.openOrCloseDrawer.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

            }
        });
        mViewModel.closeSplashEvent.observe(this, new Observer<Void>() {
            @Override
            public void onChanged(Void v) {
                initFragment(new MainFragment());
            }
        });
        mViewModel.mEntryInfoRepository.getData().observe(this, new Observer<List<EntryBean>>() {
            @Override
            public void onChanged(List<EntryBean> entryBeans) {

            }
        });
    }

    private void initFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_content, fragment);
        transaction.commit();
    }

    private void initScheme() {
        if (ActivityUtils.isActivityAlive(this)) {
            Intent intent = new Intent();

        }
        try {
            Uri uri = getIntent().getData();

//            TaskStackBuilder.create(this)
//                    .addParentStack(getIntent().getComponent())
//                    .addNextIntent(getIntent())
//                    .startActivities();

            if (uri != null) {
                LogUtils.i(TAG, GsonUtils.toJson(uri));
                //获取指定参数值
                String query1 = uri.getQueryParameter("query1");
                boolean query2 = uri.getBooleanQueryParameter("query2", false);
                if ("1".equals(query1) && query2) {
                    initFragment(new MainFragment());
                    ActivityUtils.startActivity(RadioRecycleViewActivity.class);
                    return;
                }
            }
        } catch (Exception e) {
            initFragment(new SplashFragment());
            return;
        }
        initFragment(new SplashFragment());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        LogUtils.i();
//        startIntent(intent);

    }
}
