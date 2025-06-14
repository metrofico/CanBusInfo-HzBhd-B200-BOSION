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

/* compiled from: UiMgr.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/hzbhd/canbus/car/_367/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mSettingPageUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "getMSettingPageUiSet", "()Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class UiMgr extends AbstractUiMgr {
    private final SettingPageUiSet mSettingPageUiSet;

    public UiMgr(final Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        Intrinsics.checkNotNullExpressionValue(settingUiSet, "getSettingUiSet(context)");
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
        Intrinsics.checkNotNullParameter(context, "$context");
        if (Intrinsics.areEqual(InitUtilsKt.getSettingViewItemName(i, i2), "S367_Car_0")) {
            CanbusMsgSender.sendMsg(new byte[]{22, -45, (byte) (i3 + 1), 0});
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = InitUtilsKt.getMSettingItemIndex().get("S367_Car_0");
            if (itemListBean != null) {
                itemListBean.setValue(itemListBean.getValueSrnArray().get(i3));
                MsgMgrInterface canMsgMgr = MsgMgrFactory.getCanMsgMgr(context);
                Intrinsics.checkNotNull(canMsgMgr, "null cannot be cast to non-null type com.hzbhd.canbus.car._367.MsgMgr");
                ((MsgMgr) canMsgMgr).updateSettingActivity(null);
            }
        }
    }
}
