package com.hzbhd.proxy.camera.manager;

import android.os.IBinder;
import android.os.RemoteException;
import android.view.Surface;

import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.proxy.camera.aidl.ICameraCallback;
import com.hzbhd.proxy.camera.aidl.ICameraManager;
import com.hzbhd.proxy.camera.listener.ICameraListener;
import com.hzbhd.systemstatus.proxy.IServiceConnectListener;
import com.hzbhd.systemstatus.proxy.ServiceStateManager;
import com.hzbhd.util.LogUtil;

import java.lang.reflect.Method;
import java.util.HashMap;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;


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
            public void onConnected() {
                INSTANCE.onServiceConn();
            }
        });
    }


    private synchronized ICameraManager getMICameraManager() {
        if (mICameraManager == null) {
            try {
                Method method = Class.forName("android.os.ServiceManager").getMethod("getService", String.class);
                IBinder binder = (IBinder) method.invoke(null, SERVICE_NAME_CAMERA);
                ICameraManager asInterface = ICameraManager.Stub.asInterface(binder);
                if (asInterface != null) {
                    // from class: com.hzbhd.proxy.camera.manager.CameraManager$$ExternalSyntheticLambda0
// android.os.IBinder.DeathRecipient
                    asInterface.asBinder().linkToDeath(() -> CameraManager.mICameraManager = null, 0);
                    mICameraManager = asInterface;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mICameraManager;
    }

    public void startPreview(int flag, Surface surface) {

        try {
            ICameraManager mICameraManager2 = getMICameraManager();
            if (mICameraManager2 != null) {
                mICameraManager2.startPreviewWithCallBack(flag, surface, callbackMaps.get(flag));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startTestPreview(int flag) {
        try {
            ICameraManager mICameraManager2 = getMICameraManager();
            if (mICameraManager2 != null) {
                mICameraManager2.startPreviewWithCallBack(flag, null, callbackMaps.get(flag));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopPreview(int flag) {
        try {
            ICameraManager mICameraManager2 = getMICameraManager();
            if (mICameraManager2 != null) {
                mICameraManager2.stopPreview(flag);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setFlagAttr(int flag, int type, int value) {
        try {
            ICameraManager mICameraManager2 = getMICameraManager();
            if (mICameraManager2 != null) {
                mICameraManager2.setFlagAttr(flag, type, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getFlagAttr(int flag, int type) {
        try {
            ICameraManager mICameraManager2 = getMICameraManager();
            Integer numValueOf = mICameraManager2 != null ? mICameraManager2.getFlagAttr(flag, type) : null;

            return numValueOf.intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void startRecord(int flag, Surface surface) {
        try {
            ICameraManager mICameraManager2 = getMICameraManager();
            if (mICameraManager2 != null) {
                mICameraManager2.startRecord(flag, surface);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopRecord(int flag) {
        try {
            ICameraManager mICameraManager2 = getMICameraManager();
            if (mICameraManager2 != null) {
                mICameraManager2.stopRecord(flag);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCameraInfo(int flag, int type, String value) {

        try {
            ICameraManager mICameraManager2 = getMICameraManager();
            if (mICameraManager2 != null) {
                mICameraManager2.setCameraInfo(flag, type, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getCameraInfo(int flag, int type) {
        try {
            ICameraManager mICameraManager2 = getMICameraManager();
            String cameraInfo = mICameraManager2 != null ? mICameraManager2.getCameraInfo(flag, type) : null;

            return cameraInfo;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private void onServiceConn() {
        if (LogUtil.log5()) {
            LogUtil.d("onServiceConn: " + (getMICameraManager() != null));
        }
        try {
            for (Integer cameraFlag : callbackMaps.keySet()) {
                ICameraManager mICameraManager2 = getMICameraManager();
                if (mICameraManager2 != null) {

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

    public void setListener(int cameraFlag, ICameraListener iCameraListener) throws RemoteException {

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

    public static final class MyICameraCallback extends ICameraCallback.Stub {
        private final int cameraFlag;

        public MyICameraCallback(int i) {
            this.cameraFlag = i;
        }

        public int getCameraFlag() {
            return this.cameraFlag;
        }

        @Override // com.hzbhd.proxy.camera.aidl.ICameraCallback
        public void onSignalChange(int signal) {
            ICameraListener iCameraListener = CameraManager.listenerMaps.get(Integer.valueOf(this.cameraFlag));
            if (iCameraListener != null) {
                iCameraListener.onSignalChange(signal);
            }
        }

        @Override // com.hzbhd.proxy.camera.aidl.ICameraCallback
        public void onPreviewState(int state) {
            ICameraListener iCameraListener = CameraManager.listenerMaps.get(Integer.valueOf(this.cameraFlag));
            if (iCameraListener != null) {
                iCameraListener.onPreviewState(state);
            }
        }

        @Override // com.hzbhd.proxy.camera.aidl.ICameraCallback
        public void onAttrChange(int attrType, int value) {
            ICameraListener iCameraListener = CameraManager.listenerMaps.get(Integer.valueOf(this.cameraFlag));
            if (iCameraListener != null) {
                iCameraListener.onAttrChange(attrType, value);
            }
        }

        @Override // com.hzbhd.proxy.camera.aidl.ICameraCallback
        public void onInfoChange(int attrType, String value) {
            ICameraListener iCameraListener = CameraManager.listenerMaps.get(Integer.valueOf(this.cameraFlag));
            if (iCameraListener != null) {
                iCameraListener.onInfoChange(attrType, value);
            }
        }
    }
}
