package com.hzbhd.canbus.car._219;

import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.constant.share.lcd.LcdInfoShare;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import nfore.android.bt.res.NfDef;

/* compiled from: Handler.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0002J.\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\nJ\u0016\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011¨\u0006\u0013"}, d2 = {"Lcom/hzbhd/canbus/car/_219/Handler;", "", "()V", "sendMediaSourceData", "", "list", "", "", "sendMusicOrVideoData", "storagePath", "", "totalNumber", "currentNumber", "min", "sec", "sendRadioData", "band", "", LcdInfoShare.RadioInfo.freq, "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class Handler {
    public static final Handler INSTANCE = new Handler();

    private Handler() {
    }

    private final void sendMediaSourceData(List<Byte> list) {
        CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, 9}, CollectionsKt.toByteArray(list)));
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final void sendRadioData(String band, String freq) {
        Intrinsics.checkNotNullParameter(band, "band");
        Intrinsics.checkNotNullParameter(freq, "freq");
        ArrayList arrayList = new ArrayList();
        byte b = 2;
        arrayList.add((byte) 2);
        int iHashCode = band.hashCode();
        if (iHashCode != 2092) {
            if (iHashCode != 2443) {
                if (iHashCode != 2763) {
                    if (iHashCode != 64901) {
                        switch (iHashCode) {
                            case 69706:
                                if (band.equals("FM1")) {
                                    b = 0;
                                    break;
                                } else {
                                    return;
                                }
                            case 69707:
                                if (band.equals("FM2")) {
                                    b = 1;
                                    break;
                                } else {
                                    return;
                                }
                            case 69708:
                                if (!band.equals("FM3")) {
                                    return;
                                }
                                break;
                            default:
                                return;
                        }
                    } else if (!band.equals("AM1")) {
                        return;
                    } else {
                        b = 4;
                    }
                } else if (!band.equals("WB")) {
                    return;
                } else {
                    b = 6;
                }
            } else if (!band.equals("LW")) {
                return;
            } else {
                b = 5;
            }
        } else if (!band.equals("AM")) {
            return;
        } else {
            b = 3;
        }
        arrayList.add(Byte.valueOf(b));
        String str = freq;
        int iIndexOf$default = StringsKt.indexOf$default((CharSequence) str, ".", 0, false, 6, (Object) null);
        if (iIndexOf$default != -1) {
            String strSubstring = freq.substring(0, iIndexOf$default);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
            arrayList.add(Byte.valueOf(Byte.parseByte(strSubstring)));
            String strSubstring2 = freq.substring(iIndexOf$default + 1);
            Intrinsics.checkNotNullExpressionValue(strSubstring2, "this as java.lang.String).substring(startIndex)");
            arrayList.add(Byte.valueOf(Byte.parseByte(strSubstring2)));
        } else {
            String strSubstring3 = freq.substring(0, StringsKt.getLastIndex(str) - 1);
            Intrinsics.checkNotNullExpressionValue(strSubstring3, "this as java.lang.String…ing(startIndex, endIndex)");
            arrayList.add(Byte.valueOf(Byte.parseByte(strSubstring3)));
            String strSubstring4 = freq.substring(StringsKt.getLastIndex(str) - 1);
            Intrinsics.checkNotNullExpressionValue(strSubstring4, "this as java.lang.String).substring(startIndex)");
            arrayList.add(Byte.valueOf(Byte.parseByte(strSubstring4)));
        }
        sendMediaSourceData(arrayList);
    }

    public final void sendMusicOrVideoData(int storagePath, int totalNumber, int currentNumber, int min, int sec) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Byte.valueOf(storagePath == 9 ? NfDef.AVRCP_EVENT_ID_UIDS_CHANGED : NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED));
        arrayList.add(Byte.valueOf((byte) totalNumber));
        arrayList.add(Byte.valueOf((byte) currentNumber));
        arrayList.add(Byte.valueOf((byte) min));
        arrayList.add(Byte.valueOf((byte) sec));
        sendMediaSourceData(arrayList);
    }
}
