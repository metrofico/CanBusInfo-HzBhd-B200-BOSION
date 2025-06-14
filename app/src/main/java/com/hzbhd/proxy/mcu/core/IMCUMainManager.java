package com.hzbhd.proxy.mcu.core;

import android.os.Bundle;
import com.hzbhd.proxy.mcu.aidl.IMCUMsgCallback;
import com.hzbhd.proxy.mcu.base.IMCUBaseManager;
import com.hzbhd.proxy.mcu.constant.MCU_MESSAGE_PEER;

/* loaded from: classes2.dex */
public interface IMCUMainManager extends IMCUBaseManager {
    void clearMessage(MCU_MESSAGE_PEER mcu_message_peer);

    void closeDevice();

    int getCarBackState();

    int getHardwareReset();

    int getMcuhardware();

    int getPowerOnType();

    int getPowerStatus();

    byte getProcotolVersion();

    void getVersion();

    Bundle initMCU();

    void initSystemReady();

    boolean isInitMCU();

    void registerMCUCanboxListener(IMCUCanBoxControlListener iMCUCanBoxControlListener);

    void registerMCUMainListener(IMCUMainListener iMCUMainListener);

    void registerMcuMsgListener(IMCUMsgCallback iMCUMsgCallback);

    void requestPowerStatus(int i);

    void sendMCUCanboxData(int i, byte[] bArr);

    void sendMCUDebugData(byte[] bArr);

    void sendTestCmd(byte[] bArr);

    void setSendSleepStatus(boolean z);

    void setUpgradeStatus(boolean z);

    void startSendHeartBeatMsg();

    void stopSendHeartBeatMsg();

    void unRegisterMCUMainListener(IMCUMainListener iMCUMainListener);

    void unregisterMCUCanboxListener(IMCUCanBoxControlListener iMCUCanBoxControlListener);

    void unregisterMcuMsgListener(IMCUMsgCallback iMCUMsgCallback);

    byte[] updateFlashData(byte[] bArr);
}
