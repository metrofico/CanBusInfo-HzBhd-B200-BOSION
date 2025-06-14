package com.hzbhd.ui.base;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;
import com.hzbhd.ui.base.BhdViewUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ViewText.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\u0018\u001a\u00020\u0019J\b\u0010\u001a\u001a\u00020\u0019H\u0016J\u0006\u0010\u001b\u001a\u00020\u0019J\u0010\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0006\u0010\u001f\u001a\u00020\u0019R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/hzbhd/ui/base/ViewText;", "Lcom/hzbhd/ui/base/BhdViewUtil$ViewUtilListener;", "view", "Landroid/widget/TextView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/widget/TextView;Landroid/content/Context;Landroid/util/AttributeSet;)V", "color", "Landroid/content/res/ColorStateList;", "color1", "color_d", "", "color_d1", "color_n", "color_n1", "color_p", "color_p1", "color_s", "color_s1", "mView", "shadowColor", "shadowColor1", "onAttachedToWindow", "", "onColorChange", "onDetachedFromWindow", "onFocusChange", "isFocus", "", "resetTextColor", "bhdview_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ViewText implements BhdViewUtil.ViewUtilListener {
    private ColorStateList color;
    private ColorStateList color1;
    private final int color_d;
    private final int color_d1;
    private final int color_n;
    private final int color_n1;
    private final int color_p;
    private final int color_p1;
    private final int color_s;
    private final int color_s1;
    private final TextView mView;
    private final int shadowColor;
    private final int shadowColor1;

    @Override // com.hzbhd.ui.base.BhdViewUtil.ViewUtilListener
    public void onFocusChange(boolean isFocus) {
    }

    public ViewText(TextView view, Context context, AttributeSet attrs) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        this.mView = view;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.text_color_attr);
        int color = typedArrayObtainStyledAttributes.getColor(R.styleable.text_color_attr_textColor_n, 0);
        this.color_n = color;
        int color2 = typedArrayObtainStyledAttributes.getColor(R.styleable.text_color_attr_textColor_p, color);
        this.color_p = color2;
        int color3 = typedArrayObtainStyledAttributes.getColor(R.styleable.text_color_attr_textColor_s, color);
        this.color_s = color3;
        int color4 = typedArrayObtainStyledAttributes.getColor(R.styleable.text_color_attr_textColor_d, color);
        this.color_d = color4;
        if (color != 0) {
            this.color = new ColorStateList(new int[][]{new int[]{android.R.attr.state_selected}, new int[]{android.R.attr.state_pressed}, new int[]{-16842910}, new int[]{-16842919}, new int[]{-16842913}}, new int[]{color3, color2, color4, color, color});
        }
        int color5 = typedArrayObtainStyledAttributes.getColor(R.styleable.text_color_attr_textColor_n1, 0);
        this.color_n1 = color5;
        int color6 = typedArrayObtainStyledAttributes.getColor(R.styleable.text_color_attr_textColor_p1, color5);
        this.color_p1 = color6;
        int color7 = typedArrayObtainStyledAttributes.getColor(R.styleable.text_color_attr_textColor_s1, color5);
        this.color_s1 = color7;
        int color8 = typedArrayObtainStyledAttributes.getColor(R.styleable.text_color_attr_textColor_d1, color5);
        this.color_d1 = color8;
        if (color5 != 0) {
            this.color1 = new ColorStateList(new int[][]{new int[]{android.R.attr.state_selected}, new int[]{android.R.attr.state_pressed}, new int[]{-16842910}, new int[]{-16842919}, new int[]{-16842913}}, new int[]{color7, color6, color8, color5, color5});
        }
        this.shadowColor = typedArrayObtainStyledAttributes.getColor(R.styleable.text_color_attr_shadowColor, 0);
        this.shadowColor1 = typedArrayObtainStyledAttributes.getColor(R.styleable.text_color_attr_shadowColor1, 0);
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void resetTextColor() {
        if (BhdViewUtil.INSTANCE.getColorStyle() == 1) {
            ColorStateList colorStateList = this.color1;
            if (colorStateList != null) {
                this.mView.setTextColor(colorStateList);
            }
            int i = this.shadowColor1;
            if (i != 0) {
                this.mView.setShadowLayer(5.0f, 0.0f, 2.0f, i);
                return;
            }
            return;
        }
        ColorStateList colorStateList2 = this.color;
        if (colorStateList2 != null) {
            this.mView.setTextColor(colorStateList2);
        }
        int i2 = this.shadowColor;
        if (i2 != 0) {
            this.mView.setShadowLayer(5.0f, 0.0f, 2.0f, i2);
        }
    }

    @Override // com.hzbhd.ui.base.BhdViewUtil.ViewUtilListener
    public void onColorChange() {
        resetTextColor();
    }

    public final void onAttachedToWindow() {
        if (this.color_n1 != 0) {
            BhdViewUtil.addListener$default(BhdViewUtil.INSTANCE, this, 0, 2, null);
        }
    }

    public final void onDetachedFromWindow() {
        if (this.color_n1 != 0) {
            BhdViewUtil.removeListener$default(BhdViewUtil.INSTANCE, this, 0, 2, null);
        }
    }
}
