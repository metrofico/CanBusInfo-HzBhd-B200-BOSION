package com.hzbhd.canbus.car._259;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    private Context mContext;
    private ParkPageUiSet mParkPageUiSet;
    private OnSettingItemSelectListener mOnSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._259.UiMgr.1
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            if (i != 0) {
                if (i2 == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 111, 16, (byte) i3, -1, -1});
                    return;
                }
                if (i2 == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 111, 17, (byte) i3, -1, -1});
                    return;
                }
                if (i2 == 2) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 111, 18, (byte) i3, -1, -1});
                    return;
                }
                if (i2 != 3) {
                    if (i2 == 4 && UiMgr.this.getCurrentCarId() == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 111, 20, (byte) i3, -1, -1});
                        return;
                    }
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, 111, 19, (byte) i3, -1, -1});
                return;
            }
            if (i2 == 0) {
                if (UiMgr.this.getCurrentCarId() == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 111, 1, (byte) i3, -1, -1});
                    return;
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, 111, 2, (byte) i3, -1, -1});
                    return;
                }
            }
            if (i2 == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, 111, 3, (byte) i3, -1, -1});
                return;
            }
            if (i2 == 2) {
                CanbusMsgSender.sendMsg(new byte[]{22, 111, 4, (byte) i3, -1, -1});
            } else if (i2 == 3) {
                CanbusMsgSender.sendMsg(new byte[]{22, 111, 5, (byte) i3, -1, -1});
            } else {
                if (i2 != 4) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, 111, 6, (byte) i3, -1, -1});
            }
        }
    };
    private OnAirBtnClickListener mOnAirTopBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._259.UiMgr.2
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 5, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 5, 0});
                return;
            }
            if (i == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 25, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 25, 0});
            } else if (i == 2) {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 6, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 6, 0});
            } else {
                if (i != 3) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 41, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 41, 0});
            }
        }
    };
    private OnAirBtnClickListener mOnAirBottomRightBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._259.UiMgr.3
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 1, 0});
            }
        }
    };
    private OnAirBtnClickListener mOnAirBottomBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._259.UiMgr.4
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 2, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 2, 0});
                return;
            }
            if (i == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 4, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 4, 0});
                return;
            }
            if (i == 2) {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 7, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 7, 0});
            } else if (i == 3) {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 21, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 21, 0});
            } else {
                if (i != 4) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 22, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 22, 0});
            }
        }
    };
    private OnAirWindSpeedUpDownClickListener mSetWindSpeedViewOnClickListener = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._259.UiMgr.5
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
    private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._259.UiMgr.6
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
    private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._259.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            if (UiMgr.this.getCurrentCarId() == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 0});
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 15, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 15, 0});
            }
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            if (UiMgr.this.getCurrentCarId() == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 14, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 14, 0});
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 16, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 16, 0});
            }
        }
    };

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        int currentCarId = getCurrentCarId();
        if (currentCarId == 0) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"geely_auto_close_the_window_after_locking", "geely_close_the_sunroof_after_locking", "geely_electronic_assist_mode", "geely_remote_lock_feedback", "geely_daytime_running_lights"});
        } else {
            if (currentCarId != 1) {
                return;
            }
            removeSettingRightItemByNameList(this.mContext, new String[]{"geely_fortification_prompt_type", "skylight"});
        }
    }

    public UiMgr(Context context) {
        this.mContext = context;
        getSettingUiSet(context).setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
        this.mParkPageUiSet = getParkPageUiSet(context);
        AirPageUiSet airUiSet = getAirUiSet(context);
        if (getCurrentCarId() == 1) {
            airUiSet.getFrontArea().setShowPmValue(false);
        } else {
            airUiSet.getFrontArea().setShowPmValue(true);
        }
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirTopBtnClickListener, null, this.mOnAirBottomRightBtnClickListener, this.mOnAirBottomBtnClickListener});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.mSetWindSpeedViewOnClickListener);
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mTempSetViewOnUpDownClickListenerLeft, null, this.mTempSetViewOnUpDownClickListenerRight});
    }

    public void setShowRadar(boolean z) {
        this.mParkPageUiSet.setShowRadar(z);
    }
}
