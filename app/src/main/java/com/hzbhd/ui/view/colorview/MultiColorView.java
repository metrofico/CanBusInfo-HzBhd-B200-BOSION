package com.hzbhd.ui.view.colorview;

import android.annotation.SuppressLint;
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

import com.hzbhd.canbus.R;


@SuppressLint("AppCompatCustomView")
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
        resIdArr2[0] = new ResId(typedArrayObtainStyledAttributes3.getResourceId(R.styleable.radioButtonAttrs_min_fg, 0), typedArrayObtainStyledAttributes3.getResourceId(R.styleable.radioButtonAttrs_min_fg_p, 0), 0, 0);
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
                setIndex(Settings.System.getInt(context.getContentResolver(), "changeAllColor", 0));
            }
        });
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public MultiColorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);


    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public MultiColorView(Context context, AttributeSet attrs, int i) {
        this(context, attrs, i, 0);


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

    public final class ResId {
        private final int n;
        private final int p;
        private final int s;
        private final int d;

        public ResId(int n, int p, int s, int d) {
            this.n = n;
            this.p = p;
            this.s = s;
            this.d = d;
        }

        // Métodos getter
        public int getN() {
            return n;
        }

        public int getP() {
            return p;
        }

        public int getS() {
            return s;
        }

        public int getD() {
            return d;
        }

        // Método copy
        public ResId copy(int n, int p, int s, int d) {
            return new ResId(n, p, s, d);
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ResId)) return false;
            ResId resId = (ResId) o;
            return n == resId.n && p == resId.p && s == resId.s && d == resId.d;
        }


        @Override
        public String toString() {
            return "ResId(n=" + n + ", p=" + p + ", s=" + s + ", d=" + d + ")";
        }
    }
}
