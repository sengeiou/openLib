package com.open9527.code.lib.module.browser;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;
import com.open9527.code.common.databinding.CommonBindingTitleActivity;
import com.open9527.code.lib.R;
import com.open9527.code.lib.databinding.ActivityBrowserBinding;
import com.open9527.code.webview.CallBackFunction;
import com.open9527.code.webview.bridge.BridgeHandler;
import com.open9527.code.webview.bridge.DefaultHandler;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/12 15:13.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class BrowserTitleActivity extends CommonBindingTitleActivity<ActivityBrowserBinding> {

    @Override
    public int bindLayout() {
        return R.layout.activity_browser;
    }

    @Override
    public boolean hasContentScroll() {
        return false;
    }

    @Override
    public void initData(@Nullable Bundle bundle) {

    }


    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
//        String url = "http://activity.fm.easttone.com/awardquestion/toIndex.do?&linkid=3e89d38278aa41a9acc8ac6904a29228&actId=8a8a9e5f6d67102a016d67102a760000&jwid=gh_835d899e19dc&type=h5";
        String url="https://github.com/open9527";
//        String url = "https://activity.shmedia.tech/businesshall/vm/toIndex.do?openid=logout&appid=${appid}&linkid=ff808081552973740155298eb8360006&actId=8a8a9e5f6caf3e51016caf3e517f0000&jwid=gh_835d899e19dc&type=h5&token=logout";
        mBinding.x5Web.loadUrl(url);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mBinding.x5Web.canGoBack() && event.getKeyCode() ==
                KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            mBinding.x5Web.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        if (mBinding.x5Web != null) {
            mBinding.x5Web.clearHistory();
            ViewGroup parent = (ViewGroup) mBinding.x5Web.getParent();
            if (parent != null) {
                parent.removeView(mBinding.x5Web);
            }
            mBinding.x5Web.destroy();
        }
        super.onDestroy();
    }


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onResume() {
        super.onResume();
        if (mBinding.x5Web != null) {
            mBinding.x5Web.getSettings().setJavaScriptEnabled(true);
        }
    }
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onStop() {
        super.onStop();
        if (mBinding.x5Web != null) {
            mBinding.x5Web.getSettings().setJavaScriptEnabled(false);
        }
    }

    @Override
    public void doBusiness() {
        //js交互方法
        initWebViewBridge();
    }

    @JavascriptInterface
    public void initWebViewBridge() {
        mBinding.x5Web.setDefaultHandler(new DefaultHandler());
        mBinding.x5Web.registerHandler("goToLoginFormTheApp", new MyBridgeHandler("goToLoginFormTheApp") {
            @Override
            public void handler(String data, CallBackFunction function) {

            }
        });
    }

    public class MyBridgeHandler implements BridgeHandler {
        private String type;

        public MyBridgeHandler(String type) {
            this.type = type;
        }

        @Override
        public void handler(String data, CallBackFunction function) {
            LogUtils.i(TAG, "data=" + data + "  type= " + type);
            switch (type) {
                case "goToLoginFormTheApp":
                    //TODO:
                    break;
                default:
                    break;
            }
        }
    }
}
