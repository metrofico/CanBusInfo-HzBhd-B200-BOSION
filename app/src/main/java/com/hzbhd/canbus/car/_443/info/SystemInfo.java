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

        this.mDeviceInfo = StringsKt.trim((CharSequence) deviceId).toString();
        String str = SystemProperties.get("persist.bhd.mcu.version", "");

        this.mcuInfo = str;
        Calendar calendar = Calendar.getInstance();

        this.calendar = calendar;
        if (calendar == null) {

            calendar = null;
        }
        calendar.setTimeInMillis(Build.TIME);
    }

    public final String getDeviceInfo() {
        String str = this.mDeviceInfo;
        if (str != null) {
            return str;
        }

        return null;
    }

    public final String getMcuInfo() {
        String str = this.mcuInfo;
        if (str != null) {
            return str;
        }

        return null;
    }

    public final int get_MMI_TruckSize() {

        return 2;
    }

    public final int get_MMI_TruckType() {

        return 1;
    }

    public final int get_MMI_SWYear() {
        Calendar calendar = this.calendar;
        if (calendar == null) {

            calendar = null;
        }
        return calendar.get(1) % 100;
    }

    public final int get_MMI_Swweek() {
        Calendar calendar = this.calendar;
        if (calendar == null) {

            calendar = null;
        }
        return calendar.get(3);
    }

    public final int getYear() {
        Calendar calendar = this.calendar;
        if (calendar == null) {

            calendar = null;
        }
        return calendar.get(1);
    }

    public final int getDayOfYear() {
        Calendar calendar = this.calendar;
        if (calendar == null) {

            calendar = null;
        }
        return calendar.get(6);
    }
}
