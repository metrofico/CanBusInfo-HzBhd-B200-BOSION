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
public class CarSettingDoorView extends RelativeLayout {
    private SettingDialogView adv_close;
    private ExecutorService executorService;
    private List<SettingDialogBean> listClose;
    private List<SettingDialogBean> listOpen;
    private Context mContext;
    private View mView;
    private SettingDialogView sdv_open;
    private SettingSelectView ssv_auto_lock;
    private SettingSelectView ssv_boot;
    private SettingSelectView ssv_intelligence;
    private SettingSelectView ssv_voice;

    public CarSettingDoorView(Context context) {
        this(context, null);
    }

    public CarSettingDoorView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CarSettingDoorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.listOpen = new ArrayList();
        this.listClose = new ArrayList();
        this.executorService = Executors.newSingleThreadExecutor();
        this.mContext = context;
        this.mView = LayoutInflater.from(context).inflate(R.layout._283_car_setting_door, (ViewGroup) this, true);
        initView();
        initClick();
        new Handler().postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingDoorView$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m1104x9fcd31a5();
            }
        }, 1000L);
    }

    private void initView() {
        this.sdv_open = (SettingDialogView) this.mView.findViewById(R.id.sdv_open);
        this.adv_close = (SettingDialogView) this.mView.findViewById(R.id.adv_close);
        this.ssv_auto_lock = (SettingSelectView) this.mView.findViewById(R.id.ssv_auto_lock);
        this.ssv_intelligence = (SettingSelectView) this.mView.findViewById(R.id.ssv_intelligence);
        this.ssv_boot = (SettingSelectView) this.mView.findViewById(R.id.ssv_boot);
        this.ssv_voice = (SettingSelectView) this.mView.findViewById(R.id.ssv_voice);
        this.executorService.execute(new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingDoorView.1
            @Override // java.lang.Runnable
            public void run() {
                CarSettingDoorView.this.listOpen.add(new SettingDialogBean(CarSettingDoorView.this.mContext.getString(R.string._283_door_title_1)));
                CarSettingDoorView.this.listOpen.add(new SettingDialogBean(CarSettingDoorView.this.mContext.getString(R.string._283_door_title_2)));
                CarSettingDoorView.this.listOpen.add(new SettingDialogBean(CarSettingDoorView.this.mContext.getString(R.string._283_door_title_3)));
                CarSettingDoorView.this.sdv_open.setListFirst(CarSettingDoorView.this.listOpen);
            }
        });
        this.executorService.execute(new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingDoorView.2
            @Override // java.lang.Runnable
            public void run() {
                CarSettingDoorView.this.listClose.add(new SettingDialogBean(CarSettingDoorView.this.mContext.getString(R.string._283_door_title_4)));
                CarSettingDoorView.this.listClose.add(new SettingDialogBean(CarSettingDoorView.this.mContext.getString(R.string._283_door_title_5)));
                CarSettingDoorView.this.listClose.add(new SettingDialogBean(CarSettingDoorView.this.mContext.getString(R.string._283_door_title_6)));
                CarSettingDoorView.this.adv_close.setListFirst(CarSettingDoorView.this.listClose);
            }
        });
    }

    private void initClick() {
        SettingDialogView.OnItemClickListener onItemClickListener = new SettingDialogView.OnItemClickListener() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingDoorView$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.car_cus._283.view.SettingDialogView.OnItemClickListener
            public final void onClick(View view, int i) {
                MessageSender.sendMsg(new byte[]{22, 111, view.getId() != R.id.adv_close ? (byte) 1 : (byte) 2, (byte) i});
            }
        };
        SettingSelectView.OnItemClickListener onItemClickListener2 = new SettingSelectView.OnItemClickListener() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingDoorView$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.car_cus._283.view.SettingSelectView.OnItemClickListener
            public final void onClick(View view, boolean z) {
                CarSettingDoorView.lambda$initClick$2(view, z);
            }
        };
        this.sdv_open.setOnItemClickListener(onItemClickListener);
        this.adv_close.setOnItemClickListener(onItemClickListener);
        this.ssv_auto_lock.setOnItemClickListener(onItemClickListener2);
        this.ssv_intelligence.setOnItemClickListener(onItemClickListener2);
        this.ssv_boot.setOnItemClickListener(onItemClickListener2);
        this.ssv_voice.setOnItemClickListener(onItemClickListener2);
    }

    static /* synthetic */ void lambda$initClick$2(View view, boolean z) {
        byte b = 3;
        switch (view.getId()) {
            case R.id.ssv_boot /* 2131363390 */:
                b = 5;
                break;
            case R.id.ssv_intelligence /* 2131363403 */:
                b = 4;
                break;
            case R.id.ssv_voice /* 2131363419 */:
                b = 6;
                break;
        }
        MessageSender.sendMsg((byte) 111, b, z);
    }

    /* renamed from: refreshUi, reason: merged with bridge method [inline-methods] */
    public void m1104x9fcd31a5() {
        this.sdv_open.setSelect(GeneralDzData.door_open);
        this.adv_close.setSelect(GeneralDzData.door_unlock);
        this.ssv_auto_lock.setSelect(GeneralDzData.door_auto_lock);
        this.ssv_intelligence.setSelect(GeneralDzData.door_intelligence);
        this.ssv_boot.setSelect(GeneralDzData.door_boot);
        this.ssv_voice.setSelect(GeneralDzData.door_voice);
    }
}
