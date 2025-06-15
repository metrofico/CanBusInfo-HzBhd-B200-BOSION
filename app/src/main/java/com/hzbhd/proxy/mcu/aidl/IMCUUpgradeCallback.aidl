
package com.hzbhd.proxy.mcu.aidl;

// Declare any non-default types here with import statements

interface IMCUUpgradeCallback {
     //   void notifyMCUVersion(String softVer, String softDate, String screenVersion, String InternalVersion, String customerModel, String canboxVersion);

    void notifyMCUUpgradeStartCheckStatus(boolean isUpgrade, String status);

    void notifyMCUUpgradeEndCheckStatus(String status);

    void notifyMCUUpgradeSendDataSeq(int seq);
   // void notifyMCUVersion(String softVer,String softDate,String screenVersion,String InternalVersion,String customerModel,String canboxVersion);
}
