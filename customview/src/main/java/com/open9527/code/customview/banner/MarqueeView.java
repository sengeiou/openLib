package com.open9527.code.customview.banner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.open9527.code.customview.AdaptScreenUtils;
import com.open9527.code.customview.R;

import java.util.ArrayList;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/12 11:01.
 * E-Mail Address ：open_9527@163.com.
 * DESC :跑马灯.
 */
public class MarqueeView extends ViewFlipper {

    private Context mContext;
    private ArrayList<String> notices;
    private boolean isSetAnimDuration = false;
    private OnItemClickListener onItemClickListener;

    private int interval = 2000;
    private int animDuration = 500;
    private int textSize = 14;
    private int textColor = 0xffffffff;

    private boolean singleLine = false;
    private int gravity = Gravity.START | Gravity.CENTER_VERTICAL;
    private static final int TEXT_GRAVITY_LEFT = 0, TEXT_GRAVITY_CENTER = 1, TEXT_GRAVITY_RIGHT = 2;

    public MarqueeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        this.mContext = context;
        if (notices == null) {
            notices = new ArrayList<>();
        }

        @SuppressLint("CustomViewStyleable")
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.MarqueeViewStyle, defStyleAttr, 0);
        interval = typedArray.getInteger(R.styleable.MarqueeViewStyle_mvInterval, interval);
        isSetAnimDuration = typedArray.hasValue(R.styleable.MarqueeViewStyle_mvAnimDuration);
        singleLine = typedArray.getBoolean(R.styleable.MarqueeViewStyle_mvSingleLine, false);
        animDuration = typedArray.getInteger(R.styleable.MarqueeViewStyle_mvAnimDuration, animDuration);
        if (typedArray.hasValue(R.styleable.MarqueeViewStyle_mvTextSize)) {
            textSize = (int) typedArray.getDimension(R.styleable.MarqueeViewStyle_mvTextSize, textSize);
            textSize = AdaptScreenUtils.px2Pt(mContext, textSize);
        }
        textColor = typedArray.getColor(R.styleable.MarqueeViewStyle_mvTextColor, textColor);
        int gravityType = typedArray.getInt(R.styleable.MarqueeViewStyle_mvGravity, TEXT_GRAVITY_LEFT);
        switch (gravityType) {
            case TEXT_GRAVITY_CENTER:
                gravity = Gravity.CENTER;
                break;
            case TEXT_GRAVITY_RIGHT:
                gravity = Gravity.START | Gravity.CENTER_VERTICAL;
                break;
            default:
                break;
        }
        typedArray.recycle();
        setFlipInterval(interval);
    }

    /**
     * 根据公告字符串启动轮播
     *
     * @param notice 公告
     */
    public void startWithText(final String notice) {
        if (TextUtils.isEmpty(notice)) {
            return;
        }
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getViewTreeObserver().removeGlobalOnLayoutListener(this);
                startWithFixedWidth(notice, getWidth());
            }
        });
    }

    /**
     * 根据公告字符串列表启动轮播
     *
     * @param notices 公告集合
     */
    public void startWithList(ArrayList<String> notices) {
        setNotices(notices);
        start();
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public void setSingleLine(boolean singleLine) {
        this.singleLine = singleLine;
    }

    public void setAnimDuration(int animDuration) {
        this.animDuration = animDuration;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
    }

    /**
     * 根据宽度和公告字符串启动轮播
     *
     * @param notice 公告
     * @param width  width
     */
    private void startWithFixedWidth(String notice, int width) {
        int noticeLength = notice.length();
        int dpW = AdaptScreenUtils.px2Pt(mContext, width);
        int limit = dpW / textSize;
        if (dpW == 0) {
            throw new RuntimeException("Please set MarqueeView width !");
        }
        ArrayList<String> list = new ArrayList<>();
        if (noticeLength <= limit) {
            list.add(notice);
        } else {
            int size = noticeLength / limit + (noticeLength % limit != 0 ? 1 : 0);
            for (int i = 0; i < size; i++) {
                int startIndex = i * limit;
                int endIndex = ((i + 1) * limit >= noticeLength ? noticeLength : (i + 1) * limit);
                list.add(notice.substring(startIndex, endIndex));
            }
        }
        notices.addAll(list);
        start();
    }

    /**
     * 启动轮播
     *
     * @return 是否启动轮播
     */
    public boolean start() {
        if (notices == null || notices.size() == 0) {
            return false;
        }
        //先移除所有view
        removeAllViews();
        //然后重置动画
        resetAnimation();

        //根据设置的数据集合数量创建TextView
        for (int i = 0; i < notices.size(); i++) {
            final TextView textView = createTextView(notices.get(i), i);
            final int finalI = i;
            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(finalI, textView);
                    }
                }
            });
            addView(textView);
        }

        //如果集合数目大于1，则开始；否则停止
        if (notices.size() > 1) {
            startFlipping();
        } else {
            stopFlipping();
        }
        return true;
    }


    /**
     * 重置动画
     */
    private void resetAnimation() {
        clearAnimation();
        //设置进入的动画
        Animation animIn = AnimationUtils.loadAnimation(mContext, R.anim.anim_marquee_in);
        if (isSetAnimDuration) {
            animIn.setDuration(animDuration);
        }
        setInAnimation(animIn);

        //设置结束的动画
        Animation animOut = AnimationUtils.loadAnimation(mContext, R.anim.anim_marquee_out);
        if (isSetAnimDuration) {
            animOut.setDuration(animDuration);
        }
        setOutAnimation(animOut);
    }

    /**
     * 创建ViewFlipper下的TextView
     *
     * @param text     text
     * @param position position
     * @return TextView
     */
    protected TextView createTextView(CharSequence text, int position) {
        TextView tv = new TextView(mContext);
        tv.setGravity(gravity);
        tv.setText(text);
        tv.setTextColor(textColor);
        tv.setTextSize(textSize);
        tv.setSingleLine(singleLine);
        if (singleLine) {
            tv.setEllipsize(TextUtils.TruncateAt.END);
        }
        tv.setTag(position);
        return tv;
    }

    /**
     * 获取当前索引位置
     *
     * @return 索引
     */
    public int getPosition() {
        return (int) getCurrentView().getTag();
    }

    public ArrayList<String> getNotices() {
        return notices;
    }

    /**
     * 设置数据集合
     *
     * @param notices 数据
     */
    public void setNotices(ArrayList<String> notices) {
        this.notices = notices;
    }

    /**
     * 设置点击事件
     *
     * @param onItemClickListener 点击事件listener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, TextView textView);
    }

    @Override
    public Resources getResources() {
        return AdaptScreenUtils.adaptWidth(mContext, super.getResources(), 1080);
    }
}
