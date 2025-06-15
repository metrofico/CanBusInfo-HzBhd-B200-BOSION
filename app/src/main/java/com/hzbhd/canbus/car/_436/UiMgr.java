package com.hzbhd.canbus.car._436;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.ByteCompanionObject;


public class UiMgr extends AbstractUiMgr {
    Context mContext;
    MsgMgr mMsgMgr;
    byte noneData = 0;
    byte noneDataAlr = 0;
    private int windModeTag = 0;
    DecimalFormat towDecimal = new DecimalFormat("###0.00");
    DecimalFormat oneDecimal = new DecimalFormat("###0.0");
    DecimalFormat timeFormat = new DecimalFormat("00");
    OnSettingItemSelectListener onSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._436.UiMgr.1
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_436_Setting_function")) {
                UiMgr uiMgr2 = UiMgr.this;
                if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "_436_Setting_function", "_436_Setting_function_1")) {
                    byte b = (byte) (i3 + 248);
                    CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -106, -1, -1, -1, b, -1, -1, -1, -1, ByteCompanionObject.MIN_VALUE});
                    CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -106, -1, -1, -1, b, -1, -1, -1, -1, ByteCompanionObject.MIN_VALUE});
                }
                UiMgr uiMgr3 = UiMgr.this;
                if (i2 == uiMgr3.getSettingRightIndex(uiMgr3.mContext, "_436_Setting_function", "_436_Setting_function_2")) {
                    if (i3 == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -106, -1, -1, -1, 63, -1, -1, -1, -1, ByteCompanionObject.MIN_VALUE});
                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -106, -1, -1, -1, 63, -1, -1, -1, -1, ByteCompanionObject.MIN_VALUE});
                    }
                    if (i3 == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -106, -1, -1, -1, ByteCompanionObject.MAX_VALUE, -1, -1, -1, -1, ByteCompanionObject.MIN_VALUE});
                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -106, -1, -1, -1, ByteCompanionObject.MAX_VALUE, -1, -1, -1, -1, ByteCompanionObject.MIN_VALUE});
                    }
                }
                UiMgr uiMgr4 = UiMgr.this;
                if (i2 == uiMgr4.getSettingRightIndex(uiMgr4.mContext, "_436_Setting_function", "_436_Setting_function_3")) {
                    if (i3 == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 8, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 8, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 8, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 8, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 8, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 8, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                    } else if (i3 == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 16, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 16, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 16, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 16, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 16, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 16, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                    } else if (i3 == 2) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 24, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 24, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 24, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 24, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 24, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 24, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                    }
                }
                UiMgr uiMgr5 = UiMgr.this;
                if (i2 == uiMgr5.getSettingRightIndex(uiMgr5.mContext, "_436_Setting_function", "_436_Setting_function_4")) {
                    if (i3 == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 0, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 0, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 0, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 0, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 0, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 0, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                    } else if (i3 == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 4, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 4, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 4, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 4, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 4, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 4, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                    }
                }
                UiMgr uiMgr6 = UiMgr.this;
                if (i2 == uiMgr6.getSettingRightIndex(uiMgr6.mContext, "_436_Setting_function", "_436_Setting_function_4")) {
                    if (i3 == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -8, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -8, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -8, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -8, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -8, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -8, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                        return;
                    }
                    if (i3 != 1) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -6, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                    CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -6, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                    CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -6, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                    CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -6, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                    CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -6, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                    CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -6, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                }
            }
        }
    };
    OnAirSeatClickListener onAirSeatClickListener = new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._436.UiMgr.2
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onLeftSeatClick() {
            if (UiMgr.this.windModeTag != 0) {
                if (UiMgr.this.windModeTag != 1) {
                    if (UiMgr.this.windModeTag != 2) {
                        if (UiMgr.this.windModeTag == 3) {
                            UiMgr.this.sendAir(1, 20, 0, 0, 0, 0, 0, 0);
                            UiMgr.this.sendAir(1, 20, 0, 0, 0, 0, 0, 0);
                            UiMgr.this.sendAir(1, 16, 0, 0, 0, 0, 0, 0);
                            UiMgr.this.sendAir(1, 16, 0, 0, 0, 0, 0, 0);
                            UiMgr.this.windModeTag = 0;
                            return;
                        }
                        return;
                    }
                    UiMgr.this.sendAir(1, 12, 0, 0, 0, 0, 0, 0);
                    UiMgr.this.sendAir(1, 12, 0, 0, 0, 0, 0, 0);
                    UiMgr.this.sendAir(1, 8, 0, 0, 0, 0, 0, 0);
                    UiMgr.this.sendAir(1, 8, 0, 0, 0, 0, 0, 0);
                    UiMgr.this.windModeTag = 3;
                    return;
                }
                UiMgr.this.sendAir(1, 4, 0, 0, 0, 0, 0, 0);
                UiMgr.this.sendAir(1, 4, 0, 0, 0, 0, 0, 0);
                UiMgr.this.sendAir(1, 0, 0, 0, 0, 0, 0, 0);
                UiMgr.this.sendAir(1, 0, 0, 0, 0, 0, 0, 0);
                UiMgr.this.windModeTag = 2;
                return;
            }
            UiMgr.this.sendAir(1, 28, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(1, 28, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(1, 27, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(1, 27, 0, 0, 0, 0, 0, 0);
            UiMgr.this.windModeTag = 1;
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onRightSeatClick() {
            if (UiMgr.this.windModeTag != 0) {
                if (UiMgr.this.windModeTag != 1) {
                    if (UiMgr.this.windModeTag != 2) {
                        if (UiMgr.this.windModeTag == 3) {
                            UiMgr.this.sendAir(1, 20, 0, 0, 0, 0, 0, 0);
                            UiMgr.this.sendAir(1, 20, 0, 0, 0, 0, 0, 0);
                            UiMgr.this.sendAir(1, 16, 0, 0, 0, 0, 0, 0);
                            UiMgr.this.sendAir(1, 16, 0, 0, 0, 0, 0, 0);
                            UiMgr.this.windModeTag = 0;
                            return;
                        }
                        return;
                    }
                    UiMgr.this.sendAir(1, 12, 0, 0, 0, 0, 0, 0);
                    UiMgr.this.sendAir(1, 12, 0, 0, 0, 0, 0, 0);
                    UiMgr.this.sendAir(1, 8, 0, 0, 0, 0, 0, 0);
                    UiMgr.this.sendAir(1, 8, 0, 0, 0, 0, 0, 0);
                    UiMgr.this.windModeTag = 3;
                    return;
                }
                UiMgr.this.sendAir(1, 4, 0, 0, 0, 0, 0, 0);
                UiMgr.this.sendAir(1, 4, 0, 0, 0, 0, 0, 0);
                UiMgr.this.sendAir(1, 0, 0, 0, 0, 0, 0, 0);
                UiMgr.this.sendAir(1, 0, 0, 0, 0, 0, 0, 0);
                UiMgr.this.windModeTag = 2;
                return;
            }
            UiMgr.this.sendAir(1, 28, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(1, 28, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(1, 27, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(1, 27, 0, 0, 0, 0, 0, 0);
            UiMgr.this.windModeTag = 1;
        }
    };
    OnAirBtnClickListener topAir = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._436.UiMgr.3
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAir(3, 0, 0, 0, 0, 0, 0, 0);
                UiMgr.this.sendAir(3, 0, 0, 0, 0, 0, 0, 0);
                UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
                UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
                return;
            }
            if (i == 1) {
                UiMgr.this.sendAir(5, 0, 0, 0, 0, 0, 0, 0);
                UiMgr.this.sendAir(5, 0, 0, 0, 0, 0, 0, 0);
                UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
                UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
                return;
            }
            if (i != 2) {
                return;
            }
            UiMgr.this.sendAir(1, 2, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(1, 2, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
        }
    };
    OnAirBtnClickListener leftAir = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._436.UiMgr.4
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            UiMgr.this.sendAir(1, 1, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(1, 1, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
        }
    };
    OnAirBtnClickListener rightAir = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._436.UiMgr.5
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            UiMgr.this.sendAir(1, 64, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(1, 64, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
        }
    };
    OnAirBtnClickListener bottomAir = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._436.UiMgr.6
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAir(9, 0, 0, 0, 0, 0, 0, 0);
                UiMgr.this.sendAir(9, 0, 0, 0, 0, 0, 0, 0);
                UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
                UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
                return;
            }
            if (i == 1) {
                UiMgr.this.sendAir(1, 128, 0, 0, 0, 0, 0, 0);
                UiMgr.this.sendAir(1, 128, 0, 0, 0, 0, 0, 0);
                UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
                UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
                return;
            }
            if (i != 2) {
                return;
            }
            UiMgr.this.sendAir(1, 0, 1, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(1, 0, 1, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
        }
    };
    OnAirTemperatureUpDownClickListener centerTemp = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._436.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAir(65, 0, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(65, 0, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAir(129, 0, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(129, 0, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
        }
    };
    OnAirWindSpeedUpDownClickListener windSpeedControl = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._436.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            UiMgr.this.sendAir(33, 0, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(33, 0, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            UiMgr.this.sendAir(17, 0, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(17, 0, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
        }
    };
    int eachId = getEachId();
    int differentId = getCurrentCarId();

    public UiMgr(Context context) {
        this.mContext = context;
        getSettingUiSet(this.mContext).setOnSettingItemSelectListener(this.onSettingItemSelectListener);
        AirPageUiSet airUiSet = getAirUiSet(this.mContext);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.topAir, this.leftAir, this.rightAir, this.bottomAir});
        airUiSet.getFrontArea().setOnAirSeatClickListener(this.onAirSeatClickListener);
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{null, this.centerTemp, null});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.windSpeedControl);
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
    }

    public void sendAirCmd0x394(int i, int i2, int i3, int i4) {
        byte[] bArr = new byte[8];
        for (int i5 = 0; i5 < 8; i5++) {
            if (i5 == i) {
                bArr[i] = (byte) (i4 << i2);
            } else {
                bArr[i5] = this.noneDataAlr;
            }
        }
        CanbusMsgSender.sendMsg(SplicingByte(new byte[]{22, 0, 0, 3, -108}, SplicingByte(bArr, new byte[]{(byte) ((i + 5) << 4)})));
    }

    public void sendAir(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 3, -108, (byte) i, (byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6, (byte) i7, (byte) i8, -64});
    }

    public void sendMediaInfo(int i, int i2, int i3, int i4, byte[] bArr) {
        CanbusMsgSender.sendMsg(SplicingByte(new byte[]{22, 0, 0, 3, -106, (byte) i, (byte) i2, (byte) i3, (byte) i4, (byte) bArr.length}, bArr));
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

    private byte[] SplicingByte(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    private MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }
}
