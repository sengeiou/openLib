package com.open9527.code.common.databinding.click;

import android.os.Looper;

import androidx.annotation.RestrictTo;

import io.reactivex.Observer;

import static androidx.annotation.RestrictTo.Scope.LIBRARY_GROUP;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/27 10:44.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
@RestrictTo(LIBRARY_GROUP)
public final class Preconditions {
    public static void checkNotNull(Object value, String message) {
        if (value == null) {
            throw new NullPointerException(message);
        }
    }

    public static boolean checkMainThread(Observer<?> observer) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            observer.onError(new IllegalStateException(
                    "Expected to be called on the main thread but was " + Thread.currentThread().getName()));
            return false;
        }
        return true;
    }

    private Preconditions() {
        throw new AssertionError("No instances.");
    }
}
