package com.hzbhd.canbus.car._392;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
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
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        SettingPageUiSet settingUiSet = getSettingUiSet(this.mContext);
        settingUiSet.setOnSettingItemClickListener(new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._392.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
            public void onClickItem(int i, int i2) {
                UiMgr uiMgr = UiMgr.this;
                if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_392_setting")) {
                    UiMgr uiMgr2 = UiMgr.this;
                    if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "_392_setting", "_392_setting2")) {
                        UiMgr.this.send0xCAUnitInfo(2, 0);
                        UiMgr uiMgr3 = UiMgr.this;
                        uiMgr3.getMsgMgr(uiMgr3.mContext).toast("Reduce Success");
                    }
                    UiMgr uiMgr4 = UiMgr.this;
                    if (i2 == uiMgr4.getSettingRightIndex(uiMgr4.mContext, "_392_setting", "_392_setting3")) {
                        UiMgr.this.send0xCAUnitInfo(3, 0);
                        UiMgr uiMgr5 = UiMgr.this;
                        uiMgr5.getMsgMgr(uiMgr5.mContext).toast("Restore Success");
                    }
                }
            }
        });
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._392.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                UiMgr uiMgr = UiMgr.this;
                if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_392_setting")) {
                    UiMgr uiMgr2 = UiMgr.this;
                    if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "_392_setting", "_392_setting1")) {
                        UiMgr.this.send0xCAUnitInfo(1, i3);
                        UiMgr uiMgr3 = UiMgr.this;
                        uiMgr3.getMsgMgr(uiMgr3.mContext).updateSettings(i, i2, i3);
                    }
                    UiMgr uiMgr4 = UiMgr.this;
                    if (i2 == uiMgr4.getSettingRightIndex(uiMgr4.mContext, "_392_setting", "_392_setting4")) {
                        UiMgr.this.send0xCAUnitInfo(4, i3);
                        UiMgr uiMgr5 = UiMgr.this;
                        uiMgr5.getMsgMgr(uiMgr5.mContext).updateSettings(i, i2, i3);
                    }
                    UiMgr uiMgr6 = UiMgr.this;
                    if (i2 == uiMgr6.getSettingRightIndex(uiMgr6.mContext, "_392_setting", "_392_setting5")) {
                        UiMgr.this.send0xCAUnitInfo(5, i3);
                        UiMgr uiMgr7 = UiMgr.this;
                        uiMgr7.getMsgMgr(uiMgr7.mContext).updateSettings(i, i2, i3);
                    }
                }
                UiMgr uiMgr8 = UiMgr.this;
                if (i == uiMgr8.getSettingLeftIndexes(uiMgr8.mContext, "_392_car_type")) {
                    UiMgr uiMgr9 = UiMgr.this;
                    if (i2 == uiMgr9.getSettingRightIndex(uiMgr9.mContext, "_392_car_type", "_392_car_type")) {
                        UiMgr.this.send0x2D(i3 + 1);
                        UiMgr uiMgr10 = UiMgr.this;
                        uiMgr10.getMsgMgr(uiMgr10.mContext).updateSettings(i, i2, i3);
                    }
                }
            }
        });
    }

    public void send0xC8BtInfo(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -53});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void send0xCAUnitInfo(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -54, (byte) i, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void send0x2D(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, 45, 2, (byte) i});
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
