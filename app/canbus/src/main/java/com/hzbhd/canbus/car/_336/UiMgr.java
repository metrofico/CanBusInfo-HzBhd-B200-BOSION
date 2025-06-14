package com.hzbhd.canbus.car._336;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
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
    OnDriveDataPageStatusListener onDriveDataPageStatusListener = new OnDriveDataPageStatusListener() { // from class: com.hzbhd.canbus.car._336.UiMgr.1
        @Override // com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener
        public void onStatusChange(int i) {
            UiMgr.this.activeRequestData(17);
            UiMgr.this.activeRequestData(33);
            UiMgr.this.activeRequestData(97);
        }
    };
    OnAirPageStatusListener onAirPageStatusListener = new OnAirPageStatusListener() { // from class: com.hzbhd.canbus.car._336.UiMgr.2
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener
        public void onStatusChange(int i) {
            UiMgr.this.activeRequestData(49);
        }
    };
    OnSettingPageStatusListener onSettingPageStatusListener = new OnSettingPageStatusListener() { // from class: com.hzbhd.canbus.car._336.UiMgr.3
        @Override // com.hzbhd.canbus.interfaces.OnSettingPageStatusListener
        public void onStatusChange(int i) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_336_car_settings")) {
                UiMgr.this.activeRequestData(97);
            }
        }
    };
    OnSettingItemSelectListener onSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._336.UiMgr.4
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_336_car_settings")) {
                switch (i2) {
                    case 0:
                        UiMgr.this.sendCarSettingsInfo(16, i3);
                        break;
                    case 1:
                        UiMgr.this.sendCarSettingsInfo(17, i3);
                        break;
                    case 2:
                        UiMgr.this.sendCarSettingsInfo(18, i3);
                        break;
                    case 3:
                        UiMgr.this.sendCarSettingsInfo(19, i3);
                        break;
                    case 4:
                        UiMgr.this.sendCarSettingsInfo(32, i3);
                        break;
                    case 5:
                        UiMgr.this.sendCarSettingsInfo(33, i3);
                        break;
                    case 6:
                        UiMgr.this.sendCarSettingsInfo(34, i3);
                        break;
                    case 7:
                        UiMgr.this.sendCarSettingsInfo(35, i3);
                        break;
                    case 8:
                        UiMgr.this.sendCarSettingsInfo(48, i3);
                        break;
                    case 9:
                        UiMgr.this.sendCarSettingsInfo(49, i3);
                        break;
                    case 10:
                        UiMgr.this.sendCarSettingsInfo(50, i3);
                        break;
                }
            }
        }
    };
    int eachId = getEachId();
    int differentId = getCurrentCarId();

    private void initUi() {
    }

    public UiMgr(Context context) {
        this.mContext = context;
        SettingPageUiSet settingUiSet = getSettingUiSet(this.mContext);
        settingUiSet.setOnSettingItemSelectListener(this.onSettingItemSelectListener);
        settingUiSet.setOnSettingPageStatusListener(this.onSettingPageStatusListener);
        getAirUiSet(this.mContext).setOnAirPageStatusListener(this.onAirPageStatusListener);
        getDriverDataPageUiSet(this.mContext).setOnDriveDataPageStatusListener(this.onDriveDataPageStatusListener);
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        initUi();
        intiData();
    }

    private void intiData() {
        activeRequestData(NfDef.STATE_3WAY_M_HOLD);
        sendCarModelInfo();
    }

    public void activeRequestData(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, 106, 1, (byte) i});
    }

    private void sendCarModelInfo() {
        int i = this.eachId;
        if (i == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, 36, 1, 40});
        } else if (i == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, 36, 2, 40});
        } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 36, 3, 40});
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendCarSettingsInfo(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, 111, (byte) i, (byte) i2, -1, -1});
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

    private MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }

    private int getDecimalFrom8Bit(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return Integer.parseInt(i + "" + i2 + "" + i3 + "" + i4 + "" + i5 + "" + i6 + "" + i7 + "" + i8, 2);
    }
}
