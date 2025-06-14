package com.hzbhd.canbus.car._337;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierCreateAndDestroyListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDevicePageStatusListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.constant.disc.MpegConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    public static int seatState;
    public static int seatStatePortrait;
    Context mContext;
    MsgMgr mMsgMgr;
    String PARK_PAGE_360_VISIBILITY_TAG = "PARK_PAGE_360_VISIBILITY_TAG";
    DecimalFormat towDecimal = new DecimalFormat("###0.00");
    DecimalFormat oneDecimal = new DecimalFormat("###0.0");
    DecimalFormat timeFormat = new DecimalFormat("00");
    OnAirPageStatusListener onAirPageStatusListener = new OnAirPageStatusListener() { // from class: com.hzbhd.canbus.car._337.UiMgr.1
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener
        public void onStatusChange(int i) {
            UiMgr.this.activeRequest(35, 0);
            UiMgr.this.activeRequest(52, 0);
        }
    };
    OnAirBtnClickListener onAirBtnClickListenerTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._337.UiMgr.2
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                if (UiMgr.this.isPortrait()) {
                    UiMgr.this.sendPortraitAirInfo(23);
                    return;
                } else {
                    UiMgr.this.sendAirInfo(17);
                    return;
                }
            }
            if (i != 1) {
                return;
            }
            if (UiMgr.this.isPortrait()) {
                UiMgr.this.sendPortraitAirInfo(25);
            } else {
                UiMgr.this.sendAirInfo(19);
            }
        }
    };
    OnAirBtnClickListener onAirBtnClickListenerLeft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._337.UiMgr.3
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0 && UiMgr.this.isPortrait()) {
                UiMgr.this.sendPortraitAirInfo(19);
            }
        }
    };
    OnAirBtnClickListener onAirBtnClickListenerRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._337.UiMgr.4
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0 && UiMgr.this.isPortrait()) {
                UiMgr.this.sendPortraitAirInfo(20);
            }
        }
    };
    OnAirBtnClickListener onAirBtnClickListenerBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._337.UiMgr.5
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 1) {
                if (UiMgr.this.isPortrait()) {
                    UiMgr.this.sendPortraitAirInfo(1);
                    return;
                } else {
                    UiMgr.this.sendAirInfo(16);
                    return;
                }
            }
            if (i != 2) {
                return;
            }
            if (UiMgr.this.isPortrait()) {
                UiMgr.this.sendPortraitAirInfo(21);
            } else {
                UiMgr.this.sendAirInfo(34);
            }
        }
    };
    OnAirSeatClickListener onAirSeatClickListener = new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._337.UiMgr.6
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onLeftSeatClick() {
            if (UiMgr.this.isPortrait()) {
                if (UiMgr.seatStatePortrait == 0) {
                    UiMgr.this.sendPortraitAirInfo(7);
                    UiMgr.seatStatePortrait = 1;
                    return;
                }
                if (UiMgr.seatStatePortrait == 1) {
                    UiMgr.this.sendPortraitAirInfo(8);
                    UiMgr.seatStatePortrait = 2;
                    return;
                } else if (UiMgr.seatStatePortrait == 2) {
                    UiMgr.this.sendPortraitAirInfo(32);
                    UiMgr.seatStatePortrait = 3;
                    return;
                } else {
                    if (UiMgr.seatStatePortrait == 3) {
                        UiMgr.this.sendPortraitAirInfo(33);
                        UiMgr.seatStatePortrait = 4;
                        return;
                    }
                    return;
                }
            }
            if (UiMgr.seatState == 0) {
                UiMgr.this.sendAirInfo(21);
                UiMgr.seatState = 1;
                return;
            }
            if (UiMgr.seatState == 1) {
                UiMgr.this.sendAirInfo(24);
                UiMgr.seatState = 2;
                return;
            }
            if (UiMgr.seatState == 2) {
                UiMgr.this.sendAirInfo(25);
                UiMgr.seatState = 3;
            } else if (UiMgr.seatState == 3) {
                UiMgr.this.sendAirInfo(26);
                UiMgr.seatState = 4;
            } else if (UiMgr.seatState == 4) {
                UiMgr.this.sendAirInfo(27);
                UiMgr.seatState = 0;
            }
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onRightSeatClick() {
            if (UiMgr.seatState == 0) {
                UiMgr.this.sendAirInfo(21);
                UiMgr.seatState = 1;
                return;
            }
            if (UiMgr.seatState == 1) {
                UiMgr.this.sendAirInfo(24);
                UiMgr.seatState = 2;
                return;
            }
            if (UiMgr.seatState == 2) {
                UiMgr.this.sendAirInfo(25);
                UiMgr.seatState = 3;
            } else if (UiMgr.seatState == 3) {
                UiMgr.this.sendAirInfo(26);
                UiMgr.seatState = 4;
            } else if (UiMgr.seatState == 4) {
                UiMgr.this.sendAirInfo(27);
                UiMgr.seatState = 0;
            }
        }
    };
    OnAirWindSpeedUpDownClickListener OnAirWindSpeedUpDownClickListener = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._337.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            if (UiMgr.this.isPortrait()) {
                UiMgr.this.sendPortraitAirInfo(9);
            } else {
                UiMgr.this.sendAirInfo(29);
            }
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            if (UiMgr.this.isPortrait()) {
                UiMgr.this.sendPortraitAirInfo(10);
            } else {
                UiMgr.this.sendAirInfo(28);
            }
        }
    };
    OnAirTemperatureUpDownClickListener onAirTempLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._337.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            if (UiMgr.this.isPortrait()) {
                UiMgr.this.sendPortraitAirInfo(3);
            } else {
                UiMgr.this.sendAirInfo(30);
            }
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            if (UiMgr.this.isPortrait()) {
                UiMgr.this.sendPortraitAirInfo(2);
            } else {
                UiMgr.this.sendAirInfo(31);
            }
        }
    };
    OnAirTemperatureUpDownClickListener onAirTempRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._337.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            if (UiMgr.this.isPortrait()) {
                UiMgr.this.sendPortraitAirInfo(5);
            } else {
                UiMgr.this.sendAirInfo(32);
            }
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            if (UiMgr.this.isPortrait()) {
                UiMgr.this.sendPortraitAirInfo(4);
            } else {
                UiMgr.this.sendAirInfo(33);
            }
        }
    };
    OnAirBtnClickListener onAirBtnClickListenerRearBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._337.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirInfo(130);
            } else {
                if (i != 1) {
                    return;
                }
                UiMgr.this.sendAirInfo(129);
            }
        }
    };
    OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerRarCenter = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._337.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirInfo(133);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirInfo(HotKeyConstant.K_SLEEP);
        }
    };
    OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListenerRear = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._337.UiMgr.12
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            UiMgr.this.sendAirInfo(132);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            UiMgr.this.sendAirInfo(131);
        }
    };
    private int rearSeatTag = 0;
    OnAirSeatClickListener onAirSeatClickListenerRear = new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._337.UiMgr.13
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onLeftSeatClick() {
            if (UiMgr.this.rearSeatTag != 0) {
                if (UiMgr.this.rearSeatTag != 1) {
                    if (UiMgr.this.rearSeatTag == 2) {
                        UiMgr.this.sendAirInfo(137);
                        UiMgr.this.rearSeatTag = 0;
                        return;
                    }
                    return;
                }
                UiMgr.this.sendAirInfo(136);
                UiMgr.this.rearSeatTag = 2;
                return;
            }
            UiMgr.this.sendAirInfo(135);
            UiMgr.this.rearSeatTag = 1;
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onRightSeatClick() {
            if (UiMgr.this.rearSeatTag != 0) {
                if (UiMgr.this.rearSeatTag != 1) {
                    if (UiMgr.this.rearSeatTag == 2) {
                        UiMgr.this.sendAirInfo(137);
                        UiMgr.this.rearSeatTag = 0;
                        return;
                    }
                    return;
                }
                UiMgr.this.sendAirInfo(136);
                UiMgr.this.rearSeatTag = 2;
                return;
            }
            UiMgr.this.sendAirInfo(135);
            UiMgr.this.rearSeatTag = 1;
        }
    };
    OnSettingItemSelectListener onSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._337.UiMgr.14
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_337_car_setting")) {
                UiMgr uiMgr2 = UiMgr.this;
                if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "_337_car_setting", "_337_setting_0")) {
                    UiMgr.this.send0x83CarSettingInfo(4, i3 + 1, 0);
                    return;
                }
                UiMgr uiMgr3 = UiMgr.this;
                if (i2 == uiMgr3.getSettingRightIndex(uiMgr3.mContext, "_337_car_setting", "_337_setting_1")) {
                    UiMgr.this.send0x83CarSettingInfo(5, i3 + 1, 0);
                    return;
                }
                UiMgr uiMgr4 = UiMgr.this;
                if (i2 == uiMgr4.getSettingRightIndex(uiMgr4.mContext, "_337_car_setting", "_337_setting_2")) {
                    UiMgr.this.send0x83CarSettingInfo(6, i3 + 1, 0);
                    return;
                }
                UiMgr uiMgr5 = UiMgr.this;
                if (i2 == uiMgr5.getSettingRightIndex(uiMgr5.mContext, "_337_car_setting", "_337_setting_3")) {
                    UiMgr.this.send0x83CarSettingInfo(6, i3 + 4, 0);
                    return;
                }
                UiMgr uiMgr6 = UiMgr.this;
                if (i2 == uiMgr6.getSettingRightIndex(uiMgr6.mContext, "_337_car_setting", "_337_setting_4")) {
                    UiMgr.this.send0x83CarSettingInfo(7, i3 + 1, 0);
                    return;
                }
                UiMgr uiMgr7 = UiMgr.this;
                if (i2 == uiMgr7.getSettingRightIndex(uiMgr7.mContext, "_337_car_setting", "_337_setting_5")) {
                    UiMgr.this.send0x83CarSettingInfo(8, i3 + 1, 0);
                    return;
                }
                UiMgr uiMgr8 = UiMgr.this;
                if (i2 == uiMgr8.getSettingRightIndex(uiMgr8.mContext, "_337_car_setting", "_337_setting_6")) {
                    UiMgr.this.send0x83CarSettingInfo(9, i3, 0);
                    return;
                }
                UiMgr uiMgr9 = UiMgr.this;
                if (i2 == uiMgr9.getSettingRightIndex(uiMgr9.mContext, "_337_car_setting", "_337_setting_7")) {
                    UiMgr.this.send0x83CarSettingInfo(12, i3, 0);
                    return;
                }
                UiMgr uiMgr10 = UiMgr.this;
                if (i2 == uiMgr10.getSettingRightIndex(uiMgr10.mContext, "_337_car_setting", "_337_setting_8")) {
                    UiMgr.this.send0x83CarSettingInfo(13, i3, 0);
                    return;
                }
                UiMgr uiMgr11 = UiMgr.this;
                if (i2 == uiMgr11.getSettingRightIndex(uiMgr11.mContext, "_337_car_setting", "_337_setting_9")) {
                    UiMgr.this.send0x83CarSettingInfo(15, i3, 0);
                    return;
                }
                UiMgr uiMgr12 = UiMgr.this;
                if (i2 == uiMgr12.getSettingRightIndex(uiMgr12.mContext, "_337_car_setting", "_337_setting_10")) {
                    UiMgr.this.send0x83CarSettingInfo(14, i3, 0);
                    return;
                }
                UiMgr uiMgr13 = UiMgr.this;
                if (i2 == uiMgr13.getSettingRightIndex(uiMgr13.mContext, "_337_car_setting", "_337_setting_11")) {
                    UiMgr.this.send0x83CarSettingInfo(16, i3, 0);
                    return;
                }
                UiMgr uiMgr14 = UiMgr.this;
                if (i2 == uiMgr14.getSettingRightIndex(uiMgr14.mContext, "_337_car_setting", "_337_setting_12")) {
                    UiMgr.this.send0x83CarSettingInfo(17, i3, 0);
                    return;
                }
                UiMgr uiMgr15 = UiMgr.this;
                if (i2 == uiMgr15.getSettingRightIndex(uiMgr15.mContext, "_337_car_setting", "_337_setting_13")) {
                    UiMgr.this.send0x83CarSettingInfo(18, i3, 0);
                    return;
                }
                UiMgr uiMgr16 = UiMgr.this;
                if (i2 == uiMgr16.getSettingRightIndex(uiMgr16.mContext, "_337_car_setting", "_337_setting_14")) {
                    UiMgr.this.send0x83CarSettingInfo(19, i3, 0);
                    return;
                }
                UiMgr uiMgr17 = UiMgr.this;
                if (i2 == uiMgr17.getSettingRightIndex(uiMgr17.mContext, "_337_car_setting", "_337_setting_15")) {
                    UiMgr.this.send0x83CarSettingInfo(23, i3, 0);
                    return;
                }
                UiMgr uiMgr18 = UiMgr.this;
                if (i2 == uiMgr18.getSettingRightIndex(uiMgr18.mContext, "_337_car_setting", "_337_setting_16")) {
                    UiMgr.this.send0x83CarSettingInfo(24, i3, 0);
                    return;
                }
                UiMgr uiMgr19 = UiMgr.this;
                if (i2 == uiMgr19.getSettingRightIndex(uiMgr19.mContext, "_337_car_setting", "_337_setting_17")) {
                    UiMgr.this.send0x83CarSettingInfo(11, i3, 0);
                    return;
                }
                UiMgr uiMgr20 = UiMgr.this;
                if (i2 == uiMgr20.getSettingRightIndex(uiMgr20.mContext, "_337_car_setting", "_337_setting_18")) {
                    UiMgr.this.send0x83CarSettingInfo(25, i3, 0);
                    return;
                }
                UiMgr uiMgr21 = UiMgr.this;
                if (i2 == uiMgr21.getSettingRightIndex(uiMgr21.mContext, "_337_car_setting", "_337_setting_19")) {
                    UiMgr.this.send0x83CarSettingInfo(27, i3, 0);
                    return;
                }
                UiMgr uiMgr22 = UiMgr.this;
                if (i2 == uiMgr22.getSettingRightIndex(uiMgr22.mContext, "_337_car_setting", "_337_setting_20")) {
                    UiMgr.this.send0x83CarSettingInfo(28, i3, 0);
                    return;
                }
                UiMgr uiMgr23 = UiMgr.this;
                if (i2 == uiMgr23.getSettingRightIndex(uiMgr23.mContext, "_337_car_setting", "_337_setting_21")) {
                    UiMgr.this.send0x83CarSettingInfo(48, i3, 0);
                    return;
                }
                UiMgr uiMgr24 = UiMgr.this;
                if (i2 == uiMgr24.getSettingRightIndex(uiMgr24.mContext, "_337_car_setting", "_337_setting_22")) {
                    UiMgr.this.send0x83CarSettingInfo(49, i3, 0);
                    return;
                }
                UiMgr uiMgr25 = UiMgr.this;
                if (i2 == uiMgr25.getSettingRightIndex(uiMgr25.mContext, "_337_car_setting", "_337_setting_23")) {
                    UiMgr.this.send0x83CarSettingInfo(50, i3, 0);
                    return;
                }
                UiMgr uiMgr26 = UiMgr.this;
                if (i2 == uiMgr26.getSettingRightIndex(uiMgr26.mContext, "_337_car_setting", "_337_setting_24")) {
                    UiMgr.this.send0x83CarSettingInfo(52, i3, 0);
                    return;
                }
                UiMgr uiMgr27 = UiMgr.this;
                if (i2 == uiMgr27.getSettingRightIndex(uiMgr27.mContext, "_337_car_setting", "_337_setting_25")) {
                    UiMgr.this.send0x83CarSettingInfo(53, i3, 0);
                    return;
                }
                UiMgr uiMgr28 = UiMgr.this;
                if (i2 == uiMgr28.getSettingRightIndex(uiMgr28.mContext, "_337_car_setting", "_337_setting_26")) {
                    UiMgr.this.send0x83CarSettingInfo(54, i3, 0);
                    return;
                }
                UiMgr uiMgr29 = UiMgr.this;
                if (i2 == uiMgr29.getSettingRightIndex(uiMgr29.mContext, "_337_car_setting", "_337_setting_26_on_27")) {
                    UiMgr.this.send0x83CarSettingInfo(55, i3, 0);
                    return;
                }
                UiMgr uiMgr30 = UiMgr.this;
                if (i2 == uiMgr30.getSettingRightIndex(uiMgr30.mContext, "_337_car_setting", "_337_setting_27")) {
                    UiMgr.this.send0x83CarSettingInfo(57, i3, 0);
                    return;
                }
                UiMgr uiMgr31 = UiMgr.this;
                if (i2 == uiMgr31.getSettingRightIndex(uiMgr31.mContext, "_337_car_setting", "_337_setting_28")) {
                    UiMgr.this.send0x83CarSettingInfo(58, i3, 0);
                    return;
                }
                UiMgr uiMgr32 = UiMgr.this;
                if (i2 == uiMgr32.getSettingRightIndex(uiMgr32.mContext, "_337_car_setting", "_337_setting_29")) {
                    UiMgr.this.send0x83CarSettingInfo(59, i3, 0);
                    return;
                }
                UiMgr uiMgr33 = UiMgr.this;
                if (i2 == uiMgr33.getSettingRightIndex(uiMgr33.mContext, "_337_car_setting", "_337_setting_30")) {
                    UiMgr.this.send0x83CarSettingInfo(60, i3, 0);
                    return;
                }
                UiMgr uiMgr34 = UiMgr.this;
                if (i2 == uiMgr34.getSettingRightIndex(uiMgr34.mContext, "_337_car_setting", "_337_setting_31")) {
                    UiMgr.this.send0x83CarSettingInfo(61, i3, 0);
                    return;
                }
                UiMgr uiMgr35 = UiMgr.this;
                if (i2 == uiMgr35.getSettingRightIndex(uiMgr35.mContext, "_337_car_setting", "_337_setting_32")) {
                    UiMgr.this.send0x83CarSettingInfo(51, i3, 0);
                    return;
                }
                UiMgr uiMgr36 = UiMgr.this;
                if (i2 == uiMgr36.getSettingRightIndex(uiMgr36.mContext, "_337_car_setting", "_337_setting_33")) {
                    UiMgr.this.send0x83CarSettingInfo(62, i3, 0);
                    return;
                }
                UiMgr uiMgr37 = UiMgr.this;
                if (i2 == uiMgr37.getSettingRightIndex(uiMgr37.mContext, "_337_car_setting", "_337_setting_34")) {
                    UiMgr.this.send0x83CarSettingInfo(68, i3, 0);
                    return;
                }
                UiMgr uiMgr38 = UiMgr.this;
                if (i2 == uiMgr38.getSettingRightIndex(uiMgr38.mContext, "_337_car_setting", "_337_setting_35")) {
                    UiMgr.this.send0x83CarSettingInfo(56, i3, 0);
                    return;
                }
                UiMgr uiMgr39 = UiMgr.this;
                if (i2 == uiMgr39.getSettingRightIndex(uiMgr39.mContext, "_337_car_setting", "_337_setting_37")) {
                    UiMgr.this.send0x83CarSettingInfo(65, i3, 0);
                    return;
                }
                UiMgr uiMgr40 = UiMgr.this;
                if (i2 == uiMgr40.getSettingRightIndex(uiMgr40.mContext, "_337_car_setting", "_337_setting_38")) {
                    UiMgr.this.send0x83CarSettingInfo(63, i3, 0);
                    return;
                }
                UiMgr uiMgr41 = UiMgr.this;
                if (i2 == uiMgr41.getSettingRightIndex(uiMgr41.mContext, "_337_car_setting", "_337_setting_39")) {
                    UiMgr.this.send0x83CarSettingInfo(69, i3, 0);
                    return;
                }
                UiMgr uiMgr42 = UiMgr.this;
                if (i2 == uiMgr42.getSettingRightIndex(uiMgr42.mContext, "_337_car_setting", "_337_setting_40")) {
                    UiMgr.this.send0x83CarSettingInfo(70, i3, 0);
                    return;
                }
                UiMgr uiMgr43 = UiMgr.this;
                if (i2 == uiMgr43.getSettingRightIndex(uiMgr43.mContext, "_337_car_setting", "_337_setting_41")) {
                    UiMgr.this.send0x83CarSettingInfo(66, i3, 0);
                    return;
                }
                UiMgr uiMgr44 = UiMgr.this;
                if (i2 == uiMgr44.getSettingRightIndex(uiMgr44.mContext, "_337_car_setting", "_337_setting_42")) {
                    UiMgr.this.send0x83CarSettingInfo(67, i3, 0);
                    return;
                }
                UiMgr uiMgr45 = UiMgr.this;
                if (i2 == uiMgr45.getSettingRightIndex(uiMgr45.mContext, "_337_car_setting", "_337_setting_43")) {
                    UiMgr.this.send0x83CarSettingInfo(72, i3, 0);
                    return;
                }
                UiMgr uiMgr46 = UiMgr.this;
                if (i2 == uiMgr46.getSettingRightIndex(uiMgr46.mContext, "_337_car_setting", "_337_setting_44")) {
                    UiMgr.this.send0x83CarSettingInfo(71, i3, 0);
                    return;
                }
                UiMgr uiMgr47 = UiMgr.this;
                if (i2 == uiMgr47.getSettingRightIndex(uiMgr47.mContext, "_337_car_setting", "_337_setting_45")) {
                    UiMgr.this.send0x83CarSettingInfo(73, i3, 0);
                    return;
                }
                UiMgr uiMgr48 = UiMgr.this;
                if (i2 == uiMgr48.getSettingRightIndex(uiMgr48.mContext, "_337_car_setting", "_337_setting_46")) {
                    UiMgr.this.send0x83CarSettingInfo(74, i3, 0);
                    return;
                }
                UiMgr uiMgr49 = UiMgr.this;
                if (i2 == uiMgr49.getSettingRightIndex(uiMgr49.mContext, "_337_car_setting", "_337_setting_47")) {
                    UiMgr.this.send0x83CarSettingInfo(76, i3, 0);
                    return;
                }
                UiMgr uiMgr50 = UiMgr.this;
                if (i2 == uiMgr50.getSettingRightIndex(uiMgr50.mContext, "_337_car_setting", "_337_setting_48")) {
                    UiMgr.this.send0x83CarSettingInfo(77, i3, 0);
                    return;
                }
                UiMgr uiMgr51 = UiMgr.this;
                if (i2 == uiMgr51.getSettingRightIndex(uiMgr51.mContext, "_337_car_setting", "_337_setting_49")) {
                    UiMgr.this.send0x83CarSettingInfo(78, i3, 0);
                    return;
                }
                UiMgr uiMgr52 = UiMgr.this;
                if (i2 == uiMgr52.getSettingRightIndex(uiMgr52.mContext, "_337_car_setting", "_337_setting_50")) {
                    UiMgr.this.send0x83CarSettingInfo(79, i3, 0);
                    return;
                }
                UiMgr uiMgr53 = UiMgr.this;
                if (i2 == uiMgr53.getSettingRightIndex(uiMgr53.mContext, "_337_car_setting", "_337_setting_51")) {
                    UiMgr.this.send0x83CarSettingInfo(75, i3, 0);
                    return;
                }
                UiMgr uiMgr54 = UiMgr.this;
                if (i2 == uiMgr54.getSettingRightIndex(uiMgr54.mContext, "_337_car_setting", "_337_setting_52")) {
                    UiMgr uiMgr55 = UiMgr.this;
                    uiMgr55.getMsgMgr(uiMgr55.mContext).updateSettings(UiMgr.this.mContext, i, i2, UiMgr.this.PARK_PAGE_360_VISIBILITY_TAG, i3);
                    UiMgr uiMgr56 = UiMgr.this;
                    uiMgr56.getMsgMgr(uiMgr56.mContext).showDialogAndRestartApp(UiMgr.this.mContext.getString(R.string._337_setting_52_3));
                    return;
                }
                UiMgr uiMgr57 = UiMgr.this;
                if (i2 == uiMgr57.getSettingRightIndex(uiMgr57.mContext, "_337_car_setting", "_337_park")) {
                    UiMgr.this.send0x83CarSettingInfo(2, i3 + 1, 0);
                    UiMgr uiMgr58 = UiMgr.this;
                    uiMgr58.getMsgMgr(uiMgr58.mContext).updateSettings(i, i2, i3);
                }
                UiMgr uiMgr59 = UiMgr.this;
                if (i2 == uiMgr59.getSettingRightIndex(uiMgr59.mContext, "_337_car_setting", "_337_rear_camera")) {
                    UiMgr.this.send0x83CarSettingInfo(1, i3, 0);
                    UiMgr uiMgr60 = UiMgr.this;
                    uiMgr60.getMsgMgr(uiMgr60.mContext).updateSettings(i, i2, i3);
                }
            }
            UiMgr uiMgr61 = UiMgr.this;
            if (i == uiMgr61.getSettingLeftIndexes(uiMgr61.mContext, "_337_amplifier_info")) {
                UiMgr uiMgr62 = UiMgr.this;
                if (i2 == uiMgr62.getSettingRightIndex(uiMgr62.mContext, "_337_amplifier_info", "_337_amplifier_info1")) {
                    if (i3 != 0) {
                        UiMgr.this.sendAmplifierInfo(9, i3 - 1);
                        return;
                    } else {
                        UiMgr.this.sendAmplifierInfo(9, 5);
                        return;
                    }
                }
                UiMgr uiMgr63 = UiMgr.this;
                if (i2 == uiMgr63.getSettingRightIndex(uiMgr63.mContext, "_337_amplifier_info", "_337_amplifier_info2")) {
                    UiMgr.this.sendAmplifierInfo(7, i3 + 1);
                    return;
                }
                UiMgr uiMgr64 = UiMgr.this;
                if (i2 == uiMgr64.getSettingRightIndex(uiMgr64.mContext, "_337_amplifier_info", "_337_amplifier_info3")) {
                    UiMgr.this.sendAmplifierInfo(10, i3);
                    return;
                }
                UiMgr uiMgr65 = UiMgr.this;
                if (i2 == uiMgr65.getSettingRightIndex(uiMgr65.mContext, "_337_amplifier_info", "_337_amplifier_info4")) {
                    UiMgr.this.sendAmplifierInfo(8, i3);
                }
            }
        }
    };
    OnSettingItemSeekbarSelectListener onSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._337.UiMgr.15
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
        public void onClickItem(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_337_car_setting")) {
                UiMgr uiMgr2 = UiMgr.this;
                if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "_337_car_setting", "_337_setting_36")) {
                    UiMgr.this.send0x83CarSettingInfo(64, i3 + 10, 0);
                }
            }
        }
    };
    OnSettingPageStatusListener onSettingPageStatusListener = new OnSettingPageStatusListener() { // from class: com.hzbhd.canbus.car._337.UiMgr.16
        @Override // com.hzbhd.canbus.interfaces.OnSettingPageStatusListener
        public void onStatusChange(int i) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_337_car_setting")) {
                UiMgr.this.activeRequest(49, 0);
            }
            UiMgr uiMgr2 = UiMgr.this;
            if (i == uiMgr2.getSettingLeftIndexes(uiMgr2.mContext, "_337_air")) {
                UiMgr.this.activeRequest(53, 0);
            }
            UiMgr uiMgr3 = UiMgr.this;
            if (i == uiMgr3.getSettingLeftIndexes(uiMgr3.mContext, "_337_car_config")) {
                UiMgr.this.activeRequest(63, 0);
            }
        }
    };
    OnSettingItemClickListener onSettingItemClickListener = new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._337.UiMgr.17
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
        public void onClickItem(int i, int i2) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_337_air")) {
                UiMgr uiMgr2 = UiMgr.this;
                if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "_337_air", "_337_air_2")) {
                    UiMgr.this.sendAirInfo(36);
                    return;
                }
                UiMgr uiMgr3 = UiMgr.this;
                if (i2 == uiMgr3.getSettingRightIndex(uiMgr3.mContext, "_337_air", "_337_air_3")) {
                    UiMgr.this.sendAirInfo(37);
                    return;
                }
                UiMgr uiMgr4 = UiMgr.this;
                if (i2 == uiMgr4.getSettingRightIndex(uiMgr4.mContext, "_337_air", "_337_air_4")) {
                    UiMgr.this.sendAirInfo(38);
                    return;
                }
                UiMgr uiMgr5 = UiMgr.this;
                if (i2 == uiMgr5.getSettingRightIndex(uiMgr5.mContext, "_337_air", "_337_air_5")) {
                    UiMgr.this.sendAirInfo(39);
                    return;
                }
                UiMgr uiMgr6 = UiMgr.this;
                if (i2 == uiMgr6.getSettingRightIndex(uiMgr6.mContext, "_337_air", "_337_air_6")) {
                    UiMgr.this.sendAirInfo(40);
                    return;
                }
                UiMgr uiMgr7 = UiMgr.this;
                if (i2 == uiMgr7.getSettingRightIndex(uiMgr7.mContext, "_337_air", "_337_air_7")) {
                    UiMgr.this.sendAirInfo(41);
                    return;
                }
                UiMgr uiMgr8 = UiMgr.this;
                if (i2 == uiMgr8.getSettingRightIndex(uiMgr8.mContext, "_337_air", "_337_air_8")) {
                    UiMgr.this.sendAirInfo(42);
                    return;
                }
                UiMgr uiMgr9 = UiMgr.this;
                if (i2 == uiMgr9.getSettingRightIndex(uiMgr9.mContext, "_337_air", "_337_air_9")) {
                    UiMgr.this.sendAirInfo(43);
                }
            }
        }
    };
    OnAmplifierCreateAndDestroyListener onAmplifierCreateAndDestroyListener = new OnAmplifierCreateAndDestroyListener() { // from class: com.hzbhd.canbus.car._337.UiMgr.18
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierCreateAndDestroyListener
        public void destroy() {
        }

        @Override // com.hzbhd.canbus.interfaces.OnAmplifierCreateAndDestroyListener
        public void create() {
            UiMgr.this.activeRequest(55, 0);
        }
    };
    OnAmplifierPositionListener onAmplifierPositionListener = new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._337.UiMgr.19
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
        public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
            int i2 = AnonymousClass23.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[amplifierPosition.ordinal()];
            if (i2 == 1) {
                MyLog.e("FRONT_REAR", i + "");
                UiMgr.this.sendAmplifierInfo(6, (-i) + 10);
            } else {
                if (i2 != 2) {
                    return;
                }
                MyLog.e("LEFT_RIGHT", i + "");
                UiMgr.this.sendAmplifierInfo(5, i + 10);
            }
        }
    };
    OnAmplifierSeekBarListener onAmplifierSeekBarListener = new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._337.UiMgr.20
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
        public void progress(AmplifierActivity.AmplifierBand amplifierBand, int i) {
            String str = "0x" + i;
            int i2 = AnonymousClass23.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[amplifierBand.ordinal()];
            if (i2 == 1) {
                UiMgr.this.sendAmplifierInfo(1, i);
                return;
            }
            if (i2 == 2) {
                UiMgr.this.sendAmplifierInfo(2, Integer.parseInt(str.replaceAll("^0[x|X]", ""), 16));
            } else if (i2 == 3) {
                UiMgr.this.sendAmplifierInfo(3, Integer.parseInt(str.replaceAll("^0[x|X]", ""), 16));
            } else {
                if (i2 != 4) {
                    return;
                }
                UiMgr.this.sendAmplifierInfo(4, Integer.parseInt(str.replaceAll("^0[x|X]", ""), 16));
            }
        }
    };
    OnOriginalCarDevicePageStatusListener onOriginalCarDevicePageStatusListener = new OnOriginalCarDevicePageStatusListener() { // from class: com.hzbhd.canbus.car._337.UiMgr.21
        @Override // com.hzbhd.canbus.interfaces.OnOriginalCarDevicePageStatusListener
        public void onStatusChange(int i) {
            UiMgr.this.activeRequest(56, 0);
        }
    };
    private int cycleModelTag = 0;
    private int playTag = 0;
    OnOriginalBottomBtnClickListener onOriginalBottomBtnClickListener = new OnOriginalBottomBtnClickListener() { // from class: com.hzbhd.canbus.car._337.UiMgr.22
        @Override // com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener
        public void onClickBottomBtnItem(int i) {
            switch (i) {
                case 0:
                    UiMgr.this.sendOriginalCarInfo(2, 0, 0);
                    break;
                case 1:
                    UiMgr.this.sendOriginalCarInfo(3, 0, 0);
                    break;
                case 2:
                    if (UiMgr.this.cycleModelTag == 0) {
                        UiMgr.this.sendOriginalCarInfo(8, 0, 0);
                        UiMgr.this.cycleModelTag = 1;
                        break;
                    } else {
                        UiMgr.this.sendOriginalCarInfo(17, 0, 0);
                        UiMgr.this.cycleModelTag = 0;
                        break;
                    }
                case 3:
                    if (UiMgr.this.playTag == 0) {
                        UiMgr.this.sendOriginalCarInfo(19, 0, 0);
                        UiMgr.this.playTag = 1;
                        break;
                    } else {
                        UiMgr.this.sendOriginalCarInfo(20, 0, 0);
                        UiMgr.this.playTag = 0;
                        break;
                    }
                case 4:
                    UiMgr.this.sendOriginalCarInfo(0, 0, 0);
                    break;
                case 5:
                    UiMgr.this.sendOriginalCarInfo(4, 0, 0);
                    break;
                case 6:
                    UiMgr.this.sendOriginalCarInfo(1, 0, 0);
                    break;
            }
        }
    };
    int eachId = getEachId();
    int differentId = getCurrentCarId();

    public UiMgr(Context context) {
        this.mContext = context;
        this.PARK_PAGE_360_VISIBILITY_TAG += this.differentId + "" + this.eachId;
        AirPageUiSet airUiSet = getAirUiSet(this.mContext);
        airUiSet.getFrontArea().setOnAirPageStatusListener(this.onAirPageStatusListener);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.onAirBtnClickListenerTop, this.onAirBtnClickListenerLeft, this.onAirBtnClickListenerRight, this.onAirBtnClickListenerBottom});
        airUiSet.getFrontArea().setOnAirSeatClickListener(this.onAirSeatClickListener);
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.OnAirWindSpeedUpDownClickListener);
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.onAirTempLeft, null, this.onAirTempRight});
        airUiSet.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, this.onAirBtnClickListenerRearBottom});
        airUiSet.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{null, this.onAirTemperatureUpDownClickListenerRarCenter, null});
        airUiSet.getRearArea().setSetWindSpeedViewOnClickListener(this.onAirWindSpeedUpDownClickListenerRear);
        airUiSet.getRearArea().setOnAirSeatClickListener(this.onAirSeatClickListenerRear);
        SettingPageUiSet settingUiSet = getSettingUiSet(this.mContext);
        settingUiSet.setOnSettingItemSelectListener(this.onSettingItemSelectListener);
        settingUiSet.setOnSettingItemSeekbarSelectListener(this.onSettingItemSeekbarSelectListener);
        settingUiSet.setOnSettingPageStatusListener(this.onSettingPageStatusListener);
        settingUiSet.setOnSettingItemClickListener(this.onSettingItemClickListener);
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(this.mContext);
        amplifierPageUiSet.setOnAmplifierCreateAndDestroyListener(this.onAmplifierCreateAndDestroyListener);
        amplifierPageUiSet.setOnAmplifierPositionListener(this.onAmplifierPositionListener);
        amplifierPageUiSet.setOnAmplifierSeekBarListener(this.onAmplifierSeekBarListener);
        OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(this.mContext);
        originalCarDevicePageUiSet.setOnOriginalCarDevicePageStatusListener(this.onOriginalCarDevicePageStatusListener);
        originalCarDevicePageUiSet.setOnOriginalBottomBtnClickListeners(this.onOriginalBottomBtnClickListener);
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        initUi();
        intiData();
    }

    private void intiData() {
        selectCarModel();
    }

    private void initUi() {
        int iIntiCarSeetingUi = intiCarSeetingUi();
        int iInitSeatSettingUi = initSeatSettingUi();
        initAirUI();
        initDriveDataUi();
        int iInitAmplifierUi = initAmplifierUi();
        initOriginalCarUi();
        int iInitCArConfigInfoUi = initCArConfigInfoUi();
        initParkPageUi();
        if (iIntiCarSeetingUi == 0 && iInitSeatSettingUi == 0 && iInitAmplifierUi == 0 && iInitCArConfigInfoUi == 0) {
            removeMainPrjBtnByName(this.mContext, MainAction.SETTING);
        }
    }

    private void initParkPageUi() {
        if (SharePreUtil.getIntValue(this.mContext, this.PARK_PAGE_360_VISIBILITY_TAG, 0) == 0) {
            if (!getMsgMgr(this.mContext).is404("_337_car_setting", "_337_setting_52")) {
                MsgMgr msgMgr = getMsgMgr(this.mContext);
                Context context = this.mContext;
                msgMgr.updateSettings(context, getSettingLeftIndexes(context, "_337_car_setting"), getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_52"), this.PARK_PAGE_360_VISIBILITY_TAG, 0);
            }
            getParkPageUiSet(this.mContext).setShowPanoramic(true);
            return;
        }
        if (!getMsgMgr(this.mContext).is404("_337_car_setting", "_337_setting_52")) {
            MsgMgr msgMgr2 = getMsgMgr(this.mContext);
            Context context2 = this.mContext;
            msgMgr2.updateSettings(context2, getSettingLeftIndexes(context2, "_337_car_setting"), getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_52"), this.PARK_PAGE_360_VISIBILITY_TAG, 1);
        }
        getParkPageUiSet(this.mContext).setShowPanoramic(false);
    }

    private int initCArConfigInfoUi() {
        if (isH7()) {
            return 1;
        }
        removeSettingLeftItemByNameList(this.mContext, new String[]{"_337_car_config"});
        return 0;
    }

    private void initOriginalCarUi() {
        if (isH7()) {
            return;
        }
        removeMainPrjBtnByName(this.mContext, MainAction.ORIGINAL_CAR_DEVICE);
    }

    private int initAmplifierUi() {
        if (isH9AndH6Couple() || isH7()) {
            return 1;
        }
        removeMainPrjBtnByName(this.mContext, MainAction.AMPLIFIER);
        removeSettingLeftItemByNameList(this.mContext, new String[]{"_337_amplifier_info"});
        return 0;
    }

    private void initDriveDataUi() {
        boolean z;
        boolean z2 = true;
        if (isCarModel7()) {
            z = true;
        } else {
            removeDriveDataPagesByHeadTitles(this.mContext, "_337_drive_car_info");
            z = false;
        }
        if (isH9()) {
            z = true;
        } else {
            removeDriveDataPagesByHeadTitles(this.mContext, "_337_drive_car_info_tow");
        }
        if (!isCarModel6() && !isH7()) {
            removeDriveDataPagesByHeadTitles(this.mContext, "_337_panorama_info");
            z2 = z;
        }
        if (z2) {
            return;
        }
        removeMainPrjBtnByName(this.mContext, MainAction.DRIVE_DATA);
    }

    private void initAirUI() {
        AirPageUiSet airUiSet = getAirUiSet(this.mContext);
        if (isH9()) {
            airUiSet.setHaveRearArea(true);
        } else {
            airUiSet.setHaveRearArea(false);
        }
        if (isPortrait()) {
            getAirUiSet(this.mContext).getFrontArea().setDisableBtnArray(new String[]{"dual"});
        } else {
            getAirUiSet(this.mContext).getFrontArea().setDisableBtnArray(new String[]{"dual", "rear_defog", "front_defog"});
        }
    }

    private int initSeatSettingUi() {
        boolean z;
        if (isH9AndH6Couple()) {
            z = true;
        } else {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_337_air_2", "_337_air_3", "_337_air_4", "_337_air_5"});
            z = false;
        }
        if (isH9()) {
            z = true;
        } else {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_337_air_6", "_337_air_7", "_337_air_8", "_337_air_9"});
        }
        if (z) {
            return 1;
        }
        removeSettingLeftItemByNameList(this.mContext, new String[]{"_337_air"});
        return 0;
    }

    private int intiCarSeetingUi() {
        boolean z;
        if (isCarModel1()) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_337_setting_0", "_337_setting_1", "_337_setting_2", "_337_setting_3"});
            z = false;
        } else {
            if (isGreatWall()) {
                removeSettingRightItemByNameList(this.mContext, new String[]{"_337_setting_2"});
            } else {
                removeSettingRightItemByNameList(this.mContext, new String[]{"_337_setting_3"});
            }
            z = true;
        }
        if (isCarModel2()) {
            z = true;
        } else {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_337_setting_4"});
        }
        if (isCarModel3()) {
            z = true;
        } else {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_337_setting_5"});
        }
        if (isCarModel4()) {
            z = true;
        } else {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_337_setting_6"});
        }
        if (isH9()) {
            z = true;
        } else {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_337_setting_7", "_337_setting_9", "_337_setting_10", "_337_setting_11", "_337_setting_12", "_337_setting_13", "_337_setting_14", "_337_setting_15", "_337_setting_16", "_337_setting_18", "_337_setting_33", "_337_setting_34", "_337_setting_35", "_337_setting_36", "_337_setting_37", "_337_setting_38", "_337_setting_39", "_337_setting_40", "_337_setting_41", "_337_setting_42", "_337_setting_43", "_337_setting_44", "_337_setting_45", "_337_setting_46"});
        }
        if (isH9AndH7()) {
            z = true;
        } else {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_337_setting_8", "_337_setting_19", "_337_setting_30"});
        }
        if (isH9AndF7()) {
            z = true;
        } else {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_337_setting_8", "_337_setting_19", "_337_setting_21", "_337_setting_22", "_337_setting_23", "_337_setting_24", "_337_setting_25", "_337_setting_26", "_337_setting_26_on_27", "_337_setting_27", "_337_setting_28", "_337_setting_29", "_337_setting_30", "_337_setting_32", "_337_setting_51"});
        }
        if (isH7()) {
            z = true;
        } else {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_337_setting_31"});
        }
        if (isCarModel5()) {
            z = true;
        } else {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_337_setting_17"});
        }
        if (isCarModel6()) {
            z = true;
        } else {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_337_setting_20"});
        }
        if (isGreatWall()) {
            z = true;
        } else {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_337_setting_47", "_337_setting_48", "_337_setting_49", "_337_setting_50"});
        }
        if (isH2()) {
            z = true;
        } else {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_337_park", "_337_rear_camera"});
        }
        if (z) {
            return 1;
        }
        removeSettingLeftItemByNameList(this.mContext, new String[]{"_337_car_setting"});
        return 0;
    }

    public void selectCarModel() {
        switch (this.eachId) {
            case 1:
                sendCarModelInfo(32);
                break;
            case 2:
                sendCarModelInfo(1);
                break;
            case 3:
                sendCarModelInfo(2);
                break;
            case 4:
                sendCarModelInfo(4);
                break;
            case 5:
                sendCarModelInfo(5);
                break;
            case 6:
                sendCarModelInfo(5);
                break;
            case 7:
                sendCarModelInfo(8);
                break;
            case 9:
                sendCarModelInfo(12);
                break;
            case 10:
                sendCarModelInfo(6);
                break;
            case 11:
                sendCarModelInfo(9);
                break;
            case 12:
                sendCarModelInfo(13);
                break;
            case 13:
                sendCarModelInfo(MpegConstantsDef.MPEG_INFO_MENU_STATUS_CFM);
                break;
            case 14:
                sendCarModelInfo(15);
                break;
            case 15:
                sendCarModelInfo(1);
                break;
            case 16:
                sendCarModelInfo(4);
                break;
            case 17:
                sendCarModelInfo(17);
                break;
        }
    }

    /* renamed from: com.hzbhd.canbus.car._337.UiMgr$23, reason: invalid class name */
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
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.TREBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.MIDDLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.BASS.ordinal()] = 4;
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

    public void sendAirInfo(int i) {
        byte b = (byte) i;
        CanbusMsgSender.sendMsg(new byte[]{22, -124, b, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, -124, b, 0});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void activeRequest(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -112, (byte) i, (byte) i2});
    }

    public void makeConnection() {
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
    }

    public void send0x83CarSettingInfo(int i, int i2, int i3) {
        CanbusMsgSender.sendMsg(new byte[]{22, -125, (byte) i, (byte) i2, (byte) i3});
    }

    private void sendCarModelInfo(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -123, (byte) i});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAmplifierInfo(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -122, (byte) i, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendOriginalCarInfo(int i, int i2, int i3) {
        CanbusMsgSender.sendMsg(new byte[]{22, -120, (byte) i, (byte) i2, (byte) i3});
    }

    public void sendSourceCmd(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -64, (byte) i, (byte) i2});
    }

    public void sendRadioInfo(int i, int i2, int i3, int i4) {
        CanbusMsgSender.sendMsg(new byte[]{22, -62, (byte) i, (byte) i2, (byte) i3, (byte) i4});
    }

    public void sendSourceInfo3Line(int i, int i2, int i3, int i4, int i5, int i6) {
        CanbusMsgSender.sendMsg(new byte[]{22, -61, (byte) i, (byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6});
    }

    public void sendSourceInfo1Line(byte[] bArr) {
        CanbusMsgSender.sendMsg(bArr);
    }

    public void sendSourceInfo2Line(byte[] bArr) {
        CanbusMsgSender.sendMsg(bArr);
    }

    public void sendPhoneInfo(byte[] bArr) {
        CanbusMsgSender.sendMsg(bArr);
    }

    public void sendPortraitAirInfo(int i) {
        byte b = (byte) i;
        CanbusMsgSender.sendMsg(new byte[]{22, -32, b, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, -32, b, 0});
    }

    public boolean isCarModel1() {
        int i = this.eachId;
        return i == 7 || i == 2 || i == 3 || i == 4 || i == 15 || i == 16;
    }

    public boolean isCarModel2() {
        int i = this.eachId;
        return i == 7 || i == 8 || i == 9 || i == 10 || i == 11 || i == 12;
    }

    public boolean isCarModel3() {
        int i = this.eachId;
        return i == 7 || i == 8 || i == 9 || i == 10;
    }

    public boolean isCarModel4() {
        int i = this.eachId;
        return i == 1 || i == 2 || i == 3 || i == 9 || i == 10 || i == 11 || i == 12 || i == 13 || i == 14 || i == 15;
    }

    public boolean isCarModel5() {
        int i = this.eachId;
        return i == 2 || i == 7 || i == 9 || i == 13 || i == 14;
    }

    public boolean isCarModel6() {
        int i = this.eachId;
        return i == 9 || i == 11 || i == 12;
    }

    public boolean isCarModel7() {
        int i = this.eachId;
        return i == 4 || i == 7 || i == 9 || i == 10 || i == 11 || i == 12 || i == 13 || i == 14 || i == 16;
    }

    public boolean isCarModel8() {
        int i = this.eachId;
        return i == 9 || i == 12 || i == 14;
    }

    public boolean isGreatWall() {
        return this.differentId == 0;
    }

    public boolean isH9() {
        return this.eachId == 11;
    }

    public boolean isH7() {
        return this.eachId == 12;
    }

    public boolean isH9AndH7() {
        int i = this.eachId;
        return i == 11 || i == 12;
    }

    public boolean isH9AndF7() {
        int i = this.eachId;
        return i == 11 || i == 14;
    }

    public boolean isH9AndH6Couple() {
        int i = this.eachId;
        return i == 10 || i == 11;
    }

    private boolean isH2() {
        int i = this.eachId;
        return i == 2 || i == 3;
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

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
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
