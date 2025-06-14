package com.hzbhd.proxy.share.aidl;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.hzbhd.proxy.share.aidl.IShareBundleCallback;
import com.hzbhd.proxy.share.aidl.IShareIntCallback;
import com.hzbhd.proxy.share.aidl.IShareStringCallback;

/* loaded from: classes2.dex */
public interface IShareDataService extends IInterface {

    public static class Default implements IShareDataService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.hzbhd.proxy.share.aidl.IShareDataService
        public Bundle getBundle(String str) throws RemoteException {
            return null;
        }

        @Override // com.hzbhd.proxy.share.aidl.IShareDataService
        public int getInt(String str) throws RemoteException {
            return 0;
        }

        @Override // com.hzbhd.proxy.share.aidl.IShareDataService
        public String getString(String str) throws RemoteException {
            return null;
        }

        @Override // com.hzbhd.proxy.share.aidl.IShareDataService
        public Bundle registerShareBundleCallback(String str, IShareBundleCallback iShareBundleCallback) throws RemoteException {
            return null;
        }

        @Override // com.hzbhd.proxy.share.aidl.IShareDataService
        public int registerShareIntCallback(String str, IShareIntCallback iShareIntCallback) throws RemoteException {
            return 0;
        }

        @Override // com.hzbhd.proxy.share.aidl.IShareDataService
        public String registerShareStringCallback(String str, IShareStringCallback iShareStringCallback) throws RemoteException {
            return null;
        }

