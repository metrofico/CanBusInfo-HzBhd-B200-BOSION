package com.hzbhd.canbus.car._239;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;


public class UiMgr extends AbstractUiMgr {
    private Context mContext;
    private int mData = 0;
    private OnAirWindSpeedUpDownClickListener setWindSpeedViewOnClickListener = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._239.UiMgr.2
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            CanbusMsgSender.sendMsg(new byte[]{22, -125, -96, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            CanbusMsgSender.sendMsg(new byte[]{22, -125, -96, 1});
        }
    };
    private OnAirTemperatureUpDownClickListener mUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._239.UiMgr.3
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            CanbusMsgSender.sendMsg(new byte[]{22, -125, -95, 1});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            CanbusMsgSender.sendMsg(new byte[]{22, -125, -95, 0});
        }
    };
    private OnAirTemperatureUpDownClickListener mUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._239.UiMgr.4
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            CanbusMsgSender.sendMsg(new byte[]{22, -125, -94, 1});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            CanbusMsgSender.sendMsg(new byte[]{22, -125, -94, 0});
        }
    };
    private OnAirBtnClickListener mOnAirBtnClickListenerTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._239.UiMgr.5
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                if (GeneralAirData.front_defog) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, -93, 4});
                }
            } else {
                if (i != 1) {
                    return;
                }
                if (GeneralAirData.rear_defog) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, -89, 0});
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, -89, 1});
                }
            }
        }
    };
    private OnAirBtnClickListener mOnAirBtnClickListenerCenter1 = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._239.UiMgr.6
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            if (GeneralAirData.ac) {
                CanbusMsgSender.sendMsg(new byte[]{22, -125, -92, 0});
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, -125, -92, 1});
            }
        }
    };
    private OnAirBtnClickListener mOnAirBtnClickListenerCenter2 = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._239.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            if (GeneralAirData.in_out_cycle) {
                CanbusMsgSender.sendMsg(new byte[]{22, -125, -91, 0});
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, -125, -91, 1});
            }
        }
    };
    private OnAirBtnClickListener mOnAirBtnClickListenerButtom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._239.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
        }
    };
    private OnAirPageStatusListener mOnAirPageStatusListener = new OnAirPageStatusListener() { // from class: com.hzbhd.canbus.car._239.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener
        public void onStatusChange(int i) {
            if (i == 1) {
                UiMgr.this.sendData(new byte[]{22, -126, 35});
            }
        }
    };
    private OnSettingPageStatusListener mOnSettingPageStatusListener = new OnSettingPageStatusListener() { // from class: com.hzbhd.canbus.car._239.UiMgr.10
        @Override // com.hzbhd.canbus.interfaces.OnSettingPageStatusListener
        public void onStatusChange(int i) {
            if (i == 0) {
                UiMgr.this.sendData(new byte[]{22, -126, 49});
            }
        }
    };
    private OnSettingItemSelectListener mOnSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._239.UiMgr.11
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            if (i == 0 && i2 == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 1, (byte) i3});
            }
        }
    };

    public UiMgr(Context context) {
        this.mContext = context;
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
        settingUiSet.setOnSettingPageStatusListener(this.mOnSettingPageStatusListener);
        AirPageUiSet airUiSet = getAirUiSet(context);
        airUiSet.setOnAirPageStatusListener(this.mOnAirPageStatusListener);
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mUpDownClickListenerLeft, null, this.mUpDownClickListenerRight});
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerTop, this.mOnAirBtnClickListenerCenter1, this.mOnAirBtnClickListenerCenter2, this.mOnAirBtnClickListenerButtom});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.setWindSpeedViewOnClickListener);
        airUiSet.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._239.UiMgr.1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                UiMgr.this.seatClick();
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
                UiMgr.this.seatClick();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void seatClick() {
        int i = this.mData;
        if (i == 0) {
            this.mData = 1;
        } else if (i == 1) {
            this.mData = 2;
        } else if (i == 2) {
            this.mData = 3;
        } else if (i == 3) {
            this.mData = 4;
        } else if (i == 4) {
            this.mData = 0;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -125, -93, (byte) this.mData});
    }
}
