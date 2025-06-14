package com.hzbhd.proxy.keydispatcher.constants;

import com.hzbhd.commontools.SourceConstantsDef;

/* loaded from: classes2.dex */
public class KeyDispatcherConstant {

    public enum APP_TYPE {
        SYSTEM_MODULE,
        SOURCE_APP,
        USER_APP
    }

    public static int getAppId(SourceConstantsDef.MODULE_ID module_id) {
        return (APP_TYPE.SYSTEM_MODULE.ordinal() << 8) + module_id.getValue();
    }

    public static int getAppId(SourceConstantsDef.SOURCE_ID source_id) {
        return (APP_TYPE.SOURCE_APP.ordinal() << 8) + source_id.getValue();
    }

    public static int getAppId(int i) {
        return (APP_TYPE.USER_APP.ordinal() << 8) + i;
    }

    public static APP_TYPE getAppType(int i) {
        return APP_TYPE.values()[i >> 8];
    }
}
