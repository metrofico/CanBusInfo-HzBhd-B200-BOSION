package com.hzbhd.canbus.car._295;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import nfore.android.bt.res.NfDef;


public class UiMgr extends AbstractUiMgr {
    private int currentClickFront;
    private int currentClickRear;
    private int currentWindLv;
    private Context mContext;
    private MsgMgr mMsgMgr;
    private OnAirBtnClickListener mOnFrontAirTopBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._295.UiMgr.2
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 18, (byte) (!GeneralAirData.rear_lock ? 1 : 0)});
                return;
            }
            if (i == 1) {
                UiMgr.this.sendAirBtnCtrl(32, GeneralAirData.clean_air);
            } else if (i == 2) {
                UiMgr.this.sendAirBtnCtrl(35, GeneralAirData.steering_wheel_heating);
            } else {
                if (i != 3) {
                    return;
                }
                UiMgr.this.sendAirBtnCtrl(36, GeneralAirData.seat_steering_wheel_synchronization);
            }
        }
    };
    private OnAirBtnClickListener mOnFrontAirBottomLeftBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._295.UiMgr.3
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, 58, -127, 0});
                return;
            }
            int i2 = UiMgr.this.currentWindLv;
            if (i2 == 0) {
                UiMgr.this.currentWindLv = 1;
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 1, 0});
            } else if (i2 == 1) {
                UiMgr.this.currentWindLv = 2;
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 1, 1});
            } else {
                if (i2 != 2) {
                    return;
                }
                UiMgr.this.currentWindLv = 0;
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 1, 2});
            }
        }
    };
    private OnAirBtnClickListener mOnFrontAirBottomRightBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._295.UiMgr.4
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr.this.sendAirBtnCtrl(2, GeneralAirData.power);
        }
    };
    private OnAirBtnClickListener mOnFrontOnAirBottomBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._295.UiMgr.5
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirBtnCtrl(17, GeneralAirData.sync);
                return;
            }
            if (i == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, 58, -127, 1});
                return;
            }
            if (i == 2) {
                UiMgr.this.sendAirBtnCtrl(15, GeneralAirData.ac);
            } else {
                if (i != 3) {
                    return;
                }
                GeneralAirData.in_out_cycle = !GeneralAirData.in_out_cycle;
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 19, (byte) (!GeneralAirData.in_out_cycle ? 1 : 0)});
                Log.d("cwh", "GeneralAirData.in_out_cycle     " + GeneralAirData.in_out_cycle);
            }
        }
    };
    private OnAirSeatClickListener mOnFrontAirSeatClickListener = new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._295.UiMgr.6
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onLeftSeatClick() {
            int i = UiMgr.this.currentClickFront;
            if (i == 0) {
                UiMgr.this.currentClickFront = 1;
                UiMgr.this.sendAirClick(24);
                return;
            }
            if (i == 1) {
                UiMgr.this.currentClickFront = 2;
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 24, 0});
                return;
            }
            if (i == 2) {
                UiMgr.this.currentClickFront = 3;
                UiMgr.this.sendAirClick(25);
                return;
            }
            if (i == 3) {
                UiMgr.this.currentClickFront = 4;
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 25, 0});
            } else if (i == 4) {
                UiMgr.this.currentClickFront = 5;
                UiMgr.this.sendAirClick(26);
            } else {
                if (i != 5) {
                    return;
                }
                UiMgr.this.currentClickFront = 0;
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 26, 0});
            }
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onRightSeatClick() {
            int i = UiMgr.this.currentClickFront;
            if (i == 0) {
                UiMgr.this.currentClickFront = 1;
                UiMgr.this.sendAirClick(24);
                return;
            }
            if (i == 1) {
                UiMgr.this.currentClickFront = 2;
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 24, 0});
                return;
            }
            if (i == 2) {
                UiMgr.this.currentClickFront = 3;
                UiMgr.this.sendAirClick(25);
                return;
            }
            if (i == 3) {
                UiMgr.this.currentClickFront = 4;
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 25, 0});
            } else if (i == 4) {
                UiMgr.this.currentClickFront = 5;
                UiMgr.this.sendAirClick(26);
            } else {
                if (i != 5) {
                    return;
                }
                UiMgr.this.currentClickFront = 0;
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 26, 0});
            }
        }
    };
    private OnAirTemperatureUpDownClickListener mFrontTempSetViewOnUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._295.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            int i = 255;
            if (GeneralAirData.fahrenheit_celsius) {
                int i2 = (int) (MsgMgr.leftFrontTemp + 1.0f);
                if (MsgMgr.leftFrontTemp != 59.0f && MsgMgr.leftFrontTemp != 255.0f) {
                    i = i2;
                }
                if (MsgMgr.leftFrontTemp == 254.0f) {
                    i = 32;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 20, (byte) i});
                return;
            }
            int i3 = (int) (MsgMgr.leftFrontTemp + 2.0f);
            if (MsgMgr.leftFrontTemp != 170.0f && MsgMgr.leftFrontTemp != 255.0f) {
                i = i3;
            }
            if (MsgMgr.leftFrontTemp == 254.0f) {
                i = 122;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 20, (byte) (i != 123 ? i : 122)});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            boolean z = GeneralAirData.fahrenheit_celsius;
            int i = com.hzbhd.canbus.car._0.MsgMgr.DVD_MODE;
            if (z) {
                int i2 = (int) (MsgMgr.leftFrontTemp - 1.0f);
                if (MsgMgr.leftFrontTemp != 32.0f && MsgMgr.leftFrontTemp != 254.0f) {
                    i = i2;
                }
                if (MsgMgr.leftFrontTemp == 255.0f) {
                    i = 59;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 20, (byte) i});
                return;
            }
            int i3 = (int) (MsgMgr.leftFrontTemp - 2.0f);
            if (MsgMgr.leftFrontTemp != 122.0f && MsgMgr.leftFrontTemp != 254.0f) {
                i = i3;
            }
            if (MsgMgr.leftFrontTemp == 255.0f) {
                i = NfDef.STATE_RESP_AND_HOLD;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 20, (byte) i});
        }
    };
    private OnAirTemperatureUpDownClickListener mFrontTempSetViewOnUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._295.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            int i = 255;
            if (GeneralAirData.fahrenheit_celsius) {
                int i2 = (int) (MsgMgr.rightFrontTemp + 1.0f);
                if (MsgMgr.rightFrontTemp != 59.0f && MsgMgr.rightFrontTemp != 255.0f) {
                    i = i2;
                }
                if (MsgMgr.rightFrontTemp == 254.0f) {
                    i = 32;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 21, (byte) i});
                return;
            }
            int i3 = (int) (MsgMgr.rightFrontTemp + 2.0f);
            if (MsgMgr.rightFrontTemp != 170.0f && MsgMgr.rightFrontTemp != 255.0f) {
                i = i3;
            }
            if (MsgMgr.rightFrontTemp == 254.0f) {
                i = 122;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 21, (byte) (i != 123 ? i : 122)});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            boolean z = GeneralAirData.fahrenheit_celsius;
            int i = com.hzbhd.canbus.car._0.MsgMgr.DVD_MODE;
            if (z) {
                int i2 = (int) (MsgMgr.rightFrontTemp - 1.0f);
                if (MsgMgr.rightFrontTemp != 32.0f && MsgMgr.rightFrontTemp != 254.0f) {
                    i = i2;
                }
                if (MsgMgr.rightFrontTemp == 255.0f) {
                    i = 59;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 21, (byte) i});
                return;
            }
            int i3 = (int) (MsgMgr.rightFrontTemp - 2.0f);
            if (MsgMgr.rightFrontTemp != 122.0f && MsgMgr.rightFrontTemp != 254.0f) {
                i = i3;
            }
            if (MsgMgr.rightFrontTemp == 255.0f) {
                i = NfDef.STATE_RESP_AND_HOLD;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 21, (byte) i});
        }
    };
    private OnAirWindSpeedUpDownClickListener mSetFrontWindSpeedViewOnClickListener = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._295.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            int i = MsgMgr.frontWindLv - 1;
            if (i < 0) {
                i = 0;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 23, (byte) i});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            int i = MsgMgr.frontWindLv + 1;
            if (i > 7) {
                i = 7;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 23, (byte) i});
        }
    };
    private OnAirSeatHeatColdMinPlusClickListener mFrontLeftSeatHeatListener = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._295.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
            UiMgr.this.sendSeatHeatColdMsg(GeneralAirData.front_left_seat_heat_level - 1, 33);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr.this.sendSeatHeatColdMsg(GeneralAirData.front_left_seat_heat_level + 1, 33);
        }
    };
    private OnAirSeatHeatColdMinPlusClickListener mFrontRightSeatHeatListener = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._295.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
            UiMgr.this.sendSeatHeatColdMsg(GeneralAirData.front_right_seat_heat_level - 1, 34);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr.this.sendSeatHeatColdMsg(GeneralAirData.front_right_seat_heat_level + 1, 34);
        }
    };
    private OnAirSeatHeatColdMinPlusClickListener mFrontLeftSeatColdListener = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._295.UiMgr.12
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
            UiMgr.this.sendSeatHeatColdMsg(GeneralAirData.front_left_seat_cold_level - 1, 37);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr.this.sendSeatHeatColdMsg(GeneralAirData.front_left_seat_cold_level + 1, 37);
        }
    };
    private OnAirSeatHeatColdMinPlusClickListener mFrontRightSeatColdListener = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._295.UiMgr.13
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
            UiMgr.this.sendSeatHeatColdMsg(GeneralAirData.front_right_seat_cold_level - 1, 38);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr.this.sendSeatHeatColdMsg(GeneralAirData.front_right_seat_cold_level + 1, 38);
        }
    };
    private OnAirSeatHeatColdMinPlusClickListener mRearLeftSeatHeatColdListener = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._295.UiMgr.14
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
            UiMgr.this.sendSeatHeatColdMsg(GeneralAirData.rear_left_seat_heat_level - 1, 43);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr.this.sendSeatHeatColdMsg(GeneralAirData.rear_left_seat_heat_level + 1, 43);
        }
    };
    private OnAirSeatHeatColdMinPlusClickListener mRearRightSeatHeatColdListener = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._295.UiMgr.15
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
            UiMgr.this.sendSeatHeatColdMsg(GeneralAirData.rear_right_seat_heat_level - 1, 44);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr.this.sendSeatHeatColdMsg(GeneralAirData.rear_right_seat_heat_level + 1, 44);
        }
    };
    private OnAirTemperatureUpDownClickListener mRearTempSetViewOnUpDownClickListenerCenter = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._295.UiMgr.16
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            int i = 255;
            if (GeneralAirData.fahrenheit_celsius) {
                int i2 = (int) (MsgMgr.rearTemp + 1.0f);
                if (MsgMgr.rearTemp != 59.0f && MsgMgr.rearTemp != 255.0f) {
                    i = i2;
                }
                if (MsgMgr.rearTemp == 254.0f) {
                    i = 32;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 22, (byte) i});
                return;
            }
            int i3 = (int) (MsgMgr.rearTemp + 2.0f);
            if (MsgMgr.rearTemp != 170.0f && MsgMgr.rearTemp != 255.0f) {
                i = i3;
            }
            if (MsgMgr.rearTemp == 254.0f) {
                i = 122;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 22, (byte) (i != 123 ? i : 122)});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            boolean z = GeneralAirData.fahrenheit_celsius;
            int i = com.hzbhd.canbus.car._0.MsgMgr.DVD_MODE;
            if (z) {
                int i2 = (int) (MsgMgr.rearTemp - 1.0f);
                if (MsgMgr.rearTemp != 32.0f && MsgMgr.rearTemp != 254.0f) {
                    i = i2;
                }
                if (MsgMgr.rearTemp == 255.0f) {
                    i = 59;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 22, (byte) i});
                return;
            }
            int i3 = (int) (MsgMgr.rearTemp - 2.0f);
            if (MsgMgr.rearTemp != 122.0f && MsgMgr.rearTemp != 254.0f) {
                i = i3;
            }
            if (MsgMgr.rearTemp == 255.0f) {
                i = NfDef.STATE_RESP_AND_HOLD;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 22, (byte) i});
        }
    };
    private OnAirWindSpeedUpDownClickListener mSetRearWindSpeedViewOnClickListener = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._295.UiMgr.17
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            int i = GeneralAirData.rear_wind_level - 1;
            if (i < 0) {
                i = 0;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 41, (byte) i});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            int i = GeneralAirData.rear_wind_level + 1;
            if (i > 7) {
                i = 7;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 41, (byte) i});
        }
    };
    private OnAirBtnClickListener mOnRearAirButtomBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._295.UiMgr.18
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                GeneralAirData.rear_auto = !GeneralAirData.rear_auto;
                UiMgr.this.sendAirBtnCtrl(40, GeneralAirData.rear_auto);
            } else {
                if (i != 1) {
                    return;
                }
                UiMgr.this.sendAirBtnCtrl(39, GeneralAirData.rear_power);
            }
        }
    };
    private OnAirSeatClickListener mOnRearAirSeatClickListener = new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._295.UiMgr.19
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onLeftSeatClick() {
            int i = UiMgr.this.currentClickRear;
            if (i == 0) {
                UiMgr.this.currentClickRear = 1;
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 42, 1});
            } else if (i == 1) {
                UiMgr.this.currentClickRear = 2;
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 42, 2});
            } else {
                if (i != 2) {
                    return;
                }
                UiMgr.this.currentClickRear = 0;
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 42, 3});
            }
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onRightSeatClick() {
            int i = UiMgr.this.currentClickRear;
            if (i == 0) {
                UiMgr.this.currentClickRear = 1;
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 42, 1});
            } else if (i == 1) {
                UiMgr.this.currentClickRear = 2;
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 42, 2});
            } else {
                if (i != 2) {
                    return;
                }
                UiMgr.this.currentClickRear = 0;
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 42, 3});
            }
        }
    };
    private OnSettingItemSelectListener mOnItemSelectedListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._295.UiMgr.20
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            switch (i) {
                case 0:
                    switch (i3) {
                        case 0:
                            UiMgr.this.sendDrivingDataMsg(1);
                            break;
                        case 1:
                            UiMgr.this.sendDrivingDataMsg(2);
                            break;
                        case 2:
                            UiMgr.this.sendDrivingDataMsg(3);
                            break;
                        case 3:
                            UiMgr.this.sendDrivingDataMsg(4);
                            break;
                        case 4:
                            UiMgr.this.sendDrivingDataMsg(5);
                            break;
                        case 5:
                            UiMgr.this.sendDrivingDataMsg(6);
                            break;
                        case 6:
                            UiMgr.this.sendDrivingDataMsg(8);
                            break;
                        case 7:
                            UiMgr.this.sendDrivingDataMsg(9);
                            break;
                    }
                    return;
                case 1:
                    if (i2 == 0) {
                        UiMgr.this.sendSettingLeft1Msg(1, i3);
                        return;
                    }
                    if (i2 == 1) {
                        UiMgr.this.sendSettingLeft1Msg(2, i3);
                        return;
                    }
                    if (i2 == 2) {
                        UiMgr.this.sendSettingLeft1Msg(3, i3);
                        return;
                    }
                    if (i2 == 3) {
                        UiMgr.this.sendSettingLeft1Msg(4, i3);
                        return;
                    } else if (i2 == 4) {
                        UiMgr.this.sendSettingLeft1Msg(5, i3);
                        return;
                    } else {
                        if (i2 != 5) {
                            return;
                        }
                        UiMgr.this.sendSettingLeft1Msg(6, i3);
                        return;
                    }
                case 2:
                    switch (i2) {
                        case 0:
                            UiMgr.this.sendSettingLeft2Msg(2, i3);
                            break;
                        case 1:
                            UiMgr.this.sendSettingLeft2Msg(1, i3);
                            break;
                        case 2:
                            UiMgr.this.sendSettingLeft2Msg(4, i3);
                            break;
                        case 3:
                            UiMgr.this.sendSettingLeft2Msg(5, i3);
                            break;
                        case 4:
                            UiMgr.this.sendSettingLeft2Msg(6, i3);
                            break;
                        case 5:
                            UiMgr.this.sendSettingLeft2Msg(7, i3);
                            break;
                        case 6:
                            UiMgr.this.sendSettingLeft2Msg(8, i3);
                            break;
                        case 7:
                            UiMgr.this.sendSettingLeft2Msg(9, i3);
                            break;
                        case 8:
                            UiMgr.this.sendSettingLeft2Msg(3, i3);
                            break;
                    }
                    return;
                case 3:
                    CanbusMsgSender.sendMsg(new byte[]{22, -118, 3, (byte) i3});
                    return;
                case 4:
                    if (i2 == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 75, 2, (byte) i3});
                        if (i3 == 1) {
                            MsgMgr.carSpeedWarning = 1;
                        } else {
                            MsgMgr.carSpeedWarning = 0;
                        }
                    }
                    if (i2 == 3) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 75, 4, (byte) i3});
                        return;
                    }
                    return;
                case 5:
                    switch (i2) {
                        case 0:
                            UiMgr.this.sendSettingLeft5Msg(9, i3 + 1);
                            break;
                        case 1:
                            UiMgr.this.sendSettingLeft5Msg(11, i3);
                            break;
                        case 2:
                            UiMgr.this.sendSettingLeft5Msg(8, i3 + 1);
                            break;
                        case 3:
                            UiMgr.this.sendSettingLeft5Msg(1, i3);
                            break;
                        case 4:
                            UiMgr.this.sendSettingLeft5Msg(2, i3);
                            break;
                        case 5:
                            UiMgr.this.sendSettingLeft5Msg(3, i3);
                            break;
                        case 6:
                            UiMgr.this.sendSettingLeft5Msg(12, i3);
                            break;
                        case 7:
                            UiMgr.this.sendSettingLeft5Msg(4, i3);
                            break;
                        case 8:
                            UiMgr.this.sendSettingLeft5Msg(5, i3);
                            break;
                        case 9:
                            UiMgr.this.sendSettingLeft5Msg(6, i3);
                            break;
                        case 10:
                            UiMgr.this.sendSettingLeft5Msg(13, i3);
                            break;
                        case 11:
                            UiMgr.this.sendSettingLeft5Msg(7, i3);
                            break;
                        case 12:
                            UiMgr.this.sendSettingLeft5Msg(10, i3);
                            break;
                    }
                    return;
                case 6:
                    if (i2 == 0) {
                        UiMgr.this.sendSettingLeft6Msg(7, 2 - i3);
                        return;
                    }
                    if (i2 == 1) {
                        UiMgr.this.sendSettingLeft6Msg(1, i3);
                        return;
                    }
                    if (i2 == 2) {
                        UiMgr.this.sendSettingLeft6Msg(8, i3);
                        return;
                    }
                    if (i2 == 3) {
                        UiMgr.this.sendSettingLeft6Msg(9, i3);
                        return;
                    }
                    if (i2 == 4) {
                        UiMgr.this.sendSettingLeft6Msg(11, i3);
                        return;
                    } else if (i2 == 10) {
                        UiMgr.this.sendSettingLeft6Msg(10, i3);
                        return;
                    } else {
                        if (i2 != 11) {
                            return;
                        }
                        UiMgr.this.sendSettingLeft6Msg(12, i3);
                        return;
                    }
                case 7:
                    switch (i2) {
                        case 0:
                            UiMgr.this.sendSettingLeft7Msg(3, i3);
                            break;
                        case 1:
                            UiMgr.this.sendSettingLeft7Msg(6, i3);
                            break;
                        case 2:
                            UiMgr.this.sendSettingLeft7Msg(4, i3);
                            break;
                        case 3:
                            UiMgr.this.sendSettingLeft7Msg(5, i3);
                            break;
                        case 4:
                            UiMgr.this.sendSettingLeft7Msg(1, i3);
                            break;
                        case 5:
                            UiMgr.this.sendSettingLeft7Msg(2, i3);
                            break;
                        case 6:
                            UiMgr.this.sendSettingLeft7Msg(16, i3);
                            break;
                    }
                    return;
                case 8:
                    if (i2 == 0) {
                        UiMgr.this.sendSettingLeft7Msg(12, i3);
                        return;
                    } else if (i2 == 1) {
                        UiMgr.this.sendSettingLeft7Msg(18, i3 + 1);
                        return;
                    } else {
                        if (i2 != 2) {
                            return;
                        }
                        UiMgr.this.sendSettingLeft7Msg(17, i3);
                        return;
                    }
                case 9:
                    if (i2 == 0) {
                        UiMgr.this.sendSettingLeft9Msg(1, i3);
                        return;
                    }
                    if (i2 == 1) {
                        UiMgr.this.sendSettingLeft9Msg(2, i3);
                        return;
                    }
                    if (i2 == 2) {
                        UiMgr.this.sendSettingLeft9Msg(3, i3);
                        return;
                    } else if (i2 == 3) {
                        UiMgr.this.sendSettingLeft9Msg(4, i3);
                        return;
                    } else {
                        if (i2 != 4) {
                            return;
                        }
                        UiMgr.this.sendSettingLeft9Msg(5, i3);
                        return;
                    }
                case 10:
                    if (i2 == 0) {
                        UiMgr.this.sendSettingLeft10Msg(1, i3);
                        return;
                    }
                    if (i2 == 1) {
                        UiMgr.this.sendSettingLeft10Msg(2, i3);
                        return;
                    }
                    if (i2 == 2) {
                        UiMgr.this.sendSettingLeft10Msg(3, i3);
                        return;
                    }
                    if (i2 == 3) {
                        UiMgr.this.sendSettingLeft10Msg(4, i3);
                        return;
                    } else if (i2 == 4) {
                        UiMgr.this.sendSettingLeft10Msg(5, i3);
                        return;
                    } else {
                        if (i2 != 5) {
                            return;
                        }
                        UiMgr.this.sendSettingLeft10Msg(6, i3);
                        return;
                    }
                case 11:
                    switch (i2) {
                        case 0:
                            UiMgr.this.sendSettingLeft11Msg(1, i3);
                            break;
                        case 1:
                            UiMgr.this.sendSettingLeft11Msg(2, i3);
                            break;
                        case 2:
                            UiMgr.this.sendSettingLeft11Msg(3, i3);
                            break;
                        case 3:
                            UiMgr.this.sendSettingLeft11Msg(4, i3);
                            break;
                        case 4:
                            UiMgr.this.sendSettingLeft11Msg(5, i3);
                            break;
                        case 5:
                            UiMgr.this.sendSettingLeft11Msg(6, i3);
                            break;
                        case 6:
                            UiMgr.this.sendSettingLeft11Msg(7, i3);
                            break;
                        case 7:
                            UiMgr.this.sendSettingLeft11Msg(8, i3);
                            break;
                        case 8:
                            UiMgr.this.sendSettingLeft11Msg(9, i3);
                            break;
                        case 9:
                            UiMgr.this.sendSettingLeft11Msg(10, i3);
                            break;
                    }
                    return;
                case 12:
                    switch (i2) {
                        case 0:
                            CanbusMsgSender.sendMsg(new byte[]{22, -54, 1, (byte) (2 - i3)});
                            break;
                        case 1:
                            CanbusMsgSender.sendMsg(new byte[]{22, -54, 2, (byte) (2 - i3)});
                            break;
                        case 2:
                            CanbusMsgSender.sendMsg(new byte[]{22, -54, 3, (byte) (2 - i3)});
                            break;
                        case 3:
                            CanbusMsgSender.sendMsg(new byte[]{22, -54, 4, (byte) (i3 + 1)});
                            break;
                        case 4:
                            CanbusMsgSender.sendMsg(new byte[]{22, -54, 5, (byte) (i3 + 1)});
                            break;
                        case 5:
                            CanbusMsgSender.sendMsg(new byte[]{22, -54, 7, (byte) (i3 + 1)});
                            break;
                        case 6:
                            CanbusMsgSender.sendMsg(new byte[]{22, -54, 6, (byte) (i3 + 1)});
                            break;
                    }
                case 13:
                case 15:
                case 17:
                default:
                    return;
                case 14:
                    break;
                case 16:
                    if (i3 == 0) {
                        UiMgr.this.sendDrivingDataMsg(32);
                        return;
                    }
                    if (i3 == 1) {
                        UiMgr.this.sendDrivingDataMsg(33);
                        return;
                    } else if (i3 == 2) {
                        UiMgr.this.sendDrivingDataMsg(34);
                        return;
                    } else {
                        if (i3 != 3) {
                            return;
                        }
                        UiMgr.this.sendDrivingDataMsg(35);
                        return;
                    }
                case 18:
                    CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte) (i3 + 1)});
                    UiMgr.this.mMsgMgr.updateLanguageSet(18, 0, i3);
                    return;
                case 19:
                    if (i2 == 0) {
                        if (i3 == 0) {
                            CanbusMsgSender.sendMsg(new byte[]{22, 77, 1, 5});
                            return;
                        }
                        if (i3 == 1) {
                            CanbusMsgSender.sendMsg(new byte[]{22, 77, 1, 10});
                            return;
                        } else if (i3 == 2) {
                            CanbusMsgSender.sendMsg(new byte[]{22, 77, 1, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED});
                            return;
                        } else {
                            if (i3 != 3) {
                                return;
                            }
                            CanbusMsgSender.sendMsg(new byte[]{22, 77, 1, -1});
                            return;
                        }
                    }
                    if (i2 != 1) {
                        if (i2 != 3) {
                            return;
                        }
                        UiMgr.this.sendSettingLeft17Msg(3, i3);
                        return;
                    }
                    switch (i3) {
                        case 0:
                            CanbusMsgSender.sendMsg(new byte[]{22, 77, 2, -2});
                            break;
                        case 1:
                            CanbusMsgSender.sendMsg(new byte[]{22, 77, 2, 16});
                            break;
                        case 2:
                            CanbusMsgSender.sendMsg(new byte[]{22, 77, 2, 17});
                            break;
                        case 3:
                            CanbusMsgSender.sendMsg(new byte[]{22, 77, 2, 18});
                            break;
                        case 4:
                            CanbusMsgSender.sendMsg(new byte[]{22, 77, 2, 19});
                            break;
                        case 5:
                            CanbusMsgSender.sendMsg(new byte[]{22, 77, 2, 20});
                            break;
                        case 6:
                            CanbusMsgSender.sendMsg(new byte[]{22, 77, 2, 21});
                            break;
                        case 7:
                            CanbusMsgSender.sendMsg(new byte[]{22, 77, 2, 22});
                            break;
                        case 8:
                            CanbusMsgSender.sendMsg(new byte[]{22, 77, 2, 23});
                            break;
                        case 9:
                            CanbusMsgSender.sendMsg(new byte[]{22, 77, 2, 24});
                            break;
                        case 10:
                            CanbusMsgSender.sendMsg(new byte[]{22, 77, 2, 25});
                            break;
                        case 11:
                            CanbusMsgSender.sendMsg(new byte[]{22, 77, 2, 26});
                            break;
                        case 12:
                            CanbusMsgSender.sendMsg(new byte[]{22, 77, 2, 27});
                            break;
                        case 13:
                            CanbusMsgSender.sendMsg(new byte[]{22, 77, 2, 28});
                            break;
                        case 14:
                            CanbusMsgSender.sendMsg(new byte[]{22, 77, 2, 29});
                            break;
                        case 15:
                            CanbusMsgSender.sendMsg(new byte[]{22, 77, 2, -1});
                            break;
                    }
                    return;
            }
            if (i2 == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 10, (byte) i3});
                return;
            }
            if (i2 == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 14, (byte) i3});
                return;
            }
            if (i2 == 2) {
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 1, (byte) i3});
                return;
            }
            if (i2 != 3) {
                return;
            }
            if (i3 == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, 58, -127, 0});
            } else if (i3 == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, 58, -127, 1});
            } else if (i3 == 2) {
                CanbusMsgSender.sendMsg(new byte[]{22, 58, -127, 2});
            } else if (i3 == 3) {
                CanbusMsgSender.sendMsg(new byte[]{22, 58, -127, 3});
            }
            UiMgr.this.mMsgMgr.updateAirSet(14, 3, i3);
        }
    };
    private OnSettingItemSeekbarSelectListener mOnSeekBarChangeListener = new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._295.UiMgr.21
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
        public void onClickItem(int i, int i2, int i3) {
            if (i == 4) {
                if (i2 == 2) {
                    if (MsgMgr.carSpeedWarning == 0) {
                        Toast.makeText(UiMgr.this.mContext, UiMgr.this.mContext.getText(R.string.car_speed_warning), 0).show();
                        return;
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, 75, 3, (byte) i3});
                        return;
                    }
                }
                return;
            }
            if (i != 15) {
                if (i == 19) {
                    if (i2 == 2) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 77, 4, (byte) (i3 / 10)});
                        return;
                    }
                    return;
                }
                if (i == 6) {
                    if (i2 == 5) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 74, 2, (byte) i3});
                        return;
                    }
                    if (i2 == 6) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 74, 3, (byte) i3});
                        return;
                    } else if (i2 == 7) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 74, 4, (byte) i3});
                        return;
                    } else {
                        if (i2 != 8) {
                            return;
                        }
                        CanbusMsgSender.sendMsg(new byte[]{22, 74, 5, (byte) i3});
                        return;
                    }
                }
                if (i == 7) {
                    switch (i2) {
                        case 7:
                            CanbusMsgSender.sendMsg(new byte[]{22, 109, 10, (byte) i3});
                            break;
                        case 8:
                            CanbusMsgSender.sendMsg(new byte[]{22, 109, 11, (byte) i3});
                            break;
                        case 9:
                            CanbusMsgSender.sendMsg(new byte[]{22, 109, 7, (byte) i3});
                            break;
                        case 10:
                            CanbusMsgSender.sendMsg(new byte[]{22, 109, 8, (byte) i3});
                            break;
                        case 11:
                            CanbusMsgSender.sendMsg(new byte[]{22, 109, 9, (byte) i3});
                            break;
                    }
                    return;
                }
                if (i != 8) {
                    return;
                }
                if (i2 == 3) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 109, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) i3});
                } else if (i2 == 4) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 109, 14, (byte) i3});
                } else if (i2 == 5) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 109, 15, (byte) i3});
                }
            }
            if (i2 == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -14, 10, (byte) i3});
            } else if (i2 == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -14, 11, (byte) i3});
            } else {
                if (i2 != 2) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -14, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) i3});
            }
        }
    };
    private OnPanoramicItemClickListener mOnPanoramicItemClickListener = new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._295.UiMgr.22
        @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
        public void onClickItem(int i) {
        }
    };

    private byte setWidgetData(boolean z) {
        return !z ? (byte) 1 : (byte) 0;
    }

    public UiMgr(Context context) {
        this.mContext = context;
        this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(this.mOnItemSelectedListener);
        settingUiSet.setOnSettingItemSeekbarSelectListener(this.mOnSeekBarChangeListener);
        getParkPageUiSet(context).setOnPanoramicItemClickListener(this.mOnPanoramicItemClickListener);
        AirPageUiSet airUiSet = getAirUiSet(context);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnFrontAirTopBtnClickListener, this.mOnFrontAirBottomLeftBtnClickListener, this.mOnFrontAirBottomRightBtnClickListener, this.mOnFrontOnAirBottomBtnClickListener});
        airUiSet.getFrontArea().setOnAirSeatClickListener(this.mOnFrontAirSeatClickListener);
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mFrontTempSetViewOnUpDownClickListenerLeft, null, this.mFrontTempSetViewOnUpDownClickListenerRight});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.mSetFrontWindSpeedViewOnClickListener);
        airUiSet.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.mFrontLeftSeatHeatListener, this.mFrontRightSeatHeatListener, this.mFrontLeftSeatColdListener, this.mFrontRightSeatColdListener});
        airUiSet.getRearArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.mRearLeftSeatHeatColdListener, this.mRearRightSeatHeatColdListener});
        airUiSet.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{null, this.mRearTempSetViewOnUpDownClickListenerCenter, null});
        airUiSet.getRearArea().setSetWindSpeedViewOnClickListener(this.mSetRearWindSpeedViewOnClickListener);
        airUiSet.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, this.mOnRearAirButtomBtnClickListener});
        airUiSet.getRearArea().setOnAirSeatClickListener(this.mOnRearAirSeatClickListener);
        settingUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._295.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
            public void onConfirmClick(int i, int i2) {
                if (i == 1) {
                    if (i2 == 6) {
                        Toast.makeText(UiMgr.this.mContext, UiMgr.this.mContext.getText(R.string.reset_completed), 0).show();
                        UiMgr.this.sendData(new byte[]{22, -117, 5, 7});
                    }
                    return;
                }
                if (i == 2) {
                    if (i2 == 9) {
                        Toast.makeText(UiMgr.this.mContext, UiMgr.this.mContext.getText(R.string.reset_completed), 0).show();
                        UiMgr.this.sendData(new byte[]{22, -117, 9, 10});
                        return;
                    }
                    return;
                }
                if (i == 4) {
                    if (i2 == 0) {
                        UiMgr.this.sendData(new byte[]{22, 75, 1, 1});
                        return;
                    }
                    return;
                }
                if (i == 11) {
                    if (i2 == 10) {
                        Toast.makeText(UiMgr.this.mContext, UiMgr.this.mContext.getText(R.string.reset_completed), 0).show();
                        UiMgr.this.sendData(new byte[]{22, 123, 11});
                        return;
                    } else {
                        if (i2 == 11) {
                            Toast.makeText(UiMgr.this.mContext, UiMgr.this.mContext.getText(R.string.reset_completed), 0).show();
                            UiMgr.this.sendData(new byte[]{22, 123, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED});
                            return;
                        }
                        return;
                    }
                }
                if (i != 13) {
                    return;
                }
                switch (i2) {
                    case 0:
                        UiMgr.this.sendSettingResetData(128);
                        break;
                    case 1:
                        UiMgr.this.sendSettingResetData(64);
                        break;
                    case 2:
                        UiMgr.this.sendSettingResetData(32);
                        break;
                    case 3:
                        UiMgr.this.sendSettingResetData(16);
                        break;
                    case 4:
                        UiMgr.this.sendSettingResetData(8);
                        break;
                    case 5:
                        UiMgr.this.sendSettingResetData(4);
                        break;
                    case 6:
                        UiMgr.this.sendSettingResetData(2);
                        break;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirBtnCtrl(int i, boolean z) {
        CanbusMsgSender.sendMsg(new byte[]{22, 58, (byte) i, setWidgetData(z)});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirClick(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, 58, (byte) i, 1});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSeatHeatColdMsg(int i, int i2) {
        if (i >= 3) {
            i = 3;
        }
        if (i <= 0) {
            i = 0;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, 58, (byte) i2, (byte) i});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendDrivingDataMsg(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -117, (byte) i, 0});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSettingLeft1Msg(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -117, 5, (byte) i, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSettingLeft2Msg(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -117, 9, (byte) i, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSettingLeft5Msg(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, 76, (byte) i, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSettingLeft6Msg(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, 74, (byte) i, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSettingLeft7Msg(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, 109, (byte) i, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSettingLeft9Msg(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, 110, (byte) i, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSettingLeft10Msg(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, 111, (byte) i, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSettingLeft11Msg(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, 123, (byte) i, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSettingLeft17Msg(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, 77, (byte) i, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSettingResetData(int i) {
        Context context = this.mContext;
        Toast.makeText(context, context.getText(R.string.reset_completed), 0).show();
        sendData(new byte[]{22, 26, (byte) i});
    }
}
