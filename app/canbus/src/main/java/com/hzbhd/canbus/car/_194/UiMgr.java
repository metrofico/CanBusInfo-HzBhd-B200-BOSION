package com.hzbhd.canbus.car._194;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.interfaces.OnTirePageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import java.util.Iterator;
import java.util.List;
import nfore.android.bt.res.NfDef;

/* loaded from: classes.dex */
public class UiMgr extends AbstractUiMgr {
    protected static final int CAR_1 = 1;
    protected static final int CAR_10 = 10;
    protected static final int CAR_11 = 11;
    protected static final int CAR_12 = 12;
    protected static final int CAR_13 = 13;
    protected static final int CAR_14 = 14;
    protected static final int CAR_15 = 15;
    protected static final int CAR_16 = 16;
    protected static final int CAR_17 = 17;
    protected static final int CAR_18 = 18;
    protected static final int CAR_19 = 19;
    protected static final int CAR_2 = 2;
    protected static final int CAR_20 = 20;
    protected static final int CAR_21 = 21;
    protected static final int CAR_22 = 22;
    protected static final int CAR_3 = 3;
    protected static final int CAR_4 = 4;
    protected static final int CAR_5 = 5;
    protected static final int CAR_6 = 6;
    protected static final int CAR_7 = 7;
    protected static final int CAR_8 = 8;
    protected static final int CAR_9 = 9;
    private static int mFrontWindMode;
    private Context mContext;
    MsgMgr mMsgMgr;
    private int WindStatus = 1;
    OnPanoramicItemClickListener onPanoramicItemClickListener = new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._194.UiMgr.7
        @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
        public void onClickItem(int i) {
            switch (i) {
                case 0:
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, 4});
                    break;
                case 1:
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, 5});
                    break;
                case 2:
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, 6});
                    break;
                case 3:
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, 7});
                    break;
                case 4:
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, 8});
                    break;
                case 5:
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, 9});
                    break;
                case 6:
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, 10});
                    break;
                case 7:
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, 11});
                    break;
                case 8:
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, 0});
                    break;
            }
        }
    };
    OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._194.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            UiMgr.this.sendAcCmd(AIR_CMD.WIND_DN);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            UiMgr.this.sendAcCmd(AIR_CMD.WIND_UP);
        }
    };
    OnAirSeatClickListener onAirSeatClickListener = new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._194.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onLeftSeatClick() {
            UiMgr.this.sendAcCmd(AIR_CMD.MODE);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onRightSeatClick() {
            UiMgr.this.sendAcCmd(AIR_CMD.MODE);
        }
    };
    OnConfirmDialogListener onConfirmDialogListener = new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._194.UiMgr.10
        @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
        public void onConfirmClick(int i, int i2) {
            if (i == 0 && i2 == 12) {
                CanbusMsgSender.sendMsg(new byte[]{22, -119, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, 0});
            }
        }
    };
    OnSettingItemClickListener mOnSettingItemClickListener = new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._194.UiMgr.11
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
        public void onClickItem(int i, int i2) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_194_driving_maintenance")) {
                UiMgr uiMgr2 = UiMgr.this;
                if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "_194_driving_maintenance", "_194_tire_pressure_reset")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, 1, 1});
                }
            }
            UiMgr uiMgr3 = UiMgr.this;
            if (i == uiMgr3.getSettingLeftIndexes(uiMgr3.mContext, "panorama_setting")) {
                UiMgr uiMgr4 = UiMgr.this;
                if (i2 == uiMgr4.getSettingRightIndex(uiMgr4.mContext, "panorama_setting", "str_250_0_4")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, 1});
                }
            }
            UiMgr uiMgr5 = UiMgr.this;
            if (i == uiMgr5.getSettingLeftIndexes(uiMgr5.mContext, "_194_airconditioning_settings")) {
                UiMgr uiMgr6 = UiMgr.this;
                if (i2 == uiMgr6.getSettingRightIndex(uiMgr6.mContext, "_194_airconditioning_settings", "_194_pm_25")) {
                    UiMgr uiMgr7 = UiMgr.this;
                    uiMgr7.getMsgMgr(uiMgr7.mContext).showPmInfo();
                }
            }
        }
    };
    OnSettingItemSeekbarSelectListener onSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._194.UiMgr.12
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
        public void onClickItem(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_194_window")) {
                UiMgr uiMgr2 = UiMgr.this;
                if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "_194_window", "_194_skylights_state")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 9, 1, (byte) i3});
                }
            }
        }
    };
    OnSettingItemSelectListener onSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._194.UiMgr.13
        int bit0 = 0;
        int bit1 = 0;
        int bit2 = 0;
        int bit3 = 0;
        int bit4 = 0;
        int bit5 = 0;
        int bit6 = 0;
        int bit7 = 0;

        private void Left10(int i, int i2, int i3) {
        }

        private void Left9(int i, int i2, int i3) {
        }

        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_194_car_select")) {
                UiMgr uiMgr2 = UiMgr.this;
                if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "_194_car_select", "_194_car_select0")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -18, 80, (byte) i3});
                    UiMgr uiMgr3 = UiMgr.this;
                    uiMgr3.getMsgMgr(uiMgr3.mContext).updateSettings(i, i2, i3);
                }
                UiMgr uiMgr4 = UiMgr.this;
                if (i2 == uiMgr4.getSettingRightIndex(uiMgr4.mContext, "_194_car_select", "_194_car_select1")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -18, 81, (byte) i3});
                    UiMgr uiMgr5 = UiMgr.this;
                    uiMgr5.getMsgMgr(uiMgr5.mContext).updateSettings(i, i2, i3);
                }
            }
            Left1(i, i2, i3);
            Left2(i, i2, i3);
            Left3(i, i2, i3);
            Left4(i, i2, i3);
            Left5(i, i2, i3);
            Left6(i, i2, i3);
            Left7(i, i2, i3);
            left8(i, i2, i3);
            Left9(i, i2, i3);
            Left10(i, i2, i3);
            Left11(i, i2, i3);
            Left12(i, i2, i3);
            Left13(i, i2, i3);
        }

        private void Left13(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_194_drive_model1")) {
                UiMgr uiMgr2 = UiMgr.this;
                if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "_194_drive_model1", "_194_drive_model1")) {
                    if (i3 == 3) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 11, (byte) (i3 + 1), 0});
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 11, (byte) (i3 + 1)});
                    }
                }
            }
            UiMgr uiMgr3 = UiMgr.this;
            if (i == uiMgr3.getSettingLeftIndexes(uiMgr3.mContext, "_194_drive_model1")) {
                UiMgr uiMgr4 = UiMgr.this;
                if (i2 == uiMgr4.getSettingRightIndex(uiMgr4.mContext, "_194_drive_model1", "_194_drive_model2")) {
                    if (i3 == 0) {
                        this.bit6 = 0;
                        this.bit7 = 0;
                    } else if (i3 == 1) {
                        this.bit6 = 1;
                        this.bit7 = 0;
                    } else if (i3 == 2) {
                        this.bit6 = 0;
                        this.bit7 = 1;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 11, 4, (byte) UiMgr.this.getDecimalFrom8Bit(this.bit7, this.bit6, this.bit5, this.bit4, this.bit3, this.bit2, this.bit1, this.bit0)});
                }
            }
            UiMgr uiMgr5 = UiMgr.this;
            if (i == uiMgr5.getSettingLeftIndexes(uiMgr5.mContext, "_194_drive_model1")) {
                UiMgr uiMgr6 = UiMgr.this;
                if (i2 == uiMgr6.getSettingRightIndex(uiMgr6.mContext, "_194_drive_model1", "_194_drive_model3")) {
                    if (i3 == 0) {
                        this.bit4 = 0;
                        this.bit5 = 0;
                    } else if (i3 == 1) {
                        this.bit4 = 1;
                        this.bit5 = 0;
                    } else if (i3 == 2) {
                        this.bit4 = 0;
                        this.bit5 = 1;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 11, 4, (byte) UiMgr.this.getDecimalFrom8Bit(this.bit7, this.bit6, this.bit5, this.bit4, this.bit3, this.bit2, this.bit1, this.bit0)});
                }
            }
            UiMgr uiMgr7 = UiMgr.this;
            if (i == uiMgr7.getSettingLeftIndexes(uiMgr7.mContext, "_194_drive_model1")) {
                UiMgr uiMgr8 = UiMgr.this;
                if (i2 == uiMgr8.getSettingRightIndex(uiMgr8.mContext, "_194_drive_model1", "_194_drive_model4")) {
                    if (i3 == 0) {
                        this.bit3 = 0;
                    } else if (i3 == 1) {
                        this.bit3 = 1;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 11, 4, (byte) UiMgr.this.getDecimalFrom8Bit(this.bit7, this.bit6, this.bit5, this.bit4, this.bit3, this.bit2, this.bit1, this.bit0)});
                }
            }
        }

        private void Left12(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "language_setup")) {
                UiMgr uiMgr2 = UiMgr.this;
                if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "language_setup", "language_setup")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 10, 1, (byte) (i3 + 1)});
                }
            }
        }

        private void Left11(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "panorama_setting")) {
                UiMgr uiMgr2 = UiMgr.this;
                uiMgr2.getSettingRightIndex(uiMgr2.mContext, "panorama_setting", "str_250_0_4");
            }
            UiMgr uiMgr3 = UiMgr.this;
            if (i == uiMgr3.getSettingLeftIndexes(uiMgr3.mContext, "panorama_setting")) {
                UiMgr uiMgr4 = UiMgr.this;
                if (i2 == uiMgr4.getSettingRightIndex(uiMgr4.mContext, "panorama_setting", "_194_360panoramic_10")) {
                    if (i3 == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, 16});
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, 17});
                    }
                }
            }
            UiMgr uiMgr5 = UiMgr.this;
            if (i == uiMgr5.getSettingLeftIndexes(uiMgr5.mContext, "panorama_setting")) {
                UiMgr uiMgr6 = UiMgr.this;
                if (i2 == uiMgr6.getSettingRightIndex(uiMgr6.mContext, "panorama_setting", "_194_360panoramic_11")) {
                    if (i3 == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED});
                    } else if (i3 == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED});
                    } else if (i3 == 2) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, 14});
                    } else if (i3 == 3) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, 15});
                    }
                }
            }
            UiMgr uiMgr7 = UiMgr.this;
            if (i == uiMgr7.getSettingLeftIndexes(uiMgr7.mContext, "panorama_setting")) {
                UiMgr uiMgr8 = UiMgr.this;
                if (i2 == uiMgr8.getSettingRightIndex(uiMgr8.mContext, "panorama_setting", "_194_360panoramic_12")) {
                    if (i3 == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, 2});
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, 3});
                    }
                }
            }
        }

        private void left8(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_194_advanced_driver_assistance")) {
                UiMgr uiMgr2 = UiMgr.this;
                if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "_194_advanced_driver_assistance", "_194_pedestrian_warning_beep")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 1, (byte) i3});
                }
            }
            UiMgr uiMgr3 = UiMgr.this;
            if (i == uiMgr3.getSettingLeftIndexes(uiMgr3.mContext, "_194_advanced_driver_assistance")) {
                UiMgr uiMgr4 = UiMgr.this;
                if (i2 == uiMgr4.getSettingRightIndex(uiMgr4.mContext, "_194_advanced_driver_assistance", "_194_speed_auxiliary")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 2, (byte) i3});
                }
            }
            UiMgr uiMgr5 = UiMgr.this;
            if (i == uiMgr5.getSettingLeftIndexes(uiMgr5.mContext, "_194_advanced_driver_assistance")) {
                UiMgr uiMgr6 = UiMgr.this;
                if (i2 == uiMgr6.getSettingRightIndex(uiMgr6.mContext, "_194_advanced_driver_assistance", "_194_assist_mode")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 3, (byte) i3});
                }
            }
            UiMgr uiMgr7 = UiMgr.this;
            if (i == uiMgr7.getSettingLeftIndexes(uiMgr7.mContext, "_194_advanced_driver_assistance")) {
                UiMgr uiMgr8 = UiMgr.this;
                if (i2 == uiMgr8.getSettingRightIndex(uiMgr8.mContext, "_194_advanced_driver_assistance", "_194_lane_departure_warning")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 4, (byte) i3});
                }
            }
            UiMgr uiMgr9 = UiMgr.this;
            if (i == uiMgr9.getSettingLeftIndexes(uiMgr9.mContext, "_194_advanced_driver_assistance")) {
                UiMgr uiMgr10 = UiMgr.this;
                if (i2 == uiMgr10.getSettingRightIndex(uiMgr10.mContext, "_194_advanced_driver_assistance", "_194_alarm_sensitivity")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 5, (byte) i3});
                }
            }
            UiMgr uiMgr11 = UiMgr.this;
            if (i == uiMgr11.getSettingLeftIndexes(uiMgr11.mContext, "_194_advanced_driver_assistance")) {
                UiMgr uiMgr12 = UiMgr.this;
                if (i2 == uiMgr12.getSettingRightIndex(uiMgr12.mContext, "_194_advanced_driver_assistance", "_194_alarm_sound")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 6, (byte) i3});
                }
            }
            UiMgr uiMgr13 = UiMgr.this;
            if (i == uiMgr13.getSettingLeftIndexes(uiMgr13.mContext, "_194_advanced_driver_assistance")) {
                UiMgr uiMgr14 = UiMgr.this;
                if (i2 == uiMgr14.getSettingRightIndex(uiMgr14.mContext, "_194_advanced_driver_assistance", "_194_forward_collision_auxiliary_systems")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 7, (byte) i3});
                }
            }
            UiMgr uiMgr15 = UiMgr.this;
            if (i == uiMgr15.getSettingLeftIndexes(uiMgr15.mContext, "_194_advanced_driver_assistance")) {
                UiMgr uiMgr16 = UiMgr.this;
                if (i2 == uiMgr16.getSettingRightIndex(uiMgr16.mContext, "_194_advanced_driver_assistance", "_194_forward_collision_assist__assist_mode")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 8, (byte) i3});
                }
            }
            UiMgr uiMgr17 = UiMgr.this;
            if (i == uiMgr17.getSettingLeftIndexes(uiMgr17.mContext, "_194_advanced_driver_assistance")) {
                UiMgr uiMgr18 = UiMgr.this;
                if (i2 == uiMgr18.getSettingRightIndex(uiMgr18.mContext, "_194_advanced_driver_assistance", "_194_forward_collision_aid__alert_sensitivity")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 9, (byte) i3});
                }
            }
            UiMgr uiMgr19 = UiMgr.this;
            if (i == uiMgr19.getSettingLeftIndexes(uiMgr19.mContext, "_194_advanced_driver_assistance")) {
                UiMgr uiMgr20 = UiMgr.this;
                if (i2 == uiMgr20.getSettingRightIndex(uiMgr20.mContext, "_194_advanced_driver_assistance", "_194_add_function1")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 10, (byte) i3});
                }
            }
            UiMgr uiMgr21 = UiMgr.this;
            if (i == uiMgr21.getSettingLeftIndexes(uiMgr21.mContext, "_194_advanced_driver_assistance")) {
                UiMgr uiMgr22 = UiMgr.this;
                if (i2 == uiMgr22.getSettingRightIndex(uiMgr22.mContext, "_194_advanced_driver_assistance", "_194_add_function2")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 11, (byte) i3});
                }
            }
            UiMgr uiMgr23 = UiMgr.this;
            if (i == uiMgr23.getSettingLeftIndexes(uiMgr23.mContext, "_194_advanced_driver_assistance")) {
                UiMgr uiMgr24 = UiMgr.this;
                if (i2 == uiMgr24.getSettingRightIndex(uiMgr24.mContext, "_194_advanced_driver_assistance", "_194_add_function3")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) i3});
                }
            }
            UiMgr uiMgr25 = UiMgr.this;
            if (i == uiMgr25.getSettingLeftIndexes(uiMgr25.mContext, "_194_advanced_driver_assistance")) {
                UiMgr uiMgr26 = UiMgr.this;
                if (i2 == uiMgr26.getSettingRightIndex(uiMgr26.mContext, "_194_advanced_driver_assistance", "_194_add_function4")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) i3});
                }
            }
            UiMgr uiMgr27 = UiMgr.this;
            if (i == uiMgr27.getSettingLeftIndexes(uiMgr27.mContext, "_194_advanced_driver_assistance")) {
                UiMgr uiMgr28 = UiMgr.this;
                if (i2 == uiMgr28.getSettingRightIndex(uiMgr28.mContext, "_194_advanced_driver_assistance", "_194_add_function5")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 14, (byte) i3});
                }
            }
            UiMgr uiMgr29 = UiMgr.this;
            if (i == uiMgr29.getSettingLeftIndexes(uiMgr29.mContext, "_194_advanced_driver_assistance")) {
                UiMgr uiMgr30 = UiMgr.this;
                if (i2 == uiMgr30.getSettingRightIndex(uiMgr30.mContext, "_194_advanced_driver_assistance", "_194_add_function6")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 15, (byte) i3});
                }
            }
            UiMgr uiMgr31 = UiMgr.this;
            if (i == uiMgr31.getSettingLeftIndexes(uiMgr31.mContext, "_194_advanced_driver_assistance")) {
                UiMgr uiMgr32 = UiMgr.this;
                if (i2 == uiMgr32.getSettingRightIndex(uiMgr32.mContext, "_194_advanced_driver_assistance", "_194_add_function7")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 16, (byte) i3});
                }
            }
            UiMgr uiMgr33 = UiMgr.this;
            if (i == uiMgr33.getSettingLeftIndexes(uiMgr33.mContext, "_194_advanced_driver_assistance")) {
                UiMgr uiMgr34 = UiMgr.this;
                if (i2 == uiMgr34.getSettingRightIndex(uiMgr34.mContext, "_194_advanced_driver_assistance", "_194_add_function8")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 17, (byte) i3});
                }
            }
            UiMgr uiMgr35 = UiMgr.this;
            if (i == uiMgr35.getSettingLeftIndexes(uiMgr35.mContext, "_194_advanced_driver_assistance")) {
                UiMgr uiMgr36 = UiMgr.this;
                if (i2 == uiMgr36.getSettingRightIndex(uiMgr36.mContext, "_194_advanced_driver_assistance", "_194_add_function9")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 18, (byte) i3});
                }
            }
            UiMgr uiMgr37 = UiMgr.this;
            if (i == uiMgr37.getSettingLeftIndexes(uiMgr37.mContext, "_194_advanced_driver_assistance")) {
                UiMgr uiMgr38 = UiMgr.this;
                if (i2 == uiMgr38.getSettingRightIndex(uiMgr38.mContext, "_194_advanced_driver_assistance", "_194_add_function10")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 19, (byte) i3});
                }
            }
            UiMgr uiMgr39 = UiMgr.this;
            if (i == uiMgr39.getSettingLeftIndexes(uiMgr39.mContext, "_194_advanced_driver_assistance")) {
                UiMgr uiMgr40 = UiMgr.this;
                if (i2 == uiMgr40.getSettingRightIndex(uiMgr40.mContext, "_194_advanced_driver_assistance", "_194_add_function11")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 20, (byte) i3});
                }
            }
        }

        private void Left7(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_194_instrument_brightness")) {
                UiMgr uiMgr2 = UiMgr.this;
                if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "_194_instrument_brightness", "_194_meter_brightness_level")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 7, 1, (byte) (i3 + 1)});
                }
            }
        }

        private void Left6(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_194_reset")) {
                UiMgr uiMgr2 = UiMgr.this;
                uiMgr2.getSettingRightIndex(uiMgr2.mContext, "_194_reset", "_194_add_action2");
            }
        }

        private void Left5(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_194_driving_maintenance")) {
                UiMgr uiMgr2 = UiMgr.this;
                uiMgr2.getSettingRightIndex(uiMgr2.mContext, "_194_driving_maintenance", "_194_tire_pressure_reset");
            }
            UiMgr uiMgr3 = UiMgr.this;
            if (i == uiMgr3.getSettingLeftIndexes(uiMgr3.mContext, "_194_driving_maintenance")) {
                UiMgr uiMgr4 = UiMgr.this;
                if (i2 == uiMgr4.getSettingRightIndex(uiMgr4.mContext, "_194_driving_maintenance", "_194_vehicle_stability_control")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, 2, (byte) i3});
                }
            }
            UiMgr uiMgr5 = UiMgr.this;
            if (i == uiMgr5.getSettingLeftIndexes(uiMgr5.mContext, "_194_driving_maintenance")) {
                UiMgr uiMgr6 = UiMgr.this;
                if (i2 == uiMgr6.getSettingRightIndex(uiMgr6.mContext, "_194_driving_maintenance", "_194_ecodriving")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, 3, (byte) i3});
                }
            }
            UiMgr uiMgr7 = UiMgr.this;
            if (i == uiMgr7.getSettingLeftIndexes(uiMgr7.mContext, "_194_driving_maintenance")) {
                UiMgr uiMgr8 = UiMgr.this;
                if (i2 == uiMgr8.getSettingRightIndex(uiMgr8.mContext, "_194_driving_maintenance", "_194_add_action1")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, 4, (byte) i3});
                }
            }
        }

        private void Left4(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_194_airconditioning_settings")) {
                UiMgr uiMgr2 = UiMgr.this;
                if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "_194_airconditioning_settings", "_194_rear_window_demisting_demisting_linkage")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 5, 1, (byte) i3});
                }
            }
            UiMgr uiMgr3 = UiMgr.this;
            if (i == uiMgr3.getSettingLeftIndexes(uiMgr3.mContext, "_194_airconditioning_settings")) {
                UiMgr uiMgr4 = UiMgr.this;
                if (i2 == uiMgr4.getSettingRightIndex(uiMgr4.mContext, "_194_airconditioning_settings", "_194_automatic_mode_wind")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 5, 2, (byte) i3});
                }
            }
            UiMgr uiMgr5 = UiMgr.this;
            if (i == uiMgr5.getSettingLeftIndexes(uiMgr5.mContext, "_194_airconditioning_settings")) {
                UiMgr uiMgr6 = UiMgr.this;
                if (i2 == uiMgr6.getSettingRightIndex(uiMgr6.mContext, "_194_airconditioning_settings", "_194_partition_temperature_settings")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 5, 3, (byte) i3});
                }
            }
        }

        private void Left3(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_194_convenient_and_comfortable")) {
                UiMgr uiMgr2 = UiMgr.this;
                if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "_194_convenient_and_comfortable", "_194_i_went_home_with")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, 1, (byte) i3});
                }
            }
            UiMgr uiMgr3 = UiMgr.this;
            if (i == uiMgr3.getSettingLeftIndexes(uiMgr3.mContext, "_194_convenient_and_comfortable")) {
                UiMgr uiMgr4 = UiMgr.this;
                if (i2 == uiMgr4.getSettingRightIndex(uiMgr4.mContext, "_194_convenient_and_comfortable", "_194_searching_cars_indication")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, 2, (byte) i3});
                }
            }
            UiMgr uiMgr5 = UiMgr.this;
            if (i == uiMgr5.getSettingLeftIndexes(uiMgr5.mContext, "_194_convenient_and_comfortable")) {
                UiMgr uiMgr6 = UiMgr.this;
                if (i2 == uiMgr6.getSettingRightIndex(uiMgr6.mContext, "_194_convenient_and_comfortable", "_194_steering_wheel_feels")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, 3, (byte) i3});
                }
            }
            UiMgr uiMgr7 = UiMgr.this;
            if (i == uiMgr7.getSettingLeftIndexes(uiMgr7.mContext, "_194_convenient_and_comfortable")) {
                UiMgr uiMgr8 = UiMgr.this;
                if (i2 == uiMgr8.getSettingRightIndex(uiMgr8.mContext, "_194_convenient_and_comfortable", "_194_unlock_mode")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, 3, (byte) i3});
                }
            }
            UiMgr uiMgr9 = UiMgr.this;
            if (i == uiMgr9.getSettingLeftIndexes(uiMgr9.mContext, "_194_convenient_and_comfortable")) {
                UiMgr uiMgr10 = UiMgr.this;
                if (i2 == uiMgr10.getSettingRightIndex(uiMgr10.mContext, "_194_convenient_and_comfortable", "_194_smart_unlock_the_car_near")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, 4, (byte) i3});
                }
            }
            UiMgr uiMgr11 = UiMgr.this;
            if (i == uiMgr11.getSettingLeftIndexes(uiMgr11.mContext, "_194_convenient_and_comfortable")) {
                UiMgr uiMgr12 = UiMgr.this;
                if (i2 == uiMgr12.getSettingRightIndex(uiMgr12.mContext, "_194_convenient_and_comfortable", "_194_a_foldable_outside_mirror_automatically")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 8, 1, (byte) i3});
                }
            }
        }

        private void Left2(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_194_lighting_control")) {
                UiMgr uiMgr2 = UiMgr.this;
                if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "_194_lighting_control", "_194_i_came_home_with__reversing_lights")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, 1, (byte) DataHandleUtils.setIntByteWithBit(MsgMgr.mHomeWithMeLight, 7, i3 == 1)});
                }
            }
            UiMgr uiMgr3 = UiMgr.this;
            if (i == uiMgr3.getSettingLeftIndexes(uiMgr3.mContext, "_194_lighting_control")) {
                UiMgr uiMgr4 = UiMgr.this;
                if (i2 == uiMgr4.getSettingRightIndex(uiMgr4.mContext, "_194_lighting_control", "_194_i_came_home_with__beam_lights")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, 1, (byte) DataHandleUtils.setIntByteWithBit(MsgMgr.mHomeWithMeLight, 6, i3 == 1)});
                }
            }
            UiMgr uiMgr5 = UiMgr.this;
            if (i == uiMgr5.getSettingLeftIndexes(uiMgr5.mContext, "_194_lighting_control")) {
                UiMgr uiMgr6 = UiMgr.this;
                if (i2 == uiMgr6.getSettingRightIndex(uiMgr6.mContext, "_194_lighting_control", "_194_i_came_home_with__rear_fog")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, 1, (byte) DataHandleUtils.setIntByteWithBit(MsgMgr.mHomeWithMeLight, 5, i3 == 1)});
                }
            }
            UiMgr uiMgr7 = UiMgr.this;
            if (i == uiMgr7.getSettingLeftIndexes(uiMgr7.mContext, "_194_lighting_control")) {
                UiMgr uiMgr8 = UiMgr.this;
                if (i2 == uiMgr8.getSettingRightIndex(uiMgr8.mContext, "_194_lighting_control", "_194_i_came_home_with_a_duration")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, 2, (byte) DataHandleUtils.setIntFromByteWithBit(MsgMgr.mHomeWithMeTime, i3, 0, 4)});
                }
            }
            UiMgr uiMgr9 = UiMgr.this;
            if (i == uiMgr9.getSettingLeftIndexes(uiMgr9.mContext, "_194_lighting_control")) {
                UiMgr uiMgr10 = UiMgr.this;
                if (i2 == uiMgr10.getSettingRightIndex(uiMgr10.mContext, "_194_lighting_control", "_194_searching_cars_indication__reversing_lights")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, 3, (byte) DataHandleUtils.setIntByteWithBit(MsgMgr.mFindCarLight, 7, i3 == 1)});
                }
            }
            UiMgr uiMgr11 = UiMgr.this;
            if (i == uiMgr11.getSettingLeftIndexes(uiMgr11.mContext, "_194_lighting_control")) {
                UiMgr uiMgr12 = UiMgr.this;
                if (i2 == uiMgr12.getSettingRightIndex(uiMgr12.mContext, "_194_lighting_control", "_194_searching_cars_indication__near_light")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, 3, (byte) DataHandleUtils.setIntByteWithBit(MsgMgr.mFindCarLight, 6, i3 == 1)});
                }
            }
            UiMgr uiMgr13 = UiMgr.this;
            if (i == uiMgr13.getSettingLeftIndexes(uiMgr13.mContext, "_194_lighting_control")) {
                UiMgr uiMgr14 = UiMgr.this;
                if (i2 == uiMgr14.getSettingRightIndex(uiMgr14.mContext, "_194_lighting_control", "_194_searching_cars_indication__rear_fog")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, 3, (byte) DataHandleUtils.setIntByteWithBit(MsgMgr.mFindCarLight, 5, i3 == 1)});
                }
            }
            UiMgr uiMgr15 = UiMgr.this;
            if (i == uiMgr15.getSettingLeftIndexes(uiMgr15.mContext, "_194_lighting_control")) {
                UiMgr uiMgr16 = UiMgr.this;
                if (i2 == uiMgr16.getSettingRightIndex(uiMgr16.mContext, "_194_lighting_control", "_194_searching_cars_indicate_the_duration")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, 4, (byte) DataHandleUtils.setIntFromByteWithBit(MsgMgr.mFindCarTime, i3, 0, 4)});
                }
            }
        }

        private void Left1(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_194_lock_control")) {
                UiMgr uiMgr2 = UiMgr.this;
                if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "_194_lock_control", "_194_driving_luosuo")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, 1, (byte) i3});
                }
            }
            UiMgr uiMgr3 = UiMgr.this;
            if (i == uiMgr3.getSettingLeftIndexes(uiMgr3.mContext, "_194_lock_control")) {
                UiMgr uiMgr4 = UiMgr.this;
                if (i2 == uiMgr4.getSettingRightIndex(uiMgr4.mContext, "_194_lock_control", "_194_unlock")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, 2, (byte) i3});
                }
            }
            UiMgr uiMgr5 = UiMgr.this;
            if (i == uiMgr5.getSettingLeftIndexes(uiMgr5.mContext, "_194_lock_control")) {
                UiMgr uiMgr6 = UiMgr.this;
                if (i2 == uiMgr6.getSettingRightIndex(uiMgr6.mContext, "_194_lock_control", "_194_unlock_mode")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, 3, (byte) i3});
                }
            }
            UiMgr uiMgr7 = UiMgr.this;
            if (i == uiMgr7.getSettingLeftIndexes(uiMgr7.mContext, "_194_lock_control")) {
                UiMgr uiMgr8 = UiMgr.this;
                if (i2 == uiMgr8.getSettingRightIndex(uiMgr8.mContext, "_194_lock_control", "_194_smart_unlock_the_car_near")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, 4, (byte) i3});
                }
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._194.UiMgr.14
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAcCmd(AIR_CMD.POWER);
            } else if (i == 1) {
                UiMgr.this.sendAcCmd(AIR_CMD.AC);
            } else {
                if (i != 2) {
                    return;
                }
                UiMgr.this.sendAcCmd(AIR_CMD.AUTO);
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._194.UiMgr.15
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAcCmd(AIR_CMD.FRONT_DEFOG);
            } else if (i == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 3, 1});
            } else {
                if (i != 2) {
                    return;
                }
                UiMgr.this.sendAcCmd(AIR_CMD.REAR_DEFOG);
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerRearBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._194.UiMgr.16
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 26, 1});
            } else {
                if (i != 1) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 27, 1});
            }
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerRearCenter = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._194.UiMgr.17
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 24, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 24, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 24, 2});
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 24, 0});
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._194.UiMgr.18
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAcCmd(AIR_CMD.LEFT_TEMP_UP);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAcCmd(AIR_CMD.LEFT_TEMP_DN);
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._194.UiMgr.19
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAcCmd(AIR_CMD.LEFT_TEMP_UP);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAcCmd(AIR_CMD.LEFT_TEMP_DN);
        }
    };
    OnAirSeatHeatColdMinPlusClickListener onMinPlusClickListenerLeft = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._194.UiMgr.20
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
            UiMgr.this.sendAcCmd(AIR_CMD.LEFT_SEAT_HEAT);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr.this.sendAcCmd(AIR_CMD.LEFT_SEAT_HEAT);
        }
    };
    OnAirSeatHeatColdMinPlusClickListener onMinPlusClickListenerRight = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._194.UiMgr.21
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
            UiMgr.this.sendAcCmd(AIR_CMD.RIGHT_SEAT_HEAT);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr.this.sendAcCmd(AIR_CMD.RIGHT_SEAT_HEAT);
        }
    };
    private int mDifferent = getCurrentCarId();

    enum AIR_CMD {
        POWER,
        AC,
        LOOP,
        AUTO,
        LEFT_SEAT_HEAT,
        RIGHT_SEAT_HEAT,
        LEFT_TEMP_UP,
        LEFT_TEMP_DN,
        WIND_DN,
        WIND_UP,
        FRONT_DEFOG,
        REAR_DEFOG,
        MODE
    }

    public UiMgr(Context context) {
        this.mContext = context;
        AirPageUiSet airUiSet = getAirUiSet(context);
        FrontArea frontArea = airUiSet.getFrontArea();
        frontArea.setOnAirSeatClickListener(this.onAirSeatClickListener);
        frontArea.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, null, null, this.mOnAirBtnClickListenerFrontBottom});
        frontArea.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
        frontArea.setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.onMinPlusClickListenerLeft, this.onMinPlusClickListenerRight, null, null});
        frontArea.setSetWindSpeedViewOnClickListener(this.onAirWindSpeedUpDownClickListener);
        airUiSet.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, this.mOnAirBtnClickListenerRearBottom});
        airUiSet.getRearArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._194.UiMgr.1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 20, 2});
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 20, 1});
            }
        });
        airUiSet.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{null, this.mOnUpDownClickListenerRearCenter, null});
        airUiSet.getRearArea().setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._194.UiMgr.2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                if (UiMgr.this.WindStatus == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -118, 25, 1});
                    UiMgr.this.WindStatus = 2;
                } else if (UiMgr.this.WindStatus == 2) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -118, 25, 2});
                    UiMgr.this.WindStatus = 3;
                } else if (UiMgr.this.WindStatus == 3) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -118, 25, 3});
                    UiMgr.this.WindStatus = 1;
                }
            }
        });
        airUiSet.setOnAirPageStatusListener(new OnAirPageStatusListener() { // from class: com.hzbhd.canbus.car._194.UiMgr.3
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener
            public void onStatusChange(int i) {
                CanbusMsgSender.sendMsg(new byte[]{22, -112, 33});
                CanbusMsgSender.sendMsg(new byte[]{22, -112, 39});
            }
        });
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingConfirmDialogListener(this.onConfirmDialogListener);
        settingUiSet.setOnSettingItemClickListener(this.mOnSettingItemClickListener);
        settingUiSet.setOnSettingItemSeekbarSelectListener(this.onSettingItemSeekbarSelectListener);
        settingUiSet.setOnSettingItemSelectListener(this.onSettingItemSelectListener);
        settingUiSet.setOnSettingPageStatusListener(new OnSettingPageStatusListener() { // from class: com.hzbhd.canbus.car._194.UiMgr.4
            @Override // com.hzbhd.canbus.interfaces.OnSettingPageStatusListener
            public void onStatusChange(int i) {
                CanbusMsgSender.sendMsg(new byte[]{22, -112, 57});
                CanbusMsgSender.sendMsg(new byte[]{22, -112, 67});
                CanbusMsgSender.sendMsg(new byte[]{22, -112, 83});
            }
        });
        getParkPageUiSet(this.mContext).setOnPanoramicItemClickListener(this.onPanoramicItemClickListener);
        getDriverDataPageUiSet(this.mContext).setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener() { // from class: com.hzbhd.canbus.car._194.UiMgr.5
            @Override // com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener
            public void onStatusChange(int i) {
                CanbusMsgSender.sendMsg(new byte[]{22, -112, 36});
                CanbusMsgSender.sendMsg(new byte[]{22, -112, 39});
                CanbusMsgSender.sendMsg(new byte[]{22, -112, 84});
                CanbusMsgSender.sendMsg(new byte[]{22, -112, 96});
                CanbusMsgSender.sendMsg(new byte[]{22, -112, 97});
                CanbusMsgSender.sendMsg(new byte[]{22, -112, 98});
            }
        });
        getTireUiSet(this.mContext).setOnTirePageStatusListener(new OnTirePageStatusListener() { // from class: com.hzbhd.canbus.car._194.UiMgr.6
            @Override // com.hzbhd.canbus.interfaces.OnTirePageStatusListener
            public void onStatusChange(int i) {
                CanbusMsgSender.sendMsg(new byte[]{22, -112, 82});
            }
        });
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        intiUi();
    }

    private void intiUi() {
        int i = this.mDifferent;
        if (i != 13 && i != 17) {
            getAirUiSet(this.mContext).setHaveRearArea(false);
        }
        int i2 = this.mDifferent;
        if (i2 != 6 && i2 != 14) {
            removeSettingLeftItemByNameList(this.mContext, new String[]{"_194_lock_control", "_194_lighting_control"});
        }
        int i3 = this.mDifferent;
        if (i3 != 1 && i3 != 2 && i3 != 7 && i3 != 8 && i3 != 9 && i3 != 10 && i3 != 11 && i3 != 12 && i3 != 13 && i3 != 14 && i3 != 15) {
            removeSettingLeftItemByNameList(this.mContext, new String[]{"_194_convenient_and_comfortable", "_194_airconditioning_settings", "_194_driving_maintenance", "_194_reset", "_194_instrument_brightness", "_194_advanced_driver_assistance", "_194_window", "language_setup"});
        }
        int i4 = this.mDifferent;
        if (i4 != 11 && i4 != 12 && i4 != 17) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_194_pm_25"});
        }
        int i5 = this.mDifferent;
        if (i5 != 6 && i5 != 7) {
            removeMainPrjBtnByName(this.mContext, MainAction.WARNING);
        }
        int i6 = this.mDifferent;
        if (i6 != 9 && i6 != 10) {
            removeSettingLeftItemByNameList(this.mContext, new String[]{"panorama_setting"});
            getParkPageUiSet(this.mContext).setShowPanoramic(false);
        }
        if (this.mDifferent != 16) {
            removeMainPrjBtnByName(this.mContext, MainAction.DRIVE_DATA);
        }
    }

    /* renamed from: com.hzbhd.canbus.car._194.UiMgr$22, reason: invalid class name */
    static /* synthetic */ class AnonymousClass22 {
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$car$_194$UiMgr$AIR_CMD;

        static {
            int[] iArr = new int[AIR_CMD.values().length];
            $SwitchMap$com$hzbhd$canbus$car$_194$UiMgr$AIR_CMD = iArr;
            try {
                iArr[AIR_CMD.POWER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$car$_194$UiMgr$AIR_CMD[AIR_CMD.AC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$car$_194$UiMgr$AIR_CMD[AIR_CMD.LOOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$car$_194$UiMgr$AIR_CMD[AIR_CMD.AUTO.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$car$_194$UiMgr$AIR_CMD[AIR_CMD.LEFT_SEAT_HEAT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$car$_194$UiMgr$AIR_CMD[AIR_CMD.RIGHT_SEAT_HEAT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$car$_194$UiMgr$AIR_CMD[AIR_CMD.LEFT_TEMP_UP.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$car$_194$UiMgr$AIR_CMD[AIR_CMD.LEFT_TEMP_DN.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$car$_194$UiMgr$AIR_CMD[AIR_CMD.WIND_DN.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$car$_194$UiMgr$AIR_CMD[AIR_CMD.WIND_UP.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$car$_194$UiMgr$AIR_CMD[AIR_CMD.FRONT_DEFOG.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$car$_194$UiMgr$AIR_CMD[AIR_CMD.REAR_DEFOG.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$car$_194$UiMgr$AIR_CMD[AIR_CMD.MODE.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAcCmd(AIR_CMD air_cmd) {
        switch (AnonymousClass22.$SwitchMap$com$hzbhd$canbus$car$_194$UiMgr$AIR_CMD[air_cmd.ordinal()]) {
            case 1:
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 5, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 5, 0});
                break;
            case 2:
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 8, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 8, 0});
                break;
            case 3:
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 3, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 3, 0});
                break;
            case 4:
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 9, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 9, 0});
                break;
            case 5:
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 17, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 17, 0});
                break;
            case 6:
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 18, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 18, 0});
                break;
            case 7:
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 1, 0});
                break;
            case 8:
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 1, 2});
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 1, 0});
                break;
            case 9:
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 4, 2});
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 4, 0});
                break;
            case 10:
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 4, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 4, 0});
                break;
            case 11:
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 6, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 6, 0});
                break;
            case 12:
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 7, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 7, 0});
                break;
            case 13:
                sendAirCommandFrontWindMode();
                break;
        }
    }

    protected static void sendAirCommandFrontWindMode() {
        CanbusMsgSender.sendMsg(new byte[]{22, -118, 16, new byte[]{1, 2, 3, 4}[mFrontWindMode]});
        CanbusMsgSender.sendMsg(new byte[]{22, -118, 16, 0});
        int i = mFrontWindMode + 1;
        mFrontWindMode = i;
        if (i >= 4) {
            mFrontWindMode = 0;
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

    /* JADX INFO: Access modifiers changed from: private */
    public int getDecimalFrom8Bit(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return Integer.parseInt(i + "" + i2 + "" + i3 + "" + i4 + "" + i5 + "" + i6 + "" + i7 + "" + i8, 2);
    }
}
