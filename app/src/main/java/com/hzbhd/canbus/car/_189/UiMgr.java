package com.hzbhd.canbus.car._189;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.interfaces.OnTirePageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.common.settings.constant.BodaSysContant;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.text.Typography;
import nfore.android.bt.res.NfDef;

/* loaded from: classes.dex */
public class UiMgr extends AbstractUiMgr {
    private AirActivity mActivity;
    private Context mContext;
    private MsgMgr msgMgr;
    private UiMgr uiMgr;
    private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._189.UiMgr.3
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.airBtnClick(3, 1);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.airBtnClick(3, 0);
        }
    };
    private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerCenter = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._189.UiMgr.4
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.airBtnClick(5, 5);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.airBtnClick(5, 5);
        }
    };
    private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._189.UiMgr.5
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.airBtnClick(4, 1);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.airBtnClick(4, 0);
        }
    };
    private OnAirWindSpeedUpDownClickListener mSetWindSpeedViewOnClickListener = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._189.UiMgr.6
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            int i = GeneralAirData.front_wind_level - 1;
            if (i < 0) {
                i = 0;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 0, (byte) ((i << 4) & NfDef.STATE_3WAY_M_HOLD), 0, 0, 0, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            int i = GeneralAirData.front_wind_level + 1;
            if (i > 8) {
                i = 8;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 0, (byte) ((i << 4) & NfDef.STATE_3WAY_M_HOLD), 0, 0, 0, 0});
        }
    };
    private OnAirBtnClickListener mOnAirTopBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._189.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                if (Settings.Global.getInt(UiMgr.this.mContext.getContentResolver(), BodaSysContant.Can.SwitchAcTemperature, 0) == 1) {
                    UiMgr.this.airBtnClick(5, 1);
                    return;
                } else {
                    UiMgr.this.airBtnClick(5, 0);
                    return;
                }
            }
            if (i == 1) {
                UiMgr.this.airBtnClick(0, 4);
                return;
            }
            if (i == 2) {
                UiMgr.this.airBtnClick(5, 5);
                return;
            }
            if (i == 3) {
                UiMgr.this.airBtnClick(1, 2);
            } else {
                if (i != 4) {
                    return;
                }
                if (Settings.Global.getInt(UiMgr.this.mContext.getContentResolver(), BodaSysContant.Can.SwitchAcTemperature, 0) == 1) {
                    UiMgr.this.airBtnClick(5, 0);
                } else {
                    UiMgr.this.airBtnClick(5, 1);
                }
            }
        }
    };
    private OnAirBtnClickListener mOnAirBottomLeftBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._189.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.airBtnClick(0, 0);
            } else if (i == 1) {
                UiMgr.this.airBtnClick(5, 6);
            }
        }
    };
    private OnAirBtnClickListener mOnAirBottomRightBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._189.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr.this.airBtnClick(0, 7);
        }
    };
    private OnAirBtnClickListener mOnAirBottomBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._189.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.airBtnClick(0, 1);
                return;
            }
            if (i == 1) {
                UiMgr.this.airBtnClick(0, 5);
                return;
            }
            if (i == 2) {
                UiMgr.this.airBtnClick(2, 0);
            } else if (i == 3) {
                UiMgr.this.airBtnClick(1, 3);
            } else {
                if (i != 4) {
                    return;
                }
                UiMgr.this.airBtnClick(5, 7);
            }
        }
    };
    private OnAirSeatHeatColdMinPlusClickListener mFrontLeftSeatHeatColdListener = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._189.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
            UiMgr.this.airBtnClick(5, 0);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr.this.airBtnClick(5, 0);
        }
    };
    private OnAirSeatHeatColdMinPlusClickListener mFrontRightSeatHeatColdListener = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._189.UiMgr.12
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
            UiMgr.this.airBtnClick(5, 1);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr.this.airBtnClick(5, 1);
        }
    };
    private OnAirPageStatusListener mOnAirPageStatusListener = new OnAirPageStatusListener() { // from class: com.hzbhd.canbus.car._189.UiMgr.13
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener
        public void onStatusChange(int i) {
            if (i == 1) {
                UiMgr.this.sendData(new byte[]{22, -112, 36});
                UiMgr.this.sendData(new byte[]{22, -112, 54});
            }
        }
    };
    private OnSettingPageStatusListener mOnSettingPageStatusListener = new OnSettingPageStatusListener() { // from class: com.hzbhd.canbus.car._189.UiMgr.14
        @Override // com.hzbhd.canbus.interfaces.OnSettingPageStatusListener
        public void onStatusChange(int i) {
            if (i == 0) {
                UiMgr.this.sendData(new byte[]{22, -112, ByteCompanionObject.MAX_VALUE});
                UiMgr.this.mContext.sendBroadcast(new Intent(com.hzbhd.canbus.car._237.MsgMgr.UPDATE_SETTING_ACTION));
            }
        }
    };
    private OnSettingItemSelectListener mOnSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._189.UiMgr.15
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.getMsgMgr(uiMgr.mContext).updateSetting(i, i2, i3);
            UiMgr uiMgr2 = UiMgr.this;
            String titleSrn = uiMgr2.getSettingUiSet(uiMgr2.mContext).getList().get(i).getItemList().get(i2).getTitleSrn();
            titleSrn.hashCode();
            char c = 65535;
            switch (titleSrn.hashCode()) {
                case -2038413889:
                    if (titleSrn.equals("geely_auto_close_the_window_after_locking")) {
                        c = 0;
                        break;
                    }
                    break;
                case -1803284089:
                    if (titleSrn.equals("_189_3d_view")) {
                        c = 1;
                        break;
                    }
                    break;
                case -1782367495:
                    if (titleSrn.equals("front_collision_warning")) {
                        c = 2;
                        break;
                    }
                    break;
                case -1532616569:
                    if (titleSrn.equals("_189_front_view")) {
                        c = 3;
                        break;
                    }
                    break;
                case -1531228540:
                    if (titleSrn.equals("fisheye_correction")) {
                        c = 4;
                        break;
                    }
                    break;
                case -1503791253:
                    if (titleSrn.equals("geely_double_flash_tips_when_door_open")) {
                        c = 5;
                        break;
                    }
                    break;
                case -1313804765:
                    if (titleSrn.equals("lock_auto_close_position_light")) {
                        c = 6;
                        break;
                    }
                    break;
                case -1101576519:
                    if (titleSrn.equals("language_status")) {
                        c = 7;
                        break;
                    }
                    break;
                case -890108588:
                    if (titleSrn.equals("_189_rear_view")) {
                        c = '\b';
                        break;
                    }
                    break;
                case -607138901:
                    if (titleSrn.equals("baojun_car_set")) {
                        c = '\t';
                        break;
                    }
                    break;
                case -581860367:
                    if (titleSrn.equals("geely_theme_colors")) {
                        c = '\n';
                        break;
                    }
                    break;
                case -576028092:
                    if (titleSrn.equals("single_reversing_image")) {
                        c = 11;
                        break;
                    }
                    break;
                case -574355914:
                    if (titleSrn.equals("geely_parking_unlock")) {
                        c = '\f';
                        break;
                    }
                    break;
                case -445499202:
                    if (titleSrn.equals("geely_daytime_running_lights")) {
                        c = '\r';
                        break;
                    }
                    break;
                case -436118719:
                    if (titleSrn.equals("geely_auto_close_position_light_after_locking")) {
                        c = 14;
                        break;
                    }
                    break;
                case -260900821:
                    if (titleSrn.equals("geely_remote_lock_feedback")) {
                        c = 15;
                        break;
                    }
                    break;
                case -168434144:
                    if (titleSrn.equals("auxiliary_follow_up_steering")) {
                        c = 16;
                        break;
                    }
                    break;
                case -91583442:
                    if (titleSrn.equals("geely_speed_lock")) {
                        c = 17;
                        break;
                    }
                    break;
                case -48647515:
                    if (titleSrn.equals("_189_track_view")) {
                        c = 18;
                        break;
                    }
                    break;
                case 100725:
                    if (titleSrn.equals("esc")) {
                        c = 19;
                        break;
                    }
                    break;
                case 77591115:
                    if (titleSrn.equals("_189_car_setting_1")) {
                        c = 20;
                        break;
                    }
                    break;
                case 77591117:
                    if (titleSrn.equals("_189_car_setting_3")) {
                        c = 21;
                        break;
                    }
                    break;
                case 77591118:
                    if (titleSrn.equals("_189_car_setting_4")) {
                        c = 22;
                        break;
                    }
                    break;
                case 77591131:
                    if (titleSrn.equals("_189_car_setting_A")) {
                        c = 23;
                        break;
                    }
                    break;
                case 77591132:
                    if (titleSrn.equals("_189_car_setting_B")) {
                        c = 24;
                        break;
                    }
                    break;
                case 77591133:
                    if (titleSrn.equals("_189_car_setting_C")) {
                        c = 25;
                        break;
                    }
                    break;
                case 77591134:
                    if (titleSrn.equals("_189_car_setting_D")) {
                        c = 26;
                        break;
                    }
                    break;
                case 77591135:
                    if (titleSrn.equals("_189_car_setting_E")) {
                        c = 27;
                        break;
                    }
                    break;
                case 77591136:
                    if (titleSrn.equals("_189_car_setting_F")) {
                        c = 28;
                        break;
                    }
                    break;
                case 78151973:
                    if (titleSrn.equals("geely_low_speed_cue")) {
                        c = 29;
                        break;
                    }
                    break;
                case 79766017:
                    if (titleSrn.equals("geely_electronic_assist_mode")) {
                        c = 30;
                        break;
                    }
                    break;
                case 468255189:
                    if (titleSrn.equals("geely_breath_model")) {
                        c = 31;
                        break;
                    }
                    break;
                case 536949215:
                    if (titleSrn.equals("geely_title_1")) {
                        c = ' ';
                        break;
                    }
                    break;
                case 536949216:
                    if (titleSrn.equals("geely_title_2")) {
                        c = '!';
                        break;
                    }
                    break;
                case 536949217:
                    if (titleSrn.equals("geely_title_3")) {
                        c = Typography.quote;
                        break;
                    }
                    break;
                case 536949218:
                    if (titleSrn.equals("geely_title_4")) {
                        c = '#';
                        break;
                    }
                    break;
                case 536949219:
                    if (titleSrn.equals("geely_title_5")) {
                        c = Typography.dollar;
                        break;
                    }
                    break;
                case 536949220:
                    if (titleSrn.equals("geely_title_6")) {
                        c = '%';
                        break;
                    }
                    break;
                case 536949221:
                    if (titleSrn.equals("geely_title_7")) {
                        c = Typography.amp;
                        break;
                    }
                    break;
                case 536949222:
                    if (titleSrn.equals("geely_title_8")) {
                        c = '\'';
                        break;
                    }
                    break;
                case 649954453:
                    if (titleSrn.equals("geely_close_the_sunroof_after_locking")) {
                        c = '(';
                        break;
                    }
                    break;
                case 846874949:
                    if (titleSrn.equals("dynamic_trace")) {
                        c = ')';
                        break;
                    }
                    break;
                case 1037260512:
                    if (titleSrn.equals("geely_power_down_auo_unlock")) {
                        c = '*';
                        break;
                    }
                    break;
                case 1043320734:
                    if (titleSrn.equals("geely_remote_lock_tune")) {
                        c = '+';
                        break;
                    }
                    break;
                case 1152847106:
                    if (titleSrn.equals("voice_skylight")) {
                        c = ',';
                        break;
                    }
                    break;
                case 1411874120:
                    if (titleSrn.equals("r_gear_position_exits_five_second_delay")) {
                        c = '-';
                        break;
                    }
                    break;
                case 1564493009:
                    if (titleSrn.equals("_189_left_view")) {
                        c = '.';
                        break;
                    }
                    break;
                case 1589098083:
                    if (titleSrn.equals("geely_smart_cornering_light")) {
                        c = '/';
                        break;
                    }
                    break;
                case 1614664871:
                    if (titleSrn.equals("_189_steering_view")) {
                        c = '0';
                        break;
                    }
                    break;
                case 1683822644:
                    if (titleSrn.equals("static_trace")) {
                        c = '1';
                        break;
                    }
                    break;
                case 1698741364:
                    if (titleSrn.equals("_189_right_view")) {
                        c = '2';
                        break;
                    }
                    break;
                case 1708548990:
                    if (titleSrn.equals("geely_fortification_prompt_type")) {
                        c = '3';
                        break;
                    }
                    break;
                case 1720570364:
                    if (titleSrn.equals("geely_emergency_brake_auto")) {
                        c = '4';
                        break;
                    }
                    break;
                case 1788772482:
                    if (titleSrn.equals("open_door_light_sign")) {
                        c = '5';
                        break;
                    }
                    break;
                case 1790874862:
                    if (titleSrn.equals("_189_open_big_light")) {
                        c = '6';
                        break;
                    }
                    break;
                case 1918412771:
                    if (titleSrn.equals("geely_boyue_1")) {
                        c = '7';
                        break;
                    }
                    break;
                case 1918412772:
                    if (titleSrn.equals("geely_boyue_2")) {
                        c = '8';
                        break;
                    }
                    break;
                case 1918412773:
                    if (titleSrn.equals("geely_boyue_3")) {
                        c = '9';
                        break;
                    }
                    break;
                case 1918412774:
                    if (titleSrn.equals("geely_boyue_4")) {
                        c = ':';
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 8, (byte) i3});
                    break;
                case 1:
                    byte[] bArr = new byte[3];
                    bArr[0] = 22;
                    bArr[1] = -126;
                    bArr[2] = (byte) (i3 == 1 ? 8 : 9);
                    CanbusMsgSender.sendMsg(bArr);
                    break;
                case 2:
                    CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 22, (byte) i3});
                    break;
                case 3:
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, 4});
                    break;
                case 4:
                    byte[] bArr2 = new byte[3];
                    bArr2[0] = 22;
                    bArr2[1] = -126;
                    bArr2[2] = (byte) (i3 == 1 ? 16 : 17);
                    CanbusMsgSender.sendMsg(bArr2);
                    break;
                case 5:
                case '5':
                    CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 2, (byte) i3});
                    break;
                case 6:
                case 14:
                    CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 4, (byte) i3});
                    break;
                case 7:
                    CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 26, (byte) i3});
                    break;
                case '\b':
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, 5});
                    break;
                case '\t':
                    CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 20, (byte) i3});
                    break;
                case '\n':
                    CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 28, (byte) i3});
                    break;
                case 11:
                    byte[] bArr3 = new byte[3];
                    bArr3[0] = 22;
                    bArr3[1] = -126;
                    bArr3[2] = (byte) (i3 == 1 ? 20 : 21);
                    CanbusMsgSender.sendMsg(bArr3);
                    break;
                case '\f':
                case '*':
                case ':':
                    CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 1, (byte) i3});
                    break;
                case '\r':
                    CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 10, (byte) i3});
                    break;
                case 15:
                case '+':
                    CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 3, (byte) i3});
                    break;
                case 16:
                    byte[] bArr4 = new byte[3];
                    bArr4[0] = 22;
                    bArr4[1] = -126;
                    bArr4[2] = (byte) (i3 == 1 ? 22 : 23);
                    CanbusMsgSender.sendMsg(bArr4);
                    break;
                case 17:
                    CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 0, (byte) i3});
                    break;
                case 18:
                    byte[] bArr5 = new byte[3];
                    bArr5[0] = 22;
                    bArr5[1] = -126;
                    bArr5[2] = (byte) (i3 == 1 ? 6 : 7);
                    CanbusMsgSender.sendMsg(bArr5);
                    break;
                case 19:
                    CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 19, (byte) i3});
                    break;
                case 20:
                    CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 40, (byte) i3});
                    break;
                case 21:
                    CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 42, (byte) i3});
                    break;
                case 22:
                    CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 43, (byte) i3});
                    break;
                case 23:
                    CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 44, (byte) i3});
                    break;
                case 24:
                    CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 45, (byte) i3});
                    break;
                case 25:
                    CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 46, (byte) i3});
                    break;
                case 26:
                    CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 47, (byte) i3});
                    break;
                case 27:
                    CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 48, (byte) i3});
                    break;
                case 28:
                    CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, ByteCompanionObject.MIN_VALUE, (byte) i3});
                    break;
                case 29:
                    CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 27, (byte) i3});
                    break;
                case 30:
                    CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 6, (byte) i3});
                    break;
                case 31:
                    CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 18, (byte) i3});
                    break;
                case ' ':
                    CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 29, (byte) i3});
                    break;
                case '!':
                    CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 30, (byte) i3});
                    break;
                case '\"':
                    CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 31, (byte) i3});
                    break;
                case '#':
                    CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 32, (byte) i3});
                    break;
                case '$':
                    CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 33, (byte) i3});
                    break;
                case '%':
                    CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 34, (byte) i3});
                    break;
                case '&':
                    CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 35, (byte) i3});
                    break;
                case '\'':
                    CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 36, (byte) i3});
                    break;
                case '(':
                    CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 9, (byte) i3});
                    break;
                case ')':
                    byte[] bArr6 = new byte[3];
                    bArr6[0] = 22;
                    bArr6[1] = -126;
                    bArr6[2] = (byte) (i3 == 1 ? 14 : 15);
                    CanbusMsgSender.sendMsg(bArr6);
                    break;
                case ',':
                    CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, (byte) (i3 + 11), 0});
                    break;
                case '-':
                    byte[] bArr7 = new byte[3];
                    bArr7[0] = 22;
                    bArr7[1] = -126;
                    bArr7[2] = (byte) (i3 == 1 ? 18 : 19);
                    CanbusMsgSender.sendMsg(bArr7);
                    break;
                case '.':
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, 2});
                    break;
                case '/':
                    CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 5, (byte) i3});
                    break;
                case '0':
                    byte[] bArr8 = new byte[3];
                    bArr8[0] = 22;
                    bArr8[1] = -126;
                    bArr8[2] = (byte) (i3 == 1 ? 10 : 11);
                    CanbusMsgSender.sendMsg(bArr8);
                    break;
                case '1':
                    byte[] bArr9 = new byte[3];
                    bArr9[0] = 22;
                    bArr9[1] = -126;
                    bArr9[2] = (byte) (i3 == 1 ? 12 : 13);
                    CanbusMsgSender.sendMsg(bArr9);
                    break;
                case '2':
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, 3});
                    break;
                case '3':
                    CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 17, (byte) i3});
                    break;
                case '4':
                    CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 21, (byte) i3});
                    break;
                case '6':
                    if (i3 != 3) {
                        CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 39, (byte) i3});
                        break;
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 39, -1});
                        break;
                    }
                case '7':
                    CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 23, (byte) i3});
                    break;
                case '8':
                    CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 24, (byte) i3});
                    break;
                case '9':
                    CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 25, (byte) i3});
                    break;
            }
        }
    };
    OnSettingItemSeekbarSelectListener mOnSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._189.UiMgr.16
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
        public void onClickItem(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.getMsgMgr(uiMgr.mContext).updateSetting(i, i2, i3);
            UiMgr uiMgr2 = UiMgr.this;
            String titleSrn = uiMgr2.getSettingUiSet(uiMgr2.mContext).getList().get(i).getItemList().get(i2).getTitleSrn();
            titleSrn.hashCode();
            if (titleSrn.equals("_189_car_setting_2")) {
                CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 41, (byte) i3});
            } else if (titleSrn.equals("geely_title_9")) {
                CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 37, (byte) i3});
            }
        }
    };
    private int eachId = getEachId();

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        if (getCurrentCarId() != 15 && getCurrentCarId() != 9) {
            removeMainPrjBtnByName(this.mContext, MainAction.DRIVE_DATA);
        }
        switch (getCurrentCarId()) {
            case 0:
                removeMainPrjBtnByName(this.mContext, MainAction.TIRE_INFO);
                removeMainPrjBtnByName(this.mContext, MainAction.SETTING);
                break;
            case 1:
                removeMainPrjBtnByName(this.mContext, MainAction.TIRE_INFO);
                removeMainPrjBtnByName(this.mContext, MainAction.SETTING);
                break;
            case 2:
                removeMainPrjBtnByName(this.mContext, MainAction.TIRE_INFO);
                removeMainPrjBtnByName(this.mContext, MainAction.SETTING);
                break;
            case 3:
                removeMainPrjBtnByName(this.mContext, MainAction.SETTING);
                break;
            case 4:
                removeMainPrjBtnByName(this.mContext, MainAction.TIRE_INFO);
                remvoeSettingLeftItemByName(this.mContext, "car_set1");
                remvoeSettingLeftItemByName(this.mContext, "car_set2");
                remvoeSettingLeftItemByName(this.mContext, "car_set3");
                remvoeSettingLeftItemByName(this.mContext, "car_set4");
                remvoeSettingLeftItemByName(this.mContext, "car_set5");
                remvoeSettingLeftItemByName(this.mContext, "car_set6");
                remvoeSettingLeftItemByName(this.mContext, "car_set7");
                remvoeSettingLeftItemByName(this.mContext, "car_set8");
                remvoeSettingLeftItemByName(this.mContext, "car_set9");
                remvoeSettingLeftItemByName(this.mContext, "car_set10");
                remvoeSettingLeftItemByName(this.mContext, "car_set11");
                remvoeSettingLeftItemByName(this.mContext, "car_set12");
                remvoeSettingLeftItemByName(this.mContext, "car_set13");
                remvoeSettingLeftItemByName(this.mContext, "car_set14");
                remvoeSettingLeftItemByName(this.mContext, "car_set15");
                remvoeSettingLeftItemByName(this.mContext, "car_set16");
                remvoeSettingLeftItemByName(this.mContext, "car_set17");
                remvoeSettingLeftItemByName(this.mContext, "car_set18");
                remvoeSettingLeftItemByName(this.mContext, "panorama_setting");
                break;
            case 5:
                removeMainPrjBtnByName(this.mContext, MainAction.TIRE_INFO);
                removeMainPrjBtnByName(this.mContext, MainAction.SETTING);
                break;
            case 6:
                removeMainPrjBtnByName(this.mContext, MainAction.TIRE_INFO);
                remvoeSettingLeftItemByName(this.mContext, "car_set");
                remvoeSettingLeftItemByName(this.mContext, "car_set2");
                remvoeSettingLeftItemByName(this.mContext, "car_set3");
                remvoeSettingLeftItemByName(this.mContext, "car_set4");
                remvoeSettingLeftItemByName(this.mContext, "car_set5");
                remvoeSettingLeftItemByName(this.mContext, "car_set6");
                remvoeSettingLeftItemByName(this.mContext, "car_set7");
                remvoeSettingLeftItemByName(this.mContext, "car_set8");
                remvoeSettingLeftItemByName(this.mContext, "car_set9");
                remvoeSettingLeftItemByName(this.mContext, "car_set10");
                remvoeSettingLeftItemByName(this.mContext, "car_set11");
                remvoeSettingLeftItemByName(this.mContext, "car_set12");
                remvoeSettingLeftItemByName(this.mContext, "car_set13");
                remvoeSettingLeftItemByName(this.mContext, "car_set14");
                remvoeSettingLeftItemByName(this.mContext, "car_set15");
                remvoeSettingLeftItemByName(this.mContext, "car_set16");
                remvoeSettingLeftItemByName(this.mContext, "car_set17");
                remvoeSettingLeftItemByName(this.mContext, "car_set18");
                remvoeSettingLeftItemByName(this.mContext, "panorama_setting");
                break;
            case 7:
                removeMainPrjBtnByName(this.mContext, MainAction.TIRE_INFO);
                remvoeSettingLeftItemByName(this.mContext, "car_set1");
                remvoeSettingLeftItemByName(this.mContext, "car_set");
                remvoeSettingLeftItemByName(this.mContext, "car_set3");
                remvoeSettingLeftItemByName(this.mContext, "car_set4");
                remvoeSettingLeftItemByName(this.mContext, "car_set5");
                remvoeSettingLeftItemByName(this.mContext, "car_set6");
                remvoeSettingLeftItemByName(this.mContext, "car_set7");
                remvoeSettingLeftItemByName(this.mContext, "car_set8");
                remvoeSettingLeftItemByName(this.mContext, "car_set9");
                remvoeSettingLeftItemByName(this.mContext, "car_set10");
                remvoeSettingLeftItemByName(this.mContext, "car_set11");
                remvoeSettingLeftItemByName(this.mContext, "car_set12");
                remvoeSettingLeftItemByName(this.mContext, "car_set13");
                remvoeSettingLeftItemByName(this.mContext, "car_set14");
                remvoeSettingLeftItemByName(this.mContext, "car_set15");
                remvoeSettingLeftItemByName(this.mContext, "car_set16");
                remvoeSettingLeftItemByName(this.mContext, "car_set17");
                remvoeSettingLeftItemByName(this.mContext, "car_set18");
                removeSettingRightItem(this.mContext, 1, 5, 11);
                removeSettingRightItemByNameList(this.mContext, new String[]{"geely_emergency_brake_auto", "front_collision_warning", "geely_boyue_1", "geely_boyue_2", "geely_boyue_3", "geely_boyue_4", "geely_auto_close_position_light_after_locking", "language_status", "voice_skylight"});
                break;
            case 8:
                removeMainPrjBtnByName(this.mContext, MainAction.SETTING);
                break;
            case 9:
                remvoeSettingLeftItemByName(this.mContext, "car_set1");
                remvoeSettingLeftItemByName(this.mContext, "car_set2");
                remvoeSettingLeftItemByName(this.mContext, "car_set3");
                remvoeSettingLeftItemByName(this.mContext, "car_set");
                remvoeSettingLeftItemByName(this.mContext, "car_set5");
                remvoeSettingLeftItemByName(this.mContext, "car_set6");
                remvoeSettingLeftItemByName(this.mContext, "car_set7");
                remvoeSettingLeftItemByName(this.mContext, "car_set8");
                remvoeSettingLeftItemByName(this.mContext, "car_set9");
                remvoeSettingLeftItemByName(this.mContext, "car_set10");
                remvoeSettingLeftItemByName(this.mContext, "car_set11");
                remvoeSettingLeftItemByName(this.mContext, "car_set12");
                remvoeSettingLeftItemByName(this.mContext, "car_set13");
                remvoeSettingLeftItemByName(this.mContext, "car_set14");
                remvoeSettingLeftItemByName(this.mContext, "car_set15");
                remvoeSettingLeftItemByName(this.mContext, "car_set16");
                remvoeSettingLeftItemByName(this.mContext, "car_set17");
                remvoeSettingLeftItemByName(this.mContext, "car_set18");
                remvoeSettingLeftItemByName(this.mContext, "panorama_setting");
                removeDriveData(this.mContext, 0, 1);
                break;
            case 10:
                removeMainPrjBtnByName(this.mContext, MainAction.TIRE_INFO);
                remvoeSettingLeftItemByName(this.mContext, "car_set1");
                remvoeSettingLeftItemByName(this.mContext, "car_set2");
                remvoeSettingLeftItemByName(this.mContext, "car_set3");
                remvoeSettingLeftItemByName(this.mContext, "car_set");
                remvoeSettingLeftItemByName(this.mContext, "car_set5");
                remvoeSettingLeftItemByName(this.mContext, "car_set6");
                remvoeSettingLeftItemByName(this.mContext, "car_set7");
                remvoeSettingLeftItemByName(this.mContext, "car_set8");
                remvoeSettingLeftItemByName(this.mContext, "car_set9");
                remvoeSettingLeftItemByName(this.mContext, "car_set10");
                remvoeSettingLeftItemByName(this.mContext, "car_set11");
                remvoeSettingLeftItemByName(this.mContext, "car_set12");
                remvoeSettingLeftItemByName(this.mContext, "car_set13");
                remvoeSettingLeftItemByName(this.mContext, "car_set14");
                remvoeSettingLeftItemByName(this.mContext, "car_set15");
                remvoeSettingLeftItemByName(this.mContext, "car_set16");
                remvoeSettingLeftItemByName(this.mContext, "car_set17");
                remvoeSettingLeftItemByName(this.mContext, "car_set18");
                removeSettingRightItem(this.mContext, 1, 0, 13);
                removeSettingRightItem(this.mContext, 1, 7, 7);
                break;
            case 11:
                removeMainPrjBtnByName(this.mContext, MainAction.TIRE_INFO);
                remvoeSettingLeftItemByName(this.mContext, "car_set1");
                remvoeSettingLeftItemByName(this.mContext, "car_set2");
                remvoeSettingLeftItemByName(this.mContext, "car_set");
                remvoeSettingLeftItemByName(this.mContext, "car_set4");
                remvoeSettingLeftItemByName(this.mContext, "car_set5");
                remvoeSettingLeftItemByName(this.mContext, "car_set6");
                remvoeSettingLeftItemByName(this.mContext, "car_set7");
                remvoeSettingLeftItemByName(this.mContext, "car_set8");
                remvoeSettingLeftItemByName(this.mContext, "car_set9");
                remvoeSettingLeftItemByName(this.mContext, "car_set10");
                remvoeSettingLeftItemByName(this.mContext, "car_set11");
                remvoeSettingLeftItemByName(this.mContext, "car_set12");
                remvoeSettingLeftItemByName(this.mContext, "car_set13");
                remvoeSettingLeftItemByName(this.mContext, "car_set14");
                remvoeSettingLeftItemByName(this.mContext, "car_set15");
                remvoeSettingLeftItemByName(this.mContext, "car_set16");
                remvoeSettingLeftItemByName(this.mContext, "car_set17");
                remvoeSettingLeftItemByName(this.mContext, "car_set18");
                remvoeSettingLeftItemByName(this.mContext, "panorama_setting");
                break;
            case 12:
                removeMainPrjBtnByName(this.mContext, MainAction.TIRE_INFO);
                remvoeSettingLeftItemByName(this.mContext, "car_set1");
                remvoeSettingLeftItemByName(this.mContext, "car_set2");
                remvoeSettingLeftItemByName(this.mContext, "car_set");
                remvoeSettingLeftItemByName(this.mContext, "car_set4");
                remvoeSettingLeftItemByName(this.mContext, "car_set5");
                remvoeSettingLeftItemByName(this.mContext, "car_set6");
                remvoeSettingLeftItemByName(this.mContext, "car_set7");
                remvoeSettingLeftItemByName(this.mContext, "car_set8");
                remvoeSettingLeftItemByName(this.mContext, "car_set9");
                remvoeSettingLeftItemByName(this.mContext, "car_set10");
                remvoeSettingLeftItemByName(this.mContext, "car_set11");
                remvoeSettingLeftItemByName(this.mContext, "car_set12");
                remvoeSettingLeftItemByName(this.mContext, "car_set13");
                remvoeSettingLeftItemByName(this.mContext, "car_set14");
                remvoeSettingLeftItemByName(this.mContext, "car_set15");
                remvoeSettingLeftItemByName(this.mContext, "car_set16");
                remvoeSettingLeftItemByName(this.mContext, "car_set17");
                remvoeSettingLeftItemByName(this.mContext, "car_set18");
                remvoeSettingLeftItemByName(this.mContext, "panorama_setting");
                break;
            case 13:
                removeMainPrjBtnByName(this.mContext, MainAction.TIRE_INFO);
                remvoeSettingLeftItemByName(this.mContext, "car_set1");
                remvoeSettingLeftItemByName(this.mContext, "car_set2");
                remvoeSettingLeftItemByName(this.mContext, "car_set3");
                remvoeSettingLeftItemByName(this.mContext, "car_set4");
                remvoeSettingLeftItemByName(this.mContext, "car_set");
                remvoeSettingLeftItemByName(this.mContext, "car_set6");
                remvoeSettingLeftItemByName(this.mContext, "car_set7");
                remvoeSettingLeftItemByName(this.mContext, "car_set8");
                remvoeSettingLeftItemByName(this.mContext, "car_set9");
                remvoeSettingLeftItemByName(this.mContext, "car_set10");
                remvoeSettingLeftItemByName(this.mContext, "car_set11");
                remvoeSettingLeftItemByName(this.mContext, "car_set12");
                remvoeSettingLeftItemByName(this.mContext, "car_set13");
                remvoeSettingLeftItemByName(this.mContext, "car_set14");
                remvoeSettingLeftItemByName(this.mContext, "car_set15");
                remvoeSettingLeftItemByName(this.mContext, "car_set16");
                remvoeSettingLeftItemByName(this.mContext, "car_set17");
                remvoeSettingLeftItemByName(this.mContext, "car_set18");
                remvoeSettingLeftItemByName(this.mContext, "panorama_setting");
                break;
            case 14:
                removeMainPrjBtnByName(this.mContext, MainAction.TIRE_INFO);
                removeMainPrjBtnByName(this.mContext, MainAction.SETTING);
                break;
            case 15:
                removeMainPrjBtnByName(this.mContext, MainAction.TIRE_INFO);
                remvoeSettingLeftItemByName(this.mContext, "car_set1");
                remvoeSettingLeftItemByName(this.mContext, "car_set2");
                remvoeSettingLeftItemByName(this.mContext, "car_set3");
                remvoeSettingLeftItemByName(this.mContext, "car_set4");
                remvoeSettingLeftItemByName(this.mContext, "car_set5");
                remvoeSettingLeftItemByName(this.mContext, "car_set");
                remvoeSettingLeftItemByName(this.mContext, "car_set7");
                remvoeSettingLeftItemByName(this.mContext, "car_set8");
                remvoeSettingLeftItemByName(this.mContext, "car_set9");
                remvoeSettingLeftItemByName(this.mContext, "car_set10");
                remvoeSettingLeftItemByName(this.mContext, "car_set11");
                remvoeSettingLeftItemByName(this.mContext, "car_set12");
                remvoeSettingLeftItemByName(this.mContext, "car_set13");
                remvoeSettingLeftItemByName(this.mContext, "car_set14");
                remvoeSettingLeftItemByName(this.mContext, "car_set15");
                remvoeSettingLeftItemByName(this.mContext, "car_set16");
                remvoeSettingLeftItemByName(this.mContext, "car_set17");
                remvoeSettingLeftItemByName(this.mContext, "car_set18");
                remvoeSettingLeftItemByName(this.mContext, "panorama_setting");
                removeDriveData(this.mContext, 1, 1);
                break;
            case 16:
                removeMainPrjBtnByName(this.mContext, MainAction.TIRE_INFO);
                remvoeSettingLeftItemByName(this.mContext, "car_set1");
                remvoeSettingLeftItemByName(this.mContext, "car_set2");
                remvoeSettingLeftItemByName(this.mContext, "car_set3");
                remvoeSettingLeftItemByName(this.mContext, "car_set4");
                remvoeSettingLeftItemByName(this.mContext, "car_set");
                remvoeSettingLeftItemByName(this.mContext, "car_set6");
                remvoeSettingLeftItemByName(this.mContext, "car_set7");
                remvoeSettingLeftItemByName(this.mContext, "car_set8");
                remvoeSettingLeftItemByName(this.mContext, "car_set9");
                remvoeSettingLeftItemByName(this.mContext, "car_set10");
                remvoeSettingLeftItemByName(this.mContext, "car_set11");
                remvoeSettingLeftItemByName(this.mContext, "car_set12");
                remvoeSettingLeftItemByName(this.mContext, "car_set13");
                remvoeSettingLeftItemByName(this.mContext, "car_set14");
                remvoeSettingLeftItemByName(this.mContext, "car_set15");
                remvoeSettingLeftItemByName(this.mContext, "car_set16");
                remvoeSettingLeftItemByName(this.mContext, "car_set17");
                remvoeSettingLeftItemByName(this.mContext, "car_set18");
                remvoeSettingLeftItemByName(this.mContext, "panorama_setting");
                break;
            case 17:
                removeMainPrjBtnByName(this.mContext, MainAction.TIRE_INFO);
                remvoeSettingLeftItemByName(this.mContext, "car_set1");
                remvoeSettingLeftItemByName(this.mContext, "car_set2");
                remvoeSettingLeftItemByName(this.mContext, "car_set3");
                remvoeSettingLeftItemByName(this.mContext, "car_set4");
                remvoeSettingLeftItemByName(this.mContext, "car_set5");
                remvoeSettingLeftItemByName(this.mContext, "car_set6");
                remvoeSettingLeftItemByName(this.mContext, "car_set");
                remvoeSettingLeftItemByName(this.mContext, "car_set8");
                remvoeSettingLeftItemByName(this.mContext, "car_set9");
                remvoeSettingLeftItemByName(this.mContext, "car_set10");
                remvoeSettingLeftItemByName(this.mContext, "car_set11");
                remvoeSettingLeftItemByName(this.mContext, "car_set12");
                remvoeSettingLeftItemByName(this.mContext, "car_set13");
                remvoeSettingLeftItemByName(this.mContext, "car_set14");
                remvoeSettingLeftItemByName(this.mContext, "car_set15");
                remvoeSettingLeftItemByName(this.mContext, "car_set16");
                remvoeSettingLeftItemByName(this.mContext, "car_set17");
                remvoeSettingLeftItemByName(this.mContext, "car_set18");
                remvoeSettingLeftItemByName(this.mContext, "panorama_setting");
                break;
            case 18:
                removeMainPrjBtnByName(this.mContext, MainAction.TIRE_INFO);
                remvoeSettingLeftItemByName(this.mContext, "car_set1");
                remvoeSettingLeftItemByName(this.mContext, "car_set2");
                remvoeSettingLeftItemByName(this.mContext, "car_set3");
                remvoeSettingLeftItemByName(this.mContext, "car_set4");
                remvoeSettingLeftItemByName(this.mContext, "car_set5");
                remvoeSettingLeftItemByName(this.mContext, "car_set6");
                remvoeSettingLeftItemByName(this.mContext, "car_set7");
                remvoeSettingLeftItemByName(this.mContext, "car_set");
                remvoeSettingLeftItemByName(this.mContext, "car_set9");
                remvoeSettingLeftItemByName(this.mContext, "car_set10");
                remvoeSettingLeftItemByName(this.mContext, "car_set11");
                remvoeSettingLeftItemByName(this.mContext, "car_set12");
                remvoeSettingLeftItemByName(this.mContext, "car_set13");
                remvoeSettingLeftItemByName(this.mContext, "car_set14");
                remvoeSettingLeftItemByName(this.mContext, "car_set15");
                remvoeSettingLeftItemByName(this.mContext, "car_set16");
                remvoeSettingLeftItemByName(this.mContext, "car_set17");
                remvoeSettingLeftItemByName(this.mContext, "car_set18");
                remvoeSettingLeftItemByName(this.mContext, "panorama_setting");
                break;
            case 19:
                remvoeSettingLeftItemByName(this.mContext, "car_set1");
                remvoeSettingLeftItemByName(this.mContext, "car_set2");
                remvoeSettingLeftItemByName(this.mContext, "car_set3");
                remvoeSettingLeftItemByName(this.mContext, "car_set4");
                remvoeSettingLeftItemByName(this.mContext, "car_set5");
                remvoeSettingLeftItemByName(this.mContext, "car_set6");
                remvoeSettingLeftItemByName(this.mContext, "car_set7");
                remvoeSettingLeftItemByName(this.mContext, "car_set8");
                remvoeSettingLeftItemByName(this.mContext, "car_set");
                remvoeSettingLeftItemByName(this.mContext, "car_set10");
                remvoeSettingLeftItemByName(this.mContext, "car_set11");
                remvoeSettingLeftItemByName(this.mContext, "car_set12");
                remvoeSettingLeftItemByName(this.mContext, "car_set13");
                remvoeSettingLeftItemByName(this.mContext, "car_set14");
                remvoeSettingLeftItemByName(this.mContext, "car_set15");
                remvoeSettingLeftItemByName(this.mContext, "car_set16");
                remvoeSettingLeftItemByName(this.mContext, "car_set17");
                remvoeSettingLeftItemByName(this.mContext, "car_set18");
                remvoeSettingLeftItemByName(this.mContext, "panorama_setting");
                break;
            case 20:
                removeMainPrjBtnByName(this.mContext, MainAction.TIRE_INFO);
                removeMainPrjBtnByName(this.mContext, MainAction.SETTING);
                break;
            case 21:
                removeMainPrjBtnByName(this.mContext, MainAction.TIRE_INFO);
                remvoeSettingLeftItemByName(this.mContext, "car_set1");
                remvoeSettingLeftItemByName(this.mContext, "car_set2");
                remvoeSettingLeftItemByName(this.mContext, "car_set3");
                remvoeSettingLeftItemByName(this.mContext, "car_set4");
                remvoeSettingLeftItemByName(this.mContext, "car_set5");
                remvoeSettingLeftItemByName(this.mContext, "car_set6");
                remvoeSettingLeftItemByName(this.mContext, "car_set7");
                remvoeSettingLeftItemByName(this.mContext, "car_set8");
                remvoeSettingLeftItemByName(this.mContext, "car_set9");
                remvoeSettingLeftItemByName(this.mContext, "car_set");
                remvoeSettingLeftItemByName(this.mContext, "car_set11");
                remvoeSettingLeftItemByName(this.mContext, "car_set12");
                remvoeSettingLeftItemByName(this.mContext, "car_set13");
                remvoeSettingLeftItemByName(this.mContext, "car_set14");
                remvoeSettingLeftItemByName(this.mContext, "car_set15");
                remvoeSettingLeftItemByName(this.mContext, "car_set16");
                remvoeSettingLeftItemByName(this.mContext, "car_set17");
                remvoeSettingLeftItemByName(this.mContext, "car_set18");
                remvoeSettingLeftItemByName(this.mContext, "panorama_setting");
                break;
            case 22:
                removeMainPrjBtnByName(this.mContext, MainAction.TIRE_INFO);
                remvoeSettingLeftItemByName(this.mContext, "car_set1");
                remvoeSettingLeftItemByName(this.mContext, "car_set2");
                remvoeSettingLeftItemByName(this.mContext, "car_set3");
                remvoeSettingLeftItemByName(this.mContext, "car_set4");
                remvoeSettingLeftItemByName(this.mContext, "car_set5");
                remvoeSettingLeftItemByName(this.mContext, "car_set6");
                remvoeSettingLeftItemByName(this.mContext, "car_set7");
                remvoeSettingLeftItemByName(this.mContext, "car_set8");
                remvoeSettingLeftItemByName(this.mContext, "car_set9");
                remvoeSettingLeftItemByName(this.mContext, "car_set10");
                remvoeSettingLeftItemByName(this.mContext, "car_set");
                remvoeSettingLeftItemByName(this.mContext, "car_set12");
                remvoeSettingLeftItemByName(this.mContext, "car_set13");
                remvoeSettingLeftItemByName(this.mContext, "car_set14");
                remvoeSettingLeftItemByName(this.mContext, "car_set15");
                remvoeSettingLeftItemByName(this.mContext, "car_set16");
                remvoeSettingLeftItemByName(this.mContext, "car_set17");
                remvoeSettingLeftItemByName(this.mContext, "car_set18");
                remvoeSettingLeftItemByName(this.mContext, "panorama_setting");
                break;
            case 23:
                removeMainPrjBtnByName(this.mContext, MainAction.TIRE_INFO);
                remvoeSettingLeftItemByName(this.mContext, "car_set1");
                remvoeSettingLeftItemByName(this.mContext, "car_set2");
                remvoeSettingLeftItemByName(this.mContext, "car_set3");
                remvoeSettingLeftItemByName(this.mContext, "car_set4");
                remvoeSettingLeftItemByName(this.mContext, "car_set5");
                remvoeSettingLeftItemByName(this.mContext, "car_set6");
                remvoeSettingLeftItemByName(this.mContext, "car_set7");
                remvoeSettingLeftItemByName(this.mContext, "car_set8");
                remvoeSettingLeftItemByName(this.mContext, "car_set9");
                remvoeSettingLeftItemByName(this.mContext, "car_set10");
                remvoeSettingLeftItemByName(this.mContext, "car_set11");
                remvoeSettingLeftItemByName(this.mContext, "car_set");
                remvoeSettingLeftItemByName(this.mContext, "car_set13");
                remvoeSettingLeftItemByName(this.mContext, "car_set14");
                remvoeSettingLeftItemByName(this.mContext, "car_set15");
                remvoeSettingLeftItemByName(this.mContext, "car_set16");
                remvoeSettingLeftItemByName(this.mContext, "car_set17");
                remvoeSettingLeftItemByName(this.mContext, "car_set18");
                remvoeSettingLeftItemByName(this.mContext, "panorama_setting");
                break;
            case 24:
                removeMainPrjBtnByName(this.mContext, MainAction.TIRE_INFO);
                remvoeSettingLeftItemByName(this.mContext, "car_set1");
                remvoeSettingLeftItemByName(this.mContext, "car_set2");
                remvoeSettingLeftItemByName(this.mContext, "car_set3");
                remvoeSettingLeftItemByName(this.mContext, "car_set4");
                remvoeSettingLeftItemByName(this.mContext, "car_set5");
                remvoeSettingLeftItemByName(this.mContext, "car_set6");
                remvoeSettingLeftItemByName(this.mContext, "car_set7");
                remvoeSettingLeftItemByName(this.mContext, "car_set8");
                remvoeSettingLeftItemByName(this.mContext, "car_set9");
                remvoeSettingLeftItemByName(this.mContext, "car_set10");
                remvoeSettingLeftItemByName(this.mContext, "car_set11");
                remvoeSettingLeftItemByName(this.mContext, "car_set12");
                remvoeSettingLeftItemByName(this.mContext, "car_set");
                remvoeSettingLeftItemByName(this.mContext, "car_set14");
                remvoeSettingLeftItemByName(this.mContext, "car_set15");
                remvoeSettingLeftItemByName(this.mContext, "car_set16");
                remvoeSettingLeftItemByName(this.mContext, "car_set17");
                remvoeSettingLeftItemByName(this.mContext, "car_set18");
                remvoeSettingLeftItemByName(this.mContext, "panorama_setting");
                break;
            case 25:
                removeMainPrjBtnByName(this.mContext, MainAction.TIRE_INFO);
                remvoeSettingLeftItemByName(this.mContext, "car_set1");
                remvoeSettingLeftItemByName(this.mContext, "car_set2");
                remvoeSettingLeftItemByName(this.mContext, "car_set3");
                remvoeSettingLeftItemByName(this.mContext, "car_set4");
                remvoeSettingLeftItemByName(this.mContext, "car_set5");
                remvoeSettingLeftItemByName(this.mContext, "car_set6");
                remvoeSettingLeftItemByName(this.mContext, "car_set7");
                remvoeSettingLeftItemByName(this.mContext, "car_set8");
                remvoeSettingLeftItemByName(this.mContext, "car_set9");
                remvoeSettingLeftItemByName(this.mContext, "car_set10");
                remvoeSettingLeftItemByName(this.mContext, "car_set11");
                remvoeSettingLeftItemByName(this.mContext, "car_set12");
                remvoeSettingLeftItemByName(this.mContext, "car_set13");
                remvoeSettingLeftItemByName(this.mContext, "car_set15");
                remvoeSettingLeftItemByName(this.mContext, "car_set16");
                remvoeSettingLeftItemByName(this.mContext, "car_set17");
                remvoeSettingLeftItemByName(this.mContext, "car_set18");
                remvoeSettingLeftItemByName(this.mContext, "car_set");
                remvoeSettingLeftItemByName(this.mContext, "panorama_setting");
                break;
            case 26:
                removeMainPrjBtnByName(this.mContext, MainAction.TIRE_INFO);
                remvoeSettingLeftItemByName(this.mContext, "car_set1");
                remvoeSettingLeftItemByName(this.mContext, "car_set2");
                remvoeSettingLeftItemByName(this.mContext, "car_set3");
                remvoeSettingLeftItemByName(this.mContext, "car_set4");
                remvoeSettingLeftItemByName(this.mContext, "car_set5");
                remvoeSettingLeftItemByName(this.mContext, "car_set6");
                remvoeSettingLeftItemByName(this.mContext, "car_set7");
                remvoeSettingLeftItemByName(this.mContext, "car_set8");
                remvoeSettingLeftItemByName(this.mContext, "car_set9");
                remvoeSettingLeftItemByName(this.mContext, "car_set10");
                remvoeSettingLeftItemByName(this.mContext, "car_set11");
                remvoeSettingLeftItemByName(this.mContext, "car_set12");
                remvoeSettingLeftItemByName(this.mContext, "car_set13");
                remvoeSettingLeftItemByName(this.mContext, "car_set14");
                remvoeSettingLeftItemByName(this.mContext, "car_set16");
                remvoeSettingLeftItemByName(this.mContext, "car_set17");
                remvoeSettingLeftItemByName(this.mContext, "car_set18");
                remvoeSettingLeftItemByName(this.mContext, "car_set");
                remvoeSettingLeftItemByName(this.mContext, "panorama_setting");
                break;
            case 27:
                removeMainPrjBtnByName(this.mContext, MainAction.TIRE_INFO);
                remvoeSettingLeftItemByName(this.mContext, "car_set1");
                remvoeSettingLeftItemByName(this.mContext, "car_set2");
                remvoeSettingLeftItemByName(this.mContext, "car_set3");
                remvoeSettingLeftItemByName(this.mContext, "car_set4");
                remvoeSettingLeftItemByName(this.mContext, "car_set5");
                remvoeSettingLeftItemByName(this.mContext, "car_set6");
                remvoeSettingLeftItemByName(this.mContext, "car_set7");
                remvoeSettingLeftItemByName(this.mContext, "car_set8");
                remvoeSettingLeftItemByName(this.mContext, "car_set9");
                remvoeSettingLeftItemByName(this.mContext, "car_set10");
                remvoeSettingLeftItemByName(this.mContext, "car_set11");
                remvoeSettingLeftItemByName(this.mContext, "car_set12");
                remvoeSettingLeftItemByName(this.mContext, "car_set13");
                remvoeSettingLeftItemByName(this.mContext, "car_set14");
                remvoeSettingLeftItemByName(this.mContext, "car_set15");
                remvoeSettingLeftItemByName(this.mContext, "car_set17");
                remvoeSettingLeftItemByName(this.mContext, "car_set18");
                remvoeSettingLeftItemByName(this.mContext, "car_set");
                remvoeSettingLeftItemByName(this.mContext, "panorama_setting");
                break;
            case 28:
                removeMainPrjBtnByName(this.mContext, MainAction.TIRE_INFO);
                remvoeSettingLeftItemByName(this.mContext, "car_set1");
                remvoeSettingLeftItemByName(this.mContext, "car_set2");
                remvoeSettingLeftItemByName(this.mContext, "car_set3");
                remvoeSettingLeftItemByName(this.mContext, "car_set4");
                remvoeSettingLeftItemByName(this.mContext, "car_set5");
                remvoeSettingLeftItemByName(this.mContext, "car_set6");
                remvoeSettingLeftItemByName(this.mContext, "car_set7");
                remvoeSettingLeftItemByName(this.mContext, "car_set8");
                remvoeSettingLeftItemByName(this.mContext, "car_set9");
                remvoeSettingLeftItemByName(this.mContext, "car_set10");
                remvoeSettingLeftItemByName(this.mContext, "car_set11");
                remvoeSettingLeftItemByName(this.mContext, "car_set12");
                remvoeSettingLeftItemByName(this.mContext, "car_set13");
                remvoeSettingLeftItemByName(this.mContext, "car_set14");
                remvoeSettingLeftItemByName(this.mContext, "car_set15");
                remvoeSettingLeftItemByName(this.mContext, "car_set16");
                remvoeSettingLeftItemByName(this.mContext, "car_set18");
                remvoeSettingLeftItemByName(this.mContext, "car_set");
                remvoeSettingLeftItemByName(this.mContext, "panorama_setting");
                break;
            case 29:
                removeMainPrjBtnByName(this.mContext, MainAction.TIRE_INFO);
                remvoeSettingLeftItemByName(this.mContext, "car_set1");
                remvoeSettingLeftItemByName(this.mContext, "car_set2");
                remvoeSettingLeftItemByName(this.mContext, "car_set3");
                remvoeSettingLeftItemByName(this.mContext, "car_set4");
                remvoeSettingLeftItemByName(this.mContext, "car_set5");
                remvoeSettingLeftItemByName(this.mContext, "car_set6");
                remvoeSettingLeftItemByName(this.mContext, "car_set7");
                remvoeSettingLeftItemByName(this.mContext, "car_set8");
                remvoeSettingLeftItemByName(this.mContext, "car_set9");
                remvoeSettingLeftItemByName(this.mContext, "car_set10");
                remvoeSettingLeftItemByName(this.mContext, "car_set11");
                remvoeSettingLeftItemByName(this.mContext, "car_set12");
                remvoeSettingLeftItemByName(this.mContext, "car_set13");
                remvoeSettingLeftItemByName(this.mContext, "car_set14");
                remvoeSettingLeftItemByName(this.mContext, "car_set16");
                remvoeSettingLeftItemByName(this.mContext, "car_set17");
                remvoeSettingLeftItemByName(this.mContext, "car_set15");
                remvoeSettingLeftItemByName(this.mContext, "car_set");
                remvoeSettingLeftItemByName(this.mContext, "panorama_setting");
                break;
        }
    }

    public UiMgr(Context context) {
        this.mContext = context;
        carInfo();
        int currentCarId = getCurrentCarId();
        if (currentCarId == 4 || currentCarId == 7 || currentCarId == 15 || currentCarId == 20 || currentCarId == 22 || currentCarId == 23 || currentCarId == 24 || currentCarId == 25 || currentCarId == 27 || currentCarId == 28 || currentCarId == 29) {
            getParkPageUiSet(context).setShowRadar(true);
        }
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
        settingUiSet.setOnSettingPageStatusListener(this.mOnSettingPageStatusListener);
        settingUiSet.setOnSettingItemSeekbarSelectListener(this.mOnSettingItemSeekbarSelectListener);
        if (currentCarId == 4 || currentCarId == 6 || currentCarId == 7 || currentCarId == 9 || currentCarId == 10 || currentCarId == 11 || currentCarId == 12 || currentCarId == 13 || currentCarId == 15 || currentCarId == 16 || currentCarId == 17 || currentCarId == 18 || currentCarId == 19 || currentCarId == 21 || currentCarId == 22 || currentCarId == 23 || currentCarId == 24 || currentCarId == 25 || currentCarId == 27 || currentCarId == 28 || currentCarId == 29) {
            AirPageUiSet airUiSet = getAirUiSet(context);
            airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirTopBtnClickListener, this.mOnAirBottomLeftBtnClickListener, this.mOnAirBottomRightBtnClickListener, this.mOnAirBottomBtnClickListener});
            airUiSet.setOnAirPageStatusListener(this.mOnAirPageStatusListener);
            airUiSet.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.mFrontLeftSeatHeatColdListener, this.mFrontRightSeatHeatColdListener});
            airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.mSetWindSpeedViewOnClickListener);
            airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mTempSetViewOnUpDownClickListenerLeft, this.mTempSetViewOnUpDownClickListenerCenter, this.mTempSetViewOnUpDownClickListenerRight});
            if (getCurrentCarId() == 11 || getCurrentCarId() == 12 || getCurrentCarId() == 18) {
                airUiSet.getFrontArea().setShowPmValue(true);
            } else {
                airUiSet.getFrontArea().setShowPmValue(false);
            }
            airUiSet.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._189.UiMgr.1
                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
                public void onLeftSeatClick() {
                    UiMgr.this.airBtnClick(0, 6);
                }

                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
                public void onRightSeatClick() {
                    UiMgr.this.airBtnClick(0, 6);
                }
            });
        }
        getTireUiSet(context).setOnTirePageStatusListener(new OnTirePageStatusListener() { // from class: com.hzbhd.canbus.car._189.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnTirePageStatusListener
            public void onStatusChange(int i) {
                if (i == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -112, 96});
                }
            }
        });
    }

    private void carInfo() {
        int i = this.eachId;
        if (i == 1 || i == 5 || i == 6 || i == 21) {
            return;
        }
        if (i == 12) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 0});
        }
        if (this.eachId == 13) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 0});
        }
        if (this.eachId == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 1});
        }
        if (this.eachId == 3) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 1});
        }
        if (this.eachId == 9) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 3});
        }
        if (this.eachId == 7) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 4});
        }
        if (this.eachId == 11) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 5});
        }
        if (this.eachId == 4) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 6});
        }
        if (this.eachId == 10) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 7});
        }
        if (this.eachId == 14) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 8});
        }
        if (this.eachId == 15) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 9});
        }
        if (this.eachId == 16) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 10});
        }
        if (this.eachId == 17) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 11});
        }
        if (this.eachId == 18) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED});
        }
        if (this.eachId == 19) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED});
        }
        if (this.eachId == 22) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED});
        }
        if (this.eachId == 20) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 14});
        }
        if (this.eachId == 23) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 15});
        }
        if (this.eachId == 24) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 16});
        }
        if (this.eachId == 29) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 16});
        }
        if (this.eachId == 25) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 17});
        }
        if (this.eachId == 8) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 18});
        }
        if (this.eachId == 26) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 19});
        }
        if (this.eachId == 28) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 19});
        }
        if (this.eachId == 27) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 20});
        }
        if (this.eachId == 30) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 21});
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void airBtnClick(int i, int i2) {
        if (i == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -118, (byte) DataHandleUtils.setIntByteWithBit(0, i2, true), 0, 0, 0, 0, 0});
            CanbusMsgSender.sendMsg(new byte[]{22, -118, (byte) DataHandleUtils.setIntByteWithBit(0, i2, false), 0, 0, 0, 0, 0});
            return;
        }
        if (i == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 0, (byte) DataHandleUtils.setIntByteWithBit(0, i2, true), 0, 0, 0, 0});
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 0, (byte) DataHandleUtils.setIntByteWithBit(0, i2, false), 0, 0, 0, 0});
            return;
        }
        if (i == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 0, 0, (byte) DataHandleUtils.setIntByteWithBit(0, i2, true), 0, 0, 0});
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 0, 0, (byte) DataHandleUtils.setIntByteWithBit(0, i2, false), 0, 0, 0});
            return;
        }
        if (i == 3) {
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 0, 0, 0, (byte) DataHandleUtils.setIntByteWithBit(0, i2, true), 0, 0});
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 0, 0, 0, (byte) DataHandleUtils.setIntByteWithBit(0, i2, false), 0, 0});
        } else if (i == 4) {
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 0, 0, 0, 0, (byte) DataHandleUtils.setIntByteWithBit(0, i2, true), 0});
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 0, 0, 0, 0, (byte) DataHandleUtils.setIntByteWithBit(0, i2, false), 0});
        } else {
            if (i != 5) {
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 0, 0, 0, 0, 0, (byte) DataHandleUtils.setIntByteWithBit(0, i2, true)});
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 0, 0, 0, 0, 0, (byte) DataHandleUtils.setIntByteWithBit(0, i2, false)});
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
        if (this.msgMgr == null) {
            this.msgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.msgMgr;
    }

    private UiMgr getUiMgr(Context context) {
        if (this.uiMgr == null) {
            this.uiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.uiMgr;
    }
}
