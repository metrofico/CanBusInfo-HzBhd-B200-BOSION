package com.hzbhd.canbus.car._207;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    private MsgMgr mMsgMgr;

    public UiMgr(Context context) {
        this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        final SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemClickListener(new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._207.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
            public void onClickItem(int i, int i2) {
                if (i == 1) {
                    if (i2 != 2) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, 124, 3, 0, 0});
                    return;
                }
                if (i == 2) {
                    if (i2 == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 124, 7, 0, 0});
                        return;
                    } else {
                        if (i2 != 1) {
                            return;
                        }
                        CanbusMsgSender.sendMsg(new byte[]{22, 124, 6, 0, 0});
                        return;
                    }
                }
                if (i != 3) {
                    return;
                }
                if (i2 == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 124, 4, 0, 0});
                } else {
                    if (i2 != 1) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, 124, 5, 0, 0});
                }
            }
        });
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._207.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                if (titleSrn.equals("_207_setting_1")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -51, 1, (byte) i3, 0});
                }
            }
        });
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._207.UiMgr.3
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public void onClickItem(int i, int i2, int i3) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "_207_setting_4":
                        CanbusMsgSender.sendMsg(new byte[]{22, -51, 4, (byte) i3, (byte) UiMgr.this.mMsgMgr.mTime1Minute});
                        break;
                    case "_207_setting_5":
                        CanbusMsgSender.sendMsg(new byte[]{22, -51, 4, (byte) UiMgr.this.mMsgMgr.mTime1Hour, (byte) i3});
                        break;
                    case "_207_setting_10":
                        CanbusMsgSender.sendMsg(new byte[]{22, -51, 7, (byte) i3, (byte) UiMgr.this.mMsgMgr.mTime2Minute});
                        break;
                    case "_207_setting_11":
                        CanbusMsgSender.sendMsg(new byte[]{22, -51, 7, (byte) UiMgr.this.mMsgMgr.mTime2Hour, (byte) i3});
                        break;
                }
            }
        });
        settingUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._207.UiMgr.4
            @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
            public void onConfirmClick(int i, int i2) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "_207_setting_112":
                        CanbusMsgSender.sendMsg(new byte[]{22, -51, 5, 0, 0});
                        break;
                    case "_207_setting_113":
                        CanbusMsgSender.sendMsg(new byte[]{22, -51, 6, 0, 0});
                        break;
                    case "_207_setting_12":
                        CanbusMsgSender.sendMsg(new byte[]{22, -51, 2, 0, 0});
                        break;
                    case "_207_setting_13":
                        CanbusMsgSender.sendMsg(new byte[]{22, -51, 3, 0, 0});
                        break;
                    case "_207_setting_14":
                        CanbusMsgSender.sendMsg(new byte[]{22, 124, 1, 0, 0});
                        break;
                    case "_207_setting_15":
                        CanbusMsgSender.sendMsg(new byte[]{22, 124, 2, 0, 0});
                        break;
                }
            }
        });
    }
}
