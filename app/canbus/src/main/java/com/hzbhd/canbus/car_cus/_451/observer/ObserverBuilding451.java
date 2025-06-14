package com.hzbhd.canbus.car_cus._451.observer;

import com.hzbhd.canbus.car_cus._451.Interface.CanInfoObserver;
import com.hzbhd.ui.util.BaseUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* loaded from: classes2.dex */
public class ObserverBuilding451 {
    private static ObserverBuilding451 building;
    private static List<CanInfoObserver> observerList = new ArrayList();

    private static class Holder {
        private static final ObserverBuilding451 INSTANCE = new ObserverBuilding451();

        private Holder() {
        }
    }

    public static ObserverBuilding451 getInstance() {
        return Holder.INSTANCE;
    }

    private ObserverBuilding451() {
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
        BaseUtil.INSTANCE.runUi(new Function0<Unit>() { // from class: com.hzbhd.canbus.car_cus._451.observer.ObserverBuilding451.1
            @Override // kotlin.jvm.functions.Function0
            public Unit invoke() {
                Iterator it = ObserverBuilding451.observerList.iterator();
                while (it.hasNext()) {
                    ((CanInfoObserver) it.next()).dataChange();
                }
                return null;
            }
        });
    }
}