        @Override // com.hzbhd.proxy.share.aidl.IShareDataService
        public void reportBundle(String str, Bundle bundle) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.share.aidl.IShareDataService
        public void reportInt(String str, int i) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.share.aidl.IShareDataService
        public void reportOtherBundle(String str, Bundle bundle, int i) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.share.aidl.IShareDataService
        public void reportOtherInt(String str, int i, int i2) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.share.aidl.IShareDataService
        public void reportOtherString(String str, String str2, int i) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.share.aidl.IShareDataService
        public void reportString(String str, String str2) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.share.aidl.IShareDataService
        public void unregisterShareBundleCallback(String str, IShareBundleCallback iShareBundleCallback) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.share.aidl.IShareDataService
        public void unregisterShareIntCallback(String str, IShareIntCallback iShareIntCallback) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.share.aidl.IShareDataService
        public void unregisterShareStringCallback(String str, IShareStringCallback iShareStringCallback) throws RemoteException {
        }
    }

    Bundle getBundle(String str) throws RemoteException;

    int getInt(String str) throws RemoteException;

    String getString(String str) throws RemoteException;

    Bundle registerShareBundleCallback(String str, IShareBundleCallback iShareBundleCallback) throws RemoteException;

    int registerShareIntCallback(String str, IShareIntCallback iShareIntCallback) throws RemoteException;

    String registerShareStringCallback(String str, IShareStringCallback iShareStringCallback) throws RemoteException;

    void reportBundle(String str, Bundle bundle) throws RemoteException;

    void reportInt(String str, int i) throws RemoteException;

    void reportOtherBundle(String str, Bundle bundle, int i) throws RemoteException;

    void reportOtherInt(String str, int i, int i2) throws RemoteException;

    void reportOtherString(String str, String str2, int i) throws RemoteException;

    void reportString(String str, String str2) throws RemoteException;

    void unregisterShareBundleCallback(String str, IShareBundleCallback iShareBundleCallback) throws RemoteException;

    void unregisterShareIntCallback(String str, IShareIntCallback iShareIntCallback) throws RemoteException;

    void unregisterShareStringCallback(String str, IShareStringCallback iShareStringCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IShareDataService {
        private static final String DESCRIPTOR = "com.hzbhd.proxy.share.aidl.IShareDataService";
        static final int TRANSACTION_getBundle = 13;
        static final int TRANSACTION_getInt = 3;
        static final int TRANSACTION_getString = 8;
        static final int TRANSACTION_registerShareBundleCallback = 11;
        static final int TRANSACTION_registerShareIntCallback = 1;
        static final int TRANSACTION_registerShareStringCallback = 6;
        static final int TRANSACTION_reportBundle = 14;
        static final int TRANSACTION_reportInt = 4;
        static final int TRANSACTION_reportOtherBundle = 15;
        static final int TRANSACTION_reportOtherInt = 5;
        static final int TRANSACTION_reportOtherString = 10;
        static final int TRANSACTION_reportString = 9;
        static final int TRANSACTION_unregisterShareBundleCallback = 12;
        static final int TRANSACTION_unregisterShareIntCallback = 2;
        static final int TRANSACTION_unregisterShareStringCallback = 7;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IShareDataService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IShareDataService)) {
                return (IShareDataService) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            Bundle bundle;
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iRegisterShareIntCallback = registerShareIntCallback(parcel.readString(), IShareIntCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(iRegisterShareIntCallback);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterShareIntCallback(parcel.readString(), IShareIntCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    int i3 = getInt(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    reportInt(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    reportOtherInt(parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    String strRegisterShareStringCallback = registerShareStringCallback(parcel.readString(), IShareStringCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeString(strRegisterShareStringCallback);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterShareStringCallback(parcel.readString(), IShareStringCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    String string = getString(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(string);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    reportString(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    reportOtherString(parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    Bundle bundleRegisterShareBundleCallback = registerShareBundleCallback(parcel.readString(), IShareBundleCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (bundleRegisterShareBundleCallback != null) {
                        parcel2.writeInt(1);
                        bundleRegisterShareBundleCallback.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterShareBundleCallback(parcel.readString(), IShareBundleCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    Bundle bundle2 = getBundle(parcel.readString());
                    parcel2.writeNoException();
                    if (bundle2 != null) {
                        parcel2.writeInt(1);
                        bundle2.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    String string2 = parcel.readString();
                    bundle = parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null;
                    reportBundle(string2, bundle);
                    parcel2.writeNoException();
                    if (bundle != null) {
                        parcel2.writeInt(1);
                        bundle.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    String string3 = parcel.readString();
                    bundle = parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null;
                    reportOtherBundle(string3, bundle, parcel.readInt());
                    parcel2.writeNoException();
                    if (bundle != null) {
                        parcel2.writeInt(1);
                        bundle.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IShareDataService {
            public static IShareDataService sDefaultImpl;
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

            @Override // com.hzbhd.proxy.share.aidl.IShareDataService
            public int registerShareIntCallback(String str, IShareIntCallback iShareIntCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iShareIntCallback != null ? iShareIntCallback.asBinder() : null);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerShareIntCallback(str, iShareIntCallback);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.share.aidl.IShareDataService
            public void unregisterShareIntCallback(String str, IShareIntCallback iShareIntCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iShareIntCallback != null ? iShareIntCallback.asBinder() : null);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterShareIntCallback(str, iShareIntCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.share.aidl.IShareDataService
            public int getInt(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInt(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.share.aidl.IShareDataService
            public void reportInt(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reportInt(str, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.share.aidl.IShareDataService
            public void reportOtherInt(String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reportOtherInt(str, i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.share.aidl.IShareDataService
            public String registerShareStringCallback(String str, IShareStringCallback iShareStringCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iShareStringCallback != null ? iShareStringCallback.asBinder() : null);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerShareStringCallback(str, iShareStringCallback);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.share.aidl.IShareDataService
            public void unregisterShareStringCallback(String str, IShareStringCallback iShareStringCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iShareStringCallback != null ? iShareStringCallback.asBinder() : null);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterShareStringCallback(str, iShareStringCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.share.aidl.IShareDataService
            public String getString(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getString(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.share.aidl.IShareDataService
            public void reportString(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reportString(str, str2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.share.aidl.IShareDataService
            public void reportOtherString(String str, String str2, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reportOtherString(str, str2, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.share.aidl.IShareDataService
            public Bundle registerShareBundleCallback(String str, IShareBundleCallback iShareBundleCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iShareBundleCallback != null ? iShareBundleCallback.asBinder() : null);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerShareBundleCallback(str, iShareBundleCallback);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.share.aidl.IShareDataService
            public void unregisterShareBundleCallback(String str, IShareBundleCallback iShareBundleCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iShareBundleCallback != null ? iShareBundleCallback.asBinder() : null);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterShareBundleCallback(str, iShareBundleCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.share.aidl.IShareDataService
            public Bundle getBundle(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBundle(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.share.aidl.IShareDataService
            public void reportBundle(String str, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reportBundle(str, bundle);
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

            @Override // com.hzbhd.proxy.share.aidl.IShareDataService
            public void reportOtherBundle(String str, Bundle bundle, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reportOtherBundle(str, bundle, i);
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

        public static boolean setDefaultImpl(IShareDataService iShareDataService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iShareDataService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iShareDataService;
            return true;
        }

        public static IShareDataService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
