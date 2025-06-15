package com.hzbhd.canbus.car._41;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TimerUtil;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.TimerTask;
import kotlin.text.Typography;
import nfore.android.bt.res.NfDef;


public class UiMgr extends AbstractUiMgr {
    private static final String SHARE_41_CTM_SYSTEM = "share_41_ctm_system";
    static final String SHARE_41_FRONT_CAMERA_SWITCH = "share_41_front_camera_switch";
    private static final String SHARE_41_LANGUAGE = "share_41_language";
    private Context mContext;
    private MsgMgr mMsgMgr;
    private TimerUtil mRequestTimer;
    public String KEY_TEM_UNIT = "KEY_TEM_UNIT";
    private OnAirBtnClickListener mOnAirBtnClickListenerLeft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._41.UiMgr.8
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
    private OnAirBtnClickListener mOnAirBtnClickListenerBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._41.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 3});
                return;
            }
            if (i == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 4});
            } else if (i == 2) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 5});
            } else {
                if (i != 3) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 6});
            }
        }
    };

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        requestCommand();
        MsgMgr msgMgr = getMsgMgr(this.mContext);
        Context context2 = this.mContext;
        msgMgr.updateSettings(context2, getSettingLeftIndexes(context2, "front_camera_setup"), getSettingRightIndex(this.mContext, "front_camera_setup", "F_camera"), "F_camera", SharePreUtil.getIntValue(this.mContext, "F_camera", 0));
        MsgMgr msgMgr2 = getMsgMgr(this.mContext);
        Context context3 = this.mContext;
        msgMgr2.updateSettings(context3, getSettingLeftIndexes(context3, "_41_wheel_key"), getSettingRightIndex(this.mContext, "_41_wheel_key", "_41_wheel_key_mute_home"), "ID41_K_MUTE_OR_HOME", SharePreUtil.getIntValue(this.mContext, "ID41_K_MUTE_OR_HOME", 0));
    }

    public UiMgr(final Context context) {
        this.mContext = context;
        initSetting(context);
        final SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._41.UiMgr.1
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
        settingUiSet.setOnSettingItemClickListener(new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._41.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
            public void onClickItem(int i, int i2) {
                Objects.requireNonNull(settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn());
            }
        });
        settingUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._41.UiMgr.3
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
                    case "_41_tpms1":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 17, 0});
                        break;
                    case "_41_delete_fuel_record":
                        CanbusMsgSender.sendMsg(new byte[]{22, -112, 51, 3});
                        break;
                }
            }
        });
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._41.UiMgr.4
            /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
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
                    case -1216608962:
                        if (titleSrn.equals("F_camera")) {
                            c = 3;
                            break;
                        }
                        break;
                    case -722490656:
                        if (titleSrn.equals("_41_speed_distance_units")) {
                            c = 4;
                            break;
                        }
                        break;
                    case -549818908:
                        if (titleSrn.equals("_41_tachometer_switch")) {
                            c = 5;
                            break;
                        }
                        break;
                    case 102584022:
                        if (titleSrn.equals("language_setup")) {
                            c = 6;
                            break;
                        }
                        break;
                    case 117616127:
                        if (titleSrn.equals("_55_0x69_data0_bit10")) {
                            c = 7;
                            break;
                        }
                        break;
                    case 207393826:
                        if (titleSrn.equals("_41_util_tem")) {
                            c = '\b';
                            break;
                        }
                        break;
                    case 704422172:
                        if (titleSrn.equals("_55_0x67_data0_bit20")) {
                            c = '\t';
                            break;
                        }
                        break;
                    case 868863258:
                        if (titleSrn.equals("_41_key_remote_unlock")) {
                            c = '\n';
                            break;
                        }
                        break;
                    case 1005119904:
                        if (titleSrn.equals("_55_0x69_data1_bit43")) {
                            c = 11;
                            break;
                        }
                        break;
                    case 1005119968:
                        if (titleSrn.equals("_55_0x69_data1_bit65")) {
                            c = '\f';
                            break;
                        }
                        break;
                    case 1093047774:
                        if (titleSrn.equals("_41_wheel_key_mute_home")) {
                            c = '\r';
                            break;
                        }
                        break;
                    case 1245017999:
                        if (titleSrn.equals("_41_tachometer")) {
                            c = 14;
                            break;
                        }
                        break;
                    case 1285535413:
                        if (titleSrn.equals("_41_ctm_system")) {
                            c = 15;
                            break;
                        }
                        break;
                    case 1286553292:
                        if (titleSrn.equals("_194_unlock_mode")) {
                            c = 16;
                            break;
                        }
                        break;
                    case 1298522815:
                        if (titleSrn.equals("_55_0x68_data1_bit10")) {
                            c = 17;
                            break;
                        }
                        break;
                    case 1298522943:
                        if (titleSrn.equals("_55_0x68_data1_bit54")) {
                            c = 18;
                            break;
                        }
                        break;
                    case 1298523007:
                        if (titleSrn.equals("_55_0x68_data1_bit76")) {
                            c = 19;
                            break;
                        }
                        break;
                    case 1300899316:
                        if (titleSrn.equals("_55_0x75_data1_bit0")) {
                            c = 20;
                            break;
                        }
                        break;
                    case 1300899317:
                        if (titleSrn.equals("_55_0x75_data1_bit1")) {
                            c = 21;
                            break;
                        }
                        break;
                    case 1591925822:
                        if (titleSrn.equals("_55_0x67_data1_bit10")) {
                            c = 22;
                            break;
                        }
                        break;
                    case 1591925886:
                        if (titleSrn.equals("_55_0x67_data1_bit32")) {
                            c = 23;
                            break;
                        }
                        break;
                    case 1591925981:
                        if (titleSrn.equals("_55_0x67_data1_bit64")) {
                            c = 24;
                            break;
                        }
                        break;
                    case 1594302323:
                        if (titleSrn.equals("_55_0x65_data1_bit0")) {
                            c = 25;
                            break;
                        }
                        break;
                    case 1594302326:
                        if (titleSrn.equals("_55_0x65_data1_bit3")) {
                            c = 26;
                            break;
                        }
                        break;
                    case 1723385042:
                        if (titleSrn.equals("_55_0x66_data1_bit0")) {
                            c = 27;
                            break;
                        }
                        break;
                    case 1723385043:
                        if (titleSrn.equals("_55_0x66_data1_bit1")) {
                            c = 28;
                            break;
                        }
                        break;
                    case 1723385044:
                        if (titleSrn.equals("_55_0x66_data1_bit2")) {
                            c = 29;
                            break;
                        }
                        break;
                    case 1723385045:
                        if (titleSrn.equals("_55_0x66_data1_bit3")) {
                            c = 30;
                            break;
                        }
                        break;
                    case 1823838613:
                        if (titleSrn.equals("_55_0x67_data0_bit3")) {
                            c = 31;
                            break;
                        }
                        break;
                    case 1981550482:
                        if (titleSrn.equals("_55_0x68_data1_bit2")) {
                            c = ' ';
                            break;
                        }
                        break;
                    case 1981550483:
                        if (titleSrn.equals("_55_0x68_data1_bit3")) {
                            c = '!';
                            break;
                        }
                        break;
                    case 2082004050:
                        if (titleSrn.equals("_55_0x69_data0_bit2")) {
                            c = Typography.quote;
                            break;
                        }
                        break;
                    case 2082004051:
                        if (titleSrn.equals("_55_0x69_data0_bit3")) {
                            c = '#';
                            break;
                        }
                        break;
                    case 2082004052:
                        if (titleSrn.equals("_55_0x69_data0_bit4")) {
                            c = Typography.dollar;
                            break;
                        }
                        break;
                }
                switch (c) {
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
                        UiMgr uiMgr = UiMgr.this;
                        uiMgr.getMsgMgr(uiMgr.mContext).updateSettings(UiMgr.this.mContext, i, i2, "F_camera", i3);
                        break;
                    case 4:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 21, (byte) i3});
                        break;
                    case 5:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 35, (byte) i3});
                        break;
                    case 6:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 85, (byte) i3});
                        UiMgr.this.getMsgMgr(context).updateSettings(i, i2, i3);
                        SharePreUtil.setIntValue(context, UiMgr.SHARE_41_LANGUAGE, i3);
                        break;
                    case 7:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 18, (byte) i3});
                        break;
                    case '\b':
                        SharePreUtil.setStringValue(UiMgr.this.mContext, UiMgr.this.KEY_TEM_UNIT, i3 == 0 ? "C" : "F");
                        UiMgr uiMgr2 = UiMgr.this;
                        MsgMgr msgMgr = uiMgr2.getMsgMgr(uiMgr2.mContext);
                        UiMgr uiMgr3 = UiMgr.this;
                        int settingLeftIndexes = uiMgr3.getSettingLeftIndexes(uiMgr3.mContext, "_41_util");
                        UiMgr uiMgr4 = UiMgr.this;
                        msgMgr.updateSettings(settingLeftIndexes, uiMgr4.getSettingRightIndex(uiMgr4.mContext, "_41_util", "_41_util_tem"), i3);
                        break;
                    case '\t':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 27, (byte) i3});
                        break;
                    case '\n':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 9, (byte) i3});
                        break;
                    case 11:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, (byte) i3});
                        break;
                    case '\f':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, (byte) i3});
                        break;
                    case '\r':
                        UiMgr uiMgr5 = UiMgr.this;
                        uiMgr5.getMsgMgr(uiMgr5.mContext).updateSettings(UiMgr.this.mContext, i, i2, "ID41_K_MUTE_OR_HOME", i3);
                        break;
                    case 14:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 22, (byte) i3});
                        break;
                    case 15:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 96, (byte) i3});
                        UiMgr.this.getMsgMgr(context).updateSettings(i, i2, i3);
                        SharePreUtil.setIntValue(context, UiMgr.SHARE_41_CTM_SYSTEM, i3);
                        break;
                    case 16:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 25, (byte) i3});
                        break;
                    case 17:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 31, (byte) i3});
                        break;
                    case 18:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 34, (byte) i3});
                        break;
                    case 19:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 36, (byte) i3});
                        break;
                    case 20:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 37, (byte) i3});
                        break;
                    case 21:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 38, (byte) i3});
                        break;
                    case 22:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, (byte) i3});
                        break;
                    case 23:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 5, (byte) i3});
                        break;
                    case 24:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, (byte) i3});
                        break;
                    case 25:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 10, (byte) i3});
                        break;
                    case 26:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 23, (byte) i3});
                        break;
                    case 27:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, (byte) i3});
                        break;
                    case 28:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 30, (byte) i3});
                        break;
                    case 29:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 26, (byte) i3});
                        break;
                    case 30:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) i3});
                        break;
                    case 31:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 28, (byte) i3});
                        break;
                    case ' ':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 32, (byte) i3});
                        break;
                    case '!':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 33, (byte) i3});
                        break;
                    case '\"':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 19, (byte) i3});
                        break;
                    case '#':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 29, (byte) i3});
                        break;
                    case '$':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 20, (byte) i3});
                        break;
                }
            }
        });
        getParkPageUiSet(context).setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._41.UiMgr.5
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
        AirPageUiSet airUiSet = getAirUiSet(context);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, this.mOnAirBtnClickListenerLeft, null, this.mOnAirBtnClickListenerBottom});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._41.UiMgr.6
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -83, (byte) DataHandleUtils.rangeNumber(GeneralAirData.front_wind_level - 1, 1, 7)});
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -83, (byte) DataHandleUtils.rangeNumber(GeneralAirData.front_wind_level + 1, 1, 7)});
            }
        });
        airUiSet.getFrontArea().setOnAirPageStatusListener(new OnAirPageStatusListener() { // from class: com.hzbhd.canbus.car._41.UiMgr.7
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener
            public void onStatusChange(int i) {
                CanbusMsgSender.sendMsg(new byte[]{22, -112, 33, 0});
            }
        });
        DriverDataPageUiSet driverDataPageUiSet = getDriverDataPageUiSet(context);
        int[] settingItemPosition = getSettingItemPosition(settingUiSet, "_41_delete_fuel_record");
        driverDataPageUiSet.setLeftPosition(settingItemPosition[0]);
        driverDataPageUiSet.setRightPosition(settingItemPosition[1]);
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
        getMsgMgr(context).updateSettings(6, 2, SharePreUtil.getIntValue(context, SHARE_41_LANGUAGE, 0));
        getMsgMgr(this.mContext).updateSettings(getSettingLeftIndexes(this.mContext, "_41_util"), getSettingRightIndex(this.mContext, "_41_util", "_41_util_tem"), !SharePreUtil.getStringValue(this.mContext, this.KEY_TEM_UNIT, "C").equals("C") ? 1 : 0);
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
        getSettingTimerUtil().startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._41.UiMgr.10
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
