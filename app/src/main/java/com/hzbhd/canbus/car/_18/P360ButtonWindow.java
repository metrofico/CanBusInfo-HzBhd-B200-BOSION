package com.hzbhd.canbus.car._18;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import com.hzbhd.R;


public class P360ButtonWindow extends RelativeLayout {
    private static final int BUTTOM = 4;
    private static final int LEFT = 0;
    private static final int RIGHT = 1;
    private static final int TOP = 3;
    private static View view;
    private int dpi;
    private boolean isScroll;
    private PanoramaListener listener;
    private float mTouchStartX;
    private float mTouchStartY;
    private int screenHeight;
    private int screenWidth;
    private boolean showTag;
    private WindowManager windowManager;
    private WindowManager.LayoutParams wmParams;
    private float x;
    private float y;

    public interface PanoramaListener {
        void clickEvent();
    }

    public P360ButtonWindow(Context context, PanoramaListener panoramaListener) {
        super(context);
        this.showTag = false;
        this.listener = panoramaListener;
        view = LayoutInflater.from(context).inflate(R.layout._18_p360_window, this);
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        this.windowManager = windowManager;
        this.dpi = 108;
        this.screenWidth = windowManager.getDefaultDisplay().getWidth();
        this.screenHeight = this.windowManager.getDefaultDisplay().getHeight();
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.wmParams = layoutParams;
        layoutParams.type = 2010;
        this.wmParams.format = 1;
        this.wmParams.gravity = 51;
        this.wmParams.flags = 8;
        this.wmParams.width = this.dpi + 50;
        this.wmParams.height = this.dpi + 50;
        this.wmParams.y = 860 - (this.dpi + 50);
        this.wmParams.x = 1920 - (this.dpi + 50);
    }

    public boolean getShowTag() {
        return this.showTag;
    }

    public void show() {
        this.showTag = true;
        this.windowManager.addView(this, this.wmParams);
    }

    public void hide() {
        this.showTag = false;
        this.windowManager.removeViewImmediate(this);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.x = motionEvent.getRawX();
        this.y = motionEvent.getRawY();
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mTouchStartX = motionEvent.getX();
            this.mTouchStartY = motionEvent.getY();
        } else if (action != 1) {
            if (action == 2 && (this.isScroll || Math.abs(this.mTouchStartX - motionEvent.getX()) > this.dpi / 3 || Math.abs(this.mTouchStartY - motionEvent.getY()) > this.dpi / 3)) {
                updateViewPosition();
                this.isScroll = true;
            }
        } else {
            if (this.isScroll) {
                autoView();
            } else {
                this.listener.clickEvent();
            }
            this.isScroll = false;
            this.mTouchStartY = 0.0f;
            this.mTouchStartX = 0.0f;
        }
        return super.onTouchEvent(motionEvent);
    }

    private void autoView() {
        int[] iArr = new int[2];
        getLocationOnScreen(iArr);
        if (iArr[0] < (this.screenWidth / 2) - (getWidth() / 2)) {
            updateViewPosition(0);
        } else {
            updateViewPosition(1);
        }
    }

    private void updateViewPosition(int i) {
        if (i == 0) {
            this.wmParams.x = 0;
        } else if (i == 1) {
            this.wmParams.x = this.screenWidth - this.dpi;
        } else if (i == 3) {
            this.wmParams.y = 0;
        } else if (i == 4) {
            this.wmParams.y = this.screenHeight - this.dpi;
        }
        this.windowManager.updateViewLayout(this, this.wmParams);
    }

    private void updateViewPosition() {
        this.wmParams.x = (int) (this.x - this.mTouchStartX);
        this.wmParams.y = (int) ((this.y - this.mTouchStartY) - (this.screenHeight / 25));
        this.windowManager.updateViewLayout(this, this.wmParams);
    }
}
