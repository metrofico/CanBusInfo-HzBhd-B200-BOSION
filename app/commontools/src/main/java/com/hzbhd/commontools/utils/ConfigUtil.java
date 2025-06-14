package com.hzbhd.commontools.utils;

import android.text.TextUtils;
import android.util.Log;

import com.hzbhd.util.LogUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.log4j.helpers.DateLayout;

/* loaded from: classes2.dex */
public class ConfigUtil {
    private static final String CONFIG_CLASS_NAME = "MyConfig";
    private static final String TAG = "ConfigUtil";
    private static ConfigUtil sConfigUtil;
    private Object clientConfig;
    private Object frameworkConfig;
    private Object platformConfig;
    private final HARDWARD_PLATFORM_VESRION sHARDWARD_PLATFORM_VESRION;
    private String sCHIP_NAME = SystemPropertiesUtils.get("ro.product.model");
    private int sBuildVersionSDK = SystemPropertiesUtils.getInt("ro.build.version.sdk", 25);
    private final String CHIPNAME_NULL = DateLayout.NULL_DATE_FORMAT;
    private final String CHIPNAME_T3 = "T3";
    private final String CHIPNAME_T8 = "T8";
    private final String CHIPNAME_RK3399 = "rk3399";
    private final String CHIPNAME_AC8257 = "ac8257";
    private final String CHIPNAME_AC8227 = "8227L_demo";
    private final int BUILD_VERSION_CODES_N = 24;
    private final int BUILD_VERSION_CODES_N_MR1 = 25;
    private final int BUILD_VERSION_CODES_O = 26;
    private final int BUILD_VERSION_CODES_O_MR1 = 27;
    private final int BUILD_VERSION_CODES_P = 28;
    private HashMap<String, Object> jarConfig = new HashMap<>();
    private String DeviceInfor = "";
    private final int BUILD_VERSION_CODES_Q = 29;

    private enum HARDWARD_PLATFORM_VESRION {
        T3N_MR1, T8O_MR1, T3O_MR1, RK3399O_MR1, RK3399P, T3Q_MR1, AC8257P, AC8227O
    }

    public ConfigUtil() {
        if (this.sCHIP_NAME.contains("T3") && this.sBuildVersionSDK == 25) {
            this.sHARDWARD_PLATFORM_VESRION = HARDWARD_PLATFORM_VESRION.T3N_MR1;
            return;
        }
        if (this.sCHIP_NAME.contains("T3") && this.sBuildVersionSDK == 27) {
            this.sHARDWARD_PLATFORM_VESRION = HARDWARD_PLATFORM_VESRION.T3O_MR1;
            return;
        }
        if (this.sCHIP_NAME.contains("T8") && this.sBuildVersionSDK == 27) {
            this.sHARDWARD_PLATFORM_VESRION = HARDWARD_PLATFORM_VESRION.T8O_MR1;
            return;
        }
        if (this.sCHIP_NAME.contains("rk3399") && this.sBuildVersionSDK == 27) {
            this.sHARDWARD_PLATFORM_VESRION = HARDWARD_PLATFORM_VESRION.RK3399O_MR1;
            return;
        }
        if (this.sCHIP_NAME.contains("rk3399") && this.sBuildVersionSDK == 28) {
            this.sHARDWARD_PLATFORM_VESRION = HARDWARD_PLATFORM_VESRION.RK3399P;
            return;
        }
        if (this.sCHIP_NAME.contains("ac8257") && this.sBuildVersionSDK == 28) {
            this.sHARDWARD_PLATFORM_VESRION = HARDWARD_PLATFORM_VESRION.AC8257P;
            return;
        }
        if (this.sCHIP_NAME.contains("8227L_demo") && this.sBuildVersionSDK == 27) {
            this.sHARDWARD_PLATFORM_VESRION = HARDWARD_PLATFORM_VESRION.AC8227O;
        } else if (this.sCHIP_NAME.contains("T3") && this.sBuildVersionSDK == 29) {
            this.sHARDWARD_PLATFORM_VESRION = HARDWARD_PLATFORM_VESRION.T3Q_MR1;
        } else {
            this.sHARDWARD_PLATFORM_VESRION = HARDWARD_PLATFORM_VESRION.T3N_MR1;
        }
    }

    private static ConfigUtil getInstance() {
        if (sConfigUtil == null) {
            sConfigUtil = new ConfigUtil();
        }
        return sConfigUtil;
    }

    public static synchronized <T> T getJarConfig(Class<T> cls) {
        synchronized (ConfigUtil.class) {
            if (getInstance().jarConfig.get(cls.getName()) == null) {
                getInstance().jarConfig.put(cls.getName(), getInstance().getConfig(cls, "jar", null));
            }
        }
        return (T) getInstance().jarConfig.get(cls.getName());
    }

