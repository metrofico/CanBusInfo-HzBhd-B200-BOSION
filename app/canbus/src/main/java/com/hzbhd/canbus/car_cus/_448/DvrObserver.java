package com.hzbhd.canbus.car_cus._448;

import com.hzbhd.canbus.car_cus._448.Interface.DvrDataInterface;
import com.hzbhd.ui.util.BaseUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* loaded from: classes2.dex */
public class DvrObserver {
    private static List<DvrDataInterface> observerList = new ArrayList();

    private static class Holder {
        private static final DvrObserver canObserver = new DvrObserver();

        private Holder() {
        }
    }

    public static DvrObserver getInstance() {
        return Holder.canObserver;
    }

    private DvrObserver() {
    }

    public void register(DvrDataInterface dvrDataInterface) {
        if (observerList.contains(dvrDataInterface)) {
            return;
        }
        observerList.add(dvrDataInterface);
    }

    public void unRegister(DvrDataInterface dvrDataInterface) {
        if (observerList.isEmpty() || !observerList.contains(dvrDataInterface)) {
            return;
        }
        observerList.remove(dvrDataInterface);
    }

    public void dataChange(final String str) {
        BaseUtil.INSTANCE.runUi(new Function0<Unit>() { // from class: com.hzbhd.canbus.car_cus._448.DvrObserver.1
            @Override // kotlin.jvm.functions.Function0
            public Unit invoke() {
                Iterator it = DvrObserver.observerList.iterator();
                while (it.hasNext()) {
                    ((DvrDataInterface) it.next()).dataChange(str);
                }
                return null;
            }
        });
    }
}
