package com.hzbhd.canbus.adapter.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.RemoteException;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.hzbhd.canbus.adapter.util.CanTypeMsg;
import com.hzbhd.canbus.car._464.MsgMgr;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.commontools.utils.ConfigUtil;
import com.hzbhd.midware.constant.HotKeyConstant;

import java.util.Arrays;

import kotlinx.coroutines.scheduling.WorkQueueKt;
import nfore.android.bt.res.NfDef;

/* loaded from: classes.dex */
public class Util {
    public static String CANBUS_AMP_VAL_BAL = "CANBUS_AMP_VAL_BAL";
    public static String CANBUS_AMP_VAL_BASS = "CANBUS_AMP_VAL_BASS";
    public static String CANBUS_AMP_VAL_EFFECT = "CANBUS_AMP_VAL_EFFECT";
    public static String CANBUS_AMP_VAL_FAD = "CANBUS_AMP_VAL_FAD";
    public static String CANBUS_AMP_VAL_MID = "CANBUS_AMP_VAL_MID";
    public static String CANBUS_AMP_VAL_TRE = "CANBUS_AMP_VAL_TRE";
    public static String CANBUS_AMP_VAL_VOL = "CANBUS_AMP_VAL_VOL";
    public static String CANBUS_IS_DATA_PORT = "CANBUS_IS_DATA_PORT";
    public static final int MCU_HARDWARE_CURRENTMAX = 10;
    public static final int MCU_HARDWARE_stm32f030c8 = 1;
    public static final int MCU_HARDWARE_stm32f030rc = 3;
    public static final int MCU_HARDWARE_stm32f072c8 = 2;
    public static final int MCU_HARDWARE_stm8s208 = 0;
    public static boolean isCanbusCmdStyleSt32 = false;
    private static String oldMsg;
    private static long oneTime;
    private static Toast toast;
    private static long twoTime;

    public enum SingleSpinMachine {
        PUBLIC,
        HONG,
        YHT,
        KT,
        LK,
        KT2,
        HTT2
    }

    private static byte ascToBcd(byte b) {
        if (b >= 48 && b <= 57) {
            return (byte) (b - 48);
        }
        if (b == 42) {
            return (byte) 10;
        }
        if (b == 35) {
            return (byte) 11;
        }
        if (b == 43) {
            return NfDef.AVRCP_EVENT_ID_UIDS_CHANGED;
        }
        return (byte) 15;
    }

    public static byte byteNum2AscByte(byte b) {
        if (b < 0 || b > 9) {
            return (byte) 48;
        }
        return (byte) (b + 48);
    }

    public static int castByte2LegalNum(byte b) {
        return (b & Byte.MIN_VALUE) != 0 ? b & 255 : b;
    }

