package com.hzbhd.canbus.car._339;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;




public final class UiMgr extends AbstractUiMgr {
    private final ParkPageUiSet parkPageUiSet;
    private final SettingPageUiSet settingPageUiSet;

    public UiMgr(Context context) {

        SettingPageUiSet settingUiSet = getSettingUiSet(context);

        this.settingPageUiSet = settingUiSet;
        ParkPageUiSet parkPageUiSet = getParkPageUiSet(context);

        this.parkPageUiSet = parkPageUiSet;
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._339.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public final void onClickItem(int i, int i2, int i3) {
                UiMgr.m716_init_$lambda0(this.f$0, i, i2, i3);
            }
        });
        parkPageUiSet.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._339.UiMgr$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public final void onClickItem(int i) {
                UiMgr.m717_init_$lambda1(this.f$0, i);
            }
        });
    }

    public final SettingPageUiSet getSettingPageUiSet() {
        return this.settingPageUiSet;
    }

    public final ParkPageUiSet getParkPageUiSet() {
        return this.parkPageUiSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-0, reason: not valid java name */
    public static final void m716_init_$lambda0(UiMgr this$0, int i, int i2, int i3) {

        if (i == 0) {
            if (i2 == 0) {
                this$0.sendRadarInfo(i3);
            }
        } else if (i == 1) {
            if (i2 == 0) {
                this$0.sendReversingVideoInfo(i3);
            }
        } else if (i == 2 && i2 == 0) {
            this$0.sendWarningSoundInfo(i3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-1, reason: not valid java name */
    public static final void m717_init_$lambda1(UiMgr this$0, int i) {

        if (i == 0) {
            this$0.sendReversingVideoInfo(0);
        } else {
            if (i != 1) {
                return;
            }
            this$0.sendReversingVideoInfo(1);
        }
    }

    private final void sendRadarInfo(int data0) {
        CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte) data0});
    }

    private final void sendWarningSoundInfo(int data0) {
        CanbusMsgSender.sendMsg(new byte[]{22, -120, (byte) data0});
    }

    private final void sendReversingVideoInfo(int data0) {
        CanbusMsgSender.sendMsg(new byte[]{22, -119, (byte) data0});
    }
}
