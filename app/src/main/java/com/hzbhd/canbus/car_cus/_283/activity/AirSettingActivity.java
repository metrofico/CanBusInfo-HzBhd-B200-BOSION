package com.hzbhd.canbus.car_cus._283.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.hzbhd.R;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.car_cus._283.bean.SettingDialogBean;
import com.hzbhd.canbus.car_cus._283.view.SettingDialogView;
import com.hzbhd.canbus.car_cus._283.view.SettingSelectView;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes2.dex */
public class AirSettingActivity extends AbstractBaseActivity {
    private SettingDialogView sdv_temp_interval;
    private SettingSelectView ssv_auto_cycle;
    private SettingSelectView ssv_auto_defogging;
    private SettingSelectView sync_hot;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    SettingSelectView.OnItemClickListener ssv_oicl = new SettingSelectView.OnItemClickListener() { // from class: com.hzbhd.canbus.car_cus._283.activity.AirSettingActivity.1
        @Override // com.hzbhd.canbus.car_cus._283.view.SettingSelectView.OnItemClickListener
        public void onClick(View view, boolean z) {
            switch (view.getId()) {
                case R.id.ssv_auto_cycle /* 2131363383 */:
                    MessageSender.sendMsg((byte) 58, (byte) 14, GeneralDzData.auto_cycle);
                    break;
                case R.id.ssv_auto_defogging /* 2131363384 */:
                    MessageSender.sendMsg((byte) 58, (byte) 10, GeneralDzData.auto_defogging);
                    break;
                case R.id.sync_hot /* 2131363483 */:
                    MessageSender.sendMsg((byte) 58, (byte) 36, GeneralDzData.wheel_seat_hot_sync);
                    break;
            }
        }
    };

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout._283_activity_air_setting);
        getWindow().setFlags(1024, 1024);
        initView();
    }

    private void initView() {
        this.sdv_temp_interval = (SettingDialogView) findViewById(R.id.sdv_temp_interval);
        this.sync_hot = (SettingSelectView) findViewById(R.id.sync_hot);
        this.ssv_auto_defogging = (SettingSelectView) findViewById(R.id.ssv_auto_defogging);
        this.ssv_auto_cycle = (SettingSelectView) findViewById(R.id.ssv_auto_cycle);
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._283.activity.AirSettingActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m1083xc0048995(view);
            }
        });
        this.sync_hot.setOnItemClickListener(this.ssv_oicl);
        this.ssv_auto_defogging.setOnItemClickListener(this.ssv_oicl);
        this.ssv_auto_cycle.setOnItemClickListener(this.ssv_oicl);
        initIntervalList();
        refreshUi(null);
    }

    /* renamed from: lambda$initView$0$com-hzbhd-canbus-car_cus-_283-activity-AirSettingActivity, reason: not valid java name */
    /* synthetic */ void m1083xc0048995(View view) {
        onBackPressed();
    }

    private void initIntervalList() {
        final ArrayList arrayList = new ArrayList();
        this.executorService.execute(new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.activity.AirSettingActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m1081x2f71b975(arrayList);
            }
        });
        this.sdv_temp_interval.setOnItemClickListener(new SettingDialogView.OnItemClickListener() { // from class: com.hzbhd.canbus.car_cus._283.activity.AirSettingActivity$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.car_cus._283.view.SettingDialogView.OnItemClickListener
            public final void onClick(View view, int i) {
                this.f$0.m1082x40278636(arrayList, view, i);
            }
        });
    }

    /* renamed from: lambda$initIntervalList$2$com-hzbhd-canbus-car_cus-_283-activity-AirSettingActivity, reason: not valid java name */
    /* synthetic */ void m1081x2f71b975(final List list) {
        String[] strArr = {"0.5", "1", "1.5", "2", "2.5", "3"};
        for (int i = 0; i < 6; i++) {
            list.add(new SettingDialogBean(strArr[i]));
        }
        String stringValue = SharePreUtil.getStringValue(this, AirActivity.TEMP_INTERVAL, "0.5");
        if (TextUtils.isEmpty(stringValue)) {
            ((SettingDialogBean) list.get(0)).setSelect(true);
        } else {
            for (int i2 = 0; i2 < list.size(); i2++) {
                if (stringValue.equals(((SettingDialogBean) list.get(i2)).getValue())) {
                    ((SettingDialogBean) list.get(i2)).setSelect(true);
                } else {
                    ((SettingDialogBean) list.get(i2)).setSelect(false);
                }
            }
        }
        runOnUiThread(new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.activity.AirSettingActivity$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m1080x1ebbecb4(list);
            }
        });
    }

    /* renamed from: lambda$initIntervalList$1$com-hzbhd-canbus-car_cus-_283-activity-AirSettingActivity, reason: not valid java name */
    /* synthetic */ void m1080x1ebbecb4(List list) {
        this.sdv_temp_interval.setList(list);
    }

    /* renamed from: lambda$initIntervalList$3$com-hzbhd-canbus-car_cus-_283-activity-AirSettingActivity, reason: not valid java name */
    /* synthetic */ void m1082x40278636(List list, View view, int i) {
        this.sdv_temp_interval.setSelect(i);
        SharePreUtil.setStringValue(this, AirActivity.TEMP_INTERVAL, ((SettingDialogBean) list.get(i)).getValue());
    }

    @Override // com.hzbhd.canbus.interfaces.MgrRefreshUiInterface
    public void refreshUi(Bundle bundle) {
        this.sync_hot.setSelect(GeneralDzData.wheel_seat_hot_sync);
        this.ssv_auto_defogging.setSelect(GeneralDzData.auto_defogging);
        this.ssv_auto_cycle.setSelect(GeneralDzData.auto_cycle);
    }
}
