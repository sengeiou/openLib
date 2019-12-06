package com.open9527.code.lib.module.customview;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.AdaptScreenUtils;
import com.blankj.utilcode.util.LogUtils;
import com.google.android.material.tabs.TabLayout;
import com.open9527.code.common.databinding.CommonBindingTitleActivity;
import com.open9527.code.common.pageadapter.CommonPagerAdapter;
import com.open9527.code.lib.R;
import com.open9527.code.lib.databinding.ActivityCustomTablayoutBinding;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/17 9:58.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class TabLayoutTitleActivity extends CommonBindingTitleActivity<ActivityCustomTablayoutBinding> {
    private String mTitles[] = {"超多个文字", "体育", "段子", "美食", "电影", "科技", "搞笑", "多个文字", "超多个文字"};

    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_custom_tablayout;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        initTabView();
    }


    private void initTabView() {
        for (int i = 0; i < mTitles.length; i++) {
            final View view = LayoutInflater.from(mActivity).inflate(R.layout.item_tabview, null, false);
            final TextView title = view.findViewById(R.id.tv_title);
            title.setText(mTitles[i]);
            if (title.getText().equals("体育")) {
                Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
                drawable.setBounds(0, 0, AdaptScreenUtils.pt2Px(40), AdaptScreenUtils.pt2Px(40));// 设置边界
                title.setCompoundDrawables(drawable, null, null, null);
            }
            mBinding.tabLayout.addTab(mBinding.tabLayout.newTab().setCustomView(view));
        }
        setTabLayoutSelected(mBinding.tabLayout.getTabAt(0), getResources().getColor(R.color.colorAccent), View.VISIBLE);
        mBinding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                LogUtils.i(TAG, "选中");
                setTabLayoutSelected(tab, getResources().getColor(R.color.colorAccent), View.VISIBLE);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                LogUtils.i(TAG, "未选中");
                setTabLayoutSelected(tab, getResources().getColor(R.color.color_333), View.INVISIBLE);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                LogUtils.i(TAG, "再次选中");
                setTabLayoutSelected(tab, getResources().getColor(R.color.colorAccent), View.VISIBLE);
            }
        });



    }

    /**
     * 配置选中/未选中状态
     *
     * @param tab
     * @param color
     * @param visibility
     */
    private void setTabLayoutSelected(TabLayout.Tab tab, int color, int visibility) {
        TextView title = tab.getCustomView().findViewById(R.id.tv_title);
        View view = tab.getCustomView().findViewById(R.id.selected_view);
        title.setTextColor(color);
        view.setVisibility(visibility);
    }
}
