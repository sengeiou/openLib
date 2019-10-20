package com.open9527.code.lib.samples.androidtojs;

import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.open9527.code.common.databinding.CommonBindingTitleActivity;
import com.open9527.code.lib.R;
import com.open9527.code.lib.databinding.ActivityX5webviewBinding;
//import com.open9527.code.webview.X5WebView;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/24 18:52.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class WebViewTitleActivity extends CommonBindingTitleActivity<ActivityX5webviewBinding> {

//    private X5WebView x5WebView;

    @Override
    public CharSequence bindTitle() {
        return "WebViewTitleActivity";
    }

    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_x5webview;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
//        x5WebView = mBinding.webView;
//        x5WebView.loadUrl("https://github.com/open9527");
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onDebouncingClick(@NonNull View view) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            if (x5WebView.canGoBack()) {
//                x5WebView.goBack();
//                return true;
//                //退出网页
//            } else {
//                handleFinish();
//            }
//        }
        return false;
    }

    public void handleFinish() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        } else {
            finish();
        }
    }


    @Override
    protected void onDestroy() {
//        try {
//            if (x5WebView != null) {
//                x5WebView.destroy();
//                x5WebView = null;
//            }
//        } catch (Exception e) {
//            Log.e("X5WebViewActivity", e.getMessage());
//        }
        super.onDestroy();
    }
}
