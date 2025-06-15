package com.hzbhd.proxy.mcu.core;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;

import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.proxy.mcu.aidl.IMCUCanBoxControlCallback;
import com.hzbhd.proxy.mcu.aidl.IMCUMainCallback;
import com.hzbhd.proxy.mcu.aidl.IMCUMainService;
import com.hzbhd.proxy.mcu.aidl.IMCUMsgCallback;
import com.hzbhd.proxy.mcu.constant.MCU_MESSAGE_PEER;
import com.hzbhd.proxy.service.ServiceConstants;
import com.hzbhd.systemstatus.proxy.IServiceConnectListener;
import com.hzbhd.systemstatus.proxy.ServiceStateManager;
import com.hzbhd.util.LogUtil;
import com.hzbhd.util.ServiceManagerReflection;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes2.dex */
public class MCUMainManager implements IMCUMainManager {
    private static MCUMainManager instance;
    private IMCUMainService mMCUMainService;
    private final Set<IMCUMainListener> mMCUMainList = new HashSet();
    private final HashSet<IMCUCanBoxControlListener> mMCUCanList = new HashSet<>();
    private IMCUMainCallback mMCUImcuMainCallback = new MCUMainCallback();
    private MainHandlerMCUManager handler = new MainHandlerMCUManager();

    public class CanBoxControlCallback extends IMCUCanBoxControlCallback.Stub {

        MCUMainManager mMCUMainManager;

        public CanBoxControlCallback(MCUMainManager mMCUMainManager) {
            this.mMCUMainManager = mMCUMainManager;
        }

        @Override
        public void notifyCanboxData(int cmd, byte[] data) {
            for (IMCUCanBoxControlListener listener : mMCUCanList) {
                listener.notifyCanboxData(cmd, data);
            }
        }
    }

    private final IMCUCanBoxControlCallback mMCUCanBoxControlCallback = new CanBoxControlCallback(this);

    private static class MainHandlerMCUManager extends Handler {


        @Override
        public void handleMessage(Message message) {
            super.handleMessage(message);
            MCUMainManager mainManager = MCUMainManager.getInstance();
            switch (MSG_ID.values()[message.what]) {
                case mcuInit:
                    Bundle mcuInitBundle = (Bundle) message.obj;
                    Iterator<IMCUMainListener> it1 = mainManager.mMCUMainList.iterator();
                    byte protocolVersion = mcuInitBundle.getByte("procotolVersion");
                    boolean powerOnType = mcuInitBundle.getBoolean("powerOnType");
                    boolean hardwareReset = mcuInitBundle.getBoolean("hardwareReset");
                    while (it1.hasNext()) {
                        it1.next().mcuInit(protocolVersion, powerOnType, hardwareReset);
                    }
                    break;

                case notifyMCUVersion:
                    Bundle mcuVersionBundle = (Bundle) message.obj;
                    String mcuVersion = mcuVersionBundle.getString("McuVersion");
                    for (IMCUMainListener item : mainManager.mMCUMainList) {
                        item.notifyMCUVersion(mcuVersion, null, null);
                    }
                    break;

                case notifyPowerStatus:
                    Integer powerStatusValue = (Integer) message.obj;
                    for (IMCUMainListener value : mainManager.mMCUMainList) {
                        value.notifyPowerStatus(powerStatusValue != null ? powerStatusValue : 0);
                    }
                    break;

                case notifyHardwareVersion:
                    Bundle hardwareVersionBundle = (Bundle) message.obj;
                    String modelName = hardwareVersionBundle.getString("modelName");
                    String hardwareVersion = hardwareVersionBundle.getString("hardwareVersion");
                    String serialNum = hardwareVersionBundle.getString("serialNum");
                    String data = hardwareVersionBundle.getString("data");
                    for (IMCUMainListener listener : mainManager.mMCUMainList) {
                        listener.notifyHardwareVersion(modelName, hardwareVersion, serialNum, data);
                    }
                    break;

                case notifyScreenVersion:
                    String screenVersion = (String) message.obj;
                    for (IMCUMainListener mainListener : mainManager.mMCUMainList) {
                        mainListener.notifyScreenVersion(screenVersion);
                    }
                    break;

                case notifyCanboxVersion:
                    String canboxVersion = (String) message.obj;
                    for (IMCUMainListener imcuMainListener : mainManager.mMCUMainList) {
                        imcuMainListener.notifyCanboxVersion(canboxVersion);
                    }
                    break;

                default:
                    break;
            }
        }
    }


