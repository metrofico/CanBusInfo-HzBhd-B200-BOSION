package com.hzbhd.canbus.car._220;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.adapter.util.Util;
import com.hzbhd.canbus.interfaces.OnBackCameraStatusListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.text.Typography;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    protected static final int CAR_ID_EV_19 = 15;
    protected static final int CAR_ID_GA3S = 1;
    protected static final int CAR_ID_GA3_13_14 = 0;
    protected static final int CAR_ID_GA4_18 = 10;
    protected static final int CAR_ID_GA6_14_16 = 4;
    protected static final int CAR_ID_GA6_19 = 18;
    protected static final int CAR_ID_GAXL_13_15 = 6;
    protected static final int CAR_ID_GE3_18 = 13;
    protected static final int CAR_ID_GM6_19 = 17;
    protected static final int CAR_ID_GM8_20 = 20;
    protected static final int CAR_ID_GS3_17 = 8;
    protected static final int CAR_ID_GS3_21 = 24;

    /* renamed from: CAR_ID_GS3高配_17, reason: contains not printable characters */
    protected static final int f2CAR_ID_GS3_17 = 23;
    protected static final int CAR_ID_GS4_14_17 = 5;
    protected static final int CAR_ID_GS4_18 = 12;
    protected static final int CAR_ID_GS4_19 = 14;
    protected static final int CAR_ID_GS4_20 = 19;
    protected static final int CAR_ID_GS4_22 = 27;
    protected static final int CAR_ID_GS4_HYBRID_18 = 11;
    protected static final int CAR_ID_GS4_HYBRID_20 = 26;
    protected static final int CAR_ID_GS5_12_14 = 2;
    protected static final int CAR_ID_GS5_20 = 22;
    protected static final int CAR_ID_GS5_SUPER_14_13 = 3;
    protected static final int CAR_ID_GS7_17 = 9;
    protected static final int CAR_ID_GS8_17 = 7;
    protected static final int CAR_ID_GS8_19 = 16;
    protected static final int CAR_ID_GS8_20 = 25;

    /* renamed from: CAR_ID_电动出租车, reason: contains not printable characters */
    protected static final int f3CAR_ID_ = 21;
    private int EachID;
    private AirPageUiSet airPageUiSet;
    private Context mContext;
    private int mDifferent;
    private FrontArea mFrontArea;
    private MsgMgr mMsgMgr;
    private MyPanoramicView mMyPanoramicView;
    private MsgMgr msgMgr;
    private SettingPageUiSet settingPageUiSet;
    private int SupportPanoramicView = 0;
    private OnSettingItemSeekbarSelectListener mOnSeekBarChangeListener = new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._220.UiMgr.10
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
        public void onClickItem(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.getMsgMgr(uiMgr.mContext).updateSettings(i, i2, i3);
            String titleSrn = UiMgr.this.settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
            titleSrn.hashCode();
            switch (titleSrn) {
                case "_220_Ambient_light_brightness":
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 41, (byte) (((byte) i3) - 1)});
                    break;
                case "_220_Heat_Preservation":
                    CanbusMsgSender.sendMsg(new byte[]{22, -87, 6, (byte) i3});
                    break;
                case "_220_Ambient_light_Color":
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 42, (byte) (((byte) i3) - 1)});
                    break;
                case "charging_set_2":
                    MsgMgr.mNewEnergyStartTime = DataHandleUtils.setIntFromByteWithBit(MsgMgr.mNewEnergyStartTime, (byte) i3, 6, 5);
                    UiMgr.this.sndNewEnergyCmdDatas();
                    break;
                case "charging_set_3":
                    MsgMgr.mNewEnergyStartTime = DataHandleUtils.setIntFromByteWithBit(MsgMgr.mNewEnergyStartTime, (byte) i3, 0, 6);
                    UiMgr.this.sndNewEnergyCmdDatas();
                    break;
                case "charging_set_4":
                    MsgMgr.mNewEnergyEndTime = DataHandleUtils.setIntFromByteWithBit(MsgMgr.mNewEnergyEndTime, (byte) i3, 6, 5);
                    UiMgr.this.sndNewEnergyCmdDatas();
                    break;
                case "charging_set_5":
                    MsgMgr.mNewEnergyEndTime = DataHandleUtils.setIntFromByteWithBit(MsgMgr.mNewEnergyEndTime, (byte) i3, 0, 6);
                    UiMgr.this.sndNewEnergyCmdDatas();
                    break;
                case "_220_remote_poweron_time":
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 9, (byte) i3});
                    break;
                case "_220_remote_start_time":
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 10, (byte) i3});
                    break;
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._220.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirCommand(21);
                return;
            }
            if (i == 2) {
                UiMgr.this.sendAirCommand(30);
            } else if (i == 3) {
                UiMgr.this.sendAirCommand(1);
            } else {
                if (i != 4) {
                    return;
                }
                UiMgr.this.sendAirCommand(32);
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._220.UiMgr.12
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirCommand(3);
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._220.UiMgr.13
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirCommand(27);
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._220.UiMgr.14
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirCommand(6);
            } else if (i == 1) {
                UiMgr.this.sendAirCommand(23);
            } else {
                if (i != 2) {
                    return;
                }
                UiMgr.this.sendAirCommand(31);
            }
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._220.UiMgr.15
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirCommand(13);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirCommand(14);
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._220.UiMgr.16
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirCommand(15);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirCommand(16);
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerRear = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._220.UiMgr.17
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirCommand(28);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirCommand(29);
        }
    };
    private OnSettingItemClickListener mOnSettingItemClickListener = new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._220.UiMgr.18
        /* JADX WARN: Type inference failed for: r3v12, types: [com.hzbhd.canbus.car._220.UiMgr$18$1] */
        /* JADX WARN: Type inference failed for: r3v7, types: [com.hzbhd.canbus.car._220.UiMgr$18$2] */
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
        public void onClickItem(int i, int i2) {
            String titleSrn = UiMgr.this.settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
            titleSrn.hashCode();
            switch (titleSrn) {
                case "_220_right_seat_heat":
                    new Thread() { // from class: com.hzbhd.canbus.car._220.UiMgr.18.2
                        @Override // java.lang.Thread, java.lang.Runnable
                        public void run() {
                            super.run();
                            CanbusMsgSender.sendMsg(new byte[]{22, -125, 23, 2});
                            try {
                                sleep(50L);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            CanbusMsgSender.sendMsg(new byte[]{22, -125, 23, 1});
                        }
                    }.start();
                    break;
                case "_23_enter_panoramic":
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, -1});
                    UiMgr uiMgr = UiMgr.this;
                    uiMgr.getMsgMgr(uiMgr.mContext).forceReverse(UiMgr.this.mContext, true);
                    break;
                case "_220_left_seat_heat":
                    new Thread() { // from class: com.hzbhd.canbus.car._220.UiMgr.18.1
                        @Override // java.lang.Thread, java.lang.Runnable
                        public void run() {
                            super.run();
                            CanbusMsgSender.sendMsg(new byte[]{22, -125, 22, 2});
                            try {
                                sleep(50L);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            CanbusMsgSender.sendMsg(new byte[]{22, -125, 22, 1});
                        }
                    }.start();
                    break;
            }
        }
    };

    private int plusVal(int i) {
        return i + 1;
    }

    private int swapVal(int i) {
        return i == 0 ? 2 : 1;
    }

    private void setCarIdCmd() {
        switch (this.EachID) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 8:
                no_new_energy();
                break;
        }
        if (this.EachID != 8) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_P_mode_exit", "_220_Turn_signals_activate_panorama"});
        }
        if (this.EachID != 8) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_Distraction_Reminder", "_220_Fatigue_Testing", "_220_Ventilate_While_Smoking", "_220_Decrease_The_Volume_During_A_Call", "_220_Sight_Bright_Screen", "_220_Gestures_To_Cut_The_Song", "_220_Convenient_For_Car"});
        }
        int i = this.EachID;
        if (i == 3 || i == 7 || i == 8 || i == 0) {
            removeSettingLeftItemByNameList(this.mContext, new String[]{"_220_language_settings"});
        }
        int i2 = this.EachID;
        if (i2 == 7 || i2 == 0) {
            removeSettingLeftItemByNameList(this.mContext, new String[]{"_220_seat_set"});
        }
        if (this.EachID != 6) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_seat_welcome_light"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_smart_key_automatic_identification_seat"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_manually_adjustable_exterior_mirror_angle"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_automatically_adjusting_the_angle_of_the_outer_mirror"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_Key_forget_reminder"});
        }
        if (this.EachID != 8) {
            removeDriveDataPagesByHeadTitles(this.mContext, "car_info");
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_Auto_Close_Windows"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_Wiper_maintenance_mode"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_Delayed_headlight_off"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_Memorize_the_current_driving_mode"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_Unlock_the_ventilation"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_Air_conditioning_self_drying"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_frontrightdoor"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_rearrightdoor"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_frontleftdoor"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_rearleftdoor"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_Skylight"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_Sunshade"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_ElectricTailgateFunction"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_ElectricTailgate"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_Electric_Tailgate_Open_Position"});
            removeSettingLeftItemByNameList(this.mContext, new String[]{"_220_car_control"});
        }
        if (this.EachID != 2) {
            removeDriveDataPagesByHeadTitles(this.mContext, "_220_add_function_page1");
        }
        int i3 = this.EachID;
        if (i3 == 6 || i3 == 0) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_steering_modes"});
        }
        int i4 = this.EachID;
        if (i4 != 4 && i4 != 5 && i4 != 8) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_speed_lock"});
        }
        int i5 = this.EachID;
        if (i5 == 2 || i5 == 5 || i5 == 8 || i5 == 0) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_remote_left_front_window_and_skylight"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_front_wiper_maintenance_functions"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_i_went_home_with"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_daytime_running_lights"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_auto_light_sensitivity"});
        }
        int i6 = this.EachID;
        if (i6 == 1 || i6 == 3 || i6 == 0) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_left_seat_heat"});
        }
        int i7 = this.EachID;
        if (i7 == 1 || i7 == 3 || i7 == 0 || i7 == 8) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_right_seat_heat"});
        }
        int i8 = this.EachID;
        if (i8 == 5 || i8 == 0) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_automatic_folding_exterior_rear_view_mirror"});
        }
        if (this.EachID == 0) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_unlock_lock_tone"});
        }
        int i9 = this.EachID;
        if (i9 == 0 || i9 == 4) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_intelligent_active_lock"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_smart_unlock_initiative"});
        }
        int i10 = this.EachID;
        if (i10 == 0 || i10 == 4 || i10 == 5) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_intelligent_welcome_light_function"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_Automatic_Wiper_Function"});
        }
        int i11 = this.EachID;
        if (i11 != 1 && i11 != 3) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_ambient_light_control"});
        }
        int i12 = this.EachID;
        if (i12 == 0 || i12 == 3 || i12 == 4 || i12 == 5) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_air_quality_sensor"});
        }
        int i13 = this.EachID;
        if (i13 == 0 || i13 == 1 || i13 == 4) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_automatic_mode_wind"});
        }
        int i14 = this.EachID;
        if (i14 != 1 && i14 != 8) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_open_trunk_induction"});
        }
        if (this.EachID != 1) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_Ambient_light_brightness"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_Ambient_light_Color"});
        }
        int i15 = this.EachID;
        if (i15 != 3 && i15 != 8) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_Intelligent_high_beam"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_Active_brake_assist"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_Cruise_mode"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_Front_collision_warning"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_Front_collision_warning_distance"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_Lane_assist_switch"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_Lane_assist"});
        }
        int i16 = this.EachID;
        if (i16 != 3 && i16 != 6 && i16 != 7) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_Wireless_charging"});
        }
        if (this.EachID != 7) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_220_Low_speed_beep"});
        }
        int i17 = this.EachID;
        if (i17 != 2 && i17 != 8) {
            removeMainPrjBtnByName(this.mContext, MainAction.DRIVE_DATA);
        }
        switch (getCurrentCarId()) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 16:
                no_new_energy();
                break;
            case 10:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 2, 1});
                no_new_energy();
                break;
            case 11:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 2, 2});
                break;
            case 12:
            case 14:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 2, 4});
                no_new_energy();
                break;
            case 13:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 2, 3});
                break;
            case 15:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 2, 5});
                break;
            case 17:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 2, 6});
                no_new_energy();
                break;
            case 18:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 2, 7});
                no_new_energy();
                break;
            case 19:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 2, 8});
                no_new_energy();
                break;
        }
    }

    private void no_new_energy() {
        removeSettingLeftItemByNameList(this.mContext, new String[]{"_220_energy_set"});
    }

    private void resetCamera360Items() {
        if (getCurrentCarId() != 6) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"support_panorama"});
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isHaveCam360() {
        int currentCarId = getCurrentCarId();
        if (currentCarId == 3 || currentCarId == 9 || currentCarId == 14 || currentCarId == 16 || currentCarId == 19 || currentCarId == 22 || currentCarId == 5 || currentCarId == 6 || currentCarId == 7 || currentCarId == 11 || currentCarId == 12) {
            return true;
        }
        switch (currentCarId) {
            case 25:
            case 26:
            case 27:
                return true;
            default:
                return false;
        }
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        setIsHaveRearAir();
        setCarIdCmd();
    }

    private void setIsHaveRearAir() {
        int i;
        if (getCurrentCarId() == 7 || getCurrentCarId() == 16 || getCurrentCarId() == 25 || getCurrentCarId() == 17 || (i = this.EachID) == 1 || i == 6) {
            this.airPageUiSet.setHaveRearArea(true);
        } else {
            this.airPageUiSet.setHaveRearArea(false);
        }
    }

    public UiMgr(final Context context) {
        this.mContext = context;
        this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        this.airPageUiSet = getAirUiSet(context);
        this.settingPageUiSet = getSettingUiSet(context);
        this.mFrontArea = this.airPageUiSet.getFrontArea();
        setIsHaveRearAir();
        this.mDifferent = getCurrentCarId();
        this.EachID = getEachId();
        this.airPageUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
        this.airPageUiSet.getRearArea().setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._220.UiMgr.1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                UiMgr.this.sendAirCommand(17);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
                UiMgr.this.sendAirCommand(17);
            }
        });
        this.airPageUiSet.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{null, this.mOnUpDownClickListenerRear, null});
        this.airPageUiSet.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._220.UiMgr.2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public void onClickItem(int i) {
                if (i == 0) {
                    UiMgr.this.sendAirCommand(2);
                }
            }
        }});
        this.mFrontArea.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
        this.airPageUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._220.UiMgr.3
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr.this.sendAirCommand(12);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr.this.sendAirCommand(11);
            }
        });
        final ParkPageUiSet parkPageUiSet = getParkPageUiSet(context);
        int i = this.mDifferent;
        if (i == 7 || i == 7 || i == 16 || i == 16 || i == 9 || i == 6 || i == 5 || i == 11 || i == 12 || i == 14 || i == 19) {
            parkPageUiSet.setShowRadar(SharePreUtil.getIntValue(context, "_220_SAVE_360", 0) == 0);
            parkPageUiSet.setShowPanoramic(SharePreUtil.getIntValue(context, "_220_SAVE_360", 0) == 1);
            parkPageUiSet.setShowCusPanoramicView(false);
        } else if (getCurrentCarId() == 3) {
            parkPageUiSet.setShowPanoramic(false);
            parkPageUiSet.setShowRadar(SharePreUtil.getIntValue(context, "_220_SAVE_360", 0) == 0);
            parkPageUiSet.setShowCusPanoramicView(SharePreUtil.getIntValue(context, "_220_SAVE_360", 0) == 1);
        } else {
            parkPageUiSet.setShowCusPanoramicView(false);
            parkPageUiSet.setShowPanoramic(false);
            parkPageUiSet.setShowRadar(true);
        }
        if (isHaveCam360()) {
            this.mMsgMgr.init360Disp(this.mContext);
        }
        final SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSeekbarSelectListener(this.mOnSeekBarChangeListener);
        settingUiSet.setOnSettingItemClickListener(this.mOnSettingItemClickListener);
        settingUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._220.UiMgr.4
            @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
            public void onConfirmClick(int i2, int i3) {
                if (i2 == 0 && i3 == 12) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -119, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, 0});
                    Context context2 = context;
                    Toast.makeText(context2, context2.getText(R.string.reset_completed), 0).show();
                }
            }
        });
        parkPageUiSet.setOnBackCameraStatusListener(new OnBackCameraStatusListener() { // from class: com.hzbhd.canbus.car._220.UiMgr.5
            @Override // com.hzbhd.canbus.interfaces.OnBackCameraStatusListener
            public void addViewToWindows() {
                List<ParkPageUiSet.Bean> listInitCamera360Btns;
                if (UiMgr.this.isHaveCam360()) {
                    if (parkPageUiSet.getPanoramicBtnList() != null) {
                        parkPageUiSet.getPanoramicBtnList().clear();
                    }
                    new ArrayList();
                    List<ParkPageUiSet.Bean> list = null;
                    if (UiMgr.this.mDifferent == 5 || UiMgr.this.mDifferent == 12 || UiMgr.this.mDifferent == 14 || UiMgr.this.mDifferent == 19 || UiMgr.this.mDifferent == 10 || UiMgr.this.mDifferent == 6) {
                        listInitCamera360Btns = UiMgr.this.initCamera360Btns();
                    } else {
                        parkPageUiSet.setShowPanoramic(false);
                        listInitCamera360Btns = null;
                    }
                    if (SharePreUtil.getIntValue(UiMgr.this.mContext, "_220_SAVE_360", 0) != 0) {
                        if (UiMgr.this.mDifferent != 3) {
                            parkPageUiSet.setShowPanoramic(true);
                        }
                        list = listInitCamera360Btns;
                    } else {
                        parkPageUiSet.setShowPanoramic(false);
                    }
                    parkPageUiSet.setPanoramicBtnList(list);
                }
            }
        });
        if (getCurrentCarId() == 9 || getCurrentCarId() == 7 || getCurrentCarId() == 16) {
            parkPageUiSet.setOnPanoramicItemTouchListener(new OnPanoramicItemTouchListener() { // from class: com.hzbhd.canbus.car._220.UiMgr.6
                @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener
                public void onTouchItem(MotionEvent motionEvent) {
                    UiMgr uiMgr = UiMgr.this;
                    if (uiMgr.isInThrumpchiRightTouchArea(uiMgr.mContext, motionEvent)) {
                        UiMgr uiMgr2 = UiMgr.this;
                        uiMgr2.sendThrumpchiRightTouchCoord(uiMgr2.mContext, motionEvent);
                        return;
                    }
                    UiMgr uiMgr3 = UiMgr.this;
                    uiMgr3.sendTrumpchiBottomBtn1(uiMgr3.mContext, motionEvent);
                    UiMgr uiMgr4 = UiMgr.this;
                    uiMgr4.sendTrumpchiBottomBtn2(uiMgr4.mContext, motionEvent);
                    UiMgr uiMgr5 = UiMgr.this;
                    uiMgr5.sendTrumpchiBottomBtn3(uiMgr5.mContext, motionEvent);
                    UiMgr uiMgr6 = UiMgr.this;
                    uiMgr6.sendTrumpchiBtnUp(uiMgr6.mContext, motionEvent);
                    UiMgr uiMgr7 = UiMgr.this;
                    uiMgr7.sendTrumpchiBtnDn(uiMgr7.mContext, motionEvent);
                    UiMgr uiMgr8 = UiMgr.this;
                    uiMgr8.sendTrumpchiBtnLeft(uiMgr8.mContext, motionEvent);
                    UiMgr uiMgr9 = UiMgr.this;
                    uiMgr9.sendTrumpchiBtnRight(uiMgr9.mContext, motionEvent);
                    UiMgr uiMgr10 = UiMgr.this;
                    uiMgr10.sendTrumpchiBtnOk(uiMgr10.mContext, motionEvent);
                }
            });
        }
        parkPageUiSet.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._220.UiMgr.7
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public void onClickItem(int i2) {
                String titleSrn = parkPageUiSet.getPanoramicBtnList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "_194_front":
                        CanbusMsgSender.sendMsg(new byte[]{22, -89, 1, -1});
                        break;
                    case "_194_right":
                        CanbusMsgSender.sendMsg(new byte[]{22, -89, 8, -1});
                        break;
                    case "_194_exit":
                        if (UiMgr.this.mDifferent != 6) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -89, 0, -1});
                            break;
                        } else {
                            CanbusMsgSender.sendMsg(new byte[]{22, -124, 0});
                            break;
                        }
                    case "_194_left":
                        CanbusMsgSender.sendMsg(new byte[]{22, -89, 7, -1});
                        break;
                    case "_194_rear":
                        CanbusMsgSender.sendMsg(new byte[]{22, -89, 2, -1});
                        break;
                }
            }
        });
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._220.UiMgr.8
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i2, int i3, int i4) {
                UiMgr uiMgr = UiMgr.this;
                uiMgr.getMsgMgr(uiMgr.mContext).updateSettings(i2, i3, i4);
                UiMgr.this.setItemsCmd(settingUiSet.getList().get(i2).getItemList().get(i3).getTitleSrn(), i4);
            }
        });
        settingUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._220.UiMgr.9
            @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
            public void onConfirmClick(int i2, int i3) {
                String titleSrn = settingUiSet.getList().get(i2).getItemList().get(i3).getTitleSrn();
                titleSrn.hashCode();
                if (titleSrn.equals("_220_energy_charge_right_now")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -87, 1, 1});
                } else if (titleSrn.equals("reset")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 0, -1});
                }
            }
        });
        if (getCurrentCarId() == 5 || getCurrentCarId() == 10) {
            parkPageUiSet.setShowCusPanoramicView(false);
            parkPageUiSet.setShowPanoramic(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sndNewEnergyCmdDatas() {
        CanbusMsgSender.sendMsg(new byte[]{22, -87, 2, (byte) ((MsgMgr.mNewEnergyStartTime >> 8) & 255), (byte) DataHandleUtils.getIntFromByteWithBit(MsgMgr.mNewEnergyStartTime, 0, 8), (byte) ((MsgMgr.mNewEnergyEndTime >> 8) & 255), (byte) DataHandleUtils.getIntFromByteWithBit(MsgMgr.mNewEnergyEndTime, 0, 8), (byte) MsgMgr.mNewEnergyData});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<ParkPageUiSet.Bean> initCamera360Btns() {
        ArrayList arrayList = new ArrayList();
        if (getCurrentCarId() == 5 || getCurrentCarId() == 12 || getCurrentCarId() == 10) {
            arrayList.add(new ParkPageUiSet.Bean(0, "_194_front", ""));
            arrayList.add(new ParkPageUiSet.Bean(0, "_194_rear", ""));
            arrayList.add(new ParkPageUiSet.Bean(0, "_194_left", ""));
            arrayList.add(new ParkPageUiSet.Bean(0, "_194_right", ""));
        } else if (getCurrentCarId() == 14 || getCurrentCarId() == 19) {
            arrayList.add(new ParkPageUiSet.Bean(0, "_194_front", ""));
            arrayList.add(new ParkPageUiSet.Bean(0, "_194_rear", ""));
            arrayList.add(new ParkPageUiSet.Bean(0, "_194_left", ""));
            arrayList.add(new ParkPageUiSet.Bean(0, "_194_right", ""));
            arrayList.add(new ParkPageUiSet.Bean(0, "_194_exit", ""));
        } else if (getCurrentCarId() == 6) {
            arrayList.add(new ParkPageUiSet.Bean(0, "_194_exit", ""));
        } else {
            arrayList.add(new ParkPageUiSet.Bean(0, "_194_exit", ""));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public void setItemsCmd(String str, int i) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -2124895205:
                if (str.equals("_220_Delayed_headlight_off")) {
                    c = 0;
                    break;
                }
                break;
            case -2121592808:
                if (str.equals("_220_rearrightdoor")) {
                    c = 1;
                    break;
                }
                break;
            case -2101623002:
                if (str.equals("_220_speeding_alert")) {
                    c = 2;
                    break;
                }
                break;
            case -2026872673:
                if (str.equals("_220_Cruise_mode")) {
                    c = 3;
                    break;
                }
                break;
            case -2003039537:
                if (str.equals("_220_Sunshade")) {
                    c = 4;
                    break;
                }
                break;
            case -2000828773:
                if (str.equals("_220_auto_when_the_compressor_status")) {
                    c = 5;
                    break;
                }
                break;
            case -1995717173:
                if (str.equals("_220_Front_collision_warning")) {
                    c = 6;
                    break;
                }
                break;
            case -1979650045:
                if (str.equals("_220_Skylight")) {
                    c = 7;
                    break;
                }
                break;
            case -1963353434:
                if (str.equals("_220_language")) {
                    c = '\b';
                    break;
                }
                break;
            case -1905420321:
                if (str.equals("_220_Low_speed_beep")) {
                    c = '\t';
                    break;
                }
                break;
            case -1901162969:
                if (str.equals("_220_rearleftdoor")) {
                    c = '\n';
                    break;
                }
                break;
            case -1888064510:
                if (str.equals("_220_automatic_mode_wind")) {
                    c = 11;
                    break;
                }
                break;
            case -1872477900:
                if (str.equals("_220_auto_light_sensitivity")) {
                    c = '\f';
                    break;
                }
                break;
            case -1807487191:
                if (str.equals("_220_remote_left_front_window_and_skylight")) {
                    c = '\r';
                    break;
                }
                break;
            case -1760066865:
                if (str.equals("_220_remote_unlock")) {
                    c = 14;
                    break;
                }
                break;
            case -1720286544:
                if (str.equals("_220_steering_modes")) {
                    c = 15;
                    break;
                }
                break;
            case -1620441994:
                if (str.equals("_220_Key_forget_reminder")) {
                    c = 16;
                    break;
                }
                break;
            case -1536212359:
                if (str.equals("_220_right_seat_heat")) {
                    c = 17;
                    break;
                }
                break;
            case -1498096753:
                if (str.equals("_220_energy_recycle_i_pedal")) {
                    c = 18;
                    break;
                }
                break;
            case -1459836163:
                if (str.equals("_220_P_mode_exit")) {
                    c = 19;
                    break;
                }
                break;
            case -1409279546:
                if (str.equals("_220_auto_unlock")) {
                    c = 20;
                    break;
                }
                break;
            case -1310751467:
                if (str.equals("_220_Air_conditioning_self_drying")) {
                    c = 21;
                    break;
                }
                break;
            case -1253907986:
                if (str.equals("_220_Lane_assist")) {
                    c = 22;
                    break;
                }
                break;
            case -1251555340:
                if (str.equals("_220_intelligent_welcome_light_function")) {
                    c = 23;
                    break;
                }
                break;
            case -1237574730:
                if (str.equals("_220_Intelligent_high_beam")) {
                    c = 24;
                    break;
                }
                break;
            case -1226835875:
                if (str.equals("_220_air_quality_sensor")) {
                    c = 25;
                    break;
                }
                break;
            case -1064418657:
                if (str.equals("_220_i_went_home_with")) {
                    c = 26;
                    break;
                }
                break;
            case -1003140119:
                if (str.equals("_220_Front_collision_warning_distance")) {
                    c = 27;
                    break;
                }
                break;
            case -754233788:
                if (str.equals("_220_Wireless_charging")) {
                    c = 28;
                    break;
                }
                break;
            case -624405314:
                if (str.equals("_220_ElectricTailgateFunction")) {
                    c = 29;
                    break;
                }
                break;
            case -511308010:
                if (str.equals("_220_Decrease_The_Volume_During_A_Call")) {
                    c = 30;
                    break;
                }
                break;
            case -500437698:
                if (str.equals("_220_smart_key_automatic_identification_seat")) {
                    c = 31;
                    break;
                }
                break;
            case -483459599:
                if (str.equals("_220_speed_lock")) {
                    c = ' ';
                    break;
                }
                break;
            case -369497201:
                if (str.equals("_220_and_left_auxiliary_line")) {
                    c = '!';
                    break;
                }
                break;
            case -364381731:
                if (str.equals("_220_Distraction_Reminder")) {
                    c = Typography.quote;
                    break;
                }
                break;
            case -310817255:
                if (str.equals("_220_unlock_lock_tone")) {
                    c = '#';
                    break;
                }
                break;
            case -206769063:
                if (str.equals("_220_automatic_heating_passenger_seat")) {
                    c = Typography.dollar;
                    break;
                }
                break;
            case -193573745:
                if (str.equals("_220_frontrightdoor")) {
                    c = '%';
                    break;
                }
                break;
            case -79772807:
                if (str.equals("_220_Turn_signals_activate_panorama")) {
                    c = Typography.amp;
                    break;
                }
                break;
            case -70694761:
                if (str.equals("_220_front_wiper_maintenance_functions")) {
                    c = '\'';
                    break;
                }
                break;
            case -37853488:
                if (str.equals("_220_frontleftdoor")) {
                    c = '(';
                    break;
                }
                break;
            case 75114088:
                if (str.equals("_220_Active_brake_assist")) {
                    c = ')';
                    break;
                }
                break;
            case 111379720:
                if (str.equals("_220_energy_recycle_lev")) {
                    c = '*';
                    break;
                }
                break;
            case 342065334:
                if (str.equals("_220_negative_ion_mode")) {
                    c = '+';
                    break;
                }
                break;
            case 389223571:
                if (str.equals("_220_Intelligent_Perception_System")) {
                    c = ',';
                    break;
                }
                break;
            case 460497606:
                if (str.equals("_220_ElectricTailgate")) {
                    c = '-';
                    break;
                }
                break;
            case 466028160:
                if (str.equals("_220_left_seat_heat")) {
                    c = '.';
                    break;
                }
                break;
            case 489704252:
                if (str.equals("_220_manually_adjustable_exterior_mirror_angle")) {
                    c = '/';
                    break;
                }
                break;
            case 561990276:
                if (str.equals("_220_Memorize_the_current_driving_mode")) {
                    c = '0';
                    break;
                }
                break;
            case 600351815:
                if (str.equals("charging_set_2")) {
                    c = '1';
                    break;
                }
                break;
            case 600351819:
                if (str.equals("charging_set_6")) {
                    c = '2';
                    break;
                }
                break;
            case 615606949:
                if (str.equals("_220_Lane_assist_switch")) {
                    c = '3';
                    break;
                }
                break;
            case 766972267:
                if (str.equals("_220_Ventilate_While_Smoking")) {
                    c = '4';
                    break;
                }
                break;
            case 785457789:
                if (str.equals("_220_Gestures_To_Cut_The_Song")) {
                    c = '5';
                    break;
                }
                break;
            case 816109113:
                if (str.equals("loop_set_1")) {
                    c = '6';
                    break;
                }
                break;
            case 816109114:
                if (str.equals("loop_set_2")) {
                    c = '7';
                    break;
                }
                break;
            case 816109115:
                if (str.equals("loop_set_3")) {
                    c = '8';
                    break;
                }
                break;
            case 816109116:
                if (str.equals("loop_set_4")) {
                    c = '9';
                    break;
                }
                break;
            case 816109117:
                if (str.equals("loop_set_5")) {
                    c = ':';
                    break;
                }
                break;
            case 816109118:
                if (str.equals("loop_set_6")) {
                    c = ';';
                    break;
                }
                break;
            case 816109119:
                if (str.equals("loop_set_7")) {
                    c = Typography.less;
                    break;
                }
                break;
            case 837090372:
                if (str.equals("_220_fog_lights_steering_assist")) {
                    c = '=';
                    break;
                }
                break;
            case 896830732:
                if (str.equals("_220_Convenient_For_Car")) {
                    c = Typography.greater;
                    break;
                }
                break;
            case 1031618892:
                if (str.equals("_220_driver_mode")) {
                    c = '?';
                    break;
                }
                break;
            case 1186387950:
                if (str.equals("_220_intelligent_active_lock")) {
                    c = '@';
                    break;
                }
                break;
            case 1253609831:
                if (str.equals("_220_airconditioned_comfort_curve_settings")) {
                    c = 'A';
                    break;
                }
                break;
            case 1304098434:
                if (str.equals("_220_Unlock_the_ventilation")) {
                    c = 'B';
                    break;
                }
                break;
            case 1364465153:
                if (str.equals("_220_Sight_Bright_Screen")) {
                    c = 'C';
                    break;
                }
                break;
            case 1411310665:
                if (str.equals("_220_automatically_adjusting_the_angle_of_the_outer_mirror")) {
                    c = 'D';
                    break;
                }
                break;
            case 1419739241:
                if (str.equals("_220_the_instrument_cluster_alarm_volume")) {
                    c = 'E';
                    break;
                }
                break;
            case 1483466642:
                if (str.equals("_220_automatic_driving_seat_heating")) {
                    c = 'F';
                    break;
                }
                break;
            case 1518455214:
                if (str.equals("_220_Electric_Tailgate_Open_Position")) {
                    c = 'G';
                    break;
                }
                break;
            case 1596681492:
                if (str.equals("_220_and_the_right_auxiliary_line")) {
                    c = 'H';
                    break;
                }
                break;
            case 1661375245:
                if (str.equals("_220_seat_welcome_light")) {
                    c = 'I';
                    break;
                }
                break;
            case 1671878986:
                if (str.equals("_220_Fatigue_Testing")) {
                    c = 'J';
                    break;
                }
                break;
            case 1700563150:
                if (str.equals("_220_Automatic_Wiper_Function")) {
                    c = 'K';
                    break;
                }
                break;
            case 1718316607:
                if (str.equals("_220_automatic_folding_exterior_rear_view_mirror")) {
                    c = 'L';
                    break;
                }
                break;
            case 1752510671:
                if (str.equals("_220_open_trunk_induction")) {
                    c = 'M';
                    break;
                }
                break;
            case 1859527651:
                if (str.equals("_220_smart_unlock_initiative")) {
                    c = 'N';
                    break;
                }
                break;
            case 1915332337:
                if (str.equals("_220_Wiper_maintenance_mode")) {
                    c = 'O';
                    break;
                }
                break;
            case 1943420554:
                if (str.equals("_220_rear_wiper_automatic_reverse_function")) {
                    c = 'P';
                    break;
                }
                break;
            case 2028924223:
                if (str.equals("_220_ambient_light_control")) {
                    c = 'Q';
                    break;
                }
                break;
            case 2076384769:
                if (str.equals("_220_daytime_running_lights")) {
                    c = 'R';
                    break;
                }
                break;
            case 2134220680:
                if (str.equals("_220_auto_outer_loop_control")) {
                    c = 'S';
                    break;
                }
                break;
            case 2143978074:
                if (str.equals("_220_Auto_Close_Windows")) {
                    c = 'T';
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 55, (byte) swapVal(i)});
                break;
            case 1:
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -120, 2, 14});
                            break;
                        } else {
                            CanbusMsgSender.sendMsg(new byte[]{22, -120, 2, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED});
                            break;
                        }
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -120, 2, 3});
                        break;
                    }
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, -120, 2, 2});
                    break;
                }
            case 2:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 7, (byte) i});
                break;
            case 3:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 46, (byte) swapVal(i)});
                break;
            case 4:
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -120, 5, 18});
                            break;
                        } else {
                            CanbusMsgSender.sendMsg(new byte[]{22, -120, 5, 17});
                            break;
                        }
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -120, 5, 6});
                        break;
                    }
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, -120, 5, 5});
                    break;
                }
            case 5:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 2, (byte) swapVal(i)});
                break;
            case 6:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 47, (byte) swapVal(i)});
                break;
            case 7:
                CanbusMsgSender.sendMsg(new byte[]{22, -120, 5, (byte) i});
                break;
            case '\b':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 1, (byte) plusVal(i)});
                break;
            case '\t':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 52, (byte) plusVal(i)});
                break;
            case '\n':
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -120, 4, 14});
                            break;
                        } else {
                            CanbusMsgSender.sendMsg(new byte[]{22, -120, 4, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED});
                            break;
                        }
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -120, 4, 3});
                        break;
                    }
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, -120, 4, 2});
                    break;
                }
            case 11:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 38, (byte) plusVal(i)});
                break;
            case '\f':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 21, (byte) plusVal(i)});
                break;
            case '\r':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 15, (byte) swapVal(i)});
                break;
            case 14:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) swapVal(i)});
                break;
            case 15:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 11, (byte) plusVal(i)});
                break;
            case 16:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 51, (byte) swapVal(i)});
                break;
            case 17:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 23, (byte) i});
                break;
            case 18:
                CanbusMsgSender.sendMsg(new byte[]{22, -87, 4, (byte) swapVal(i)});
                break;
            case 19:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 57, (byte) swapVal(i)});
                break;
            case 20:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 14, (byte) swapVal(i)});
                break;
            case 21:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 60, (byte) swapVal(i)});
                break;
            case 22:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 50, (byte) plusVal(i)});
                break;
            case 23:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, (byte) swapVal(i)});
                break;
            case 24:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 43, (byte) swapVal(i)});
                break;
            case 25:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 37, (byte) plusVal(i)});
                break;
            case 26:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 18, (byte) plusVal(i)});
                break;
            case 27:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 48, (byte) plusVal(i)});
                break;
            case 28:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 44, (byte) swapVal(i)});
                break;
            case 29:
                CanbusMsgSender.sendMsg(new byte[]{22, -120, 6, (byte) (i + 1)});
                break;
            case 30:
                if (this.EachID == 8) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -123, 5, (byte) swapVal(i)});
                    break;
                }
                break;
            case 31:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 26, (byte) swapVal(i)});
                break;
            case ' ':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) swapVal(i)});
                break;
            case '!':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 35, (byte) swapVal(i)});
                break;
            case '\"':
                if (this.EachID == 8) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -123, 2, (byte) swapVal(i)});
                    break;
                }
                break;
            case '#':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 28, (byte) swapVal(i)});
                break;
            case '$':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 6, (byte) swapVal(i)});
                break;
            case '%':
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -120, 1, 14});
                            break;
                        } else {
                            CanbusMsgSender.sendMsg(new byte[]{22, -120, 1, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED});
                            break;
                        }
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -120, 1, 3});
                        break;
                    }
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, -120, 1, 2});
                    break;
                }
            case '&':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 58, (byte) swapVal(i)});
                break;
            case '\'':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 16, (byte) swapVal(i)});
                break;
            case '(':
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -120, 3, 14});
                            break;
                        } else {
                            CanbusMsgSender.sendMsg(new byte[]{22, -120, 3, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED});
                            break;
                        }
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -120, 3, 3});
                        break;
                    }
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, -120, 3, 2});
                    break;
                }
            case ')':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 45, (byte) swapVal(i)});
                break;
            case '*':
                CanbusMsgSender.sendMsg(new byte[]{22, -87, 3, (byte) i});
                break;
            case '+':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 24, (byte) swapVal(i)});
                break;
            case ',':
                if (this.EachID == 8) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -123, 1, (byte) swapVal(i)});
                    break;
                }
                break;
            case '-':
                CanbusMsgSender.sendMsg(new byte[]{22, -120, 7, (byte) (i + 1)});
                break;
            case '.':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 22, (byte) i});
                break;
            case '/':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 31, (byte) swapVal(i)});
                break;
            case '0':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 56, (byte) swapVal(i)});
                break;
            case '1':
                if (isHaveCam360()) {
                    SharePreUtil.setIntValue(this.mContext, "_220_SAVE_360", i);
                    this.mMsgMgr.init360Disp(this.mContext);
                    ParkPageUiSet parkPageUiSet = getParkPageUiSet(this.mContext);
                    parkPageUiSet.setShowRadar(i == 0);
                    parkPageUiSet.setShowPanoramic(i == 1);
                    if (this.mDifferent == 3) {
                        parkPageUiSet.setShowCusPanoramicView(i == 1);
                        break;
                    }
                }
                break;
            case '2':
                MsgMgr.mNewEnergyData = DataHandleUtils.setIntByteWithBit(MsgMgr.mNewEnergyData, 0, i == 1);
                sndNewEnergyCmdDatas();
                break;
            case '3':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 49, (byte) swapVal(i)});
                break;
            case '4':
                if (this.EachID == 8) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -123, 4, (byte) swapVal(i)});
                    break;
                }
                break;
            case '5':
                if (this.EachID == 8) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -123, 7, (byte) swapVal(i)});
                    break;
                }
                break;
            case '6':
                MsgMgr.mNewEnergyData = DataHandleUtils.setIntByteWithBit(MsgMgr.mNewEnergyData, 1, i == 1);
                sndNewEnergyCmdDatas();
                break;
            case '7':
                MsgMgr.mNewEnergyData = DataHandleUtils.setIntByteWithBit(MsgMgr.mNewEnergyData, 2, i == 1);
                sndNewEnergyCmdDatas();
                break;
            case '8':
                MsgMgr.mNewEnergyData = DataHandleUtils.setIntByteWithBit(MsgMgr.mNewEnergyData, 3, i == 1);
                sndNewEnergyCmdDatas();
                break;
            case '9':
                MsgMgr.mNewEnergyData = DataHandleUtils.setIntByteWithBit(MsgMgr.mNewEnergyData, 4, i == 1);
                sndNewEnergyCmdDatas();
                break;
            case ':':
                MsgMgr.mNewEnergyData = DataHandleUtils.setIntByteWithBit(MsgMgr.mNewEnergyData, 5, i == 1);
                sndNewEnergyCmdDatas();
                break;
            case ';':
                MsgMgr.mNewEnergyData = DataHandleUtils.setIntByteWithBit(MsgMgr.mNewEnergyData, 6, i == 1);
                sndNewEnergyCmdDatas();
                break;
            case '<':
                MsgMgr.mNewEnergyData = DataHandleUtils.setIntByteWithBit(MsgMgr.mNewEnergyData, 7, i == 1);
                sndNewEnergyCmdDatas();
                break;
            case '=':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 19, (byte) swapVal(i)});
                break;
            case '>':
                if (this.EachID == 8) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -123, 8, (byte) swapVal(i)});
                    break;
                }
                break;
            case '?':
                CanbusMsgSender.sendMsg(new byte[]{22, -87, 5, (byte) swapVal(i)});
                break;
            case '@':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 29, (byte) swapVal(i)});
                break;
            case 'A':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 4, (byte) plusVal(i)});
                break;
            case 'B':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 59, (byte) swapVal(i)});
                break;
            case 'C':
                if (this.EachID == 8) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -123, 6, (byte) swapVal(i)});
                    break;
                }
                break;
            case 'D':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 32, (byte) plusVal(i)});
                break;
            case 'E':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 8, (byte) plusVal(i)});
                break;
            case 'F':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 5, (byte) swapVal(i)});
                break;
            case 'G':
                CanbusMsgSender.sendMsg(new byte[]{22, -120, 8, (byte) (i + 1)});
                break;
            case 'H':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 34, (byte) swapVal(i)});
                break;
            case 'I':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 25, (byte) swapVal(i)});
                break;
            case 'J':
                if (this.EachID == 8) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -123, 3, (byte) swapVal(i)});
                    break;
                }
                break;
            case 'K':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 40, (byte) swapVal(i)});
                break;
            case 'L':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 27, (byte) swapVal(i)});
                break;
            case 'M':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 39, (byte) plusVal(i)});
                break;
            case 'N':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 30, (byte) swapVal(i)});
                break;
            case 'O':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 54, (byte) swapVal(i)});
                break;
            case 'P':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 17, (byte) swapVal(i)});
                break;
            case 'Q':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 36, (byte) swapVal(i)});
                break;
            case 'R':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 20, (byte) swapVal(i)});
                break;
            case 'S':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 3, (byte) plusVal(i)});
                break;
            case 'T':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 53, (byte) swapVal(i)});
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.hzbhd.canbus.car._220.UiMgr$19] */
    public void sendAirCommand(final int i) {
        new Thread() { // from class: com.hzbhd.canbus.car._220.UiMgr.19
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                CanbusMsgSender.sendMsg(new byte[]{22, -88, (byte) i, 1});
                try {
                    sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -88, (byte) i, 0});
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendTrumpchiBottomBtn1(Context context, MotionEvent motionEvent) {
        float x = (int) motionEvent.getX();
        float y = (int) motionEvent.getY();
        float dimenByResId = CommUtil.getDimenByResId(context, "dp239");
        float dimenByResId2 = CommUtil.getDimenByResId(context, "dp530");
        if (x < 0.0f || x > dimenByResId || y < dimenByResId2 || motionEvent.getAction() != 1) {
            return;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -55, 1, 6, 0, 0});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendTrumpchiBottomBtn2(Context context, MotionEvent motionEvent) {
        float x = (int) motionEvent.getX();
        float y = (int) motionEvent.getY();
        float dimenByResId = CommUtil.getDimenByResId(context, "dp239");
        float dimenByResId2 = CommUtil.getDimenByResId(context, "dp530");
        if (x <= dimenByResId || x > dimenByResId * 2.0f || y < dimenByResId2 || motionEvent.getAction() != 1) {
            return;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -55, 1, 7, 0, 0});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendTrumpchiBottomBtn3(Context context, MotionEvent motionEvent) {
        float x = (int) motionEvent.getX();
        float y = (int) motionEvent.getY();
        float dimenByResId = CommUtil.getDimenByResId(context, "dp239");
        float dimenByResId2 = CommUtil.getDimenByResId(context, "dp530");
        if (x <= 2.0f * dimenByResId || x > dimenByResId * 3.0f || y < dimenByResId2 || motionEvent.getAction() != 1) {
            return;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -55, 1, 8, 0, 0});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendTrumpchiBtnUp(Context context, MotionEvent motionEvent) {
        motionEvent.getX();
        if (((int) motionEvent.getY()) > CommUtil.getDimenByResId(context, "dp256") || motionEvent.getAction() != 1) {
            return;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -55, 1, 1, 0, 0});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendTrumpchiBtnDn(Context context, MotionEvent motionEvent) {
        motionEvent.getX();
        float y = (int) motionEvent.getY();
        float dimenByResId = CommUtil.getDimenByResId(context, "dp366");
        float dimenByResId2 = CommUtil.getDimenByResId(context, "dp485");
        if (y <= dimenByResId || y > dimenByResId2 || motionEvent.getAction() != 1) {
            return;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -55, 1, 2, 0, 0});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendTrumpchiBtnLeft(Context context, MotionEvent motionEvent) {
        float x = (int) motionEvent.getX();
        float y = (int) motionEvent.getY();
        float dimenByResId = CommUtil.getDimenByResId(context, "dp256");
        float dimenByResId2 = CommUtil.getDimenByResId(context, "dp350");
        float dimenByResId3 = CommUtil.getDimenByResId(context, "dp282");
        if (y <= dimenByResId || y > dimenByResId2 || x <= 0.0f || x > dimenByResId3 || motionEvent.getAction() != 1) {
            return;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -55, 1, 3, 0, 0});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendTrumpchiBtnRight(Context context, MotionEvent motionEvent) {
        float x = (int) motionEvent.getX();
        float y = (int) motionEvent.getY();
        float dimenByResId = CommUtil.getDimenByResId(context, "dp256");
        float dimenByResId2 = CommUtil.getDimenByResId(context, "dp350");
        float dimenByResId3 = CommUtil.getDimenByResId(context, "dp427");
        float dimenByResId4 = CommUtil.getDimenByResId(context, "dp716");
        if (y <= dimenByResId || y > dimenByResId2 || x <= dimenByResId3 || x > dimenByResId4 || motionEvent.getAction() != 1) {
            return;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -55, 1, 4, 0, 0});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendTrumpchiBtnOk(Context context, MotionEvent motionEvent) {
        float x = (int) motionEvent.getX();
        float y = (int) motionEvent.getY();
        float dimenByResId = CommUtil.getDimenByResId(context, "dp256");
        float dimenByResId2 = CommUtil.getDimenByResId(context, "dp366");
        float dimenByResId3 = CommUtil.getDimenByResId(context, "dp282");
        float dimenByResId4 = CommUtil.getDimenByResId(context, "dp427");
        if (y <= dimenByResId || y > dimenByResId2 || x <= dimenByResId3 || x > dimenByResId4 || motionEvent.getAction() != 1) {
            return;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -55, 1, 5, 0, 0});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isInThrumpchiRightTouchArea(Context context, MotionEvent motionEvent) {
        float x = (int) motionEvent.getX();
        motionEvent.getY();
        return x >= ((float) ((((int) ((float) CommUtil.getDimenByResId(context, "dp1024"))) * 7) / 10));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendThrumpchiRightTouchCoord(Context context, MotionEvent motionEvent) {
        float x = (int) motionEvent.getX();
        float y = (int) motionEvent.getY();
        int dimenByResId = (int) (((x - ((7.0f * CommUtil.getDimenByResId(context, "dp1024")) / 10.0f)) * ((int) (1280.0f / r2))) + 900.0f);
        int dimenByResId2 = (int) (y * ((int) (720.0f / CommUtil.getDimenByResId(context, "dp600"))));
        int i = dimenByResId2 / 16;
        int i2 = ((dimenByResId2 % 16) << 4) | (dimenByResId / 256);
        int i3 = dimenByResId % 256;
        if (motionEvent.getAction() == 1) {
            Util.reportCanbusInfo(new byte[]{22, -55, 2, (byte) i, (byte) i2, (byte) i3});
        }
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public View getCusPanoramicView(Context context) {
        if (this.mMyPanoramicView == null) {
            this.mMyPanoramicView = new MyPanoramicView(context);
        }
        return this.mMyPanoramicView;
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

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
        if (this.msgMgr == null) {
            this.msgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.msgMgr;
    }
}
