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

/* compiled from: UiMgr.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0013"}, d2 = {"Lcom/hzbhd/canbus/car/_339/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "parkPageUiSet", "Lcom/hzbhd/canbus/ui_set/ParkPageUiSet;", "getParkPageUiSet", "()Lcom/hzbhd/canbus/ui_set/ParkPageUiSet;", "settingPageUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "getSettingPageUiSet", "()Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "sendRadarInfo", "", "data0", "", "sendReversingVideoInfo", "sendWarningSoundInfo", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class UiMgr extends AbstractUiMgr {
    private final ParkPageUiSet parkPageUiSet;
    private final SettingPageUiSet settingPageUiSet;

    public UiMgr(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        Intrinsics.checkNotNullExpressionValue(settingUiSet, "getSettingUiSet(context)");
        this.settingPageUiSet = settingUiSet;
        ParkPageUiSet parkPageUiSet = getParkPageUiSet(context);
        Intrinsics.checkNotNullExpressionValue(parkPageUiSet, "getParkPageUiSet(context)");
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
        Intrinsics.checkNotNullParameter(this$0, "this$0");
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
        Intrinsics.checkNotNullParameter(this$0, "this$0");
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
