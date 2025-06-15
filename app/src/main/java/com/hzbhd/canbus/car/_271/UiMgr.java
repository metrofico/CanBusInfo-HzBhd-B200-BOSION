package com.hzbhd.canbus.car._271;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;


public class UiMgr extends AbstractUiMgr {
    private Context mContext;
    private ParkPageUiSet mParkPageUiSet;
    private OnSettingItemSelectListener mOnSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._271.UiMgr.1
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            if (UiMgr.this.getCurrentCarId() != 0) {
                if (i != 0) {
                    if (i != 1) {
                        return;
                    }
                    if (i2 == 0) {
                        UiMgr.this.onSettingSender(5, i3);
                        return;
                    } else {
                        UiMgr.this.onSettingSender(6, i3);
                        return;
                    }
                }
                if (i2 == 1) {
                    UiMgr.this.onSettingSender(2, i3);
                    return;
                } else {
                    if (i2 != 2) {
                        return;
                    }
                    UiMgr.this.onSettingSender(3, i3);
                    return;
                }
            }
            if (i == 0) {
                if (i2 == 1) {
                    UiMgr.this.onSettingSender(2, i3);
                    return;
                } else {
                    if (i2 != 2) {
                        return;
                    }
                    UiMgr.this.onSettingSender(3, i3);
                    return;
                }
            }
            if (i == 1) {
                if (i2 == 0) {
                    UiMgr.this.onSettingSender(4, i3);
                    return;
                } else if (i2 == 1) {
                    UiMgr.this.onSettingSender(5, i3);
                    return;
                } else {
                    if (i2 != 2) {
                        return;
                    }
                    UiMgr.this.onSettingSender(6, i3);
                    return;
                }
            }
            if (i != 2) {
                return;
            }
            if (i2 == 0) {
                UiMgr.this.onSettingSender(7, i3);
                return;
            }
            if (i2 == 1) {
                UiMgr.this.onSettingSender(8, i3);
            } else if (i2 == 2) {
                UiMgr.this.onSettingSender(9, i3);
            } else {
                if (i2 != 3) {
                    return;
                }
                UiMgr.this.onSettingSender(10, i3);
            }
        }
    };
    private OnSettingItemSeekbarSelectListener mOnSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._271.UiMgr.2
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
        public void onClickItem(int i, int i2, int i3) {
            if (UiMgr.this.getCurrentCarId() != 0) {
                if (i == 0 && i2 == 0) {
                    UiMgr.this.onSettingSender(1, i3);
                    return;
                }
                return;
            }
            if (i == 0 && i2 == 0) {
                UiMgr.this.onSettingSender(1, i3);
            }
            if (i == 2 && i2 == 4) {
                UiMgr.this.onSettingSender(11, i3);
            }
        }
    };
    private OnAirBtnClickListener mSetOnAirBtnClickListeners1 = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._271.UiMgr.3
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.onAirSender(5);
            } else if (i == 1) {
                UiMgr.this.onAirSender(21);
            } else {
                if (i != 2) {
                    return;
                }
                UiMgr.this.onAirSender(22);
            }
        }
    };
    private OnAirBtnClickListener mSetOnAirBtnClickListeners2 = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._271.UiMgr.4
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr.this.onAirSender(4);
        }
    };
    private OnAirBtnClickListener mSetOnAirBtnClickListeners4 = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._271.UiMgr.5
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.onAirSender(2);
            } else {
                if (i != 1) {
                    return;
                }
                UiMgr.this.onAirSender(7);
            }
        }
    };
    private OnAirWindSpeedUpDownClickListener msetSetWindSpeedViewOnClickListener = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._271.UiMgr.6
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            UiMgr.this.onAirSender(12);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            UiMgr.this.onAirSender(11);
        }
    };
    private OnAirTemperatureUpDownClickListener mSetTempSetViewOnUpDownClickListeners1 = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._271.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.onAirSender(13);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.onAirSender(14);
        }
    };

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        int currentCarId = getCurrentCarId();
        if (currentCarId == 0) {
            removeFrontAirButton(context, 0, 2, 2);
            removeMainPrjBtn(context, 0, 2);
            removeSettingRightItem(context, 0, 2, 2);
        } else {
            if (currentCarId != 1) {
                return;
            }
            removeFrontAirButton(context, 1, 0, 2);
            removeSettingLeftItem(context, 2, 2);
            removeSettingRightItem(context, 1, 0, 2);
        }
    }

    public UiMgr(Context context) {
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
        settingUiSet.setOnSettingItemSeekbarSelectListener(this.mOnSettingItemSeekbarSelectListener);
        AirPageUiSet airUiSet = getAirUiSet(context);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mSetOnAirBtnClickListeners1, this.mSetOnAirBtnClickListeners2, null, this.mSetOnAirBtnClickListeners4});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.msetSetWindSpeedViewOnClickListener);
        FrontArea frontArea = airUiSet.getFrontArea();
        OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListener = this.mSetTempSetViewOnUpDownClickListeners1;
        frontArea.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{onAirTemperatureUpDownClickListener, null, onAirTemperatureUpDownClickListener});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAirSender(int i) {
        byte b = (byte) i;
        CanbusMsgSender.sendMsg(new byte[]{22, 61, b, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, 61, b, 0});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSettingSender(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, 111, (byte) i, (byte) i2, -1, -1});
    }
}
