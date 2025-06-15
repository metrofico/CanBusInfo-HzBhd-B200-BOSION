package com.hzbhd.ui.view.paged;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.hzbhd.ui.view.lifecycle.BaseLifeFrameLayout;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;


public abstract class CellChild extends BaseLifeFrameLayout {
    private static final String TAG = "CellChild";
    private float downX;
    private float downY;
    private boolean event_move;
    private boolean isLongClick;
    private boolean isThisTouch;
    private final Runnable longClickRunnable;
    private int page;
    private int pageX;
    private int pageY;
    private int xsize;
    private int ysize;

    protected void onClick() {
    }

    protected void onLongClick() {
    }

    public final int getPageX() {
        return this.pageX;
    }

    protected final void setPageX(int i) {
        this.pageX = i;
    }

    public final int getPageY() {
        return this.pageY;
    }

    protected final void setPageY(int i) {
        this.pageY = i;
    }

    public final int getPage() {
        return this.page;
    }

    public final void setPage(int i) {
        this.page = i;
    }

    public final int getXsize() {
        return this.xsize;
    }

    protected final void setXsize(int i) {
        this.xsize = i;
    }

    public final int getYsize() {
        return this.ysize;
    }

    protected final void setYsize(int i) {
        this.ysize = i;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CellChild(Context context) {
        super(context);

        this.xsize = 1;
        this.ysize = 1;
        this.longClickRunnable = new Runnable() { // from class: com.hzbhd.ui.view.paged.CellChild$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                isLongClick = true;
                onLongClick();
            }
        };
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CellChild(Context context, AttributeSet attrs) {
        super(context, attrs);


        this.xsize = 1;
        this.ysize = 1;
        this.longClickRunnable = new Runnable() { // from class: com.hzbhd.ui.view.paged.CellChild$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                isLongClick = true;
                onLongClick();
            }
        };
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CellChild(Context context, AttributeSet attrs, int i) {
        super(context, attrs, i);


        this.xsize = 1;
        this.ysize = 1;
        this.longClickRunnable = new Runnable() { // from class: com.hzbhd.ui.view.paged.CellChild$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                isLongClick = true;
                onLongClick();
            }
        };
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CellChild(Context context, AttributeSet attrs, int i, int i2) {
        super(context, attrs, i, i2);


        this.xsize = 1;
        this.ysize = 1;
        this.longClickRunnable = new Runnable() { // from class: com.hzbhd.ui.view.paged.CellChild$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                isLongClick = true;
                onLongClick();
            }
        };
    }

    public final void setLoc(int x, int y) {
        this.pageX = x;
        this.pageY = y;
    }

    public final void setSize(int x_size, int y_size) {
        this.xsize = x_size;
        this.ysize = y_size;
    }

    public final boolean isCover(int x, int y, int xSize, int ySize) {
        int i = this.pageX;
        if (this.xsize + i < x + 1 || x + xSize < i + 1) {
            return false;
        }
        int i2 = this.pageY;
        return this.ysize + i2 >= y + 1 && y + ySize >= i2 + 1;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        this.isThisTouch = true;
        this.isLongClick = false;
        setPressed(true);
        postDelayed(this.longClickRunnable, 500L);
        return super.onInterceptTouchEvent(ev);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: longClickRunnable$lambda-0, reason: not valid java name */


    @Override // android.view.View
    public void setPressed(boolean pressed) {
        super.setPressed(pressed);
        Log.d(TAG, "setPressed: " + pressed);
    }

    public final void onTouch(MotionEvent ev) {

        if (ev.getAction() == 0) {
            this.isThisTouch = false;
            this.event_move = false;
            this.downX = ev.getRawX();
            this.downY = ev.getRawY();
            return;
        }
        if (this.isThisTouch) {
            if (ev.getAction() != 1 && ev.getAction() != 3) {
                if (ev.getRawX() == this.downX) {
                    return;
                }
                if (ev.getRawY() == this.downY) {
                    return;
                }
                setPressed(false);
                this.event_move = true;
                removeCallbacks(this.longClickRunnable);
                return;
            }
            setPressed(false);
            removeCallbacks(this.longClickRunnable);
            if (this.event_move || !this.isThisTouch) {
                return;
            }
            if (!this.isLongClick) {
                onClick();
            }
            this.isThisTouch = false;
        }
    }
}
