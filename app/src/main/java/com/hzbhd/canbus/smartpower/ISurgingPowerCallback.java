package com.hzbhd.canbus.smartpower;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public interface ISurgingPowerCallback extends IInterface {

    public static class Default implements ISurgingPowerCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.hzbhd.canbus.smartpower.ISurgingPowerCallback
        public void notifySurgingPower(byte[] bArr) throws RemoteException {
        }
    }

    void notifySurgingPower(byte[] bArr) throws RemoteException;

    public static abstract class Stub extends Binder implements ISurgingPowerCallback {
        private static final String DESCRIPTOR = "com.hzbhd.canbus.smartpower.ISurgingPowerCallback";
        static final int TRANSACTION_notifySurgingPower = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISurgingPowerCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ISurgingPowerCallback)) {
                return (ISurgingPowerCallback) iInterfaceQueryLocalInterface;
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
            notifySurgingPower(parcel.createByteArray());
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements ISurgingPowerCallback {
            public static ISurgingPowerCallback sDefaultImpl;
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

            @Override // com.hzbhd.canbus.smartpower.ISurgingPowerCallback
            public void notifySurgingPower(byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByteArray(bArr);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifySurgingPower(bArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISurgingPowerCallback iSurgingPowerCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iSurgingPowerCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSurgingPowerCallback;
            return true;
        }

        public static ISurgingPowerCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
