package com.hzbhd.canbus.car_cus._439.smartPanel.window;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car_cus._439.air.util.SharedTag;
import com.hzbhd.canbus.car_cus._439.air.view.CheckItemView2;
import com.hzbhd.canbus.util.SharePreUtil;

/* loaded from: classes2.dex */
public class AirSettingWindow {
    private Button cancel;
    private WindowManager.LayoutParams layoutParams;
    private WindowManager mWindowManager;
    private CheckItemView2 mode1;
    private CheckItemView2 mode2;
    private Button ok;
    private ConstraintLayout round_view;
    private View view;
    public boolean addTag = false;
    private boolean isShowAirSettings = false;

    public AirSettingWindow(Context context) {
        initWindow(context);
    }

    private void initWindow(Context context) {
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(2003);
        this.layoutParams = layoutParams;
        layoutParams.gravity = 80;
        this.layoutParams.format = 1;
        this.layoutParams.x = 0;
        this.layoutParams.y = 0;
        this.layoutParams.flags = 1024;
        intiView(context);
        boolean boolValue = SharePreUtil.getBoolValue(context, SharedTag.KEY_AIR_SETTING_TAG, false);
        this.isShowAirSettings = boolValue;
        if (boolValue) {
            selectMode(1);
        } else {
            selectMode(2);
        }
    }

    private void intiView(final Context context) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout._439_window_settings, (ViewGroup) null);
        this.view = viewInflate;
        Button button = (Button) viewInflate.findViewById(R.id.ok);
        this.ok = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._439.smartPanel.window.AirSettingWindow.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SharePreUtil.setBoolValue(context, SharedTag.KEY_AIR_SETTING_TAG, AirSettingWindow.this.isShowAirSettings);
                AirSettingWindow.this.hide();
                Toast.makeText(context, "SUCCESS", 0).show();
            }
        });
        CheckItemView2 checkItemView2 = (CheckItemView2) this.view.findViewById(R.id.mode1);
        this.mode1 = checkItemView2;
        checkItemView2.setTitle(context.getString(R.string._439_fun_08));
        this.mode1.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._439.smartPanel.window.AirSettingWindow.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AirSettingWindow.this.selectMode(1);
            }
        });
        CheckItemView2 checkItemView22 = (CheckItemView2) this.view.findViewById(R.id.mode2);
        this.mode2 = checkItemView22;
        checkItemView22.setTitle(context.getString(R.string._439_fun_09));
        this.mode2.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._439.smartPanel.window.AirSettingWindow.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AirSettingWindow.this.selectMode(2);
            }
        });
        Button button2 = (Button) this.view.findViewById(R.id.cancel);
        this.cancel = button2;
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._439.smartPanel.window.AirSettingWindow.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AirSettingWindow.this.hide();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void selectMode(int i) {
        if (i == 1) {
            this.isShowAirSettings = true;
            this.mode1.check(true);
            this.mode2.check(false);
        } else {
            if (i != 2) {
                return;
            }
            this.isShowAirSettings = false;
            this.mode1.check(false);
            this.mode2.check(true);
        }
    }

    public void hide() {
        if (this.addTag) {
            this.mWindowManager.removeView(this.view);
            this.addTag = false;
        }
    }

    public void show() {
        if (this.addTag) {
            return;
        }
        this.mWindowManager.addView(this.view, this.layoutParams);
        this.addTag = true;
    }
}
