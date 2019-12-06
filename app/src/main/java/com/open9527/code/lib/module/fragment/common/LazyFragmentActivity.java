package com.open9527.code.lib.module.fragment.common;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.open9527.code.common.databinding.CommonBindingTitleActivity;
import com.open9527.code.lib.R;
import com.open9527.code.lib.databinding.ActivityLazyfragmentBinding;
import com.open9527.code.lib.module.fragment.FragmentOne;

import java.util.ArrayList;
import java.util.List;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

/**
 * Created by     : open9527
 * Created times  : on 2019/11/28/028 16:13.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class LazyFragmentActivity extends CommonBindingTitleActivity<ActivityLazyfragmentBinding> {
    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_lazyfragment;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        final LazyFragmentAdapter adapter = new LazyFragmentAdapter(getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.setItems(getItems());
        mBinding.viewpager.setAdapter(adapter);
    }


    private List<Fragment> getItems() {
        ArrayList<Fragment> items = new ArrayList<>();
        items.add(FragmentTwo.newInstance("FragmentOne0"));
        items.add(FragmentTwo.newInstance("FragmentOne1"));
        items.add(FragmentTwo.newInstance("FragmentOne2"));
        items.add(FragmentTwo.newInstance("FragmentOne3"));
        items.add(FragmentTwo.newInstance("FragmentOne4"));

        return items;
    }

}
