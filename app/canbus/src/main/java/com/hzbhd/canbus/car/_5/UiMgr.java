package com.hzbhd.canbus.car._5;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    private Context mContext;
    private MsgMgr mMsgMgr;

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
    }

    public UiMgr(Context context) {
        this.mContext = context;
        this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._5.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public final void onClickItem(int i, int i2, int i3) {
                UiMgr.lambda$new$0(i, i2, i3);
            }
        });
        settingUiSet.setOnSettingPageStatusListener(new OnSettingPageStatusListener() { // from class: com.hzbhd.canbus.car._5.UiMgr$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnSettingPageStatusListener
            public final void onStatusChange(int i) {
                this.f$0.m893lambda$new$1$comhzbhdcanbuscar_5UiMgr(i);
            }
        });
    }

    static /* synthetic */ void lambda$new$0(int i, int i2, int i3) {
        if (i == 0) {
            if (i2 == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, 76, 9, (byte) i3});
            } else if (i2 == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, 76, 10, (byte) i3});
            } else if (i2 == 2) {
                CanbusMsgSender.sendMsg(new byte[]{22, 76, 11, (byte) i3});
            }
        }
    }

    /* renamed from: lambda$new$1$com-hzbhd-canbus-car-_5-UiMgr, reason: not valid java name */
    /* synthetic */ void m893lambda$new$1$comhzbhdcanbuscar_5UiMgr(int i) {
        try {
            this.mMsgMgr.updateSettingData(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
