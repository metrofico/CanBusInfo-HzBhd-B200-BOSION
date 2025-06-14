package com.hzbhd.canbus.util;

import android.content.Context;
import android.content.Intent;
import com.hzbhd.canbus.receiver.SpeechReceiver;

/* loaded from: classes2.dex */
public class KeyBroadcast {
    private static Intent speechIntent;

    public static void sendSpeechBroadcast(Context context) {
        if (speechIntent == null) {
            speechIntent = new Intent(SpeechReceiver.speech_rx_can);
        }
        speechIntent.putExtra("tx_action", "speech.dialog.show");
        context.sendBroadcast(speechIntent);
    }
}
