package com.hzbhd.canbus.car_cus._439.drive.util;

import com.hzbhd.canbus.interfaces.ActionDo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class NotifyDriveDate {
    private static NotifyDriveDate notifyPanel;
    List<ActionDo> list = new ArrayList();

    public static NotifyDriveDate getInstance() {
        if (notifyPanel == null) {
            notifyPanel = new NotifyDriveDate();
        }
        return notifyPanel;
    }

    private NotifyDriveDate() {
    }

    public void register(ActionDo actionDo) {
        this.list.add(actionDo);
    }

    public void unregister(ActionDo actionDo) {
        if (this.list.isEmpty() || !this.list.contains(actionDo)) {
            return;
        }
        this.list.remove(actionDo);
    }

    public void update() {
        Iterator<ActionDo> it = this.list.iterator();
        while (it.hasNext()) {
            it.next().todo(null);
        }
    }
}
