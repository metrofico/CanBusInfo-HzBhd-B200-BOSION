package com.hzbhd.ui.view.colorview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.hzbhd.ui.view.R;
import com.hzbhd.ui.view.colorview.ColorUtil;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;


@SuppressLint("AppCompatCustomView")
public class ColorView extends TextView implements ColorUtil.ColorViewInterface {
    private int id_d;
    private int id_n;
    private int id_p;
    private int id_s;
    private boolean isColorImage;
    private int mix_type;
    private int n_mix_color;
    private int p_mix_color;

    /* renamed from: isColorImage, reason: from getter */
    protected final boolean getIsColorImage() {
        return this.isColorImage;
    }

    protected final void setColorImage(boolean z) {
        this.isColorImage = z;
    }

    protected final int getId_n() {
        return this.id_n;
    }

    protected final void setId_n(int i) {
        this.id_n = i;
    }

    protected final int getId_p() {
        return this.id_p;
    }

    protected final void setId_p(int i) {
        this.id_p = i;
    }

    protected final int getId_s() {
        return this.id_s;
    }

    protected final void setId_s(int i) {
        this.id_s = i;
    }

    protected final int getId_d() {
        return this.id_d;
    }

    protected final void setId_d(int i) {
        this.id_d = i;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ColorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        init(context, attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ColorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);

        init(context, attributeSet);
    }

    private final void init(Context context, AttributeSet attrs) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.color_attr);
        boolean z = typedArrayObtainStyledAttributes.getBoolean(R.styleable.color_attr_is_color_view, false);
        this.isColorImage = z;
        if (z) {
            this.mix_type = typedArrayObtainStyledAttributes.getInt(R.styleable.color_attr_mix_type, 0);
            this.n_mix_color = typedArrayObtainStyledAttributes.getColor(R.styleable.color_attr_n_mix_color, 0);
            this.p_mix_color = typedArrayObtainStyledAttributes.getColor(R.styleable.color_attr_p_mix_color, 0);
        } else {
            this.id_n = typedArrayObtainStyledAttributes.getResourceId(R.styleable.color_attr_n, 0);
            this.id_p = typedArrayObtainStyledAttributes.getResourceId(R.styleable.color_attr_p, 0);
            this.id_s = typedArrayObtainStyledAttributes.getResourceId(R.styleable.color_attr_s, 0);
            int resourceId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.color_attr_d, 0);
            this.id_d = resourceId;
            if (this.id_n != 0 || this.id_p != 0 || this.id_s != 0 || resourceId != 0) {
                setBackground(new PressedDrawable(context, this.id_n, this.id_p, this.id_s, this.id_d));
            }
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.isColorImage) {
            ColorUtil.instance.addColorInterface(this);
        }
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.isColorImage) {
            ColorUtil.instance.removeColorInterface(this);
        }
    }

    @Override // com.hzbhd.ui.view.colorview.ColorUtil.ColorViewInterface
    public void onColorChange() {
        if (getBackground() == null || !this.isColorImage) {
            return;
        }
        ColorUtil colorUtil = ColorUtil.instance;
        Drawable current = getBackground().getCurrent();

        colorUtil.viewStateChange(current, isPressed());
    }

    @Override // android.view.View
    public void setBackground(Drawable background) {

        super.setBackground(background);
        if (this.isColorImage) {
            ColorUtil.instance.viewStateChange(background, isPressed());
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (!this.isColorImage || getBackground() == null) {
            return;
        }
        ColorUtil colorUtil = ColorUtil.instance;
        Drawable current = getBackground().getCurrent();

        colorUtil.viewStateChange(current, isPressed());
    }
}
