package com.hzbhd.canbus.adapter.park;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.hzbhd.canbus.adapter.park.IParkCallBackInterface;

/* loaded from: classes.dex */
public interface IParkInterface extends IInterface {

    public static class Default implements IParkInterface {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.hzbhd.canbus.adapter.park.IParkInterface
        public void registerCallBack(IParkCallBackInterface iParkCallBackInterface) throws RemoteException {
        }

        @Override // com.hzbhd.canbus.adapter.park.IParkInterface
        public void sendPanoramicParkKey(int i) throws RemoteException {
        }

        @Override // com.hzbhd.canbus.adapter.park.IParkInterface
        public void sendPanoramicParkOn(boolean z) throws RemoteException {
        }

        @Override // com.hzbhd.canbus.adapter.park.IParkInterface
        public void sendPanoramicParkPos(int i, int i2, int i3) throws RemoteException {
        }

        @Override // com.hzbhd.canbus.adapter.park.IParkInterface
        public void sendPanoramicParkWH(int i, int i2) throws RemoteException {
        }

        @Override // com.hzbhd.canbus.adapter.park.IParkInterface
        public void unRegisterCallBack() throws RemoteException {
        }
    }

    void registerCallBack(IParkCallBackInterface iParkCallBackInterface) throws RemoteException;

    void sendPanoramicParkKey(int i) throws RemoteException;

    void sendPanoramicParkOn(boolean z) throws RemoteException;

    void sendPanoramicParkPos(int i, int i2, int i3) throws RemoteException;

    void sendPanoramicParkWH(int i, int i2) throws RemoteException;

    void unRegisterCallBack() throws RemoteException;

    public static abstract class Stub extends Binder implements IParkInterface {
        private static final String DESCRIPTOR = "com.hzbhd.canbus.adapter.park.IParkInterface";
        static final int TRANSACTION_registerCallBack = 1;
        static final int TRANSACTION_sendPanoramicParkKey = 4;
        static final int TRANSACTION_sendPanoramicParkOn = 3;
        static final int TRANSACTION_sendPanoramicParkPos = 6;
        static final int TRANSACTION_sendPanoramicParkWH = 5;
        static final int TRANSACTION_unRegisterCallBack = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IParkInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IParkInterface)) {
                return (IParkInterface) iInterfaceQueryLocalInterface;
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
                    registerCallBack(IParkCallBackInterface.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    unRegisterCallBack();
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    sendPanoramicParkOn(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    sendPanoramicParkKey(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    sendPanoramicParkWH(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    sendPanoramicParkPos(parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IParkInterface {
            public static IParkInterface sDefaultImpl;
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

            @Override // com.hzbhd.canbus.adapter.park.IParkInterface
            public void registerCallBack(IParkCallBackInterface iParkCallBackInterface) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iParkCallBackInterface != null ? iParkCallBackInterface.asBinder() : null);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerCallBack(iParkCallBackInterface);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.canbus.adapter.park.IParkInterface
            public void unRegisterCallBack() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unRegisterCallBack();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.canbus.adapter.park.IParkInterface
            public void sendPanoramicParkOn(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendPanoramicParkOn(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.canbus.adapter.park.IParkInterface
            public void sendPanoramicParkKey(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendPanoramicParkKey(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.canbus.adapter.park.IParkInterface
            public void sendPanoramicParkWH(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendPanoramicParkWH(i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.canbus.adapter.park.IParkInterface
            public void sendPanoramicParkPos(int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendPanoramicParkPos(i, i2, i3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IParkInterface iParkInterface) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iParkInterface == null) {
                return false;
            }
            Proxy.sDefaultImpl = iParkInterface;
            return true;
        }

        public static IParkInterface getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
