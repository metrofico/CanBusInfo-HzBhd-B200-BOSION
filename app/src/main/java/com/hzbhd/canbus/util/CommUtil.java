package com.hzbhd.canbus.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.hzbhd.canbus.R;
import com.hzbhd.canbus.BuildConfig;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.factory.Dependency;
import com.hzbhd.canbus.factory.proxy.CanSettingProxy;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.proxy.keydispatcher.SendKeyManager;
import com.hzbhd.proxy.sourcemanager.SourceManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/* loaded from: classes2.dex */
public class CommUtil {
    public static String getVersionName(Context context) {
        return BuildConfig.VERSION_NAME;
    }

    public static void printHexString(String str, byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                hexString = '0' + hexString;
            }
            sb.append(hexString.toUpperCase());
            sb.append(" ");
        }
        LogUtil.showLog(sb.toString());
    }

    public static void printHexStringByTag(String str, String str2, byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                hexString = '0' + hexString;
            }
            sb.append(hexString.toUpperCase());
            sb.append(" ");
        }
        LogUtil.showLog(str, sb.toString());
    }

    public static String getStrByResId(Context context, String str) {
        int identifier;
        int i = R.string.str_no_found;
        try {
            identifier = context.getResources().getIdentifier(str, "string", context.getPackageName());
        } catch (Exception e) {
            LogUtil.showLog("getStrByResId:" + e.toString());
            identifier = R.string.str_no_found;
        }
        if (identifier != 0) {
            i = identifier;
        }
        return context.getString(i);
    }

    public static int getStrIdByResId(Context context, String str) {
        int identifier;
        if (context != null) {
            try {
                identifier = context.getResources().getIdentifier(str, "string", context.getPackageName());
            } catch (Exception e) {
                LogUtil.showLog("getStrIdByResId:" + e.toString());
                identifier = R.string.str_no_found;
            }
        } else {
            identifier = 0;
        }
        return identifier == 0 ? R.string.str_no_found : identifier;
    }

    public static int getImgIdByResId(Context context, String str) {
        int identifier;
        if (context != null) {
            try {
                identifier = context.getResources().getIdentifier(str, "drawable", context.getPackageName());
            } catch (Exception e) {
                LogUtil.showLog("getImgIdByResId:" + e.toString());
                identifier = R.drawable.music_photo_null;
            }
        } else {
            identifier = 0;
        }
        return identifier == 0 ? R.drawable.music_photo_null : identifier;
    }

    public static String getAssetsString(Context context, String str) {
        try {
            InputStream inputStreamOpen = context.getResources().getAssets().open(str);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStreamOpen, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = bufferedReader.readLine();
                if (line != null) {
                    sb.append(line);
                } else {
                    bufferedReader.close();
                    inputStreamReader.close();
                    inputStreamOpen.close();
                    return sb.toString();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String subArrayToString(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            bArr2[i3] = bArr[i + i3];
        }
        return new String(bArr2);
    }

    public static int getDimenByResId(Context context, String str) {
        int identifier;
        int i = R.dimen.dp0;
        if (context != null) {
            try {
                identifier = context.getResources().getIdentifier(str, "dimen", context.getPackageName());
            } catch (Exception e) {
                LogUtil.showLog("getDimenByResId:" + e.toString());
                identifier = R.dimen.dp0;
            }
        } else {
            identifier = 0;
        }
        if (identifier != 0) {
            i = identifier;
        }
        return context.getResources().getDimensionPixelSize(i);
    }

    public static void printScreenWidthHeigh(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        LogUtil.showLog("screenWidth:" + i);
        LogUtil.showLog("screenHeight:" + i2);
    }

    public static byte getRadioFreqHiOrLow(String str, String str2, boolean z) throws NumberFormatException {
        int i;
        int i2 = 0;
        if (str.equals("AM2") || str.equals("MW") || str.equals("AM1") || str.equals("LW")) {
            if (z) {
                i = Integer.parseInt(str2);
            } else {
                i = Integer.parseInt(str2);
            }
        } else {
            if (!str.equals("FM1") && !str.equals("FM2") && !str.equals("FM3") && !str.equals("OIRT")) {
                return (byte) 0;
            }
            i = (int) (Double.parseDouble(str2) * 100.0d);
            i2 = z ? i >> 8 : i & 255;
        }
        return (byte) i2;
    }

    public static byte getRadioCurrentBand(String str, byte b, byte b2, byte b3, byte b4, byte b5) {
        str.hashCode();
        switch (str) {
            case "LW":
            case "AM1":
                return b4;
            case "MW":
            case "AM2":
                return b5;
            case "FM1":
                return b;
            case "FM2":
                return b2;
            case "FM3":
            case "OIRT":
                return b3;
            default:
                return (byte) 0;
        }
    }

    public static void showToast(Context context, String str) {
        Toast.makeText(context, str, Toast.LENGTH_LONG).show();
    }

    public static Bitmap decodeSampleBitmapFromResource(Resources resources, int i, int i2, int i3) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, i, options);
        options.inSampleSize = calculateInSampleSize(options, i2, i3);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(resources, i);
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            int i6 = i3 / 2;
            int i7 = i4 / 2;
            while (i6 / i5 >= i2 && i7 / i5 >= i) {
                i5 *= 2;
            }
        }
        return i5;
    }

    public static int isAirTemperatureSwitch(Context context) {
        return ((CanSettingProxy) Dependency.get(CanSettingProxy.class)).getSwitchAcTemperature() ? 1 : 0;
    }

    public static boolean equal(Object obj, Object... objArr) {
        for (Object obj2 : objArr) {
            if (obj.equals(obj2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isMiscReverse() {
        return MediaShareData.Misc.INSTANCE.getMiscReverse() == 1;
    }

    public static boolean isBackCamera(Context context) {
        return isMiscReverse() && !isPanoramic(context);
    }

    public static boolean isPanoramic(Context context) {
        return SharePreUtil.getBoolValue(context, Constant.SHARE_IS_PANORAMIC, false);
    }

    public static void requestAudioChannel(SourceConstantsDef.SOURCE_ID source_id, SourceConstantsDef.DISP_ID disp_id, Bundle bundle) {
        SourceManager.getInstance().requestAudioChannel(source_id, disp_id, bundle);
    }

    public static void releaseAudioChannel(SourceConstantsDef.SOURCE_ID source_id, SourceConstantsDef.DISP_ID disp_id) {
        SourceManager.getInstance().releaseAudioChannel(source_id, disp_id);
    }

    public static void playBeep() {
        SendKeyManager.getInstance().playBeep(0);
    }

    public static String temperatureUnitSwitch(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            if (((CanSettingProxy) Dependency.get(CanSettingProxy.class)).getTemperatureUnit() != 1) {
                return (!str.endsWith("℃") && str.endsWith("℉")) ? String.format("%.1f℃", Float.valueOf(((Float.parseFloat(str.substring(0, str.length() - 1)) - 32.0f) * 5.0f) / 9.0f)) : str;
            }
            if (str.endsWith("℃")) {
                return String.format("%.1f℉", Float.valueOf(((Float.parseFloat(str.substring(0, str.length() - 1)) * 9.0f) / 5.0f) + 32.0f));
            }
            str.endsWith("℉");
            return str;
        } catch (Exception e) {
            Log.i("CommUtil", "temperatureUnitSwitch failed [" + str + "], message: " + e.getMessage());
            return str;
        }
    }
}
