package com.hzbhd.canbus.car._403;

import com.hzbhd.constant.share.lcd.LcdInfoShare;
import kotlin.Metadata;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a&\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0005¨\u0006\b"}, d2 = {"radioAscii", "", "index", "", "band", "", "size", LcdInfoShare.RadioInfo.freq, "CanBusInfo_release"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class MsgMgrKt {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0033, code lost:
    
        if (r9.equals("FM1") == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0037, code lost:
    
        r9 = kotlin.text.StringsKt.toDoubleOrNull(r11);
        kotlin.jvm.internal.Intrinsics.checkNotNull(r9);
        r9 = java.lang.String.valueOf(com.hzbhd.canbus.car._369.MsgMgrKt.accurateTo(r9.doubleValue(), 10)).getBytes(kotlin.text.Charsets.US_ASCII);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, "this as java.lang.String).getBytes(charset)");
        r11 = new byte[]{0, (byte) r8};
        r10 = (r10 - 2) - r9.length;
        r8 = new byte[r10];
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0061, code lost:
    
        if (r5 >= r10) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0063, code lost:
    
        r8[r5] = 32;
        r5 = r5 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0077, code lost:
    
        if (r9.equals("AM2") == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0080, code lost:
    
        if (r9.equals("AM1") == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0083, code lost:
    
        r9 = r11.getBytes(kotlin.text.Charsets.US_ASCII);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, "this as java.lang.String).getBytes(charset)");
        r11 = new byte[]{0, (byte) r8};
        r10 = (r10 - 2) - 5;
        r0 = new byte[r10];
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0098, code lost:
    
        if (r5 >= r10) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x009a, code lost:
    
        r0[r5] = 32;
        r5 = r5 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:?, code lost:
    
        return kotlin.collections.ArraysKt.plus(kotlin.collections.ArraysKt.plus(r11, r8), r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:?, code lost:
    
        return kotlin.collections.ArraysKt.plus(kotlin.collections.ArraysKt.plus(r11, r0), com.hzbhd.canbus.car._361.MsgMgrKt.restrict(r9, 5, 32));
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0020, code lost:
    
        if (r9.equals("FM3") != false) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0029, code lost:
    
        if (r9.equals("FM2") == false) goto L28;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final byte[] radioAscii(int r8, java.lang.String r9, int r10, java.lang.String r11) {
        /*
            java.lang.String r0 = "band"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            java.lang.String r0 = "freq"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
            int r0 = r9.hashCode()
            java.lang.String r1 = "this as java.lang.String).getBytes(charset)"
            r2 = 32
            r3 = 1
            r4 = 2
            r5 = 0
            switch(r0) {
                case 64901: goto L7a;
                case 64902: goto L71;
                case 69706: goto L2d;
                case 69707: goto L23;
                case 69708: goto L1a;
                default: goto L18;
            }
        L18:
            goto Lac
        L1a:
            java.lang.String r0 = "FM3"
            boolean r9 = r9.equals(r0)
            if (r9 == 0) goto Lac
            goto L37
        L23:
            java.lang.String r0 = "FM2"
            boolean r9 = r9.equals(r0)
            if (r9 != 0) goto L37
            goto Lac
        L2d:
            java.lang.String r0 = "FM1"
            boolean r9 = r9.equals(r0)
            if (r9 != 0) goto L37
            goto Lac
        L37:
            java.lang.Double r9 = kotlin.text.StringsKt.toDoubleOrNull(r11)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r9)
            double r6 = r9.doubleValue()
            r9 = 10
            double r6 = com.hzbhd.canbus.car._369.MsgMgrKt.accurateTo(r6, r9)
            java.lang.String r9 = java.lang.String.valueOf(r6)
            java.nio.charset.Charset r11 = kotlin.text.Charsets.US_ASCII
            byte[] r9 = r9.getBytes(r11)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r1)
            byte[] r11 = new byte[r4]
            r11[r5] = r5
            byte r8 = (byte) r8
            r11[r3] = r8
            int r10 = r10 - r4
            int r8 = r9.length
            int r10 = r10 - r8
            byte[] r8 = new byte[r10]
        L61:
            if (r5 >= r10) goto L68
            r8[r5] = r2
            int r5 = r5 + 1
            goto L61
        L68:
            byte[] r8 = kotlin.collections.ArraysKt.plus(r11, r8)
            byte[] r8 = kotlin.collections.ArraysKt.plus(r8, r9)
            goto Lae
        L71:
            java.lang.String r0 = "AM2"
            boolean r9 = r9.equals(r0)
            if (r9 != 0) goto L83
            goto Lac
        L7a:
            java.lang.String r0 = "AM1"
            boolean r9 = r9.equals(r0)
            if (r9 != 0) goto L83
            goto Lac
        L83:
            java.nio.charset.Charset r9 = kotlin.text.Charsets.US_ASCII
            byte[] r9 = r11.getBytes(r9)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r1)
            byte[] r11 = new byte[r4]
            r11[r5] = r5
            byte r8 = (byte) r8
            r11[r3] = r8
            int r10 = r10 - r4
            r8 = 5
            int r10 = r10 - r8
            byte[] r0 = new byte[r10]
        L98:
            if (r5 >= r10) goto L9f
            r0[r5] = r2
            int r5 = r5 + 1
            goto L98
        L9f:
            byte[] r10 = kotlin.collections.ArraysKt.plus(r11, r0)
            byte[] r8 = com.hzbhd.canbus.car._361.MsgMgrKt.restrict(r9, r8, r2)
            byte[] r8 = kotlin.collections.ArraysKt.plus(r10, r8)
            goto Lae
        Lac:
            byte[] r8 = new byte[r5]
        Lae:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._403.MsgMgrKt.radioAscii(int, java.lang.String, int, java.lang.String):byte[]");
    }
}
