package com.hzbhd.setting.proxy.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public interface ISettingsCallBack extends IInterface {

    public static class Default implements ISettingsCallBack {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.hzbhd.setting.proxy.aidl.ISettingsCallBack
        public void onSettingsBytes(int i, int i2, byte[] bArr) throws RemoteException {
        }

        @Override // com.hzbhd.setting.proxy.aidl.ISettingsCallBack
        public void onSettingsInt(int i, int i2, int i3) throws RemoteException {
        }

        @Override // com.hzbhd.setting.proxy.aidl.ISettingsCallBack
        public void onSettingsString(int i, int i2, String str) throws RemoteException {
        }
    }

    void onSettingsBytes(int i, int i2, byte[] bArr) throws RemoteException;

    void onSettingsInt(int i, int i2, int i3) throws RemoteException;

    void onSettingsString(int i, int i2, String str) throws RemoteException;

    public static abstract class Stub extends Binder implements ISettingsCallBack {
        private static final String DESCRIPTOR = "com.hzbhd.setting.proxy.aidl.ISettingsCallBack";
        static final int TRANSACTION_onSettingsBytes = 2;
        static final int TRANSACTION_onSettingsInt = 1;
        static final int TRANSACTION_onSettingsString = 3;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISettingsCallBack asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ISettingsCallBack)) {
                return (ISettingsCallBack) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onSettingsInt(parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onSettingsBytes(parcel.readInt(), parcel.readInt(), parcel.createByteArray());
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
            onSettingsString(parcel.readInt(), parcel.readInt(), parcel.readString());
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements ISettingsCallBack {
            public static ISettingsCallBack sDefaultImpl;
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

            @Override // com.hzbhd.setting.proxy.aidl.ISettingsCallBack
            public void onSettingsInt(int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSettingsInt(i, i2, i3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.setting.proxy.aidl.ISettingsCallBack
            public void onSettingsBytes(int i, int i2, byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeByteArray(bArr);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSettingsBytes(i, i2, bArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.setting.proxy.aidl.ISettingsCallBack
            public void onSettingsString(int i, int i2, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSettingsString(i, i2, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISettingsCallBack iSettingsCallBack) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iSettingsCallBack == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSettingsCallBack;
            return true;
        }

        public static ISettingsCallBack getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
