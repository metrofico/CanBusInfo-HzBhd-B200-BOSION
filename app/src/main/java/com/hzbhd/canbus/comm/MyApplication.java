package com.hzbhd.canbus.comm;

import android.app.Activity;
import android.app.Application;

import com.hzbhd.canbus.adapter.util.CrashReportUtils;
import com.hzbhd.canbus.factory.Dependency;
import com.hzbhd.ui.util.BaseUtil;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class MyApplication extends Application {
    public static boolean IS_SET = false;
    private List<Activity> mActivityList;

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        BaseUtil.INSTANCE.init(getApplicationContext());
        BaseUtil.INSTANCE.startTestMode(0);
        this.mActivityList = new ArrayList<>();
        BaseUtil.INSTANCE.runBack(() -> {
            CrashReportUtils.init(MyApplication.this.getApplicationContext());
            if (Dependency.isStart()) {
                return;
            }
            new Dependency(MyApplication.this.getApplicationContext()).start();
        });
        ScreenConfig.initScreenConfig(this);
    }

    public void addActivity(Activity activity) {
        this.mActivityList.add(activity);
    }

    public void removeActivity(Activity activity) {
        this.mActivityList.remove(activity);
    }

    public void removeAllActivity() {
        for (Activity activity : this.mActivityList) {
            if (activity != null) {
                activity.finish();
            }
        }
    }
}
