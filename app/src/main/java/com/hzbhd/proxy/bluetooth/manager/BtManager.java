package com.hzbhd.proxy.bluetooth.manager;

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
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BtManager.kt */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 W2\u00020\u0001:\u0001WB\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020\tJ\u000e\u0010G\u001a\u00020F2\u0006\u0010H\u001a\u00020\u0011J\u0006\u0010I\u001a\u00020%J\b\u0010J\u001a\u0004\u0018\u00010\tJ\u000e\u0010K\u001a\u00020F2\u0006\u0010G\u001a\u00020\tJ\b\u0010L\u001a\u00020FH\u0002J\u000e\u0010M\u001a\u00020F2\u0006\u0010N\u001a\u00020OJ\u0018\u0010P\u001a\u00020F2\u0006\u0010N\u001a\u00020O2\b\u0010Q\u001a\u0004\u0018\u00010\u0011J\u0016\u0010R\u001a\u00020F2\u0006\u0010S\u001a\u00020\t2\u0006\u0010T\u001a\u00020\u0011J\u000e\u0010R\u001a\u00020F2\u0006\u0010T\u001a\u00020\u0011J\u0010\u0010U\u001a\u00020F2\b\u0010V\u001a\u0004\u0018\u000102R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R!\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR!\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\bj\b\u0012\u0004\u0012\u00020\u000e`\n8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0010\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0013R\u0011\u0010\u0016\u001a\u00020\u00178F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u0016\u0010 \u001a\u0004\u0018\u00010!8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u0011\u0010$\u001a\u00020%8F¢\u0006\u0006\u001a\u0004\b$\u0010&R\u0011\u0010'\u001a\u00020%8F¢\u0006\u0006\u001a\u0004\b'\u0010&R\u0011\u0010(\u001a\u00020%8F¢\u0006\u0006\u001a\u0004\b(\u0010&R\u0011\u0010)\u001a\u00020%8F¢\u0006\u0006\u001a\u0004\b)\u0010&R\u0011\u0010*\u001a\u00020%8F¢\u0006\u0006\u001a\u0004\b*\u0010&R$\u0010,\u001a\u00020%2\u0006\u0010+\u001a\u00020%8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b,\u0010&\"\u0004\b-\u0010.R\u0011\u0010/\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b0\u0010\u0013R\u0010\u00101\u001a\u0004\u0018\u000102X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00103\u001a\u0004\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000R\u0013\u00104\u001a\u0004\u0018\u00010\u00118F¢\u0006\u0006\u001a\u0004\b5\u0010\u0013R\u0013\u00106\u001a\u0004\u0018\u00010\u00118F¢\u0006\u0006\u001a\u0004\b7\u0010\u0013R\u0013\u00108\u001a\u0004\u0018\u00010\u00118F¢\u0006\u0006\u001a\u0004\b9\u0010\u0013R$\u0010:\u001a\u00020\u00112\u0006\u0010:\u001a\u00020\u00118F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b;\u0010\u0013\"\u0004\b<\u0010=R\u0011\u0010>\u001a\u00020%8F¢\u0006\u0006\u001a\u0004\b?\u0010&R!\u0010@\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\bj\b\u0012\u0004\u0012\u00020\u000e`\n8F¢\u0006\u0006\u001a\u0004\bA\u0010\fR$\u0010B\u001a\u00020\u00112\u0006\u0010B\u001a\u00020\u00118F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bC\u0010\u0013\"\u0004\bD\u0010=¨\u0006X"}, d2 = {"Lcom/hzbhd/proxy/bluetooth/manager/BtManager;", "", "()V", "callDevice", "Lcom/hzbhd/constant/bluetooth/BtConstants$CallDevice;", "getCallDevice", "()Lcom/hzbhd/constant/bluetooth/BtConstants$CallDevice;", "callList", "Ljava/util/ArrayList;", "Lcom/hzbhd/proxy/bluetooth/bean/BtCall;", "Lkotlin/collections/ArrayList;", "getCallList", "()Ljava/util/ArrayList;", "conHfp", "Lcom/hzbhd/proxy/bluetooth/bean/Device;", "getConHfp", "currA2dpAddress", "", "getCurrA2dpAddress", "()Ljava/lang/String;", "currHfpAddress", "getCurrHfpAddress", "currStatus", "Lcom/hzbhd/proxy/bluetooth/bean/BtStatus;", "getCurrStatus", "()Lcom/hzbhd/proxy/bluetooth/bean/BtStatus;", "iBtCallback", "Lcom/hzbhd/proxy/bluetooth/aidl/IBtCallback;", "getIBtCallback", "()Lcom/hzbhd/proxy/bluetooth/aidl/IBtCallback;", "setIBtCallback", "(Lcom/hzbhd/proxy/bluetooth/aidl/IBtCallback;)V", "iBtManager", "Lcom/hzbhd/proxy/bluetooth/aidl/IBtManager;", "getIBtManager", "()Lcom/hzbhd/proxy/bluetooth/aidl/IBtManager;", "isAutoAnswer", "", "()Z", "isAutoConn", "isConn", "isMicMute", "isMicOut", "filter", "isWechatFilter", "setWechatFilter", "(Z)V", "localAddress", "getLocalAddress", "mBtListener", "Lcom/hzbhd/proxy/bluetooth/listener/BtListener;", "mIBtManager", "musicAlbum", "getMusicAlbum", "musicArtist", "getMusicArtist", "musicTitle", "getMusicTitle", LcdInfoShare.MediaInfo.name, "getName", "setName", "(Ljava/lang/String;)V", "pairMode", "getPairMode", "pairedHfp", "getPairedHfp", "pinCode", "getPinCode", "setPinCode", "answer", "", NotificationCompat.CATEGORY_CALL, "num", "enableWechatFilter", "getWechatCall", "handup", "onServiceConnected", "sendAction", "action", "Lcom/hzbhd/constant/bluetooth/BtConstants$BT_ACTION;", "sendDeviceAction", "address", "sendKey", "btCall", "key", "setBtCallBack", "callback", "Companion", "bt-proxy_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
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
            if (this.this$0.mBtListener != null) {
                BtListener btListener = this.this$0.mBtListener;
                Intrinsics.checkNotNull(btListener);
                btListener.onCloseA2dp();
            } else if (LogUtil.log3()) {
                LogUtil.d("iBtCallback: listener is null");
            }
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void onCloseHfp() {
            if (this.this$0.mBtListener != null) {
                BtListener btListener = this.this$0.mBtListener;
                Intrinsics.checkNotNull(btListener);
                btListener.onCloseHfp();
            } else if (LogUtil.log3()) {
                LogUtil.d("iBtCallback: listener is null");
            }
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void updateBtStatus(int changeState, String status) {
            Intrinsics.checkNotNullParameter(status, "status");
            if (this.this$0.mBtListener != null) {
                BtListener btListener = this.this$0.mBtListener;
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
            if (this.this$0.mBtListener != null) {
                BtListener btListener = this.this$0.mBtListener;
                Intrinsics.checkNotNull(btListener);
                btListener.updateIsAutoConn(autoConn);
            } else if (LogUtil.log3()) {
                LogUtil.d("iBtCallback: listener is null");
            }
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void updateName(String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            if (this.this$0.mBtListener != null) {
                BtListener btListener = this.this$0.mBtListener;
                Intrinsics.checkNotNull(btListener);
                btListener.updateName(name);
            } else if (LogUtil.log3()) {
                LogUtil.d("iBtCallback: listener is null");
            }
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void onFoundDeviceChange(List<String> stringList) {
            Intrinsics.checkNotNullParameter(stringList, "stringList");
            if (this.this$0.mBtListener != null) {
                BtListener btListener = this.this$0.mBtListener;
                Intrinsics.checkNotNull(btListener);
                btListener.onVisibleDeviceChange(BtUtil.INSTANCE.stringToDevices(stringList));
            } else if (LogUtil.log3()) {
                LogUtil.d("iBtCallback: listener is null");
            }
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void onHfpConnChange(List<String> hfpList) {
            Intrinsics.checkNotNullParameter(hfpList, "hfpList");
            if (this.this$0.mBtListener != null) {
                BtListener btListener = this.this$0.mBtListener;
                Intrinsics.checkNotNull(btListener);
                btListener.onHfpConnChange(BtUtil.INSTANCE.stringToDevices(hfpList));
            } else if (LogUtil.log3()) {
                LogUtil.d("iBtCallback: listener is null");
            }
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void onCurrHfpAddressChange(String address) {
            Intrinsics.checkNotNullParameter(address, "address");
            if (this.this$0.mBtListener != null) {
                BtListener btListener = this.this$0.mBtListener;
                Intrinsics.checkNotNull(btListener);
                btListener.onCurrHfpAddressChange(address);
            } else if (LogUtil.log3()) {
                LogUtil.d("iBtCallback: listener is null");
            }
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void onCurrA2dpAddressChange(String address) {
            Intrinsics.checkNotNullParameter(address, "address");
            if (this.this$0.mBtListener != null) {
                BtListener btListener = this.this$0.mBtListener;
                Intrinsics.checkNotNull(btListener);
                btListener.onCurrA2dpAddressChange(address);
            } else if (LogUtil.log3()) {
                LogUtil.d("iBtCallback: listener is null");
            }
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void onMicOutChange(boolean micOut) {
            if (this.this$0.mBtListener != null) {
                BtListener btListener = this.this$0.mBtListener;
                Intrinsics.checkNotNull(btListener);
                btListener.onMicOutChange(micOut);
            } else if (LogUtil.log3()) {
                LogUtil.d("iBtCallback: listener is null");
            }
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void onPairedChange(List<String> pairList) {
            Intrinsics.checkNotNullParameter(pairList, "pairList");
            if (this.this$0.mBtListener != null) {
                BtListener btListener = this.this$0.mBtListener;
                Intrinsics.checkNotNull(btListener);
                btListener.onPairedChange(BtUtil.INSTANCE.stringToDevices(pairList));
            } else if (LogUtil.log3()) {
                LogUtil.d("iBtCallback: listener is null");
            }
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void updateId3(String title, String artist, String album) {
            if (this.this$0.mBtListener != null) {
                BtListener btListener = this.this$0.mBtListener;
                Intrinsics.checkNotNull(btListener);
                btListener.updateId3(title, artist, album);
            } else if (LogUtil.log3()) {
                LogUtil.d("iBtCallback: listener is null");
            }
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void updateMicMute(boolean mute) {
            if (this.this$0.mBtListener != null) {
                BtListener btListener = this.this$0.mBtListener;
                Intrinsics.checkNotNull(btListener);
                btListener.updateMicMute(mute);
            } else if (LogUtil.log3()) {
                LogUtil.d("iBtCallback: listener is null");
            }
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void updateCallDevice(int callDevice) {
            if (this.this$0.mBtListener != null) {
                BtListener btListener = this.this$0.mBtListener;
                Intrinsics.checkNotNull(btListener);
                btListener.updateCallDevice(BtConstants.CallDevice.values()[callDevice]);
            } else if (LogUtil.log3()) {
                LogUtil.d("iBtCallback: listener is null");
            }
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void updateCall(List<String> call, String changeCall) {
            Intrinsics.checkNotNullParameter(call, "call");
            if (this.this$0.mBtListener != null) {
                BtListener btListener = this.this$0.mBtListener;
                Intrinsics.checkNotNull(btListener);
                btListener.updateCall(BtUtil.INSTANCE.stringToCall(call), changeCall != null ? BtCall.INSTANCE.fromJson(changeCall) : null);
            } else if (LogUtil.log3()) {
                LogUtil.d("iBtCallback: listener is null");
            }
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void updateWechatCall(String changeCall) {
            if (this.this$0.mBtListener != null) {
                BtListener btListener = this.this$0.mBtListener;
                Intrinsics.checkNotNull(btListener);
                btListener.updateWechatCall(changeCall != null ? BtCall.INSTANCE.fromJson(changeCall) : null);
            } else if (LogUtil.log3()) {
                LogUtil.d("iBtCallback: listener is null");
            }
        }

        @Override // com.hzbhd.proxy.bluetooth.aidl.IBtCallback
        public void updateIsAutoAnswer(boolean autoAnswer) {
            if (this.this$0.mBtListener != null) {
                BtListener btListener = this.this$0.mBtListener;
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
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<BtManager> instance$delegate = LazyKt.lazy(new Function0<BtManager>() { // from class: com.hzbhd.proxy.bluetooth.manager.BtManager$Companion$instance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final BtManager invoke() {
            return new BtManager();
        }
    });

    /* JADX WARN: Removed duplicated region for block: B:12:0x002d A[Catch: all -> 0x0031, TRY_LEAVE, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0007, B:8:0x0015, B:11:0x002a, B:12:0x002d), top: B:18:0x0003, inners: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected final com.hzbhd.proxy.bluetooth.aidl.IBtManager getIBtManager() {
        /*
            r4 = this;
            java.lang.Class<com.hzbhd.proxy.bluetooth.manager.BtManager> r0 = com.hzbhd.proxy.bluetooth.manager.BtManager.class
            monitor-enter(r0)
            com.hzbhd.proxy.bluetooth.aidl.IBtManager r1 = r4.mIBtManager     // Catch: java.lang.Throwable -> L31
            if (r1 != 0) goto L2d
            java.lang.String r1 = "hzbhd_bluetooth"
            android.os.IBinder r1 = android.os.ServiceManager.getService(r1)     // Catch: java.lang.Throwable -> L31
            com.hzbhd.proxy.bluetooth.aidl.IBtManager r1 = com.hzbhd.proxy.bluetooth.aidl.IBtManager.Stub.asInterface(r1)     // Catch: java.lang.Throwable -> L31
            r4.mIBtManager = r1     // Catch: java.lang.Throwable -> L31
            if (r1 == 0) goto L2d
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch: java.lang.Exception -> L29 java.lang.Throwable -> L31
            android.os.IBinder r1 = r1.asBinder()     // Catch: java.lang.Exception -> L29 java.lang.Throwable -> L31
            com.hzbhd.proxy.bluetooth.manager.BtManager$$ExternalSyntheticLambda0 r2 = new com.hzbhd.proxy.bluetooth.manager.BtManager$$ExternalSyntheticLambda0     // Catch: java.lang.Exception -> L29 java.lang.Throwable -> L31
            r2.<init>()     // Catch: java.lang.Exception -> L29 java.lang.Throwable -> L31
            r3 = 0
            r1.linkToDeath(r2, r3)     // Catch: java.lang.Exception -> L29 java.lang.Throwable -> L31
            r4.onServiceConnected()     // Catch: java.lang.Exception -> L29 java.lang.Throwable -> L31
            goto L2d
        L29:
            r1 = move-exception
            r1.printStackTrace()     // Catch: java.lang.Throwable -> L31
        L2d:
            com.hzbhd.proxy.bluetooth.aidl.IBtManager r1 = r4.mIBtManager     // Catch: java.lang.Throwable -> L31
            monitor-exit(r0)
            return r1
        L31:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.proxy.bluetooth.manager.BtManager.getIBtManager():com.hzbhd.proxy.bluetooth.aidl.IBtManager");
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
        ServiceStateManager.getInstance().registerConnectListener(SourceConstantsDef.MODULE_ID.BLUETOOTH.ordinal(), new IServiceConnectListener() { // from class: com.hzbhd.proxy.bluetooth.manager.BtManager$$ExternalSyntheticLambda1
            @Override // com.hzbhd.systemstatus.proxy.IServiceConnectListener
            public final void onConnected() {
                BtManager.onServiceConnected$lambda$2(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServiceConnected$lambda$2(BtManager this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.mBtListener != null) {
            try {
                if (LogUtil.log5()) {
                    LogUtil.d("onServiceConnected: bt");
                }
                IBtManager iBtManager = this$0.getIBtManager();
                if (iBtManager != null) {
                    iBtManager.addBtCallBack(this$0.iBtCallback);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
            BtCall.Companion companion = BtCall.INSTANCE;
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

    /* compiled from: BtManager.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/hzbhd/proxy/bluetooth/manager/BtManager$Companion;", "", "()V", "SERVICE_NAME_BT", "", "instance", "Lcom/hzbhd/proxy/bluetooth/manager/BtManager;", "getInstance", "()Lcom/hzbhd/proxy/bluetooth/manager/BtManager;", "instance$delegate", "Lkotlin/Lazy;", "bt-proxy_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final BtManager getInstance() {
            return (BtManager) BtManager.instance$delegate.getValue();
        }
    }
}
