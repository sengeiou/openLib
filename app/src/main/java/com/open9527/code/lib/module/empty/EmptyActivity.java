package com.open9527.code.lib.module.empty;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.open9527.code.common.databinding.CommonBindingTitleActivity;
import com.open9527.code.lib.R;
import com.open9527.code.lib.databinding.ActivityEmptyBinding;
import com.open9527.empty.EmptyConfig;
import com.open9527.empty.EmptyManager;
import com.open9527.empty.IEmpty;


/**
 * empty
 */
public class EmptyActivity extends CommonBindingTitleActivity<ActivityEmptyBinding> {


    private EmptyManager emptyManager;

    @Override
    public int bindLayout() {
        return R.layout.activity_empty;
    }

    @Override
    public void initData(@Nullable Bundle bundle) {
        super.initData(bundle);
        initRequest(0);
    }

    private void initRequest(int type) {
        //模拟请求延时
        ThreadUtils.runOnUiThreadDelayed(new Runnable() {
            @Override
            public void run() {
                if (0 == type) {
                    emptyManager.error();
                } else if (1 == type) {
                    emptyManager.empty();
                } else if (2 == type) {
                    emptyManager.success();
                }
                LogUtils.i(TAG, "runOnUiThreadDelayed");
            }
        }, 3000);
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {

        EmptyConfig emptyConfig = EmptyConfig.builder()
                .setRootView(mRootView)
                .setLoadingLayoutID(R.layout.empty_loading)
                .setLoadingView(getView("setLoadingView"))
                .setErrorLayoutID(R.layout.empty_error)
                .setErrorView(getView("setErrorView"))
                .setEmptyLayoutID(R.layout.empty_custom)
                .setEmptyView(getView("setEmptyView"))
                .create();

        emptyManager = EmptyManager.build(emptyConfig, new IEmpty.EmptyListener() {
            @Override
            public void onErrorRetry(View view) {
                LogUtils.i(TAG, "onErrorRetry");
                emptyManager.loading();
                initRequest(1);
            }

            @Override
            public void onEmpty(View view) {
                LogUtils.i(TAG, "onEmpty");
                emptyManager.loading();
                initRequest(2);

            }
        });
        emptyManager.loading();
        mBinding.tvDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.i(TAG, "onClick-->tvDesc");
                emptyManager.loading();
                initRequest(0);
            }
        });

    }

    private TextView getView(String string) {
        TextView textView = new TextView(this);
        textView.setGravity(Gravity.CENTER);
        textView.setText(string);
        return textView;
    }


}
