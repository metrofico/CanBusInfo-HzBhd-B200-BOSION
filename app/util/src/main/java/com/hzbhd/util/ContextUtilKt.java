package com.hzbhd.util;

import android.content.Context;
import android.content.SharedPreferences;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

public final class ContextUtilKt {
    public static Context baseContext;
    public static SharedPreferences baseShare;

    public static final Context getBaseContext() {
        Context context = baseContext;
        if (context != null) {
            return context;
        }
        return null;
    }

    public static final void setBaseContext(Context context) {
        baseContext = context;
    }

    public static final SharedPreferences getBaseShare() {
        SharedPreferences sharedPreferences = baseShare;
        if (sharedPreferences != null) {
            return sharedPreferences;
        }
        return null;
    }

    public static final void setBaseShare(SharedPreferences sharedPreferences) {
        baseShare = sharedPreferences;
    }
}
