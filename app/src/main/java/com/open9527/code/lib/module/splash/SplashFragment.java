package com.open9527.code.lib.module.splash;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.open9527.code.common.databinding.CommonBindingFragment;
import com.open9527.code.lib.MainViewModel;
import com.open9527.code.lib.R;
import com.open9527.code.lib.databinding.FragmentSplashBinding;
import com.open9527.code.lib.utils.ImageLoadUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/16 17:25.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class SplashFragment extends CommonBindingFragment<FragmentSplashBinding> {


    private CompositeDisposable mCompositeDisposable;
    private static final int time = 5;
    private SplashFragment fragment;
    private MainViewModel mViewModel;

    @Override
    public void initData(@Nullable Bundle bundle) {
        if (getActivity() != null) {
            mViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        }
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_splash;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        mTitleGroup.setVisibility(View.GONE);
        fragment = this;
        ImageLoadUtils.imageLoad(mActivity,mBinding.ivAd,"https://bs.storage.shmedia.tech/1613346.png?imageslim");

        mCompositeDisposable = new CompositeDisposable();
        mCompositeDisposable.add(getObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getDisposableObserver()));
        mBinding.tvTime.setOnClickListener(view -> removeFragment());
    }


    @Override
    public void doLazyBusiness() {

    }


    /**
     * 接收
     *
     * @return
     */

    private Observable<? extends Long> getObservable() {
        return Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(time + 1)
                .map(aLong -> time - aLong);
    }

    /**
     * 观察者
     *
     * @return
     */
    private DisposableObserver<Long> getDisposableObserver() {
        return new DisposableObserver<Long>() {
            @Override
            public void onNext(Long aLong) {
//                LogUtils.i(TAG, "onNext-->" + aLong);
                mBinding.tvTime.setText(String.valueOf(aLong + "跳过"));
            }

            @Override
            public void onError(Throwable throwable) {
                removeFragment();
            }

            @Override
            public void onComplete() {
                removeFragment();
            }
        };
    }

    private void removeFragment() {
        mViewModel.closeSplashEvent.postValue(null);
        FragmentActivity activity = (FragmentActivity) mActivity;
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.remove(fragment);
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
    }
}
