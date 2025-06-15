// McuInitCallBack.aidl
package com.hzbhd.proxy.mcu.aidl;
import android.os.Bundle;
// Declare any non-default types here with import statements

interface IMCUMainCallback {
    void mcuInit(byte procotolVersion,boolean powerOnType,boolean hardwareReset);

    void notifyMCUVersion(String softVer, String softDate, String hardwareConfig);

    void notifyHardwareVersion(String modelName, String hardwareVersion, String serialNum, String data);

    void notifyCanboxVersion(String canboxVersion);

    void notifyScreenVersion(String screenVersion);

    void notifyPowerStatus(int status);
}
