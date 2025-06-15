package com.hzbhd.config.use;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;

import com.hzbhd.build.ui.UIUtil;
import com.hzbhd.config.bean.UIBean;
import com.hzbhd.constant.config.ConfigConstant;
import com.hzbhd.util.DefaultSharedUtilReflection;

import java.util.Arrays;
import java.util.List;

import kotlin.Metadata;

/* compiled from: UI.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0017B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0011\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0002\u0010\rJ\u0016\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u000e\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010\u0013\u001a\u00020\fJ\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\fR$\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00048F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u0018"}, d2 = {"Lcom/hzbhd/config/use/UI;", "", "()V", "value", "", "restartWhenSelectUI", "getRestartWhenSelectUI", "()Z", "setRestartWhenSelectUI", "(Z)V", "getSelectUI", "", "", "()[Ljava/lang/String;", "getUI", "context", "Landroid/content/Context;", "appName", "Lcom/hzbhd/config/bean/UIBean$AppName;", "getUIId", "setUI", "", "uiId", "UIKey", "UI-config_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class UI {
    public static final UI INSTANCE = new UI();

    /* compiled from: UI.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/hzbhd/config/use/UI$UIKey;", "", "(Ljava/lang/String;I)V", "ui_s_id", "ui_s_id_encrypt", "ui_b_restart_when_select_ui", "UI-config_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public enum UIKey {
        ui_s_id,
        ui_s_id_encrypt,
        ui_b_restart_when_select_ui
    }

    /* compiled from: UI.kt */


    private UI() {
    }

    public String getUI(Context context, UIBean.AppName appName) {


        if (TextUtils.isEmpty(Settings.System.getString(context.getContentResolver(), UIKey.ui_s_id.name()))) {
            return getUI(appName);
        }
        String string = Settings.System.getString(context.getContentResolver(), UIKey.ui_s_id.name());

        return string;
    }

    public String getUIId() {
        String saveUI = DefaultSharedUtilReflection.getStr(UIKey.ui_s_id_encrypt.name(), "");
        if (TextUtils.isEmpty(saveUI)) {
            String str = DefaultSharedUtilReflection.getStr(UIKey.ui_s_id.name(), UIDefault.INSTANCE.getCurrUI());

            return str;
        }
        UIEncrypt uIEncrypt = UIEncrypt.INSTANCE;

        return uIEncrypt.changeStringToUI(saveUI);
    }

    public String[] getSelectUI() {
        String configSelectUI = DefaultSharedUtilReflection.getStr(ConfigConstant.SharedKey.ui_s_select_ui_array.name(), "");
        String str = configSelectUI;
        if (TextUtils.isEmpty(str)) {
            return UIDefault.INSTANCE.getSelectUI();
        }

        List<String> listSplit$default = Arrays.asList(str.split("___"));
        int size = listSplit$default.size();
        String[] strArr = new String[size];
        for (int i = 0; i < size; i++) {
            strArr[i] = UIEncrypt.INSTANCE.changeStringToUI(listSplit$default.get(i));
        }
        return strArr;
    }

    public String getUI(UIBean.AppName appName) {
        String media;

        String uIId = getUIId();
        switch (appName) {
            case media:
                UIUtil.UIMix uIMix = UIUtil.INSTANCE.getUiMap().get("ui_" + uIId);
                if (uIMix == null || (media = uIMix.getMedia()) == null) {
                    return "01";
                }
                break;
            case radio:
                UIUtil.UIMix uIMix2 = UIUtil.INSTANCE.getUiMap().get("ui_" + uIId);
                if (uIMix2 == null || (media = uIMix2.getRadio()) == null) {
                    return "01";
                }
                break;
            case bt:
                UIUtil.UIMix uIMix3 = UIUtil.INSTANCE.getUiMap().get("ui_" + uIId);
                if (uIMix3 == null || (media = uIMix3.getBt()) == null) {
                    return "01";
                }
                break;
            case dsp:
                UIUtil.UIMix uIMix4 = UIUtil.INSTANCE.getUiMap().get("ui_" + uIId);
                if (uIMix4 == null || (media = uIMix4.getDsp()) == null) {
                    return "01";
                }
                break;
            case misc:
                UIUtil.UIMix uIMix5 = UIUtil.INSTANCE.getUiMap().get("ui_" + uIId);
                if (uIMix5 == null || (media = uIMix5.getMisc()) == null) {
                    return "01";
                }
                break;
            case camera:
                UIUtil.UIMix uIMix6 = UIUtil.INSTANCE.getUiMap().get("ui_" + uIId);
                if (uIMix6 == null || (media = uIMix6.getCamera()) == null) {
                    return "01";
                }
                break;
            case widget:
                UIUtil.UIMix uIMix7 = UIUtil.INSTANCE.getUiMap().get("ui_" + uIId);
                if (uIMix7 == null || (media = uIMix7.getWidget()) == null) {
                    return "01";
                }
                break;
            case launcher:
                UIUtil.UIMix uIMix8 = UIUtil.INSTANCE.getUiMap().get("ui_" + uIId);
                if (uIMix8 == null || (media = uIMix8.getLauncher()) == null) {
                    return "01";
                }
                break;
            case systemui:
                UIUtil.UIMix uIMix9 = UIUtil.INSTANCE.getUiMap().get("ui_" + uIId);
                if (uIMix9 == null || (media = uIMix9.getSystemui()) == null) {
                    return "01";
                }
                break;
            case dab:
                UIUtil.UIMix uIMix10 = UIUtil.INSTANCE.getUiMap().get("ui_" + uIId);
                if (uIMix10 == null || (media = uIMix10.getDab()) == null) {
                    return "01";
                }
                break;
            case settings:
                UIUtil.UIMix uIMix11 = UIUtil.INSTANCE.getUiMap().get("ui_" + uIId);
                if (uIMix11 == null || (media = uIMix11.getSettings()) == null) {
                    return "01";
                }
                break;
            default:
                return "01";
        }
        return media;
    }

    public void setUI(String uiId) {

        DefaultSharedUtilReflection.setStr(UIKey.ui_s_id_encrypt.name(), UIEncrypt.INSTANCE.changeUIToString(uiId));
    }

    public boolean getRestartWhenSelectUI() {
        return DefaultSharedUtilReflection.getBool(UIKey.ui_b_restart_when_select_ui.name(), UIDefault.INSTANCE.getRestartWhenSelectUI());
    }

    public void setRestartWhenSelectUI(boolean z) {
        DefaultSharedUtilReflection.setBool(UIKey.ui_b_restart_when_select_ui.name(), z);
    }
}
