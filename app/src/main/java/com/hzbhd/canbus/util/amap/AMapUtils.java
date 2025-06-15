package com.hzbhd.canbus.util.amap;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.IntentFilter;

/* loaded from: classes2.dex */
public class AMapUtils {
    public static final String AUTONAVI_STANDARD_BROADCAST_SEND = "AUTONAVI_STANDARD_BROADCAST_SEND";
    private static AMapUtils mAMapUtils;
    private AMapBroadcast mAMapBroadcast;

    public static AMapUtils getInstance() {
        if (mAMapUtils == null) {
            mAMapUtils = new AMapUtils();
        }
        return mAMapUtils;
    }

    private AMapUtils() {
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    public void startAMap(Context context) {
        if (this.mAMapBroadcast == null) {
            this.mAMapBroadcast = new AMapBroadcast();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(AUTONAVI_STANDARD_BROADCAST_SEND);
            context.registerReceiver(this.mAMapBroadcast, intentFilter);
        }
    }
}
