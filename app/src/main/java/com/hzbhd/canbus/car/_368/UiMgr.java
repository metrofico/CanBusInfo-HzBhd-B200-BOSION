package com.hzbhd.canbus.car._368;

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
import nfore.android.bt.res.NfDef;




public final class UiMgr extends AbstractUiMgr {
    private final SettingPageUiSet mSettingPageUiSet;

    public UiMgr(final Context context) {

        SettingPageUiSet settingUiSet = getSettingUiSet(context);

        this.mSettingPageUiSet = settingUiSet;
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._368.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public final void onClickItem(int i, int i2, int i3) {
                UiMgr.m795lambda4$lambda3(context, i, i2, i3);
            }
        });
    }

    public final SettingPageUiSet getMSettingPageUiSet() {
        return this.mSettingPageUiSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-4$lambda-3, reason: not valid java name */
    public static final void m795lambda4$lambda3(Context context, int i, int i2, int i3) {

        String settingViewItemName = InitUtilsKt.getSettingViewItemName(i, i2);
        if (Intrinsics.areEqual(settingViewItemName, "S367_Car_0")) {
            byte b = 4;
            byte[] bArr = new byte[4];
            bArr[0] = 22;
            bArr[1] = 36;
            if (i3 == 1) {
                b = 2;
            } else if (i3 != 2) {
                return;
            }
            bArr[2] = b;
            bArr[3] = NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED;
            CanbusMsgSender.sendMsg(bArr);
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = InitUtilsKt.getMSettingItemIndex().get("S367_Car_0");
            if (itemListBean != null) {
                itemListBean.setValue(itemListBean.getValueSrnArray().get(i3));
            }
            MsgMgrInterface canMsgMgr = MsgMgrFactory.getCanMsgMgr(context);

            ((MsgMgr) canMsgMgr).updateSettingActivity(null);
            return;
        }
        if (Intrinsics.areEqual(settingViewItemName, "S368_Color0")) {
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = InitUtilsKt.getMSettingItemIndex().get("S368_Color0");
            if (itemListBean2 != null) {
                itemListBean2.setValue(itemListBean2.getValueSrnArray().get(i3));
            }
            MsgMgrInterface canMsgMgr2 = MsgMgrFactory.getCanMsgMgr(context);

            MsgMgr msgMgr = (MsgMgr) canMsgMgr2;
            msgMgr.updateSettingActivity(null);
            msgMgr.setColor(Integer.valueOf(i3));
        }
    }
}
