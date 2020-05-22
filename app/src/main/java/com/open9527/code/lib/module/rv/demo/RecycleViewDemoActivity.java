package com.open9527.code.lib.module.rv.demo;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.utilcode.util.ToastUtils;
import com.open9527.code.common.databinding.CommonBindingTitleActivity;
import com.open9527.code.lib.R;
import com.open9527.code.lib.databinding.ActivityRecycleviewBinding;
import com.open9527.code.lib.model.RadioBean;
import com.open9527.code.lib.module.rv.BaseItem;
import com.open9527.code.lib.module.rv.BaseItemAdapter;
import com.open9527.code.lib.module.rv.common.SingleCheckItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by     : open9527
 * Created times  : on 2019/11/22 10:43.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class RecycleViewDemoActivity extends CommonBindingTitleActivity<ActivityRecycleviewBinding> {
    private List<BaseItem> baseItemList = new ArrayList<>();
    private List<RadioBean> radioBeans = new ArrayList<>();
    private BaseItemAdapter<BaseItem> mAdapter;

    @Override
    public void initData(@Nullable Bundle bundle) {
        for (int i = 0; i < 20; i++) {
            RadioBean radioBean = new RadioBean(String.valueOf("这是一段文字" + i));
            radioBeans.add(radioBean);
        }
        for (int i = 0; i <radioBeans.size() ; i++) {
            RadioBean radioBean=radioBeans.get(i);
            baseItemList.add(new SingleCheckItem(radioBean));
        }
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_recycleview;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        mBinding.rvList.setLayoutManager(new LinearLayoutManager(mActivity));
        mBinding.rvList.setHasFixedSize(true);
        mAdapter = new BaseItemAdapter<>();
        mBinding.rvList.setAdapter(mAdapter);
        mAdapter.setItems(baseItemList);
    }
}
