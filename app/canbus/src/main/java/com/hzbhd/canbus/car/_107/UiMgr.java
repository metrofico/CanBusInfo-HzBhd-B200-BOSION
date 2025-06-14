package com.hzbhd.canbus.car._107;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class UiMgr extends AbstractUiMgr {
    private Context mContext;
    OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._107.UiMgr.4
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirData0x82(21);
            } else {
                if (i != 1) {
                    return;
                }
                UiMgr.this.sendAirData0x82(22);
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._107.UiMgr.5
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            UiMgr.this.sendAirData0x82(17);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._107.UiMgr.6
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._107.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirData0x82(20);
            } else {
                if (i != 1) {
                    return;
                }
                UiMgr.this.sendAirData0x82(19);
            }
        }
    };
    OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._107.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirData0x82(30);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirData0x82(31);
        }
    };
    OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._107.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirData0x82(32);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirData0x82(33);
        }
    };
    OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._107.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            UiMgr.this.sendAirData0x82(28);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            UiMgr.this.sendAirData0x82(31);
        }
    };
    private int seatTag = 0;
    OnAirSeatClickListener onAirSeatClickListener = new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._107.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onLeftSeatClick() {
            if (UiMgr.this.seatTag == 0) {
                UiMgr.this.seatTag = 1;
                UiMgr.this.sendAirData0x82(24);
            } else if (UiMgr.this.seatTag == 1) {
                UiMgr.this.seatTag = 2;
                UiMgr.this.sendAirData0x82(26);
            } else if (UiMgr.this.seatTag == 2) {
                UiMgr.this.seatTag = 0;
                UiMgr.this.sendAirData0x82(27);
            }
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onRightSeatClick() {
            if (UiMgr.this.seatTag == 0) {
                UiMgr.this.seatTag = 1;
                UiMgr.this.sendAirData0x82(24);
            } else if (UiMgr.this.seatTag == 1) {
                UiMgr.this.seatTag = 2;
                UiMgr.this.sendAirData0x82(26);
            } else if (UiMgr.this.seatTag == 2) {
                UiMgr.this.seatTag = 0;
                UiMgr.this.sendAirData0x82(27);
            }
        }
    };

    public UiMgr(Context context) {
        this.mContext = context;
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._107.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
            public void onConfirmClick(int i, int i2) {
                if (i == 0 && i2 == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, 1});
                }
            }
        });
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._107.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                if (i == 0 && i2 == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, (byte) i3});
                }
                UiMgr uiMgr = UiMgr.this;
                if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "car_set")) {
                    UiMgr uiMgr2 = UiMgr.this;
                    if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "car_set", "_107_car_language")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, (byte) i3});
                    }
                }
            }
        });
        getParkPageUiSet(this.mContext).setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._107.UiMgr.3
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public void onClickItem(int i) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, (byte) (i + 1)});
            }
        });
        AirPageUiSet airUiSet = getAirUiSet(this.mContext);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.onAirTemperatureUpDownClickListenerLeft, null, this.onAirTemperatureUpDownClickListenerRight});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.onAirWindSpeedUpDownClickListener);
        airUiSet.getFrontArea().setOnAirSeatClickListener(this.onAirSeatClickListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirData0x82(int i) {
        byte b = (byte) i;
        CanbusMsgSender.sendMsg(new byte[]{22, -126, b, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, -126, b, 0});
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
}
