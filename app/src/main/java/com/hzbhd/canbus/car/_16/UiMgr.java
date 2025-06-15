package com.hzbhd.canbus.car._16;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.car._16.UiMgr;
import com.hzbhd.canbus.interfaces.OnAmplifierCreateAndDestroyListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnHybirdPageStatusListener;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AirBtnAction;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.Iterator;
import java.util.List;
import kotlin.text.Typography;
import nfore.android.bt.res.NfDef;


public class UiMgr extends AbstractUiMgr {
    private int can_different_id;
    private int each_can_id;
    private String[] mAirBtnListFrontBottom;
    private String[] mAirBtnListFrontLeft;
    private String[] mAirBtnListFrontRight;
    private String[] mAirBtnListFrontTop;
    private String[] mAirBtnListRearBottom;
    private Context mContext;
    MsgMgr mMsgMgr;
    private OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
    private SettingPageUiSet mSettingPageUiSet;
    private int screenHeight;
    private int screenWidth;
    private int mMinuteFuelLeft = 5;
    private int mHistoricalLeft = 6;
    OnPanoramicItemTouchListener mOnPanoramicItemTouchListener = new OnPanoramicItemTouchListener() { // from class: com.hzbhd.canbus.car._16.UiMgr.6
        @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener
        public void onTouchItem(MotionEvent motionEvent) {
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            int i = (x * 1023) / UiMgr.this.screenWidth;
            int i2 = (y * 1000) / UiMgr.this.screenHeight;
            byte b = (byte) (i & 255);
            byte b2 = (byte) ((i >> 8) & 255);
            byte b3 = (byte) (i2 & 255);
            byte b4 = (byte) ((i2 >> 8) & 255);
            if (motionEvent.getAction() == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -104, 1, b2, b, b4, b3, 0});
            } else if (motionEvent.getAction() == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -104, 0, b2, b, b4, b3, 0});
            }
        }
    };
    OnOriginalSongItemClickListener mOnOriginalSongItemClickListener = new OnOriginalSongItemClickListener() { // from class: com.hzbhd.canbus.car._16.UiMgr.7
        @Override // com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener
        public void onSongItemClick(int i) {
            if (MsgMgr.mOriginalInt == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -123, 16, (byte) (i + 1)});
            } else if (MsgMgr.mOriginalInt == 2) {
                CanbusMsgSender.sendMsg(new byte[]{22, -123, 8, (byte) (i + 1)});
            }
        }

        @Override // com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener
        public void onSongItemLongClick(int i) {
            if (MsgMgr.mOriginalInt == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -123, 17, (byte) (i + 1)});
            }
        }
    };
    OnOriginalBottomBtnClickListener mOnOriginalBottomBtnClickListener = new AnonymousClass8();
    OnOriginalTopBtnClickListener mOnOriginalTopBtnClickListener = new OnOriginalTopBtnClickListener() { // from class: com.hzbhd.canbus.car._16.UiMgr.9
        @Override // com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener
        public void onClickTopBtnItem(int i) {
            String str = UiMgr.this.mOriginalCarDevicePageUiSet.getRowTopBtnAction()[i];
            str.hashCode();
            switch (str) {
                case "random":
                    CanbusMsgSender.sendMsg(new byte[]{22, -123, 1, 0});
                    break;
                case "repeat":
                    CanbusMsgSender.sendMsg(new byte[]{22, -123, 2, 0});
                    break;
                case "lock":
                    if (!MsgMgr.DeviceRearLock) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 97, 1});
                        break;
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 97, 0});
                        break;
                    }
                case "power":
                    if (!MsgMgr.DevicePower) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 96, 1});
                        break;
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 96, 0});
                        break;
                    }
            }
        }
    };
    OnAmplifierSeekBarListener mOnAmplifierSeekBarListener = new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._16.UiMgr.10
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
        public void progress(AmplifierActivity.AmplifierBand amplifierBand, int i) {
            if (amplifierBand == AmplifierActivity.AmplifierBand.VOLUME) {
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 7, (byte) i});
                return;
            }
            if (amplifierBand == AmplifierActivity.AmplifierBand.BASS) {
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 4, (byte) (i + 2)});
            } else if (amplifierBand == AmplifierActivity.AmplifierBand.MIDDLE) {
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 6, (byte) (i + 2)});
            } else if (amplifierBand == AmplifierActivity.AmplifierBand.TREBLE) {
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 5, (byte) (i + 2)});
            }
        }
    };
    OnAmplifierPositionListener mOnAmplifierPositionListener = new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._16.UiMgr.11
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
        public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
            if (amplifierPosition == AmplifierActivity.AmplifierPosition.LEFT_RIGHT) {
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 2, (byte) (i + 7)});
            } else if (amplifierPosition == AmplifierActivity.AmplifierPosition.FRONT_REAR) {
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, (byte) ((-i) + 7)});
            }
        }
    };
    OnSettingItemSeekbarSelectListener mOnSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._16.UiMgr.12
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
        public void onClickItem(int i, int i2, int i3) {
            String titleSrn = UiMgr.this.mSettingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
            titleSrn.hashCode();
            if (titleSrn.equals("hiworld_jeep_123_0x60_data1_65")) {
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 5, (byte) i3});
            } else if (titleSrn.equals("radar_volume")) {
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 21, (byte) i3});
            }
        }
    };
    OnSettingItemClickListener mOnSettingItemClickListener = new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._16.UiMgr.13
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
        public void onClickItem(int i, int i2) {
            String titleSrn = UiMgr.this.mSettingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
            String titleSrn2 = UiMgr.this.mSettingPageUiSet.getList().get(i).getTitleSrn();
            titleSrn.hashCode();
            if (!titleSrn.equals("clear_data")) {
                if (titleSrn.equals("update_data")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 8, 0});
                    return;
                }
                return;
            }
            titleSrn2.hashCode();
            if (titleSrn2.equals("_55_0x6A_0x04_0x02")) {
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 9, 0});
            } else if (titleSrn2.equals("_16_title_17")) {
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 10, 0});
            }
        }
    };
    OnSettingItemSelectListener mOnSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._16.UiMgr.14
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            String titleSrn = UiMgr.this.mSettingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
            titleSrn.hashCode();
            char c = 65535;
            switch (titleSrn.hashCode()) {
                case -2116235428:
                    if (titleSrn.equals("_55_0x65_data1_bit21")) {
                        c = 0;
                        break;
                    }
                    break;
                case -1830876700:
                    if (titleSrn.equals("front_radar_distance")) {
                        c = 1;
                        break;
                    }
                    break;
                case -1091778295:
                    if (titleSrn.equals("rear_radar_distance")) {
                        c = 2;
                        break;
                    }
                    break;
                case -902681199:
                    if (titleSrn.equals("_18_vehicle_setting_item_1_0")) {
                        c = 3;
                        break;
                    }
                    break;
                case -902681198:
                    if (titleSrn.equals("_18_vehicle_setting_item_1_1")) {
                        c = 4;
                        break;
                    }
                    break;
                case -902681197:
                    if (titleSrn.equals("_18_vehicle_setting_item_1_2")) {
                        c = 5;
                        break;
                    }
                    break;
                case -902681196:
                    if (titleSrn.equals("_18_vehicle_setting_item_1_3")) {
                        c = 6;
                        break;
                    }
                    break;
                case -902681195:
                    if (titleSrn.equals("_18_vehicle_setting_item_1_4")) {
                        c = 7;
                        break;
                    }
                    break;
                case -902681194:
                    if (titleSrn.equals("_18_vehicle_setting_item_1_5")) {
                        c = '\b';
                        break;
                    }
                    break;
                case -902680234:
                    if (titleSrn.equals("_18_vehicle_setting_item_2_4")) {
                        c = '\t';
                        break;
                    }
                    break;
                case -902680233:
                    if (titleSrn.equals("_18_vehicle_setting_item_2_5")) {
                        c = '\n';
                        break;
                    }
                    break;
                case -902680232:
                    if (titleSrn.equals("_18_vehicle_setting_item_2_6")) {
                        c = 11;
                        break;
                    }
                    break;
                case -902680231:
                    if (titleSrn.equals("_18_vehicle_setting_item_2_7")) {
                        c = '\f';
                        break;
                    }
                    break;
                case -796889975:
                    if (titleSrn.equals("radar_display")) {
                        c = '\r';
                        break;
                    }
                    break;
                case -723657951:
                    if (titleSrn.equals("_11_0x26_data2_bit20")) {
                        c = 14;
                        break;
                    }
                    break;
                case -556746441:
                    if (titleSrn.equals("hiworld_jeep_123_0xC1_data2")) {
                        c = 15;
                        break;
                    }
                    break;
                case 97333442:
                    if (titleSrn.equals("amplifier_switch")) {
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
                case 163845763:
                    if (titleSrn.equals("_11_0x26_data3_bit32")) {
                        c = 18;
                        break;
                    }
                    break;
                case 426787759:
                    if (titleSrn.equals("_16_Seat_sliding")) {
                        c = 19;
                        break;
                    }
                    break;
                case 957932200:
                    if (titleSrn.equals("light_ctrl_3")) {
                        c = 20;
                        break;
                    }
                    break;
                case 957932201:
                    if (titleSrn.equals("light_ctrl_4")) {
                        c = 21;
                        break;
                    }
                    break;
                case 1051349540:
                    if (titleSrn.equals("_11_0x26_data4_bit65")) {
                        c = 22;
                        break;
                    }
                    break;
                case 1475566907:
                    if (titleSrn.equals("_55_0x65_data1_bit54_item1")) {
                        c = 23;
                        break;
                    }
                    break;
                case 1591925886:
                    if (titleSrn.equals("_55_0x67_data1_bit32")) {
                        c = 24;
                        break;
                    }
                    break;
                case 1845541289:
                    if (titleSrn.equals("_16_add_setting_1")) {
                        c = 25;
                        break;
                    }
                    break;
                case 1845541290:
                    if (titleSrn.equals("_16_add_setting_2")) {
                        c = 26;
                        break;
                    }
                    break;
                case 1845541291:
                    if (titleSrn.equals("_16_add_setting_3")) {
                        c = 27;
                        break;
                    }
                    break;
                case 1845541292:
                    if (titleSrn.equals("_16_add_setting_4")) {
                        c = 28;
                        break;
                    }
                    break;
                case 1845541293:
                    if (titleSrn.equals("_16_add_setting_5")) {
                        c = 29;
                        break;
                    }
                    break;
                case 2015341706:
                    if (titleSrn.equals("_16_title_19")) {
                        c = 30;
                        break;
                    }
                    break;
                case 2015341728:
                    if (titleSrn.equals("_16_title_20")) {
                        c = 31;
                        break;
                    }
                    break;
                case 2015341730:
                    if (titleSrn.equals("_16_title_22")) {
                        c = ' ';
                        break;
                    }
                    break;
                case 2015341731:
                    if (titleSrn.equals("_16_title_23")) {
                        c = '!';
                        break;
                    }
                    break;
                case 2081713660:
                    if (titleSrn.equals("_18_vehicle_setting_item_3_43")) {
                        c = Typography.quote;
                        break;
                    }
                    break;
                case 2081713724:
                    if (titleSrn.equals("_18_vehicle_setting_item_3_65")) {
                        c = '#';
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 20, (byte) i3});
                    break;
                case 1:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 23, (byte) (i3 + 2)});
                    break;
                case 2:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 23, (byte) i3});
                    break;
                case 3:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 19, (byte) i3});
                    break;
                case 4:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 18, (byte) i3});
                    break;
                case 5:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 1, (byte) i3});
                    break;
                case 6:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 2, (byte) i3});
                    break;
                case 7:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 14, (byte) i3});
                    break;
                case '\b':
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 15, (byte) i3});
                    break;
                case '\t':
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) i3});
                    break;
                case '\n':
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 16, (byte) i3});
                    break;
                case 11:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 3, (byte) i3});
                    break;
                case '\f':
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 17, (byte) i3});
                    break;
                case '\r':
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 22, (byte) i3});
                    break;
                case 14:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 35, (byte) i3});
                    break;
                case 15:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 36, (byte) i3});
                    break;
                case 16:
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 8, (byte) i3});
                    break;
                case 17:
                    if (i3 <= 10) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 38, (byte) i3});
                        break;
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 38, (byte) (i3 + 5)});
                        break;
                    }
                case 18:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 34, (byte) i3});
                    break;
                case 19:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 29, (byte) i3});
                    break;
                case 20:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 4, (byte) i3});
                    break;
                case 21:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 6, (byte) i3});
                    break;
                case 22:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 37, (byte) i3});
                    break;
                case 23:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 0, (byte) i3});
                    break;
                case 24:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 7, (byte) i3});
                    break;
                case 25:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 28, (byte) i3});
                    break;
                case 26:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 25, (byte) i3});
                    break;
                case 27:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 26, (byte) (i3 + 3)});
                    break;
                case 28:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 27, (byte) (i3 + 3)});
                    break;
                case 29:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 24, (byte) (i3 + 1)});
                    break;
                case 30:
                    byte[] bArr = new byte[4];
                    bArr[0] = 22;
                    bArr[1] = -125;
                    bArr[2] = 48;
                    bArr[3] = (byte) DataHandleUtils.setIntByteWithBit(0, 7, i3 == 1);
                    CanbusMsgSender.sendMsg(bArr);
                    break;
                case 31:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 48, (byte) i3});
                    break;
                case ' ':
                    if (i3 != 0) {
                        if (i3 == 1) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, 8});
                            break;
                        }
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, 1});
                        break;
                    }
                    break;
                case '!':
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 9, (byte) i3});
                    break;
                case '\"':
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) i3});
                    break;
                case '#':
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) (i3 + 4)});
                    break;
            }
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_16_title_21")) {
                UiMgr uiMgr2 = UiMgr.this;
                if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "_16_title_21", "_16_ampl_settings_Position")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) i3});
                }
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._16.UiMgr.15
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            Log.d("ZhouRui", "拿到的索引值=" + i);
            Log.d("ZhouRui", "拿到的功能值=" + UiMgr.this.mAirBtnListFrontTop[i]);
            UiMgr uiMgr = UiMgr.this;
            uiMgr.sendAirCommand(uiMgr.mAirBtnListFrontTop[i]);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._16.UiMgr.16
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.sendAirCommand(uiMgr.mAirBtnListFrontLeft[i]);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._16.UiMgr.17
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.sendAirCommand(uiMgr.mAirBtnListFrontRight[i]);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._16.UiMgr.18
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.sendAirCommand(uiMgr.mAirBtnListFrontBottom[i]);
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._16.UiMgr.19
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            if (UiMgr.this.isPortrait()) {
                UiMgr.this.sendAirCommand(3);
            } else {
                UiMgr.this.sendAirCommandC7(3, 1);
            }
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            if (UiMgr.this.isPortrait()) {
                UiMgr.this.sendAirCommand(2);
            } else {
                UiMgr.this.sendAirCommandC7(3, 0);
            }
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._16.UiMgr.20
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            if (UiMgr.this.isPortrait()) {
                UiMgr.this.sendAirCommand(5);
            } else {
                UiMgr.this.sendAirCommandC7(4, 1);
            }
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            if (UiMgr.this.isPortrait()) {
                UiMgr.this.sendAirCommand(4);
            } else {
                UiMgr.this.sendAirCommandC7(4, 0);
            }
        }
    };
    OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatFrontLeft = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._16.UiMgr.21
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
            UiMgr.this.sendAirCommand(12);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr.this.sendAirCommand(11);
        }
    };
    OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatFrontRight = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._16.UiMgr.22
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
            UiMgr.this.sendAirCommand(14);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr.this.sendAirCommand(13);
        }
    };
    OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerColdFrontLeft = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._16.UiMgr.23
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr.this.sendAirCommand(12);
        }
    };
    OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerColdFrontRight = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._16.UiMgr.24
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr.this.sendAirCommand(14);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerRearBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._16.UiMgr.25
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.sendRearAirCommand(uiMgr.mAirBtnListRearBottom[i]);
        }
    };
    OnAirWindSpeedUpDownClickListener mOnAirWindSpeedUpDownClickListenerFront = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._16.UiMgr.26
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            if (UiMgr.this.isPortrait()) {
                UiMgr.this.sendAirCommand(9);
            } else {
                UiMgr.this.sendAirCommandC7(1, 0);
            }
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            if (UiMgr.this.isPortrait()) {
                UiMgr.this.sendAirCommand(10);
            } else {
                UiMgr.this.sendAirCommandC7(1, 1);
            }
        }
    };
    OnAirWindSpeedUpDownClickListener mOnAirWindSpeedUpDownClickListenerRear = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._16.UiMgr.27
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            UiMgr.this.sendAirCommand(40);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            UiMgr.this.sendAirCommand(41);
        }
    };
    OnAirSeatClickListener mOnRearAirSeatClickListener = new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._16.UiMgr.28
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onLeftSeatClick() {
            UiMgr.this.sendAirCommand(43);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onRightSeatClick() {
            UiMgr.this.sendAirCommand(43);
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerRearLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._16.UiMgr.29
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirCommand(39);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirCommand(38);
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerRearRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._16.UiMgr.30
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirCommand(66);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirCommand(65);
        }
    };

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        if (SharePreUtil.getBoolValue(this.mContext, "share_16_is_suppot_tire", true)) {
            return;
        }
        removeMainPrjBtnByName(context, MainAction.TIRE_INFO);
    }

    public UiMgr(Context context) {
        this.mContext = context;
        initUI(context);
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        this.mSettingPageUiSet = settingUiSet;
        settingUiSet.setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
        this.mSettingPageUiSet.setOnSettingItemClickListener(this.mOnSettingItemClickListener);
        this.mSettingPageUiSet.setOnSettingItemSeekbarSelectListener(this.mOnSettingItemSeekbarSelectListener);
        this.mSettingPageUiSet.setOnSettingPageStatusListener(new OnSettingPageStatusListener() { // from class: com.hzbhd.canbus.car._16.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnSettingPageStatusListener
            public void onStatusChange(int i) {
                UiMgr.this.activeRequest(50, 0);
                UiMgr.this.activeRequest(49, 0);
                UiMgr.this.activeRequest(80, 0);
                UiMgr.this.activeRequest(30, 0);
                UiMgr.this.activeRequest(38, 0);
                UiMgr.this.activeRequest(82, 0);
                UiMgr.this.activeRequest(80, 0);
                UiMgr.this.activeRequest(35, 0);
            }
        });
        AirPageUiSet airUiSet = getAirUiSet(context);
        String[][] lineBtnAction = airUiSet.getFrontArea().getLineBtnAction();
        this.mAirBtnListFrontTop = lineBtnAction[0];
        this.mAirBtnListFrontLeft = lineBtnAction[1];
        this.mAirBtnListFrontRight = lineBtnAction[2];
        this.mAirBtnListFrontBottom = lineBtnAction[3];
        this.mAirBtnListRearBottom = airUiSet.getRearArea().getLineBtnAction()[3];
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
        airUiSet.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.mOnMinPlusClickListenerHeatFrontLeft, this.mOnMinPlusClickListenerHeatFrontRight, this.mOnMinPlusClickListenerColdFrontLeft, this.mOnMinPlusClickListenerColdFrontRight});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.mOnAirWindSpeedUpDownClickListenerFront);
        airUiSet.getRearArea().setSetWindSpeedViewOnClickListener(this.mOnAirWindSpeedUpDownClickListenerRear);
        airUiSet.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerRearLeft, null, this.mOnUpDownClickListenerRearRight});
        airUiSet.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, this.mOnAirBtnClickListenerRearBottom});
        airUiSet.getRearArea().setOnAirSeatClickListener(this.mOnRearAirSeatClickListener);
        airUiSet.getFrontArea().setOnAirPageStatusListener(new OnAirPageStatusListener() { // from class: com.hzbhd.canbus.car._16.UiMgr.2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener
            public void onStatusChange(int i) {
                UiMgr.this.activeRequest(40, 0);
                UiMgr.this.activeRequest(88, 0);
            }
        });
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        amplifierPageUiSet.setOnAmplifierSeekBarListener(this.mOnAmplifierSeekBarListener);
        amplifierPageUiSet.setOnAmplifierPositionListener(this.mOnAmplifierPositionListener);
        amplifierPageUiSet.setOnAmplifierCreateAndDestroyListener(new OnAmplifierCreateAndDestroyListener() { // from class: com.hzbhd.canbus.car._16.UiMgr.3
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierCreateAndDestroyListener
            public void destroy() {
            }

            @Override // com.hzbhd.canbus.interfaces.OnAmplifierCreateAndDestroyListener
            public void create() {
                UiMgr.this.activeRequest(49, 0);
            }
        });
        OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(context);
        this.mOriginalCarDevicePageUiSet = originalCarDevicePageUiSet;
        originalCarDevicePageUiSet.setOnOriginalTopBtnClickListeners(this.mOnOriginalTopBtnClickListener);
        this.mOriginalCarDevicePageUiSet.setOnOriginalBottomBtnClickListeners(this.mOnOriginalBottomBtnClickListener);
        this.mOriginalCarDevicePageUiSet.setOnOriginalSongItemClickListener(this.mOnOriginalSongItemClickListener);
        this.screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        this.screenHeight = context.getResources().getDisplayMetrics().heightPixels;
        getParkPageUiSet(context).setOnPanoramicItemTouchListener(this.mOnPanoramicItemTouchListener);
        getDriverDataPageUiSet(this.mContext).setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener() { // from class: com.hzbhd.canbus.car._16.UiMgr.4
            @Override // com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener
            public void onStatusChange(int i) {
                UiMgr.this.activeRequest(34, 0);
                UiMgr.this.activeRequest(33, 0);
                UiMgr.this.activeRequest(43, 0);
                UiMgr.this.activeRequest(39, 0);
                UiMgr.this.activeRequest(36, 0);
                UiMgr.this.activeRequest(35, 0);
            }
        });
        getHybirdPageUiSet(context).setOnHybirdPageStatusListener(new OnHybirdPageStatusListener() { // from class: com.hzbhd.canbus.car._16.UiMgr.5
            @Override // com.hzbhd.canbus.interfaces.OnHybirdPageStatusListener
            public void onStatusChange(int i) {
                CanbusMsgSender.sendMsg(new byte[]{22, -112, 31, 0});
            }
        });
    }

    private void initUI(Context context) {
        this.can_different_id = getCurrentCarId();
        this.each_can_id = ((MsgMgr) MsgMgrFactory.getCanMsgMgr(context)).getEachCanId();
        if (!isCHR_EV()) {
            removeDriveData(context, "_16_min_title_2");
        }
        int i = this.can_different_id;
        if (i == 4 || i == 5) {
            removeMainPrjBtnByName(context, MainAction.DRIVE_DATA);
        } else if (i == 6 || i == 7 || i == 8) {
            removeDriveDateItemForTitles(context, new String[]{"_16_parking_brake", "_16_ig", "_16_door_move"});
        }
        int i2 = this.can_different_id;
        if (i2 == 4 || i2 == 6 || i2 == 7 || i2 == 8) {
            removeMainPrjBtnByName(context, MainAction.TIRE_INFO);
        }
        if (!hasAmplifier()) {
            removeMainPrjBtnByName(context, MainAction.AMPLIFIER);
            removeSettingLeftItemByNameList(context, new String[]{"_16_title_21"});
        }
        int i3 = this.can_different_id;
        if (i3 == 4 || i3 == 5 || i3 == 6 || i3 == 7 || i3 == 8) {
            removeSettingLeftItemByNameList(context, new String[]{"light_settings", "lock_settings", "airSetting", "other_set", "radar_settings"});
        }
        if (!isHybrid()) {
            removeMainPrjBtnByName(context, MainAction.HYBIRD);
        }
        if (!hasOriginalCarDevice()) {
            removeMainPrjBtnByName(context, MainAction.ORIGINAL_CAR_DEVICE);
        }
        if (this.can_different_id == 5) {
            removeMainPrjBtnByName(context, "air");
        }
        int i4 = this.can_different_id;
        if (i4 != 3 && i4 != 4 && i4 != 9 && this.each_can_id != 58) {
            Log.d("ZhouRui", "移除negative_ion");
            getAirUiSet(context).getFrontArea().setShowSeatHeat(false);
            getAirUiSet(context).getFrontArea().setShowSeatCold(false);
            removeFrontAirButtonByName(context, AirBtnAction.NEGATIVE_ION);
            removeFrontAirButtonByName(context, AirBtnAction.SWING);
            removeFrontAirButtonByName(context, AirBtnAction.POLLRN_REMOVAL);
            removeFrontAirButtonByName(context, AirBtnAction.FRONT_WINDOW_HEAT);
        }
        if (this.can_different_id == 3) {
            getAirUiSet(context).getFrontArea().setDisableBtnArray(new String[]{AirBtnAction.NEGATIVE_ION, AirBtnAction.POLLRN_REMOVAL});
        }
        if (this.can_different_id != 4) {
            getAirUiSet(context).getFrontArea().setDisableBtnArray(new String[]{AirBtnAction.SWING, AirBtnAction.FRONT_WINDOW_HEAT});
        }
        if (this.each_can_id == 58) {
            getAirUiSet(context).getFrontArea().setDisableBtnArray(new String[]{AirBtnAction.NEGATIVE_ION, AirBtnAction.POLLRN_REMOVAL, AirBtnAction.SWING, AirBtnAction.FRONT_WINDOW_HEAT});
        }
        if (this.can_different_id != 9) {
            removeFrontAirButtonByName(context, AirBtnAction.ECON);
            removeRearAirButtonByName(context, AirBtnAction.REAR);
        }
    }

    private boolean hasOriginalCarDevice() {
        int i = this.can_different_id;
        return i == 4 || i == 9 || this.each_can_id == 33;
    }

    private boolean isHybrid() {
        int i = this.each_can_id;
        return i == 30 || i == 31 || i == 32 || i == 19 || i == 1 || i == 67;
    }

    public boolean hasAmplifierNoSettings() {
        int i = this.can_different_id;
        return i == 4 || i == 6 || i == 7 || i == 8;
    }

    public boolean hasAmplifier() {
        int i;
        int i2 = this.each_can_id;
        return i2 == 6 || i2 == 12 || i2 == 14 || (i = this.can_different_id) == 4 || i == 6 || i == 7 || i == 8 || i == 9 || i2 == 67;
    }

    private boolean isCHR_EV() {
        return this.can_different_id == 3 && this.each_can_id == 19;
    }

    /* renamed from: com.hzbhd.canbus.car._16.UiMgr$8, reason: invalid class name */
    class AnonymousClass8 implements OnOriginalBottomBtnClickListener {
        AnonymousClass8() {
        }

        @Override // com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener
        public void onClickBottomBtnItem(int i) {
            String str = UiMgr.this.mOriginalCarDevicePageUiSet.getRowBottomBtnAction()[i];
            str.hashCode();
            switch (str) {
                case "random":
                    if (MsgMgr.mOriginalInt == 3) {
                        UiMgr.this.sendUsbCmd(1, 0);
                        break;
                    } else if (MsgMgr.mOriginalInt == 2) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -123, 1, 0});
                        break;
                    }
                    break;
                case "repeat":
                    CanbusMsgSender.sendMsg(new byte[]{22, -123, 2, 0});
                    break;
                case "prev_disc":
                    if (MsgMgr.mOriginalInt != 2) {
                        if (MsgMgr.mOriginalInt == 3) {
                            UiMgr.this.sendUsbCmd(7, 0);
                            break;
                        }
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -123, 7, 0});
                        break;
                    }
                    break;
                case "up":
                    if (MsgMgr.mOriginalInt != 1) {
                        if (MsgMgr.mOriginalInt != 2) {
                            if (MsgMgr.mOriginalInt == 3) {
                                UiMgr.this.sendUsbCmd(5, 0);
                                break;
                            }
                        } else {
                            CanbusMsgSender.sendMsg(new byte[]{22, -123, 5, 0});
                            break;
                        }
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -123, 22, 0});
                        break;
                    }
                    break;
                case "down":
                    if (MsgMgr.mOriginalInt != 1) {
                        if (MsgMgr.mOriginalInt != 2) {
                            if (MsgMgr.mOriginalInt == 3) {
                                UiMgr.this.sendUsbCmd(4, 0);
                                break;
                            }
                        } else {
                            CanbusMsgSender.sendMsg(new byte[]{22, -123, 4, 0});
                            break;
                        }
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -123, 21, 0});
                        break;
                    }
                    break;
                case "left":
                    if (MsgMgr.mOriginalInt != 1) {
                        if (MsgMgr.mOriginalInt == 2) {
                            new Thread(new Runnable() { // from class: com.hzbhd.canbus.car._16.UiMgr$8$$ExternalSyntheticLambda0
                                @Override // java.lang.Runnable
                                public final void run() throws InterruptedException {
                                    UiMgr.AnonymousClass8.lambda$onClickBottomBtnItem$0();
                                }
                            }).start();
                            break;
                        } else if (MsgMgr.mOriginalInt == 3) {
                            UiMgr.this.sendUsbCmd(10, 0);
                            break;
                        }
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -123, 18, 0});
                        break;
                    }
                    break;
                case "mode":
                    if (MsgMgr.mOriginalInt == 2) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 98, 1});
                        break;
                    }
                    break;
                case "stop":
                    if (MsgMgr.mOriginalInt == 2) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 99, 1});
                        break;
                    }
                    break;
                case "cycle":
                    if (MsgMgr.mOriginalInt == 3) {
                        UiMgr.this.sendUsbCmd(2, 0);
                        break;
                    }
                    break;
                case "right":
                    if (MsgMgr.mOriginalInt != 1) {
                        if (MsgMgr.mOriginalInt == 2) {
                            new Thread(new Runnable() { // from class: com.hzbhd.canbus.car._16.UiMgr$8$$ExternalSyntheticLambda1
                                @Override // java.lang.Runnable
                                public final void run() throws InterruptedException {
                                    UiMgr.AnonymousClass8.lambda$onClickBottomBtnItem$1();
                                }
                            }).start();
                            break;
                        } else if (MsgMgr.mOriginalInt == 3) {
                            UiMgr.this.sendUsbCmd(9, 0);
                            break;
                        }
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -123, 19, 0});
                        break;
                    }
                    break;
                case "next_disc":
                    if (MsgMgr.mOriginalInt != 2) {
                        if (MsgMgr.mOriginalInt == 3) {
                            UiMgr.this.sendUsbCmd(6, 0);
                            break;
                        }
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -123, 6, 0});
                        break;
                    }
                    break;
                case "play_pause":
                    if (MsgMgr.mOriginalInt != 1) {
                        if (MsgMgr.mOriginalInt != 2) {
                            if (MsgMgr.mOriginalInt != 3) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -124, 100, 1});
                                break;
                            } else {
                                UiMgr.this.sendUsbCmd(3, 0);
                                break;
                            }
                        } else {
                            CanbusMsgSender.sendMsg(new byte[]{22, -123, 3, 0});
                            break;
                        }
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -123, 20, 0});
                        break;
                    }
            }
        }

        static /* synthetic */ void lambda$onClickBottomBtnItem$0() throws InterruptedException {
            CanbusMsgSender.sendMsg(new byte[]{22, -123, 10, 1});
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            CanbusMsgSender.sendMsg(new byte[]{22, -123, 10, 0});
        }

        static /* synthetic */ void lambda$onClickBottomBtnItem$1() throws InterruptedException {
            CanbusMsgSender.sendMsg(new byte[]{22, -123, 9, 1});
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            CanbusMsgSender.sendMsg(new byte[]{22, -123, 9, 0});
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendRearAirCommand(String str) {
        str.hashCode();
        switch (str) {
            case "rear_auto":
                sendAirCommand(45);
                break;
            case "rear_power":
                sendAirCommand(44);
                break;
            case "rear":
                sendAirCommandC7(4, 3);
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void activeRequest(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -112, (byte) i, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00d3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void sendAirCommand(java.lang.String r11) {
        /*
            Method dump skipped, instructions count: 556
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._16.UiMgr.sendAirCommand(java.lang.String):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendUsbCmd(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -123, (byte) i, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.hzbhd.canbus.car._16.UiMgr$31] */
    public void sendAirCommand(final int i) {
        new Thread() { // from class: com.hzbhd.canbus.car._16.UiMgr.31
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                CanbusMsgSender.sendMsg(new byte[]{22, -32, (byte) i, 1});
                try {
                    sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -32, (byte) i, 0});
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.hzbhd.canbus.car._16.UiMgr$32] */
    public void sendAirCommandC7(int i, int i2) {
        final int intByteWithBit;
        final int intByteWithBit2;
        final int intByteWithBit3;
        final int intByteWithBit4;
        final int intByteWithBit5;
        final int intByteWithBit6;
        final int intByteWithBit7;
        final int intByteWithBit8;
        final int intByteWithBit9;
        final int intByteWithBit10;
        if (i != 0) {
            if (i == 1) {
                intByteWithBit3 = DataHandleUtils.setIntByteWithBit(0, i2, true);
                intByteWithBit7 = DataHandleUtils.setIntByteWithBit(0, i2, false);
                intByteWithBit = 0;
                intByteWithBit4 = 0;
                intByteWithBit5 = 0;
                intByteWithBit6 = 0;
                intByteWithBit2 = 0;
                intByteWithBit10 = 0;
                intByteWithBit9 = intByteWithBit10;
                intByteWithBit8 = intByteWithBit9;
                new Thread() { // from class: com.hzbhd.canbus.car._16.UiMgr.32
                    @Override // java.lang.Thread, java.lang.Runnable
                    public void run() {
                        super.run();
                        CanbusMsgSender.sendMsg(new byte[]{22, -57, (byte) intByteWithBit, (byte) intByteWithBit3, (byte) intByteWithBit4, (byte) intByteWithBit5, (byte) intByteWithBit6});
                        try {
                            sleep(100L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        CanbusMsgSender.sendMsg(new byte[]{22, -57, (byte) intByteWithBit2, (byte) intByteWithBit7, (byte) intByteWithBit10, (byte) intByteWithBit9, (byte) intByteWithBit8});
                    }
                }.start();
            }
            if (i == 2) {
                intByteWithBit4 = DataHandleUtils.setIntByteWithBit(0, i2, true);
                intByteWithBit10 = DataHandleUtils.setIntByteWithBit(0, i2, false);
                intByteWithBit = 0;
                intByteWithBit3 = 0;
                intByteWithBit5 = 0;
                intByteWithBit6 = 0;
                intByteWithBit2 = 0;
                intByteWithBit7 = 0;
                intByteWithBit9 = 0;
                intByteWithBit8 = intByteWithBit9;
                new Thread() { // from class: com.hzbhd.canbus.car._16.UiMgr.32
                    @Override // java.lang.Thread, java.lang.Runnable
                    public void run() {
                        super.run();
                        CanbusMsgSender.sendMsg(new byte[]{22, -57, (byte) intByteWithBit, (byte) intByteWithBit3, (byte) intByteWithBit4, (byte) intByteWithBit5, (byte) intByteWithBit6});
                        try {
                            sleep(100L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        CanbusMsgSender.sendMsg(new byte[]{22, -57, (byte) intByteWithBit2, (byte) intByteWithBit7, (byte) intByteWithBit10, (byte) intByteWithBit9, (byte) intByteWithBit8});
                    }
                }.start();
            }
            if (i == 3) {
                intByteWithBit5 = DataHandleUtils.setIntByteWithBit(0, i2, true);
                intByteWithBit9 = DataHandleUtils.setIntByteWithBit(0, i2, false);
                intByteWithBit = 0;
                intByteWithBit3 = 0;
                intByteWithBit4 = 0;
                intByteWithBit6 = 0;
                intByteWithBit2 = 0;
                intByteWithBit7 = 0;
                intByteWithBit10 = 0;
                intByteWithBit8 = 0;
            } else if (i != 4) {
                intByteWithBit = 0;
                intByteWithBit3 = 0;
                intByteWithBit4 = 0;
                intByteWithBit5 = 0;
                intByteWithBit6 = 0;
                intByteWithBit2 = 0;
                intByteWithBit7 = 0;
            } else {
                intByteWithBit6 = DataHandleUtils.setIntByteWithBit(0, i2, true);
                intByteWithBit8 = DataHandleUtils.setIntByteWithBit(0, i2, false);
                intByteWithBit = 0;
                intByteWithBit3 = 0;
                intByteWithBit4 = 0;
                intByteWithBit5 = 0;
                intByteWithBit2 = 0;
                intByteWithBit7 = 0;
                intByteWithBit10 = 0;
                intByteWithBit9 = 0;
            }
            new Thread() { // from class: com.hzbhd.canbus.car._16.UiMgr.32
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    super.run();
                    CanbusMsgSender.sendMsg(new byte[]{22, -57, (byte) intByteWithBit, (byte) intByteWithBit3, (byte) intByteWithBit4, (byte) intByteWithBit5, (byte) intByteWithBit6});
                    try {
                        sleep(100L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -57, (byte) intByteWithBit2, (byte) intByteWithBit7, (byte) intByteWithBit10, (byte) intByteWithBit9, (byte) intByteWithBit8});
                }
            }.start();
        }
        intByteWithBit = DataHandleUtils.setIntByteWithBit(0, i2, true);
        intByteWithBit2 = DataHandleUtils.setIntByteWithBit(0, i2, false);
        intByteWithBit3 = 0;
        intByteWithBit4 = 0;
        intByteWithBit5 = 0;
        intByteWithBit6 = 0;
        intByteWithBit7 = 0;
        intByteWithBit10 = intByteWithBit7;
        intByteWithBit9 = intByteWithBit10;
        intByteWithBit8 = intByteWithBit9;
        new Thread() { // from class: com.hzbhd.canbus.car._16.UiMgr.32
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                CanbusMsgSender.sendMsg(new byte[]{22, -57, (byte) intByteWithBit, (byte) intByteWithBit3, (byte) intByteWithBit4, (byte) intByteWithBit5, (byte) intByteWithBit6});
                try {
                    sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -57, (byte) intByteWithBit2, (byte) intByteWithBit7, (byte) intByteWithBit10, (byte) intByteWithBit9, (byte) intByteWithBit8});
            }
        }.start();
    }

    public boolean isLanKu() {
        return this.each_can_id == 51;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isPortrait() {
        return this.mContext.getResources().getConfiguration().orientation == 1;
    }

    protected int getSettingLeftIndexes(Context context, String str) {
        List<SettingPageUiSet.ListBean> list = getPageUiSet(context).getSettingPageUiSet().getList();
        Iterator<SettingPageUiSet.ListBean> it = list.iterator();
        for (int i = 0; i < list.size(); i++) {
            if (str.equals(it.next().getTitleSrn())) {
                return i;
            }
        }
        return 404;
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
        return 404;
    }

    private MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }
}
