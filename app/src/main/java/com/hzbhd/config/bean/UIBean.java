package com.hzbhd.config.bean;

import java.util.Objects;

public final class UIBean {

    // Enum for App Name
    public enum AppName {
        media,
        radio,
        bt,
        dsp,
        misc,
        camera,
        widget,
        launcher,
        systemui,
        settings,
        dab
    }

    // Class representing UIMix with its properties
    public static final class UIMix {
        private final String media;
        private final String radio;
        private final String bt;
        private final String dsp;
        private final String misc;
        private final String camera;
        private final String widget;
        private final String launcher;
        private final String systemui;
        private final String settings;
        private String dab;

        // Constructor
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

        // Getters for each field
        public String getMedia() {
            return media;
        }

        public String getRadio() {
            return radio;
        }

        public String getBt() {
            return bt;
        }

        public String getDsp() {
            return dsp;
        }

        public String getMisc() {
            return misc;
        }

        public String getCamera() {
            return camera;
        }

        public String getWidget() {
            return widget;
        }

        public String getLauncher() {
            return launcher;
        }

        public String getSystemui() {
            return systemui;
        }

        public String getSettings() {
            return settings;
        }

        public String getDab() {
            return dab;
        }

        // Setter for 'dab' property
        public void setDab(String dab) {
            if (dab == null) {
                throw new IllegalArgumentException("dab cannot be null.");
            }
            this.dab = dab;
        }

        // Copy method to create a new instance with the same properties
        public UIMix copy(String media, String radio, String bt, String dsp, String misc, String camera, String widget, String launcher, String systemui, String settings, String dab) {
            return new UIMix(media, radio, bt, dsp, misc, camera, widget, launcher, systemui, settings, dab);
        }

        // Override equals method for comparison
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            UIMix uimix = (UIMix) obj;
            return Objects.equals(media, uimix.media) &&
                    Objects.equals(radio, uimix.radio) &&
                    Objects.equals(bt, uimix.bt) &&
                    Objects.equals(dsp, uimix.dsp) &&
                    Objects.equals(misc, uimix.misc) &&
                    Objects.equals(camera, uimix.camera) &&
                    Objects.equals(widget, uimix.widget) &&
                    Objects.equals(launcher, uimix.launcher) &&
                    Objects.equals(systemui, uimix.systemui) &&
                    Objects.equals(settings, uimix.settings) &&
                    Objects.equals(dab, uimix.dab);
        }

        // Override hashCode method
        @Override
        public int hashCode() {
            return Objects.hash(media, radio, bt, dsp, misc, camera, widget, launcher, systemui, settings, dab);
        }

        // Override toString method for a string representation
        @Override
        public String toString() {
            return "UIMix{" +
                    "media='" + media + '\'' +
                    ", radio='" + radio + '\'' +
                    ", bt='" + bt + '\'' +
                    ", dsp='" + dsp + '\'' +
                    ", misc='" + misc + '\'' +
                    ", camera='" + camera + '\'' +
                    ", widget='" + widget + '\'' +
                    ", launcher='" + launcher + '\'' +
                    ", systemui='" + systemui + '\'' +
                    ", settings='" + settings + '\'' +
                    ", dab='" + dab + '\'' +
                    '}';
        }
    }
}
