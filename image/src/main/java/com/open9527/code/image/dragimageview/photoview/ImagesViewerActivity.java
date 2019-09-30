package com.open9527.code.image.dragimageview.photoview;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.open9527.code.image.R;
import com.open9527.code.image.dragimageview.entities.DraggableImageInfo;
import com.open9527.code.image.dragimageview.view.DraggableImageGalleryViewer;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by     : open9527
 * Created times  : on 2019/9/29 16:39.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class ImagesViewerActivity extends AppCompatActivity {
    private static final String PARAMS = "draggableImages";
    private static final String INDEX = "index";
    private DraggableImageGalleryViewer mGalleryViewer;

    public static void start(Context context, ArrayList<DraggableImageInfo> draggableImages, int index) {
        Intent intent = new Intent(context, ImagesViewerActivity.class);
        intent.putExtra(PARAMS, draggableImages);
        intent.putExtra(INDEX, index);
        context.startActivity(intent);
        if (context instanceof Activity) {
            ((Activity) context).overridePendingTransition(0, 0);
        }
    }

    /**
     * 使状态栏透明
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    void transparentStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_activity);
        transparentStatusBar(this);
        initView();
        initData();
    }

    private void initData() {
        List<DraggableImageInfo> draggableImages = (List<DraggableImageInfo>) getIntent().getSerializableExtra(PARAMS);
        int index = getIntent().getIntExtra(INDEX, 0);
        if (null != draggableImages && draggableImages.size() > 0) {
            mGalleryViewer.showImagesWithAnimator(draggableImages, index);
        }
    }

    private void initView() {
        mGalleryViewer = findViewById(R.id.root_view);
        mGalleryViewer.setActionListener(new DraggableImageGalleryViewer.ActionListener() {
            @Override
            public void closeViewer() {
                finish();
                overridePendingTransition(0, 0);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (!mGalleryViewer.closeWithAnimator()) {
            super.onBackPressed();
        }
    }
}
