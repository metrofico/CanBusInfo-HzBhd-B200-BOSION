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
import com.hzbhd.R;
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
public class CarSettingLightView extends RelativeLayout {
    private ExecutorService executorService;
    private boolean isSeekbar;
    private List<SettingDialogBean> listAmbientTen;
    private List<SettingDialogBean> listAmbientThree;
    private List<SettingDialogBean> listAmbientTwenty;
    private List<SettingDialogBean> listOpenTime;
    private List<SettingDialogBean> listTravel;
    private Context mContext;
    private Handler mHandler;
    private View mView;
    private SettingDialogView sdv_ambient_ten;
    private SettingDialogView sdv_ambient_three;
    private SettingDialogView sdv_ambient_twenty;
    private SettingProgressView sdv_coming;
    private SettingProgressView sdv_leaving;
    private SettingDialogView sdv_openTime;
    private SettingDialogView sdv_travel;
    private SettingProgressView spv_ambient_car_all;
    private SettingProgressView spv_ambient_car_in;
    private SettingProgressView spv_ambient_car_right;
    private SettingProgressView spv_big_light;
    private SettingProgressView spv_door;
    private SettingProgressView spv_footwell;
    private SettingProgressView spv_instrument;
    private SettingProgressView spv_light_system;
    private SettingSelectView ssv_assist;
    private SettingSelectView ssv_automatic;
    private SettingSelectView ssv_bend;
    private SettingSelectView ssv_day;
    private SettingSelectView ssv_laneChange;

    public CarSettingLightView(Context context) {
        this(context, null);
    }

