package com.hzbhd.canbus.car._165;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.car._165.MyPanoramicView;
import com.hzbhd.canbus.comm.MyApplication;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnBackCameraStatusListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AirBtnAction;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TimerUtil;
import java.util.Objects;
import java.util.TimerTask;
import nfore.android.bt.res.NfDef;

/* loaded from: classes.dex */
public class UiMgr extends AbstractUiMgr {
    static final String SHARE_165_LANGUAGE = "share_165_language";
    private BtnGroup mBtnGroup;
    private MsgMgr mMsgMgr;
    private TimerUtil mTimerUtil;
    private MyPanoramicView myPanoramicView;
    private final String TAG = "_165_UiMgr";
    private final int MSG_SEND_AIR_COMMAND_UP = 0;
    private Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.hzbhd.canbus.car._165.UiMgr.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what != 0) {
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, -32, (byte) message.arg1, 0});
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._165.UiMgr.6
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirCommand(3);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirCommand(2);
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._165.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirCommand(5);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirCommand(4);
        }
    };
    OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatFrontLeft = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._165.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr.this.sendAirCommand(11);
        }
    };
    OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatFrontRight = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._165.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr.this.sendAirCommand(13);
        }
    };
    OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerColdFrontLeft = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._165.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr.this.sendAirCommand(12);
        }
    };
    OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerColdFrontRight = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._165.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr.this.sendAirCommand(14);
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerRearLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._165.UiMgr.12
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirCommand(39);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirCommand(38);
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerRearRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._165.UiMgr.13
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            if (UiMgr.this.mDifferent == 33) {
                UiMgr.this.sendAirCommand(66);
            } else {
                UiMgr.this.mOnUpDownClickListenerRearLeft.onClickUp();
            }
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            if (UiMgr.this.mDifferent == 33) {
                UiMgr.this.sendAirCommand(65);
            } else {
                UiMgr.this.mOnUpDownClickListenerRearLeft.onClickDown();
            }
        }
    };
    private int mDifferent = getCurrentCarId();

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public View getCusPanoramicView(Context context) {
        if (this.myPanoramicView == null) {
            this.myPanoramicView = new MyPanoramicView(context);
        }
        return this.myPanoramicView;
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        if (!SharePreUtil.getBoolValue(context, "share_165_is_suppot_tire", true)) {
            removeMainPrjBtnByName(context, MainAction.TIRE_INFO);
        }
        if (!SharePreUtil.getBoolValue(context, "share_165_is_suppot_hybrid", true)) {
            removeMainPrjBtnByName(context, MainAction.HYBIRD);
        }
        if (this.mDifferent != 17) {
            remvoeSettingLeftItemByName(context, "_11_0x26_data3_bit32");
        }
        if (this.mDifferent != 49) {
            remvoeSettingLeftItemByName(context, "_55_0x65_data1_bit21");
            remvoeSettingLeftItemByName(context, "_11_0x26_data4_bit65");
            remvoeSettingLeftItemByName(context, "_165_sterring_column");
        }
        if (this.mDifferent != 1) {
            remvoeSettingLeftItemByName(context, "language_setup");
        }
        if ((this.mDifferent & NfDef.STATE_3WAY_M_HOLD) != 48) {
            removeFrontAirButtonByName(context, AirBtnAction.POLLRN_REMOVAL);
        }
        if (this.mDifferent != 33) {
            removeFrontAirButtonByName(context, AirBtnAction.BLOW_WINDOW_FOOT);
        }
        if (this.mDifferent != 50) {
            removeRearAirButtonByName(context, AirBtnAction.REAR_AUTO);
        }
        MyApplication.IS_SET = true;
    }

    public UiMgr(final Context context) {
        initPanoramicBtnGroup();
        final AirPageUiSet airUiSet = getAirUiSet(context);
        airUiSet.getFrontArea().setShowSeatHeat(false);
        airUiSet.getFrontArea().setShowSeatCold(isSupportSeatCold());
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._165.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                this.f$0.m255lambda$new$0$comhzbhdcanbuscar_165UiMgr(airUiSet, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._165.UiMgr$$ExternalSyntheticLambda11
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                this.f$0.m256lambda$new$1$comhzbhdcanbuscar_165UiMgr(airUiSet, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._165.UiMgr$$ExternalSyntheticLambda12
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                this.f$0.m259lambda$new$2$comhzbhdcanbuscar_165UiMgr(airUiSet, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._165.UiMgr$$ExternalSyntheticLambda13
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                this.f$0.m260lambda$new$3$comhzbhdcanbuscar_165UiMgr(airUiSet, i);
            }
        }});
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
        airUiSet.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.mOnMinPlusClickListenerHeatFrontLeft, this.mOnMinPlusClickListenerHeatFrontRight, this.mOnMinPlusClickListenerColdFrontLeft, this.mOnMinPlusClickListenerColdFrontRight});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._165.UiMgr.2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr.this.sendAirCommand(9);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr.this.sendAirCommand(10);
            }
        });
        airUiSet.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._165.UiMgr$$ExternalSyntheticLambda14
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                this.f$0.m261lambda$new$4$comhzbhdcanbuscar_165UiMgr(airUiSet, i);
            }
        }});
        airUiSet.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerRearLeft, null, this.mOnUpDownClickListenerRearRight});
        airUiSet.getRearArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._165.UiMgr.3
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr.this.sendAirCommand(40);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr.this.sendAirCommand(41);
            }
        });
        final SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._165.UiMgr$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public final void onClickItem(int i, int i2, int i3) {
                this.f$0.m262lambda$new$5$comhzbhdcanbuscar_165UiMgr(settingUiSet, context, i, i2, i3);
            }
        });
        settingUiSet.setOnSettingItemClickListener(new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._165.UiMgr$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
            public final void onClickItem(int i, int i2) {
                UiMgr.lambda$new$6(settingUiSet, i, i2);
            }
        });
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._165.UiMgr$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public final void onClickItem(int i, int i2, int i3) {
                UiMgr.lambda$new$7(settingUiSet, i, i2, i3);
            }
        });
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        amplifierPageUiSet.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._165.UiMgr$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
            public final void progress(AmplifierActivity.AmplifierBand amplifierBand, int i) {
                UiMgr.lambda$new$8(amplifierBand, i);
            }
        });
        amplifierPageUiSet.setOnAmplifierPositionListener(new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._165.UiMgr$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
            public final void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
                UiMgr.lambda$new$9(amplifierPosition, i);
            }
        });
        getDriverDataPageUiSet(context).setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener() { // from class: com.hzbhd.canbus.car._165.UiMgr.4
            /* JADX WARN: Type inference failed for: r2v1, types: [com.hzbhd.canbus.car._165.UiMgr$4$1] */
            @Override // com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener
            public void onStatusChange(int i) {
                if (i == -1) {
                    new Thread() { // from class: com.hzbhd.canbus.car._165.UiMgr.4.1
                        @Override // java.lang.Thread, java.lang.Runnable
                        public void run() {
                            super.run();
                            try {
                                CanbusMsgSender.sendMsg(new byte[]{22, -112, 33, 0});
                                sleep(100L);
                                CanbusMsgSender.sendMsg(new byte[]{22, -112, 34, 0});
                                sleep(100L);
                                CanbusMsgSender.sendMsg(new byte[]{22, -112, 35, 0});
                                sleep(100L);
                                CanbusMsgSender.sendMsg(new byte[]{22, -112, 39, 0});
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                }
            }
        });
        ParkPageUiSet parkPageUiSet = getParkPageUiSet(context);
        parkPageUiSet.setShowCusPanoramicView(isSupportPanoramic());
        parkPageUiSet.setOnBackCameraStatusListener(new OnBackCameraStatusListener() { // from class: com.hzbhd.canbus.car._165.UiMgr$$ExternalSyntheticLambda6
            @Override // com.hzbhd.canbus.interfaces.OnBackCameraStatusListener
            public final void addViewToWindows() {
                this.f$0.m257lambda$new$10$comhzbhdcanbuscar_165UiMgr(context);
            }
        });
        parkPageUiSet.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._165.UiMgr$$ExternalSyntheticLambda7
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public final void onClickItem(int i) {
                UiMgr.lambda$new$11(i);
            }
        });
        if (this.mDifferent == 49) {
            final DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) Objects.requireNonNull(context.getSystemService("window"))).getDefaultDisplay().getRealMetrics(displayMetrics);
            parkPageUiSet.setOnPanoramicItemTouchListener(new OnPanoramicItemTouchListener() { // from class: com.hzbhd.canbus.car._165.UiMgr$$ExternalSyntheticLambda8
                @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener
                public final void onTouchItem(MotionEvent motionEvent) {
                    this.f$0.m258lambda$new$12$comhzbhdcanbuscar_165UiMgr(displayMetrics, context, motionEvent);
                }
            });
        }
        final OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(context);
        originalCarDevicePageUiSet.setOnOriginalTopBtnClickListeners(new OnOriginalTopBtnClickListener() { // from class: com.hzbhd.canbus.car._165.UiMgr$$ExternalSyntheticLambda9
            @Override // com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener
            public final void onClickTopBtnItem(int i) {
                UiMgr.lambda$new$13(originalCarDevicePageUiSet, i);
            }
        });
        originalCarDevicePageUiSet.setOnOriginalBottomBtnClickListeners(new OnOriginalBottomBtnClickListener() { // from class: com.hzbhd.canbus.car._165.UiMgr$$ExternalSyntheticLambda10
            @Override // com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener
            public final void onClickBottomBtnItem(int i) {
                UiMgr.lambda$new$14(originalCarDevicePageUiSet, i);
            }
        });
    }

    /* renamed from: lambda$new$0$com-hzbhd-canbus-car-_165-UiMgr, reason: not valid java name */
    /* synthetic */ void m255lambda$new$0$comhzbhdcanbuscar_165UiMgr(AirPageUiSet airPageUiSet, int i) {
        sendAirCommand(airPageUiSet.getFrontArea().getLineBtnAction()[0][i]);
    }

    /* renamed from: lambda$new$1$com-hzbhd-canbus-car-_165-UiMgr, reason: not valid java name */
    /* synthetic */ void m256lambda$new$1$comhzbhdcanbuscar_165UiMgr(AirPageUiSet airPageUiSet, int i) {
        sendAirCommand(airPageUiSet.getFrontArea().getLineBtnAction()[1][i]);
    }

    /* renamed from: lambda$new$2$com-hzbhd-canbus-car-_165-UiMgr, reason: not valid java name */
    /* synthetic */ void m259lambda$new$2$comhzbhdcanbuscar_165UiMgr(AirPageUiSet airPageUiSet, int i) {
        sendAirCommand(airPageUiSet.getFrontArea().getLineBtnAction()[2][i]);
    }

    /* renamed from: lambda$new$3$com-hzbhd-canbus-car-_165-UiMgr, reason: not valid java name */
    /* synthetic */ void m260lambda$new$3$comhzbhdcanbuscar_165UiMgr(AirPageUiSet airPageUiSet, int i) {
        sendAirCommand(airPageUiSet.getFrontArea().getLineBtnAction()[3][i]);
    }

    /* renamed from: lambda$new$4$com-hzbhd-canbus-car-_165-UiMgr, reason: not valid java name */
    /* synthetic */ void m261lambda$new$4$comhzbhdcanbuscar_165UiMgr(AirPageUiSet airPageUiSet, int i) {
        sendAirCommand(airPageUiSet.getRearArea().getLineBtnAction()[3][i]);
    }

    /* renamed from: lambda$new$5$com-hzbhd-canbus-car-_165-UiMgr, reason: not valid java name */
    /* synthetic */ void m262lambda$new$5$comhzbhdcanbuscar_165UiMgr(SettingPageUiSet settingPageUiSet, Context context, int i, int i2, int i3) {
        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        titleSrn.hashCode();
        switch (titleSrn) {
            case "_55_0x65_data1_bit21":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 20, (byte) i3});
                return;
            case "_283_rear_radar_alert_distance":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 23, (byte) i3});
                return;
            case "_165_sterring_column":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 38, (byte) i3});
                return;
            case "_18_vehicle_setting_item_1_0":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 19, (byte) i3});
                return;
            case "_18_vehicle_setting_item_1_1":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 18, (byte) i3});
                return;
            case "_18_vehicle_setting_item_1_2":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 1, (byte) i3});
                return;
            case "_18_vehicle_setting_item_1_3":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 2, (byte) i3});
                return;
            case "_18_vehicle_setting_item_1_4":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 14, (byte) i3});
                return;
            case "_18_vehicle_setting_item_1_5":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 15, (byte) i3});
                return;
            case "_18_vehicle_setting_item_2_4":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) i3});
                return;
            case "_18_vehicle_setting_item_2_5":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 16, (byte) i3});
                return;
            case "_18_vehicle_setting_item_2_6":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 3, (byte) i3});
                return;
            case "_18_vehicle_setting_item_2_7":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 17, (byte) i3});
                return;
            case "_165_ev_mode":
                CanbusMsgSender.sendMsg(new byte[]{22, 51, 65});
                return;
            case "_11_0x26_data2_bit20":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 35, (byte) (i3 + 1)});
                return;
            case "amplifier_switch":
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 8, (byte) i3});
                return;
            case "language_setup":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 36, (byte) i3});
                getMsgMgr(context).updateSetting(i, i2, i3);
                SharePreUtil.setIntValue(context, SHARE_165_LANGUAGE, i3);
                return;
            case "_18_vehicle_setting_item_3_210":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 6, (byte) i3});
                break;
            case "_11_0x26_data3_bit32":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 34, (byte) i3});
                break;
            case "_186_asl":
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, (byte) (1 << (i3 * 3))});
                break;
            case "_283_front_radar_alert_distance":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 24, (byte) i3});
                break;
            case "_165_eco_mode":
                CanbusMsgSender.sendMsg(new byte[]{22, 51, 64});
                break;
            case "light_ctrl_3":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 4, (byte) i3});
                break;
            case "_11_0x26_data4_bit65":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 37, (byte) i3});
                break;
            case "_55_0x65_data1_bit54_item1":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 0, (byte) i3});
                break;
            case "_55_0x67_data1_bit32":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 7, (byte) i3});
                break;
            case "_18_surround":
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 9, (byte) i3});
                break;
            case "_18_vehicle_setting_item_3_43":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) i3});
                break;
        }
    }

    static /* synthetic */ void lambda$new$6(SettingPageUiSet settingPageUiSet, int i, int i2) {
        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        titleSrn.hashCode();
        switch (titleSrn) {
            case "_165_clear_data":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 9, 0});
                break;
            case "clear_data":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 10, 0});
                break;
            case "update_data":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 8, 0});
                break;
            case "?":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 11, 0});
                break;
        }
    }

    static /* synthetic */ void lambda$new$7(SettingPageUiSet settingPageUiSet, int i, int i2, int i3) {
        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        titleSrn.hashCode();
        if (titleSrn.equals("hiworld_jeep_123_0x60_data1_65")) {
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 5, (byte) i3});
        } else if (titleSrn.equals("radar_volume")) {
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 21, (byte) i3});
        }
    }

    /* renamed from: com.hzbhd.canbus.car._165.UiMgr$18, reason: invalid class name */
    static /* synthetic */ class AnonymousClass18 {
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand;
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition;

        static {
            int[] iArr = new int[AmplifierActivity.AmplifierBand.values().length];
            $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand = iArr;
            try {
                iArr[AmplifierActivity.AmplifierBand.BASS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.TREBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.MIDDLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.VOLUME.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[AmplifierActivity.AmplifierPosition.values().length];
            $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition = iArr2;
            try {
                iArr2[AmplifierActivity.AmplifierPosition.FRONT_REAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[AmplifierActivity.AmplifierPosition.LEFT_RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    static /* synthetic */ void lambda$new$8(AmplifierActivity.AmplifierBand amplifierBand, int i) {
        int i2 = AnonymousClass18.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[amplifierBand.ordinal()];
        if (i2 == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 4, (byte) (i + 2)});
            return;
        }
        if (i2 == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 5, (byte) (i + 2)});
        } else if (i2 == 3) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 6, (byte) (i + 2)});
        } else {
            if (i2 != 4) {
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 7, (byte) i});
        }
    }

    static /* synthetic */ void lambda$new$9(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
        int i2 = AnonymousClass18.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[amplifierPosition.ordinal()];
        if (i2 == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, (byte) (i + 7)});
        } else {
            if (i2 != 2) {
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 2, (byte) (i + 7)});
        }
    }

    /* renamed from: lambda$new$10$com-hzbhd-canbus-car-_165-UiMgr, reason: not valid java name */
    /* synthetic */ void m257lambda$new$10$comhzbhdcanbuscar_165UiMgr(Context context) {
        if (this.mBtnGroup != null) {
            ((MyPanoramicView) getCusPanoramicView(context)).setOnBtnClickListener(this.mBtnGroup.getBtnClick(context));
        }
    }

    static /* synthetic */ void lambda$new$11(int i) {
        if (i == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 32, 0});
        }
    }

    /* renamed from: lambda$new$12$com-hzbhd-canbus-car-_165-UiMgr, reason: not valid java name */
    /* synthetic */ void m258lambda$new$12$comhzbhdcanbuscar_165UiMgr(DisplayMetrics displayMetrics, Context context, MotionEvent motionEvent) {
        boolean zIsMiscReverse = CommUtil.isMiscReverse();
        if ((motionEvent.getAction() == 0 || motionEvent.getAction() == 1) && zIsMiscReverse) {
            final byte[] bArr = {22, -123, (byte) ((motionEvent.getX() / displayMetrics.widthPixels) * 255.0f), (byte) ((motionEvent.getY() / CommUtil.getDimenByResId(context, "video_height")) * 255.0f), (byte) (motionEvent.getAction() == 0 ? 1 : 0)};
            getTimerUtil().stopTimer();
            getTimerUtil().startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._165.UiMgr.5
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    if (!CommUtil.isMiscReverse()) {
                        UiMgr.this.getTimerUtil().stopTimer();
                    } else {
                        CanbusMsgSender.sendMsg(bArr);
                    }
                }
            }, 0L, 1000L);
        }
    }

    static /* synthetic */ void lambda$new$13(OriginalCarDevicePageUiSet originalCarDevicePageUiSet, int i) {
        String str = originalCarDevicePageUiSet.getRowTopBtnAction()[i];
        str.hashCode();
        if (str.equals("lock")) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 97, (byte) (!GeneralOriginalCarDeviceData.lock ? 1 : 0)});
        } else if (str.equals("power")) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 96, (byte) (!GeneralOriginalCarDeviceData.power ? 1 : 0)});
        }
    }

    static /* synthetic */ void lambda$new$14(OriginalCarDevicePageUiSet originalCarDevicePageUiSet, int i) {
        String str = originalCarDevicePageUiSet.getRowBottomBtnAction()[i];
        str.hashCode();
        switch (str) {
            case "auto_store":
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 98, 1});
                break;
            case "play":
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 100, 1});
                break;
            case "stop":
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 99, 1});
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public TimerUtil getTimerUtil() {
        if (this.mTimerUtil == null) {
            this.mTimerUtil = new TimerUtil();
        }
        return this.mTimerUtil;
    }

    private void sendAirCommand(String str) {
        str.hashCode();
        switch (str) {
            case "pollrn_removal":
                sendAirCommand(33);
                break;
            case "front_defog":
                sendAirCommand(19);
                break;
            case "rear_defog":
                sendAirCommand(20);
                break;
            case "rear_power":
                sendAirCommand(44);
                break;
            case "blow_positive":
                if (this.mDifferent == 33) {
                    sendAirCommand(67);
                    break;
                } else {
                    sendAirCommand(8);
                    break;
                }
            case "blow_negative":
                if (this.mDifferent == 33) {
                    sendAirCommand(69);
                    break;
                } else {
                    sendAirCommand(7);
                    break;
                }
            case "ac":
                sendAirCommand(23);
                break;
            case "auto":
                sendAirCommand(21);
                break;
            case "dual":
                sendAirCommand(16);
                break;
            case "rear":
                sendAirCommand(42);
                break;
            case "power":
                sendAirCommand(1);
                break;
            case "in_out_cycle":
                sendAirCommand(25);
                break;
            case "auto_wind_mode":
                sendAirCommand(32);
                break;
            case "blow_window_foot":
                sendAirCommand(68);
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirCommand(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -32, (byte) i, 1});
        Message message = new Message();
        message.what = 0;
        message.arg1 = i;
        this.mHandler.sendMessageDelayed(message, 100L);
    }

    private MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }

    private void initPanoramicBtnGroup() {
        int i = this.mDifferent;
        if ((i & NfDef.STATE_3WAY_M_HOLD) == 32) {
            this.mBtnGroup = new BtnGroup(new OnPanoramicBtnCllick() { // from class: com.hzbhd.canbus.car._165.UiMgr.14
                @Override // com.hzbhd.canbus.car._165.UiMgr.OnPanoramicBtnCllick, com.hzbhd.canbus.car._165.MyPanoramicView.OnBtnClickListener
                public void onStartTopClick() {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, 1});
                }

                @Override // com.hzbhd.canbus.car._165.UiMgr.OnPanoramicBtnCllick, com.hzbhd.canbus.car._165.MyPanoramicView.OnBtnClickListener
                public void onStartBottomClick() {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, 2});
                }
            }, new OnPanoramicBtnCllick() { // from class: com.hzbhd.canbus.car._165.UiMgr.15
                @Override // com.hzbhd.canbus.car._165.UiMgr.OnPanoramicBtnCllick, com.hzbhd.canbus.car._165.MyPanoramicView.OnBtnClickListener
                public void onStartBottomClick() {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, 4});
                }

                @Override // com.hzbhd.canbus.car._165.UiMgr.OnPanoramicBtnCllick, com.hzbhd.canbus.car._165.MyPanoramicView.OnBtnClickListener
                public void onEndTopClick() {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, 6});
                }

                @Override // com.hzbhd.canbus.car._165.UiMgr.OnPanoramicBtnCllick, com.hzbhd.canbus.car._165.MyPanoramicView.OnBtnClickListener
                public void onEndBottomClick() {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, 3});
                }
            });
        } else if (i == 1) {
            this.mBtnGroup = new BtnGroup(new OnPanoramicBtnCllick() { // from class: com.hzbhd.canbus.car._165.UiMgr.16
                @Override // com.hzbhd.canbus.car._165.UiMgr.OnPanoramicBtnCllick, com.hzbhd.canbus.car._165.MyPanoramicView.OnBtnClickListener
                public void onStartBottomClick() {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, 2});
                }

                @Override // com.hzbhd.canbus.car._165.UiMgr.OnPanoramicBtnCllick, com.hzbhd.canbus.car._165.MyPanoramicView.OnBtnClickListener
                public void onMidBottomClick() {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, 1});
                }
            }, new OnPanoramicBtnCllick() { // from class: com.hzbhd.canbus.car._165.UiMgr.17
                @Override // com.hzbhd.canbus.car._165.UiMgr.OnPanoramicBtnCllick, com.hzbhd.canbus.car._165.MyPanoramicView.OnBtnClickListener
                public void onStartBottomClick() {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, 4});
                }

                @Override // com.hzbhd.canbus.car._165.UiMgr.OnPanoramicBtnCllick, com.hzbhd.canbus.car._165.MyPanoramicView.OnBtnClickListener
                public void onMidBottomClick() {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, 7});
                }

                @Override // com.hzbhd.canbus.car._165.UiMgr.OnPanoramicBtnCllick, com.hzbhd.canbus.car._165.MyPanoramicView.OnBtnClickListener
                public void onEndBottomClick() {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, 3});
                }
            });
        }
    }

    private class OnPanoramicBtnCllick implements MyPanoramicView.OnBtnClickListener {
        private OnPanoramicBtnCllick() {
        }

        @Override // com.hzbhd.canbus.car._165.MyPanoramicView.OnBtnClickListener
        public void onStartTopClick() {
            Log.i("_165_UiMgr", "onStartTopClick: ");
        }

        @Override // com.hzbhd.canbus.car._165.MyPanoramicView.OnBtnClickListener
        public void onStartBottomClick() {
            Log.i("_165_UiMgr", "onStartBottomClick: ");
        }

        @Override // com.hzbhd.canbus.car._165.MyPanoramicView.OnBtnClickListener
        public void onMidBottomClick() {
            Log.i("_165_UiMgr", "onMidBottomClick: ");
        }

        @Override // com.hzbhd.canbus.car._165.MyPanoramicView.OnBtnClickListener
        public void onEndTopClick() {
            Log.i("_165_UiMgr", "onEndTopClick: ");
        }

        @Override // com.hzbhd.canbus.car._165.MyPanoramicView.OnBtnClickListener
        public void onEndBottomClick() {
            Log.i("_165_UiMgr", "onEndBottomClick: ");
        }
    }

    private class BtnGroup {
        MyPanoramicView.OnBtnClickListener onNonRevBtnClick;
        MyPanoramicView.OnBtnClickListener onRevBtnClick;

        public BtnGroup(MyPanoramicView.OnBtnClickListener onBtnClickListener, MyPanoramicView.OnBtnClickListener onBtnClickListener2) {
            this.onNonRevBtnClick = onBtnClickListener;
            this.onRevBtnClick = onBtnClickListener2;
        }

        public MyPanoramicView.OnBtnClickListener getBtnClick(Context context) {
            boolean zIsBackCamera = CommUtil.isBackCamera(context);
            boolean zIsPanoramic = CommUtil.isPanoramic(context);
            if (zIsBackCamera) {
                return this.onRevBtnClick;
            }
            if (zIsPanoramic) {
                return this.onNonRevBtnClick;
            }
            return null;
        }
    }

    private boolean isSupportPanoramic() {
        int i = this.mDifferent;
        return (i & NfDef.STATE_3WAY_M_HOLD) == 32 || i == 1;
    }

    private boolean isSupportSeatCold() {
        return this.mDifferent == 19;
    }
}
