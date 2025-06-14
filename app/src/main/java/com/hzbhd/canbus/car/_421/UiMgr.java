package com.hzbhd.canbus.car._421;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    AirPageUiSet airPageUiSet;
    Context mContext;
    private MsgMgr msgMgr;
    ParkPageUiSet parkPageUiSet;
    SettingPageUiSet settingPageUiSet;

    public UiMgr(final Context context) {
        this.parkPageUiSet = getParkPageUiSet(context);
        this.settingPageUiSet = getSettingUiSet(context);
        this.mContext = context;
        this.airPageUiSet = getAirUiSet(context);
        this.parkPageUiSet.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._421.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public void onClickItem(int i) {
                if (i == 0) {
                    UiMgr.this.sendPanoramicItemMessage(0);
                } else if (i == 1) {
                    UiMgr.this.sendPanoramicItemMessage(1);
                } else {
                    if (i != 2) {
                        return;
                    }
                    UiMgr.this.sendPanoramicItemMessage(2);
                }
            }
        });
        this.settingPageUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._421.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                String titleSrn = UiMgr.this.settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "_421_Item_01":
                        UiMgr.this.sendSettingItemMessage(5, i3);
                        break;
                    case "_421_Item_02":
                        UiMgr.this.sendSettingItemMessage(4, i3);
                        break;
                    case "_421_Item_05":
                        UiMgr.this.sendSettingItemMessage(1, i3);
                        break;
                }
            }
        });
        this.settingPageUiSet.setOnSettingItemSwitchListener(new OnSettingItemSwitchListener() { // from class: com.hzbhd.canbus.car._421.UiMgr.3
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener
            public void onSwitch(int i, int i2, int i3) {
                String titleSrn = UiMgr.this.settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "_421_Item_03":
                        UiMgr.this.sendSettingItemMessage(3, i3);
                        break;
                    case "_421_Item_04":
                        UiMgr.this.sendSettingItemMessage(2, i3);
                        break;
                    case "_421_Item_06":
                        UiMgr.this.sendSettingItemMessage(16, i3);
                        break;
                    case "_421_Item_07":
                        UiMgr.this.sendSettingItemMessage(9, i3);
                        break;
                    case "_421_Item_08":
                        UiMgr.this.sendSettingItemMessage(8, i3);
                        break;
                    case "_421_Item_09":
                        UiMgr.this.sendSettingItemMessage(7, i3);
                        break;
                    case "_421_Item_10":
                        UiMgr.this.sendSettingItemMessage(6, i3);
                        Log.d("lai", "onSwitch: " + i3);
                        break;
                    case "F_camera":
                        UiMgr uiMgr = UiMgr.this;
                        uiMgr.getMsgMgr(uiMgr.mContext).updateSetting(i, i2, i3);
                        if (i3 == 0) {
                            SharePreUtil.setBoolValue(context, "F_camera", false);
                        }
                        if (i3 == 1) {
                            SharePreUtil.setBoolValue(context, "F_camera", true);
                            break;
                        }
                        break;
                }
            }
        });
        this.airPageUiSet.getFrontArea().setOnAirPageStatusListener(new OnAirPageStatusListener() { // from class: com.hzbhd.canbus.car._421.UiMgr.4
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener
            public void onStatusChange(int i) {
                CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, 49});
            }
        });
        this.settingPageUiSet.setOnSettingPageStatusListener(new OnSettingPageStatusListener() { // from class: com.hzbhd.canbus.car._421.UiMgr.5
            @Override // com.hzbhd.canbus.interfaces.OnSettingPageStatusListener
            public void onStatusChange(int i) {
                CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, -121});
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendPanoramicItemMessage(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, 74, 1, (byte) i});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSettingItemMessage(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -116, (byte) i, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
        MsgMgr msgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(this.mContext);
        this.msgMgr = msgMgr;
        return msgMgr;
    }
}