    public CarSettingLightView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CarSettingLightView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.executorService = Executors.newSingleThreadExecutor();
        this.listOpenTime = new ArrayList();
        this.listTravel = new ArrayList();
        this.listAmbientThree = new ArrayList();
        this.listAmbientTen = new ArrayList();
        this.listAmbientTwenty = new ArrayList();
        this.isSeekbar = false;
        this.mHandler = new Handler(Looper.getMainLooper()) { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingLightView.6
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what != 0) {
                    return;
                }
                CarSettingLightView.this.isSeekbar = false;
            }
        };
        this.mContext = context;
        this.mView = LayoutInflater.from(context).inflate(R.layout._283_cat_setting_light, (ViewGroup) this, true);
        initView();
        initClick();
        new Handler().postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingLightView$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m1107xd61148e9();
            }
        }, 1000L);
    }

    private void initView() {
        this.ssv_assist = (SettingSelectView) this.mView.findViewById(R.id.ssv_assist);
        this.ssv_bend = (SettingSelectView) this.mView.findViewById(R.id.ssv_bend);
        this.ssv_laneChange = (SettingSelectView) this.mView.findViewById(R.id.ssv_laneChange);
        this.ssv_automatic = (SettingSelectView) this.mView.findViewById(R.id.ssv_automatic);
        this.ssv_day = (SettingSelectView) this.mView.findViewById(R.id.ssv_day);
        this.sdv_openTime = (SettingDialogView) this.mView.findViewById(R.id.sdv_openTime);
        this.sdv_travel = (SettingDialogView) this.mView.findViewById(R.id.sdv_travel);
        this.sdv_ambient_three = (SettingDialogView) this.mView.findViewById(R.id.sdv_ambient_three);
        this.sdv_ambient_ten = (SettingDialogView) this.mView.findViewById(R.id.sdv_ambient_ten);
        this.sdv_ambient_twenty = (SettingDialogView) this.mView.findViewById(R.id.sdv_ambient_twenty);
        this.spv_instrument = (SettingProgressView) this.mView.findViewById(R.id.spv_instrument);
        this.spv_door = (SettingProgressView) this.mView.findViewById(R.id.spv_door);
        this.spv_footwell = (SettingProgressView) this.mView.findViewById(R.id.spv_footwell);
        this.sdv_coming = (SettingProgressView) this.mView.findViewById(R.id.sdv_coming);
        this.sdv_leaving = (SettingProgressView) this.mView.findViewById(R.id.sdv_leaving);
        this.spv_ambient_car_in = (SettingProgressView) this.mView.findViewById(R.id.spv_ambient_car_in);
        this.spv_ambient_car_right = (SettingProgressView) this.mView.findViewById(R.id.spv_ambient_car_right);
        this.spv_ambient_car_all = (SettingProgressView) this.mView.findViewById(R.id.spv_ambient_car_all);
        this.spv_big_light = (SettingProgressView) this.mView.findViewById(R.id.spv_big_light);
        this.spv_light_system = (SettingProgressView) this.mView.findViewById(R.id.spv_light_system);
        this.executorService.execute(new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingLightView.1
            @Override // java.lang.Runnable
            public void run() {
                CarSettingLightView.this.listAmbientThree.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_car_setting_ambient_lighting_0)));
                CarSettingLightView.this.listAmbientThree.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_car_setting_ambient_lighting_1)));
                CarSettingLightView.this.listAmbientThree.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_car_setting_ambient_lighting_2)));
                CarSettingLightView.this.listAmbientThree.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_car_setting_ambient_lighting_3)));
                CarSettingLightView.this.sdv_ambient_three.setListFirst(CarSettingLightView.this.listAmbientThree);
            }
        });
        this.executorService.execute(new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingLightView.2
            @Override // java.lang.Runnable
            public void run() {
                CarSettingLightView.this.listAmbientTen.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_car_setting_ambient_lighting_0)));
                CarSettingLightView.this.listAmbientTen.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_car_setting_ambient_lighting_4)));
                CarSettingLightView.this.listAmbientTen.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_car_setting_ambient_lighting_5)));
                CarSettingLightView.this.listAmbientTen.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_car_setting_ambient_lighting_6)));
                CarSettingLightView.this.listAmbientTen.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_car_setting_ambient_lighting_7)));
                CarSettingLightView.this.listAmbientTen.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_car_setting_ambient_lighting_8)));
                CarSettingLightView.this.listAmbientTen.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_car_setting_ambient_lighting_9)));
                CarSettingLightView.this.listAmbientTen.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_car_setting_ambient_lighting_10)));
                CarSettingLightView.this.listAmbientTen.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_car_setting_ambient_lighting_10_8)));
                CarSettingLightView.this.listAmbientTen.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_car_setting_ambient_lighting_11)));
                CarSettingLightView.this.listAmbientTen.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_car_setting_ambient_lighting_12)));
                CarSettingLightView.this.sdv_ambient_ten.setListFirst(CarSettingLightView.this.listAmbientTen);
            }
        });
        this.executorService.execute(new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingLightView.3
            @Override // java.lang.Runnable
            public void run() {
                CarSettingLightView.this.listAmbientTwenty.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_car_setting_ambient_lighting_0)));
                CarSettingLightView.this.listAmbientTwenty.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_car_setting_ambient_lighting_4)));
                CarSettingLightView.this.listAmbientTwenty.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_car_setting_ambient_lighting_5)));
                CarSettingLightView.this.listAmbientTwenty.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_car_setting_ambient_lighting_6)));
                CarSettingLightView.this.listAmbientTwenty.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_car_setting_ambient_lighting_7)));
                CarSettingLightView.this.listAmbientTwenty.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_car_setting_ambient_lighting_8)));
                CarSettingLightView.this.listAmbientTwenty.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_car_setting_ambient_lighting_9)));
                CarSettingLightView.this.listAmbientTwenty.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_car_setting_ambient_lighting_10)));
                CarSettingLightView.this.listAmbientTwenty.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_car_setting_ambient_lighting_10_8)));
                CarSettingLightView.this.listAmbientTwenty.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_car_setting_ambient_lighting_11)));
                CarSettingLightView.this.listAmbientTwenty.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_car_setting_ambient_lighting_12)));
                CarSettingLightView.this.listAmbientTwenty.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_car_setting_ambient_lighting_13)));
                CarSettingLightView.this.listAmbientTwenty.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_car_setting_ambient_lighting_14)));
                CarSettingLightView.this.listAmbientTwenty.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_car_setting_ambient_lighting_15)));
                CarSettingLightView.this.listAmbientTwenty.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_car_setting_ambient_lighting_16)));
                CarSettingLightView.this.listAmbientTwenty.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_car_setting_ambient_lighting_17)));
                CarSettingLightView.this.listAmbientTwenty.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_car_setting_ambient_lighting_18)));
                CarSettingLightView.this.listAmbientTwenty.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_car_setting_ambient_lighting_19)));
                CarSettingLightView.this.listAmbientTwenty.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_car_setting_ambient_lighting_20)));
                CarSettingLightView.this.listAmbientTwenty.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_car_setting_ambient_lighting_21)));
                CarSettingLightView.this.listAmbientTwenty.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_car_setting_ambient_lighting_22)));
                CarSettingLightView.this.listAmbientTwenty.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_car_setting_ambient_lighting_33)));
                CarSettingLightView.this.listAmbientTwenty.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_car_setting_ambient_lighting_34)));
                CarSettingLightView.this.listAmbientTwenty.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_car_setting_ambient_lighting_35)));
                CarSettingLightView.this.listAmbientTwenty.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_car_setting_ambient_lighting_36)));
                CarSettingLightView.this.listAmbientTwenty.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_car_setting_ambient_lighting_37)));
                CarSettingLightView.this.listAmbientTwenty.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_car_setting_ambient_lighting_38)));
                CarSettingLightView.this.listAmbientTwenty.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_car_setting_ambient_lighting_39)));
                CarSettingLightView.this.listAmbientTwenty.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_car_setting_ambient_lighting_40)));
                CarSettingLightView.this.listAmbientTwenty.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_car_setting_ambient_lighting_41)));
                CarSettingLightView.this.listAmbientTwenty.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_car_setting_ambient_lighting_42)));
                CarSettingLightView.this.sdv_ambient_twenty.setListFirst(CarSettingLightView.this.listAmbientTwenty);
            }
        });
        this.executorService.execute(new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingLightView.4
            @Override // java.lang.Runnable
            public void run() {
                CarSettingLightView.this.listOpenTime.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_light_early)));
                CarSettingLightView.this.listOpenTime.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_light_medium)));
                CarSettingLightView.this.listOpenTime.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_light_late)));
                CarSettingLightView.this.sdv_openTime.setListFirst(CarSettingLightView.this.listOpenTime);
            }
        });
        this.executorService.execute(new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingLightView.5
            @Override // java.lang.Runnable
            public void run() {
                CarSettingLightView.this.listTravel.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_light_left)));
                CarSettingLightView.this.listTravel.add(new SettingDialogBean(CarSettingLightView.this.mContext.getString(R.string._283_light_right)));
                CarSettingLightView.this.sdv_travel.setListFirst(CarSettingLightView.this.listTravel);
            }
        });
        this.spv_light_system.setMinProgress(1);
        this.spv_light_system.setAll(4, 1.0f, "");
        this.spv_big_light.setAll(6, 1.0f, "");
        this.spv_ambient_car_in.setAll(10, 10.0f, this.mContext.getString(R.string._283_light_unit_1));
        this.spv_ambient_car_right.setAll(10, 10.0f, this.mContext.getString(R.string._283_light_unit_1));
        this.spv_ambient_car_all.setAll(10, 10.0f, this.mContext.getString(R.string._283_light_unit_1));
        this.spv_instrument.setAll(10, 10.0f, this.mContext.getString(R.string._283_light_unit_1));
        this.spv_door.setAll(10, 10.0f, this.mContext.getString(R.string._283_light_unit_1));
        this.spv_footwell.setAll(10, 10.0f, this.mContext.getString(R.string._283_light_unit_1));
        this.sdv_coming.setAll(6, 5.0f, this.mContext.getString(R.string._283_light_unit_2));
        this.sdv_leaving.setAll(6, 5.0f, this.mContext.getString(R.string._283_light_unit_2));
        this.sdv_coming.setBigAndSmallValueText(this.mContext.getString(R.string._283_close), "");
        this.sdv_leaving.setBigAndSmallValueText(this.mContext.getString(R.string._283_close), "");
        this.spv_light_system.setBigAndSmallValueText("1.0", "");
        this.spv_big_light.setBigAndSmallValueText(this.mContext.getString(R.string._283_close), "");
        this.spv_ambient_car_in.setBigAndSmallValueText(this.mContext.getString(R.string._283_car_setting_ambient_lighting_least), "");
        this.spv_ambient_car_right.setBigAndSmallValueText(this.mContext.getString(R.string._283_car_setting_ambient_lighting_least), "");
        this.spv_ambient_car_all.setBigAndSmallValueText(this.mContext.getString(R.string._283_car_setting_ambient_lighting_least), "");
    }

    private void initClick() {
        SettingSelectView.OnItemClickListener onItemClickListener = new SettingSelectView.OnItemClickListener() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingLightView$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.car_cus._283.view.SettingSelectView.OnItemClickListener
            public final void onClick(View view, boolean z) {
                this.f$0.m1105x32397322(view, z);
            }
        };
        SettingDialogView.OnItemClickListener onItemClickListener2 = new SettingDialogView.OnItemClickListener() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingLightView$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.car_cus._283.view.SettingDialogView.OnItemClickListener
            public final void onClick(View view, int i) {
                CarSettingLightView.lambda$initClick$2(view, i);
            }
        };
        SettingProgressView.OnItemClickListener onItemClickListener3 = new SettingProgressView.OnItemClickListener() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingLightView$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.car_cus._283.view.SettingProgressView.OnItemClickListener
            public final void onProgressChanged(View view, int i) {
                this.f$0.m1106xe9bc6aa4(view, i);
            }
        };
        this.ssv_assist.setOnItemClickListener(onItemClickListener);
        this.ssv_bend.setOnItemClickListener(onItemClickListener);
        this.ssv_laneChange.setOnItemClickListener(onItemClickListener);
        this.ssv_automatic.setOnItemClickListener(onItemClickListener);
        this.ssv_day.setOnItemClickListener(onItemClickListener);
        this.sdv_openTime.setOnItemClickListener(onItemClickListener2);
        this.sdv_travel.setOnItemClickListener(onItemClickListener2);
        this.sdv_ambient_three.setOnItemClickListener(onItemClickListener2);
        this.sdv_ambient_ten.setOnItemClickListener(onItemClickListener2);
        this.sdv_ambient_twenty.setOnItemClickListener(onItemClickListener2);
        this.spv_instrument.setOnItemClickListener(onItemClickListener3);
        this.spv_door.setOnItemClickListener(onItemClickListener3);
        this.spv_footwell.setOnItemClickListener(onItemClickListener3);
        this.sdv_coming.setOnItemClickListener(onItemClickListener3);
        this.sdv_leaving.setOnItemClickListener(onItemClickListener3);
        this.spv_ambient_car_in.setOnItemClickListener(onItemClickListener3);
        this.spv_ambient_car_right.setOnItemClickListener(onItemClickListener3);
        this.spv_ambient_car_all.setOnItemClickListener(onItemClickListener3);
        this.spv_big_light.setOnItemClickListener(onItemClickListener3);
        this.spv_light_system.setOnItemClickListener(onItemClickListener3);
    }

    /* renamed from: lambda$initClick$1$com-hzbhd-canbus-car_cus-_283-view-carsetting-CarSettingLightView, reason: not valid java name */
    /* synthetic */ void m1105x32397322(View view, boolean z) {
        switch (view.getId()) {
            case R.id.ssv_assist /* 2131363381 */:
                sendMsg((byte) 1, z);
                break;
            case R.id.ssv_automatic /* 2131363386 */:
                sendMsg((byte) 4, z);
                break;
            case R.id.ssv_bend /* 2131363389 */:
                sendMsg((byte) 2, z);
                break;
            case R.id.ssv_day /* 2131363393 */:
                sendMsg((byte) 16, z);
                break;
            case R.id.ssv_laneChange /* 2131363405 */:
                sendMsg((byte) 5, z);
                break;
        }
    }

    static /* synthetic */ void lambda$initClick$2(View view, int i) {
        int id = view.getId();
        if (id == R.id.sdv_openTime) {
            MessageSender.sendMsg(new byte[]{22, 109, 3, (byte) i});
        }
        if (id == R.id.sdv_travel) {
            MessageSender.sendMsg(new byte[]{22, 109, 6, (byte) i});
            return;
        }
        switch (id) {
            case R.id.sdv_ambient_ten /* 2131363262 */:
                if (i == 0) {
                    MessageSender.sendMsg(new byte[]{22, 109, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) i});
                    break;
                } else {
                    MessageSender.sendMsg(new byte[]{22, 109, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) (i + 3)});
                    break;
                }
            case R.id.sdv_ambient_three /* 2131363263 */:
                MessageSender.sendMsg(new byte[]{22, 109, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) i});
                break;
            case R.id.sdv_ambient_twenty /* 2131363264 */:
                if (i == 0) {
                    MessageSender.sendMsg(new byte[]{22, 109, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) i});
                    break;
                } else {
                    MessageSender.sendMsg(new byte[]{22, 109, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) (i + 3)});
                    break;
                }
        }
    }

    /* renamed from: lambda$initClick$3$com-hzbhd-canbus-car_cus-_283-view-carsetting-CarSettingLightView, reason: not valid java name */
    /* synthetic */ void m1106xe9bc6aa4(View view, int i) {
        this.isSeekbar = true;
        int id = view.getId();
        if (id == R.id.sdv_coming) {
            MessageSender.sendMsg(new byte[]{22, 109, 10, (byte) (i * this.sdv_coming.getInterval())});
        }
        if (id == R.id.sdv_leaving) {
            MessageSender.sendMsg(new byte[]{22, 109, 11, (byte) (i * this.sdv_leaving.getInterval())});
            return;
        }
        switch (id) {
            case R.id.spv_ambient_car_all /* 2131363348 */:
                MessageSender.sendMsg(new byte[]{22, 109, 15, (byte) (i * this.spv_ambient_car_all.getInterval())});
                break;
            case R.id.spv_ambient_car_in /* 2131363349 */:
                MessageSender.sendMsg(new byte[]{22, 109, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) (i * this.spv_ambient_car_in.getInterval())});
                break;
            case R.id.spv_ambient_car_right /* 2131363350 */:
                MessageSender.sendMsg(new byte[]{22, 109, 14, (byte) (i * this.spv_ambient_car_right.getInterval())});
                break;
            case R.id.spv_big_light /* 2131363351 */:
                MessageSender.sendMsg(new byte[]{22, 109, 17, (byte) (i * this.spv_big_light.getInterval())});
                break;
            case R.id.spv_door /* 2131363352 */:
                MessageSender.sendMsg(new byte[]{22, 109, 8, (byte) (i * this.spv_door.getInterval())});
                break;
            case R.id.spv_footwell /* 2131363353 */:
                MessageSender.sendMsg(new byte[]{22, 109, 9, (byte) (i * this.spv_footwell.getInterval())});
                break;
            default:
                switch (id) {
                    case R.id.spv_instrument /* 2131363356 */:
                        MessageSender.sendMsg(new byte[]{22, 109, 7, (byte) (i * ((int) this.spv_instrument.getInterval()))});
                        break;
                    case R.id.spv_light_system /* 2131363357 */:
                        MessageSender.sendMsg(new byte[]{22, 109, 18, (byte) (i * this.spv_light_system.getInterval())});
                        break;
                }
        }
    }

    /* renamed from: refreshUi, reason: merged with bridge method [inline-methods] */
    public void m1107xd61148e9() {
        this.ssv_assist.setSelect(GeneralDzData.light_assis);
        this.ssv_bend.setSelect(GeneralDzData.light_bend);
        this.ssv_laneChange.setSelect(GeneralDzData.light_change);
        this.ssv_automatic.setSelect(GeneralDzData.light_automatic);
        this.ssv_day.setSelect(GeneralDzData.light_day);
        this.sdv_openTime.setSelect(GeneralDzData.light_openTime);
        this.sdv_travel.setSelect(GeneralDzData.light_travel);
        if (GeneralDzData.light_ambient >= 14) {
            this.sdv_ambient_twenty.setSelect(GeneralDzData.light_ambient - 3);
            this.sdv_ambient_ten.setSelect(0);
            this.sdv_ambient_three.setSelect(0);
        } else if (GeneralDzData.light_ambient >= 4) {
            this.sdv_ambient_twenty.setSelect(GeneralDzData.light_ambient - 3);
            this.sdv_ambient_ten.setSelect(GeneralDzData.light_ambient - 3);
            this.sdv_ambient_three.setSelect(0);
        } else if (GeneralDzData.light_ambient > 0) {
            this.sdv_ambient_twenty.setSelect(0);
            this.sdv_ambient_ten.setSelect(0);
            this.sdv_ambient_three.setSelect(GeneralDzData.light_ambient);
        } else if (GeneralDzData.light_ambient == 0) {
            this.sdv_ambient_twenty.setSelect(0);
            this.sdv_ambient_ten.setSelect(0);
            this.sdv_ambient_three.setSelect(0);
        }
        if (!this.isSeekbar) {
            this.spv_instrument.setValueProgress(GeneralDzData.light_instrument);
            this.spv_door.setValueProgress(GeneralDzData.light_door);
            this.spv_footwell.setValueProgress(GeneralDzData.light_footwell);
            this.sdv_coming.setValueProgress(GeneralDzData.light_coming);
            this.sdv_leaving.setValueProgress(GeneralDzData.light_leaving);
            this.spv_ambient_car_in.setValueProgress(GeneralDzData.light_ambient_in);
            this.spv_ambient_car_right.setValueProgress(GeneralDzData.light_ambient_right);
            this.spv_ambient_car_all.setValueProgress(GeneralDzData.light_ambient_all);
            this.spv_big_light.setValueProgress(GeneralDzData.light_big);
            this.spv_light_system.setValueProgress(GeneralDzData.light_system);
            return;
        }
        this.mHandler.removeMessages(0);
        this.mHandler.sendEmptyMessageDelayed(0, 200L);
    }

    private void sendMsg(byte b, boolean z) {
        MessageSender.sendMsg((byte) 109, b, z);
    }
}
