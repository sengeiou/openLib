package com.open9527.code.lib.module.fragment.common;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Created by     : open9527
 * Created times  : on 2019/11/28/028 16:06.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public abstract class LazyFragment extends Fragment {


    private View root;

    protected boolean isFirstLoad = true;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (root == null) {
            root = inflater.inflate(getLayoutResource(), container, false);
        }
        initView(root);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstLoad) {
            // 将数据加载逻辑放到onResume()方法中
            initLazyData();
            isFirstLoad = false;
        }
    }


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    /**
     * 初始化资源控件
     *
     * @param view root
     */
    protected abstract void initView(View view);


    /**
     * 懒加载数据
     */
    protected abstract void initLazyData();

    /**
     * 获取布局资源
     *
     * @return layout resource id
     */
    protected abstract int getLayoutResource();


}
