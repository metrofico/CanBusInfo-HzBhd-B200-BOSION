package com.hzbhd.canbus.car._382;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    Context mContext;
    MsgMgr mMsgMgr;
    OnAirPageStatusListener onAirPageStatusListener = new OnAirPageStatusListener() { // from class: com.hzbhd.canbus.car._382.UiMgr.1
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener
        public void onStatusChange(int i) {
            UiMgr.this.activeRequest(49);
        }
    };
    int windModelState = 0;
    OnAirSeatClickListener onAirSeatClickListener = new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._382.UiMgr.2
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onLeftSeatClick() {
            if (UiMgr.this.windModelState == 0) {
                UiMgr.this.windModelState = 1;
                UiMgr.this.sendAirData0xE0(3, 1);
                UiMgr.this.sendAirData0xE0(4, 1);
                UiMgr.this.sendAirData0xE0(5, 1);
                return;
            }
            if (UiMgr.this.windModelState == 1) {
                UiMgr.this.windModelState = 2;
                UiMgr.this.sendAirData0xE0(3, 1);
                UiMgr.this.sendAirData0xE0(4, 1);
                UiMgr.this.sendAirData0xE0(5, 0);
                return;
            }
            if (UiMgr.this.windModelState == 2) {
                UiMgr.this.windModelState = 3;
                UiMgr.this.sendAirData0xE0(3, 1);
                UiMgr.this.sendAirData0xE0(4, 0);
                UiMgr.this.sendAirData0xE0(5, 0);
                return;
            }
            UiMgr.this.windModelState = 0;
            UiMgr.this.sendAirData0xE0(3, 0);
            UiMgr.this.sendAirData0xE0(4, 0);
            UiMgr.this.sendAirData0xE0(5, 0);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onRightSeatClick() {
            if (UiMgr.this.windModelState == 0) {
                UiMgr.this.windModelState = 1;
                UiMgr.this.sendAirData0xE0(3, 1);
                UiMgr.this.sendAirData0xE0(4, 1);
                UiMgr.this.sendAirData0xE0(5, 1);
                return;
            }
            if (UiMgr.this.windModelState == 1) {
                UiMgr.this.windModelState = 2;
                UiMgr.this.sendAirData0xE0(3, 1);
                UiMgr.this.sendAirData0xE0(4, 1);
                UiMgr.this.sendAirData0xE0(5, 0);
                return;
            }
            if (UiMgr.this.windModelState == 2) {
                UiMgr.this.windModelState = 3;
                UiMgr.this.sendAirData0xE0(3, 1);
                UiMgr.this.sendAirData0xE0(4, 0);
                UiMgr.this.sendAirData0xE0(5, 0);
                return;
            }
            UiMgr.this.windModelState = 0;
            UiMgr.this.sendAirData0xE0(3, 0);
            UiMgr.this.sendAirData0xE0(4, 0);
            UiMgr.this.sendAirData0xE0(5, 0);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._382.UiMgr.3
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                if (GeneralAirData.in_out_cycle) {
                    UiMgr.this.sendAirData0xE0(1, 1);
                    return;
                } else {
                    UiMgr.this.sendAirData0xE0(1, 0);
                    return;
                }
            }
            if (i != 1) {
                return;
            }
            if (GeneralAirData.auto_wind_lv == 0) {
                UiMgr.this.sendAirData0xE0(2, 1);
            } else if (GeneralAirData.auto_wind_lv == 1) {
                UiMgr.this.sendAirData0xE0(2, 2);
            } else {
                UiMgr.this.sendAirData0xE0(2, 0);
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._382.UiMgr.4
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            if (GeneralAirData.steering_wheel_heating) {
                UiMgr.this.sendAirData0xE0(10, 0);
            } else {
                UiMgr.this.sendAirData0xE0(10, 1);
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._382.UiMgr.5
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            if (GeneralAirData.front_defog) {
                UiMgr.this.sendAirData0xE0(11, 0);
            } else {
                UiMgr.this.sendAirData0xE0(11, 1);
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._382.UiMgr.6
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            if (GeneralAirData.power) {
                UiMgr.this.sendAirData0xE0(6, 0);
            } else {
                UiMgr.this.sendAirData0xE0(6, 1);
            }
        }
    };
    OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._382.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            if (GeneralAirData.front_wind_level == 0) {
                UiMgr.this.sendAirData0xE0(7, 7);
            } else {
                UiMgr.this.sendAirData0xE0(7, GeneralAirData.front_wind_level - 1);
            }
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            if (GeneralAirData.front_wind_level == 7) {
                UiMgr.this.sendAirData0xE0(7, 0);
            } else {
                UiMgr.this.sendAirData0xE0(7, GeneralAirData.front_wind_level + 1);
            }
        }
    };
    int defaultTmpValueLeft = 42;
    OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._382.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            if (UiMgr.this.defaultTmpValueLeft == 62) {
                UiMgr.this.sendAirData0xE0(8, 255);
                return;
            }
            UiMgr.this.defaultTmpValueLeft += 2;
            UiMgr uiMgr = UiMgr.this;
            uiMgr.sendAirData0xE0(8, uiMgr.defaultTmpValueLeft);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            if (UiMgr.this.defaultTmpValueLeft == 34) {
                UiMgr.this.sendAirData0xE0(8, com.hzbhd.canbus.car._464.MsgMgr.DVD_MODE);
                return;
            }
            UiMgr uiMgr = UiMgr.this;
            uiMgr.defaultTmpValueLeft -= 2;
            UiMgr uiMgr2 = UiMgr.this;
            uiMgr2.sendAirData0xE0(8, uiMgr2.defaultTmpValueLeft);
        }
    };
    int defaultTmpRight = 42;
    OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._382.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            if (UiMgr.this.defaultTmpRight == 62) {
                UiMgr.this.sendAirData0xE0(9, 255);
                return;
            }
            UiMgr.this.defaultTmpRight += 2;
            UiMgr uiMgr = UiMgr.this;
            uiMgr.sendAirData0xE0(9, uiMgr.defaultTmpRight);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            if (UiMgr.this.defaultTmpRight == 34) {
                UiMgr.this.sendAirData0xE0(9, com.hzbhd.canbus.car._464.MsgMgr.DVD_MODE);
                return;
            }
            UiMgr uiMgr = UiMgr.this;
            uiMgr.defaultTmpRight -= 2;
            UiMgr uiMgr2 = UiMgr.this;
            uiMgr2.sendAirData0xE0(9, uiMgr2.defaultTmpRight);
        }
    };
    OnSettingItemSelectListener onSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._382.UiMgr.10
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_382_car_tyres")) {
                UiMgr uiMgr2 = UiMgr.this;
                if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "_382_car_tyres", "_382_car_tyres1")) {
                    UiMgr.this.send0x4BTyresInfo(2, i3);
                }
            }
            UiMgr uiMgr3 = UiMgr.this;
            if (i == uiMgr3.getSettingLeftIndexes(uiMgr3.mContext, "_382_car_auxiliary")) {
                UiMgr uiMgr4 = UiMgr.this;
                if (i2 == uiMgr4.getSettingRightIndex(uiMgr4.mContext, "_382_car_auxiliary", "_382_car_auxiliary1")) {
                    UiMgr.this.send0x4D(1, i3);
                }
            }
            UiMgr uiMgr5 = UiMgr.this;
            if (i == uiMgr5.getSettingLeftIndexes(uiMgr5.mContext, "_382_car_light")) {
                UiMgr uiMgr6 = UiMgr.this;
                if (i2 == uiMgr6.getSettingRightIndex(uiMgr6.mContext, "_382_car_light", "_382_car_light1")) {
                    UiMgr.this.send0x6D(19, i3);
                }
                UiMgr uiMgr7 = UiMgr.this;
                if (i2 == uiMgr7.getSettingRightIndex(uiMgr7.mContext, "_382_car_light", "_382_car_light2")) {
                    UiMgr.this.send0x6D(18, i3);
                }
                UiMgr uiMgr8 = UiMgr.this;
                if (i2 == uiMgr8.getSettingRightIndex(uiMgr8.mContext, "_382_car_light", "_382_car_light3")) {
                    UiMgr.this.send0x6D(16, i3);
                }
                UiMgr uiMgr9 = UiMgr.this;
                if (i2 == uiMgr9.getSettingRightIndex(uiMgr9.mContext, "_382_car_light", "_382_car_light4")) {
                    UiMgr.this.send0x6D(13, i3 + 1);
                }
                UiMgr uiMgr10 = UiMgr.this;
                if (i2 == uiMgr10.getSettingRightIndex(uiMgr10.mContext, "_382_car_light", "_382_car_light5")) {
                    UiMgr.this.send0x6D(14, i3);
                }
            }
            UiMgr uiMgr11 = UiMgr.this;
            if (i == uiMgr11.getSettingLeftIndexes(uiMgr11.mContext, "_382_window")) {
                UiMgr uiMgr12 = UiMgr.this;
                if (i2 == uiMgr12.getSettingRightIndex(uiMgr12.mContext, "_382_window", "_382_window1")) {
                    UiMgr.this.send0x6F(1, i3);
                }
                UiMgr uiMgr13 = UiMgr.this;
                if (i2 == uiMgr13.getSettingRightIndex(uiMgr13.mContext, "_382_window", "_382_window2")) {
                    UiMgr.this.send0x6F(2, i3);
                }
                UiMgr uiMgr14 = UiMgr.this;
                if (i2 == uiMgr14.getSettingRightIndex(uiMgr14.mContext, "_382_window", "_382_window3")) {
                    UiMgr.this.send0x6F(3, i3);
                }
                UiMgr uiMgr15 = UiMgr.this;
                if (i2 == uiMgr15.getSettingRightIndex(uiMgr15.mContext, "_382_window", "_382_window4")) {
                    UiMgr.this.send0x6F(5, i3);
                }
                UiMgr uiMgr16 = UiMgr.this;
                if (i2 == uiMgr16.getSettingRightIndex(uiMgr16.mContext, "_382_window", "_382_window5")) {
                    UiMgr.this.send0x6F(6, i3);
                }
                UiMgr uiMgr17 = UiMgr.this;
                if (i2 == uiMgr17.getSettingRightIndex(uiMgr17.mContext, "_382_window", "_382_window6")) {
                    UiMgr.this.send0x6F(7, i3);
                }
                UiMgr uiMgr18 = UiMgr.this;
                if (i2 == uiMgr18.getSettingRightIndex(uiMgr18.mContext, "_382_window", "_382_window7")) {
                    UiMgr.this.send0x6F(8, i3);
                }
            }
            UiMgr uiMgr19 = UiMgr.this;
            if (i == uiMgr19.getSettingLeftIndexes(uiMgr19.mContext, "_382_parking")) {
                UiMgr uiMgr20 = UiMgr.this;
                if (i2 == uiMgr20.getSettingRightIndex(uiMgr20.mContext, "_382_parking", "_382_parking1")) {
                    UiMgr.this.send0x7A(5, i3);
                }
            }
            UiMgr uiMgr21 = UiMgr.this;
            if (i == uiMgr21.getSettingLeftIndexes(uiMgr21.mContext, "_382_unit_setting")) {
                UiMgr uiMgr22 = UiMgr.this;
                if (i2 == uiMgr22.getSettingRightIndex(uiMgr22.mContext, "_382_unit_setting", "_382_unit_setting1")) {
                    if (i3 == 0) {
                        UiMgr.this.send0xCA(1, 2);
                    } else {
                        UiMgr.this.send0xCA(1, 1);
                    }
                }
                UiMgr uiMgr23 = UiMgr.this;
                if (i2 == uiMgr23.getSettingRightIndex(uiMgr23.mContext, "_382_unit_setting", "_382_unit_setting2")) {
                    if (i3 == 0) {
                        UiMgr.this.send0xCA(2, 2);
                    } else {
                        UiMgr.this.send0xCA(2, 1);
                    }
                }
                UiMgr uiMgr24 = UiMgr.this;
                if (i2 == uiMgr24.getSettingRightIndex(uiMgr24.mContext, "_382_unit_setting", "_382_unit_setting3")) {
                    if (i3 == 0) {
                        UiMgr.this.send0xCA(3, 2);
                    } else {
                        UiMgr.this.send0xCA(3, 1);
                    }
                }
                UiMgr uiMgr25 = UiMgr.this;
                if (i2 == uiMgr25.getSettingRightIndex(uiMgr25.mContext, "_382_unit_setting", "_382_unit_setting4")) {
                    UiMgr.this.send0xCA(4, i3 + 1);
                }
                UiMgr uiMgr26 = UiMgr.this;
                if (i2 == uiMgr26.getSettingRightIndex(uiMgr26.mContext, "_382_unit_setting", "_382_unit_setting5")) {
                    UiMgr.this.send0xCA(5, i3 + 1);
                }
                UiMgr uiMgr27 = UiMgr.this;
                if (i2 == uiMgr27.getSettingRightIndex(uiMgr27.mContext, "_382_unit_setting", "_382_unit_setting6")) {
                    UiMgr.this.send0xCA(6, i3 + 1);
                }
            }
            UiMgr uiMgr28 = UiMgr.this;
            if (i == uiMgr28.getSettingLeftIndexes(uiMgr28.mContext, "_382_athor")) {
                UiMgr uiMgr29 = UiMgr.this;
                if (i2 == uiMgr29.getSettingRightIndex(uiMgr29.mContext, "_382_athor", "_382_athor6")) {
                    UiMgr.this.send0xCC(2, i3);
                }
            }
        }
    };
    OnSettingItemSeekbarSelectListener onSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._382.UiMgr.11
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
        public void onClickItem(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_382_car_tyres")) {
                UiMgr uiMgr2 = UiMgr.this;
                if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "_382_car_tyres", "_382_car_tyres2")) {
                    UiMgr.this.send0x4BTyresInfo(3, i3);
                }
            }
            UiMgr uiMgr3 = UiMgr.this;
            if (i == uiMgr3.getSettingLeftIndexes(uiMgr3.mContext, "_382_car_light")) {
                UiMgr uiMgr4 = UiMgr.this;
                if (i2 == uiMgr4.getSettingRightIndex(uiMgr4.mContext, "_382_car_light", "_382_car_light6")) {
                    UiMgr.this.send0x6D(15, i3);
                }
                UiMgr uiMgr5 = UiMgr.this;
                if (i2 == uiMgr5.getSettingRightIndex(uiMgr5.mContext, "_382_car_light", "_382_car_light7")) {
                    UiMgr.this.send0x6D(17, i3);
                }
            }
            UiMgr uiMgr6 = UiMgr.this;
            if (i == uiMgr6.getSettingLeftIndexes(uiMgr6.mContext, "_382_parking")) {
                UiMgr uiMgr7 = UiMgr.this;
                if (i2 == uiMgr7.getSettingRightIndex(uiMgr7.mContext, "_382_parking", "_382_parking2")) {
                    UiMgr.this.send0x7A(1, i3);
                }
                UiMgr uiMgr8 = UiMgr.this;
                if (i2 == uiMgr8.getSettingRightIndex(uiMgr8.mContext, "_382_parking", "_382_parking3")) {
                    UiMgr.this.send0x7A(2, i3);
                }
                UiMgr uiMgr9 = UiMgr.this;
                if (i2 == uiMgr9.getSettingRightIndex(uiMgr9.mContext, "_382_parking", "_382_parking4")) {
                    UiMgr.this.send0x7A(3, i3);
                }
                UiMgr uiMgr10 = UiMgr.this;
                if (i2 == uiMgr10.getSettingRightIndex(uiMgr10.mContext, "_382_parking", "_382_parking5")) {
                    UiMgr.this.send0x7A(4, i3);
                }
            }
        }
    };
    OnSettingItemClickListener onSettingItemClickListener = new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._382.UiMgr.12
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
        public void onClickItem(int i, int i2) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_382_athor")) {
                UiMgr uiMgr2 = UiMgr.this;
                if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "_382_athor", "_382_athor8")) {
                    UiMgr uiMgr3 = UiMgr.this;
                    uiMgr3.getMsgMgr(uiMgr3.mContext).Toast("Reset success!");
                    UiMgr.this.send0xCC(1, 1);
                }
            }
        }
    };
    OnDriveDataPageStatusListener onDriveDataPageStatusListener = new OnDriveDataPageStatusListener() { // from class: com.hzbhd.canbus.car._382.UiMgr.13
        @Override // com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener
        public void onStatusChange(int i) {
            UiMgr.this.activeRequest(17);
        }
    };
    int eachId = getEachId();
    int differentId = getCurrentCarId();

    private void initUi() {
    }

    private void intiData() {
    }

    public UiMgr(Context context) {
        this.mContext = context;
        AirPageUiSet airUiSet = getAirUiSet(this.mContext);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
        airUiSet.getFrontArea().setOnAirSeatClickListener(this.onAirSeatClickListener);
        airUiSet.getFrontArea().setOnAirPageStatusListener(this.onAirPageStatusListener);
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.onAirTemperatureUpDownClickListenerLeft, null, this.onAirTemperatureUpDownClickListenerRight});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.onAirWindSpeedUpDownClickListener);
        SettingPageUiSet settingUiSet = getSettingUiSet(this.mContext);
        settingUiSet.setOnSettingItemSelectListener(this.onSettingItemSelectListener);
        settingUiSet.setOnSettingItemSeekbarSelectListener(this.onSettingItemSeekbarSelectListener);
        settingUiSet.setOnSettingItemClickListener(this.onSettingItemClickListener);
        getDriverDataPageUiSet(this.mContext).setOnDriveDataPageStatusListener(this.onDriveDataPageStatusListener);
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        initUi();
        intiData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirData0xE0(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, 58, (byte) i, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void send0x4BTyresInfo(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, 75, (byte) i, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void send0x4D(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, 77, (byte) i, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void send0x6D(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, 109, (byte) i, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void send0x6F(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, 111, (byte) i, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void send0x7A(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, 122, (byte) i, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void send0xCA(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -54, (byte) i, (byte) i2});
    }

    public void send0xCBTimeInfo(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        CanbusMsgSender.sendMsg(new byte[]{22, -53, (byte) i, (byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6, (byte) i7, (byte) i8, (byte) i9, (byte) i10});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void send0xCC(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -52, (byte) i, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void activeRequest(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, 106, 1, (byte) i});
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

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }

    private int getDecimalFrom8Bit(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return Integer.parseInt(i + "" + i2 + "" + i3 + "" + i4 + "" + i5 + "" + i6 + "" + i7 + "" + i8, 2);
    }

    public boolean isLandscape() {
        return this.mContext.getResources().getConfiguration().orientation == 2;
    }

    public boolean isPortrait() {
        return this.mContext.getResources().getConfiguration().orientation == 1;
    }
}
