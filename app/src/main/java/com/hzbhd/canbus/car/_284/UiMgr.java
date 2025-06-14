package com.hzbhd.canbus.car._284;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnBackCameraStatusListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.Arrays;
import kotlin.text.Typography;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    static final String SHARE_284_SUPPORT_PANORAMIC = "share_284_support_panoramic";
    static final String SHARE_284_SUPPORT_RIGHTVIEW = "share_284_support_rightview";
    private Context mContext;
    private MsgMgr mMsgMgr;
    private final int MSG_SEND_AIR_COMMAND_UP = 16;
    private byte[] m0x86Data = {22, -122, 0, 0, 0, 0, 0, 0};
    private byte[] m0x85Data = {22, -123, 0, 0};
    private Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.hzbhd.canbus.car._284.UiMgr.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 16) {
                CanbusMsgSender.sendMsg(UiMgr.this.m0x86Data);
            }
        }
    };
    private OnAirBtnClickListener mOnAirBtnClickListenerTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._284.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr.this.sendAirCommand(0, i);
        }
    };
    private OnAirBtnClickListener mOnAirBtnClickListenerLeft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._284.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr.this.sendAirCommand(1, i);
        }
    };
    private OnAirBtnClickListener mOnAirBtnClickListenerRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._284.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr.this.sendAirCommand(2, i);
        }
    };
    private OnAirBtnClickListener mOnAirBtnClickListenerBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._284.UiMgr.12
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr.this.sendAirCommand(3, i);
        }
    };
    private OnAirTemperatureUpDownClickListener mOnUpDownClickListener = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._284.UiMgr.13
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            byte[] bArrCopyOf = Arrays.copyOf(UiMgr.this.m0x86Data, UiMgr.this.m0x86Data.length);
            bArrCopyOf[5] = (byte) DataHandleUtils.setIntByteWithBit(0, 1, true);
            UiMgr.this.sendAirCommand(bArrCopyOf);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            byte[] bArrCopyOf = Arrays.copyOf(UiMgr.this.m0x86Data, UiMgr.this.m0x86Data.length);
            bArrCopyOf[5] = (byte) DataHandleUtils.setIntByteWithBit(0, 0, true);
            UiMgr.this.sendAirCommand(bArrCopyOf);
        }
    };

    void initSettingItem() {
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        this.mContext = context;
        Log.i("ljq", "updateUiByDifferentCar: ");
    }

    public UiMgr(final Context context) {
        this.mContext = context;
        Log.i("ljq", "UiMgr: ");
        final SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemClickListener(new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._284.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
            public void onClickItem(int i, int i2) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                if (titleSrn.equals("_55_0xE8_data4")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -123, UiMgr.this.m0x85Data[2], (byte) (UiMgr.this.m0x85Data[3] | 64)});
                } else if (titleSrn.equals("_284_open_right_view")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -123, UiMgr.this.m0x85Data[2], (byte) (UiMgr.this.m0x85Data[3] | 32)});
                }
            }
        });
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._284.UiMgr.3
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public void onClickItem(int i, int i2, int i3) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                if (titleSrn.equals("_250_ambient_light_brightness")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 39, (byte) i3});
                } else if (titleSrn.equals("_284_setting_item_29")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 41, (byte) (i3 + 10)});
                }
            }
        });
        settingUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._284.UiMgr.4
            @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
            public void onConfirmClick(int i, int i2) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                if (titleSrn.equals("_284_tire_pressure_reset")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, 1});
                } else if (titleSrn.equals("_284_vehicle_setting_default")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 0, 1});
                }
            }
        });
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._284.UiMgr.5
            /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                char c;
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                char c2 = 65535;
                switch (titleSrn.hashCode()) {
                    case -2080242761:
                        if (titleSrn.equals("_284_85_1_7")) {
                            c2 = 0;
                            break;
                        }
                        break;
                    case -1839027678:
                        if (titleSrn.equals("_194_window")) {
                            c2 = 1;
                            break;
                        }
                        break;
                    case -1720286544:
                        if (titleSrn.equals("_220_steering_modes")) {
                            c2 = 2;
                            break;
                        }
                        break;
                    case -1613767836:
                        if (titleSrn.equals("seatDriveProfile")) {
                            c2 = 3;
                            break;
                        }
                        break;
                    case -1590596702:
                        if (titleSrn.equals("_250_automatic_folding_mirror")) {
                            c2 = 4;
                            break;
                        }
                        break;
                    case -1438897752:
                        if (titleSrn.equals("_284_support_right_view")) {
                            c2 = 5;
                            break;
                        }
                        break;
                    case -1313213960:
                        if (titleSrn.equals("auto_lock_when_stop_car")) {
                            c2 = 6;
                            break;
                        }
                        break;
                    case -1241372009:
                        if (titleSrn.equals("_284_85_0_title")) {
                            c2 = 7;
                            break;
                        }
                        break;
                    case -1015001166:
                        if (titleSrn.equals("_163_setting_1")) {
                            c2 = '\b';
                            break;
                        }
                        break;
                    case -1015001161:
                        if (titleSrn.equals("_163_setting_6")) {
                            c2 = '\t';
                            break;
                        }
                        break;
                    case -43540113:
                        if (titleSrn.equals("_284_setting_item_02")) {
                            c2 = '\n';
                            break;
                        }
                        break;
                    case -43540112:
                        if (titleSrn.equals("_284_setting_item_03")) {
                            c2 = 11;
                            break;
                        }
                        break;
                    case -43540109:
                        if (titleSrn.equals("_284_setting_item_06")) {
                            c = '\f';
                            c2 = c;
                            break;
                        }
                        break;
                    case -43540108:
                        if (titleSrn.equals("_284_setting_item_07")) {
                            c2 = '\r';
                            break;
                        }
                        break;
                    case -43540107:
                        if (titleSrn.equals("_284_setting_item_08")) {
                            c2 = 14;
                            break;
                        }
                        break;
                    case -43540106:
                        if (titleSrn.equals("_284_setting_item_09")) {
                            c2 = 15;
                            break;
                        }
                        break;
                    case -43540098:
                        if (titleSrn.equals("_284_setting_item_0A")) {
                            c2 = 16;
                            break;
                        }
                        break;
                    case -43540097:
                        if (titleSrn.equals("_284_setting_item_0B")) {
                            c2 = 17;
                            break;
                        }
                        break;
                    case -43540095:
                        if (titleSrn.equals("_284_setting_item_0D")) {
                            c2 = 18;
                            break;
                        }
                        break;
                    case -43540094:
                        if (titleSrn.equals("_284_setting_item_0E")) {
                            c2 = 19;
                            break;
                        }
                        break;
                    case -43540093:
                        if (titleSrn.equals("_284_setting_item_0F")) {
                            c2 = 20;
                            break;
                        }
                        break;
                    case -43540083:
                        if (titleSrn.equals("_284_setting_item_11")) {
                            c = 21;
                            c2 = c;
                            break;
                        }
                        break;
                    case -43540082:
                        if (titleSrn.equals("_284_setting_item_12")) {
                            c2 = 22;
                            break;
                        }
                        break;
                    case -43540081:
                        if (titleSrn.equals("_284_setting_item_13")) {
                            c = 23;
                            c2 = c;
                            break;
                        }
                        break;
                    case -43540080:
                        if (titleSrn.equals("_284_setting_item_14")) {
                            c = 24;
                            c2 = c;
                            break;
                        }
                        break;
                    case -43540079:
                        if (titleSrn.equals("_284_setting_item_15")) {
                            c = 25;
                            c2 = c;
                            break;
                        }
                        break;
                    case -43540078:
                        if (titleSrn.equals("_284_setting_item_16")) {
                            c = 26;
                            c2 = c;
                            break;
                        }
                        break;
                    case -43540077:
                        if (titleSrn.equals("_284_setting_item_17")) {
                            c = 27;
                            c2 = c;
                            break;
                        }
                        break;
                    case -43540066:
                        if (titleSrn.equals("_284_setting_item_1B")) {
                            c = 28;
                            c2 = c;
                            break;
                        }
                        break;
                    case -43540065:
                        if (titleSrn.equals("_284_setting_item_1C")) {
                            c = 29;
                            c2 = c;
                            break;
                        }
                        break;
                    case -43540064:
                        if (titleSrn.equals("_284_setting_item_1D")) {
                            c = 30;
                            c2 = c;
                            break;
                        }
                        break;
                    case -43540063:
                        if (titleSrn.equals("_284_setting_item_1E")) {
                            c = 31;
                            c2 = c;
                            break;
                        }
                        break;
                    case -43540053:
                        if (titleSrn.equals("_284_setting_item_20")) {
                            c = ' ';
                            c2 = c;
                            break;
                        }
                        break;
                    case -43540052:
                        if (titleSrn.equals("_284_setting_item_21")) {
                            c = '!';
                            c2 = c;
                            break;
                        }
                        break;
                    case -43540051:
                        if (titleSrn.equals("_284_setting_item_22")) {
                            c = Typography.quote;
                            c2 = c;
                            break;
                        }
                        break;
                    case -43540050:
                        if (titleSrn.equals("_284_setting_item_23")) {
                            c = '#';
                            c2 = c;
                            break;
                        }
                        break;
                    case -43540049:
                        if (titleSrn.equals("_284_setting_item_24")) {
                            c = Typography.dollar;
                            c2 = c;
                            break;
                        }
                        break;
                    case -43540048:
                        if (titleSrn.equals("_284_setting_item_25")) {
                            c = '%';
                            c2 = c;
                            break;
                        }
                        break;
                    case -43540047:
                        if (titleSrn.equals("_284_setting_item_26")) {
                            c = Typography.amp;
                            c2 = c;
                            break;
                        }
                        break;
                    case -43540045:
                        if (titleSrn.equals("_284_setting_item_28")) {
                            c = '\'';
                            c2 = c;
                            break;
                        }
                        break;
                    case -43540036:
                        if (titleSrn.equals("_284_setting_item_2A")) {
                            c = '(';
                            c2 = c;
                            break;
                        }
                        break;
                    case -43540035:
                        if (titleSrn.equals("_284_setting_item_2B")) {
                            c = ')';
                            c2 = c;
                            break;
                        }
                        break;
                    case -43540034:
                        if (titleSrn.equals("_284_setting_item_2C")) {
                            c = '*';
                            c2 = c;
                            break;
                        }
                        break;
                    case -43540033:
                        if (titleSrn.equals("_284_setting_item_2D")) {
                            c = '+';
                            c2 = c;
                            break;
                        }
                        break;
                    case -43540032:
                        if (titleSrn.equals("_284_setting_item_2E")) {
                            c = ',';
                            c2 = c;
                            break;
                        }
                        break;
                    case -43540031:
                        if (titleSrn.equals("_284_setting_item_2F")) {
                            c = '-';
                            c2 = c;
                            break;
                        }
                        break;
                    case -43540022:
                        if (titleSrn.equals("_284_setting_item_30")) {
                            c = '.';
                            c2 = c;
                            break;
                        }
                        break;
                    case -43540021:
                        if (titleSrn.equals("_284_setting_item_31")) {
                            c = '/';
                            c2 = c;
                            break;
                        }
                        break;
                    case -43540019:
                        if (titleSrn.equals("_284_setting_item_33")) {
                            c = '0';
                            c2 = c;
                            break;
                        }
                        break;
                    case -43540018:
                        if (titleSrn.equals("_284_setting_item_34")) {
                            c = '1';
                            c2 = c;
                            break;
                        }
                        break;
                    case -43540017:
                        if (titleSrn.equals("_284_setting_item_35")) {
                            c = '2';
                            c2 = c;
                            break;
                        }
                        break;
                    case -43540016:
                        if (titleSrn.equals("_284_setting_item_36")) {
                            c = '3';
                            c2 = c;
                            break;
                        }
                        break;
                    case -43540015:
                        if (titleSrn.equals("_284_setting_item_37")) {
                            c = '4';
                            c2 = c;
                            break;
                        }
                        break;
                    case -43540014:
                        if (titleSrn.equals("_284_setting_item_38")) {
                            c = '5';
                            c2 = c;
                            break;
                        }
                        break;
                    case -43540005:
                        if (titleSrn.equals("_284_setting_item_3A")) {
                            c = '6';
                            c2 = c;
                            break;
                        }
                        break;
                    case -43540004:
                        if (titleSrn.equals("_284_setting_item_3B")) {
                            c = '7';
                            c2 = c;
                            break;
                        }
                        break;
                    case -43540003:
                        if (titleSrn.equals("_284_setting_item_3C")) {
                            c = '8';
                            c2 = c;
                            break;
                        }
                        break;
                    case -43540002:
                        if (titleSrn.equals("_284_setting_item_3D")) {
                            c = '9';
                            c2 = c;
                            break;
                        }
                        break;
                    case -43540001:
                        if (titleSrn.equals("_284_setting_item_3E")) {
                            c = ':';
                            c2 = c;
                            break;
                        }
                        break;
                    case -43540000:
                        if (titleSrn.equals("_284_setting_item_3F")) {
                            c = ';';
                            c2 = c;
                            break;
                        }
                        break;
                    case -43539988:
                        if (titleSrn.equals("_284_setting_item_43")) {
                            c = Typography.less;
                            c2 = c;
                            break;
                        }
                        break;
                    case -43539987:
                        if (titleSrn.equals("_284_setting_item_44")) {
                            c = '=';
                            c2 = c;
                            break;
                        }
                        break;
                    case -43539986:
                        if (titleSrn.equals("_284_setting_item_45")) {
                            c = Typography.greater;
                            c2 = c;
                            break;
                        }
                        break;
                    case -43539985:
                        if (titleSrn.equals("_284_setting_item_46")) {
                            c = '?';
                            c2 = c;
                            break;
                        }
                        break;
                    case -43539984:
                        if (titleSrn.equals("_284_setting_item_47")) {
                            c = '@';
                            c2 = c;
                            break;
                        }
                        break;
                    case -43539983:
                        if (titleSrn.equals("_284_setting_item_48")) {
                            c = 'A';
                            c2 = c;
                            break;
                        }
                        break;
                    case -43539982:
                        if (titleSrn.equals("_284_setting_item_49")) {
                            c = 'B';
                            c2 = c;
                            break;
                        }
                        break;
                    case 712683749:
                        if (titleSrn.equals("support_panorama")) {
                            c = 'C';
                            c2 = c;
                            break;
                        }
                        break;
                    case 943720037:
                        if (titleSrn.equals("auto_door_unlock")) {
                            c = 'D';
                            c2 = c;
                            break;
                        }
                        break;
                    case 957932200:
                        if (titleSrn.equals("light_ctrl_3")) {
                            c = 'E';
                            c2 = c;
                            break;
                        }
                        break;
                    case 958464329:
                        if (titleSrn.equals("auto_lock_when_drive")) {
                            c = 'F';
                            c2 = c;
                            break;
                        }
                        break;
                    case 1235727081:
                        if (titleSrn.equals("_250_language")) {
                            c = 'G';
                            c2 = c;
                            break;
                        }
                        break;
                    case 1720570364:
                        if (titleSrn.equals("geely_emergency_brake_auto")) {
                            c = 'H';
                            c2 = c;
                            break;
                        }
                        break;
                    case 1989254418:
                        if (titleSrn.equals("_176_car_setting_title_25")) {
                            c = 'I';
                            c2 = c;
                            break;
                        }
                        break;
                }
                switch (c2) {
                    case 0:
                        UiMgr.this.m0x85Data[3] = (byte) DataHandleUtils.setIntByteWithBit(UiMgr.this.m0x85Data[3], 7, i3 == 1);
                        CanbusMsgSender.sendMsg(UiMgr.this.m0x85Data);
                        UiMgr.this.getMsgMgr(context).updateSettings(i, i2, i3);
                        break;
                    case 1:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 65, (byte) (i3 + 1)});
                        break;
                    case 2:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 64, (byte) (i3 + 1)});
                        break;
                    case 3:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 66, (byte) (i3 + 1)});
                        break;
                    case 4:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 1, (byte) (i3 + 1)});
                        break;
                    case 5:
                        SharePreUtil.setIntValue(context, UiMgr.SHARE_284_SUPPORT_RIGHTVIEW, i3);
                        UiMgr.this.getMsgMgr(context).updateSettings(i, i2, i3);
                        UiMgr.this.getMsgMgr(context).updateRightViewItem(i3 == 1);
                        break;
                    case 6:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 5, (byte) (i3 + 1)});
                        break;
                    case 7:
                        UiMgr.this.m0x85Data[2] = (byte) (i3 + 1);
                        CanbusMsgSender.sendMsg(UiMgr.this.m0x85Data);
                        UiMgr.this.getMsgMgr(context).updateSettings(i, i2, i3);
                        break;
                    case '\b':
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 50, (byte) (i3 + 1)});
                        break;
                    case '\t':
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 25, (byte) (i3 + 1)});
                        break;
                    case '\n':
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 2, (byte) (i3 + 1)});
                        break;
                    case 11:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 3, (byte) (i3 + 1)});
                        break;
                    case '\f':
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 6, (byte) (i3 + 1)});
                        break;
                    case '\r':
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 7, (byte) (i3 + 1)});
                        break;
                    case 14:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 8, (byte) (i3 + 1)});
                        break;
                    case 15:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 9, (byte) (i3 + 1)});
                        break;
                    case 16:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 10, (byte) i3});
                        break;
                    case 17:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 11, (byte) i3});
                        break;
                    case 18:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) (i3 + 1)});
                        break;
                    case 19:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 14, (byte) (i3 + 1)});
                        break;
                    case 20:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 15, (byte) (i3 + 1)});
                        break;
                    case 21:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 17, (byte) (i3 + 1)});
                        break;
                    case 22:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 18, (byte) i3});
                        break;
                    case 23:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 19, (byte) (i3 + 1)});
                        break;
                    case 24:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 20, (byte) (i3 + 1)});
                        break;
                    case 25:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 21, (byte) (i3 + 1)});
                        break;
                    case 26:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 22, (byte) (i3 + 1)});
                        break;
                    case 27:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 23, (byte) (i3 + 1)});
                        break;
                    case 28:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 27, (byte) (i3 + 1)});
                        break;
                    case 29:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 28, (byte) i3});
                        break;
                    case 30:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 29, (byte) (i3 + 1)});
                        break;
                    case 31:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 30, (byte) i3});
                        break;
                    case ' ':
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 32, (byte) i3});
                        break;
                    case '!':
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, (byte) (i3 + 1)});
                        break;
                    case '\"':
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 34, (byte) (i3 + 1)});
                        break;
                    case '#':
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 35, (byte) (i3 + 1)});
                        break;
                    case '$':
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 36, (byte) (i3 + 1)});
                        break;
                    case '%':
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 37, (byte) (i3 + 1)});
                        break;
                    case '&':
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 38, (byte) (i3 + 1)});
                        break;
                    case '\'':
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 40, (byte) i3});
                        break;
                    case '(':
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 42, (byte) (i3 + 1)});
                        break;
                    case ')':
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 43, (byte) (i3 + 1)});
                        break;
                    case '*':
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 44, (byte) (i3 + 1)});
                        break;
                    case '+':
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 45, (byte) (i3 + 1)});
                        break;
                    case ',':
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 46, (byte) (i3 + 1)});
                        break;
                    case '-':
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 47, (byte) (i3 + 1)});
                        break;
                    case '.':
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 48, (byte) (i3 + 1)});
                        break;
                    case '/':
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 49, (byte) (i3 + 1)});
                        break;
                    case '0':
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 51, (byte) (i3 + 1)});
                        break;
                    case '1':
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 52, (byte) (i3 + 1)});
                        break;
                    case '2':
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 53, (byte) (i3 + 1)});
                        break;
                    case '3':
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 54, (byte) (i3 + 1)});
                        break;
                    case '4':
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 55, (byte) (i3 + 1)});
                        break;
                    case '5':
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 56, (byte) (i3 + 1)});
                        break;
                    case '6':
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 58, (byte) (i3 + 1)});
                        break;
                    case '7':
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 59, (byte) (i3 + 1)});
                        break;
                    case '8':
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 60, (byte) (i3 + 1)});
                        break;
                    case '9':
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 61, (byte) (i3 + 1)});
                        break;
                    case ':':
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 62, (byte) (i3 + 1)});
                        break;
                    case ';':
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 63, (byte) (i3 + 1)});
                        break;
                    case '<':
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 67, (byte) (i3 + 1)});
                        break;
                    case '=':
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 68, (byte) (i3 + 1)});
                        break;
                    case '>':
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 69, (byte) (i3 + 1)});
                        break;
                    case '?':
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 70, (byte) (i3 + 1)});
                        break;
                    case '@':
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 71, (byte) (i3 + 1)});
                        break;
                    case 'A':
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 72, (byte) (i3 + 1)});
                        break;
                    case 'B':
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 73, (byte) (i3 + 1)});
                        break;
                    case 'C':
                        SharePreUtil.setIntValue(context, UiMgr.SHARE_284_SUPPORT_PANORAMIC, i3);
                        UiMgr.this.getMsgMgr(context).updateSettings(i, i2, i3);
                        UiMgr.this.getMsgMgr(context).updatePanoramicItem(i3 == 1);
                        break;
                    case 'D':
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 16, (byte) (i3 + 1)});
                        break;
                    case 'E':
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 24, (byte) (i3 + 1)});
                        break;
                    case 'F':
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 4, (byte) (i3 + 1)});
                        break;
                    case 'G':
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 57, (byte) (i3 + 1)});
                        break;
                    case 'H':
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 31, (byte) (i3 + 1)});
                        break;
                    case 'I':
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 26, (byte) (i3 + 1)});
                        break;
                }
            }
        });
        final ParkPageUiSet parkPageUiSet = getParkPageUiSet(context);
        parkPageUiSet.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._284.UiMgr.6
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public void onClickItem(int i) {
                String titleSrn = parkPageUiSet.getPanoramicBtnList().get(i).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "_253_exit_panoramic":
                        CanbusMsgSender.sendMsg(new byte[]{22, -123, UiMgr.this.m0x85Data[2], (byte) (UiMgr.this.m0x85Data[3] | 64)});
                        break;
                    case "_284_panoramic_status_10":
                        CanbusMsgSender.sendMsg(new byte[]{22, -89, 16});
                        break;
                    case "_284_panoramic_status_1":
                        CanbusMsgSender.sendMsg(new byte[]{22, -89, 1});
                        break;
                    case "_284_panoramic_status_2":
                        CanbusMsgSender.sendMsg(new byte[]{22, -89, 3});
                        break;
                    case "_284_panoramic_status_3":
                        CanbusMsgSender.sendMsg(new byte[]{22, -89, 4});
                        break;
                    case "_284_panoramic_status_4":
                        CanbusMsgSender.sendMsg(new byte[]{22, -89, 2});
                        break;
                    case "_284_panoramic_status_6":
                        CanbusMsgSender.sendMsg(new byte[]{22, -89, 17});
                        break;
                    case "_284_panoramic_status_7":
                        CanbusMsgSender.sendMsg(new byte[]{22, -89, 5});
                        break;
                    case "_284_panoramic_status_9":
                        CanbusMsgSender.sendMsg(new byte[]{22, -89, 18});
                        break;
                }
            }
        });
        parkPageUiSet.setOnBackCameraStatusListener(new OnBackCameraStatusListener() { // from class: com.hzbhd.canbus.car._284.UiMgr.7
            @Override // com.hzbhd.canbus.interfaces.OnBackCameraStatusListener
            public void addViewToWindows() {
                boolean zIsPanoramic = CommUtil.isPanoramic(context);
                parkPageUiSet.setShowRadar(!zIsPanoramic);
                parkPageUiSet.setShowPanoramic(zIsPanoramic);
            }
        });
        AirPageUiSet airUiSet = getAirUiSet(context);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerTop, this.mOnAirBtnClickListenerLeft, this.mOnAirBtnClickListenerRight, this.mOnAirBtnClickListenerBottom});
        FrontArea frontArea = airUiSet.getFrontArea();
        OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListener = this.mOnUpDownClickListener;
        frontArea.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{onAirTemperatureUpDownClickListener, null, onAirTemperatureUpDownClickListener});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._284.UiMgr.8
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                byte[] bArrCopyOf = Arrays.copyOf(UiMgr.this.m0x86Data, UiMgr.this.m0x86Data.length);
                bArrCopyOf[3] = (byte) DataHandleUtils.setIntByteWithBit(0, 0, true);
                UiMgr.this.sendAirCommand(bArrCopyOf);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                byte[] bArrCopyOf = Arrays.copyOf(UiMgr.this.m0x86Data, UiMgr.this.m0x86Data.length);
                bArrCopyOf[3] = (byte) DataHandleUtils.setIntByteWithBit(0, 1, true);
                UiMgr.this.sendAirCommand(bArrCopyOf);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirCommand(int i, int i2) {
        byte[] bArrCopyOf;
        String str = getAirUiSet(this.mContext).getFrontArea().getLineBtnAction()[i][i2];
        byte[] bArr = this.m0x86Data;
        bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
        str.hashCode();
        switch (str) {
            case "front_defog":
                bArrCopyOf[2] = (byte) DataHandleUtils.setIntByteWithBit(0, 4, true);
                break;
            case "ac_max":
                bArrCopyOf[2] = (byte) DataHandleUtils.setIntByteWithBit(0, 0, true);
                break;
            case "rear_defog":
                bArrCopyOf[3] = (byte) DataHandleUtils.setIntByteWithBit(0, 2, true);
                break;
            case "blow_negative":
                bArrCopyOf[2] = (byte) DataHandleUtils.setIntByteWithBit(0, 6, true);
                break;
            case "ac":
                bArrCopyOf[2] = (byte) DataHandleUtils.setIntByteWithBit(0, 1, true);
                break;
            case "auto":
                bArrCopyOf[2] = (byte) DataHandleUtils.setIntByteWithBit(0, 5, true);
                break;
            case "rear":
                bArrCopyOf[7] = (byte) DataHandleUtils.setIntByteWithBit(0, 2, true);
                break;
            case "sync":
                bArrCopyOf[3] = (byte) DataHandleUtils.setIntByteWithBit(0, 3, true);
                break;
            case "power":
                bArrCopyOf[2] = (byte) DataHandleUtils.setIntByteWithBit(0, 7, true);
                break;
            case "in_out_cycle":
                bArrCopyOf[2] = (byte) DataHandleUtils.setIntByteWithBit(0, GeneralAirData.in_out_cycle ? 2 : 3, true);
                break;
            case "blow_head_foot":
                bArrCopyOf[4] = (byte) DataHandleUtils.setIntByteWithBit(0, 2, true);
                break;
            case "blow_foot":
                bArrCopyOf[4] = (byte) DataHandleUtils.setIntByteWithBit(0, 3, true);
                break;
            case "blow_head":
                bArrCopyOf[4] = (byte) DataHandleUtils.setIntByteWithBit(0, 1, true);
                break;
            case "blow_window_foot":
                bArrCopyOf[4] = (byte) DataHandleUtils.setIntByteWithBit(0, 4, true);
                break;
        }
        sendAirCommand(bArrCopyOf);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirCommand(byte[] bArr) {
        CanbusMsgSender.sendMsg(bArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }
}
