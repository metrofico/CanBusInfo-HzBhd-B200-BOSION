package com.hzbhd.canbus.car._481;

import android.content.Context;

import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car._327.MsgMgr;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;


public class UiMgr extends AbstractUiMgr {
    Context context;
    SettingPageUiSet settingPageUiSet;
    private MsgMgr mMsgMgr;

    public static final String L0R0 = "Share_481_Settings_Driving_L0R0";
    //
    private final OnSettingItemSelectListener mOnSettingItemSelectListener = (sectionId, category, itemSelected) -> {
        String titleSrn = UiMgr.this.settingPageUiSet.getList().get(sectionId).getTitleSrn();
        switch (titleSrn) {
            case "_481_setting_driving": {
                if (category == 0) {
                    // comfort = 0
                    // sport = 1
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 13, (byte) (itemSelected)});
                    getmMsgMgr().updateSettings(UiMgr.this.context, L0R0, sectionId, category, itemSelected);
                }
                break;
            }
        }
    };

    public UiMgr(Context context) {
        this.context = context;
        this.settingPageUiSet = getSettingUiSet(context);
    }

    public MsgMgr getmMsgMgr() {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }

    public void CartypeSend() {
        // CanbusMsgSender.sendMsg(new byte[]{22, -18, -112, (byte) SharePreUtil.getIntValue(this.mContext, this.CAR_TYPE, 0)});
    }

}
