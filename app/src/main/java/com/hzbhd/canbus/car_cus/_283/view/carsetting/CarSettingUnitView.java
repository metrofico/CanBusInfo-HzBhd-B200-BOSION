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
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes2.dex */
public class CarSettingUnitView extends RelativeLayout {
    private static final int Interval = 1;
    private static final String UNIT_LANGUAGE = "_283_language";
    private ExecutorService executorService;
    private List<SettingDialogBean> listConsumption;
    private List<SettingDialogBean> listDistance;
    private List<SettingDialogBean> listElectric;
    private List<SettingDialogBean> listLanguage;
    private List<SettingDialogBean> listPressure;
    private List<SettingDialogBean> listSpeed;
    private List<SettingDialogBean> listTemperature;
    private List<SettingDialogBean> listVolume;
    private Context mContext;
    private View mView;
    private SettingDialogView sdv_consumption;
    private SettingDialogView sdv_distance;
    private SettingDialogView sdv_electric;
    private SettingDialogView sdv_language;
    private SettingDialogView sdv_pressure;
    private SettingDialogView sdv_speed;
    private SettingDialogView sdv_temperature;
    private SettingDialogView sdv_volume;

    public CarSettingUnitView(Context context) {
        this(context, null);
    }

    public CarSettingUnitView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CarSettingUnitView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.executorService = Executors.newSingleThreadExecutor();
        this.listDistance = new ArrayList();
        this.listSpeed = new ArrayList();
        this.listTemperature = new ArrayList();
        this.listVolume = new ArrayList();
        this.listConsumption = new ArrayList();
        this.listPressure = new ArrayList();
        this.listElectric = new ArrayList();
        this.listLanguage = new ArrayList();
        this.mContext = context;
        this.mView = LayoutInflater.from(context).inflate(R.layout._283_car_setting_unit, (ViewGroup) this, true);
        initView();
        initClick();
        new Handler().postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingUnitView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m1119x46ba0c7b();
            }
        }, 1000L);
    }

    private void initView() {
        this.sdv_distance = (SettingDialogView) this.mView.findViewById(R.id.sdv_distance);
        this.sdv_speed = (SettingDialogView) this.mView.findViewById(R.id.sdv_speed);
        this.sdv_temperature = (SettingDialogView) this.mView.findViewById(R.id.sdv_temperature);
        this.sdv_volume = (SettingDialogView) this.mView.findViewById(R.id.sdv_volume);
        this.sdv_consumption = (SettingDialogView) this.mView.findViewById(R.id.sdv_consumption);
        this.sdv_pressure = (SettingDialogView) this.mView.findViewById(R.id.sdv_pressure);
        this.sdv_electric = (SettingDialogView) this.mView.findViewById(R.id.sdv_electric);
        this.sdv_language = (SettingDialogView) this.mView.findViewById(R.id.sdv_language);
        this.executorService.execute(new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingUnitView.1
            @Override // java.lang.Runnable
            public void run() {
                CarSettingUnitView.this.listDistance.add(new SettingDialogBean(CarSettingUnitView.this.mContext.getString(R.string._283_unit_2)));
                CarSettingUnitView.this.listDistance.add(new SettingDialogBean(CarSettingUnitView.this.mContext.getString(R.string._283_unit_3)));
                CarSettingUnitView.this.sdv_distance.setListFirst(CarSettingUnitView.this.listDistance);
            }
        });
        this.executorService.execute(new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingUnitView.2
            @Override // java.lang.Runnable
            public void run() {
                CarSettingUnitView.this.listSpeed.add(new SettingDialogBean(CarSettingUnitView.this.mContext.getString(R.string._283_unit_1)));
                CarSettingUnitView.this.listSpeed.add(new SettingDialogBean(CarSettingUnitView.this.mContext.getString(R.string._283_unit_4)));
                CarSettingUnitView.this.sdv_speed.setListFirst(CarSettingUnitView.this.listSpeed);
            }
        });
        this.executorService.execute(new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingUnitView.3
            @Override // java.lang.Runnable
            public void run() {
                CarSettingUnitView.this.listTemperature.add(new SettingDialogBean(CarSettingUnitView.this.mContext.getString(R.string._283_shezhidu)));
                CarSettingUnitView.this.listTemperature.add(new SettingDialogBean(CarSettingUnitView.this.mContext.getString(R.string._283_huashi)));
                CarSettingUnitView.this.sdv_temperature.setListFirst(CarSettingUnitView.this.listTemperature);
            }
        });
        this.executorService.execute(new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingUnitView.4
            @Override // java.lang.Runnable
            public void run() {
                CarSettingUnitView.this.listVolume.add(new SettingDialogBean(CarSettingUnitView.this.mContext.getString(R.string._283_unit_5)));
                CarSettingUnitView.this.listVolume.add(new SettingDialogBean(CarSettingUnitView.this.mContext.getString(R.string._283_unit_6)));
                CarSettingUnitView.this.listVolume.add(new SettingDialogBean(CarSettingUnitView.this.mContext.getString(R.string._283_unit_7)));
                CarSettingUnitView.this.sdv_volume.setListFirst(CarSettingUnitView.this.listVolume);
            }
        });
        this.executorService.execute(new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingUnitView.5
            @Override // java.lang.Runnable
            public void run() {
                CarSettingUnitView.this.listConsumption.add(new SettingDialogBean(CarSettingUnitView.this.mContext.getString(R.string._283_unit_8)));
                CarSettingUnitView.this.listConsumption.add(new SettingDialogBean(CarSettingUnitView.this.mContext.getString(R.string._283_unit_9)));
                CarSettingUnitView.this.listConsumption.add(new SettingDialogBean(CarSettingUnitView.this.mContext.getString(R.string._283_unit_10)));
                CarSettingUnitView.this.listConsumption.add(new SettingDialogBean(CarSettingUnitView.this.mContext.getString(R.string._283_unit_11)));
                CarSettingUnitView.this.sdv_consumption.setListFirst(CarSettingUnitView.this.listConsumption);
            }
        });
        this.executorService.execute(new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingUnitView.6
            @Override // java.lang.Runnable
            public void run() {
                CarSettingUnitView.this.listPressure.add(new SettingDialogBean(CarSettingUnitView.this.mContext.getString(R.string._283_tmp_unit_1)));
                CarSettingUnitView.this.listPressure.add(new SettingDialogBean(CarSettingUnitView.this.mContext.getString(R.string._283_tmp_unit_2)));
                CarSettingUnitView.this.listPressure.add(new SettingDialogBean(CarSettingUnitView.this.mContext.getString(R.string._283_tmp_unit_3)));
                CarSettingUnitView.this.sdv_pressure.setListFirst(CarSettingUnitView.this.listPressure);
            }
        });
        this.executorService.execute(new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingUnitView.7
            @Override // java.lang.Runnable
            public void run() {
                CarSettingUnitView.this.listElectric.add(new SettingDialogBean(CarSettingUnitView.this.mContext.getString(R.string._283_unit_12)));
                CarSettingUnitView.this.listElectric.add(new SettingDialogBean(CarSettingUnitView.this.mContext.getString(R.string._283_unit_13)));
                CarSettingUnitView.this.sdv_electric.setListFirst(CarSettingUnitView.this.listElectric);
            }
        });
        this.executorService.execute(new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingUnitView.8
            @Override // java.lang.Runnable
            public void run() {
                CarSettingUnitView.this.listLanguage.add(new SettingDialogBean(CarSettingUnitView.this.mContext.getString(R.string._283_language_english)));
                CarSettingUnitView.this.listLanguage.add(new SettingDialogBean(CarSettingUnitView.this.mContext.getString(R.string._283_language_chinese)));
                String stringValue = SharePreUtil.getStringValue(CarSettingUnitView.this.mContext, CarSettingUnitView.UNIT_LANGUAGE, CarSettingUnitView.this.mContext.getString(R.string._283_language_english));
                CarSettingUnitView.this.sdv_language.setList(CarSettingUnitView.this.listLanguage);
                CarSettingUnitView.this.sdv_language.setSelectOnValue(stringValue);
            }
        });
    }

    private void initClick() {
        this.sdv_language.setOnItemClickListener(new SettingDialogView.OnItemClickListener() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingUnitView.9
            @Override // com.hzbhd.canbus.car_cus._283.view.SettingDialogView.OnItemClickListener
            public void onClick(View view, int i) {
                MessageSender.sendMsg(new byte[]{22, -102, 1, (byte) (i + 1)});
                SharePreUtil.setStringValue(CarSettingUnitView.this.mContext, CarSettingUnitView.UNIT_LANGUAGE, ((SettingDialogBean) CarSettingUnitView.this.listLanguage.get(i)).getValue());
                CarSettingUnitView.this.sdv_language.setSelect(i);
            }
        });
        SettingDialogView.OnItemClickListener onItemClickListener = new SettingDialogView.OnItemClickListener() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingUnitView.10
            @Override // com.hzbhd.canbus.car_cus._283.view.SettingDialogView.OnItemClickListener
            public void onClick(View view, int i) {
                byte b;
                switch (view.getId()) {
                    case R.id.sdv_consumption /* 2131363266 */:
                        b = 5;
                        break;
                    case R.id.sdv_distance /* 2131363268 */:
                    default:
                        b = 1;
                        break;
                    case R.id.sdv_electric /* 2131363270 */:
                        b = 7;
                        break;
                    case R.id.sdv_pressure /* 2131363278 */:
                        b = 6;
                        break;
                    case R.id.sdv_speed /* 2131363279 */:
                        b = 2;
                        break;
                    case R.id.sdv_temperature /* 2131363281 */:
                        b = 3;
                        break;
                    case R.id.sdv_volume /* 2131363285 */:
                        b = 4;
                        break;
                }
                MessageSender.sendMsg(new byte[]{22, -54, b, (byte) (i + 1)});
            }
        };
        this.sdv_distance.setOnItemClickListener(onItemClickListener);
        this.sdv_speed.setOnItemClickListener(onItemClickListener);
        this.sdv_temperature.setOnItemClickListener(onItemClickListener);
        this.sdv_volume.setOnItemClickListener(onItemClickListener);
        this.sdv_consumption.setOnItemClickListener(onItemClickListener);
        this.sdv_pressure.setOnItemClickListener(onItemClickListener);
        this.sdv_electric.setOnItemClickListener(onItemClickListener);
    }

    /* renamed from: refreshUi, reason: merged with bridge method [inline-methods] */
    public void m1119x46ba0c7b() {
        this.sdv_distance.setSelect(1 - GeneralDzData.unit_distance);
        this.sdv_speed.setSelect(1 - GeneralDzData.unit_speed);
        this.sdv_temperature.setSelect(1 - GeneralDzData.unit_temperature);
        this.sdv_pressure.setSelect(GeneralDzData.unit_pressure);
        this.sdv_electric.setSelect(GeneralDzData.unit_electric);
        this.sdv_volume.setSelect(GeneralDzData.unit_volume);
        this.sdv_consumption.setSelect(GeneralDzData.unit_consumption);
    }
}
