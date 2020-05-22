package com.open9527.code.lib.module.image.preview;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.open9527.code.common.databinding.CommonBindingTitleActivity;
import com.open9527.code.common.recycleview.GridSpaceItemDecoration;
import com.open9527.code.lib.R;
import com.open9527.code.lib.cell.GalleryCell;
import com.open9527.code.lib.databinding.ActivityGalleryBinding;
import com.open9527.recycleview.adapter.BaseBindingCell;
import com.open9527.recycleview.adapter.BaseBindingCellAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 自定义相册
 */
public class GalleryActivity extends CommonBindingTitleActivity<ActivityGalleryBinding> {

    private BaseBindingCellAdapter<BaseBindingCell> mAdapter;

    @Override
    public int bindLayout() {
        return R.layout.activity_gallery;
    }

    @Override
    public void initData(@Nullable Bundle bundle) {
        super.initData(bundle);
        ThreadUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getAlbum();
            }
        });
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        mBinding.setLayoutManager(new GridLayoutManager(mActivity, 3));
        mBinding.setItemDecoration(getGridSpaceItemDecoration());
        mBinding.setAdapter(mAdapter = new BaseBindingCellAdapter<>());

        List<BaseBindingCell> cellList = new LinkedList<>();
        for (int i = 0; i < mAllPhoto.size(); i++) {
            cellList.add(new GalleryCell(mAllPhoto,mAllPhoto.get(i)));
        }
        mAdapter.addItems(cellList, true);
    }

    private RecyclerView.ItemDecoration getGridSpaceItemDecoration() {
        return new GridSpaceItemDecoration(SizeUtils.dp2px(5), true).setNoShowSpace(0, 0);

    }

    /**
     * 图片专辑
     */
    private final HashMap<String, List<String>> mAllAlbum = new HashMap<>();
    /**
     * 全部图片
     */
    ArrayList<String> mAllPhoto = new ArrayList<>();

    private void getAlbum() {
        final Uri contentUri = MediaStore.Files.getContentUri("external");
        final String sortOrder = MediaStore.Files.FileColumns.DATE_MODIFIED + " DESC";
        final String selection =
                "(" + MediaStore.Files.FileColumns.MEDIA_TYPE + "=?"
                        + " OR "
                        + MediaStore.Files.FileColumns.MEDIA_TYPE + "=?)"
                        + " AND "
                        + MediaStore.MediaColumns.SIZE + ">0";

        final String[] selectionAllArgs = {String.valueOf(MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE)};

        ContentResolver contentResolver = getContentResolver();
        String[] projections;
        projections = new String[]{MediaStore.Files.FileColumns._ID, MediaStore.MediaColumns.DATA,
                MediaStore.MediaColumns.DISPLAY_NAME, MediaStore.MediaColumns.DATE_MODIFIED,
                MediaStore.MediaColumns.MIME_TYPE, MediaStore.MediaColumns.WIDTH, MediaStore
                .MediaColumns.HEIGHT, MediaStore.MediaColumns.SIZE};

        Cursor cursor = contentResolver.query(contentUri, projections, selection, selectionAllArgs, sortOrder);
        if (cursor != null && cursor.moveToFirst()) {

            int pathIndex = cursor.getColumnIndex(MediaStore.MediaColumns.DATA);
            int mimeTypeIndex = cursor.getColumnIndex(MediaStore.MediaColumns.MIME_TYPE);
            int sizeIndex = cursor.getColumnIndex(MediaStore.MediaColumns.SIZE);
            int widthIndex = cursor.getColumnIndex(MediaStore.MediaColumns.WIDTH);
            int heightIndex = cursor.getColumnIndex(MediaStore.MediaColumns.HEIGHT);

            do {
                long size = cursor.getLong(sizeIndex);
                if (size < 1) {
                    continue;
                }

                String type = cursor.getString(mimeTypeIndex);
                String path = cursor.getString(pathIndex);
                if (TextUtils.isEmpty(path) || TextUtils.isEmpty(type)) {
                    continue;
                }

                int width = cursor.getInt(widthIndex);
                int height = cursor.getInt(heightIndex);
                if (width < 1 || height < 1) {
                    continue;
                }

                File file = new File(path);
                if (!file.exists() || !file.isFile()) {
                    continue;
                }

                File parentFile = file.getParentFile();
                if (parentFile != null) {
                    // 获取目录名作为专辑名称
                    String albumName = parentFile.getName();
                    List<String> files = mAllAlbum.get(albumName);
                    if (files == null) {
                        files = new ArrayList<>();
                        mAllAlbum.put(albumName, files);
                    }
                    files.add(path);
                    mAllPhoto.add(path);
                }

            } while (cursor.moveToNext());
            cursor.close();
        }
    }
}
