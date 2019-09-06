package com.open9527.code.shareelement.transition;

import android.transition.Transition;
import android.view.View;

import java.util.List;

/**
 * Created by     : open9527
 * Created times  : on 2019/8/22 17:47.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public interface IShareElementTransitionFactory {
    Transition buildShareElementEnterTransition(List<View> shareViewList);

    Transition buildShareElementExitTransition(List<View> shareViewList);

    Transition buildEnterTransition();

    Transition buildExitTransition();
}
