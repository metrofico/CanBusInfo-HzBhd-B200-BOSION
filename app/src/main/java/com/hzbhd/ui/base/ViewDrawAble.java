package com.hzbhd.ui.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import com.hzbhd.canbus.R;


public final class ViewDrawAble implements BhdViewUtil.ViewUtilListener {
    private int focusFg;
    private PressedDrawable focusFgDrawable;
    private int focusId;
    private final int id_d;
    private final int id_d1;
    private final int id_n;
    private final int id_n1;
    private final int id_p;
    private final int id_p1;
    private final int id_s;
    private final int id_s1;
    private final View mView;
    private PressedDrawable pressedDrawable;
    private PressedDrawable pressedDrawable1;

    public ViewDrawAble(View view, Context context, AttributeSet attrs) {
        this.mView = view;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.color_attr);
        this.focusId = typedArrayObtainStyledAttributes.getInt(R.styleable.color_attr_focusid, 0);
        this.focusFg = typedArrayObtainStyledAttributes.getResourceId(R.styleable.color_attr_focusFg, 0);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.color_attr_n, 0);
        this.id_n = resourceId;
        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.color_attr_p, 0);
        this.id_p = resourceId2;
        int resourceId3 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.color_attr_s, 0);
        this.id_s = resourceId3;
        int resourceId4 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.color_attr_d, 0);
        this.id_d = resourceId4;
        if (resourceId != 0) {
            this.pressedDrawable = new PressedDrawable(context, resourceId, resourceId2, resourceId3, resourceId4);
        }
        int resourceId5 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.color_attr_n1, 0);
        this.id_n1 = resourceId5;
        int resourceId6 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.color_attr_p1, 0);
        this.id_p1 = resourceId6;
        int resourceId7 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.color_attr_s1, 0);
        this.id_s1 = resourceId7;
        int resourceId8 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.color_attr_d1, 0);
        this.id_d1 = resourceId8;
        if (resourceId5 != 0) {
            this.pressedDrawable1 = new PressedDrawable(context, resourceId5, resourceId6, resourceId7, resourceId8);
        }
        if (this.focusFg != 0) {
            this.focusFgDrawable = new PressedDrawable(context, R.drawable.transparent, 0, this.focusFg, 0);
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public final int getFocusId() {
        return this.focusId;
    }

    public final void setFocusId(int i) {
        this.focusId = i;
    }

    public final int getFocusFg() {
        return this.focusFg;
    }

    public final void setFocusFg(int i) {
        this.focusFg = i;
    }

    public final PressedDrawable getPressedDrawable() {
        return this.pressedDrawable;
    }

    public final void setPressedDrawable(PressedDrawable pressedDrawable) {
        this.pressedDrawable = pressedDrawable;
    }

    public final PressedDrawable getPressedDrawable1() {
        return this.pressedDrawable1;
    }

    public final void setPressedDrawable1(PressedDrawable pressedDrawable) {
        this.pressedDrawable1 = pressedDrawable;
    }

    public final PressedDrawable getFocusFgDrawable() {
        return this.focusFgDrawable;
    }

    public final void setFocusFgDrawable(PressedDrawable pressedDrawable) {
        this.focusFgDrawable = pressedDrawable;
    }

    public void resetBackground() {
        if (BhdViewUtil.INSTANCE.getColorStyle() == 1) {
            PressedDrawable pressedDrawable = this.focusFgDrawable;
            if (pressedDrawable != null) {
                this.mView.setForeground(pressedDrawable);
            }
            PressedDrawable pressedDrawable2 = this.pressedDrawable1;
            if (pressedDrawable2 == null) {
                return;
            }
            this.mView.setBackground(pressedDrawable2);
            return;
        }
        PressedDrawable pressedDrawable3 = this.focusFgDrawable;
        if (pressedDrawable3 != null) {
            this.mView.setForeground(pressedDrawable3);
        }
        PressedDrawable pressedDrawable4 = this.pressedDrawable;
        if (pressedDrawable4 == null) {
            return;
        }
        this.mView.setBackground(pressedDrawable4);
    }

    @Override // com.hzbhd.ui.base.BhdViewUtil.ViewUtilListener
    public void onColorChange() {
        resetBackground();
    }

    @Override // com.hzbhd.ui.base.BhdViewUtil.ViewUtilListener
    public void onFocusChange(boolean isFocus) {
        if (this.mView.isSelected() != isFocus) {
            this.mView.setSelected(isFocus);
        }
    }

    public final void onAttachedToWindow() {
        BhdViewUtil.INSTANCE.addListener(this, this.focusId);
    }

    public final void onDetachedFromWindow() {
        BhdViewUtil.INSTANCE.removeListener(this, this.focusId);
    }
}
