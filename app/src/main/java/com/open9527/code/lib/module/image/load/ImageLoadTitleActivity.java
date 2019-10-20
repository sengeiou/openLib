package com.open9527.code.lib.module.image.load;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import com.open9527.code.common.databinding.BindingBaseCell;
import com.open9527.code.common.databinding.BindingBaseCellAdapter;
import com.open9527.code.common.databinding.CommonBindingTitleActivity;
import com.open9527.code.common.databinding.interfaces.IBindingCellClickListener;
import com.open9527.code.common.recycleview.decoration.CommonItemDecoration;
import com.open9527.code.image.dragimageview.DraggableImageViewerHelper;
import com.open9527.code.lib.R;
import com.open9527.code.lib.cell.ImageLoadCell;
import com.open9527.code.lib.databinding.ActivityImageloadBinding;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/10 14:26.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class ImageLoadTitleActivity extends CommonBindingTitleActivity<ActivityImageloadBinding> implements IBindingCellClickListener {

    private List<String> list = new LinkedList<>();
    private List<BindingBaseCell> cellList = new LinkedList<>();


    @Override
    public void initData(@Nullable Bundle bundle) {
        list.add("https://github.com/open9527/Images/raw/master/icon/unsplash/cat/1.jpg");
        list.add("https://github.com/open9527/Images/raw/master/icon/unsplash/cat/2.jpg");
        list.add("https://github.com/open9527/Images/raw/master/icon/unsplash/cat/3.jpg");
        list.add("https://github.com/open9527/Images/raw/master/icon/unsplash/cat/4.jpg");
        list.add("https://github.com/open9527/Images/raw/master/icon/unsplash/cat/5.jpg");
        list.add("https://github.com/open9527/Images/raw/master/icon/unsplash/cat/6.jpg");
        list.add("https://github.com/open9527/Images/raw/master/icon/unsplash/cat/7.jpg");
        list.add("https://github.com/open9527/Images/raw/master/icon/unsplash/cat/8.jpg");
        list.add("https://github.com/open9527/Images/raw/master/icon/unsplash/cat/9.jpg");
        list.add("https://github.com/open9527/Images/raw/master/icon/unsplash/cat/10.jpg");
        list.add("https://github.com/open9527/Images/raw/master/icon/unsplash/cat/11.jpg");
        list.add("https://github.com/open9527/Images/raw/master/icon/unsplash/cat/12.jpg");
        list.add("https://github.com/open9527/Images/raw/master/icon/unsplash/cat/13.jpg");

        list.add("http://uploads.5068.com/allimg/1801/84-1P123160546.jpg");
        list.add("http://2t.5068.com/uploads/allimg/150616/1-1506161i627.jpg");
        list.add("http://uploads.5068.com/allimg/141215/39-141215114418.jpg");
        list.add("http://cdnq.duitang.com/uploads/item/201505/13/20150513145543_h4wkj.jpeg");
        list.add("http://uploads.5068.com/allimg/1801/84-1P123160548.jpg");
        list.add("http://uploads.5068.com/allimg/1801/85-1P113101542.jpg");
        list.add("http://www.005.tv/uploads/allimg/160926/14130C552-32.jpg");
        list.add("http://b-ssl.duitang.com/uploads/item/201312/27/20131227232853_fJCmr.jpeg");
        list.add("http://uploads.5068.com/allimg/140716/39_140716171213_1.jpg");
        list.add("http://b-ssl.duitang.com/uploads/item/201207/30/20120730095212_LEexc.jpeg");
        list.add("http://pic.rmb.bdstatic.com/b395e365fc7700f73069e092f464b49b.jpeg");
        list.add("http://omsproductionimg.yangkeduo.com/images/label/610/GS4X5Ojt5TlVPuAtNGqr2hywByGs2FHN.jpg@120w_1l_50Q.webp");
        list.add("https://github.com/open9527/Images/raw/master/upload/adapter.webp");

        for (int i = 0; i < list.size(); i++) {
            final String url = list.get(i);
            cellList.add(new ImageLoadCell(url));
        }

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_imageload;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        mBinding.includeRecycleview.commonRv.setLayoutManager(new GridLayoutManager(this, 3));
        mBinding.includeRecycleview.commonRv.setHasFixedSize(true);
        if (mBinding.includeRecycleview.commonRv.getItemDecorationCount() == 0) {
//            mBinding.includeRecycleview.commonRv.addItemDecoration(new androidx.recyclerview.widget.DividerItemDecoration(this,RecyclerView.HORIZONTAL));
//            mBinding.includeRecycleview.commonRv.addItemDecoration(new androidx.recyclerview.widget.DividerItemDecoration(this,RecyclerView.VERTICAL));
            mBinding.includeRecycleview.commonRv.addItemDecoration(new CommonItemDecoration(this, 3));
        }

        BindingBaseCellAdapter mAdapter = new BindingBaseCellAdapter<>();
        mBinding.includeRecycleview.commonRv.setAdapter(mAdapter);
        mAdapter.setItems(cellList);
        mAdapter.setOnBindingCellClickListener(this);

    }

    @Override
    public void onItemClick(View view, int position, BindingBaseCell... bindingBaseCells) {
        if (bindingBaseCells[0] instanceof ImageLoadCell) {
//            ImageLoadCell imageLoadCell = (ImageLoadCell) bindingBaseCells[0];
            DraggableImageViewerHelper.showImages(mActivity, mBinding.includeRecycleview.commonRv, R.id.iv_images, list, position);
        }
    }
}
