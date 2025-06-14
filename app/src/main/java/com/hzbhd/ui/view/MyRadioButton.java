package com.hzbhd.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioButton;
import com.hzbhd.util.LogUtil;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MyRadioButton.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b+\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB'\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t¢\u0006\u0002\u0010\fJ\u0010\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020LH\u0017J \u0010M\u001a\u00020\u000f2\u0006\u0010N\u001a\u00020\u000f2\u0006\u0010O\u001a\u00020\t2\u0006\u0010P\u001a\u00020\tH\u0002J\u0010\u0010Q\u001a\u00020R2\u0006\u0010S\u001a\u00020\tH\u0002J\u001a\u0010T\u001a\u00020J2\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002J\u0010\u0010U\u001a\u00020J2\u0006\u0010V\u001a\u00020WH\u0016R\u000e\u0010\r\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0011\"\u0004\b\u0016\u0010\u0013R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0011\"\u0004\b\u0019\u0010\u0013R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0011\"\u0004\b\u001c\u0010\u0013R\u001b\u0010\u001d\u001a\u00020\u001e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b\u001f\u0010 R\u001c\u0010#\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0011\"\u0004\b%\u0010\u0013R\u001a\u0010&\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001a\u0010+\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010(\"\u0004\b-\u0010*R\u001c\u0010.\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u0011\"\u0004\b0\u0010\u0013R\u001a\u00101\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010(\"\u0004\b3\u0010*R\u001a\u00104\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010(\"\u0004\b6\u0010*R\u001c\u00107\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010\u0011\"\u0004\b9\u0010\u0013R\u001a\u0010:\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010(\"\u0004\b<\u0010*R\u001a\u0010=\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010(\"\u0004\b?\u0010*R\u001c\u0010@\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010\u0011\"\u0004\bB\u0010\u0013R\u001a\u0010C\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010(\"\u0004\bE\u0010*R\u001a\u0010F\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010(\"\u0004\bH\u0010*¨\u0006X"}, d2 = {"Lcom/hzbhd/ui/view/MyRadioButton;", "Landroid/widget/RadioButton;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "drawGravity", "fullBg", "Landroid/graphics/Bitmap;", "getFullBg", "()Landroid/graphics/Bitmap;", "setFullBg", "(Landroid/graphics/Bitmap;)V", "fullBgP", "getFullBgP", "setFullBgP", "fullFg", "getFullFg", "setFullFg", "fullFgP", "getFullFgP", "setFullFgP", "mDrawablePaint", "Landroid/graphics/Paint;", "getMDrawablePaint", "()Landroid/graphics/Paint;", "mDrawablePaint$delegate", "Lkotlin/Lazy;", "minBg", "getMinBg", "setMinBg", "minBgHeight", "getMinBgHeight", "()I", "setMinBgHeight", "(I)V", "minBgLeft", "getMinBgLeft", "setMinBgLeft", "minBgP", "getMinBgP", "setMinBgP", "minBgTop", "getMinBgTop", "setMinBgTop", "minBgWidth", "getMinBgWidth", "setMinBgWidth", "minFg", "getMinFg", "setMinFg", "minFgHeight", "getMinFgHeight", "setMinFgHeight", "minFgLeft", "getMinFgLeft", "setMinFgLeft", "minFgP", "getMinFgP", "setMinFgP", "minFgTop", "getMinFgTop", "setMinFgTop", "minFgWidth", "getMinFgWidth", "setMinFgWidth", "draw", "", "canvas", "Landroid/graphics/Canvas;", "getDrawableBitmap", "bitmap", "drawWidth", "drawHeight", "getGravityFactor", "", "axis", "init", "setChecked", "checked", "", "commonview-base_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes3.dex */
public class MyRadioButton extends RadioButton {
    private int drawGravity;
    private Bitmap fullBg;
    private Bitmap fullBgP;
    private Bitmap fullFg;
    private Bitmap fullFgP;

    /* renamed from: mDrawablePaint$delegate, reason: from kotlin metadata */
    private final Lazy mDrawablePaint;
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

