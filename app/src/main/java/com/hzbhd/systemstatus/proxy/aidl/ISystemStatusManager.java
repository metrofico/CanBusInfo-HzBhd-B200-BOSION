package com.hzbhd.systemstatus.proxy.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.hzbhd.systemstatus.proxy.aidl.IServiceConnectCallback;

/* loaded from: classes2.dex */
public interface ISystemStatusManager extends IInterface {

    public static class Default implements ISystemStatusManager {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.hzbhd.systemstatus.proxy.aidl.ISystemStatusManager
        public String getServiceData(int i, int i2) throws RemoteException {
            return null;
        }

        @Override // com.hzbhd.systemstatus.proxy.aidl.ISystemStatusManager
        public void regitsterServiceConnectCallback(int i, IServiceConnectCallback iServiceConnectCallback) throws RemoteException {
        }

        @Override // com.hzbhd.systemstatus.proxy.aidl.ISystemStatusManager
        public void setServiceData(int i, int i2, String str) throws RemoteException {
        }

        @Override // com.hzbhd.systemstatus.proxy.aidl.ISystemStatusManager
        public void unregisterServiceConnectCallback(int i, IServiceConnectCallback iServiceConnectCallback) throws RemoteException {
        }
    }

    String getServiceData(int i, int i2) throws RemoteException;

    void regitsterServiceConnectCallback(int i, IServiceConnectCallback iServiceConnectCallback) throws RemoteException;

    void setServiceData(int i, int i2, String str) throws RemoteException;

    void unregisterServiceConnectCallback(int i, IServiceConnectCallback iServiceConnectCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements ISystemStatusManager {
        private static final String DESCRIPTOR = "com.hzbhd.systemstatus.proxy.aidl.ISystemStatusManager";
        static final int TRANSACTION_getServiceData = 4;
        static final int TRANSACTION_regitsterServiceConnectCallback = 1;
        static final int TRANSACTION_setServiceData = 3;
        static final int TRANSACTION_unregisterServiceConnectCallback = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISystemStatusManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ISystemStatusManager)) {
                return (ISystemStatusManager) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                regitsterServiceConnectCallback(parcel.readInt(), IServiceConnectCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                unregisterServiceConnectCallback(parcel.readInt(), IServiceConnectCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                setServiceData(parcel.readInt(), parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            if (i != 4) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            String serviceData = getServiceData(parcel.readInt(), parcel.readInt());
            parcel2.writeNoException();
            parcel2.writeString(serviceData);
            return true;
        }

        private static class Proxy implements ISystemStatusManager {
            public static ISystemStatusManager sDefaultImpl;
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

            @Override // com.hzbhd.systemstatus.proxy.aidl.ISystemStatusManager
            public void regitsterServiceConnectCallback(int i, IServiceConnectCallback iServiceConnectCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iServiceConnectCallback != null ? iServiceConnectCallback.asBinder() : null);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().regitsterServiceConnectCallback(i, iServiceConnectCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.systemstatus.proxy.aidl.ISystemStatusManager
            public void unregisterServiceConnectCallback(int i, IServiceConnectCallback iServiceConnectCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iServiceConnectCallback != null ? iServiceConnectCallback.asBinder() : null);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterServiceConnectCallback(i, iServiceConnectCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.systemstatus.proxy.aidl.ISystemStatusManager
            public void setServiceData(int i, int i2, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setServiceData(i, i2, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.systemstatus.proxy.aidl.ISystemStatusManager
            public String getServiceData(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getServiceData(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISystemStatusManager iSystemStatusManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iSystemStatusManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSystemStatusManager;
            return true;
        }

        public static ISystemStatusManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
