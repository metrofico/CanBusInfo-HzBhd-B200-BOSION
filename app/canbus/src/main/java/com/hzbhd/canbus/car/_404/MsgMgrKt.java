package com.hzbhd.canbus.car._404;

import com.hzbhd.canbus.CanbusMsgSender;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.text.StringsKt;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a0\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0003¨\u0006\n"}, d2 = {"sendTextInfo", "", "dataType", "", "text", "", "charsets", "Ljava/nio/charset/Charset;", "dataLength", "textLength", "CanBusInfo_release"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class MsgMgrKt {
    public static /* synthetic */ void sendTextInfo$default(int i, String str, Charset charset, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            str = "Unknown";
        }
        sendTextInfo(i, str, charset, i2, i3);
    }

    public static final void sendTextInfo(int i, String text, Charset charsets, int i2, int i3) {
        byte[] bytes;
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(charsets, "charsets");
        if (text.length() >= i3) {
            bytes = (StringsKt.substring(text, new IntRange(0, i3)) + "...").getBytes(charsets);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        } else {
            bytes = text.getBytes(charsets);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        }
        CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, (byte) i}, com.hzbhd.canbus.car._361.MsgMgrKt.restrict$default(bytes, i2, 0, 4, null)));
    }
}
