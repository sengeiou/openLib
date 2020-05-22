package com.open9527.empty;

import android.view.View;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/21 15:05.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class EmptyManager implements IEmpty {

    /**
     * EmptyConfig 配置文件
     */
    private EmptyConfig emptyConfig;

    /**
     * 布局处理帮助类
     */
    private EmptyLayoutHelper emptyLayoutHelper;

    /**
     * 点击事件
     */
    private EmptyListener listener;


    private EmptyManager(EmptyConfig emptyConfig, EmptyListener listener) {
        this.emptyConfig = emptyConfig;
        this.listener = listener;
        this.emptyLayoutHelper = EmptyLayoutHelper.create(emptyConfig.getRootView());
    }

    public static EmptyManager build(EmptyConfig emptyConfig, EmptyListener listener) {
        return new EmptyManager(emptyConfig, listener);
    }

    @Override
    public void loading() {
        emptyLayoutHelper.setEmptyType(EmptyLayoutHelper.LOADING);
        showEmptyLayout();
    }

    @Override
    public void success() {
        emptyLayoutHelper.setEmptyType(EmptyLayoutHelper.SUCCESS);
        showEmptyLayout();

    }

    @Override
    public void error() {
        emptyLayoutHelper.setEmptyType(EmptyLayoutHelper.ERROR);
        showEmptyLayout();
    }

    @Override
    public void empty() {
        emptyLayoutHelper.setEmptyType(EmptyLayoutHelper.EMPTY);
        showEmptyLayout();
    }


    @Override
    public void onErrorRetry(View view) {
        setViewClickListener(view);
    }

    @Override
    public void onEmpty(View view) {
        setViewClickListener(view);
    }


    /**
     * 配置错误重试事件
     */
    private void setViewClickListener(View view) {
        if (view != null) {
            view.setOnClickListener(v -> {
                if (EmptyLayoutHelper.EMPTY == emptyLayoutHelper.getEmptyType()) {
                    if (listener != null) {
                        listener.onEmpty(v);
                    }
                }
                if (EmptyLayoutHelper.ERROR == emptyLayoutHelper.getEmptyType()) {
                    if (listener != null) {
                        listener.onErrorRetry(v);
                    }
                }
            });
        }
    }


    /**
     * 根据类型显示需要的布局
     */
    private void showEmptyLayout() {
        View view = createEmptyLayout();
        if (view != null) {
            emptyLayoutHelper.replaceLayout(view);
        } else {
            throw new IllegalArgumentException("view is null!");
        }
    }

    /**
     * 创建根据type类型加载的布局
     */

    private View createEmptyLayout() {
        View emptyLayout = null;
        switch (emptyLayoutHelper.getEmptyType()) {
            case EmptyLayoutHelper.LOADING: {
                if (emptyConfig.getLoadingLayoutID() > 0) {
                    emptyLayout = emptyLayoutHelper.inflate(emptyConfig.getLoadingLayoutID());
                } else if (emptyConfig.getLoadingView() != null) {
                    emptyLayout = emptyConfig.getLoadingView();
                } else {
                    emptyLayout = emptyLayoutHelper.inflate(R.layout.empty_sub_loading);
                }
            }
            break;
            case EmptyLayoutHelper.SUCCESS: {
                emptyLayout = emptyConfig.getRootView();
            }
            break;
            case EmptyLayoutHelper.ERROR: {
                if (emptyConfig.getErrorLayoutID() > 0) {
                    emptyLayout = emptyLayoutHelper.inflate(emptyConfig.getErrorLayoutID());
                } else if (emptyConfig.getErrorView() != null) {
                    emptyLayout = emptyConfig.getErrorView();
                } else {
                    emptyLayout = emptyLayoutHelper.inflate(R.layout.empty_sub_error);
                }
                onErrorRetry(emptyLayout);
            }
            break;
            case EmptyLayoutHelper.EMPTY: {
                if (emptyConfig.getEmptyLayoutID() > 0) {
                    emptyLayout = emptyLayoutHelper.inflate(emptyConfig.getEmptyLayoutID());
                } else if (emptyConfig.getEmptyView() != null) {
                    emptyLayout = emptyConfig.getEmptyView();
                } else {
                    emptyLayout = emptyLayoutHelper.inflate(R.layout.empty_sub_empty);
                }
                onEmpty(emptyLayout);
            }
            break;
            default:
                break;
        }
        return emptyLayout;
    }

}
