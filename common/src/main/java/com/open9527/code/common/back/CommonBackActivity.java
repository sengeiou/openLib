package com.open9527.code.common.back;

import android.os.Bundle;

import com.blankj.swipepanel.SwipePanel;
import com.blankj.utilcode.util.SizeUtils;
import com.open9527.code.base.BaseActivity;
import com.open9527.code.common.R;

/**
 * Created by     : open9527
 * Created times  : on 2019/8/22 17:55.
 * E-Mail Address ：open_9527@163.com.
 * DESC :侧滑返回.
 */
public abstract class CommonBackActivity extends BaseActivity {

    public abstract boolean isSwipeBack();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSwipeBack();
    }

    private void initSwipeBack() {
        if (isSwipeBack()) {
            final SwipePanel swipeLayout = new SwipePanel(this);
            swipeLayout.setLeftDrawable(R.drawable.base_back);
            swipeLayout.setLeftEdgeSize(SizeUtils.dp2px(100));
            swipeLayout.wrapView(findViewById(android.R.id.content));
            swipeLayout.setOnFullSwipeListener(new SwipePanel.OnFullSwipeListener() {
                @Override
                public void onFullSwipe(int direction) {
                    swipeLayout.close(direction);
                    finish();
                }
            });
        }
    }
}
