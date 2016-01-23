package com.example.cupcake.timewidget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by cupcake on 16-1-12.
 */
public class TimeWidget extends View {

    private Context mContext;
    private Paint paint;

    public TimeWidget(Context context) {
        super(context);
        init(context);
    }

    public TimeWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TimeWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context mContext) {
        this.mContext = mContext;

        paint = new Paint();
        paint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        ViewGroup.LayoutParams params = getLayoutParams();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = 500;
        setLayoutParams(params);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawSecondOval(canvas);
        drawAlarmOval(canvas);
    }

    private void drawSecondOval(Canvas canvas) {
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(30);
        paint.setStyle(Paint.Style.STROKE);

        PathEffect effect = new DashPathEffect(new float[] {3, 3}, 1);
        paint.setPathEffect(effect);

        RectF rectF = new RectF(getWidth() / 2 - 200, 250 - 200, getWidth() / 2 + 200, 250 + 200);
        canvas.drawOval(rectF, paint);
    }

    private void drawAlarmOval(Canvas canvas) {
        Paint subPaint = new Paint();
        subPaint.setColor(Color.WHITE);
        subPaint.setAntiAlias(true);
        subPaint.setStrokeWidth(1);
        subPaint.setStyle(Paint.Style.STROKE);
        RectF rectF = new RectF(getWidth() / 2 - 150, 250 - 150, getWidth() / 2 + 150, 250 + 150);

        canvas.drawArc(rectF, 0, 360, false, subPaint);

    }
}
