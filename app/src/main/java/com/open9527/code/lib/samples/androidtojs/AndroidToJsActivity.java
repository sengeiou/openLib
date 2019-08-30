package com.open9527.code.lib.samples.androidtojs;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.github.lzyzsd.jsbridge.DefaultHandler;
import com.open9527.code.common.activity.CommonScreenActivity;
import com.open9527.code.lib.R;

/**
 * Created by     : open9527
 * Created times  : on 2019/8/27 10:39.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class AndroidToJsActivity extends CommonScreenActivity {

    private EditText et;
    private Button bt;
    private Button bt2;
    private BridgeWebView webview;


    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_android_to_js;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        et = findViewById(R.id.et);
        bt = findViewById(R.id.bt);
        bt2 = findViewById(R.id.bt2);
        webview = findViewById(R.id.webview);
        webview.setDefaultHandler(new DefaultHandler());
        webview.setWebChromeClient(new WebChromeClient());
//        webview.loadUrl("file:///android_asset/html/test.html");
        webview.loadUrl("https://test.shmedia.tech/app_ja/ja_tt/20190824/2cbbc2fe2504413881943367f6c13d2d.html");
//      注册监听方法当js中调用callHandler方法时会调用此方法（handlerName必须和js中相同）
        webview.registerHandler("showReportView", new BridgeHandler() {
//        webview.registerHandler("submitFromWeb", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                LogUtils.e("TAG", "js返回:" + data);
                //显示js传递给Android的消息
                ToastUtils.showShort("js返回:" + data);
                //Android返回给JS的消息
                function.onCallBack("我是js调用Android返回数据：" + et.getText().toString());
            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              调用js中的方法（必须和js中的handlerName想同）
                webview.callHandler("functionInJs", "Android调用js66", new CallBackFunction() {
                    @Override
                    public void onCallBack(String data) {
                        LogUtils.e("TAG", "onCallBack:" + data);
                        ToastUtils.showShort(data);
                    }
                });
            }
        });
        webview.setDefaultHandler(new DefaultHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                ToastUtils.showShort(data);
                function.onCallBack("Android收到了默认的消息");
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              发送信息给js,此处不需要配置handlerName
                webview.send(et.getText().toString().trim(), new CallBackFunction() {
                    @Override
                    public void onCallBack(String data) {
//                      接收js的回调数据
                        ToastUtils.showShort(data);
                    }
                });
            }
        });
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onDebouncingClick(@NonNull View view) {

    }
}
