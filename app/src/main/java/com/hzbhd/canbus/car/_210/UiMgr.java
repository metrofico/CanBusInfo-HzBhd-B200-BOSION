package com.hzbhd.canbus.car._210;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.factory.Dependency;
import com.hzbhd.canbus.factory.proxy.CanSettingProxy;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnBackCameraStatusListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    private int currentClickFront;
    private int frontRear;
    private int leftRight;
    private Context mContext;
    private MsgMgr mMsgMgr;
    private int mParkAccessInt;
    private OnAirBtnClickListener mOnFrontAirTopBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._210.UiMgr.3
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 26, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 26, 0});
                return;
            }
            if (i == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 4, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 4, 0});
                return;
            }
            if (i == 2) {
                if (((CanSettingProxy) Dependency.get(CanSettingProxy.class)).getSwitchAcTemperature()) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 61, 18, 1});
                    CanbusMsgSender.sendMsg(new byte[]{22, 61, 18, 0});
                    return;
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, 61, 17, 1});
                    CanbusMsgSender.sendMsg(new byte[]{22, 61, 17, 0});
                    return;
                }
            }
            if (i == 3) {
                if (((CanSettingProxy) Dependency.get(CanSettingProxy.class)).getSwitchAcTemperature()) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 61, 17, 1});
                    CanbusMsgSender.sendMsg(new byte[]{22, 61, 17, 0});
                    return;
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, 61, 18, 1});
                    CanbusMsgSender.sendMsg(new byte[]{22, 61, 18, 0});
                    return;
                }
            }
            if (i == 4) {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 44, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 44, 0});
            } else {
                if (i != 5) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 3, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 3, 0});
            }
        }
    };
    private OnAirBtnClickListener mOnFrontAirBottomLeftBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._210.UiMgr.4
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 2, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 2, 0});
            } else if (((CanSettingProxy) Dependency.get(CanSettingProxy.class)).getSwitchAcTemperature()) {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 24, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 24, 0});
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 23, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 23, 0});
            }
        }
    };
    private OnAirBtnClickListener mOnFrontAirBottomRightBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._210.UiMgr.5
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 1, 0});
            } else if (((CanSettingProxy) Dependency.get(CanSettingProxy.class)).getSwitchAcTemperature()) {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 23, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 23, 0});
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 24, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 24, 0});
            }
        }
    };
    private OnAirBtnClickListener mOnFrontOnAirBottomBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._210.UiMgr.6
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 5, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 5, 0});
                return;
            }
            if (i == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 6, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 6, 0});
            } else if (i == 2) {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 7, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 7, 0});
            } else {
                if (i != 3) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 45, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 45, 0});
            }
        }
    };
    private OnAirSeatClickListener mOnFrontAirSeatClickListener = new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._210.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onLeftSeatClick() {
            int i = UiMgr.this.currentClickFront;
            if (i == 0) {
                UiMgr.this.currentClickFront = 1;
                UiMgr.this.sendAirClick(8);
            } else if (i == 1) {
                UiMgr.this.currentClickFront = 2;
                UiMgr.this.sendAirClick(9);
            } else {
                if (i != 2) {
                    return;
                }
                UiMgr.this.currentClickFront = 0;
                UiMgr.this.sendAirClick(10);
            }
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onRightSeatClick() {
            int i = UiMgr.this.currentClickFront;
            if (i == 0) {
                UiMgr.this.currentClickFront = 1;
                UiMgr.this.sendAirClick(8);
            } else if (i == 1) {
                UiMgr.this.currentClickFront = 2;
                UiMgr.this.sendAirClick(9);
            } else {
                if (i != 2) {
                    return;
                }
                UiMgr.this.currentClickFront = 0;
                UiMgr.this.sendAirClick(10);
            }
        }
    };
    private OnAirTemperatureUpDownClickListener mFrontTempSetViewOnUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._210.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 14, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 14, 0});
        }
    };
    private OnAirTemperatureUpDownClickListener mFrontTempSetViewOnUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._210.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 15, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 15, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 16, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 16, 0});
        }
    };
    private OnAirWindSpeedUpDownClickListener mSetFrontWindSpeedViewOnClickListener = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._210.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 11, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 11, 0});
        }
    };
    private OnAirSeatHeatColdMinPlusClickListener mFrontLeftSeatHeatListener = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._210.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 17, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 17, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 17, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 17, 0});
        }
    };
    private OnAirSeatHeatColdMinPlusClickListener mFrontRightSeatHeatListener = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._210.UiMgr.12
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 18, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 18, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 18, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 18, 0});
        }
    };
    private OnAirSeatHeatColdMinPlusClickListener mFrontLeftSeatColdListener = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._210.UiMgr.13
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 23, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 23, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 23, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 23, 0});
        }
    };
    private OnAirSeatHeatColdMinPlusClickListener mFrontRightSeatColdListener = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._210.UiMgr.14
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 24, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 24, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 24, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 24, 0});
        }
    };
    private OnAirTemperatureUpDownClickListener mRearTempSetViewOnUpDownClickListenerCenter = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._210.UiMgr.15
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 32, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 32, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 33, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 33, 0});
        }
    };
    private OnAirWindSpeedUpDownClickListener mSetRearWindSpeedViewOnClickListener = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._210.UiMgr.16
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 43, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 43, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 42, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 42, 0});
        }
    };
    private OnAirBtnClickListener mOnRearAirBottomBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._210.UiMgr.17
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 34, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 34, 0});
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 46, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 46, 0});
            }
        }
    };
    private OnSettingItemSelectListener mOnItemSelectedListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._210.UiMgr.18
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            if (i == 0) {
                if (i3 == 2) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, 27});
                    return;
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte) (i3 + 1)});
                    return;
                }
            }
            if (i == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, 109, 4, (byte) i3});
                return;
            }
            if (i == 2) {
                CanbusMsgSender.sendMsg(new byte[]{22, 108, 5, (byte) i3});
                return;
            }
            if (i == 3) {
                if (i2 != 0) {
                    SharePreUtil.setIntValue(UiMgr.this.mContext, "_210_park_assess_item", i3);
                    UiMgr.this.mMsgMgr.updateOrignalSetting();
                    return;
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, -14, 6, (byte) i3});
                    return;
                }
            }
            if (i == 4) {
                CanbusMsgSender.sendMsg(new byte[]{22, 111, 3, (byte) i3, 32, 0});
            } else {
                if (i != 5) {
                    return;
                }
                if (i2 == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 7, (byte) i3});
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 8, (byte) i3});
                }
            }
        }
    };
    private OnPanoramicItemClickListener mOnPanoramicItemClickListener = new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._210.UiMgr.19
        @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                if (MsgMgr.angleWide) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -14, 8, 0});
                    return;
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, -14, 8, 1});
                    return;
                }
            }
            if (i != 1) {
                return;
            }
            if (MsgMgr.overlook) {
                CanbusMsgSender.sendMsg(new byte[]{22, -14, 9, 0});
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, -14, 9, 1});
            }
        }
    };
    private OnAmplifierSeekBarListener mAmplifierSeekBarListener = new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._210.UiMgr.20
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
        public void progress(AmplifierActivity.AmplifierBand amplifierBand, int i) {
            Log.d("cwh", "progress = " + i);
            int i2 = AnonymousClass22.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[amplifierBand.ordinal()];
            if (i2 == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -83, 4, (byte) i});
                return;
            }
            if (i2 == 2) {
                CanbusMsgSender.sendMsg(new byte[]{22, -83, 6, (byte) i});
            } else if (i2 == 3) {
                CanbusMsgSender.sendMsg(new byte[]{22, -83, 5, (byte) i});
            } else {
                if (i2 != 4) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -83, 1, (byte) i});
            }
        }
    };
    private OnAmplifierPositionListener mOnAmplifierPositionListener = new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._210.UiMgr.21
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
        public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
            int i2 = AnonymousClass22.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[amplifierPosition.ordinal()];
            if (i2 == 1) {
                if (UiMgr.this.leftRight != i) {
                    UiMgr.this.leftRight = i;
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 2, (byte) (i + 7)});
                }
                Log.d("cwh", "value  = " + i);
                Log.d("cwh", "leftRight  = " + UiMgr.this.leftRight);
                return;
            }
            if (i2 != 2) {
                return;
            }
            if (UiMgr.this.frontRear != i) {
                UiMgr.this.frontRear = i;
                CanbusMsgSender.sendMsg(new byte[]{22, -83, 3, (byte) (i + 7)});
            }
            Log.d("cwh", "value  = " + i);
            Log.d("cwh", "frontRear  = " + UiMgr.this.frontRear);
        }
    };

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
    }

    public UiMgr(Context context) {
        this.mContext = context;
        this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(this.mOnItemSelectedListener);
        final ParkPageUiSet parkPageUiSet = getParkPageUiSet(context);
        parkPageUiSet.setOnPanoramicItemClickListener(this.mOnPanoramicItemClickListener);
        parkPageUiSet.setOnBackCameraStatusListener(new OnBackCameraStatusListener() { // from class: com.hzbhd.canbus.car._210.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnBackCameraStatusListener
            public void addViewToWindows() {
                parkPageUiSet.setShowRadar(SharePreUtil.getIntValue(UiMgr.this.mContext, "_210_park_assess_item", 0) == 1);
            }
        });
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        amplifierPageUiSet.setOnAmplifierSeekBarListener(this.mAmplifierSeekBarListener);
        amplifierPageUiSet.setOnAmplifierPositionListener(this.mOnAmplifierPositionListener);
        AirPageUiSet airUiSet = getAirUiSet(context);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnFrontAirTopBtnClickListener, this.mOnFrontAirBottomLeftBtnClickListener, this.mOnFrontAirBottomRightBtnClickListener, this.mOnFrontOnAirBottomBtnClickListener});
        airUiSet.getFrontArea().setOnAirSeatClickListener(this.mOnFrontAirSeatClickListener);
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mFrontTempSetViewOnUpDownClickListenerLeft, null, this.mFrontTempSetViewOnUpDownClickListenerRight});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.mSetFrontWindSpeedViewOnClickListener);
        airUiSet.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.mFrontLeftSeatHeatListener, this.mFrontRightSeatHeatListener, this.mFrontLeftSeatColdListener, this.mFrontRightSeatColdListener});
        airUiSet.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{null, this.mRearTempSetViewOnUpDownClickListenerCenter, null});
        airUiSet.getRearArea().setSetWindSpeedViewOnClickListener(this.mSetRearWindSpeedViewOnClickListener);
        airUiSet.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, this.mOnRearAirBottomBtnClickListener});
        settingUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._210.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
            public void onConfirmClick(int i, int i2) {
                if (i == 5) {
                    if (i2 == 2) {
                        Toast.makeText(UiMgr.this.mContext, UiMgr.this.mContext.getText(R.string.reset_completed), 0).show();
                        CanbusMsgSender.sendMsg(new byte[]{22, -83, 10, 1});
                    } else {
                        Toast.makeText(UiMgr.this.mContext, UiMgr.this.mContext.getText(R.string.reset_completed), 0).show();
                        CanbusMsgSender.sendMsg(new byte[]{22, -83, 11, 1});
                    }
                }
            }
        });
    }

    /* renamed from: com.hzbhd.canbus.car._210.UiMgr$22, reason: invalid class name */
    static /* synthetic */ class AnonymousClass22 {
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand;
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition;

        static {
            int[] iArr = new int[AmplifierActivity.AmplifierPosition.values().length];
            $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition = iArr;
            try {
                iArr[AmplifierActivity.AmplifierPosition.LEFT_RIGHT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[AmplifierActivity.AmplifierPosition.FRONT_REAR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[AmplifierActivity.AmplifierBand.values().length];
            $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand = iArr2;
            try {
                iArr2[AmplifierActivity.AmplifierBand.BASS.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.TREBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.MIDDLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.VOLUME.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirClick(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, 61, (byte) i, 1});
    }
}
