package com.hzbhd.canbus.view;

import android.view.View;
import androidx.viewpager.widget.ViewPager;

/* loaded from: classes2.dex */
public class VerticalTransformer implements ViewPager.PageTransformer {
    @Override // androidx.viewpager.widget.ViewPager.PageTransformer
    public void transformPage(View view, float f) {
        if (f < -1.0f) {
            view.setAlpha(0.0f);
        } else {
            if (f <= 1.0f) {
                view.setAlpha(1.0f);
                view.setTranslationX(view.getWidth() * (-f));
                view.setTranslationY(f * view.getHeight());
                return;
            }
            view.setAlpha(0.0f);
        }
    }
}
