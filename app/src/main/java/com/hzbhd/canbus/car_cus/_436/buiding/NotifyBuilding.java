package com.hzbhd.canbus.car_cus._436.buiding;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.hzbhd.canbus.car_cus._436.Interface.MdNotifyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class NotifyBuilding {
    private static final Integer UI_UPDATE_TAG = 9090;
    private static NotifyBuilding notifyBuilding;
    private List<MdNotifyListener> list = new ArrayList();
    private Handler handler = new Handler(Looper.getMainLooper()) { // from class: com.hzbhd.canbus.car_cus._436.buiding.NotifyBuilding.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.arg1 == NotifyBuilding.UI_UPDATE_TAG.intValue()) {
                Iterator it = NotifyBuilding.this.list.iterator();
                while (it.hasNext()) {
                    ((MdNotifyListener) it.next()).updateUi();
                }
            }
        }
    };

    public static NotifyBuilding getInstance() {
        if (notifyBuilding == null) {
            notifyBuilding = new NotifyBuilding();
        }
        return notifyBuilding;
    }

    private NotifyBuilding() {
    }

    public void register(MdNotifyListener mdNotifyListener) {
        this.list.add(mdNotifyListener);
    }

    public void unRegister(MdNotifyListener mdNotifyListener) {
        if (this.list.isEmpty()) {
            return;
        }
        this.list.remove(mdNotifyListener);
    }

    public void updateUi() {
        Message message = new Message();
        message.arg1 = UI_UPDATE_TAG.intValue();
        this.handler.sendMessage(message);
    }
}
