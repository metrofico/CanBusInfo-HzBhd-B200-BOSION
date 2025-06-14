package com.hzbhd.proxy.mcu.upgrade;

import com.hzbhd.proxy.mcu.upgrade.UpgradeConstants;

/* loaded from: classes2.dex */
public interface IMCUUpgradeListener {
    void notifyMCUUpgradeEndCheckStatus(UpgradeConstants.UpgradeStartEndStatus upgradeStartEndStatus);

    void notifyMCUUpgradeSendDataSeq(int i);

    void notifyMCUUpgradeStartCheckStatus(boolean z, UpgradeConstants.UpgradeStartCheckStatus upgradeStartCheckStatus);
}