    private final Paint getMDrawablePaint() {
        return (Paint) this.mDrawablePaint.getValue();
    }

    public final int getMinBgWidth() {
        return this.minBgWidth;
    }

    public final void setMinBgWidth(int i) {
        this.minBgWidth = i;
    }

    public final int getMinBgHeight() {
        return this.minBgHeight;
    }

    public final void setMinBgHeight(int i) {
        this.minBgHeight = i;
    }

    public final int getMinFgWidth() {
        return this.minFgWidth;
    }

    public final void setMinFgWidth(int i) {
        this.minFgWidth = i;
    }

    public final int getMinFgHeight() {
        return this.minFgHeight;
    }

    public final void setMinFgHeight(int i) {
        this.minFgHeight = i;
    }

    public final int getMinBgTop() {
        return this.minBgTop;
    }

    public final void setMinBgTop(int i) {
        this.minBgTop = i;
    }

    public final int getMinBgLeft() {
        return this.minBgLeft;
    }

    public final void setMinBgLeft(int i) {
        this.minBgLeft = i;
    }

    public final int getMinFgLeft() {
        return this.minFgLeft;
    }

    public final void setMinFgLeft(int i) {
        this.minFgLeft = i;
    }

    public final int getMinFgTop() {
        return this.minFgTop;
    }

    public final void setMinFgTop(int i) {
        this.minFgTop = i;
    }

    public final Bitmap getFullBg() {
        return this.fullBg;
    }

    public final void setFullBg(Bitmap bitmap) {
        this.fullBg = bitmap;
    }

    public final Bitmap getMinBg() {
        return this.minBg;
    }

    public final void setMinBg(Bitmap bitmap) {
        this.minBg = bitmap;
    }

    public final Bitmap getFullFg() {
        return this.fullFg;
    }

    public final void setFullFg(Bitmap bitmap) {
        this.fullFg = bitmap;
    }

    public final Bitmap getMinFg() {
        return this.minFg;
    }

    public final void setMinFg(Bitmap bitmap) {
        this.minFg = bitmap;
    }

    public final Bitmap getFullBgP() {
        return this.fullBgP;
    }

    public final void setFullBgP(Bitmap bitmap) {
        this.fullBgP = bitmap;
    }

    public final Bitmap getMinBgP() {
        return this.minBgP;
    }

    public final void setMinBgP(Bitmap bitmap) {
        this.minBgP = bitmap;
    }

    public final Bitmap getFullFgP() {
        return this.fullFgP;
    }

    public final void setFullFgP(Bitmap bitmap) {
        this.fullFgP = bitmap;
    }

    public final Bitmap getMinFgP() {
        return this.minFgP;
    }

