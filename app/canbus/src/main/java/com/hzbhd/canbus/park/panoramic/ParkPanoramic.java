package com.hzbhd.canbus.park.panoramic;

import android.view.MotionEvent;
import com.hzbhd.canbus.adapter.park.ParkManager;

/* loaded from: classes2.dex */
public class ParkPanoramic {
    private static final boolean ENABLE_PARK_PANORAMIC = true;
    private ParkManager mParkManager;

    public static boolean isEnableParkPanoramic() {
        return true;
    }

    public void constructParkPanoramic() {
        ParkManager atvManager = ParkManager.getAtvManager();
        this.mParkManager = atvManager;
        atvManager.sendPanoramicParkOn(true);
        this.mParkManager.sendPanoramicParkWH(800, 480);
    }

    public void destroyParkPanoramic() {
        this.mParkManager.sendPanoramicParkOn(false);
    }

    public void sendTouch(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        int action = motionEvent.getAction();
        int i = 2;
        if (action == 0) {
            i = 1;
        } else if (action == 1) {
            i = 3;
        } else if (action != 2) {
            i = 0;
        }
        this.mParkManager.sendPanoramicParkPos(i, x, y);
    }
}
