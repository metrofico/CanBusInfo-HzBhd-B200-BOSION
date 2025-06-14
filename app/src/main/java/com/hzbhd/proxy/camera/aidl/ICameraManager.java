package com.hzbhd.proxy.camera.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.Surface;
import com.hzbhd.proxy.camera.aidl.ICameraCallback;

/* loaded from: classes2.dex */
public interface ICameraManager extends IInterface {

    public static class Default implements ICameraManager {
        @Override // com.hzbhd.proxy.camera.aidl.ICameraManager
        public void addCallBack(int i, ICameraCallback iCameraCallback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.hzbhd.proxy.camera.aidl.ICameraManager
        public String getCameraInfo(int i, int i2) throws RemoteException {
            return null;
        }

        @Override // com.hzbhd.proxy.camera.aidl.ICameraManager
        public int getFlagAttr(int i, int i2) throws RemoteException {
            return 0;
        }

        @Override // com.hzbhd.proxy.camera.aidl.ICameraManager
        public void removeCallBack(int i, ICameraCallback iCameraCallback) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.camera.aidl.ICameraManager
        public void setCameraInfo(int i, int i2, String str) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.camera.aidl.ICameraManager
        public void setFlagAttr(int i, int i2, int i3) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.camera.aidl.ICameraManager
        public void startPreview(int i, Surface surface) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.camera.aidl.ICameraManager
        public void startPreviewWithCallBack(int i, Surface surface, ICameraCallback iCameraCallback) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.camera.aidl.ICameraManager
        public void startRecord(int i, Surface surface) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.camera.aidl.ICameraManager
        public void stopPreview(int i) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.camera.aidl.ICameraManager
        public void stopRecord(int i) throws RemoteException {
        }
    }

    void addCallBack(int i, ICameraCallback iCameraCallback) throws RemoteException;

    String getCameraInfo(int i, int i2) throws RemoteException;

    int getFlagAttr(int i, int i2) throws RemoteException;

    void removeCallBack(int i, ICameraCallback iCameraCallback) throws RemoteException;

    void setCameraInfo(int i, int i2, String str) throws RemoteException;

    void setFlagAttr(int i, int i2, int i3) throws RemoteException;

    void startPreview(int i, Surface surface) throws RemoteException;

    void startPreviewWithCallBack(int i, Surface surface, ICameraCallback iCameraCallback) throws RemoteException;

    void startRecord(int i, Surface surface) throws RemoteException;

    void stopPreview(int i) throws RemoteException;

    void stopRecord(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements ICameraManager {
        private static final String DESCRIPTOR = "com.hzbhd.proxy.camera.aidl.ICameraManager";
        static final int TRANSACTION_addCallBack = 5;
        static final int TRANSACTION_getCameraInfo = 10;
        static final int TRANSACTION_getFlagAttr = 4;
        static final int TRANSACTION_removeCallBack = 6;
        static final int TRANSACTION_setCameraInfo = 9;
        static final int TRANSACTION_setFlagAttr = 3;
        static final int TRANSACTION_startPreview = 1;
        static final int TRANSACTION_startPreviewWithCallBack = 11;
        static final int TRANSACTION_startRecord = 7;
        static final int TRANSACTION_stopPreview = 2;
        static final int TRANSACTION_stopRecord = 8;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICameraManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ICameraManager)) {
                return (ICameraManager) iInterfaceQueryLocalInterface;
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
                    startPreview(parcel.readInt(), parcel.readInt() != 0 ? (Surface) Surface.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopPreview(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    setFlagAttr(parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    int flagAttr = getFlagAttr(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(flagAttr);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    addCallBack(parcel.readInt(), ICameraCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeCallBack(parcel.readInt(), ICameraCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    startRecord(parcel.readInt(), parcel.readInt() != 0 ? (Surface) Surface.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopRecord(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    setCameraInfo(parcel.readInt(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    String cameraInfo = getCameraInfo(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(cameraInfo);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    startPreviewWithCallBack(parcel.readInt(), parcel.readInt() != 0 ? (Surface) Surface.CREATOR.createFromParcel(parcel) : null, ICameraCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ICameraManager {
            public static ICameraManager sDefaultImpl;
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

            @Override // com.hzbhd.proxy.camera.aidl.ICameraManager
            public void startPreview(int i, Surface surface) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (surface != null) {
                        parcelObtain.writeInt(1);
                        surface.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startPreview(i, surface);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.camera.aidl.ICameraManager
            public void stopPreview(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopPreview(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.camera.aidl.ICameraManager
            public void setFlagAttr(int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setFlagAttr(i, i2, i3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.camera.aidl.ICameraManager
            public int getFlagAttr(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFlagAttr(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.camera.aidl.ICameraManager
            public void addCallBack(int i, ICameraCallback iCameraCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iCameraCallback != null ? iCameraCallback.asBinder() : null);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addCallBack(i, iCameraCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.camera.aidl.ICameraManager
            public void removeCallBack(int i, ICameraCallback iCameraCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iCameraCallback != null ? iCameraCallback.asBinder() : null);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeCallBack(i, iCameraCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.camera.aidl.ICameraManager
            public void startRecord(int i, Surface surface) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (surface != null) {
                        parcelObtain.writeInt(1);
                        surface.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startRecord(i, surface);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.camera.aidl.ICameraManager
            public void stopRecord(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopRecord(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.camera.aidl.ICameraManager
            public void setCameraInfo(int i, int i2, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setCameraInfo(i, i2, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.camera.aidl.ICameraManager
            public String getCameraInfo(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCameraInfo(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.camera.aidl.ICameraManager
            public void startPreviewWithCallBack(int i, Surface surface, ICameraCallback iCameraCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (surface != null) {
                        parcelObtain.writeInt(1);
                        surface.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iCameraCallback != null ? iCameraCallback.asBinder() : null);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startPreviewWithCallBack(i, surface, iCameraCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICameraManager iCameraManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iCameraManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iCameraManager;
            return true;
        }

        public static ICameraManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
