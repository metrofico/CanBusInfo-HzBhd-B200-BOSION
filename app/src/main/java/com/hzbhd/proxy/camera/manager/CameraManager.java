package com.hzbhd.proxy.camera.manager;

import android.os.RemoteException;
import android.view.Surface;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.proxy.camera.aidl.ICameraCallback;
import com.hzbhd.proxy.camera.aidl.ICameraManager;
import com.hzbhd.proxy.camera.listener.ICameraListener;
import com.hzbhd.systemstatus.proxy.IServiceConnectListener;
import com.hzbhd.systemstatus.proxy.ServiceStateManager;
import com.hzbhd.util.LogUtil;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CameraManager.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001#B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0007J\u0016\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0007J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\u001e\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u0004J\u001e\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u0007J\u0016\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u000bJ\u0016\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u001eJ\u0018\u0010\u001f\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u00072\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eJ\u000e\u0010 \u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u0007J\u000e\u0010!\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u0007J\u000e\u0010\"\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R*\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b`\tX\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\n\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000b0\u0006j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000b`\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\f\u001a\u0004\u0018\u00010\r8BX\u0082\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006$"}, d2 = {"Lcom/hzbhd/proxy/camera/manager/CameraManager;", "", "()V", "SERVICE_NAME_CAMERA", "", "callbackMaps", "Ljava/util/HashMap;", "", "Lcom/hzbhd/proxy/camera/manager/CameraManager$MyICameraCallback;", "Lkotlin/collections/HashMap;", "listenerMaps", "Lcom/hzbhd/proxy/camera/listener/ICameraListener;", "mICameraManager", "Lcom/hzbhd/proxy/camera/aidl/ICameraManager;", "getMICameraManager", "()Lcom/hzbhd/proxy/camera/aidl/ICameraManager;", "getCameraInfo", "flag", "type", "getFlagAttr", "onServiceConn", "", "setCameraInfo", "value", "setFlagAttr", "setListener", "cameraFlag", "iCameraListener", "startPreview", "surface", "Landroid/view/Surface;", "startRecord", "startTestPreview", "stopPreview", "stopRecord", "MyICameraCallback", "camera-proxy_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class CameraManager {
    public static final String SERVICE_NAME_CAMERA = "hzbhd_camera";
    private static ICameraManager mICameraManager;
    public static final CameraManager INSTANCE = new CameraManager();
    private static final HashMap<Integer, ICameraListener> listenerMaps = new HashMap<>();
    private static final HashMap<Integer, MyICameraCallback> callbackMaps = new HashMap<>();

    private CameraManager() {
    }

    static {
        ServiceStateManager.getInstance().registerConnectListener(SourceConstantsDef.MODULE_ID.CAMERA.ordinal(), new IServiceConnectListener() { // from class: com.hzbhd.proxy.camera.manager.CameraManager$$ExternalSyntheticLambda1
            @Override // com.hzbhd.systemstatus.proxy.IServiceConnectListener
            public final void onConnected() {
                CameraManager.m1205_init_$lambda0();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-0, reason: not valid java name */
    public static final void m1205_init_$lambda0() {
        INSTANCE.onServiceConn();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0025 A[Catch: all -> 0x0029, TRY_LEAVE, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0005, B:7:0x0011, B:11:0x0025, B:10:0x0022), top: B:17:0x0001, inners: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final synchronized com.hzbhd.proxy.camera.aidl.ICameraManager getMICameraManager() {
        /*
            r4 = this;
            monitor-enter(r4)
            com.hzbhd.proxy.camera.aidl.ICameraManager r0 = com.hzbhd.proxy.camera.manager.CameraManager.mICameraManager     // Catch: java.lang.Throwable -> L29
            if (r0 != 0) goto L25
            java.lang.String r0 = "hzbhd_camera"
            android.os.IBinder r0 = android.os.ServiceManager.getService(r0)     // Catch: java.lang.Exception -> L21 java.lang.Throwable -> L29
            com.hzbhd.proxy.camera.aidl.ICameraManager r0 = com.hzbhd.proxy.camera.aidl.ICameraManager.Stub.asInterface(r0)     // Catch: java.lang.Exception -> L21 java.lang.Throwable -> L29
            if (r0 == 0) goto L25
            android.os.IBinder r1 = r0.asBinder()     // Catch: java.lang.Exception -> L21 java.lang.Throwable -> L29
            com.hzbhd.proxy.camera.manager.CameraManager$$ExternalSyntheticLambda0 r2 = new com.hzbhd.proxy.camera.manager.CameraManager$$ExternalSyntheticLambda0     // Catch: java.lang.Exception -> L21 java.lang.Throwable -> L29
            r2.<init>()     // Catch: java.lang.Exception -> L21 java.lang.Throwable -> L29
            r3 = 0
            r1.linkToDeath(r2, r3)     // Catch: java.lang.Exception -> L21 java.lang.Throwable -> L29
            com.hzbhd.proxy.camera.manager.CameraManager.mICameraManager = r0     // Catch: java.lang.Exception -> L21 java.lang.Throwable -> L29
            goto L25
        L21:
            r0 = move-exception
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L29
        L25:
            com.hzbhd.proxy.camera.aidl.ICameraManager r0 = com.hzbhd.proxy.camera.manager.CameraManager.mICameraManager     // Catch: java.lang.Throwable -> L29
            monitor-exit(r4)
            return r0
        L29:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.proxy.camera.manager.CameraManager.getMICameraManager():com.hzbhd.proxy.camera.aidl.ICameraManager");
    }

    public final void startPreview(int flag, Surface surface) {
        Intrinsics.checkNotNullParameter(surface, "surface");
        try {
            ICameraManager mICameraManager2 = getMICameraManager();
            if (mICameraManager2 != null) {
                mICameraManager2.startPreviewWithCallBack(flag, surface, callbackMaps.get(Integer.valueOf(flag)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void startTestPreview(int flag) {
        try {
            ICameraManager mICameraManager2 = getMICameraManager();
            if (mICameraManager2 != null) {
                mICameraManager2.startPreviewWithCallBack(flag, null, callbackMaps.get(Integer.valueOf(flag)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void stopPreview(int flag) {
        try {
            ICameraManager mICameraManager2 = getMICameraManager();
            if (mICameraManager2 != null) {
                mICameraManager2.stopPreview(flag);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void setFlagAttr(int flag, int type, int value) {
        try {
            ICameraManager mICameraManager2 = getMICameraManager();
            if (mICameraManager2 != null) {
                mICameraManager2.setFlagAttr(flag, type, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final int getFlagAttr(int flag, int type) {
        try {
            ICameraManager mICameraManager2 = getMICameraManager();
            Integer numValueOf = mICameraManager2 != null ? Integer.valueOf(mICameraManager2.getFlagAttr(flag, type)) : null;
            Intrinsics.checkNotNull(numValueOf);
            return numValueOf.intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public final void startRecord(int flag, Surface surface) {
        try {
            ICameraManager mICameraManager2 = getMICameraManager();
            if (mICameraManager2 != null) {
                mICameraManager2.startRecord(flag, surface);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void stopRecord(int flag) {
        try {
            ICameraManager mICameraManager2 = getMICameraManager();
            if (mICameraManager2 != null) {
                mICameraManager2.stopRecord(flag);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void setCameraInfo(int flag, int type, String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        try {
            ICameraManager mICameraManager2 = getMICameraManager();
            if (mICameraManager2 != null) {
                mICameraManager2.setCameraInfo(flag, type, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final String getCameraInfo(int flag, int type) {
        try {
            ICameraManager mICameraManager2 = getMICameraManager();
            String cameraInfo = mICameraManager2 != null ? mICameraManager2.getCameraInfo(flag, type) : null;
            Intrinsics.checkNotNull(cameraInfo);
            return cameraInfo;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private final void onServiceConn() {
        if (LogUtil.log5()) {
            LogUtil.d("onServiceConn: " + (getMICameraManager() != null));
        }
        try {
            for (Integer cameraFlag : callbackMaps.keySet()) {
                ICameraManager mICameraManager2 = getMICameraManager();
                if (mICameraManager2 != null) {
                    Intrinsics.checkNotNullExpressionValue(cameraFlag, "cameraFlag");
                    mICameraManager2.addCallBack(cameraFlag.intValue(), callbackMaps.get(cameraFlag));
                }
                ICameraListener iCameraListener = listenerMaps.get(cameraFlag);
                if (iCameraListener != null) {
                    iCameraListener.onServiceConn();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void setListener(int cameraFlag, ICameraListener iCameraListener) throws RemoteException {
        Intrinsics.checkNotNullParameter(iCameraListener, "iCameraListener");
        HashMap<Integer, ICameraListener> map = listenerMaps;
        if (map.containsKey(Integer.valueOf(cameraFlag))) {
            map.replace(Integer.valueOf(cameraFlag), iCameraListener);
        } else {
            map.put(Integer.valueOf(cameraFlag), iCameraListener);
        }
        HashMap<Integer, MyICameraCallback> map2 = callbackMaps;
        if (map2.containsKey(Integer.valueOf(cameraFlag))) {
            return;
        }
        map2.put(Integer.valueOf(cameraFlag), new MyICameraCallback(cameraFlag));
        ICameraManager mICameraManager2 = getMICameraManager();
        if (mICameraManager2 != null) {
            mICameraManager2.addCallBack(cameraFlag, map2.get(Integer.valueOf(cameraFlag)));
        }
    }

    /* compiled from: CameraManager.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0003H\u0016J\u001a\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\fH\u0016J\u0010\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u0003H\u0016J\u0010\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/hzbhd/proxy/camera/manager/CameraManager$MyICameraCallback;", "Lcom/hzbhd/proxy/camera/aidl/ICameraCallback$Stub;", "cameraFlag", "", "(I)V", "getCameraFlag", "()I", "onAttrChange", "", "attrType", "value", "onInfoChange", "", "onPreviewState", "state", "onSignalChange", "signal", "camera-proxy_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class MyICameraCallback extends ICameraCallback.Stub {
        private final int cameraFlag;

        public MyICameraCallback(int i) {
            this.cameraFlag = i;
        }

        public final int getCameraFlag() {
            return this.cameraFlag;
        }

        @Override // com.hzbhd.proxy.camera.aidl.ICameraCallback
        public void onSignalChange(int signal) {
            ICameraListener iCameraListener = (ICameraListener) CameraManager.listenerMaps.get(Integer.valueOf(this.cameraFlag));
            if (iCameraListener != null) {
                iCameraListener.onSignalChange(signal);
            }
        }

        @Override // com.hzbhd.proxy.camera.aidl.ICameraCallback
        public void onPreviewState(int state) {
            ICameraListener iCameraListener = (ICameraListener) CameraManager.listenerMaps.get(Integer.valueOf(this.cameraFlag));
            if (iCameraListener != null) {
                iCameraListener.onPreviewState(state);
            }
        }

        @Override // com.hzbhd.proxy.camera.aidl.ICameraCallback
        public void onAttrChange(int attrType, int value) {
            ICameraListener iCameraListener = (ICameraListener) CameraManager.listenerMaps.get(Integer.valueOf(this.cameraFlag));
            if (iCameraListener != null) {
                iCameraListener.onAttrChange(attrType, value);
            }
        }

        @Override // com.hzbhd.proxy.camera.aidl.ICameraCallback
        public void onInfoChange(int attrType, String value) {
            ICameraListener iCameraListener = (ICameraListener) CameraManager.listenerMaps.get(Integer.valueOf(this.cameraFlag));
            if (iCameraListener != null) {
                iCameraListener.onInfoChange(attrType, value);
            }
        }
    }
}
