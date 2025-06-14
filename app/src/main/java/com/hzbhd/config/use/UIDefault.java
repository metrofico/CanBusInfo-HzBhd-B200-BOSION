package com.hzbhd.config.use;

import kotlin.Metadata;

/* compiled from: UIDefault.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0019\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\t\u001a\u00020\nX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u0005X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u0011X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0019\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0015\u0010\u0007R\u0014\u0010\u0016\u001a\u00020\nX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/hzbhd/config/use/UIDefault;", "", "()V", "allSelectAbleUI", "", "", "getAllSelectAbleUI", "()[Ljava/lang/String;", "[Ljava/lang/String;", "colorViewColor", "", "getColorViewColor", "()I", "currUI", "getCurrUI", "()Ljava/lang/String;", "restartWhenSelectUI", "", "getRestartWhenSelectUI", "()Z", "selectUI", "getSelectUI", "uiScale", "getUiScale", "UI-config_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class UIDefault {
    private static final int colorViewColor = 0;
    public static final UIDefault INSTANCE = new UIDefault();
    private static final boolean restartWhenSelectUI = true;
    private static final String currUI = "e3";
    private static final String[] selectUI = {"e3", "55", "0g", "i1", "69", "c3"};
    private static final String[] allSelectAbleUI = {"a1", "h2", "0g", "c3", "69", "55", "i1", "e3", "f3"};
    private static final int uiScale = 720;

    private UIDefault() {
    }

    public final boolean getRestartWhenSelectUI() {
        return restartWhenSelectUI;
    }

    public final String getCurrUI() {
        return currUI;
    }

    public final String[] getSelectUI() {
        return selectUI;
    }

    public final String[] getAllSelectAbleUI() {
        return allSelectAbleUI;
    }

    public final int getUiScale() {
        return uiScale;
    }

    public final int getColorViewColor() {
        return colorViewColor;
    }
}
