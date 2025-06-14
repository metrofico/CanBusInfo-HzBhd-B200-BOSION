package com.hzbhd.canbus.car._68;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.interfaces.OnHybirdPageStatusListener;
import com.hzbhd.canbus.interfaces.OnOnStarClickListener;
import com.hzbhd.canbus.interfaces.OnOnStartStatusChangeListener;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionTouchListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralOnStartData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.OnStartPageUiSet;
import com.hzbhd.canbus.ui_set.PanelKeyPageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.TimerUtil;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.text.Typography;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    AirPageUiSet airPageUiSet;
    private Context mContext;
    private MsgMgr mMsgMgr;
    private MyPanoramicView mPanoramicView;
    OnAirTemperatureUpDownClickListener mUpDownTempCenter = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._68.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            if (!UiMgr.this.isPortrateScreen()) {
                UiMgr.this.sendAirKeyMsg82(7, 97);
            } else {
                UiMgr.this.sendAirKeyMsgE0(51);
            }
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            if (!UiMgr.this.isPortrateScreen()) {
                UiMgr.this.sendAirKeyMsg82(7, 98);
            } else {
                UiMgr.this.sendAirKeyMsgE0(50);
            }
        }
    };
    OnAirWindSpeedUpDownClickListener mOnAirWindSpeedUpDownClickListenerRear = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._68.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            if (!UiMgr.this.isPortrateScreen()) {
                UiMgr.this.sendAirKeyMsg82(7, 100);
            } else {
                UiMgr.this.sendAirKeyMsgE0(48);
            }
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            if (!UiMgr.this.isPortrateScreen()) {
                UiMgr.this.sendAirKeyMsg82(7, 99);
            } else {
                UiMgr.this.sendAirKeyMsgE0(49);
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerRearTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._68.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                if (!UiMgr.this.isPortrateScreen()) {
                    UiMgr.this.sendAirKeyMsg82(7, 101);
                    return;
                } else {
                    UiMgr.this.sendAirKeyMsgE0(53);
                    return;
                }
            }
            if (i != 1) {
                return;
            }
            if (!UiMgr.this.isPortrateScreen()) {
                UiMgr.this.sendAirKeyMsg82(7, 103);
            } else {
                UiMgr.this.sendAirKeyMsgE0(55);
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerRearBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._68.UiMgr.12
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                if (UiMgr.this.isPortrateScreen()) {
                    return;
                }
                UiMgr.this.sendAirKeyMsg82(7, 107);
            } else if (i == 1) {
                if (UiMgr.this.isPortrateScreen()) {
                    return;
                }
                UiMgr.this.sendAirKeyMsg82(7, 105);
            } else {
                if (i != 2) {
                    return;
                }
                if (!UiMgr.this.isPortrateScreen()) {
                    UiMgr.this.sendAirKeyMsg82(7, 104);
                } else {
                    UiMgr.this.sendAirKeyMsgE0(52);
                }
            }
        }
    };
    OnAirSeatHeatColdMinPlusClickListener mOnleftSeatCold = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._68.UiMgr.13
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 7, 23});
            UiMgr.this.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._68.UiMgr.13.1
                @Override // java.lang.Runnable
                public void run() {
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, 7, 0});
                }
            }, 50L);
        }
    };
    OnAirSeatHeatColdMinPlusClickListener mOnrightSeatCold = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._68.UiMgr.14
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 7, 25});
            UiMgr.this.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._68.UiMgr.14.1
                @Override // java.lang.Runnable
                public void run() {
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, 7, 0});
                }
            }, 50L);
        }
    };
    OnAirSeatHeatColdMinPlusClickListener mOnLeftSeatHeat = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._68.UiMgr.15
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            if (GeneralAirData.front_left_seat_heat_level == 0) {
                if (!UiMgr.this.isPortrateScreen()) {
                    UiMgr.this.sendAirKeyMsg82(7, 22);
                } else {
                    UiMgr.this.sendAirKeyMsgE0(11);
                }
            }
            if (GeneralAirData.front_left_seat_heat_level == 1) {
                if (!UiMgr.this.isPortrateScreen()) {
                    UiMgr.this.sendAirKeyMsg82(7, 22);
                } else {
                    UiMgr.this.sendAirKeyMsgE0(11);
                }
            }
            if (GeneralAirData.front_left_seat_heat_level == 2) {
                if (!UiMgr.this.isPortrateScreen()) {
                    UiMgr.this.sendAirKeyMsg82(7, 22);
                } else {
                    UiMgr.this.sendAirKeyMsgE0(11);
                }
            }
            if (GeneralAirData.front_left_seat_heat_level == 3) {
                if (!UiMgr.this.isPortrateScreen()) {
                    UiMgr.this.sendAirKeyMsg82(7, 22);
                } else {
                    UiMgr.this.sendAirKeyMsgE0(11);
                }
            }
        }
    };
    OnAirSeatHeatColdMinPlusClickListener mOnRightSeatHeat = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._68.UiMgr.16
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            if (GeneralAirData.front_right_seat_heat_level == 0) {
                if (!UiMgr.this.isPortrateScreen()) {
                    UiMgr.this.sendAirKeyMsg82(7, 24);
                } else {
                    UiMgr.this.sendAirKeyMsgE0(14);
                }
            }
            if (GeneralAirData.front_right_seat_heat_level == 1) {
                if (!UiMgr.this.isPortrateScreen()) {
                    UiMgr.this.sendAirKeyMsg82(7, 24);
                } else {
                    UiMgr.this.sendAirKeyMsgE0(14);
                }
            }
            if (GeneralAirData.front_right_seat_heat_level == 2) {
                if (!UiMgr.this.isPortrateScreen()) {
                    UiMgr.this.sendAirKeyMsg82(7, 24);
                } else {
                    UiMgr.this.sendAirKeyMsgE0(14);
                }
            }
            if (GeneralAirData.front_right_seat_heat_level == 3) {
                if (!UiMgr.this.isPortrateScreen()) {
                    UiMgr.this.sendAirKeyMsg82(7, 24);
                } else {
                    UiMgr.this.sendAirKeyMsgE0(14);
                }
            }
        }
    };
    OnAirWindSpeedUpDownClickListener mOnAirWindSpeedUpDownClickListenerFront = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._68.UiMgr.17
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            if (!UiMgr.this.isPortrateScreen()) {
                UiMgr.this.sendAirKeyMsg82(7, 7);
            } else {
                UiMgr.this.sendAirKeyMsgE0(9);
            }
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            if (!UiMgr.this.isPortrateScreen()) {
                UiMgr.this.sendAirKeyMsg82(7, 6);
            } else {
                UiMgr.this.sendAirKeyMsgE0(10);
            }
        }
    };
    OnAirTemperatureUpDownClickListener onUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._68.UiMgr.18
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            if (!UiMgr.this.isPortrateScreen()) {
                UiMgr.this.sendAirKeyMsg82(7, 4);
            } else {
                UiMgr.this.sendAirKeyMsgE0(3);
            }
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            if (!UiMgr.this.isPortrateScreen()) {
                UiMgr.this.sendAirKeyMsg82(7, 5);
            } else {
                UiMgr.this.sendAirKeyMsgE0(2);
            }
        }
    };
    OnAirTemperatureUpDownClickListener onUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._68.UiMgr.19
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            if (!UiMgr.this.isPortrateScreen()) {
                UiMgr.this.sendAirKeyMsg82(7, 20);
            } else {
                UiMgr.this.sendAirKeyMsgE0(5);
            }
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            if (!UiMgr.this.isPortrateScreen()) {
                UiMgr.this.sendAirKeyMsg82(7, 21);
            } else {
                UiMgr.this.sendAirKeyMsgE0(4);
            }
        }
    };
    private OnHybirdPageStatusListener mOnHybirdPageStatusListener = new OnHybirdPageStatusListener() { // from class: com.hzbhd.canbus.car._68.UiMgr.20
        @Override // com.hzbhd.canbus.interfaces.OnHybirdPageStatusListener
        public void onStatusChange(int i) {
        }
    };
    private OnSettingPageStatusListener mOnSettingPageStatusListener = new OnSettingPageStatusListener() { // from class: com.hzbhd.canbus.car._68.UiMgr.21
        @Override // com.hzbhd.canbus.interfaces.OnSettingPageStatusListener
        public void onStatusChange(int i) {
        }
    };
    private OnSettingItemSelectListener mOnSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._68.UiMgr.22
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            String titleSrn = uiMgr.getSettingUiSet(uiMgr.mContext).getList().get(i).getItemList().get(i2).getTitleSrn();
            titleSrn.hashCode();
            char c = 65535;
            switch (titleSrn.hashCode()) {
                case -2067859824:
                    if (titleSrn.equals("adapter_front_light")) {
                        c = 0;
                        break;
                    }
                    break;
                case -2044134219:
                    if (titleSrn.equals("personalization_by_driver")) {
                        c = 1;
                        break;
                    }
                    break;
                case -2022373321:
                    if (titleSrn.equals("auto_mode_wind_set")) {
                        c = 2;
                        break;
                    }
                    break;
                case -2003824814:
                    if (titleSrn.equals("_61_radar_switch")) {
                        c = 3;
                        break;
                    }
                    break;
                case -1949342696:
                    if (titleSrn.equals("prevent_open_door_auto_lock")) {
                        c = 4;
                        break;
                    }
                    break;
                case -1775953625:
                    if (titleSrn.equals("remote_window_control")) {
                        c = 5;
                        break;
                    }
                    break;
                case -1647100313:
                    if (titleSrn.equals("delay_lock")) {
                        c = 6;
                        break;
                    }
                    break;
                case -1627575416:
                    if (titleSrn.equals("leave_car_auto_lock")) {
                        c = 7;
                        break;
                    }
                    break;
                case -1614395726:
                    if (titleSrn.equals("air_quality_sensor_set")) {
                        c = '\b';
                        break;
                    }
                    break;
                case -1538471658:
                    if (titleSrn.equals("convenience_get_off_option")) {
                        c = '\t';
                        break;
                    }
                    break;
                case -1535502006:
                    if (titleSrn.equals("_68_Pollution_control")) {
                        c = '\n';
                        break;
                    }
                    break;
                case -1466177363:
                    if (titleSrn.equals("air_launcher_mode")) {
                        c = 11;
                        break;
                    }
                    break;
                case -1388141665:
                    if (titleSrn.equals("rear_seat_tis")) {
                        c = '\f';
                        break;
                    }
                    break;
                case -1382834175:
                    if (titleSrn.equals("remote_control_seat_auto_heat")) {
                        c = '\r';
                        break;
                    }
                    break;
                case -1331709104:
                    if (titleSrn.equals("adaptive_cruise_start_reminder")) {
                        c = 14;
                        break;
                    }
                    break;
                case -1164051160:
                    if (titleSrn.equals("key_forgot_remain")) {
                        c = 15;
                        break;
                    }
                    break;
                case -1101576519:
                    if (titleSrn.equals("language_status")) {
                        c = 16;
                        break;
                    }
                    break;
                case -1045027611:
                    if (titleSrn.equals("remote_lock_again")) {
                        c = 17;
                        break;
                    }
                    break;
                case -993115198:
                    if (titleSrn.equals("lane_change_tis")) {
                        c = 18;
                        break;
                    }
                    break;
                case -743253170:
                    if (titleSrn.equals("remote_control_unlock_light_feedback")) {
                        c = 19;
                        break;
                    }
                    break;
                case -667783574:
                    if (titleSrn.equals("air_partition_temperature")) {
                        c = 20;
                        break;
                    }
                    break;
                case -599641101:
                    if (titleSrn.equals("ramp_start_assist")) {
                        c = 21;
                        break;
                    }
                    break;
                case -539410636:
                    if (titleSrn.equals("remote_start_air")) {
                        c = 22;
                        break;
                    }
                    break;
                case -526007558:
                    if (titleSrn.equals("flank_blind_zone_warning")) {
                        c = 23;
                        break;
                    }
                    break;
                case -440182422:
                    if (titleSrn.equals("lock_big_light_delay_set")) {
                        c = 24;
                        break;
                    }
                    break;
                case -393117929:
                    if (titleSrn.equals("stop_auto_unlock")) {
                        c = 25;
                        break;
                    }
                    break;
                case -333628661:
                    if (titleSrn.equals("front_work_man_check")) {
                        c = 26;
                        break;
                    }
                    break;
                case -174199675:
                    if (titleSrn.equals("remote_start_rear_air")) {
                        c = 27;
                        break;
                    }
                    break;
                case -58415930:
                    if (titleSrn.equals("seat_auto_heat")) {
                        c = 28;
                        break;
                    }
                    break;
                case -36611461:
                    if (titleSrn.equals("auto_wiper")) {
                        c = 29;
                        break;
                    }
                    break;
                case -8742296:
                    if (titleSrn.equals("sport_turn")) {
                        c = 30;
                        break;
                    }
                    break;
                case 25657870:
                    if (titleSrn.equals("remote_launcher_car")) {
                        c = 31;
                        break;
                    }
                    break;
                case 78971422:
                    if (titleSrn.equals("start_auto_lock")) {
                        c = ' ';
                        break;
                    }
                    break;
                case 207472786:
                    if (titleSrn.equals("auto_rear_view_mirror_fold")) {
                        c = '!';
                        break;
                    }
                    break;
                case 220749579:
                    if (titleSrn.equals("car_status_notify")) {
                        c = Typography.quote;
                        break;
                    }
                    break;
                case 261992690:
                    if (titleSrn.equals("find_car_light_function")) {
                        c = '#';
                        break;
                    }
                    break;
                case 371113033:
                    if (titleSrn.equals("reverse_rear_view_mirror_auto_tilt")) {
                        c = Typography.dollar;
                        break;
                    }
                    break;
                case 437771727:
                    if (titleSrn.equals("_68_add2")) {
                        c = '%';
                        break;
                    }
                    break;
                case 437771728:
                    if (titleSrn.equals("_68_add3")) {
                        c = Typography.amp;
                        break;
                    }
                    break;
                case 437771729:
                    if (titleSrn.equals("_68_add4")) {
                        c = '\'';
                        break;
                    }
                    break;
                case 437771730:
                    if (titleSrn.equals("_68_add5")) {
                        c = '(';
                        break;
                    }
                    break;
                case 437771731:
                    if (titleSrn.equals("_68_add6")) {
                        c = ')';
                        break;
                    }
                    break;
                case 437771732:
                    if (titleSrn.equals("_68_add7")) {
                        c = '*';
                        break;
                    }
                    break;
                case 529054061:
                    if (titleSrn.equals("auto_re_lock_doors")) {
                        c = '+';
                        break;
                    }
                    break;
                case 576264281:
                    if (titleSrn.equals("driver_seat_auto_identify")) {
                        c = ',';
                        break;
                    }
                    break;
                case 588625313:
                    if (titleSrn.equals("collision_or_check_tis_type")) {
                        c = '-';
                        break;
                    }
                    break;
                case 593999981:
                    if (titleSrn.equals("charging_limit")) {
                        c = '.';
                        break;
                    }
                    break;
                case 781183872:
                    if (titleSrn.equals("revers_back_wiper_auto_launcher")) {
                        c = '/';
                        break;
                    }
                    break;
                case 932962796:
                    if (titleSrn.equals("auto_prevent_ready")) {
                        c = '0';
                        break;
                    }
                    break;
                case 1137302718:
                    if (titleSrn.equals("_68_Automatically_unlock_near_car")) {
                        c = '1';
                        break;
                    }
                    break;
                case 1172774528:
                    if (titleSrn.equals("remote_unlock_set")) {
                        c = '2';
                        break;
                    }
                    break;
                case 1321211278:
                    if (titleSrn.equals("close_unlock_set")) {
                        c = '3';
                        break;
                    }
                    break;
                case 1439613768:
                    if (titleSrn.equals("remote_start_seat_cold")) {
                        c = '4';
                        break;
                    }
                    break;
                case 1439752788:
                    if (titleSrn.equals("remote_start_seat_heat")) {
                        c = '5';
                        break;
                    }
                    break;
                case 1578036129:
                    if (titleSrn.equals("back_windows_auto_defog")) {
                        c = '6';
                        break;
                    }
                    break;
                case 1690202822:
                    if (titleSrn.equals("call_memory_position")) {
                        c = '7';
                        break;
                    }
                    break;
                case 1837290941:
                    if (titleSrn.equals("driver_seat_auto_return")) {
                        c = '8';
                        break;
                    }
                    break;
                case 1851660334:
                    if (titleSrn.equals("remote_control_unlock_light_or_horn_feedback")) {
                        c = '9';
                        break;
                    }
                    break;
                case 1952102175:
                    if (titleSrn.equals("front_windows_auto_defog")) {
                        c = ':';
                        break;
                    }
                    break;
                case 2024458437:
                    if (titleSrn.equals("reverse_rear_view_mirror_help")) {
                        c = ';';
                        break;
                    }
                    break;
                case 2121005011:
                    if (titleSrn.equals("back_car_pass_tis")) {
                        c = Typography.less;
                        break;
                    }
                    break;
                case 2132082026:
                    if (titleSrn.equals("car_auto_keep")) {
                        c = '=';
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 94, (byte) i3});
                    break;
                case 1:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 14, (byte) i3});
                    break;
                case 2:
                    if (!UiMgr.this.isPortrateScreen()) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, 0, (byte) i3});
                        break;
                    }
                    break;
                case 3:
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte) i3});
                    break;
                case 4:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 2, (byte) i3});
                    break;
                case 5:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 27, (byte) i3});
                    break;
                case 6:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 5, (byte) i3});
                    break;
                case 7:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 23, (byte) i3});
                    break;
                case '\b':
                    if (!UiMgr.this.isPortrateScreen()) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, 1, (byte) i3});
                        break;
                    }
                    break;
                case '\t':
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 89, (byte) i3});
                    break;
                case '\n':
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 9, (byte) i3});
                    break;
                case 11:
                    if (!UiMgr.this.isPortrateScreen()) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, 6, (byte) i3});
                        break;
                    }
                    break;
                case '\f':
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 83, (byte) i3});
                    break;
                case '\r':
                    if (!UiMgr.this.isPortrateScreen()) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, 5, (byte) i3});
                        break;
                    }
                    break;
                case 14:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 82, (byte) i3});
                    break;
                case 15:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) i3});
                    break;
                case 16:
                    CanbusMsgSender.sendMsg(new byte[]{22, -121, (byte) i3});
                    break;
                case 17:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 10, (byte) i3});
                    break;
                case 18:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 87, (byte) i3});
                    break;
                case 19:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 6, (byte) i3});
                    break;
                case 20:
                    if (!UiMgr.this.isPortrateScreen()) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, 2, (byte) i3});
                        break;
                    }
                    break;
                case 21:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 81, (byte) i3});
                    break;
                case 22:
                    if (!UiMgr.this.isPortrateScreen()) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, 67, (byte) i3});
                        break;
                    }
                    break;
                case 23:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 22, (byte) i3});
                    break;
                case 24:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 1, (byte) i3});
                    break;
                case 25:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 4, (byte) i3});
                    break;
                case 26:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 85, (byte) i3});
                    break;
                case 27:
                    if (!UiMgr.this.isPortrateScreen()) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, 68, (byte) i3});
                        break;
                    }
                    break;
                case 28:
                    if (!UiMgr.this.isPortrateScreen()) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, 8, (byte) i3});
                        break;
                    }
                    break;
                case 29:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 26, (byte) i3});
                    break;
                case 30:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 93, (byte) i3});
                    break;
                case 31:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 11, (byte) i3});
                    break;
                case ' ':
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 3, (byte) i3});
                    break;
                case '!':
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 91, (byte) i3});
                    break;
                case '\"':
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 25, (byte) i3});
                    break;
                case '#':
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 0, (byte) i3});
                    break;
                case '$':
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 90, (byte) i3});
                    break;
                case '%':
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 36, (byte) i3});
                    break;
                case '&':
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 35, (byte) i3});
                    break;
                case '\'':
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 37, (byte) i3});
                    break;
                case '(':
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 38, (byte) i3});
                    break;
                case ')':
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 39, (byte) i3});
                    break;
                case '*':
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 32, (byte) i3});
                    break;
                case '+':
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 15, (byte) i3});
                    break;
                case ',':
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 21, (byte) i3});
                    break;
                case '-':
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 84, (byte) i3});
                    break;
                case '.':
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 34, (byte) i3});
                    break;
                case '/':
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 9, (byte) i3});
                    break;
                case '0':
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 24, (byte) i3});
                    break;
                case '1':
                    if (i3 != 0) {
                        if (i3 != 1) {
                            if (i3 == 2) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -125, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, 1});
                                break;
                            }
                        } else {
                            CanbusMsgSender.sendMsg(new byte[]{22, -125, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, 0});
                            break;
                        }
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, 2});
                        break;
                    }
                    break;
                case '2':
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 8, (byte) i3});
                    break;
                case '3':
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) i3});
                    break;
                case '4':
                    if (!UiMgr.this.isPortrateScreen()) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, 66, (byte) i3});
                        break;
                    }
                    break;
                case '5':
                    if (!UiMgr.this.isPortrateScreen()) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, 65, (byte) i3});
                        break;
                    }
                    break;
                case '6':
                    if (!UiMgr.this.isPortrateScreen()) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, 3, (byte) i3});
                        break;
                    }
                    break;
                case '7':
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 88, (byte) i3});
                    break;
                case '8':
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 20, (byte) i3});
                    break;
                case '9':
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 7, (byte) i3});
                    break;
                case ':':
                    if (!UiMgr.this.isPortrateScreen()) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, 4, (byte) i3});
                        break;
                    }
                    break;
                case ';':
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 19, (byte) i3});
                    break;
                case '<':
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 86, (byte) i3});
                    break;
                case '=':
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 92, (byte) i3});
                    break;
            }
            LogUtil.showLog("leftPos:" + i);
            LogUtil.showLog("rightPos:" + i2);
            LogUtil.showLog("selectPos:" + i3);
        }
    };
    private OnSettingItemSeekbarSelectListener mOnSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._68.UiMgr.23
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
        public void onClickItem(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            String titleSrn = uiMgr.getSettingUiSet(uiMgr.mContext).getList().get(i).getItemList().get(i2).getTitleSrn();
            titleSrn.hashCode();
            if (titleSrn.equals("warning_volume_set")) {
                CanbusMsgSender.sendMsg(new byte[]{22, -120, (byte) i3});
            }
        }
    };
    OnSettingItemClickListener onSettingItemClickListener = new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._68.UiMgr.24
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
        public void onClickItem(int i, int i2) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "other_set")) {
                UiMgr uiMgr2 = UiMgr.this;
                if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "other_set", "_68_panorama_on")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -103, 1});
                }
            }
        }
    };
    private OnAirSeatHeatColdMinPlusClickListener onMinPlusClickListener = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._68.UiMgr.25
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
            LogUtil.showLog("onClickMin");
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            LogUtil.showLog("onClickPlus");
        }
    };
    private OnOnStarClickListener onOnStarClickListener = new OnOnStarClickListener() { // from class: com.hzbhd.canbus.car._68.UiMgr.26
        @Override // com.hzbhd.canbus.interfaces.OnOnStarClickListener
        public void init() {
            if (UiMgr.this.getState() == 0) {
                FutureUtil.instance.reportMcuDataExtra(new byte[]{-123, 1}, UiMgr.this.mContext);
            }
        }

        @Override // com.hzbhd.canbus.interfaces.OnOnStarClickListener
        public void numberClick(int i) {
            if (UiMgr.this.getState() == 4) {
                FutureUtil.instance.reportMcuDataExtra(new byte[]{-123, (byte) (i | 128)}, UiMgr.this.mContext);
            }
        }

        @Override // com.hzbhd.canbus.interfaces.OnOnStarClickListener
        public void handOn(String str) {
            if (UiMgr.this.getState() == 2) {
                FutureUtil.instance.reportMcuDataExtra(new byte[]{-122, 2}, UiMgr.this.mContext);
            } else if (str.length() > 0) {
                FutureUtil.instance.reportMcuDataExtra(DataHandleUtils.byteMerger(new byte[]{-122}, FutureUtil.instance.str2Bcd(UiMgr.this.fillSpaces(str))), UiMgr.this.mContext);
            }
        }

        @Override // com.hzbhd.canbus.interfaces.OnOnStarClickListener
        public void handOff() {
            if (UiMgr.this.getState() == 2 || UiMgr.this.getState() == 3 || UiMgr.this.getState() == 4) {
                FutureUtil.instance.reportMcuDataExtra(new byte[]{-123, 3}, UiMgr.this.mContext);
            }
        }

        @Override // com.hzbhd.canbus.interfaces.OnOnStarClickListener
        public void exit() {
            FutureUtil.instance.reportMcuDataExtra(new byte[]{-123, 0}, UiMgr.this.mContext);
        }
    };
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private int eachID = getEachId();
    private int differentID = getCurrentCarId();

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        if (this.differentID != 1001) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_68_Pollution_control"});
        }
    }

    public UiMgr(final Context context) {
        this.mContext = context;
        this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
        settingUiSet.setOnSettingPageStatusListener(this.mOnSettingPageStatusListener);
        settingUiSet.setOnSettingItemSeekbarSelectListener(this.mOnSettingItemSeekbarSelectListener);
        settingUiSet.setOnSettingItemClickListener(this.onSettingItemClickListener);
        OnStartPageUiSet onStartPageUiSet = getOnStartPageUiSet(context);
        onStartPageUiSet.setOnOnStarClickListener(this.onOnStarClickListener);
        onStartPageUiSet.setOnOnStartStatusChangeListener(new OnOnStartStatusChangeListener() { // from class: com.hzbhd.canbus.car._68.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnOnStartStatusChangeListener
            public void onWirelessSwithc() {
                CanbusMsgSender.sendMsg(new byte[]{22, -112, 65});
                CanbusMsgSender.sendMsg(new byte[]{22, -112, 66});
            }
        });
        final ParkPageUiSet parkPageUiSet = getParkPageUiSet(context);
        parkPageUiSet.setOnPanoramicItemTouchListener(new OnPanoramicItemTouchListener() { // from class: com.hzbhd.canbus.car._68.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener
            public void onTouchItem(MotionEvent motionEvent) {
                int i;
                int i2;
                int i3;
                if (motionEvent.getAction() == 0) {
                    i = 1;
                } else {
                    motionEvent.getAction();
                    i = 0;
                }
                int x = (int) motionEvent.getX();
                int y = (int) motionEvent.getY();
                if (((int) context.getResources().getDimension(R.dimen.dp1024)) < 801) {
                    i2 = (x * 1023) / 800;
                    i3 = (y * 1000) / 480;
                } else {
                    i2 = (x * 1023) / 1024;
                    i3 = (y * 1000) / 600;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -104, (byte) i, (byte) ((i2 >> 8) & 255), (byte) (i2 & 255), (byte) ((i3 >> 8) & 255), (byte) (i3 & 255), 0});
            }
        });
        parkPageUiSet.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._68.UiMgr.3
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public void onClickItem(int i) {
                String titleSrn = parkPageUiSet.getPanoramicBtnList().get(i).getTitleSrn();
                titleSrn.hashCode();
                if (titleSrn.equals("_68_panorama_off")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -103, 0});
                }
            }
        });
        final TimerUtil timerUtil = new TimerUtil();
        final PanelKeyPageUiSet panelKeyPageUiSet = getPanelKeyPageUiSet(context);
        panelKeyPageUiSet.setOnPanelKeyPositionTouchListener(new OnPanelKeyPositionTouchListener() { // from class: com.hzbhd.canbus.car._68.UiMgr.4
            @Override // com.hzbhd.canbus.interfaces.OnPanelKeyPositionTouchListener
            public void onTouch(final int i, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    timerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._68.UiMgr.4.1
                        @Override // java.util.TimerTask, java.lang.Runnable
                        public void run() {
                            String str = panelKeyPageUiSet.getList().get(i);
                            str.hashCode();
                            switch (str) {
                                case "OPEL_KEY_FM_AM":
                                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 6});
                                    break;
                                case "OPEL_KEY_CD_MP3":
                                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 7});
                                    break;
                                case "OPEL_KEY_LEFT":
                                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 4});
                                    break;
                                case "OPEL_KEY_RIGH":
                                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 5});
                                    break;
                                case "OPEL_KEY_1":
                                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 8});
                                    break;
                                case "OPEL_KEY_2":
                                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 9});
                                    break;
                                case "OPEL_KEY_3":
                                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 10});
                                    break;
                                case "OPEL_KEY_4":
                                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 11});
                                    break;
                                case "OPEL_KEY_5":
                                    CanbusMsgSender.sendMsg(new byte[]{22, -58, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED});
                                    break;
                                case "OPEL_KEY_6":
                                    CanbusMsgSender.sendMsg(new byte[]{22, -58, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED});
                                    break;
                                case "OPEL_KEY_7":
                                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 14});
                                    break;
                                case "OPEL_KEY_8":
                                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 15});
                                    break;
                                case "OPEL_KEY_9":
                                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 16});
                                    break;
                                case "OPEL_KEY_BC":
                                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 3});
                                    break;
                                case "OPEL_KEY_OK":
                                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 1});
                                    break;
                                case "OPEL_KEY_SETTING":
                                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 2});
                                    break;
                            }
                        }
                    }, 0L, 200L);
                }
                if (motionEvent.getAction() == 1) {
                    timerUtil.stopTimer();
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 0});
                }
            }
        });
        AirPageUiSet airUiSet = getAirUiSet(context);
        this.airPageUiSet = airUiSet;
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._68.UiMgr.5
            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
            /* JADX WARN: Removed duplicated region for block: B:4:0x001d  */
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onClickItem(int r5) {
                /*
                    r4 = this;
                    com.hzbhd.canbus.car._68.UiMgr r0 = com.hzbhd.canbus.car._68.UiMgr.this
                    com.hzbhd.canbus.adapter.bean.AirPageUiSet r0 = r0.airPageUiSet
                    com.hzbhd.canbus.adapter.bean.FrontArea r0 = r0.getFrontArea()
                    java.lang.String[][] r0 = r0.getLineBtnAction()
                    r1 = 0
                    r0 = r0[r1]
                    r5 = r0[r5]
                    r5.hashCode()
                    int r0 = r5.hashCode()
                    r2 = 2
                    r3 = -1
                    switch(r0) {
                        case 3005871: goto L35;
                        case 3094652: goto L2a;
                        case 756225563: goto L1f;
                        default: goto L1d;
                    }
                L1d:
                    r1 = r3
                    goto L3e
                L1f:
                    java.lang.String r0 = "in_out_cycle"
                    boolean r5 = r5.equals(r0)
                    if (r5 != 0) goto L28
                    goto L1d
                L28:
                    r1 = r2
                    goto L3e
                L2a:
                    java.lang.String r0 = "dual"
                    boolean r5 = r5.equals(r0)
                    if (r5 != 0) goto L33
                    goto L1d
                L33:
                    r1 = 1
                    goto L3e
                L35:
                    java.lang.String r0 = "auto"
                    boolean r5 = r5.equals(r0)
                    if (r5 != 0) goto L3e
                    goto L1d
                L3e:
                    r5 = 7
                    switch(r1) {
                        case 0: goto L60;
                        case 1: goto L5a;
                        case 2: goto L43;
                        default: goto L42;
                    }
                L42:
                    goto L75
                L43:
                    com.hzbhd.canbus.car._68.UiMgr r0 = com.hzbhd.canbus.car._68.UiMgr.this
                    boolean r0 = r0.isPortrateScreen()
                    if (r0 != 0) goto L52
                    com.hzbhd.canbus.car._68.UiMgr r0 = com.hzbhd.canbus.car._68.UiMgr.this
                    r1 = 3
                    com.hzbhd.canbus.car._68.UiMgr.access$000(r0, r5, r1)
                    goto L75
                L52:
                    com.hzbhd.canbus.car._68.UiMgr r5 = com.hzbhd.canbus.car._68.UiMgr.this
                    r0 = 25
                    com.hzbhd.canbus.car._68.UiMgr.access$100(r5, r0)
                    goto L75
                L5a:
                    com.hzbhd.canbus.car._68.UiMgr r5 = com.hzbhd.canbus.car._68.UiMgr.this
                    r5.isPortrateScreen()
                    goto L75
                L60:
                    com.hzbhd.canbus.car._68.UiMgr r0 = com.hzbhd.canbus.car._68.UiMgr.this
                    boolean r0 = r0.isPortrateScreen()
                    if (r0 != 0) goto L6e
                    com.hzbhd.canbus.car._68.UiMgr r0 = com.hzbhd.canbus.car._68.UiMgr.this
                    com.hzbhd.canbus.car._68.UiMgr.access$000(r0, r5, r2)
                    goto L75
                L6e:
                    com.hzbhd.canbus.car._68.UiMgr r5 = com.hzbhd.canbus.car._68.UiMgr.this
                    r0 = 21
                    com.hzbhd.canbus.car._68.UiMgr.access$100(r5, r0)
                L75:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._68.UiMgr.AnonymousClass5.onClickItem(int):void");
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._68.UiMgr.6
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public void onClickItem(int i) {
                String str = UiMgr.this.airPageUiSet.getFrontArea().getLineBtnAction()[1][i];
                str.hashCode();
                if (str.equals("ac")) {
                    if (!UiMgr.this.isPortrateScreen()) {
                        UiMgr.this.sendAirKeyMsg82(7, 1);
                        return;
                    } else {
                        UiMgr.this.sendAirKeyMsgE0(23);
                        return;
                    }
                }
                if (str.equals("power")) {
                    if (!UiMgr.this.isPortrateScreen()) {
                        UiMgr.this.sendAirKeyMsg82(7, 26);
                    } else {
                        UiMgr.this.sendAirKeyMsgE0(1);
                    }
                }
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._68.UiMgr.7
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public void onClickItem(int i) {
                Log.d("mww", "onClickItem " + i);
                String str = UiMgr.this.airPageUiSet.getFrontArea().getLineBtnAction()[2][i];
                str.hashCode();
                if (str.equals("front_defog")) {
                    if (!UiMgr.this.isPortrateScreen()) {
                        UiMgr.this.sendAirKeyMsg82(7, 12);
                        return;
                    } else {
                        UiMgr.this.sendAirKeyMsgE0(18);
                        return;
                    }
                }
                if (str.equals("rear_defog")) {
                    if (!UiMgr.this.isPortrateScreen()) {
                        UiMgr.this.sendAirKeyMsg82(7, 27);
                    } else {
                        UiMgr.this.sendAirKeyMsgE0(20);
                    }
                }
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._68.UiMgr.8
            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
            /* JADX WARN: Removed duplicated region for block: B:4:0x001c  */
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onClickItem(int r4) {
                /*
                    r3 = this;
                    com.hzbhd.canbus.car._68.UiMgr r0 = com.hzbhd.canbus.car._68.UiMgr.this
                    com.hzbhd.canbus.adapter.bean.AirPageUiSet r0 = r0.airPageUiSet
                    com.hzbhd.canbus.adapter.bean.FrontArea r0 = r0.getFrontArea()
                    java.lang.String[][] r0 = r0.getLineBtnAction()
                    r1 = 3
                    r0 = r0[r1]
                    r4 = r0[r4]
                    r4.hashCode()
                    int r0 = r4.hashCode()
                    r2 = -1
                    switch(r0) {
                        case 341572893: goto L48;
                        case 1018451744: goto L3d;
                        case 1434490075: goto L32;
                        case 1434539597: goto L29;
                        case 1568764496: goto L1e;
                        default: goto L1c;
                    }
                L1c:
                    r1 = r2
                    goto L52
                L1e:
                    java.lang.String r0 = "blow_window_foot"
                    boolean r4 = r4.equals(r0)
                    if (r4 != 0) goto L27
                    goto L1c
                L27:
                    r1 = 4
                    goto L52
                L29:
                    java.lang.String r0 = "blow_head"
                    boolean r4 = r4.equals(r0)
                    if (r4 != 0) goto L52
                    goto L1c
                L32:
                    java.lang.String r0 = "blow_foot"
                    boolean r4 = r4.equals(r0)
                    if (r4 != 0) goto L3b
                    goto L1c
                L3b:
                    r1 = 2
                    goto L52
                L3d:
                    java.lang.String r0 = "blow_head_foot"
                    boolean r4 = r4.equals(r0)
                    if (r4 != 0) goto L46
                    goto L1c
                L46:
                    r1 = 1
                    goto L52
                L48:
                    java.lang.String r0 = "blow_window"
                    boolean r4 = r4.equals(r0)
                    if (r4 != 0) goto L51
                    goto L1c
                L51:
                    r1 = 0
                L52:
                    r4 = 8
                    r0 = 7
                    switch(r1) {
                        case 0: goto Lb4;
                        case 1: goto L9c;
                        case 2: goto L86;
                        case 3: goto L72;
                        case 4: goto L5a;
                        default: goto L58;
                    }
                L58:
                    goto Lca
                L5a:
                    com.hzbhd.canbus.car._68.UiMgr r4 = com.hzbhd.canbus.car._68.UiMgr.this
                    boolean r4 = r4.isPortrateScreen()
                    if (r4 != 0) goto L6a
                    com.hzbhd.canbus.car._68.UiMgr r4 = com.hzbhd.canbus.car._68.UiMgr.this
                    r1 = 10
                    com.hzbhd.canbus.car._68.UiMgr.access$000(r4, r0, r1)
                    goto Lca
                L6a:
                    com.hzbhd.canbus.car._68.UiMgr r4 = com.hzbhd.canbus.car._68.UiMgr.this
                    r0 = 32
                    com.hzbhd.canbus.car._68.UiMgr.access$100(r4, r0)
                    goto Lca
                L72:
                    com.hzbhd.canbus.car._68.UiMgr r1 = com.hzbhd.canbus.car._68.UiMgr.this
                    boolean r1 = r1.isPortrateScreen()
                    if (r1 != 0) goto L80
                    com.hzbhd.canbus.car._68.UiMgr r1 = com.hzbhd.canbus.car._68.UiMgr.this
                    com.hzbhd.canbus.car._68.UiMgr.access$000(r1, r0, r4)
                    goto Lca
                L80:
                    com.hzbhd.canbus.car._68.UiMgr r4 = com.hzbhd.canbus.car._68.UiMgr.this
                    com.hzbhd.canbus.car._68.UiMgr.access$100(r4, r0)
                    goto Lca
                L86:
                    com.hzbhd.canbus.car._68.UiMgr r1 = com.hzbhd.canbus.car._68.UiMgr.this
                    boolean r1 = r1.isPortrateScreen()
                    if (r1 != 0) goto L96
                    com.hzbhd.canbus.car._68.UiMgr r4 = com.hzbhd.canbus.car._68.UiMgr.this
                    r1 = 11
                    com.hzbhd.canbus.car._68.UiMgr.access$000(r4, r0, r1)
                    goto Lca
                L96:
                    com.hzbhd.canbus.car._68.UiMgr r0 = com.hzbhd.canbus.car._68.UiMgr.this
                    com.hzbhd.canbus.car._68.UiMgr.access$100(r0, r4)
                    goto Lca
                L9c:
                    com.hzbhd.canbus.car._68.UiMgr r4 = com.hzbhd.canbus.car._68.UiMgr.this
                    boolean r4 = r4.isPortrateScreen()
                    if (r4 != 0) goto Lac
                    com.hzbhd.canbus.car._68.UiMgr r4 = com.hzbhd.canbus.car._68.UiMgr.this
                    r1 = 9
                    com.hzbhd.canbus.car._68.UiMgr.access$000(r4, r0, r1)
                    goto Lca
                Lac:
                    com.hzbhd.canbus.car._68.UiMgr r4 = com.hzbhd.canbus.car._68.UiMgr.this
                    r0 = 33
                    com.hzbhd.canbus.car._68.UiMgr.access$100(r4, r0)
                    goto Lca
                Lb4:
                    com.hzbhd.canbus.car._68.UiMgr r4 = com.hzbhd.canbus.car._68.UiMgr.this
                    boolean r4 = r4.isPortrateScreen()
                    if (r4 != 0) goto Lc4
                    com.hzbhd.canbus.car._68.UiMgr r4 = com.hzbhd.canbus.car._68.UiMgr.this
                    r1 = 28
                    com.hzbhd.canbus.car._68.UiMgr.access$000(r4, r0, r1)
                    goto Lca
                Lc4:
                    com.hzbhd.canbus.car._68.UiMgr r4 = com.hzbhd.canbus.car._68.UiMgr.this
                    r0 = 6
                    com.hzbhd.canbus.car._68.UiMgr.access$100(r4, r0)
                Lca:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._68.UiMgr.AnonymousClass8.onClickItem(int):void");
            }
        }});
        this.airPageUiSet.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerRearTop, null, null, this.mOnAirBtnClickListenerRearBottom});
        this.airPageUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.onUpDownClickListenerLeft, null, this.onUpDownClickListenerRight});
        this.airPageUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.mOnAirWindSpeedUpDownClickListenerFront);
        this.airPageUiSet.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.mOnLeftSeatHeat, this.mOnRightSeatHeat, this.mOnleftSeatCold, this.mOnrightSeatCold});
        this.airPageUiSet.getRearArea().setSetWindSpeedViewOnClickListener(this.mOnAirWindSpeedUpDownClickListenerRear);
        this.airPageUiSet.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{null, this.mUpDownTempCenter, null});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String fillSpaces(String str) {
        if (str.length() < 20) {
            int length = 20 - str.length();
            for (int i = 0; i < length; i++) {
                str = str + " ";
            }
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getState() {
        return DataHandleUtils.getIntFromByteWithBit(GeneralOnStartData.mOnStarStatus, 0, 7);
    }

    private void sendAirKeyMsg(final int i) {
        if (isPortrateScreen()) {
            this.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._68.UiMgr.27
                @Override // java.lang.Runnable
                public void run() {
                    CanbusMsgSender.sendMsg(new byte[]{22, -32, (byte) i, 1});
                }
            }, 30L);
            this.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._68.UiMgr.28
                @Override // java.lang.Runnable
                public void run() {
                    CanbusMsgSender.sendMsg(new byte[]{22, -32, (byte) i, 0});
                }
            }, 30L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirKeyMsg82(final int i, final int i2) {
        this.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._68.UiMgr.29
            @Override // java.lang.Runnable
            public void run() {
                CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte) i, (byte) i2});
            }
        }, 30L);
        this.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._68.UiMgr.30
            @Override // java.lang.Runnable
            public void run() {
                CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte) i, 0});
            }
        }, 30L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirKeyMsgE0(final int i) {
        this.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._68.UiMgr.31
            @Override // java.lang.Runnable
            public void run() {
                CanbusMsgSender.sendMsg(new byte[]{22, -32, (byte) i, 1});
            }
        }, 30L);
        this.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._68.UiMgr.32
            @Override // java.lang.Runnable
            public void run() {
                CanbusMsgSender.sendMsg(new byte[]{22, -32, (byte) i, 0});
            }
        }, 30L);
    }

    public boolean isPortrateScreen() {
        Context context = this.mContext;
        return context != null && context.getResources().getConfiguration().orientation == 1;
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public View getCusPanoramicView(Context context) {
        if (this.mPanoramicView == null) {
            this.mPanoramicView = new MyPanoramicView(context);
        }
        return this.mPanoramicView;
    }

    public void send0xC0Info(byte[] bArr) {
        CanbusMsgSender.sendMsg(bArr);
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

    public void send0xE2CarModelInfo(int i) {
        if (i == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, -30, 68});
        }
        if (i == 3) {
            CanbusMsgSender.sendMsg(new byte[]{22, -30, 69});
            return;
        }
        if (i == 7) {
            CanbusMsgSender.sendMsg(new byte[]{22, -30, 1});
            return;
        }
        if (i == 13) {
            CanbusMsgSender.sendMsg(new byte[]{22, -30, 67});
            return;
        }
        if (i == 9) {
            CanbusMsgSender.sendMsg(new byte[]{22, -30, 2});
            return;
        }
        if (i == 10) {
            CanbusMsgSender.sendMsg(new byte[]{22, -30, 3});
            return;
        }
        if (i == 21) {
            CanbusMsgSender.sendMsg(new byte[]{22, -30, 11});
            return;
        }
        if (i == 22) {
            CanbusMsgSender.sendMsg(new byte[]{22, -30, 70});
            return;
        }
        if (i == 30) {
            CanbusMsgSender.sendMsg(new byte[]{22, -30, 8});
            return;
        }
        if (i == 31) {
            CanbusMsgSender.sendMsg(new byte[]{22, -30, 9});
            return;
        }
        switch (i) {
            case 25:
                CanbusMsgSender.sendMsg(new byte[]{22, -30, 0});
                break;
            case 26:
                CanbusMsgSender.sendMsg(new byte[]{22, -30, 21});
                break;
            case 27:
                CanbusMsgSender.sendMsg(new byte[]{22, -30, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED});
                break;
            case 28:
                CanbusMsgSender.sendMsg(new byte[]{22, -30, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED});
                break;
            default:
                CanbusMsgSender.sendMsg(new byte[]{22, -30, ByteCompanionObject.MIN_VALUE});
                break;
        }
    }
}
