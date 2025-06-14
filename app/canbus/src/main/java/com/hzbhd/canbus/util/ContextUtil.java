package com.hzbhd.canbus.util;

import android.content.Context;

/* loaded from: classes2.dex */
public class ContextUtil {
    private static ContextUtil c;
    private Context mContext;

    public static ContextUtil getInstance() {
        if (c == null) {
            c = new ContextUtil();
        }
        return c;
    }

    private ContextUtil() {
    }

    public void setContext(Context context) {
        this.mContext = context;
    }

    public Context getContext() {
        return this.mContext;
    }
}
