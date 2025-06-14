package com.hzbhd.canbus.car._324;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UiMgr.kt */
@Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"com/hzbhd/canbus/car/_324/UiMgr$4$fastParams$1", "", "command", "", "getCommand", "()[B", "setCommand", "([B)V", "isFasting", "", "()Z", "setFasting", "(Z)V", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class UiMgr$4$fastParams$1 {
    private byte[] command = {22, -118, 0, 0};
    private boolean isFasting;

    UiMgr$4$fastParams$1() {
    }

    /* renamed from: isFasting, reason: from getter */
    public final boolean getIsFasting() {
        return this.isFasting;
    }

    public final void setFasting(boolean z) {
        this.isFasting = z;
    }

    public final byte[] getCommand() {
        return this.command;
    }

    public final void setCommand(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<set-?>");
        this.command = bArr;
    }
}
