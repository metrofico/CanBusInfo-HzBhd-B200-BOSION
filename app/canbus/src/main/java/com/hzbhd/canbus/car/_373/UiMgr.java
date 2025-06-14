package com.hzbhd.canbus.car._373;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierCreateAndDestroyListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    Context mContext;
    MsgMgr mMsgMgr;
    DecimalFormat towDecimal = new DecimalFormat("###0.00");
    DecimalFormat oneDecimal = new DecimalFormat("###0.0");
    DecimalFormat timeFormat = new DecimalFormat("00");
    OnAmplifierPositionListener onAmplifierPositionListener = new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._373.UiMgr.2
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
        public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
            int i2 = AnonymousClass23.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[amplifierPosition.ordinal()];
            if (i2 == 1) {
                UiMgr.this.AmpData2 = (-i) + 10;
                UiMgr.this.send0x93AmpInfo();
                AmpUtil.saveAmpSendValue(UiMgr.this.mContext, 5, UiMgr.this.AmpData2);
                return;
            }
            if (i2 != 2) {
                return;
            }
            UiMgr.this.AmpData1 = i + 10;
            UiMgr.this.send0x93AmpInfo();
            AmpUtil.saveAmpSendValue(UiMgr.this.mContext, 6, UiMgr.this.AmpData1);
        }
    };
    OnAmplifierSeekBarListener onAmplifierSeekBarListener = new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._373.UiMgr.3
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
        public void progress(AmplifierActivity.AmplifierBand amplifierBand, int i) {
            int i2 = AnonymousClass23.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[amplifierBand.ordinal()];
            if (i2 == 1) {
                UiMgr.this.AmpData0 = i;
                UiMgr.this.send0x93AmpInfo();
                AmpUtil.saveAmpSendValue(UiMgr.this.mContext, 1, UiMgr.this.AmpData0);
                return;
            }
            if (i2 == 2) {
                UiMgr.this.AmpData3 = i + 1;
                UiMgr.this.send0x93AmpInfo();
                AmpUtil.saveAmpSendValue(UiMgr.this.mContext, 4, UiMgr.this.AmpData3);
                return;
            }
            if (i2 == 3) {
                UiMgr.this.AmpData4 = i + 1;
                UiMgr.this.send0x93AmpInfo();
                AmpUtil.saveAmpSendValue(UiMgr.this.mContext, 3, UiMgr.this.AmpData4);
                return;
            }
            if (i2 != 4) {
                return;
            }
            UiMgr.this.AmpData5 = i + 1;
            UiMgr.this.send0x93AmpInfo();
            AmpUtil.saveAmpSendValue(UiMgr.this.mContext, 2, UiMgr.this.AmpData5);
        }
    };
    OnAmplifierCreateAndDestroyListener onAmplifierCreateAndDestroyListener = new OnAmplifierCreateAndDestroyListener() { // from class: com.hzbhd.canbus.car._373.UiMgr.4
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierCreateAndDestroyListener
        public void create() {
        }

        @Override // com.hzbhd.canbus.interfaces.OnAmplifierCreateAndDestroyListener
        public void destroy() {
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._373.UiMgr.5
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) throws InterruptedException {
            if (i == 0) {
                UiMgr.this.send0x95AirInfo(0);
            } else if (i == 1) {
                UiMgr.this.send0x95AirInfo(1);
            } else {
                if (i != 2) {
                    return;
                }
                UiMgr.this.send0x95AirInfo(36);
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._373.UiMgr.6
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._373.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) throws InterruptedException {
            if (i != 0) {
                return;
            }
            UiMgr.this.send0x95AirInfo(4);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._373.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) throws InterruptedException {
            if (i == 0) {
                UiMgr.this.send0x95AirInfo(7);
            } else {
                if (i != 1) {
                    return;
                }
                UiMgr.this.send0x95AirInfo(3);
            }
        }
    };
    int windState = 0;
    OnAirSeatClickListener onAirSeatClickListener = new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._373.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onLeftSeatClick() throws InterruptedException {
            if (UiMgr.this.windState == 0) {
                UiMgr.this.send0x95AirInfo(8);
                UiMgr.this.windState = 1;
                return;
            }
            if (UiMgr.this.windState == 1) {
                UiMgr.this.send0x95AirInfo(8);
                UiMgr.this.windState = 2;
            } else if (UiMgr.this.windState == 2) {
                UiMgr.this.send0x95AirInfo(8);
                UiMgr.this.windState = 3;
            } else if (UiMgr.this.windState == 3) {
                UiMgr.this.send0x95AirInfo(8);
                UiMgr.this.windState = 0;
            }
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onRightSeatClick() throws InterruptedException {
            if (UiMgr.this.windState == 0) {
                UiMgr.this.send0x95AirInfo(8);
                UiMgr.this.windState = 1;
                return;
            }
            if (UiMgr.this.windState == 1) {
                UiMgr.this.send0x95AirInfo(8);
                UiMgr.this.windState = 2;
            } else if (UiMgr.this.windState == 2) {
                UiMgr.this.send0x95AirInfo(8);
                UiMgr.this.windState = 3;
            } else if (UiMgr.this.windState == 3) {
                UiMgr.this.send0x95AirInfo(8);
                UiMgr.this.windState = 0;
            }
        }
    };
    OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._373.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() throws InterruptedException {
            UiMgr.this.send0x95AirInfo(12);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() throws InterruptedException {
            UiMgr.this.send0x95AirInfo(13);
        }
    };
    OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._373.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() throws InterruptedException {
            UiMgr.this.send0x95AirInfo(15);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() throws InterruptedException {
            UiMgr.this.send0x95AirInfo(14);
        }
    };
    OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._373.UiMgr.12
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() throws InterruptedException {
            UiMgr.this.send0x95AirInfo(17);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() throws InterruptedException {
            UiMgr.this.send0x95AirInfo(16);
        }
    };
    OnAirPageStatusListener onAirPageStatusListener = new OnAirPageStatusListener() { // from class: com.hzbhd.canbus.car._373.UiMgr.13
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener
        public void onStatusChange(int i) {
            UiMgr.this.requestDate(5);
        }
    };
    OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatFrontLeft = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._373.UiMgr.14
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() throws InterruptedException {
            UiMgr.this.send0x95AirInfo(32);
        }
    };
    OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatFrontRight = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._373.UiMgr.15
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() throws InterruptedException {
            UiMgr.this.send0x95AirInfo(34);
        }
    };
    OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerColdFrontLeft = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._373.UiMgr.16
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() throws InterruptedException {
            UiMgr.this.send0x95AirInfo(33);
        }
    };
    OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerColdFrontRight = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._373.UiMgr.17
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() throws InterruptedException {
            UiMgr.this.send0x95AirInfo(35);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerRearBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._373.UiMgr.18
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) throws InterruptedException {
            if (i == 0) {
                UiMgr.this.send0x96RearAirInfo(0);
            } else if (i == 1) {
                UiMgr.this.send0x96RearAirInfo(4);
            } else {
                if (i != 2) {
                    return;
                }
                UiMgr.this.send0x96RearAirInfo(31);
            }
        }
    };
    OnAirWindSpeedUpDownClickListener onRearAirWindSpeedUpDownClickListener = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._373.UiMgr.19
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() throws InterruptedException {
            UiMgr.this.send0x96RearAirInfo(12);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() throws InterruptedException {
            UiMgr.this.send0x96RearAirInfo(13);
        }
    };
    OnAirTemperatureUpDownClickListener onRearAirTemperatureUpDownClickListener = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._373.UiMgr.20
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() throws InterruptedException {
            UiMgr.this.send0x96RearAirInfo(15);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() throws InterruptedException {
            UiMgr.this.send0x96RearAirInfo(14);
        }
    };
    int rearSeatState = 0;
    OnAirSeatClickListener onRearAirSeatClickListener = new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._373.UiMgr.21
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onLeftSeatClick() throws InterruptedException {
            if (UiMgr.this.rearSeatState == 0) {
                UiMgr.this.send0x96RearAirInfo(8);
                UiMgr.this.rearSeatState = 1;
            } else if (UiMgr.this.rearSeatState == 1) {
                UiMgr.this.send0x96RearAirInfo(9);
                UiMgr.this.rearSeatState = 2;
            } else if (UiMgr.this.rearSeatState == 2) {
                UiMgr.this.send0x96RearAirInfo(10);
                UiMgr.this.rearSeatState = 0;
            }
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onRightSeatClick() throws InterruptedException {
            if (UiMgr.this.rearSeatState == 0) {
                UiMgr.this.send0x96RearAirInfo(8);
                UiMgr.this.rearSeatState = 1;
            } else if (UiMgr.this.rearSeatState == 1) {
                UiMgr.this.send0x96RearAirInfo(9);
                UiMgr.this.rearSeatState = 2;
            } else if (UiMgr.this.rearSeatState == 2) {
                UiMgr.this.send0x96RearAirInfo(10);
                UiMgr.this.rearSeatState = 0;
            }
        }
    };
    OnSettingItemSelectListener onSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._373.UiMgr.22
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_373_setting_car_control")) {
                UiMgr uiMgr2 = UiMgr.this;
                if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "_373_setting_car_control", "_373_setting_car_control1")) {
                    UiMgr.this.send0x97CarControlInfo(1, i3);
                }
                UiMgr uiMgr3 = UiMgr.this;
                if (i2 == uiMgr3.getSettingRightIndex(uiMgr3.mContext, "_373_setting_car_control", "_373_setting_car_control2")) {
                    UiMgr.this.send0x97CarControlInfo(39, i3);
                }
                UiMgr uiMgr4 = UiMgr.this;
                if (i2 == uiMgr4.getSettingRightIndex(uiMgr4.mContext, "_373_setting_car_control", "_373_setting_car_control_add_function")) {
                    if (i3 == 0) {
                        UiMgr.this.send0x97CarControlInfo(17, 0);
                    } else if (i3 == 1) {
                        UiMgr.this.send0x97CarControlInfo(17, 30);
                    } else if (i3 == 2) {
                        UiMgr.this.send0x97CarControlInfo(17, 60);
                    } else if (i3 == 3) {
                        UiMgr.this.send0x97CarControlInfo(17, 90);
                    }
                }
                UiMgr uiMgr5 = UiMgr.this;
                if (i2 == uiMgr5.getSettingRightIndex(uiMgr5.mContext, "_373_setting_car_control", "_373_setting_car_control3")) {
                    if (i3 == 0) {
                        UiMgr.this.send0x97CarControlInfo(18, 0);
                    } else if (i3 == 1) {
                        UiMgr.this.send0x97CarControlInfo(18, 30);
                    } else if (i3 == 2) {
                        UiMgr.this.send0x97CarControlInfo(18, 60);
                    } else if (i3 == 3) {
                        UiMgr.this.send0x97CarControlInfo(18, 90);
                    }
                }
                UiMgr uiMgr6 = UiMgr.this;
                if (i2 == uiMgr6.getSettingRightIndex(uiMgr6.mContext, "_373_setting_car_control", "_373_setting_car_control4")) {
                    UiMgr.this.send0x97CarControlInfo(19, i3 + 1);
                }
                UiMgr uiMgr7 = UiMgr.this;
                if (i2 == uiMgr7.getSettingRightIndex(uiMgr7.mContext, "_373_setting_car_control", "_373_setting_car_control5")) {
                    UiMgr.this.send0x97CarControlInfo(21, i3 + 1);
                }
                UiMgr uiMgr8 = UiMgr.this;
                if (i2 == uiMgr8.getSettingRightIndex(uiMgr8.mContext, "_373_setting_car_control", "_373_setting_car_control6")) {
                    UiMgr.this.send0x97CarControlInfo(34, i3 + 1);
                }
                UiMgr uiMgr9 = UiMgr.this;
                if (i2 == uiMgr9.getSettingRightIndex(uiMgr9.mContext, "_373_setting_car_control", "_373_setting_car_control7")) {
                    UiMgr.this.send0x97CarControlInfo(36, i3 + 1);
                }
                UiMgr uiMgr10 = UiMgr.this;
                if (i2 == uiMgr10.getSettingRightIndex(uiMgr10.mContext, "_373_setting_car_control", "_373_setting_car_control8")) {
                    UiMgr.this.send0x97CarControlInfo(37, i3 + 1);
                }
                UiMgr uiMgr11 = UiMgr.this;
                if (i2 == uiMgr11.getSettingRightIndex(uiMgr11.mContext, "_373_setting_car_control", "_373_setting_car_control9")) {
                    if (i3 == 0) {
                        UiMgr.this.send0x97CarControlInfo(50, 0);
                    } else if (i3 == 1) {
                        UiMgr.this.send0x97CarControlInfo(50, 3);
                    } else if (i3 == 2) {
                        UiMgr.this.send0x97CarControlInfo(50, 20);
                    } else if (i3 == 3) {
                        UiMgr.this.send0x97CarControlInfo(50, 40);
                    }
                }
            }
            UiMgr uiMgr12 = UiMgr.this;
            if (i == uiMgr12.getSettingLeftIndexes(uiMgr12.mContext, "_373_other_seting")) {
                UiMgr uiMgr13 = UiMgr.this;
                if (i2 == uiMgr13.getSettingRightIndex(uiMgr13.mContext, "_373_other_seting", "_373_amp_seting1")) {
                    if (i3 == 0) {
                        UiMgr.this.AmpData6 = 0;
                    } else if (i3 == 1) {
                        UiMgr.this.AmpData6 = 2;
                    } else if (i3 == 2) {
                        UiMgr.this.AmpData6 = 4;
                    } else if (i3 == 3) {
                        UiMgr.this.AmpData6 = 6;
                    }
                    UiMgr.this.send0x93AmpInfo();
                    AmpUtil.saveAmpSendValue(UiMgr.this.mContext, 7, UiMgr.this.AmpData6);
                }
                UiMgr uiMgr14 = UiMgr.this;
                if (i2 == uiMgr14.getSettingRightIndex(uiMgr14.mContext, "_373_other_seting", "_373_amp_seting2")) {
                    UiMgr.this.send0x97CarControlInfo(82, i3);
                }
                UiMgr uiMgr15 = UiMgr.this;
                if (i2 == uiMgr15.getSettingRightIndex(uiMgr15.mContext, "_373_other_seting", "_373_amp_seting3")) {
                    UiMgr.this.send0x97CarControlInfo(82, i3 + 1);
                }
            }
        }
    };
    int AmpData0 = 0;
    int AmpData1 = 0;
    int AmpData2 = 0;
    int AmpData3 = 0;
    int AmpData4 = 0;
    int AmpData5 = 0;
    int AmpData6 = 0;
    int eachId = getEachId();
    int differentId = getCurrentCarId();

    public UiMgr(Context context) {
        this.mContext = context;
        AirPageUiSet airUiSet = getAirUiSet(this.mContext);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
        airUiSet.getFrontArea().setOnAirSeatClickListener(this.onAirSeatClickListener);
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.onAirWindSpeedUpDownClickListener);
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.onAirTemperatureUpDownClickListenerLeft, null, this.onAirTemperatureUpDownClickListenerRight});
        airUiSet.getFrontArea().setOnAirPageStatusListener(this.onAirPageStatusListener);
        airUiSet.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.mOnMinPlusClickListenerHeatFrontLeft, this.mOnMinPlusClickListenerHeatFrontRight, this.mOnMinPlusClickListenerColdFrontLeft, this.mOnMinPlusClickListenerColdFrontRight});
        airUiSet.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, this.mOnAirBtnClickListenerRearBottom});
        airUiSet.getRearArea().setSetWindSpeedViewOnClickListener(this.onRearAirWindSpeedUpDownClickListener);
        airUiSet.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{null, this.onRearAirTemperatureUpDownClickListener, null});
        airUiSet.getRearArea().setOnAirSeatClickListener(this.onRearAirSeatClickListener);
        getSettingUiSet(this.mContext).setOnSettingItemSelectListener(this.onSettingItemSelectListener);
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(this.mContext);
        amplifierPageUiSet.setOnAmplifierPositionListener(this.onAmplifierPositionListener);
        amplifierPageUiSet.setOnAmplifierSeekBarListener(this.onAmplifierSeekBarListener);
        amplifierPageUiSet.setOnAmplifierCreateAndDestroyListener(this.onAmplifierCreateAndDestroyListener);
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        initAmp(context);
    }

    private void initAmp(Context context) {
        new Thread(new Runnable() { // from class: com.hzbhd.canbus.car._373.UiMgr.1
            @Override // java.lang.Runnable
            public void run() {
                new AmpUtil().initAmpData(UiMgr.this.mContext);
            }
        }).start();
        new AmpUtil().intiAmpUi(context);
    }

    /* renamed from: com.hzbhd.canbus.car._373.UiMgr$23, reason: invalid class name */
    static /* synthetic */ class AnonymousClass23 {
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand;
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition;

        static {
            int[] iArr = new int[AmplifierActivity.AmplifierBand.values().length];
            $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand = iArr;
            try {
                iArr[AmplifierActivity.AmplifierBand.VOLUME.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.BASS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.MIDDLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.TREBLE.ordinal()] = 4;
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

    public void send0x81MakeConnection() {
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void send0x93AmpInfo() {
        CanbusMsgSender.sendMsg(new byte[]{22, -109, (byte) this.AmpData0, (byte) this.AmpData1, (byte) this.AmpData2, (byte) this.AmpData3, (byte) this.AmpData4, (byte) this.AmpData5, (byte) this.AmpData6});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void send0x95AirInfo(int i) throws InterruptedException {
        byte b = (byte) i;
        CanbusMsgSender.sendMsg(new byte[]{22, -107, b, 1});
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -107, b, 0});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void send0x96RearAirInfo(int i) throws InterruptedException {
        byte b = (byte) i;
        CanbusMsgSender.sendMsg(new byte[]{22, -106, b, 1});
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -106, b, 0});
    }

    public void requestDate(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -15, (byte) i, 0});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void send0x97CarControlInfo(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -105, (byte) i, (byte) i2});
    }

    public void send0x98TimeInfo(int i, int i2, int i3, int i4) {
        CanbusMsgSender.sendMsg(new byte[]{22, -104, (byte) i, (byte) i2, (byte) i3, (byte) i4});
    }

    public void send0x90MediaInfo(byte[] bArr) {
        CanbusMsgSender.sendMsg(bArr);
    }

    public void send0x99CompassInfo(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -103, (byte) i});
    }

    protected int getDrivingPageIndexes(Context context, String str) {
        List<DriverDataPageUiSet.Page> list = getPageUiSet(context).getDriverDataPageUiSet().getList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getHeadTitleSrn().equals(str)) {
                return i;
            }
        }
        return -1;
    }

    protected int getDrivingItemIndexes(Context context, String str) {
        List<DriverDataPageUiSet.Page> list = getPageUiSet(context).getDriverDataPageUiSet().getList();
        for (int i = 0; i < list.size(); i++) {
            Iterator<DriverDataPageUiSet.Page> it = list.iterator();
            while (it.hasNext()) {
                List<DriverDataPageUiSet.Page.Item> itemList = it.next().getItemList();
                for (int i2 = 0; i2 < itemList.size(); i2++) {
                    if (itemList.get(i2).getTitleSrn().equals(str)) {
                        return i2;
                    }
                }
            }
        }
        return -1;
    }

    protected int getSettingLeftIndexes(Context context, String str) {
        List<SettingPageUiSet.ListBean> list = getPageUiSet(context).getSettingPageUiSet().getList();
        Iterator<SettingPageUiSet.ListBean> it = list.iterator();
        for (int i = 0; i < list.size(); i++) {
            if (str.equals(it.next().getTitleSrn())) {
                return i;
            }
        }
        return -1;
    }

    protected int getSettingRightIndex(Context context, String str, String str2) {
        List<SettingPageUiSet.ListBean> list = getPageUiSet(context).getSettingPageUiSet().getList();
        Iterator<SettingPageUiSet.ListBean> it = list.iterator();
        for (int i = 0; i < list.size(); i++) {
            SettingPageUiSet.ListBean next = it.next();
            if (str.equals(next.getTitleSrn())) {
                List<SettingPageUiSet.ListBean.ItemListBean> itemList = next.getItemList();
                Iterator<SettingPageUiSet.ListBean.ItemListBean> it2 = itemList.iterator();
                for (int i2 = 0; i2 < itemList.size(); i2++) {
                    if (str2.equals(it2.next().getTitleSrn())) {
                        return i2;
                    }
                }
            }
        }
        return -1;
    }

    private MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }

    private int getDecimalFrom8Bit(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return Integer.parseInt(i + "" + i2 + "" + i3 + "" + i4 + "" + i5 + "" + i6 + "" + i7 + "" + i8, 2);
    }

    public boolean isLandscape() {
        return this.mContext.getResources().getConfiguration().orientation == 2;
    }

    public boolean isPortrait() {
        return this.mContext.getResources().getConfiguration().orientation == 1;
    }
}
