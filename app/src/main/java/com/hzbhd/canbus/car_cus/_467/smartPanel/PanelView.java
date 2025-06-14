package com.hzbhd.canbus.car_cus._467.smartPanel;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;
import androidx.core.content.ContextCompat;
import com.hzbhd.canbus.R;

/* loaded from: classes2.dex */
public class PanelView extends Button {
    protected int id_n;
    protected int id_p;

    public PanelView(Context context) {
        this(context, null);
    }

    public PanelView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PanelView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.id_n = 0;
        this.id_p = 0;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.img_attr);
        this.id_n = typedArrayObtainStyledAttributes.getResourceId(2, 0);
        this.id_p = typedArrayObtainStyledAttributes.getResourceId(3, 0);
        setSelect(context, false);
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        super.setBackground(drawable);
    }

    public void setSelect(Context context, boolean z) {
        if (z) {
            int i = this.id_p;
            if (i != 0) {
                setBackground(ContextCompat.getDrawable(context, i));
                return;
            }
            return;
        }
        int i2 = this.id_n;
        if (i2 != 0) {
            setBackground(ContextCompat.getDrawable(context, i2));
        }
    }

    public void setImg(Drawable drawable) {
        if (this.id_n != 0) {
            setBackground(drawable);
        }
    }
}
