package com.open9527.code.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.blankj.utilcode.util.ClickUtils;

/**
 * Created by     : open9527
 * Created times  : on 2019/8/22 17:35.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public abstract class BaseFragment extends Fragment implements IBaseView {

    protected final String TAG = getClass().getSimpleName();

    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";

    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onDebouncingClick(v);
        }
    };

    protected AppCompatActivity mActivity;
    protected LayoutInflater mInflater;
    protected View mContentView;

    protected boolean isFirstLoad = true; // 是否第一次加载

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstLoad) {
            doBusiness();
            isFirstLoad = false;
        }
    }

    @Override
    public void onAttach(Context context) {
        logI(TAG, "onAttach");
        super.onAttach(context);
        mActivity = (AppCompatActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        logI(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        FragmentManager fm = getFragmentManager();
        if (fm == null) return;
        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);
            FragmentTransaction ft = fm.beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commitAllowingStateLoss();
        }
        Bundle bundle = getArguments();
        initData(bundle);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        logI(TAG, "onCreateView");
        super.onCreateView(inflater, container, savedInstanceState);
        mInflater = inflater;
        setContentView();
        return mContentView;
    }

    @Override
    public void setContentView() {
        if (bindLayout() <= 0) return;
        mContentView = mInflater.inflate(bindLayout(), null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        logI(TAG, "onViewCreated");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        logI(TAG, "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
        initView(savedInstanceState, mContentView);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        logI(TAG, "onHiddenChanged: " + hidden);
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onDestroyView() {
        logI(TAG, "onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        logI(TAG, "onSaveInstanceState");
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }

    @Override
    public void onDestroy() {
        logI(TAG, "onDestroy");
        super.onDestroy();
    }

    public void applyDebouncingClickListener(View... views) {
        ClickUtils.applyGlobalDebouncing(views, mClickListener);
    }

    public <T extends View> T findViewById(@IdRes int id) {
        if (mContentView == null) throw new NullPointerException("ContentView is null.");
        return mContentView.findViewById(id);
    }


}
