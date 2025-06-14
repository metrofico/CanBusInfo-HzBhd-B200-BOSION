package com.hzbhd.canbus.car._469.speech;

import com.hzbhd.canbus.car._469.speech.STxData;

/* loaded from: classes2.dex */
public interface ISysRxListener {
    void AcAir(boolean z);

    void AcAuto(boolean z);

    void AcCold(boolean z);

    void AcDefrost(boolean z);

    void AcLoop(boolean z);

    void AcMode(STxData.AcCtrl.Mode.ModeEnum modeEnum);

    void AcTempDec(int i);

    void AcTempInc(int i);

    void AcTempTo(int i);

    void AcWarm(boolean z);

    void AcWind(boolean z);

    void AcWindTo(int i);

    void CarAlarmlight(boolean z);

    void CarClearancelamps(boolean z);

    void CarHeadlight(boolean z);

    void CarHighBeam(boolean z);

    void CarLock(boolean z);

    void CarReadinglamp(boolean z);

    void CarToplight(boolean z);

    void CarWindowClose();

    void CarWindowOpen(STxData.CarCtrl.WindowOpen.TypeEnum typeEnum);

    void CarWiper(boolean z);

    void CarWiperCtrl(STxData.CarCtrl.WiperCtrl.ModeEnum modeEnum);

    void CarWiperMove(int i);

    void SystemSync();
}
