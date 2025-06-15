package com.hzbhd.canbus.car._370;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDevicePageStatusListener;
import com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.Iterator;
import java.util.List;
import kotlin.text.Typography;


public class UiMgr extends AbstractUiMgr {
    private AirPageUiSet airPageUiSet;
    private Context mContext;
    private MsgMgr msgMgr;
    private OriginalCarDevicePageUiSet originalCarDevicePageUiSet;
    private SettingPageUiSet settingPageUiSet;
    OnSettingItemSelectListener onSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._370.UiMgr.5
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            String titleSrn = UiMgr.this.settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
            titleSrn.hashCode();
            char c = 65535;
            switch (titleSrn.hashCode()) {
                case -2023122657:
                    if (titleSrn.equals("_370_Engine_Off_Light_delay")) {
                        c = 0;
                        break;
                    }
                    break;
                case -1802151965:
                    if (titleSrn.equals("_112_rear_mirror_dimmer")) {
                        c = 1;
                        break;
                    }
                    break;
                case -1791231156:
                    if (titleSrn.equals("_370_Air_Pressure")) {
                        c = 2;
                        break;
                    }
                    break;
                case -1710161959:
                    if (titleSrn.equals("hiworld_jeep_123_0x62_data1_4")) {
                        c = 3;
                        break;
                    }
                    break;
                case -1710160041:
                    if (titleSrn.equals("hiworld_jeep_123_0x62_data3_0")) {
                        c = 4;
                        break;
                    }
                    break;
                case -1710160040:
                    if (titleSrn.equals("hiworld_jeep_123_0x62_data3_1")) {
                        c = 5;
                        break;
                    }
                    break;
                case -1710160039:
                    if (titleSrn.equals("hiworld_jeep_123_0x62_data3_2")) {
                        c = 6;
                        break;
                    }
                    break;
                case -1710160038:
                    if (titleSrn.equals("hiworld_jeep_123_0x62_data3_3")) {
                        c = 7;
                        break;
                    }
                    break;
                case -1710160037:
                    if (titleSrn.equals("hiworld_jeep_123_0x62_data3_4")) {
                        c = '\b';
                        break;
                    }
                    break;
                case -1475383431:
                    if (titleSrn.equals("hiworld_jeep_123_0x62_data2_10")) {
                        c = '\t';
                        break;
                    }
                    break;
                case -1475383367:
                    if (titleSrn.equals("hiworld_jeep_123_0x62_data2_32")) {
                        c = '\n';
                        break;
                    }
                    break;
                case -1475383239:
                    if (titleSrn.equals("hiworld_jeep_123_0x62_data2_76")) {
                        c = 11;
                        break;
                    }
                    break;
                case -1319415810:
                    if (titleSrn.equals("_370_Automatic_parking_brake")) {
                        c = '\f';
                        break;
                    }
                    break;
                case -1082386052:
                    if (titleSrn.equals("hiworld_jeep_123_0x60_data1_65")) {
                        c = '\r';
                        break;
                    }
                    break;
                case -984370334:
                    if (titleSrn.equals("_370_Speed_adjustment_volume")) {
                        c = 14;
                        break;
                    }
                    break;
                case -280006876:
                    if (titleSrn.equals("_370_Fuel_consumption")) {
                        c = 15;
                        break;
                    }
                    break;
                case -187827060:
                    if (titleSrn.equals("_370_Measure")) {
                        c = 16;
                        break;
                    }
                    break;
                case -63588360:
                    if (titleSrn.equals("_370_Mileage")) {
                        c = 17;
                        break;
                    }
                    break;
                case 102584022:
                    if (titleSrn.equals("language_setup")) {
                        c = 18;
                        break;
                    }
                    break;
                case 234755008:
                    if (titleSrn.equals("_370_Surround_Sound")) {
                        c = 19;
                        break;
                    }
                    break;
                case 591812763:
                    if (titleSrn.equals("_370_Remote_Start")) {
                        c = 20;
                        break;
                    }
                    break;
                case 931921358:
                    if (titleSrn.equals("_370_Engine_Off_Power_delay")) {
                        c = 21;
                        break;
                    }
                    break;
                case 941738070:
                    if (titleSrn.equals("_370_Start_Open_Heat")) {
                        c = 22;
                        break;
                    }
                    break;
                case 947163282:
                    if (titleSrn.equals("compass_direction")) {
                        c = 23;
                        break;
                    }
                    break;
                case 1026194358:
                    if (titleSrn.equals("_370_Remote_Start_Reminder")) {
                        c = 24;
                        break;
                    }
                    break;
                case 1170379802:
                    if (titleSrn.equals("hiworld_jeep_123_0x43_data3_0")) {
                        c = 25;
                        break;
                    }
                    break;
                case 1170379803:
                    if (titleSrn.equals("hiworld_jeep_123_0x43_data3_1")) {
                        c = 26;
                        break;
                    }
                    break;
                case 1170380765:
                    if (titleSrn.equals("hiworld_jeep_123_0x43_data4_2")) {
                        c = 27;
                        break;
                    }
                    break;
                case 1170380766:
                    if (titleSrn.equals("hiworld_jeep_123_0x43_data4_3")) {
                        c = 28;
                        break;
                    }
                    break;
                case 1170380768:
                    if (titleSrn.equals("hiworld_jeep_123_0x43_data4_5")) {
                        c = 29;
                        break;
                    }
                    break;
                case 1170380769:
                    if (titleSrn.equals("hiworld_jeep_123_0x43_data4_6")) {
                        c = 30;
                        break;
                    }
                    break;
                case 1170381728:
                    if (titleSrn.equals("hiworld_jeep_123_0x43_data5_4")) {
                        c = 31;
                        break;
                    }
                    break;
                case 1508681806:
                    if (titleSrn.equals("hiworld_jeep_123_0x62_data3_4_2")) {
                        c = ' ';
                        break;
                    }
                    break;
                case 1904746963:
                    if (titleSrn.equals("hiworld_jeep_123_0x60_data1_0")) {
                        c = '!';
                        break;
                    }
                    break;
                case 1904746964:
                    if (titleSrn.equals("hiworld_jeep_123_0x60_data1_1")) {
                        c = Typography.quote;
                        break;
                    }
                    break;
                case 1904746965:
                    if (titleSrn.equals("hiworld_jeep_123_0x60_data1_2")) {
                        c = '#';
                        break;
                    }
                    break;
                case 1904746966:
                    if (titleSrn.equals("hiworld_jeep_123_0x60_data1_3")) {
                        c = '$';
                        break;
                    }
                    break;
                case 1904746967:
                    if (titleSrn.equals("hiworld_jeep_123_0x60_data1_4")) {
                        c = '%';
                        break;
                    }
                    break;
                case 1918511028:
                    if (titleSrn.equals("_370_Driver_Seat_Convenient")) {
                        c = '&';
                        break;
                    }
                    break;
                case 1922035637:
                    if (titleSrn.equals("hiworld_jeep_123_0x43_data3_32")) {
                        c = '\'';
                        break;
                    }
                    break;
                case 1922035701:
                    if (titleSrn.equals("hiworld_jeep_123_0x43_data3_54")) {
                        c = '(';
                        break;
                    }
                    break;
                case 1922035765:
                    if (titleSrn.equals("hiworld_jeep_123_0x43_data3_76")) {
                        c = ')';
                        break;
                    }
                    break;
                case 1922065364:
                    if (titleSrn.equals("hiworld_jeep_123_0x43_data4_10")) {
                        c = '*';
                        break;
                    }
                    break;
                case 1922095155:
                    if (titleSrn.equals("hiworld_jeep_123_0x43_data5_10")) {
                        c = '+';
                        break;
                    }
                    break;
                case 1922095219:
                    if (titleSrn.equals("hiworld_jeep_123_0x43_data5_32")) {
                        c = ',';
                        break;
                    }
                    break;
                case 2072595165:
                    if (titleSrn.equals("_370_Temp_Unit")) {
                        c = '-';
                        break;
                    }
                    break;
                case 2134500875:
                    if (titleSrn.equals("_370_Remote_unlock")) {
                        c = '.';
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 64, (byte) UiMgr.this.ResolveDelay(i3)});
                    break;
                case 1:
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 40, (byte) i3});
                    break;
                case 2:
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 7, (byte) i3});
                    break;
                case 3:
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 37, (byte) i3});
                    break;
                case 4:
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 38, (byte) i3});
                    break;
                case 5:
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 39, (byte) i3});
                    break;
                case 6:
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 42, (byte) i3});
                    break;
                case 7:
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 36, (byte) i3});
                    break;
                case '\b':
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 35, (byte) i3});
                    break;
                case '\t':
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 32, (byte) UiMgr.this.ResolveDelay(i3)});
                    break;
                case '\n':
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 33, (byte) UiMgr.this.ResolveDelay(i3)});
                    break;
                case 11:
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 41, (byte) (i3 + 1)});
                    break;
                case '\f':
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, -63, (byte) i3});
                    break;
                case '\r':
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 56, (byte) i3});
                    break;
                case 14:
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, -48, (byte) i3});
                    break;
                case 15:
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 5, (byte) i3});
                    break;
                case 16:
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, (byte) i3});
                    break;
                case 17:
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, (byte) i3});
                    break;
                case 18:
                    if (i3 != 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 10});
                        break;
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 1});
                        break;
                    }
                case 19:
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, -47, (byte) i3});
                    break;
                case 20:
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 59, (byte) i3});
                    break;
                case 21:
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 65, (byte) UiMgr.this.ResolveDelay2(i3)});
                    break;
                case 22:
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, -112, (byte) i3});
                    break;
                case 23:
                    CanbusMsgSender.sendMsg(new byte[]{22, -55, (byte) UiMgr.this.Resolvecompass(i3)});
                    break;
                case 24:
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, -111, (byte) i3});
                    break;
                case 25:
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, -89, (byte) i3});
                    break;
                case 26:
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, -88, (byte) i3});
                    break;
                case 27:
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, -91, (byte) i3});
                    break;
                case 28:
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, -92, (byte) i3});
                    break;
                case 29:
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, (byte) i3});
                    break;
                case 30:
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, -90, (byte) i3});
                    break;
                case 31:
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte) i3});
                    break;
                case ' ':
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 50, (byte) i3});
                    break;
                case '!':
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 48, (byte) i3});
                    break;
                case '\"':
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 49, (byte) i3});
                    break;
                case '#':
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 52, (byte) i3});
                    break;
                case '$':
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 54, (byte) i3});
                    break;
                case '%':
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 55, (byte) i3});
                    break;
                case '&':
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, (byte) i3});
                    break;
                case '\'':
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, -87, (byte) i3});
                    break;
                case '(':
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, -86, (byte) i3});
                    break;
                case ')':
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, -96, (byte) i3});
                    break;
                case '*':
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, -85, (byte) i3});
                    break;
                case '+':
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, (byte) i3});
                    break;
                case ',':
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, -94, (byte) i3});
                    break;
                case '-':
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, (byte) i3});
                    break;
                case '.':
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 60, (byte) i3});
                    break;
            }
        }
    };
    OnSettingItemSeekbarSelectListener onSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._370.UiMgr.6
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
        public void onClickItem(int i, int i2, int i3) {
            String titleSrn = UiMgr.this.settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
            titleSrn.hashCode();
            if (titleSrn.equals("hiworld_jeep_123_0x62_data3_765")) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, 43, (byte) i3});
            } else if (titleSrn.equals("compass_deviation")) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -80, (byte) i3});
            }
        }
    };
    OnAmplifierPositionListener onAmplifierPositionListener = new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._370.UiMgr.7
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
        public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
            int i2 = AnonymousClass19.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[amplifierPosition.ordinal()];
            if (i2 == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, (byte) ((-i) + 10)});
                Log.d("lai", i + "l1");
            } else {
                if (i2 != 2) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 4, (byte) (i + 10)});
                Log.d("lai", i + "l2");
            }
        }
    };
    OnAmplifierSeekBarListener onAmplifierSeekBarListener = new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._370.UiMgr.8
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
        public void progress(AmplifierActivity.AmplifierBand amplifierBand, int i) {
            int i2 = AnonymousClass19.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[amplifierBand.ordinal()];
            if (i2 == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 2, (byte) i});
                return;
            }
            if (i2 == 2) {
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 5, (byte) (i + 1)});
            } else if (i2 == 3) {
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 6, (byte) (i + 1)});
            } else {
                if (i2 != 4) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 7, (byte) (i + 1)});
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._370.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirData(16);
            } else if (i == 1) {
                UiMgr.this.sendAirData(1);
            } else {
                if (i != 2) {
                    return;
                }
                UiMgr.this.sendAirData(2);
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontleft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._370.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            UiMgr.this.sendAirData(12);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._370.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            UiMgr.this.sendAirData(14);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._370.UiMgr.12
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirData(15);
            } else if (i == 2) {
                UiMgr.this.sendAirData(3);
            } else {
                if (i != 3) {
                    return;
                }
                UiMgr.this.sendAirData(13);
            }
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._370.UiMgr.13
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirData(4);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirData(5);
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._370.UiMgr.14
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirData(20);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirData(21);
        }
    };
    OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListenerFront = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._370.UiMgr.15
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            UiMgr.this.sendAirData(7);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            UiMgr.this.sendAirData(6);
        }
    };
    int a = 0;
    OnAirSeatClickListener onAirSeatClickListener = new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._370.UiMgr.16
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onLeftSeatClick() {
            if (UiMgr.this.a == 0) {
                UiMgr.this.sendAirData(8);
                UiMgr.this.a = 1;
                return;
            }
            if (UiMgr.this.a == 1) {
                UiMgr.this.sendAirData(9);
                UiMgr.this.a = 2;
            } else if (UiMgr.this.a == 2) {
                UiMgr.this.sendAirData(10);
                UiMgr.this.a = 3;
            } else if (UiMgr.this.a == 3) {
                UiMgr.this.sendAirData(11);
                UiMgr.this.a = 0;
            }
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onRightSeatClick() {
            if (UiMgr.this.a == 0) {
                UiMgr.this.sendAirData(8);
                UiMgr.this.a = 1;
                return;
            }
            if (UiMgr.this.a == 1) {
                UiMgr.this.sendAirData(9);
                UiMgr.this.a = 2;
            } else if (UiMgr.this.a == 2) {
                UiMgr.this.sendAirData(10);
                UiMgr.this.a = 3;
            } else if (UiMgr.this.a == 3) {
                UiMgr.this.sendAirData(11);
                UiMgr.this.a = 0;
            }
        }
    };
    OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatFrontLeft = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._370.UiMgr.17
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr.this.sendAirData(17);
        }
    };
    OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatFrontRight = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._370.UiMgr.18
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr.this.sendAirData(18);
        }
    };
    private int EachID = getEachId();
    private int DifferentID = getCurrentCarId();

    /* JADX INFO: Access modifiers changed from: private */
    public int ResolveDelay(int i) {
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            return 30;
        }
        return i == 2 ? 60 : 90;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int ResolveDelay2(int i) {
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            return 3;
        }
        return i == 2 ? 20 : 40;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int Resolvecompass(int i) {
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            return 8;
        }
        if (i == 2) {
            return 16;
        }
        if (i == 3) {
            return 24;
        }
        if (i == 4) {
            return 32;
        }
        if (i == 5) {
            return 40;
        }
        return i == 6 ? 48 : 56;
    }

    private int swapVal(int i) {
        return i == 0 ? 2 : 1;
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        if (this.DifferentID == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -30, 0, 1});
        }
        if (this.DifferentID == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, -30, 0, 0});
        }
        if (this.EachID == 2) {
            removeMainPrjBtnByName(this.mContext, "air");
            removeMainPrjBtnByName(this.mContext, MainAction.AMPLIFIER);
            removeSettingLeftItemByNameList(this.mContext, new String[]{"_370_Sound_Settings"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_0x60_data1_1"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_0x60_data1_2"});
        }
        if (this.EachID != 3) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_0x62_data3_765"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_370_Remote_Start_Reminder"});
        }
        if (this.EachID != 2) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_0x62_data3_2"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_370_Remote_Start"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_370_Remote_unlock"});
        }
        if (this.EachID != 1) {
            removeMainPrjBtnByName(this.mContext, MainAction.ORIGINAL_CAR_DEVICE);
            removeDriveDateItemForTitles(this.mContext, new String[]{"engine_speed"});
            removeDriveDateItemForTitles(this.mContext, new String[]{"a_current_speed"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_0x62_data2_32"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_0x62_data3_0"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_0x62_data3_1"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_112_rear_mirror_dimmer"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_0x60_data1_4"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_370_Engine_Off_Light_delay"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_370_Engine_Off_Power_delay"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_370_Driver_Seat_Convenient"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_370_Start_Open_Heat"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_0x43_data5_10"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_0x43_data5_4"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_0x43_data4_2"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_0x43_data4_6"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_0x43_data3_1"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_0x43_data3_0"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_0x43_data3_32"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_0x43_data3_54"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_0x43_data4_10"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_0x43_data4_5"});
            removeSettingLeftItemByNameList(this.mContext, new String[]{"compass"});
        }
        if (this.EachID == 1) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_370_Fuel_consumption"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_370_Mileage"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_370_Temp_Unit"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_370_Air_Pressure"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_0x62_data1_4"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_0x62_data2_76"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_0x60_data1_65"});
        }
    }

    public UiMgr(Context context) {
        this.mContext = context;
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        this.settingPageUiSet = settingUiSet;
        settingUiSet.setOnSettingItemSelectListener(this.onSettingItemSelectListener);
        this.settingPageUiSet.setOnSettingItemSeekbarSelectListener(this.onSettingItemSeekbarSelectListener);
        this.settingPageUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._370.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
            public void onConfirmClick(int i, int i2) {
                String titleSrn = UiMgr.this.settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                if (titleSrn.equals("compass_run_calibration")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, -79, 1});
                } else if (titleSrn.equals("hiworld_jeep_123_0x43_data5_5")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, -64, 1});
                }
            }
        });
        GeneralDoorData.isShowHandBrake = true;
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        amplifierPageUiSet.setOnAmplifierPositionListener(this.onAmplifierPositionListener);
        amplifierPageUiSet.setOnAmplifierSeekBarListener(this.onAmplifierSeekBarListener);
        AirPageUiSet airUiSet = getAirUiSet(context);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFrontleft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.onAirWindSpeedUpDownClickListenerFront);
        airUiSet.getFrontArea().setOnAirSeatClickListener(this.onAirSeatClickListener);
        airUiSet.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.mOnMinPlusClickListenerHeatFrontLeft, this.mOnMinPlusClickListenerHeatFrontRight});
        OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(context);
        this.originalCarDevicePageUiSet = originalCarDevicePageUiSet;
        originalCarDevicePageUiSet.setOnOriginalCarDevicePageStatusListener(new OnOriginalCarDevicePageStatusListener() { // from class: com.hzbhd.canbus.car._370.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnOriginalCarDevicePageStatusListener
            public void onStatusChange(int i) {
            }
        });
        this.originalCarDevicePageUiSet.setOnOriginalTopBtnClickListeners(new OnOriginalTopBtnClickListener() { // from class: com.hzbhd.canbus.car._370.UiMgr.3
            @Override // com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener
            public void onClickTopBtnItem(int i) {
                if (i == 0) {
                    UiMgr uiMgr = UiMgr.this;
                    if (uiMgr.getMsgMgr(uiMgr.mContext).rpt == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -56, 15});
                        return;
                    }
                    UiMgr uiMgr2 = UiMgr.this;
                    if (uiMgr2.getMsgMgr(uiMgr2.mContext).rpt == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -56, 14});
                        return;
                    }
                    return;
                }
                if (i != 1) {
                    return;
                }
                UiMgr uiMgr3 = UiMgr.this;
                if (uiMgr3.getMsgMgr(uiMgr3.mContext).rdm == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -56, 10});
                    return;
                }
                UiMgr uiMgr4 = UiMgr.this;
                if (uiMgr4.getMsgMgr(uiMgr4.mContext).rdm == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -56, 9});
                }
            }
        });
        this.originalCarDevicePageUiSet.setOnOriginalBottomBtnClickListeners(new OnOriginalBottomBtnClickListener() { // from class: com.hzbhd.canbus.car._370.UiMgr.4
            @Override // com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener
            public void onClickBottomBtnItem(int i) {
                if (i == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -56, 4});
                    return;
                }
                if (i == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -56, 2});
                } else if (i == 2) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -56, 1});
                } else {
                    if (i != 3) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -56, 3});
                }
            }
        });
    }

    /* renamed from: com.hzbhd.canbus.car._370.UiMgr$19, reason: invalid class name */
    static /* synthetic */ class AnonymousClass19 {
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
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.TREBLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.MIDDLE.ordinal()] = 4;
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
    public void sendAirData(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -126, 1, (byte) i, 1});
    }

    private void sendAmplifierData(int i) {
        byte b = (byte) i;
        CanbusMsgSender.sendMsg(new byte[]{22, -124, b, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, -124, b, 0});
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

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
        if (this.msgMgr == null) {
            this.msgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.msgMgr;
    }
}
