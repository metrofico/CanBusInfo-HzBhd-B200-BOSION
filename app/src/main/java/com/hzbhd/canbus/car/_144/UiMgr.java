package com.hzbhd.canbus.car._144;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import nfore.android.bt.res.NfDef;


public class UiMgr extends AbstractUiMgr {
    private int currentClickFront;
    private int currentWindLv;
    private Context mContext;
    private OnAirBtnClickListener mOnAirTopBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._144.UiMgr.3
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
    private OnAirBtnClickListener mOnAirBottomLeftBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._144.UiMgr.4
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr.this.sendAirBtnCtrl(4, GeneralAirData.auto);
        }
    };
    private OnAirBtnClickListener mOnAirBottomRightBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._144.UiMgr.5
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr.this.sendAirBtnCtrl(1, GeneralAirData.power);
        }
    };
    private OnAirBtnClickListener mOnAirBottomBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._144.UiMgr.6
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
    private OnAirSeatClickListener mOnAirSeatClickListener = new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._144.UiMgr.7
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
    private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._144.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            CanbusMsgSender.sendMsg(new byte[]{22, 59, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, 1});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            CanbusMsgSender.sendMsg(new byte[]{22, 59, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, 2});
        }
    };
    private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._144.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            CanbusMsgSender.sendMsg(new byte[]{22, 59, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 1});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            CanbusMsgSender.sendMsg(new byte[]{22, 59, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 2});
        }
    };
    private OnAirWindSpeedUpDownClickListener mSetWindSpeedViewOnClickListener = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._144.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            CanbusMsgSender.sendMsg(new byte[]{22, 59, 11, 2});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            CanbusMsgSender.sendMsg(new byte[]{22, 59, 11, 1});
        }
    };
    private OnPanelKeyPositionListener mOnPanelKeyPositionListener = new OnPanelKeyPositionListener() { // from class: com.hzbhd.canbus.car._144.UiMgr.11
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

    private byte setWidgetData(boolean z) {
        return !z ? (byte) 1 : (byte) 0;
    }

    public UiMgr(final Context context) {
        this.mContext = context;
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._144.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                if (i == 0) {
                    switch (i2) {
                        case 0:
                            CanbusMsgSender.sendMsg(new byte[]{22, 123, 8, (byte) i3});
                            break;
                        case 1:
                            CanbusMsgSender.sendMsg(new byte[]{22, 123, 11, (byte) i3});
                            break;
                        case 2:
                            CanbusMsgSender.sendMsg(new byte[]{22, 123, 9, (byte) i3});
                            break;
                        case 3:
                            CanbusMsgSender.sendMsg(new byte[]{22, 123, 10, (byte) DataHandleUtils.setIntFromByteWithBit(DataHandleUtils.setIntByteWithBit(0, 7, i3 == 1), Settings.System.getInt(context.getContentResolver(), "left0right4", 0), 0, 7)});
                            break;
                        case 5:
                            CanbusMsgSender.sendMsg(new byte[]{22, 123, 1, (byte) i3});
                            break;
                        case 6:
                            CanbusMsgSender.sendMsg(new byte[]{22, 123, 2, (byte) i3});
                            break;
                        case 7:
                            CanbusMsgSender.sendMsg(new byte[]{22, 123, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) i3});
                            break;
                        case 8:
                            CanbusMsgSender.sendMsg(new byte[]{22, 123, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) i3});
                            break;
                        case 9:
                            CanbusMsgSender.sendMsg(new byte[]{22, 123, 4, (byte) i3});
                            break;
                        case 10:
                            CanbusMsgSender.sendMsg(new byte[]{22, 123, 5, (byte) i3});
                            break;
                        case 11:
                            CanbusMsgSender.sendMsg(new byte[]{22, 123, 6, (byte) i3});
                            break;
                        case 12:
                            CanbusMsgSender.sendMsg(new byte[]{22, 123, 3, (byte) i3});
                            Intent intent = new Intent("REVERSING_SOUND");
                            intent.putExtra("selectPos1", i3);
                            UiMgr.this.mContext.sendBroadcast(intent);
                            break;
                    }
                }
                if (i != 1) {
                    if (i != 2) {
                        return;
                    }
                    if (i2 == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -54, 3, (byte) (2 - i3)});
                        return;
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -54, 5, (byte) (i3 + 1)});
                        return;
                    }
                }
                if (i3 < 16) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte) (i3 + 1)});
                    return;
                }
                switch (i3) {
                    case 16:
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, 18});
                        break;
                    case 17:
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, 20});
                        break;
                    case 18:
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, 22});
                        break;
                    case 19:
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, 23});
                        break;
                    case 20:
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, 26});
                        break;
                    case 21:
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, 25});
                        break;
                    case 22:
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, 28});
                        break;
                    case 23:
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, 29});
                        break;
                    case 24:
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, 30});
                        break;
                    case 25:
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, 31});
                        break;
                    case 26:
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, 32});
                        break;
                    case 27:
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, 33});
                        break;
                }
            }
        });
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._144.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public void onClickItem(int i, int i2, int i3) {
                if (i == 0 && i2 == 4) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 123, 10, (byte) DataHandleUtils.setIntFromByteWithBit(DataHandleUtils.setIntByteWithBit(0, 7, Settings.System.getInt(context.getContentResolver(), "left0right3", 0) == 1), i3, 0, 7)});
                }
            }
        });
        AirPageUiSet airUiSet = getAirUiSet(context);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirTopBtnClickListener, this.mOnAirBottomLeftBtnClickListener, this.mOnAirBottomRightBtnClickListener, this.mOnAirBottomBtnClickListener});
        airUiSet.getFrontArea().setOnAirSeatClickListener(this.mOnAirSeatClickListener);
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mTempSetViewOnUpDownClickListenerLeft, null, this.mTempSetViewOnUpDownClickListenerRight});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.mSetWindSpeedViewOnClickListener);
        getPanelKeyPageUiSet(context).setOnPanelKeyPositionListener(this.mOnPanelKeyPositionListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirBtnCtrl(int i, boolean z) {
        CanbusMsgSender.sendMsg(new byte[]{22, 59, (byte) i, setWidgetData(z)});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirClick(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, 59, (byte) i, 1});
    }
}