    public final void setMinFgP(Bitmap bitmap) {
        this.minFgP = bitmap;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MyRadioButton(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.mDrawablePaint = LazyKt.lazy(MyRadioButton$mDrawablePaint$2.INSTANCE);
        init(context, null);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MyRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        this.mDrawablePaint = LazyKt.lazy(MyRadioButton$mDrawablePaint$2.INSTANCE);
        init(context, attrs);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MyRadioButton(Context context, AttributeSet attrs, int i) {
        super(context, attrs, i);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        this.mDrawablePaint = LazyKt.lazy(MyRadioButton$mDrawablePaint$2.INSTANCE);
        init(context, attrs);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MyRadioButton(Context context, AttributeSet attrs, int i, int i2) {
        super(context, attrs, i, i2);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        this.mDrawablePaint = LazyKt.lazy(MyRadioButton$mDrawablePaint$2.INSTANCE);
        init(context, attrs);
    }

    private final void init(Context context, AttributeSet attrs) {
        setButtonDrawable((Drawable) null);
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
            TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attrs, R.styleable.color_attr);
            this.drawGravity = typedArrayObtainStyledAttributes.getInteger(R.styleable.color_attr_draw_gravity, 51);
            typedArrayObtainStyledAttributes2.recycle();
        }
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        if (LogUtil.log2()) {
            LogUtil.d("draw: " + getMeasuredWidth() + ',' + getMeasuredHeight() + "   " + getMinWidth() + ',' + getMinHeight());
        }
        new Rect(0, 0, getMeasuredWidth(), getMeasuredHeight());
        int gravityFactor = (int) (getGravityFactor(0) * (getMeasuredWidth() - this.minBgWidth));
        int gravityFactor2 = (int) (getGravityFactor(4) * (getMeasuredHeight() - this.minBgHeight));
        if (isChecked()) {
            Bitmap bitmap = this.fullBgP;
            if (bitmap != null) {
                canvas.drawBitmap(getDrawableBitmap(bitmap, getMeasuredWidth(), getMeasuredHeight()), 0.0f, 0.0f, getMDrawablePaint());
            }
            Bitmap bitmap2 = this.minBgP;
            if (bitmap2 != null) {
                canvas.drawBitmap(getDrawableBitmap(bitmap2, this.minBgWidth, this.minBgHeight), this.minBgLeft + gravityFactor, this.minBgTop + gravityFactor2, getMDrawablePaint());
            }
        } else {
            Bitmap bitmap3 = this.fullBg;
            if (bitmap3 != null) {
                canvas.drawBitmap(getDrawableBitmap(bitmap3, getMeasuredWidth(), getMeasuredHeight()), 0.0f, 0.0f, getMDrawablePaint());
            }
            Bitmap bitmap4 = this.minBg;
            if (bitmap4 != null) {
                canvas.drawBitmap(getDrawableBitmap(bitmap4, this.minBgWidth, this.minBgHeight), this.minBgLeft + gravityFactor, this.minBgTop + gravityFactor2, getMDrawablePaint());
            }
        }
        super.onDraw(canvas);
        dispatchDraw(canvas);
        if (this.minFgWidth == 0) {
            this.minFgWidth = this.minBgWidth;
        }
        if (this.minFgHeight == 0) {
            this.minFgHeight = this.minBgHeight;
        }
        if (this.minFgLeft == 0) {
            this.minFgLeft = this.minBgLeft + gravityFactor;
        }
        if (this.minFgTop == 0) {
            this.minFgTop = this.minBgTop + gravityFactor2;
        }
        if (isChecked()) {
            Bitmap bitmap5 = this.fullFgP;
            if (bitmap5 != null) {
                canvas.drawBitmap(getDrawableBitmap(bitmap5, getMeasuredWidth(), getMeasuredHeight()), 0.0f, 0.0f, getMDrawablePaint());
            }
            Bitmap bitmap6 = this.minFgP;
            if (bitmap6 != null) {
                canvas.drawBitmap(getDrawableBitmap(bitmap6, this.minFgWidth, this.minFgHeight), this.minFgLeft, this.minFgTop, getMDrawablePaint());
                return;
            }
            return;
        }
        Bitmap bitmap7 = this.fullFg;
        if (bitmap7 != null) {
            canvas.drawBitmap(getDrawableBitmap(bitmap7, getMeasuredWidth(), getMeasuredHeight()), 0.0f, 0.0f, getMDrawablePaint());
        }
        Bitmap bitmap8 = this.minFg;
        if (bitmap8 != null) {
            canvas.drawBitmap(getDrawableBitmap(bitmap8, this.minFgWidth, this.minFgHeight), this.minFgLeft, this.minFgTop, getMDrawablePaint());
        }
    }

    private final Bitmap getDrawableBitmap(Bitmap bitmap, int drawWidth, int drawHeight) {
        Matrix matrix = new Matrix();
        if (drawWidth != 0 && drawHeight != 0) {
            matrix.postScale(drawWidth / bitmap.getWidth(), drawHeight / bitmap.getHeight());
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(bitmap, 0, …map.height, matrix, true)");
        return bitmapCreateBitmap;
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean checked) {
        super.setChecked(checked);
        invalidate();
    }

    private final float getGravityFactor(int axis) {
        int i = ((this.drawGravity >> axis) & 15) ^ 1;
        if (i != 0) {
            return (i == 2 || i != 4) ? 0.0f : 1.0f;
        }
        return 0.5f;
    }
}
