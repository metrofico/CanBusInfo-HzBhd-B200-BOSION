package com.hzbhd.canbus.car._363;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;




public final class UiMgr extends AbstractUiMgr {
    private final SettingPageUiSet mSettingPageUiSet;

    public UiMgr(Context context) {

        SettingPageUiSet settingUiSet = getSettingUiSet(context);

        this.mSettingPageUiSet = settingUiSet;
        settingUiSet.setOnSettingItemSwitchListener(new OnSettingItemSwitchListener() { // from class: com.hzbhd.canbus.car._363.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener
            public final void onSwitch(int i, int i2, int i3) {
                UiMgr.m782lambda1$lambda0(this.f$0, i, i2, i3);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-1$lambda-0, reason: not valid java name */
    public static final void m782lambda1$lambda0(UiMgr this$0, int i, int i2, int i3) {

        if (Intrinsics.areEqual(this$0.mSettingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn(), "_340_automatic_folding")) {
            CanbusMsgSender.sendMsg(new byte[]{22, -116, 1, (byte) i3});
        }
    }
}
