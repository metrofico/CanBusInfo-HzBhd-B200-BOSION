package com.hzbhd.canbus.car_cus._446;

import com.hzbhd.canbus.car_cus._446.Interface.CanInfoObserver;
import com.hzbhd.ui.util.BaseUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* loaded from: classes2.dex */
public class CanObserver {
    private static CanObserver building;
    private static List<CanInfoObserver> observerList = new ArrayList();

    public static CanObserver getInstance() {
        if (building == null) {
            building = new CanObserver();
        }
        return building;
    }

    private CanObserver() {
    }

    public void register(CanInfoObserver canInfoObserver) {
        if (observerList.contains(canInfoObserver)) {
            return;
        }
        observerList.add(canInfoObserver);
    }

    public void unRegister(CanInfoObserver canInfoObserver) {
        if (observerList.isEmpty() || !observerList.contains(canInfoObserver)) {
            return;
        }
        observerList.remove(canInfoObserver);
    }

    public void dataChange() {
        BaseUtil.INSTANCE.runUi(new Function0<Unit>() { // from class: com.hzbhd.canbus.car_cus._446.CanObserver.1
            @Override // kotlin.jvm.functions.Function0
            public Unit invoke() {
                Iterator it = CanObserver.observerList.iterator();
                while (it.hasNext()) {
                    ((CanInfoObserver) it.next()).dataChange();
                }
                return null;
            }
        });
    }
}
