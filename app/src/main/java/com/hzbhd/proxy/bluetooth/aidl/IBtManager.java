package com.hzbhd.proxy.bluetooth.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.hzbhd.proxy.bluetooth.aidl.IBtCallback;
import java.util.List;

/* loaded from: classes2.dex */
public interface IBtManager extends IInterface {

    public static class Default implements IBtManager {
        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
        public void addBtCallBack(IBtCallback iBtCallback) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
        public void answer(String str) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
        public void call(String str) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
        public boolean enableWechatFilter() throws RemoteException {
            return false;
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
        public List<String> getCall() throws RemoteException {
            return null;
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
        public int getCallDevice() throws RemoteException {
            return 0;
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
        public String getCallNum() throws RemoteException {
            return null;
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
        public List<String> getConHfp() throws RemoteException {
            return null;
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
        public String getCurrA2dpAddress() throws RemoteException {
            return null;
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
        public String getCurrHfpAddress() throws RemoteException {
            return null;
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
        public String getLocalAddress() throws RemoteException {
            return null;
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
        public String getMusicAlbum() throws RemoteException {
            return null;
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
        public String getMusicArtist() throws RemoteException {
            return null;
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
        public String getMusicTitle() throws RemoteException {
            return null;
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
        public String getName() throws RemoteException {
            return null;
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
        public boolean getPairMode() throws RemoteException {
            return false;
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
        public List<String> getPairedHfp() throws RemoteException {
            return null;
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
        public String getPinCode() throws RemoteException {
            return null;
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
        public String getState() throws RemoteException {
            return null;
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
        public String getWechatCall() throws RemoteException {
            return null;
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
        public void handup(String str) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
        public boolean isAutoAnswer() throws RemoteException {
            return false;
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
        public boolean isAutoConn() throws RemoteException {
            return false;
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
        public boolean isMicMute() throws RemoteException {
            return false;
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
        public boolean isMicOut() throws RemoteException {
            return false;
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
        public boolean isWechatFilter() throws RemoteException {
            return false;
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
        public void removeBtCallBack(IBtCallback iBtCallback) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
        public void sendAction(String str) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
        public void sendCallKey(String str, String str2) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
        public void sendDeviceAction(String str, String str2) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
        public void sendKey(String str) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
        public void setName(String str) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
        public void setPinCode(String str) throws RemoteException {
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
        public void setWechatFilter(boolean z) throws RemoteException {
        }
    }

    void addBtCallBack(IBtCallback iBtCallback) throws RemoteException;

    void answer(String str) throws RemoteException;

    void call(String str) throws RemoteException;

    boolean enableWechatFilter() throws RemoteException;

    List<String> getCall() throws RemoteException;

    int getCallDevice() throws RemoteException;

    String getCallNum() throws RemoteException;

    List<String> getConHfp() throws RemoteException;

    String getCurrA2dpAddress() throws RemoteException;

    String getCurrHfpAddress() throws RemoteException;

    String getLocalAddress() throws RemoteException;

    String getMusicAlbum() throws RemoteException;

    String getMusicArtist() throws RemoteException;

    String getMusicTitle() throws RemoteException;

    String getName() throws RemoteException;

    boolean getPairMode() throws RemoteException;

    List<String> getPairedHfp() throws RemoteException;

    String getPinCode() throws RemoteException;

    String getState() throws RemoteException;

    String getWechatCall() throws RemoteException;

    void handup(String str) throws RemoteException;

    boolean isAutoAnswer() throws RemoteException;

    boolean isAutoConn() throws RemoteException;

    boolean isMicMute() throws RemoteException;

    boolean isMicOut() throws RemoteException;

    boolean isWechatFilter() throws RemoteException;

    void removeBtCallBack(IBtCallback iBtCallback) throws RemoteException;

    void sendAction(String str) throws RemoteException;

    void sendCallKey(String str, String str2) throws RemoteException;

    void sendDeviceAction(String str, String str2) throws RemoteException;

    void sendKey(String str) throws RemoteException;

    void setName(String str) throws RemoteException;

    void setPinCode(String str) throws RemoteException;

    void setWechatFilter(boolean z) throws RemoteException;

    public static abstract class Stub extends Binder implements IBtManager {
        private static final String DESCRIPTOR = "com.hzbhd.proxy.bluetooth.aidl.IBtManager";
        static final int TRANSACTION_addBtCallBack = 1;
        static final int TRANSACTION_answer = 30;
        static final int TRANSACTION_call = 5;
        static final int TRANSACTION_enableWechatFilter = 26;
        static final int TRANSACTION_getCall = 29;
        static final int TRANSACTION_getCallDevice = 22;
        static final int TRANSACTION_getCallNum = 19;
        static final int TRANSACTION_getConHfp = 12;
        static final int TRANSACTION_getCurrA2dpAddress = 15;
        static final int TRANSACTION_getCurrHfpAddress = 14;
        static final int TRANSACTION_getLocalAddress = 13;
        static final int TRANSACTION_getMusicAlbum = 17;
        static final int TRANSACTION_getMusicArtist = 18;
        static final int TRANSACTION_getMusicTitle = 16;
        static final int TRANSACTION_getName = 10;
        static final int TRANSACTION_getPairMode = 23;
        static final int TRANSACTION_getPairedHfp = 11;
        static final int TRANSACTION_getPinCode = 25;
        static final int TRANSACTION_getState = 8;
        static final int TRANSACTION_getWechatCall = 33;
        static final int TRANSACTION_handup = 31;
        static final int TRANSACTION_isAutoAnswer = 34;
        static final int TRANSACTION_isAutoConn = 9;
        static final int TRANSACTION_isMicMute = 21;
        static final int TRANSACTION_isMicOut = 20;
        static final int TRANSACTION_isWechatFilter = 27;
        static final int TRANSACTION_removeBtCallBack = 2;
        static final int TRANSACTION_sendAction = 3;
        static final int TRANSACTION_sendCallKey = 32;
        static final int TRANSACTION_sendDeviceAction = 4;
        static final int TRANSACTION_sendKey = 6;
        static final int TRANSACTION_setName = 7;
        static final int TRANSACTION_setPinCode = 24;
        static final int TRANSACTION_setWechatFilter = 28;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IBtManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IBtManager)) {
                return (IBtManager) iInterfaceQueryLocalInterface;
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
                    addBtCallBack(IBtCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeBtCallBack(IBtCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    sendAction(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    sendDeviceAction(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    call(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    sendKey(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    setName(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    String state = getState();
                    parcel2.writeNoException();
                    parcel2.writeString(state);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsAutoConn = isAutoConn();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsAutoConn ? 1 : 0);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    String name = getName();
                    parcel2.writeNoException();
                    parcel2.writeString(name);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<String> pairedHfp = getPairedHfp();
                    parcel2.writeNoException();
                    parcel2.writeStringList(pairedHfp);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<String> conHfp = getConHfp();
                    parcel2.writeNoException();
                    parcel2.writeStringList(conHfp);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    String localAddress = getLocalAddress();
                    parcel2.writeNoException();
                    parcel2.writeString(localAddress);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    String currHfpAddress = getCurrHfpAddress();
                    parcel2.writeNoException();
                    parcel2.writeString(currHfpAddress);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    String currA2dpAddress = getCurrA2dpAddress();
                    parcel2.writeNoException();
                    parcel2.writeString(currA2dpAddress);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    String musicTitle = getMusicTitle();
                    parcel2.writeNoException();
                    parcel2.writeString(musicTitle);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    String musicAlbum = getMusicAlbum();
                    parcel2.writeNoException();
                    parcel2.writeString(musicAlbum);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    String musicArtist = getMusicArtist();
                    parcel2.writeNoException();
                    parcel2.writeString(musicArtist);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    String callNum = getCallNum();
                    parcel2.writeNoException();
                    parcel2.writeString(callNum);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsMicOut = isMicOut();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsMicOut ? 1 : 0);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsMicMute = isMicMute();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsMicMute ? 1 : 0);
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    int callDevice = getCallDevice();
                    parcel2.writeNoException();
                    parcel2.writeInt(callDevice);
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean pairMode = getPairMode();
                    parcel2.writeNoException();
                    parcel2.writeInt(pairMode ? 1 : 0);
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    setPinCode(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    String pinCode = getPinCode();
                    parcel2.writeNoException();
                    parcel2.writeString(pinCode);
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zEnableWechatFilter = enableWechatFilter();
                    parcel2.writeNoException();
                    parcel2.writeInt(zEnableWechatFilter ? 1 : 0);
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsWechatFilter = isWechatFilter();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsWechatFilter ? 1 : 0);
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    setWechatFilter(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<String> call = getCall();
                    parcel2.writeNoException();
                    parcel2.writeStringList(call);
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    answer(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    handup(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    sendCallKey(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    String wechatCall = getWechatCall();
                    parcel2.writeNoException();
                    parcel2.writeString(wechatCall);
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsAutoAnswer = isAutoAnswer();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsAutoAnswer ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IBtManager {
            public static IBtManager sDefaultImpl;
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

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
            public void addBtCallBack(IBtCallback iBtCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBtCallback != null ? iBtCallback.asBinder() : null);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addBtCallBack(iBtCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
            public void removeBtCallBack(IBtCallback iBtCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBtCallback != null ? iBtCallback.asBinder() : null);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeBtCallBack(iBtCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
            public void sendAction(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendAction(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
            public void sendDeviceAction(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendDeviceAction(str, str2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
            public void call(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().call(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
            public void sendKey(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendKey(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
            public void setName(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setName(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
            public String getState() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getState();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
            public boolean isAutoConn() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isAutoConn();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
            public String getName() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getName();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
            public List<String> getPairedHfp() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPairedHfp();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArrayList();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
            public List<String> getConHfp() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConHfp();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArrayList();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
            public String getLocalAddress() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLocalAddress();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
            public String getCurrHfpAddress() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrHfpAddress();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
            public String getCurrA2dpAddress() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrA2dpAddress();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
            public String getMusicTitle() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMusicTitle();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
            public String getMusicAlbum() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMusicAlbum();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
            public String getMusicArtist() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMusicArtist();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
            public String getCallNum() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCallNum();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
            public boolean isMicOut() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isMicOut();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
            public boolean isMicMute() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isMicMute();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
            public int getCallDevice() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCallDevice();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
            public boolean getPairMode() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPairMode();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
            public void setPinCode(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setPinCode(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
            public String getPinCode() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPinCode();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
            public boolean enableWechatFilter() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().enableWechatFilter();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
            public boolean isWechatFilter() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(27, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isWechatFilter();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
            public void setWechatFilter(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(28, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setWechatFilter(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
            public List<String> getCall() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(29, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCall();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArrayList();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
            public void answer(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(30, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().answer(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
            public void handup(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(31, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().handup(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
            public void sendCallKey(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(32, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendCallKey(str, str2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
            public String getWechatCall() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(33, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWechatCall();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hzbhd.proxy.bluetooth.aidl.IBtManager
            public boolean isAutoAnswer() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(34, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isAutoAnswer();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IBtManager iBtManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iBtManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iBtManager;
            return true;
        }

        public static IBtManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
