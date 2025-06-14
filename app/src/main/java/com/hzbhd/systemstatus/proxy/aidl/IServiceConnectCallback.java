package com.hzbhd.systemstatus.proxy.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public interface IServiceConnectCallback extends IInterface {

    public static class Default implements IServiceConnectCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.hzbhd.systemstatus.proxy.aidl.IServiceConnectCallback
        public void onConnected() throws RemoteException {
        }
    }

    void onConnected() throws RemoteException;

    public static abstract class Stub extends Binder implements IServiceConnectCallback {
        private static final String DESCRIPTOR = "com.hzbhd.systemstatus.proxy.aidl.IServiceConnectCallback";
        static final int TRANSACTION_onConnected = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IServiceConnectCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IServiceConnectCallback)) {
                return (IServiceConnectCallback) iInterfaceQueryLocalInterface;
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
            onConnected();
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements IServiceConnectCallback {
            public static IServiceConnectCallback sDefaultImpl;
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

            @Override // com.hzbhd.systemstatus.proxy.aidl.IServiceConnectCallback
            public void onConnected() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onConnected();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IServiceConnectCallback iServiceConnectCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iServiceConnectCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iServiceConnectCallback;
            return true;
        }

        public static IServiceConnectCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
