package com.hzbhd.config.use;


import com.hzbhd.util.DefaultSharedUtilReflection;

import kotlin.Metadata;


public final class CanBus {
    public static final CanBus INSTANCE = new CanBus();

    public enum CanBusKey {
        canbus_b_showapp,
        canbus_i_cantype,
        canbus_i_each_id,
        canbus_i_different_id,
        canbus_i_select_car_position,
        canbus_b_setting_show,
        canbus_i_canbus_baud_rate,
        canbus_i_canbus_packtype,
        canbus_camera_configuration
    }

    public enum MsKey {
        canbus_b_ms_k9_new160configuration
    }

    private CanBus() {
    }

    public final boolean isShowApp() {
        return DefaultSharedUtilReflection.getBool(CanBusKey.canbus_b_showapp.name(), CanBusDefault.INSTANCE.isShowApp());
    }

    public final void setIsShowApp(boolean value) {
        DefaultSharedUtilReflection.setBool(CanBusKey.canbus_b_showapp.name(), value);
    }

    public final int getCanType() {
        return DefaultSharedUtilReflection.getInt(CanBusKey.canbus_i_cantype.name(), CanBusDefault.INSTANCE.getCanType());
    }

    public final void setCanType(int canType) {
        DefaultSharedUtilReflection.setInt(CanBusKey.canbus_i_cantype.name(), canType);
    }

    public final int getCanBaud_Rate() {
        return DefaultSharedUtilReflection.getInt(CanBusKey.canbus_i_canbus_baud_rate.name(), CanBusDefault.INSTANCE.getCanBaud_Rate());
    }

    public final void setBaud_Rate(int baud_rate) {
        DefaultSharedUtilReflection.setInt(CanBusKey.canbus_i_canbus_baud_rate.name(), baud_rate);
    }

    public final int getCanPackType() {
        return DefaultSharedUtilReflection.getInt(CanBusKey.canbus_i_canbus_packtype.name(), CanBusDefault.INSTANCE.getCanPackType());
    }

    public final void setCanPackType(int packtype) {
        DefaultSharedUtilReflection.setInt(CanBusKey.canbus_i_canbus_packtype.name(), packtype);
    }

    public final int getEachId() {
        return DefaultSharedUtilReflection.getInt(CanBusKey.canbus_i_each_id.name(), CanBusDefault.INSTANCE.getEachId());
    }

    public final void setEachId(int id) {
        DefaultSharedUtilReflection.setInt(CanBusKey.canbus_i_each_id.name(), id);
    }

    public final int getDifferentId() {
        return DefaultSharedUtilReflection.getInt(CanBusKey.canbus_i_different_id.name(), CanBusDefault.INSTANCE.getDifferentId());
    }

    public final void setDifferentId(int id) {
        DefaultSharedUtilReflection.setInt(CanBusKey.canbus_i_different_id.name(), id);
    }

    public final int getSelectCarPosition() {
        return DefaultSharedUtilReflection.getInt(CanBusKey.canbus_i_select_car_position.name(), CanBusDefault.INSTANCE.getSelectCarPosition());
    }

    public final void setSelectCarPosition(int position) {
        DefaultSharedUtilReflection.setInt(CanBusKey.canbus_i_select_car_position.name(), position);
    }

    public final boolean getCameraChannelSelect() {
        if (CanBusSpecialConfig.INSTANCE.cameraSupportHighConfigurationID(getCanType())) {
            return getCameraConfiguration();
        }
        return false;
    }

    public final boolean getCameraConfiguration() {
        return DefaultSharedUtilReflection.getBool(CanBusKey.canbus_camera_configuration.name(), CanBusDefault.INSTANCE.getCameraConfiguration());
    }

    public final void setCameraConfiguration(boolean isHighConfiguration) {
        DefaultSharedUtilReflection.setBool(CanBusKey.canbus_camera_configuration.name(), isHighConfiguration);
    }

    public final boolean getMsK9New160Config() {
        return DefaultSharedUtilReflection.getBool(MsKey.canbus_b_ms_k9_new160configuration.name(), CanBusDefault.INSTANCE.isMsK9New160Configuration());
    }

    public final void setMsK9New160Config(boolean isNew160) {
        DefaultSharedUtilReflection.setBool(MsKey.canbus_b_ms_k9_new160configuration.name(), isNew160);
    }
}
