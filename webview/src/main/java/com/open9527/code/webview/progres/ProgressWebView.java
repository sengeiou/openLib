
package com.open9527.code.webview.progres;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.open9527.code.webview.InterWebListener;
import com.open9527.code.webview.R;
import com.open9527.code.webview.X5WebView;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/24 18:15.
 * E-Mail Address ：open_9527@163.com.
 * DESC :自定义带进度条的webView.
 */
public class ProgressWebView extends FrameLayout {

    private X5WebView webView;

    public ProgressWebView(@NonNull Context context) {
        super(context);
        init();
    }

    public ProgressWebView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProgressWebView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Context context = getContext();
        if (context != null) {
            View view = LayoutInflater.from(context).inflate( R.layout.view_progress_web_view, this, false);
            webView = view.findViewById(R.id.web_view);
            final ProgressBar pbProgress = view.findViewById(R.id.pb_progress);
            pbProgress.setVisibility(VISIBLE);
            webView.getX5WebChromeClient().setWebListener(new InterWebListener() {
                @Override
                public void hindProgressBar() {
                    pbProgress.setVisibility(GONE);
                }

                @Override
                public void showErrorView() {

                }

                @Override
                public void startProgress(int newProgress) {
                    pbProgress.setProgress(newProgress);
                }

                @Override
                public void showTitle(String title) {

                }
            });
        }
    }

    /**
     * 获取X5WebView对象
     *
     * @return 获取X5WebView对象
     */
    public X5WebView getWebView() {
        return webView;
    }

}
