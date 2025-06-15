package com.hzbhd.canbus.car._301;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierResetPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AirBtnAction;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TimerUtil;
import java.util.Objects;
import java.util.TimerTask;
import kotlin.text.Typography;
import nfore.android.bt.res.NfDef;


public class UiMgr extends AbstractUiMgr {
    protected static final String CAN_269_SAVE_AMP_BASS = "__269_SAVE_AMP_BASS";
    protected static final String CAN_269_SAVE_AMP_FR = "__269_SAVE_AMP_FR";
    protected static final String CAN_269_SAVE_AMP_LR = "__269_SAVE_AMP_LR";
    protected static final String CAN_269_SAVE_AMP_MID = "__269_SAVE_AMP_MID";
    protected static final String CAN_269_SAVE_AMP_TRE = "__269_SAVE_AMP_TRE";
    protected static final String CAN_269_SAVE_AMP_VOL = "__269_SAVE_AMP_VOL";
    protected static final String CAN_269_SAVE_AMP__HEAVY_BASS = "__269_SAVE_AMP_HEAVY_BASS";
    protected static final String CAN_269_SAVE_IS_AMP_FIRST = "CAN_269_SAVE_IS_AMP_FIRST";
    private static final String SHARE_41_CTM_SYSTEM = "share_41_ctm_system";
    static final String SHARE_41_FRONT_CAMERA_SWITCH = "share_41_front_camera_switch";
    private static final String SHARE_41_LANGUAGE = "share_41_language";
    private Context mContext;
    private MsgMgr mMsgMgr;
    private TimerUtil mRequestTimer;
    private final MsgMgr msgMgr;
    private boolean mIsClickReset = false;
    private byte AMP_VOL_OFFSET = 0;
    private byte AMP_BAL_OFFSET = 0;
    private byte AMP_EQ_OFFSET = 0;
    private OnAirBtnClickListener mOnAirBtnClickListenerLeft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._301.UiMgr.18
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            byte[] bArr = new byte[4];
            bArr[0] = 22;
            bArr[1] = -58;
            bArr[2] = -84;
            bArr[3] = (byte) (GeneralAirData.ac ? 2 : 1);
            CanbusMsgSender.sendMsg(bArr);
        }
    };
    private OnAirBtnClickListener mOnAirBtnClickListenerBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._301.UiMgr.19
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirKeyMsg(7);
                return;
            }
            if (i == 1) {
                UiMgr.this.sendAirKeyMsg(33);
                return;
            }
            if (i == 2) {
                UiMgr.this.sendAirKeyMsg(8);
                return;
            }
            if (i == 3) {
                UiMgr.this.sendAirKeyMsg(32);
            } else if (i == 4) {
                UiMgr.this.sendAirKeyMsg(36);
            } else {
                if (i != 5) {
                    return;
                }
                UiMgr.this.sendAirKeyMsg(37);
            }
        }
    };
    OnAirTemperatureUpDownClickListener onUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._301.UiMgr.21
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirKeyMsg(3);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirKeyMsg(2);
        }
    };
    OnAirTemperatureUpDownClickListener onUpDownClickListenerCenter = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._301.UiMgr.22
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
        }
    };
    OnAirTemperatureUpDownClickListener onUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._301.UiMgr.23
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirKeyMsg(5);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirKeyMsg(4);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerRearBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._301.UiMgr.24
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
        }
    };
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private int mDifferentId = getCurrentCarId();

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        if (this.mDifferentId == 2) {
            removeFrontAirButtonByName(context, AirBtnAction.BLOW_HEAD);
            removeFrontAirButtonByName(context, AirBtnAction.BLOW_HEAD_FOOT);
            removeFrontAirButtonByName(context, AirBtnAction.BLOW_FOOT);
            removeFrontAirButtonByName(context, AirBtnAction.BLOW_WINDOW_FOOT);
        } else {
            removeFrontAirButtonByName(context, "blow_positive");
            removeFrontAirButtonByName(context, AirBtnAction.BLOW_NEGATIVE);
        }
        requestCommand();
    }

    public UiMgr(final Context context) {
        this.mContext = context;
        this.msgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        initSetting(context);
        final SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._301.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public void onClickItem(int i, int i2, int i3) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                if (titleSrn.equals("_55_0x69_data1_bit20")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, (byte) (i3 + 5)});
                } else if (titleSrn.equals("compass_zoom")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, -63, (byte) i3});
                }
            }
        });
        settingUiSet.setOnSettingItemClickListener(new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._301.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
            public void onClickItem(int i, int i2) {
                Objects.requireNonNull(settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn());
            }
        });
        settingUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._301.UiMgr.3
            @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
            public void onConfirmClick(int i, int i2) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "_41_default_all":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 15, 0});
                        break;
                    case "front_camera_switch":
                        SharePreUtil.setBoolValue(context, UiMgr.SHARE_41_FRONT_CAMERA_SWITCH, !SharePreUtil.getBoolValue(context, UiMgr.SHARE_41_FRONT_CAMERA_SWITCH, false));
                        break;
                    case "_55_0x6E_0x06":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 0});
                        break;
                    case "compass_run_calibration":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -64, 1});
                        break;
                    case "_41_delete_fuel_record":
                        CanbusMsgSender.sendMsg(new byte[]{22, -112, 51, 3});
                        break;
                }
            }
        });
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._301.UiMgr.4
            /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                char c;
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                char c2 = 65535;
                switch (titleSrn.hashCode()) {
                    case -2116235428:
                        if (titleSrn.equals("_55_0x65_data1_bit21")) {
                            c2 = 0;
                            break;
                        }
                        break;
                    case -2116235332:
                        if (titleSrn.equals("_55_0x65_data1_bit54")) {
                            c2 = 1;
                            break;
                        }
                        break;
                    case -2116235268:
                        if (titleSrn.equals("_55_0x65_data1_bit76")) {
                            c2 = 2;
                            break;
                        }
                        break;
                    case -1904072502:
                        if (titleSrn.equals("_301_volume_and_speed_linkage")) {
                            c2 = 3;
                            break;
                        }
                        break;
                    case -1400265020:
                        if (titleSrn.equals("_163_setting_16")) {
                            c2 = 4;
                            break;
                        }
                        break;
                    case -722490656:
                        if (titleSrn.equals("_41_speed_distance_units")) {
                            c2 = 5;
                            break;
                        }
                        break;
                    case -549818908:
                        if (titleSrn.equals("_41_tachometer_switch")) {
                            c2 = 6;
                            break;
                        }
                        break;
                    case 102584022:
                        if (titleSrn.equals("language_setup")) {
                            c2 = 7;
                            break;
                        }
                        break;
                    case 117616127:
                        if (titleSrn.equals("_55_0x69_data0_bit10")) {
                            c2 = '\b';
                            break;
                        }
                        break;
                    case 624754683:
                        if (titleSrn.equals("_301_reverse_gear_tone")) {
                            c2 = '\t';
                            break;
                        }
                        break;
                    case 704422172:
                        if (titleSrn.equals("_55_0x67_data0_bit20")) {
                            c2 = '\n';
                            break;
                        }
                        break;
                    case 775437606:
                        if (titleSrn.equals("_301_personalized_driving_position")) {
                            c2 = 11;
                            break;
                        }
                        break;
                    case 868863258:
                        if (titleSrn.equals("_41_key_remote_unlock")) {
                            c = '\f';
                            c2 = c;
                            break;
                        }
                        break;
                    case 1005119904:
                        if (titleSrn.equals("_55_0x69_data1_bit43")) {
                            c2 = '\r';
                            break;
                        }
                        break;
                    case 1005119968:
                        if (titleSrn.equals("_55_0x69_data1_bit65")) {
                            c = 14;
                            c2 = c;
                            break;
                        }
                        break;
                    case 1245017999:
                        if (titleSrn.equals("_41_tachometer")) {
                            c = 15;
                            c2 = c;
                            break;
                        }
                        break;
                    case 1285535413:
                        if (titleSrn.equals("_41_ctm_system")) {
                            c = 16;
                            c2 = c;
                            break;
                        }
                        break;
                    case 1286553292:
                        if (titleSrn.equals("_194_unlock_mode")) {
                            c = 17;
                            c2 = c;
                            break;
                        }
                        break;
                    case 1298522815:
                        if (titleSrn.equals("_55_0x68_data1_bit10")) {
                            c2 = 18;
                            break;
                        }
                        break;
                    case 1298522943:
                        if (titleSrn.equals("_55_0x68_data1_bit54")) {
                            c2 = 19;
                            break;
                        }
                        break;
                    case 1298523007:
                        if (titleSrn.equals("_55_0x68_data1_bit76")) {
                            c2 = 20;
                            break;
                        }
                        break;
                    case 1300899316:
                        if (titleSrn.equals("_55_0x75_data1_bit0")) {
                            c2 = 21;
                            break;
                        }
                        break;
                    case 1300899317:
                        if (titleSrn.equals("_55_0x75_data1_bit1")) {
                            c2 = 22;
                            break;
                        }
                        break;
                    case 1436065648:
                        if (titleSrn.equals("_301_dts_audio")) {
                            c2 = 23;
                            break;
                        }
                        break;
                    case 1456922374:
                        if (titleSrn.equals("_301_four_wheel_drive_awd")) {
                            c2 = 24;
                            break;
                        }
                        break;
                    case 1518102037:
                        if (titleSrn.equals("_301_seat_position_movement")) {
                            c2 = 25;
                            break;
                        }
                        break;
                    case 1591925822:
                        if (titleSrn.equals("_55_0x67_data1_bit10")) {
                            c = 26;
                            c2 = c;
                            break;
                        }
                        break;
                    case 1591925886:
                        if (titleSrn.equals("_55_0x67_data1_bit32")) {
                            c = 27;
                            c2 = c;
                            break;
                        }
                        break;
                    case 1591925981:
                        if (titleSrn.equals("_55_0x67_data1_bit64")) {
                            c = 28;
                            c2 = c;
                            break;
                        }
                        break;
                    case 1594302323:
                        if (titleSrn.equals("_55_0x65_data1_bit0")) {
                            c = 29;
                            c2 = c;
                            break;
                        }
                        break;
                    case 1594302326:
                        if (titleSrn.equals("_55_0x65_data1_bit3")) {
                            c = 30;
                            c2 = c;
                            break;
                        }
                        break;
                    case 1723385042:
                        if (titleSrn.equals("_55_0x66_data1_bit0")) {
                            c = 31;
                            c2 = c;
                            break;
                        }
                        break;
                    case 1723385043:
                        if (titleSrn.equals("_55_0x66_data1_bit1")) {
                            c = ' ';
                            c2 = c;
                            break;
                        }
                        break;
                    case 1723385044:
                        if (titleSrn.equals("_55_0x66_data1_bit2")) {
                            c = '!';
                            c2 = c;
                            break;
                        }
                        break;
                    case 1723385045:
                        if (titleSrn.equals("_55_0x66_data1_bit3")) {
                            c = Typography.quote;
                            c2 = c;
                            break;
                        }
                        break;
                    case 1823838613:
                        if (titleSrn.equals("_55_0x67_data0_bit3")) {
                            c = '#';
                            c2 = c;
                            break;
                        }
                        break;
                    case 1981550482:
                        if (titleSrn.equals("_55_0x68_data1_bit2")) {
                            c = Typography.dollar;
                            c2 = c;
                            break;
                        }
                        break;
                    case 1981550483:
                        if (titleSrn.equals("_55_0x68_data1_bit3")) {
                            c = '%';
                            c2 = c;
                            break;
                        }
                        break;
                    case 2082004050:
                        if (titleSrn.equals("_55_0x69_data0_bit2")) {
                            c = Typography.amp;
                            c2 = c;
                            break;
                        }
                        break;
                    case 2082004051:
                        if (titleSrn.equals("_55_0x69_data0_bit3")) {
                            c = '\'';
                            c2 = c;
                            break;
                        }
                        break;
                    case 2082004052:
                        if (titleSrn.equals("_55_0x69_data0_bit4")) {
                            c = '(';
                            c2 = c;
                            break;
                        }
                        break;
                }
                switch (c2) {
                    case 0:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 11, (byte) i3});
                        break;
                    case 1:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 7, (byte) i3});
                        break;
                    case 2:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 8, (byte) i3});
                        break;
                    case 3:
                        UiMgr.this.sendAmpKeyMsg(7, i3);
                        break;
                    case 4:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 42, (byte) i3});
                        break;
                    case 5:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 21, (byte) i3});
                        break;
                    case 6:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 35, (byte) i3});
                        break;
                    case 7:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 85, (byte) i3});
                        UiMgr.this.getMsgMgr(context).updateSettings(i, i2, i3);
                        SharePreUtil.setIntValue(context, UiMgr.SHARE_41_LANGUAGE, i3);
                        break;
                    case '\b':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 18, (byte) i3});
                        break;
                    case '\t':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 36, (byte) i3});
                        break;
                    case '\n':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 27, (byte) i3});
                        break;
                    case 11:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 39, (byte) i3});
                        break;
                    case '\f':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 9, (byte) i3});
                        break;
                    case '\r':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, (byte) i3});
                        break;
                    case 14:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, (byte) i3});
                        break;
                    case 15:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 22, (byte) i3});
                        break;
                    case 16:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 96, (byte) i3});
                        UiMgr.this.getMsgMgr(context).updateSettings(i, i2, i3);
                        SharePreUtil.setIntValue(context, UiMgr.SHARE_41_CTM_SYSTEM, i3);
                        break;
                    case 17:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 25, (byte) i3});
                        break;
                    case 18:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 31, (byte) i3});
                        break;
                    case 19:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 34, (byte) i3});
                        break;
                    case 20:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 41, (byte) i3});
                        break;
                    case 21:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 37, (byte) i3});
                        break;
                    case 22:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 38, (byte) i3});
                        break;
                    case 23:
                        UiMgr.this.sendAmpKeyMsg(8, i3);
                        break;
                    case 24:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 43, (byte) i3});
                        break;
                    case 25:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 40, (byte) i3});
                        break;
                    case 26:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, (byte) i3});
                        break;
                    case 27:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 5, (byte) i3});
                        break;
                    case 28:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, (byte) i3});
                        break;
                    case 29:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 10, (byte) i3});
                        break;
                    case 30:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 23, (byte) i3});
                        break;
                    case 31:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, (byte) i3});
                        break;
                    case ' ':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 30, (byte) i3});
                        break;
                    case '!':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 26, (byte) i3});
                        break;
                    case '\"':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) i3});
                        break;
                    case '#':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 28, (byte) i3});
                        break;
                    case '$':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 32, (byte) i3});
                        break;
                    case '%':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 33, (byte) i3});
                        break;
                    case '&':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 19, (byte) i3});
                        break;
                    case '\'':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 29, (byte) i3});
                        break;
                    case '(':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 20, (byte) i3});
                        break;
                }
            }
        });
        getParkPageUiSet(context).setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._301.UiMgr.5
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public void onClickItem(int i) {
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
        });
        final AirPageUiSet airUiSet = getAirUiSet(context);
        if (isThree()) {
            airUiSet.getFrontArea().setShowSeatHeat(true);
            airUiSet.getFrontArea().setShowSeatCold(true);
        }
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._301.UiMgr.6
            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
            /* JADX WARN: Removed duplicated region for block: B:4:0x001b  */
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onClickItem(int r5) {
                /*
                    r4 = this;
                    com.hzbhd.canbus.adapter.bean.AirPageUiSet r0 = r2
                    com.hzbhd.canbus.adapter.bean.FrontArea r0 = r0.getFrontArea()
                    java.lang.String[][] r0 = r0.getLineBtnAction()
                    r1 = 0
                    r0 = r0[r1]
                    r5 = r0[r5]
                    r5.hashCode()
                    int r0 = r5.hashCode()
                    r2 = 1
                    r3 = -1
                    switch(r0) {
                        case -597744666: goto L49;
                        case 3005871: goto L3e;
                        case 3094652: goto L33;
                        case 106858757: goto L28;
                        case 756225563: goto L1d;
                        default: goto L1b;
                    }
                L1b:
                    r1 = r3
                    goto L52
                L1d:
                    java.lang.String r0 = "in_out_cycle"
                    boolean r5 = r5.equals(r0)
                    if (r5 != 0) goto L26
                    goto L1b
                L26:
                    r1 = 4
                    goto L52
                L28:
                    java.lang.String r0 = "power"
                    boolean r5 = r5.equals(r0)
                    if (r5 != 0) goto L31
                    goto L1b
                L31:
                    r1 = 3
                    goto L52
                L33:
                    java.lang.String r0 = "dual"
                    boolean r5 = r5.equals(r0)
                    if (r5 != 0) goto L3c
                    goto L1b
                L3c:
                    r1 = 2
                    goto L52
                L3e:
                    java.lang.String r0 = "auto"
                    boolean r5 = r5.equals(r0)
                    if (r5 != 0) goto L47
                    goto L1b
                L47:
                    r1 = r2
                    goto L52
                L49:
                    java.lang.String r0 = "blow_positive"
                    boolean r5 = r5.equals(r0)
                    if (r5 != 0) goto L52
                    goto L1b
                L52:
                    r5 = 25
                    switch(r1) {
                        case 0: goto L74;
                        case 1: goto L6c;
                        case 2: goto L64;
                        case 3: goto L5e;
                        case 4: goto L58;
                        default: goto L57;
                    }
                L57:
                    goto L79
                L58:
                    com.hzbhd.canbus.car._301.UiMgr r0 = com.hzbhd.canbus.car._301.UiMgr.this
                    com.hzbhd.canbus.car._301.UiMgr.access$200(r0, r5)
                    goto L79
                L5e:
                    com.hzbhd.canbus.car._301.UiMgr r5 = com.hzbhd.canbus.car._301.UiMgr.this
                    com.hzbhd.canbus.car._301.UiMgr.access$200(r5, r2)
                    goto L79
                L64:
                    com.hzbhd.canbus.car._301.UiMgr r5 = com.hzbhd.canbus.car._301.UiMgr.this
                    r0 = 16
                    com.hzbhd.canbus.car._301.UiMgr.access$200(r5, r0)
                    goto L79
                L6c:
                    com.hzbhd.canbus.car._301.UiMgr r5 = com.hzbhd.canbus.car._301.UiMgr.this
                    r0 = 21
                    com.hzbhd.canbus.car._301.UiMgr.access$200(r5, r0)
                    goto L79
                L74:
                    com.hzbhd.canbus.car._301.UiMgr r0 = com.hzbhd.canbus.car._301.UiMgr.this
                    com.hzbhd.canbus.car._301.UiMgr.access$200(r0, r5)
                L79:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._301.UiMgr.AnonymousClass6.onClickItem(int):void");
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._301.UiMgr.7
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public void onClickItem(int i) {
                String str = airUiSet.getFrontArea().getLineBtnAction()[1][i];
                str.hashCode();
                if (str.equals("ac")) {
                    byte[] bArr = new byte[4];
                    bArr[0] = 22;
                    bArr[1] = -58;
                    bArr[2] = -84;
                    bArr[3] = (byte) (GeneralAirData.ac ? 2 : 1);
                    CanbusMsgSender.sendMsg(bArr);
                }
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._301.UiMgr.8
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public void onClickItem(int i) {
                Log.d("mww", "onClickItem " + i);
                String str = airUiSet.getFrontArea().getLineBtnAction()[2][i];
                str.hashCode();
                if (str.equals("front_defog")) {
                    UiMgr.this.sendAirKeyMsg(19);
                } else if (str.equals("rear_defog")) {
                    UiMgr.this.sendAirKeyMsg(20);
                }
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._301.UiMgr.9
            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
            /* JADX WARN: Removed duplicated region for block: B:4:0x001b  */
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onClickItem(int r5) {
                /*
                    r4 = this;
                    com.hzbhd.canbus.adapter.bean.AirPageUiSet r0 = r2
                    com.hzbhd.canbus.adapter.bean.FrontArea r0 = r0.getFrontArea()
                    java.lang.String[][] r0 = r0.getLineBtnAction()
                    r1 = 3
                    r0 = r0[r1]
                    r5 = r0[r5]
                    r5.hashCode()
                    int r0 = r5.hashCode()
                    r2 = 1
                    r3 = -1
                    switch(r0) {
                        case -597744666: goto L52;
                        case -424438238: goto L47;
                        case 1018451744: goto L3c;
                        case 1434490075: goto L33;
                        case 1434539597: goto L28;
                        case 1568764496: goto L1d;
                        default: goto L1b;
                    }
                L1b:
                    r1 = r3
                    goto L5c
                L1d:
                    java.lang.String r0 = "blow_window_foot"
                    boolean r5 = r5.equals(r0)
                    if (r5 != 0) goto L26
                    goto L1b
                L26:
                    r1 = 5
                    goto L5c
                L28:
                    java.lang.String r0 = "blow_head"
                    boolean r5 = r5.equals(r0)
                    if (r5 != 0) goto L31
                    goto L1b
                L31:
                    r1 = 4
                    goto L5c
                L33:
                    java.lang.String r0 = "blow_foot"
                    boolean r5 = r5.equals(r0)
                    if (r5 != 0) goto L5c
                    goto L1b
                L3c:
                    java.lang.String r0 = "blow_head_foot"
                    boolean r5 = r5.equals(r0)
                    if (r5 != 0) goto L45
                    goto L1b
                L45:
                    r1 = 2
                    goto L5c
                L47:
                    java.lang.String r0 = "blow_negative"
                    boolean r5 = r5.equals(r0)
                    if (r5 != 0) goto L50
                    goto L1b
                L50:
                    r1 = r2
                    goto L5c
                L52:
                    java.lang.String r0 = "blow_positive"
                    boolean r5 = r5.equals(r0)
                    if (r5 != 0) goto L5b
                    goto L1b
                L5b:
                    r1 = 0
                L5c:
                    r5 = 37
                    r0 = 36
                    switch(r1) {
                        case 0: goto L97;
                        case 1: goto L83;
                        case 2: goto L7b;
                        case 3: goto L73;
                        case 4: goto L6c;
                        case 5: goto L64;
                        default: goto L63;
                    }
                L63:
                    goto Laa
                L64:
                    com.hzbhd.canbus.car._301.UiMgr r5 = com.hzbhd.canbus.car._301.UiMgr.this
                    r0 = 32
                    com.hzbhd.canbus.car._301.UiMgr.access$200(r5, r0)
                    goto Laa
                L6c:
                    com.hzbhd.canbus.car._301.UiMgr r5 = com.hzbhd.canbus.car._301.UiMgr.this
                    r0 = 7
                    com.hzbhd.canbus.car._301.UiMgr.access$200(r5, r0)
                    goto Laa
                L73:
                    com.hzbhd.canbus.car._301.UiMgr r5 = com.hzbhd.canbus.car._301.UiMgr.this
                    r0 = 8
                    com.hzbhd.canbus.car._301.UiMgr.access$200(r5, r0)
                    goto Laa
                L7b:
                    com.hzbhd.canbus.car._301.UiMgr r5 = com.hzbhd.canbus.car._301.UiMgr.this
                    r0 = 33
                    com.hzbhd.canbus.car._301.UiMgr.access$200(r5, r0)
                    goto Laa
                L83:
                    android.content.Context r1 = r3
                    int r1 = com.hzbhd.canbus.util.CommUtil.isAirTemperatureSwitch(r1)
                    if (r1 != r2) goto L91
                    com.hzbhd.canbus.car._301.UiMgr r5 = com.hzbhd.canbus.car._301.UiMgr.this
                    com.hzbhd.canbus.car._301.UiMgr.access$200(r5, r0)
                    goto Laa
                L91:
                    com.hzbhd.canbus.car._301.UiMgr r0 = com.hzbhd.canbus.car._301.UiMgr.this
                    com.hzbhd.canbus.car._301.UiMgr.access$200(r0, r5)
                    goto Laa
                L97:
                    android.content.Context r1 = r3
                    int r1 = com.hzbhd.canbus.util.CommUtil.isAirTemperatureSwitch(r1)
                    if (r1 != r2) goto La5
                    com.hzbhd.canbus.car._301.UiMgr r0 = com.hzbhd.canbus.car._301.UiMgr.this
                    com.hzbhd.canbus.car._301.UiMgr.access$200(r0, r5)
                    goto Laa
                La5:
                    com.hzbhd.canbus.car._301.UiMgr r5 = com.hzbhd.canbus.car._301.UiMgr.this
                    com.hzbhd.canbus.car._301.UiMgr.access$200(r5, r0)
                Laa:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._301.UiMgr.AnonymousClass9.onClickItem(int):void");
            }
        }});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._301.UiMgr.10
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr.this.sendAirKeyMsg(9);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr.this.sendAirKeyMsg(10);
            }
        });
        airUiSet.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, this.mOnAirBtnClickListenerRearBottom});
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.onUpDownClickListenerLeft, this.onUpDownClickListenerCenter, this.onUpDownClickListenerRight});
        airUiSet.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._301.UiMgr.11
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                UiMgr.this.sendAirKeyMsg(11);
            }
        }, new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._301.UiMgr.12
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                UiMgr.this.sendAirKeyMsg(13);
            }
        }, new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._301.UiMgr.13
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                UiMgr.this.sendAirKeyMsg(12);
            }
        }, new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._301.UiMgr.14
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                UiMgr.this.sendAirKeyMsg(14);
            }
        }});
        DriverDataPageUiSet driverDataPageUiSet = getDriverDataPageUiSet(context);
        int[] settingItemPosition = getSettingItemPosition(settingUiSet, "_41_delete_fuel_record");
        driverDataPageUiSet.setLeftPosition(settingItemPosition[0]);
        driverDataPageUiSet.setRightPosition(settingItemPosition[1]);
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        amplifierPageUiSet.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._301.UiMgr.15
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
            public void progress(AmplifierActivity.AmplifierBand amplifierBand, int i) {
                int i2 = AnonymousClass25.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[amplifierBand.ordinal()];
                if (i2 == 1) {
                    UiMgr.this.mIsClickReset = false;
                    SharePreUtil.setIntValue(UiMgr.this.mContext, UiMgr.CAN_269_SAVE_AMP__HEAVY_BASS, i);
                    GeneralAmplifierData.megaBass = i;
                    UiMgr.this.sendAmpKeyMsg(6, i);
                    return;
                }
                if (i2 == 2) {
                    UiMgr.this.mIsClickReset = false;
                    SharePreUtil.setIntValue(UiMgr.this.mContext, UiMgr.CAN_269_SAVE_AMP_BASS, i);
                    GeneralAmplifierData.bandBass = i;
                    UiMgr.this.sendAmpKeyMsg(5, i);
                    return;
                }
                if (i2 == 3) {
                    UiMgr.this.mIsClickReset = false;
                    SharePreUtil.setIntValue(UiMgr.this.mContext, UiMgr.CAN_269_SAVE_AMP_TRE, i);
                    GeneralAmplifierData.bandTreble = i;
                    UiMgr.this.sendAmpKeyMsg(3, i);
                    return;
                }
                if (i2 == 4) {
                    UiMgr.this.mIsClickReset = false;
                    SharePreUtil.setIntValue(UiMgr.this.mContext, UiMgr.CAN_269_SAVE_AMP_MID, i);
                    GeneralAmplifierData.bandMiddle = i;
                    UiMgr.this.sendAmpKeyMsg(4, i);
                    return;
                }
                if (i2 != 5) {
                    return;
                }
                UiMgr.this.mIsClickReset = false;
                SharePreUtil.setIntValue(UiMgr.this.mContext, UiMgr.CAN_269_SAVE_AMP_VOL, i);
                GeneralAmplifierData.volume = i;
                UiMgr.this.sendAmpKeyMsg(9, i);
            }
        });
        amplifierPageUiSet.setOnAmplifierPositionListener(new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._301.UiMgr.16
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
            public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
                int i2 = AnonymousClass25.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[amplifierPosition.ordinal()];
                if (i2 == 1) {
                    if (UiMgr.this.mIsClickReset) {
                        return;
                    }
                    SharePreUtil.setIntValue(UiMgr.this.mContext, UiMgr.CAN_269_SAVE_AMP_FR, i);
                    GeneralAmplifierData.frontRear = i;
                    UiMgr.this.sendAmpKeyMsg(1, i + 9);
                    return;
                }
                if (i2 != 2) {
                    return;
                }
                if (UiMgr.this.mIsClickReset) {
                    UiMgr.this.mIsClickReset = false;
                    return;
                }
                SharePreUtil.setIntValue(UiMgr.this.mContext, UiMgr.CAN_269_SAVE_AMP_LR, i);
                GeneralAmplifierData.leftRight = i;
                UiMgr.this.sendAmpKeyMsg(2, i + 9);
            }
        });
        amplifierPageUiSet.setOnAmplifierResetPositionListener(new OnAmplifierResetPositionListener() { // from class: com.hzbhd.canbus.car._301.UiMgr.17
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierResetPositionListener
            public void resetClick() {
                UiMgr.this.sendAmpKeyMsg(10, 0);
                UiMgr.this.mIsClickReset = true;
                UiMgr.this.msgMgr.updateAmpUi(null);
            }
        });
    }

    /* renamed from: com.hzbhd.canbus.car._301.UiMgr$25, reason: invalid class name */
    static /* synthetic */ class AnonymousClass25 {
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
                iArr2[AmplifierActivity.AmplifierBand.MEGA_BASS.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.BASS.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.TREBLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.MIDDLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.VOLUME.ordinal()] = 5;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirKeyMsg(final int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -32, (byte) i, 1});
        this.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._301.UiMgr$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                CanbusMsgSender.sendMsg(new byte[]{22, -32, (byte) i, 0});
            }
        }, 30L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAmpKeyMsg(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte) i, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }

    private void initSetting(Context context) {
        getMsgMgr(context).updateSettings(6, 1, SharePreUtil.getIntValue(context, SHARE_41_CTM_SYSTEM, 0));
        getMsgMgr(context).updateSettings(6, 4, SharePreUtil.getIntValue(context, SHARE_41_LANGUAGE, 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public TimerUtil getSettingTimerUtil() {
        if (this.mRequestTimer == null) {
            this.mRequestTimer = new TimerUtil();
        }
        return this.mRequestTimer;
    }

    private int[] getSettingItemPosition(SettingPageUiSet settingPageUiSet, String str) {
        int[] iArr = {-1, -1};
        for (int i = 0; i < settingPageUiSet.getList().size(); i++) {
            SettingPageUiSet.ListBean listBean = settingPageUiSet.getList().get(i);
            for (int i2 = 0; i2 < listBean.getItemList().size(); i2++) {
                if (listBean.getItemList().get(i2).getTitleSrn().equals(str)) {
                    iArr[0] = i;
                    iArr[1] = i2;
                }
            }
        }
        return iArr;
    }

    private void requestCommand() {
        final byte[][] bArr = {new byte[]{22, -112, 50, 0}, new byte[]{22, -112, 37, 0}, new byte[]{22, -112, -46, 0}};
        getSettingTimerUtil().startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._301.UiMgr.20
            int i = 0;

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                int i = this.i;
                byte[][] bArr2 = bArr;
                if (i >= bArr2.length) {
                    UiMgr.this.getSettingTimerUtil().stopTimer();
                } else {
                    this.i = i + 1;
                    CanbusMsgSender.sendMsg(bArr2[i]);
                }
            }
        }, 0L, 100L);
    }

    public boolean isThree() {
        return this.mDifferentId == 2;
    }
}
