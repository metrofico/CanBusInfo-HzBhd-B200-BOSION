package com.hzbhd.canbus.car._433;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.hzbhd.R;

/* loaded from: classes2.dex */
public class BluetoothView extends RelativeLayout {
    private static final int BUTTOM = 4;
    private static final int LEFT = 0;
    private static final int RIGHT = 1;
    private static final int TOP = 3;
    private static View view;
    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private Button btn_5;
    private Button btn_6;
    private Button btn_7;
    private Button btn_8;
    private Button btn_9;
    private int dpi;
    private boolean isScroll;
    private float mTouchStartX;
    private float mTouchStartY;
    private int screenHeight;
    private int screenWidth;
    private WindowManager wm;
    private WindowManager.LayoutParams wmParams;
    private float x;
    private float y;

    public interface PanoramaListener {
        void clickEvent1();

        void clickEvent2();

        void clickEvent3();

        void clickEvent4();

        void clickEvent5();

        void clickEvent6();

        void clickEvent7();

        void clickEvent8();

        void clickEvent9();
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

    public BluetoothView(Activity activity, String str, PanoramaListener panoramaListener) {
        super(activity);
        view = LayoutInflater.from(activity).inflate(R.layout._433_bt_button_view, this);
        this.wm = (WindowManager) activity.getSystemService("window");
        buttonClick(panoramaListener);
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
        this.wmParams.width = this.dpi + 300;
        this.wmParams.height = this.dpi + 400;
        this.wmParams.y = (this.screenHeight - this.dpi) >> 1;
        this.wm.addView(this, this.wmParams);
    }

    private void buttonClick(final PanoramaListener panoramaListener) {
        Button button = (Button) view.findViewById(R.id.btn_1);
        this.btn_1 = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car._433.BluetoothView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                panoramaListener.clickEvent1();
            }
        });
        Button button2 = (Button) view.findViewById(R.id.btn_2);
        this.btn_2 = button2;
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car._433.BluetoothView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                panoramaListener.clickEvent2();
            }
        });
        Button button3 = (Button) view.findViewById(R.id.btn_3);
        this.btn_3 = button3;
        button3.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car._433.BluetoothView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                panoramaListener.clickEvent3();
            }
        });
        Button button4 = (Button) view.findViewById(R.id.btn_4);
        this.btn_4 = button4;
        button4.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car._433.BluetoothView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                panoramaListener.clickEvent4();
            }
        });
        Button button5 = (Button) view.findViewById(R.id.btn_5);
        this.btn_5 = button5;
        button5.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car._433.BluetoothView.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                panoramaListener.clickEvent5();
            }
        });
        Button button6 = (Button) view.findViewById(R.id.btn_6);
        this.btn_6 = button6;
        button6.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car._433.BluetoothView.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                panoramaListener.clickEvent6();
            }
        });
        Button button7 = (Button) view.findViewById(R.id.btn_7);
        this.btn_7 = button7;
        button7.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car._433.BluetoothView.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                panoramaListener.clickEvent7();
            }
        });
        Button button8 = (Button) view.findViewById(R.id.btn_8);
        this.btn_8 = button8;
        button8.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car._433.BluetoothView.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                panoramaListener.clickEvent8();
            }
        });
        Button button9 = (Button) view.findViewById(R.id.btn_9);
        this.btn_9 = button9;
        button9.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car._433.BluetoothView.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                BluetoothView.this.hide();
                panoramaListener.clickEvent9();
            }
        });
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
