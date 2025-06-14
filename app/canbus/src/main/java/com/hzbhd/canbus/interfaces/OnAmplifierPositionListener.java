package com.hzbhd.canbus.interfaces;

import com.hzbhd.canbus.activity.AmplifierActivity;
import kotlin.Metadata;

/* compiled from: OnAmplifierPositionListener.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/hzbhd/canbus/interfaces/OnAmplifierPositionListener;", "", "position", "", "amplifierPosition", "Lcom/hzbhd/canbus/activity/AmplifierActivity$AmplifierPosition;", "value", "", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public interface OnAmplifierPositionListener {
    void position(AmplifierActivity.AmplifierPosition amplifierPosition, int value);
}
