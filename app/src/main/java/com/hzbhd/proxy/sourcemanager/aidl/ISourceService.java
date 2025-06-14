package com.hzbhd.proxy.sourcemanager.aidl;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.hzbhd.proxy.sourcemanager.aidl.IAudioCallback;
import com.hzbhd.proxy.sourcemanager.aidl.ISourceBluetoothCallback;
import com.hzbhd.proxy.sourcemanager.aidl.ISourceCallback;

/* loaded from: classes2.dex */
public interface ISourceService extends IInterface {

    public static class Default implements ISourceService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.hzbhd.proxy.sourcemanager.aidl.ISourceService
        public int getCurrentAudioSource(int i) throws RemoteException {
            return 0;
        }

        @Override // com.hzbhd.proxy.sourcemanager.aidl.ISourceService
        public void notifyAppAudio(int i, String str, int i2, boolean z) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.sourcemanager.aidl.ISourceService
        public int registerAudioCallback(int i, IAudioCallback iAudioCallback) throws RemoteException {
            return 0;
        }

        @Override // com.hzbhd.proxy.sourcemanager.aidl.ISourceService
        public boolean registerSourceCallback(int i, ISourceCallback iSourceCallback) throws RemoteException {
            return false;
        }

        @Override // com.hzbhd.proxy.sourcemanager.aidl.ISourceService
        public int releaseAudioChannel(int i, int i2) throws RemoteException {
            return 0;
        }

        @Override // com.hzbhd.proxy.sourcemanager.aidl.ISourceService
        public boolean releaseBluetooth(int i) throws RemoteException {
            return false;
        }

        @Override // com.hzbhd.proxy.sourcemanager.aidl.ISourceService
        public int requestAudioChannel(int i, int i2, Bundle bundle) throws RemoteException {
            return 0;
        }

        @Override // com.hzbhd.proxy.sourcemanager.aidl.ISourceService
        public boolean requestBluetooth(int i, ISourceBluetoothCallback iSourceBluetoothCallback) throws RemoteException {
            return false;
        }

        @Override // com.hzbhd.proxy.sourcemanager.aidl.ISourceService
        public int unregisterAudioCallback(int i, IAudioCallback iAudioCallback) throws RemoteException {
            return 0;
        }

        @Override // com.hzbhd.proxy.sourcemanager.aidl.ISourceService
        public boolean unregisterSourceCallback(int i) throws RemoteException {
            return false;
        }
    }

    int getCurrentAudioSource(int i) throws RemoteException;

    void notifyAppAudio(int i, String str, int i2, boolean z) throws RemoteException;

    int registerAudioCallback(int i, IAudioCallback iAudioCallback) throws RemoteException;

    boolean registerSourceCallback(int i, ISourceCallback iSourceCallback) throws RemoteException;

    int releaseAudioChannel(int i, int i2) throws RemoteException;

    boolean releaseBluetooth(int i) throws RemoteException;

    int requestAudioChannel(int i, int i2, Bundle bundle) throws RemoteException;

    boolean requestBluetooth(int i, ISourceBluetoothCallback iSourceBluetoothCallback) throws RemoteException;

    int unregisterAudioCallback(int i, IAudioCallback iAudioCallback) throws RemoteException;

    boolean unregisterSourceCallback(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements ISourceService {
        private static final String DESCRIPTOR = "com.hzbhd.proxy.sourcemanager.aidl.ISourceService";
        static final int TRANSACTION_getCurrentAudioSource = 6;
        static final int TRANSACTION_notifyAppAudio = 5;
        static final int TRANSACTION_registerAudioCallback = 9;
        static final int TRANSACTION_registerSourceCallback = 1;
        static final int TRANSACTION_releaseAudioChannel = 4;
        static final int TRANSACTION_releaseBluetooth = 8;
        static final int TRANSACTION_requestAudioChannel = 3;
        static final int TRANSACTION_requestBluetooth = 7;
        static final int TRANSACTION_unregisterAudioCallback = 10;
        static final int TRANSACTION_unregisterSourceCallback = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISourceService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ISourceService)) {
                return (ISourceService) iInterfaceQueryLocalInterface;
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
                    boolean zRegisterSourceCallback = registerSourceCallback(parcel.readInt(), ISourceCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zRegisterSourceCallback ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zUnregisterSourceCallback = unregisterSourceCallback(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zUnregisterSourceCallback ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iRequestAudioChannel = requestAudioChannel(parcel.readInt(), parcel.readInt(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(iRequestAudioChannel);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iReleaseAudioChannel = releaseAudioChannel(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iReleaseAudioChannel);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyAppAudio(parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    int currentAudioSource = getCurrentAudioSource(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(currentAudioSource);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRequestBluetooth = requestBluetooth(parcel.readInt(), ISourceBluetoothCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zRequestBluetooth ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReleaseBluetooth = releaseBluetooth(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zReleaseBluetooth ? 1 : 0);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iRegisterAudioCallback = registerAudioCallback(parcel.readInt(), IAudioCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(iRegisterAudioCallback);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iUnregisterAudioCallback = unregisterAudioCallback(parcel.readInt(), IAudioCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(iUnregisterAudioCallback);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ISourceService {
            public static ISourceService sDefaultImpl;
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

            @Override // com.hzbhd.proxy.sourcemanager.aidl.ISourceService
            public boolean registerSourceCallback(int i, ISourceCallback iSourceCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iSourceCallback != null ? iSourceCallback.asBinder() : null);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerSourceCallback(i, iSourceCallback);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.sourcemanager.aidl.ISourceService
            public boolean unregisterSourceCallback(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unregisterSourceCallback(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.sourcemanager.aidl.ISourceService
            public int requestAudioChannel(int i, int i2, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestAudioChannel(i, i2, bundle);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.sourcemanager.aidl.ISourceService
            public int releaseAudioChannel(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().releaseAudioChannel(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.sourcemanager.aidl.ISourceService
            public void notifyAppAudio(int i, String str, int i2, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyAppAudio(i, str, i2, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.sourcemanager.aidl.ISourceService
            public int getCurrentAudioSource(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentAudioSource(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.sourcemanager.aidl.ISourceService
            public boolean requestBluetooth(int i, ISourceBluetoothCallback iSourceBluetoothCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iSourceBluetoothCallback != null ? iSourceBluetoothCallback.asBinder() : null);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestBluetooth(i, iSourceBluetoothCallback);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.sourcemanager.aidl.ISourceService
            public boolean releaseBluetooth(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().releaseBluetooth(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.sourcemanager.aidl.ISourceService
            public int registerAudioCallback(int i, IAudioCallback iAudioCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iAudioCallback != null ? iAudioCallback.asBinder() : null);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerAudioCallback(i, iAudioCallback);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.sourcemanager.aidl.ISourceService
            public int unregisterAudioCallback(int i, IAudioCallback iAudioCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iAudioCallback != null ? iAudioCallback.asBinder() : null);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unregisterAudioCallback(i, iAudioCallback);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISourceService iSourceService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iSourceService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSourceService;
            return true;
        }

        public static ISourceService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
