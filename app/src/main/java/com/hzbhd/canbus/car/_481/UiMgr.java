package com.hzbhd.canbus.car._481;

import android.content.Context;

import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;

import java.util.List;


public class UiMgr extends AbstractUiMgr {
    Context context;
    SettingPageUiSet settingPageUiSet;
    private MsgMgr mMsgMgr;

    public static final String L0R0 = "Share_481_Settings_Driving_L0R0";
    public static final String L1R0 = "Share_481_Settings_Driving_L1R0";
    //
    private final OnSettingItemSelectListener mOnSettingItemSelectListener = (sectionId, category, itemSelected) -> {
        String titleSrn = UiMgr.this.settingPageUiSet.getList().get(sectionId).getTitleSrn();
        switch (titleSrn) {
            case "_481_setting_driving": {
                if (category == 0) {
                    // comfort = 0
                    // sport = 1
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 9, (byte) (itemSelected)});
                    getmMsgMgr().updateSettings(UiMgr.this.context, L0R0, sectionId, category, itemSelected);
                }
                break;
            }
            case "_327_setting_the_light_that_accompanies_me_home": {
                if (category == 0) {
                    // off = 0
                    // 15s = 1
                    // 30s = 2
                    // 45s = 3
                    // 60s = 4
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 12, (byte) (itemSelected)});
                    getmMsgMgr().updateSettings(UiMgr.this.context, L1R0, sectionId, category, itemSelected);
                }
                break;
            }
        }
    };

    private void initSettingsData() {
        MsgMgr msgMgr = getmMsgMgr();
        if (getLeftIndexes(context, "_481_setting_driving") != -1) {
            msgMgr.updateSettings(this.context, L0R0, getLeftIndexes(context, "_481_setting_driving"), 0, SharePreUtil.getIntValue(context, L0R0, 0));
        }
        if (getLeftIndexes(context, "_327_setting_the_light_that_accompanies_me_home") != -1) {
            msgMgr.updateSettings(this.context, L1R0, getLeftIndexes(context, "_327_setting_the_light_that_accompanies_me_home"), 0, SharePreUtil.getIntValue(context, L1R0, 0));
        }
    }

    protected int getLeftIndexes(Context context, String str) {
        List<SettingPageUiSet.ListBean> list = getPageUiSet(context).getSettingPageUiSet().getList();
        for (int i = 0; i < list.size(); i++) {
            if (str.equals(list.get(i).getTitleSrn())) {
                return i;
            }
        }
        return -1;
    }

    public UiMgr(Context context) {
        this.context = context;
        this.settingPageUiSet = getSettingUiSet(context);
        this.settingPageUiSet.setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
        initSettingsData();
    }

    public MsgMgr getmMsgMgr() {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }


}
