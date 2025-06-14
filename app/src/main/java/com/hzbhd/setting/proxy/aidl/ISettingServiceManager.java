package com.hzbhd.setting.proxy.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.hzbhd.setting.proxy.aidl.IModuleCallBack;
import com.hzbhd.setting.proxy.aidl.ISettingsCallBack;

/* loaded from: classes2.dex */
public interface ISettingServiceManager extends IInterface {

    public static class Default implements ISettingServiceManager {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.hzbhd.setting.proxy.aidl.ISettingServiceManager
        public byte[] getBytes(int i, int i2, int i3) throws RemoteException {
            return null;
        }

        @Override // com.hzbhd.setting.proxy.aidl.ISettingServiceManager
        public String getConfig(int i) throws RemoteException {
            return null;
        }

        @Override // com.hzbhd.setting.proxy.aidl.ISettingServiceManager
        public int getInt(int i, int i2, int i3) throws RemoteException {
            return 0;
        }

        @Override // com.hzbhd.setting.proxy.aidl.ISettingServiceManager
        public String getString(int i, int i2, int i3) throws RemoteException {
            return null;
        }

        @Override // com.hzbhd.setting.proxy.aidl.ISettingServiceManager
        public void notifyBytes(int i, int i2, int i3, byte[] bArr) throws RemoteException {
        }

        @Override // com.hzbhd.setting.proxy.aidl.ISettingServiceManager
        public void notifyConfig(int i, String str) throws RemoteException {
        }

        @Override // com.hzbhd.setting.proxy.aidl.ISettingServiceManager
        public void notifyInt(int i, int i2, int i3, int i4) throws RemoteException {
        }

        @Override // com.hzbhd.setting.proxy.aidl.ISettingServiceManager
        public void notifyString(int i, int i2, int i3, String str) throws RemoteException {
        }

        @Override // com.hzbhd.setting.proxy.aidl.ISettingServiceManager
        public void registerModuleListener(int i, IModuleCallBack iModuleCallBack) throws RemoteException {
        }

        @Override // com.hzbhd.setting.proxy.aidl.ISettingServiceManager
        public void registerSettingsListener(int i, ISettingsCallBack iSettingsCallBack) throws RemoteException {
        }

        @Override // com.hzbhd.setting.proxy.aidl.ISettingServiceManager
        public void setBytes(int i, int i2, int i3, byte[] bArr) throws RemoteException {
        }

        @Override // com.hzbhd.setting.proxy.aidl.ISettingServiceManager
        public void setInt(int i, int i2, int i3, int i4) throws RemoteException {
        }

        @Override // com.hzbhd.setting.proxy.aidl.ISettingServiceManager
        public void setString(int i, int i2, int i3, String str) throws RemoteException {
        }

        @Override // com.hzbhd.setting.proxy.aidl.ISettingServiceManager
        public void unregisterModuleListener(int i, IModuleCallBack iModuleCallBack) throws RemoteException {
        }

        @Override // com.hzbhd.setting.proxy.aidl.ISettingServiceManager
        public void unregisterSettingsListener(int i, ISettingsCallBack iSettingsCallBack) throws RemoteException {
        }
    }

    byte[] getBytes(int i, int i2, int i3) throws RemoteException;

    String getConfig(int i) throws RemoteException;

    int getInt(int i, int i2, int i3) throws RemoteException;

    String getString(int i, int i2, int i3) throws RemoteException;

    void notifyBytes(int i, int i2, int i3, byte[] bArr) throws RemoteException;

    void notifyConfig(int i, String str) throws RemoteException;

    void notifyInt(int i, int i2, int i3, int i4) throws RemoteException;

    void notifyString(int i, int i2, int i3, String str) throws RemoteException;

    void registerModuleListener(int i, IModuleCallBack iModuleCallBack) throws RemoteException;

    void registerSettingsListener(int i, ISettingsCallBack iSettingsCallBack) throws RemoteException;

    void setBytes(int i, int i2, int i3, byte[] bArr) throws RemoteException;

    void setInt(int i, int i2, int i3, int i4) throws RemoteException;

    void setString(int i, int i2, int i3, String str) throws RemoteException;

    void unregisterModuleListener(int i, IModuleCallBack iModuleCallBack) throws RemoteException;

    void unregisterSettingsListener(int i, ISettingsCallBack iSettingsCallBack) throws RemoteException;

