package com.open9527.code.lib.module.dialog.demo;

import android.view.View;
import android.view.Window;

import androidx.fragment.app.FragmentActivity;

import com.open9527.code.lib.R;
import com.open9527.code.lib.module.dialog.common.BaseDialogFragment;
import com.open9527.code.lib.module.dialog.common.DialogLayoutCallback;


/**
 * <pre>
 *     author: blankj
 *     blog  : http://blankj.com
 *     time  : 2019/10/29
 *     desc  :
 * </pre>
 */
public class CommonDialogLoading extends BaseDialogFragment {

    public CommonDialogLoading init(FragmentActivity activity, final Runnable onCancelListener) {
        super.init(activity, new DialogLayoutCallback() {
            @Override
            public int bindTheme() {
                return R.style.CommonLoadingDialogStyle;
            }

            @Override
            public int bindLayout() {
                return R.layout.common_dialog_loading;
            }

            @Override
            public void initView(BaseDialogFragment dialog, View contentView) {
                if (onCancelListener == null) {
                    setCancelable(false);
                } else {
                    setCancelable(true);
                }
            }

            @Override
            public void setWindowStyle(Window window) {
            }

            @Override
            public void onCancel(BaseDialogFragment dialog) {
                if (onCancelListener != null) {
                    onCancelListener.run();
                }
            }

            @Override
            public void onDismiss(BaseDialogFragment dialog) {

            }
        });
        return this;
    }
}
