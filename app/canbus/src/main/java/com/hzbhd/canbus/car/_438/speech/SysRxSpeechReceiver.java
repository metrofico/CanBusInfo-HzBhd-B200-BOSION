package com.hzbhd.canbus.car._438.speech;

import android.content.BroadcastReceiver;
import android.content.Context;

/* loaded from: classes2.dex */
public class SysRxSpeechReceiver extends BroadcastReceiver {
    private static final String TAG = "SysRxSpeechReceiver";
    private Context mContext;
    private ISysRxListener mListener;

    public SysRxSpeechReceiver(Context context, ISysRxListener iSysRxListener) {
        this.mContext = context;
        this.mListener = iSysRxListener;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0051  */
    @Override // android.content.BroadcastReceiver
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onReceive(android.content.Context r7, android.content.Intent r8) {
        /*
            Method dump skipped, instructions count: 1456
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._438.speech.SysRxSpeechReceiver.onReceive(android.content.Context, android.content.Intent):void");
    }
}
