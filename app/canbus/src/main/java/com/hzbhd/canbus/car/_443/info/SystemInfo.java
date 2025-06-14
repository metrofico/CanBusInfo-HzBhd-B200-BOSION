package com.hzbhd.canbus.car._443.info;

import android.os.Build;
import android.os.SystemProperties;
import com.hzbhd.commontools.utils.ConfigUtil;
import java.util.Calendar;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: SystemInfo.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0007\b\u0016¢\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\tH\u0002J\u0006\u0010\u000e\u001a\u00020\u000bJ\u0006\u0010\u000f\u001a\u00020\u0006J\u0006\u0010\u0010\u001a\u00020\u0006J\u0006\u0010\u0011\u001a\u00020\u000bJ\u0006\u0010\u0012\u001a\u00020\u000bJ\u0006\u0010\u0013\u001a\u00020\u000bJ\u0006\u0010\u0014\u001a\u00020\u000bJ\u0006\u0010\u0015\u001a\u00020\u000bJ\u0006\u0010\u0016\u001a\u00020\u000bJ\u0006\u0010\u0017\u001a\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/hzbhd/canbus/car/_443/info/SystemInfo;", "", "()V", "calendar", "Ljava/util/Calendar;", "mDeviceInfo", "", "mcuInfo", "IntToByte", "", "data", "", "byteToInt", "byte", "getDayOfYear", "getDeviceInfo", "getMcuInfo", "getYear", "get_MMI_SWMainNo", "get_MMI_SWYear", "get_MMI_SubSwNo", "get_MMI_Swweek", "get_MMI_TruckSize", "get_MMI_TruckType", "Companion", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class SystemInfo {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<SystemInfo> instance$delegate = LazyKt.lazy(new Function0<SystemInfo>() { // from class: com.hzbhd.canbus.car._443.info.SystemInfo$Companion$instance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final SystemInfo invoke() {
            return new SystemInfo();
        }
    });
    private Calendar calendar;
    private String mDeviceInfo;
    private String mcuInfo;

    private final byte IntToByte(int data) {
        return (byte) data;
    }

    private final int byteToInt(byte b) {
        return b & 255;
    }

    public final int get_MMI_SWMainNo() {
        return 1;
    }

    public final int get_MMI_SubSwNo() {
        return 1;
    }

    /* compiled from: SystemInfo.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/hzbhd/canbus/car/_443/info/SystemInfo$Companion;", "", "()V", "instance", "Lcom/hzbhd/canbus/car/_443/info/SystemInfo;", "getInstance", "()Lcom/hzbhd/canbus/car/_443/info/SystemInfo;", "instance$delegate", "Lkotlin/Lazy;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SystemInfo getInstance() {
            return (SystemInfo) SystemInfo.instance$delegate.getValue();
        }
    }

    public SystemInfo() {
        String deviceId = ConfigUtil.getDeviceId();
        Intrinsics.checkNotNullExpressionValue(deviceId, "getDeviceId()");
        this.mDeviceInfo = StringsKt.trim((CharSequence) deviceId).toString();
        String str = SystemProperties.get("persist.bhd.mcu.version", "");
        Intrinsics.checkNotNullExpressionValue(str, "get(\"persist.bhd.mcu.version\",\"\")");
        this.mcuInfo = str;
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        this.calendar = calendar;
        if (calendar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("calendar");
            calendar = null;
        }
        calendar.setTimeInMillis(Build.TIME);
    }

    public final String getDeviceInfo() {
        String str = this.mDeviceInfo;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mDeviceInfo");
        return null;
    }

    public final String getMcuInfo() {
        String str = this.mcuInfo;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mcuInfo");
        return null;
    }

    public final int get_MMI_TruckSize() {
        Intrinsics.areEqual(getDeviceInfo(), "MS1G0102-PN9");
        return 2;
    }

    public final int get_MMI_TruckType() {
        Intrinsics.areEqual(getDeviceInfo(), "MS1G0102-PN9");
        return 1;
    }

    public final int get_MMI_SWYear() {
        Calendar calendar = this.calendar;
        if (calendar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("calendar");
            calendar = null;
        }
        return calendar.get(1) % 100;
    }

    public final int get_MMI_Swweek() {
        Calendar calendar = this.calendar;
        if (calendar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("calendar");
            calendar = null;
        }
        return calendar.get(3);
    }

    public final int getYear() {
        Calendar calendar = this.calendar;
        if (calendar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("calendar");
            calendar = null;
        }
        return calendar.get(1);
    }

    public final int getDayOfYear() {
        Calendar calendar = this.calendar;
        if (calendar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("calendar");
            calendar = null;
        }
        return calendar.get(6);
    }
}
