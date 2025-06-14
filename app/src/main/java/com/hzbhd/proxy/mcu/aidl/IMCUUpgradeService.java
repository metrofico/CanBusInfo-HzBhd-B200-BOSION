package com.hzbhd.proxy.mcu.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.hzbhd.proxy.mcu.aidl.IMCUUpgradeCallback;

/* loaded from: classes2.dex */
public interface IMCUUpgradeService extends IInterface {

    public static class Default implements IMCUUpgradeService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUUpgradeService
        public void registerMCUUpgradeCallback(IMCUUpgradeCallback iMCUUpgradeCallback) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUUpgradeService
        public int reqUpgrade(String str) throws RemoteException {
            return 0;
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUUpgradeService
        public int sendUpgradeData(byte[] bArr, int i, int i2, int i3) throws RemoteException {
            return 0;
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUUpgradeService
        public void unRegisterMCUUpgradeCallback(IMCUUpgradeCallback iMCUUpgradeCallback) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUUpgradeService
        public byte[] updateFlashData(byte[] bArr) throws RemoteException {
            return null;
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUUpgradeService
        public int upgradeEnd(int i) throws RemoteException {
            return 0;
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUUpgradeService
        public boolean upgradeStart(boolean z, boolean z2, int i, int i2, int i3, int i4) throws RemoteException {
            return false;
        }
    }

    void registerMCUUpgradeCallback(IMCUUpgradeCallback iMCUUpgradeCallback) throws RemoteException;

    int reqUpgrade(String str) throws RemoteException;

    int sendUpgradeData(byte[] bArr, int i, int i2, int i3) throws RemoteException;

    void unRegisterMCUUpgradeCallback(IMCUUpgradeCallback iMCUUpgradeCallback) throws RemoteException;

    byte[] updateFlashData(byte[] bArr) throws RemoteException;

    int upgradeEnd(int i) throws RemoteException;

    boolean upgradeStart(boolean z, boolean z2, int i, int i2, int i3, int i4) throws RemoteException;

    public static abstract class Stub extends Binder implements IMCUUpgradeService {
        private static final String DESCRIPTOR = "com.hzbhd.proxy.mcu.aidl.IMCUUpgradeService";
        static final int TRANSACTION_registerMCUUpgradeCallback = 1;
        static final int TRANSACTION_reqUpgrade = 5;
        static final int TRANSACTION_sendUpgradeData = 4;
        static final int TRANSACTION_unRegisterMCUUpgradeCallback = 2;
        static final int TRANSACTION_updateFlashData = 7;
        static final int TRANSACTION_upgradeEnd = 6;
        static final int TRANSACTION_upgradeStart = 3;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMCUUpgradeService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IMCUUpgradeService)) {
                return (IMCUUpgradeService) iInterfaceQueryLocalInterface;
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
                    registerMCUUpgradeCallback(IMCUUpgradeCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    unRegisterMCUUpgradeCallback(IMCUUpgradeCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zUpgradeStart = upgradeStart(parcel.readInt() != 0, parcel.readInt() != 0, parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zUpgradeStart ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iSendUpgradeData = sendUpgradeData(parcel.createByteArray(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iSendUpgradeData);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iReqUpgrade = reqUpgrade(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iReqUpgrade);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iUpgradeEnd = upgradeEnd(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iUpgradeEnd);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    byte[] bArrUpdateFlashData = updateFlashData(parcel.createByteArray());
                    parcel2.writeNoException();
                    parcel2.writeByteArray(bArrUpdateFlashData);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IMCUUpgradeService {
            public static IMCUUpgradeService sDefaultImpl;
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

            @Override // com.hzbhd.proxy.mcu.aidl.IMCUUpgradeService
            public void registerMCUUpgradeCallback(IMCUUpgradeCallback iMCUUpgradeCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMCUUpgradeCallback != null ? iMCUUpgradeCallback.asBinder() : null);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerMCUUpgradeCallback(iMCUUpgradeCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.mcu.aidl.IMCUUpgradeService
            public void unRegisterMCUUpgradeCallback(IMCUUpgradeCallback iMCUUpgradeCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMCUUpgradeCallback != null ? iMCUUpgradeCallback.asBinder() : null);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unRegisterMCUUpgradeCallback(iMCUUpgradeCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.mcu.aidl.IMCUUpgradeService
            public boolean upgradeStart(boolean z, boolean z2, int i, int i2, int i3, int i4) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(z2 ? 1 : 0);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        boolean zUpgradeStart = Stub.getDefaultImpl().upgradeStart(z, z2, i, i2, i3, i4);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return zUpgradeStart;
                    }
                    parcelObtain2.readException();
                    boolean z3 = parcelObtain2.readInt() != 0;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    return z3;
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // com.hzbhd.proxy.mcu.aidl.IMCUUpgradeService
            public int sendUpgradeData(byte[] bArr, int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByteArray(bArr);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().sendUpgradeData(bArr, i, i2, i3);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.mcu.aidl.IMCUUpgradeService
            public int reqUpgrade(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reqUpgrade(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.mcu.aidl.IMCUUpgradeService
            public int upgradeEnd(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().upgradeEnd(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.mcu.aidl.IMCUUpgradeService
            public byte[] updateFlashData(byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByteArray(bArr);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().updateFlashData(bArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createByteArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMCUUpgradeService iMCUUpgradeService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iMCUUpgradeService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iMCUUpgradeService;
            return true;
        }

        public static IMCUUpgradeService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