    private enum MSG_ID {
        notifyCanboxData, mcuInit, notifyMCUVersion, notifyHardwareVersion, notifyCanboxVersion, notifyScreenVersion, notifyPowerStatus
    }

    @Override // com.hzbhd.proxy.mcu.base.IMCUBaseManager
    public void init() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public IMCUMainService getMCUMainService() {
        if (this.mMCUMainService == null) {
            this.mMCUMainService = IMCUMainService.Stub.asInterface(ServiceManagerReflection.getService(ServiceConstants.SERVICE_NAME_MCU_SERVICE));
            onServiceConn();
        }
        return IMCUMainService.Stub.asInterface(ServiceManagerReflection.getService(ServiceConstants.SERVICE_NAME_MCU_SERVICE));
    }


    public static MCUMainManager getInstance() {
        if (instance == null) {
            synchronized (MCUMainManager.class) {
                if (instance == null) {
                    instance = new MCUMainManager();
                }
            }
        }
        return instance;
    }

    @Override // com.hzbhd.proxy.mcu.core.IMCUMainManager
    public Bundle initMCU() {
        if (LogUtil.log5()) {
            LogUtil.d("initMCU() called");
        }
        IMCUMainService mCUMainService = getMCUMainService();
        if (mCUMainService != null) {
            try {
                return mCUMainService.initMCU();
            } catch (RemoteException e) {
                e.printStackTrace();
                return null;
            }
        }
        if (!LogUtil.log5()) {
            return null;
        }
        LogUtil.d("initMCU() called mMCUMainService == null");
        return null;
    }

