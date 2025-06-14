package com.hzbhd.canbus.util;

import android.app.ActivityManager;
import android.content.Context;
import android.text.TextUtils;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import com.hzbhd.canbus.car._464.MsgMgr;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* loaded from: classes2.dex */
public class DataHandleUtils {
    public static boolean getBoolBit0(int i) {
        return (i & 1) != 0;
    }

    public static boolean getBoolBit1(int i) {
        return (i & 2) != 0;
    }

    public static boolean getBoolBit2(int i) {
        return (i & 4) != 0;
    }

    public static boolean getBoolBit3(int i) {
        return (i & 8) != 0;
    }

    public static boolean getBoolBit4(int i) {
        return (i & 16) != 0;
    }

    public static boolean getBoolBit5(int i) {
        return (i & 32) != 0;
    }

    public static boolean getBoolBit6(int i) {
        return (i & 64) != 0;
    }

    public static boolean getBoolBit7(int i) {
        return (i & 128) != 0;
    }

    public static boolean getIntByteWithBit(int i, int i2) {
        return i2 == 0 ? (i & 1) != 0 : 1 == i2 ? (i & 2) != 0 : 2 == i2 ? (i & 4) != 0 : 3 == i2 ? (i & 8) != 0 : 4 == i2 ? (i & 16) != 0 : 5 == i2 ? (i & 32) != 0 : 6 == i2 ? (i & 64) != 0 : 7 == i2 && (i & 128) != 0;
    }

    public static int getIntFromByteWithBit(int i, int i2, int i3) {
        return ((i & 255) >> i2) & ((1 << i3) - 1);
    }

    public static int getLsb(int i) {
        return ((i & 65535) >> 0) & 255;
    }

    public static int getMsb(int i) {
        return ((i & 65535) >> 8) & 255;
    }

    public static int getMsbLsbResult(int i, int i2) {
        return ((i & 255) << 8) | (i2 & 255);
    }

    public static int getMsbLsbResult_4bit(int i, int i2) {
        return ((i & 255) << 4) | (i2 & 255);
    }

    public static int rangeNumber(int i, int i2, int i3) {
        if (i > i3) {
            i = i3;
        }
        return i < i2 ? i2 : i;
    }

    public static int setIntByteWithBit(int i, int i2, boolean z) {
        return z ? i2 == 0 ? i | 1 : 1 == i2 ? i | 2 : 2 == i2 ? i | 4 : 3 == i2 ? i | 8 : 4 == i2 ? i | 16 : 5 == i2 ? i | 32 : 6 == i2 ? i | 64 : 7 == i2 ? i | 128 : i : i2 == 0 ? i & MsgMgr.DVD_MODE : 1 == i2 ? i & 253 : 2 == i2 ? i & MsgMgr.RADIO_MODE : 3 == i2 ? i & 247 : 4 == i2 ? i & 239 : 5 == i2 ? i & HotKeyConstant.K_DARK_MODE : 6 == i2 ? i & 191 : 7 == i2 ? i & WorkQueueKt.MASK : i;
    }

    public static int setIntFromByteWithBit(int i, int i2, int i3, int i4) {
        return (i & (~(((1 << i4) - 1) << i3))) | (i2 << i3);
    }

    public static int setOneBit(int i, int i2, int i3) {
        return i ^ ((i3 ^ ((i >> i2) & 1)) << i2);
    }

    public static String toTimeFormat(int i, int i2, int i3, boolean z) {
        StringBuilder sb = new StringBuilder();
        if (z) {
            if (i != 0) {
                if (i < 10) {
                    sb.append("0");
                }
                sb.append(i);
                sb.append(":");
            } else {
                sb.append("00:");
            }
        }
        if (i2 != 0) {
            if (i2 < 10) {
                sb.append("0");
            }
            sb.append(i2);
            sb.append(":");
        } else {
            sb.append("00:");
        }
        if (i3 != 0) {
            if (i3 < 10) {
                sb.append("0");
            }
            sb.append(i3);
        } else {
            sb.append("00");
        }
        return sb.toString();
    }

