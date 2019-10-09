package com.open9527.code.lib.databinding;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.open9527.code.common.databinding.BindingBaseCellAdapter;
import com.open9527.code.common.databinding.CommonBindingActivity;
import com.open9527.code.lib.R;

import java.util.ArrayList;

/**
 * Created by     : open9527
 * Created times  : on 2019/8/23 13:42.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class DataBindingActivity extends CommonBindingActivity<ActivityDatabindingBinding> {

    private String string = "在全球，随着Flutter被越来越多的知名公司应用在自己的商业APP中，" +
            "Flutter这门新技术也逐渐进入了移动开发者的视野，尤其是当Google在2018年IO大会上发布了第一个" +
            "Preview版本后，国内刮起来一股学习Flutter的热潮。\n\n为了更好的方便帮助中国开发者了解这门新技术" +
            "，我们，Flutter中文网，前后发起了Flutter翻译计划、Flutter开源计划，前者主要的任务是翻译" +
            "Flutter官方文档，后者则主要是开发一些常用的包来丰富Flutter生态，帮助开发者提高开发效率。而时" +
            "至今日，这两件事取得的效果还都不错！";

    @Override
    public CharSequence bindTitle() {
        return "DataBindingActivity";
    }

    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_databinding;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        mBinding.rvList.setLayoutManager(new LinearLayoutManager(this));
        BindingBaseCellAdapter bindingBaseCellAdapter = new BindingBaseCellAdapter<>();
        mBinding.rvList.setAdapter(bindingBaseCellAdapter);
        for (int i = 0; i < 10; i++) {
            BindingTxtCell bindingTxtCell = new BindingTxtCell(string + i);
            bindingBaseCellAdapter.addItem(bindingTxtCell);
        }
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onDebouncingClick(@NonNull View view) {

    }
}
