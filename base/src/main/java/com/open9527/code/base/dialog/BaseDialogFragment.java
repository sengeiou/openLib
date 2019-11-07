package com.open9527.code.base.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.open9527.code.base.R;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/27 17:07.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public abstract class BaseDialogFragment extends DialogFragment {

    private static final String TAG = "BaseDialogFragment";
    private static final float DEFAULT_DIM = 0.2f;
    private static Dialog dialog;
    private Local local = Local.BOTTOM;
    private Activity activity;

    public enum Local {
        /**
         * 顶部
         */
        TOP,
        /**
         * 居中
         */
        CENTER,
        /**
         * 底部
         */
        BOTTOM
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (local == Local.BOTTOM) {
            setStyle(DialogFragment.STYLE_NO_TITLE, R.style.BottomDialog);
        } else if (local == Local.CENTER || local == Local.TOP) {
            setStyle(DialogFragment.STYLE_NO_TITLE, R.style.CenterDialog);
        }
    }

    /**
     * 开始展示
     */
    @Override
    public void onStart() {
        super.onStart();
        setDialogGravity();
    }

    /**
     * 销毁弹窗时调用
     */
    @Override
    public void onDestroy() {
        setBgAlpha(1.0f);
        super.onDestroy();
        if (mListener != null) {
            mListener.listener();
        }
        dismissDialog();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dialog = getDialog();
        if (dialog != null) {
            if (dialog.getWindow() != null) {
                dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            }
            dialog.setCanceledOnTouchOutside(isCancel());
            dialog.setCancelable(isCancel());
        }
        View v = inflater.inflate(getLayoutRes(), container, false);
        bindView(v);
        return v;
    }

    /**
     * 该方法的作用主要是：当DialogFragment依附的Activity被创建的时候调用，此时fragment的活动窗体被初始化
     *
     * @param savedInstanceState savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    /**
     * 设置屏幕透明度
     *
     * @param bgAlpha 透明度
     */
    public void setBgAlpha(float bgAlpha) {
        if (activity != null) {
            DialogUtils.setBackgroundAlpha(activity, bgAlpha);
        }
    }


    /**
     * 获取布局资源文件
     *
     * @return 布局资源文件id值
     */
    @LayoutRes
    public abstract int getLayoutRes();

    /**
     * 绑定
     *
     * @param v view
     */
    public abstract void bindView(View v);

    /**
     * 设置是否可以cancel
     *
     * @return
     */
    protected abstract boolean isCancel();


    /**
     * 设置dialog位置
     */
    public void setDialogGravity() {
        if (dialog == null) {
            dialog = getDialog();
        }
        //这个主要是设置弹窗的位置
        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.dimAmount = getDimAmount();

            if (getWidth() > 0) {
                params.width = getWidth();
            } else {
                params.width = WindowManager.LayoutParams.MATCH_PARENT;
            }

            if (getHeight() > 0) {
                params.height = getHeight();
            } else {
                params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            }
            if (local == Local.TOP) {
                params.gravity = Gravity.TOP;
            } else if (local == Local.CENTER) {
                params.gravity = Gravity.CENTER;
            } else {
                params.gravity = Gravity.BOTTOM;
            }
            window.setAttributes(params);
        }
    }

    /**
     * 配置 位置
     *
     * @return
     */
    public int getGravity() {
        return Gravity.TOP;
    }

    /**
     * 获取弹窗高度
     *
     * @return int类型值
     */
    public int getHeight() {
        return -1;
    }

    /**
     * 获取弹窗宽度
     *
     * @return int类型值
     */
    public int getWidth() {
        return -1;
    }

    /**
     * 距离x 轴
     *
     * @return
     */
    public float getX() {
        return 0;
    }

    /**
     * 距离Y 轴
     *
     * @return
     */
    public float getY() {
        return 0;
    }


    public float getDimAmount() {
        return DEFAULT_DIM;
    }

    public String getFragmentTag() {
        return TAG;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public void show(FragmentManager fragmentManager) {
        DialogUtils.checkMainThread();
        if (fragmentManager != null) {
            //show(fragmentManager, getFragmentTag());

            //主要是为了解决Can not perform this action after onSaveInstanceState异常
            //发生场景：Activity即将被销毁，再给它添加Fragment就会出错。
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.add(this, getFragmentTag());
            ft.commitAllowingStateLoss();
        } else {
            throw new NullPointerException("Need to set FragmentManager");
        }
    }

    //一定要销毁dialog，设置为null，防止内存泄漏
    public static void dismissDialog() {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        dialog = null;
    }


    public onFinishListener mListener;

    public void setFinishListener(onFinishListener listener) {
        mListener = listener;
    }

    public interface onFinishListener {
        /**
         * 监听
         */
        void listener();
    }

}