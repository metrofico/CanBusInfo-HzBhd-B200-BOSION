package com.hzbhd.commontools.utils;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes2.dex */
public class CommUtil {
    private static final String TAG = "CommUtil";
    private static Context mContext;
    private static SharedPreferences mShare;

    public interface TestInterface {
        public static final String TAG = "AUTO_TEST";

        void startTest(int i);
    }

    public static void init(Context context) {
        mContext = context;
        mShare = context.getSharedPreferences("dsp_save", 0);
    }

    public static SharedPreferences getShare() {
        return mShare;
    }

    public static void startTestMode(int i) {
        try {
            ((TestInterface) Class.forName("com.hzbhd.service.test.MyTest").newInstance()).startTest(i);
        } catch (Exception unused) {
        }
    }

    public static Context getContext() {
        return mContext;
    }
}
