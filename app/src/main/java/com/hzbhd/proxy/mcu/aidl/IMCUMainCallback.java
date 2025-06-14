package com.hzbhd.proxy.mcu.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public interface IMCUMainCallback extends IInterface {

    public static class Default implements IMCUMainCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainCallback
        public void mcuInit(byte b, boolean z, boolean z2) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainCallback
        public void notifyCanboxVersion(String str) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainCallback
        public void notifyHardwareVersion(String str, String str2, String str3, String str4) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainCallback
        public void notifyMCUVersion(String str, String str2, String str3) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainCallback
        public void notifyPowerStatus(int i) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainCallback
        public void notifyScreenVersion(String str) throws RemoteException {
        }
    }

    void mcuInit(byte b, boolean z, boolean z2) throws RemoteException;

    void notifyCanboxVersion(String str) throws RemoteException;

    void notifyHardwareVersion(String str, String str2, String str3, String str4) throws RemoteException;

    void notifyMCUVersion(String str, String str2, String str3) throws RemoteException;

    void notifyPowerStatus(int i) throws RemoteException;

    void notifyScreenVersion(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IMCUMainCallback {
        private static final String DESCRIPTOR = "com.hzbhd.proxy.mcu.aidl.IMCUMainCallback";
        static final int TRANSACTION_mcuInit = 1;
        static final int TRANSACTION_notifyCanboxVersion = 4;
        static final int TRANSACTION_notifyHardwareVersion = 3;
        static final int TRANSACTION_notifyMCUVersion = 2;
        static final int TRANSACTION_notifyPowerStatus = 6;
        static final int TRANSACTION_notifyScreenVersion = 5;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMCUMainCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IMCUMainCallback)) {
                return (IMCUMainCallback) iInterfaceQueryLocalInterface;
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
                    mcuInit(parcel.readByte(), parcel.readInt() != 0, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyMCUVersion(parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyHardwareVersion(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyCanboxVersion(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyScreenVersion(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyPowerStatus(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IMCUMainCallback {
            public static IMCUMainCallback sDefaultImpl;
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

            @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainCallback
            public void mcuInit(byte b, boolean z, boolean z2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByte(b);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(z2 ? 1 : 0);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().mcuInit(b, z, z2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainCallback
            public void notifyMCUVersion(String str, String str2, String str3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyMCUVersion(str, str2, str3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainCallback
            public void notifyHardwareVersion(String str, String str2, String str3, String str4) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeString(str4);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyHardwareVersion(str, str2, str3, str4);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainCallback
            public void notifyCanboxVersion(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyCanboxVersion(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainCallback
            public void notifyScreenVersion(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyScreenVersion(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainCallback
            public void notifyPowerStatus(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyPowerStatus(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMCUMainCallback iMCUMainCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iMCUMainCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iMCUMainCallback;
            return true;
        }

        public static IMCUMainCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