    /* JADX WARN: Removed duplicated region for block: B:68:0x00c4 A[FALL_THROUGH, RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getFileName(int r1) {
        /*
            Method dump skipped, instructions count: 456
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.adapter.util.Util.getFileName(int):java.lang.String");
    }

    public static int getIntByBit(byte b, int i) {
        if (i == 0) {
            return 0;
        }

        switch (i) {
            case 1:
                return (b & 1) != 0 ? 1 : 0;
            case 2:
                return (b & 2) != 0 ? 1 : 0;
            case 3:
                return (b & 4) != 0 ? 1 : 0;
            case 4:
                return (b & 8) != 0 ? 1 : 0;
            case 5:
                return (b & 16) != 0 ? 1 : 0;
            case 6:
                return (b & 32) != 0 ? 1 : 0;
            case 7:
                return (b & 64) != 0 ? 1 : 0;
            case 8:
                return (b & Byte.MIN_VALUE) != 0 ? 1 : 0;
            default:
                return 0;
        }
    }


    public static boolean getIntByteWithBit(byte b, int i) {
        return i == 0 ? (b & 1) != 0 : 1 == i ? (b & 2) != 0 : 2 == i ? (b & 4) != 0 : 3 == i ? (b & 8) != 0 : 4 == i ? (b & 16) != 0 : 5 == i ? (b & 32) != 0 : 6 == i ? (b & 64) != 0 : 7 == i && (b & Byte.MIN_VALUE) != 0;
    }

    public static boolean getIntByteWithBit(int i, int i2) {
        return i2 == 0 ? (i & 1) != 0 : 1 == i2 ? (i & 2) != 0 : 2 == i2 ? (i & 4) != 0 : 3 == i2 ? (i & 8) != 0 : 4 == i2 ? (i & 16) != 0 : 5 == i2 ? (i & 32) != 0 : 6 == i2 ? (i & 64) != 0 : 7 == i2 && (i & 128) != 0;
    }

    public static int getIntFromByteWithBit(int i, int i2, int i3) {
        return ((i & 255) >> i2) & ((1 << i3) - 1);
    }

    public static boolean isCanbusCmdPorting(Context context) {
        return true;
    }

    public static byte setByteWithBit(byte b, int i, boolean z) {
        int i2;
        if (z) {
            if (i == 0) {
                i2 = b | 1;
            } else if (1 == i) {
                i2 = b | 2;
            } else if (2 == i) {
                i2 = b | 4;
            } else if (3 == i) {
                i2 = b | 8;
            } else if (4 == i) {
                i2 = b | 16;
            } else if (5 == i) {
                i2 = b | 32;
            } else if (6 == i) {
                i2 = b | 64;
            } else {
                if (7 != i) {
                    return b;
                }
                i2 = b | Byte.MIN_VALUE;
            }
        } else if (i == 0) {
            i2 = b & 254;
        } else if (1 == i) {
            i2 = b & 253;
        } else if (2 == i) {
            i2 = b & 251;
        } else if (3 == i) {
            i2 = b & 247;
        } else if (4 == i) {
            i2 = b & 239;
        } else if (5 == i) {
            i2 = b & 223;
        } else if (6 == i) {
            i2 = b & 191;
        } else {
            if (7 != i) {
                return b;
            }
            i2 = b & Byte.MAX_VALUE;
        }
        return (byte) i2;
    }

    public static final int MASK = 0x7F;           // 127

    public static int setIntByteWithBit(int i, int i2, boolean z) {
        return z ? i2 == 0 ? i | 1 : 1 == i2 ? i | 2 : 2 == i2 ? i | 4 : 3 == i2 ? i | 8 : 4 == i2 ? i | 16 : 5 == i2 ? i | 32 : 6 == i2 ? i | 64 : 7 == i2 ? i | 128 : i : i2 == 0 ? i & MsgMgr.DVD_MODE : 1 == i2 ? i & 253 : 2 == i2 ? i & MsgMgr.RADIO_MODE : 3 == i2 ? i & 247 : 4 == i2 ? i & 239 : 5 == i2 ? i & HotKeyConstant.K_DARK_MODE : 6 == i2 ? i & 191 : 7 == i2 ? i & MASK : i;
    }

    public static int setIntFromByteWithBit(int i, int i2, int i3, int i4) {
        return (i & (~(((1 << i4) - 1) << i3))) | (i2 << i3);
    }

    public static void showToast(Context context, String str) {
        if (toast == null) {
            Toast toastMakeText = Toast.makeText(context, str, Toast.LENGTH_SHORT);
            toast = toastMakeText;
            toastMakeText.show();
            oneTime = System.currentTimeMillis();
        } else {
            twoTime = System.currentTimeMillis();
            if (!str.equals(oldMsg)) {
                oldMsg = str;
                toast.setText(str);
                toast.show();
            } else if (twoTime - oneTime > 0) {
                toast.show();
            }
        }
        oneTime = twoTime;
    }

    public static SingleSpinMachine isYHT(Context context) {
        int i = Settings.System.getInt(context.getContentResolver(), "LCD_MCU_VERSION", 0);
        if (i == 16) {
            return SingleSpinMachine.KT;
        }
        if (i == 34) {
            return SingleSpinMachine.YHT;
        }
        if (i == 49) {
            return SingleSpinMachine.LK;
        }
        if (i == 50) {
            return SingleSpinMachine.KT2;
        }
        if (i == 64) {
            return SingleSpinMachine.HONG;
        }
        if (i == 65) {
            return SingleSpinMachine.HTT2;
        }
        if (i == 73) {
            return SingleSpinMachine.HTT2;
        }
        if (i == 74) {
            return SingleSpinMachine.HTT2;
        }
        return SingleSpinMachine.PUBLIC;
    }

    public static void WLog(Context context, String str, String str2) {
        if (Settings.System.getInt(context.getContentResolver(), str, 0) == 1) {
            Log.i(str, str2);
        }
    }

    public static boolean isPowerOffStatus(Context context) {
        return Settings.System.getInt(context.getContentResolver(), "PowerIsOff", 0) == 1;
    }

    public static void SaveBooleanValue(Context context, String str, boolean z) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("wujay", 0).edit();
        editorEdit.putBoolean(str, z);
        editorEdit.commit();
    }

    public static boolean GetBooleanValue(Context context, String str, boolean z) {
        return context.getSharedPreferences("wujay", 0).getBoolean(str, z);
    }

    public static void SaveIntValue(Context context, String str, int i) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("wujay", 0).edit();
        editorEdit.putInt(str, i);
        editorEdit.commit();
    }

    public static int GetIntValue(Context context, String str, int i) {
        return context.getSharedPreferences("wujay", 0).getInt(str, i);
    }

    public static boolean getIsDebug(Context context) {
        int i;
        return (context == null || (i = Settings.System.getInt(context.getContentResolver(), "isDebugModel", 0)) == 0 || i != 1) ? false : true;
    }

    public static String switchInteToHex(Integer num) {
        String hexString = Integer.toHexString(num.intValue());
        return hexString.length() == 1 ? "0" + hexString : hexString;
    }

    public static int getIntFronmDimen(Context context, int i) {
        return context.getResources().getDimensionPixelSize(context.getResources().getIdentifier("dp" + i, "dimen", context.getPackageName()));
    }

    public static byte[] asciiToBcd(byte[] bArr, int i) {
        int i2 = (i + 1) / 2;
        byte[] bArr2 = new byte[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = i3 * 2;
            int i5 = i4 + 1;
            if (i5 >= i) {
                bArr2[i3] = (byte) ((ascToBcd(bArr[i4]) << 4) | 15);
            } else {
                bArr2[i3] = (byte) ((ascToBcd(bArr[i4]) << 4) | ascToBcd(bArr[i5]));
            }
        }
        return bArr2;
    }

    public static byte byte2Bcd(byte b) {
        return (byte) (((b % 10) & 15) | ((b / 10) << 4));
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

    public static byte[] phoneNum2UnicodeLittleExtra(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = i * 2;
            bArr2[i2] = (byte) (bArr[i] & 255);
            bArr2[i2 + 1] = 0;
        }
        return bArr2;
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

    public static byte[] phoneNum2Ascii(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = 48;
            bArr2[i] = 48;
        }
        return bArr2;
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

    public static byte[] addBytes(byte[] bArr, byte b) {
        if (bArr == null) {
            return new byte[]{b};
        }
        int length = bArr.length + 1;
        byte[] bArr2 = new byte[length];
        for (int i = 0; i < length; i++) {
            if (i < bArr.length) {
                bArr2[i] = bArr[i];
            } else {
                bArr2[i] = b;
            }
        }
        return bArr2;
    }

    public static byte[] CompensateZero(byte[] bArr, int i) {
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        for (int length = bArr.length; length < i; length++) {
            bArr2[length] = 0;
        }
        return bArr2;
    }

    public static byte[] string2UnicodeSMALL(String str) {
        byte[] bArr = new byte[str.length() * 2];
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            int i2 = i * 2;
            bArr[i2] = (byte) (cCharAt & 255);
            bArr[i2 + 1] = (byte) ((cCharAt >> '\b') & 255);
        }
        return bArr;
    }

    public static byte[] string2UnicodeBIG(String str) {
        byte[] bArr = new byte[str.length() * 2];
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            int i2 = i * 2;
            bArr[i2] = (byte) ((cCharAt >> '\b') & 255);
            bArr[i2 + 1] = (byte) (cCharAt & 255);
        }
        return bArr;
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

    public static String byte2HexString(byte b) {
        String hexString = Integer.toHexString(b & 255);
        return hexString.length() == 1 ? '0' + hexString : hexString;
    }

    public static Boolean isSaleInside(Context context) {
        boolean z = false;
        int i = Settings.System.getInt(context.getContentResolver(), "McuHardWare", 0);
        if (i >= 1 && i <= 10) {
            z = true;
        }
        return Boolean.valueOf(z);
    }

    public static int getMcuChipType(Context context) {
        return Settings.System.getInt(context.getContentResolver(), "McuHardWare", 0);
    }

    public static Boolean getBooleanFromByteWithBit(int i, int i2, int i3) {
        if ((((i & 255) >> i2) & ((1 << i3) - 1)) == 1) {
            return true;
        }
        return false;
    }

    public static int setIntByteWithBitFromOtherInt(int i, int i2, int i3, int i4) {
        return setIntByteWithBit(i, i2, getIntByteWithBit(i3, i4));
    }

    public static byte setByteWithBitFromOtherByte(int i, int i2, int i3, int i4) {
        return (byte) (setIntByteWithBit(i, i2, getIntByteWithBit(i3, i4)) & 255);
    }

    public static int setIntFromByteWithBitFromOtherInt(int i, int i2, int i3, int i4, int i5) {
        return setIntFromByteWithBit(i, getIntFromByteWithBit(i3, i4, i5), i2, i5);
    }

    public static byte setByteFromByteWithBitFromOtherByte(int i, int i2, int i3, int i4, int i5) {
        return (byte) (setIntFromByteWithBit(i, getIntFromByteWithBit(i3, i4, i5), i2, i5) & 255);
    }

    public static void reportCanbusInfo(byte[] bArr) {
        try {
            FutureUtil.instance.reportCanbusInfo(bArr);
        } catch (Exception unused) {
        }
    }

    private static void sndSrcMediaInfo(Context context, SourceConstantsDef.SOURCE_ID source_id, byte[] bArr) throws RemoteException {
        if (source_id != SourceConstantsDef.SOURCE_ID.NULL) {
            int i = AnonymousClass1.$SwitchMap$com$hzbhd$commontools$SourceConstantsDef$SOURCE_ID[source_id.ordinal()];
            if (i == 1) {
                if (Settings.System.getInt(context.getContentResolver(), "btState", 1) == 1) {
                    if (FutureUtil.instance.getCurrentValidSource() == source_id || FutureUtil.instance.getCurrentValidSource() == SourceConstantsDef.SOURCE_ID.AUX2 || FutureUtil.instance.getCurrentValidSource() == SourceConstantsDef.SOURCE_ID.BTPHONE) {
                        FutureUtil.instance.reportCanbusInfo(bArr);
                        Settings.System.putString(context.getContentResolver(), "currentReport", Base64.encodeToString(bArr, 0));
                        Settings.System.putString(context.getContentResolver(), "CANBUS_SRC_REP", Base64.encodeToString(bArr, 0));
                        return;
                    }
                    return;
                }
                return;
            }
            if (i == 2) {
                if (Settings.System.getInt(context.getContentResolver(), "btState", 1) == 1) {
                    if (FutureUtil.instance.getCurrentValidSource() == source_id || FutureUtil.instance.getCurrentValidSource() == SourceConstantsDef.SOURCE_ID.BACKCAMERA || FutureUtil.instance.getCurrentValidSource() == SourceConstantsDef.SOURCE_ID.BTPHONE) {
                        FutureUtil.instance.reportCanbusInfo(bArr);
                        Settings.System.putString(context.getContentResolver(), "currentReport", Base64.encodeToString(bArr, 0));
                        Settings.System.putString(context.getContentResolver(), "CANBUS_SRC_REP", Base64.encodeToString(bArr, 0));
                        return;
                    }
                    return;
                }
                return;
            }
            if (Settings.System.getInt(context.getContentResolver(), "btState", 1) == 1) {
                if (FutureUtil.instance.getCurrentValidSource() == source_id || FutureUtil.instance.getCurrentValidSource() == SourceConstantsDef.SOURCE_ID.BTPHONE) {
                    FutureUtil.instance.reportCanbusInfo(bArr);
                    Settings.System.putString(context.getContentResolver(), "currentReport", Base64.encodeToString(bArr, 0));
                    Settings.System.putString(context.getContentResolver(), "CANBUS_SRC_REP", Base64.encodeToString(bArr, 0));
                    return;
                }
                return;
            }
            return;
        }
        FutureUtil.instance.reportCanbusInfo(bArr);
    }

    public static void reportSimpleMediaInfo(Context context, CanTypeMsg.ENUM_CANSBUS_SIMPLE_MEDIA_TYPE enum_cansbus_simple_media_type, int i, boolean z, boolean z2) {
        byte bOrdinal = (byte) enum_cansbus_simple_media_type.ordinal();
        SourceConstantsDef.SOURCE_ID source_id = SourceConstantsDef.SOURCE_ID.NULL;
        switch (AnonymousClass1.$SwitchMap$com$hzbhd$canbus$adapter$util$CanTypeMsg$ENUM_CANSBUS_SIMPLE_MEDIA_TYPE[enum_cansbus_simple_media_type.ordinal()]) {
            case 1:
                source_id = SourceConstantsDef.SOURCE_ID.NULL;
                break;
            case 2:
                source_id = SourceConstantsDef.SOURCE_ID.FM;
                break;
            case 3:
                source_id = SourceConstantsDef.SOURCE_ID.AUX1;
                break;
            case 4:
                source_id = SourceConstantsDef.SOURCE_ID.NULL;
                break;
            case 5:
                source_id = SourceConstantsDef.SOURCE_ID.BTAUDIO;
                break;
            case 6:
                source_id = SourceConstantsDef.SOURCE_ID.DTV;
                break;
            case 7:
                source_id = SourceConstantsDef.SOURCE_ID.ATV;
                break;
            case 8:
            case 9:
                source_id = SourceConstantsDef.SOURCE_ID.MPEG;
                break;
            case 10:
                source_id = SourceConstantsDef.SOURCE_ID.VIDEO;
                break;
            case 11:
            case 12:
                if (z) {
                    source_id = SourceConstantsDef.SOURCE_ID.VIDEO;
                    break;
                } else {
                    source_id = SourceConstantsDef.SOURCE_ID.MUSIC;
                    break;
                }
        }
        if (i == 255) {
            switch (AnonymousClass1.$SwitchMap$com$hzbhd$canbus$adapter$util$CanTypeMsg$ENUM_CANSBUS_SIMPLE_MEDIA_TYPE[enum_cansbus_simple_media_type.ordinal()]) {
                case 1:
                case 6:
                case 7:
                    i = 0;
                    break;
                case 2:
                    i = 1;
                    break;
                case 3:
                    i = 48;
                    break;
                case 4:
                case 5:
                    i = 64;
                    break;
                case 8:
                    i = 16;
                    bOrdinal = 16;
                    break;
                case 9:
                    i = 33;
                    bOrdinal = 17;
                    break;
                case 10:
                    i = 35;
                    bOrdinal = 17;
                    break;
            }
        }
        try {
            sndSrcMediaInfo(context, source_id, z2 ? new byte[]{22, -64, bOrdinal, (byte) i, 0, 0, 0, 0, 0, 0} : new byte[]{22, -64, bOrdinal, (byte) i});
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static String getProtocolCompany(String str) {
        return str.contains("Simple") ? CanTypeMsg.SIMPLE : str.contains("Hiworld") ? CanTypeMsg.HIWORLD : str.contains("Raise") ? CanTypeMsg.RAISE : str.contains("Xinbas") ? CanTypeMsg.XINBAS : str.contains("Oudi") ? CanTypeMsg.OUDI : str.contains("Luzheng") ? CanTypeMsg.LUZHENG : str.contains(CanTypeMsg.XFY) ? CanTypeMsg.XFY : str.contains("Binary") ? CanTypeMsg.BINARY : str.contains(CanTypeMsg.CYT) ? CanTypeMsg.CYT : str.contains("Dogen") ? CanTypeMsg.DOGEN : CanTypeMsg.OTHER;
    }

    public static void reportHiworldMideaInfo(Context context, CanTypeMsg.ENUM_CANSBUS_HIWORLD_MEDIA_TYPE enum_cansbus_hiworld_media_type, boolean z, byte[] bArr) {
        SourceConstantsDef.SOURCE_ID source_id = SourceConstantsDef.SOURCE_ID.NULL;
        byte[] bArr2 = new byte[12];
        byte[] bArr3 = {22, -46, (byte) enum_cansbus_hiworld_media_type.ordinal()};
        byte length = (byte) (bArr.length < 12 ? bArr.length : 12);
        if (bArr != null) {
            for (int i = 0; i < length; i++) {
                bArr2[i] = bArr[i];
            }
        }
        switch (AnonymousClass1.$SwitchMap$com$hzbhd$canbus$adapter$util$CanTypeMsg$ENUM_CANSBUS_HIWORLD_MEDIA_TYPE[enum_cansbus_hiworld_media_type.ordinal()]) {
            case 1:
                source_id = SourceConstantsDef.SOURCE_ID.NULL;
                break;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                source_id = SourceConstantsDef.SOURCE_ID.FM;
                break;
            case 7:
            case 8:
                source_id = SourceConstantsDef.SOURCE_ID.MPEG;
                break;
            case 9:
                source_id = SourceConstantsDef.SOURCE_ID.DTV;
                break;
            case 10:
                source_id = SourceConstantsDef.SOURCE_ID.NULL;
                break;
            case 11:
                source_id = SourceConstantsDef.SOURCE_ID.AUX1;
                break;
            case 12:
            case 13:
                if (z) {
                    source_id = SourceConstantsDef.SOURCE_ID.VIDEO;
                    break;
                } else {
                    source_id = SourceConstantsDef.SOURCE_ID.MUSIC;
                    break;
                }
        }
        try {
            sndSrcMediaInfo(context, source_id, byteMerger(bArr3, bArr2));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: com.hzbhd.canbus.adapter.util.Util$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$adapter$util$CanTypeMsg$ENUM_CANSBUS_HIWORLD_MEDIA_TYPE;
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$adapter$util$CanTypeMsg$ENUM_CANSBUS_SIMPLE_MEDIA_TYPE;
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$commontools$SourceConstantsDef$SOURCE_ID;

        static {
            int[] iArr = new int[CanTypeMsg.ENUM_CANSBUS_HIWORLD_MEDIA_TYPE.values().length];
            $SwitchMap$com$hzbhd$canbus$adapter$util$CanTypeMsg$ENUM_CANSBUS_HIWORLD_MEDIA_TYPE = iArr;
            try {
                iArr[CanTypeMsg.ENUM_CANSBUS_HIWORLD_MEDIA_TYPE.OFF.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$adapter$util$CanTypeMsg$ENUM_CANSBUS_HIWORLD_MEDIA_TYPE[CanTypeMsg.ENUM_CANSBUS_HIWORLD_MEDIA_TYPE.FM1.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$adapter$util$CanTypeMsg$ENUM_CANSBUS_HIWORLD_MEDIA_TYPE[CanTypeMsg.ENUM_CANSBUS_HIWORLD_MEDIA_TYPE.FM2.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$adapter$util$CanTypeMsg$ENUM_CANSBUS_HIWORLD_MEDIA_TYPE[CanTypeMsg.ENUM_CANSBUS_HIWORLD_MEDIA_TYPE.FM3.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$adapter$util$CanTypeMsg$ENUM_CANSBUS_HIWORLD_MEDIA_TYPE[CanTypeMsg.ENUM_CANSBUS_HIWORLD_MEDIA_TYPE.AM1.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$adapter$util$CanTypeMsg$ENUM_CANSBUS_HIWORLD_MEDIA_TYPE[CanTypeMsg.ENUM_CANSBUS_HIWORLD_MEDIA_TYPE.AM2.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$adapter$util$CanTypeMsg$ENUM_CANSBUS_HIWORLD_MEDIA_TYPE[CanTypeMsg.ENUM_CANSBUS_HIWORLD_MEDIA_TYPE.CD.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$adapter$util$CanTypeMsg$ENUM_CANSBUS_HIWORLD_MEDIA_TYPE[CanTypeMsg.ENUM_CANSBUS_HIWORLD_MEDIA_TYPE.DVD.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$adapter$util$CanTypeMsg$ENUM_CANSBUS_HIWORLD_MEDIA_TYPE[CanTypeMsg.ENUM_CANSBUS_HIWORLD_MEDIA_TYPE.TV.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$adapter$util$CanTypeMsg$ENUM_CANSBUS_HIWORLD_MEDIA_TYPE[CanTypeMsg.ENUM_CANSBUS_HIWORLD_MEDIA_TYPE.PHONE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$adapter$util$CanTypeMsg$ENUM_CANSBUS_HIWORLD_MEDIA_TYPE[CanTypeMsg.ENUM_CANSBUS_HIWORLD_MEDIA_TYPE.AUX.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$adapter$util$CanTypeMsg$ENUM_CANSBUS_HIWORLD_MEDIA_TYPE[CanTypeMsg.ENUM_CANSBUS_HIWORLD_MEDIA_TYPE.USB.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$adapter$util$CanTypeMsg$ENUM_CANSBUS_HIWORLD_MEDIA_TYPE[CanTypeMsg.ENUM_CANSBUS_HIWORLD_MEDIA_TYPE.MCARD.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$adapter$util$CanTypeMsg$ENUM_CANSBUS_HIWORLD_MEDIA_TYPE[CanTypeMsg.ENUM_CANSBUS_HIWORLD_MEDIA_TYPE.DVDC.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$adapter$util$CanTypeMsg$ENUM_CANSBUS_HIWORLD_MEDIA_TYPE[CanTypeMsg.ENUM_CANSBUS_HIWORLD_MEDIA_TYPE.CAMERA.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$adapter$util$CanTypeMsg$ENUM_CANSBUS_HIWORLD_MEDIA_TYPE[CanTypeMsg.ENUM_CANSBUS_HIWORLD_MEDIA_TYPE.IPOD.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$adapter$util$CanTypeMsg$ENUM_CANSBUS_HIWORLD_MEDIA_TYPE[CanTypeMsg.ENUM_CANSBUS_HIWORLD_MEDIA_TYPE.NAVI.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            int[] iArr2 = new int[CanTypeMsg.ENUM_CANSBUS_SIMPLE_MEDIA_TYPE.values().length];
            $SwitchMap$com$hzbhd$canbus$adapter$util$CanTypeMsg$ENUM_CANSBUS_SIMPLE_MEDIA_TYPE = iArr2;
            try {
                iArr2[CanTypeMsg.ENUM_CANSBUS_SIMPLE_MEDIA_TYPE.OFF.ordinal()] = 1;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$adapter$util$CanTypeMsg$ENUM_CANSBUS_SIMPLE_MEDIA_TYPE[CanTypeMsg.ENUM_CANSBUS_SIMPLE_MEDIA_TYPE.RADIO.ordinal()] = 2;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$adapter$util$CanTypeMsg$ENUM_CANSBUS_SIMPLE_MEDIA_TYPE[CanTypeMsg.ENUM_CANSBUS_SIMPLE_MEDIA_TYPE.AUX.ordinal()] = 3;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$adapter$util$CanTypeMsg$ENUM_CANSBUS_SIMPLE_MEDIA_TYPE[CanTypeMsg.ENUM_CANSBUS_SIMPLE_MEDIA_TYPE.PHONE.ordinal()] = 4;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$adapter$util$CanTypeMsg$ENUM_CANSBUS_SIMPLE_MEDIA_TYPE[CanTypeMsg.ENUM_CANSBUS_SIMPLE_MEDIA_TYPE.A2DP.ordinal()] = 5;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$adapter$util$CanTypeMsg$ENUM_CANSBUS_SIMPLE_MEDIA_TYPE[CanTypeMsg.ENUM_CANSBUS_SIMPLE_MEDIA_TYPE.DTV.ordinal()] = 6;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$adapter$util$CanTypeMsg$ENUM_CANSBUS_SIMPLE_MEDIA_TYPE[CanTypeMsg.ENUM_CANSBUS_SIMPLE_MEDIA_TYPE.ATV.ordinal()] = 7;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$adapter$util$CanTypeMsg$ENUM_CANSBUS_SIMPLE_MEDIA_TYPE[CanTypeMsg.ENUM_CANSBUS_SIMPLE_MEDIA_TYPE.CD.ordinal()] = 8;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$adapter$util$CanTypeMsg$ENUM_CANSBUS_SIMPLE_MEDIA_TYPE[CanTypeMsg.ENUM_CANSBUS_SIMPLE_MEDIA_TYPE.DVD.ordinal()] = 9;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$adapter$util$CanTypeMsg$ENUM_CANSBUS_SIMPLE_MEDIA_TYPE[CanTypeMsg.ENUM_CANSBUS_SIMPLE_MEDIA_TYPE.USB_VIDEO.ordinal()] = 10;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$adapter$util$CanTypeMsg$ENUM_CANSBUS_SIMPLE_MEDIA_TYPE[CanTypeMsg.ENUM_CANSBUS_SIMPLE_MEDIA_TYPE.USB.ordinal()] = 11;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$adapter$util$CanTypeMsg$ENUM_CANSBUS_SIMPLE_MEDIA_TYPE[CanTypeMsg.ENUM_CANSBUS_SIMPLE_MEDIA_TYPE.SD.ordinal()] = 12;
            } catch (NoSuchFieldError unused29) {
            }
            int[] iArr3 = new int[SourceConstantsDef.SOURCE_ID.values().length];
            $SwitchMap$com$hzbhd$commontools$SourceConstantsDef$SOURCE_ID = iArr3;
            try {
                iArr3[SourceConstantsDef.SOURCE_ID.AUX1.ordinal()] = 1;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                $SwitchMap$com$hzbhd$commontools$SourceConstantsDef$SOURCE_ID[SourceConstantsDef.SOURCE_ID.MPEG.ordinal()] = 2;
            } catch (NoSuchFieldError unused31) {
            }
        }
    }

    public static byte[] mergeByteArrayAndAscii(byte[] bArr, String str) {
        return byteMerger(bArr, str.getBytes());
    }

    public static boolean isOriginalScreen() {
        return ConfigUtil.getDeviceId().contains("N60") || ConfigUtil.getDeviceId().contains("N74") || ConfigUtil.getDeviceId().contains("N52") || ConfigUtil.getDeviceId().contains("N95") || ConfigUtil.getDeviceId().contains("N0B") || ConfigUtil.getDeviceId().contains("N0J") || ConfigUtil.getDeviceId().contains("NZ1");
    }

    public static boolean isOriginalScreenN60N74() {
        return ConfigUtil.getDeviceId().contains("N60") || ConfigUtil.getDeviceId().contains("N74") || ConfigUtil.getDeviceId().contains("N95") || ConfigUtil.getDeviceId().contains("N0B") || ConfigUtil.getDeviceId().contains("N0J") || ConfigUtil.getDeviceId().contains("NZ1");
    }

    public static boolean isOriginalSreenNeedBt() {
        return ConfigUtil.getDeviceId().contains("N52");
    }

    public static boolean isOriginalScreenCanSwitchDspAndOriginalAmp() {
        return ConfigUtil.getDeviceId().contains("N95") || ConfigUtil.getDeviceId().contains("N0B") || ConfigUtil.getDeviceId().contains("N52") || ConfigUtil.getDeviceId().contains("NZ1");
    }

    public static void setStopAllFuncFlag(Context context, boolean z) {
        Log.e("getStopAllFuncFlag", " setStopAllFuncFlag isNeedStopAllFunc=" + z);
        Settings.System.putInt(context.getContentResolver(), "PRJ_3163_STOP_FUNC_FLAG", z ? 1 : 0);
    }

    public static boolean getStopAllFuncFlag(Context context) {
        Log.e("getStopAllFuncFlag", "isNeedStopAllFunc=" + Settings.System.getInt(context.getContentResolver(), "PRJ_3163_STOP_FUNC_FLAG", 0));
        return Settings.System.getInt(context.getContentResolver(), "PRJ_3163_STOP_FUNC_FLAG", 0) == 1;
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

    public static String numCompensateZero(int i) {
        if (i < 10) {
            return "0" + i;
        }
        return "" + i;
    }

    public static int getDecimalBit(int i, int i2) {
        return (i / ((int) Math.pow(10.0d, i2))) % 10;
    }

    public static byte[] exceptBOMHead(byte[] bArr) {
        byte b;
        while (bArr.length > 2 && (((b = bArr[0]) == -2 && bArr[1] == -1) || (b == -1 && bArr[1] == -2))) {
            bArr = Arrays.copyOfRange(bArr, 2, bArr.length);
        }
        return bArr;
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

    public static boolean compare(Object obj, Object... objArr) {
        for (Object obj2 : objArr) {
            if (obj2.equals(obj)) {
                return true;
            }
        }
        return false;
    }

    public static byte[] deal0x57Data(byte[] bArr) {
        return bArr.length > 2 ? byteMerger(new byte[]{87}, Arrays.copyOfRange(bArr, 2, bArr.length - 1)) : new byte[0];
    }

    public static boolean contains(String str, String... strArr) {
        for (String str2 : strArr) {
            if (str.contains(str2)) {
                return true;
            }
        }
        return false;
    }
}
