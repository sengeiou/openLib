package com.open9527.code.lib.module.smartswipe;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.billy.android.swipe.SmartSwipeRefresh;
import com.open9527.code.common.databinding.CommonBindingTitleActivity;
import com.open9527.code.lib.R;
import com.open9527.code.lib.databinding.ActivitySmartswipeBinding;

/**
 * Created by     : open9527
 * Created times  : on 2019/11/1 16:56.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class SmartSwipeActivity extends CommonBindingTitleActivity<ActivitySmartswipeBinding> {
    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_smartswipe;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        SmartSwipeRefresh.behindMode(mBinding.rvList, false).setDataLoader(loader);
    }

    SmartSwipeRefresh.SmartSwipeRefreshDataLoader loader = new SmartSwipeRefresh.SmartSwipeRefreshDataLoader() {
        @Override
        public void onRefresh(final SmartSwipeRefresh ssr) {
            //加载刷新数据
            ssr.finished(true);
            ssr.setNoMoreData(true);
        }

        @Override
        public void onLoadMore(final SmartSwipeRefresh ssr) {
            //加载下一页数据
            ssr.finished(true);
            ssr.setNoMoreData(true);
        }
    };

}
