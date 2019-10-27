package com.open9527.code.lib;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.open9527.code.common.databinding.CommonBindingActivity;
import com.open9527.code.lib.databinding.ActivityMainBinding;
import com.open9527.code.lib.model.EntryBean;
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
    public void initData(@Nullable Bundle bundle) {
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        initFragment(new SplashFragment());
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }


    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
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
}