    @Override // com.hzbhd.proxy.mcu.core.IMCUMainManager
    public void clearMessage(MCU_MESSAGE_PEER mcu_message_peer) {
        IMCUMainService mCUMainService = getMCUMainService();
        if (mCUMainService != null) {
            try {
                mCUMainService.clearMessage(mcu_message_peer.ordinal());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.hzbhd.proxy.mcu.core.IMCUMainManager
    public void closeDevice() {
        IMCUMainService mCUMainService = getMCUMainService();
        if (mCUMainService != null) {
            try {
                mCUMainService.closeDevice();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.hzbhd.proxy.mcu.core.IMCUMainManager
    public void initSystemReady() {
        IMCUMainService mCUMainService = getMCUMainService();
        if (mCUMainService != null) {
            try {
                mCUMainService.initSystemReady();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.hzbhd.proxy.mcu.core.IMCUMainManager
    public void setSendSleepStatus(boolean z) {
        IMCUMainService mCUMainService = getMCUMainService();
        if (mCUMainService != null) {
            try {
                mCUMainService.setSendSleepStatus(z);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.hzbhd.proxy.mcu.core.IMCUMainManager
    public void startSendHeartBeatMsg() {
        IMCUMainService mCUMainService = getMCUMainService();
        if (mCUMainService != null) {
            try {
                mCUMainService.startSendHeartBeatMsg();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.hzbhd.proxy.mcu.core.IMCUMainManager
    public void stopSendHeartBeatMsg() {
        IMCUMainService mCUMainService = getMCUMainService();
        if (mCUMainService != null) {
            try {
                mCUMainService.stopSendHeartBeatMsg();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.hzbhd.proxy.mcu.core.IMCUMainManager
    public void setUpgradeStatus(boolean z) {
        IMCUMainService mCUMainService = getMCUMainService();
        if (mCUMainService != null) {
            try {
                mCUMainService.setUpgradeStatus(z);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.hzbhd.proxy.mcu.core.IMCUMainManager
    public void sendMCUCanboxData(int i, byte[] bArr) {
        IMCUMainService mCUMainService = getMCUMainService();
        if (mCUMainService != null) {
            try {
                mCUMainService.sendMCUCanboxData(i, bArr);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.hzbhd.proxy.mcu.core.IMCUMainManager
    public void registerMCUCanboxListener(IMCUCanBoxControlListener iMCUCanBoxControlListener) {
        if (!this.mMCUCanList.contains(iMCUCanBoxControlListener)) {
            this.mMCUCanList.add(iMCUCanBoxControlListener);
        }
        IMCUMainService mCUMainService = getMCUMainService();
        if (mCUMainService != null) {
            try {
                mCUMainService.registerMCUCanboxCallback(this.mMCUCanBoxControlCallback);
                iMCUCanBoxControlListener.onMcuConn();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.hzbhd.proxy.mcu.core.IMCUMainManager
    public void unregisterMCUCanboxListener(IMCUCanBoxControlListener iMCUCanBoxControlListener) {
        if (this.mMCUCanList.contains(iMCUCanBoxControlListener)) {
            this.mMCUCanList.remove(iMCUCanBoxControlListener);
        }
        if (this.mMCUCanList.isEmpty()) {
            try {
                IMCUMainService mCUMainService = getMCUMainService();
                if (mCUMainService != null) {
                    mCUMainService.unregisterMCUCanboxCallback(this.mMCUCanBoxControlCallback);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.hzbhd.proxy.mcu.core.IMCUMainManager
    public void requestPowerStatus(int i) {
        IMCUMainService mCUMainService = getMCUMainService();
        if (mCUMainService != null) {
            try {
                mCUMainService.requestPowerStatus(i);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.hzbhd.proxy.mcu.core.IMCUMainManager
    public int getPowerStatus() {
        IMCUMainService mCUMainService = getMCUMainService();
        if (mCUMainService == null) {
            return -1;
        }
        try {
            return mCUMainService.getPowerStatus();
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override // com.hzbhd.proxy.mcu.core.IMCUMainManager
    public void sendTestCmd(byte[] bArr) {
        IMCUMainService mCUMainService = getMCUMainService();
        if (mCUMainService != null) {
            try {
                mCUMainService.sendTestCmd(bArr);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.hzbhd.proxy.mcu.core.IMCUMainManager
    public void registerMcuMsgListener(IMCUMsgCallback iMCUMsgCallback) {
        IMCUMainService mCUMainService = getMCUMainService();
        if (mCUMainService != null) {
            try {
                mCUMainService.registerMcuMsgListener(iMCUMsgCallback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.hzbhd.proxy.mcu.core.IMCUMainManager
    public void unregisterMcuMsgListener(IMCUMsgCallback iMCUMsgCallback) {
        IMCUMainService mCUMainService = getMCUMainService();
        if (mCUMainService != null) {
            try {
                mCUMainService.unregisterMcuMsgListener(iMCUMsgCallback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.hzbhd.proxy.mcu.core.IMCUMainManager
    public byte[] updateFlashData(byte[] bArr) {
        IMCUMainService mCUMainService = getMCUMainService();
        if (mCUMainService == null) {
            return null;
        }
        try {
            return mCUMainService.updateFlashData(bArr);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.hzbhd.proxy.mcu.core.IMCUMainManager
    public void sendMCUDebugData(byte[] bArr) {
        IMCUMainService mCUMainService = getMCUMainService();
        if (mCUMainService != null) {
            try {
                mCUMainService.sendMCUDebugData(bArr);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.hzbhd.proxy.mcu.core.IMCUMainManager
    public int getMcuhardware() {
        IMCUMainService mCUMainService = getMCUMainService();
        if (mCUMainService == null) {
            return -1;
        }
        try {
            return mCUMainService.getMcuHardware();
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override // com.hzbhd.proxy.mcu.core.IMCUMainManager
    public int getHardwareReset() {
        IMCUMainService mCUMainService = getMCUMainService();
        if (mCUMainService == null) {
            return -1;
        }
        try {
            return mCUMainService.getHardwareReset();
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override // com.hzbhd.proxy.mcu.core.IMCUMainManager
    public int getPowerOnType() {
        IMCUMainService mCUMainService = getMCUMainService();
        if (mCUMainService == null) {
            return -1;
        }
        try {
            return mCUMainService.getPowerOnType();
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override // com.hzbhd.proxy.mcu.core.IMCUMainManager
    public byte getProcotolVersion() {
        IMCUMainService mCUMainService = getMCUMainService();
        if (mCUMainService == null) {
            return (byte) 0;
        }
        try {
            return mCUMainService.getProcotolVersion();
        } catch (RemoteException e) {
            e.printStackTrace();
            return (byte) 0;
        }
    }

    @Override // com.hzbhd.proxy.mcu.core.IMCUMainManager
    public int getCarBackState() {
        IMCUMainService mCUMainService = getMCUMainService();
        if (mCUMainService == null) {
            return -1;
        }
        try {
            return mCUMainService.getCarBackState();
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override // com.hzbhd.proxy.mcu.core.IMCUMainManager
    public void getVersion() {
        IMCUMainService mCUMainService = getMCUMainService();
        if (mCUMainService != null) {
            try {
                mCUMainService.getVersion();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.hzbhd.proxy.mcu.core.IMCUMainManager
    public boolean isInitMCU() {
        IMCUMainService mCUMainService = getMCUMainService();
        if (mCUMainService == null) {
            return false;
        }
        try {
            return mCUMainService.isInitMCU();
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.hzbhd.proxy.mcu.core.IMCUMainManager
    public void registerMCUMainListener(IMCUMainListener iMCUMainListener) {
        this.mMCUMainList.add(iMCUMainListener);
    }

    @Override // com.hzbhd.proxy.mcu.core.IMCUMainManager
    public void unRegisterMCUMainListener(IMCUMainListener iMCUMainListener) {
        if (this.mMCUMainList.contains(iMCUMainListener)) {
            this.mMCUMainList.remove(iMCUMainListener);
        }
    }

    public class MCUMainCallback extends IMCUMainCallback.Stub {
        public MCUMainCallback() {
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainCallback
        public void mcuInit(byte b, boolean z, boolean z2) throws RemoteException {
            Bundle bundle = new Bundle();
            bundle.putByte("procotolVersion", b);
            bundle.putBoolean("powerOnType", z);
            bundle.putBoolean("hardwareReset", z2);
            handler.obtainMessage(MSG_ID.mcuInit.ordinal(), bundle).sendToTarget();
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainCallback
        public void notifyMCUVersion(String str, String str2, String str3) throws RemoteException {
            Bundle bundle = new Bundle();
            bundle.putString("softVerion", str);
            bundle.putString("softDate", str2);
            bundle.putString("hardwareConfig", str3);
            handler.obtainMessage(MSG_ID.notifyMCUVersion.ordinal(), bundle).sendToTarget();
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainCallback
        public void notifyHardwareVersion(String str, String str2, String str3, String str4) throws RemoteException {
            Bundle bundle = new Bundle();
            bundle.putString("modelName", str);
            bundle.putString("hardwareVersion", str2);
            bundle.putString("serialNum", str3);
            bundle.putString("data", str4);
            handler.obtainMessage(MSG_ID.notifyHardwareVersion.ordinal(), bundle).sendToTarget();
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainCallback
        public void notifyCanboxVersion(String str) throws RemoteException {
            handler.obtainMessage(MSG_ID.notifyCanboxVersion.ordinal(), str).sendToTarget();
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainCallback
        public void notifyScreenVersion(String str) throws RemoteException {
            handler.obtainMessage(MSG_ID.notifyScreenVersion.ordinal(), str).sendToTarget();
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainCallback
        public void notifyPowerStatus(int i) throws RemoteException {
            handler.obtainMessage(MSG_ID.notifyPowerStatus.ordinal(), i).sendToTarget();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyMcuConn() {
        Iterator<IMCUCanBoxControlListener> it = this.mMCUCanList.iterator();
        while (it.hasNext()) {
            it.next().onMcuConn();
        }
    }

    private void onServiceConn() {
        ServiceStateManager.getInstance().registerConnectListener(SourceConstantsDef.MODULE_ID.MCU.ordinal(), new IServiceConnectListener() { // from class: com.hzbhd.proxy.mcu.core.MCUMainManager.3
            @Override // com.hzbhd.systemstatus.proxy.IServiceConnectListener
            public void onConnected() {
                try {
                    if (LogUtil.log5()) {
                        LogUtil.d("onServiceConnected: mcu");
                    }
                    IMCUMainService mCUMainService = MCUMainManager.this.getMCUMainService();
                    if (mCUMainService != null) {
                        try {
                            mCUMainService.registerMCUCanboxCallback(MCUMainManager.this.mMCUCanBoxControlCallback);
                            MCUMainManager.this.notifyMcuConn();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
    }
}
