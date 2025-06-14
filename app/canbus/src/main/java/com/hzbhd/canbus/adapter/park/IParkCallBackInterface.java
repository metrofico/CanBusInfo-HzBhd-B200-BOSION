package com.hzbhd.canbus.adapter.park;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IParkCallBackInterface extends IInterface {

    public static class Default implements IParkCallBackInterface {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.hzbhd.canbus.adapter.park.IParkCallBackInterface
        public void onParkOrbitAngleChange(int i) throws RemoteException {
        }
    }

    void onParkOrbitAngleChange(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IParkCallBackInterface {
        private static final String DESCRIPTOR = "com.hzbhd.canbus.adapter.park.IParkCallBackInterface";
        static final int TRANSACTION_onParkOrbitAngleChange = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IParkCallBackInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IParkCallBackInterface)) {
                return (IParkCallBackInterface) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            onParkOrbitAngleChange(parcel.readInt());
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements IParkCallBackInterface {
            public static IParkCallBackInterface sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.hzbhd.canbus.adapter.park.IParkCallBackInterface
            public void onParkOrbitAngleChange(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onParkOrbitAngleChange(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IParkCallBackInterface iParkCallBackInterface) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iParkCallBackInterface == null) {
                return false;
            }
            Proxy.sDefaultImpl = iParkCallBackInterface;
            return true;
        }

        public static IParkCallBackInterface getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
