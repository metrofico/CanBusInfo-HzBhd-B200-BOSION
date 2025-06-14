package com.hzbhd.canbus.config;

import android.util.Log;

import com.hzbhd.commontools.utils.bhdGsonUtils;
import com.hzbhd.config.use.CanBus;
import com.hzbhd.util.DefaultSharedUtilReflection;


public final class CanbusConfig {
    public static final CanbusConfig INSTANCE = new CanbusConfig();
    private static final String TAG = "CanbusConfig";

    private CanbusConfig() {
    }

    public final void setCanType(int canType) {
        Log.i(TAG, "setCanType: commit and sync  " + canType);
        CanBus.INSTANCE.setCanType(canType);
        //DefaultSharedUtilRefl.commit();
        DefaultSharedUtilReflection.commit();
        bhdGsonUtils.callBhdSyncUsingReflection();
        //SystemMix.bhd_sync();
    }

    public final int getCanType() {
        return CanBus.INSTANCE.getCanType();
    }

    public final int getCanBaud_Rate() {
        return CanBus.INSTANCE.getCanBaud_Rate();
    }

    public final void setBaud_Rate(int baud_rate) {
        CanBus.INSTANCE.setBaud_Rate(baud_rate);
    }

    public final int getCanPackType() {
        return CanBus.INSTANCE.getCanPackType();
    }

    public final void setCanPackType(int packtype) {
        CanBus.INSTANCE.setCanPackType(packtype);
    }

    public final void setIsShowApp(boolean isShowApp) {
        Log.i(TAG, "setIsShowApp: " + isShowApp);
        CanBus.INSTANCE.setIsShowApp(isShowApp);
    }

    public final boolean isShowApp() {
        return CanBus.INSTANCE.isShowApp();
    }

    public final void setSelectCarPosition(int position) {
        Log.i(TAG, "setSelectCarPosition: " + position);
        CanBus.INSTANCE.setSelectCarPosition(position);
    }

    public final int getSelectCarPosition() {
        return CanBus.INSTANCE.getSelectCarPosition();
    }

    public final void setDifferentId(int id) {
        Log.i(TAG, "setDifferentId: " + id);
        CanBus.INSTANCE.setDifferentId(id);
    }

    public final int getDifferentId() {
        return CanBus.INSTANCE.getDifferentId();
    }

    public final void setEachId(int id) {
        Log.i(TAG, "setEachId: " + id);
        CanBus.INSTANCE.setEachId(id);
    }

    public final int getEachId() {
        return CanBus.INSTANCE.getEachId();
    }

    public final void setCameraConfiguration(boolean isHighConfiguration) {
        Log.i(TAG, "setCameraConfiguration: " + isHighConfiguration);
        CanBus.INSTANCE.setCameraConfiguration(isHighConfiguration);
    }

    public final boolean getCameraConfiguration() {
        return CanBus.INSTANCE.getCameraConfiguration();
    }
}
