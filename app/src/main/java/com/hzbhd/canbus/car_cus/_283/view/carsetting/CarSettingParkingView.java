package com.hzbhd.canbus.car_cus._283.view.carsetting;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
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
import com.hzbhd.canbus.car_cus._283.view.SettingProgressView;
import com.hzbhd.canbus.car_cus._283.view.SettingSelectView;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class CarSettingParkingView extends RelativeLayout {
    private ExecutorService executorService;
    private boolean isSeekbar;
    private List<SettingDialogBean> listParking;
    private Context mContext;
    private Handler mHandler;
    private View mView;
    private SettingDialogView sdv_parking;
    private SettingProgressView spv_front_tone;
    private SettingProgressView spv_front_volume;
    private SettingProgressView spv_rear_tone;
    private SettingProgressView spv_rear_volume;
    private SettingSelectView ssv_auto;
    private SettingSelectView ssv_off_road;
    private SettingSelectView ssv_out;
    private SettingSelectView ssv_parking_function;
    private SettingSelectView ssv_parking_radar_voice;
    private SettingSelectView ssv_parking_switch;

    public CarSettingParkingView(Context context) {
        this(context, null);
    }

    public CarSettingParkingView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CarSettingParkingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.listParking = new ArrayList();
        this.executorService = Executors.newSingleThreadExecutor();
        this.isSeekbar = false;
        this.mHandler = new Handler(Looper.getMainLooper()) { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingParkingView.3
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what != 0) {
                    return;
                }
                CarSettingParkingView.this.isSeekbar = false;
            }
        };
        this.mContext = context;
        this.mView = LayoutInflater.from(context).inflate(R.layout._283_car_setting_parking, (ViewGroup) this, true);
        initView();
        initClick();
        new Handler().postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingParkingView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m1112xc1aadb0b();
            }
        }, 1000L);
    }

    private void initView() {
        this.sdv_parking = (SettingDialogView) this.mView.findViewById(R.id.sdv_parking);
        this.spv_front_volume = (SettingProgressView) this.mView.findViewById(R.id.spv_front_volume);
        this.spv_front_tone = (SettingProgressView) this.mView.findViewById(R.id.spv_front_tone);
        this.spv_rear_volume = (SettingProgressView) this.mView.findViewById(R.id.spv_rear_volume);
        this.spv_rear_tone = (SettingProgressView) this.mView.findViewById(R.id.spv_rear_tone);
        this.ssv_parking_function = (SettingSelectView) this.mView.findViewById(R.id.ssv_parking_function);
        this.ssv_parking_switch = (SettingSelectView) this.mView.findViewById(R.id.ssv_parking_switch);
        this.ssv_parking_radar_voice = (SettingSelectView) this.mView.findViewById(R.id.ssv_parking_radar_voice);
        this.ssv_out = (SettingSelectView) this.mView.findViewById(R.id.ssv_out);
        this.ssv_auto = (SettingSelectView) this.mView.findViewById(R.id.ssv_auto);
        this.ssv_off_road = (SettingSelectView) this.mView.findViewById(R.id.ssv_off_road);
        this.executorService.execute(new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingParkingView.1
            @Override // java.lang.Runnable
            public void run() {
                CarSettingParkingView.this.listParking.add(new SettingDialogBean(CarSettingParkingView.this.mContext.getString(R.string._283_parking_1)));
                CarSettingParkingView.this.listParking.add(new SettingDialogBean(CarSettingParkingView.this.mContext.getString(R.string._283_parking_2)));
                CarSettingParkingView.this.listParking.add(new SettingDialogBean(CarSettingParkingView.this.mContext.getString(R.string._283_parking_3)));
                CarSettingParkingView.this.sdv_parking.setListFirst(CarSettingParkingView.this.listParking);
            }
        });
        setProgress(this.spv_front_volume);
        setProgress(this.spv_front_tone);
        setProgress(this.spv_rear_volume);
        setProgress(this.spv_rear_tone);
        this.spv_front_volume.setBigAndSmallValueText("1.0", "");
        this.spv_front_tone.setBigAndSmallValueText("1.0", "");
        this.spv_rear_volume.setBigAndSmallValueText("1.0", "");
        this.spv_rear_tone.setBigAndSmallValueText("1.0", "");
    }

    private void initClick() {
        SettingProgressView.OnItemClickListener onItemClickListener = new SettingProgressView.OnItemClickListener() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingParkingView$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.car_cus._283.view.SettingProgressView.OnItemClickListener
            public final void onProgressChanged(View view, int i) {
                this.f$0.m1110xb4715b04(view, i);
            }
        };
        SettingSelectView.OnItemClickListener onItemClickListener2 = new SettingSelectView.OnItemClickListener() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingParkingView$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.car_cus._283.view.SettingSelectView.OnItemClickListener
            public final void onClick(View view, boolean z) {
                this.f$0.m1111xa5c2ea85(view, z);
            }
        };
        this.spv_front_volume.setOnItemClickListener(onItemClickListener);
        this.spv_front_tone.setOnItemClickListener(onItemClickListener);
        this.spv_rear_volume.setOnItemClickListener(onItemClickListener);
        this.spv_rear_tone.setOnItemClickListener(onItemClickListener);
        this.ssv_parking_function.setOnItemClickListener(onItemClickListener2);
        this.ssv_parking_switch.setOnItemClickListener(onItemClickListener2);
        this.ssv_auto.setOnItemClickListener(onItemClickListener2);
        this.ssv_out.setOnItemClickListener(onItemClickListener2);
        this.ssv_parking_radar_voice.setOnItemClickListener(onItemClickListener2);
        this.ssv_off_road.setOnItemClickListener(onItemClickListener2);
        this.sdv_parking.setOnItemClickListener(new SettingDialogView.OnItemClickListener() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingParkingView.2
            @Override // com.hzbhd.canbus.car_cus._283.view.SettingDialogView.OnItemClickListener
            public void onClick(View view, int i) {
                MessageSender.sendMsg(new byte[]{22, 74, 7, (byte) (2 - i)});
            }
        });
    }

    /* renamed from: lambda$initClick$1$com-hzbhd-canbus-car_cus-_283-view-carsetting-CarSettingParkingView, reason: not valid java name */
    /* synthetic */ void m1110xb4715b04(View view, int i) {
        byte b;
        this.isSeekbar = true;
        switch (view.getId()) {
            case R.id.spv_front_tone /* 2131363354 */:
                b = 3;
                break;
            case R.id.spv_front_volume /* 2131363355 */:
            case R.id.spv_instrument /* 2131363356 */:
            case R.id.spv_light_system /* 2131363357 */:
            default:
                b = 2;
                break;
            case R.id.spv_rear_tone /* 2131363358 */:
                b = 5;
                break;
            case R.id.spv_rear_volume /* 2131363359 */:
                b = 4;
                break;
        }
        MessageSender.sendMsg(new byte[]{22, 74, b, (byte) i});
    }

    /* renamed from: lambda$initClick$2$com-hzbhd-canbus-car_cus-_283-view-carsetting-CarSettingParkingView, reason: not valid java name */
    /* synthetic */ void m1111xa5c2ea85(View view, boolean z) {
        int id = view.getId();
        byte b = 1;
        if (id != R.id.ssv_auto) {
            if (id != R.id.ssv_off_road) {
                switch (id) {
                    case R.id.ssv_out /* 2131363410 */:
                        b = 9;
                        break;
                    case R.id.ssv_parking_function /* 2131363411 */:
                        b = 11;
                        break;
                    case R.id.ssv_parking_radar_voice /* 2131363412 */:
                        b = 8;
                        break;
                    case R.id.ssv_parking_switch /* 2131363413 */:
                        b = NfDef.AVRCP_EVENT_ID_UIDS_CHANGED;
                        break;
                }
            } else {
                b = 10;
            }
        }
        sendMsg(b, z);
    }

    /* renamed from: refreshUi, reason: merged with bridge method [inline-methods] */
    public void m1112xc1aadb0b() {
        this.sdv_parking.setSelect(GeneralDzData.parking_mode);
        this.ssv_parking_function.setSelect(GeneralDzData.parking_function);
        this.ssv_parking_switch.setSelect(GeneralDzData.parking_switch);
        this.ssv_auto.setSelect(GeneralDzData.parking_auto);
        this.ssv_out.setSelect(GeneralDzData.parking_out);
        this.ssv_parking_radar_voice.setSelect(GeneralDzData.parking_radar_voice);
        this.ssv_off_road.setSelect(GeneralDzData.parking_off_road);
        if (!this.isSeekbar) {
            this.spv_front_volume.setValueProgress(GeneralDzData.parking_front_volume);
            this.spv_front_tone.setValueProgress(GeneralDzData.parking_front_tone);
            this.spv_rear_volume.setValueProgress(GeneralDzData.parking_rear_volume);
            this.spv_rear_tone.setValueProgress(GeneralDzData.parking_rear_tone);
            return;
        }
        this.mHandler.removeMessages(0);
        this.mHandler.sendEmptyMessageDelayed(0, 200L);
    }

    private void setProgress(SettingProgressView settingProgressView) {
        settingProgressView.setMaxAndMinProgress(1, 9);
    }

    private void sendMsg(byte b, boolean z) {
        MessageSender.sendMsg((byte) 74, b, z);
    }
}
