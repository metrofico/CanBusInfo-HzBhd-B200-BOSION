package com.hzbhd.canbus.car_cus._467.air.observer;

import com.hzbhd.canbus.car_cus._467.air.Interface.AirInfoObserver;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class AirBuilder {
    private static AirBuilder building;
    private static List<AirInfoObserver> observerList = new ArrayList();

    public static AirBuilder getInstance() {
        if (building == null) {
            building = new AirBuilder();
        }
        return building;
    }

    private AirBuilder() {
    }

    public void register(AirInfoObserver airInfoObserver) {
        if (observerList.contains(airInfoObserver)) {
            return;
        }
        observerList.add(airInfoObserver);
    }

    public void unRegister(AirInfoObserver airInfoObserver) {
        if (observerList.isEmpty() || !observerList.contains(airInfoObserver)) {
            return;
        }
        observerList.remove(airInfoObserver);
    }

    public void dataChange() {
        Iterator<AirInfoObserver> it = observerList.iterator();
        while (it.hasNext()) {
            it.next().infoChange();
        }
    }
}
