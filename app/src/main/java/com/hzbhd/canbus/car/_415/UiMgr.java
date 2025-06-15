package com.hzbhd.canbus.car._415;

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


public class UiMgr extends AbstractUiMgr {
    Context mContext;
    MsgMgr mMsgMgr;
    int eachId = getEachId();
    int differentId = getCurrentCarId();

    public UiMgr(Context context) {
        this.mContext = context;
        SettingPageUiSet settingUiSet = getSettingUiSet(this.mContext);
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._415.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                UiMgr uiMgr = UiMgr.this;
                if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_415_setting")) {
                    UiMgr uiMgr2 = UiMgr.this;
                    if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "_415_setting", "_415_setting0")) {
                        UiMgr.this.send0x6BSettingInfo(2, i3);
                    }
                    UiMgr uiMgr3 = UiMgr.this;
                    if (i2 == uiMgr3.getSettingRightIndex(uiMgr3.mContext, "_415_setting", "_415_setting1")) {
                        UiMgr.this.send0x6BSettingInfo(1, i3);
                    }
                }
                UiMgr uiMgr4 = UiMgr.this;
                if (i == uiMgr4.getSettingLeftIndexes(uiMgr4.mContext, "_415_setting2")) {
                    UiMgr uiMgr5 = UiMgr.this;
                    if (i2 == uiMgr5.getSettingRightIndex(uiMgr5.mContext, "_415_setting2", "_415_setting3")) {
                        UiMgr.this.send0x6CSettingInfo(2, i3);
                    }
                    UiMgr uiMgr6 = UiMgr.this;
                    if (i2 == uiMgr6.getSettingRightIndex(uiMgr6.mContext, "_415_setting2", "_415_setting4")) {
                        UiMgr.this.send0x6CSettingInfo(1, i3 + 1);
                    }
                }
            }
        });
        settingUiSet.setOnSettingPageStatusListener(new OnSettingPageStatusListener() { // from class: com.hzbhd.canbus.car._415.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnSettingPageStatusListener
            public void onStatusChange(int i) {
                UiMgr.this.activeRequest(102);
                UiMgr.this.activeRequest(103);
            }
        });
        getAirUiSet(this.mContext).getFrontArea().setOnAirPageStatusListener(new OnAirPageStatusListener() { // from class: com.hzbhd.canbus.car._415.UiMgr.3
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener
            public void onStatusChange(int i) {
                UiMgr.this.activeRequest(49);
            }
        });
        getDriverDataPageUiSet(this.mContext).setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener() { // from class: com.hzbhd.canbus.car._415.UiMgr.4
            @Override // com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener
            public void onStatusChange(int i) {
                UiMgr.this.activeRequest(52);
            }
        });
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void send0x6BSettingInfo(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, 107, (byte) i, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void send0x6CSettingInfo(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, 108, (byte) i, (byte) i2});
    }

    public void send0xCBTimeInfo(int i, int i2, int i3) {
        CanbusMsgSender.sendMsg(new byte[]{22, -53, 0, (byte) i, (byte) i2, 0, 0, (byte) i3, 0, 0, 0, 0});
    }

    public void sendMediaInfo0x91(int i, byte[] bArr) {
        CanbusMsgSender.sendMsg(SplicingByte(new byte[]{22, -111, (byte) i}, bArr));
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

    private MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }

    private int getDecimalFrom8Bit(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return Integer.parseInt(i + "" + i2 + "" + i3 + "" + i4 + "" + i5 + "" + i6 + "" + i7 + "" + i8, 2);
    }

    private byte[] SplicingByte(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    public boolean isLandscape() {
        return this.mContext.getResources().getConfiguration().orientation == 2;
    }

    public boolean isPortrait() {
        return this.mContext.getResources().getConfiguration().orientation == 1;
    }
}
