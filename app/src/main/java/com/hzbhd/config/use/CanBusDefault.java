package com.hzbhd.config.use;

import kotlin.Metadata;


public final class CanBusDefault {
    private static final int airDisplaySetup = 0;
    private static final boolean airSwitchTemperature = false;
    private static final int airTemperatureUnit = 0;
    private static final boolean cameraConfiguration = false;
    private static final int canBaud_Rate = 0;
    private static final boolean canBusBackBtn = false;
    private static final int canPackType = 0;
    private static final int canType = 0;
    private static final int differentId = 0;
    private static final boolean doorFrontSwap = false;
    private static final boolean doorRearSwap = false;
    private static final int eachId = 0;
    private static final boolean isMsK9New160Configuration = false;
    private static final boolean keySwitchRightLeft = false;
    private static final boolean keySwitchUpDn = false;
    private static final int selectCarPosition = 0;
    private static final boolean supportVideoInput1080p = false;
    private static final boolean supportVideoInputSettings = false;
    public static final CanBusDefault INSTANCE = new CanBusDefault();
    private static final boolean doorShowTheTrunk = true;
    private static final boolean airOutdoorTemperatureDisplay = true;
    private static final boolean supportColorAdjust = true;
    private static final boolean needShow = true;
    private static final boolean longClick = true;
    private static final int useNewApp = 1;
    private static final boolean canSettingShow = true;
    private static final boolean radarDisplay = true;
    private static final boolean doorShowInfo = true;
    private static final boolean doorShowTheHood = true;
    private static final boolean isShowApp = true;

    private CanBusDefault() {
    }

    public final boolean getDoorShowTheTrunk() {
        return doorShowTheTrunk;
    }

    public final boolean getAirSwitchTemperature() {
        return airSwitchTemperature;
    }

    public final boolean getAirOutdoorTemperatureDisplay() {
        return airOutdoorTemperatureDisplay;
    }

    public final int getAirTemperatureUnit() {
        return airTemperatureUnit;
    }

    public final boolean getKeySwitchRightLeft() {
        return keySwitchRightLeft;
    }

    public final boolean isMsK9New160Configuration() {
        return isMsK9New160Configuration;
    }

    public final int getSelectCarPosition() {
        return selectCarPosition;
    }

    public final boolean getCameraConfiguration() {
        return cameraConfiguration;
    }

    public final boolean getSupportColorAdjust() {
        return supportColorAdjust;
    }

    public final boolean getSupportVideoInputSettings() {
        return supportVideoInputSettings;
    }

    public final boolean getSupportVideoInput1080p() {
        return supportVideoInput1080p;
    }

    public final int getCanType() {
        return canType;
    }

    public final int getCanBaud_Rate() {
        return canBaud_Rate;
    }

    public final int getCanPackType() {
        return canPackType;
    }

    public final int getDifferentId() {
        return differentId;
    }

    public final int getEachId() {
        return eachId;
    }

    public final boolean getNeedShow() {
        return needShow;
    }

    public final boolean getLongClick() {
        return longClick;
    }

    public final boolean getCanBusBackBtn() {
        return canBusBackBtn;
    }

    public final int getUseNewApp() {
        return useNewApp;
    }

    public final boolean getCanSettingShow() {
        return canSettingShow;
    }

    public final boolean getRadarDisplay() {
        return radarDisplay;
    }

    public final boolean getDoorShowInfo() {
        return doorShowInfo;
    }

    public final boolean getDoorFrontSwap() {
        return doorFrontSwap;
    }

    public final boolean getDoorRearSwap() {
        return doorRearSwap;
    }

    public final boolean getDoorShowTheHood() {
        return doorShowTheHood;
    }

    public final int getAirDisplaySetup() {
        return airDisplaySetup;
    }

    public final boolean getKeySwitchUpDn() {
        return keySwitchUpDn;
    }

    public final boolean isShowApp() {
        return isShowApp;
    }
}
