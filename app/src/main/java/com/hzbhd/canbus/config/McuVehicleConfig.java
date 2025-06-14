package com.hzbhd.canbus.config;

import com.hzbhd.common.settings.constant.BodaSysContant;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.commontools.utils.bhdGsonUtils;
import com.hzbhd.constant.mcu.McuConstants;
import com.hzbhd.setting.proxy.SettingsManager;


public final class McuVehicleConfig {
    private static final String TAG = "McuVehicleConfig";
    public static final McuVehicleConfig INSTANCE = new McuVehicleConfig();
    private static final McuVehicleBean bean = new McuVehicleBean();

    private McuVehicleConfig() {
    }

    public final McuVehicleBean getBean() {
        return bean;
    }

    public final void setMcu() {
        SettingsManager.getInstance().setString(SourceConstantsDef.MODULE_ID.MCU, BodaSysContant.TypeDef.SETTING, McuConstants.SETTING_SET.updateVehicleConfig.ordinal(), 0, bhdGsonUtils.toJson(bean));
    }

    public static final class McuVehicleBean {
        private boolean app_handle_key = true;
        private int baud_rate;
        private int brand;
        private int can_protocol;
        private int model;

        public final int getBrand() {
            return this.brand;
        }

        public final void setBrand(int i) {
            this.brand = i;
        }

        public final int getModel() {
            return this.model;
        }

        public final void setModel(int i) {
            this.model = i;
        }

        public final int getBaud_rate() {
            return this.baud_rate;
        }

        public final void setBaud_rate(int i) {
            this.baud_rate = i;
        }

        public final int getCan_protocol() {
            return this.can_protocol;
        }

        public final void setCan_protocol(int i) {
            this.can_protocol = i;
        }

        public final boolean getApp_handle_key() {
            return this.app_handle_key;
        }

        public final void setApp_handle_key(boolean z) {
            this.app_handle_key = z;
        }
    }
}
