package com.hzbhd.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

public final class ContextUtilKt {
    public static Context baseContext;
    public static SharedPreferences baseShare;

    public static Context getBaseContext() {
        return baseContext;
    }

    public static void setBaseContext(Context context) {
        baseContext = context;
        Log.d("ContextUtilKt", "initialize base context");
    }

    public static SharedPreferences getBaseShare() {
        return baseShare;
    }

    public static void setBaseShare(SharedPreferences sharedPreferences) {
        baseShare = sharedPreferences;
    }
}