    public static abstract class Stub extends Binder implements ISettingServiceManager {
        private static final String DESCRIPTOR = "com.hzbhd.setting.proxy.aidl.ISettingServiceManager";
        static final int TRANSACTION_getBytes = 7;
        static final int TRANSACTION_getConfig = 14;
        static final int TRANSACTION_getInt = 6;
        static final int TRANSACTION_getString = 8;
        static final int TRANSACTION_notifyBytes = 12;
        static final int TRANSACTION_notifyConfig = 15;
        static final int TRANSACTION_notifyInt = 11;
        static final int TRANSACTION_notifyString = 13;
        static final int TRANSACTION_registerModuleListener = 9;
        static final int TRANSACTION_registerSettingsListener = 1;
        static final int TRANSACTION_setBytes = 4;
        static final int TRANSACTION_setInt = 3;
        static final int TRANSACTION_setString = 5;
        static final int TRANSACTION_unregisterModuleListener = 10;
        static final int TRANSACTION_unregisterSettingsListener = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISettingServiceManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ISettingServiceManager)) {
                return (ISettingServiceManager) iInterfaceQueryLocalInterface;
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
                    registerSettingsListener(parcel.readInt(), ISettingsCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterSettingsListener(parcel.readInt(), ISettingsCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    setInt(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    setBytes(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.createByteArray());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    setString(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    int i3 = getInt(parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    byte[] bytes = getBytes(parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeByteArray(bytes);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    String string = getString(parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(string);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerModuleListener(parcel.readInt(), IModuleCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterModuleListener(parcel.readInt(), IModuleCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyInt(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyBytes(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.createByteArray());
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyString(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    String config = getConfig(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(config);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyConfig(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ISettingServiceManager {
            public static ISettingServiceManager sDefaultImpl;
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

            @Override // com.hzbhd.setting.proxy.aidl.ISettingServiceManager
            public void registerSettingsListener(int i, ISettingsCallBack iSettingsCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iSettingsCallBack != null ? iSettingsCallBack.asBinder() : null);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerSettingsListener(i, iSettingsCallBack);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.setting.proxy.aidl.ISettingServiceManager
            public void unregisterSettingsListener(int i, ISettingsCallBack iSettingsCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iSettingsCallBack != null ? iSettingsCallBack.asBinder() : null);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterSettingsListener(i, iSettingsCallBack);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.setting.proxy.aidl.ISettingServiceManager
            public void setInt(int i, int i2, int i3, int i4) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setInt(i, i2, i3, i4);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.setting.proxy.aidl.ISettingServiceManager
            public void setBytes(int i, int i2, int i3, byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeByteArray(bArr);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setBytes(i, i2, i3, bArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.setting.proxy.aidl.ISettingServiceManager
            public void setString(int i, int i2, int i3, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setString(i, i2, i3, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.setting.proxy.aidl.ISettingServiceManager
            public int getInt(int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInt(i, i2, i3);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.setting.proxy.aidl.ISettingServiceManager
            public byte[] getBytes(int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBytes(i, i2, i3);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createByteArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.setting.proxy.aidl.ISettingServiceManager
            public String getString(int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getString(i, i2, i3);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.setting.proxy.aidl.ISettingServiceManager
            public void registerModuleListener(int i, IModuleCallBack iModuleCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iModuleCallBack != null ? iModuleCallBack.asBinder() : null);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerModuleListener(i, iModuleCallBack);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.setting.proxy.aidl.ISettingServiceManager
            public void unregisterModuleListener(int i, IModuleCallBack iModuleCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iModuleCallBack != null ? iModuleCallBack.asBinder() : null);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterModuleListener(i, iModuleCallBack);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.setting.proxy.aidl.ISettingServiceManager
            public void notifyInt(int i, int i2, int i3, int i4) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyInt(i, i2, i3, i4);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.setting.proxy.aidl.ISettingServiceManager
            public void notifyBytes(int i, int i2, int i3, byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeByteArray(bArr);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyBytes(i, i2, i3, bArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.setting.proxy.aidl.ISettingServiceManager
            public void notifyString(int i, int i2, int i3, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyString(i, i2, i3, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.setting.proxy.aidl.ISettingServiceManager
            public String getConfig(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConfig(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.setting.proxy.aidl.ISettingServiceManager
            public void notifyConfig(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyConfig(i, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISettingServiceManager iSettingServiceManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iSettingServiceManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSettingServiceManager;
            return true;
        }

        public static ISettingServiceManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
