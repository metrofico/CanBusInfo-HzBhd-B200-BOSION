package com.hzbhd.canbus.car._346;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    int differentId;
    int[] mCanBusInfoInt;
    Context mContext;
    MsgMgr msgMgr;
    SettingPageUiSet settingPageUiSet;
    OnSettingItemSelectListener onSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._346.UiMgr.3
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            if (i == 0 && i2 == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte) i3});
            }
        }
    };
    int eachId = getEachId();

    public UiMgr(Context context) {
        this.mContext = context;
        selectCarModel();
        if (this.eachId != 3) {
            removeMainPrjBtnByName(this.mContext, "air");
        }
        if (this.eachId != 2) {
            removeMainPrjBtnByName(this.mContext, MainAction.SETTING);
        }
        AirPageUiSet airUiSet = getAirUiSet(this.mContext);
        if (this.eachId == 3) {
            airUiSet.getRearArea().setWindMaxLevel(3);
        }
        airUiSet.setOnAirPageStatusListener(new OnAirPageStatusListener() { // from class: com.hzbhd.canbus.car._346.UiMgr.1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener
            public void onStatusChange(int i) {
                CanbusMsgSender.sendMsg(new byte[]{22, -112, 38, 0});
            }
        });
        SettingPageUiSet settingUiSet = getSettingUiSet(this.mContext);
        settingUiSet.setOnSettingItemSelectListener(this.onSettingItemSelectListener);
        settingUiSet.setOnSettingPageStatusListener(new OnSettingPageStatusListener() { // from class: com.hzbhd.canbus.car._346.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnSettingPageStatusListener
            public void onStatusChange(int i) {
            }
        });
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

    public void selectCarModel() {
        int eachId = getEachId();
        if (eachId == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -30, 1});
            return;
        }
        if (eachId == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, -30, 2});
        } else if (eachId == 3) {
            CanbusMsgSender.sendMsg(new byte[]{22, -30, 4});
        } else {
            if (eachId != 4) {
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, -30, 5});
        }
    }
}
