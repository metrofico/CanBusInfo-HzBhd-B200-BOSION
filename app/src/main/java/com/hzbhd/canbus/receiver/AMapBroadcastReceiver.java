package com.hzbhd.canbus.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.util.amap.AMapUtils;

import java.util.regex.Pattern;

/* loaded from: classes2.dex */
public class AMapBroadcastReceiver extends BroadcastReceiver {
    private static int resultDirection(int current, int base) {
        if (current >= base) {
            return current - base;
        } else {
            return 8 - (base - current);
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (!TextUtils.isEmpty(action) && action.equals(AMapUtils.AUTONAVI_STANDARD_BROADCAST_SEND)) {
            MsgMgrFactory.getCanMsgMgrUtil(context).aMapCallBack(intent.getExtras());
        }
    }

    public static int getCarDirection(int angle, String directionText) {
        int directionBase;

        if (TextUtils.isEmpty(directionText)) {
            directionBase = 0;
        } else if (directionText.equals("东")) { // Este
            directionBase = 0;
        } else if (directionText.equals("南")) { // Sur
            directionBase = 2;
        } else if (directionText.equals("西")) { // Oeste
            directionBase = 4;
        } else if (directionText.equals("北")) { // Norte
            directionBase = 6;
        } else {
            directionBase = 0;
        }

        if ((angle > 337.5 && angle <= 360) || (angle > 0 && angle < 22.5)) {
            return resultDirection(7, directionBase); // Norte
        } else if (angle >= 22.5 && angle < 67.5) {
            return resultDirection(8, directionBase); // Noreste
        } else if (angle >= 67.5 && angle < 112.5) {
            return resultDirection(1, directionBase); // Este
        } else if (angle >= 112.5 && angle < 157.5) {
            return resultDirection(2, directionBase); // Sureste
        } else if (angle >= 157.5 && angle < 202.5) {
            return resultDirection(3, directionBase); // Sur
        } else if (angle >= 202.5 && angle < 247.5) {
            return resultDirection(4, directionBase); // Suroeste
        } else if (angle >= 247.5 && angle < 292.5) {
            return resultDirection(5, directionBase); // Oeste
        } else if (angle >= 292.5 && angle < 337.5) {
            return resultDirection(6, directionBase); // Noroeste
        }

        return 0; // Sin dirección válida
    }

    private String removeChinese(String str) {
        return TextUtils.isEmpty(str) ? "" : Pattern.compile("[一-龥]").matcher(str.trim()).replaceAll("");
    }

    private String getSurplus(int i) {
        int i2 = i / 3600;
        int i3 = i - (i2 * 3600);
        int i4 = i3 / 60;
        if (i3 - (i4 * 60) >= 30) {
            i4++;
        }
        return i2 + ":" + i4;
    }
}
