package com.hzbhd.proxy.keydispatcher.aidl;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public interface IKeyDispatcherCallback extends IInterface {

    public static class Default implements IKeyDispatcherCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.hzbhd.proxy.keydispatcher.aidl.IKeyDispatcherCallback
        public boolean onKeyEvent(int i, int i2, int i3, Bundle bundle) throws RemoteException {
            return false;
        }
    }

    boolean onKeyEvent(int i, int i2, int i3, Bundle bundle) throws RemoteException;

    public static abstract class Stub extends Binder implements IKeyDispatcherCallback {
        private static final String DESCRIPTOR = "com.hzbhd.proxy.keydispatcher.aidl.IKeyDispatcherCallback";
        static final int TRANSACTION_onKeyEvent = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IKeyDispatcherCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IKeyDispatcherCallback)) {
                return (IKeyDispatcherCallback) iInterfaceQueryLocalInterface;
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
            boolean zOnKeyEvent = onKeyEvent(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
            parcel2.writeNoException();
            parcel2.writeInt(zOnKeyEvent ? 1 : 0);
            return true;
        }

        private static class Proxy implements IKeyDispatcherCallback {
            public static IKeyDispatcherCallback sDefaultImpl;
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

            @Override // com.hzbhd.proxy.keydispatcher.aidl.IKeyDispatcherCallback
            public boolean onKeyEvent(int i, int i2, int i3, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().onKeyEvent(i, i2, i3, bundle);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IKeyDispatcherCallback iKeyDispatcherCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iKeyDispatcherCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iKeyDispatcherCallback;
            return true;
        }

        public static IKeyDispatcherCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
