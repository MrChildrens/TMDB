package com.goku.tmdb.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.goku.tmdb.R;

public class PercentCircleView extends View {
    private static final String TAG = "PercentCircleView";

    private Paint mPaint;
    private RectF mPercentOval;
    private float mPercent = 0;
    private int mTextsize = 30;

    public PercentCircleView(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public PercentCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    public PercentCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    public PercentCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PercentCircleView);
        mPercent = typedArray.getFloat(R.styleable.PercentCircleView_percent, 0);
        mTextsize = typedArray.getDimensionPixelOffset(R.styleable.PercentCircleView_textSize, 30);
        typedArray.recycle();

        mPaint = new Paint();
        mPercentOval = new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float sweepAngle = (float) (360L * mPercent);
        float startAngle = -90;
        float centerX = getPivotX();
        float centerY = getPivotY();


        mPaint.setColor(getResources().getColor(R.color.tmdb_main_bg_color));
        mPaint.setStyle(Paint.Style.STROKE);

        int outOvalStokeWidth = getResources().getDimensionPixelOffset(R.dimen.percent_circle_stroke_width);
        int outOvalRadius = (getWidth() - outOvalStokeWidth) / 2;
        mPaint.setStrokeWidth(outOvalStokeWidth);
        canvas.drawCircle(centerX, centerY, outOvalRadius, mPaint);

        int strokeLightColor;
        int strokeNormalColor;
        if (mPercent <= 0) {
            strokeLightColor = R.color.rating_no_color;
            strokeNormalColor = R.color.rating_no_color;
        } else if (mPercent > 0 && mPercent < 0.4) {
            strokeLightColor = R.color.rating_low_light_color;
            strokeNormalColor = R.color.rating_low_normal_color;
        } else if (mPercent < 0.7) {
            strokeLightColor = R.color.rating_middle_light_color;
            strokeNormalColor = R.color.rating_middle_normal_color;
        } else {
            strokeLightColor = R.color.rating_high_light_color;
            strokeNormalColor = R.color.rating_high_normal_color;
        }

        int percentOvalStokeWidth = getResources().getDimensionPixelOffset(R.dimen.percent_circle_stroke_width);
        int percentOvalRadius = (getWidth() - outOvalStokeWidth - percentOvalStokeWidth) / 2;
        mPaint.setStrokeWidth(percentOvalStokeWidth);
        mPaint.setColor(getResources().getColor(strokeLightColor));


        mPercentOval.left = centerX - percentOvalRadius;
        mPercentOval.top = centerX - percentOvalRadius;
        mPercentOval.right = centerX + percentOvalRadius;
        mPercentOval.bottom = centerX + percentOvalRadius;

        canvas.drawArc(mPercentOval, startAngle, sweepAngle, false, mPaint);

        mPaint.setColor(getResources().getColor(strokeNormalColor));
        canvas.drawArc(mPercentOval, startAngle + sweepAngle, 360 - sweepAngle, false, mPaint);

        int innerOvalRadius = (getWidth() - outOvalStokeWidth - percentOvalStokeWidth) / 2;
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(getResources().getColor(R.color.tmdb_main_bg_color));
        canvas.drawCircle(getPivotX(), getPivotY(), innerOvalRadius, mPaint);


        mPaint.setColor(Color.WHITE);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextSize(mTextsize);
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);

        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        //居中线
        float midLine = -(fontMetrics.top + fontMetrics.bottom) / 2 + getMeasuredHeight() / 2;
        int percentDoubleValue = (int) (mPercent * 100);
        if (percentDoubleValue <= 0) {
            canvas.drawText("NR", centerX, midLine, mPaint);
        } else {
            canvas.drawText(percentDoubleValue + "%", centerX, midLine, mPaint);
        }
    }

    public void setPercent(float percent) {
        mPercent = percent;
        invalidate();
    }
}
