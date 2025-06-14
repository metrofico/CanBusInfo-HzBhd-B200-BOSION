package com.hzbhd.proxy.mcu.aidl;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.hzbhd.proxy.mcu.aidl.IMCUCanBoxControlCallback;
import com.hzbhd.proxy.mcu.aidl.IMCUMainCallback;
import com.hzbhd.proxy.mcu.aidl.IMCUMsgCallback;

/* loaded from: classes2.dex */
public interface IMCUMainService extends IInterface {

    public static class Default implements IMCUMainService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
        public void clearMessage(int i) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
        public void closeDevice() throws RemoteException {
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
        public int getCarBackState() throws RemoteException {
            return 0;
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
        public int getHardwareReset() throws RemoteException {
            return 0;
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
        public int getMcuHardware() throws RemoteException {
            return 0;
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
        public int getPowerOnType() throws RemoteException {
            return 0;
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
        public int getPowerStatus() throws RemoteException {
            return 0;
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
        public byte getProcotolVersion() throws RemoteException {
            return (byte) 0;
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
        public void getVersion() throws RemoteException {
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
        public Bundle initMCU() throws RemoteException {
            return null;
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
        public void initSystemReady() throws RemoteException {
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
        public boolean isInitMCU() throws RemoteException {
            return false;
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
        public void registerMCUCanboxCallback(IMCUCanBoxControlCallback iMCUCanBoxControlCallback) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
        public void registerMCUMainCallback(IMCUMainCallback iMCUMainCallback) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
        public void registerMcuMsgListener(IMCUMsgCallback iMCUMsgCallback) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
        public void requestPowerStatus(int i) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
        public void sendMCUCanboxData(int i, byte[] bArr) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
        public void sendMCUDebugData(byte[] bArr) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
        public void sendTestCmd(byte[] bArr) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
        public void setSendSleepStatus(boolean z) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
        public void setUpgradeStatus(boolean z) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
        public void startSendHeartBeatMsg() throws RemoteException {
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
        public void stopSendHeartBeatMsg() throws RemoteException {
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
        public void unregisterMCUCanboxCallback(IMCUCanBoxControlCallback iMCUCanBoxControlCallback) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
        public void unregisterMCUMainCallback(IMCUMainCallback iMCUMainCallback) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
        public void unregisterMcuMsgListener(IMCUMsgCallback iMCUMsgCallback) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
        public byte[] updateFlashData(byte[] bArr) throws RemoteException {
            return null;
        }
    }

    void clearMessage(int i) throws RemoteException;

    void closeDevice() throws RemoteException;

    int getCarBackState() throws RemoteException;

    int getHardwareReset() throws RemoteException;

    int getMcuHardware() throws RemoteException;

    int getPowerOnType() throws RemoteException;

    int getPowerStatus() throws RemoteException;

    byte getProcotolVersion() throws RemoteException;

    void getVersion() throws RemoteException;

    Bundle initMCU() throws RemoteException;

    void initSystemReady() throws RemoteException;

    boolean isInitMCU() throws RemoteException;

    void registerMCUCanboxCallback(IMCUCanBoxControlCallback iMCUCanBoxControlCallback) throws RemoteException;

    void registerMCUMainCallback(IMCUMainCallback iMCUMainCallback) throws RemoteException;

    void registerMcuMsgListener(IMCUMsgCallback iMCUMsgCallback) throws RemoteException;

    void requestPowerStatus(int i) throws RemoteException;

    void sendMCUCanboxData(int i, byte[] bArr) throws RemoteException;

    void sendMCUDebugData(byte[] bArr) throws RemoteException;

    void sendTestCmd(byte[] bArr) throws RemoteException;

    void setSendSleepStatus(boolean z) throws RemoteException;

    void setUpgradeStatus(boolean z) throws RemoteException;

    void startSendHeartBeatMsg() throws RemoteException;

    void stopSendHeartBeatMsg() throws RemoteException;

    void unregisterMCUCanboxCallback(IMCUCanBoxControlCallback iMCUCanBoxControlCallback) throws RemoteException;

    void unregisterMCUMainCallback(IMCUMainCallback iMCUMainCallback) throws RemoteException;

    void unregisterMcuMsgListener(IMCUMsgCallback iMCUMsgCallback) throws RemoteException;

    byte[] updateFlashData(byte[] bArr) throws RemoteException;

    public static abstract class Stub extends Binder implements IMCUMainService {
        private static final String DESCRIPTOR = "com.hzbhd.proxy.mcu.aidl.IMCUMainService";
        static final int TRANSACTION_clearMessage = 4;
        static final int TRANSACTION_closeDevice = 6;
        static final int TRANSACTION_getCarBackState = 19;
        static final int TRANSACTION_getHardwareReset = 16;
        static final int TRANSACTION_getMcuHardware = 15;
        static final int TRANSACTION_getPowerOnType = 17;
        static final int TRANSACTION_getPowerStatus = 23;
        static final int TRANSACTION_getProcotolVersion = 18;
        static final int TRANSACTION_getVersion = 20;
        static final int TRANSACTION_initMCU = 3;
        static final int TRANSACTION_initSystemReady = 5;
        static final int TRANSACTION_isInitMCU = 21;
        static final int TRANSACTION_registerMCUCanboxCallback = 12;
        static final int TRANSACTION_registerMCUMainCallback = 1;
        static final int TRANSACTION_registerMcuMsgListener = 25;
        static final int TRANSACTION_requestPowerStatus = 22;
        static final int TRANSACTION_sendMCUCanboxData = 11;
        static final int TRANSACTION_sendMCUDebugData = 14;
        static final int TRANSACTION_sendTestCmd = 24;
        static final int TRANSACTION_setSendSleepStatus = 9;
        static final int TRANSACTION_setUpgradeStatus = 10;
        static final int TRANSACTION_startSendHeartBeatMsg = 7;
        static final int TRANSACTION_stopSendHeartBeatMsg = 8;
        static final int TRANSACTION_unregisterMCUCanboxCallback = 13;
        static final int TRANSACTION_unregisterMCUMainCallback = 2;
        static final int TRANSACTION_unregisterMcuMsgListener = 26;
        static final int TRANSACTION_updateFlashData = 27;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMCUMainService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IMCUMainService)) {
                return (IMCUMainService) iInterfaceQueryLocalInterface;
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
                    registerMCUMainCallback(IMCUMainCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterMCUMainCallback(IMCUMainCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    Bundle bundleInitMCU = initMCU();
                    parcel2.writeNoException();
                    if (bundleInitMCU != null) {
                        parcel2.writeInt(1);
                        bundleInitMCU.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    clearMessage(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    initSystemReady();
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    closeDevice();
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    startSendHeartBeatMsg();
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopSendHeartBeatMsg();
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    setSendSleepStatus(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    setUpgradeStatus(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    sendMCUCanboxData(parcel.readInt(), parcel.createByteArray());
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerMCUCanboxCallback(IMCUCanBoxControlCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterMCUCanboxCallback(IMCUCanBoxControlCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    sendMCUDebugData(parcel.createByteArray());
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    int mcuHardware = getMcuHardware();
                    parcel2.writeNoException();
                    parcel2.writeInt(mcuHardware);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    int hardwareReset = getHardwareReset();
                    parcel2.writeNoException();
                    parcel2.writeInt(hardwareReset);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    int powerOnType = getPowerOnType();
                    parcel2.writeNoException();
                    parcel2.writeInt(powerOnType);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    byte procotolVersion = getProcotolVersion();
                    parcel2.writeNoException();
                    parcel2.writeByte(procotolVersion);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    int carBackState = getCarBackState();
                    parcel2.writeNoException();
                    parcel2.writeInt(carBackState);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    getVersion();
                    parcel2.writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsInitMCU = isInitMCU();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsInitMCU ? 1 : 0);
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestPowerStatus(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    int powerStatus = getPowerStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(powerStatus);
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    sendTestCmd(parcel.createByteArray());
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerMcuMsgListener(IMCUMsgCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterMcuMsgListener(IMCUMsgCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    byte[] bArrUpdateFlashData = updateFlashData(parcel.createByteArray());
                    parcel2.writeNoException();
                    parcel2.writeByteArray(bArrUpdateFlashData);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IMCUMainService {
            public static IMCUMainService sDefaultImpl;
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

            @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
            public void registerMCUMainCallback(IMCUMainCallback iMCUMainCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMCUMainCallback != null ? iMCUMainCallback.asBinder() : null);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerMCUMainCallback(iMCUMainCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
            public void unregisterMCUMainCallback(IMCUMainCallback iMCUMainCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMCUMainCallback != null ? iMCUMainCallback.asBinder() : null);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterMCUMainCallback(iMCUMainCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
            public Bundle initMCU() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().initMCU();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
            public void clearMessage(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clearMessage(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
            public void initSystemReady() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().initSystemReady();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
            public void closeDevice() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().closeDevice();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
            public void startSendHeartBeatMsg() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startSendHeartBeatMsg();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
            public void stopSendHeartBeatMsg() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopSendHeartBeatMsg();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
            public void setSendSleepStatus(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSendSleepStatus(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
            public void setUpgradeStatus(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setUpgradeStatus(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
            public void sendMCUCanboxData(int i, byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeByteArray(bArr);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendMCUCanboxData(i, bArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
            public void registerMCUCanboxCallback(IMCUCanBoxControlCallback iMCUCanBoxControlCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMCUCanBoxControlCallback != null ? iMCUCanBoxControlCallback.asBinder() : null);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerMCUCanboxCallback(iMCUCanBoxControlCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
            public void unregisterMCUCanboxCallback(IMCUCanBoxControlCallback iMCUCanBoxControlCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMCUCanBoxControlCallback != null ? iMCUCanBoxControlCallback.asBinder() : null);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterMCUCanboxCallback(iMCUCanBoxControlCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
            public void sendMCUDebugData(byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByteArray(bArr);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendMCUDebugData(bArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
            public int getMcuHardware() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMcuHardware();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
            public int getHardwareReset() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getHardwareReset();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
            public int getPowerOnType() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPowerOnType();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
            public byte getProcotolVersion() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getProcotolVersion();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readByte();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
            public int getCarBackState() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCarBackState();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
            public void getVersion() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getVersion();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
            public boolean isInitMCU() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isInitMCU();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
            public void requestPowerStatus(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestPowerStatus(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
            public int getPowerStatus() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPowerStatus();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
            public void sendTestCmd(byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByteArray(bArr);
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendTestCmd(bArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
            public void registerMcuMsgListener(IMCUMsgCallback iMCUMsgCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMCUMsgCallback != null ? iMCUMsgCallback.asBinder() : null);
                    if (!this.mRemote.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerMcuMsgListener(iMCUMsgCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
            public void unregisterMcuMsgListener(IMCUMsgCallback iMCUMsgCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMCUMsgCallback != null ? iMCUMsgCallback.asBinder() : null);
                    if (!this.mRemote.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterMcuMsgListener(iMCUMsgCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainService
            public byte[] updateFlashData(byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByteArray(bArr);
                    if (!this.mRemote.transact(27, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
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

        public static boolean setDefaultImpl(IMCUMainService iMCUMainService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iMCUMainService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iMCUMainService;
            return true;
        }

        public static IMCUMainService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
