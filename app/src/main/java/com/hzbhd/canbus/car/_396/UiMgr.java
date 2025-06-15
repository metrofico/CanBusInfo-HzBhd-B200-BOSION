package com.hzbhd.canbus.car._396;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.Iterator;
import java.util.List;
import nfore.android.bt.res.NfDef;


public class UiMgr extends AbstractUiMgr {
    Context mContext;
    MsgMgr mMsgMgr;
    private OnAirSeatHeatColdMinPlusClickListener onMinPlusClickListenerLeft = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._396.UiMgr.5
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr.this.SenAirMsg((byte) 17);
        }
    };
    private OnAirSeatHeatColdMinPlusClickListener onMinPlusClickListenerRight = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._396.UiMgr.6
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr.this.SenAirMsg((byte) 18);
        }
    };
    private OnAirBtnClickListener mOnAirBtnClickListenerTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._396.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.SenAirMsg((byte) 2);
            } else {
                if (i != 1) {
                    return;
                }
                UiMgr.this.SenAirMsg((byte) 7);
            }
        }
    };
    private OnAirBtnClickListener mOnAirBtnClickListenerLeft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._396.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            UiMgr.this.SenAirMsg((byte) 5);
        }
    };
    private OnAirBtnClickListener mOnAirBtnClickListenerRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._396.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            UiMgr.this.SenAirMsg((byte) 6);
        }
    };
    private OnAirBtnClickListener mOnAirBtnClickListenerBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._396.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.SenAirMsg((byte) 4);
            } else if (i == 1) {
                UiMgr.this.SenAirMsg((byte) 1);
            } else {
                if (i != 2) {
                    return;
                }
                UiMgr.this.SenAirMsg((byte) 3);
            }
        }
    };
    OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._396.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            UiMgr.this.SenAirMsg(NfDef.AVRCP_EVENT_ID_UIDS_CHANGED);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            UiMgr.this.SenAirMsg((byte) 11);
        }
    };
    int model = 0;
    OnAirSeatClickListener onAirSeatClickListener = new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._396.UiMgr.12
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onLeftSeatClick() {
            if (UiMgr.this.model == 0) {
                UiMgr.this.SenAirMsg((byte) 9);
                UiMgr.this.model = 1;
                return;
            }
            if (UiMgr.this.model == 1) {
                UiMgr.this.SenAirMsg((byte) 10);
                UiMgr.this.model = 2;
            } else if (UiMgr.this.model == 2) {
                UiMgr.this.SenAirMsg((byte) 27);
                UiMgr.this.model = 3;
            } else if (UiMgr.this.model == 3) {
                UiMgr.this.SenAirMsg((byte) 28);
                UiMgr.this.model = 0;
            }
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onRightSeatClick() {
            if (UiMgr.this.model == 0) {
                UiMgr.this.SenAirMsg((byte) 9);
                UiMgr.this.model = 1;
                return;
            }
            if (UiMgr.this.model == 1) {
                UiMgr.this.SenAirMsg((byte) 10);
                UiMgr.this.model = 2;
            } else if (UiMgr.this.model == 2) {
                UiMgr.this.SenAirMsg((byte) 27);
                UiMgr.this.model = 3;
            } else if (UiMgr.this.model == 3) {
                UiMgr.this.SenAirMsg((byte) 28);
                UiMgr.this.model = 0;
            }
        }
    };
    OnAirPageStatusListener onAirPageStatusListener = new OnAirPageStatusListener() { // from class: com.hzbhd.canbus.car._396.UiMgr.13
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener
        public void onStatusChange(int i) {
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._396.UiMgr.14
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.SenAirMsg(NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.SenAirMsg((byte) 14);
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._396.UiMgr.15
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.SenAirMsg((byte) 15);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.SenAirMsg((byte) 16);
        }
    };
    int eachId = getEachId();
    int differentId = getCurrentCarId();

    public UiMgr(Context context) {
        this.mContext = context;
        AirPageUiSet airUiSet = getAirUiSet(this.mContext);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerTop, this.mOnAirBtnClickListenerLeft, this.mOnAirBtnClickListenerRight, this.mOnAirBtnClickListenerBottom});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.onAirWindSpeedUpDownClickListener);
        airUiSet.getFrontArea().setOnAirSeatClickListener(this.onAirSeatClickListener);
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerLeft, null, this.mOnUpDownClickListenerRight});
        airUiSet.getFrontArea().setOnAirPageStatusListener(this.onAirPageStatusListener);
        airUiSet.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.onMinPlusClickListenerLeft, this.onMinPlusClickListenerRight});
        SettingPageUiSet settingUiSet = getSettingUiSet(this.mContext);
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._396.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                UiMgr uiMgr = UiMgr.this;
                if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_396_air")) {
                    UiMgr uiMgr2 = UiMgr.this;
                    if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "_396_air", "_396_air1")) {
                        UiMgr.this.sendAirSetting0x3A(1, i3);
                    }
                    UiMgr uiMgr3 = UiMgr.this;
                    if (i2 == uiMgr3.getSettingRightIndex(uiMgr3.mContext, "_396_air", "_396_air2")) {
                        UiMgr.this.sendAirSetting0x3A(4, i3);
                    }
                    UiMgr uiMgr4 = UiMgr.this;
                    if (i2 == uiMgr4.getSettingRightIndex(uiMgr4.mContext, "_396_air", "_396_air3")) {
                        UiMgr.this.sendAirSetting0x3A(10, i3);
                    }
                    UiMgr uiMgr5 = UiMgr.this;
                    if (i2 == uiMgr5.getSettingRightIndex(uiMgr5.mContext, "_396_air", "_396_air4")) {
                        UiMgr.this.sendAirSetting0x3A(16, i3);
                    }
                }
                UiMgr uiMgr6 = UiMgr.this;
                if (i == uiMgr6.getSettingLeftIndexes(uiMgr6.mContext, "_396_setting")) {
                    UiMgr uiMgr7 = UiMgr.this;
                    if (i2 == uiMgr7.getSettingRightIndex(uiMgr7.mContext, "_396_setting", "_396_setting0")) {
                        UiMgr.this.sendAirSetting0x6F(3, i3);
                    }
                    UiMgr uiMgr8 = UiMgr.this;
                    if (i2 == uiMgr8.getSettingRightIndex(uiMgr8.mContext, "_396_setting", "_396_setting1")) {
                        UiMgr.this.sendAirSetting0x6F(4, i3);
                    }
                    UiMgr uiMgr9 = UiMgr.this;
                    if (i2 == uiMgr9.getSettingRightIndex(uiMgr9.mContext, "_396_setting", "_396_setting2")) {
                        UiMgr.this.sendAirSetting0x6F(6, i3);
                    }
                    UiMgr uiMgr10 = UiMgr.this;
                    if (i2 == uiMgr10.getSettingRightIndex(uiMgr10.mContext, "_396_setting", "_396_setting3")) {
                        UiMgr.this.sendAirSetting0x6F(8, i3);
                    }
                    UiMgr uiMgr11 = UiMgr.this;
                    if (i2 == uiMgr11.getSettingRightIndex(uiMgr11.mContext, "_396_setting", "_396_setting4")) {
                        UiMgr.this.sendAirSetting0x6F(9, i3);
                    }
                    UiMgr uiMgr12 = UiMgr.this;
                    if (i2 == uiMgr12.getSettingRightIndex(uiMgr12.mContext, "_396_setting", "_396_setting5")) {
                        UiMgr.this.sendAirSetting0x6F(18, i3);
                    }
                    UiMgr uiMgr13 = UiMgr.this;
                    if (i2 == uiMgr13.getSettingRightIndex(uiMgr13.mContext, "_396_setting", "_396_setting6")) {
                        UiMgr.this.sendAirSetting0x6F(20, i3);
                    }
                    UiMgr uiMgr14 = UiMgr.this;
                    if (i2 == uiMgr14.getSettingRightIndex(uiMgr14.mContext, "_396_setting", "_396_setting7")) {
                        UiMgr.this.sendAirSetting0x6F(22, i3);
                    }
                    UiMgr uiMgr15 = UiMgr.this;
                    if (i2 == uiMgr15.getSettingRightIndex(uiMgr15.mContext, "_396_setting", "_396_setting8")) {
                        UiMgr.this.sendAirSetting0x6F(23, i3);
                    }
                    UiMgr uiMgr16 = UiMgr.this;
                    if (i2 == uiMgr16.getSettingRightIndex(uiMgr16.mContext, "_396_setting", "_396_setting10")) {
                        UiMgr.this.sendAirSetting0x6F(32, i3);
                    }
                    UiMgr uiMgr17 = UiMgr.this;
                    if (i2 == uiMgr17.getSettingRightIndex(uiMgr17.mContext, "_396_setting", "_396_setting11")) {
                        UiMgr.this.sendAirSetting0x6F(35, i3);
                    }
                    UiMgr uiMgr18 = UiMgr.this;
                    if (i2 == uiMgr18.getSettingRightIndex(uiMgr18.mContext, "_396_setting", "_396_setting12")) {
                        UiMgr.this.sendAirSetting0x6F(36, i3);
                    }
                    UiMgr uiMgr19 = UiMgr.this;
                    if (i2 == uiMgr19.getSettingRightIndex(uiMgr19.mContext, "_396_setting", "_396_setting13")) {
                        UiMgr.this.sendAirSetting0x6F(37, i3);
                    }
                    UiMgr uiMgr20 = UiMgr.this;
                    if (i2 == uiMgr20.getSettingRightIndex(uiMgr20.mContext, "_396_setting", "_396_setting14")) {
                        UiMgr.this.sendAirSetting0x6F(38, i3);
                    }
                    UiMgr uiMgr21 = UiMgr.this;
                    if (i2 == uiMgr21.getSettingRightIndex(uiMgr21.mContext, "_396_setting", "_396_setting15")) {
                        UiMgr.this.sendAirSetting0x6F(39, i3);
                    }
                    UiMgr uiMgr22 = UiMgr.this;
                    if (i2 == uiMgr22.getSettingRightIndex(uiMgr22.mContext, "_396_setting", "_396_setting16")) {
                        UiMgr.this.sendAirSetting0x6F(40, i3);
                    }
                    UiMgr uiMgr23 = UiMgr.this;
                    if (i2 == uiMgr23.getSettingRightIndex(uiMgr23.mContext, "_396_setting", "_396_setting17")) {
                        UiMgr.this.sendAirSetting0x6F(41, i3);
                    }
                    UiMgr uiMgr24 = UiMgr.this;
                    if (i2 == uiMgr24.getSettingRightIndex(uiMgr24.mContext, "_396_setting", "_396_setting18")) {
                        UiMgr.this.sendAirSetting0x6F(42, i3);
                    }
                    UiMgr uiMgr25 = UiMgr.this;
                    if (i2 == uiMgr25.getSettingRightIndex(uiMgr25.mContext, "_396_setting", "_396_setting19")) {
                        UiMgr.this.sendAirSetting0x6F(58, i3);
                    }
                    UiMgr uiMgr26 = UiMgr.this;
                    if (i2 == uiMgr26.getSettingRightIndex(uiMgr26.mContext, "_396_setting", "_396_setting20")) {
                        UiMgr.this.sendAirSetting0x6F(59, i3);
                    }
                    UiMgr uiMgr27 = UiMgr.this;
                    if (i2 == uiMgr27.getSettingRightIndex(uiMgr27.mContext, "_396_setting", "_396_setting21")) {
                        UiMgr.this.sendAirSetting0x6F(60, i3);
                    }
                    UiMgr uiMgr28 = UiMgr.this;
                    if (i2 == uiMgr28.getSettingRightIndex(uiMgr28.mContext, "_396_setting", "_396_setting22")) {
                        UiMgr.this.sendAirSetting0x6F(61, i3);
                    }
                    UiMgr uiMgr29 = UiMgr.this;
                    if (i2 == uiMgr29.getSettingRightIndex(uiMgr29.mContext, "_396_setting", "_396_setting23")) {
                        UiMgr.this.sendAirSetting0x6F(65, i3);
                    }
                    UiMgr uiMgr30 = UiMgr.this;
                    if (i2 == uiMgr30.getSettingRightIndex(uiMgr30.mContext, "_396_setting", "_396_setting24")) {
                        UiMgr.this.sendAirSetting0x6F(66, i3);
                    }
                    UiMgr uiMgr31 = UiMgr.this;
                    if (i2 == uiMgr31.getSettingRightIndex(uiMgr31.mContext, "_396_setting", "_396_setting25")) {
                        UiMgr.this.sendAirSetting0x6F(67, i3);
                    }
                    UiMgr uiMgr32 = UiMgr.this;
                    if (i2 == uiMgr32.getSettingRightIndex(uiMgr32.mContext, "_396_setting", "_396_setting26")) {
                        UiMgr.this.sendAirSetting0x6F(68, i3);
                    }
                    UiMgr uiMgr33 = UiMgr.this;
                    if (i2 == uiMgr33.getSettingRightIndex(uiMgr33.mContext, "_396_setting", "_396_setting28")) {
                        UiMgr.this.sendAirSetting0x6F(70, i3);
                    }
                    UiMgr uiMgr34 = UiMgr.this;
                    if (i2 == uiMgr34.getSettingRightIndex(uiMgr34.mContext, "_396_setting", "_396_setting29")) {
                        UiMgr.this.sendAirSetting0x6F(81, i3);
                    }
                    UiMgr uiMgr35 = UiMgr.this;
                    if (i2 == uiMgr35.getSettingRightIndex(uiMgr35.mContext, "_396_setting", "_396_setting30")) {
                        UiMgr.this.sendAirSetting0x6F(82, i3);
                    }
                    UiMgr uiMgr36 = UiMgr.this;
                    if (i2 == uiMgr36.getSettingRightIndex(uiMgr36.mContext, "_396_setting", "_396_setting31")) {
                        UiMgr.this.sendAirSetting0x6F(83, i3);
                    }
                    UiMgr uiMgr37 = UiMgr.this;
                    if (i2 == uiMgr37.getSettingRightIndex(uiMgr37.mContext, "_396_setting", "_396_setting33")) {
                        UiMgr.this.sendAirSetting0x6F(84, i3);
                    }
                    UiMgr uiMgr38 = UiMgr.this;
                    if (i2 == uiMgr38.getSettingRightIndex(uiMgr38.mContext, "_396_setting", "_396_setting34")) {
                        UiMgr.this.sendAirSetting0x6F(97, i3);
                    }
                    UiMgr uiMgr39 = UiMgr.this;
                    if (i2 == uiMgr39.getSettingRightIndex(uiMgr39.mContext, "_396_setting", "_396_setting35")) {
                        UiMgr.this.sendAirSetting0x6F(98, i3);
                    }
                    UiMgr uiMgr40 = UiMgr.this;
                    if (i2 == uiMgr40.getSettingRightIndex(uiMgr40.mContext, "_396_setting", "_396_setting36")) {
                        UiMgr.this.sendAirSetting0x6F(99, i3);
                    }
                    UiMgr uiMgr41 = UiMgr.this;
                    if (i2 == uiMgr41.getSettingRightIndex(uiMgr41.mContext, "_396_setting", "_396_setting37")) {
                        UiMgr.this.sendAirSetting0x6F(100, i3);
                    }
                    UiMgr uiMgr42 = UiMgr.this;
                    if (i2 == uiMgr42.getSettingRightIndex(uiMgr42.mContext, "_396_setting", "_396_setting38")) {
                        UiMgr.this.sendAirSetting0x6F(101, i3);
                    }
                    UiMgr uiMgr43 = UiMgr.this;
                    if (i2 == uiMgr43.getSettingRightIndex(uiMgr43.mContext, "_396_setting", "_396_setting39")) {
                        UiMgr.this.sendAirSetting0x6F(102, i3);
                    }
                    UiMgr uiMgr44 = UiMgr.this;
                    if (i2 == uiMgr44.getSettingRightIndex(uiMgr44.mContext, "_396_setting", "_396_setting40")) {
                        UiMgr.this.sendAirSetting0x6F(103, i3);
                    }
                    UiMgr uiMgr45 = UiMgr.this;
                    if (i2 == uiMgr45.getSettingRightIndex(uiMgr45.mContext, "_396_setting", "_396_setting41")) {
                        UiMgr.this.sendAirSetting0x6F(104, i3);
                    }
                    UiMgr uiMgr46 = UiMgr.this;
                    if (i2 == uiMgr46.getSettingRightIndex(uiMgr46.mContext, "_396_setting", "_396_setting42")) {
                        UiMgr.this.sendAirSetting0x6F(105, i3);
                    }
                    UiMgr uiMgr47 = UiMgr.this;
                    if (i2 == uiMgr47.getSettingRightIndex(uiMgr47.mContext, "_396_setting", "_396_setting43")) {
                        UiMgr.this.sendAirSetting0x6F(106, i3);
                    }
                    UiMgr uiMgr48 = UiMgr.this;
                    if (i2 == uiMgr48.getSettingRightIndex(uiMgr48.mContext, "_396_setting", "_396_setting44")) {
                        UiMgr.this.sendAirSetting0x6F(107, i3);
                    }
                    UiMgr uiMgr49 = UiMgr.this;
                    if (i2 == uiMgr49.getSettingRightIndex(uiMgr49.mContext, "_396_setting", "_396_setting45")) {
                        UiMgr.this.sendAirSetting0x6F(108, i3);
                    }
                    UiMgr uiMgr50 = UiMgr.this;
                    if (i2 == uiMgr50.getSettingRightIndex(uiMgr50.mContext, "_396_setting", "_396_setting46")) {
                        UiMgr.this.sendAirSetting0x6F(109, i3);
                    }
                    UiMgr uiMgr51 = UiMgr.this;
                    if (i2 == uiMgr51.getSettingRightIndex(uiMgr51.mContext, "_396_setting", "_396_setting47")) {
                        UiMgr.this.sendAirSetting0x6F(110, i3);
                    }
                    UiMgr uiMgr52 = UiMgr.this;
                    if (i2 == uiMgr52.getSettingRightIndex(uiMgr52.mContext, "_396_setting", "_396_setting48")) {
                        UiMgr.this.sendAirSetting0x6F(111, i3);
                    }
                    UiMgr uiMgr53 = UiMgr.this;
                    if (i2 == uiMgr53.getSettingRightIndex(uiMgr53.mContext, "_396_setting", "_396_setting49")) {
                        UiMgr.this.sendAirSetting0x6F(112, i3);
                    }
                    UiMgr uiMgr54 = UiMgr.this;
                    if (i2 == uiMgr54.getSettingRightIndex(uiMgr54.mContext, "_396_setting", "_396_setting50")) {
                        UiMgr.this.sendAirSetting0x6F(113, i3);
                    }
                    UiMgr uiMgr55 = UiMgr.this;
                    if (i2 == uiMgr55.getSettingRightIndex(uiMgr55.mContext, "_396_setting", "_396_setting51")) {
                        UiMgr.this.sendAirSetting0x6F(114, i3);
                    }
                }
                UiMgr uiMgr56 = UiMgr.this;
                if (i == uiMgr56.getSettingLeftIndexes(uiMgr56.mContext, "_396_panoro")) {
                    UiMgr uiMgr57 = UiMgr.this;
                    if (i2 == uiMgr57.getSettingRightIndex(uiMgr57.mContext, "_396_panoro", "_396_panoro0")) {
                        if (i3 == 0) {
                            UiMgr uiMgr58 = UiMgr.this;
                            uiMgr58.getMsgMgr(uiMgr58.mContext).hideP360Button();
                        } else {
                            UiMgr uiMgr59 = UiMgr.this;
                            uiMgr59.getMsgMgr(uiMgr59.mContext).showP360Button();
                        }
                        UiMgr uiMgr60 = UiMgr.this;
                        uiMgr60.getMsgMgr(uiMgr60.mContext).updateSettings(i, i2, i3);
                    }
                    UiMgr uiMgr61 = UiMgr.this;
                    if (i2 == uiMgr61.getSettingRightIndex(uiMgr61.mContext, "_396_panoro", "_396_panoro1")) {
                        UiMgr.this.sendPanoro0xF2(11, i3);
                    }
                    UiMgr uiMgr62 = UiMgr.this;
                    if (i2 == uiMgr62.getSettingRightIndex(uiMgr62.mContext, "_396_panoro", "_396_panoro2")) {
                        UiMgr.this.sendPanoro0xF2(12, i3);
                    }
                    UiMgr uiMgr63 = UiMgr.this;
                    if (i2 == uiMgr63.getSettingRightIndex(uiMgr63.mContext, "_396_panoro", "_396_panoro3")) {
                        UiMgr.this.sendPanoro0xF2(18, i3);
                    }
                }
                UiMgr uiMgr64 = UiMgr.this;
                if (i == uiMgr64.getSettingLeftIndexes(uiMgr64.mContext, "_396_setting_language")) {
                    UiMgr uiMgr65 = UiMgr.this;
                    if (i2 == uiMgr65.getSettingRightIndex(uiMgr65.mContext, "_396_setting_language", "_396_setting_language")) {
                        UiMgr.this.sendLanguage0x9A(1, i3 + 1);
                        UiMgr uiMgr66 = UiMgr.this;
                        uiMgr66.getMsgMgr(uiMgr66.mContext).updateSettings(i, i2, i3);
                    }
                }
            }
        });
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._396.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public void onClickItem(int i, int i2, int i3) {
                UiMgr uiMgr = UiMgr.this;
                if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_396_setting")) {
                    UiMgr uiMgr2 = UiMgr.this;
                    if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "_396_setting", "_396_setting9")) {
                        UiMgr.this.sendAirSetting0x6F(24, i3);
                    }
                    UiMgr uiMgr3 = UiMgr.this;
                    if (i2 == uiMgr3.getSettingRightIndex(uiMgr3.mContext, "_396_setting", "_396_setting27")) {
                        UiMgr.this.sendAirSetting0x6F(69, i3);
                    }
                    UiMgr uiMgr4 = UiMgr.this;
                    if (i2 == uiMgr4.getSettingRightIndex(uiMgr4.mContext, "_396_setting", "_396_setting52")) {
                        UiMgr.this.sendAirSetting0x6F(115, i3 * 5);
                    }
                }
            }
        });
        settingUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._396.UiMgr.3
            @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
            public void onConfirmClick(int i, int i2) {
                UiMgr uiMgr = UiMgr.this;
                if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_396_setting")) {
                    UiMgr uiMgr2 = UiMgr.this;
                    if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "_396_setting", "_396_setting53")) {
                        UiMgr.this.sendAirSetting0x6F(169, 1);
                    }
                    UiMgr uiMgr3 = UiMgr.this;
                    if (i2 == uiMgr3.getSettingRightIndex(uiMgr3.mContext, "_396_setting", "_396_setting54")) {
                        UiMgr.this.sendAirSetting0x6F(NfDef.STATE_RESP_AND_HOLD, 1);
                    }
                }
            }
        });
        getParkPageUiSet(this.mContext).setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._396.UiMgr.4
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public void onClickItem(int i) {
                switch (i) {
                    case 0:
                        UiMgr.this.sendPanoro0xF2(16, 5);
                        break;
                    case 1:
                        UiMgr.this.sendPanoro0xF2(16, 6);
                        break;
                    case 2:
                        UiMgr.this.sendPanoro0xF2(16, 7);
                        break;
                    case 3:
                        UiMgr.this.sendPanoro0xF2(16, 8);
                        break;
                    case 4:
                        UiMgr.this.sendPanoro0xF2(16, 10);
                        break;
                    case 5:
                        UiMgr.this.sendPanoro0xF2(16, 11);
                        break;
                    case 6:
                        UiMgr.this.sendPanoro0xF2(16, 12);
                        break;
                    case 7:
                        UiMgr.this.sendPanoro0xF2(16, 13);
                        break;
                    case 8:
                        UiMgr.this.sendPanoro0xF2(17, 0);
                        break;
                    case 9:
                        UiMgr.this.sendPanoro0xF2(17, 1);
                        break;
                    case 10:
                        UiMgr.this.sendPanoro0xF2(15, 0);
                        break;
                }
            }
        });
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void SenAirMsg(byte b) {
        CanbusMsgSender.sendMsg(new byte[]{22, 61, b, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, 61, b, 0});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirSetting0x3A(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, 58, (byte) i, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirSetting0x6F(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, 111, (byte) i, (byte) i2, 0, 0});
    }

    public void sendPanoro0xF2(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -14, (byte) i, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendLanguage0x9A(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -102, (byte) i, (byte) i2});
    }

    public void sendTime0xCB(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        CanbusMsgSender.sendMsg(new byte[]{22, -53, (byte) i, (byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6, (byte) i7, (byte) i8, (byte) i9, (byte) i10});
    }

    public void activeRequest(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -102, 5, 1, (byte) i});
    }

    public void sendCarType0x24(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte) i, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED});
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
