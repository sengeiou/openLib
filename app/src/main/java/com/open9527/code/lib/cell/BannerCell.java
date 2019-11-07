package com.open9527.code.lib.cell;

import android.database.Observable;
import android.util.DisplayMetrics;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
import com.open9527.code.common.databinding.BindingBaseCell;
import com.open9527.code.common.databinding.BindingBaseCellAdapter;
import com.open9527.code.common.databinding.BindingItemViewHolder;
import com.open9527.code.common.databinding.click.BindingAction;
import com.open9527.code.common.databinding.click.BindingClick;
import com.open9527.code.lib.R;
import com.open9527.code.lib.databinding.ItemCellBannerBinding;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/30 14:27.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class BannerCell extends BindingBaseCell<BannerCell> {

    private final DisplayMetrics displayMetrics;
    private float itemWidth = 0.8f;
    private float ratio = 0.5f;  // 宽高比

    public ObservableField<String> urlObservableField = new ObservableField<>();
    public ObservableField<String> titleObservableField = new ObservableField<>();

    public BannerCell(String url, String title) {
        super(R.layout.item_cell_banner);
        urlObservableField.set(url);
        titleObservableField.set(title);
        displayMetrics = Utils.getApp().getResources().getDisplayMetrics();
    }

    @Override
    public void bind(@NonNull BindingItemViewHolder holder, int position) {
        ItemCellBannerBinding binding = (ItemCellBannerBinding) holder.mBinding;
        binding.setCell(this);
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.width = (int) (displayMetrics.widthPixels * itemWidth);
        layoutParams.height = (int) (layoutParams.width * ratio);
    }

    public BindingClick bannerClick = new BindingClick(new BindingAction() {
        @Override
        public void call() {
            ToastUtils.showShort(titleObservableField.get());
        }
    });


}
