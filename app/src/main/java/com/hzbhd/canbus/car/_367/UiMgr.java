package com.hzbhd.canbus.car._367;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.InitUtilsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;




public final class UiMgr extends AbstractUiMgr {
    private final SettingPageUiSet mSettingPageUiSet;

    public UiMgr(final Context context) {

        SettingPageUiSet settingUiSet = getSettingUiSet(context);

        this.mSettingPageUiSet = settingUiSet;
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._367.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public final void onClickItem(int i, int i2, int i3) {
                UiMgr.m794_init_$lambda1(context, i, i2, i3);
            }
        });
    }

    public final SettingPageUiSet getMSettingPageUiSet() {
        return this.mSettingPageUiSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-1, reason: not valid java name */
    public static final void m794_init_$lambda1(Context context, int i, int i2, int i3) {

        if (Intrinsics.areEqual(InitUtilsKt.getSettingViewItemName(i, i2), "S367_Car_0")) {
            CanbusMsgSender.sendMsg(new byte[]{22, -45, (byte) (i3 + 1), 0});
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = InitUtilsKt.getMSettingItemIndex().get("S367_Car_0");
            if (itemListBean != null) {
                itemListBean.setValue(itemListBean.getValueSrnArray().get(i3));
                MsgMgrInterface canMsgMgr = MsgMgrFactory.getCanMsgMgr(context);

                ((MsgMgr) canMsgMgr).updateSettingActivity(null);
            }
        }
    }
}
