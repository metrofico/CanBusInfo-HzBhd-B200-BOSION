package com.hzbhd.canbus.car._305;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.MainAction;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    private Context mContext;

    public UiMgr(Context context) {
        this.mContext = context;
        if (getCurrentCarId() != 1) {
            removeMainPrjBtnByName(context, MainAction.SETTING);
        } else {
            getSettingUiSet(context).setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._305.UiMgr$$ExternalSyntheticLambda0
                @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
                public final void onClickItem(int i, int i2, int i3) {
                    UiMgr.lambda$new$0(i, i2, i3);
                }
            });
        }
    }

    static /* synthetic */ void lambda$new$0(int i, int i2, int i3) {
        if (i == 0 && i2 == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 1, (byte) (i3 + 1)});
        }
    }
}
