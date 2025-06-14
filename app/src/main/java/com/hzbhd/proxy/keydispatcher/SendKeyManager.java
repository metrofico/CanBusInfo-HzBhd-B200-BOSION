package com.hzbhd.proxy.keydispatcher;

import android.os.Bundle;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import com.hzbhd.proxy.keydispatcher.aidl.IKeyDispatcherService;

/* loaded from: classes2.dex */
public class SendKeyManager {
    public static final String BUNDLE_KEY_LONGPRESSKEY = "LongPressKey";
    private static SendKeyManager mSendKeyManager;

    public static synchronized SendKeyManager getInstance() {
        synchronized (SendKeyManager.class) {
            if (mSendKeyManager == null) {
                mSendKeyManager = new SendKeyManager();
            }
        }
        return mSendKeyManager;
        return mSendKeyManager;
    }

    public void playBeep(int i) {
        setKeyEvent(4096, i, 0, null);
    }

    public void startSource(int i) {
        setKeyEvent(4097, i, 0, null);
    }

    public void setAppKeyEvent(int i, int i2) {
        setKeyEvent(4099, i, i2);
    }

    public void setAppMute(int i, boolean z) {
        setKeyEvent(4098, i, z ? 1 : 0, null);
    }

    public void setVolume(int i) {
        setKeyEvent(HotKeyConstant.K_SET_VOLUME, i, 0, null);
    }

    public void setBacklightLevel(int i) {
        setKeyEvent(HotKeyConstant.K_SET_BACKLIGHT, i, 0, null);
    }

    public void setBacklightStatus(boolean z) {
        setKeyEvent(HotKeyConstant.K_SYS_BLACKOUT, !z ? 1 : 0, 0, null);
    }

    public void forceReverse(boolean z) {
        setKeyEvent(HotKeyConstant.K_CMD_REVERSE, z ? 1 : 0, 0, null);
    }

    public void setSourceBluetooth(SourceConstantsDef.SOURCE_ID source_id, HotKeyConstant.BT_ACTION bt_action) {
        setKeyEvent(HotKeyConstant.K_SET_SOURCE_BLUETOOTH, source_id.getValue(), bt_action.ordinal(), null);
    }

    public void setSystemToast(int i, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("text", str);
        setKeyEvent(HotKeyConstant.K_SYS_TOAST, i, 0, bundle);
    }

    public void resetMpu(HotKeyConstant.RESET_MODE reset_mode, int i) {
        setKeyEvent(HotKeyConstant.K_MPU_RESET, reset_mode.ordinal(), i, null);
    }

    public void setKeyEvent(int i, HotKeyConstant.KeyState keyState, boolean z) {
        setKeyEvent(i, keyState.ordinal(), z ? 1 : 0, null);
    }

    public void setKeyEvent(int i, int i2, int i3) {
        setKeyEvent(i, i2, i3, null);
    }

    public void setKeyEvent(int i, int i2, int i3, Bundle bundle) {
        IKeyDispatcherService iKeyDispatcherService = KeyDispatcherManager.getIKeyDispatcherService();
        if (iKeyDispatcherService == null) {
            return;
        }
        try {
            iKeyDispatcherService.setKeyEvent(i, i2, i3, bundle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
