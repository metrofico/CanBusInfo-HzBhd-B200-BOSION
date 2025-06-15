package com.hzbhd.canbus;

import android.util.Log;

import com.hzbhd.canbus.factory.Dependency;
import com.hzbhd.canbus.factory.proxy.CanSettingProxy;
import com.hzbhd.commontools.utils.FgeString;
import com.hzbhd.proxy.mcu.core.MCUMainManager;
import com.hzbhd.util.LogUtil;

import java.text.DecimalFormat;

/* loaded from: classes.dex */
public class CanbusMsgSender {
    public static DecimalFormat df = new DecimalFormat("00");

    public static void sendMsg(byte[] bArr) {
        logCanData(bArr);
        try {
            MCUMainManager.getInstance().sendMCUCanboxData(39, bArr);
        } catch (Exception unused) {
            if (LogUtil.log5()) {
                LogUtil.d("sendMsg: error: " + FgeString.bytes2HexString(bArr, bArr.length));
            }
        }
    }

    public static int convByte(byte b) {
        return b & 255;
    }

    public static void logCanData(byte[] bArr) {
        boolean canDataLogSwith;
        try {
            canDataLogSwith = Dependency.get(CanSettingProxy.class).getCanDataLogSwith();
        } catch (Exception unused) {
            canDataLogSwith = true;
        }
        if (canDataLogSwith) {
            String str = "27 16————>";
            for (int i = 1; i < bArr.length; i++) {
                String hexString = Integer.toHexString(convByte(bArr[i]));
                if (hexString.length() == 1) {
                    hexString = "0" + hexString;
                }
                str = str + hexString + " ";
            }
            Log.d("CAN_TX", str);
        }
    }
}
