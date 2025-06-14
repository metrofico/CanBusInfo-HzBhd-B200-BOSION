package com.hzbhd.constant.share.canbus;

/* loaded from: classes2.dex */
public class CanbusAirShare {
    private boolean ac;
    private boolean auto;
    private boolean blow_foot;
    private boolean blow_head;
    private boolean blow_window;
    private boolean dual;
    private boolean front_defog;
    private boolean in_out_cycle;
    private String left_temperature;
    private boolean power;
    private boolean rear_defog;
    private String right_temperature;
    private int wind_speed;

    public void setWind_speed(int i) {
        this.wind_speed = i;
    }

    public int getWind_speed() {
        return this.wind_speed;
    }

    public void setLeft_temperature(String str) {
        this.left_temperature = str;
    }

    public String getLeft_temperature() {
        return this.left_temperature;
    }

    public void setRight_temperature(String str) {
        this.right_temperature = str;
    }

    public String getRight_temperature() {
        return this.right_temperature;
    }

    public void setBlow_window(boolean z) {
        this.blow_window = z;
    }

    public boolean getBlow_window() {
        return this.blow_window;
    }

    public void setBlow_head(boolean z) {
        this.blow_head = z;
    }

    public boolean getBlow_head() {
        return this.blow_head;
    }

    public void setBlow_foot(boolean z) {
        this.blow_foot = z;
    }

    public boolean getBlow_foot() {
        return this.blow_foot;
    }

    public void setFront_defog(boolean z) {
        this.front_defog = z;
    }

    public boolean getFront_defog() {
        return this.front_defog;
    }

    public void setAc(boolean z) {
        this.ac = z;
    }

    public boolean getAc() {
        return this.ac;
    }

    public void setDual(boolean z) {
        this.dual = z;
    }

    public boolean getDual() {
        return this.dual;
    }

    public void setAuto(boolean z) {
        this.auto = z;
    }

    public boolean getAuto() {
        return this.auto;
    }

    public void setRear_defog(boolean z) {
        this.rear_defog = z;
    }

    public boolean getRear_defog() {
        return this.rear_defog;
    }

    public void setPower(boolean z) {
        this.power = z;
    }

    public boolean getPower() {
        return this.power;
    }

    public void setIn_out_cycle(boolean z) {
        this.in_out_cycle = z;
    }

    public boolean getIn_out_cycle() {
        return this.in_out_cycle;
    }
}
