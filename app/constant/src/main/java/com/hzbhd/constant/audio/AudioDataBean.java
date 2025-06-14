package com.hzbhd.constant.audio;

import java.util.ArrayList;
import java.util.HashMap;

public final class AudioDataBean {

    // Class for Equalizer configuration
    public static final class Equalizer {
        private final ArrayList<Double> freq;
        private final ArrayList<Double> gain;
        private final ArrayList<Double> q;

        public Equalizer(ArrayList<Double> q, ArrayList<Double> freq, ArrayList<Double> gain) {
            this.q = q;
            this.freq = freq;
            this.gain = gain;
        }

        public final ArrayList<Double> getFreq() {
            return this.freq;
        }

        public final ArrayList<Double> getGain() {
            return this.gain;
        }

        public final ArrayList<Double> getQ() {
            return this.q;
        }

        // Method to create a copy of the Equalizer object
        public final Equalizer copy(ArrayList<Double> q, ArrayList<Double> freq, ArrayList<Double> gain) {
            return new Equalizer(q, freq, gain);
        }
    }

    // FaderBalance class holding audio fader data per HORN_TYPE
    public static final class FaderBalance {
        private final HashMap<AudioConstants.HORN_TYPE, Integer> data;

        public FaderBalance(HashMap<AudioConstants.HORN_TYPE, Integer> data) {
            this.data = data;
        }

        public HashMap<AudioConstants.HORN_TYPE, Integer> getData() {
            return this.data;
        }

        // Using AudioConstants.HORN_TYPE and other parameters where necessary
        public final FaderBalance copy(HashMap<AudioConstants.HORN_TYPE, Integer> data) {
            return new FaderBalance(data);
        }
    }

    // DelayTime class for storing delay data for each horn type
    public static final class DelayTime {
        private final HashMap<AudioConstants.HORN_TYPE, Double> data;

        public DelayTime(HashMap<AudioConstants.HORN_TYPE, Double> data) {
            this.data = data;
        }

        public HashMap<AudioConstants.HORN_TYPE, Double> getData() {
            return this.data;
        }

        public final DelayTime copy(HashMap<AudioConstants.HORN_TYPE, Double> data) {
            return new DelayTime(data);
        }
    }

    // Loudness class for managing loudness settings
    public static final class Loudness {
        private boolean enable;
        private double gain;
        private boolean highBoost;

        public Loudness(boolean enable, double gain, boolean highBoost) {
            this.enable = enable;
            this.gain = gain;
            this.highBoost = highBoost;
        }

        public boolean getEnable() {
            return this.enable;
        }

        public double getGain() {
            return this.gain;
        }

        public boolean getHighBoost() {
            return this.highBoost;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }

        public void setGain(double gain) {
            this.gain = gain;
        }

        public void setHighBoost(boolean highBoost) {
            this.highBoost = highBoost;
        }

        // Method to copy Loudness instance
        public final Loudness copy(boolean enable, double gain, boolean highBoost) {
            return new Loudness(enable, gain, highBoost);
        }
    }

    // SourceEq class for storing source equalizer data
    public static final class SourceEq {
        private final HashMap<Integer, Equalizer> data;

        public SourceEq(HashMap<Integer, Equalizer> data) {
            this.data = data;
        }

        public HashMap<Integer, Equalizer> getData() {
            return this.data;
        }

        // Method to copy SourceEq instance
        public final SourceEq copy(HashMap<Integer, Equalizer> data) {
            return new SourceEq(data);
        }
    }

    // UserEq class for storing user-specific equalizer settings
    public static final class UserEq {
        private final HashMap<Integer, Equalizer> data;

        public UserEq(HashMap<Integer, Equalizer> data) {
            this.data = data;
        }

        public HashMap<Integer, Equalizer> getData() {
            return this.data;
        }

        // Method to copy UserEq instance
        public final UserEq copy(HashMap<Integer, Equalizer> data) {
            return new UserEq(data);
        }
    }

    // Hpf class for storing high-pass filter settings
    public static final class Hpf {
        private final double freq;
        private final double q;
        private final int slope;

        public Hpf(double freq, double q, int slope) {
            this.freq = freq;
            this.q = q;
            this.slope = slope;
        }

        public double getFreq() {
            return this.freq;
        }

        public double getQ() {
            return this.q;
        }

        public int getSlope() {
            return this.slope;
        }

        // Method to copy Hpf instance
        public final Hpf copy(double freq, double q, int slope) {
            return new Hpf(freq, q, slope);
        }
    }

    // Lpf class for storing low-pass filter settings
    public static final class Lpf {
        private final double freq;
        private final double q;
        private final int slope;

        public Lpf(double freq, double q, int slope) {
            this.freq = freq;
            this.q = q;
            this.slope = slope;
        }

        public double getFreq() {
            return this.freq;
        }

        public double getQ() {
            return this.q;
        }

        public int getSlope() {
            return this.slope;
        }

        // Method to copy Lpf instance
        public final Lpf copy(double freq, double q, int slope) {
            return new Lpf(freq, q, slope);
        }
    }
}
