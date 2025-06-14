package com.hzbhd.proxy.keydispatcher.aidl;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.hzbhd.proxy.keydispatcher.aidl.IKeyDispatcherCallback;

/* loaded from: classes2.dex */
public interface IKeyDispatcherService extends IInterface {

    public static class Default implements IKeyDispatcherService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.hzbhd.proxy.keydispatcher.aidl.IKeyDispatcherService
        public boolean registerKeyDispatcherCallback(int i, IKeyDispatcherCallback iKeyDispatcherCallback) throws RemoteException {
            return false;
        }

        @Override // com.hzbhd.proxy.keydispatcher.aidl.IKeyDispatcherService
        public boolean registerThirdApp(int i) throws RemoteException {
            return false;
        }

        @Override // com.hzbhd.proxy.keydispatcher.aidl.IKeyDispatcherService
        public void setKeyEvent(int i, int i2, int i3, Bundle bundle) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.keydispatcher.aidl.IKeyDispatcherService
        public boolean unregisterKeyDispatcherCallback(int i, IKeyDispatcherCallback iKeyDispatcherCallback) throws RemoteException {
            return false;
        }

        @Override // com.hzbhd.proxy.keydispatcher.aidl.IKeyDispatcherService
        public boolean unregisterThirdApp(int i) throws RemoteException {
            return false;
        }
    }

    boolean registerKeyDispatcherCallback(int i, IKeyDispatcherCallback iKeyDispatcherCallback) throws RemoteException;

    boolean registerThirdApp(int i) throws RemoteException;

    void setKeyEvent(int i, int i2, int i3, Bundle bundle) throws RemoteException;

    boolean unregisterKeyDispatcherCallback(int i, IKeyDispatcherCallback iKeyDispatcherCallback) throws RemoteException;

    boolean unregisterThirdApp(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IKeyDispatcherService {
        private static final String DESCRIPTOR = "com.hzbhd.proxy.keydispatcher.aidl.IKeyDispatcherService";
        static final int TRANSACTION_registerKeyDispatcherCallback = 1;
        static final int TRANSACTION_registerThirdApp = 3;
        static final int TRANSACTION_setKeyEvent = 5;
        static final int TRANSACTION_unregisterKeyDispatcherCallback = 2;
        static final int TRANSACTION_unregisterThirdApp = 4;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IKeyDispatcherService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IKeyDispatcherService)) {
                return (IKeyDispatcherService) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                boolean zRegisterKeyDispatcherCallback = registerKeyDispatcherCallback(parcel.readInt(), IKeyDispatcherCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(zRegisterKeyDispatcherCallback ? 1 : 0);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                boolean zUnregisterKeyDispatcherCallback = unregisterKeyDispatcherCallback(parcel.readInt(), IKeyDispatcherCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(zUnregisterKeyDispatcherCallback ? 1 : 0);
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                boolean zRegisterThirdApp = registerThirdApp(parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeInt(zRegisterThirdApp ? 1 : 0);
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                boolean zUnregisterThirdApp = unregisterThirdApp(parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeInt(zUnregisterThirdApp ? 1 : 0);
                return true;
            }
            if (i != 5) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            int i3 = parcel.readInt();
            int i4 = parcel.readInt();
            int i5 = parcel.readInt();
            Bundle bundle = parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null;
            setKeyEvent(i3, i4, i5, bundle);
            parcel2.writeNoException();
            if (bundle != null) {
                parcel2.writeInt(1);
                bundle.writeToParcel(parcel2, 1);
            } else {
                parcel2.writeInt(0);
            }
            return true;
        }

        private static class Proxy implements IKeyDispatcherService {
            public static IKeyDispatcherService sDefaultImpl;
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

            @Override // com.hzbhd.proxy.keydispatcher.aidl.IKeyDispatcherService
            public boolean registerKeyDispatcherCallback(int i, IKeyDispatcherCallback iKeyDispatcherCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iKeyDispatcherCallback != null ? iKeyDispatcherCallback.asBinder() : null);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerKeyDispatcherCallback(i, iKeyDispatcherCallback);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.keydispatcher.aidl.IKeyDispatcherService
            public boolean unregisterKeyDispatcherCallback(int i, IKeyDispatcherCallback iKeyDispatcherCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iKeyDispatcherCallback != null ? iKeyDispatcherCallback.asBinder() : null);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unregisterKeyDispatcherCallback(i, iKeyDispatcherCallback);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.keydispatcher.aidl.IKeyDispatcherService
            public boolean registerThirdApp(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerThirdApp(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.keydispatcher.aidl.IKeyDispatcherService
            public boolean unregisterThirdApp(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unregisterThirdApp(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.keydispatcher.aidl.IKeyDispatcherService
            public void setKeyEvent(int i, int i2, int i3, Bundle bundle) throws RemoteException {
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
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setKeyEvent(i, i2, i3, bundle);
                        return;
                    }
                    parcelObtain2.readException();
                    if (parcelObtain2.readInt() != 0) {
                        bundle.readFromParcel(parcelObtain2);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IKeyDispatcherService iKeyDispatcherService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iKeyDispatcherService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iKeyDispatcherService;
            return true;
        }

        public static IKeyDispatcherService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
