package com.hzbhd.canbus.car._249;

import android.content.Context;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import kotlin.jvm.internal.ByteCompanionObject;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    private Context mContext;

    public UiMgr(Context context) {
        this.mContext = context;
        getAirUiSet(context).setOnAirPageStatusListener(new OnAirPageStatusListener() { // from class: com.hzbhd.canbus.car._249.UiMgr.1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener
            public void onStatusChange(int i) {
                if (i == 1) {
                    UiMgr.this.sendData(new byte[]{22, -112, 35});
                    UiMgr.this.sendData(new byte[]{22, -112, 54});
                    UiMgr.this.sendData(new byte[]{22, -112, ByteCompanionObject.MAX_VALUE});
                }
            }
        });
    }
}
