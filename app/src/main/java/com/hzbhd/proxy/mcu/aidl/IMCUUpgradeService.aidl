
package com.hzbhd.proxy.mcu.aidl;

// Declare any non-default types here with import statements
import com.hzbhd.proxy.mcu.aidl.IMCUUpgradeCallback;
interface IMCUUpgradeService {

     void registerMCUUpgradeCallback(IMCUUpgradeCallback callback);
     void unRegisterMCUUpgradeCallback(IMCUUpgradeCallback callback);

     // 升级开始
      boolean upgradeStart(boolean isAppUpgrade,boolean isBootUpgrade,int UpgradeDataSize,int checkCode,int upgradeVersionLength,int upgradeVersion);

     // 升级数据
      int sendUpgradeData(in byte[] data, int seq, int totalSeq, int dataLength);

     // 升级校验总和
//      int upgradeCheckSum(byte data);

  /*   // mcu键控制
      int mcuKeyControl(in byte[] para);*/

     // 请求升级
      int reqUpgrade(String dev);

     // 更新结束
      int upgradeEnd(int checkSum);

    // 更新MCU部分Flash数据
    byte[] updateFlashData(in byte[] para);
}
