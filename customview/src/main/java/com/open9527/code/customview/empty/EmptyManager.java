package com.open9527.code.customview.empty;

import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.IntDef;
import androidx.annotation.LayoutRes;

import com.open9527.code.customview.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/21 15:05.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class EmptyManager {
    public final static int LOADING = 1;
    public final static int ERROR = -1;
    public final static int SUCCESS = 0;
    public final static int CUSTOM = 2;

    @IntDef({LOADING, ERROR, SUCCESS, CUSTOM})

    @Retention(RetentionPolicy.SOURCE)
    public @interface EmptyType {
    }

    @EmptyType
    private int emptyType = LOADING;

    public void setEmptyType(@EmptyType int emptyType) {
        this.emptyType = emptyType;
    }

    @EmptyType
    public int getEmptyType() {
        return emptyType;
    }

    private EmptyConfig emptyConfig;

    private EmptyManager(EmptyConfig emptyConfig) {
        this.emptyConfig = emptyConfig;
    }

    public static EmptyManager build(EmptyConfig emptyConfig) {
        return new EmptyManager(emptyConfig);
    }

    /**
     * 根据类型显示需要的布局
     *
     * @param emptyType
     */
    public void showEmptyLayout(@EmptyType int emptyType) {
        createEmptyLayout(emptyType);
        emptyConfig.getEmptyLayoutHelper().showStatusLayout(emptyLayout);
    }


    /**
     * 创建根据type类型加载的布局
     */
    private View emptyLayout;

    private void createEmptyLayout(@EmptyType int emptyType) {
        switch (emptyType) {
            case LOADING:
                emptyLayout = inflate(R.layout.empty_sub_loading);
                break;
            case ERROR:
                emptyLayout = inflate(R.layout.empty_sub_error);
                setErrorRetryClickListener(emptyLayout);
                break;
            default:
                showSuccessLayout();
                break;
        }
    }


    /**
     * 配置错误重试事件
     */
    private void setErrorRetryClickListener(View emptyLayout) {
        if (emptyLayout != null && emptyConfig != null) {
            emptyLayout.setOnClickListener(view -> {
                if (emptyConfig.getiEmptyClick() != null) {
                    emptyConfig.getiEmptyClick().onErrorRetry(view);
                }
            });
        }
    }

    /**
     * 用于加载布局
     *
     * @param resource
     * @return
     */
    private LayoutInflater inflater;

    private View inflate(@LayoutRes int resource) {
        if (inflater == null) {
            inflater = LayoutInflater.from(emptyConfig.getContentLayout().getContext());
        }
        return inflater.inflate(resource, null);
    }

    /**
     * 显示原有布局
     */
    public void showSuccessLayout() {
        emptyConfig.getEmptyLayoutHelper().restoreLayout();
    }

}
