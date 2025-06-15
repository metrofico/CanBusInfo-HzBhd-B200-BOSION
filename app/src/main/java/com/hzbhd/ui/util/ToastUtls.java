package com.hzbhd.ui.util;

import android.content.Context;
import android.content.res.Resources;
import android.widget.Toast;

import com.hzbhd.util.LogUtil;

/* loaded from: classes2.dex */
public class ToastUtls {
    private static Toast toast;

    public static Toast getToast(Context context, String str, int i) {
        try {
            toast.cancel();
        } catch (Exception unused) {
        }
        toast = Toast.makeText(context, str, i);
        if (LogUtil.log5()) {
            LogUtil.d("getToast: ");
        }
        return toast;
    }

    public static Toast getToast(Context context, int i, int i2) {
        try {
            toast.cancel();
        } catch (Exception unused) {
        }
        toast = Toast.makeText(context, i, i2);
        if (LogUtil.log5()) {
            LogUtil.d("getToast: ");
        }
        return toast;
    }

    public static void showShortText(Context context, int i, int i2, int i3) {
        if (LogUtil.log5()) {
            LogUtil.d("showShortText: " + i);
        }
        Toast toast2 = getToast(context, i, 0);
        toast2.setGravity(80, i2, i3);
        toast2.show();
    }

    public static void showShortText(Context context, String str, int i, int i2) {
        if (LogUtil.log5()) {
            LogUtil.d("showShortText: ");
        }
        Toast toast2 = getToast(context, str, 0);
        toast2.setGravity(80, i, i2);
        toast2.show();
    }

    public static void showLongText(Context context, int i, int i2, int i3) {
        Toast toast2 = getToast(context, i, 1);
        toast2.setGravity(17, 0, 0);
        toast2.show();
    }

    public static void showShortText(Context context, int i) throws Resources.NotFoundException {
        Toast toastMakeText = Toast.makeText(context, i, Toast.LENGTH_SHORT);
        toastMakeText.setGravity(80, 0, 250);
        toastMakeText.show();
    }

    public static void showShortTextBottom(Context context, int i) {
        Toast.makeText(context, i, Toast.LENGTH_SHORT).show();
    }

    public static void showLongText(Context context, int i) throws Resources.NotFoundException {
        Toast toastMakeText = Toast.makeText(context, i, Toast.LENGTH_LONG);
        toastMakeText.setGravity(80, 0, 250);
        toastMakeText.show();
    }

    public static void showLongText(Context context, String str) {
        Toast toastMakeText = Toast.makeText(context, str, Toast.LENGTH_LONG);
        toastMakeText.setGravity(80, 0, 250);
        toastMakeText.show();
    }
}
