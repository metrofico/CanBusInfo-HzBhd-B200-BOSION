package com.hzbhd.canbus.car._395;

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
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.Iterator;
import java.util.List;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    Context mContext;
    MsgMgr mMsgMgr;
    private OnAirBtnClickListener mOnAirBtnClickListenerTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._395.UiMgr.2
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
    private OnAirBtnClickListener mOnAirBtnClickListenerLeft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._395.UiMgr.3
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            UiMgr.this.SenAirMsg((byte) 5);
        }
    };
    private OnAirBtnClickListener mOnAirBtnClickListenerRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._395.UiMgr.4
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            UiMgr.this.SenAirMsg((byte) 6);
        }
    };
    private OnAirBtnClickListener mOnAirBtnClickListenerBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._395.UiMgr.5
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            UiMgr.this.SenAirMsg((byte) 1);
        }
    };
    OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._395.UiMgr.6
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
    OnAirSeatClickListener onAirSeatClickListener = new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._395.UiMgr.7
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
    OnAirPageStatusListener onAirPageStatusListener = new OnAirPageStatusListener() { // from class: com.hzbhd.canbus.car._395.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener
        public void onStatusChange(int i) {
            UiMgr.this.activeRequest(49);
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._395.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.SenAirMsg(NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.SenAirMsg((byte) 14);
        }
    };
    OnSettingItemSelectListener onSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._395.UiMgr.10
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_394_seting")) {
                UiMgr uiMgr2 = UiMgr.this;
                if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "_394_seting", "_394_seting1")) {
                    UiMgr.this.sendCarSetting0x6F(18, i3);
                }
                UiMgr uiMgr3 = UiMgr.this;
                if (i2 == uiMgr3.getSettingRightIndex(uiMgr3.mContext, "_394_seting", "_394_seting2")) {
                    UiMgr.this.sendCarSetting0x6F(32, i3);
                }
                UiMgr uiMgr4 = UiMgr.this;
                if (i2 == uiMgr4.getSettingRightIndex(uiMgr4.mContext, "_394_seting", "_394_seting3")) {
                    UiMgr.this.sendCarSetting0x6F(58, i3);
                }
                UiMgr uiMgr5 = UiMgr.this;
                if (i2 == uiMgr5.getSettingRightIndex(uiMgr5.mContext, "_394_seting", "_394_seting4")) {
                    UiMgr.this.sendCarSetting0x6F(59, i3);
                }
                UiMgr uiMgr6 = UiMgr.this;
                if (i2 == uiMgr6.getSettingRightIndex(uiMgr6.mContext, "_394_seting", "_394_seting6")) {
                    UiMgr.this.sendCarSetting0x6F(60, i3);
                }
                UiMgr uiMgr7 = UiMgr.this;
                if (i2 == uiMgr7.getSettingRightIndex(uiMgr7.mContext, "_394_seting", "_394_seting7")) {
                    UiMgr.this.sendCarSetting0x6F(61, i3);
                }
            }
            UiMgr uiMgr8 = UiMgr.this;
            if (i == uiMgr8.getSettingLeftIndexes(uiMgr8.mContext, "_394_language")) {
                UiMgr uiMgr9 = UiMgr.this;
                if (i2 == uiMgr9.getSettingRightIndex(uiMgr9.mContext, "_394_language", "_394_language")) {
                    UiMgr.this.sendLanguage0x9A(i3);
                    UiMgr uiMgr10 = UiMgr.this;
                    uiMgr10.getMsgMgr(uiMgr10.mContext).updateSettings(i, i2, i3);
                }
            }
        }
    };
    private int TireState = 0;
    OnSettingItemClickListener onSettingItemClickListener = new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._395.UiMgr.11
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
        public void onClickItem(int i, int i2) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_394_seting")) {
                UiMgr uiMgr2 = UiMgr.this;
                if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "_394_seting", "_394_seting5")) {
                    if (UiMgr.this.TireState == 0) {
                        UiMgr.this.sendCarSetting0x6F(169, 1);
                        UiMgr.this.TireState = 1;
                    } else {
                        UiMgr.this.TireState = 0;
                        UiMgr.this.sendCarSetting0x6F(169, 0);
                    }
                }
                UiMgr uiMgr3 = UiMgr.this;
                if (i2 == uiMgr3.getSettingRightIndex(uiMgr3.mContext, "_394_seting", "_394_seting8")) {
                    UiMgr.this.sendCarSetting0x6F(NfDef.STATE_RESP_AND_HOLD, 1);
                    UiMgr uiMgr4 = UiMgr.this;
                    uiMgr4.getMsgMgr(uiMgr4.mContext).toast("success");
                }
            }
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
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerLeft, null, null});
        airUiSet.getFrontArea().setOnAirPageStatusListener(this.onAirPageStatusListener);
        SettingPageUiSet settingUiSet = getSettingUiSet(this.mContext);
        settingUiSet.setOnSettingItemSelectListener(this.onSettingItemSelectListener);
        settingUiSet.setOnSettingItemClickListener(this.onSettingItemClickListener);
        getDriverDataPageUiSet(this.mContext).setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener() { // from class: com.hzbhd.canbus.car._395.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener
            public void onStatusChange(int i) {
                UiMgr.this.activeRequest(50);
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
    public void sendCarSetting0x6F(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, 111, (byte) i, (byte) i2, 0, 0});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendLanguage0x9A(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte) (i + 1)});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void activeRequest(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, (byte) i});
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
