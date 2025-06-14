package com.hzbhd.proxy.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.hzbhd.proxy.callback.IDvrDataCallBack;

/* loaded from: classes2.dex */
public interface IDvrAidlInterface extends IInterface {

    public static class Default implements IDvrAidlInterface {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.hzbhd.proxy.aidl.IDvrAidlInterface
        public void registerOnDataReadCallback(IDvrDataCallBack iDvrDataCallBack) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.aidl.IDvrAidlInterface
        public void sendData(byte[] bArr) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.aidl.IDvrAidlInterface
        public void unregisterOnDataReadCallback(IDvrDataCallBack iDvrDataCallBack) throws RemoteException {
        }
    }

    void registerOnDataReadCallback(IDvrDataCallBack iDvrDataCallBack) throws RemoteException;

    void sendData(byte[] bArr) throws RemoteException;

    void unregisterOnDataReadCallback(IDvrDataCallBack iDvrDataCallBack) throws RemoteException;

    public static abstract class Stub extends Binder implements IDvrAidlInterface {
        private static final String DESCRIPTOR = "com.hzbhd.proxy.aidl.IDvrAidlInterface";
        static final int TRANSACTION_registerOnDataReadCallback = 1;
        static final int TRANSACTION_sendData = 3;
        static final int TRANSACTION_unregisterOnDataReadCallback = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDvrAidlInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IDvrAidlInterface)) {
                return (IDvrAidlInterface) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                registerOnDataReadCallback(IDvrDataCallBack.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                unregisterOnDataReadCallback(IDvrDataCallBack.Stub.asInterface(parcel.readStrongBinder()));
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
            sendData(parcel.createByteArray());
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements IDvrAidlInterface {
            public static IDvrAidlInterface sDefaultImpl;
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

            @Override // com.hzbhd.proxy.aidl.IDvrAidlInterface
            public void registerOnDataReadCallback(IDvrDataCallBack iDvrDataCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iDvrDataCallBack != null ? iDvrDataCallBack.asBinder() : null);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerOnDataReadCallback(iDvrDataCallBack);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.aidl.IDvrAidlInterface
            public void unregisterOnDataReadCallback(IDvrDataCallBack iDvrDataCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iDvrDataCallBack != null ? iDvrDataCallBack.asBinder() : null);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterOnDataReadCallback(iDvrDataCallBack);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.aidl.IDvrAidlInterface
            public void sendData(byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByteArray(bArr);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendData(bArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDvrAidlInterface iDvrAidlInterface) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iDvrAidlInterface == null) {
                return false;
            }
            Proxy.sDefaultImpl = iDvrAidlInterface;
            return true;
        }

        public static IDvrAidlInterface getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
