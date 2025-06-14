package com.hzbhd.canbus.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.hzbhd.canbus.util.ActionControlUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SpeechReceiver.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\f"}, d2 = {"Lcom/hzbhd/canbus/receiver/SpeechReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "registerReceiver", "unregisterReceiver", "Companion", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class SpeechReceiver extends BroadcastReceiver {
    public static final String speech_rx_can = "speech.rx.can";
    public static final String speech_to_can = "speech.tx.can";
    private static final String tx_action = "tx_action";
    private static final String type = "type";
    private static final String value = "value";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        String action = intent.getAction();
        if (Intrinsics.areEqual(action, speech_to_can)) {
            ActionControlUtil.acControl(context, intent.getStringExtra(tx_action), intent.getStringExtra("type"), intent.getStringExtra(value));
        } else {
            Intrinsics.areEqual(action, speech_rx_can);
        }
    }

    public final void registerReceiver(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        context.registerReceiver(this, new IntentFilter(speech_to_can));
    }

    public final void unregisterReceiver(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        context.unregisterReceiver(this);
    }
}
