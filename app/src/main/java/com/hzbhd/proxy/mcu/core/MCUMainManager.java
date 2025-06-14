package com.hzbhd.proxy.mcu.core;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.os.ServiceManager;
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
    private IMCUCanBoxControlCallback mMCUCanBoxControlCallback = new IMCUCanBoxControlCallback.Stub() { // from class: com.hzbhd.proxy.mcu.core.MCUMainManager.1
        @Override // com.hzbhd.proxy.mcu.aidl.IMCUCanBoxControlCallback
        public void notifyCanboxData(int i, byte[] bArr) throws RemoteException {
            Iterator it = MCUMainManager.this.mMCUCanList.iterator();
            while (it.hasNext()) {
                ((IMCUCanBoxControlListener) it.next()).notifyCanboxData(i, bArr);
            }
        }
    };
    private Handler mMainHandler = new Handler() { // from class: com.hzbhd.proxy.mcu.core.MCUMainManager.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (AnonymousClass4.$SwitchMap$com$hzbhd$proxy$mcu$core$MCUMainManager$MSG_ID[MSG_ID.values()[message.what].ordinal()]) {
                case 1:
                    Bundle bundle = (Bundle) message.obj;
                    Iterator it = MCUMainManager.this.mMCUMainList.iterator();
                    while (it.hasNext()) {
                        ((IMCUMainListener) it.next()).mcuInit(bundle.getByte("procotolVersion"), bundle.getBoolean("powerOnType"), bundle.getBoolean("hardwareReset"));
                    }
                    break;
                case 2:
                    String string = ((Bundle) message.obj).getString("McuVersion");
                    Iterator it2 = MCUMainManager.this.mMCUMainList.iterator();
                    while (it2.hasNext()) {
                        ((IMCUMainListener) it2.next()).notifyMCUVersion(string, null, null);
                    }
                    break;
                case 3:
                    Iterator it3 = MCUMainManager.this.mMCUMainList.iterator();
                    while (it3.hasNext()) {
                        ((IMCUMainListener) it3.next()).notifyPowerStatus(((Integer) message.obj).intValue());
                    }
                    break;
                case 4:
                    Bundle bundle2 = (Bundle) message.obj;
                    String string2 = bundle2.getString("modelName");
                    String string3 = bundle2.getString("hardwareVersion");
                    String string4 = bundle2.getString("serialNum");
                    String string5 = bundle2.getString("data");
                    Iterator it4 = MCUMainManager.this.mMCUMainList.iterator();
                    while (it4.hasNext()) {
                        ((IMCUMainListener) it4.next()).notifyHardwareVersion(string2, string3, string4, string5);
                    }
                    break;
                case 5:
                    Iterator it5 = MCUMainManager.this.mMCUMainList.iterator();
                    while (it5.hasNext()) {
                        ((IMCUMainListener) it5.next()).notifyScreenVersion((String) message.obj);
                    }
                    break;
                case 6:
                    Iterator it6 = MCUMainManager.this.mMCUMainList.iterator();
                    while (it6.hasNext()) {
                        ((IMCUMainListener) it6.next()).notifyCanboxVersion((String) message.obj);
                    }
                    break;
            }
        }
    };

    private enum MSG_ID {
        notifyCanboxData,
        mcuInit,
        notifyMCUVersion,
        notifyHardwareVersion,
        notifyCanboxVersion,
        notifyScreenVersion,
        notifyPowerStatus
    }

    @Override // com.hzbhd.proxy.mcu.base.IMCUBaseManager
    public void init() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public IMCUMainService getMCUMainService() {
        if (this.mMCUMainService == null) {
            this.mMCUMainService = IMCUMainService.Stub.asInterface(ServiceManager.getService(ServiceConstants.SERVICE_NAME_MCU_SERVICE));
            onServiceConn();
        }
        return IMCUMainService.Stub.asInterface(ServiceManager.getService(ServiceConstants.SERVICE_NAME_MCU_SERVICE));
    }

    /* renamed from: com.hzbhd.proxy.mcu.core.MCUMainManager$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$proxy$mcu$core$MCUMainManager$MSG_ID;

        static {
            int[] iArr = new int[MSG_ID.values().length];
            $SwitchMap$com$hzbhd$proxy$mcu$core$MCUMainManager$MSG_ID = iArr;
            try {
                iArr[MSG_ID.mcuInit.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$hzbhd$proxy$mcu$core$MCUMainManager$MSG_ID[MSG_ID.notifyMCUVersion.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$hzbhd$proxy$mcu$core$MCUMainManager$MSG_ID[MSG_ID.notifyPowerStatus.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$hzbhd$proxy$mcu$core$MCUMainManager$MSG_ID[MSG_ID.notifyHardwareVersion.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$hzbhd$proxy$mcu$core$MCUMainManager$MSG_ID[MSG_ID.notifyScreenVersion.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$hzbhd$proxy$mcu$core$MCUMainManager$MSG_ID[MSG_ID.notifyCanboxVersion.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
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
            MCUMainManager.this.mMainHandler.obtainMessage(MSG_ID.mcuInit.ordinal(), bundle).sendToTarget();
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainCallback
        public void notifyMCUVersion(String str, String str2, String str3) throws RemoteException {
            Bundle bundle = new Bundle();
            bundle.putString("softVerion", str);
            bundle.putString("softDate", str2);
            bundle.putString("hardwareConfig", str3);
            MCUMainManager.this.mMainHandler.obtainMessage(MSG_ID.notifyMCUVersion.ordinal(), bundle).sendToTarget();
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainCallback
        public void notifyHardwareVersion(String str, String str2, String str3, String str4) throws RemoteException {
            Bundle bundle = new Bundle();
            bundle.putString("modelName", str);
            bundle.putString("hardwareVersion", str2);
            bundle.putString("serialNum", str3);
            bundle.putString("data", str4);
            MCUMainManager.this.mMainHandler.obtainMessage(MSG_ID.notifyHardwareVersion.ordinal(), bundle).sendToTarget();
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainCallback
        public void notifyCanboxVersion(String str) throws RemoteException {
            MCUMainManager.this.mMainHandler.obtainMessage(MSG_ID.notifyCanboxVersion.ordinal(), str).sendToTarget();
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainCallback
        public void notifyScreenVersion(String str) throws RemoteException {
            MCUMainManager.this.mMainHandler.obtainMessage(MSG_ID.notifyScreenVersion.ordinal(), str).sendToTarget();
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUMainCallback
        public void notifyPowerStatus(int i) throws RemoteException {
            MCUMainManager.this.mMainHandler.obtainMessage(MSG_ID.notifyPowerStatus.ordinal(), Integer.valueOf(i)).sendToTarget();
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
