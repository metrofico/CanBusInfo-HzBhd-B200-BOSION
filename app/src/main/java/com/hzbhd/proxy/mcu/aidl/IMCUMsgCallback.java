package com.hzbhd.proxy.mcu.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public interface IMCUMsgCallback extends IInterface {

    public static class Default implements IMCUMsgCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUMsgCallback
        public void onMsg(boolean z, byte[] bArr) throws RemoteException {
        }
    }

    void onMsg(boolean z, byte[] bArr) throws RemoteException;

    public static abstract class Stub extends Binder implements IMCUMsgCallback {
        private static final String DESCRIPTOR = "com.hzbhd.proxy.mcu.aidl.IMCUMsgCallback";
        static final int TRANSACTION_onMsg = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMCUMsgCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IMCUMsgCallback)) {
                return (IMCUMsgCallback) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            onMsg(parcel.readInt() != 0, parcel.createByteArray());
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements IMCUMsgCallback {
            public static IMCUMsgCallback sDefaultImpl;
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

            @Override // com.hzbhd.proxy.mcu.aidl.IMCUMsgCallback
            public void onMsg(boolean z, byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeByteArray(bArr);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMsg(z, bArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMCUMsgCallback iMCUMsgCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iMCUMsgCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iMCUMsgCallback;
            return true;
        }

        public static IMCUMsgCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
