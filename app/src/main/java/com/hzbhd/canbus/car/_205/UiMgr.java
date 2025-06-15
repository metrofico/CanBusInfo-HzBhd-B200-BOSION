package com.hzbhd.canbus.car._205;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;


public class UiMgr extends AbstractUiMgr {
    private Context mContext;

    public UiMgr(Context context) {
        this.mContext = context;
        getSettingUiSet(context).setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._205.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                if (i2 != 1) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -121, (byte) i3});
            }
        });
    }
}
