package com.hzbhd.canbus.car._447;

import android.util.Log;
import com.hzbhd.commontools.utils.FgeString;
import com.hzbhd.midware.jni.SerialPort;
import com.hzbhd.util.LogUtil;

/* loaded from: classes2.dex */
public class RadarUartDevice {
    private static final int MSG_BUFFER_SIZE = 1024;
    private static final int MSG_MAX_SIZE = 512;
    private static final String TAG = "RadarUartDevice";
    private static IUartDataReport mIUartDataReport;
    private static byte[] mMsgUnHandledData;
    private static byte[] mReadDataBuf;
    private static int mUnHandledDataLen;
    private static RadarUartDevice sRadarUartDevice;
    private int mDev = SerialPort.jni_serial_open(3);

    interface IUartDataReport {
        void onRead(byte[] bArr);
    }

    public static synchronized RadarUartDevice getRadarUartDevice(IUartDataReport iUartDataReport) {
        if (sRadarUartDevice == null) {
            sRadarUartDevice = new RadarUartDevice(iUartDataReport);
        }
        return sRadarUartDevice;
    }

    public RadarUartDevice(IUartDataReport iUartDataReport) {
        mIUartDataReport = iUartDataReport;
        Log.i(TAG, "jni_serial_open . " + this.mDev);
        int i = this.mDev;
        if (i != -1) {
            SerialPort.jni_serial_setup(i, 19200, 8, 'N', 1);
            mReadDataBuf = new byte[512];
            mMsgUnHandledData = new byte[1024];
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.hzbhd.canbus.car._447.RadarUartDevice$1] */
    public void start() {
        new Thread() { // from class: com.hzbhd.canbus.car._447.RadarUartDevice.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                while (true) {
                    try {
                        byte[] msg = RadarUartDevice.this.getMsg();
                        if (msg == null) {
                            Log.e(RadarUartDevice.TAG, "msg == null");
                            sleep(50L);
                        } else if (RadarUartDevice.mIUartDataReport != null) {
                            RadarUartDevice.mIUartDataReport.onRead(msg);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public synchronized byte[] getMsg() {
        byte[] msg;
        while (true) {
            try {
                msg = readMsg();
                if (msg == null) {
                    Thread.sleep(10L);
                } else {
                    printOneMsg(true, msg);
                }
            } catch (Exception unused) {
                return null;
            }
        }
        return msg;
    }

    private void printOneMsg(boolean z, byte[] bArr) {
        if (bArr == null) {
            return;
        }
        if (z) {
            if (LogUtil.log3()) {
                LogUtil.d("get :" + FgeString.bytes2HexString(bArr, bArr.length));
                return;
            }
            return;
        }
        Log.d(TAG, "send:" + FgeString.bytes2HexString(bArr, bArr.length));
    }

    private int readDABData(byte[] bArr) throws InterruptedException {
        while (true) {
            try {
                int iJni_serial_read_data = SerialPort.jni_serial_read_data(this.mDev, bArr, bArr.length, 10);
                if (iJni_serial_read_data > 0) {
                    return iJni_serial_read_data;
                }
                Thread.sleep(10L);
            } catch (Exception unused) {
                return -1;
            }
        }
    }

    private byte[] readMsg() {
        try {
            int dABData = readDABData(mReadDataBuf);
            if (dABData < 8) {
                return null;
            }
            for (int i = 0; i <= dABData - 8; i++) {
                byte[] bArr = mReadDataBuf;
                if (bArr[i] == 85 && bArr[i + 6] == -2 && bArr[i + 7] == -1) {
                    byte[] bArr2 = new byte[8];
                    System.arraycopy(bArr, i, bArr2, 0, 8);
                    return bArr2;
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            mUnHandledDataLen = 0;
            return null;
        }
    }
}
