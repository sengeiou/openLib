package com.open9527.code.lib.module.dialog.common;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.blankj.utilcode.util.Utils;

/**
 * <pre>
 *     author: blankj
 *     blog  : http://blankj.com
 *     time  : 2019/11/11
 *     desc  :
 * </pre>
 */
public class BaseDialogFragment extends DialogFragment {

    protected DialogLayoutCallback mDialogLayoutCallback;
    protected DialogCallback       mDialogCallback;

    protected FragmentActivity mActivity;
    protected View mContentView;

    public BaseDialogFragment init(FragmentActivity activity, DialogLayoutCallback listener) {
        mActivity = activity;
        mDialogLayoutCallback = listener;
        return this;
    }

    public BaseDialogFragment init(FragmentActivity activity, DialogCallback dialogCallback) {
        mActivity = activity;
        mDialogCallback = dialogCallback;
        return this;
    }

    @Override
    public int getTheme() {
        if (mDialogLayoutCallback != null) {
            int theme = mDialogLayoutCallback.bindTheme();
            if (theme != View.NO_ID) {
                return theme;
            }
        }
        return super.getTheme();
    }

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (mDialogCallback != null) {
            return mDialogCallback.bindDialog(mActivity);
        }
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mDialogLayoutCallback != null) {
            return inflater.inflate(mDialogLayoutCallback.bindLayout(), container, false);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (mDialogLayoutCallback != null) {
            mDialogLayoutCallback.initView(this, view);
            return;
        }
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog == null) return;
        Window window = dialog.getWindow();
        if (window == null) return;
        if (mDialogCallback != null) {
            mDialogCallback.setWindowStyle(window);
        } else if (mDialogLayoutCallback != null) {
            mDialogLayoutCallback.setWindowStyle(window);
        }
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        if (mDialogLayoutCallback != null) {
            mDialogLayoutCallback.onCancel(this);
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (mDialogLayoutCallback != null) {
            mDialogLayoutCallback.onDismiss(this);
        }
    }

    public void show() {
        show(getClass().getSimpleName());
    }

    public void show(final String tag) {
        ThreadUtils.runOnUiThread(new Runnable() {
            @SuppressLint("CommitTransaction")
            @Override
            public void run() {
                if (ActivityUtils.isActivityAlive(mActivity)) {
                    FragmentManager fm = mActivity.getSupportFragmentManager();
                    Fragment prev = fm.findFragmentByTag(tag);
                    if (prev != null) {
                        fm.beginTransaction().remove(prev);
                    }
                    BaseDialogFragment.super.showNow(fm, tag);
                }
            }
        });
    }

    @Override
    public void dismiss() {
        ThreadUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (ActivityUtils.isActivityAlive(mActivity)) {
                    BaseDialogFragment.super.dismissAllowingStateLoss();
                }
            }
        });
    }
}


