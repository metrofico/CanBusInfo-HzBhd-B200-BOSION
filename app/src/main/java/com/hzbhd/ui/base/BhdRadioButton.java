package com.hzbhd.ui.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.RadioButton;

import com.hzbhd.canbus.R;
import com.hzbhd.util.LogUtil;


@SuppressLint("AppCompatCustomView")
public final class BhdRadioButton extends RadioButton {
    private Bitmap fullBg;
    private Bitmap fullBgP;
    private Bitmap fullFg;
    private Bitmap fullFgP;

    /* renamed from: mDrawablePaint$delegate, reason: from kotlin metadata */
    private final Paint mDrawablePaint;
    private Bitmap minBg;
    private int minBgHeight;
    private int minBgLeft;
    private Bitmap minBgP;
    private int minBgTop;
    private int minBgWidth;
    private Bitmap minFg;
    private int minFgHeight;
    private int minFgLeft;
    private Bitmap minFgP;
    private int minFgTop;
    private int minFgWidth;

    private Paint getMDrawablePaint() {
        return this.mDrawablePaint;
    }

    public int getMinBgWidth() {
        return this.minBgWidth;
    }

    public void setMinBgWidth(int i) {
        this.minBgWidth = i;
    }

    public int getMinBgHeight() {
        return this.minBgHeight;
    }

    public void setMinBgHeight(int i) {
        this.minBgHeight = i;
    }

    public int getMinFgWidth() {
        return this.minFgWidth;
    }

    public void setMinFgWidth(int i) {
        this.minFgWidth = i;
    }

    public int getMinFgHeight() {
        return this.minFgHeight;
    }

    public void setMinFgHeight(int i) {
        this.minFgHeight = i;
    }

    public int getMinBgTop() {
        return this.minBgTop;
    }

    public void setMinBgTop(int i) {
        this.minBgTop = i;
    }

    public int getMinBgLeft() {
        return this.minBgLeft;
    }

    public void setMinBgLeft(int i) {
        this.minBgLeft = i;
    }

    public int getMinFgLeft() {
        return this.minFgLeft;
    }

    public void setMinFgLeft(int i) {
        this.minFgLeft = i;
    }

    public int getMinFgTop() {
        return this.minFgTop;
    }

    public void setMinFgTop(int i) {
        this.minFgTop = i;
    }

    public Bitmap getFullBg() {
        return this.fullBg;
    }

    public void setFullBg(Bitmap bitmap) {
        this.fullBg = bitmap;
    }

    public Bitmap getMinBg() {
        return this.minBg;
    }

    public void setMinBg(Bitmap bitmap) {
        this.minBg = bitmap;
    }

    public Bitmap getFullFg() {
        return this.fullFg;
    }

    public void setFullFg(Bitmap bitmap) {
        this.fullFg = bitmap;
    }

    public Bitmap getMinFg() {
        return this.minFg;
    }

    public void setMinFg(Bitmap bitmap) {
        this.minFg = bitmap;
    }

    public Bitmap getFullBgP() {
        return this.fullBgP;
    }

    public void setFullBgP(Bitmap bitmap) {
        this.fullBgP = bitmap;
    }

    public Bitmap getMinBgP() {
        return this.minBgP;
    }

    public void setMinBgP(Bitmap bitmap) {
        this.minBgP = bitmap;
    }

    public Bitmap getFullFgP() {
        return this.fullFgP;
    }

    public void setFullFgP(Bitmap bitmap) {
        this.fullFgP = bitmap;
    }

    public Bitmap getMinFgP() {
        return this.minFgP;
    }

