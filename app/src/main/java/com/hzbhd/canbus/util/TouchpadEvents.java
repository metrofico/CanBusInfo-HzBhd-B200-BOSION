package com.hzbhd.canbus.util;

import android.app.AlertDialog;
import android.content.Context;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.hzbhd.R;

/* loaded from: classes2.dex */
public class TouchpadEvents {
    private static int ContinuousValue = 0;
    private static final String ORIGIN_TAG = "ORIGIN";
    private static final String SLIDE_TAG = "SLIDE_TAG";
    private static final String TIME_TAG = "TIME_TAG";
    private static int carDownX = 0;
    private static int carDownY = 0;
    private static CountDownTimer countDownTimer = null;
    private static int firstAction = 0;
    private static int originTag = 0;
    private static int slideTag = 5;
    private static int spareOriginTag = 0;
    private static int spareSlideTag = 5;
    private static int spareTimeTag = 500;
    private static int timeTag = 500;
    private LinearLayout alert_layout;
    private Button cancel;
    private SeekBar data_seekbar;
    private TextView data_textview;
    private Button develop_ok;
    AlertDialog dialog;
    private Button isNot_develop;
    private TextView left_bottom;
    private TextView left_top;
    private Button ok_set;
    private SeekBar operation_seekbar;
    private TextView operation_textview;
    private TextView right_bottom;
    private TextView right_top;

    public interface Events {
        void enter();

        void goDown();

        void goLeft();

        void goRight();

        void goUp();
    }

