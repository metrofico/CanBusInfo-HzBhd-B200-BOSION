package com.hzbhd.proxy.bluetooth.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* loaded from: classes2.dex */
public interface IBtCallback extends IInterface {

    public static class Default implements IBtCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void callingMessage(String str, String str2) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void onCallNumChange(String str) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void onCloseA2dp() throws RemoteException {
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void onCloseHfp() throws RemoteException {
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void onCurrA2dpAddressChange(String str) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void onCurrHfpAddressChange(String str) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void onFoundDeviceChange(List<String> list) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void onHfpConnChange(List<String> list) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void onMicOutChange(boolean z) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void onPairedChange(List<String> list) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void updateBtStatus(int i, String str) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void updateCall(List<String> list, String str) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void updateCallDevice(int i) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void updateCallName(String str) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void updateCallTime(long j) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void updateId3(String str, String str2, String str3) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void updateIsAutoAnswer(boolean z) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void updateIsAutoConn(boolean z) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void updateMicMute(boolean z) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void updateName(String str) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void updateWechatCall(String str) throws RemoteException {
        }
    }

    void callingMessage(String str, String str2) throws RemoteException;

    void onCallNumChange(String str) throws RemoteException;

    void onCloseA2dp() throws RemoteException;

    void onCloseHfp() throws RemoteException;

    void onCurrA2dpAddressChange(String str) throws RemoteException;

    void onCurrHfpAddressChange(String str) throws RemoteException;

    void onFoundDeviceChange(List<String> list) throws RemoteException;

    void onHfpConnChange(List<String> list) throws RemoteException;

    void onMicOutChange(boolean z) throws RemoteException;

    void onPairedChange(List<String> list) throws RemoteException;

    void updateBtStatus(int i, String str) throws RemoteException;

    void updateCall(List<String> list, String str) throws RemoteException;

    void updateCallDevice(int i) throws RemoteException;

    void updateCallName(String str) throws RemoteException;

    void updateCallTime(long j) throws RemoteException;

    void updateId3(String str, String str2, String str3) throws RemoteException;

    void updateIsAutoAnswer(boolean z) throws RemoteException;

    void updateIsAutoConn(boolean z) throws RemoteException;

    void updateMicMute(boolean z) throws RemoteException;

    void updateName(String str) throws RemoteException;

    void updateWechatCall(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IBtCallback {
        private static final String DESCRIPTOR = "com.hzbhd.proxy.bluetooth.aidl.IBtCallback";
        static final int TRANSACTION_callingMessage = 18;
        static final int TRANSACTION_onCallNumChange = 13;
        static final int TRANSACTION_onCloseA2dp = 1;
        static final int TRANSACTION_onCloseHfp = 2;
        static final int TRANSACTION_onCurrA2dpAddressChange = 9;
        static final int TRANSACTION_onCurrHfpAddressChange = 8;
        static final int TRANSACTION_onFoundDeviceChange = 6;
        static final int TRANSACTION_onHfpConnChange = 7;
        static final int TRANSACTION_onMicOutChange = 5;
        static final int TRANSACTION_onPairedChange = 10;
        static final int TRANSACTION_updateBtStatus = 3;
        static final int TRANSACTION_updateCall = 19;
        static final int TRANSACTION_updateCallDevice = 17;
        static final int TRANSACTION_updateCallName = 12;
        static final int TRANSACTION_updateCallTime = 15;
        static final int TRANSACTION_updateId3 = 14;
        static final int TRANSACTION_updateIsAutoAnswer = 21;
        static final int TRANSACTION_updateIsAutoConn = 4;
        static final int TRANSACTION_updateMicMute = 16;
        static final int TRANSACTION_updateName = 11;
        static final int TRANSACTION_updateWechatCall = 20;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IBtCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IBtCallback)) {
                return (IBtCallback) iInterfaceQueryLocalInterface;
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
                    onCloseA2dp();
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    onCloseHfp();
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateBtStatus(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateIsAutoConn(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    onMicOutChange(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    onFoundDeviceChange(parcel.createStringArrayList());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    onHfpConnChange(parcel.createStringArrayList());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    onCurrHfpAddressChange(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    onCurrA2dpAddressChange(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    onPairedChange(parcel.createStringArrayList());
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateName(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateCallName(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    onCallNumChange(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateId3(parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateCallTime(parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateMicMute(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateCallDevice(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    callingMessage(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateCall(parcel.createStringArrayList(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateWechatCall(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateIsAutoAnswer(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IBtCallback {
            public static IBtCallback sDefaultImpl;
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

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
            public void onCloseA2dp() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCloseA2dp();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
            public void onCloseHfp() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCloseHfp();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
            public void updateBtStatus(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateBtStatus(i, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
            public void updateIsAutoConn(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateIsAutoConn(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
            public void onMicOutChange(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMicOutChange(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
            public void onFoundDeviceChange(List<String> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStringList(list);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onFoundDeviceChange(list);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
            public void onHfpConnChange(List<String> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStringList(list);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onHfpConnChange(list);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
            public void onCurrHfpAddressChange(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCurrHfpAddressChange(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
            public void onCurrA2dpAddressChange(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCurrA2dpAddressChange(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
            public void onPairedChange(List<String> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStringList(list);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPairedChange(list);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
            public void updateName(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateName(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
            public void updateCallName(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateCallName(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
            public void onCallNumChange(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCallNumChange(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
            public void updateId3(String str, String str2, String str3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateId3(str, str2, str3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
            public void updateCallTime(long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateCallTime(j);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
            public void updateMicMute(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateMicMute(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
            public void updateCallDevice(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateCallDevice(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
            public void callingMessage(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callingMessage(str, str2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
            public void updateCall(List<String> list, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStringList(list);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateCall(list, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
            public void updateWechatCall(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateWechatCall(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
            public void updateIsAutoAnswer(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateIsAutoAnswer(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IBtCallback iBtCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iBtCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iBtCallback;
            return true;
        }

        public static IBtCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
