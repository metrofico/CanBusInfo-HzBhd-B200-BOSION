package com.hzbhd.canbus.car._238;

import android.content.Context;
import android.content.Intent;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.interfaces.OnTirePageStatusListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.SharePreUtil;



public class UiMgr extends AbstractUiMgr {
    private Context mContext;
    private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._238.UiMgr.3
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.airBtnClick(3, 1);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.airBtnClick(3, 0);
        }
    };
    private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._238.UiMgr.4
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.airBtnClick(3, 1);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.airBtnClick(3, 0);
        }
    };
    private OnAirWindSpeedUpDownClickListener mSetWindSpeedViewOnClickListener = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._238.UiMgr.5
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            UiMgr.this.airBtnClick(1, 0);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            UiMgr.this.airBtnClick(1, 1);
        }
    };
    private OnAirBtnClickListener mOnAirTopBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._238.UiMgr.6
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.airBtnClick(0, 4);
            } else {
                if (i != 1) {
                    return;
                }
                UiMgr.this.airBtnClick(1, 2);
            }
        }
    };
    private OnAirBtnClickListener mOnAirBottomLeftBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._238.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.airBtnClick(0, 0);
            }
        }
    };
    private OnAirBtnClickListener mOnAirBottomRightBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._238.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                LogUtil.showLog("Power");
                UiMgr.this.airBtnClick(0, 7);
            }
        }
    };
    private OnAirBtnClickListener mOnAirBottomBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._238.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.airBtnClick(0, 1);
            } else if (i == 1) {
                UiMgr.this.airBtnClick(0, 5);
            } else {
                if (i != 2) {
                    return;
                }
                UiMgr.this.airBtnClick(2, 0);
            }
        }
    };
    private OnAirSeatHeatColdMinPlusClickListener mFrontLeftSeatHeatColdListener = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._238.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
        }
    };
    private OnAirSeatHeatColdMinPlusClickListener mFrontRightSeatHeatColdListener = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._238.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
        }
    };
    private OnAirPageStatusListener mOnAirPageStatusListener = new OnAirPageStatusListener() { // from class: com.hzbhd.canbus.car._238.UiMgr.12
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener
        public void onStatusChange(int i) {
            if (i == 1) {
                UiMgr.this.sendData(new byte[]{22, -112, 36});
                UiMgr.this.sendData(new byte[]{22, -112, 54});
            }
        }
    };
    private OnSettingPageStatusListener mOnSettingPageStatusListener = new OnSettingPageStatusListener() { // from class: com.hzbhd.canbus.car._238.UiMgr.13
        @Override // com.hzbhd.canbus.interfaces.OnSettingPageStatusListener
        public void onStatusChange(int i) {
            if (i == 0) {
                UiMgr.this.sendData(new byte[]{22, -112, Byte.MAX_VALUE});
                UiMgr.this.mContext.sendBroadcast(new Intent(com.hzbhd.canbus.car._237.MsgMgr.UPDATE_SETTING_ACTION));
            }
        }
    };
    private OnSettingItemSelectListener mOnSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._238.UiMgr.14
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            if (i == 0 && i2 == 0) {
                byte[] bArr = new byte[4];
                bArr[0] = 22;
                bArr[1] = -56;
                bArr[2] = 0;
                bArr[3] = (byte) (i3 == 0 ? 1 : 0);
                CanbusMsgSender.sendMsg(bArr);
                SharePreUtil.setStringValue(UiMgr.this.mContext, Constant.SHARE_PRE_IS_USE_F_UNIT, i3 + "");
                UiMgr.this.mContext.sendBroadcast(new Intent(com.hzbhd.canbus.car._237.MsgMgr.UPDATE_SETTING_ACTION));
                CanbusMsgSender.sendMsg(new byte[]{22, -112, 36});
                CanbusMsgSender.sendMsg(new byte[]{22, -112, 54});
            }
        }
    };

    public UiMgr(Context context) {
        this.mContext = context;
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
        settingUiSet.setOnSettingPageStatusListener(this.mOnSettingPageStatusListener);
        AirPageUiSet airUiSet = getAirUiSet(context);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirTopBtnClickListener, this.mOnAirBottomLeftBtnClickListener, this.mOnAirBottomRightBtnClickListener, this.mOnAirBottomBtnClickListener});
        airUiSet.setOnAirPageStatusListener(this.mOnAirPageStatusListener);
        airUiSet.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.mFrontLeftSeatHeatColdListener, this.mFrontRightSeatHeatColdListener});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.mSetWindSpeedViewOnClickListener);
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mTempSetViewOnUpDownClickListenerLeft, null, this.mTempSetViewOnUpDownClickListenerRight});
        airUiSet.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._238.UiMgr.1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                UiMgr.this.airBtnClick(0, 6);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
                UiMgr.this.airBtnClick(0, 6);
            }
        });
        getTireUiSet(context).setOnTirePageStatusListener(new OnTirePageStatusListener() { // from class: com.hzbhd.canbus.car._238.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnTirePageStatusListener
            public void onStatusChange(int i) {
                if (i == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -112, 96});
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void airBtnClick(int i, int i2) {
        if (i == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -57, (byte) DataHandleUtils.setIntByteWithBit(0, i2, true)});
            CanbusMsgSender.sendMsg(new byte[]{22, -57, (byte) DataHandleUtils.setIntByteWithBit(0, i2, false)});
            return;
        }
        if (i == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, (byte) DataHandleUtils.setIntByteWithBit(0, i2, true)});
            CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, (byte) DataHandleUtils.setIntByteWithBit(0, i2, false)});
        } else if (i == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, 0, (byte) DataHandleUtils.setIntByteWithBit(0, i2, true)});
            CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, 0, (byte) DataHandleUtils.setIntByteWithBit(0, i2, false)});
        } else {
            if (i != 3) {
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, 0, 0, (byte) DataHandleUtils.setIntByteWithBit(0, i2, true)});
            CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, 0, 0, (byte) DataHandleUtils.setIntByteWithBit(0, i2, false)});
        }
    }
}
