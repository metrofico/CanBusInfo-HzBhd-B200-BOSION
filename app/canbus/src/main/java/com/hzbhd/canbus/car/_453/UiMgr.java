package com.hzbhd.canbus.car._453;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.Iterator;
import java.util.List;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    private Context mContext;
    OnAirWindSpeedUpDownClickListener windControl = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._453.UiMgr.1
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 6});
            CanbusMsgSender.sendMsg(new byte[]{22, -58, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 5});
            CanbusMsgSender.sendMsg(new byte[]{22, -58, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 0});
        }
    };
    OnAirTemperatureUpDownClickListener leftTemp = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._453.UiMgr.2
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -58, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 2});
            CanbusMsgSender.sendMsg(new byte[]{22, -58, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 0});
        }
    };
    OnAirTemperatureUpDownClickListener rightTemp = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._453.UiMgr.3
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 3});
            CanbusMsgSender.sendMsg(new byte[]{22, -58, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 4});
            CanbusMsgSender.sendMsg(new byte[]{22, -58, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 0});
        }
    };
    OnAirBtnClickListener topBtn = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._453.UiMgr.4
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 8});
                CanbusMsgSender.sendMsg(new byte[]{22, -58, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 0});
            } else {
                if (i != 2) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -58, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 9});
                CanbusMsgSender.sendMsg(new byte[]{22, -58, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 0});
            }
        }
    };
    OnAirBtnClickListener leftBtn = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._453.UiMgr.5
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, -58, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 10});
            CanbusMsgSender.sendMsg(new byte[]{22, -58, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 0});
        }
    };
    OnAirBtnClickListener rightBtn = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._453.UiMgr.6
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, -58, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 7});
            CanbusMsgSender.sendMsg(new byte[]{22, -58, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 0});
        }
    };
    OnAirBtnClickListener bottomBtn = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._453.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED});
                CanbusMsgSender.sendMsg(new byte[]{22, -58, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 0});
            } else if (i == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 11});
                CanbusMsgSender.sendMsg(new byte[]{22, -58, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 0});
            } else {
                if (i != 2) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -58, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED});
                CanbusMsgSender.sendMsg(new byte[]{22, -58, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 0});
            }
        }
    };
    OnSettingItemSelectListener onSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._453.UiMgr.8
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_453_setting_modular_1")) {
                UiMgr uiMgr2 = UiMgr.this;
                if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "_453_setting_modular_1", "_453_park_assist_tone")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, (byte) i3});
                }
                UiMgr uiMgr3 = UiMgr.this;
                if (i2 == uiMgr3.getSettingRightIndex(uiMgr3.mContext, "_453_setting_modular_1", "_453_park_assist_delay_timer")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, (byte) i3});
                }
                UiMgr uiMgr4 = UiMgr.this;
                if (i2 == uiMgr4.getSettingRightIndex(uiMgr4.mContext, "_453_setting_modular_1", "_453_park_assist")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, (byte) i3});
                }
            }
            UiMgr uiMgr5 = UiMgr.this;
            if (i == uiMgr5.getSettingLeftIndexes(uiMgr5.mContext, "_453_setting_modular_2")) {
                UiMgr uiMgr6 = UiMgr.this;
                if (i2 == uiMgr6.getSettingRightIndex(uiMgr6.mContext, "_453_setting_modular_2", "_453_vehicle_auto_relock")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, (byte) i3});
                }
                UiMgr uiMgr7 = UiMgr.this;
                if (i2 == uiMgr7.getSettingRightIndex(uiMgr7.mContext, "_453_setting_modular_2", "_453_door_unlocking")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, (byte) i3});
                }
            }
            UiMgr uiMgr8 = UiMgr.this;
            if (i == uiMgr8.getSettingLeftIndexes(uiMgr8.mContext, "_453_setting_modular_3")) {
                UiMgr uiMgr9 = UiMgr.this;
                if (i2 == uiMgr9.getSettingRightIndex(uiMgr9.mContext, "_453_setting_modular_3", "_453_exterior_litghts_approach_lamps")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 5, (byte) i3});
                }
                UiMgr uiMgr10 = UiMgr.this;
                if (i2 == uiMgr10.getSettingRightIndex(uiMgr10.mContext, "_453_setting_modular_3", "_453_mood_lighting_mode")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, (byte) i3});
                }
                UiMgr uiMgr11 = UiMgr.this;
                if (i2 == uiMgr11.getSettingRightIndex(uiMgr11.mContext, "_453_setting_modular_3", "_453_mood_lighting_front")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 8, (byte) i3});
                }
                UiMgr uiMgr12 = UiMgr.this;
                if (i2 == uiMgr12.getSettingRightIndex(uiMgr12.mContext, "_453_setting_modular_3", "_453_mood_lighting_rear")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 9, (byte) i3});
                }
                UiMgr uiMgr13 = UiMgr.this;
                if (i2 == uiMgr13.getSettingRightIndex(uiMgr13.mContext, "_453_setting_modular_3", "_453_mood_lighting_color")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 10, (byte) i3});
                }
                UiMgr uiMgr14 = UiMgr.this;
                if (i2 == uiMgr14.getSettingRightIndex(uiMgr14.mContext, "_453_setting_modular_3", "_453_mood_lighting_lumim")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 11, (byte) i3});
                }
                UiMgr uiMgr15 = UiMgr.this;
                if (i2 == uiMgr15.getSettingRightIndex(uiMgr15.mContext, "_453_setting_modular_3", "_453_drive_away_locking")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) i3});
                }
            }
        }
    };
    private int eachId = getEachId();
    private int differentId = getCurrentCarId();

    public UiMgr(Context context) {
        this.mContext = context;
        getSettingUiSet(context).setOnSettingItemSelectListener(this.onSettingItemSelectListener);
        AirPageUiSet airUiSet = getAirUiSet(context);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.topBtn, this.leftBtn, this.rightBtn, this.bottomBtn});
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.leftTemp, null, this.rightTemp});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.windControl);
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
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

    private int getDecimalFrom8Bit(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return Integer.parseInt(i + "" + i2 + "" + i3 + "" + i4 + "" + i5 + "" + i6 + "" + i7 + "" + i8, 2);
    }
}
