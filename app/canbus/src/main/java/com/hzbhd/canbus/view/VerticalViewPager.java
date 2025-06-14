package com.hzbhd.canbus.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.viewpager.widget.ViewPager;

/* loaded from: classes2.dex */
public class VerticalViewPager extends ViewPager {
    private boolean mScrollAble;

    public VerticalViewPager(Context context) {
        super(context);
        this.mScrollAble = true;
    }

    public VerticalViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mScrollAble = true;
        setPageTransformer(true, new VerticalTransformer());
    }

    public void setScrollEnabled(boolean z) {
        this.mScrollAble = z;
    }

    private MotionEvent swapTouchEvent(MotionEvent motionEvent) {
        float width = getWidth();
        float height = getHeight();
        motionEvent.setLocation((motionEvent.getY() / height) * width, (motionEvent.getX() / width) * height);
        return motionEvent;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.mScrollAble) {
            return super.onInterceptTouchEvent(swapTouchEvent(MotionEvent.obtain(motionEvent)));
        }
        return false;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mScrollAble) {
            return super.onTouchEvent(swapTouchEvent(MotionEvent.obtain(motionEvent)));
        }
        return false;
    }
}
