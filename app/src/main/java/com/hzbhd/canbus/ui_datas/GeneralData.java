package com.hzbhd.canbus.ui_datas;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GeneralData.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/hzbhd/canbus/ui_datas/GeneralData;", "", "()V", "canVersion", "", "getCanVersion", "()Ljava/lang/String;", "setCanVersion", "(Ljava/lang/String;)V", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class GeneralData {
    public static final GeneralData INSTANCE = new GeneralData();
    private static String canVersion = "";

    private GeneralData() {
    }

    public final String getCanVersion() {
        return canVersion;
    }

    public final void setCanVersion(String str) {

        canVersion = str;
    }
}
