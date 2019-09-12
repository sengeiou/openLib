package com.open9527.code.lib.samples.shareelement;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.AdaptScreenUtils;
import com.open9527.code.common.activity.CommonScreenActivity;
import com.open9527.code.common.config.CommonConfig;
import com.open9527.code.lib.R;
import com.open9527.code.shareelement.ShareElementHelper;
import com.open9527.code.shareelement.transition.IShareElementSelector;
import com.open9527.code.shareelement.transition.ShareElementInfo;

import java.util.List;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/6 16:27.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class ShareElementActivity extends AppCompatActivity {


    private ShareElementFragment mFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ShareElementHelper.enableContentTransition(getApplication());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_sub_content);
        mFragment = new ShareElementFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.common_content, mFragment).commit();

    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
        ShareElementHelper.onActivityReenter(this, resultCode, data, new IShareElementSelector() {
            @Override
            public void selectShareElements(List<ShareElementInfo> list) {
                mFragment.selectShareElement(list.get(0));
            }
        });
    }

    @Override
    public Resources getResources() {
        return AdaptScreenUtils.adaptWidth(super.getResources(), CommonConfig.ADAPT_SCREEN_WIDTH);
    }
}
