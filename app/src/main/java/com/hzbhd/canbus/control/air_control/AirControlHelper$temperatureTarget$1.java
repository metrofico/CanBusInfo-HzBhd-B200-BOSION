package com.hzbhd.canbus.control.air_control;

import android.os.Handler;
import android.util.Log;
import com.hzbhd.canbus.control.air_control.temperature.AirTemperatureControl;
import com.hzbhd.canbus.interfaces.AirControlInterface;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Regex;

/* compiled from: AirControlHelper.kt */
@Metadata(d1 = {"\u00001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J6\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\n2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"com/hzbhd/canbus/control/air_control/AirControlHelper$temperatureTarget$1", "Lcom/hzbhd/canbus/control/air_control/temperature/AirTemperatureControl;", "highRegex", "Lkotlin/text/Regex;", "lowRegex", "unitRegex", "step", "", "target", "type", "", "value", "targetAbsolute", "tag", "current", "Lkotlin/Function0;", "up", "Lcom/hzbhd/canbus/interfaces/AirControlInterface;", "down", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class AirControlHelper$temperatureTarget$1 extends AirTemperatureControl {
    final /* synthetic */ AirControlHelper this$0;
    private final Regex unitRegex = new Regex("[℃℉]");
    private final Regex lowRegex = new Regex("[Ll][Oo].*");
    private final Regex highRegex = new Regex("[Hh][Ii].*");

    @Override // com.hzbhd.canbus.control.air_control.temperature.AirTemperatureControl, com.hzbhd.canbus.control.air_control.AbstractAirControl, com.hzbhd.canbus.interfaces.AirControlInterface
    public void step() {
    }

    AirControlHelper$temperatureTarget$1(AirControlHelper airControlHelper) {
        this.this$0 = airControlHelper;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0120  */
    @Override // com.hzbhd.canbus.control.air_control.AbstractAirControl, com.hzbhd.canbus.interfaces.AirControlInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void target(java.lang.String r9, java.lang.String r10) {
        /*
            Method dump skipped, instructions count: 307
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.control.air_control.AirControlHelper$temperatureTarget$1.target(java.lang.String, java.lang.String):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: target$lambda-0, reason: not valid java name */
    public static final void m1146target$lambda0(AirControlHelper this$0, String tag, Ref.FloatRef times) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(tag, "$tag");
        Intrinsics.checkNotNullParameter(times, "$times");
        this$0.target(tag, 0.5f, times.element, this$0.getTemperatureUp(), this$0.getTemperatureDown());
    }

    private final void targetAbsolute(final String tag, final String target, final Function0<String> current, final AirControlInterface up, final AirControlInterface down) {
        float f;
        long j = 500;
        if (this.lowRegex.matches(current.invoke())) {
            this.this$0.getTemperatureUp().step();
            f = 0.5f;
        } else {
            if (this.highRegex.matches(current.invoke())) {
                this.this$0.getTemperatureDown().step();
                f = -0.5f;
            } else {
                j = 0;
                f = 0.0f;
            }
        }
        final float f2 = f;
        final long j2 = j;
        Handler handler = this.this$0.mHandler;
        final AirControlHelper airControlHelper = this.this$0;
        handler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.control.air_control.AirControlHelper$temperatureTarget$1$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() throws NumberFormatException {
                AirControlHelper$temperatureTarget$1.m1147targetAbsolute$lambda1(target, current, this, j2, f2, airControlHelper, tag, up, down);
            }
        }, j2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: targetAbsolute$lambda-1, reason: not valid java name */
    public static final void m1147targetAbsolute$lambda1(String target, Function0 current, AirControlHelper$temperatureTarget$1 this$0, long j, float f, AirControlHelper this$1, String tag, AirControlInterface up, AirControlInterface down) throws NumberFormatException {
        Intrinsics.checkNotNullParameter(target, "$target");
        Intrinsics.checkNotNullParameter(current, "$current");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        Intrinsics.checkNotNullParameter(tag, "$tag");
        Intrinsics.checkNotNullParameter(up, "$up");
        Intrinsics.checkNotNullParameter(down, "$down");
        float f2 = Float.parseFloat(target) - Float.parseFloat(this$0.unitRegex.replace((CharSequence) current.invoke(), ""));
        Log.i("SpeechDebug", "targetAbsolute: delay[" + j + "], offset[" + f + "], times[" + f2 + ']');
        this$1.target(tag, 0.5f, f2 - f, up, down);
    }
}
