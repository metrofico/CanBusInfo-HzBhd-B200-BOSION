package com.hzbhd.proxy.mcu.upgrade;

import com.hzbhd.proxy.mcu.base.IMCUBaseManager;
import com.hzbhd.proxy.mcu.upgrade.UpgradeConstants;

/* loaded from: classes2.dex */
public interface IMCUUpgradeManager extends IMCUBaseManager {
    void registerMCUUpgradeListener(IMCUUpgradeListener iMCUUpgradeListener);

    int reqUpgrade(UpgradeConstants.DevType devType);

    int sendUpgradeData(byte[] bArr, int i, int i2, int i3);

    void unRegisterMCUUpgradeListener(IMCUUpgradeListener iMCUUpgradeListener);

    byte[] updateFlashData(byte[] bArr);

    int upgradeEnd(int i);

    boolean upgradeStart(boolean z, boolean z2, int i, int i2, int i3, int i4);
}
