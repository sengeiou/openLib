package com.open9527.code.image.dragimageview;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
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
     * @param url
     * @param index
     */
    public static void showImages(Context context, View view, String url, int index) {
        if (null == url || TextUtils.isEmpty(url)) {
            return;
        }
        ImageInfo imageInfo = new ImageInfo(url);
        ArrayList<DraggableImageInfo> draggableImageInfos = new ArrayList<>();
        if (view != null) {
            draggableImageInfos.add(createImageDraggableParamsWithWHRadio(view, imageInfo.getOriginUrl(), imageInfo.getThumbnailUrl(), imageInfo.getImgSize()));
        } else {
            draggableImageInfos.add(createImageDraggableParamsWithWHRadio(null, imageInfo.getOriginUrl(), imageInfo.getThumbnailUrl(), imageInfo.getImgSize()));
        }
        ImagesViewerActivity.start(context, draggableImageInfos, index);
    }


    /**
     * 查看多张
     *
     * @param context
     * @param views
     * @param urlList
     * @param index
     */
    public static void showImages(Context context, List<View> views, List<String> urlList, int index) {
        if (null == urlList || urlList.size() == 0) {
            return;
        }
        List<ImageInfo> imageInfoList = getImageInfoList(urlList);
        ArrayList<DraggableImageInfo> draggableImageInfos = new ArrayList<>();
        for (int i = 0; i < imageInfoList.size(); i++) {
            final ImageInfo info = imageInfoList.get(i);
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
     * @param urlList
     * @param index
     */

    public static void showImages(Context context, RecyclerView recycleView, int ivIds, List<String> urlList, int index) {
        if (null == urlList || urlList.size() == 0) {
            return;
        }
        List<View> views = new ArrayList<>();
        int childCount = recycleView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = recycleView.getChildAt(i).findViewById(ivIds);
            views.add(view);
        }
        showImages(context, views, urlList, index);
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

    /**
     * 创建 List<ImageInfo> 集合
     *
     * @param urlList
     * @return
     */
    private static List<ImageInfo> getImageInfoList(List<String> urlList) {
        List<ImageInfo> result = new ArrayList<>();
        for (int i = 0; i < urlList.size(); i++) {
            final String url = urlList.get(i);
            result.add(new ImageInfo(url));
        }
        return result;
    }
}
