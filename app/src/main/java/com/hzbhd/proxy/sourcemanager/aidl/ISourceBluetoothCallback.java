package com.hzbhd.proxy.sourcemanager.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public interface ISourceBluetoothCallback extends IInterface {

    public static class Default implements ISourceBluetoothCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.hzbhd.proxy.sourcemanager.aidl.ISourceBluetoothCallback
        public void onState(int i) throws RemoteException {
        }
    }

    void onState(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements ISourceBluetoothCallback {
        private static final String DESCRIPTOR = "com.hzbhd.proxy.sourcemanager.aidl.ISourceBluetoothCallback";
        static final int TRANSACTION_onState = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISourceBluetoothCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ISourceBluetoothCallback)) {
                return (ISourceBluetoothCallback) iInterfaceQueryLocalInterface;
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
            onState(parcel.readInt());
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements ISourceBluetoothCallback {
            public static ISourceBluetoothCallback sDefaultImpl;
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

            @Override // com.hzbhd.proxy.sourcemanager.aidl.ISourceBluetoothCallback
            public void onState(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onState(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISourceBluetoothCallback iSourceBluetoothCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iSourceBluetoothCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSourceBluetoothCallback;
            return true;
        }

        public static ISourceBluetoothCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
