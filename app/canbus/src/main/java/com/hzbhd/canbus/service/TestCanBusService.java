package com.hzbhd.canbus.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.TestCanBusUtil;

/* loaded from: classes2.dex */
public class TestCanBusService extends Service {
    TestCanBusUtil testCanBusUtil;
    private MyBinder binder = new MyBinder();
    int Tag = 0;

    public class MyBinder extends Binder {
        public MyBinder() {
        }

        public TestCanBusService getService() {
            return TestCanBusService.this;
        }
    }

    public int getTag() {
        return this.Tag;
    }

    public void setTag(int i) {
        this.Tag = i;
    }

    public void continueTest() {
        if (this.testCanBusUtil == null) {
            this.testCanBusUtil = new TestCanBusUtil();
        }
        this.testCanBusUtil.continueTest(this);
    }

    public void pauseTest() {
        if (this.testCanBusUtil == null) {
            this.testCanBusUtil = new TestCanBusUtil();
        }
        this.testCanBusUtil.pauseTest();
    }

    public void stopTest() {
        if (this.testCanBusUtil == null) {
            this.testCanBusUtil = new TestCanBusUtil();
        }
        this.testCanBusUtil.stopTest();
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        MyLog.temporaryTracking("onCreate");
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        MyLog.temporaryTracking("onStartCommand");
        if (this.testCanBusUtil == null) {
            this.testCanBusUtil = new TestCanBusUtil();
        }
        this.testCanBusUtil.startTest(this);
        return super.onStartCommand(intent, i, i2);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        MyLog.temporaryTracking("onBind");
        return this.binder;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        if (this.testCanBusUtil == null) {
            this.testCanBusUtil = new TestCanBusUtil();
        }
        this.testCanBusUtil.stopTest();
        MyLog.temporaryTracking("onDestroy");
    }
}