    public static void putXY(Context context, final int i, final int i2, final Events events) {
        if (firstAction == 0) {
            firstAction = 1;
            initValueAdjust(context);
        }
        if (ContinuousValue == 0) {
            carDownX = i;
            carDownY = i2;
            MyLog.temporaryTracking("按下");
        }
        CountDownTimer countDownTimer2 = countDownTimer;
        if (countDownTimer2 != null) {
            countDownTimer2.cancel();
            ContinuousValue++;
            MyLog.temporaryTracking("坐标连续次数+1");
        }
        int i3 = timeTag;
        CountDownTimer countDownTimer3 = new CountDownTimer(i3, i3) { // from class: com.hzbhd.canbus.util.TouchpadEvents.1
            @Override // android.os.CountDownTimer
            public void onTick(long j) {
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                if (TouchpadEvents.originTag == 0) {
                    TouchpadEvents.leftTop(i, i2, events);
                } else if (TouchpadEvents.originTag == 1) {
                    TouchpadEvents.rightTop(i, i2, events);
                } else if (TouchpadEvents.originTag == 2) {
                    TouchpadEvents.leftBottom(i, i2, events);
                } else if (TouchpadEvents.originTag == 3) {
                    TouchpadEvents.rightBottom(i, i2, events);
                }
                TouchpadEvents.initValueLogic();
            }
        };
        countDownTimer = countDownTimer3;
        countDownTimer3.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void leftTop(int i, int i2, Events events) {
        if (ContinuousValue > slideTag) {
            int i3 = carDownX - i;
            int i4 = carDownY - i2;
            if (Math.abs(i3) > Math.abs(i4)) {
                if (i3 < 0) {
                    events.goRight();
                    return;
                } else {
                    events.goLeft();
                    return;
                }
            }
            if (i4 < 0) {
                events.goDown();
                return;
            } else {
                events.goUp();
                return;
            }
        }
        events.enter();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void rightTop(int i, int i2, Events events) {
        if (ContinuousValue > slideTag) {
            int i3 = carDownX - i;
            int i4 = carDownY - i2;
            if (Math.abs(i3) > Math.abs(i4)) {
                if (i3 < 0) {
                    events.goLeft();
                    return;
                } else {
                    events.goRight();
                    return;
                }
            }
            if (i4 < 0) {
                events.goDown();
                return;
            } else {
                events.goUp();
                return;
            }
        }
        events.enter();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void leftBottom(int i, int i2, Events events) {
        if (ContinuousValue > slideTag) {
            int i3 = carDownX - i;
            int i4 = carDownY - i2;
            if (Math.abs(i3) > Math.abs(i4)) {
                if (i3 < 0) {
                    events.goRight();
                    return;
                } else {
                    events.goLeft();
                    return;
                }
            }
            if (i4 < 0) {
                events.goUp();
                return;
            } else {
                events.goDown();
                return;
            }
        }
        events.enter();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void rightBottom(int i, int i2, Events events) {
        if (ContinuousValue > slideTag) {
            int i3 = carDownX - i;
            int i4 = carDownY - i2;
            if (Math.abs(i3) > Math.abs(i4)) {
                if (i3 < 0) {
                    events.goLeft();
                    return;
                } else {
                    events.goRight();
                    return;
                }
            }
            if (i4 < 0) {
                events.goUp();
                return;
            } else {
                events.goDown();
                return;
            }
        }
        events.enter();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void initValueLogic() {
        carDownX = 0;
        carDownY = 0;
        ContinuousValue = 0;
        countDownTimer.cancel();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void initValueAdjust(Context context) {
        timeTag = SharePreUtil.getIntValue(context, TIME_TAG, 500);
        slideTag = SharePreUtil.getIntValue(context, SLIDE_TAG, 5);
        originTag = SharePreUtil.getIntValue(context, ORIGIN_TAG, 0);
    }

    public void showAdjustView(Context context) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.calibration_touchpad_envents, (ViewGroup) null, true);
        this.dialog = new AlertDialog.Builder(context).setView(viewInflate).create();
        initValueAdjust(context);
        initSpareData();
        onClick(context, viewInflate);
        this.dialog.setCancelable(false);
        this.dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        this.dialog.show();
    }

    private void onClick(final Context context, View view) {
        if (this.develop_ok == null) {
            this.develop_ok = (Button) view.findViewById(R.id.develop_ok);
        }
        if (this.isNot_develop == null) {
            this.isNot_develop = (Button) view.findViewById(R.id.isNot_develop);
        }
        if (this.alert_layout == null) {
            this.alert_layout = (LinearLayout) view.findViewById(R.id.alert_layout);
        }
        if (this.left_top == null) {
            this.left_top = (TextView) view.findViewById(R.id.left_top);
        }
        if (this.right_top == null) {
            this.right_top = (TextView) view.findViewById(R.id.right_top);
        }
        if (this.left_bottom == null) {
            this.left_bottom = (TextView) view.findViewById(R.id.left_bottom);
        }
        if (this.right_bottom == null) {
            this.right_bottom = (TextView) view.findViewById(R.id.right_bottom);
        }
        if (this.data_seekbar == null) {
            this.data_seekbar = (SeekBar) view.findViewById(R.id.data_seekbar);
        }
        if (this.operation_seekbar == null) {
            this.operation_seekbar = (SeekBar) view.findViewById(R.id.operation_seekbar);
        }
        if (this.data_textview == null) {
            this.data_textview = (TextView) view.findViewById(R.id.data_textview);
        }
        if (this.operation_textview == null) {
            this.operation_textview = (TextView) view.findViewById(R.id.operation_textview);
        }
        if (this.ok_set == null) {
            this.ok_set = (Button) view.findViewById(R.id.ok_set);
        }
        if (this.cancel == null) {
            this.cancel = (Button) view.findViewById(R.id.cancel);
        }
        int i = originTag;
        if (i == 0) {
            this.left_top.setBackgroundResource(R.color.orange);
            this.right_top.setBackgroundResource(R.color.transparent);
            this.left_bottom.setBackgroundResource(R.color.transparent);
            this.right_bottom.setBackgroundResource(R.color.transparent);
        } else if (i == 1) {
            this.left_top.setBackgroundResource(R.color.transparent);
            this.right_top.setBackgroundResource(R.color.orange);
            this.left_bottom.setBackgroundResource(R.color.transparent);
            this.right_bottom.setBackgroundResource(R.color.transparent);
        } else if (i == 2) {
            this.left_top.setBackgroundResource(R.color.transparent);
            this.right_top.setBackgroundResource(R.color.transparent);
            this.left_bottom.setBackgroundResource(R.color.orange);
            this.right_bottom.setBackgroundResource(R.color.transparent);
        } else if (i == 3) {
            this.left_top.setBackgroundResource(R.color.transparent);
            this.right_top.setBackgroundResource(R.color.transparent);
            this.left_bottom.setBackgroundResource(R.color.transparent);
            this.right_bottom.setBackgroundResource(R.color.orange);
        }
        this.ok_set.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.util.TouchpadEvents.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SharePreUtil.setIntValue(context, TouchpadEvents.TIME_TAG, TouchpadEvents.spareTimeTag);
                SharePreUtil.setIntValue(context, TouchpadEvents.SLIDE_TAG, TouchpadEvents.spareSlideTag);
                SharePreUtil.setIntValue(context, TouchpadEvents.ORIGIN_TAG, TouchpadEvents.spareOriginTag);
                TouchpadEvents.initValueAdjust(context);
                TouchpadEvents.this.initSpareData();
                TouchpadEvents.this.dialog.dismiss();
            }
        });
        this.cancel.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.util.TouchpadEvents.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                TouchpadEvents.this.initSpareData();
                TouchpadEvents.this.dialog.dismiss();
            }
        });
        this.data_seekbar.setMax(1000);
        this.data_seekbar.setProgress(timeTag);
        this.data_textview.setText(String.valueOf(timeTag));
        this.data_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.hzbhd.canbus.util.TouchpadEvents.4
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i2, boolean z) {
                TouchpadEvents.this.data_textview.setText(String.valueOf(seekBar.getProgress()));
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                TouchpadEvents.this.data_textview.setText(String.valueOf(seekBar.getProgress()));
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                TouchpadEvents.this.data_textview.setText(String.valueOf(seekBar.getProgress()));
                int unused = TouchpadEvents.spareTimeTag = seekBar.getProgress();
            }
        });
        this.operation_seekbar.setMax(100);
        this.operation_seekbar.setProgress(slideTag);
        this.operation_textview.setText(String.valueOf(slideTag));
        this.operation_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.hzbhd.canbus.util.TouchpadEvents.5
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i2, boolean z) {
                TouchpadEvents.this.operation_textview.setText(String.valueOf(seekBar.getProgress()));
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                TouchpadEvents.this.operation_textview.setText(String.valueOf(seekBar.getProgress()));
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                TouchpadEvents.this.operation_textview.setText(String.valueOf(seekBar.getProgress()));
                int unused = TouchpadEvents.spareSlideTag = seekBar.getProgress();
            }
        });
        this.develop_ok.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.util.TouchpadEvents.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                TouchpadEvents.this.alert_layout.setVisibility(8);
            }
        });
        this.isNot_develop.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.util.TouchpadEvents.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                TouchpadEvents.this.dialog.dismiss();
            }
        });
        this.left_top.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.util.TouchpadEvents.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                TouchpadEvents.this.left_top.setBackgroundResource(R.color.orange);
                TouchpadEvents.this.right_top.setBackgroundResource(R.color.transparent);
                TouchpadEvents.this.left_bottom.setBackgroundResource(R.color.transparent);
                TouchpadEvents.this.right_bottom.setBackgroundResource(R.color.transparent);
                int unused = TouchpadEvents.spareOriginTag = 0;
            }
        });
        this.right_top.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.util.TouchpadEvents.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                TouchpadEvents.this.left_top.setBackgroundResource(R.color.transparent);
                TouchpadEvents.this.right_top.setBackgroundResource(R.color.orange);
                TouchpadEvents.this.left_bottom.setBackgroundResource(R.color.transparent);
                TouchpadEvents.this.right_bottom.setBackgroundResource(R.color.transparent);
                int unused = TouchpadEvents.spareOriginTag = 1;
            }
        });
        this.left_bottom.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.util.TouchpadEvents.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                TouchpadEvents.this.left_top.setBackgroundResource(R.color.transparent);
                TouchpadEvents.this.right_top.setBackgroundResource(R.color.transparent);
                TouchpadEvents.this.left_bottom.setBackgroundResource(R.color.orange);
                TouchpadEvents.this.right_bottom.setBackgroundResource(R.color.transparent);
                int unused = TouchpadEvents.spareOriginTag = 2;
            }
        });
        this.right_bottom.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.util.TouchpadEvents.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                TouchpadEvents.this.left_top.setBackgroundResource(R.color.transparent);
                TouchpadEvents.this.right_top.setBackgroundResource(R.color.transparent);
                TouchpadEvents.this.left_bottom.setBackgroundResource(R.color.transparent);
                TouchpadEvents.this.right_bottom.setBackgroundResource(R.color.orange);
                int unused = TouchpadEvents.spareOriginTag = 3;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initSpareData() {
        spareTimeTag = timeTag;
        spareSlideTag = slideTag;
        spareOriginTag = originTag;
    }
}
