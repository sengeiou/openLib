package com.open9527.code.lib;


import android.content.Context;

import com.billy.android.swipe.SmartSwipeBack;
import com.didichuxing.doraemonkit.DoraemonKit;
import com.open9527.code.common.CommonApplication;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

/**
 * Created by     : open9527
 * Created times  : on 2019/8/22 17:48.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class Application extends CommonApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        initSwipeBack();
        DoraemonKit.install(this);
    }

    /**
     * 配置侧滑样式( 微信样式)
     */
    private void initSwipeBack() {
        SmartSwipeBack.activitySlidingBack(this, activity -> !(activity instanceof MainActivity));
    }


    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }
}
