package com.hzbhd.canbus.car_cus._283.view.carsetting;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.car_cus._283.bean.SettingDialogBean;
import com.hzbhd.canbus.car_cus._283.view.SettingDialogView;
import com.hzbhd.canbus.car_cus._283.view.SettingSelectView;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes2.dex */
public class CarSettingTyreView extends RelativeLayout {
    private ExecutorService executorService;
    private List<SettingDialogBean> listAlarm;
    private List<SettingDialogBean> listLoad;
    private Context mContext;
    private View mView;
    private SettingDialogView sdv_alarm;
    private SettingDialogView sdv_load;
    private SettingSelectView ssv_alarm;

    public CarSettingTyreView(Context context) {
        this(context, null);
    }

    public CarSettingTyreView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CarSettingTyreView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.listLoad = new ArrayList();
        this.listAlarm = new ArrayList();
        this.executorService = Executors.newSingleThreadExecutor();
        this.mContext = context;
        this.mView = LayoutInflater.from(context).inflate(R.layout._283_car_setting_tyre, (ViewGroup) this, true);
        initView();
        initClick();
        new Handler().postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingTyreView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m1118x3345a86f();
            }
        }, 1000L);
    }

    private void initView() {
        this.sdv_load = (SettingDialogView) this.mView.findViewById(R.id.sdv_load);
        this.sdv_alarm = (SettingDialogView) this.mView.findViewById(R.id.sdv_alarm);
        this.ssv_alarm = (SettingSelectView) this.mView.findViewById(R.id.ssv_alarm);
        this.executorService.execute(new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingTyreView.1
            @Override // java.lang.Runnable
            public void run() {
                CarSettingTyreView.this.listLoad.add(new SettingDialogBean(CarSettingTyreView.this.mContext.getString(R.string._283_tmps_choose_title_1)));
                CarSettingTyreView.this.listLoad.add(new SettingDialogBean(CarSettingTyreView.this.mContext.getString(R.string._283_tmps_choose_title_2)));
                CarSettingTyreView.this.listLoad.add(new SettingDialogBean(CarSettingTyreView.this.mContext.getString(R.string._283_tmps_choose_title_3)));
                CarSettingTyreView.this.sdv_load.setListFirst(CarSettingTyreView.this.listLoad);
            }
        });
        this.executorService.execute(new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingTyreView.2
            @Override // java.lang.Runnable
            public void run() {
                for (int i = 30; i <= 240; i += 10) {
                    CarSettingTyreView.this.listAlarm.add(new SettingDialogBean(i + "", CarSettingTyreView.this.mContext.getString(R.string._283_unit_1)));
                }
                CarSettingTyreView.this.sdv_alarm.setListFirst(CarSettingTyreView.this.listAlarm);
            }
        });
    }

    private void initClick() {
        this.ssv_alarm.setOnItemClickListener(new SettingSelectView.OnItemClickListener() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingTyreView.3
            @Override // com.hzbhd.canbus.car_cus._283.view.SettingSelectView.OnItemClickListener
            public void onClick(View view, boolean z) {
                if (z) {
                    MessageSender.sendMsg(new byte[]{22, 75, 2, 0});
                } else {
                    MessageSender.sendMsg(new byte[]{22, 75, 2, 1});
                }
            }
        });
        SettingDialogView.OnItemClickListener onItemClickListener = new SettingDialogView.OnItemClickListener() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingTyreView$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.car_cus._283.view.SettingDialogView.OnItemClickListener
            public final void onClick(View view, int i) {
                this.f$0.m1117x46c2d316(view, i);
            }
        };
        this.sdv_load.setOnItemClickListener(onItemClickListener);
        this.sdv_alarm.setOnItemClickListener(onItemClickListener);
    }

    /* renamed from: lambda$initClick$1$com-hzbhd-canbus-car_cus-_283-view-carsetting-CarSettingTyreView, reason: not valid java name */
    /* synthetic */ void m1117x46c2d316(View view, int i) {
        int id = view.getId();
        if (id == R.id.sdv_alarm) {
            MessageSender.sendMsg(new byte[]{22, 75, 3, (byte) Integer.parseInt(this.listAlarm.get(i).getValue())});
        } else {
            if (id != R.id.sdv_load) {
                return;
            }
            MessageSender.sendMsg(new byte[]{22, 75, 4, (byte) i});
        }
    }

    /* renamed from: refreshUi, reason: merged with bridge method [inline-methods] */
    public void m1118x3345a86f() {
        this.sdv_load.setSelect(GeneralDzData.tmpsChoose);
        this.ssv_alarm.setSelect(GeneralDzData.tmpsAlarm);
        this.sdv_alarm.setEnable(GeneralDzData.tmpsAlarm);
        this.sdv_alarm.setSelectOnValue(String.valueOf(GeneralDzData.tmpsAlarmSpeed));
    }
}
