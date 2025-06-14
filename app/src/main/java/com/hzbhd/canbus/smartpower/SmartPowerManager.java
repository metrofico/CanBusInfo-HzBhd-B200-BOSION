package com.hzbhd.canbus.smartpower;

import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import com.hzbhd.canbus.adapter.util.HzbhdLog;
import com.hzbhd.canbus.smartpower.ISurgingPowerCallback;
import java.util.HashMap;

/* loaded from: classes2.dex */
public class SmartPowerManager {
    private static final int NOTIFYSWCSETTINGS = 0;
    private static final String TAG = "SmartPowerManager";
    private static SmartPowerManager mSmartPowerManager;
    private static ISurgingPwerInterface mSmartPowerService;
    private static HashMap<OnSmartPowerChangeListener, SwcCallback> mSwcListenerMap = new HashMap<>();

    public interface OnSmartPowerChangeListener {
        void onDataChange(byte[] bArr);
    }

    public void sendMsg(byte[] bArr) throws RemoteException {
        ISurgingPwerInterface iSurgingPwerInterface = mSmartPowerService;
        if (iSurgingPwerInterface != null) {
            iSurgingPwerInterface.sendMsg(bArr);
        }
    }

    public static synchronized SmartPowerManager getInstance() {
        String str = TAG;
        HzbhdLog.e(str, "SmartPowerService SmartPowerManager getInstance");
        if (mSmartPowerManager == null) {
            mSmartPowerManager = new SmartPowerManager();
            HzbhdLog.e(str, "SmartPowerService SmartPowerManager getInstance 22");
        }
        return mSmartPowerManager;
    }

    private static final class SwcCallback extends ISurgingPowerCallback.Stub {
        private Handler mSwcHandler;
        private String mToken;

        SwcCallback(OnSmartPowerChangeListener onSmartPowerChangeListener) {
            this.mSwcHandler = new PowerChangeHandler(onSmartPowerChangeListener);
            try {
                if (SmartPowerManager.mSmartPowerService != null) {
                    this.mToken = SmartPowerManager.mSmartPowerService.registerCallBack(this);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override // com.hzbhd.canbus.smartpower.ISurgingPowerCallback
        public void notifySurgingPower(byte[] bArr) throws RemoteException {
            this.mSwcHandler.obtainMessage(0, bArr).sendToTarget();
        }

        private static final class PowerChangeHandler extends Handler {
            private OnSmartPowerChangeListener mListener;

            PowerChangeHandler(OnSmartPowerChangeListener onSmartPowerChangeListener) {
                this.mListener = onSmartPowerChangeListener;
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what != 0) {
                    return;
                }
                HzbhdLog.i("SettingManager", "handleMessage-->NOTIFYSWCSETTINGS");
                this.mListener.onDataChange((byte[]) message.obj);
            }
        }
    }

    public synchronized void addOnSmartPowerChangeListener(OnSmartPowerChangeListener onSmartPowerChangeListener) {
        if (onSmartPowerChangeListener != null) {
            if (!mSwcListenerMap.containsKey(onSmartPowerChangeListener)) {
                mSwcListenerMap.put(onSmartPowerChangeListener, new SwcCallback(onSmartPowerChangeListener));
            }
        }
    }

    private SmartPowerManager() {
        HzbhdLog.e(TAG, "SmartPowerService SmartPowerManager");
        if (mSmartPowerService == null) {
            mSmartPowerService = new ISurgingPwerInterface() { // from class: com.hzbhd.canbus.smartpower.SmartPowerManager.1
                @Override // android.os.IInterface
                public IBinder asBinder() {
                    return null;
                }

                @Override // com.hzbhd.canbus.smartpower.ISurgingPwerInterface
                public String registerCallBack(ISurgingPowerCallback iSurgingPowerCallback) throws RemoteException {
                    return null;
                }

                @Override // com.hzbhd.canbus.smartpower.ISurgingPwerInterface
                public void sendMsg(byte[] bArr) throws RemoteException {
                }

                @Override // com.hzbhd.canbus.smartpower.ISurgingPwerInterface
                public void unRegisterCallBack(String str) throws RemoteException {
                }
            };
        }
    }
}
