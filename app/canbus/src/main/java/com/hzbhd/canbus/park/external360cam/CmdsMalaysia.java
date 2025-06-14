package com.hzbhd.canbus.park.external360cam;

import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.util.CommUtil;

/* loaded from: classes2.dex */
public class CmdsMalaysia implements ItfcCmds {
    @Override // com.hzbhd.canbus.park.external360cam.ItfcCmds
    public void frontAll() {
        FutureUtil.instance.reqMcuKey(new byte[]{-56, 5, 0, -1, 5, -5});
    }

    @Override // com.hzbhd.canbus.park.external360cam.ItfcCmds
    public void rearAll() {
        FutureUtil.instance.reqMcuKey(new byte[]{-56, 5, 0, -1, 6, -5});
    }

    @Override // com.hzbhd.canbus.park.external360cam.ItfcCmds
    public void leftAll() {
        FutureUtil.instance.reqMcuKey(new byte[]{-56, 5, 0, -1, 7, -5});
    }

    @Override // com.hzbhd.canbus.park.external360cam.ItfcCmds
    public void rightAll() {
        FutureUtil.instance.reqMcuKey(new byte[]{-56, 5, 0, -1, 8, -5});
    }

    @Override // com.hzbhd.canbus.park.external360cam.ItfcCmds
    public void frontOnly() {
        FutureUtil.instance.reqMcuKey(new byte[]{-56, 5, 0, -1, 9, -5});
    }

    @Override // com.hzbhd.canbus.park.external360cam.ItfcCmds
    public void rearOnly() {
        FutureUtil.instance.reqMcuKey(new byte[]{-56, 5, 0, -1, 16, -5});
    }

    @Override // com.hzbhd.canbus.park.external360cam.ItfcCmds
    public void leftOnly() {
        FutureUtil.instance.reqMcuKey(new byte[]{-56, 5, 0, -1, 17, -5});
    }

    @Override // com.hzbhd.canbus.park.external360cam.ItfcCmds
    public void rightOnly() {
        FutureUtil.instance.reqMcuKey(new byte[]{-56, 5, 0, -1, 18, -5});
    }

    @Override // com.hzbhd.canbus.park.external360cam.ItfcCmds
    public void allFrontLeftRight() {
        FutureUtil.instance.reqMcuKey(new byte[]{-56, 5, 0, -1, 19, -5});
    }

    @Override // com.hzbhd.canbus.park.external360cam.ItfcCmds
    public void fourRegion() {
        FutureUtil.instance.reqMcuKey(new byte[]{-56, 5, 0, -1, 20, -5});
    }

    @Override // com.hzbhd.canbus.park.external360cam.ItfcCmds
    public void allRearLeftRight() {
        FutureUtil.instance.reqMcuKey(new byte[]{-56, 5, 0, -1, 21, -5});
    }

    @Override // com.hzbhd.canbus.park.external360cam.ItfcCmds
    public void exit() {
        FutureUtil.instance.reqMcuKey(new byte[]{-56, 5, 0, -1, 2, -5});
    }

    @Override // com.hzbhd.canbus.park.external360cam.ItfcCmds
    public void enter() {
        FutureUtil.instance.reqMcuKey(new byte[]{-56, 5, 0, -1, 3, -5});
    }

    @Override // com.hzbhd.canbus.park.external360cam.ItfcCmds
    public void sndCoord(int i, int i2) {
        CommUtil.playBeep();
        FutureUtil.instance.sendPositionExtra(5, new byte[]{-69, 102}, i, i2);
    }
}
