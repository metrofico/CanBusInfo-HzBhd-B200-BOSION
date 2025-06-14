package com.hzbhd.canbus.car._387;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    AirPageUiSet airPageUiSet;
    Context mContext;
    ParkPageUiSet parkPageUiSet;
    SettingPageUiSet settingPageUiSet;
    int eachId = getEachId();
    int differentId = getCurrentCarId();

    public UiMgr(Context context) {
        this.mContext = context;
        this.airPageUiSet = getAirUiSet(this.mContext);
        this.settingPageUiSet = getSettingUiSet(this.mContext);
        this.parkPageUiSet = getParkPageUiSet(this.mContext);
        int i = this.differentId;
        if (i == 3 || i == 1) {
            this.airPageUiSet.getFrontArea().setWindMaxLevel(8);
        }
        this.settingPageUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._387.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i2, int i3, int i4) {
                String titleSrn = UiMgr.this.settingPageUiSet.getList().get(i2).getItemList().get(i3).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "_387_item_01":
                        UiMgr.this.sendSettingMessage(7, i4);
                        break;
                    case "_387_item_02":
                        UiMgr.this.sendSettingMessage(6, i4);
                        break;
                    case "_387_item_03":
                        UiMgr.this.sendSettingMessage(5, i4);
                        break;
                    case "_387_item_04":
                        UiMgr.this.sendSettingMessage(3, i4);
                        break;
                    case "_387_item_05":
                        UiMgr.this.sendSettingMessage(2, i4);
                        break;
                    case "_387_item_06":
                        UiMgr.this.sendSettingMessage(1, i4);
                        break;
                    case "_387_item_07":
                        UiMgr.this.sendSettingMessage(12, i4);
                        break;
                    case "_387_item_08":
                        UiMgr.this.sendSettingMessage(11, i4);
                        break;
                    case "_387_item_09":
                        UiMgr.this.sendSettingMessage(8, i4);
                        break;
                    case "_387_item_10":
                        UiMgr.this.sendSettingMessage(10, i4);
                        break;
                    case "_387_item_11":
                        UiMgr.this.sendSettingMessage(9, i4);
                        break;
                }
            }
        });
        this.settingPageUiSet.setOnSettingPageStatusListener(new OnSettingPageStatusListener() { // from class: com.hzbhd.canbus.car._387.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnSettingPageStatusListener
            public void onStatusChange(int i2) {
                UiMgr.this.ActiveRequest(120);
            }
        });
        this.parkPageUiSet.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._387.UiMgr.3
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public void onClickItem(int i2) {
                if (i2 == 0) {
                    UiMgr.this.sendPanoramicMessage(5);
                    return;
                }
                if (i2 == 1) {
                    UiMgr.this.sendPanoramicMessage(6);
                    return;
                }
                if (i2 == 2) {
                    UiMgr.this.sendPanoramicMessage(7);
                } else if (i2 == 3) {
                    UiMgr.this.sendPanoramicMessage(8);
                } else {
                    if (i2 != 4) {
                        return;
                    }
                    UiMgr.this.sendPanoramicMessage(9);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSettingMessage(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, 126, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 0, (byte) i, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendPanoramicMessage(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -14, 16, (byte) i});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ActiveRequest(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, (byte) i});
    }
}
