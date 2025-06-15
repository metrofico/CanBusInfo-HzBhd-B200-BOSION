package com.hzbhd.ui.view.colorview;

import android.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;

import androidx.core.content.ContextCompat;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;


public final class PressedDrawable extends StateListDrawable {
    public PressedDrawable(Context context, int i, int i2, int i3, int i4) {

        if (i2 != 0) {
            addState(new int[]{R.attr.state_pressed}, ContextCompat.getDrawable(context, i2));
        }
        if (i3 != 0) {
            addState(new int[]{R.attr.state_selected}, ContextCompat.getDrawable(context, i3));
        }
        if (i4 != 0) {
            addState(new int[]{-16842910}, ContextCompat.getDrawable(context, i4));
        }
        if (i != 0) {
            addState(new int[]{-16842919}, ContextCompat.getDrawable(context, i));
        }
    }

    public PressedDrawable(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable2 != null) {
            addState(new int[]{R.attr.state_pressed}, drawable2);
        }
        if (drawable3 != null) {
            addState(new int[]{R.attr.state_selected}, drawable3);
        }
        if (drawable4 != null) {
            addState(new int[]{-16842910}, drawable4);
        }
        if (drawable != null) {
            addState(new int[]{-16842919}, drawable);
        }
    }
}
