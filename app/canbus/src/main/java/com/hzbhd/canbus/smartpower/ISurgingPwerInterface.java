package com.hzbhd.canbus.smartpower;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.hzbhd.canbus.smartpower.ISurgingPowerCallback;

/* loaded from: classes2.dex */
public interface ISurgingPwerInterface extends IInterface {

    public static class Default implements ISurgingPwerInterface {
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
    }

    String registerCallBack(ISurgingPowerCallback iSurgingPowerCallback) throws RemoteException;

    void sendMsg(byte[] bArr) throws RemoteException;

    void unRegisterCallBack(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements ISurgingPwerInterface {
        private static final String DESCRIPTOR = "com.hzbhd.canbus.smartpower.ISurgingPwerInterface";
        static final int TRANSACTION_registerCallBack = 2;
        static final int TRANSACTION_sendMsg = 1;
        static final int TRANSACTION_unRegisterCallBack = 3;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISurgingPwerInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ISurgingPwerInterface)) {
                return (ISurgingPwerInterface) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                sendMsg(parcel.createByteArray());
                parcel2.writeNoException();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                String strRegisterCallBack = registerCallBack(ISurgingPowerCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeString(strRegisterCallBack);
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
            unRegisterCallBack(parcel.readString());
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements ISurgingPwerInterface {
            public static ISurgingPwerInterface sDefaultImpl;
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

            @Override // com.hzbhd.canbus.smartpower.ISurgingPwerInterface
            public void sendMsg(byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByteArray(bArr);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendMsg(bArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.canbus.smartpower.ISurgingPwerInterface
            public String registerCallBack(ISurgingPowerCallback iSurgingPowerCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iSurgingPowerCallback != null ? iSurgingPowerCallback.asBinder() : null);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerCallBack(iSurgingPowerCallback);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.canbus.smartpower.ISurgingPwerInterface
            public void unRegisterCallBack(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unRegisterCallBack(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISurgingPwerInterface iSurgingPwerInterface) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iSurgingPwerInterface == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSurgingPwerInterface;
            return true;
        }

        public static ISurgingPwerInterface getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
