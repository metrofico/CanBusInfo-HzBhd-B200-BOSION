package com.hzbhd.canbus.car._219;

import com.hzbhd.canbus.CanbusMsgSender;

import java.util.ArrayList;
import java.util.List;

import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.text.StringsKt;
import nfore.android.bt.res.NfDef;


public final class Handler {
    public static final Handler INSTANCE = new Handler();

    private Handler() {
    }

    private final void sendMediaSourceData(List<Byte> list) {
        CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, 9}, CollectionsKt.toByteArray(list)));
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final void sendRadioData(String band, String freq) {


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
        int iIndexOf$default = str.indexOf(".");
        ;
        if (iIndexOf$default != -1) {
            String strSubstring = freq.substring(0, iIndexOf$default);

            arrayList.add(Byte.valueOf(Byte.parseByte(strSubstring)));
            String strSubstring2 = freq.substring(iIndexOf$default + 1);

            arrayList.add(Byte.valueOf(Byte.parseByte(strSubstring2)));
        } else {
            String strSubstring3 = freq.substring(0, StringsKt.getLastIndex(str) - 1);

            arrayList.add(Byte.valueOf(Byte.parseByte(strSubstring3)));
            String strSubstring4 = freq.substring(StringsKt.getLastIndex(str) - 1);

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
