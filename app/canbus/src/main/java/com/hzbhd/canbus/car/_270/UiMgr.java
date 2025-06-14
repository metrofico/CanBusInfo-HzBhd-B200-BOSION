package com.hzbhd.canbus.car._270;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    private Context mContext;

    public UiMgr(Context context) {
        getSettingUiSet(context).setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._270.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                if (i == 0) {
                    if (i2 == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 111, 2, (byte) i3, -1, -1});
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, 111, 1, (byte) i3, -1, -1});
                    }
                }
            }
        });
    }
}
