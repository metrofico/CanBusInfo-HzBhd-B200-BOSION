package com.hzbhd.canbus.car._143;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionTouchListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AirBtnAction;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.PanelKeyPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import nfore.android.bt.res.NfDef;


public class UiMgr extends AbstractUiMgr {
    private int currentClickFront;
    private int currentWindLv;
    public int data10_0xA3;
    public int data11_0xA3;
    public int data12_0xA3;
    public int data1_0xA3;
    public int data2_0xA3;
    public int data3_0xA3;
    public int data4_0xA3;
    public int data5_0xA3;
    public int data6_0xA3;
    public int data7_0xA3;
    public int data8_0xA3;
    public int data9_0xA3;
    private Context mContext;
    private int mDifferentId;
    private MsgMgr mMsgMgr;
    private final String TAG = "_143_UiMgr";
    String KEY_VOL_DISTRIBUTION = "KEY_VOL_DISTRIBUTION";
    String KEY_VOL_LOUD = "KEY_VOL_LOUD";
    String KEY_VOL_SET = "KEY_VOL_SET";
    String KEY_VOL_SELECT = "KEY_VOL_SELECT";
    byte[] m0x8ACommand = new byte[2];
    private OnAirBtnClickListener mOnAirTopBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._143.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirBtnCtrl(3, GeneralAirData.ac_max);
                return;
            }
            if (i == 1) {
                UiMgr.this.sendAirBtnCtrl(15, GeneralAirData.mono);
                return;
            }
            if (i == 2) {
                UiMgr.this.sendAirBtnCtrl(2, GeneralAirData.ac);
                return;
            }
            if (i != 3) {
                return;
            }
            int i2 = UiMgr.this.currentWindLv;
            if (i2 == 0) {
                UiMgr.this.currentWindLv = 1;
                CanbusMsgSender.sendMsg(new byte[]{22, 59, 14, 0});
            } else if (i2 == 1) {
                UiMgr.this.currentWindLv = 2;
                CanbusMsgSender.sendMsg(new byte[]{22, 59, 14, 1});
            } else {
                if (i2 != 2) {
                    return;
                }
                UiMgr.this.currentWindLv = 0;
                CanbusMsgSender.sendMsg(new byte[]{22, 59, 14, 2});
            }
        }
    };
    private OnAirBtnClickListener mOnAirBottomLeftBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._143.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr.this.sendAirBtnCtrl(4, GeneralAirData.auto);
        }
    };
    private OnAirBtnClickListener mOnAirBottomRightBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._143.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr.this.sendAirBtnCtrl(1, GeneralAirData.power);
        }
    };
    private OnAirBtnClickListener mOnAirBottomBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._143.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirBtnCtrl(7, GeneralAirData.in_out_cycle);
                return;
            }
            if (i == 1) {
                UiMgr.this.sendAirBtnCtrl(16, GeneralAirData.aqs);
            } else if (i == 2) {
                UiMgr.this.sendAirBtnCtrl(5, GeneralAirData.front_defog);
            } else {
                if (i != 3) {
                    return;
                }
                UiMgr.this.sendAirBtnCtrl(6, GeneralAirData.rear_defog);
            }
        }
    };
    private OnAirSeatClickListener mOnAirSeatClickListener = new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._143.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onLeftSeatClick() {
            int i = UiMgr.this.currentClickFront;
            if (i == 0) {
                UiMgr.this.currentClickFront = 1;
                UiMgr.this.sendAirClick(8);
                return;
            }
            if (i == 1) {
                UiMgr.this.currentClickFront = 2;
                CanbusMsgSender.sendMsg(new byte[]{22, 59, 8, 0});
                return;
            }
            if (i == 2) {
                UiMgr.this.currentClickFront = 3;
                UiMgr.this.sendAirClick(9);
                return;
            }
            if (i == 3) {
                UiMgr.this.currentClickFront = 4;
                CanbusMsgSender.sendMsg(new byte[]{22, 59, 9, 0});
            } else if (i == 4) {
                UiMgr.this.currentClickFront = 5;
                UiMgr.this.sendAirClick(10);
            } else {
                if (i != 5) {
                    return;
                }
                UiMgr.this.currentClickFront = 0;
                CanbusMsgSender.sendMsg(new byte[]{22, 59, 10, 0});
            }
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onRightSeatClick() {
            int i = UiMgr.this.currentClickFront;
            if (i == 0) {
                UiMgr.this.currentClickFront = 1;
                UiMgr.this.sendAirClick(8);
                return;
            }
            if (i == 1) {
                UiMgr.this.currentClickFront = 2;
                CanbusMsgSender.sendMsg(new byte[]{22, 59, 8, 0});
                return;
            }
            if (i == 2) {
                UiMgr.this.currentClickFront = 3;
                UiMgr.this.sendAirClick(9);
                return;
            }
            if (i == 3) {
                UiMgr.this.currentClickFront = 4;
                CanbusMsgSender.sendMsg(new byte[]{22, 59, 9, 0});
            } else if (i == 4) {
                UiMgr.this.currentClickFront = 5;
                UiMgr.this.sendAirClick(10);
            } else {
                if (i != 5) {
                    return;
                }
                UiMgr.this.currentClickFront = 0;
                CanbusMsgSender.sendMsg(new byte[]{22, 59, 10, 0});
            }
        }
    };
    private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._143.UiMgr.12
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            CanbusMsgSender.sendMsg(new byte[]{22, 59, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, 1});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            CanbusMsgSender.sendMsg(new byte[]{22, 59, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, 2});
        }
    };
    private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._143.UiMgr.13
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            CanbusMsgSender.sendMsg(new byte[]{22, 59, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 1});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            CanbusMsgSender.sendMsg(new byte[]{22, 59, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 2});
        }
    };
    private OnAirWindSpeedUpDownClickListener mSetWindSpeedViewOnClickListener = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._143.UiMgr.14
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            CanbusMsgSender.sendMsg(new byte[]{22, 59, 11, 2});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            CanbusMsgSender.sendMsg(new byte[]{22, 59, 11, 1});
        }
    };
    private OnPanelKeyPositionListener mOnPanelKeyPositionListener = new OnPanelKeyPositionListener() { // from class: com.hzbhd.canbus.car._143.UiMgr.15
        @Override // com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener
        public void click(int i) {
            switch (i) {
                case 0:
                    CanbusMsgSender.sendMsg(new byte[]{22, 33, 22, 1});
                    CanbusMsgSender.sendMsg(new byte[]{22, 33, 22, 0});
                    break;
                case 1:
                    CanbusMsgSender.sendMsg(new byte[]{22, 33, 23, 1});
                    CanbusMsgSender.sendMsg(new byte[]{22, 33, 23, 0});
                    break;
                case 2:
                    CanbusMsgSender.sendMsg(new byte[]{22, 33, 38, 1});
                    CanbusMsgSender.sendMsg(new byte[]{22, 33, 38, 0});
                    break;
                case 3:
                    CanbusMsgSender.sendMsg(new byte[]{22, 33, 25, 1});
                    CanbusMsgSender.sendMsg(new byte[]{22, 33, 25, 0});
                    break;
                case 4:
                    CanbusMsgSender.sendMsg(new byte[]{22, 33, 36, 1});
                    CanbusMsgSender.sendMsg(new byte[]{22, 33, 36, 0});
                    break;
                case 5:
                    CanbusMsgSender.sendMsg(new byte[]{22, 33, 26, 1});
                    CanbusMsgSender.sendMsg(new byte[]{22, 33, 26, 0});
                    break;
                case 6:
                    CanbusMsgSender.sendMsg(new byte[]{22, 33, 37, 1});
                    CanbusMsgSender.sendMsg(new byte[]{22, 33, 37, 0});
                    break;
                case 7:
                    CanbusMsgSender.sendMsg(new byte[]{22, 33, 24, 1});
                    CanbusMsgSender.sendMsg(new byte[]{22, 33, 24, 0});
                    break;
                case 8:
                    CanbusMsgSender.sendMsg(new byte[]{22, 33, 39, 1});
                    CanbusMsgSender.sendMsg(new byte[]{22, 33, 39, 0});
                    break;
            }
        }
    };
    int D0B7 = 0;
    int D0B6 = 0;
    int D0B5 = 0;
    int D0B4 = 0;
    int D0B3 = 0;
    int D0B2 = 0;
    int D0B1 = 0;
    int Data1 = 0;
    int Data2 = 0;
    int Data3 = 0;
    int Data4 = 0;
    int Data5 = 0;
    int Data6 = 0;
    int D0B7_0x8B = 0;
    int D0B6_0x8B = 0;
    int D0B5_0x8B = 0;
    int D0B4_0x8B = 0;
    int D0B3_0x8B = 0;
    int D0B2_0x8B = 0;
    int D0B1_0x8B = 0;
    int Data1_0x8B = 0;
    int Data2_0x8B = 0;
    int Data3_0x8B = 0;
    int Data4_0x8B = 0;
    int Data5_0x8B = 0;
    int Data6_0x8B = 0;
    public int D0B7_0xA1 = 0;
    public int D0B6_0xA1 = 0;
    public int D0B5_0xA1 = 0;
    public int Data1_0xA1 = 0;
    public int Data2_0xA1 = 0;

    private static int setIntByteWithBit(int i, int i2, int i3) {
        return i ^ ((i3 ^ ((i >> i2) & 1)) << i2);
    }

    private byte setWidgetData(boolean z) {
        return !z ? (byte) 1 : (byte) 0;
    }

    public UiMgr(final Context context) {
        try {
            this.mContext = context;
            int currentCarId = getCurrentCarId();
            this.mDifferentId = currentCarId;
            byte[] bArr = this.m0x8ACommand;
            bArr[0] = 22;
            bArr[1] = -118;
            if (currentCarId == 4 || currentCarId == 5 || currentCarId == 12 || currentCarId == 52 || currentCarId == 13 || currentCarId == 53 || currentCarId == 23) {
                removeMainPrjBtnByName(context, MainAction.WARNING);
            }
            int i = this.mDifferentId;
            if (i != 6 && i != 8 && i != 17) {
                removeMainPrjBtnByName(context, MainAction.PANEL_KEY);
            }
            if (this.mDifferentId != 21) {
                removeMainPrjBtnByName(context, MainAction.AMPLIFIER);
                removeSettingLeftItemByNameList(this.mContext, new String[]{"_143_0xAD_setting1"});
            } else {
                initAmpContent(context);
            }
            int i2 = this.mDifferentId;
            if (i2 != 1 && i2 != 2 && i2 != 3 && i2 != 11 && i2 != 13 && i2 != 53 && i2 != 14 && i2 != 15 && i2 != 65 && i2 != 16 && i2 != 18 && i2 != 19 && i2 != 20 && i2 != 24 && i2 != 25 && i2 != 26 && i2 != 27) {
                removeSettingLeftItemByNameList(this.mContext, new String[]{"car_set"});
            }
            int i3 = this.mDifferentId;
            if (i3 != 1 && i3 != 2 && i3 != 3 && i3 != 11 && i3 != 16 && i3 != 53 && i3 != 13 && i3 != 19 && i3 != 20 && i3 != 21 && i3 != 26 && i3 != 24 && i3 != 25 && i3 != 27) {
                removeSettingLeftItemByNameList(this.mContext, new String[]{"_143_car_setting_2"});
            }
            int i4 = this.mDifferentId;
            if (i4 != 3 && i4 != 14 && i4 != 16 && i4 != 26) {
                removeSettingLeftItemByNameList(this.mContext, new String[]{"_143_speed_setting1"});
            }
            int i5 = this.mDifferentId;
            if (i5 != 16 && i5 != 26) {
                removeSettingLeftItemByNameList(this.mContext, new String[]{"_143_0x82_setting_1"});
            }
            if (this.mDifferentId != 16) {
                removeSettingLeftItemByNameList(this.mContext, new String[]{"_143_0x85_setting"});
            }
            AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
            amplifierPageUiSet.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._143.UiMgr.1
                @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
                public void progress(AmplifierActivity.AmplifierBand amplifierBand, int i6) {
                    int i7 = AnonymousClass16.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[amplifierBand.ordinal()];
                    if (i7 == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -83, 16, (byte) i6});
                    } else {
                        if (i7 != 2) {
                            return;
                        }
                        CanbusMsgSender.sendMsg(new byte[]{22, -83, 15, (byte) i6});
                    }
                }
            });
            amplifierPageUiSet.setOnAmplifierPositionListener(new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._143.UiMgr.2
                @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
                public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i6) {
                    int i7 = AnonymousClass16.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[amplifierPosition.ordinal()];
                    if (i7 == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -83, 3, (byte) (i6 + 7)});
                    } else {
                        if (i7 != 2) {
                            return;
                        }
                        CanbusMsgSender.sendMsg(new byte[]{22, -83, 2, (byte) (i6 + 7)});
                    }
                }
            });
            final SettingPageUiSet settingUiSet = getSettingUiSet(context);
            settingUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._143.UiMgr.3
                @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
                public void onConfirmClick(int i6, int i7) {
                    UiMgr uiMgr = UiMgr.this;
                    if (i6 == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_143_comp_page")) {
                        UiMgr uiMgr2 = UiMgr.this;
                        if (i7 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "_143_comp_page", "_143_comp_page1")) {
                            send0x1BInfo(2);
                        }
                        UiMgr uiMgr3 = UiMgr.this;
                        if (i7 == uiMgr3.getSettingRightIndex(uiMgr3.mContext, "_143_comp_page", "_143_comp_page2")) {
                            send0x1BInfo(3);
                        }
                    }
                }

                private void send0x1BInfo(int i6) {
                    byte b = (byte) i6;
                    CanbusMsgSender.sendMsg(new byte[]{22, 27, b, b, 1, -1});
                    UiMgr uiMgr = UiMgr.this;
                    uiMgr.getMsgMgr(uiMgr.mContext).toast("RESET SUCCESS!");
                }
            });
            settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._143.UiMgr.4
                /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
                /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
                /* JADX WARN: Removed duplicated region for block: B:25:0x0139  */
                @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public void onClickItem(int r20, int r21, int r22) {
                    /*
                        Method dump skipped, instructions count: 2556
                        To view this dump change 'Code comments level' option to 'DEBUG'
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._143.UiMgr.AnonymousClass4.onClickItem(int, int, int):void");
                }
            });
            settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._143.UiMgr.5
                /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
                /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
                /* JADX WARN: Removed duplicated region for block: B:12:0x005c  */
                /* JADX WARN: Removed duplicated region for block: B:48:0x0134  */
                /* JADX WARN: Removed duplicated region for block: B:82:0x01c6  */
                @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public void onClickItem(int r11, int r12, int r13) {
                    /*
                        Method dump skipped, instructions count: 704
                        To view this dump change 'Code comments level' option to 'DEBUG'
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._143.UiMgr.AnonymousClass5.onClickItem(int, int, int):void");
                }
            });
            AirPageUiSet airUiSet = getAirUiSet(context);
            airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirTopBtnClickListener, this.mOnAirBottomLeftBtnClickListener, this.mOnAirBottomRightBtnClickListener, this.mOnAirBottomBtnClickListener});
            airUiSet.getFrontArea().setOnAirSeatClickListener(this.mOnAirSeatClickListener);
            airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mTempSetViewOnUpDownClickListenerLeft, null, this.mTempSetViewOnUpDownClickListenerRight});
            airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.mSetWindSpeedViewOnClickListener);
            if (this.mDifferentId == 27) {
                airUiSet.getFrontArea().setCanSetWindSpeed(false);
                airUiSet.getFrontArea().setCanSetRightTemp(false);
                airUiSet.getFrontArea().setCanSetLeftTemp(false);
                airUiSet.getFrontArea().setDisableBtnArray(new String[]{AirBtnAction.AC_MAX, AirBtnAction.AUTO_WIND_LV, "auto", "power", "in_out_cycle", AirBtnAction.AQS, "front_defog", "rear_defog"});
            } else {
                airUiSet.getFrontArea().setCanSetWindSpeed(true);
                airUiSet.getFrontArea().setCanSetRightTemp(true);
                airUiSet.getFrontArea().setCanSetLeftTemp(true);
                airUiSet.getFrontArea().setDisableBtnArray(new String[0]);
            }
            final PanelKeyPageUiSet panelKeyPageUiSet = getPanelKeyPageUiSet(context);
            panelKeyPageUiSet.setOnPanelKeyPositionTouchListener(new OnPanelKeyPositionTouchListener() { // from class: com.hzbhd.canbus.car._143.UiMgr.6
                @Override // com.hzbhd.canbus.interfaces.OnPanelKeyPositionTouchListener
                public void onTouch(int i6, MotionEvent motionEvent) {
                    int i7;
                    String str = (String) ((List) Objects.requireNonNull(panelKeyPageUiSet.getList())).get(i6);
                    str.hashCode();
                    switch (str) {
                        case "panel_btn_esc":
                            i7 = 37;
                            break;
                        case "panel_btn_dark":
                            i7 = 39;
                            break;
                        case "panel_btn_down":
                            i7 = 24;
                            break;
                        case "panel_btn_left":
                            i7 = 25;
                            break;
                        case "panel_btn_menu":
                            i7 = 22;
                            break;
                        case "panel_btn_mode":
                            i7 = 38;
                            break;
                        case "panel_btn_right":
                            i7 = 26;
                            break;
                        case "panel_btn_ok":
                            i7 = 36;
                            break;
                        case "panel_btn_up":
                            i7 = 23;
                            break;
                        default:
                            i7 = 0;
                            break;
                    }
                    if (motionEvent.getAction() == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 33, (byte) i7, 1});
                    }
                    if (motionEvent.getAction() == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 33, (byte) i7, 0});
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("cwh", "错误类型：" + e);
        }
    }

    /* renamed from: com.hzbhd.canbus.car._143.UiMgr$16, reason: invalid class name */
    static /* synthetic */ class AnonymousClass16 {
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
                iArr2[AmplifierActivity.AmplifierBand.BASS.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.TREBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private void initAmpContent(Context context) {
        MsgMgr msgMgr = getMsgMgr(context);
        int settingLeftIndexes = getSettingLeftIndexes(context, "_143_0xAD_setting1");
        int settingRightIndex = getSettingRightIndex(this.mContext, "_143_0xAD_setting1", "_143_0xAD_setting4");
        String str = this.KEY_VOL_DISTRIBUTION;
        msgMgr.updateSettings(context, settingLeftIndexes, settingRightIndex, str, SharePreUtil.getIntValue(this.mContext, str, 0));
        CanbusMsgSender.sendMsg(new byte[]{22, -83, 11, (byte) SharePreUtil.getIntValue(this.mContext, this.KEY_VOL_DISTRIBUTION, 0)});
        MsgMgr msgMgr2 = getMsgMgr(context);
        int settingLeftIndexes2 = getSettingLeftIndexes(context, "_143_0xAD_setting1");
        int settingRightIndex2 = getSettingRightIndex(this.mContext, "_143_0xAD_setting1", "_143_0xAD_setting5");
        String str2 = this.KEY_VOL_LOUD;
        msgMgr2.updateSettings(context, settingLeftIndexes2, settingRightIndex2, str2, SharePreUtil.getIntValue(this.mContext, str2, 0));
        MsgMgr msgMgr3 = getMsgMgr(context);
        int settingLeftIndexes3 = getSettingLeftIndexes(context, "_143_0xAD_setting1");
        int settingRightIndex3 = getSettingRightIndex(this.mContext, "_143_0xAD_setting1", "_143_0xAD_setting6");
        String str3 = this.KEY_VOL_SET;
        msgMgr3.updateSettings(context, settingLeftIndexes3, settingRightIndex3, str3, SharePreUtil.getIntValue(this.mContext, str3, 0));
        MsgMgr msgMgr4 = getMsgMgr(context);
        int settingLeftIndexes4 = getSettingLeftIndexes(context, "_143_0xAD_setting1");
        int settingRightIndex4 = getSettingRightIndex(this.mContext, "_143_0xAD_setting1", "_143_0xAD_setting7");
        String str4 = this.KEY_VOL_SELECT;
        msgMgr4.updateSettings(context, settingLeftIndexes4, settingRightIndex4, str4, SharePreUtil.getIntValue(this.mContext, str4, 0));
        CanbusMsgSender.sendMsg(new byte[]{22, -83, 11, (byte) SharePreUtil.getIntValue(this.mContext, this.KEY_VOL_DISTRIBUTION, 0)});
        CanbusMsgSender.sendMsg(new byte[]{22, -83, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) SharePreUtil.getIntValue(this.mContext, this.KEY_VOL_LOUD, 0)});
        CanbusMsgSender.sendMsg(new byte[]{22, -83, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) SharePreUtil.getIntValue(this.mContext, this.KEY_VOL_SET, 0)});
        CanbusMsgSender.sendMsg(new byte[]{22, -83, 14, (byte) SharePreUtil.getIntValue(this.mContext, this.KEY_VOL_SELECT, 0)});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirBtnCtrl(int i, boolean z) {
        CanbusMsgSender.sendMsg(new byte[]{22, 59, (byte) i, setWidgetData(z)});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirClick(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, 59, (byte) i, 1});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void send0x8AData16Command() {
        CanbusMsgSender.sendMsg(new byte[]{22, -118, (byte) getDecimalFrom8Bit(this.D0B7, this.D0B6, this.D0B5, this.D0B4, this.D0B3, this.D0B2, this.D0B1, 0), (byte) this.Data1, (byte) this.Data2, (byte) this.Data3, (byte) this.Data4, (byte) this.Data5, (byte) this.Data6, 0, 0, 0});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void send0x8BData16Command() {
        CanbusMsgSender.sendMsg(new byte[]{22, -117, (byte) getDecimalFrom8Bit(this.D0B7_0x8B, this.D0B6_0x8B, this.D0B5_0x8B, this.D0B4_0x8B, this.D0B3_0x8B, this.D0B2_0x8B, this.D0B1_0x8B, 0), (byte) this.Data1_0x8B, (byte) this.Data2_0x8B, (byte) this.Data3_0x8B, (byte) this.Data4_0x8B, (byte) this.Data5_0x8B, (byte) this.Data6_0x8B, 0, 0, 0});
    }

    private int getDecimalFrom8Bit(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return Integer.parseInt(i + "" + i2 + "" + i3 + "" + i4 + "" + i5 + "" + i6 + "" + i7 + "" + i8, 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void send0x7DDataCommand(int i, int i2) {
        byte[] bArr = getMsgMgr(this.mContext).get0x72Enable();
        bArr[0] = 22;
        bArr[1] = 125;
        bArr[2] = (byte) i;
        bArr[3] = (byte) i2;
        CanbusMsgSender.sendMsg(bArr);
    }

    public void send0xA1HostState() {
        CanbusMsgSender.sendMsg(new byte[]{22, -95, (byte) getDecimalFrom8Bit(this.D0B7_0xA1, this.D0B6_0xA1, this.D0B5_0xA1, 0, 0, 0, 0, 0), (byte) this.Data1_0xA1, (byte) this.Data2_0xA1});
        this.D0B6_0xA1 = 0;
    }

    public void send0xA2Info(int i, int i2, int i3, int i4) {
        CanbusMsgSender.sendMsg(new byte[]{22, -94, (byte) i, (byte) i2, (byte) i3, (byte) i4, 0, 0});
    }

    public void send0xA3Info(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -93, (byte) i, (byte) this.data1_0xA3, (byte) this.data2_0xA3, (byte) this.data3_0xA3, (byte) this.data4_0xA3, (byte) this.data5_0xA3, (byte) this.data6_0xA3, (byte) this.data7_0xA3, (byte) this.data8_0xA3, (byte) this.data9_0xA3, (byte) this.data10_0xA3, (byte) this.data11_0xA3, (byte) this.data12_0xA3});
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
}
