package com.open9527.code.lib.samples.adapter.cell;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.blankj.utilcode.util.ActivityUtils;
import com.open9527.code.common.recycleview.BaseItem;
import com.open9527.code.common.recycleview.ItemViewHolder;
import com.open9527.code.lib.R;
import com.open9527.code.lib.samples.shareelement.PreviewActivity;
import com.open9527.code.lib.utils.CommonUtils;

import java.util.ArrayList;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/4 17:57.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class NinePicCell extends BaseItem<NinePicCell> {

    public String url;

    public NinePicCell(int layout, String url) {
        super(layout);
        this.url = url;
    }

    @Override
    public void bind(@NonNull ItemViewHolder holder, int position) {
        ImageView icon = holder.findViewById(R.id.iv_photo);
        CommonUtils.imageLoad(holder.itemView.getContext(), icon, url, ImageView.ScaleType.FIT_XY);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= 22) {
                    share(icon, position);
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void share(View view, int position) {
        Intent intent = new Intent(view.getContext(), PreviewActivity.class);
        intent.putStringArrayListExtra("share_list", new ArrayList<>());
        intent.putExtra("share_position", position);
        Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(ActivityUtils.getTopActivity(), view, "share").toBundle();
        startActivity(intent, bundle);
    }
}
