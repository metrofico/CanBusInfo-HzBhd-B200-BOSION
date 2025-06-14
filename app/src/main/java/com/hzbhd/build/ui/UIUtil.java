package com.hzbhd.build.ui;

import java.util.HashMap;
import java.util.Map;

public final class UIUtil {
    public static final UIUtil INSTANCE = new UIUtil();

    // Reemplazo del mapa de Kotlin con un HashMap en Java
    private static final Map<String, UIMix> uiMap = new HashMap<String, UIMix>() {{
        put("ui_a1", new UIMix("a1", "a1", "a1", "a1", "a1", "a1", "a1", "a1", "a1", "a1", "01"));
        put("ui_h2", new UIMix("h2", "h2", "h2", "h2", "01", "f5", "h2", "h2", "h2", "01", "01"));
        put("ui_0g", new UIMix("0g", "0g", "0g", "01", "0g", "01", "0g", "0g", "0g", "01", "01"));
        put("ui_c3", new UIMix("h3", "h3", "h3", "01", "01", "01", "c3", "c3", "c3", "01", "01"));
        put("ui_69", new UIMix("69", "69", "69", "01", "01", "01", "69", "69", "69", "01", "01"));
        put("ui_55", new UIMix("55", "55", "55", "01", "01", "01", "55", "55", "55", "01", "01"));
        put("ui_i1", new UIMix("i1", "i1", "i1", "01", "01", "01", "i1", "i1", "i1", "01", "01"));
        put("ui_e3", new UIMix("e3", "e3", "e3", "e3", "e3", "01", "e3", "e3", "e3", "01", "01"));
        put("ui_f3", new UIMix("f3", "f3", "f3", "f3", "01", "f5", "f3", "f3", "f3", "01", "01"));
    }};

    private UIUtil() {
    }

    public final Map<String, UIMix> getUiMap() {
        return uiMap;
    }

    public static final class UIMix {
        private final String bt;
        private final String camera;
        private String dab;
        private final String dsp;
        private final String launcher;
        private final String media;
        private final String misc;
        private final String radio;
        private final String settings;
        private final String systemui;
        private final String widget;

        public UIMix(String media, String radio, String bt, String dsp, String misc, String camera, String widget, String launcher, String systemui, String settings, String dab) {
            if (media == null || radio == null || bt == null || dsp == null || misc == null || camera == null || widget == null || launcher == null || systemui == null || settings == null || dab == null) {
                throw new IllegalArgumentException("None of the parameters can be null.");
            }
            this.media = media;
            this.radio = radio;
            this.bt = bt;
            this.dsp = dsp;
            this.misc = misc;
            this.camera = camera;
            this.widget = widget;
            this.launcher = launcher;
            this.systemui = systemui;
            this.settings = settings;
            this.dab = dab;
        }

        public String getBt() {
            return this.bt;
        }

        public String getCamera() {
            return this.camera;
        }

        public String getDab() {
            return this.dab;
        }

        public String getDsp() {
            return this.dsp;
        }

        public String getLauncher() {
            return this.launcher;
        }

        public String getMedia() {
            return this.media;
        }

        public String getMisc() {
            return this.misc;
        }

        public String getRadio() {
            return this.radio;
        }

        public String getSettings() {
            return this.settings;
        }

        public String getSystemui() {
            return this.systemui;
        }

        public String getWidget() {
            return this.widget;
        }

        public void setDab(String dab) {
            if (dab == null) {
                throw new IllegalArgumentException("dab cannot be null");
            }
            this.dab = dab;
        }

        public UIMix copy(String media, String radio, String bt, String dsp, String misc, String camera, String widget, String launcher, String systemui, String settings, String dab) {
            return new UIMix(media, radio, bt, dsp, misc, camera, widget, launcher, systemui, settings, dab);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof UIMix)) {
                return false;
            }
            UIMix uIMix = (UIMix) other;
            return this.media.equals(uIMix.media) && this.radio.equals(uIMix.radio) && this.bt.equals(uIMix.bt)
                    && this.dsp.equals(uIMix.dsp) && this.misc.equals(uIMix.misc) && this.camera.equals(uIMix.camera)
                    && this.widget.equals(uIMix.widget) && this.launcher.equals(uIMix.launcher) && this.systemui.equals(uIMix.systemui)
                    && this.settings.equals(uIMix.settings) && this.dab.equals(uIMix.dab);
        }

        @Override
        public int hashCode() {
            return (((((((((((((((((((media.hashCode() * 31) + radio.hashCode()) * 31) + bt.hashCode()) * 31) + dsp.hashCode()) * 31)
                    + misc.hashCode()) * 31) + camera.hashCode()) * 31) + widget.hashCode()) * 31) + launcher.hashCode()) * 31)
                    + systemui.hashCode()) * 31) + settings.hashCode()) * 31) + dab.hashCode();
        }

        @Override
        public String toString() {
            return "UIMix(media=" + media + ", radio=" + radio + ", bt=" + bt + ", dsp=" + dsp + ", misc=" + misc
                    + ", camera=" + camera + ", widget=" + widget + ", launcher=" + launcher + ", systemui=" + systemui
                    + ", settings=" + settings + ", dab=" + dab + ")";
        }
    }
}
