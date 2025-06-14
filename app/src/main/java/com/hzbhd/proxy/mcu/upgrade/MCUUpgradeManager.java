package com.hzbhd.proxy.mcu.upgrade;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import androidx.core.app.NotificationCompat;
import com.hzbhd.proxy.mcu.aidl.IMCUUpgradeCallback;
import com.hzbhd.proxy.mcu.aidl.IMCUUpgradeService;
import com.hzbhd.proxy.mcu.constant.MCUConstants;
import com.hzbhd.proxy.mcu.upgrade.UpgradeConstants;
import com.hzbhd.proxy.service.ServiceConstants;
import com.hzbhd.proxy.service.bhdNewServiceConnection;
import com.hzbhd.util.LogUtil;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes2.dex */
public class MCUUpgradeManager extends bhdNewServiceConnection implements IMCUUpgradeManager {
    private static final String TAG = "MCUUpgradeManager";
    private static MCUUpgradeManager instance;
    private IMCUUpgradeCallback mIMCUUpgradeCallback;
    private Handler mMsgHandler;
    private final Set<IMCUUpgradeListener> mUpgradeList;
    private IMCUUpgradeService mUpgradeMCUService;

    public enum UPGRADE_MSG_ID {
        notifyMCUVersion,
        notifyHardwareVersion,
        notifyCanboxVersion,
        notifyScreenVersion,
        notifyMCUUpgradeStartCheckStatus,
        notifyMCUUpgradeEndCheckStatus,
        notifyMCUUpgradeSendDataSeq
    }

    @Override // com.hzbhd.proxy.service.IbhdServiceConnection
    public String getServiceAction() {
        return MCUConstants.MCU_ACTION;
    }

    @Override // com.hzbhd.proxy.service.bhdNewServiceConnection, com.hzbhd.proxy.service.IbhdServiceConnection
    public String getServiceName() {
        return ServiceConstants.SERVICE_NAME_MCU_UPGRADE;
    }

    @Override // com.hzbhd.proxy.service.IbhdServiceConnection
    public String getServicePkgName() {
        return "com.hzbhd.service.mcu";
    }

    @Override // com.hzbhd.proxy.mcu.base.IMCUBaseManager
    public void init() {
    }

