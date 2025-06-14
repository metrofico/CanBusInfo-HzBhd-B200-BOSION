package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.util.AttributeSet;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes2.dex */
public class MyRecyclerView extends RecyclerView {
    private OnScrollChangeListener mOnScrollChangeListener;
    private float moveY;

    public interface OnScrollChangeListener {
        void scrollDown();

        void scrollUp();
    }

    public MyRecyclerView(Context context) {
        this(context, null);
    }

    public MyRecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MyRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x000b, code lost:
    
        if (r0 != 2) goto L24;
     */
    /* JADX WARN: Removed duplicated region for block: B:15:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0033  */
    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r4) {
        /*
            r3 = this;
            int r0 = r4.getAction()
            r1 = 0
            if (r0 == 0) goto Le
            r2 = 1
            if (r0 == r2) goto L20
            r2 = 2
            if (r0 == r2) goto L14
            goto L40
        Le:
            float r0 = r4.getY()
            r3.moveY = r0
        L14:
            float r0 = r3.moveY
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 != 0) goto L20
            float r0 = r4.getY()
            r3.moveY = r0
        L20:
            float r0 = r3.moveY
            float r2 = r4.getY()
            float r0 = r0 - r2
            int r2 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r2 <= 0) goto L33
            com.hzbhd.canbus.car_cus._283.view.MyRecyclerView$OnScrollChangeListener r0 = r3.mOnScrollChangeListener
            if (r0 == 0) goto L3e
            r0.scrollDown()
            goto L3e
        L33:
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 >= 0) goto L3e
            com.hzbhd.canbus.car_cus._283.view.MyRecyclerView$OnScrollChangeListener r0 = r3.mOnScrollChangeListener
            if (r0 == 0) goto L3e
            r0.scrollUp()
        L3e:
            r3.moveY = r1
        L40:
            boolean r4 = super.onTouchEvent(r4)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car_cus._283.view.MyRecyclerView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void setOnScrollChangeListener(OnScrollChangeListener onScrollChangeListener) {
        this.mOnScrollChangeListener = onScrollChangeListener;
    }
}
