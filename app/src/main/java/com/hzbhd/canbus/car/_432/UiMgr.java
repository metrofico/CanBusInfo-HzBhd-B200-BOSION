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




public final class UiMgr extends AbstractUiMgr {
    private SyncPageUiSet syncPageUiSet;

    public UiMgr(Context context) {

        SyncPageUiSet syncPageUiSet = getSyncPageUiSet(context);

        this.syncPageUiSet = syncPageUiSet;
        DriverDataPageUiSet driverDataPageUiSet = getDriverDataPageUiSet(context);

        driverDataPageUiSet.setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener() { // from class: com.hzbhd.canbus.car._432.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener
            public final void onStatusChange(int i) {
                UiMgr.m852_init_$lambda0(i);
            }
        });
        AirPageUiSet airUiSet = getAirUiSet(context);

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
