package com.hzbhd.canbus.interfaces;

import com.hzbhd.canbus.activity.AmplifierActivity;
import kotlin.Metadata;

/* compiled from: OnAmplifierSeekPlusMinListener.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0007"}, d2 = {"Lcom/hzbhd/canbus/interfaces/OnAmplifierSeekPlusMinListener;", "", "min", "", "amplifierBand", "Lcom/hzbhd/canbus/activity/AmplifierActivity$AmplifierBand;", "plus", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public interface OnAmplifierSeekPlusMinListener {
    void min(AmplifierActivity.AmplifierBand amplifierBand);

    void plus(AmplifierActivity.AmplifierBand amplifierBand);
}