    public static String Byte2Unicode(byte[] bArr, boolean z) {
        int i;
        byte b;
        byte b2;
        StringBuffer stringBuffer = new StringBuffer("");
        int i2 = 0;
        while (i2 < bArr.length) {
            if (z) {
                int i3 = i2 + 1;
                b2 = bArr[i2];
                i = i3 + 1;
                b = bArr[i3];
            } else {
                int i4 = i2 + 1;
                byte b3 = bArr[i2];
                i = i4 + 1;
                byte b4 = bArr[i4];
                b = b3;
                b2 = b4;
            }
            stringBuffer.append((char) ((b & 255) + ((b2 << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK)));
            i2 = i;
        }
        return stringBuffer.toString();
    }

    public static String bcd2Str(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i];
            char c = (char) (((b & 240) >> 4) & 15);
            if (c >= 0 && c <= '\t') {
                cArr[i * 2] = (char) (c + '0');
            } else if (c == '\n') {
                cArr[i * 2] = '*';
            } else {
                if (c != 11) {
                    if (c != 15) {
                        break;
                        break;
                    }
                    break;
                }
                cArr[i * 2] = '#';
            }
            char c2 = (char) (b & 15);
            if (c2 < 0 || c2 > '\t') {
                if (c2 != '\n') {
                    if (c2 != 11) {
                        if (c2 == 15) {
                            break;
                        }
                    } else {
                        cArr[(i * 2) + 1] = '#';
                    }
                } else {
                    cArr[(i * 2) + 1] = '*';
                }
            } else {
                cArr[(i * 2) + 1] = (char) (c2 + '0');
            }
        }
        return new String(cArr);
    }

    public static boolean getBoolBit(int i, int i2) {
        switch (i) {
            case 0:
                return getBoolBit0(i2);
            case 1:
                return getBoolBit1(i2);
            case 2:
                return getBoolBit2(i2);
            case 3:
                return getBoolBit3(i2);
            case 4:
                return getBoolBit4(i2);
            case 5:
                return getBoolBit5(i2);
            case 6:
                return getBoolBit6(i2);
            case 7:
                return getBoolBit7(i2);
            default:
                return false;
        }
    }

    public static int setIntByteWithBitFromOtherInt(int i, int i2, int i3, int i4) {
        return setIntByteWithBit(i, i2, getIntByteWithBit(i3, i4));
    }

    public static int setIntFromByteWithBitFromOtherInt(int i, int i2, int i3, int i4, int i5) {
        return setIntFromByteWithBit(i, getIntFromByteWithBit(i3, i4, i5), i2, i5);
    }

    public static boolean isBackground(Context context, String str) throws SecurityException {
        if (context == null || TextUtils.isEmpty(str)) {
            return false;
        }
        List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
        return runningTasks == null || runningTasks.size() <= 0 || !str.equals(runningTasks.get(0).topActivity.getClassName());
    }

    public static String bytes2HexString(byte[] bArr, int i) {
        String str = "";
        for (int i2 = 0; i2 < i; i2++) {
            String hexString = Integer.toHexString(bArr[i2] & 255);
            if (hexString.length() == 1) {
                hexString = '0' + hexString;
            }
            str = str + hexString.toUpperCase() + " ";
        }
        return str;
    }

    public static String bytes2HexString(int[] iArr, int i) {
        String str = "";
        for (int i2 = 0; i2 < i; i2++) {
            String hexString = Integer.toHexString(iArr[i2] & 255);
            if (hexString.length() == 1) {
                hexString = '0' + hexString;
            }
            str = str + hexString.toUpperCase() + " ";
        }
        return str;
    }

    public static int setWidgetVisiable(Boolean bool) {
        return bool.booleanValue() ? 0 : 4;
    }

    public static byte[] byteMerger(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    public static byte[] byteMerger(byte[] bArr, byte[] bArr2, byte b) {
        return byteMerger(byteMerger(bArr, bArr2), new byte[]{b});
    }

    public static byte[] limitDataLength(byte[] bArr, int i) {
        return Arrays.copyOf(bArr, i);
    }

    public static byte[] compensateZero(byte[] bArr, int i) {
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        for (int length = bArr.length; length < i; length++) {
            bArr2[length] = 0;
        }
        return bArr2;
    }

    public static int[] restructureArray(int[] iArr, int[] iArr2) {
        int length = iArr.length - iArr2.length;
        int[] iArrCopyOf = Arrays.copyOf(iArr, iArr.length);
        int[] iArr3 = new int[length];
        int i = 0;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            for (int i3 : iArr2) {
                if (i3 == i2) {
                    iArrCopyOf[i2] = 65535;
                }
            }
            int i4 = iArrCopyOf[i2];
            if (i4 != 65535) {
                iArr3[i] = i4;
                i++;
            }
        }
        return iArr3;
    }

    public static float getRound(float f, int i) {
        return (float) (Math.round(f * r0) / Math.pow(10.0d, i));
    }

    public static byte[] getBytesEndWithAssign(byte[] bArr, int i, byte b) {
        int i2 = i + 2;
        byte[] bArr2 = new byte[bArr.length - i2];
        int i3 = 0;
        for (int i4 = 0; i4 < bArr.length - i2; i4++) {
            byte b2 = bArr[i4 + i2];
            bArr2[i4] = b2;
            if (b2 != b) {
                i3++;
            }
        }
        byte[] bArr3 = i3 == 0 ? new byte[1] : new byte[i3];
        for (int i5 = 0; i5 < bArr3.length; i5++) {
            bArr3[i5] = bArr2[i5];
        }
        return bArr3;
    }

    public static byte[] setReportHiworldSrcInfoData(byte b, byte b2) {
        byte[] bArr = new byte[15];
        bArr[0] = 22;
        bArr[1] = b;
        bArr[2] = b2;
        for (int i = 3; i < 15; i++) {
            bArr[i] = 32;
        }
        return bArr;
    }

    public static byte[] makeBytesFixedLength(byte[] bArr, int i) {
        byte[] bArr2 = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            if (i2 >= bArr.length) {
                bArr2[i2] = 0;
            } else {
                bArr2[i2] = bArr[i2];
            }
        }
        return bArr2;
    }

    public static byte[] makeBytesFixedLength(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i];
        for (int i3 = 0; i3 < i; i3++) {
            if (i3 >= bArr.length) {
                bArr2[i3] = (byte) i2;
            } else {
                bArr2[i3] = bArr[i3];
            }
        }
        return bArr2;
    }

    public static byte[] stringGetBytes(String str, String str2) {
        byte[] bArr = new byte[0];
        try {
            return str.getBytes(str2);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return bArr;
        }
    }

    public static int rangeNumber(int i, int i2) {
        return rangeNumber(i, 0, i2);
    }

    public static String makeMediaInfoCenteredInBytes(int i, String str) {
        String str2 = "";
        if (str == null || str.length() > i) {
            return "";
        }
        int length = i - str.length();
        int i2 = length / 2;
        int i3 = length - i2;
        for (int i4 = 0; i4 < i2; i4++) {
            str2 = str2 + " ";
        }
        String str3 = str2 + str;
        for (int i5 = 0; i5 < i3; i5++) {
            str3 = str3 + " ";
        }
        return str3;
    }

    public static byte[] exceptBOMHead(byte[] bArr) {
        byte b;
        while (bArr.length > 2 && (((b = bArr[0]) == -2 && bArr[1] == -1) || (b == -1 && bArr[1] == -2))) {
            bArr = Arrays.copyOfRange(bArr, 2, bArr.length);
        }
        return bArr;
    }

    public static byte[] phoneNum2UnicodeBig(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = i * 2;
            bArr2[i2] = 0;
            bArr2[i2 + 1] = (byte) (bArr[i] & 255);
        }
        return bArr2;
    }

    public static void knob(Context context, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            RealKeyUtil.realKeyClick(context, i);
        }
    }

    public static int getDecimalFrom8Bit(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return Integer.parseInt(i + "" + i2 + "" + i3 + "" + i4 + "" + i5 + "" + i6 + "" + i7 + "" + i8, 2);
    }

    public static String byteToHexString(byte b) {
        return Integer.toHexString((b & 255) | InputDeviceCompat.SOURCE_ANY).substring(6);
    }

    public static int blockBit(int i, int i2) {
        if (i2 == 0) {
            return (getIntFromByteWithBit(i, 1, 7) & 255) << 1;
        }
        if (i2 == 7) {
            return getIntFromByteWithBit(i, 0, 7);
        }
        int i3 = i2 + 1;
        int intFromByteWithBit = getIntFromByteWithBit(i, i3, 7 - i2) & 255;
        return ((getIntFromByteWithBit(i, 0, i2) & 255) & 255) ^ (intFromByteWithBit << i3);
    }

    public static byte[] splicingByte(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }
}
