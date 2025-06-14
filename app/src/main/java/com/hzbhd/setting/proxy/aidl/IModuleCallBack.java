package com.hzbhd.setting.proxy.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public interface IModuleCallBack extends IInterface {

    public static class Default implements IModuleCallBack {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.hzbhd.setting.proxy.aidl.IModuleCallBack
        public byte[] getModuleBytes(int i, int i2) throws RemoteException {
            return null;
        }

        @Override // com.hzbhd.setting.proxy.aidl.IModuleCallBack
        public int getModuleInt(int i, int i2) throws RemoteException {
            return 0;
        }

        @Override // com.hzbhd.setting.proxy.aidl.IModuleCallBack
        public String getModuleString(int i, int i2) throws RemoteException {
            return null;
        }

        @Override // com.hzbhd.setting.proxy.aidl.IModuleCallBack
        public void onModuleBytes(int i, int i2, byte[] bArr) throws RemoteException {
        }

        @Override // com.hzbhd.setting.proxy.aidl.IModuleCallBack
        public void onModuleInt(int i, int i2, int i3) throws RemoteException {
        }

        @Override // com.hzbhd.setting.proxy.aidl.IModuleCallBack
        public void onModuleString(int i, int i2, String str) throws RemoteException {
        }
    }

    byte[] getModuleBytes(int i, int i2) throws RemoteException;

    int getModuleInt(int i, int i2) throws RemoteException;

    String getModuleString(int i, int i2) throws RemoteException;

    void onModuleBytes(int i, int i2, byte[] bArr) throws RemoteException;

    void onModuleInt(int i, int i2, int i3) throws RemoteException;

    void onModuleString(int i, int i2, String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IModuleCallBack {
        private static final String DESCRIPTOR = "com.hzbhd.setting.proxy.aidl.IModuleCallBack";
        static final int TRANSACTION_getModuleBytes = 5;
        static final int TRANSACTION_getModuleInt = 4;
        static final int TRANSACTION_getModuleString = 6;
        static final int TRANSACTION_onModuleBytes = 2;
        static final int TRANSACTION_onModuleInt = 1;
        static final int TRANSACTION_onModuleString = 3;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IModuleCallBack asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IModuleCallBack)) {
                return (IModuleCallBack) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    onModuleInt(parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    onModuleBytes(parcel.readInt(), parcel.readInt(), parcel.createByteArray());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    onModuleString(parcel.readInt(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    int moduleInt = getModuleInt(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(moduleInt);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    byte[] moduleBytes = getModuleBytes(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeByteArray(moduleBytes);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    String moduleString = getModuleString(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(moduleString);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IModuleCallBack {
            public static IModuleCallBack sDefaultImpl;
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

            @Override // com.hzbhd.setting.proxy.aidl.IModuleCallBack
            public void onModuleInt(int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onModuleInt(i, i2, i3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.setting.proxy.aidl.IModuleCallBack
            public void onModuleBytes(int i, int i2, byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeByteArray(bArr);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onModuleBytes(i, i2, bArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.setting.proxy.aidl.IModuleCallBack
            public void onModuleString(int i, int i2, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onModuleString(i, i2, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.setting.proxy.aidl.IModuleCallBack
            public int getModuleInt(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getModuleInt(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.setting.proxy.aidl.IModuleCallBack
            public byte[] getModuleBytes(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getModuleBytes(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createByteArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.setting.proxy.aidl.IModuleCallBack
            public String getModuleString(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getModuleString(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IModuleCallBack iModuleCallBack) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iModuleCallBack == null) {
                return false;
            }
            Proxy.sDefaultImpl = iModuleCallBack;
            return true;
        }

        public static IModuleCallBack getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
