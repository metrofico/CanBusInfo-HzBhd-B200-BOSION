package com.hzbhd.proxy.bluetooth.manager;

import android.os.IBinder;
import android.os.RemoteException;

import androidx.core.app.NotificationCompat;

import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.constant.bluetooth.BtConstants;
import com.hzbhd.constant.share.lcd.LcdInfoShare;
import com.hzbhd.proxy.bluetooth.BtUtil;
import com.hzbhd.proxy.bluetooth.aidl.IBtCallback;
import com.hzbhd.proxy.bluetooth.aidl.IBtManager;
import com.hzbhd.proxy.bluetooth.bean.BtCall;
import com.hzbhd.proxy.bluetooth.bean.BtStatus;
import com.hzbhd.proxy.bluetooth.bean.Device;
import com.hzbhd.proxy.bluetooth.listener.BtListener;
import com.hzbhd.systemstatus.proxy.IServiceConnectListener;
import com.hzbhd.systemstatus.proxy.ServiceStateManager;
import com.hzbhd.util.LogUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class BtManager {
    public static final String SERVICE_NAME_BT = "hzbhd_bluetooth";
    private IBtCallback iBtCallback = new IBtCallback.Stub() { // from class: com.hzbhd.proxy.bluetooth.manager.BtManager$iBtCallback$1
        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void callingMessage(String name, String address) {
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void onCallNumChange(String num) {
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void updateCallName(String name) {
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void updateCallTime(long time) {
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void onCloseA2dp() {
            if (mBtListener != null) {
                BtListener btListener = mBtListener;
                Intrinsics.checkNotNull(btListener);
                btListener.onCloseA2dp();
            } else if (LogUtil.log3()) {
                LogUtil.d("iBtCallback: listener is null");
            }
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void onCloseHfp() {
            if (mBtListener != null) {
                BtListener btListener = mBtListener;
                Intrinsics.checkNotNull(btListener);
                btListener.onCloseHfp();
            } else if (LogUtil.log3()) {
                LogUtil.d("iBtCallback: listener is null");
            }
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void updateBtStatus(int changeState, String status) {
            Intrinsics.checkNotNullParameter(status, "status");
            if (mBtListener != null) {
                BtListener btListener = mBtListener;
                Intrinsics.checkNotNull(btListener);
                BtConstants.BT_STATUS bt_status = BtConstants.BT_STATUS.values()[changeState];
                BtStatus btStatusFromJson = BtStatus.INSTANCE.fromJson(status);
                Intrinsics.checkNotNull(btStatusFromJson);
                btListener.updateBtStatus(bt_status, btStatusFromJson);
                return;
            }
            if (LogUtil.log3()) {
                LogUtil.d("iBtCallback: listener is null");
            }
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void updateIsAutoConn(boolean autoConn) {
            if (mBtListener != null) {
                BtListener btListener = mBtListener;
                Intrinsics.checkNotNull(btListener);
                btListener.updateIsAutoConn(autoConn);
            } else if (LogUtil.log3()) {
                LogUtil.d("iBtCallback: listener is null");
            }
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void updateName(String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            if (mBtListener != null) {
                BtListener btListener = mBtListener;
                Intrinsics.checkNotNull(btListener);
                btListener.updateName(name);
            } else if (LogUtil.log3()) {
                LogUtil.d("iBtCallback: listener is null");
            }
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void onFoundDeviceChange(List<String> stringList) {
            Intrinsics.checkNotNullParameter(stringList, "stringList");
            if (mBtListener != null) {
                BtListener btListener = mBtListener;
                Intrinsics.checkNotNull(btListener);
                btListener.onVisibleDeviceChange(BtUtil.INSTANCE.stringToDevices(stringList));
            } else if (LogUtil.log3()) {
                LogUtil.d("iBtCallback: listener is null");
            }
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void onHfpConnChange(List<String> hfpList) {
            Intrinsics.checkNotNullParameter(hfpList, "hfpList");
            if (mBtListener != null) {
                BtListener btListener = mBtListener;
                Intrinsics.checkNotNull(btListener);
                btListener.onHfpConnChange(BtUtil.INSTANCE.stringToDevices(hfpList));
            } else if (LogUtil.log3()) {
                LogUtil.d("iBtCallback: listener is null");
            }
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void onCurrHfpAddressChange(String address) {
            Intrinsics.checkNotNullParameter(address, "address");
            if (mBtListener != null) {
                BtListener btListener = mBtListener;
                Intrinsics.checkNotNull(btListener);
                btListener.onCurrHfpAddressChange(address);
            } else if (LogUtil.log3()) {
                LogUtil.d("iBtCallback: listener is null");
            }
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void onCurrA2dpAddressChange(String address) {
            Intrinsics.checkNotNullParameter(address, "address");
            if (mBtListener != null) {
                BtListener btListener = mBtListener;
                Intrinsics.checkNotNull(btListener);
                btListener.onCurrA2dpAddressChange(address);
            } else if (LogUtil.log3()) {
                LogUtil.d("iBtCallback: listener is null");
            }
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void onMicOutChange(boolean micOut) {
            if (mBtListener != null) {
                BtListener btListener = mBtListener;
                Intrinsics.checkNotNull(btListener);
                btListener.onMicOutChange(micOut);
            } else if (LogUtil.log3()) {
                LogUtil.d("iBtCallback: listener is null");
            }
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void onPairedChange(List<String> pairList) {
            Intrinsics.checkNotNullParameter(pairList, "pairList");
            if (mBtListener != null) {
                BtListener btListener = mBtListener;
                Intrinsics.checkNotNull(btListener);
                btListener.onPairedChange(BtUtil.INSTANCE.stringToDevices(pairList));
            } else if (LogUtil.log3()) {
                LogUtil.d("iBtCallback: listener is null");
            }
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void updateId3(String title, String artist, String album) {
            if (mBtListener != null) {
                BtListener btListener = mBtListener;
                Intrinsics.checkNotNull(btListener);
                btListener.updateId3(title, artist, album);
            } else if (LogUtil.log3()) {
                LogUtil.d("iBtCallback: listener is null");
            }
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void updateMicMute(boolean mute) {
            if (mBtListener != null) {
                BtListener btListener = mBtListener;
                Intrinsics.checkNotNull(btListener);
                btListener.updateMicMute(mute);
            } else if (LogUtil.log3()) {
                LogUtil.d("iBtCallback: listener is null");
            }
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void updateCallDevice(int callDevice) {
            if (mBtListener != null) {
                BtListener btListener = mBtListener;
                Intrinsics.checkNotNull(btListener);
                btListener.updateCallDevice(BtConstants.CallDevice.values()[callDevice]);
            } else if (LogUtil.log3()) {
                LogUtil.d("iBtCallback: listener is null");
            }
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void updateCall(List<String> call, String changeCall) {
            Intrinsics.checkNotNullParameter(call, "call");
            if (mBtListener != null) {
                BtListener btListener = mBtListener;
                Intrinsics.checkNotNull(btListener);
                btListener.updateCall(BtUtil.INSTANCE.stringToCall(call), changeCall != null ? BtCall.INSTANCE.fromJson(changeCall) : null);
            } else if (LogUtil.log3()) {
                LogUtil.d("iBtCallback: listener is null");
            }
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void updateWechatCall(String changeCall) {
            if (mBtListener != null) {
                BtListener btListener = mBtListener;
                Intrinsics.checkNotNull(btListener);
                btListener.updateWechatCall(changeCall != null ? BtCall.INSTANCE.fromJson(changeCall) : null);
            } else if (LogUtil.log3()) {
                LogUtil.d("iBtCallback: listener is null");
            }
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void updateIsAutoAnswer(boolean autoAnswer) {
            if (mBtListener != null) {
                BtListener btListener = mBtListener;
                Intrinsics.checkNotNull(btListener);
                btListener.updateIsAutoAnswer(autoAnswer);
            } else if (LogUtil.log3()) {
                LogUtil.d("iBtCallback: listener is null");
            }
        }
    };
    private BtListener mBtListener;
    private IBtManager mIBtManager;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final BtManager INSTANCE = new BtManager();


    /* JADX WARN: Removed duplicated region for block: B:12:0x002d A[Catch: all -> 0x0031, TRY_LEAVE, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0007, B:8:0x0015, B:11:0x002a, B:12:0x002d), top: B:18:0x0003, inners: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public IBtManager getIBtManager() {
        IBtManager mService = null;
        synchronized (BtManager.class) {
            Method method;
            try {
                method = Class.forName("android.os.ServiceManager").getMethod("getService", String.class);
                IBinder binder = (IBinder) method.invoke(null, SERVICE_NAME_BT);
                if (binder != null) {
                    mService = IBtManager.Stub.asInterface(binder);
                    if (mService != null) {
                        this.mIBtManager = mService;
                        mService.asBinder().linkToDeath(() -> {
                            this.mIBtManager = null;
                        }, 0);
                        onServiceConnected();
                    }
                }
            } catch (RemoteException | InvocationTargetException | NoSuchMethodException |
                     ClassNotFoundException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return mService;
    }


    /* JADX INFO: Access modifiers changed from: private */
    public static final void _get_iBtManager_$lambda$1$lambda$0(BtManager this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.mIBtManager = null;
    }

    public final boolean isConn() {
        return getIBtManager() != null;
    }

    private final void onServiceConnected() {
        // from class: com.hzbhd.proxy.bluetooth.manager.BtManager$$ExternalSyntheticLambda1
// com.hzbhd.systemstatus.proxy.IServiceConnectListener
        ServiceStateManager.getInstance().registerConnectListener(SourceConstantsDef.MODULE_ID.BLUETOOTH.ordinal(), () -> {
            if (mBtListener != null) {
                try {
                    if (LogUtil.log5()) {
                        LogUtil.d("onServiceConnected: bt");
                    }
                    IBtManager iBtManager = getIBtManager();
                    if (iBtManager != null) {
                        iBtManager.addBtCallBack(iBtCallback);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public final IBtCallback getIBtCallback() {
        return this.iBtCallback;
    }

    public final void setIBtCallback(IBtCallback iBtCallback) {
        Intrinsics.checkNotNullParameter(iBtCallback, "<set-?>");
        this.iBtCallback = iBtCallback;
    }

    public final void setBtCallBack(BtListener callback) {
        this.mBtListener = callback;
        if (callback != null) {
            if (getIBtManager() != null) {
                try {
                    IBtManager iBtManager = getIBtManager();
                    if (iBtManager != null) {
                        iBtManager.addBtCallBack(this.iBtCallback);
                        return;
                    }
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
            return;
        }
        if (getIBtManager() != null) {
            try {
                IBtManager iBtManager2 = getIBtManager();
                if (iBtManager2 != null) {
                    iBtManager2.removeBtCallBack(this.iBtCallback);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public final void sendAction(BtConstants.BT_ACTION action) {
        Intrinsics.checkNotNullParameter(action, "action");
        try {
            IBtManager iBtManager = getIBtManager();
            if (iBtManager != null) {
                iBtManager.sendAction(action.name());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void sendDeviceAction(BtConstants.BT_ACTION action, String address) {
        Intrinsics.checkNotNullParameter(action, "action");
        try {
            IBtManager iBtManager = getIBtManager();
            if (iBtManager != null) {
                iBtManager.sendDeviceAction(action.name(), address);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final BtStatus getCurrStatus() {
        try {
            BtStatus.Companion companion = BtStatus.INSTANCE;
            IBtManager iBtManager = getIBtManager();
            Intrinsics.checkNotNull(iBtManager);
            BtStatus btStatusFromJson = companion.fromJson(iBtManager.getState());
            Intrinsics.checkNotNull(btStatusFromJson);
            return btStatusFromJson;
        } catch (Exception e) {
            e.printStackTrace();
            return new BtStatus();
        }
    }

    public final void call(String num) {
        Intrinsics.checkNotNullParameter(num, "num");
        try {
            IBtManager iBtManager = getIBtManager();
            if (iBtManager != null) {
                iBtManager.call(num);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final boolean isAutoConn() {
        try {
            IBtManager iBtManager = getIBtManager();
            Intrinsics.checkNotNull(iBtManager);
            return iBtManager.isAutoConn();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public final String getName() {
        try {
            IBtManager iBtManager = getIBtManager();
            Intrinsics.checkNotNull(iBtManager);
            String name = iBtManager.getName();
            Intrinsics.checkNotNullExpressionValue(name, "iBtManager!!.name");
            return name;
        } catch (Exception e) {
            e.printStackTrace();
            return "AWC001";
        }
    }

    public final void setName(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        try {
            IBtManager iBtManager = getIBtManager();
            if (iBtManager == null) {
                return;
            }
            iBtManager.setName(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final ArrayList<Device> getPairedHfp() {
        try {
            BtUtil btUtil = BtUtil.INSTANCE;
            IBtManager iBtManager = getIBtManager();
            Intrinsics.checkNotNull(iBtManager);
            List<String> pairedHfp = iBtManager.getPairedHfp();
            Intrinsics.checkNotNullExpressionValue(pairedHfp, "iBtManager!!.pairedHfp");
            return btUtil.stringToDevices(pairedHfp);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public final ArrayList<Device> getConHfp() {
        try {
            BtUtil btUtil = BtUtil.INSTANCE;
            IBtManager iBtManager = getIBtManager();
            Intrinsics.checkNotNull(iBtManager);
            List<String> conHfp = iBtManager.getConHfp();
            Intrinsics.checkNotNullExpressionValue(conHfp, "iBtManager!!.conHfp");
            return btUtil.stringToDevices(conHfp);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public final String getLocalAddress() {
        try {
            IBtManager iBtManager = getIBtManager();
            Intrinsics.checkNotNull(iBtManager);
            String localAddress = iBtManager.getLocalAddress();
            Intrinsics.checkNotNullExpressionValue(localAddress, "iBtManager!!.localAddress");
            return localAddress;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public final String getCurrA2dpAddress() {
        try {
            IBtManager iBtManager = getIBtManager();
            Intrinsics.checkNotNull(iBtManager);
            String currA2dpAddress = iBtManager.getCurrA2dpAddress();
            Intrinsics.checkNotNullExpressionValue(currA2dpAddress, "iBtManager!!.currA2dpAddress");
            return currA2dpAddress;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public final String getCurrHfpAddress() {
        try {
            IBtManager iBtManager = getIBtManager();
            Intrinsics.checkNotNull(iBtManager);
            String currHfpAddress = iBtManager.getCurrHfpAddress();
            Intrinsics.checkNotNullExpressionValue(currHfpAddress, "iBtManager!!.currHfpAddress");
            return currHfpAddress;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public final String getMusicTitle() {
        try {
            IBtManager iBtManager = getIBtManager();
            Intrinsics.checkNotNull(iBtManager);
            return iBtManager.getMusicTitle();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public final String getMusicAlbum() {
        try {
            IBtManager iBtManager = getIBtManager();
            Intrinsics.checkNotNull(iBtManager);
            return iBtManager.getMusicAlbum();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public final String getMusicArtist() {
        try {
            IBtManager iBtManager = getIBtManager();
            Intrinsics.checkNotNull(iBtManager);
            return iBtManager.getMusicArtist();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public final boolean isMicOut() {
        try {
            IBtManager iBtManager = getIBtManager();
            Intrinsics.checkNotNull(iBtManager);
            return iBtManager.isMicOut();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public final boolean isMicMute() {
        try {
            IBtManager iBtManager = getIBtManager();
            Intrinsics.checkNotNull(iBtManager);
            return iBtManager.isMicMute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public final BtConstants.CallDevice getCallDevice() {
        try {
            BtConstants.CallDevice[] callDeviceArrValues = BtConstants.CallDevice.values();
            IBtManager iBtManager = getIBtManager();
            Intrinsics.checkNotNull(iBtManager);
            return callDeviceArrValues[iBtManager.getCallDevice()];
        } catch (Exception e) {
            e.printStackTrace();
            return BtConstants.CallDevice.Car;
        }
    }

    public final void sendKey(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        try {
            IBtManager iBtManager = getIBtManager();
            if (iBtManager != null) {
                iBtManager.sendKey(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void sendKey(BtCall btCall, String key) {
        Intrinsics.checkNotNullParameter(btCall, "btCall");
        Intrinsics.checkNotNullParameter(key, "key");
        try {
            IBtManager iBtManager = getIBtManager();
            if (iBtManager != null) {
                iBtManager.sendCallKey(BtCall.INSTANCE.toJsonString(btCall), key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final boolean getPairMode() {
        try {
            IBtManager iBtManager = getIBtManager();
            Intrinsics.checkNotNull(iBtManager);
            return iBtManager.getPairMode();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public final String getPinCode() {
        try {
            IBtManager iBtManager = getIBtManager();
            Intrinsics.checkNotNull(iBtManager);
            String pinCode = iBtManager.getPinCode();
            Intrinsics.checkNotNullExpressionValue(pinCode, "iBtManager!!.pinCode");
            return pinCode;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public final void setPinCode(String pinCode) {
        Intrinsics.checkNotNullParameter(pinCode, "pinCode");
        try {
            IBtManager iBtManager = getIBtManager();
            if (iBtManager == null) {
                return;
            }
            iBtManager.setPinCode(pinCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final boolean enableWechatFilter() {
        try {
            IBtManager iBtManager = getIBtManager();
            Intrinsics.checkNotNull(iBtManager);
            return iBtManager.enableWechatFilter();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public final boolean isWechatFilter() {
        try {
            IBtManager iBtManager = getIBtManager();
            Intrinsics.checkNotNull(iBtManager);
            return iBtManager.isWechatFilter();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public final void setWechatFilter(boolean z) {
        try {
            IBtManager iBtManager = getIBtManager();
            if (iBtManager == null) {
                return;
            }
            iBtManager.setWechatFilter(z);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final ArrayList<BtCall> getCallList() {
        try {
            BtUtil btUtil = BtUtil.INSTANCE;
            IBtManager iBtManager = getIBtManager();
            Intrinsics.checkNotNull(iBtManager);
            List<String> call = iBtManager.getCall();
            Intrinsics.checkNotNullExpressionValue(call, "iBtManager!!.call");
            return btUtil.stringToCall(call);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public final void answer(BtCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        try {
            IBtManager iBtManager = getIBtManager();
            if (iBtManager != null) {
                iBtManager.answer(BtCall.INSTANCE.toJsonString(call));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void handup(BtCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        try {
            IBtManager iBtManager = getIBtManager();
            if (iBtManager != null) {
                iBtManager.handup(BtCall.INSTANCE.toJsonString(call));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final BtCall getWechatCall() {
        try {
            BtCall companion = BtCall.INSTANCE;
            IBtManager iBtManager = getIBtManager();
            return companion.fromJson(iBtManager != null ? iBtManager.getWechatCall() : null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public final boolean isAutoAnswer() {
        try {
            IBtManager iBtManager = getIBtManager();
            Intrinsics.checkNotNull(iBtManager);
            return iBtManager.isAutoAnswer();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
