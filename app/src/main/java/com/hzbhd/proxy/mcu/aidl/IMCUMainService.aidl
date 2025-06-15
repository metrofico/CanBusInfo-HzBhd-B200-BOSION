
package com.hzbhd.proxy.mcu.aidl;

// Declare any non-default types here with import statements
import com.hzbhd.proxy.mcu.aidl.IMCUMainCallback;
import com.hzbhd.proxy.mcu.aidl.IMCUCanBoxControlCallback;
import com.hzbhd.proxy.mcu.aidl.IMCUMsgCallback;

interface IMCUMainService {
    void registerMCUMainCallback(IMCUMainCallback callback);
    void unregisterMCUMainCallback(IMCUMainCallback callback);
    Bundle initMCU();
    void clearMessage(int type);
    void initSystemReady();
    void closeDevice();
    void startSendHeartBeatMsg();
    void stopSendHeartBeatMsg();
    void setSendSleepStatus(boolean enable);
    void setUpgradeStatus(boolean isUpgrade);

    void sendMCUCanboxData(int cmd,in byte[] parameter);
//    void sendMCUCanboxData(in int[] parameter);
    void registerMCUCanboxCallback(IMCUCanBoxControlCallback callback);
    void unregisterMCUCanboxCallback(IMCUCanBoxControlCallback callback);

    void sendMCUDebugData(in byte[] data);

    int getMcuHardware();
    int getHardwareReset();
    int getPowerOnType();
    byte getProcotolVersion();


    int getCarBackState();

     // 获取MCU 版本
      void getVersion();

    boolean isInitMCU();

     // MCU电源状态
     void requestPowerStatus(int status);
     int getPowerStatus();

     void sendTestCmd(in byte[] reqType);

     void registerMcuMsgListener(IMCUMsgCallback callback);

     void unregisterMcuMsgListener(IMCUMsgCallback callback);


     byte[] updateFlashData(in byte[] para);
}
