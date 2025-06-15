package com.hzbhd.canbus.car._2;

import android.content.Context;

import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.interfaces.OnTirePageStatusListener;
import com.hzbhd.canbus.interfaces.OnWarningStatusChangeListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;

import java.util.Iterator;
import java.util.List;

import kotlin.text.Typography;
import nfore.android.bt.res.NfDef;


public class UiMgr extends AbstractUiMgr {
    private Context mContext;
    MsgMgr mMsgMgr;
    private MsgMgr msgMgr;
    private String FRONT_RADAR_KEY = "FRONT_RADAR_KEY";
    OnAirSeatHeatColdMinPlusClickListener mFrontRightSeatHeatListener = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._2.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            if (GeneralAirData.front_right_seat_heat_level == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -82, 1});
            }
            if (GeneralAirData.front_right_seat_heat_level == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -82, 2});
            }
            if (GeneralAirData.front_right_seat_heat_level == 2) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -82, 3});
            }
            if (GeneralAirData.front_right_seat_heat_level == 3) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -82, 0});
            }
        }
    };
    OnAirSeatHeatColdMinPlusClickListener mFrontLeftSeatHeatListener = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._2.UiMgr.12
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            if (GeneralAirData.front_left_seat_heat_level == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -83, 1});
            }
            if (GeneralAirData.front_left_seat_heat_level == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -83, 2});
            }
            if (GeneralAirData.front_left_seat_heat_level == 2) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -83, 3});
            }
            if (GeneralAirData.front_left_seat_heat_level == 3) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -83, 0});
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._2.UiMgr.13
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                if (GeneralAirData.clean_air) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, -81, 0});
                    return;
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, -81, 1});
                    return;
                }
            }
            if (i == 1) {
                if (GeneralAirData.aqs) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, -80, 0});
                    return;
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, -80, 1});
                    return;
                }
            }
            if (i == 3) {
                boolean z = GeneralAirData.max_front;
            } else {
                if (i != 4) {
                    return;
                }
                if (GeneralAirData.in_out_cycle) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, -65, 0});
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, -65, 1});
                }
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._2.UiMgr.14
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                if (GeneralAirData.power) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, -78, 0});
                    return;
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, -78, 1});
                    return;
                }
            }
            if (i != 1) {
                return;
            }
            if (GeneralAirData.steering_wheel_heating) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 0});
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 1});
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._2.UiMgr.15
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                if (GeneralAirData.ac) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, -66, 0});
                    return;
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, -66, 1});
                    return;
                }
            }
            if (i == 1) {
                if (GeneralAirData.ac_max) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -69, 2});
            } else if (i == 2) {
                if (GeneralAirData.auto) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -69, 1});
            } else {
                if (i != 3) {
                    return;
                }
                if (GeneralAirData.auto_wind_lv == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, -79, 1});
                }
                if (GeneralAirData.auto_wind_lv == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, -79, 2});
                }
                if (GeneralAirData.auto_wind_lv == 2) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, -79, 0});
                }
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontLeft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._2.UiMgr.16
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 1) {
                return;
            }
            if (GeneralAirData.front_defog) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -86, 0});
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -86, 1});
            }
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._2.UiMgr.17
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -72, 1});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -72, 0});
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._2.UiMgr.18
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -71, 1});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -71, 0});
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerRear = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._2.UiMgr.19
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -70, 1});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -70, 0});
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerRearTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._2.UiMgr.20
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                if (GeneralAirData.rear_power) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, -94, 0});
                    return;
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, -94, 0});
                    return;
                }
            }
            if (i != 1) {
                return;
            }
            if (GeneralAirData.rear_lock) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -68, 0});
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -68, 0});
            }
        }
    };
    OnAirWindSpeedUpDownClickListener mOnAirWindSpeedUpDownClickListenerFront = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._2.UiMgr.21
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            if (GeneralAirData.front_wind_level > 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -73, (byte) (GeneralAirData.front_wind_level - 1)});
            }
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            if (GeneralAirData.front_wind_level < 8) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -73, (byte) (GeneralAirData.front_wind_level + 1)});
            }
        }
    };
    OnAirWindSpeedUpDownClickListener mOnAirWindSpeedUpDownClickListenerRear = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._2.UiMgr.22
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            if (GeneralAirData.rear_wind_level > 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -90, (byte) (GeneralAirData.rear_wind_level - 1)});
            }
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            if (GeneralAirData.rear_wind_level < 8) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -90, (byte) (GeneralAirData.rear_wind_level + 1)});
            }
        }
    };

    public UiMgr(Context context) {
        this.mContext = context;
        this.msgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        final SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemClickListener(new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._2.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
            public void onClickItem(int i, int i2) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                if (titleSrn.equals("_2_setting_1_4")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 34, 1, 0, 0, 0});
                    UiMgr uiMgr = UiMgr.this;
                    uiMgr.getMsgMgr(uiMgr.mContext).tireSetting();
                }
            }
        });
        settingUiSet.setOnSettingPageStatusListener(new OnSettingPageStatusListener() { // from class: com.hzbhd.canbus.car._2.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnSettingPageStatusListener
            public void onStatusChange(int i) {
                UiMgr.this.activeRequest(64, 255);
            }
        });
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._2.UiMgr.3
            /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                char c = 65535;
                switch (titleSrn.hashCode()) {
                    case -2115760223:
                        if (titleSrn.equals("_2_set_language")) {
                            c = 0;
                            break;
                        }
                        break;
                    case 386686829:
                        if (titleSrn.equals("_2_setting_11_1")) {
                            c = 1;
                            break;
                        }
                        break;
                    case 386686830:
                        if (titleSrn.equals("_2_setting_11_2")) {
                            c = 2;
                            break;
                        }
                        break;
                    case 386686831:
                        if (titleSrn.equals("_2_setting_11_3")) {
                            c = 3;
                            break;
                        }
                        break;
                    case 386686832:
                        if (titleSrn.equals("_2_setting_11_4")) {
                            c = 4;
                            break;
                        }
                        break;
                    case 386686833:
                        if (titleSrn.equals("_2_setting_11_5")) {
                            c = 5;
                            break;
                        }
                        break;
                    case 386686834:
                        if (titleSrn.equals("_2_setting_11_6")) {
                            c = 6;
                            break;
                        }
                        break;
                    case 386687792:
                        if (titleSrn.equals("_2_setting_12_3")) {
                            c = 7;
                            break;
                        }
                        break;
                    case 386687793:
                        if (titleSrn.equals("_2_setting_12_4")) {
                            c = '\b';
                            break;
                        }
                        break;
                    case 386687794:
                        if (titleSrn.equals("_2_setting_12_5")) {
                            c = '\t';
                            break;
                        }
                        break;
                    case 386687795:
                        if (titleSrn.equals("_2_setting_12_6")) {
                            c = '\n';
                            break;
                        }
                        break;
                    case 386687796:
                        if (titleSrn.equals("_2_setting_12_7")) {
                            c = 11;
                            break;
                        }
                        break;
                    case 386688751:
                        if (titleSrn.equals("_2_setting_13_1")) {
                            c = '\f';
                            break;
                        }
                        break;
                    case 386688752:
                        if (titleSrn.equals("_2_setting_13_2")) {
                            c = '\r';
                            break;
                        }
                        break;
                    case 386688753:
                        if (titleSrn.equals("_2_setting_13_3")) {
                            c = 14;
                            break;
                        }
                        break;
                    case 386688754:
                        if (titleSrn.equals("_2_setting_13_4")) {
                            c = 15;
                            break;
                        }
                        break;
                    case 386688758:
                        if (titleSrn.equals("_2_setting_13_8")) {
                            c = 16;
                            break;
                        }
                        break;
                    case 386690673:
                        if (titleSrn.equals("_2_setting_15_1")) {
                            c = 17;
                            break;
                        }
                        break;
                    case 386691634:
                        if (titleSrn.equals("_2_setting_16_1")) {
                            c = 18;
                            break;
                        }
                        break;
                    case 982305510:
                        if (titleSrn.equals("_2_setting_0_0")) {
                            c = 19;
                            break;
                        }
                        break;
                    case 982306471:
                        if (titleSrn.equals("_2_setting_1_0")) {
                            c = 20;
                            break;
                        }
                        break;
                    case 982306473:
                        if (titleSrn.equals("_2_setting_1_2")) {
                            c = 21;
                            break;
                        }
                        break;
                    case 982307432:
                        if (titleSrn.equals("_2_setting_2_0")) {
                            c = 22;
                            break;
                        }
                        break;
                    case 982307433:
                        if (titleSrn.equals("_2_setting_2_1")) {
                            c = 23;
                            break;
                        }
                        break;
                    case 982307434:
                        if (titleSrn.equals("_2_setting_2_2")) {
                            c = 24;
                            break;
                        }
                        break;
                    case 982307435:
                        if (titleSrn.equals("_2_setting_2_3")) {
                            c = 25;
                            break;
                        }
                        break;
                    case 982308394:
                        if (titleSrn.equals("_2_setting_3_1")) {
                            c = 26;
                            break;
                        }
                        break;
                    case 982308395:
                        if (titleSrn.equals("_2_setting_3_2")) {
                            c = 27;
                            break;
                        }
                        break;
                    case 982308396:
                        if (titleSrn.equals("_2_setting_3_3")) {
                            c = 28;
                            break;
                        }
                        break;
                    case 982308397:
                        if (titleSrn.equals("_2_setting_3_4")) {
                            c = 29;
                            break;
                        }
                        break;
                    case 982308398:
                        if (titleSrn.equals("_2_setting_3_5")) {
                            c = 30;
                            break;
                        }
                        break;
                    case 982308399:
                        if (titleSrn.equals("_2_setting_3_6")) {
                            c = 31;
                            break;
                        }
                        break;
                    case 982310316:
                        if (titleSrn.equals("_2_setting_5_1")) {
                            c = ' ';
                            break;
                        }
                        break;
                    case 982311276:
                        if (titleSrn.equals("_2_setting_6_0")) {
                            c = '!';
                            break;
                        }
                        break;
                    case 982312240:
                        if (titleSrn.equals("_2_setting_7_3")) {
                            c = Typography.quote;
                            break;
                        }
                        break;
                    case 982314160:
                        if (titleSrn.equals("_2_setting_9_1")) {
                            c = '#';
                            break;
                        }
                        break;
                    case 982314161:
                        if (titleSrn.equals("_2_setting_9_2")) {
                            c = Typography.dollar;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        switch (i3) {
                            case 0:
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 0});
                                break;
                            case 1:
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 1});
                                break;
                            case 2:
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 2});
                                break;
                            case 3:
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 3});
                                break;
                            case 4:
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 4});
                                break;
                            case 5:
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 5});
                                break;
                            case 6:
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 6});
                                break;
                            case 7:
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 7});
                                break;
                            case 8:
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 8});
                                break;
                            case 9:
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 9});
                                break;
                            case 10:
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 10});
                                break;
                            case 11:
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 11});
                                break;
                            case 12:
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED});
                                break;
                            case 13:
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 16});
                                break;
                            case 14:
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 17});
                                break;
                            case 15:
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 18});
                                break;
                            case 16:
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 22});
                                break;
                            case 17:
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 23});
                                break;
                            case 18:
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 29});
                                break;
                            case 19:
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 30});
                                break;
                        }
                    case 1:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -112, (byte) i3});
                        break;
                    case 2:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -111, (byte) i3});
                        break;
                    case 3:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -110, (byte) i3});
                        break;
                    case 4:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -109, (byte) i3});
                        break;
                    case 5:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -108, (byte) i3});
                        break;
                    case 6:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -107, (byte) i3});
                        break;
                    case 7:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -48, (byte) i3});
                        break;
                    case '\b':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -46, (byte) i3});
                        break;
                    case '\t':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -47, (byte) i3});
                        break;
                    case '\n':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -43, (byte) i3});
                        break;
                    case 11:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -45, (byte) i3});
                        break;
                    case '\f':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -42, (byte) i3});
                        break;
                    case '\r':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -41, (byte) i3});
                        break;
                    case 14:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -40, (byte) i3});
                        break;
                    case 15:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -39, (byte) i3});
                        break;
                    case 16:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -35, (byte) i3});
                        break;
                    case 17:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -87, (byte) i3});
                        break;
                    case 18:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 70, (byte) i3});
                        break;
                    case 19:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 16, (byte) i3});
                        break;
                    case 20:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 32, (byte) i3});
                        break;
                    case 21:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 35, (byte) i3});
                        break;
                    case 22:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 48, (byte) i3});
                        break;
                    case 23:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 49, (byte) i3});
                        break;
                    case 24:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 57, (byte) i3});
                        break;
                    case 25:
                    case 26:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 50, (byte) i3});
                        break;
                    case 27:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 51, (byte) i3});
                        break;
                    case 28:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 52, (byte) i3});
                        break;
                    case 29:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 53, (byte) i3});
                        break;
                    case 30:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 55, (byte) (i3 + 1)});
                        break;
                    case 31:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 56, (byte) i3});
                        break;
                    case ' ':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 80, (byte) i3});
                        break;
                    case '!':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 86, (byte) i3});
                        break;
                    case '\"':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 94, (byte) i3});
                        break;
                    case '#':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 112, (byte) i3});
                        break;
                    case '$':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 113, (byte) i3});
                        break;
                }
            }
        });
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._2.UiMgr.4
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public void onClickItem(int i, int i2, int i3) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "_2_setting_16_2":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 71, (byte) i3});
                        break;
                    case "_2_setting_16_3":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 72, (byte) i3});
                        break;
                    case "_2_setting_16_4":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 73, (byte) i3});
                        break;
                    case "_2_setting_1_1":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 33, (byte) i3});
                        break;
                    case "_2_setting_4_3":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 65, (byte) i3});
                        break;
                    case "_2_setting_4_4":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 66, (byte) i3});
                        break;
                    case "_2_setting_4_5":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 67, (byte) i3});
                        break;
                    case "_2_setting_4_6":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 68, (byte) i3});
                        break;
                    case "_2_setting_5_5":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 83, (byte) (i3 * 10)});
                        break;
                    case "_2_setting_5_6":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 84, (byte) (i3 * 5)});
                        break;
                    case "_2_setting_5_7":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 85, (byte) (i3 * 5)});
                        break;
                    case "_2_setting_6_1":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 87, (byte) (i3 * 10)});
                        break;
                    case "_2_setting_6_2":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 88, (byte) (i3 * 10)});
                        break;
                    case "_2_setting_6_3":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 95, (byte) i3});
                        break;
                    case "_2_setting_7_1":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 91, (byte) (i3 * 10)});
                        break;
                    case "_2_setting_7_2":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 92, (byte) (i3 * 10)});
                        break;
                    case "_2_setting_7_4":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 93, (byte) (i3 * 10)});
                        break;
                }
            }
        });
        settingUiSet.setOnSettingItemSwitchListener(new OnSettingItemSwitchListener() { // from class: com.hzbhd.canbus.car._2.UiMgr.5
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener
            public void onSwitch(int i, int i2, int i3) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "_326_radar_info_1":
                        UiMgr uiMgr = UiMgr.this;
                        uiMgr.getMsgMgr(uiMgr.mContext).updateSettings(UiMgr.this.mContext, "FRONT_RADAR_KEY", i, i2, i3);
                        break;
                    case "_2_setting_10_10":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -119, (byte) i3});
                        break;
                    case "_2_setting_10_1":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, Byte.MIN_VALUE, (byte) i3});
                        break;
                    case "_2_setting_10_2":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -127, (byte) i3});
                        break;
                    case "_2_setting_10_3":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -126, (byte) i3});
                        break;
                    case "_2_setting_10_4":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -125, (byte) i3});
                        break;
                    case "_2_setting_10_5":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -124, (byte) i3});
                        break;
                    case "_2_setting_10_6":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -123, (byte) i3});
                        break;
                    case "_2_setting_10_7":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -122, (byte) i3});
                        break;
                    case "_2_setting_10_8":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -121, (byte) i3});
                        break;
                    case "_2_setting_10_9":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -120, (byte) i3});
                        break;
                    case "_2_setting_13_5":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -38, (byte) i3});
                        break;
                    case "_2_setting_13_6":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -37, (byte) i3});
                        break;
                    case "_2_setting_13_7":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -36, (byte) i3});
                        break;
                    case "_2_setting_14_1":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -54, (byte) i3});
                        break;
                    case "_2_setting_15_2":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -88, (byte) i3});
                        break;
                    case "_2_setting_15_3":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -89, (byte) i3});
                        break;
                    case "_2_setting_15_4":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -67, (byte) i3});
                        break;
                    case "_2_setting_4_1":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 64, (byte) i3});
                        break;
                    case "_2_setting_4_2":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 69, (byte) i3});
                        break;
                    case "_2_setting_5_2":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 81, (byte) i3});
                        break;
                    case "_2_setting_5_3":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 82, (byte) i3});
                        break;
                    case "_2_setting_5_4":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -56, (byte) i3});
                        break;
                    case "_2_setting_6_4":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 90, (byte) i3});
                        break;
                    case "_2_setting_6_5":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 89, (byte) i3});
                        break;
                    case "_2_setting_8_1":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 96, (byte) i3});
                        break;
                    case "_2_setting_8_2":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 97, (byte) i3});
                        break;
                    case "_2_setting_8_3":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 98, (byte) i3});
                        break;
                    case "_2_setting_8_4":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 99, (byte) i3});
                        break;
                    case "_2_setting_8_5":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 100, (byte) i3});
                        break;
                    case "_2_setting_9_3":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 114, (byte) i3});
                        break;
                    case "_2_setting_9_4":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 115, (byte) i3});
                        break;
                    case "_2_setting_9_5":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 116, (byte) i3});
                        break;
                    case "_2_setting_9_6":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -55, (byte) i3});
                        break;
                }
            }
        });
        getParkPageUiSet(this.mContext).setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._2.UiMgr.6
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public void onClickItem(int i) {
                if (i == 1) {
                    if (SharePreUtil.getBoolValue(UiMgr.this.mContext, "_2_park_voice", false)) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -85, 0});
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -85, 1});
                    }
                }
            }
        });
        AirPageUiSet airUiSet = getAirUiSet(context);
        airUiSet.getRearArea().setSetWindSpeedViewOnClickListener(this.mOnAirWindSpeedUpDownClickListenerRear);
        airUiSet.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerRearTop, null, null, null});
        airUiSet.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{null, this.mOnUpDownClickListenerRear, null});
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFrontLeft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.mOnAirWindSpeedUpDownClickListenerFront);
        airUiSet.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.mFrontLeftSeatHeatListener, this.mFrontRightSeatHeatListener, null, null});
        airUiSet.getFrontArea().setOnAirPageStatusListener(new OnAirPageStatusListener() { // from class: com.hzbhd.canbus.car._2.UiMgr.7
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener
            public void onStatusChange(int i) {
                UiMgr.this.activeRequest(33, 0);
            }
        });
        getDriverDataPageUiSet(this.mContext).setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener() { // from class: com.hzbhd.canbus.car._2.UiMgr.8
            @Override // com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener
            public void onStatusChange(int i) {
                UiMgr.this.activeRequest(80, 0);
                UiMgr.this.activeRequest(22, 0);
            }
        });
        getTireUiSet(this.mContext).setOnTirePageStatusListener(new OnTirePageStatusListener() { // from class: com.hzbhd.canbus.car._2.UiMgr.9
            @Override // com.hzbhd.canbus.interfaces.OnTirePageStatusListener
            public void onStatusChange(int i) {
                UiMgr.this.activeRequest(101, 0);
                UiMgr.this.activeRequest(102, 0);
            }
        });
        getWarningPageUiSet(this.mContext).setOnWarningStatusChangeListener(new OnWarningStatusChangeListener() { // from class: com.hzbhd.canbus.car._2.UiMgr.10
            @Override // com.hzbhd.canbus.interfaces.OnWarningStatusChangeListener
            public void onStatusChange(int i) {
                UiMgr.this.activeRequest(96, 0);
                UiMgr.this.activeRequest(97, 0);
                UiMgr.this.activeRequest(98, 0);
            }
        });
    }

    public void activeRequest(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -112, (byte) i, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }

    protected int getSettingLeftIndexes(Context context, String str) {
        List<SettingPageUiSet.ListBean> list = getPageUiSet(context).getSettingPageUiSet().getList();
        Iterator<SettingPageUiSet.ListBean> it = list.iterator();
        for (int i = 0; i < list.size(); i++) {
            if (str.equals(it.next().getTitleSrn())) {
                return i;
            }
        }
        return 404;
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
}