    public MCUUpgradeManager(Context context) {
        super(context);
        this.mUpgradeList = new HashSet();
        this.mIMCUUpgradeCallback = new MCUUpgradeCallback();
        this.mMsgHandler = new Handler(Looper.getMainLooper()) { // from class: com.hzbhd.proxy.mcu.upgrade.MCUUpgradeManager.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = AnonymousClass2.$SwitchMap$com$hzbhd$proxy$mcu$upgrade$MCUUpgradeManager$UPGRADE_MSG_ID[UPGRADE_MSG_ID.values()[message.what].ordinal()];
                if (i == 1) {
                    Bundle bundle = (Bundle) message.obj;
                    boolean z = bundle.getBoolean("isUpgrade");
                    String string = bundle.getString(NotificationCompat.CATEGORY_STATUS);
                    synchronized (MCUUpgradeManager.this.mUpgradeList) {
                        Iterator it = MCUUpgradeManager.this.mUpgradeList.iterator();
                        while (it.hasNext()) {
                            ((IMCUUpgradeListener) it.next()).notifyMCUUpgradeStartCheckStatus(z, UpgradeConstants.UpgradeStartCheckStatus.valueOf(string));
                        }
                    }
                    return;
                }
                if (i == 2) {
                    synchronized (MCUUpgradeManager.this.mUpgradeList) {
                        Iterator it2 = MCUUpgradeManager.this.mUpgradeList.iterator();
                        while (it2.hasNext()) {
                            ((IMCUUpgradeListener) it2.next()).notifyMCUUpgradeEndCheckStatus(UpgradeConstants.UpgradeStartEndStatus.valueOf((String) message.obj));
                        }
                    }
                    return;
                }
                if (i != 3) {
                    return;
                }
                synchronized (MCUUpgradeManager.this.mUpgradeList) {
                    Iterator it3 = MCUUpgradeManager.this.mUpgradeList.iterator();
                    while (it3.hasNext()) {
                        ((IMCUUpgradeListener) it3.next()).notifyMCUUpgradeSendDataSeq(((Integer) message.obj).intValue());
                    }
                }
            }
        };
    }

    public static MCUUpgradeManager getInstance(Context context) {
        if (instance == null) {
            synchronized (MCUUpgradeManager.class) {
                if (instance == null) {
                    instance = new MCUUpgradeManager(context);
                }
            }
        }
        return instance;
    }

    @Override // com.hzbhd.proxy.service.bhdNewServiceConnection
    public void onServiceConnectionChanged(IBinder iBinder, boolean z) {
        if (LogUtil.log5()) {
            LogUtil.d("onServiceConnectionChanged,state=" + z);
        }
        if (z) {
            try {
                IMCUUpgradeService iMCUUpgradeServiceAsInterface = IMCUUpgradeService.Stub.asInterface(iBinder);
                this.mUpgradeMCUService = iMCUUpgradeServiceAsInterface;
                iMCUUpgradeServiceAsInterface.registerMCUUpgradeCallback(this.mIMCUUpgradeCallback);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        this.mUpgradeMCUService = null;
    }

    @Override // com.hzbhd.proxy.mcu.upgrade.IMCUUpgradeManager
    public boolean upgradeStart(boolean z, boolean z2, int i, int i2, int i3, int i4) {
        IMCUUpgradeService iMCUUpgradeService = this.mUpgradeMCUService;
        if (iMCUUpgradeService == null) {
            return false;
        }
        try {
            return iMCUUpgradeService.upgradeStart(z, z2, i, i2, i3, i4);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.hzbhd.proxy.mcu.upgrade.IMCUUpgradeManager
    public int sendUpgradeData(byte[] bArr, int i, int i2, int i3) {
        IMCUUpgradeService iMCUUpgradeService = this.mUpgradeMCUService;
        if (iMCUUpgradeService == null) {
            return -1;
        }
        try {
            return iMCUUpgradeService.sendUpgradeData(bArr, i, i2, i3);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override // com.hzbhd.proxy.mcu.upgrade.IMCUUpgradeManager
    public int reqUpgrade(UpgradeConstants.DevType devType) {
        IMCUUpgradeService iMCUUpgradeService = this.mUpgradeMCUService;
        if (iMCUUpgradeService == null) {
            return -1;
        }
        try {
            return iMCUUpgradeService.reqUpgrade(devType.name());
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override // com.hzbhd.proxy.mcu.upgrade.IMCUUpgradeManager
    public int upgradeEnd(int i) {
        IMCUUpgradeService iMCUUpgradeService = this.mUpgradeMCUService;
        if (iMCUUpgradeService == null) {
            return -1;
        }
        try {
            return iMCUUpgradeService.upgradeEnd(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override // com.hzbhd.proxy.mcu.upgrade.IMCUUpgradeManager
    public void registerMCUUpgradeListener(IMCUUpgradeListener iMCUUpgradeListener) {
        synchronized (this.mUpgradeList) {
            this.mUpgradeList.add(iMCUUpgradeListener);
        }
    }

    @Override // com.hzbhd.proxy.mcu.upgrade.IMCUUpgradeManager
    public void unRegisterMCUUpgradeListener(IMCUUpgradeListener iMCUUpgradeListener) {
        synchronized (this.mUpgradeList) {
            this.mUpgradeList.remove(iMCUUpgradeListener);
        }
    }

    @Override // com.hzbhd.proxy.mcu.upgrade.IMCUUpgradeManager
    public byte[] updateFlashData(byte[] bArr) {
        if (LogUtil.log5()) {
            LogUtil.d("MCUUpgradeManager>updateFlashData(): para=" + bArr);
        }
        if (LogUtil.log5()) {
            LogUtil.d("MCUUpgradeManager>updateFlashData(): mUpgradeMCUService=" + this.mUpgradeMCUService);
        }
        IMCUUpgradeService iMCUUpgradeService = this.mUpgradeMCUService;
        if (iMCUUpgradeService == null) {
            return null;
        }
        try {
            return iMCUUpgradeService.updateFlashData(bArr);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    private class MCUUpgradeCallback extends IMCUUpgradeCallback.Stub {
        private MCUUpgradeCallback() {
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUUpgradeCallback
        public void notifyMCUUpgradeStartCheckStatus(boolean z, String str) throws RemoteException {
            Bundle bundle = new Bundle();
            bundle.putBoolean("isUpgrade", z);
            bundle.putString(NotificationCompat.CATEGORY_STATUS, str);
            MCUUpgradeManager.this.mMsgHandler.obtainMessage(UPGRADE_MSG_ID.notifyMCUUpgradeStartCheckStatus.ordinal(), bundle).sendToTarget();
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUUpgradeCallback
        public void notifyMCUUpgradeEndCheckStatus(String str) throws RemoteException {
            MCUUpgradeManager.this.mMsgHandler.obtainMessage(UPGRADE_MSG_ID.notifyMCUUpgradeEndCheckStatus.ordinal(), str).sendToTarget();
        }

        @Override // com.hzbhd.proxy.mcu.aidl.IMCUUpgradeCallback
        public void notifyMCUUpgradeSendDataSeq(int i) throws RemoteException {
            MCUUpgradeManager.this.mMsgHandler.obtainMessage(UPGRADE_MSG_ID.notifyMCUUpgradeSendDataSeq.ordinal(), Integer.valueOf(i)).sendToTarget();
        }
    }

    /* renamed from: com.hzbhd.proxy.mcu.upgrade.MCUUpgradeManager$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$proxy$mcu$upgrade$MCUUpgradeManager$UPGRADE_MSG_ID;

        static {
            int[] iArr = new int[UPGRADE_MSG_ID.values().length];
            $SwitchMap$com$hzbhd$proxy$mcu$upgrade$MCUUpgradeManager$UPGRADE_MSG_ID = iArr;
            try {
                iArr[UPGRADE_MSG_ID.notifyMCUUpgradeStartCheckStatus.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$hzbhd$proxy$mcu$upgrade$MCUUpgradeManager$UPGRADE_MSG_ID[UPGRADE_MSG_ID.notifyMCUUpgradeEndCheckStatus.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$hzbhd$proxy$mcu$upgrade$MCUUpgradeManager$UPGRADE_MSG_ID[UPGRADE_MSG_ID.notifyMCUUpgradeSendDataSeq.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
