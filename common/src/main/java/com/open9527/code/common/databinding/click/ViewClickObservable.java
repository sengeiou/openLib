package com.open9527.code.common.databinding.click;

import android.view.View;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

import static com.open9527.code.common.databinding.click.Preconditions.checkMainThread;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/25 16:31.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class ViewClickObservable extends Observable<Object> {
    private final View view;

    ViewClickObservable(View view) {
        this.view = view;
    }

    @Override protected void subscribeActual(Observer<? super Object> observer) {
        if (!checkMainThread(observer)) {
            return;
        }
        Listener listener = new Listener(view, observer);
        observer.onSubscribe(listener);
        view.setOnClickListener(listener);
    }

    static final class Listener extends MainThreadDisposable implements View.OnClickListener {
        private final View view;
        private final Observer<? super Object> observer;

        Listener(View view, Observer<? super Object> observer) {
            this.view = view;
            this.observer = observer;
        }

        @Override public void onClick(View v) {
            if (!isDisposed()) {
                observer.onNext(Notification.INSTANCE);
            }
        }

        @Override protected void onDispose() {
            view.setOnClickListener(null);
        }
    }
}

