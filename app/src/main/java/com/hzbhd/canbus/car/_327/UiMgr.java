package com.hzbhd.canbus.car._327;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.car_cus._283.view.CarImageView;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class UiMgr extends AbstractUiMgr {
    public static final String L0R0 = "Share_327_Settings_L0R0";
    public static final String L1R0 = "Share_327_Settings_L1R0";
    public static final String L2R0 = "Share_327_Settings_L2R0";
    public static final String L2R1 = "Share_327_Settings_L2R1";
    public static final String L3R0 = "Share_327_Settings_L3R0";
    public static final String L3R1 = "Share_327_Settings_L3R1";
    public static final String L4R0 = "Share_327_Settings_L4R0";
    public static final String L5R0 = "Share_327_Settings_L5R0";
    public static final String L6R0 = "Share_327_Settings_L6R0";
    public static final String L6R1 = "Share_327_Settings_L6R1";
    public static final String L6R2 = "Share_327_Settings_L6R2";
    public static final String L7R0 = "Share_327_Settings_L7R0";
    private static CountDownTimer mCountDownTimer;
    public static int outWinState;
    private final Context mContext;
    private MsgMgr mMsgMgr;
    SettingPageUiSet settingPageUiSet;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private int panoramicState = 0;
    int appointmentYear = 2021;
    int appointmentMonth = 1;
    int appointmentDay = 1;
    int appointmentHours = 0;
    int appointmentMins = 0;
    String CAR_TYPE = CarImageView.CARTYPE_Main;
    private final int IS_SWM_G01_EACH_CAN_ID = 25;
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._327.UiMgr.4
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.frontLeftTempAdjust(2);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.frontLeftTempAdjust(1);
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._327.UiMgr.5
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.frontRightTempAdjust(2);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.frontRightTempAdjust(1);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._327.UiMgr.6
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 1) {
                UiMgr.this.sendModuleTwo(1);
            } else if (i == 2) {
                UiMgr.this.sendModuleZero(128);
            } else {
                if (i != 3) {
                    return;
                }
                UiMgr.this.sendModuleOne(4);
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._327.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendModuleZero(32);
            } else {
                if (i != 1) {
                    return;
                }
                UiMgr.this.sendModuleZero(32);
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._327.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendModuleZero(2);
            } else {
                if (i != 1) {
                    return;
                }
                UiMgr.this.sendModuleZero(1);
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._327.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendModuleZero(16);
            } else {
                if (i != 4) {
                    return;
                }
                UiMgr.this.sendModuleOne(4);
            }
        }
    };
    OnAirSeatClickListener onAirSeatClickListener = new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._327.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onLeftSeatClick() {
            int i = UiMgr.outWinState;
            if (i == 0) {
                UiMgr.this.sendModuleTwo(32);
                UiMgr.outWinState = 1;
                return;
            }
            if (i == 1) {
                UiMgr.this.sendModuleTwo(64);
                UiMgr.outWinState = 2;
                return;
            }
            if (i == 2) {
                UiMgr.this.sendModuleTwo(96);
                UiMgr.outWinState = 3;
                return;
            }
            if (i == 3) {
                UiMgr.this.sendModuleTwo(128);
                UiMgr.outWinState = 4;
            } else if (i == 4) {
                UiMgr.this.sendModuleTwo(160);
                UiMgr.outWinState = 5;
            } else {
                if (i != 5) {
                    return;
                }
                UiMgr.this.sendModuleTwo(192);
                UiMgr.outWinState = 0;
            }
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onRightSeatClick() {
            int i = UiMgr.outWinState;
            if (i == 0) {
                UiMgr.this.sendModuleTwo(32);
                UiMgr.outWinState = 1;
                return;
            }
            if (i == 1) {
                UiMgr.this.sendModuleTwo(64);
                UiMgr.outWinState = 2;
                return;
            }
            if (i == 2) {
                UiMgr.this.sendModuleTwo(96);
                UiMgr.outWinState = 3;
                return;
            }
            if (i == 3) {
                UiMgr.this.sendModuleTwo(128);
                UiMgr.outWinState = 4;
            } else if (i == 4) {
                UiMgr.this.sendModuleTwo(160);
                UiMgr.outWinState = 5;
            } else {
                if (i != 5) {
                    return;
                }
                UiMgr.this.sendModuleTwo(192);
                UiMgr.outWinState = 0;
            }
        }
    };
    OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._327.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            UiMgr.this.sendModuleOne(1);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            UiMgr.this.sendModuleOne(2);
        }
    };
    OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatFrontLeft = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._327.UiMgr.12
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr.this.sendModuleFive(1);
        }
    };
    OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatFrontRight = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._327.UiMgr.13
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr.this.sendModuleFive(2);
        }
    };
    OnPanoramicItemClickListener panoramicItemClickListener = new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._327.UiMgr.14
        @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                if (UiMgr.this.panoramicState != 4) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -89, 4});
                    UiMgr.this.panoramicState = 4;
                    return;
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, -89, 0});
                    UiMgr.this.panoramicState = 0;
                    return;
                }
            }
            if (i == 1) {
                if (UiMgr.this.panoramicState != 5) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -89, 5});
                    UiMgr.this.panoramicState = 5;
                    return;
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, -89, 0});
                    UiMgr.this.panoramicState = 0;
                    return;
                }
            }
            if (i == 2) {
                if (UiMgr.this.panoramicState != 6) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -89, 6});
                    UiMgr.this.panoramicState = 6;
                    return;
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, -89, 0});
                    UiMgr.this.panoramicState = 0;
                    return;
                }
            }
            if (i == 3) {
                if (UiMgr.this.panoramicState != 7) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -89, 7});
                    UiMgr.this.panoramicState = 7;
                    return;
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, -89, 0});
                    UiMgr.this.panoramicState = 0;
                    return;
                }
            }
            if (i != 4) {
                return;
            }
            if (UiMgr.this.panoramicState != 8) {
                CanbusMsgSender.sendMsg(new byte[]{22, -89, 8});
                UiMgr.this.panoramicState = 8;
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, -89, 0});
                UiMgr.this.panoramicState = 0;
            }
        }
    };
    OnPanelKeyPositionListener panelKeyPositionListener = new OnPanelKeyPositionListener() { // from class: com.hzbhd.canbus.car._327.UiMgr.15
        @Override // com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener
        public void click(int i) {
            if (i == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -48, 1, 0});
                Toast.makeText(UiMgr.this.mContext, "播放原车CD音源", Toast.LENGTH_SHORT).show();
                return;
            }
            if (i == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -48, 0, 0});
                Toast.makeText(UiMgr.this.mContext, "播放非原车CD", android.widget.Toast.LENGTH_SHORT).show();
            } else if (i == 2) {
                CanbusMsgSender.sendMsg(new byte[]{22, -48, 1, 1});
                Toast.makeText(UiMgr.this.mContext, "音量 +", android.widget.Toast.LENGTH_SHORT).show();
            } else {
                if (i != 3) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -48, 1, 2});
                Toast.makeText(UiMgr.this.mContext, "音量 -", android.widget.Toast.LENGTH_SHORT).show();
            }
        }
    };
    private final OnSettingItemClickListener mOnSettingItemClickListener = new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._327.UiMgr.16
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
        public void onClickItem(int i, int i2) {
        }
    };
    private final OnSettingItemSelectListener mOnSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._327.UiMgr.17
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int sectionId, int category, int itemSelected) {
            String titleSrn = UiMgr.this.settingPageUiSet.getList().get(sectionId).getTitleSrn();
            switch (titleSrn) {
                case "_327_setting_atmosphere_lamp":
                    if (category == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -108, (byte) itemSelected});
                        break;
                    }
                    break;
                case "_327_car_type":
                    if (category == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -18, -112, (byte) itemSelected});
                        SharePreUtil.setIntValue(UiMgr.this.mContext, UiMgr.this.CAR_TYPE, itemSelected);
                        UiMgr uiMgr = UiMgr.this;
                        uiMgr.getmMsgMgr(uiMgr.mContext).updateSettings(UiMgr.this.mContext, UiMgr.L7R0, sectionId, category, itemSelected);
                        break;
                    }
                    break;
                case "_327_source_setting":
                    if (category == 0) {
                        if (itemSelected == 0) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -48, 0, 0});
                            Toast.makeText(UiMgr.this.mContext, "播放非原车CD", Toast.LENGTH_SHORT).show();
                            break;
                        } else {
                            CanbusMsgSender.sendMsg(new byte[]{22, -48, 1, 0});
                            Toast.makeText(UiMgr.this.mContext, "播放原车CD音源", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                    break;
                case "_327_function_mode_selection":
                    if (category == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, (byte) itemSelected});
                    }
                    if (category == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -92, (byte) (itemSelected + 1)});
                    }
                    if (category == 2) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -78, (byte) itemSelected});
                    }
                    if (category == 3) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -76, (byte) itemSelected});
                    }
                    if (category == 4) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -70, (byte) itemSelected});
                    }
                    if (category == 5) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -69, (byte) itemSelected});
                    }
                    if (category == 6) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -66, (byte) itemSelected});
                        break;
                    }
                    break;
                case "_327_setting_company":
                    if (category == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -110, (byte) itemSelected});
                        UiMgr uiMgr2 = UiMgr.this;
                        uiMgr2.getmMsgMgr(uiMgr2.mContext).updateSettings(UiMgr.this.mContext, UiMgr.L0R0, sectionId, category, itemSelected);
                        break;
                    }
                    break;
                case "_327_function_switch":
                    if (category == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -94, (byte) (itemSelected + 1)});
                    }
                    if (category == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte) (itemSelected + 1)});
                    }
                    if (category == 2) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -91, (byte) (itemSelected + 1)});
                    }
                    if (category == 3) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -90, (byte) (itemSelected + 1)});
                    }
                    if (category == 4) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -89, (byte) (itemSelected + 1)});
                    }
                    if (category == 5) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -88, (byte) (itemSelected + 1)});
                    }
                    if (category == 6) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -87, (byte) (itemSelected + 1)});
                    }
                    if (category == 7) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -86, (byte) (itemSelected + 1)});
                    }
                    if (category == 8) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -85, (byte) (itemSelected + 1)});
                    }
                    if (category == 9) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, (byte) (itemSelected + 1)});
                    }
                    if (category == 10) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -83, (byte) (itemSelected + 1)});
                    }
                    if (category == 11) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -82, (byte) (itemSelected + 1)});
                    }
                    if (category == 12) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -81, (byte) (itemSelected + 1)});
                    }
                    if (category == 13) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -80, (byte) (itemSelected + 1)});
                    }
                    if (category == 14) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -79, (byte) (itemSelected + 1)});
                    }
                    if (category == 15) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -77, (byte) (itemSelected + 1)});
                    }
                    if (category == 16) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -74, (byte) (itemSelected + 1)});
                    }
                    if (category == 17) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -73, (byte) (itemSelected + 1)});
                    }
                    if (category == 18) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -72, (byte) (itemSelected + 1)});
                    }
                    if (category == 19) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -57, (byte) (itemSelected + 1)});
                    }
                    if (category == 20) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -71, (byte) (itemSelected + 1)});
                    }
                    if (category == 21) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -68, (byte) (itemSelected + 1)});
                    }
                    if (category == 22) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -67, (byte) (itemSelected + 1)});
                    }
                    if (category == 23) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -65, (byte) (itemSelected + 1)});
                    }
                    if (category == 24) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -64, (byte) (itemSelected + 1)});
                    }
                    if (category == 25) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -63, (byte) (itemSelected + 1)});
                    }
                    if (category == 26) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -62, (byte) (itemSelected + 1)});
                    }
                    if (category == 27) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -61, (byte) (itemSelected + 1)});
                        break;
                    }
                    break;
                case "_327_setting_chair":
                    if (category == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -109, (byte) itemSelected});
                        break;
                    }
                    break;
                case "_327_setting_the_headlamps":
                    if (category == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -104, (byte) itemSelected});
                        break;
                    }
                    break;
                case "_327_setting_lane_departure":
                    if (category == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -105, (byte) itemSelected});
                        break;
                    }
                    break;
                case "_327_setting_environmental_lighting":
                    if (category == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -103, (byte) itemSelected});
                    }
                    if (category == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -102, (byte) (itemSelected + 1)});
                    }
                    if (category == 2) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -101, (byte) (itemSelected + 1)});
                        break;
                    }
                    break;
                case "_327_Face_recognition":
                    if (category == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -60, (byte) (itemSelected + 1)});
                    }
                    if (category == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -59, (byte) itemSelected});
                        break;
                    }
                    break;
                case "_327_setting_the_light_that_accompanies_me_home":
                    if (category == 0) {
                        if (itemSelected == 1) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -58, -106, -122});
                            UiMgr.this.MyCountDownTimer(sectionId, category + 1, 120000, 1000);
                            break;
                        } else {
                            CanbusMsgSender.sendMsg(new byte[]{22, -58, -106, 0});
                            UiMgr.this.MyCountDownTimer(sectionId, category + 1, 1000, 1000);
                            break;
                        }
                    }
                    break;
            }
        }
    };
    private final OnSettingItemSeekbarSelectListener mOnSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener() {

        @Override
        public void onClickItem(int n, int n2, int n3) {
            String title = settingPageUiSet.getList().get(n).getTitleSrn();
            String itemTitle = settingPageUiSet.getList().get(n).getItemList().get(n2).getTitleSrn();

            switch (title) {
                case "_327_setting_the_light_that_accompanies_me_home":
                    if (!itemTitle.equals("_327_setting_the_light_that_accompanies_me_home"))
                        return;

                    int[] durations = {
                            1000, 20000, 40000, 60000, 80000, 100000, 120000,
                            140000, 160000, 180000, 200000, 220000, 240000,
                            260000, 280000
                    };

                    if (n3 >= 0 && n3 < durations.length) {
                        byte durationByte = (byte) (-128 + n3 + 1);
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -106, durationByte});
                        MyCountDownTimer(n, n2, durations[n3], 1000);
                    }
                    break;

                case "_327_Reserve_charging":
                    switch (itemTitle) {
                        case "_327_day_setting":
                            appointmentDay = n3;
                            break;
                        case "_327_month_setting":
                            appointmentMonth = n3;
                            break;
                        case "_327_Hours_setting":
                            appointmentHours = n3;
                            break;
                        case "_327_branch_setting":
                            appointmentMins = n3;
                            break;
                        case "_327_year_setting":
                            appointmentYear = n3;
                            break;
                        default:
                            return;
                    }
                    getmMsgMgr(mContext).updateAppointmentSettings(n, n2, n3);
                    break;

                case "_327_function_level_setting":
                    if (itemTitle.equals("_327_Setting_SOC_target_value_of_charging_management")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -75, (byte) n3});
                    } else if (itemTitle.equals("_327_Energy_feedback_level")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -96, (byte) n3});
                    }
                    break;

                case "_327_setting_atmosphere_lamp":
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, -107, (byte) n3});
                    break;

                default:
                    // No acción si el título no coincide
                    break;
            }
        }
    };
    private final OnConfirmDialogListener confirmDialogListener = new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._327.UiMgr.19
        @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
        public void onConfirmClick(int i, int i2) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getLeftIndexes(uiMgr.mContext, "_327_Reserve_charging") && i2 == 5 && UiMgr.this.compareTime()) {
                UiMgr uiMgr2 = UiMgr.this;
                UiMgr uiMgr3 = UiMgr.this;
                UiMgr uiMgr4 = UiMgr.this;
                UiMgr uiMgr5 = UiMgr.this;
                UiMgr uiMgr6 = UiMgr.this;
                UiMgr uiMgr7 = UiMgr.this;
                CanbusMsgSender.sendMsg(new byte[]{22, -90, (byte) uiMgr2.getmMsgMgr(uiMgr2.mContext).nowYear, (byte) uiMgr3.getmMsgMgr(uiMgr3.mContext).nowMonth, (byte) uiMgr4.getmMsgMgr(uiMgr4.mContext).nowDay, (byte) uiMgr5.getmMsgMgr(uiMgr5.mContext).nowHours, (byte) uiMgr6.getmMsgMgr(uiMgr6.mContext).nowMins, (byte) uiMgr7.getmMsgMgr(uiMgr7.mContext).nowSecond});
                CanbusMsgSender.sendMsg(new byte[]{22, -122, 4, (byte) (UiMgr.this.appointmentYear - 2000), (byte) UiMgr.this.appointmentMonth, (byte) UiMgr.this.appointmentDay, (byte) UiMgr.this.appointmentHours, (byte) UiMgr.this.appointmentMins});
                Toast.makeText(UiMgr.this.mContext, "预约成功！时间：" + UiMgr.this.appointmentYear + "年" + UiMgr.this.appointmentMonth + "月" + UiMgr.this.appointmentDay + "日" + UiMgr.this.appointmentHours + ":" + UiMgr.this.appointmentMins, Toast.LENGTH_SHORT).show();
            }
        }
    };
    private final int differentCanId = getCurrentCarId();
    private final int eachCanId = getEachId();

    public UiMgr(Context context) {
        this.mContext = context;
        CartypeSend();
        if (this.differentCanId == 3) {
            getAirUiSet(context).setHaveRearArea(true);
        }
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        this.settingPageUiSet = settingUiSet;
        settingUiSet.setOnSettingItemClickListener(this.mOnSettingItemClickListener);
        this.settingPageUiSet.setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
        this.settingPageUiSet.setOnSettingItemSeekbarSelectListener(this.mOnSettingItemSeekbarSelectListener);
        this.settingPageUiSet.setOnSettingConfirmDialogListener(this.confirmDialogListener);
        this.settingPageUiSet.setOnSettingPageStatusListener(new OnSettingPageStatusListener() { // from class: com.hzbhd.canbus.car._327.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnSettingPageStatusListener
            public void onStatusChange(int i) {
                CanbusMsgSender.sendMsg(new byte[]{22, -112, 65});
                CanbusMsgSender.sendMsg(new byte[]{22, -112, 66});
            }
        });
        AirPageUiSet airUiSet = getAirUiSet(context);
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
        airUiSet.getFrontArea().setOnAirSeatClickListener(this.onAirSeatClickListener);
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.onAirWindSpeedUpDownClickListener);
        airUiSet.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.mOnMinPlusClickListenerHeatFrontLeft, this.mOnMinPlusClickListenerHeatFrontRight, null, null});
        airUiSet.setOnAirPageStatusListener(new OnAirPageStatusListener() { // from class: com.hzbhd.canbus.car._327.UiMgr.2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener
            public void onStatusChange(int i) {
                CanbusMsgSender.sendMsg(new byte[]{22, -112, 33});
                CanbusMsgSender.sendMsg(new byte[]{22, -112, 39});
            }
        });
        getParkPageUiSet(context).setOnPanoramicItemClickListener(this.panoramicItemClickListener);
        getPanelKeyPageUiSet(context).setOnPanelKeyPositionListener(this.panelKeyPositionListener);
        getDriverDataPageUiSet(this.mContext).setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener() { // from class: com.hzbhd.canbus.car._327.UiMgr.3
            @Override // com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener
            public void onStatusChange(int i) {
                CanbusMsgSender.sendMsg(new byte[]{22, -112, 56});
                CanbusMsgSender.sendMsg(new byte[]{22, -112, 57});
                CanbusMsgSender.sendMsg(new byte[]{22, -112, 64});
            }
        });
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        initSettingsUi(context);
        initSettingsData(context);
    }

    private void initSettingsUi(Context context) {
        if (this.eachCanId == 14) {
            removeMainPrjBtnByName(this.mContext, MainAction.TIRE_INFO);
        }
        if (this.eachCanId != 5) {
            removeSettingLeftItemByNameList(this.mContext, new String[]{"_327_setting_lane_departure"});
        }
        int i = this.eachCanId;
        if (i != 7 && i != 14) {
            removeSettingLeftItemByNameList(this.mContext, new String[]{"_327_setting_the_headlamps"});
        }
        if (this.differentCanId != 3) {
            removeMainPrjBtnByName(this.mContext, MainAction.PANEL_KEY);
        }
        int i2 = this.eachCanId;
        if (i2 != 2 && i2 != 3 && i2 != 5 && i2 != 6 && i2 != 7 && i2 != 12 && i2 != 13 && i2 != 14) {
            removeSettingLeftItemByNameList(this.mContext, new String[]{"_327_setting_environmental_lighting"});
        }
        int i3 = this.eachCanId;
        if (i3 != 8 && i3 != 11) {
            removeMainPrjBtn(context, 3, 3);
        } else {
            if (i3 == 8) {
                removeDriveData(context, "_327_Vehicle_information");
            }
            if (this.eachCanId == 11) {
                removeDriveData(context, "_327_operation_information");
            }
        }
        if (this.eachCanId != 11) {
            removeSettingLeftItemByNameList(this.mContext, new String[]{"_327_function_switch", "_327_function_level_setting", "_327_function_mode_selection", "_327_Face_recognition", "_327_Reserve_charging"});
        } else {
            removeSettingLeftItemByNameList(this.mContext, new String[]{"_327_setting_company", "_327_setting_chair", "_327_setting_atmosphere_lamp", "_327_setting_the_light_that_accompanies_me_home", "_327_setting_lane_departure", "_327_setting_the_headlamps", "_327_setting_environmental_lighting", "_327_car_type"});
        }
        if (this.eachCanId == 5) {
            getParkPageUiSet(context).setShowPanoramic(true);
        }
    }

    private void initSettingsData(Context context) {
        if (getLeftIndexes(context, "_327_setting_company") != -1) {
            getmMsgMgr(this.mContext).updateSettings(this.mContext, L0R0, getLeftIndexes(context, "_327_setting_company"), 0, SharePreUtil.getIntValue(this.mContext, L0R0, 0));
        }
        int leftIndexes = getLeftIndexes(context, "_327_setting_chair");
        if (leftIndexes != -1) {
            MsgMgr msgMgr = getmMsgMgr(this.mContext);
            Context context2 = this.mContext;
            msgMgr.updateSettings(context2, L1R0, leftIndexes, 0, SharePreUtil.getIntValue(context2, L1R0, 0));
        }
        int leftIndexes2 = getLeftIndexes(context, "_327_setting_atmosphere_lamp");
        if (leftIndexes2 != -1) {
            MsgMgr msgMgr2 = getmMsgMgr(this.mContext);
            Context context3 = this.mContext;
            msgMgr2.updateSettings(context3, L2R1, leftIndexes2, 1, SharePreUtil.getIntValue(context3, L2R1, 0));
        }
        int leftIndexes3 = getLeftIndexes(context, "_327_setting_the_light_that_accompanies_me_home");
        if (leftIndexes3 != -1) {
            MsgMgr msgMgr3 = getmMsgMgr(this.mContext);
            Context context4 = this.mContext;
            msgMgr3.updateSettings(context4, L3R1, leftIndexes3, 1, SharePreUtil.getIntValue(context4, L3R1, 0));
        }
        getLeftIndexes(context, "_327_setting_lane_departure");
        int leftIndexes4 = getLeftIndexes(context, "_327_setting_the_headlamps");
        if (leftIndexes4 != -1) {
            MsgMgr msgMgr4 = getmMsgMgr(this.mContext);
            Context context5 = this.mContext;
            msgMgr4.updateSettings(context5, L5R0, leftIndexes4, 0, SharePreUtil.getIntValue(context5, L5R0, 0));
        }
        int leftIndexes5 = getLeftIndexes(context, "_327_setting_environmental_lighting");
        if (leftIndexes5 != -1) {
            MsgMgr msgMgr5 = getmMsgMgr(this.mContext);
            Context context6 = this.mContext;
            msgMgr5.updateSettings(context6, L6R1, leftIndexes5, 1, SharePreUtil.getIntValue(context6, L6R1, 0));
            MsgMgr msgMgr6 = getmMsgMgr(this.mContext);
            Context context7 = this.mContext;
            msgMgr6.updateSettings(context7, L6R2, leftIndexes5, 2, SharePreUtil.getIntValue(context7, L6R2, 0));
        }
        int leftIndexes6 = getLeftIndexes(context, "_327_car_type");
        if (leftIndexes6 != -1) {
            if (getEachId() == 0) {
                MsgMgr msgMgr7 = getmMsgMgr(this.mContext);
                Context context8 = this.mContext;
                msgMgr7.updateSettings(context8, L7R0, leftIndexes6, 0, SharePreUtil.getIntValue(context8, L7R0, 2));
            } else if (getEachId() == 3) {
                MsgMgr msgMgr8 = getmMsgMgr(this.mContext);
                Context context9 = this.mContext;
                msgMgr8.updateSettings(context9, L7R0, leftIndexes6, 0, SharePreUtil.getIntValue(context9, L7R0, 1));
            } else if (getEachId() == 4) {
                MsgMgr msgMgr9 = getmMsgMgr(this.mContext);
                Context context10 = this.mContext;
                msgMgr9.updateSettings(context10, L7R0, leftIndexes6, 0, SharePreUtil.getIntValue(context10, L7R0, 0));
            }
        }
        int leftIndexes7 = getLeftIndexes(this.mContext, "_327_Reserve_charging");
        if (leftIndexes7 != -1) {
            String str = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));
            this.appointmentYear = Integer.parseInt(str.substring(0, 4));
            this.appointmentMonth = Integer.parseInt(str.substring(4, 6));
            this.appointmentDay = Integer.parseInt(str.substring(6, 8));
            this.appointmentHours = Integer.parseInt(str.substring(8, 10));
            this.appointmentMins = Integer.parseInt(str.substring(10, 12));
            getmMsgMgr(context).updateAppointmentSettings(leftIndexes7, 0, Integer.parseInt(str.substring(0, 4)));
            getmMsgMgr(context).updateAppointmentSettings(leftIndexes7, 1, Integer.parseInt(str.substring(4, 6)));
            getmMsgMgr(context).updateAppointmentSettings(leftIndexes7, 2, Integer.parseInt(str.substring(6, 8)));
            getmMsgMgr(context).updateAppointmentSettings(leftIndexes7, 3, Integer.parseInt(str.substring(8, 10)));
            getmMsgMgr(context).updateAppointmentSettings(leftIndexes7, 4, Integer.parseInt(str.substring(10, 12)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendModuleZero(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -57, (byte) i, 0, 0, 0, 0, 0});
        this.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._327.UiMgr$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public void run() {
                CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, 0, 0, 0, 0, 0});
            }
        }, 100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendModuleOne(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, (byte) i, 0, 0, 0, 0});
        this.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._327.UiMgr$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public void run() {
                CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, 0, 0, 0, 0, 0});
            }
        }, 100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendModuleTwo(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, 0, (byte) i, 0, 0, 0});
        this.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._327.UiMgr$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public void run() {
                CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, 0, 0, 0, 0, 0});
            }
        }, 100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void frontLeftTempAdjust(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, 0, 0, (byte) i, 0, 0});
        this.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._327.UiMgr$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public void run() {
                CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, 0, 0, 0, 0, 0});
            }
        }, 100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void frontRightTempAdjust(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, 0, 0, 0, (byte) i, 0});
        this.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._327.UiMgr$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public void run() {
                CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, 0, 0, 0, 0, 0});
            }
        }, 100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendModuleFive(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, 0, 0, 0, 0, (byte) i});
        this.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._327.UiMgr$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public void run() {
                CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, 0, 0, 0, 0, 0});
            }
        }, 100L);
    }

    public MsgMgr getmMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
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

    /* JADX WARN: Type inference failed for: r0v1, types: [com.hzbhd.canbus.car._327.UiMgr$20] */
    public void MyCountDownTimer(final int i, final int i2, int i3, int i4) {
        CountDownTimer countDownTimer = mCountDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        mCountDownTimer = new CountDownTimer(i3, i4) { // from class: com.hzbhd.canbus.car._327.UiMgr.20
            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                UiMgr uiMgr = UiMgr.this;
                uiMgr.getmMsgMgr(uiMgr.mContext).countDownTimeUpdateSettings(i, i2, (j / 1000) + 1);
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -106, 0});
            }
        }.start();
    }

    public boolean compareTime() {
        if (this.appointmentYear == getmMsgMgr(this.mContext).nowYear && this.appointmentMonth == getmMsgMgr(this.mContext).nowMonth) {
            if ((((((this.appointmentDay - 1) * 24) * 60) + ((this.appointmentHours - 1) * 60)) + this.appointmentMins) - (((((getmMsgMgr(this.mContext).nowDay - 1) * 24) * 60) + ((getmMsgMgr(this.mContext).nowHours - 1) * 60)) + getmMsgMgr(this.mContext).nowMins) <= 2880) {
                return true;
            }
            Toast.makeText(this.mContext, "预约充电时间不能超过当前时间48小时", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (this.appointmentYear < getmMsgMgr(this.mContext).nowYear) {
            Toast.makeText(this.mContext, "指定时间不在预约范围内", android.widget.Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this.mContext, "预约充电时间不能超过当前时间48小时", android.widget.Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public void CartypeSend() {
        CanbusMsgSender.sendMsg(new byte[]{22, -18, -112, (byte) SharePreUtil.getIntValue(this.mContext, this.CAR_TYPE, 0)});
    }
}
