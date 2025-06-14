package com.hzbhd.config.use;

import com.android.internal.util.ArrayUtils;
import kotlin.Metadata;

/* compiled from: CanBusSpecialConfig.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/hzbhd/config/use/CanBusSpecialConfig;", "", "()V", "cameraHighConfigurationID", "", "cameraSupportHighConfigurationID", "", "can_type", "", "CanBus-config_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class CanBusSpecialConfig {
    public static final CanBusSpecialConfig INSTANCE = new CanBusSpecialConfig();
    private static final int[] cameraHighConfigurationID = {451, 452};

    private CanBusSpecialConfig() {
    }

    public final boolean cameraSupportHighConfigurationID(int can_type) {
        return ArrayUtils.contains(cameraHighConfigurationID, can_type);
    }
}
