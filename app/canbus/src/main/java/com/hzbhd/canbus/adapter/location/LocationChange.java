package com.hzbhd.canbus.adapter.location;

import android.os.IBinder;
import android.os.RemoteException;
import com.hzbhd.canbus.adapter.location.LocationCallback;

/* loaded from: classes.dex */
public class LocationChange extends LocationCallback.Stub {
    @Override // com.hzbhd.canbus.adapter.location.LocationCallback.Stub, android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    @Override // com.hzbhd.canbus.adapter.location.LocationCallback
    public void getAltitude(double d) throws RemoteException {
    }

    @Override // com.hzbhd.canbus.adapter.location.LocationCallback
    public void getBear(float f) throws RemoteException {
    }

    @Override // com.hzbhd.canbus.adapter.location.LocationCallback
    public void getBearAndAltitude(float f, double d) throws RemoteException {
    }

    @Override // com.hzbhd.canbus.adapter.location.LocationCallback
    public void getLoc(double d, double d2) throws RemoteException {
    }

    @Override // com.hzbhd.canbus.adapter.location.LocationCallback
    public void getMaxSatelliteCount(int i) throws RemoteException {
    }

    @Override // com.hzbhd.canbus.adapter.location.LocationCallback
    public void getSpeed(int i) throws RemoteException {
    }
}
