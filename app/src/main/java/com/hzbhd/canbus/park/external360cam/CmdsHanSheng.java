package com.hzbhd.canbus.park.external360cam;

import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.util.CommUtil;

/* loaded from: classes2.dex */
public class CmdsHanSheng implements ItfcCmds {
    @Override // com.hzbhd.canbus.park.external360cam.ItfcCmds
    public void allFrontLeftRight() {
    }

    @Override // com.hzbhd.canbus.park.external360cam.ItfcCmds
    public void allRearLeftRight() {
    }

    @Override // com.hzbhd.canbus.park.external360cam.ItfcCmds
    public void enter() {
    }

    @Override // com.hzbhd.canbus.park.external360cam.ItfcCmds
    public void exit() {
    }

    @Override // com.hzbhd.canbus.park.external360cam.ItfcCmds
    public void fourRegion() {
    }

    @Override // com.hzbhd.canbus.park.external360cam.ItfcCmds
    public void frontAll() {
    }

    @Override // com.hzbhd.canbus.park.external360cam.ItfcCmds
    public void frontOnly() {
    }

    @Override // com.hzbhd.canbus.park.external360cam.ItfcCmds
    public void leftAll() {
    }

    @Override // com.hzbhd.canbus.park.external360cam.ItfcCmds
    public void leftOnly() {
    }

    @Override // com.hzbhd.canbus.park.external360cam.ItfcCmds
    public void rearAll() {
    }

    @Override // com.hzbhd.canbus.park.external360cam.ItfcCmds
    public void rearOnly() {
    }

    @Override // com.hzbhd.canbus.park.external360cam.ItfcCmds
    public void rightAll() {
    }

    @Override // com.hzbhd.canbus.park.external360cam.ItfcCmds
    public void rightOnly() {
    }

    @Override // com.hzbhd.canbus.park.external360cam.ItfcCmds
    public void sndCoord(int i, int i2) {
        CommUtil.playBeep();
        FutureUtil.instance.sendDtvPosition(i, i2);
    }
}
