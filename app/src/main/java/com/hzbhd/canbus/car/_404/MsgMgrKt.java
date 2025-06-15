package com.hzbhd.canbus.car._404;

import com.hzbhd.canbus.CanbusMsgSender;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.text.StringsKt;




public final class MsgMgrKt {
    public static /* synthetic */ void sendTextInfo$default(int i, String str, Charset charset, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            str = "Unknown";
        }
        sendTextInfo(i, str, charset, i2, i3);
    }

    public static final void sendTextInfo(int i, String text, Charset charsets, int i2, int i3) {
        byte[] bytes;


        if (text.length() >= i3) {
            bytes = (StringsKt.substring(text, new IntRange(0, i3)) + "...").getBytes(charsets);

        } else {
            bytes = text.getBytes(charsets);

        }
        CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, (byte) i}, com.hzbhd.canbus.car._361.MsgMgrKt.restrict$default(bytes, i2, 0, 4, null)));
    }
}
