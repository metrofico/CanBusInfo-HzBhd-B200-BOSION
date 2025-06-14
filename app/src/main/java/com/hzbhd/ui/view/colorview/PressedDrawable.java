package com.hzbhd.ui.view.colorview;

import android.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import androidx.core.content.ContextCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PressedDrawable.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B/\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0002\u0010\tB/\b\u0016\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/hzbhd/ui/view/colorview/PressedDrawable;", "Landroid/graphics/drawable/StateListDrawable;", "context", "Landroid/content/Context;", "nId", "", "pId", "sId", "dId", "(Landroid/content/Context;IIII)V", "n", "Landroid/graphics/drawable/Drawable;", "p", "s", "d", "(Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;)V", "commonview-base_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes3.dex */
public final class PressedDrawable extends StateListDrawable {
    public PressedDrawable(Context context, int i, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(context, "context");
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
