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
import com.hzbhd.canbus.car_cus._283.view.SettingSelectView;

/* loaded from: classes2.dex */
public class CarSettingRearView extends RelativeLayout {
    private Context mContext;
    private View mView;
    private SettingSelectView ssv_1;
    private SettingSelectView ssv_2;
    private SettingSelectView ssv_3;
    private SettingSelectView ssv_4;
    private SettingSelectView ssv_5;

    public CarSettingRearView(Context context) {
        this(context, null);
    }

    public CarSettingRearView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CarSettingRearView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
        this.mView = LayoutInflater.from(context).inflate(R.layout._283_car_setting_rear_view, (ViewGroup) this, true);
        initView();
        initClick();
        new Handler().postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingRearView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m1113x253b7c9b();
            }
        }, 1000L);
    }

    private void initView() {
        this.ssv_1 = (SettingSelectView) this.mView.findViewById(R.id.ssv_1);
        this.ssv_2 = (SettingSelectView) this.mView.findViewById(R.id.ssv_2);
        this.ssv_3 = (SettingSelectView) this.mView.findViewById(R.id.ssv_3);
        this.ssv_4 = (SettingSelectView) this.mView.findViewById(R.id.ssv_4);
        this.ssv_5 = (SettingSelectView) this.mView.findViewById(R.id.ssv_5);
    }

    private void initClick() {
        SettingSelectView.OnItemClickListener onItemClickListener = new SettingSelectView.OnItemClickListener() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingRearView.1
            @Override // com.hzbhd.canbus.car_cus._283.view.SettingSelectView.OnItemClickListener
            public void onClick(View view, boolean z) {
                byte b = 1;
                switch (view.getId()) {
                    case R.id.ssv_2 /* 2131363376 */:
                        b = 2;
                        break;
                    case R.id.ssv_3 /* 2131363377 */:
                        b = 3;
                        break;
                    case R.id.ssv_4 /* 2131363378 */:
                        b = 4;
                        break;
                    case R.id.ssv_5 /* 2131363379 */:
                        b = 5;
                        break;
                }
                MessageSender.sendMsg((byte) 110, b, z);
            }
        };
        this.ssv_1.setOnItemClickListener(onItemClickListener);
        this.ssv_2.setOnItemClickListener(onItemClickListener);
        this.ssv_3.setOnItemClickListener(onItemClickListener);
        this.ssv_4.setOnItemClickListener(onItemClickListener);
        this.ssv_5.setOnItemClickListener(onItemClickListener);
    }

    /* renamed from: refreshUi, reason: merged with bridge method [inline-methods] */
    public void m1113x253b7c9b() {
        this.ssv_1.setSelect(GeneralDzData.rear_sync);
        this.ssv_2.setSelect(GeneralDzData.rear_down);
        this.ssv_3.setSelect(GeneralDzData.rear_in);
        this.ssv_4.setSelect(GeneralDzData.rear_auto_wiper);
        this.ssv_5.setSelect(GeneralDzData.rear_wiper);
    }
}
