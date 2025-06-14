package com.hzbhd.canbus.util;

import android.content.Context;
import android.content.Intent;
import com.hzbhd.canbus.CanbusMsgService;
import com.hzbhd.canbus.activity.TestCanBusActivity;

/* loaded from: classes2.dex */
public class TestCanBusUtil {
    CanbusMsgService canbusMsgService;
    Context mContext;
    Thread thread;
    private int runTag = 0;
    private int pack = 85;
    private int data2 = 0;
    private int data3 = 0;
    private int data4 = 0;
    private int data5 = 0;
    private int data6 = 0;
    private int data7 = 0;
    private int data8 = 0;
    private int data9 = 0;
    private int data10 = 0;
    private int data11 = 0;
    private int data12 = 0;
    private int data13 = 0;
    private int data14 = 0;
    private int data15 = 0;
    private int data16 = 0;
    private int data17 = 0;
    private int data18 = 0;
    private int data19 = 0;

    public void startTest(Context context) {
        this.mContext = context;
        this.runTag = 0;
        if (this.canbusMsgService == null) {
            this.canbusMsgService = new CanbusMsgService();
        }
        Thread thread = new Thread(new Runnable() { // from class: com.hzbhd.canbus.util.TestCanBusUtil.1
            @Override // java.lang.Runnable
            public void run() {
                TestCanBusUtil.this.openAllTest();
                TestCanBusUtil testCanBusUtil = TestCanBusUtil.this;
                testCanBusUtil.methodBody(new byte[]{(byte) testCanBusUtil.pack, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
            }
        });
        this.thread = thread;
        thread.start();
    }

    public void continueTest(Context context) {
        this.mContext = context;
        this.runTag = 0;
        if (this.canbusMsgService == null) {
            this.canbusMsgService = new CanbusMsgService();
        }
        Thread thread = new Thread(new Runnable() { // from class: com.hzbhd.canbus.util.TestCanBusUtil.2
            @Override // java.lang.Runnable
            public void run() {
                TestCanBusUtil testCanBusUtil = TestCanBusUtil.this;
                testCanBusUtil.methodBody(new byte[]{85, (byte) testCanBusUtil.data19, (byte) TestCanBusUtil.this.data18, (byte) TestCanBusUtil.this.data17, (byte) TestCanBusUtil.this.data16, (byte) TestCanBusUtil.this.data15, (byte) TestCanBusUtil.this.data14, (byte) TestCanBusUtil.this.data13, (byte) TestCanBusUtil.this.data12, (byte) TestCanBusUtil.this.data11, (byte) TestCanBusUtil.this.data10, (byte) TestCanBusUtil.this.data9, (byte) TestCanBusUtil.this.data8, (byte) TestCanBusUtil.this.data7, (byte) TestCanBusUtil.this.data6, (byte) TestCanBusUtil.this.data5, (byte) TestCanBusUtil.this.data4, (byte) TestCanBusUtil.this.data3, (byte) TestCanBusUtil.this.data2});
            }
        });
        this.thread = thread;
        thread.start();
    }

    public void pauseTest() {
        this.runTag = 1;
    }

    public void stopTest() {
        this.runTag = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void methodBody(byte[] bArr) {
        int i;
        char c;
        char c2;
        char c3;
        this.data2 = bArr[18];
        this.data3 = bArr[17];
        this.data4 = bArr[16];
        this.data5 = bArr[15];
        this.data6 = bArr[14];
        this.data7 = bArr[13];
        this.data8 = bArr[12];
        this.data9 = bArr[11];
        this.data10 = bArr[10];
        this.data11 = bArr[9];
        this.data12 = bArr[8];
        this.data13 = bArr[7];
        this.data14 = bArr[6];
        this.data15 = bArr[5];
        this.data16 = bArr[4];
        this.data17 = bArr[3];
        this.data18 = bArr[2];
        this.data19 = bArr[1];
        this.pack = bArr[0];
        for (int i2 = 0; i2 <= 256 && (i = this.runTag) != 1 && i != 2; i2++) {
            if (this.data19 == -1) {
                this.data18++;
                this.data19 = 0;
            }
            if (this.data18 == -1) {
                this.data17++;
                this.data18 = 0;
            }
            if (this.data17 == -1) {
                this.data16++;
                this.data17 = 0;
            }
            if (this.data16 == -1) {
                this.data15++;
                this.data16 = 0;
            }
            if (this.data15 == -1) {
                this.data14++;
                this.data15 = 0;
            }
            if (this.data14 == -1) {
                this.data13++;
                this.data14 = 0;
            }
            if (this.data13 == -1) {
                this.data12++;
                this.data13 = 0;
            }
            if (this.data12 == -1) {
                this.data11++;
                this.data12 = 0;
            }
            if (this.data11 == -1) {
                this.data10++;
                this.data11 = 0;
            }
            if (this.data10 == -1) {
                this.data9++;
                this.data10 = 0;
            }
            if (this.data9 == -1) {
                this.data8++;
                this.data9 = 0;
            }
            if (this.data8 == -1) {
                this.data7++;
                this.data8 = 0;
            }
            if (this.data7 == -1) {
                this.data6++;
                this.data7 = 0;
            }
            if (this.data6 == -1) {
                this.data5++;
                this.data6 = 0;
            }
            if (this.data5 == 255) {
                this.data4++;
                this.data5 = 0;
            }
            if (this.data4 == -1) {
                this.data3++;
                this.data4 = 0;
            }
            if (this.data3 == -1) {
                this.data2++;
                this.data3 = 0;
            }
            try {
                startStandardTest();
            } catch (Exception e) {
                MyLog.temporaryTracking("遇到了错误");
                sendErrorBroadCast("Error：" + e.toString() + "\n引起错误的数据：{" + Integer.toHexString(this.pack & 255) + "," + Integer.toHexString(this.data19 & 255) + "," + Integer.toHexString(this.data18 & 255) + "," + Integer.toHexString(this.data17 & 255) + "," + Integer.toHexString(this.data16 & 255) + "," + Integer.toHexString(this.data15 & 255) + "," + Integer.toHexString(this.data14 & 255) + "," + Integer.toHexString(this.data13 & 255) + "," + Integer.toHexString(this.data12 & 255) + "," + Integer.toHexString(this.data11 & 255) + "," + Integer.toHexString(this.data10 & 255) + "," + Integer.toHexString(this.data9 & 255) + "," + Integer.toHexString(this.data8 & 255) + "," + Integer.toHexString(this.data7 & 255) + "," + Integer.toHexString(this.data6 & 255) + "," + Integer.toHexString(this.data5 & 255) + "," + Integer.toHexString(this.data4 & 255) + "," + Integer.toHexString(this.data3 & 255) + "," + Integer.toHexString(this.data2 & 255) + ",}");
                pauseTest();
            }
            int i3 = this.data8;
            if (i3 == -2) {
                sendSuccessBroadCast();
                return;
            }
            int i4 = this.data19 + 1;
            this.data19 = i4;
            if (i4 == 255) {
                c = '\f';
                c2 = '\r';
                c3 = 14;
                methodBody(new byte[]{85, (byte) i4, (byte) this.data18, (byte) this.data17, (byte) this.data16, (byte) this.data15, (byte) this.data14, (byte) this.data13, (byte) this.data12, (byte) this.data11, (byte) this.data10, (byte) this.data9, (byte) i3, (byte) this.data7, (byte) this.data6, (byte) this.data5, (byte) this.data4, (byte) this.data3, (byte) this.data2});
            } else {
                c = '\f';
                c2 = '\r';
                c3 = 14;
            }
        }
    }

    private void simpleTest() {
        MyLog.temporaryTracking("{" + Integer.toHexString(this.pack & 255) + "," + Integer.toHexString(this.data19 & 255) + "," + Integer.toHexString(this.data18 & 255) + "," + Integer.toHexString(this.data18 & 255) + "," + Integer.toHexString(this.data18 & 255) + "," + Integer.toHexString(this.data18 & 255) + "," + Integer.toHexString(this.data18 & 255) + "," + Integer.toHexString(this.data18 & 255) + "," + Integer.toHexString(this.data18 & 255) + "," + Integer.toHexString(this.data18 & 255) + "," + Integer.toHexString(this.data18 & 255) + "," + Integer.toHexString(this.data18 & 255) + "," + Integer.toHexString(this.data18 & 255) + "," + Integer.toHexString(this.data18 & 255) + "," + Integer.toHexString(this.data18 & 255) + "," + Integer.toHexString(this.data18 & 255) + "," + Integer.toHexString(this.data18 & 255) + "," + Integer.toHexString(this.data18 & 255) + "," + Integer.toHexString(this.data18 & 255) + "," + Integer.toHexString(this.data18 & 255) + "," + Integer.toHexString(this.data18 & 255) + "," + Integer.toHexString(this.data18 & 255) + ",}");
        CanbusMsgService canbusMsgService = this.canbusMsgService;
        Context context = this.mContext;
        int i = this.data18;
        canbusMsgService.testCanBusInfo(context, new byte[]{85, (byte) this.data19, (byte) i, (byte) i, (byte) i, (byte) i, (byte) i, (byte) i, (byte) i, (byte) i, (byte) i, (byte) i, (byte) i, (byte) i, (byte) i, (byte) i, (byte) i, (byte) i, (byte) i, (byte) i, (byte) i});
    }

    private void startStandardTest() {
        MyLog.temporaryTracking("Analog Data：{" + Integer.toHexString(this.pack & 255) + "," + Integer.toHexString(this.data19 & 255) + "," + Integer.toHexString(this.data18 & 255) + "," + Integer.toHexString(this.data17 & 255) + "," + Integer.toHexString(this.data16 & 255) + "," + Integer.toHexString(this.data15 & 255) + "," + Integer.toHexString(this.data14 & 255) + "," + Integer.toHexString(this.data13 & 255) + "," + Integer.toHexString(this.data12 & 255) + "," + Integer.toHexString(this.data11 & 255) + "," + Integer.toHexString(this.data10 & 255) + "," + Integer.toHexString(this.data9 & 255) + "," + Integer.toHexString(this.data8 & 255) + "," + Integer.toHexString(this.data7 & 255) + "," + Integer.toHexString(this.data6 & 255) + "," + Integer.toHexString(this.data5 & 255) + "," + Integer.toHexString(this.data4 & 255) + "," + Integer.toHexString(this.data3 & 255) + "," + Integer.toHexString(this.data2 & 255) + ",}");
        this.canbusMsgService.testCanBusInfo(this.mContext, new byte[]{85, (byte) this.data19, (byte) this.data18, (byte) this.data17, (byte) this.data16, (byte) this.data15, (byte) this.data14, (byte) this.data13, (byte) this.data12, (byte) this.data11, (byte) this.data10, (byte) this.data9, (byte) this.data8, (byte) this.data7, (byte) this.data6, (byte) this.data5, (byte) this.data4, (byte) this.data3, (byte) this.data2});
    }

    public void openAllTest() {
        for (int i = 0; i <= 255; i++) {
            this.canbusMsgService.testCanBusInfo(this.mContext, new byte[]{85, (byte) i, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1});
        }
    }

    private void sendErrorBroadCast(String str) {
        MyLog.temporaryTracking("发送ERROR广播");
        try {
            Intent intent = new Intent();
            intent.setPackage("com.hzbhd.canbus");
            intent.setAction(TestCanBusActivity.BROADCAST_ACTION_DISC);
            intent.putExtra("ERROR_DATA", str);
            this.mContext.sendBroadcast(intent);
        } catch (Exception e) {
            MyLog.temporaryTracking("广播错误：" + e.toString());
        }
    }

    private void sendSuccessBroadCast() {
        MyLog.temporaryTracking("发送SUCCESS广播");
        try {
            Intent intent = new Intent();
            intent.setPackage("com.hzbhd.canbus");
            intent.setAction(TestCanBusActivity.BROADCAST_ACTION_SUCCESS);
            intent.putExtra("ERROR_DATA", "errorData");
            this.mContext.sendBroadcast(intent);
        } catch (Exception e) {
            MyLog.temporaryTracking("广播错误：" + e.toString());
        }
    }
}
