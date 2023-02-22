package com.goku.tmdb.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

public class CircleImageView extends androidx.appcompat.widget.AppCompatImageView {

    private static final PorterDuffXfermode SRC_IN = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);//取两层绘制交集。显示上层。

    private int width;
    private int height;
    private Rect rect;
    private final Paint paint;
    private int radius;

    public CircleImageView(Context context) {
        this(context, null);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        rect = new Rect(0, 0, w, h);
        width = w;
        height = h;
        radius = Math.min(width, height) / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //xml中要用src,不能用background,否则这里drawable会为null
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        Bitmap bitmap = null;
        if (drawable instanceof BitmapDrawable) {
            bitmap = ((BitmapDrawable) drawable).getBitmap();
        }
        if (bitmap == null) {
            return;
        }

        //通过给Paint设置BitmapShader的方式画圆形图
        canvas.drawCircle(width / 2, height / 2, radius, getShaderPaint(bitmap));

        //通过设置Xfermode的方式获取圆形Bitmap来实现圆形图
        canvas.drawBitmap(getRoundBitmap(bitmap), rect, rect, paint);
    }

    private Paint getShaderPaint(Bitmap bitmap) {
        Bitmap scaleBitmap = getScaleBitmap(bitmap, radius);
        BitmapShader bitmapShader = new BitmapShader(scaleBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint.setShader(bitmapShader);
        paint.setAntiAlias(true);
        return paint;
    }

    private Bitmap getRoundBitmap(Bitmap bitmap) {
        Bitmap scaleBitmap = getScaleBitmap(bitmap, radius);
        Bitmap out = Bitmap.createBitmap(scaleBitmap.getWidth(), scaleBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(out);
        Rect rect = new Rect(0, 0, scaleBitmap.getWidth(), scaleBitmap.getHeight());
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle(scaleBitmap.getWidth() / 2, scaleBitmap.getHeight() / 2, scaleBitmap.getWidth() / 2, paint);
        paint.setXfermode(SRC_IN);
        canvas.drawBitmap(scaleBitmap, rect, rect, paint);
        paint.reset();
        return out;
    }

    private Bitmap getScaleBitmap(Bitmap bitmap, int radius) {
        Bitmap scaledSrcBmp;
        int diameter = radius * 2;

        // 为了防止宽高不相等，造成圆形图片变形，因此截取长方形中处于中间位置最大的正方形图片
        int bmpWidth = bitmap.getWidth();
        int bmpHeight = bitmap.getHeight();
        int squareWidth = 0, squareHeight = 0;
        int x = 0, y = 0;
        Bitmap squareBitmap;
        if (bmpHeight > bmpWidth) {// 高大于宽
            squareWidth = squareHeight = bmpWidth;
            x = 0;
            y = (bmpHeight - bmpWidth) / 2;
            // 截取正方形图片
            squareBitmap = Bitmap.createBitmap(bitmap, x, y, squareWidth, squareHeight);
        } else if (bmpHeight < bmpWidth) {// 宽大于高
            squareWidth = squareHeight = bmpHeight;
            x = (bmpWidth - bmpHeight) / 2;
            y = 0;
            squareBitmap = Bitmap.createBitmap(bitmap, x, y, squareWidth, squareHeight);
        } else {
            squareBitmap = bitmap;
        }
        if (squareBitmap.getWidth() != diameter || squareBitmap.getHeight() != diameter) {
            scaledSrcBmp = Bitmap.createScaledBitmap(squareBitmap, diameter, diameter, true);

        } else {
            scaledSrcBmp = squareBitmap;
        }
        return scaledSrcBmp;
    }
}
