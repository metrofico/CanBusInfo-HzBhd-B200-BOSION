package com.hzbhd.canbus.car._237;

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
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.interfaces.OnTirePageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.SharePreUtil;

import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
    int ProgressLightness;
    int Selectpos;
    private Context mContext;
    MsgMgr msgMgr;
    String Ambient_light_Lightness = "Ambient_light_Lightness";
    String Ambient_light_mode1 = "Ambient_light_mode1";
    String Temperature_Unit = "Temperature_Unit";
    int ProgressModel = 0;
    private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._237.UiMgr.3
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.airBtnClick(3, 1);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.airBtnClick(3, 0);
        }
    };
    private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._237.UiMgr.4
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.airBtnClick(3, 1);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.airBtnClick(3, 0);
        }
    };
    private OnAirWindSpeedUpDownClickListener mSetWindSpeedViewOnClickListener = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._237.UiMgr.5
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            UiMgr.this.airBtnClick(1, 0);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            UiMgr.this.airBtnClick(1, 1);
        }
    };
    private OnAirBtnClickListener mOnAirTopBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._237.UiMgr.6
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
    private OnAirBtnClickListener mOnAirBottomLeftBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._237.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.airBtnClick(0, 0);
            }
        }
    };
    private OnAirBtnClickListener mOnAirBottomRightBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._237.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                LogUtil.showLog("Power");
                UiMgr.this.airBtnClick(0, 7);
            }
        }
    };
    private OnAirBtnClickListener mOnAirBottomBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._237.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.airBtnClick(0, 1);
                return;
            }
            if (i == 1) {
                UiMgr.this.airBtnClick(0, 5);
            } else {
                if (i != 2) {
                    return;
                }
                LogUtil.showLog("in out");
                UiMgr.this.airBtnClick(2, 0);
            }
        }
    };
    private OnAirSeatHeatColdMinPlusClickListener mFrontLeftSeatHeatColdListener = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._237.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
        }
    };
    private OnAirSeatHeatColdMinPlusClickListener mFrontRightSeatHeatColdListener = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._237.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
        }
    };
    private OnAirPageStatusListener mOnAirPageStatusListener = new OnAirPageStatusListener() { // from class: com.hzbhd.canbus.car._237.UiMgr.12
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener
        public void onStatusChange(int i) {
            if (i == 1) {
                UiMgr.this.sendData(new byte[]{22, -112, 36});
                UiMgr.this.sendData(new byte[]{22, -112, 54});
            }
        }
    };
    private OnSettingPageStatusListener mOnSettingPageStatusListener = new OnSettingPageStatusListener() { // from class: com.hzbhd.canbus.car._237.UiMgr.13
        @Override // com.hzbhd.canbus.interfaces.OnSettingPageStatusListener
        public void onStatusChange(int i) {
            if (i == 0) {
                UiMgr.this.sendData(new byte[]{22, -112, Byte.MAX_VALUE});
                UiMgr.this.mContext.sendBroadcast(new Intent(MsgMgr.UPDATE_SETTING_ACTION));
            }
        }
    };
    private OnSettingItemSeekbarSelectListener mOnSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._237.UiMgr.14
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
        public void onClickItem(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "car_set")) {
                UiMgr uiMgr2 = UiMgr.this;
                if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "car_set", "_253_Ambient_light_Lightness")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -56, 1, (byte) i3});
                    UiMgr.this.ProgressLightness = i3;
                    UiMgr uiMgr3 = UiMgr.this;
                    uiMgr3.getMsgMgr(uiMgr3.mContext).updateSettings(UiMgr.this.mContext, i, i2, UiMgr.this.Ambient_light_Lightness, i3);
                    return;
                }
                UiMgr uiMgr4 = UiMgr.this;
                if (i2 == uiMgr4.getSettingRightIndex(uiMgr4.mContext, "car_set", "_253_Ambient_light_mode1")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -56, 2, (byte) i3});
                    UiMgr.this.ProgressModel = i3;
                    UiMgr uiMgr5 = UiMgr.this;
                    uiMgr5.getMsgMgr(uiMgr5.mContext).updateSettings(UiMgr.this.mContext, i, i2, UiMgr.this.Ambient_light_mode1, i3);
                }
            }
        }
    };
    private OnSettingItemSelectListener mOnSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._237.UiMgr.15
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
                CanbusMsgSender.sendMsg(new byte[]{22, -56, 0, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, -56, 0, 0});
                UiMgr uiMgr = UiMgr.this;
                uiMgr.getMsgMgr(uiMgr.mContext).updateSettings(UiMgr.this.mContext, i, i2, UiMgr.this.Temperature_Unit, i3);
                UiMgr.this.Selectpos = i3;
            }
        }
    };

    public UiMgr(Context context) {
        this.mContext = context;
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
        settingUiSet.setOnSettingPageStatusListener(this.mOnSettingPageStatusListener);
        settingUiSet.setOnSettingItemSeekbarSelectListener(this.mOnSettingItemSeekbarSelectListener);
        AirPageUiSet airUiSet = getAirUiSet(context);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirTopBtnClickListener, this.mOnAirBottomLeftBtnClickListener, this.mOnAirBottomRightBtnClickListener, this.mOnAirBottomBtnClickListener});
        airUiSet.setOnAirPageStatusListener(this.mOnAirPageStatusListener);
        airUiSet.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.mFrontLeftSeatHeatColdListener, this.mFrontRightSeatHeatColdListener});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.mSetWindSpeedViewOnClickListener);
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mTempSetViewOnUpDownClickListenerLeft, null, this.mTempSetViewOnUpDownClickListenerRight});
        airUiSet.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._237.UiMgr.1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                UiMgr.this.airBtnClick(0, 6);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
                UiMgr.this.airBtnClick(0, 6);
            }
        });
        getTireUiSet(context).setOnTirePageStatusListener(new OnTirePageStatusListener() { // from class: com.hzbhd.canbus.car._237.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnTirePageStatusListener
            public void onStatusChange(int i) {
                if (i == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -112, 96});
                }
            }
        });
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        initData();
    }

    private void initData() {
        if (!getMsgMgr(this.mContext).is404("car_set", "_253_Ambient_light_Lightness")) {
            MsgMgr msgMgr = getMsgMgr(this.mContext);
            Context context = this.mContext;
            msgMgr.updateSettings(context, getSettingLeftIndexes(context, "car_set"), getSettingRightIndex(this.mContext, "car_set", "_253_Ambient_light_Lightness"), this.Ambient_light_Lightness, this.ProgressLightness);
        }
        if (!getMsgMgr(this.mContext).is404("car_set", "Ambient_light_mode1")) {
            MsgMgr msgMgr2 = getMsgMgr(this.mContext);
            Context context2 = this.mContext;
            msgMgr2.updateSettings(context2, getSettingLeftIndexes(context2, "car_set"), getSettingRightIndex(this.mContext, "car_set", "Ambient_light_mode1"), this.Ambient_light_mode1, this.ProgressModel);
        }
        if (getMsgMgr(this.mContext).is404("car_set", "temperature_unit")) {
            return;
        }
        MsgMgr msgMgr3 = getMsgMgr(this.mContext);
        Context context3 = this.mContext;
        msgMgr3.updateSettings(context3, getSettingLeftIndexes(context3, "car_set"), getSettingRightIndex(this.mContext, "car_set", "temperature_unit"), this.Temperature_Unit, this.Selectpos);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void airBtnClick(int i, int i2) {
        if (i == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -57, (byte) DataHandleUtils.setIntByteWithBit(0, i2, true), 0, 0, 0});
            CanbusMsgSender.sendMsg(new byte[]{22, -57, (byte) DataHandleUtils.setIntByteWithBit(0, i2, false), 0, 0, 0});
            return;
        }
        if (i == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, (byte) DataHandleUtils.setIntByteWithBit(0, i2, true), 0, 0});
            CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, (byte) DataHandleUtils.setIntByteWithBit(0, i2, false), 0, 0});
        } else if (i == 2) {
            LogUtil.showLog("here");
            CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, 0, (byte) DataHandleUtils.setIntByteWithBit(0, i2, true), 0});
            CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, 0, (byte) DataHandleUtils.setIntByteWithBit(0, i2, false), 0});
        } else {
            if (i != 3) {
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, 0, 0, (byte) DataHandleUtils.setIntByteWithBit(0, i2, true)});
            CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, 0, 0, (byte) DataHandleUtils.setIntByteWithBit(0, i2, false)});
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
        if (this.msgMgr == null) {
            this.msgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.msgMgr;
    }

    protected int getSettingLeftIndexes(Context context, String str) {
        List<SettingPageUiSet.ListBean> list = getPageUiSet(context).getSettingPageUiSet().getList();
        Iterator<SettingPageUiSet.ListBean> it = list.iterator();
        for (int i = 0; i < list.size(); i++) {
            if (str.equals(it.next().getTitleSrn())) {
                return i;
            }
        }
        return 404;
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
        return 404;
    }

    public void send0xC6(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -58, (byte) i, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    }
}
