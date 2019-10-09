package com.open9527.code.image.dragimageview;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.open9527.code.image.dragimageview.core.ImageInfo;
import com.open9527.code.image.dragimageview.photoview.ImagesViewerActivity;
import com.open9527.code.image.dragimageview.entities.DraggableImageInfo;
import com.open9527.code.image.dragimageview.entities.DraggableParamsInfo;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by     : open9527
 * Created times  : on 2019/9/29 16:39.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class DraggableImageViewerHelper {


    /**
     * 查看 单张
     *
     * @param context
     * @param view
     * @param info
     * @param index
     */
    public static void showImages(Context context, View view, ImageInfo info, int index) {
        if (null == info) {
            return;
        }
        ArrayList<DraggableImageInfo> draggableImageInfos = new ArrayList<>();
        if (view != null) {
            draggableImageInfos.add(createImageDraggableParamsWithWHRadio(view, info.getOriginUrl(), info.getThumbnailUrl(), info.getImgSize()));
        } else {
            draggableImageInfos.add(createImageDraggableParamsWithWHRadio(null, info.getOriginUrl(), info.getThumbnailUrl(), info.getImgSize()));
        }
        ImagesViewerActivity.start(context, draggableImageInfos, index);
    }


    /**
     * 查看多张
     *
     * @param context
     * @param views
     * @param imageInfos
     * @param index
     */
    public static void showImages(Context context, List<View> views, List<ImageInfo> imageInfos, int index) {
        if (null == imageInfos || imageInfos.size() == 0) {
            return;
        }
        ArrayList<DraggableImageInfo> draggableImageInfos = new ArrayList<>();
        for (int i = 0; i < imageInfos.size(); i++) {
            ImageInfo info = imageInfos.get(i);
            if (views != null && i < views.size()) {
                draggableImageInfos.add(createImageDraggableParamsWithWHRadio(views.get(i), info.getOriginUrl(), info.getThumbnailUrl(), info.getImgSize()));
            } else {
                draggableImageInfos.add(createImageDraggableParamsWithWHRadio(null, info.getOriginUrl(), info.getThumbnailUrl(), info.getImgSize()));
            }
        }
        ImagesViewerActivity.start(context, draggableImageInfos, index);
    }

    /**
     * 多张(RecycleView)
     *
     * @param context
     * @param recycleView
     * @param imageInfos
     * @param index
     */

    public static void showImages(Context context, RecyclerView recycleView, int ivIds, List<ImageInfo> imageInfos, int index) {
        if (null == imageInfos || imageInfos.size() == 0) {
            return;
        }
        List<View> views = new ArrayList<>();
        int childCount = recycleView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = recycleView.getChildAt(i).findViewById(ivIds);
            views.add(view);
        }
        showImages(context, views, imageInfos, index);
    }


    /**
     * 根据宽高比，显示一张图片
     */
    private static DraggableImageInfo createImageDraggableParamsWithWHRadio(View view, String originUrl, String thumbUrl, long imgSize) {
        DraggableImageInfo draggableInfo;
        if (view != null) {
            int[] location = new int[2];
            view.getLocationInWindow(location);
            Rect windowRect = new Rect();
            view.getWindowVisibleDisplayFrame(windowRect);
            int top = location[1];
            draggableInfo = new DraggableImageInfo(originUrl,
                    thumbUrl,
                    new DraggableParamsInfo(
                            location[0],
                            top,
                            view.getWidth(),
                            view.getHeight()
                    ),
                    imgSize);
        } else {
            draggableInfo = new DraggableImageInfo(originUrl, thumbUrl, imgSize);
        }
        draggableInfo.adjustImageUrl();
        return draggableInfo;
    }
}
