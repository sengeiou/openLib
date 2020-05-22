package com.open9527.code.lib.module.splash;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.AdaptScreenUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.Utils;
import com.open9527.code.common.config.CommonConfig;
import com.open9527.code.common.databinding.CommonBindingActivity;
import com.open9527.code.lib.MainActivity;
import com.open9527.code.lib.R;
import com.open9527.code.lib.module.dialog.BottomDialogFragment;


/**
 * Created by     : open9527
 * Created times  : on 2019/10/16 17:25.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class SplashActivity extends CommonBindingActivity {
//public class SplashActivity extends AppCompatActivity {
//    @Override
//    public Resources getResources() {
//        return AdaptScreenUtils.adaptWidth(super.getResources(), CommonConfig.ADAPT_SCREEN_WIDTH);
//    }

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);
//
////        findViewById(R.id.cl_root).setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                LogUtils.i("onClick","cl_root");
////                QuickPopupBuilder.with(SplashActivity.this)
////                        .contentView(R.layout.dialog_bottom)
////                        .config(new QuickPopupConfig()
////                                .gravity(Gravity.CENTER)
////                        )
////                        .show();
////            }
////        });
//    }

    @Override
    protected void onResume() {
        getWindow().setBackgroundDrawable(null);
        super.onResume();
//        QuickPopupBuilder.with(SplashActivity.this)
//                .contentView(R.layout.dialog_bottom)
//                .config(new QuickPopupConfig()
//                        .gravity(Gravity.CENTER)
//                )
//                .show();
    }


        @Override
    public void initData(@Nullable Bundle bundle) {
        getWindow().setBackgroundDrawable(null);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        ActivityUtils.startActivity(MainActivity.class);
        finish();
//        BottomDialogFragment.create(getSupportFragmentManager())
//                .setLayoutRes(R.layout.dialog_bottom)
//                .setHeight(AdaptScreenUtils.pt2Px(500))
//                .setCancelOutside(true)
//                .setViewListener(new BottomDialogFragment.ViewListener() {
//                    @Override
//                    public void bindView(View v) {
//                        TextView textView = v.findViewById(R.id.tv_share);
//                        textView.setText("底部");
//                    }
//                })
//                .show();
    }


    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}
