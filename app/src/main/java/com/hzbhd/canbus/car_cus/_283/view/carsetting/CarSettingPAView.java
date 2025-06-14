package com.hzbhd.canbus.car_cus._283.view.carsetting;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.car_cus._283.bean.SettingDialogBean;
import com.hzbhd.canbus.car_cus._283.view.SettingDialogView;
import com.hzbhd.canbus.car_cus._283.view.SettingSelectView;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class CarSettingPAView extends RelativeLayout {
    private ExecutorService executorService;
    private List<SettingDialogBean> listDistance;
    private List<SettingDialogBean> listDriving;
    private Context mContext;
    private View mView;
    private SettingDialogView sdv_distance;
    private SettingDialogView sdv_driving;
    private SettingSelectView ssv_dead_zone;
    private SettingSelectView ssv_driverAlertSystem;
    private SettingSelectView ssv_front_assist;
    private SettingSelectView ssv_front_distance;
    private SettingSelectView ssv_front_warning;
    private SettingSelectView ssv_lane;
    private SettingSelectView ssv_lane_keeping;
    private SettingSelectView ssv_last;
    private SettingSelectView ssv_proactive;
    private SettingSelectView ssv_traffice;

    public CarSettingPAView(Context context) {
        this(context, null);
    }

    public CarSettingPAView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CarSettingPAView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.executorService = Executors.newSingleThreadExecutor();
        this.listDriving = new ArrayList();
        this.listDistance = new ArrayList();
        this.mContext = context;
        this.mView = LayoutInflater.from(context).inflate(R.layout._283_car_setting_pa, (ViewGroup) this, true);
        initView();
        initClick();
        new Handler().postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingPAView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m1109xbc8e36c8();
            }
        }, 1000L);
    }

    private void initView() {
        this.sdv_driving = (SettingDialogView) this.mView.findViewById(R.id.sdv_driving);
        this.sdv_distance = (SettingDialogView) this.mView.findViewById(R.id.sdv_distance);
        this.ssv_last = (SettingSelectView) this.mView.findViewById(R.id.ssv_last);
        this.ssv_front_assist = (SettingSelectView) this.mView.findViewById(R.id.ssv_front_assist);
        this.ssv_front_warning = (SettingSelectView) this.mView.findViewById(R.id.ssv_front_warning);
        this.ssv_front_distance = (SettingSelectView) this.mView.findViewById(R.id.ssv_front_distance);
        this.ssv_lane = (SettingSelectView) this.mView.findViewById(R.id.ssv_lane);
        this.ssv_traffice = (SettingSelectView) this.mView.findViewById(R.id.ssv_traffice);
        this.ssv_driverAlertSystem = (SettingSelectView) this.mView.findViewById(R.id.ssv_driverAlertSystem);
        this.ssv_dead_zone = (SettingSelectView) this.mView.findViewById(R.id.ssv_dead_zone);
        this.ssv_proactive = (SettingSelectView) this.mView.findViewById(R.id.ssv_proactive);
        this.ssv_lane_keeping = (SettingSelectView) this.mView.findViewById(R.id.ssv_lane_keeping);
        this.executorService.execute(new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingPAView.1
            @Override // java.lang.Runnable
            public void run() {
                CarSettingPAView.this.listDriving.add(new SettingDialogBean(CarSettingPAView.this.mContext.getString(R.string._283_driving_pattern_4)));
                CarSettingPAView.this.listDriving.add(new SettingDialogBean(CarSettingPAView.this.mContext.getString(R.string._283_driving_pattern_2)));
                CarSettingPAView.this.listDriving.add(new SettingDialogBean(CarSettingPAView.this.mContext.getString(R.string._283_driving_pattern_3)));
                CarSettingPAView.this.sdv_driving.setListFirst(CarSettingPAView.this.listDriving);
            }
        });
        this.executorService.execute(new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingPAView.2
            @Override // java.lang.Runnable
            public void run() {
                CarSettingPAView.this.listDistance.add(new SettingDialogBean(CarSettingPAView.this.mContext.getString(R.string._283_pa_distance_1)));
                CarSettingPAView.this.listDistance.add(new SettingDialogBean(CarSettingPAView.this.mContext.getString(R.string._283_pa_distance_2)));
                CarSettingPAView.this.listDistance.add(new SettingDialogBean(CarSettingPAView.this.mContext.getString(R.string._283_pa_distance_3)));
                CarSettingPAView.this.listDistance.add(new SettingDialogBean(CarSettingPAView.this.mContext.getString(R.string._283_pa_distance_4)));
                CarSettingPAView.this.listDistance.add(new SettingDialogBean(CarSettingPAView.this.mContext.getString(R.string._283_pa_distance_5)));
                CarSettingPAView.this.sdv_distance.setListFirst(CarSettingPAView.this.listDistance);
            }
        });
    }

    private void initClick() {
        SettingDialogView.OnItemClickListener onItemClickListener = new SettingDialogView.OnItemClickListener() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingPAView$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.car_cus._283.view.SettingDialogView.OnItemClickListener
            public final void onClick(View view, int i) {
                CarSettingPAView.lambda$initClick$1(view, i);
            }
        };
        SettingSelectView.OnItemClickListener onItemClickListener2 = new SettingSelectView.OnItemClickListener() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingPAView$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.car_cus._283.view.SettingSelectView.OnItemClickListener
            public final void onClick(View view, boolean z) {
                this.f$0.m1108xc934e30e(view, z);
            }
        };
        this.sdv_driving.setOnItemClickListener(onItemClickListener);
        this.sdv_distance.setOnItemClickListener(onItemClickListener);
        this.ssv_last.setOnItemClickListener(onItemClickListener2);
        this.ssv_front_assist.setOnItemClickListener(onItemClickListener2);
        this.ssv_front_warning.setOnItemClickListener(onItemClickListener2);
        this.ssv_front_distance.setOnItemClickListener(onItemClickListener2);
        this.ssv_lane.setOnItemClickListener(onItemClickListener2);
        this.ssv_traffice.setOnItemClickListener(onItemClickListener2);
        this.ssv_driverAlertSystem.setOnItemClickListener(onItemClickListener2);
        this.ssv_dead_zone.setOnItemClickListener(onItemClickListener2);
        this.ssv_proactive.setOnItemClickListener(onItemClickListener2);
        this.ssv_lane_keeping.setOnItemClickListener(onItemClickListener2);
    }

    static /* synthetic */ void lambda$initClick$1(View view, int i) {
        switch (view.getId()) {
            case R.id.sdv_distance /* 2131363268 */:
                MessageSender.sendMsg(new byte[]{22, 76, 8, (byte) (i + 1)});
                break;
            case R.id.sdv_driving /* 2131363269 */:
                MessageSender.sendMsg(new byte[]{22, 76, 9, (byte) (i + 1)});
                break;
        }
    }

    /* renamed from: lambda$initClick$2$com-hzbhd-canbus-car_cus-_283-view-carsetting-CarSettingPAView, reason: not valid java name */
    /* synthetic */ void m1108xc934e30e(View view, boolean z) {
        switch (view.getId()) {
            case R.id.ssv_dead_zone /* 2131363394 */:
                sendMsg(NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, z);
                break;
            case R.id.ssv_driverAlertSystem /* 2131363397 */:
                sendMsg((byte) 6, z);
                break;
            case R.id.ssv_front_assist /* 2131363399 */:
                sendMsg((byte) 1, z);
                break;
            case R.id.ssv_front_distance /* 2131363400 */:
                sendMsg((byte) 3, z);
                break;
            case R.id.ssv_front_warning /* 2131363401 */:
                sendMsg((byte) 2, z);
                break;
            case R.id.ssv_lane /* 2131363404 */:
                sendMsg((byte) 4, z);
                break;
            case R.id.ssv_lane_keeping /* 2131363406 */:
                sendMsg((byte) 10, z);
                break;
            case R.id.ssv_last /* 2131363407 */:
                sendMsg((byte) 11, z);
                break;
            case R.id.ssv_proactive /* 2131363414 */:
                sendMsg((byte) 7, z);
                break;
            case R.id.ssv_traffice /* 2131363417 */:
                sendMsg((byte) 5, z);
                break;
        }
    }

    /* renamed from: refreshUi, reason: merged with bridge method [inline-methods] */
    public void m1109xbc8e36c8() {
        this.sdv_driving.setSelect(GeneralDzData.pa_driving);
        this.sdv_distance.setSelect(GeneralDzData.pa_distance);
        this.ssv_last.setSelect(GeneralDzData.pa_last);
        this.ssv_front_assist.setSelect(GeneralDzData.pa_front_assist);
        this.ssv_front_warning.setSelect(GeneralDzData.pa_front_warning);
        this.ssv_front_distance.setSelect(GeneralDzData.pa_front_distance);
        this.ssv_lane.setSelect(GeneralDzData.pa_lane);
        this.ssv_traffice.setSelect(GeneralDzData.pa_traffice);
        this.ssv_driverAlertSystem.setSelect(GeneralDzData.pa_driverAlertSystem);
        this.ssv_dead_zone.setSelect(GeneralDzData.pa_dead_zone);
        this.ssv_proactive.setSelect(GeneralDzData.pa_proactive);
        this.ssv_lane_keeping.setSelect(GeneralDzData.pa_lane_keeping);
    }

    private void sendMsg(byte b, boolean z) {
        MessageSender.sendMsg((byte) 76, b, z);
    }
}
