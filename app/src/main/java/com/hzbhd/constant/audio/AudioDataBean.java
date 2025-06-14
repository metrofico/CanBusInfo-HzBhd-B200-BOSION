package com.hzbhd.constant.audio;

import com.hzbhd.constant.audio.AudioConstants;
import com.hzbhd.constant.share.lcd.LcdInfoShare;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AudioDataBean.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u000b\u0018\u00002\u00020\u0001:\t\u0003\u0004\u0005\u0006\u0007\b\t\n\u000bB\u0005¢\u0006\u0002\u0010\u0002¨\u0006\f"}, d2 = {"Lcom/hzbhd/constant/audio/AudioDataBean;", "", "()V", "DelayTime", "Equalizer", "FaderBalance", "Hpf", "Loudness", "Lpf", "SourceEq", "Sub", "UserEq", "audio-constant"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class AudioDataBean {

    /* compiled from: AudioDataBean.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BM\u0012\u0016\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005\u0012\u0016\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005\u0012\u0016\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005¢\u0006\u0002\u0010\bJ\u0019\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005HÆ\u0003J\u0019\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005HÆ\u0003J\u0019\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005HÆ\u0003JW\u0010\u0010\u001a\u00020\u00002\u0018\b\u0002\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u00052\u0018\b\u0002\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u00052\u0018\b\u0002\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R!\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR!\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR!\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\n¨\u0006\u0018"}, d2 = {"Lcom/hzbhd/constant/audio/AudioDataBean$Equalizer;", "", "q", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", LcdInfoShare.RadioInfo.freq, "gain", "(Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;)V", "getFreq", "()Ljava/util/ArrayList;", "getGain", "getQ", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "audio-constant"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final /* data */ class Equalizer {
        private final ArrayList<Double> freq;
        private final ArrayList<Double> gain;
        private final ArrayList<Double> q;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Equalizer copy$default(Equalizer equalizer, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, int i, Object obj) {
            if ((i & 1) != 0) {
                arrayList = equalizer.q;
            }
            if ((i & 2) != 0) {
                arrayList2 = equalizer.freq;
            }
            if ((i & 4) != 0) {
                arrayList3 = equalizer.gain;
            }
            return equalizer.copy(arrayList, arrayList2, arrayList3);
        }

        public final ArrayList<Double> component1() {
            return this.q;
        }

        public final ArrayList<Double> component2() {
            return this.freq;
        }

        public final ArrayList<Double> component3() {
            return this.gain;
        }

        public final Equalizer copy(ArrayList<Double> q, ArrayList<Double> freq, ArrayList<Double> gain) {
            Intrinsics.checkNotNullParameter(q, "q");
            Intrinsics.checkNotNullParameter(freq, "freq");
            Intrinsics.checkNotNullParameter(gain, "gain");
            return new Equalizer(q, freq, gain);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Equalizer)) {
                return false;
            }
            Equalizer equalizer = (Equalizer) other;
            return Intrinsics.areEqual(this.q, equalizer.q) && Intrinsics.areEqual(this.freq, equalizer.freq) && Intrinsics.areEqual(this.gain, equalizer.gain);
        }

        public int hashCode() {
            return (((this.q.hashCode() * 31) + this.freq.hashCode()) * 31) + this.gain.hashCode();
        }

        public String toString() {
            return "Equalizer(q=" + this.q + ", freq=" + this.freq + ", gain=" + this.gain + ')';
        }

        public Equalizer(ArrayList<Double> q, ArrayList<Double> freq, ArrayList<Double> gain) {
            Intrinsics.checkNotNullParameter(q, "q");
            Intrinsics.checkNotNullParameter(freq, "freq");
            Intrinsics.checkNotNullParameter(gain, "gain");
            this.q = q;
            this.freq = freq;
            this.gain = gain;
        }

        public final ArrayList<Double> getFreq() {
            return this.freq;
        }

        public final ArrayList<Double> getGain() {
            return this.gain;
        }

        public final ArrayList<Double> getQ() {
            return this.q;
        }
    }

    /* compiled from: AudioDataBean.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\"\u0010\u0002\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005`\u0006¢\u0006\u0002\u0010\u0007J%\u0010\n\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005`\u0006HÆ\u0003J/\u0010\u000b\u001a\u00020\u00002$\b\u0002\u0010\u0002\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005`\u0006HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R-\u0010\u0002\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0012"}, d2 = {"Lcom/hzbhd/constant/audio/AudioDataBean$FaderBalance;", "", "data", "Ljava/util/HashMap;", "Lcom/hzbhd/constant/audio/AudioConstants$HORN_TYPE;", "", "Lkotlin/collections/HashMap;", "(Ljava/util/HashMap;)V", "getData", "()Ljava/util/HashMap;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "audio-constant"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final /* data */ class FaderBalance {
        private final HashMap<AudioConstants.HORN_TYPE, Integer> data;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ FaderBalance copy$default(FaderBalance faderBalance, HashMap map, int i, Object obj) {
            if ((i & 1) != 0) {
                map = faderBalance.data;
            }
            return faderBalance.copy(map);
        }

        public final HashMap<AudioConstants.HORN_TYPE, Integer> component1() {
            return this.data;
        }

        public final FaderBalance copy(HashMap<AudioConstants.HORN_TYPE, Integer> data) {
            Intrinsics.checkNotNullParameter(data, "data");
            return new FaderBalance(data);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof FaderBalance) && Intrinsics.areEqual(this.data, ((FaderBalance) other).data);
        }

        public int hashCode() {
            return this.data.hashCode();
        }

        public String toString() {
            return "FaderBalance(data=" + this.data + ')';
        }

        public FaderBalance(HashMap<AudioConstants.HORN_TYPE, Integer> data) {
            Intrinsics.checkNotNullParameter(data, "data");
            this.data = data;
        }

        public final HashMap<AudioConstants.HORN_TYPE, Integer> getData() {
            return this.data;
        }
    }

    /* compiled from: AudioDataBean.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\"\u0010\u0002\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005`\u0006¢\u0006\u0002\u0010\u0007J%\u0010\n\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005`\u0006HÆ\u0003J/\u0010\u000b\u001a\u00020\u00002$\b\u0002\u0010\u0002\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005`\u0006HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R-\u0010\u0002\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0013"}, d2 = {"Lcom/hzbhd/constant/audio/AudioDataBean$DelayTime;", "", "data", "Ljava/util/HashMap;", "Lcom/hzbhd/constant/audio/AudioConstants$HORN_TYPE;", "", "Lkotlin/collections/HashMap;", "(Ljava/util/HashMap;)V", "getData", "()Ljava/util/HashMap;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "audio-constant"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final /* data */ class DelayTime {
        private final HashMap<AudioConstants.HORN_TYPE, Double> data;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ DelayTime copy$default(DelayTime delayTime, HashMap map, int i, Object obj) {
            if ((i & 1) != 0) {
                map = delayTime.data;
            }
            return delayTime.copy(map);
        }

        public final HashMap<AudioConstants.HORN_TYPE, Double> component1() {
            return this.data;
        }

        public final DelayTime copy(HashMap<AudioConstants.HORN_TYPE, Double> data) {
            Intrinsics.checkNotNullParameter(data, "data");
            return new DelayTime(data);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof DelayTime) && Intrinsics.areEqual(this.data, ((DelayTime) other).data);
        }

        public int hashCode() {
            return this.data.hashCode();
        }

        public String toString() {
            return "DelayTime(data=" + this.data + ')';
        }

        public DelayTime(HashMap<AudioConstants.HORN_TYPE, Double> data) {
            Intrinsics.checkNotNullParameter(data, "data");
            this.data = data;
        }

        public final HashMap<AudioConstants.HORN_TYPE, Double> getData() {
            return this.data;
        }
    }

    /* compiled from: AudioDataBean.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0017\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0002\u0010\bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J1\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u00032\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u000e\"\u0004\b\u0012\u0010\u0010R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000e\"\u0004\b\u0014\u0010\u0010¨\u0006 "}, d2 = {"Lcom/hzbhd/constant/audio/AudioDataBean$Sub;", "", "enable", "", "q", "", LcdInfoShare.RadioInfo.freq, "gain", "(ZDDD)V", "getEnable", "()Z", "setEnable", "(Z)V", "getFreq", "()D", "setFreq", "(D)V", "getGain", "setGain", "getQ", "setQ", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "", "audio-constant"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final /* data */ class Sub {
        private boolean enable;
        private double freq;
        private double gain;
        private double q;

        public static /* synthetic */ Sub copy$default(Sub sub, boolean z, double d, double d2, double d3, int i, Object obj) {
            if ((i & 1) != 0) {
                z = sub.enable;
            }
            if ((i & 2) != 0) {
                d = sub.q;
            }
            double d4 = d;
            if ((i & 4) != 0) {
                d2 = sub.freq;
            }
            double d5 = d2;
            if ((i & 8) != 0) {
                d3 = sub.gain;
            }
            return sub.copy(z, d4, d5, d3);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getEnable() {
            return this.enable;
        }

        /* renamed from: component2, reason: from getter */
        public final double getQ() {
            return this.q;
        }

        /* renamed from: component3, reason: from getter */
        public final double getFreq() {
            return this.freq;
        }

        /* renamed from: component4, reason: from getter */
        public final double getGain() {
            return this.gain;
        }

        public final Sub copy(boolean enable, double q, double freq, double gain) {
            return new Sub(enable, q, freq, gain);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Sub)) {
                return false;
            }
            Sub sub = (Sub) other;
            return this.enable == sub.enable && Intrinsics.areEqual((Object) Double.valueOf(this.q), (Object) Double.valueOf(sub.q)) && Intrinsics.areEqual((Object) Double.valueOf(this.freq), (Object) Double.valueOf(sub.freq)) && Intrinsics.areEqual((Object) Double.valueOf(this.gain), (Object) Double.valueOf(sub.gain));
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v8 */
        /* JADX WARN: Type inference failed for: r0v9 */
        public int hashCode() {
            boolean z = this.enable;
            ?? r0 = z;
            if (z) {
                r0 = 1;
            }
            return (((((r0 * 31) + Double.hashCode(this.q)) * 31) + Double.hashCode(this.freq)) * 31) + Double.hashCode(this.gain);
        }

        public String toString() {
            return "Sub(enable=" + this.enable + ", q=" + this.q + ", freq=" + this.freq + ", gain=" + this.gain + ')';
        }

        public Sub(boolean z, double d, double d2, double d3) {
            this.enable = z;
            this.q = d;
            this.freq = d2;
            this.gain = d3;
        }

        public final boolean getEnable() {
            return this.enable;
        }

        public final double getFreq() {
            return this.freq;
        }

        public final double getGain() {
            return this.gain;
        }

        public final double getQ() {
            return this.q;
        }

        public final void setEnable(boolean z) {
            this.enable = z;
        }

        public final void setFreq(double d) {
            this.freq = d;
        }

        public final void setGain(double d) {
            this.gain = d;
        }

        public final void setQ(double d) {
            this.q = d;
        }
    }

    /* compiled from: AudioDataBean.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0013\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J'\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00032\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000b¨\u0006\u001c"}, d2 = {"Lcom/hzbhd/constant/audio/AudioDataBean$Loudness;", "", "enable", "", "gain", "", "highBoost", "(ZDZ)V", "getEnable", "()Z", "setEnable", "(Z)V", "getGain", "()D", "setGain", "(D)V", "getHighBoost", "setHighBoost", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "", "audio-constant"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final /* data */ class Loudness {
        private boolean enable;
        private double gain;
        private boolean highBoost;

        public static /* synthetic */ Loudness copy$default(Loudness loudness, boolean z, double d, boolean z2, int i, Object obj) {
            if ((i & 1) != 0) {
                z = loudness.enable;
            }
            if ((i & 2) != 0) {
                d = loudness.gain;
            }
            if ((i & 4) != 0) {
                z2 = loudness.highBoost;
            }
            return loudness.copy(z, d, z2);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getEnable() {
            return this.enable;
        }

        /* renamed from: component2, reason: from getter */
        public final double getGain() {
            return this.gain;
        }

        /* renamed from: component3, reason: from getter */
        public final boolean getHighBoost() {
            return this.highBoost;
        }

        public final Loudness copy(boolean enable, double gain, boolean highBoost) {
            return new Loudness(enable, gain, highBoost);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Loudness)) {
                return false;
            }
            Loudness loudness = (Loudness) other;
            return this.enable == loudness.enable && Intrinsics.areEqual((Object) Double.valueOf(this.gain), (Object) Double.valueOf(loudness.gain)) && this.highBoost == loudness.highBoost;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v6 */
        /* JADX WARN: Type inference failed for: r0v7 */
        public int hashCode() {
            boolean z = this.enable;
            ?? r0 = z;
            if (z) {
                r0 = 1;
            }
            int iHashCode = ((r0 * 31) + Double.hashCode(this.gain)) * 31;
            boolean z2 = this.highBoost;
            return iHashCode + (z2 ? 1 : z2 ? 1 : 0);
        }

        public String toString() {
            return "Loudness(enable=" + this.enable + ", gain=" + this.gain + ", highBoost=" + this.highBoost + ')';
        }

        public Loudness(boolean z, double d, boolean z2) {
            this.enable = z;
            this.gain = d;
            this.highBoost = z2;
        }

        public final boolean getEnable() {
            return this.enable;
        }

        public final double getGain() {
            return this.gain;
        }

        public final boolean getHighBoost() {
            return this.highBoost;
        }

        public final void setEnable(boolean z) {
            this.enable = z;
        }

        public final void setGain(double d) {
            this.gain = d;
        }

        public final void setHighBoost(boolean z) {
            this.highBoost = z;
        }
    }

    /* compiled from: AudioDataBean.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\"\u0010\u0002\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005`\u0006¢\u0006\u0002\u0010\u0007J%\u0010\n\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005`\u0006HÆ\u0003J/\u0010\u000b\u001a\u00020\u00002$\b\u0002\u0010\u0002\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005`\u0006HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0004HÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R-\u0010\u0002\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0012"}, d2 = {"Lcom/hzbhd/constant/audio/AudioDataBean$SourceEq;", "", "data", "Ljava/util/HashMap;", "", "Lcom/hzbhd/constant/audio/AudioDataBean$Equalizer;", "Lkotlin/collections/HashMap;", "(Ljava/util/HashMap;)V", "getData", "()Ljava/util/HashMap;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "audio-constant"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final /* data */ class SourceEq {
        private final HashMap<Integer, Equalizer> data;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ SourceEq copy$default(SourceEq sourceEq, HashMap map, int i, Object obj) {
            if ((i & 1) != 0) {
                map = sourceEq.data;
            }
            return sourceEq.copy(map);
        }

        public final HashMap<Integer, Equalizer> component1() {
            return this.data;
        }

        public final SourceEq copy(HashMap<Integer, Equalizer> data) {
            Intrinsics.checkNotNullParameter(data, "data");
            return new SourceEq(data);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof SourceEq) && Intrinsics.areEqual(this.data, ((SourceEq) other).data);
        }

        public int hashCode() {
            return this.data.hashCode();
        }

        public String toString() {
            return "SourceEq(data=" + this.data + ')';
        }

        public SourceEq(HashMap<Integer, Equalizer> data) {
            Intrinsics.checkNotNullParameter(data, "data");
            this.data = data;
        }

        public final HashMap<Integer, Equalizer> getData() {
            return this.data;
        }
    }

    /* compiled from: AudioDataBean.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\"\u0010\u0002\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005`\u0006¢\u0006\u0002\u0010\u0007J%\u0010\n\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005`\u0006HÆ\u0003J/\u0010\u000b\u001a\u00020\u00002$\b\u0002\u0010\u0002\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005`\u0006HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0004HÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R-\u0010\u0002\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0012"}, d2 = {"Lcom/hzbhd/constant/audio/AudioDataBean$UserEq;", "", "data", "Ljava/util/HashMap;", "", "Lcom/hzbhd/constant/audio/AudioDataBean$Equalizer;", "Lkotlin/collections/HashMap;", "(Ljava/util/HashMap;)V", "getData", "()Ljava/util/HashMap;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "audio-constant"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final /* data */ class UserEq {
        private final HashMap<Integer, Equalizer> data;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ UserEq copy$default(UserEq userEq, HashMap map, int i, Object obj) {
            if ((i & 1) != 0) {
                map = userEq.data;
            }
            return userEq.copy(map);
        }

        public final HashMap<Integer, Equalizer> component1() {
            return this.data;
        }

        public final UserEq copy(HashMap<Integer, Equalizer> data) {
            Intrinsics.checkNotNullParameter(data, "data");
            return new UserEq(data);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof UserEq) && Intrinsics.areEqual(this.data, ((UserEq) other).data);
        }

        public int hashCode() {
            return this.data.hashCode();
        }

        public String toString() {
            return "UserEq(data=" + this.data + ')';
        }

        public UserEq(HashMap<Integer, Equalizer> data) {
            Intrinsics.checkNotNullParameter(data, "data");
            this.data = data;
        }

        public final HashMap<Integer, Equalizer> getData() {
            return this.data;
        }
    }

    /* compiled from: AudioDataBean.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/hzbhd/constant/audio/AudioDataBean$Hpf;", "", LcdInfoShare.RadioInfo.freq, "", "q", "slope", "", "(DDI)V", "getFreq", "()D", "getQ", "getSlope", "()I", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "audio-constant"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final /* data */ class Hpf {
        private final double freq;
        private final double q;
        private final int slope;

        public static /* synthetic */ Hpf copy$default(Hpf hpf, double d, double d2, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                d = hpf.freq;
            }
            double d3 = d;
            if ((i2 & 2) != 0) {
                d2 = hpf.q;
            }
            double d4 = d2;
            if ((i2 & 4) != 0) {
                i = hpf.slope;
            }
            return hpf.copy(d3, d4, i);
        }

        /* renamed from: component1, reason: from getter */
        public final double getFreq() {
            return this.freq;
        }

        /* renamed from: component2, reason: from getter */
        public final double getQ() {
            return this.q;
        }

        /* renamed from: component3, reason: from getter */
        public final int getSlope() {
            return this.slope;
        }

        public final Hpf copy(double freq, double q, int slope) {
            return new Hpf(freq, q, slope);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Hpf)) {
                return false;
            }
            Hpf hpf = (Hpf) other;
            return Intrinsics.areEqual((Object) Double.valueOf(this.freq), (Object) Double.valueOf(hpf.freq)) && Intrinsics.areEqual((Object) Double.valueOf(this.q), (Object) Double.valueOf(hpf.q)) && this.slope == hpf.slope;
        }

        public int hashCode() {
            return (((Double.hashCode(this.freq) * 31) + Double.hashCode(this.q)) * 31) + Integer.hashCode(this.slope);
        }

        public String toString() {
            return "Hpf(freq=" + this.freq + ", q=" + this.q + ", slope=" + this.slope + ')';
        }

        public Hpf(double d, double d2, int i) {
            this.freq = d;
            this.q = d2;
            this.slope = i;
        }

        public final double getFreq() {
            return this.freq;
        }

        public final double getQ() {
            return this.q;
        }

        public final int getSlope() {
            return this.slope;
        }
    }

    /* compiled from: AudioDataBean.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/hzbhd/constant/audio/AudioDataBean$Lpf;", "", LcdInfoShare.RadioInfo.freq, "", "q", "slope", "", "(DDI)V", "getFreq", "()D", "getQ", "getSlope", "()I", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "audio-constant"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final /* data */ class Lpf {
        private final double freq;
        private final double q;
        private final int slope;

        public static /* synthetic */ Lpf copy$default(Lpf lpf, double d, double d2, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                d = lpf.freq;
            }
            double d3 = d;
            if ((i2 & 2) != 0) {
                d2 = lpf.q;
            }
            double d4 = d2;
            if ((i2 & 4) != 0) {
                i = lpf.slope;
            }
            return lpf.copy(d3, d4, i);
        }

        /* renamed from: component1, reason: from getter */
        public final double getFreq() {
            return this.freq;
        }

        /* renamed from: component2, reason: from getter */
        public final double getQ() {
            return this.q;
        }

        /* renamed from: component3, reason: from getter */
        public final int getSlope() {
            return this.slope;
        }

        public final Lpf copy(double freq, double q, int slope) {
            return new Lpf(freq, q, slope);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Lpf)) {
                return false;
            }
            Lpf lpf = (Lpf) other;
            return Intrinsics.areEqual((Object) Double.valueOf(this.freq), (Object) Double.valueOf(lpf.freq)) && Intrinsics.areEqual((Object) Double.valueOf(this.q), (Object) Double.valueOf(lpf.q)) && this.slope == lpf.slope;
        }

        public int hashCode() {
            return (((Double.hashCode(this.freq) * 31) + Double.hashCode(this.q)) * 31) + Integer.hashCode(this.slope);
        }

        public String toString() {
            return "Lpf(freq=" + this.freq + ", q=" + this.q + ", slope=" + this.slope + ')';
        }

        public Lpf(double d, double d2, int i) {
            this.freq = d;
            this.q = d2;
            this.slope = i;
        }

        public final double getFreq() {
            return this.freq;
        }

        public final double getQ() {
            return this.q;
        }

        public final int getSlope() {
            return this.slope;
        }
    }
}
