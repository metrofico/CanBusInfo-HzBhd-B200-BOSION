package com.hzbhd.canbus.receiver;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.hzbhd.canbus.util.ActionControlUtil;

import kotlin.jvm.internal.Intrinsics;


public final class SpeechReceiver extends BroadcastReceiver {
    public static final String speech_rx_can = "speech.rx.can";
    public static final String speech_to_can = "speech.tx.can";
    private static final String tx_action = "tx_action";
    private static final String type = "type";
    private static final String value = "value";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (Intrinsics.areEqual(action, speech_to_can)) {
            ActionControlUtil.acControl(context, intent.getStringExtra(tx_action), intent.getStringExtra("type"), intent.getStringExtra(value));
        } else {
            Intrinsics.areEqual(action, speech_rx_can);
        }
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    public final void registerReceiver(Context context) {
        context.registerReceiver(this, new IntentFilter(speech_to_can));
    }

    public final void unregisterReceiver(Context context) {
        context.unregisterReceiver(this);
    }
}
