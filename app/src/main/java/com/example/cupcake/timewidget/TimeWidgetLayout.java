package com.example.cupcake.timewidget;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;

/**
 * Created by cupcake on 16-1-13.
 */
public class TimeWidgetLayout extends ViewGroup{
    private static final String TAG = "TimeWidgetLayout";

    private float firstClickX = 0;
    private float firstClickY = 0;

    private TimeWidget widget;
    private ValueAnimator animator;

    public TimeWidgetLayout(Context context) {
        super(context);
        init();
    }

    public TimeWidgetLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TimeWidgetLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setBackgroundColor(getResources().getColor(R.color.color_green));
        animation();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        View v = getChildAt(0);
        widget = (TimeWidget) v;
        int width = v.getMeasuredWidth();
        int height = v.getMeasuredHeight();
        Log.i(TAG, "l:" + l + " t:" + t + " r:" + r + " b:" + b + " w:" + width + " h:" + height);
        v.layout(l, t, width, height);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                firstClickX = getX();
                firstClickY = getY();
                Log.i(TAG, "centralX:" + widget.getWidth() / 2);
                Log.i(TAG, "centralY:" + widget.getHeight() / 2);

                Log.i(TAG, "intercept_down");
                return true;
            case MotionEvent.ACTION_MOVE:
                Log.i(TAG, "intercept_move");
                return true;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, "intercept_up");
                return true;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (animator != null) {
                    animator.cancel();
                }
                Log.i(TAG, "touch_down");
                if (widget != null) {
                    widget.setRotationX(-16);
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                float moveX = getX();
                float moveY = getY();

                if (firstClickX == moveX && firstClickY != moveY) {

                } else {

                }

                Log.i(TAG, "touch_move");
                return true;
            case MotionEvent.ACTION_UP:
                if (animator != null && !animator.isRunning()) {
                    animator.start();
                }
                Log.i(TAG, "touch_up");
                return true;
        }
        return super.onTouchEvent(event);
    }

    private void animation() {
        animator = ValueAnimator.ofObject(new TypeEvaluator() {
            @Override
            public Object evaluate(float fraction, Object startValue, Object endValue) {

                widget.setRotationX(-16 * fraction);
                Log.i(TAG, "fraction:" + fraction);
                return null;
            }
        }, 0, 16);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (widget != null) {
                    widget.invalidate();
                }
            }
        });

        animator.setDuration(2000);
        animator.setTarget(widget);
        animator.setInterpolator(new SpringInterpolator());
//        animator.start();
    }
}
