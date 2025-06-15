package com.hzbhd.canbus.control.air_control;

import com.hzbhd.canbus.interfaces.AirControlInterface;


public class AbstractAirControl implements AirControlInterface {
    @Override // com.hzbhd.canbus.interfaces.AirControlInterface
    public void action(String str) {
    }

    @Override // com.hzbhd.canbus.interfaces.AirControlInterface
    public boolean isComplete() {
        return false;
    }

    @Override // com.hzbhd.canbus.interfaces.AirControlInterface
    public void most() {
    }

    @Override // com.hzbhd.canbus.interfaces.AirControlInterface
    public void reset() {
    }

    @Override // com.hzbhd.canbus.interfaces.AirControlInterface
    public void step() {
    }

    @Override // com.hzbhd.canbus.interfaces.AirControlInterface
    public void target(String str, String str2) {
    }
}
