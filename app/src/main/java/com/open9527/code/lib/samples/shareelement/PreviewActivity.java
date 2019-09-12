package com.open9527.code.lib.samples.shareelement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.open9527.code.common.activity.CommonScreenActivity;
import com.open9527.code.common.activity.CommonTitleActivity;
import com.open9527.code.common.recycleview.holder.ItemViewHolder;
import com.open9527.code.image.imageload.ImageLoadConfig;
import com.open9527.code.image.imageload.ImageLoadManger;
import com.open9527.code.image.imageload.ImageLoadProcessInterface;
import com.open9527.code.lib.R;
import com.open9527.code.lib.utils.CommonUtils;
import com.open9527.code.shareelement.ShareElementHelper;
import com.open9527.code.shareelement.transition.IShareElements;
import com.open9527.code.shareelement.transition.ShareElementInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/4 17:32.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class PreviewActivity extends CommonScreenActivity implements IShareElements {


    private PreviewFragment mFragment;

    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.common_sub_content;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        ShareElementHelper.setEnterTransitions(this, this, true);
        mFragment = new PreviewFragment();
        mFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.common_content, mFragment).commit();
    }

    @Override
    public void finishAfterTransition() {
        ShareElementHelper.finishAfterTransition(this, this);
        super.finishAfterTransition();
    }

    @Override
    public ShareElementInfo[] getShareElements() {
        return mFragment.getShareElements();
    }


    @Override
    public void doBusiness() {

    }

    @Override
    public void onDebouncingClick(@NonNull View view) {

    }

}
