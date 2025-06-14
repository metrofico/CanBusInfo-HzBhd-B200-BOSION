package com.hzbhd.canbus.car_cus._436.view.childView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.core.internal.view.SupportMenu;
import com.hzbhd.midware.constant.HotKeyConstant;

/* loaded from: classes2.dex */
public class Breathe extends View {
    private static final int DEFAULT_DELAY = 15;
    private static final int DEFAULT_RADIUS = 20;
    private static final int DEFAULT_STROKE_WIDTH = 3;
    private static final String TAG = "OkView";
    private int currentFrame;
    private boolean isPlay;
    private int mDelay;
    private int mInitInnerRadius;
    private int mInitOuterRadius;
    private int mInnerAlpha;
    private float mInnerCx;
    private float mInnerCy;
    private Paint mInnerPaint;
    private float mInnerRadius;
    private int mOuterAlpha;
    private float mOuterCx;
    private float mOuterCy;
    private Paint mOuterPaint;
    private float mOuterRadius;
    private float mOuterStrokeWidth;
    private Runnable mRunnable;
    private int mWidth;

    public Breathe(Context context) {
        this(context, null);
    }

    public Breathe(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public Breathe(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mInnerAlpha = 0;
        this.mOuterAlpha = 0;
        this.isPlay = false;
        this.mOuterStrokeWidth = 3.0f;
        this.mInitInnerRadius = 20;
        this.mDelay = 15;
        this.mRunnable = new Runnable() { // from class: com.hzbhd.canbus.car_cus._436.view.childView.Breathe.1
            @Override // java.lang.Runnable
            public void run() {
                if (Breathe.this.isPlay && Breathe.this.getAlpha() != 0.0f) {
                    Breathe.this.play();
                    Breathe.this.invalidate();
                    Breathe breathe = Breathe.this;
                    breathe.postDelayed(breathe.mRunnable, Breathe.this.mDelay);
                    return;
                }
                Breathe.this.removeCallbacks(this);
            }
        };
        this.currentFrame = 1;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
        setMeasuredDimension(paddingLeft, (size2 - getPaddingTop()) - getPaddingBottom());
        this.mWidth = paddingLeft;
        int i3 = paddingLeft / 4;
        this.mInitInnerRadius = i3;
        int i4 = (int) (((paddingLeft / 2) + (this.mOuterStrokeWidth / 2.0f)) / 2.0f);
        this.mInitOuterRadius = i4;
        this.mInnerRadius = i3;
        this.mOuterRadius = i4;
        this.mInnerCx = paddingLeft / 2.0f;
        this.mInnerCy = paddingLeft / 2.0f;
        this.mOuterCx = paddingLeft / 2.0f;
        this.mOuterCy = paddingLeft / 2.0f;
        initPaints();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        this.mInnerPaint.setAlpha(this.mInnerAlpha);
        canvas.drawCircle(this.mInnerCx, this.mInnerCy, this.mInnerRadius, this.mInnerPaint);
        canvas.restore();
        canvas.save();
        this.mOuterPaint.setAlpha(this.mOuterAlpha);
        canvas.drawCircle(this.mOuterCx, this.mOuterCy, this.mOuterRadius, this.mOuterPaint);
        canvas.restore();
    }

    private void initPaints() {
        Paint paint = new Paint();
        this.mInnerPaint = paint;
        paint.setColor(SupportMenu.CATEGORY_MASK);
        this.mInnerPaint.setStyle(Paint.Style.FILL);
        this.mInnerPaint.setAntiAlias(true);
        Paint paint2 = new Paint();
        this.mOuterPaint = paint2;
        paint2.setColor(SupportMenu.CATEGORY_MASK);
        this.mOuterPaint.setStrokeWidth(this.mOuterStrokeWidth);
        this.mOuterPaint.setStyle(Paint.Style.STROKE);
        this.mOuterPaint.setAntiAlias(true);
    }

    public void show() {
        Log.d(TAG, "show: ..............................");
        this.isPlay = true;
        post(this.mRunnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void play() {
        int i = this.currentFrame;
        if (i == 1) {
            boolean zInnerCircleShow = innerCircleShow();
            boolean zOuterCircleShow = outerCircleShow();
            if (zInnerCircleShow && zOuterCircleShow) {
                this.currentFrame++;
                return;
            }
            return;
        }
        if (i != 2) {
            return;
        }
        boolean zInnerCircleHide = innerCircleHide();
        boolean zOuterCircleHide = outerCircleHide();
        boolean zOuterCircleEnlarge = outerCircleEnlarge();
        if (zInnerCircleHide && zOuterCircleHide && zOuterCircleEnlarge) {
            this.mOuterRadius = this.mInitOuterRadius;
            resumeInit();
            this.currentFrame = 1;
        }
    }

    private boolean innerCircleShow() {
        int i = (int) (this.mInnerAlpha + 15.3d);
        this.mInnerAlpha = i;
        if (i <= 229.5f) {
            return false;
        }
        this.mInnerAlpha = HotKeyConstant.K_SET_PASSWRD;
        return true;
    }

    private boolean innerCircleHide() {
        int i = (int) (this.mInnerAlpha - 15.3d);
        this.mInnerAlpha = i;
        if (i >= 0) {
            return false;
        }
        this.mInnerAlpha = 0;
        return true;
    }

    private boolean outerCircleShow() {
        int i = this.mOuterAlpha + 17;
        this.mOuterAlpha = i;
        if (i <= 255) {
            return false;
        }
        this.mOuterAlpha = 255;
        return true;
    }

    private boolean outerCircleHide() {
        int i = this.mOuterAlpha - 17;
        this.mOuterAlpha = i;
        if (i >= 0) {
            return false;
        }
        this.mOuterAlpha = 0;
        return true;
    }

    private boolean outerCircleEnlarge() {
        float f = this.mOuterRadius;
        double d = f;
        int i = this.mInitOuterRadius;
        if (d > i * 1.5d) {
            return true;
        }
        this.mOuterRadius = f + (i / 30.0f);
        return false;
    }

    public void hide() {
        Log.d(TAG, "hide: .......................");
        this.isPlay = false;
        this.currentFrame = 1;
        removeCallbacks(this.mRunnable);
        resumeInit();
    }

    private void resumeInit() {
        this.mInnerAlpha = 0;
        this.mOuterRadius = this.mInitInnerRadius;
        this.mInnerRadius = this.mInitOuterRadius;
        this.mOuterAlpha = 0;
        invalidate();
    }

    public void setOuterStrokeWidth(float f) {
        this.mOuterStrokeWidth = f;
        this.mInitOuterRadius = (int) (((this.mWidth / 2) + (f / 2.0f)) / 2.0f);
    }

    public void setDelay(int i) {
        this.mDelay = i;
    }
}
