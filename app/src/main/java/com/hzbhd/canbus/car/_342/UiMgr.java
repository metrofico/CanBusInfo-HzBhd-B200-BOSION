package com.hzbhd.canbus.car._342;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    int differentId;
    int eachId;
    Context mContext;
    MsgMgr msgMgr;
    SettingPageUiSet settingPageUiSet;
    private int panoramicState = 0;
    DecimalFormat noDecimal = new DecimalFormat("000000");
    OnSettingItemClickListener onSettingItemClickListener = new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._342.UiMgr.3
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
        public void onClickItem(int i, int i2) {
            String titleSrn = UiMgr.this.settingPageUiSet.getList().get(i).getTitleSrn();
            titleSrn.hashCode();
            if (titleSrn.equals("_342_setting_2")) {
                UiMgr uiMgr = UiMgr.this;
                if (i2 == uiMgr.getSettingRightIndex(uiMgr.mContext, "_342_setting_2", "_342_setting_2_0")) {
                    UiMgr uiMgr2 = UiMgr.this;
                    if (uiMgr2.getMsgMgr(uiMgr2.mContext).a == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 1, 1});
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 1, 0});
                    }
                }
                UiMgr uiMgr3 = UiMgr.this;
                if (i2 == uiMgr3.getSettingRightIndex(uiMgr3.mContext, "_342_setting_2", "_342_setting_2_1")) {
                    UiMgr uiMgr4 = UiMgr.this;
                    if (uiMgr4.getMsgMgr(uiMgr4.mContext).b == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 2, 1});
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 2, 0});
                    }
                }
                UiMgr uiMgr5 = UiMgr.this;
                if (i2 == uiMgr5.getSettingRightIndex(uiMgr5.mContext, "_342_setting_2", "_342_setting_2_2")) {
                    UiMgr uiMgr6 = UiMgr.this;
                    if (uiMgr6.getMsgMgr(uiMgr6.mContext).c == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 3, 1});
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 3, 0});
                    }
                }
                UiMgr uiMgr7 = UiMgr.this;
                if (i2 == uiMgr7.getSettingRightIndex(uiMgr7.mContext, "_342_setting_2", "_342_setting_2_3")) {
                    UiMgr uiMgr8 = UiMgr.this;
                    if (uiMgr8.getMsgMgr(uiMgr8.mContext).d == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 5, 1});
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 5, 0});
                    }
                }
                UiMgr uiMgr9 = UiMgr.this;
                if (i2 == uiMgr9.getSettingRightIndex(uiMgr9.mContext, "_342_setting_2", "_342_setting_2_4")) {
                    UiMgr uiMgr10 = UiMgr.this;
                    if (uiMgr10.getMsgMgr(uiMgr10.mContext).e == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 6, 1});
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 6, 0});
                    }
                }
                UiMgr uiMgr11 = UiMgr.this;
                if (i2 == uiMgr11.getSettingRightIndex(uiMgr11.mContext, "_342_setting_2", "_342_setting_2_5")) {
                    UiMgr uiMgr12 = UiMgr.this;
                    if (uiMgr12.getMsgMgr(uiMgr12.mContext).f == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 7, 1});
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 7, 0});
                    }
                }
                UiMgr uiMgr13 = UiMgr.this;
                if (i2 == uiMgr13.getSettingRightIndex(uiMgr13.mContext, "_342_setting_2", "_342_setting_2_6")) {
                    UiMgr uiMgr14 = UiMgr.this;
                    if (uiMgr14.getMsgMgr(uiMgr14.mContext).g == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 8, 1});
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 8, 0});
                    }
                }
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._342.UiMgr.4
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirData0xA8(1);
            } else if (i == 2 && UiMgr.this.eachId == 7) {
                UiMgr.this.sendAirData0xA8(2);
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontleft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._342.UiMgr.5
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                if (i != 1) {
                    return;
                }
                UiMgr.this.sendAirData0xA8(3);
            } else {
                if (UiMgr.this.eachId != 5) {
                    return;
                }
                UiMgr.this.sendAirData0xA8(6);
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._342.UiMgr.6
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                if (i != 1) {
                    return;
                }
                UiMgr.this.sendAirData0xA8(0);
            } else {
                if (UiMgr.this.eachId != 5) {
                    return;
                }
                UiMgr.this.sendAirData0xA8(21);
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._342.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirData0xA8(17);
            } else {
                if (i != 1) {
                    return;
                }
                if (!GeneralAirData.in_out_cycle) {
                    UiMgr.this.sendAirData0xA8(5);
                } else {
                    UiMgr.this.sendAirData0xA8(4);
                }
            }
        }
    };
    OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._342.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            if (UiMgr.this.eachId == 5) {
                UiMgr uiMgr = UiMgr.this;
                if (uiMgr.getMsgMgr(uiMgr.mContext).nowTemLevel1 == 12) {
                    return;
                }
                UiMgr uiMgr2 = UiMgr.this;
                CanbusMsgSender.sendMsg(new byte[]{22, -88, 19, (byte) (uiMgr2.getMsgMgr(uiMgr2.mContext).nowTemLevel1 + 1)});
                return;
            }
            if (UiMgr.this.eachId == 7) {
                UiMgr uiMgr3 = UiMgr.this;
                if (uiMgr3.getMsgMgr(uiMgr3.mContext).nowTemLevel1 == 32) {
                    return;
                }
                UiMgr uiMgr4 = UiMgr.this;
                CanbusMsgSender.sendMsg(new byte[]{22, -88, 19, (byte) (uiMgr4.getMsgMgr(uiMgr4.mContext).nowTemLevel1 + 1)});
                return;
            }
            UiMgr uiMgr5 = UiMgr.this;
            if (uiMgr5.getMsgMgr(uiMgr5.mContext).nowTemLevel1 == 15) {
                return;
            }
            UiMgr uiMgr6 = UiMgr.this;
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 19, (byte) (uiMgr6.getMsgMgr(uiMgr6.mContext).nowTemLevel1 + 1)});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr uiMgr = UiMgr.this;
            if (uiMgr.getMsgMgr(uiMgr.mContext).nowTemLevel1 > 0) {
                UiMgr uiMgr2 = UiMgr.this;
                CanbusMsgSender.sendMsg(new byte[]{22, -88, 19, (byte) (uiMgr2.getMsgMgr(uiMgr2.mContext).nowTemLevel1 - 1)});
            }
        }
    };
    OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._342.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            if (UiMgr.this.eachId == 5) {
                UiMgr uiMgr = UiMgr.this;
                if (uiMgr.getMsgMgr(uiMgr.mContext).nowTemLevel2 == 12) {
                    return;
                }
                UiMgr uiMgr2 = UiMgr.this;
                CanbusMsgSender.sendMsg(new byte[]{22, -88, 20, (byte) (uiMgr2.getMsgMgr(uiMgr2.mContext).nowTemLevel2 + 1)});
                return;
            }
            if (UiMgr.this.eachId == 7) {
                UiMgr uiMgr3 = UiMgr.this;
                if (uiMgr3.getMsgMgr(uiMgr3.mContext).nowTemLevel2 == 32) {
                    return;
                }
                UiMgr uiMgr4 = UiMgr.this;
                CanbusMsgSender.sendMsg(new byte[]{22, -88, 20, (byte) (uiMgr4.getMsgMgr(uiMgr4.mContext).nowTemLevel2 + 1)});
                return;
            }
            UiMgr uiMgr5 = UiMgr.this;
            if (uiMgr5.getMsgMgr(uiMgr5.mContext).nowTemLevel2 == 15) {
                return;
            }
            UiMgr uiMgr6 = UiMgr.this;
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 20, (byte) (uiMgr6.getMsgMgr(uiMgr6.mContext).nowTemLevel2 + 1)});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr uiMgr = UiMgr.this;
            if (uiMgr.getMsgMgr(uiMgr.mContext).nowTemLevel2 > 0) {
                UiMgr uiMgr2 = UiMgr.this;
                CanbusMsgSender.sendMsg(new byte[]{22, -88, 20, (byte) (uiMgr2.getMsgMgr(uiMgr2.mContext).nowTemLevel2 - 1)});
            }
        }
    };
    OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._342.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            if (GeneralAirData.front_wind_level == 0) {
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 18, (byte) (GeneralAirData.front_wind_level - 1)});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            if (UiMgr.this.eachId == 5 && GeneralAirData.front_wind_level < 15) {
                CanbusMsgSender.sendMsg(new byte[]{22, -88, 18, (byte) (GeneralAirData.front_wind_level + 1)});
                return;
            }
            if (UiMgr.this.eachId == 5 || GeneralAirData.front_wind_level >= 7) {
                return;
            }
            int i = GeneralAirData.front_wind_level;
            GeneralAirData.front_wind_level = i + 1;
            GeneralAirData.front_wind_level = i;
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 18, (byte) (GeneralAirData.front_wind_level + 1)});
        }
    };

    public int getCarModelData(int i) {
        if (i == 0) {
            return 0;
        }
        int i2 = 1;
        if (i != 1) {
            i2 = 2;
            if (i != 2) {
                i2 = 3;
                if (i != 3) {
                    i2 = 4;
                    if (i != 4) {
                        return 5;
                    }
                }
            }
        }
        return i2;
    }

    public UiMgr(Context context) {
        this.mContext = context;
        int eachId = getEachId();
        this.eachId = eachId;
        if (eachId == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 1, (byte) getCarModelData(0)});
        }
        if (this.eachId == 3) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 1, (byte) getCarModelData(1)});
        }
        if (this.eachId == 4) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 1, (byte) getCarModelData(2)});
        }
        if (this.eachId == 5) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 1, (byte) getCarModelData(3)});
        }
        if (this.eachId == 6) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 1, (byte) getCarModelData(4)});
        }
        if (this.eachId == 7) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 1, (byte) getCarModelData(5)});
        }
        this.differentId = getCurrentCarId();
        this.settingPageUiSet = getSettingUiSet(this.mContext);
        SettingPageUiSet settingUiSet = getSettingUiSet(this.mContext);
        settingUiSet.setOnSettingItemClickListener(this.onSettingItemClickListener);
        settingUiSet.setOnSettingPageStatusListener(new OnSettingPageStatusListener() { // from class: com.hzbhd.canbus.car._342.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnSettingPageStatusListener
            public void onStatusChange(int i) {
                UiMgr uiMgr = UiMgr.this;
                if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_342_setting_2")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -112, 97, 0});
                }
            }
        });
        AirPageUiSet airUiSet = getAirUiSet(this.mContext);
        if (this.eachId == 5) {
            airUiSet.getFrontArea().setWindMaxLevel(12);
        } else {
            airUiSet.getFrontArea().setWindMaxLevel(7);
        }
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFrontleft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.onAirTemperatureUpDownClickListenerLeft, null, this.onAirTemperatureUpDownClickListenerRight});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.onAirWindSpeedUpDownClickListener);
        getParkPageUiSet(this.mContext).setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._342.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public void onClickItem(int i) {
                if (i == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, 2});
                    return;
                }
                if (i == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, 3});
                    return;
                }
                if (i == 2) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, 4});
                } else if (i == 3) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, 5});
                } else {
                    if (i != 4) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, 0});
                }
            }
        });
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        initUi();
    }

    private void initUi() {
        if (this.eachId == 1) {
            removeMainPrjBtnByName(this.mContext, "air");
        }
        if (this.eachId != 1) {
            removeSettingLeftItemByNameList(this.mContext, new String[]{"_342_setting_2"});
            removeMainPrjBtnByName(this.mContext, MainAction.SETTING);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirData0xA8(int i) {
        byte b = (byte) i;
        CanbusMsgSender.sendMsg(new byte[]{22, -88, b, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, -88, b, 0});
    }

    private void sendAVMData0x83(int i) {
        byte b = (byte) i;
        CanbusMsgSender.sendMsg(new byte[]{22, -125, b, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, -125, b, 0});
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

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
        if (this.msgMgr == null) {
            this.msgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.msgMgr;
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
