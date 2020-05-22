package com.open9527.empty;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IntDef;
import androidx.annotation.LayoutRes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/12 15:15.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class EmptyLayoutHelper {


    final static int LOADING = 1;

    final static int SUCCESS = 0;

    final static int EMPTY = 2;

    final static int ERROR = -1;

    @IntDef({LOADING, SUCCESS, EMPTY, ERROR})
    @Retention(RetentionPolicy.SOURCE)
    @interface EmptyType {

    }

    @EmptyType
    private int emptyType = LOADING;

    void setEmptyType(@EmptyType int emptyType) {
        this.emptyType = emptyType;
    }

    @EmptyType
    int getEmptyType() {
        return emptyType;
    }


    private EmptyLayoutHelper(View view) {
        initAttributes(view);
    }

    private void initAttributes(View view) {
        setCurrentView(view);
        setmOriginalView(view);
        setmParentView(getParentView(view));
        getViewIndex();
    }

    public static EmptyLayoutHelper create(View view) {
        return new EmptyLayoutHelper(view);
    }

    /**
     * 原始布局
     */
    private View mOriginalView;

    /**
     * 当前布局
     */
    private View mCurrentView;

    /**
     * 在 viewGroup 中的位置
     */
    private int mViewIndex = 0;

    /**
     * 父控件
     */
    private ViewGroup mParentView;

    private void setCurrentView(View view) {
        this.mCurrentView = view;
    }

    private View getmCurrentView() {
        return mCurrentView;
    }

    private View getmOriginalView() {
        return mOriginalView;
    }

    private void setmOriginalView(View mOriginalView) {
        this.mOriginalView = mOriginalView;
    }

    public int getmViewIndex() {
        return mViewIndex;
    }

    public void setmViewIndex(int mViewIndex) {
        this.mViewIndex = mViewIndex;
    }

    public ViewGroup getmParentView() {
        return mParentView;
    }

    public void setmParentView(ViewGroup mParentView) {
        this.mParentView = mParentView;
    }


    /**
     * 配置EmptyLayout
     *
     * @param view
     */
    void replaceLayout(View view) {
        if (view == null) {
            return;
        }
        if (getmCurrentView() != view) {
            setCurrentView(view);
            ViewGroup parent = (ViewGroup) view.getParent();
            // 去除 view 的 父 view，才能添加到别的 ViewGroup 中
            if (parent != null) {
                parent.removeView(view);
            }
            // 替换 = 移除 + 添加
            getmParentView().removeViewAt(getmViewIndex());
            getmParentView().addView(view, getmViewIndex(), getmOriginalView().getLayoutParams());
        } else {
            throw new IllegalArgumentException("view is mCurrentView !");
        }
    }


    /**
     * 1.有直接的父控件
     * 2.父控件就是 android.R.id.content
     * 3.父控件就是自己
     *
     * @param view
     * @return 获取 ParentView
     */
    private ViewGroup getParentView(View view) {
        ViewGroup viewParent = (ViewGroup) view.getParent();
        View rootView = view.getRootView();
        if (viewParent != null) {
            //有直接的父控件
            return viewParent;
        } else if (rootView != null) {
            //父控件就是 android.R.id.content
            return rootView.findViewById(android.R.id.content);
        } else if (view instanceof ViewGroup) {
            //父控件就是自己
            return (ViewGroup) view;
        } else {
            throw new IllegalArgumentException("view is not ViewGroup!");
        }
    }


    /**
     * 用于加载布局
     *
     * @param resource
     * @return
     */
    private LayoutInflater inflater;

    public View inflate(@LayoutRes int resource) {
        if (inflater == null) {
            inflater = LayoutInflater.from(getmCurrentView().getContext());
        }
        return inflater.inflate(resource, null);
    }


    /**
     * 获取在 viewGroup 中的位置
     *
     * @param
     */
    private void getViewIndex() {
        ViewGroup viewGroup = getmParentView();
        if (viewGroup != null) {
            int count = viewGroup.getChildCount();
            for (int index = 0; index < count; index++) {
                if (getmOriginalView() == viewGroup.getChildAt(index)) {
                    setmViewIndex(index);
                    break;
                }
            }
        }
    }
}
