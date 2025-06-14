package com.hzbhd.canbus.car_cus._446.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._446.CanObserver;
import com.hzbhd.canbus.car_cus._446.view.Page1View;
import com.hzbhd.canbus.car_cus._446.view.Page2View;
import com.hzbhd.canbus.car_cus._446.view.Page3View;
import com.hzbhd.canbus.car_cus._446.view.Page4View;
import com.hzbhd.canbus.car_cus._446.view.TopView;

/* loaded from: classes2.dex */
public class WmCarSettingActivity extends Activity {
    private TopView mode1;
    private TopView mode2;
    private TopView mode3;
    private TopView mode4;
    private Page1View p1;
    private Page2View p2;
    private Page3View p3;
    private Page4View p4;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_wm_car_setting);
        initView();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        CanObserver.getInstance().register(this.p1);
        CanObserver.getInstance().register(this.p2);
        CanObserver.getInstance().register(this.p3);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        CanObserver.getInstance().unRegister(this.p1);
        CanObserver.getInstance().unRegister(this.p2);
        CanObserver.getInstance().unRegister(this.p3);
    }

    private void initView() {
        this.mode1 = (TopView) findViewById(R.id.mode1);
        this.mode2 = (TopView) findViewById(R.id.mode2);
        this.mode3 = (TopView) findViewById(R.id.mode3);
        this.mode4 = (TopView) findViewById(R.id.mode4);
        this.p1 = (Page1View) findViewById(R.id.p1);
        this.p2 = (Page2View) findViewById(R.id.p2);
        this.p3 = (Page3View) findViewById(R.id.p3);
        this.p4 = (Page4View) findViewById(R.id.p4);
        this.mode1.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._446.activity.WmCarSettingActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WmCarSettingActivity.this.SelectMode1();
            }
        });
        this.mode2.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._446.activity.WmCarSettingActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WmCarSettingActivity.this.SelectMode2();
            }
        });
        this.mode3.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._446.activity.WmCarSettingActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WmCarSettingActivity.this.SelectMode3();
            }
        });
        this.mode4.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._446.activity.WmCarSettingActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WmCarSettingActivity.this.SelectMode4();
            }
        });
        SelectMode1();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void SelectMode1() {
        Mode1TurnOn();
        Mode2TurnOff();
        Mode3TurnOff();
        Mode4TurnOff();
        this.p1.setVisibility(0);
        this.p2.setVisibility(8);
        this.p3.setVisibility(8);
        this.p4.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void SelectMode2() {
        Mode1TurnOff();
        Mode2TurnOn();
        Mode3TurnOff();
        Mode4TurnOff();
        this.p1.setVisibility(8);
        this.p2.setVisibility(0);
        this.p3.setVisibility(8);
        this.p4.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void SelectMode3() {
        Mode1TurnOff();
        Mode2TurnOff();
        Mode3TurnOn();
        Mode4TurnOff();
        this.p1.setVisibility(8);
        this.p2.setVisibility(8);
        this.p3.setVisibility(0);
        this.p4.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void SelectMode4() {
        Mode1TurnOff();
        Mode2TurnOff();
        Mode3TurnOff();
        Mode4TurnOn();
        this.p1.setVisibility(8);
        this.p2.setVisibility(8);
        this.p3.setVisibility(8);
        this.p4.setVisibility(0);
    }

    private void Mode1TurnOn() {
        this.mode1.turnOn(R.drawable.__446_shezhi1_fangxiangpan_p, getString(R.string._446_wm_car_7), R.drawable.__446_top_view_on);
    }

    private void Mode1TurnOff() {
        this.mode1.turnOff(R.drawable.__446_shezhi1_fangxiangpan_n, getString(R.string._446_wm_car_7), R.drawable.__446_top_view_off);
    }

    private void Mode2TurnOn() {
        this.mode2.turnOn(R.drawable.__446_shezhi1_menchuang_p, getString(R.string._446_wm_car_8), R.drawable.__446_top_view_on);
    }

    private void Mode2TurnOff() {
        this.mode2.turnOff(R.drawable.__446_shezhi1_menchuang_n, getString(R.string._446_wm_car_8), R.drawable.__446_top_view_off);
    }

    private void Mode3TurnOn() {
        this.mode3.turnOn(R.drawable.__446_shezhi1_faguang_p, getString(R.string._446_wm_car_9), R.drawable.__446_top_view_on);
    }

    private void Mode3TurnOff() {
        this.mode3.turnOff(R.drawable.__446_shezhi1_faguang_n, getString(R.string._446_wm_car_9), R.drawable.__446_top_view_off);
    }

    private void Mode4TurnOn() {
        this.mode4.turnOn(R.drawable.__446_shezhi1_yuanche_p, getString(R.string._446_wm_car_10), R.drawable.__446_top_view_on);
    }

    private void Mode4TurnOff() {
        this.mode4.turnOff(R.drawable.__446_shezhi1_yuanche_n, getString(R.string._446_wm_car_10), R.drawable.__446_top_view_off);
    }
}
