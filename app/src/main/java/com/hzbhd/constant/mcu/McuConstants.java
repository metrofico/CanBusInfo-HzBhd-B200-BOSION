package com.hzbhd.constant.mcu;

/* loaded from: classes2.dex */
public class McuConstants {

    public enum SETTING_GET {
        getMcuVersion,
        getMcuBootVersion,
        getMcuDtcMsg
    }

    public enum SETTING_NOTIFY {
        notifySwcSettings,
        notifySwcSample,
        notifyPnlSettings,
        notifyPnlSample,
        notifyWriteMcuEeprom,
        notifyReadMcuEeprom,
        notifyMcuTestF1,
        notifyMcuTestF0,
        notifyMcuTestF3
    }

    public enum SETTING_SET {
        getSWCSettings,
        saveSWCSettings,
        endSWCSample,
        startSWCSample,
        openSWC,
        closeSWC,
        resetSWC,
        setSWCLearnLevel,
        reqPNLAllKeyInfo,
        pnlSample,
        savePNLSettings,
        setColorLed,
        setVcom,
        getSysSetting,
        reqMcuKey,
        sendTsPosition,
        updateVideoConfig,
        updateFactorySettingsConfig,
        updateGeneralConfig,
        setBackLightLearningState,
        setBackLightLearningData,
        updateVehicleConfig,
        writeMcuEeprom,
        readMcuEeprom,
        updatePowerConfig,
        TMcuRecoveryMode,
        startMcuTest,
        sendMcuTestData,
        getMcuTestInit,
        updateLcdConfig,
        setReverse,
        updateScreenSaverMode,
        requestMcuMuteAudioGpio,
        setDeviceConnectStatus,
        screenOrientation,
        updateAccOnBackLightDelaySettings
    }
}
