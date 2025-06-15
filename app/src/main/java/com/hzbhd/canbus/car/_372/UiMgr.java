package com.hzbhd.canbus.car._372;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AirBtnAction;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.ByteCompanionObject;


public class UiMgr extends AbstractUiMgr {
    public static int accState = 0;
    public static int cruiseSpeedLimit_Data0Bit0 = 0;
    public static int cruiseSpeedLimit_Data0Bit1 = 0;
    public static int cruiseSpeedLimit_Data0Bit2 = 0;
    public static int cruiseSpeedLimit_Data0Bit3 = 0;
    public static int cruiseSpeedLimit_Data0Bit4 = 0;
    public static int cruiseSpeedLimit_Data0Bit5 = 0;
    public static int cruiseSpeedLimit_Data0Bit6 = 0;
    public static int cruiseSpeedLimit_Data0Bit7 = 0;
    public static int cruiseSpeed_Data1 = 0;
    public static int cruiseSpeed_Data2 = 0;
    public static int cruiseSpeed_Data3 = 0;
    public static int cruiseSpeed_Data4 = 0;
    public static int cruiseSpeed_Data5 = 0;
    public static int cruiseSpeed_Data6 = 0;
    public static int delayTime = 10;
    public static int phoneStateData0Bit0;
    public static int phoneStateData0Bit1;
    public static int phoneStateData0Bit2;
    public static int phoneStateData0Bit4;
    public static int phoneStateData0Bit5;
    public static int phoneStateData0Bit6;
    public static int phoneStateData1Bit0;
    public static int phoneStateData1Bit1;
    public static int phoneStateData1Bit2;
    public static int phoneStateData1Bit4;
    public static int phoneStateData2;
    public static int phoneStateData3;
    public static int phoneStateData4;
    public static int speedLimit_Data1;
    public static int speedLimit_Data2;
    public static int speedLimit_Data3;
    public static int speedLimit_Data4;
    public static int speedLimit_Data5;
    public static int speedLimit_Data6;
    public static int speedMemory_data0Bit0;
    public static int speedMemory_data0Bit1;
    public static int speedMemory_data0Bit2;
    public static int speedMemory_data0Bit3;
    public static int speedMemory_data0Bit4;
    public static int speedMemory_data0Bit5;
    public static int speedMemory_data0Bit6;
    public static int speedMemory_data0Bit7;
    public static int speedMemory_data1;
    public static int speedMemory_data2;
    public static int speedMemory_data3;
    public static int speedMemory_data4;
    public static int speedMemory_data5;
    Context mContext;
    MsgMgr mMsgMgr;
    OnSettingItemSelectListener onSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._372.UiMgr.2
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getLeftIndexes(uiMgr.mContext, "_333_setting_carState")) {
                UiMgr uiMgr2 = UiMgr.this;
                if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "_333_setting_carState", "_333_setting_carState_1")) {
                    UiMgr.this.sendCarParamInfo(2, i3);
                }
                UiMgr uiMgr3 = UiMgr.this;
                if (i2 == uiMgr3.getSettingRightIndex(uiMgr3.mContext, "_333_setting_carState", "_333_setting_carState_8")) {
                    UiMgr.this.sendCarParamInfo(1, i3);
                }
                UiMgr uiMgr4 = UiMgr.this;
                if (i2 == uiMgr4.getSettingRightIndex(uiMgr4.mContext, "_333_setting_carState", "_333_setting_carState_9")) {
                    UiMgr.this.sendCarParamInfo(5, i3);
                }
                UiMgr uiMgr5 = UiMgr.this;
                if (i2 == uiMgr5.getSettingRightIndex(uiMgr5.mContext, "_333_setting_carState", "_333_setting_carState_13")) {
                    UiMgr.this.sendCarParamInfo(6, i3);
                }
                UiMgr uiMgr6 = UiMgr.this;
                if (i2 == uiMgr6.getSettingRightIndex(uiMgr6.mContext, "_333_setting_carState", "_333_setting_carState_14")) {
                    UiMgr.this.sendCarParamInfo(7, i3);
                }
                UiMgr uiMgr7 = UiMgr.this;
                if (i2 == uiMgr7.getSettingRightIndex(uiMgr7.mContext, "_333_setting_carState", "_333_setting_carState_15")) {
                    UiMgr.this.sendCarParamInfo(8, i3);
                }
                UiMgr uiMgr8 = UiMgr.this;
                if (i2 == uiMgr8.getSettingRightIndex(uiMgr8.mContext, "_333_setting_carState", "_333_setting_carState_16")) {
                    UiMgr.this.sendCarParamInfo(9, i3 + 1);
                }
                UiMgr uiMgr9 = UiMgr.this;
                if (i2 == uiMgr9.getSettingRightIndex(uiMgr9.mContext, "_333_setting_carState", "_333_setting_carState_18")) {
                    UiMgr.this.sendCarParamInfo(12, i3);
                }
                UiMgr uiMgr10 = UiMgr.this;
                if (i2 == uiMgr10.getSettingRightIndex(uiMgr10.mContext, "_333_setting_carState", "_333_setting_carState_19")) {
                    UiMgr.this.sendCarParamInfo(13, i3);
                }
                UiMgr uiMgr11 = UiMgr.this;
                if (i2 == uiMgr11.getSettingRightIndex(uiMgr11.mContext, "_333_setting_carState", "_333_setting_carState_20")) {
                    UiMgr.this.sendCarParamInfo(14, i3);
                }
                UiMgr uiMgr12 = UiMgr.this;
                if (i2 == uiMgr12.getSettingRightIndex(uiMgr12.mContext, "_333_setting_carState", "_333_setting_carState_21")) {
                    UiMgr.this.sendCarParamInfo(15, i3);
                }
                UiMgr uiMgr13 = UiMgr.this;
                if (i2 == uiMgr13.getSettingRightIndex(uiMgr13.mContext, "_333_setting_carState", "_333_setting_carState_22")) {
                    UiMgr.this.sendCarParamInfo(11, i3);
                }
                UiMgr uiMgr14 = UiMgr.this;
                if (i2 == uiMgr14.getSettingRightIndex(uiMgr14.mContext, "_333_setting_carState", "_333_setting_carState_23")) {
                    UiMgr.this.sendCarParamInfo(10, i3);
                }
            }
            UiMgr uiMgr15 = UiMgr.this;
            if (i == uiMgr15.getLeftIndexes(uiMgr15.mContext, "_333_setting_speed_memory")) {
                switch (i2) {
                    case 1:
                        UiMgr.this.sendSettingsSpeedSwitchInfo(i3);
                        break;
                    case 2:
                        UiMgr.this.sendSettingsSpeedSelectionInfo(1, i3);
                        break;
                    case 3:
                        UiMgr.this.sendSettingsSpeedSelectionInfo(2, i3);
                        break;
                    case 4:
                        UiMgr.this.sendSettingsSpeedSelectionInfo(3, i3);
                        break;
                    case 5:
                        UiMgr.this.sendSettingsSpeedSelectionInfo(4, i3);
                        break;
                    case 6:
                        UiMgr.this.sendSettingsSpeedSelectionInfo(5, i3);
                        break;
                }
            }
            UiMgr uiMgr16 = UiMgr.this;
            if (i == uiMgr16.getLeftIndexes(uiMgr16.mContext, "_333_setting_acc") && i2 == 1) {
                if (i3 == 0) {
                    UiMgr.accState = 0;
                } else {
                    UiMgr.accState = 1;
                }
                UiMgr.this.sendAccInfo();
            }
            UiMgr uiMgr17 = UiMgr.this;
            if (i == uiMgr17.getLeftIndexes(uiMgr17.mContext, "_333_setting_Scheduled_mileage") && i2 == 0) {
                UiMgr.this.ComputerInfoData3Bit7 = i3;
                UiMgr.this.sendComputerInfo();
                UiMgr uiMgr18 = UiMgr.this;
                MsgMgr msgMgr = uiMgr18.getMsgMgr(uiMgr18.mContext);
                Context context = UiMgr.this.mContext;
                UiMgr uiMgr19 = UiMgr.this;
                msgMgr.updateSettings(context, uiMgr19.getLeftIndexes(uiMgr19.mContext, "_333_setting_Scheduled_mileage"), 0, i3);
            }
        }
    };
    OnSettingItemSeekbarSelectListener onSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._372.UiMgr.3
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
        public void onClickItem(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getLeftIndexes(uiMgr.mContext, "_333_setting_carState")) {
                UiMgr uiMgr2 = UiMgr.this;
                if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "_333_setting_carState", "_333_setting_carState_7")) {
                    UiMgr.this.sendCarParamInfo(4, i3);
                }
            }
            UiMgr uiMgr3 = UiMgr.this;
            if (i == uiMgr3.getLeftIndexes(uiMgr3.mContext, "_333_setting_speed_memory")) {
                switch (i2) {
                    case 7:
                        UiMgr.speedMemory_data1 = i3;
                        UiMgr.this.sendMemorySpeedInfo();
                        break;
                    case 8:
                        UiMgr.speedMemory_data2 = i3;
                        UiMgr.this.sendMemorySpeedInfo();
                        break;
                    case 9:
                        UiMgr.speedMemory_data3 = i3;
                        UiMgr.this.sendMemorySpeedInfo();
                        break;
                    case 10:
                        UiMgr.speedMemory_data4 = i3;
                        UiMgr.this.sendMemorySpeedInfo();
                        break;
                    case 11:
                        UiMgr.speedMemory_data5 = i3;
                        UiMgr.this.sendMemorySpeedInfo();
                        break;
                }
            }
            UiMgr uiMgr4 = UiMgr.this;
            if (i == uiMgr4.getLeftIndexes(uiMgr4.mContext, "_333_setting_cruise_speed")) {
                if (i2 == 0) {
                    UiMgr.cruiseSpeedLimit_Data0Bit7 = 0;
                    UiMgr.cruiseSpeedLimit_Data0Bit6 = 1;
                    UiMgr.cruiseSpeedLimit_Data0Bit5 = 0;
                    UiMgr.cruiseSpeedLimit_Data0Bit4 = 1;
                    UiMgr.cruiseSpeed_Data1 = i3;
                    UiMgr.this.sendCruiseSpeedInfo();
                } else if (i2 == 1) {
                    UiMgr.cruiseSpeedLimit_Data0Bit7 = 0;
                    UiMgr.cruiseSpeedLimit_Data0Bit6 = 1;
                    UiMgr.cruiseSpeedLimit_Data0Bit5 = 0;
                    UiMgr.cruiseSpeedLimit_Data0Bit4 = 1;
                    UiMgr.cruiseSpeed_Data2 = i3;
                    UiMgr.this.sendCruiseSpeedInfo();
                } else if (i2 == 2) {
                    UiMgr.cruiseSpeedLimit_Data0Bit7 = 0;
                    UiMgr.cruiseSpeedLimit_Data0Bit6 = 1;
                    UiMgr.cruiseSpeedLimit_Data0Bit5 = 0;
                    UiMgr.cruiseSpeedLimit_Data0Bit4 = 1;
                    UiMgr.cruiseSpeed_Data3 = i3;
                    UiMgr.this.sendCruiseSpeedInfo();
                } else if (i2 == 3) {
                    UiMgr.cruiseSpeedLimit_Data0Bit7 = 0;
                    UiMgr.cruiseSpeedLimit_Data0Bit6 = 1;
                    UiMgr.cruiseSpeedLimit_Data0Bit5 = 0;
                    UiMgr.cruiseSpeedLimit_Data0Bit4 = 1;
                    UiMgr.cruiseSpeed_Data4 = i3;
                    UiMgr.this.sendCruiseSpeedInfo();
                } else if (i2 == 4) {
                    UiMgr.cruiseSpeedLimit_Data0Bit7 = 0;
                    UiMgr.cruiseSpeedLimit_Data0Bit6 = 1;
                    UiMgr.cruiseSpeedLimit_Data0Bit5 = 0;
                    UiMgr.cruiseSpeedLimit_Data0Bit4 = 1;
                    UiMgr.cruiseSpeed_Data5 = i3;
                    UiMgr.this.sendCruiseSpeedInfo();
                } else if (i2 == 5) {
                    UiMgr.cruiseSpeedLimit_Data0Bit7 = 0;
                    UiMgr.cruiseSpeedLimit_Data0Bit6 = 1;
                    UiMgr.cruiseSpeedLimit_Data0Bit5 = 0;
                    UiMgr.cruiseSpeedLimit_Data0Bit4 = 1;
                    UiMgr.cruiseSpeed_Data6 = i3;
                    UiMgr.this.sendCruiseSpeedInfo();
                }
            }
            UiMgr uiMgr5 = UiMgr.this;
            if (i == uiMgr5.getLeftIndexes(uiMgr5.mContext, "_333_setting_speed_limit")) {
                if (i2 == 0) {
                    UiMgr.cruiseSpeedLimit_Data0Bit7 = 1;
                    UiMgr.cruiseSpeedLimit_Data0Bit6 = 0;
                    UiMgr.cruiseSpeedLimit_Data0Bit5 = 0;
                    UiMgr.cruiseSpeedLimit_Data0Bit4 = 1;
                    UiMgr.speedLimit_Data1 = i3;
                    UiMgr.this.sendSpeedLimitInfo();
                } else if (i2 == 1) {
                    UiMgr.cruiseSpeedLimit_Data0Bit7 = 1;
                    UiMgr.cruiseSpeedLimit_Data0Bit6 = 0;
                    UiMgr.cruiseSpeedLimit_Data0Bit5 = 0;
                    UiMgr.cruiseSpeedLimit_Data0Bit4 = 1;
                    UiMgr.speedLimit_Data2 = i3;
                    UiMgr.this.sendSpeedLimitInfo();
                } else if (i2 == 2) {
                    UiMgr.cruiseSpeedLimit_Data0Bit7 = 1;
                    UiMgr.cruiseSpeedLimit_Data0Bit6 = 0;
                    UiMgr.cruiseSpeedLimit_Data0Bit5 = 0;
                    UiMgr.cruiseSpeedLimit_Data0Bit4 = 1;
                    UiMgr.speedLimit_Data3 = i3;
                    UiMgr.this.sendSpeedLimitInfo();
                } else if (i2 == 3) {
                    UiMgr.cruiseSpeedLimit_Data0Bit7 = 1;
                    UiMgr.cruiseSpeedLimit_Data0Bit6 = 0;
                    UiMgr.cruiseSpeedLimit_Data0Bit5 = 0;
                    UiMgr.cruiseSpeedLimit_Data0Bit4 = 1;
                    UiMgr.speedLimit_Data4 = i3;
                    UiMgr.this.sendSpeedLimitInfo();
                } else if (i2 == 4) {
                    UiMgr.cruiseSpeedLimit_Data0Bit7 = 1;
                    UiMgr.cruiseSpeedLimit_Data0Bit6 = 0;
                    UiMgr.cruiseSpeedLimit_Data0Bit5 = 0;
                    UiMgr.cruiseSpeedLimit_Data0Bit4 = 1;
                    UiMgr.speedLimit_Data5 = i3;
                    UiMgr.this.sendSpeedLimitInfo();
                } else if (i2 == 5) {
                    UiMgr.cruiseSpeedLimit_Data0Bit7 = 1;
                    UiMgr.cruiseSpeedLimit_Data0Bit6 = 0;
                    UiMgr.cruiseSpeedLimit_Data0Bit5 = 0;
                    UiMgr.cruiseSpeedLimit_Data0Bit4 = 1;
                    UiMgr.speedLimit_Data6 = i3;
                    UiMgr.this.sendSpeedLimitInfo();
                }
            }
            UiMgr uiMgr6 = UiMgr.this;
            if (i == uiMgr6.getLeftIndexes(uiMgr6.mContext, "_333_setting_acc") && i2 == 2) {
                UiMgr.delayTime = i3;
                UiMgr.this.sendAccInfo();
            }
            UiMgr uiMgr7 = UiMgr.this;
            if (i == uiMgr7.getLeftIndexes(uiMgr7.mContext, "_333_setting_Scheduled_mileage") && i2 == 1) {
                UiMgr uiMgr8 = UiMgr.this;
                uiMgr8.ComputerInfoData4 = uiMgr8.getMsb(i3);
                UiMgr uiMgr9 = UiMgr.this;
                uiMgr9.ComputerInfoData5 = uiMgr9.getLsb(i3);
                UiMgr.this.sendComputerInfo();
                UiMgr uiMgr10 = UiMgr.this;
                MsgMgr msgMgr = uiMgr10.getMsgMgr(uiMgr10.mContext);
                Context context = UiMgr.this.mContext;
                UiMgr uiMgr11 = UiMgr.this;
                msgMgr.updateSettings(context, uiMgr11.getLeftIndexes(uiMgr11.mContext, "_333_setting_Scheduled_mileage"), 1, i3);
            }
        }
    };
    OnConfirmDialogListener onConfirmDialogListener = new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._372.UiMgr.4
        @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
        public void onConfirmClick(int i, int i2) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getLeftIndexes(uiMgr.mContext, "_333_setting_speed_memory") && i2 == 12) {
                UiMgr.speedMemory_data0Bit7 = 1;
                UiMgr.speedMemory_data0Bit6 = 0;
                UiMgr.this.sendMemorySpeedInfo();
            }
            UiMgr uiMgr2 = UiMgr.this;
            if (i == uiMgr2.getLeftIndexes(uiMgr2.mContext, "_333_setting_cruise_speed") && i2 == 6) {
                UiMgr.cruiseSpeedLimit_Data0Bit7 = 0;
                UiMgr.cruiseSpeedLimit_Data0Bit6 = 1;
                UiMgr.cruiseSpeedLimit_Data0Bit5 = 1;
                UiMgr.cruiseSpeedLimit_Data0Bit4 = 0;
                UiMgr.this.sendCruiseSpeedInfo();
            }
            UiMgr uiMgr3 = UiMgr.this;
            if (i == uiMgr3.getLeftIndexes(uiMgr3.mContext, "_333_setting_speed_limit") && i2 == 6) {
                UiMgr.cruiseSpeedLimit_Data0Bit7 = 1;
                UiMgr.cruiseSpeedLimit_Data0Bit6 = 0;
                UiMgr.cruiseSpeedLimit_Data0Bit5 = 1;
                UiMgr.cruiseSpeedLimit_Data0Bit4 = 0;
                UiMgr.this.sendSpeedLimitInfo();
            }
            UiMgr uiMgr4 = UiMgr.this;
            if (i == uiMgr4.getLeftIndexes(uiMgr4.mContext, "_333_drive_DrivingPage_1") && i2 == 3) {
                UiMgr.this.ComputerInfoData3Bit6 = 1;
                UiMgr.this.sendComputerInfo();
            }
            UiMgr uiMgr5 = UiMgr.this;
            if (i == uiMgr5.getLeftIndexes(uiMgr5.mContext, "_333_drive_DrivingPage_2") && i2 == 3) {
                UiMgr.this.ComputerInfoData3Bit5 = 1;
                UiMgr.this.sendComputerInfo();
            }
        }
    };
    OnSettingPageStatusListener onSettingPageStatusListener = new OnSettingPageStatusListener() { // from class: com.hzbhd.canbus.car._372.UiMgr.5
        @Override // com.hzbhd.canbus.interfaces.OnSettingPageStatusListener
        public void onStatusChange(int i) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getLeftIndexes(uiMgr.mContext, "_333_setting_speed_memory")) {
                UiMgr.this.activeRequestInfo(59);
            }
            UiMgr uiMgr2 = UiMgr.this;
            if (i == uiMgr2.getLeftIndexes(uiMgr2.mContext, "_333_setting_cruise_speed")) {
                UiMgr.this.activeRequestInfo(61);
            }
            UiMgr uiMgr3 = UiMgr.this;
            if (i == uiMgr3.getLeftIndexes(uiMgr3.mContext, "_333_setting_speed_limit")) {
                UiMgr.this.activeRequestInfo(61);
            }
            UiMgr uiMgr4 = UiMgr.this;
            if (i == uiMgr4.getLeftIndexes(uiMgr4.mContext, "_333_setting_acc")) {
                UiMgr.this.activeRequestInfo(68);
                UiMgr.this.activeRequestInfo(234);
            }
            UiMgr uiMgr5 = UiMgr.this;
            int leftIndexes = uiMgr5.getLeftIndexes(uiMgr5.mContext, "_333_setting_Scheduled_mileage");
            if (i == leftIndexes) {
                UiMgr uiMgr6 = UiMgr.this;
                uiMgr6.getMsgMgr(uiMgr6.mContext).updateSettings(UiMgr.this.mContext, leftIndexes, 0, SharePreUtil.getIntValue(UiMgr.this.mContext, "EachCanId" + UiMgr.this.getEachId() + "L" + leftIndexes + "R0", 0));
                UiMgr uiMgr7 = UiMgr.this;
                uiMgr7.getMsgMgr(uiMgr7.mContext).updateSettings(UiMgr.this.mContext, leftIndexes, 1, SharePreUtil.getIntValue(UiMgr.this.mContext, "EachCanId" + UiMgr.this.getEachId() + "L" + leftIndexes + "R1", 0));
            }
            UiMgr uiMgr8 = UiMgr.this;
            if (i == uiMgr8.getLeftIndexes(uiMgr8.mContext, "_333_drive_DrivingPage_0")) {
                UiMgr.this.activeRequestInfo(51);
                UiMgr.this.ComputerInfoData3Bit0 = 0;
                UiMgr.this.ComputerInfoData3Bit1 = 0;
                UiMgr.this.ComputerInfoData3Bit2 = 0;
                UiMgr.this.sendComputerInfo();
            }
            UiMgr uiMgr9 = UiMgr.this;
            if (i == uiMgr9.getLeftIndexes(uiMgr9.mContext, "_333_drive_DrivingPage_1")) {
                UiMgr.this.activeRequestInfo(52);
                UiMgr.this.ComputerInfoData3Bit0 = 1;
                UiMgr.this.ComputerInfoData3Bit1 = 0;
                UiMgr.this.ComputerInfoData3Bit2 = 0;
                UiMgr.this.sendComputerInfo();
            }
            UiMgr uiMgr10 = UiMgr.this;
            if (i == uiMgr10.getLeftIndexes(uiMgr10.mContext, "_333_drive_DrivingPage_2")) {
                UiMgr.this.activeRequestInfo(53);
                UiMgr.this.ComputerInfoData3Bit0 = 0;
                UiMgr.this.ComputerInfoData3Bit1 = 1;
                UiMgr.this.ComputerInfoData3Bit2 = 0;
                UiMgr.this.sendComputerInfo();
            }
            UiMgr uiMgr11 = UiMgr.this;
            if (i == uiMgr11.getLeftIndexes(uiMgr11.mContext, "_333_drive_page_1")) {
                UiMgr.this.activeRequestInfo(113);
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._372.UiMgr.6
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 1) {
                if (GeneralAirData.ac) {
                    UiMgr.this.send0xA8AirInfo(2, 0);
                    return;
                } else {
                    UiMgr.this.send0xA8AirInfo(2, 1);
                    return;
                }
            }
            if (i != 3) {
                return;
            }
            if (GeneralAirData.auto) {
                UiMgr.this.send0xA8AirInfo(1, 0);
            } else {
                UiMgr.this.send0xA8AirInfo(1, 1);
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._372.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            if (GeneralAirData.dual) {
                UiMgr.this.send0xA8AirInfo(11, 0);
            } else {
                UiMgr.this.send0xA8AirInfo(11, 1);
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._372.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._372.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 2) {
                if (GeneralAirData.ac_max) {
                    UiMgr.this.send0xA8AirInfo(3, 0);
                    return;
                } else {
                    UiMgr.this.send0xA8AirInfo(3, 1);
                    return;
                }
            }
            if (i != 3) {
                return;
            }
            if (GeneralAirData.auto_wind_lv == 0) {
                UiMgr.this.send0xA8AirInfo(9, 1);
            } else if (GeneralAirData.auto_wind_lv == 1) {
                UiMgr.this.send0xA8AirInfo(9, 2);
            } else if (GeneralAirData.auto_wind_lv == 2) {
                UiMgr.this.send0xA8AirInfo(9, 0);
            }
        }
    };
    OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._372.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.send0xA8AirInfo(4, 1);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.send0xA8AirInfo(4, 2);
        }
    };
    OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._372.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.send0xA8AirInfo(5, 1);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.send0xA8AirInfo(5, 2);
        }
    };
    int windTag = 0;
    OnAirSeatClickListener onAirSeatClickListener = new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._372.UiMgr.12
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onLeftSeatClick() {
            if (UiMgr.this.windTag == 0) {
                UiMgr.this.windTag = 1;
                UiMgr.this.send0xA8AirInfo(6, 1);
                UiMgr.this.send0xA8AirInfo(7, 1);
                UiMgr.this.send0xA8AirInfo(8, 1);
                return;
            }
            UiMgr.this.windTag = 0;
            UiMgr.this.send0xA8AirInfo(6, 0);
            UiMgr.this.send0xA8AirInfo(7, 0);
            UiMgr.this.send0xA8AirInfo(8, 0);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onRightSeatClick() {
            if (UiMgr.this.windTag == 0) {
                UiMgr.this.windTag = 1;
                UiMgr.this.send0xA8AirInfo(6, 1);
                UiMgr.this.send0xA8AirInfo(7, 1);
                UiMgr.this.send0xA8AirInfo(8, 1);
                return;
            }
            UiMgr.this.windTag = 0;
            UiMgr.this.send0xA8AirInfo(6, 0);
            UiMgr.this.send0xA8AirInfo(7, 0);
            UiMgr.this.send0xA8AirInfo(8, 0);
        }
    };
    OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._372.UiMgr.13
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            UiMgr.this.send0xA8AirInfo(10, 2);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            UiMgr.this.send0xA8AirInfo(10, 1);
        }
    };
    OnPanelKeyPositionListener onPanelKeyPositionListener = new OnPanelKeyPositionListener() { // from class: com.hzbhd.canbus.car._372.UiMgr.14
        @Override // com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener
        public void click(int i) {
            switch (i) {
                case 0:
                    UiMgr.this.sendPanelKeyInfo(8);
                    break;
                case 1:
                    UiMgr.this.sendPanelKeyInfo(6);
                    break;
                case 2:
                    UiMgr.this.sendPanelKeyInfo(9);
                    break;
                case 3:
                    UiMgr.this.sendPanelKeyInfo(5);
                    break;
                case 4:
                    UiMgr.this.sendPanelKeyInfo(7);
                    break;
                case 5:
                    UiMgr.this.sendPanelKeyInfo(2);
                    break;
                case 6:
                    UiMgr.this.sendPanelKeyInfo(3);
                    break;
                case 7:
                    UiMgr.this.sendPanelKeyInfo(4);
                    break;
                case 8:
                    UiMgr.this.sendPanelKeyInfo(1);
                    break;
                case 9:
                    UiMgr.this.sendPanelKeyInfo(0);
                    break;
            }
        }
    };
    OnDriveDataPageStatusListener onDriveDataPageStatusListener = new OnDriveDataPageStatusListener() { // from class: com.hzbhd.canbus.car._372.UiMgr.15
        @Override // com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener
        public void onStatusChange(int i) {
            UiMgr.this.activeRequestInfo(57);
            UiMgr.this.activeRequestInfo(62);
            UiMgr.this.activeRequestInfo(60);
        }
    };
    int ComputerInfoData3Bit7 = 0;
    int ComputerInfoData3Bit6 = 0;
    int ComputerInfoData3Bit5 = 0;
    int ComputerInfoData3Bit3 = 0;
    int ComputerInfoData3Bit2 = 0;
    int ComputerInfoData3Bit1 = 0;
    int ComputerInfoData3Bit0 = 0;
    int ComputerInfoData4 = 0;
    int ComputerInfoData5 = 0;
    int eachId = getEachId();

    /* JADX INFO: Access modifiers changed from: private */
    public int getLsb(int i) {
        return ((i & 65535) >> 0) & 255;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getMsb(int i) {
        return ((i & 65535) >> 8) & 255;
    }

    public UiMgr(Context context) {
        this.mContext = context;
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(this.onSettingItemSelectListener);
        settingUiSet.setOnSettingItemSeekbarSelectListener(this.onSettingItemSeekbarSelectListener);
        settingUiSet.setOnSettingConfirmDialogListener(this.onConfirmDialogListener);
        settingUiSet.setOnSettingPageStatusListener(this.onSettingPageStatusListener);
        settingUiSet.setOnSettingItemClickListener(new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._372.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
            public void onClickItem(int i, int i2) {
                UiMgr uiMgr = UiMgr.this;
                if (i == uiMgr.getLeftIndexes(uiMgr.mContext, "_333_setting_carState")) {
                    UiMgr uiMgr2 = UiMgr.this;
                    if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "_333_setting_carState", "_333_setting_carState_25")) {
                        UiMgr.this.sendCarParamInfo(16, 1);
                        UiMgr uiMgr3 = UiMgr.this;
                        uiMgr3.getMsgMgr(uiMgr3.mContext).tireSetting();
                    }
                }
            }
        });
        AirPageUiSet airUiSet = getAirUiSet(this.mContext);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.onAirTemperatureUpDownClickListenerLeft, null, this.onAirTemperatureUpDownClickListenerRight});
        airUiSet.getFrontArea().setOnAirSeatClickListener(this.onAirSeatClickListener);
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.onAirWindSpeedUpDownClickListener);
        getPanelKeyPageUiSet(this.mContext).setOnPanelKeyPositionListener(this.onPanelKeyPositionListener);
        getDriverDataPageUiSet(this.mContext).setOnDriveDataPageStatusListener(this.onDriveDataPageStatusListener);
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        intiUi();
    }

    private void intiUi() {
        if (this.eachId == 14) {
            removeDriveDataPagesByHeadTitles(this.mContext, "_333_drivePage_front_radar_info");
        }
        if (this.eachId != 3) {
            Context context = this.mContext;
            removeSettingRightItem(context, getLeftIndexes(context, "_333_drive_DrivingPage_0"), 3, 4);
        }
        if (this.eachId != 5) {
            getAirUiSet(this.mContext).setHaveRearArea(false);
            removeFrontAirButtonByName(this.mContext, AirBtnAction.REAR);
        }
        if (this.eachId != 4) {
            removeMainPrjBtnByName(this.mContext, MainAction.PANEL_KEY);
        }
        if (this.eachId == 12) {
            CanbusMsgSender.sendMsg(new byte[]{22, -54, 1});
        }
        if (this.eachId == 17) {
            CanbusMsgSender.sendMsg(new byte[]{22, -54, 2});
        }
        int i = this.eachId;
        if (i == 16 || i == 8) {
            return;
        }
        removeSettingLeftItemByNameList(this.mContext, new String[]{"_333_drive_page_1"});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void send0xA8AirInfo(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -118, (byte) i, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendCarParamInfo(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, (byte) i, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMemorySpeedInfo() {
        CanbusMsgSender.sendMsg(new byte[]{22, -120, (byte) getDecimalFrom8Bit(speedMemory_data0Bit7, speedMemory_data0Bit6, speedMemory_data0Bit5, speedMemory_data0Bit4, speedMemory_data0Bit3, speedMemory_data0Bit2, speedMemory_data0Bit1, speedMemory_data0Bit0), (byte) speedMemory_data1, (byte) speedMemory_data2, (byte) speedMemory_data3, (byte) speedMemory_data4, (byte) speedMemory_data5});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSettingsSpeedSwitchInfo(int i) {
        if (i == 0) {
            speedMemory_data0Bit6 = 0;
            sendMemorySpeedInfo();
        } else {
            speedMemory_data0Bit6 = 1;
            sendMemorySpeedInfo();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSettingsSpeedSelectionInfo(int i, int i2) {
        if (i2 == 0) {
            if (i == 1) {
                speedMemory_data0Bit5 = 0;
                sendMemorySpeedInfo();
                return;
            }
            if (i == 2) {
                speedMemory_data0Bit4 = 0;
                sendMemorySpeedInfo();
                return;
            }
            if (i == 3) {
                speedMemory_data0Bit3 = 0;
                sendMemorySpeedInfo();
                return;
            } else if (i == 4) {
                speedMemory_data0Bit2 = 0;
                sendMemorySpeedInfo();
                return;
            } else {
                if (i != 5) {
                    return;
                }
                speedMemory_data0Bit1 = 0;
                sendMemorySpeedInfo();
                return;
            }
        }
        if (i == 1) {
            speedMemory_data0Bit5 = 1;
            sendMemorySpeedInfo();
            return;
        }
        if (i == 2) {
            speedMemory_data0Bit4 = 1;
            sendMemorySpeedInfo();
            return;
        }
        if (i == 3) {
            speedMemory_data0Bit3 = 1;
            sendMemorySpeedInfo();
        } else if (i == 4) {
            speedMemory_data0Bit2 = 1;
            sendMemorySpeedInfo();
        } else {
            if (i != 5) {
                return;
            }
            speedMemory_data0Bit1 = 1;
            sendMemorySpeedInfo();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendCruiseSpeedInfo() {
        CanbusMsgSender.sendMsg(new byte[]{22, -119, (byte) getDecimalFrom8Bit(cruiseSpeedLimit_Data0Bit7, cruiseSpeedLimit_Data0Bit6, cruiseSpeedLimit_Data0Bit5, cruiseSpeedLimit_Data0Bit4, cruiseSpeedLimit_Data0Bit3, cruiseSpeedLimit_Data0Bit2, cruiseSpeedLimit_Data0Bit1, cruiseSpeedLimit_Data0Bit0), (byte) cruiseSpeed_Data1, (byte) cruiseSpeed_Data2, (byte) cruiseSpeed_Data3, (byte) cruiseSpeed_Data4, (byte) cruiseSpeed_Data5, (byte) cruiseSpeed_Data6});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSpeedLimitInfo() {
        CanbusMsgSender.sendMsg(new byte[]{22, -119, (byte) getDecimalFrom8Bit(cruiseSpeedLimit_Data0Bit7, cruiseSpeedLimit_Data0Bit6, cruiseSpeedLimit_Data0Bit5, cruiseSpeedLimit_Data0Bit4, cruiseSpeedLimit_Data0Bit3, cruiseSpeedLimit_Data0Bit2, cruiseSpeedLimit_Data0Bit1, cruiseSpeedLimit_Data0Bit0), (byte) speedLimit_Data1, (byte) speedLimit_Data2, (byte) speedLimit_Data3, (byte) speedLimit_Data4, (byte) speedLimit_Data5, (byte) speedLimit_Data6});
    }

    private void sendParkingInfo(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -113, (byte) i});
    }

    private void sendAirInfo(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -118, (byte) i, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAccInfo() {
        CanbusMsgSender.sendMsg(new byte[]{22, -21, (byte) accState, (byte) delayTime});
    }

    private void sendAmplifierInfo(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte) i, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendComputerInfo() {
        CanbusMsgSender.sendMsg(new byte[]{22, -126, 0, 0, 0, (byte) getDecimalFrom8Bit(this.ComputerInfoData3Bit7, this.ComputerInfoData3Bit6, this.ComputerInfoData3Bit5, 0, this.ComputerInfoData3Bit3, this.ComputerInfoData3Bit2, this.ComputerInfoData3Bit1, this.ComputerInfoData3Bit0), (byte) this.ComputerInfoData4, (byte) this.ComputerInfoData5});
    }

    public void sendSourceInfo(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        CanbusMsgSender.sendMsg(new byte[]{22, -64, (byte) i, (byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6, (byte) i7, (byte) i8});
    }

    public void sendRadioInfo(int i, int i2, int i3, int i4) {
        CanbusMsgSender.sendMsg(new byte[]{22, -62, (byte) i, (byte) i2, (byte) i3, (byte) i4});
    }

    public void sendVolInfo(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -60, (byte) i});
    }

    public void sendID3Info(byte[] bArr) {
        CanbusMsgSender.sendMsg(SplicingByte(new byte[]{22, -53, 1}, bArr));
    }

    private byte[] SplicingByte(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    public void sendID3Info(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20, int i21) {
        CanbusMsgSender.sendMsg(new byte[]{22, -53, (byte) i, (byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6, (byte) i7, (byte) i8, (byte) i9, (byte) i10, (byte) i11, (byte) i12, (byte) i13, (byte) i14, (byte) i15, (byte) i16, (byte) i17, (byte) i18, (byte) i19, (byte) i20, (byte) i21});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendPanelKeyInfo(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -116, (byte) i});
    }

    public void sendPhoneStateInfo() {
        CanbusMsgSender.sendMsg(new byte[]{22, -59, (byte) getDecimalFrom8Bit(0, phoneStateData0Bit6, phoneStateData0Bit5, phoneStateData0Bit4, 0, phoneStateData0Bit2, phoneStateData0Bit1, phoneStateData0Bit0), (byte) getDecimalFrom8Bit(0, 0, 0, phoneStateData1Bit4, 0, phoneStateData1Bit2, phoneStateData1Bit1, phoneStateData1Bit0), (byte) phoneStateData2, (byte) phoneStateData3, (byte) phoneStateData4});
    }

    public static void makeConnection() {
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
    }

    public void activeRequestInfo(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -112, (byte) i});
    }

    private void sendWarningInfo(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -121, (byte) i, -1});
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

    protected int getLeftIndexes(Context context, String str) {
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

    private int getDecimalFrom8Bit(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return Integer.parseInt(i + "" + i2 + "" + i3 + "" + i4 + "" + i5 + "" + i6 + "" + i7 + "" + i8, 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }
}
