package com.hzbhd.canbus.car._414;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.Iterator;
import java.util.List;


public class UiMgr extends AbstractUiMgr {
    Context mContext;
    MsgMgr mMsgMgr;
    private OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._414.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirInfo0x3D(13);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirInfo0x3D(14);
        }
    };
    private OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._414.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirInfo0x3D(13);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirInfo0x3D(14);
        }
    };
    private int nowModel = 0;
    OnAirSeatClickListener onAirSeatClickListener = new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._414.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onLeftSeatClick() {
            if (UiMgr.this.nowModel == 0) {
                UiMgr.this.sendAirInfo0x3D(26);
                UiMgr.this.nowModel = 1;
                return;
            }
            if (UiMgr.this.nowModel == 1) {
                UiMgr.this.sendAirInfo0x3D(27);
                UiMgr.this.nowModel = 2;
            } else if (UiMgr.this.nowModel == 2) {
                UiMgr.this.sendAirInfo0x3D(28);
                UiMgr.this.nowModel = 3;
            } else if (UiMgr.this.nowModel == 3) {
                UiMgr.this.sendAirInfo0x3D(29);
                UiMgr.this.nowModel = 0;
            }
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onRightSeatClick() {
            if (UiMgr.this.nowModel == 0) {
                UiMgr.this.sendAirInfo0x3D(26);
                UiMgr.this.nowModel = 1;
                return;
            }
            if (UiMgr.this.nowModel == 1) {
                UiMgr.this.sendAirInfo0x3D(27);
                UiMgr.this.nowModel = 2;
            } else if (UiMgr.this.nowModel == 2) {
                UiMgr.this.sendAirInfo0x3D(28);
                UiMgr.this.nowModel = 3;
            } else if (UiMgr.this.nowModel == 3) {
                UiMgr.this.sendAirInfo0x3D(29);
                UiMgr.this.nowModel = 0;
            }
        }
    };
    OnAirWindSpeedUpDownClickListener mSetWindSpeedView = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._414.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            UiMgr.this.sendAirInfo0x3D(12);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            UiMgr.this.sendAirInfo0x3D(11);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._414.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirInfo0x3D(1);
            } else {
                if (i != 1) {
                    return;
                }
                UiMgr.this.sendAirInfo0x3D(4);
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._414.UiMgr.12
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            UiMgr.this.sendAirInfo0x3D(5);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._414.UiMgr.13
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            UiMgr.this.sendAirInfo0x3D(6);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._414.UiMgr.14
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirInfo0x3D(7);
            } else {
                if (i != 1) {
                    return;
                }
                UiMgr.this.sendAirInfo0x3D(2);
            }
        }
    };
    int eachId = getEachId();
    int differentId = getCurrentCarId();

    public UiMgr(Context context) {
        this.mContext = context;
        AirPageUiSet airUiSet = getAirUiSet(this.mContext);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.mSetWindSpeedView);
        airUiSet.getFrontArea().setOnAirSeatClickListener(this.onAirSeatClickListener);
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
        airUiSet.getFrontArea().setOnAirPageStatusListener(new OnAirPageStatusListener() { // from class: com.hzbhd.canbus.car._414.UiMgr.1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener
            public void onStatusChange(int i) {
                UiMgr.this.activeRequest0x6A(49);
            }
        });
        SettingPageUiSet settingUiSet = getSettingUiSet(this.mContext);
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._414.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                UiMgr uiMgr = UiMgr.this;
                if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_414_setting")) {
                    UiMgr uiMgr2 = UiMgr.this;
                    if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "_414_setting", "_414_setting0")) {
                        UiMgr.this.sendCarSetting0x6F(1, i3, 255, 255);
                    }
                    UiMgr uiMgr3 = UiMgr.this;
                    if (i2 == uiMgr3.getSettingRightIndex(uiMgr3.mContext, "_414_setting", "_414_setting1")) {
                        UiMgr.this.sendCarSetting0x6F(2, i3, 255, 255);
                    }
                    UiMgr uiMgr4 = UiMgr.this;
                    if (i2 == uiMgr4.getSettingRightIndex(uiMgr4.mContext, "_414_setting", "_414_setting2")) {
                        UiMgr.this.sendCarSetting0x6F(3, i3, 255, 255);
                    }
                    UiMgr uiMgr5 = UiMgr.this;
                    if (i2 == uiMgr5.getSettingRightIndex(uiMgr5.mContext, "_414_setting", "_414_setting3")) {
                        UiMgr.this.sendCarSetting0x6F(6, i3, 255, 255);
                    }
                    UiMgr uiMgr6 = UiMgr.this;
                    if (i2 == uiMgr6.getSettingRightIndex(uiMgr6.mContext, "_414_setting", "_414_setting4")) {
                        UiMgr.this.sendCarSetting0x6F(4, i3 + 1, 255, 255);
                    }
                    UiMgr uiMgr7 = UiMgr.this;
                    if (i2 == uiMgr7.getSettingRightIndex(uiMgr7.mContext, "_414_setting", "_414_setting5")) {
                        UiMgr.this.sendCarSetting0x6F(5, i3, 255, 255);
                    }
                    UiMgr uiMgr8 = UiMgr.this;
                    if (i2 == uiMgr8.getSettingRightIndex(uiMgr8.mContext, "_414_setting", "_414_setting6")) {
                        UiMgr.this.sendCarSetting0x6F(7, i3, 255, 255);
                    }
                    UiMgr uiMgr9 = UiMgr.this;
                    if (i2 == uiMgr9.getSettingRightIndex(uiMgr9.mContext, "_414_setting", "_414_setting8")) {
                        UiMgr.this.sendCarSetting0x6F(9, i3 + 1, 255, 255);
                    }
                    UiMgr uiMgr10 = UiMgr.this;
                    if (i2 == uiMgr10.getSettingRightIndex(uiMgr10.mContext, "_414_setting", "_414_setting9")) {
                        UiMgr.this.sendCarSetting0x6F(16, i3, 255, 255);
                    }
                    UiMgr uiMgr11 = UiMgr.this;
                    if (i2 == uiMgr11.getSettingRightIndex(uiMgr11.mContext, "_414_setting", "_414_setting10")) {
                        UiMgr.this.sendCarSetting0x6F(17, i3, 255, 255);
                    }
                    UiMgr uiMgr12 = UiMgr.this;
                    if (i2 == uiMgr12.getSettingRightIndex(uiMgr12.mContext, "_414_setting", "_414_setting11")) {
                        UiMgr.this.sendCarSetting0x6F(19, i3, 255, 255);
                    }
                }
                UiMgr uiMgr13 = UiMgr.this;
                if (i == uiMgr13.getSettingLeftIndexes(uiMgr13.mContext, "_414_panoramic")) {
                    UiMgr uiMgr14 = UiMgr.this;
                    if (i2 == uiMgr14.getSettingRightIndex(uiMgr14.mContext, "_414_panoramic", "_414_panoramic0")) {
                        if (i3 == 1) {
                            UiMgr uiMgr15 = UiMgr.this;
                            uiMgr15.getMsgMgr(uiMgr15.mContext).showButton();
                            UiMgr uiMgr16 = UiMgr.this;
                            uiMgr16.getMsgMgr(uiMgr16.mContext).updateSettings(i, i2, i3);
                        } else {
                            UiMgr uiMgr17 = UiMgr.this;
                            uiMgr17.getMsgMgr(uiMgr17.mContext).hideButton();
                            UiMgr uiMgr18 = UiMgr.this;
                            uiMgr18.getMsgMgr(uiMgr18.mContext).updateSettings(i, i2, i3);
                        }
                    }
                }
                UiMgr uiMgr19 = UiMgr.this;
                if (i == uiMgr19.getSettingLeftIndexes(uiMgr19.mContext, "_414_language")) {
                    UiMgr uiMgr20 = UiMgr.this;
                    if (i2 == uiMgr20.getSettingRightIndex(uiMgr20.mContext, "_414_language", "_414_language")) {
                        UiMgr.this.send0x9ALanguageInfo(i3 + 1);
                        UiMgr uiMgr21 = UiMgr.this;
                        uiMgr21.getMsgMgr(uiMgr21.mContext).updateSettings(i, i2, i3);
                    }
                }
            }
        });
        settingUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._414.UiMgr.3
            @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
            public void onConfirmClick(int i, int i2) {
                UiMgr uiMgr = UiMgr.this;
                if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_414_setting")) {
                    UiMgr uiMgr2 = UiMgr.this;
                    if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "_414_setting", "_414_setting7")) {
                        UiMgr.this.sendCarSetting0x6F(8, 1, 255, 255);
                    }
                }
            }
        });
        settingUiSet.setOnSettingPageStatusListener(new OnSettingPageStatusListener() { // from class: com.hzbhd.canbus.car._414.UiMgr.4
            @Override // com.hzbhd.canbus.interfaces.OnSettingPageStatusListener
            public void onStatusChange(int i) {
                UiMgr.this.activeRequest0x6A(97);
            }
        });
        getParkPageUiSet(this.mContext).setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._414.UiMgr.5
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public void onClickItem(int i) {
                if (i != 0) {
                    return;
                }
                UiMgr.this.sendPanoramicInfo0xFD();
            }
        });
        getDriverDataPageUiSet(this.mContext).setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener() { // from class: com.hzbhd.canbus.car._414.UiMgr.6
            @Override // com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener
            public void onStatusChange(int i) {
                UiMgr.this.activeRequest0x6A(50);
                UiMgr.this.activeRequest0x6A(19);
            }
        });
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirInfo0x3D(int i) {
        byte b = (byte) i;
        CanbusMsgSender.sendMsg(new byte[]{22, 61, b, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, 61, b, 0});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendCarSetting0x6F(int i, int i2, int i3, int i4) {
        CanbusMsgSender.sendMsg(new byte[]{22, 111, (byte) i, (byte) i2, (byte) i3, (byte) i4});
    }

    public void sendPanoramicInfo0xFD() {
        CanbusMsgSender.sendMsg(new byte[]{22, -3, 1, 0});
    }

    public void sendCarType() {
        CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte) getEachId(), 38});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void activeRequest0x6A(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, (byte) i});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void send0x9ALanguageInfo(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte) i});
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
