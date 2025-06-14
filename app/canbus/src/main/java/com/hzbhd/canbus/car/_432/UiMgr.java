package com.hzbhd.canbus.car._432;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirInitListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SyncPageUiSet;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UiMgr.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u000b\u001a\u00020\f2\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\r\u001a\u00020\u000eJ\u0018\u0010\u000f\u001a\u00020\f2\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0010\u001a\u00020\u000eR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u0011"}, d2 = {"Lcom/hzbhd/canbus/car/_432/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "syncPageUiSet", "Lcom/hzbhd/canbus/ui_set/SyncPageUiSet;", "getSyncPageUiSet", "()Lcom/hzbhd/canbus/ui_set/SyncPageUiSet;", "setSyncPageUiSet", "(Lcom/hzbhd/canbus/ui_set/SyncPageUiSet;)V", "getDrivingItemIndexes", "", "titleSrn", "", "getDrivingPageIndexes", "headTitleSrn", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class UiMgr extends AbstractUiMgr {
    private SyncPageUiSet syncPageUiSet;

    public UiMgr(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SyncPageUiSet syncPageUiSet = getSyncPageUiSet(context);
        Intrinsics.checkNotNullExpressionValue(syncPageUiSet, "getSyncPageUiSet(context)");
        this.syncPageUiSet = syncPageUiSet;
        DriverDataPageUiSet driverDataPageUiSet = getDriverDataPageUiSet(context);
        Intrinsics.checkNotNullExpressionValue(driverDataPageUiSet, "getDriverDataPageUiSet(context)");
        driverDataPageUiSet.setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener() { // from class: com.hzbhd.canbus.car._432.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener
            public final void onStatusChange(int i) {
                UiMgr.m852_init_$lambda0(i);
            }
        });
        AirPageUiSet airUiSet = getAirUiSet(context);
        Intrinsics.checkNotNullExpressionValue(airUiSet, "getAirUiSet(context)");
        airUiSet.setOnAirInitListener(new OnAirInitListener() { // from class: com.hzbhd.canbus.car._432.UiMgr$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirInitListener
            public final void onInit() {
                UiMgr.m853_init_$lambda1();
            }
        });
    }

    public final SyncPageUiSet getSyncPageUiSet() {
        return this.syncPageUiSet;
    }

    public final void setSyncPageUiSet(SyncPageUiSet syncPageUiSet) {
        Intrinsics.checkNotNullParameter(syncPageUiSet, "<set-?>");
        this.syncPageUiSet = syncPageUiSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-0, reason: not valid java name */
    public static final void m852_init_$lambda0(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 96, 0});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 97, 0});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 98, 0});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 99, 0});
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-1, reason: not valid java name */
    public static final void m853_init_$lambda1() {
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 33, 0});
    }

    public final int getDrivingPageIndexes(Context context, String headTitleSrn) {
        Intrinsics.checkNotNullParameter(headTitleSrn, "headTitleSrn");
        List<DriverDataPageUiSet.Page> list = getPageUiSet(context).getDriverDataPageUiSet().getList();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (Intrinsics.areEqual(list.get(i).getHeadTitleSrn(), headTitleSrn)) {
                return i;
            }
        }
        return 404;
    }

    public final int getDrivingItemIndexes(Context context, String titleSrn) {
        Intrinsics.checkNotNullParameter(titleSrn, "titleSrn");
        List<DriverDataPageUiSet.Page> list = getPageUiSet(context).getDriverDataPageUiSet().getList();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Iterator<DriverDataPageUiSet.Page> it = list.iterator();
            while (it.hasNext()) {
                List<DriverDataPageUiSet.Page.Item> itemList = it.next().getItemList();
                int size2 = itemList.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    if (Intrinsics.areEqual(itemList.get(i2).getTitleSrn(), titleSrn)) {
                        return i2;
                    }
                }
            }
        }
        return 404;
    }
}
