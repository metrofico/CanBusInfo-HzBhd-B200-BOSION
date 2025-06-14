package com.hzbhd.canbus.interfaces;

/* loaded from: classes2.dex */
public interface AirControlInterface {
    void action(String str);

    boolean isComplete();

    void most();

    void reset();

    void step();

    void target(String str, String str2);
}
