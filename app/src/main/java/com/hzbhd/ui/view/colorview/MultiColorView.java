package com.hzbhd.ui.view.colorview;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.ContentObserver;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.hzbhd.ui.view.R;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MultiColorView.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u000e\b\u0016\u0018\u00002\u00020\u0001:\u00018B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tB%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b¢\u0006\u0002\u0010\u000bJ\u0010\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\"H\u0014J\u0012\u0010'\u001a\u00020%2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\u0018\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\b2\u0006\u0010-\u001a\u00020\bH\u0002J\b\u0010.\u001a\u00020\"H\u0016J0\u0010/\u001a\u00020%2\u0006\u00100\u001a\u00020\"2\u0006\u00101\u001a\u00020\b2\u0006\u00102\u001a\u00020\b2\u0006\u00103\u001a\u00020\b2\u0006\u00104\u001a\u00020\bH\u0014J\b\u00105\u001a\u00020%H\u0002J\u000e\u00106\u001a\u00020%2\u0006\u00107\u001a\u00020\bR\u000e\u0010\f\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u001bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001dR\u000e\u0010\u001e\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010#\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u001bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001d¨\u00069"}, d2 = {"Lcom/hzbhd/ui/view/colorview/MultiColorView;", "Landroid/widget/TextView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "drawBottom", "drawFgBottom", "drawFgGravity", "drawFgHeight", "drawFgLeft", "drawFgRight", "drawFgTop", "drawFgWidth", "drawGravity", "drawHeight", "drawLeft", "drawRight", "drawTop", "drawWidth", "fgResIdArray", "", "Lcom/hzbhd/ui/view/colorview/MultiColorView$ResId;", "[Lcom/hzbhd/ui/view/colorview/MultiColorView$ResId;", "gradientEndColor", "gradientStartColor", "index", "isColorView", "", "resIdArray", "dispatchSetPressed", "", "pressed", "draw", "canvas", "Landroid/graphics/Canvas;", "getGravityFactor", "", "gravity", "axis", "isFocused", "onLayout", "changed", "left", "top", "right", "bottom", "setBackground", "setIndex", "value", "ResId", "commonview-base_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes3.dex */
public class MultiColorView extends TextView {
    private final int drawBottom;
    private final int drawFgBottom;
    private final int drawFgGravity;
    private final int drawFgHeight;
    private final int drawFgLeft;
    private final int drawFgRight;
    private final int drawFgTop;
    private final int drawFgWidth;
    private final int drawGravity;
    private final int drawHeight;
    private final int drawLeft;
    private final int drawRight;
    private final int drawTop;
    private final int drawWidth;
    private final ResId[] fgResIdArray;
    private final int gradientEndColor;
    private final int gradientStartColor;
    private int index;
    private final boolean isColorView;
    private final ResId[] resIdArray;

    private final float getGravityFactor(int gravity, int axis) {
        int i = ((gravity >> axis) & 15) ^ 1;
        if (i != 0) {
            return (i == 2 || i != 4) ? 0.0f : 1.0f;
        }
        return 0.5f;
    }

    @Override // android.view.View
    public boolean isFocused() {
        return true;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MultiColorView(final Context context, AttributeSet attrs, int i, int i2) {
        super(context, attrs, i, i2);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        ResId[] resIdArr = new ResId[4];
        for (int i3 = 0; i3 < 4; i3++) {
            resIdArr[i3] = null;
        }
        this.resIdArray = resIdArr;
        ResId[] resIdArr2 = new ResId[4];
        for (int i4 = 0; i4 < 4; i4++) {
            resIdArr2[i4] = null;
        }
        this.fgResIdArray = resIdArr2;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.gradientAttr);
        this.gradientStartColor = typedArrayObtainStyledAttributes.getColor(R.styleable.gradientAttr_gradient_startColor, -1);
        this.gradientEndColor = typedArrayObtainStyledAttributes.getColor(R.styleable.gradientAttr_gradient_endColor, -1);
        typedArrayObtainStyledAttributes.recycle();
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attrs, R.styleable.color_attr);
        boolean z = typedArrayObtainStyledAttributes2.getBoolean(R.styleable.color_attr_is_color_view, true);
        this.isColorView = z;
        this.resIdArray[0] = new ResId(typedArrayObtainStyledAttributes2.getResourceId(R.styleable.color_attr_n, 0), typedArrayObtainStyledAttributes2.getResourceId(R.styleable.color_attr_p, 0), typedArrayObtainStyledAttributes2.getResourceId(R.styleable.color_attr_s, 0), typedArrayObtainStyledAttributes2.getResourceId(R.styleable.color_attr_d, 0));
        this.drawGravity = typedArrayObtainStyledAttributes2.getInt(R.styleable.color_attr_draw_gravity, 51);
        this.drawLeft = typedArrayObtainStyledAttributes2.getDimensionPixelOffset(R.styleable.color_attr_draw_left, 0);
        this.drawTop = typedArrayObtainStyledAttributes2.getDimensionPixelOffset(R.styleable.color_attr_draw_top, 0);
        this.drawRight = typedArrayObtainStyledAttributes2.getDimensionPixelOffset(R.styleable.color_attr_draw_right, 0);
        this.drawBottom = typedArrayObtainStyledAttributes2.getDimensionPixelOffset(R.styleable.color_attr_draw_bottom, 0);
        this.drawWidth = typedArrayObtainStyledAttributes2.getDimensionPixelOffset(R.styleable.color_attr_draw_width, 0);
        this.drawHeight = typedArrayObtainStyledAttributes2.getDimensionPixelOffset(R.styleable.color_attr_draw_height, 0);
        this.drawFgGravity = typedArrayObtainStyledAttributes2.getInt(R.styleable.color_attr_draw_fg_gravity, 51);
        this.drawFgLeft = typedArrayObtainStyledAttributes2.getDimensionPixelOffset(R.styleable.color_attr_draw_fg_left, 0);
        this.drawFgTop = typedArrayObtainStyledAttributes2.getDimensionPixelOffset(R.styleable.color_attr_draw_fg_top, 0);
        this.drawFgRight = typedArrayObtainStyledAttributes2.getDimensionPixelOffset(R.styleable.color_attr_draw_fg_right, 0);
        this.drawFgBottom = typedArrayObtainStyledAttributes2.getDimensionPixelOffset(R.styleable.color_attr_draw_fg_bottom, 0);
        this.drawFgWidth = typedArrayObtainStyledAttributes2.getDimensionPixelOffset(R.styleable.color_attr_draw_fg_width, 0);
        this.drawFgHeight = typedArrayObtainStyledAttributes2.getDimensionPixelOffset(R.styleable.color_attr_draw_fg_height, 0);
        typedArrayObtainStyledAttributes2.recycle();
        TypedArray typedArrayObtainStyledAttributes3 = context.obtainStyledAttributes(attrs, R.styleable.radioButtonAttrs);
        resIdArr2[0] = new ResId(typedArrayObtainStyledAttributes3.getResourceId(R.styleable.radioButtonAttrs_min_fg, 0), typedArrayObtainStyledAttributes3.getResourceId(R.styleable.radioButtonAttrs_min_fg_p, 0), 0, 0, 12, null);
        typedArrayObtainStyledAttributes3.recycle();
        TypedArray typedArrayObtainStyledAttributes4 = context.obtainStyledAttributes(attrs, R.styleable.multi_color_attr);
        this.resIdArray[1] = new ResId(typedArrayObtainStyledAttributes4.getResourceId(R.styleable.multi_color_attr_n2, 0), typedArrayObtainStyledAttributes4.getResourceId(R.styleable.multi_color_attr_p2, 0), typedArrayObtainStyledAttributes4.getResourceId(R.styleable.multi_color_attr_s2, 0), typedArrayObtainStyledAttributes4.getResourceId(R.styleable.multi_color_attr_d2, 0));
        this.resIdArray[2] = new ResId(typedArrayObtainStyledAttributes4.getResourceId(R.styleable.multi_color_attr_n3, 0), typedArrayObtainStyledAttributes4.getResourceId(R.styleable.multi_color_attr_p3, 0), typedArrayObtainStyledAttributes4.getResourceId(R.styleable.multi_color_attr_s3, 0), typedArrayObtainStyledAttributes4.getResourceId(R.styleable.multi_color_attr_d3, 0));
        this.resIdArray[3] = new ResId(typedArrayObtainStyledAttributes4.getResourceId(R.styleable.multi_color_attr_n4, 0), typedArrayObtainStyledAttributes4.getResourceId(R.styleable.multi_color_attr_p4, 0), typedArrayObtainStyledAttributes4.getResourceId(R.styleable.multi_color_attr_s4, 0), typedArrayObtainStyledAttributes4.getResourceId(R.styleable.multi_color_attr_d4, 0));
        typedArrayObtainStyledAttributes4.recycle();
        TypedArray typedArrayObtainStyledAttributes5 = context.obtainStyledAttributes(attrs, R.styleable.fontAttr);
        String string = typedArrayObtainStyledAttributes5.getString(R.styleable.fontAttr_font_path);
        if (string != null) {
            setTypeface(Typeface.createFromAsset(context.getAssets(), string));
        }
        typedArrayObtainStyledAttributes5.recycle();
        if (!z) {
            setBackground();
        }
        context.getContentResolver().registerContentObserver(Settings.System.getUriFor("changeAllColor"), false, new ContentObserver(new Handler(Looper.getMainLooper())) { // from class: com.hzbhd.ui.view.colorview.MultiColorView.2
            @Override // android.database.ContentObserver
            public void onChange(boolean selfChange) {
                super.onChange(selfChange);
                this.setIndex(Settings.System.getInt(context.getContentResolver(), "changeAllColor", 0));
            }
        });
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public MultiColorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public MultiColorView(Context context, AttributeSet attrs, int i) {
        this(context, attrs, i, 0);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
    }

    public final void setIndex(int value) {
        if (value >= this.resIdArray.length || value >= this.fgResIdArray.length) {
            return;
        }
        this.index = value;
        if (this.isColorView) {
            postInvalidate();
        } else {
            setBackground();
        }
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        Integer numValueOf;
        if (this.isColorView) {
            ResId resId = this.resIdArray[this.index];
            Integer numValueOf2 = null;
            if (isPressed() && resId != null && resId.getP() != 0) {
                numValueOf = Integer.valueOf(resId.getP());
            } else if (isSelected() && resId != null && resId.getS() != 0) {
                numValueOf = Integer.valueOf(resId.getS());
            } else if (isEnabled() || resId == null || resId.getD() == 0) {
                numValueOf = (resId == null || resId.getN() == 0) ? null : Integer.valueOf(resId.getN());
            } else {
                numValueOf = Integer.valueOf(resId.getD());
            }
            if (numValueOf != null) {
                Drawable drawable = ContextCompat.getDrawable(getContext(), numValueOf.intValue());
                if (this.drawWidth != 0 && this.drawHeight != 0) {
                    int gravityFactor = ((int) ((getGravityFactor(this.drawGravity, 0) * (getMeasuredWidth() - this.drawWidth)) + this.drawLeft)) - this.drawRight;
                    float gravityFactor2 = getGravityFactor(this.drawGravity, 4);
                    int measuredHeight = getMeasuredHeight();
                    int i = this.drawHeight;
                    int i2 = ((int) ((gravityFactor2 * (measuredHeight - i)) + this.drawTop)) - this.drawBottom;
                    if (drawable != null) {
                        drawable.setBounds(gravityFactor, i2, this.drawWidth + gravityFactor, i + i2);
                    }
                } else if (drawable != null) {
                    drawable.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
                }
                if (drawable != null) {
                    Intrinsics.checkNotNull(canvas);
                    drawable.draw(canvas);
                }
            }
            ResId resId2 = this.fgResIdArray[this.index];
            if (isPressed() && resId2 != null && resId2.getP() != 0) {
                numValueOf2 = Integer.valueOf(resId2.getP());
            } else if (isSelected() && resId2 != null && resId2.getS() != 0) {
                numValueOf2 = Integer.valueOf(resId2.getS());
            } else if (!isEnabled() && resId2 != null && resId2.getD() != 0) {
                numValueOf2 = Integer.valueOf(resId2.getD());
            } else if (resId2 != null && resId2.getN() != 0) {
                numValueOf2 = Integer.valueOf(resId2.getN());
            }
            if (numValueOf2 != null) {
                Drawable drawable2 = ContextCompat.getDrawable(getContext(), numValueOf2.intValue());
                if (this.drawFgWidth != 0 && this.drawFgHeight != 0) {
                    int gravityFactor3 = ((int) ((getGravityFactor(this.drawFgGravity, 0) * (getMeasuredWidth() - this.drawFgWidth)) + this.drawFgLeft)) - this.drawFgRight;
                    float gravityFactor4 = getGravityFactor(this.drawFgGravity, 4);
                    int measuredHeight2 = getMeasuredHeight();
                    int i3 = this.drawFgHeight;
                    int i4 = ((int) ((gravityFactor4 * (measuredHeight2 - i3)) + this.drawFgTop)) - this.drawFgBottom;
                    if (drawable2 != null) {
                        drawable2.setBounds(gravityFactor3, i4, this.drawFgWidth + gravityFactor3, i3 + i4);
                    }
                } else if (drawable2 != null) {
                    drawable2.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
                }
                if (drawable2 != null) {
                    Intrinsics.checkNotNull(canvas);
                    drawable2.draw(canvas);
                }
            }
        }
        if (this.gradientStartColor != -1 && this.gradientEndColor != -1) {
            getPaint().setShader(new LinearGradient(0.0f, 0.0f, 0.0f, getTextSize(), new int[]{this.gradientStartColor, this.gradientEndColor}, new float[]{0.5f, 1.0f}, Shader.TileMode.CLAMP));
        }
        super.draw(canvas);
    }

    @Override // android.view.View
    protected void dispatchSetPressed(boolean pressed) {
        super.dispatchSetPressed(pressed);
        postInvalidate();
    }

    @Override // android.widget.TextView, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (TextUtils.isEmpty(getText())) {
            return;
        }
        setText(getText());
    }

    private final void setBackground() {
        ResId resId = this.resIdArray[this.index];
        if (resId == null || resId.getN() == 0) {
            return;
        }
        setBackground(ContextCompat.getDrawable(getContext(), resId.getN()));
    }

    /* compiled from: MultiColorView.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J1\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0018"}, d2 = {"Lcom/hzbhd/ui/view/colorview/MultiColorView$ResId;", "", "n", "", "p", "s", "d", "(IIII)V", "getD", "()I", "getN", "getP", "getS", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "commonview-base_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    private static final /* data */ class ResId {
        private final int d;
        private final int n;
        private final int p;
        private final int s;

        public static /* synthetic */ ResId copy$default(ResId resId, int i, int i2, int i3, int i4, int i5, Object obj) {
            if ((i5 & 1) != 0) {
                i = resId.n;
            }
            if ((i5 & 2) != 0) {
                i2 = resId.p;
            }
            if ((i5 & 4) != 0) {
                i3 = resId.s;
            }
            if ((i5 & 8) != 0) {
                i4 = resId.d;
            }
            return resId.copy(i, i2, i3, i4);
        }

        /* renamed from: component1, reason: from getter */
        public final int getN() {
            return this.n;
        }

        /* renamed from: component2, reason: from getter */
        public final int getP() {
            return this.p;
        }

        /* renamed from: component3, reason: from getter */
        public final int getS() {
            return this.s;
        }

        /* renamed from: component4, reason: from getter */
        public final int getD() {
            return this.d;
        }

        public final ResId copy(int n, int p, int s, int d) {
            return new ResId(n, p, s, d);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ResId)) {
                return false;
            }
            ResId resId = (ResId) other;
            return this.n == resId.n && this.p == resId.p && this.s == resId.s && this.d == resId.d;
        }

        public int hashCode() {
            return (((((Integer.hashCode(this.n) * 31) + Integer.hashCode(this.p)) * 31) + Integer.hashCode(this.s)) * 31) + Integer.hashCode(this.d);
        }

        public String toString() {
            return "ResId(n=" + this.n + ", p=" + this.p + ", s=" + this.s + ", d=" + this.d + ')';
        }

        public ResId(int i, int i2, int i3, int i4) {
            this.n = i;
            this.p = i2;
            this.s = i3;
            this.d = i4;
        }

        public /* synthetic */ ResId(int i, int i2, int i3, int i4, int i5, DefaultConstructorMarker defaultConstructorMarker) {
            this(i, (i5 & 2) != 0 ? 0 : i2, (i5 & 4) != 0 ? 0 : i3, (i5 & 8) != 0 ? 0 : i4);
        }

        public final int getD() {
            return this.d;
        }

        public final int getN() {
            return this.n;
        }

        public final int getP() {
            return this.p;
        }

        public final int getS() {
            return this.s;
        }
    }
}
