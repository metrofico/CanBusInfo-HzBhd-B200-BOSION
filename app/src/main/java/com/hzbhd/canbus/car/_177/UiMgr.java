package com.hzbhd.canbus.car._177;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import kotlin.jvm.internal.ByteCompanionObject;
import nfore.android.bt.res.NfDef;


public class UiMgr extends AbstractUiMgr {
    public int currentClickFront;
    private String[] mAirBtnListFrontBottom;
    private String[] mAirBtnListFrontLeft;
    private String[] mAirBtnListFrontRight;
    private String[] mAirBtnListFrontTop;
    private Context mContext;
    OnSettingItemSeekbarSelectListener mOnSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._177.UiMgr.1
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
        public void onClickItem(int i, int i2, int i3) {
            if (i == 0) {
                if (i2 == 12) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 15, (byte) i3});
                }
            } else if (i == 2 && i2 == 11) {
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 25, (byte) i3});
            }
        }
    };
    OnSettingItemSelectListener mOnSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._177.UiMgr.2
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            if (i == 0) {
                switch (i2) {
                    case 0:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 2, (byte) i3});
                        break;
                    case 1:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 1, (byte) i3});
                        break;
                    case 2:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 3, (byte) i3});
                        break;
                    case 3:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 5, (byte) i3});
                        break;
                    case 4:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 4, (byte) i3});
                        break;
                    case 5:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) i3});
                        break;
                    case 6:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 11, (byte) i3});
                        break;
                    case 7:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 10, (byte) i3});
                        break;
                    case 8:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 9, (byte) i3});
                        break;
                    case 9:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 6, (byte) i3});
                        break;
                    case 10:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 7, (byte) (i3 + MsgMgr.ONE)});
                        break;
                    case 11:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 14, (byte) (i3 + MsgMgr.ONE)});
                        break;
                    case 13:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 16, (byte) (i3 + MsgMgr.ONE)});
                        break;
                    case 14:
                        if (i3 != 2) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -125, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) i3});
                            break;
                        } else {
                            CanbusMsgSender.sendMsg(new byte[]{22, -125, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, -127});
                            break;
                        }
                }
            }
            if (i != 2) {
                return;
            }
            switch (i2) {
                case 0:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 32, (byte) i3});
                    break;
                case 1:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 29, (byte) i3});
                    break;
                case 2:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 19, (byte) i3});
                    break;
                case 3:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 17, (byte) i3});
                    break;
                case 4:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 18, (byte) i3});
                    break;
                case 5:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 20, (byte) i3});
                    break;
                case 6:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 21, (byte) i3});
                    break;
                case 7:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, (byte) i3});
                    break;
                case 8:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 22, (byte) i3});
                    break;
                case 9:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 23, (byte) i3});
                    break;
                case 10:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 24, (byte) (i3 + MsgMgr.ONE)});
                    break;
                case 12:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 26, (byte) i3});
                    break;
                case 13:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 27, (byte) (i3 + MsgMgr.ONE)});
                    break;
                case 14:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 28, (byte) (i3 + MsgMgr.ONE)});
                    break;
                case 15:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 30, (byte) i3});
                    break;
                case 16:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 31, (byte) i3});
                    break;
                case 17:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 34, (byte) (i3 + MsgMgr.ONE)});
                    break;
                case 18:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 35, (byte) i3});
                    break;
                case 19:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 36, (byte) i3});
                    break;
            }
        }
    };
    OnConfirmDialogListener mOnConfirmDialogListener = new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._177.UiMgr.3
        @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
        public void onConfirmClick(int i, int i2) {
            if (i != 1) {
                return;
            }
            if (i2 == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -125, ByteCompanionObject.MIN_VALUE, 1});
                return;
            }
            if (i2 == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -125, ByteCompanionObject.MIN_VALUE, 2});
                return;
            }
            if (i2 == 2) {
                CanbusMsgSender.sendMsg(new byte[]{22, -125, ByteCompanionObject.MIN_VALUE, 3});
            } else if (i2 == 3) {
                CanbusMsgSender.sendMsg(new byte[]{22, -125, ByteCompanionObject.MIN_VALUE, 4});
            } else {
                if (i2 != 4) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -125, ByteCompanionObject.MIN_VALUE, 5});
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._177.UiMgr.4
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.sendAirCommand(uiMgr.mAirBtnListFrontTop[i]);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._177.UiMgr.5
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.sendAirCommand(uiMgr.mAirBtnListFrontLeft[i]);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._177.UiMgr.6
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.sendAirCommand(uiMgr.mAirBtnListFrontRight[i]);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._177.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.sendAirCommand(uiMgr.mAirBtnListFrontBottom[i]);
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._177.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirCommand(13);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirCommand(14);
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._177.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirCommand(15);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            if (UiMgr.this.getCurrentCarId() == 4 || UiMgr.this.getCurrentCarId() == 9) {
                return;
            }
            UiMgr.this.sendAirCommand(16);
        }
    };
    OnAirWindSpeedUpDownClickListener mSetWindSpeedView = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._177.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            if (GeneralAirData.front_wind_level == 15) {
                CanbusMsgSender.sendMsg(new byte[]{22, -88, 18, 8});
            } else if (GeneralAirData.front_wind_level > MsgMgr.WIND_LEVEL_LOW) {
                CanbusMsgSender.sendMsg(new byte[]{22, -88, 18, (byte) (GeneralAirData.front_wind_level - 1)});
            }
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            if (GeneralAirData.front_wind_level < MsgMgr.WIND_LEVEL_HIGH) {
                CanbusMsgSender.sendMsg(new byte[]{22, -88, 18, (byte) (GeneralAirData.front_wind_level + 1)});
            }
        }
    };
    OnAirSeatClickListener mOnAirSeatClickListener = new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._177.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onLeftSeatClick() {
            UiMgr.this.setFrontSearClick();
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onRightSeatClick() {
            UiMgr.this.setFrontSearClick();
        }
    };

    public UiMgr(Context context) {
        this.mContext = context;
        CanbusMsgSender.sendMsg(new byte[]{22, -125, 0, 1});
        AirPageUiSet airUiSet = getAirUiSet(context);
        String[][] lineBtnAction = airUiSet.getFrontArea().getLineBtnAction();
        this.mAirBtnListFrontTop = lineBtnAction[0];
        this.mAirBtnListFrontLeft = lineBtnAction[1];
        this.mAirBtnListFrontRight = lineBtnAction[2];
        this.mAirBtnListFrontBottom = lineBtnAction[3];
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.mSetWindSpeedView);
        airUiSet.getFrontArea().setOnAirSeatClickListener(this.mOnAirSeatClickListener);
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
        settingUiSet.setOnSettingConfirmDialogListener(this.mOnConfirmDialogListener);
        settingUiSet.setOnSettingItemSeekbarSelectListener(this.mOnSettingItemSeekbarSelectListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFrontSearClick() {
        if (GeneralAirData.front_right_blow_window) {
            this.currentClickFront = 3;
            if (GeneralAirData.front_right_blow_foot) {
                this.currentClickFront = 4;
                if (GeneralAirData.front_right_blow_head) {
                    this.currentClickFront = 5;
                }
            }
        }
        if (!GeneralAirData.front_right_blow_window) {
            this.currentClickFront = 2;
            if (!GeneralAirData.front_right_blow_foot) {
                this.currentClickFront = 1;
                if (!GeneralAirData.front_right_blow_head) {
                    this.currentClickFront = 0;
                }
            }
        }
        int i = this.currentClickFront;
        if (i != 0) {
            if (i != 1) {
                if (i == 2 || i == 3) {
                    sendAirCommand(24);
                    return;
                } else if (i != 4) {
                    if (i != 5) {
                        return;
                    }
                }
            }
            sendAirCommand(9);
            return;
        }
        sendAirCommand(7);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirCommand(String str) {
        str.hashCode();
        switch (str) {
            case "front_defog":
                sendAirCommand(6);
                break;
            case "normal":
                sendAirCommand(27);
                break;
            case "rear_defog":
                sendAirCommand(23);
                break;
            case "auto_cycle":
                sendAirCommand(22);
                break;
            case "ac":
                sendAirCommand(1);
                break;
            case "auto":
                sendAirCommand(2);
                break;
            case "dual":
                sendAirCommand(3);
                break;
            case "fast":
                sendAirCommand(26);
                break;
            case "soft":
                sendAirCommand(25);
                break;
            case "power":
                sendAirCommand(0);
                break;
            case "in_out_cycle":
                sendAirCommand(21);
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirCommand(int i) {
        byte b = (byte) i;
        CanbusMsgSender.sendMsg(new byte[]{22, -88, b, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, -88, b, 0});
    }
}
