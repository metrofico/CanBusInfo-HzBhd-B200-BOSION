package com.hzbhd.canbus.car_cus._291;

import android.os.Bundle;
import android.view.View;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.car_cus._283.view.SettingSelectView;

/* loaded from: classes2.dex */
public class MainSettingActivity extends AbstractBaseActivity {
    private SettingSelectView ssv_in;
    private SettingSelectView ssv_out;
    private SettingSelectView ssv_radar;

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout._291_activity_main_setting);
        initView();
        getWindow().setFlags(1024, 1024);
        refreshUi(null);
    }

    private void initView() {
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._291.MainSettingActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m1127x60d2e325(view);
            }
        });
        this.ssv_out = (SettingSelectView) findViewById(R.id.ssv_out);
        this.ssv_in = (SettingSelectView) findViewById(R.id.ssv_in);
        this.ssv_radar = (SettingSelectView) findViewById(R.id.ssv_radar);
        SettingSelectView.OnItemClickListener onItemClickListener = new SettingSelectView.OnItemClickListener() { // from class: com.hzbhd.canbus.car_cus._291.MainSettingActivity.1
            @Override // com.hzbhd.canbus.car_cus._283.view.SettingSelectView.OnItemClickListener
            public void onClick(View view, boolean z) {
                int id = view.getId();
                byte b = 9;
                if (id == R.id.ssv_in) {
                    b = 10;
                } else if (id != R.id.ssv_out && id == R.id.ssv_radar) {
                    b = 11;
                }
                MessageSender.sendMsg(new byte[]{22, 76, b, !z ? 1 : 0});
            }
        };
        this.ssv_out.setOnItemClickListener(onItemClickListener);
        this.ssv_in.setOnItemClickListener(onItemClickListener);
        this.ssv_radar.setOnItemClickListener(onItemClickListener);
    }

    /* renamed from: lambda$initView$0$com-hzbhd-canbus-car_cus-_291-MainSettingActivity, reason: not valid java name */
    /* synthetic */ void m1127x60d2e325(View view) {
        finish();
    }

    @Override // com.hzbhd.canbus.interfaces.MgrRefreshUiInterface
    public void refreshUi(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.ssv_out.setSelect(GeneralDzPQData.vehicleOut);
        this.ssv_in.setSelect(GeneralDzPQData.vehicleIn);
        this.ssv_radar.setSelect(GeneralDzPQData.vehicleRadarMute);
    }
}
