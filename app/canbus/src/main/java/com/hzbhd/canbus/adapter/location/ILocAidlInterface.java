package com.hzbhd.canbus.adapter.location;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.hzbhd.canbus.adapter.location.LocationCallback;

/* loaded from: classes.dex */
public interface ILocAidlInterface extends IInterface {

    public static class Default implements ILocAidlInterface {
        @Override // com.hzbhd.canbus.adapter.location.ILocAidlInterface
        public void addCallBack(LocationCallback locationCallback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.hzbhd.canbus.adapter.location.ILocAidlInterface
        public int getSpeed(int i) throws RemoteException {
            return 0;
        }

        @Override // com.hzbhd.canbus.adapter.location.ILocAidlInterface
        public void removeCallBack(LocationCallback locationCallback) throws RemoteException {
        }
    }

    void addCallBack(LocationCallback locationCallback) throws RemoteException;

    int getSpeed(int i) throws RemoteException;

    void removeCallBack(LocationCallback locationCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements ILocAidlInterface {
        private static final String DESCRIPTOR = "com.hzbhd.canbus.adapter.location.ILocAidlInterface";
        static final int TRANSACTION_addCallBack = 2;
        static final int TRANSACTION_getSpeed = 1;
        static final int TRANSACTION_removeCallBack = 3;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ILocAidlInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ILocAidlInterface)) {
                return (ILocAidlInterface) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                int speed = getSpeed(parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeInt(speed);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                addCallBack(LocationCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            if (i != 3) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            removeCallBack(LocationCallback.Stub.asInterface(parcel.readStrongBinder()));
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements ILocAidlInterface {
            public static ILocAidlInterface sDefaultImpl;
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

            @Override // com.hzbhd.canbus.adapter.location.ILocAidlInterface
            public int getSpeed(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSpeed(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.canbus.adapter.location.ILocAidlInterface
            public void addCallBack(LocationCallback locationCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(locationCallback != null ? locationCallback.asBinder() : null);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addCallBack(locationCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.canbus.adapter.location.ILocAidlInterface
            public void removeCallBack(LocationCallback locationCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(locationCallback != null ? locationCallback.asBinder() : null);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeCallBack(locationCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ILocAidlInterface iLocAidlInterface) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iLocAidlInterface == null) {
                return false;
            }
            Proxy.sDefaultImpl = iLocAidlInterface;
            return true;
        }

        public static ILocAidlInterface getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
