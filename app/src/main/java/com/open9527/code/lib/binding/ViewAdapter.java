package com.open9527.code.lib.binding;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ClickUtils;
import com.open9527.code.image.imageload.ImageLoadConfig;
import com.open9527.code.image.imageload.ImageLoadManger;
import com.open9527.code.lib.R;
import com.open9527.recycleview.adapter.BaseBindingCellAdapter;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/21 19:43.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class ViewAdapter {
    private static final String TAG = "ViewAdapter";


    /*********************************************TextView*********************************************************/
    /**
     * 配置text 文本,颜色
     *
     * @param textView
     * @param text
     * @param textColorRes
     */
    @BindingAdapter(value = {"bindText", "bindTextColor"}, requireAll = false)
    public static void setBindText(TextView textView, String text, int textColorRes) {
        textView.setText(text != null ? text : "");
        if (textColorRes > 0) {
            textView.setTextColor(ContextCompat.getColor(textView.getContext(), textColorRes));
        }
    }
    /*********************************************TextView*********************************************************/


    /*********************************************EditText*********************************************************/
    /**
     * EditText 文本,颜色
     *
     * @param editText
     * @param text
     * @param textColorRes
     */
    @BindingAdapter(value = {"bindEditText", "bindEditTextColor"}, requireAll = false)
    public static void setBindEditText(EditText editText, String text, int textColorRes) {
        editText.setText(text);
        if (textColorRes > 0) {
            editText.setTextColor(ContextCompat.getColor(editText.getContext(), textColorRes));
        }
    }

    /*********************************************EditText*********************************************************/


    /*********************************************View*********************************************************/
    //@{@drawable/ic_launcher_background}
    @BindingAdapter(value = {"bindViewBgColor", "bindViewBgDrawable"}, requireAll = false)
    public static void setBindViewBgColor(View view, int colorRes, Drawable drawableRes) {
        if (colorRes > 0) {
            view.setBackgroundColor(ContextCompat.getColor(view.getContext(), colorRes));
        }
        if (drawableRes != null) {
            view.setBackground(drawableRes);
        }
    }


    /*********************************************View*********************************************************/


    /*********************************************ImageView*********************************************************/
    /**
     * 配置ImageView
     *
     * @param imageView
     * @param imageRes
     */
    @BindingAdapter(value = {"bindImageRes"}, requireAll = false)
    public static void setBindImageRes(ImageView imageView, int imageRes) {
        imageView.setImageResource(imageRes);
    }

    /**
     * 配置ImageView 网络图片
     *
     * @param imageView
     * @param url
     * @param bindImageRadius
     */
    @BindingAdapter(value = {"bindImageUrl", "bindImageRadius", "bindImagePlaceholder"}, requireAll = false)
    public static void setBindImage(ImageView imageView, Object url, int bindImageRadius, int bindImagePlaceholder) {
        ImageLoadManger.display(imageView.getContext(), imageView, url, new ImageLoadConfig(0, bindImagePlaceholder, bindImageRadius), null);
    }


    /**
     * 配置ImageView 显示/隐藏
     *
     * @param view
     * @param showDrawable
     * @param drawableShowed
     */
    @BindingAdapter(value = {"bindShowDrawable", "bindDrawableShowed"}, requireAll = false)
    public static void setBindShowDrawable(ImageView view, boolean showDrawable, int drawableShowed) {
        view.setImageResource(showDrawable ? drawableShowed : R.color.color_transparent);
    }


    /*********************************************ImageView*********************************************************/


    /*********************************************View*********************************************************/


    /**
     * 配置view 显示/隐藏
     *
     * @param view
     * @param visible
     */
    @BindingAdapter(value = {"bindVisible"}, requireAll = false)
    public static void setBindVisible(View view, boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    /*********************************************View*********************************************************/


    /*********************************************OnClickListener*********************************************************/


    /**
     * 配置view的点击事件
     *
     * @param view
     * @param debounce        默认处理防抖
     * @param viewScale       应用点击后对视图缩放
     * @param viewAlpha       应用点击后对视图改变透明度
     * @param bgAlpha         应用点击后对背景改变透明度
     * @param bgDark          应用点击后对背景加深
     * @param onClickListener
     */
    @SuppressLint("CheckResult")
    @BindingAdapter(value = {"onBindViewClick", "bindClickDebounce", "bindClickViewScale", "bindClickViewAlpha", "bindClickBgAlpha", "bindClickBgDark"}, requireAll = false)
    public static void setBindingClick(View view, View.OnClickListener onClickListener,
                                       boolean debounce,
                                       boolean viewScale,
                                       boolean viewAlpha,
                                       boolean bgAlpha,
                                       boolean bgDark) {
        if (viewScale) ClickUtils.applyPressedViewScale(view);
        if (viewAlpha) ClickUtils.applyPressedViewAlpha(view);
        if (bgAlpha) ClickUtils.applyPressedBgAlpha(view);
        if (bgDark) ClickUtils.applyPressedBgDark(view);
        if (debounce) {
            view.setOnClickListener(onClickListener);
        } else {
            ClickUtils.applyGlobalDebouncing(view, onClickListener);
        }
    }
    /*********************************************OnClickListener*********************************************************/


    /*********************************************RecycleViewer*********************************************************/
    /**
     * 配置RecycleViewAdapter
     *
     * @param recyclerView
     * @param mAdapter
     */

    /**
     * 配置RecycleViewAdapter
     *
     * @param recyclerView
     * @param mAdapter
     * @param layoutManager
     * @param itemDecoration
     */
    @BindingAdapter(value = {"bindRvAdapter", "bindRvLayoutManager", "bindRvItemDecoration", "bindRvHasFixedSize"}, requireAll = false)
    public static void setBindingAdapter(RecyclerView recyclerView,
                                         BaseBindingCellAdapter mAdapter,
                                         RecyclerView.LayoutManager layoutManager,
                                         RecyclerView.ItemDecoration itemDecoration, boolean hasFixedSize) {
        recyclerView.setHasFixedSize(hasFixedSize);
        if (layoutManager != null) {
            recyclerView.setLayoutManager(layoutManager);
        }
        if (itemDecoration != null) {
            if (recyclerView.getItemDecorationCount() == 0) {
                recyclerView.addItemDecoration(itemDecoration);
            }
        }
        if (mAdapter != null) {
            recyclerView.setAdapter(mAdapter);
        }
    }

    /*********************************************RecycleViewer*********************************************************/


    /*********************************************SmartRefreshLayout*********************************************************/

//    @BindingAdapter(value = {"bindRefresh"}, requireAll = false)
//    public static void setBindRefresh(SmartRefreshLayout smartRefreshLayout, IRefresh<Boolean> iRefresh) {
//        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
//            @Override
//            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//                iRefresh.loadComplete(false);
//            }
//
//            @Override
//            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
//                refreshLayout.resetNoMoreData();
//                iRefresh.loadComplete(true);
//            }
//        });
//    }
    /*********************************************SmartRefreshLayout*********************************************************/
}
