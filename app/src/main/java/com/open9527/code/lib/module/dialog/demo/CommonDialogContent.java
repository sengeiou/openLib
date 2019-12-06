package com.open9527.code.lib.module.dialog.demo;

import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ScreenUtils;
import com.open9527.code.common.databinding.BindingBaseCell;
import com.open9527.code.common.databinding.BindingBaseCellAdapter;
import com.open9527.code.common.databinding.interfaces.IBindingCellClickListener;
import com.open9527.code.lib.R;
import com.open9527.code.lib.cell.DialogCell;
import com.open9527.code.lib.module.dialog.common.BaseDialogFragment;
import com.open9527.code.lib.module.dialog.common.DialogLayoutCallback;

import java.util.LinkedList;
import java.util.List;

/**
 * <pre>
 *     author: blankj
 *     blog  : http://blankj.com
 *     time  : 2019/11/18
 *     desc  :
 * </pre>
 */
public class CommonDialogContent extends BaseDialogFragment implements IBindingCellClickListener {


    private RecyclerView recyclerView;
    private Pair<CharSequence, View.OnClickListener> postion0;
    private Pair<CharSequence, View.OnClickListener> postion1;

    public CommonDialogContent init(FragmentActivity activity, final CharSequence title, final CharSequence content,
                                    final Pair<CharSequence, View.OnClickListener> positiveBtnAction,
                                    final Pair<CharSequence, View.OnClickListener> negativeBtnAction) {
        super.init(activity, new DialogLayoutCallback() {
            @Override
            public int bindTheme() {
                return R.style.CommonContentDialogStyle;
            }

            @Override
            public int bindLayout() {
                return R.layout.common_dialog_content;
            }

            @Override
            public void initView(final BaseDialogFragment dialog, View contentView) {
                if (positiveBtnAction != null) postion0 = positiveBtnAction;
                if (negativeBtnAction != null) postion1 = negativeBtnAction;
                recyclerView = contentView.findViewById(R.id.rv_list);
                initAdapter();
            }

            @Override
            public void setWindowStyle(Window window) {
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.gravity = Gravity.BOTTOM;
                attributes.width = ScreenUtils.getAppScreenWidth();
                attributes.height = ScreenUtils.getAppScreenHeight() * 2 / 5;
                attributes.windowAnimations = R.style.BottomDialogAnimation;
                window.setAttributes(attributes);
            }

            @Override
            public void onCancel(BaseDialogFragment dialog) {

            }

            @Override
            public void onDismiss(BaseDialogFragment dialog) {

            }
        });
        return this;
    }

    private List<BindingBaseCell> cellList = new LinkedList<>();
    private BindingBaseCellAdapter<BindingBaseCell> mAdapter;

    private void initAdapter() {
        for (int i = 0; i < 2; i++) {
            cellList.add(new DialogCell("数据" + i));
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        recyclerView.setHasFixedSize(true);
        mAdapter = new BindingBaseCellAdapter<>();
        recyclerView.setAdapter(mAdapter);
        mAdapter.setItems(cellList);
        mAdapter.setOnBindingCellClickListener(this);
    }

    @Override
    public void onItemClick(View view, int position, BindingBaseCell... bindingBaseCells) {
        DialogCell baseCell = (DialogCell) bindingBaseCells[0];
        if (0 == position && postion0 != null) {
            baseCell.observableField.set(postion0.first.toString());
            postion0.second.onClick(view);
        } else if (1 == position && postion1 != null) {
            baseCell.observableField.set(postion1.first.toString());
            postion1.second.onClick(view);
        }
    }
}
