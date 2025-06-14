package com.hzbhd.config.use;

import com.hzbhd.constant.disc.MpegConstantsDef;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;

/* compiled from: UIEncrypt.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004J\u0016\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004J\u000e\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0004¨\u0006\u000e"}, d2 = {"Lcom/hzbhd/config/use/UIEncrypt;", "", "()V", "changeByteArrayToUI", "", "byteArray", "", "changeStringToUI", "str", "changeUIToByteArray", "ui1", "ui2", "changeUIToString", "ui", "UI-config_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class UIEncrypt {
    public static final UIEncrypt INSTANCE = new UIEncrypt();

    private UIEncrypt() {
    }

    public final String changeUIToString(String ui) {
        Intrinsics.checkNotNullParameter(ui, "ui");
        String hexString = Integer.toHexString((~Integer.parseInt(ui, 36)) & MpegConstantsDef.MPEG_MSG_NULL);
        Intrinsics.checkNotNullExpressionValue(hexString, "toHexString(Integer.pars…(ui, 36).inv() and 0xfff)");
        return hexString;
    }

    public final String changeStringToUI(String str) {
        Intrinsics.checkNotNullParameter(str, "str");
        String string = Integer.toString(~(Integer.parseInt(str, 16) | (-4096)), CharsKt.checkRadix(36));
        Intrinsics.checkNotNullExpressionValue(string, "toString(this, checkRadix(radix))");
        return string.length() < 2 ? '0' + string : string;
    }

    public final byte[] changeUIToByteArray(String ui1, String ui2) throws NumberFormatException {
        Intrinsics.checkNotNullParameter(ui1, "ui1");
        Intrinsics.checkNotNullParameter(ui2, "ui2");
        int i = Integer.parseInt(ui1 + ui2, 36);
        System.out.println((Object) ("changeUIToByteArray  " + ui1 + ui2 + ':' + Integer.toHexString(i)));
        return new byte[]{(byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)};
    }

    public final String changeByteArrayToUI(byte[] byteArray) {
        Intrinsics.checkNotNullParameter(byteArray, "byteArray");
        int length = byteArray.length;
        int length2 = 0;
        for (int i = 0; i < length; i++) {
            length2 += (byteArray[i] & 255) << (((byteArray.length - 1) - i) * 8);
        }
        StringBuilder sbAppend = new StringBuilder().append("changeByteArrayToUI:").append(Integer.toHexString(length2)).append("    ");
        String string = Integer.toString(length2, CharsKt.checkRadix(36));
        Intrinsics.checkNotNullExpressionValue(string, "toString(this, checkRadix(radix))");
        System.out.println((Object) sbAppend.append(string).toString());
        StringBuilder sbAppend2 = new StringBuilder().append("0000");
        String string2 = Integer.toString(length2, CharsKt.checkRadix(36));
        Intrinsics.checkNotNullExpressionValue(string2, "toString(this, checkRadix(radix))");
        String string3 = sbAppend2.append(string2).toString();
        String strSubstring = string3.substring(string3.length() - 4, string3.length());
        Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }
}