    public static synchronized String getDeviceId() {
        String deviceInfo;
        synchronized (ConfigUtil.class) {
            deviceInfo = getInstance().getDeviceInfo();
        }
        return deviceInfo;
    }

    private <T> T getConfig(Class<T> cls, String str, String str2) {
        T t;
        try {
            String str3 = cls.getPackage().getName() + "." + str.toLowerCase() + ".";
            if (str2 == null) {
                if (LogUtil.log5()) {
                    LogUtil.d("getConfig: " + str3 + CONFIG_CLASS_NAME);
                }
                t = (T) Class.forName(str3 + CONFIG_CLASS_NAME).newInstance();
            } else {
                if (LogUtil.log5()) {
                    LogUtil.d("getConfig: " + str3 + str2.toLowerCase() + "." + CONFIG_CLASS_NAME);
                }
                t = (T) Class.forName(str3 + str2.toLowerCase() + "." + CONFIG_CLASS_NAME).newInstance();
            }
        } catch (Exception unused) {
            if (LogUtil.log5()) {
                LogUtil.d("getConfig not found: " + str + "." + str2);
            }
            t = null;
        }
        if (t != null) {
            return t;
        }
        try {
            return cls.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return t;
        }
    }

    public static synchronized <T> T getFrameworkConfig(Class<T> cls) {
        synchronized (ConfigUtil.class) {
            if (getInstance().frameworkConfig == null) {
                String deviceInfo = getInstance().getDeviceInfo();
                getInstance().frameworkConfig = getInstance().getConfig(cls, "framework", deviceInfo.substring(9, 10));
            }
        }
        return (T) getInstance().frameworkConfig;
    }

    public static synchronized <T> T getPlatformConfig(Class<T> cls) {
        synchronized (ConfigUtil.class) {
            if (getInstance().platformConfig == null) {
                String deviceInfo = getInstance().getDeviceInfo();
                getInstance().platformConfig = getInstance().getConfig(cls, "platform", deviceInfo.substring(3, 4));
            }
        }
        return (T) getInstance().platformConfig;
    }

    public static synchronized <T> T getClientConfig(Class<T> cls) {
        synchronized (ConfigUtil.class) {
            if (getInstance().clientConfig == null) {
                String deviceInfo = getInstance().getDeviceInfo();
                getInstance().clientConfig = getInstance().getConfig(cls, "client", deviceInfo.substring(0, 3));
            }
        }
        return (T) getInstance().clientConfig;
    }

    private String getDeviceInfo() {
        if (TextUtils.isEmpty(this.DeviceInfor)) {
            try {
                FileInputStream fileInputStream = new FileInputStream(getfactory_hardware_info_path());
                byte[] bArr = new byte[fileInputStream.available()];
                fileInputStream.read(bArr);
                fileInputStream.close();
                this.DeviceInfor = new String(bArr);
                Log.i("getDeviceInfo", "Current device infor is =" + this.DeviceInfor);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("getDeviceInfo", "getDeviceInfo: error = " + e.getMessage());
                this.DeviceInfor = "";
            }
        }
        return this.DeviceInfor;
    }

    private synchronized String getfactory_hardware_info_path() {
        return this.sHARDWARD_PLATFORM_VESRION == HARDWARD_PLATFORM_VESRION.T3N_MR1 ? "/bootloader/factory_hardware_info" : this.sHARDWARD_PLATFORM_VESRION == HARDWARD_PLATFORM_VESRION.T8O_MR1 ? "/bootloader/factory_hardware_info" : this.sHARDWARD_PLATFORM_VESRION == HARDWARD_PLATFORM_VESRION.T3O_MR1 ? "/bootloader/factory_hardware_info" : this.sHARDWARD_PLATFORM_VESRION == HARDWARD_PLATFORM_VESRION.RK3399O_MR1 ? "/system/etc/factory_hardware_info" : this.sHARDWARD_PLATFORM_VESRION == HARDWARD_PLATFORM_VESRION.RK3399P ? "/system/etc/factory_hardware_info" : this.sHARDWARD_PLATFORM_VESRION == HARDWARD_PLATFORM_VESRION.AC8257P ? "/bhd/factory_hardware_info" : this.sHARDWARD_PLATFORM_VESRION == HARDWARD_PLATFORM_VESRION.AC8227O ? "/bhd/factory_hardware_info" : this.sHARDWARD_PLATFORM_VESRION == HARDWARD_PLATFORM_VESRION.T3Q_MR1 ? "/system/etc/factory_hardware_info" : "/bootloader/factory_hardware_info";
    }
}
