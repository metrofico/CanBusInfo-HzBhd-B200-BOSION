package com.hzbhd.canbus.config;

import com.hzbhd.common.settings.constant.BodaSysContant;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.commontools.utils.bhdGsonUtils;
import com.hzbhd.constant.mcu.McuConstants;
import com.hzbhd.setting.proxy.SettingsManager;
import kotlin.Metadata;

/* compiled from: McuVehicleConfig.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u000bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\f"}, d2 = {"Lcom/hzbhd/canbus/config/McuVehicleConfig;", "", "()V", "TAG", "", "bean", "Lcom/hzbhd/canbus/config/McuVehicleConfig$McuVehicleBean;", "getBean", "()Lcom/hzbhd/canbus/config/McuVehicleConfig$McuVehicleBean;", "setMcu", "", "McuVehicleBean", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
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

    /* compiled from: McuVehicleConfig.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u001a\u0010\u0012\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000eR\u001a\u0010\u0015\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\f\"\u0004\b\u0017\u0010\u000e¨\u0006\u0018"}, d2 = {"Lcom/hzbhd/canbus/config/McuVehicleConfig$McuVehicleBean;", "", "()V", "app_handle_key", "", "getApp_handle_key", "()Z", "setApp_handle_key", "(Z)V", "baud_rate", "", "getBaud_rate", "()I", "setBaud_rate", "(I)V", "brand", "getBrand", "setBrand", "can_protocol", "getCan_protocol", "setCan_protocol", "model", "getModel", "setModel", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
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
