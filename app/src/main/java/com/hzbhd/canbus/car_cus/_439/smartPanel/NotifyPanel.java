package com.hzbhd.canbus.car_cus._439.smartPanel;

import com.hzbhd.canbus.interfaces.ActionDo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class NotifyPanel {
    private static NotifyPanel notifyPanel;
    List<ActionDo> list = new ArrayList();

    public static NotifyPanel getInstance() {
        if (notifyPanel == null) {
            notifyPanel = new NotifyPanel();
        }
        return notifyPanel;
    }

    private NotifyPanel() {
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
