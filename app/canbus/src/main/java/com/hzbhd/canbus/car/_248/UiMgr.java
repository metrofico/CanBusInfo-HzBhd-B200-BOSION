package com.hzbhd.canbus.car._248;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnBackCameraStatusListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import java.util.ArrayList;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    private int chargeEndTimeHourProgress;
    private int chargeEndTimeMinuteProgress;
    private int chargeStartTimeHourProgress;
    private int chargeStartTimeMinuteProgress;
    private Context mContext;
    private MsgMgr msgMgr;
    private OnAirSeatClickListener onAirSeatClickListener = new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._248.UiMgr.3
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onLeftSeatClick() {
            int i;
            if (GeneralAirData.front_left_blow_window) {
                i = 9;
            } else if (GeneralAirData.front_left_blow_head) {
                i = 10;
            } else {
                boolean z = GeneralAirData.front_left_blow_foot;
                i = 8;
            }
            byte b = (byte) i;
            CanbusMsgSender.sendMsg(new byte[]{22, 61, b, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, b, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onRightSeatClick() {
            int i;
            if (GeneralAirData.front_right_blow_window) {
                i = 9;
            } else if (GeneralAirData.front_right_blow_head) {
                i = 10;
            } else {
                boolean z = GeneralAirData.front_right_blow_foot;
                i = 8;
            }
            byte b = (byte) i;
            CanbusMsgSender.sendMsg(new byte[]{22, 61, b, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, b, 0});
        }
    };
    private OnAirSeatHeatColdMinPlusClickListener mFrontLeftSeatHeatColdListener = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._248.UiMgr.4
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
            if (UiMgr.this.getCurrentCarId() == 4) {
                int i = GeneralAirData.front_left_seat_heat_level - 1;
                if (i >= 3) {
                    i = 3;
                }
                if (i <= 0) {
                    i = 0;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, 59, 15, (byte) i});
            }
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            if (UiMgr.this.getCurrentCarId() == 4) {
                int i = GeneralAirData.front_left_seat_heat_level + 1;
                if (i >= 3) {
                    i = 3;
                }
                if (i <= 0) {
                    i = 0;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, 59, 15, (byte) i});
            }
        }
    };
    private OnAirSeatHeatColdMinPlusClickListener mFrontRightSeatHeatColdListener = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._248.UiMgr.5
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
            if (UiMgr.this.getCurrentCarId() == 4) {
                int i = GeneralAirData.front_right_seat_heat_level - 1;
                if (i >= 3) {
                    i = 3;
                }
                if (i <= 0) {
                    i = 0;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, 59, 16, (byte) i});
            }
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            if (UiMgr.this.getCurrentCarId() == 4) {
                int i = GeneralAirData.front_right_seat_heat_level + 1;
                if (i >= 3) {
                    i = 3;
                }
                if (i <= 0) {
                    i = 0;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, 59, 16, (byte) i});
            }
        }
    };
    private OnAirWindSpeedUpDownClickListener setWindSpeedViewOnClickListener = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._248.UiMgr.6
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 11, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 11, 0});
        }
    };
    private OnAirPageStatusListener mOnAirPageStatusListener = new OnAirPageStatusListener() { // from class: com.hzbhd.canbus.car._248.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener
        public void onStatusChange(int i) {
            if (i == 1) {
                UiMgr.this.sendData(new byte[]{22, -112, 35});
            }
        }
    };
    private OnAirTemperatureUpDownClickListener monUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._248.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 14, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 14, 0});
        }
    };
    private OnAirTemperatureUpDownClickListener monUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._248.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 15, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 15, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 16, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 16, 0});
        }
    };
    private OnAirBtnClickListener mOnAirBtnClickListenerTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._248.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 5, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 5, 0});
            } else if (i == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 6, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 6, 0});
            } else {
                if (i != 2) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 41, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 41, 0});
            }
        }
    };
    private OnAirBtnClickListener mOnAirBtnClickListenerCenter1 = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._248.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            LogUtil.showLog("position:" + i);
            if (i == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 4, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 4, 0});
            }
        }
    };
    private OnAirBtnClickListener mOnAirBtnClickListenerCenter2 = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._248.UiMgr.12
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            LogUtil.showLog("position:" + i);
            if (i == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 1, 0});
            }
        }
    };
    private OnAirBtnClickListener mOnAirBtnClickListenerButtom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._248.UiMgr.13
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 2, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 2, 0});
            } else if (i == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 25, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 25, 0});
            } else {
                if (i != 2) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 7, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 7, 0});
            }
        }
    };
    private OnSettingPageStatusListener mOnSettingPageStatusListener = new OnSettingPageStatusListener() { // from class: com.hzbhd.canbus.car._248.UiMgr.14
        @Override // com.hzbhd.canbus.interfaces.OnSettingPageStatusListener
        public void onStatusChange(int i) {
            if (i == 0) {
                UiMgr.this.sendData(new byte[]{22, -112, 64});
            }
        }
    };
    private OnSettingItemSelectListener mOnSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._248.UiMgr.15
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            switch (i) {
                case 0:
                    if (i2 == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -101, 2, (byte) i3});
                        break;
                    } else if (i2 == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -101, 3, (byte) i3});
                        break;
                    } else if (i2 == 2) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -101, 4, (byte) i3});
                        break;
                    } else if (i2 == 3) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -101, 22, (byte) i3});
                        break;
                    }
                    break;
                case 1:
                    if (i2 == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -101, 5, (byte) i3});
                        break;
                    } else if (i2 == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -101, 6, (byte) i3});
                        break;
                    } else if (i2 == 2) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -101, 23, (byte) i3});
                        break;
                    } else if (i2 == 3) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -101, 24, (byte) i3});
                        break;
                    }
                    break;
                case 2:
                    if (i2 == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -101, 8, (byte) i3});
                        break;
                    } else if (i2 == 4) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -101, 11, (byte) i3});
                        break;
                    }
                    break;
                case 3:
                    switch (i2) {
                        case 0:
                            CanbusMsgSender.sendMsg(new byte[]{22, -101, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) i3});
                            break;
                        case 1:
                            CanbusMsgSender.sendMsg(new byte[]{22, -101, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) i3});
                            break;
                        case 2:
                            CanbusMsgSender.sendMsg(new byte[]{22, -101, 14, (byte) i3});
                            break;
                        case 3:
                            CanbusMsgSender.sendMsg(new byte[]{22, -101, 15, (byte) i3});
                            break;
                        case 4:
                            CanbusMsgSender.sendMsg(new byte[]{22, -101, 16, (byte) i3});
                            break;
                        case 5:
                            CanbusMsgSender.sendMsg(new byte[]{22, -101, 17, (byte) i3});
                            break;
                        case 6:
                            CanbusMsgSender.sendMsg(new byte[]{22, -101, 25, (byte) i3});
                            break;
                    }
                case 4:
                    if (i2 == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -101, 18, (byte) i3});
                        break;
                    } else if (i2 == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -101, 19, (byte) i3});
                        break;
                    } else if (i2 == 2) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -101, 20, (byte) i3});
                        break;
                    } else if (i2 == 3) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -101, 21, (byte) i3});
                        break;
                    } else if (i2 == 4) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -101, 27, (byte) i3});
                        break;
                    }
                    break;
                case 5:
                    if (UiMgr.this.getCurrentCarId() != 0) {
                        if (i2 == 0) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -101, 1, (byte) i3});
                            break;
                        } else if (i2 == 1) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -101, 26, (byte) i3});
                            break;
                        } else if (i2 == 2) {
                            CanbusMsgSender.sendMsg(new byte[]{22, 107, 1, (byte) i3});
                            break;
                        }
                    } else if (i2 == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 110, (byte) i3, 0, 0, 0, 0, 0, 0, 0, 0, 0});
                        break;
                    } else if (!"1".equals(String.valueOf(MsgMgr.chargeModel))) {
                        UiMgr.this.showToast(R.string.only_reservation_charging_is_valid);
                        break;
                    } else if (i2 == 5) {
                        UiMgr uiMgr = UiMgr.this;
                        uiMgr.mCycelData = DataHandleUtils.setIntByteWithBit(uiMgr.mCycelData, 7, i3 == 1);
                        CanbusMsgSender.sendMsg(new byte[]{22, 110, 1, (byte) UiMgr.this.chargeStartTimeHourProgress, (byte) UiMgr.this.chargeStartTimeMinuteProgress, (byte) UiMgr.this.chargeEndTimeHourProgress, (byte) UiMgr.this.chargeEndTimeMinuteProgress, (byte) UiMgr.this.mCycelData, 0, 0, 0, 0});
                        break;
                    } else if (!"1".equals(String.valueOf(MsgMgr.recycleModel))) {
                        UiMgr.this.showToast(R.string.only_recycle_model_is_valid);
                        break;
                    } else {
                        switch (i2) {
                            case 6:
                                UiMgr uiMgr2 = UiMgr.this;
                                uiMgr2.mCycelData = DataHandleUtils.setIntByteWithBit(uiMgr2.mCycelData, 6, i3 == 1);
                                CanbusMsgSender.sendMsg(new byte[]{22, 110, 1, (byte) UiMgr.this.chargeStartTimeHourProgress, (byte) UiMgr.this.chargeStartTimeMinuteProgress, (byte) UiMgr.this.chargeEndTimeHourProgress, (byte) UiMgr.this.chargeEndTimeMinuteProgress, (byte) UiMgr.this.mCycelData, 0, 0, 0, 0});
                                break;
                            case 7:
                                UiMgr uiMgr3 = UiMgr.this;
                                uiMgr3.mCycelData = DataHandleUtils.setIntByteWithBit(uiMgr3.mCycelData, 5, i3 == 1);
                                CanbusMsgSender.sendMsg(new byte[]{22, 110, 1, (byte) UiMgr.this.chargeStartTimeHourProgress, (byte) UiMgr.this.chargeStartTimeMinuteProgress, (byte) UiMgr.this.chargeEndTimeHourProgress, (byte) UiMgr.this.chargeEndTimeMinuteProgress, (byte) UiMgr.this.mCycelData, 0, 0, 0, 0});
                                break;
                            case 8:
                                UiMgr uiMgr4 = UiMgr.this;
                                uiMgr4.mCycelData = DataHandleUtils.setIntByteWithBit(uiMgr4.mCycelData, 4, i3 == 1);
                                CanbusMsgSender.sendMsg(new byte[]{22, 110, 1, (byte) UiMgr.this.chargeStartTimeHourProgress, (byte) UiMgr.this.chargeStartTimeMinuteProgress, (byte) UiMgr.this.chargeEndTimeHourProgress, (byte) UiMgr.this.chargeEndTimeMinuteProgress, (byte) UiMgr.this.mCycelData, 0, 0, 0, 0});
                                break;
                            case 9:
                                UiMgr uiMgr5 = UiMgr.this;
                                uiMgr5.mCycelData = DataHandleUtils.setIntByteWithBit(uiMgr5.mCycelData, 3, i3 == 1);
                                CanbusMsgSender.sendMsg(new byte[]{22, 110, 1, (byte) UiMgr.this.chargeStartTimeHourProgress, (byte) UiMgr.this.chargeStartTimeMinuteProgress, (byte) UiMgr.this.chargeEndTimeHourProgress, (byte) UiMgr.this.chargeEndTimeMinuteProgress, (byte) UiMgr.this.mCycelData, 0, 0, 0, 0});
                                break;
                            case 10:
                                UiMgr uiMgr6 = UiMgr.this;
                                uiMgr6.mCycelData = DataHandleUtils.setIntByteWithBit(uiMgr6.mCycelData, 2, i3 == 1);
                                CanbusMsgSender.sendMsg(new byte[]{22, 110, 1, (byte) UiMgr.this.chargeStartTimeHourProgress, (byte) UiMgr.this.chargeStartTimeMinuteProgress, (byte) UiMgr.this.chargeEndTimeHourProgress, (byte) UiMgr.this.chargeEndTimeMinuteProgress, (byte) UiMgr.this.mCycelData, 0, 0, 0, 0});
                                break;
                            case 11:
                                UiMgr uiMgr7 = UiMgr.this;
                                uiMgr7.mCycelData = DataHandleUtils.setIntByteWithBit(uiMgr7.mCycelData, 1, i3 == 1);
                                CanbusMsgSender.sendMsg(new byte[]{22, 110, 1, (byte) UiMgr.this.chargeStartTimeHourProgress, (byte) UiMgr.this.chargeStartTimeMinuteProgress, (byte) UiMgr.this.chargeEndTimeHourProgress, (byte) UiMgr.this.chargeEndTimeMinuteProgress, (byte) UiMgr.this.mCycelData, 0, 0, 0, 0});
                                break;
                            case 12:
                                UiMgr uiMgr8 = UiMgr.this;
                                uiMgr8.mCycelData = DataHandleUtils.setIntByteWithBit(uiMgr8.mCycelData, 0, i3 == 1);
                                CanbusMsgSender.sendMsg(new byte[]{22, 110, 1, (byte) UiMgr.this.chargeStartTimeHourProgress, (byte) UiMgr.this.chargeStartTimeMinuteProgress, (byte) UiMgr.this.chargeEndTimeHourProgress, (byte) UiMgr.this.chargeEndTimeMinuteProgress, (byte) UiMgr.this.mCycelData, 0, 0, 0, 0});
                                break;
                        }
                    }
                    break;
                case 6:
                    if (UiMgr.this.getCurrentCarId() == 0) {
                        if (i2 == 0) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -101, 1, (byte) i3});
                            break;
                        } else if (i2 == 1) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -101, 26, (byte) i3});
                            break;
                        } else if (i2 == 2) {
                            CanbusMsgSender.sendMsg(new byte[]{22, 107, 1, (byte) i3});
                            break;
                        }
                    }
                    break;
                case 7:
                    if (i2 == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 109, 1, (byte) i3});
                        UiMgr uiMgr9 = UiMgr.this;
                        uiMgr9.getMsgMgr(uiMgr9.mContext).updateSettings(UiMgr.this.mContext, "EnergyRecycle", i, i2, i3);
                        break;
                    }
                    break;
            }
        }
    };
    private OnSettingItemSeekbarSelectListener onSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._248.UiMgr.16
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
        public void onClickItem(int i, int i2, int i3) {
            if (i == 2) {
                if (i2 == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -101, 7, (byte) i3});
                    return;
                } else if (i2 == 2) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -101, 9, (byte) i3});
                    return;
                } else {
                    if (i2 != 3) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -101, 10, (byte) i3});
                    return;
                }
            }
            if (i != 5) {
                return;
            }
            if (!"1".equals(String.valueOf(MsgMgr.chargeModel))) {
                UiMgr.this.showToast(R.string.only_reservation_charging_is_valid);
                return;
            }
            if (i2 == 1) {
                UiMgr.this.chargeStartTimeHourProgress = i3;
                CanbusMsgSender.sendMsg(new byte[]{22, 110, 1, (byte) UiMgr.this.chargeStartTimeHourProgress, (byte) UiMgr.this.chargeStartTimeMinuteProgress, (byte) UiMgr.this.chargeEndTimeHourProgress, (byte) UiMgr.this.chargeEndTimeMinuteProgress, 0, 0, 0, 0, 0});
                return;
            }
            if (i2 == 2) {
                UiMgr.this.chargeStartTimeMinuteProgress = i3;
                CanbusMsgSender.sendMsg(new byte[]{22, 110, 1, (byte) UiMgr.this.chargeStartTimeHourProgress, (byte) UiMgr.this.chargeStartTimeMinuteProgress, (byte) UiMgr.this.chargeEndTimeHourProgress, (byte) UiMgr.this.chargeEndTimeMinuteProgress, 0, 0, 0, 0, 0});
            } else if (i2 == 3) {
                UiMgr.this.chargeEndTimeHourProgress = i3;
                CanbusMsgSender.sendMsg(new byte[]{22, 110, 1, (byte) UiMgr.this.chargeStartTimeHourProgress, (byte) UiMgr.this.chargeStartTimeMinuteProgress, (byte) UiMgr.this.chargeEndTimeHourProgress, (byte) UiMgr.this.chargeEndTimeMinuteProgress, 0, 0, 0, 0, 0});
            } else {
                if (i2 != 4) {
                    return;
                }
                UiMgr.this.chargeEndTimeMinuteProgress = i3;
                CanbusMsgSender.sendMsg(new byte[]{22, 110, 1, (byte) UiMgr.this.chargeStartTimeHourProgress, (byte) UiMgr.this.chargeStartTimeMinuteProgress, (byte) UiMgr.this.chargeEndTimeHourProgress, (byte) UiMgr.this.chargeEndTimeMinuteProgress, 0, 0, 0, 0, 0});
            }
        }
    };
    private int mCycelData = 0;
    private int mDifferent = getCurrentCarId();

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        int i = this.mDifferent;
        if (i == 0) {
            removeFrontAirButton(context, 0, 2, 2);
            removeFrontAirButton(context, 3, 3, 3);
            return;
        }
        if (i == 1) {
            removeFrontAirButton(context, 0, 2, 2);
            removeSettingLeftItem(context, 5, 6);
            return;
        }
        if (i == 2) {
            removeSettingLeftItem(context, 5, 6);
            return;
        }
        if (i == 3) {
            removeFrontAirButton(context, 0, 2, 2);
            removeFrontAirButton(context, 3, 3, 3);
            removeSettingLeftItem(context, 5, 6);
        } else if (i == 4) {
            removeFrontAirButton(context, 3, 3, 3);
            removeSettingLeftItem(context, 5, 6);
        } else {
            if (i != 5) {
                return;
            }
            removeMainPrjBtnByName(this.mContext, MainAction.SETTING);
            removeMainPrjBtnByName(this.mContext, "air");
        }
    }

    public UiMgr(Context context) {
        this.mContext = context;
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
        settingUiSet.setOnSettingPageStatusListener(this.mOnSettingPageStatusListener);
        settingUiSet.setOnSettingItemSeekbarSelectListener(this.onSettingItemSeekbarSelectListener);
        AirPageUiSet airUiSet = getAirUiSet(context);
        airUiSet.getFrontArea().setOnAirSeatClickListener(this.onAirSeatClickListener);
        airUiSet.setOnAirPageStatusListener(this.mOnAirPageStatusListener);
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.monUpDownClickListenerLeft, null, this.monUpDownClickListenerRight});
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerTop, this.mOnAirBtnClickListenerCenter1, this.mOnAirBtnClickListenerCenter2, this.mOnAirBtnClickListenerButtom});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.setWindSpeedViewOnClickListener);
        airUiSet.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.mFrontLeftSeatHeatColdListener, this.mFrontRightSeatHeatColdListener});
        final ParkPageUiSet parkPageUiSet = getParkPageUiSet(context);
        parkPageUiSet.setOnBackCameraStatusListener(new OnBackCameraStatusListener() { // from class: com.hzbhd.canbus.car._248.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnBackCameraStatusListener
            public void addViewToWindows() {
                ArrayList arrayList = new ArrayList();
                int i = UiMgr.this.mDifferent;
                if (i == 1 || i == 4) {
                    arrayList.add(new ParkPageUiSet.Bean(0, "_248_close", ""));
                    arrayList.add(new ParkPageUiSet.Bean(0, "_248_front_panoramic", ""));
                    arrayList.add(new ParkPageUiSet.Bean(0, "_248_rear_panoramic", ""));
                    arrayList.add(new ParkPageUiSet.Bean(0, "_248_left_panoramic", ""));
                    arrayList.add(new ParkPageUiSet.Bean(0, "_248_right_panoramic", ""));
                } else if (i == 5) {
                    arrayList.add(new ParkPageUiSet.Bean(0, "_248_close", ""));
                    arrayList.add(new ParkPageUiSet.Bean(0, "_248_front_panoramic", ""));
                    arrayList.add(new ParkPageUiSet.Bean(0, "_248_front_right", ""));
                    arrayList.add(new ParkPageUiSet.Bean(0, "_248_rear_panoramic", ""));
                    arrayList.add(new ParkPageUiSet.Bean(0, "_248_horizontal_panoramic", ""));
                    arrayList.add(new ParkPageUiSet.Bean(0, "_248_vertical_panoramic", ""));
                    arrayList.add(new ParkPageUiSet.Bean(0, "_248_left_panoramic", ""));
                    arrayList.add(new ParkPageUiSet.Bean(0, "_248_right_panoramic", ""));
                } else {
                    arrayList = null;
                    parkPageUiSet.setShowPanoramic(false);
                }
                parkPageUiSet.setPanoramicBtnList(arrayList);
            }
        });
        parkPageUiSet.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._248.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public void onClickItem(int i) {
                String titleSrn = parkPageUiSet.getPanoramicBtnList().get(i).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "_248_front_right":
                        CanbusMsgSender.sendMsg(new byte[]{22, -14, 7, 2});
                        break;
                    case "_248_vertical_panoramic":
                        CanbusMsgSender.sendMsg(new byte[]{22, -14, 7, 5});
                        break;
                    case "_248_close":
                        CanbusMsgSender.sendMsg(new byte[]{22, -14, 7, 6});
                        break;
                    case "_248_right_panoramic":
                        CanbusMsgSender.sendMsg(new byte[]{22, -14, 7, 8});
                        break;
                    case "_248_horizontal_panoramic":
                        CanbusMsgSender.sendMsg(new byte[]{22, -14, 7, 4});
                        break;
                    case "_248_rear_panoramic":
                        CanbusMsgSender.sendMsg(new byte[]{22, -14, 7, 3});
                        break;
                    case "_248_left_panoramic":
                        CanbusMsgSender.sendMsg(new byte[]{22, -14, 7, 7});
                        break;
                    case "_248_front_panoramic":
                        CanbusMsgSender.sendMsg(new byte[]{22, -14, 7, 1});
                        break;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
        if (this.msgMgr == null) {
            this.msgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.msgMgr;
    }
}
