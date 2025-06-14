package com.hzbhd.canbus.car._393;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    Context mContext;
    MsgMgr mMsgMgr;
    DecimalFormat towDecimal = new DecimalFormat("###0.00");
    DecimalFormat oneDecimal = new DecimalFormat("###0.0");
    DecimalFormat timeFormat = new DecimalFormat("00");
    OnAirPageStatusListener onAirPageStatusListener = new OnAirPageStatusListener() { // from class: com.hzbhd.canbus.car._393.UiMgr.1
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener
        public void onStatusChange(int i) {
            UiMgr.this.activeRequest(49);
        }
    };
    OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._393.UiMgr.2
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.send0x3DAirInfo(13);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.send0x3DAirInfo(14);
        }
    };
    OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._393.UiMgr.3
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.send0x3DAirInfo(15);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.send0x3DAirInfo(16);
        }
    };
    OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._393.UiMgr.4
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            UiMgr.this.send0x3DAirInfo(12);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            UiMgr.this.send0x3DAirInfo(11);
        }
    };
    OnAirSeatClickListener onAirSeatClickListener = new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._393.UiMgr.5
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onLeftSeatClick() {
            UiMgr.this.send0x3DAirInfo(21);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onRightSeatClick() {
            UiMgr.this.send0x3DAirInfo(21);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._393.UiMgr.6
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.send0x3DAirInfo(4);
            } else if (i == 1) {
                UiMgr.this.send0x3DAirInfo(1);
            } else {
                if (i != 2) {
                    return;
                }
                UiMgr.this.send0x3DAirInfo(2);
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._393.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            UiMgr.this.send0x3DAirInfo(5);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._393.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            UiMgr.this.send0x3DAirInfo(6);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._393.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.send0x3DAirInfo(7);
            } else {
                if (i != 1) {
                    return;
                }
                UiMgr.this.send0x3DAirInfo(41);
            }
        }
    };
    OnSettingItemSelectListener onSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._393.UiMgr.10
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_393_car")) {
                UiMgr uiMgr2 = UiMgr.this;
                if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "_393_car", "_393_car")) {
                    if (i3 == 0) {
                        UiMgr.this.send0x24CarSelect(10, 4);
                    } else {
                        UiMgr.this.send0x24CarSelect(12, 4);
                    }
                    UiMgr uiMgr3 = UiMgr.this;
                    uiMgr3.getMsgMgr(uiMgr3.mContext).updateSettings(i, i2, i3);
                }
            }
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
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.onAirTemperatureUpDownClickListenerLeft, null, this.onAirTemperatureUpDownClickListenerRight});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.onAirWindSpeedUpDownClickListener);
        airUiSet.getFrontArea().setOnAirPageStatusListener(this.onAirPageStatusListener);
        getSettingUiSet(this.mContext).setOnSettingItemSelectListener(this.onSettingItemSelectListener);
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        initUi();
        intiData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void send0x3DAirInfo(int i) {
        byte b = (byte) i;
        CanbusMsgSender.sendMsg(new byte[]{22, 61, b, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, 61, b, 0});
    }

    public void send0x24CarSelect(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte) i, (byte) i2});
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
