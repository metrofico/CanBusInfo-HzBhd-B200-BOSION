package com.hzbhd.config.use;


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
