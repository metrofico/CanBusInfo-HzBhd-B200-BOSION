package com.hzbhd.proxy.mcu.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public interface IMCUUpgradeCallback extends IInterface {

    public static class Default implements IMCUUpgradeCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUUpgradeCallback
        public void notifyMCUUpgradeEndCheckStatus(String str) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUUpgradeCallback
        public void notifyMCUUpgradeSendDataSeq(int i) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUUpgradeCallback
        public void notifyMCUUpgradeStartCheckStatus(boolean z, String str) throws RemoteException {
        }
    }

    void notifyMCUUpgradeEndCheckStatus(String str) throws RemoteException;

    void notifyMCUUpgradeSendDataSeq(int i) throws RemoteException;

    void notifyMCUUpgradeStartCheckStatus(boolean z, String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IMCUUpgradeCallback {
        private static final String DESCRIPTOR = "com.hzbhd.proxy.mcu.aidl.IMCUUpgradeCallback";
        static final int TRANSACTION_notifyMCUUpgradeEndCheckStatus = 2;
        static final int TRANSACTION_notifyMCUUpgradeSendDataSeq = 3;
        static final int TRANSACTION_notifyMCUUpgradeStartCheckStatus = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMCUUpgradeCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IMCUUpgradeCallback)) {
                return (IMCUUpgradeCallback) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                notifyMCUUpgradeStartCheckStatus(parcel.readInt() != 0, parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                notifyMCUUpgradeEndCheckStatus(parcel.readString());
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
            notifyMCUUpgradeSendDataSeq(parcel.readInt());
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements IMCUUpgradeCallback {
            public static IMCUUpgradeCallback sDefaultImpl;
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

            @Override // com.hzbhd.proxy.mcu.aidl.IMCUUpgradeCallback
            public void notifyMCUUpgradeStartCheckStatus(boolean z, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyMCUUpgradeStartCheckStatus(z, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.mcu.aidl.IMCUUpgradeCallback
            public void notifyMCUUpgradeEndCheckStatus(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyMCUUpgradeEndCheckStatus(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.mcu.aidl.IMCUUpgradeCallback
            public void notifyMCUUpgradeSendDataSeq(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyMCUUpgradeSendDataSeq(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMCUUpgradeCallback iMCUUpgradeCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iMCUUpgradeCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iMCUUpgradeCallback;
            return true;
        }

        public static IMCUUpgradeCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
