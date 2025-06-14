package com.hzbhd.canbus.util;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hzbhd.canbus.R;

/* loaded from: classes2.dex */
public class SystemButton extends RelativeLayout {
    private static final int BUTTOM = 4;
    private static final int LEFT = 0;
    private static final int RIGHT = 1;
    private static final int TOP = 3;
    private static View view;
    private int dpi;
    private boolean isScroll;
    private float mTouchStartX;
    private float mTouchStartY;
    TextView my_P_Key;
    private int screenHeight;
    private int screenWidth;
    private WindowManager wm;
    private WindowManager.LayoutParams wmParams;
    private float x;
    private float y;

    public interface PanoramaListener {
        void clickEvent();
    }

    private int dpi(int i) {
        if (i <= 120) {
            return 36;
        }
        if (i <= 160) {
            return 48;
        }
        if (i <= 240) {
            return 72;
        }
        return i <= 320 ? 96 : 108;
    }

    public SystemButton(Activity activity, String str, final PanoramaListener panoramaListener) {
        super(activity);
        View viewInflate = LayoutInflater.from(activity).inflate(R.layout._panorama_button_view, this);
        view = viewInflate;
        TextView textView = (TextView) viewInflate.findViewById(R.id.my_P_Key);
        this.my_P_Key = textView;
        textView.setText(str);
        this.my_P_Key.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.util.SystemButton.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                panoramaListener.clickEvent();
            }
        });
        this.wm = (WindowManager) activity.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.dpi = dpi(displayMetrics.densityDpi);
        this.screenWidth = this.wm.getDefaultDisplay().getWidth();
        this.screenHeight = this.wm.getDefaultDisplay().getHeight();
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.wmParams = layoutParams;
        layoutParams.type = 2003;
        this.wmParams.format = 1;
        this.wmParams.gravity = 51;
        this.wmParams.flags = 8;
        this.wmParams.width = this.dpi + 90;
        this.wmParams.height = this.dpi + 50;
        this.wmParams.y = (this.screenHeight - this.dpi) >> 1;
        this.wm.addView(this, this.wmParams);
        hide();
    }

    public void show() {
        if (isShown()) {
            return;
        }
        setVisibility(0);
    }

    public void hide() {
        setVisibility(8);
    }

    public void destory() {
        hide();
        this.wm.removeViewImmediate(this);
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
        this.wm.updateViewLayout(this, this.wmParams);
    }

    private void updateViewPosition() {
        this.wmParams.x = (int) (this.x - this.mTouchStartX);
        this.wmParams.y = (int) ((this.y - this.mTouchStartY) - (this.screenHeight / 25));
        this.wm.updateViewLayout(this, this.wmParams);
    }
}
