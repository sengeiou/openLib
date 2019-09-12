package com.open9527.code.lib.samples.recycleview.multiple;

import android.view.Gravity;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.open9527.code.common.recycleview.BaseCell;
import com.open9527.code.common.recycleview.holder.ItemViewHolder;
import com.open9527.code.customview.AdaptScreenUtils;
import com.open9527.code.customview.banner.MarqueeView;
import com.open9527.code.lib.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/12 11:06.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class MarqueeCell extends BaseCell<MarqueeCell> {

    private ArrayList<String> list;

    public MarqueeCell(ArrayList<String> list) {
        super(R.layout.item_marquee);
        this.list = list;
    }

    @Override
    public void bind(@NonNull ItemViewHolder holder, int position) {
        MarqueeView marqueeView = holder.findViewById(R.id.marquee_view);
        marqueeView.setNotices(list);
        marqueeView.setTextColor(ColorUtils.getColor(R.color.color_333));
        marqueeView.setTextSize(14);
        marqueeView.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
        marqueeView.setSingleLine(true);
        marqueeView.setOnItemClickListener((position1, textView) -> {
            ToastUtils.showShort(textView.getText().toString());
        });
        marqueeView.start();
    }
}