    public void setMinFgP(Bitmap bitmap) {
        this.minFgP = bitmap;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BhdRadioButton(Context context) {
        super(context);
        this.mDrawablePaint = createDrawablePaint();
        init(context, null);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BhdRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mDrawablePaint = createDrawablePaint();
        init(context, attrs);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BhdRadioButton(Context context, AttributeSet attrs, int i) {
        super(context, attrs, i);
        this.mDrawablePaint = createDrawablePaint();
        init(context, attrs);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BhdRadioButton(Context context, AttributeSet attrs, int i, int i2) {
        super(context, attrs, i, i2);
        this.mDrawablePaint = createDrawablePaint();
        init(context, attrs);
    }


    private Paint createDrawablePaint() {
        Paint paint = new Paint();
        paint.setAntiAlias(false);
        paint.setFilterBitmap(false);
        return paint;
    }

    private void init(Context context, AttributeSet attrs) {
        setButtonDrawable(null);
        if (attrs != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.radioButtonAttrs);
            this.minBgWidth = typedArrayObtainStyledAttributes.getLayoutDimension(R.styleable.radioButtonAttrs_min_bg_w, 0);
            this.minBgHeight = typedArrayObtainStyledAttributes.getLayoutDimension(R.styleable.radioButtonAttrs_min_bg_h, 0);
            this.minFgWidth = typedArrayObtainStyledAttributes.getLayoutDimension(R.styleable.radioButtonAttrs_min_fg_w, 0);
            this.minFgHeight = typedArrayObtainStyledAttributes.getLayoutDimension(R.styleable.radioButtonAttrs_min_fg_h, 0);
            this.minBgLeft = typedArrayObtainStyledAttributes.getLayoutDimension(R.styleable.radioButtonAttrs_min_bg_l, 0);
            this.minBgTop = typedArrayObtainStyledAttributes.getLayoutDimension(R.styleable.radioButtonAttrs_min_bg_t, 0);
            this.minFgLeft = typedArrayObtainStyledAttributes.getLayoutDimension(R.styleable.radioButtonAttrs_min_fg_l, 0);
            this.minFgTop = typedArrayObtainStyledAttributes.getLayoutDimension(R.styleable.radioButtonAttrs_min_fg_t, 0);
            this.fullBg = BitmapFactory.decodeResource(getResources(), typedArrayObtainStyledAttributes.getResourceId(R.styleable.radioButtonAttrs_full_bg, 0));
            this.minBg = BitmapFactory.decodeResource(getResources(), typedArrayObtainStyledAttributes.getResourceId(R.styleable.radioButtonAttrs_min_bg, 0));
            this.fullFg = BitmapFactory.decodeResource(getResources(), typedArrayObtainStyledAttributes.getResourceId(R.styleable.radioButtonAttrs_full_fg, 0));
            this.minFg = BitmapFactory.decodeResource(getResources(), typedArrayObtainStyledAttributes.getResourceId(R.styleable.radioButtonAttrs_min_fg, 0));
            this.fullBgP = BitmapFactory.decodeResource(getResources(), typedArrayObtainStyledAttributes.getResourceId(R.styleable.radioButtonAttrs_full_bg_p, 0));
            this.minBgP = BitmapFactory.decodeResource(getResources(), typedArrayObtainStyledAttributes.getResourceId(R.styleable.radioButtonAttrs_min_bg_p, 0));
            this.fullFgP = BitmapFactory.decodeResource(getResources(), typedArrayObtainStyledAttributes.getResourceId(R.styleable.radioButtonAttrs_full_fg_p, 0));
            this.minFgP = BitmapFactory.decodeResource(getResources(), typedArrayObtainStyledAttributes.getResourceId(R.styleable.radioButtonAttrs_min_fg_p, 0));
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);

        if (LogUtil.log2()) {
            LogUtil.d("draw: " + getMeasuredWidth() + ',' + getMeasuredHeight() + "   " + getMinWidth() + ',' + getMinHeight());
        }
        new Rect(0, 0, getMeasuredWidth(), getMeasuredHeight());
        if (isChecked()) {
            Bitmap bitmap = this.fullBgP;
            if (bitmap != null) {
                canvas.drawBitmap(getDrawableBitmap(bitmap, getMeasuredWidth(), getMeasuredHeight()), 0.0f, 0.0f, getMDrawablePaint());
            }
            Bitmap bitmap2 = this.minBgP;
            if (bitmap2 != null) {
                canvas.drawBitmap(getDrawableBitmap(bitmap2, getMinBgWidth(), getMinBgHeight()), getMinBgLeft(), getMinBgTop(), getMDrawablePaint());
            }
        } else {
            Bitmap bitmap3 = this.fullBg;
            if (bitmap3 != null) {
                canvas.drawBitmap(getDrawableBitmap(bitmap3, getMeasuredWidth(), getMeasuredHeight()), 0.0f, 0.0f, getMDrawablePaint());
            }
            Bitmap bitmap4 = this.minBg;
            if (bitmap4 != null) {
                canvas.drawBitmap(getDrawableBitmap(bitmap4, getMinBgWidth(), getMinBgHeight()), getMinBgLeft(), getMinBgTop(), getMDrawablePaint());
            }
        }
        dispatchDraw(canvas);
        if (this.minFgWidth == 0) {
            this.minFgWidth = this.minBgWidth;
        }
        if (this.minFgHeight == 0) {
            this.minFgHeight = this.minBgHeight;
        }
        if (this.minFgLeft == 0) {
            this.minFgLeft = this.minBgLeft;
        }
        if (this.minFgTop == 0) {
            this.minFgTop = this.minBgTop;
        }
        if (isChecked()) {
            Bitmap bitmap5 = this.fullFgP;
            if (bitmap5 != null) {
                canvas.drawBitmap(getDrawableBitmap(bitmap5, getMeasuredWidth(), getMeasuredHeight()), 0.0f, 0.0f, getMDrawablePaint());
            }
            Bitmap bitmap6 = this.minFgP;
            if (bitmap6 == null) {
                return;
            }
            canvas.drawBitmap(getDrawableBitmap(bitmap6, getMinFgWidth(), getMinFgHeight()), getMinFgLeft(), getMinFgTop(), getMDrawablePaint());
            return;
        }
        Bitmap bitmap7 = this.fullFg;
        if (bitmap7 != null) {
            canvas.drawBitmap(getDrawableBitmap(bitmap7, getMeasuredWidth(), getMeasuredHeight()), 0.0f, 0.0f, getMDrawablePaint());
        }
        Bitmap bitmap8 = this.minFg;
        if (bitmap8 == null) {
            return;
        }
        canvas.drawBitmap(getDrawableBitmap(bitmap8, getMinFgWidth(), getMinFgHeight()), getMinFgLeft(), getMinFgTop(), getMDrawablePaint());
    }

    private Bitmap getDrawableBitmap(Bitmap bitmap, int drawWidth, int drawHeight) {
        Matrix matrix = new Matrix();
        if (drawWidth != 0 && drawHeight != 0) {
            matrix.postScale(drawWidth / bitmap.getWidth(), drawHeight / bitmap.getHeight());
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

        return bitmapCreateBitmap;
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean checked) {
        super.setChecked(checked);
        invalidate();
    }
}
