package com.hzbhd.canbus.adapter.park;

import android.os.IBinder;
import android.os.RemoteException;

/* loaded from: classes.dex */
public class ParkManager {
    public static final String TAG = "ParkManager";
    private static ParkClient mParkClient;
    private static ParkManager mParkManager;
    private static IParkInterface mService;
    private IBinder getServiceObj = null;
    private IParkCallBackInterface mIParkCallBackInterface = new IParkCallBackInterface() { // from class: com.hzbhd.canbus.adapter.park.ParkManager.1
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.hzbhd.canbus.adapter.park.IParkCallBackInterface
        public void onParkOrbitAngleChange(int i) throws RemoteException {
            ParkManager.mParkClient.onParkOrbitAngleChange(i);
        }
    };

    private ParkManager() {
        if (mService == null) {
            mService = new IParkInterface() { // from class: com.hzbhd.canbus.adapter.park.ParkManager.2
                @Override // android.os.IInterface
                public IBinder asBinder() {
                    return null;
                }

                @Override // com.hzbhd.canbus.adapter.park.IParkInterface
                public void registerCallBack(IParkCallBackInterface iParkCallBackInterface) throws RemoteException {
                }

                @Override // com.hzbhd.canbus.adapter.park.IParkInterface
                public void sendPanoramicParkKey(int i) throws RemoteException {
                }

                @Override // com.hzbhd.canbus.adapter.park.IParkInterface
                public void sendPanoramicParkOn(boolean z) throws RemoteException {
                }

                @Override // com.hzbhd.canbus.adapter.park.IParkInterface
                public void sendPanoramicParkPos(int i, int i2, int i3) throws RemoteException {
                }

                @Override // com.hzbhd.canbus.adapter.park.IParkInterface
                public void sendPanoramicParkWH(int i, int i2) throws RemoteException {
                }

                @Override // com.hzbhd.canbus.adapter.park.IParkInterface
                public void unRegisterCallBack() throws RemoteException {
                }
            };
        }
    }

    public static synchronized ParkManager getAtvManager() {
        if (mParkManager == null) {
            mParkManager = new ParkManager();
        }
        return mParkManager;
    }

    public void sendPanoramicParkOn(boolean z) {
        try {
            IParkInterface iParkInterface = mService;
            if (iParkInterface != null) {
                iParkInterface.sendPanoramicParkOn(z);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendPanoramicParkKey(int i) {
        try {
            IParkInterface iParkInterface = mService;
            if (iParkInterface != null) {
                iParkInterface.sendPanoramicParkKey(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendPanoramicParkWH(int i, int i2) {
        try {
            IParkInterface iParkInterface = mService;
            if (iParkInterface != null) {
                iParkInterface.sendPanoramicParkWH(i, i2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendPanoramicParkPos(int i, int i2, int i3) {
        try {
            IParkInterface iParkInterface = mService;
            if (iParkInterface != null) {
                iParkInterface.sendPanoramicParkPos(i, i2, i3);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registerCallBack(ParkClient parkClient) {
        try {
            mParkClient = parkClient;
            IParkInterface iParkInterface = mService;
            if (iParkInterface != null) {
                iParkInterface.registerCallBack(this.mIParkCallBackInterface);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void unregisterCalllBack() {
        try {
            IParkInterface iParkInterface = mService;
            if (iParkInterface != null) {
                iParkInterface.unRegisterCallBack();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
