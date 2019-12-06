package com.open9527.code.lib.module.dialog;

import android.os.Bundle;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.utilcode.util.AdaptScreenUtils;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.ViewUtils;
import com.open9527.code.common.databinding.BindingBaseCell;
import com.open9527.code.common.databinding.BindingBaseCellAdapter;
import com.open9527.code.common.databinding.CommonBindingTitleActivity;
import com.open9527.code.common.databinding.interfaces.IBindingCellClickListener;
import com.open9527.code.lib.R;
import com.open9527.code.lib.cell.DialogCell;
import com.open9527.code.lib.databinding.ActivityDialogBinding;
import com.open9527.code.lib.module.dialog.demo.CommonDialogContent;
import com.open9527.code.lib.module.dialog.demo.CommonDialogLoading;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by     : open9527
 * Created times  : on 2019/11/1 17:14.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class DialogActivity extends CommonBindingTitleActivity<ActivityDialogBinding> implements IBindingCellClickListener {
    private List<BindingBaseCell> cellList = new LinkedList<>();
    private BindingBaseCellAdapter mAdapter;
    private CommonDialogLoading mDialogLoading;


    @Override
    public void initData(@Nullable Bundle bundle) {
        cellList.add(new DialogCell("dialog---顶部"));
        cellList.add(new DialogCell("dialog---居中"));
        cellList.add(new DialogCell("dialog---底部"));
        cellList.add(new DialogCell("dialog---CustomDialogFragment"));
        cellList.add(new DialogCell("dialog---showLoading"));
        cellList.add(new DialogCell("dialog---showLoading dosomething"));
        cellList.add(new DialogCell("dialog---dismissLoading"));
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_dialog;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        initAdapter();
    }

    private void initAdapter() {
        mBinding.rvList.setLayoutManager(new LinearLayoutManager(mActivity));
        mBinding.rvList.setHasFixedSize(true);
        mAdapter = new BindingBaseCellAdapter<>();
        mBinding.rvList.setAdapter(mAdapter);
        mAdapter.setItems(cellList);
        mAdapter.setOnBindingCellClickListener(this);
    }

    @Override
    public void onItemClick(View view, int position, BindingBaseCell... bindingBaseCells) {
        DialogCell baseCell = (DialogCell) bindingBaseCells[0];
        if (0 == position) {
            TopDialogFragment.create(getSupportFragmentManager())
                    .setLayoutRes(R.layout.dialog_top)
                    .setWidth(ScreenUtils.getAppScreenWidth())
                    .setHeight(AdaptScreenUtils.pt2Px(500))
                    .setDimAmount(0.5f)
                    .setCancelOutside(true)
                    .setViewListener(new TopDialogFragment.ViewListener() {
                        @Override
                        public void bindView(View v) {
                            TextView textView = v.findViewById(R.id.tv_title);
                            textView.setText("顶部");
                        }
                    })
                    .show();
        } else if (1 == position) {
            CenteredDialogFragment.create(getSupportFragmentManager())
                    .setLayoutRes(R.layout.dialog_center)
                    .setWidth(ScreenUtils.getAppScreenWidth() - AdaptScreenUtils.pt2Px(100f))
                    .setHeight(AdaptScreenUtils.pt2Px(500))
                    .setCancelOutside(true)
                    .setViewListener(new CenteredDialogFragment.ViewListener() {
                        @Override
                        public void bindView(View v) {
                            TextView textView = v.findViewById(R.id.tv_title);
                            textView.setText("居中");
                        }
                    })
                    .show();
        } else if (2 == position) {
            BottomDialogFragment.create(getSupportFragmentManager())
                    .setLayoutRes(R.layout.dialog_bottom)
                    .setHeight(AdaptScreenUtils.pt2Px(500))
                    .setCancelOutside(true)
                    .setViewListener(new BottomDialogFragment.ViewListener() {
                        @Override
                        public void bindView(View v) {
                            TextView textView = v.findViewById(R.id.tv_share);
                            textView.setText("底部");
                        }
                    })
                    .show();
        } else if (3 == position) {
            int[] location = new int[2];
            TextView textView = view.findViewById(R.id.tv_title);
//            textView.getLocationInWindow(location); //获取在当前窗口内的绝对坐标
            textView.getLocationOnScreen(location);//获取在整个屏幕内的绝对坐标
            LogUtils.i(TAG, location[0] + "--->x坐标", location[1] + "--->y坐标");
            CustomDialogFragment.create(getSupportFragmentManager())
                    .setLayoutRes(R.layout.dialog_custom)
                    .setX(location[0])
                    .setY(location[1])
                    .setGravity(Gravity.TOP | Gravity.START)
                    .setWidth(AdaptScreenUtils.pt2Px(500))
                    .setHeight(AdaptScreenUtils.pt2Px(500))
                    .setCancelOutside(true)
                    .setViewListener(new CustomDialogFragment.ViewListener() {
                        @Override
                        public void bindView(View v) {
                            TextView textView = v.findViewById(R.id.tv_title);
                            textView.setText("CustomDialogFragment");
                        }
                    })
                    .show();
        } else if (4 == position) {
            showLoading();
        } else if (5 == position) {
            showLoading(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            });
        } else {

            showCommonDialog();

            dismissLoading();
        }
    }

    public void showLoading() {
        showLoading(null);
    }

    public void showLoading(Runnable listener) {
        if (mDialogLoading != null) {
            dismissLoading();
        }
        mDialogLoading = new CommonDialogLoading().init(this, listener);
        mDialogLoading.show();
    }

    public void dismissLoading() {
        if (mDialogLoading != null) {
            mDialogLoading.dismiss();
            mDialogLoading = null;
        }
    }


    private void showCommonDialog() {
        final Pair<CharSequence, View.OnClickListener> positiveBtnAction = new Pair<>("positiveBtnAction", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.i(TAG,"positiveBtnAction");
            }
        });


        final Pair<CharSequence, View.OnClickListener> negativeBtnAction = new Pair<>("negativeBtnAction", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.i(TAG,"negativeBtnAction");
            }
        });

        new CommonDialogContent().init(this, "", "", positiveBtnAction, negativeBtnAction).show();
    }

}
