package com.hzbhd.canbus.car._422;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    public UiMgr(Context context) {
        getAirUiSet(context).getFrontArea().setOnAirPageStatusListener(new OnAirPageStatusListener() { // from class: com.hzbhd.canbus.car._422.UiMgr.1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener
            public void onStatusChange(int i) {
                CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, 49});
            }
        });
    }
}
