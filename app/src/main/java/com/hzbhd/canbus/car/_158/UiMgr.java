package com.hzbhd.canbus.car._158;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AirBtnAction;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.OriginalBtnAction;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.constant.disc.MpegConstantsDef;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import kotlin.text.Typography;
import nfore.android.bt.res.NfDef;


public class UiMgr extends AbstractUiMgr {
    private static final String SHARE_157_AVERAGE_SPEED = "share_157_average_speed";
    private static final String SHARE_157_BSM_LINE = "share_157_bsm_line";
    private static final String SHARE_157_CTM_SYSTEM = "share_157_ctm_system";
    private static final String SHARE_157_DISPLAY = "share_157_display";
    private static final String SHARE_157_ENDURANCE_DISTANCE = "share_157_endurance_distance";
    static final String SHARE_157_FRONT_CAMERA_SWITCH = "share_157_front_camera_switch";
    static final String SHARE_157_FRONT_LINK_TURN_SIGNAL = "share_157_front_link_turn_signal";
    private static final String SHARE_157_LANGUAGE = "share_157_language";
    private static final String SHARE_157_NAVIGATION = "share_157_navigation";
    private static final String SHARE_157_PHONE = "share_157_phone";
    private static final String SHARE_157_SMS_EMAIL = "share_157_sms_email";
    private static final String SHARE_157_SOUND = "share_157_sound";
    private static final String SHARE_157_TURBINE = "share_157_turbine";
    private int mEachId;
    private MsgMgr mMsgMgr;
    private final String TAG = "_157_UiMgr";
    private final int MSG_SEND_0xE0_AIR_COMMAND_UP = 1;
    private Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.hzbhd.canbus.car._158.UiMgr.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -32, ((Byte) message.obj).byteValue(), 0});
            }
        }
    };
    private int mDifferentId = getCurrentCarId();

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
    }

    public UiMgr(final Context context) {
        Log.i("_157_UiMgr", "UiMgr: ");
        if (!getMsgMgr(context).isShowOriginalRadio()) {
            removeMainPrjBtnByName(context, MainAction.ORIGINAL_CAR_DEVICE);
        }
        initSetting(context);
        final SettingPageUiSet settingUiSet = getSettingUiSet(context);
        int i = this.mDifferentId;
        if (i == 35) {
            ArrayList arrayList = new ArrayList();
            arrayList.add("_55_0x68_data0_bit31_item1");
            arrayList.add("_55_0x68_data0_bit31_item2");
            arrayList.add("_55_0x68_data0_bit31_item3");
            arrayList.add("_55_0x68_data0_bit31_item4");
            settingUiSet.getList().get(5).getItemList().get(5).setValueSrnArray(arrayList);
        } else if (i == 36) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add("_55_0x68_data0_bit31_item1");
            arrayList2.add("_55_0x68_data1_bit54_item2");
            arrayList2.add("_55_0x68_data0_bit31_item3");
            arrayList2.add("_157_narrow");
            settingUiSet.getList().get(5).getItemList().get(5).setValueSrnArray(arrayList2);
        } else if (i == 64) {
            ArrayList arrayList3 = new ArrayList();
            arrayList3.add("_157_charge");
            arrayList3.add("_55_0x69_data1_bit65_item2");
            arrayList3.add("_55_0x69_data1_bit65_item3");
            settingUiSet.getList().get(0).getItemList().get(0).setValueSrnArray(arrayList3);
            settingUiSet.getList().get(0).getItemList().get(1).setValueSrnArray(arrayList3);
        }
        settingUiSet.setOnSettingItemClickListener(new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._158.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
            public void onClickItem(int i2, int i3) {
                Objects.requireNonNull(settingUiSet.getList().get(i2).getItemList().get(i3).getTitleSrn());
            }
        });
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._158.UiMgr.3
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public void onClickItem(int i2, int i3, int i4) {
                String titleSrn = settingUiSet.getList().get(i2).getItemList().get(i3).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "_157_saturation":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 69, (byte) (i4 + 5)});
                        break;
                    case "_157_brightness":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 67, (byte) (i4 + 5)});
                        break;
                    case "_157_contrast":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 68, (byte) (i4 + 5)});
                        break;
                    case "_55_0x69_data1_bit20":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, (byte) (i4 + 5)});
                        break;
                    case "compass_zoom":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -63, (byte) i4});
                        break;
                }
            }
        });
        settingUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._158.UiMgr.4
            @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
            public void onConfirmClick(int i2, int i3) {
                String titleSrn = settingUiSet.getList().get(i2).getItemList().get(i3).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "_157_delete_all_icons":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -10, 0});
                        break;
                    case "_41_default_all":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 15, 0});
                        break;
                    case "front_camera_switch":
                        SharePreUtil.setBoolValue(context, UiMgr.SHARE_157_FRONT_CAMERA_SWITCH, !SharePreUtil.getBoolValue(context, UiMgr.SHARE_157_FRONT_CAMERA_SWITCH, false));
                        break;
                    case "_55_0x6E_0x06":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 0});
                        break;
                    case "compass_run_calibration":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -64, 1});
                        break;
                    case "_157_tmps_correction":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 17, 0});
                        break;
                    case "_157_amplifier_reset":
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 10, 0});
                        break;
                    case "_157_panoramic_reset":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 48, 0});
                        break;
                }
            }
        });
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._158.UiMgr.5
            /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i2, int i3, int i4) {
                String titleSrn = settingUiSet.getList().get(i2).getItemList().get(i3).getTitleSrn();
                titleSrn.hashCode();
                char c = 65535;
                switch (titleSrn.hashCode()) {
                    case -2116235428:
                        if (titleSrn.equals("_55_0x65_data1_bit21")) {
                            c = 0;
                            break;
                        }
                        break;
                    case -2116235332:
                        if (titleSrn.equals("_55_0x65_data1_bit54")) {
                            c = 1;
                            break;
                        }
                        break;
                    case -2116235268:
                        if (titleSrn.equals("_55_0x65_data1_bit76")) {
                            c = 2;
                            break;
                        }
                        break;
                    case -2114831402:
                        if (titleSrn.equals("_157_rear_entertainment_system")) {
                            c = 3;
                            break;
                        }
                        break;
                    case -1904072502:
                        if (titleSrn.equals("_301_volume_and_speed_linkage")) {
                            c = 4;
                            break;
                        }
                        break;
                    case -1880694973:
                        if (titleSrn.equals("_157_phone")) {
                            c = 5;
                            break;
                        }
                        break;
                    case -1877710108:
                        if (titleSrn.equals("_157_sound")) {
                            c = 6;
                            break;
                        }
                        break;
                    case -1832488345:
                        if (titleSrn.equals("_55_0xE8_data5")) {
                            c = 7;
                            break;
                        }
                        break;
                    case -1546031052:
                        if (titleSrn.equals("_157_back_mirror_fold")) {
                            c = '\b';
                            break;
                        }
                        break;
                    case -1388813448:
                        if (titleSrn.equals("_157_endurance_distance")) {
                            c = '\t';
                            break;
                        }
                        break;
                    case -1194354345:
                        if (titleSrn.equals("_157_display")) {
                            c = '\n';
                            break;
                        }
                        break;
                    case -1018854949:
                        if (titleSrn.equals("_157_trunk_sense_switch")) {
                            c = 11;
                            break;
                        }
                        break;
                    case -722490656:
                        if (titleSrn.equals("_41_speed_distance_units")) {
                            c = '\f';
                            break;
                        }
                        break;
                    case -625706385:
                        if (titleSrn.equals("_157_auto_trunk")) {
                            c = '\r';
                            break;
                        }
                        break;
                    case -549818908:
                        if (titleSrn.equals("_41_tachometer_switch")) {
                            c = 14;
                            break;
                        }
                        break;
                    case -176819061:
                        if (titleSrn.equals("_157_sms_email")) {
                            c = 15;
                            break;
                        }
                        break;
                    case -81334456:
                        if (titleSrn.equals("_157_backlight_color")) {
                            c = 16;
                            break;
                        }
                        break;
                    case 102584022:
                        if (titleSrn.equals("language_setup")) {
                            c = 17;
                            break;
                        }
                        break;
                    case 117616127:
                        if (titleSrn.equals("_55_0x69_data0_bit10")) {
                            c = 18;
                            break;
                        }
                        break;
                    case 463009380:
                        if (titleSrn.equals("_157_turbine")) {
                            c = 19;
                            break;
                        }
                        break;
                    case 511896724:
                        if (titleSrn.equals("_157_revers_mode")) {
                            c = 20;
                            break;
                        }
                        break;
                    case 683897978:
                        if (titleSrn.equals("_157_average_speed")) {
                            c = 21;
                            break;
                        }
                        break;
                    case 704422172:
                        if (titleSrn.equals("_55_0x67_data0_bit20")) {
                            c = 22;
                            break;
                        }
                        break;
                    case 868863258:
                        if (titleSrn.equals("_41_key_remote_unlock")) {
                            c = 23;
                            break;
                        }
                        break;
                    case 1005119904:
                        if (titleSrn.equals("_55_0x69_data1_bit43")) {
                            c = 24;
                            break;
                        }
                        break;
                    case 1005119968:
                        if (titleSrn.equals("_55_0x69_data1_bit65")) {
                            c = 25;
                            break;
                        }
                        break;
                    case 1203911228:
                        if (titleSrn.equals("_157_memory_position")) {
                            c = 26;
                            break;
                        }
                        break;
                    case 1245017999:
                        if (titleSrn.equals("_41_tachometer")) {
                            c = 27;
                            break;
                        }
                        break;
                    case 1276069215:
                        if (titleSrn.equals("_157_navigation")) {
                            c = 28;
                            break;
                        }
                        break;
                    case 1285535413:
                        if (titleSrn.equals("_41_ctm_system")) {
                            c = 29;
                            break;
                        }
                        break;
                    case 1286553292:
                        if (titleSrn.equals("_194_unlock_mode")) {
                            c = 30;
                            break;
                        }
                        break;
                    case 1287717922:
                        if (titleSrn.equals("_157_turn_guide_signal")) {
                            c = 31;
                            break;
                        }
                        break;
                    case 1298522815:
                        if (titleSrn.equals("_55_0x68_data1_bit10")) {
                            c = ' ';
                            break;
                        }
                        break;
                    case 1298522943:
                        if (titleSrn.equals("_55_0x68_data1_bit54")) {
                            c = '!';
                            break;
                        }
                        break;
                    case 1298523007:
                        if (titleSrn.equals("_55_0x68_data1_bit76")) {
                            c = Typography.quote;
                            break;
                        }
                        break;
                    case 1300899316:
                        if (titleSrn.equals("_55_0x75_data1_bit0")) {
                            c = '#';
                            break;
                        }
                        break;
                    case 1300899317:
                        if (titleSrn.equals("_55_0x75_data1_bit1")) {
                            c = Typography.dollar;
                            break;
                        }
                        break;
                    case 1417574351:
                        if (titleSrn.equals("_55_0xE8_data0_bit10")) {
                            c = '%';
                            break;
                        }
                        break;
                    case 1417574415:
                        if (titleSrn.equals("_55_0xE8_data0_bit32")) {
                            c = Typography.amp;
                            break;
                        }
                        break;
                    case 1436065648:
                        if (titleSrn.equals("_301_dts_audio")) {
                            c = '\'';
                            break;
                        }
                        break;
                    case 1493848755:
                        if (titleSrn.equals("_55_0x64_data2_bit0")) {
                            c = '(';
                            break;
                        }
                        break;
                    case 1591925822:
                        if (titleSrn.equals("_55_0x67_data1_bit10")) {
                            c = ')';
                            break;
                        }
                        break;
                    case 1591925886:
                        if (titleSrn.equals("_55_0x67_data1_bit32")) {
                            c = '*';
                            break;
                        }
                        break;
                    case 1591925981:
                        if (titleSrn.equals("_55_0x67_data1_bit64")) {
                            c = '+';
                            break;
                        }
                        break;
                    case 1594302323:
                        if (titleSrn.equals("_55_0x65_data1_bit0")) {
                            c = ',';
                            break;
                        }
                        break;
                    case 1594302326:
                        if (titleSrn.equals("_55_0x65_data1_bit3")) {
                            c = '-';
                            break;
                        }
                        break;
                    case 1723385042:
                        if (titleSrn.equals("_55_0x66_data1_bit0")) {
                            c = '.';
                            break;
                        }
                        break;
                    case 1723385043:
                        if (titleSrn.equals("_55_0x66_data1_bit1")) {
                            c = '/';
                            break;
                        }
                        break;
                    case 1723385044:
                        if (titleSrn.equals("_55_0x66_data1_bit2")) {
                            c = '0';
                            break;
                        }
                        break;
                    case 1723385045:
                        if (titleSrn.equals("_55_0x66_data1_bit3")) {
                            c = '1';
                            break;
                        }
                        break;
                    case 1746333974:
                        if (titleSrn.equals("_157_bsm_reference_line")) {
                            c = '2';
                            break;
                        }
                        break;
                    case 1823838613:
                        if (titleSrn.equals("_55_0x67_data0_bit3")) {
                            c = '3';
                            break;
                        }
                        break;
                    case 1846843524:
                        if (titleSrn.equals("_55_0xE8_data0_bit4")) {
                            c = '4';
                            break;
                        }
                        break;
                    case 1889251442:
                        if (titleSrn.equals("_157_seat_auto_move")) {
                            c = '5';
                            break;
                        }
                        break;
                    case 1937336606:
                        if (titleSrn.equals("_157_backlight_mode")) {
                            c = '6';
                            break;
                        }
                        break;
                    case 1952921329:
                        if (titleSrn.equals("_55_0x68_data0_bit0")) {
                            c = '7';
                            break;
                        }
                        break;
                    case 1981550482:
                        if (titleSrn.equals("_55_0x68_data1_bit2")) {
                            c = '8';
                            break;
                        }
                        break;
                    case 1981550483:
                        if (titleSrn.equals("_55_0x68_data1_bit3")) {
                            c = '9';
                            break;
                        }
                        break;
                    case 2018618426:
                        if (titleSrn.equals("_55_0xE8_data6_bit0")) {
                            c = ':';
                            break;
                        }
                        break;
                    case 2018618427:
                        if (titleSrn.equals("_55_0xE8_data6_bit1")) {
                            c = ';';
                            break;
                        }
                        break;
                    case 2018618428:
                        if (titleSrn.equals("_55_0xE8_data6_bit2")) {
                            c = Typography.less;
                            break;
                        }
                        break;
                    case 2018618429:
                        if (titleSrn.equals("_55_0xE8_data6_bit3")) {
                            c = '=';
                            break;
                        }
                        break;
                    case 2018618430:
                        if (titleSrn.equals("_55_0xE8_data6_bit4")) {
                            c = Typography.greater;
                            break;
                        }
                        break;
                    case 2018618431:
                        if (titleSrn.equals("_55_0xE8_data6_bit5")) {
                            c = '?';
                            break;
                        }
                        break;
                    case 2082004050:
                        if (titleSrn.equals("_55_0x69_data0_bit2")) {
                            c = '@';
                            break;
                        }
                        break;
                    case 2082004051:
                        if (titleSrn.equals("_55_0x69_data0_bit3")) {
                            c = 'A';
                            break;
                        }
                        break;
                    case 2082004052:
                        if (titleSrn.equals("_55_0x69_data0_bit4")) {
                            c = 'B';
                            break;
                        }
                        break;
                    case 2082004054:
                        if (titleSrn.equals("_55_0x69_data0_bit6")) {
                            c = 'C';
                            break;
                        }
                        break;
                    case 2110633206:
                        if (titleSrn.equals("_55_0x69_data1_bit7")) {
                            c = 'D';
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 11, (byte) i4});
                        break;
                    case 1:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 7, (byte) i4});
                        break;
                    case 2:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 8, (byte) i4});
                        break;
                    case 3:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 49, (byte) i4});
                        break;
                    case 4:
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 7, (byte) i4});
                        break;
                    case 5:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -11, (byte) i4});
                        UiMgr.this.getMsgMgr(context).updateSettings(i2, i3, i4);
                        SharePreUtil.setIntValue(context, UiMgr.SHARE_157_PHONE, i4);
                        break;
                    case 6:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -12, (byte) i4});
                        UiMgr.this.getMsgMgr(context).updateSettings(i2, i3, i4);
                        SharePreUtil.setIntValue(context, UiMgr.SHARE_157_SOUND, i4);
                        break;
                    case 7:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 47, (byte) i4});
                        break;
                    case '\b':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 71, (byte) i4});
                        break;
                    case '\t':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -16, (byte) i4});
                        UiMgr.this.getMsgMgr(context).updateSettings(i2, i3, i4);
                        SharePreUtil.setIntValue(context, UiMgr.SHARE_157_ENDURANCE_DISTANCE, i4);
                        break;
                    case '\n':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -14, (byte) i4});
                        UiMgr.this.getMsgMgr(context).updateSettings(i2, i3, i4);
                        SharePreUtil.setIntValue(context, UiMgr.SHARE_157_DISPLAY, i4);
                        break;
                    case 11:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 70, (byte) i4});
                        break;
                    case '\f':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 21, (byte) i4});
                        break;
                    case '\r':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 51, (byte) i4});
                        break;
                    case 14:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 35, (byte) i4});
                        break;
                    case 15:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -9, (byte) i4});
                        UiMgr.this.getMsgMgr(context).updateSettings(i2, i3, i4);
                        SharePreUtil.setIntValue(context, UiMgr.SHARE_157_SMS_EMAIL, i4);
                        break;
                    case 16:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 65, (byte) i4});
                        break;
                    case 17:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 85, (byte) i4});
                        UiMgr.this.getMsgMgr(context).updateSettings(i2, i3, i4);
                        SharePreUtil.setIntValue(context, UiMgr.SHARE_157_LANGUAGE, i4);
                        break;
                    case 18:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 18, (byte) i4});
                        break;
                    case 19:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -13, (byte) i4});
                        UiMgr.this.getMsgMgr(context).updateSettings(i2, i3, i4);
                        SharePreUtil.setIntValue(context, UiMgr.SHARE_157_TURBINE, i4);
                        break;
                    case 20:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 57, (byte) i4});
                        break;
                    case 21:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -15, (byte) i4});
                        UiMgr.this.getMsgMgr(context).updateSettings(i2, i3, i4);
                        SharePreUtil.setIntValue(context, UiMgr.SHARE_157_AVERAGE_SPEED, i4);
                        break;
                    case 22:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 27, (byte) i4});
                        break;
                    case 23:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 9, (byte) i4});
                        break;
                    case 24:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, (byte) i4});
                        break;
                    case 25:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, (byte) i4});
                        break;
                    case 26:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 41, (byte) i4});
                        break;
                    case 27:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 22, (byte) i4});
                        break;
                    case 28:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -8, (byte) i4});
                        UiMgr.this.getMsgMgr(context).updateSettings(i2, i3, i4);
                        SharePreUtil.setIntValue(context, UiMgr.SHARE_157_NAVIGATION, i4);
                        break;
                    case 29:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 96, (byte) i4});
                        UiMgr.this.getMsgMgr(context).updateSettings(i2, i3, i4);
                        SharePreUtil.setIntValue(context, UiMgr.SHARE_157_CTM_SYSTEM, i4);
                        break;
                    case 30:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 25, (byte) i4});
                        break;
                    case 31:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 72, (byte) i4});
                        break;
                    case ' ':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 31, (byte) i4});
                        break;
                    case '!':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 34, (byte) i4});
                        break;
                    case '\"':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 36, (byte) i4});
                        break;
                    case '#':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 37, (byte) i4});
                        break;
                    case '$':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 38, (byte) i4});
                        break;
                    case '%':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 53, (byte) i4});
                        break;
                    case '&':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 54, (byte) i4});
                        break;
                    case '\'':
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 8, (byte) i4});
                        break;
                    case '(':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 42, (byte) i4});
                        break;
                    case ')':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, (byte) i4});
                        break;
                    case '*':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 5, (byte) i4});
                        break;
                    case '+':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, (byte) i4});
                        break;
                    case ',':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 10, (byte) i4});
                        break;
                    case '-':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 23, (byte) i4});
                        break;
                    case '.':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, (byte) i4});
                        break;
                    case '/':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 30, (byte) i4});
                        break;
                    case '0':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 26, (byte) i4});
                        break;
                    case '1':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) i4});
                        break;
                    case '2':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 112, (byte) i4});
                        UiMgr.this.getMsgMgr(context).updateSettings(i2, i3, i4);
                        SharePreUtil.setIntValue(context, UiMgr.SHARE_157_BSM_LINE, i4);
                        break;
                    case '3':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 28, (byte) i4});
                        break;
                    case '4':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 58, (byte) i4});
                        break;
                    case '5':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 52, (byte) i4});
                        break;
                    case '6':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 66, (byte) i4});
                        break;
                    case '7':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 40, (byte) i4});
                        break;
                    case '8':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 32, (byte) i4});
                        break;
                    case '9':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 33, (byte) i4});
                        break;
                    case ':':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 43, (byte) i4});
                        break;
                    case ';':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 44, (byte) i4});
                        break;
                    case '<':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 45, (byte) i4});
                        break;
                    case '=':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 46, (byte) i4});
                        break;
                    case '>':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 55, (byte) i4});
                        break;
                    case '?':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 56, (byte) i4});
                        break;
                    case '@':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 19, (byte) i4});
                        break;
                    case 'A':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 29, (byte) i4});
                        break;
                    case 'B':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 20, (byte) i4});
                        break;
                    case 'C':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 50, (byte) i4});
                        break;
                    case 'D':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 39, (byte) i4});
                        break;
                }
            }
        });
        settingUiSet.setOnSettingItemSwitchListener(new OnSettingItemSwitchListener() { // from class: com.hzbhd.canbus.car._158.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener
            public final void onSwitch(int i2, int i3, int i4) {
                this.f$0.m120lambda$new$0$comhzbhdcanbuscar_158UiMgr(settingUiSet, context, i2, i3, i4);
            }
        });
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        amplifierPageUiSet.setOnAmplifierPositionListener(new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._158.UiMgr$$ExternalSyntheticLambda9
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
            public final void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i2) {
                UiMgr.lambda$new$1(amplifierPosition, i2);
            }
        });
        amplifierPageUiSet.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._158.UiMgr$$ExternalSyntheticLambda10
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
            public final void progress(AmplifierActivity.AmplifierBand amplifierBand, int i2) {
                UiMgr.lambda$new$2(amplifierBand, i2);
            }
        });
        ParkPageUiSet parkPageUiSet = getParkPageUiSet(context);
        parkPageUiSet.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._158.UiMgr$$ExternalSyntheticLambda11
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public final void onClickItem(int i2) {
                UiMgr.lambda$new$3(i2);
            }
        });
        final int i2 = context.getResources().getDisplayMetrics().widthPixels;
        final int i3 = context.getResources().getDisplayMetrics().heightPixels;
        parkPageUiSet.setOnPanoramicItemTouchListener(new OnPanoramicItemTouchListener() { // from class: com.hzbhd.canbus.car._158.UiMgr$$ExternalSyntheticLambda12
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener
            public final void onTouchItem(MotionEvent motionEvent) {
                UiMgr.lambda$new$4(i2, i3, motionEvent);
            }
        });
        final OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(context);
        originalCarDevicePageUiSet.setOnOriginalTopBtnClickListeners(new OnOriginalTopBtnClickListener() { // from class: com.hzbhd.canbus.car._158.UiMgr.6
            @Override // com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener
            public void onClickTopBtnItem(int i4) {
                String str = originalCarDevicePageUiSet.getRowTopBtnAction()[i4];
                str.hashCode();
                if (str.equals(OriginalBtnAction.SCAN)) {
                    if (GeneralOriginalCarDeviceData.scan) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 10, 0});
                        return;
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 10, 1});
                        return;
                    }
                }
                if (str.equals(OriginalBtnAction.REFRESH)) {
                    if (GeneralOriginalCarDeviceData.refresh) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, 0});
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, 1});
                    }
                }
            }
        });
        originalCarDevicePageUiSet.setOnOriginalBottomBtnClickListeners(new OnOriginalBottomBtnClickListener() { // from class: com.hzbhd.canbus.car._158.UiMgr.7
            @Override // com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener
            public void onClickBottomBtnItem(int i4) {
                String str = originalCarDevicePageUiSet.getRowBottomBtnAction()[i4];
                str.hashCode();
                switch (str) {
                    case "prev_disc":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 5, 0});
                        break;
                    case "up":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 9, 0});
                        break;
                    case "band":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 1, 0});
                        break;
                    case "down":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 8, 0});
                        break;
                    case "left":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 3, 0});
                        break;
                    case "right":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 2, 0});
                        break;
                    case "next_disc":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 4, 0});
                        break;
                }
            }
        });
        originalCarDevicePageUiSet.setOnOriginalSongItemClickListener(new OnOriginalSongItemClickListener() { // from class: com.hzbhd.canbus.car._158.UiMgr.8
            @Override // com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener
            public void onSongItemClick(int i4) {
                int originalRadioPresetSize;
                int originalRadioPresetSize2;
                if (i4 == 0 || i4 == (originalRadioPresetSize2 = (originalRadioPresetSize = UiMgr.this.getMsgMgr(context).getOriginalRadioPresetSize()) + 1)) {
                    return;
                }
                if (i4 > originalRadioPresetSize2) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 11, (byte) (((i4 - originalRadioPresetSize) - 1) - 1)});
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 6, (byte) (i4 - 1)});
                }
            }

            @Override // com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener
            public void onSongItemLongClick(int i4) {
                if (i4 <= 0 || i4 > UiMgr.this.getMsgMgr(context).getOriginalRadioPresetSize()) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 7, (byte) i4});
            }
        });
        final AirPageUiSet airUiSet = getAirUiSet(context);
        if (context.getResources().getConfiguration().orientation == 1) {
            airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._158.UiMgr$$ExternalSyntheticLambda13
                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
                public final void onClickItem(int i4) {
                    this.f$0.m125lambda$new$5$comhzbhdcanbuscar_158UiMgr(airUiSet, i4);
                }
            }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._158.UiMgr$$ExternalSyntheticLambda1
                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
                public final void onClickItem(int i4) {
                    this.f$0.m126lambda$new$6$comhzbhdcanbuscar_158UiMgr(airUiSet, i4);
                }
            }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._158.UiMgr$$ExternalSyntheticLambda2
                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
                public final void onClickItem(int i4) {
                    this.f$0.m127lambda$new$7$comhzbhdcanbuscar_158UiMgr(airUiSet, i4);
                }
            }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._158.UiMgr$$ExternalSyntheticLambda3
                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
                public final void onClickItem(int i4) {
                    this.f$0.m128lambda$new$8$comhzbhdcanbuscar_158UiMgr(airUiSet, i4);
                }
            }});
            airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._158.UiMgr.9
                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
                public void onClickUp() {
                    UiMgr.this.send0xE0AirCommand(3);
                }

                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
                public void onClickDown() {
                    UiMgr.this.send0xE0AirCommand(2);
                }
            }, null, new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._158.UiMgr.10
                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
                public void onClickUp() {
                    UiMgr.this.send0xE0AirCommand(5);
                }

                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
                public void onClickDown() {
                    UiMgr.this.send0xE0AirCommand(4);
                }
            }});
            airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._158.UiMgr.11
                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
                public void onClickLeft() {
                    UiMgr.this.send0xE0AirCommand(9);
                }

                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
                public void onClickRight() {
                    UiMgr.this.send0xE0AirCommand(10);
                }
            });
            airUiSet.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._158.UiMgr$$ExternalSyntheticLambda4
                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
                public final void onClickItem(int i4) {
                    this.f$0.m129lambda$new$9$comhzbhdcanbuscar_158UiMgr(airUiSet, i4);
                }
            }});
            if (this.mDifferentId == 33) {
                removeFrontAirButtonByName(context, AirBtnAction.BLOW_HEAD);
                removeFrontAirButtonByName(context, AirBtnAction.BLOW_FOOT);
                removeFrontAirButtonByName(context, AirBtnAction.BLOW_WINDOW_FOOT);
                removeFrontAirButtonByName(context, AirBtnAction.BLOW_HEAD_FOOT);
                airUiSet.getFrontArea().setCanSetSeatHeat(true);
                airUiSet.getFrontArea().setCanSetSeatCold(true);
            } else {
                removeFrontAirButtonByName(context, "blow_positive");
                removeFrontAirButtonByName(context, AirBtnAction.BLOW_NEGATIVE);
            }
        } else {
            airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._158.UiMgr$$ExternalSyntheticLambda5
                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
                public final void onClickItem(int i4) {
                    this.f$0.m121lambda$new$10$comhzbhdcanbuscar_158UiMgr(airUiSet, i4);
                }
            }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._158.UiMgr$$ExternalSyntheticLambda6
                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
                public final void onClickItem(int i4) {
                    this.f$0.m122lambda$new$11$comhzbhdcanbuscar_158UiMgr(airUiSet, i4);
                }
            }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._158.UiMgr$$ExternalSyntheticLambda7
                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
                public final void onClickItem(int i4) {
                    this.f$0.m123lambda$new$12$comhzbhdcanbuscar_158UiMgr(airUiSet, i4);
                }
            }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._158.UiMgr$$ExternalSyntheticLambda8
                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
                public final void onClickItem(int i4) {
                    this.f$0.m124lambda$new$13$comhzbhdcanbuscar_158UiMgr(airUiSet, i4);
                }
            }});
            airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._158.UiMgr.12
                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
                public void onClickLeft() {
                    if (GeneralAirData.front_wind_level != 1) {
                        UiMgr.this.send0xC6AirCommand(173, GeneralAirData.front_wind_level - 1);
                    }
                }

                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
                public void onClickRight() {
                    if (GeneralAirData.front_wind_level != airUiSet.getFrontArea().getWindMaxLevel()) {
                        UiMgr.this.send0xC6AirCommand(173, GeneralAirData.front_wind_level + 1);
                    }
                }
            });
            airUiSet.getFrontArea().setCanSetLeftTemp(false);
            airUiSet.getFrontArea().setCanSetRightTemp(false);
            airUiSet.getFrontArea().setDisableBtnArray(new String[]{"power", "in_out_cycle", "sync", "auto", "front_defog", "rear_defog", "dual", AirBtnAction.REAR});
            airUiSet.getRearArea().setAllBtnCanClick(false);
            removeFrontAirButtonByName(context, "blow_positive");
            removeFrontAirButtonByName(context, AirBtnAction.BLOW_NEGATIVE);
        }
        if (this.mDifferentId == 33) {
            airUiSet.getFrontArea().setShowSeatHeat(true);
            airUiSet.getFrontArea().setShowSeatCold(true);
            airUiSet.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._158.UiMgr.13
                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
                public void onClickMin() {
                }

                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
                public void onClickPlus() {
                    UiMgr.this.send0xE0AirCommand(11);
                }
            }, new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._158.UiMgr.14
                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
                public void onClickMin() {
                }

                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
                public void onClickPlus() {
                    UiMgr.this.send0xE0AirCommand(13);
                }
            }, new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._158.UiMgr.15
                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
                public void onClickMin() {
                }

                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
                public void onClickPlus() {
                    UiMgr.this.send0xE0AirCommand(12);
                }
            }, new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._158.UiMgr.16
                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
                public void onClickMin() {
                }

                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
                public void onClickPlus() {
                    UiMgr.this.send0xE0AirCommand(14);
                }
            }});
        }
    }

    /* renamed from: lambda$new$0$com-hzbhd-canbus-car-_158-UiMgr, reason: not valid java name */
    /* synthetic */ void m120lambda$new$0$comhzbhdcanbuscar_158UiMgr(SettingPageUiSet settingPageUiSet, Context context, int i, int i2, int i3) {
        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        titleSrn.hashCode();
        if (titleSrn.equals("_157_front_link_turn")) {
            SharePreUtil.setIntValue(context, SHARE_157_FRONT_LINK_TURN_SIGNAL, i3);
            getMsgMgr(context).updateSettings(13, 2, i3);
        }
    }

    /* renamed from: com.hzbhd.canbus.car._158.UiMgr$17, reason: invalid class name */
    static /* synthetic */ class AnonymousClass17 {
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand;
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition;

        static {
            int[] iArr = new int[AmplifierActivity.AmplifierPosition.values().length];
            $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition = iArr;
            try {
                iArr[AmplifierActivity.AmplifierPosition.FRONT_REAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[AmplifierActivity.AmplifierPosition.LEFT_RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[AmplifierActivity.AmplifierBand.values().length];
            $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand = iArr2;
            try {
                iArr2[AmplifierActivity.AmplifierBand.TREBLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.MIDDLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.BASS.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.MEGA_BASS.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.VOLUME.ordinal()] = 5;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    static /* synthetic */ void lambda$new$1(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
        int i2 = AnonymousClass17.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[amplifierPosition.ordinal()];
        if (i2 == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, (byte) (i + 9)});
        } else {
            if (i2 != 2) {
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 2, (byte) (i + 9)});
        }
    }

    static /* synthetic */ void lambda$new$2(AmplifierActivity.AmplifierBand amplifierBand, int i) {
        int i2 = AnonymousClass17.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[amplifierBand.ordinal()];
        if (i2 == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, (byte) i});
            return;
        }
        if (i2 == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 4, (byte) i});
            return;
        }
        if (i2 == 3) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 5, (byte) i});
        } else if (i2 == 4) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 6, (byte) i});
        } else {
            if (i2 != 5) {
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 9, (byte) i});
        }
    }

    static /* synthetic */ void lambda$new$3(int i) {
        if (i == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 64, 0});
            return;
        }
        if (i == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 64, 1});
        } else if (i == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 64, 2});
        } else {
            if (i != 3) {
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 64, 3});
        }
    }

    static /* synthetic */ void lambda$new$4(int i, int i2, MotionEvent motionEvent) {
        int i3;
        if (motionEvent.getAction() == 0) {
            i3 = 1;
        } else if (motionEvent.getAction() != 1) {
            return;
        } else {
            i3 = 0;
        }
        int x = (((int) motionEvent.getX()) * 1023) / i;
        int y = (((int) motionEvent.getY()) * 1000) / i2;
        CanbusMsgSender.sendMsg(new byte[]{22, -104, (byte) i3, (byte) ((x >> 8) & 255), (byte) (x & 255), (byte) ((y >> 8) & 255), (byte) (y & 255), 0});
    }

    /* renamed from: lambda$new$5$com-hzbhd-canbus-car-_158-UiMgr, reason: not valid java name */
    /* synthetic */ void m125lambda$new$5$comhzbhdcanbuscar_158UiMgr(AirPageUiSet airPageUiSet, int i) {
        sendPortAirCommand(airPageUiSet.getFrontArea().getLineBtnAction()[0][i]);
    }

    /* renamed from: lambda$new$6$com-hzbhd-canbus-car-_158-UiMgr, reason: not valid java name */
    /* synthetic */ void m126lambda$new$6$comhzbhdcanbuscar_158UiMgr(AirPageUiSet airPageUiSet, int i) {
        sendPortAirCommand(airPageUiSet.getFrontArea().getLineBtnAction()[1][i]);
    }

    /* renamed from: lambda$new$7$com-hzbhd-canbus-car-_158-UiMgr, reason: not valid java name */
    /* synthetic */ void m127lambda$new$7$comhzbhdcanbuscar_158UiMgr(AirPageUiSet airPageUiSet, int i) {
        sendPortAirCommand(airPageUiSet.getFrontArea().getLineBtnAction()[2][i]);
    }

    /* renamed from: lambda$new$8$com-hzbhd-canbus-car-_158-UiMgr, reason: not valid java name */
    /* synthetic */ void m128lambda$new$8$comhzbhdcanbuscar_158UiMgr(AirPageUiSet airPageUiSet, int i) {
        sendPortAirCommand(airPageUiSet.getFrontArea().getLineBtnAction()[3][i]);
    }

    /* renamed from: lambda$new$9$com-hzbhd-canbus-car-_158-UiMgr, reason: not valid java name */
    /* synthetic */ void m129lambda$new$9$comhzbhdcanbuscar_158UiMgr(AirPageUiSet airPageUiSet, int i) {
        sendPortAirCommand(airPageUiSet.getRearArea().getLineBtnAction()[3][i]);
    }

    /* renamed from: lambda$new$10$com-hzbhd-canbus-car-_158-UiMgr, reason: not valid java name */
    /* synthetic */ void m121lambda$new$10$comhzbhdcanbuscar_158UiMgr(AirPageUiSet airPageUiSet, int i) {
        sendAirCommand(airPageUiSet.getFrontArea().getLineBtnAction()[0][i]);
    }

    /* renamed from: lambda$new$11$com-hzbhd-canbus-car-_158-UiMgr, reason: not valid java name */
    /* synthetic */ void m122lambda$new$11$comhzbhdcanbuscar_158UiMgr(AirPageUiSet airPageUiSet, int i) {
        sendAirCommand(airPageUiSet.getFrontArea().getLineBtnAction()[1][i]);
    }

    /* renamed from: lambda$new$12$com-hzbhd-canbus-car-_158-UiMgr, reason: not valid java name */
    /* synthetic */ void m123lambda$new$12$comhzbhdcanbuscar_158UiMgr(AirPageUiSet airPageUiSet, int i) {
        sendAirCommand(airPageUiSet.getFrontArea().getLineBtnAction()[2][i]);
    }

    /* renamed from: lambda$new$13$com-hzbhd-canbus-car-_158-UiMgr, reason: not valid java name */
    /* synthetic */ void m124lambda$new$13$comhzbhdcanbuscar_158UiMgr(AirPageUiSet airPageUiSet, int i) {
        sendAirCommand(airPageUiSet.getFrontArea().getLineBtnAction()[3][i]);
    }

    private void sendAirCommand(String str) {
        str.hashCode();
        switch (str) {
            case "ac":
                if (GeneralAirData.ac) {
                    send0xC6AirCommand(MpegConstantsDef.GotoTimeCFM, 2);
                    break;
                } else {
                    send0xC6AirCommand(MpegConstantsDef.GotoTimeCFM, 1);
                    break;
                }
            case "blow_head_foot":
                send0xC6AirCommand(MpegConstantsDef.GotoTimeCFM, 4);
                break;
            case "blow_foot":
                send0xC6AirCommand(MpegConstantsDef.GotoTimeCFM, 5);
                break;
            case "blow_head":
                send0xC6AirCommand(MpegConstantsDef.GotoTimeCFM, 3);
                break;
            case "blow_window_foot":
                send0xC6AirCommand(MpegConstantsDef.GotoTimeCFM, 6);
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void send0xC6AirCommand(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -58, (byte) i, (byte) i2});
    }

    private void sendPortAirCommand(String str) {
        str.hashCode();
        switch (str) {
            case "front_defog":
                send0xE0AirCommand(19);
                break;
            case "rear_defog":
                send0xE0AirCommand(20);
                break;
            case "rear_power":
                send0xE0AirCommand(42);
                break;
            case "blow_positive":
                send0xE0AirCommand(36);
                break;
            case "blow_negative":
                send0xE0AirCommand(37);
                break;
            case "ac":
                send0xE0AirCommand(23);
                break;
            case "auto":
                send0xE0AirCommand(21);
                break;
            case "dual":
                send0xE0AirCommand(16);
                break;
            case "power":
                send0xE0AirCommand(1);
                break;
            case "in_out_cycle":
                send0xE0AirCommand(25);
                break;
            case "blow_head_foot":
                send0xE0AirCommand(33);
                break;
            case "blow_foot":
                send0xE0AirCommand(8);
                break;
            case "blow_head":
                send0xE0AirCommand(7);
                break;
            case "blow_window_foot":
                send0xE0AirCommand(32);
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void send0xE0AirCommand(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -32, (byte) i, 1});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }

    private void initSetting(Context context) {
        getMsgMgr(context).updateSettings(8, 1, SharePreUtil.getIntValue(context, SHARE_157_CTM_SYSTEM, 0));
        getMsgMgr(context).updateSettings(8, 2, SharePreUtil.getIntValue(context, SHARE_157_LANGUAGE, 0));
        getMsgMgr(context).updateSettings(11, 0, SharePreUtil.getIntValue(context, SHARE_157_ENDURANCE_DISTANCE, 0));
        getMsgMgr(context).updateSettings(11, 1, SharePreUtil.getIntValue(context, SHARE_157_AVERAGE_SPEED, 0));
        getMsgMgr(context).updateSettings(11, 2, SharePreUtil.getIntValue(context, SHARE_157_DISPLAY, 0));
        getMsgMgr(context).updateSettings(11, 3, SharePreUtil.getIntValue(context, SHARE_157_TURBINE, 0));
        getMsgMgr(context).updateSettings(11, 4, SharePreUtil.getIntValue(context, SHARE_157_SOUND, 0));
        getMsgMgr(context).updateSettings(11, 5, SharePreUtil.getIntValue(context, SHARE_157_PHONE, 0));
        getMsgMgr(context).updateSettings(11, 6, SharePreUtil.getIntValue(context, SHARE_157_SMS_EMAIL, 0));
        getMsgMgr(context).updateSettings(11, 7, SharePreUtil.getIntValue(context, SHARE_157_NAVIGATION, 0));
        getMsgMgr(context).updateSettings(13, 0, SharePreUtil.getIntValue(context, SHARE_157_BSM_LINE, 0));
        getMsgMgr(context).updateSettings(13, 2, SharePreUtil.getIntValue(context, SHARE_157_FRONT_LINK_TURN_SIGNAL, 1));
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
}
