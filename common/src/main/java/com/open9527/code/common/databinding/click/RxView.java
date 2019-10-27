package com.open9527.code.common.databinding.click;

import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

import static com.open9527.code.common.databinding.click.Preconditions.checkNotNull;
import static android.os.Build.VERSION_CODES.JELLY_BEAN;
import static android.os.Build.VERSION_CODES.M;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/27 10:49.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public final class RxView {


    /**
     * Create an observable which emits on {@code view} click events. The emitted value is
     * unspecified and should only be used as notification.
     * <p>
     * <em>Warning:</em> The created observable keeps a strong reference to {@code view}. Unsubscribe
     * to free this reference.
     * <p>
     * <em>Warning:</em> The created observable uses {@link View#setOnClickListener} to observe
     * clicks. Only one observable can be used for a view at a time.
     */
    @CheckResult @NonNull
    public static Observable<Object> clicks(@NonNull View view) {
        checkNotNull(view, "view == null");
        return new ViewClickObservable(view);
    }


    /**
     * An action which sets the activated property of {@code view}.
     * <p>
     * <em>Warning:</em> The created observable keeps a strong reference to {@code view}. Unsubscribe
     * to free this reference.
     */
    @CheckResult @NonNull
    public static Consumer<? super Boolean> activated(@NonNull final View view) {
        checkNotNull(view, "view == null");
        return new Consumer<Boolean>() {
            @Override public void accept(Boolean value) {
                view.setActivated(value);
            }
        };
    }

    /**
     * An action which sets the clickable property of {@code view}.
     * <p>
     * <em>Warning:</em> The created observable keeps a strong reference to {@code view}. Unsubscribe
     * to free this reference.
     */
    @CheckResult @NonNull
    public static Consumer<? super Boolean> clickable(@NonNull final View view) {
        checkNotNull(view, "view == null");
        return new Consumer<Boolean>() {
            @Override public void accept(Boolean value) {
                view.setClickable(value);
            }
        };
    }

    /**
     * An action which sets the enabled property of {@code view}.
     * <p>
     * <em>Warning:</em> The created observable keeps a strong reference to {@code view}. Unsubscribe
     * to free this reference.
     */
    @CheckResult @NonNull
    public static Consumer<? super Boolean> enabled(@NonNull final View view) {
        checkNotNull(view, "view == null");
        return new Consumer<Boolean>() {
            @Override public void accept(Boolean value) {
                view.setEnabled(value);
            }
        };
    }

    /**
     * An action which sets the pressed property of {@code view}.
     * <p>
     * <em>Warning:</em> The created observable keeps a strong reference to {@code view}. Unsubscribe
     * to free this reference.
     */
    @CheckResult @NonNull
    public static Consumer<? super Boolean> pressed(@NonNull final View view) {
        checkNotNull(view, "view == null");
        return new Consumer<Boolean>() {
            @Override public void accept(Boolean value) {
                view.setPressed(value);
            }
        };
    }

    /**
     * An action which sets the selected property of {@code view}.
     * <p>
     * <em>Warning:</em> The created observable keeps a strong reference to {@code view}. Unsubscribe
     * to free this reference.
     */
    @CheckResult @NonNull
    public static Consumer<? super Boolean> selected(@NonNull final View view) {
        checkNotNull(view, "view == null");
        return new Consumer<Boolean>() {
            @Override public void accept(Boolean value) {
                view.setSelected(value);
            }
        };
    }

    /**
     * An action which sets the visibility property of {@code view}. {@code false} values use
     * {@code View.GONE}.
     * <p>
     * <em>Warning:</em> The created observable keeps a strong reference to {@code view}. Unsubscribe
     * to free this reference.
     */
    @CheckResult @NonNull
    public static Consumer<? super Boolean> visibility(@NonNull View view) {
        checkNotNull(view, "view == null");
        return visibility(view, View.GONE);
    }

    /**
     * An action which sets the visibility property of {@code view}.
     * <p>
     * <em>Warning:</em> The created observable keeps a strong reference to {@code view}. Unsubscribe
     * to free this reference.
     *
     * @param visibilityWhenFalse Visibility to set on a {@code false} value ({@code View.INVISIBLE}
     * or {@code View.GONE}).
     */
    @CheckResult @NonNull
    public static Consumer<? super Boolean> visibility(@NonNull final View view,
                                                       final int visibilityWhenFalse) {
        checkNotNull(view, "view == null");
        if (visibilityWhenFalse == View.VISIBLE) {
            throw new IllegalArgumentException(
                    "Setting visibility to VISIBLE when false would have no effect.");
        }
        if (visibilityWhenFalse != View.INVISIBLE && visibilityWhenFalse != View.GONE) {
            throw new IllegalArgumentException("Must set visibility to INVISIBLE or GONE when false.");
        }
        return new Consumer<Boolean>() {
            @Override public void accept(Boolean value) {
                view.setVisibility(value ? View.VISIBLE : visibilityWhenFalse);
            }
        };
    }

    private RxView() {
        throw new AssertionError("No instances.");
    }
}

