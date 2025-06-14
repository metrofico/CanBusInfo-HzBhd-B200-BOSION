package com.hzbhd.canbus.interfaces;

import androidx.core.app.NotificationCompat;
import com.hzbhd.canbus.activity.AmplifierActivity;
import kotlin.Metadata;

/* compiled from: OnAmplifierSeekBarListener.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u0006H&¨\u0006\u0007"}, d2 = {"Lcom/hzbhd/canbus/interfaces/OnAmplifierSeekBarListener;", "", NotificationCompat.CATEGORY_PROGRESS, "", "amplifierBand", "Lcom/hzbhd/canbus/activity/AmplifierActivity$AmplifierBand;", "", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public interface OnAmplifierSeekBarListener {
    void progress(AmplifierActivity.AmplifierBand amplifierBand, int progress);
}
