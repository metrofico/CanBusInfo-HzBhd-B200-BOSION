package com.hzbhd.canbus.car_cus._439.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._439.smartPanel.window.AirSettingWindow;
import com.hzbhd.canbus.car_cus._439.smartPanel.window.CarSelectWindow;
import com.hzbhd.canbus.car_cus._439.smartPanel.window.PanelSwitchWindow;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.ui.util.BaseUtil;

/* loaded from: classes2.dex */
public class ID439SettingActivity extends AppCompatActivity {
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_id439_setting);
        final TextView textView = (TextView) findViewById(R.id.switch1);
        final TextView textView2 = (TextView) findViewById(R.id.switch2);
        final TextView textView3 = (TextView) findViewById(R.id.switch3);
        final TextView textView4 = (TextView) findViewById(R.id.switch4);
        textView.setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.car_cus._439.activity.ID439SettingActivity.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    textView.setBackgroundColor(Color.parseColor("#3EFFFFFF"));
                    return true;
                }
                if (motionEvent.getAction() != 1) {
                    return false;
                }
                textView.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                BaseUtil.INSTANCE.startActivity(Constant.SwcActivity);
                return true;
            }
        });
        textView2.setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.car_cus._439.activity.ID439SettingActivity.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    textView2.setBackgroundColor(Color.parseColor("#3EFFFFFF"));
                    return true;
                }
                if (motionEvent.getAction() != 1) {
                    return false;
                }
                textView2.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                new PanelSwitchWindow(ID439SettingActivity.this).show();
                return true;
            }
        });
        textView3.setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.car_cus._439.activity.ID439SettingActivity.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    textView3.setBackgroundColor(Color.parseColor("#3EFFFFFF"));
                    return true;
                }
                if (motionEvent.getAction() != 1) {
                    return false;
                }
                textView3.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                CarSelectWindow.getInstance().show();
                return true;
            }
        });
        textView4.setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.car_cus._439.activity.ID439SettingActivity.4
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    textView4.setBackgroundColor(Color.parseColor("#3EFFFFFF"));
                    return true;
                }
                if (motionEvent.getAction() != 1) {
                    return false;
                }
                textView4.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                new AirSettingWindow(ID439SettingActivity.this).show();
                return true;
            }
        });
    }
}
