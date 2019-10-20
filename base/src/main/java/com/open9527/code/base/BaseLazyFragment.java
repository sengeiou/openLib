package com.open9527.code.base;


import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;

/**
 * Created by     : open9527
 * Created times  : on 2019/8/22 17:35.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public abstract class BaseLazyFragment extends BaseFragment {


    private boolean isDataLoaded;

    public abstract void doLazyBusiness();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && mContentView != null && !isDataLoaded) {
            doLazyBusiness();
            isDataLoaded = true;
        }
    }


    @Override
    public void doBusiness() {
//        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//        //onCreate
//        fragmentTransaction.setMaxLifecycle(this, Lifecycle.State.INITIALIZED);
//        //onCreate(onStop-->onCreate)
//        fragmentTransaction.setMaxLifecycle(this, Lifecycle.State.CREATED);
//        //onStart(onPause-->onStart)
//        fragmentTransaction.setMaxLifecycle(this, Lifecycle.State.STARTED);
//        //onResume();
//        fragmentTransaction.setMaxLifecycle(this, Lifecycle.State.RESUMED);
//        //onDestroy()
//        fragmentTransaction.setMaxLifecycle(this, Lifecycle.State.DESTROYED);
        if (getUserVisibleHint() && !isDataLoaded) {
            doLazyBusiness();
            isDataLoaded = true;
        }
    }
}
