package com.hzbhd.canbus.car._307;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;


public class UiMgr extends AbstractUiMgr {
    private Context mContext;
    OnSettingItemSelectListener mOnSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._307.UiMgr.1
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            String titleSrn = UiMgr.this.mSettingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
            titleSrn.hashCode();
            switch (titleSrn) {
                case "language_setup":
                    if (i3 <= 8) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte) (i3 + 1)});
                    } else if (i3 <= 11) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte) (i3 + 2)});
                    } else if (i3 <= 14) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte) (i3 + 4)});
                    } else if (i3 == 15) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte) (i3 + 5)});
                    } else if (i3 <= 22) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte) (i3 + 6)});
                    } else if (i3 <= 24) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte) (i3 + 8)});
                    } else if (i3 == 25) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, 15});
                    } else if (i3 == 26) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, 34});
                    }
                    UiMgr uiMgr = UiMgr.this;
                    uiMgr.getMsgMgr(uiMgr.mContext).updateSettings(i, i2, i3);
                    break;
                case "_307_title_10":
                    CanbusMsgSender.sendMsg(new byte[]{22, 111, 19, (byte) i3, 0, 0});
                    break;
                case "_307_title_11":
                    CanbusMsgSender.sendMsg(new byte[]{22, 111, 33, (byte) i3, 0, 0});
                    break;
                case "_307_title_12":
                    CanbusMsgSender.sendMsg(new byte[]{22, 111, 34, (byte) i3, 0, 0});
                    break;
                case "_307_title_13":
                    CanbusMsgSender.sendMsg(new byte[]{22, 111, 35, (byte) i3, 0, 0});
                    break;
                case "_307_title_14":
                    CanbusMsgSender.sendMsg(new byte[]{22, 111, 49, (byte) i3, 0, 0});
                    break;
                case "_307_title_15":
                    CanbusMsgSender.sendMsg(new byte[]{22, 111, 50, (byte) i3, 0, 0});
                    break;
                case "_307_title_16":
                    CanbusMsgSender.sendMsg(new byte[]{22, 111, 51, (byte) i3, 0, 0});
                    UiMgr uiMgr2 = UiMgr.this;
                    uiMgr2.getMsgMgr(uiMgr2.mContext).updateSettings(i, i2, i3);
                    break;
                case "_307_title_17":
                    CanbusMsgSender.sendMsg(new byte[]{22, 111, 20, (byte) i3, 0, 0});
                    break;
                case "_307_title_18":
                    CanbusMsgSender.sendMsg(new byte[]{22, 111, 5, (byte) i3, 0, 0});
                    break;
                case "_307_title_4":
                    CanbusMsgSender.sendMsg(new byte[]{22, 111, 1, (byte) i3, 0, 0});
                    break;
                case "_307_title_5":
                    CanbusMsgSender.sendMsg(new byte[]{22, 111, 2, (byte) i3, 0, 0});
                    break;
                case "_307_title_6":
                    CanbusMsgSender.sendMsg(new byte[]{22, 111, 3, (byte) i3, 0, 0});
                    break;
                case "_307_title_7":
                    CanbusMsgSender.sendMsg(new byte[]{22, 111, 4, (byte) i3, 0, 0});
                    break;
                case "_307_title_8":
                    CanbusMsgSender.sendMsg(new byte[]{22, 111, 17, (byte) i3, 0, 0});
                    break;
                case "_307_title_9":
                    CanbusMsgSender.sendMsg(new byte[]{22, 111, 18, (byte) i3, 0, 0});
                    break;
            }
        }
    };
    private SettingPageUiSet mSettingPageUiSet;
    private MsgMgr msgMgr;

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        if (getCurrentCarId() == 0) {
            removeMainPrjBtnByName(context, MainAction.SETTING);
        }
    }

    public UiMgr(Context context) {
        this.mContext = context;
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        this.mSettingPageUiSet = settingUiSet;
        settingUiSet.setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
        if (this.msgMgr == null) {
            this.msgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.msgMgr;
    }
}
