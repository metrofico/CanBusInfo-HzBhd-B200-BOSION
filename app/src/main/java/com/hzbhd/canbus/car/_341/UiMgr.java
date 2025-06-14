package com.hzbhd.canbus.car._341;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.bean.RearArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UiMgr.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/hzbhd/canbus/car/_341/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "airPageUiSet", "Lcom/hzbhd/canbus/adapter/bean/AirPageUiSet;", "getAirPageUiSet", "()Lcom/hzbhd/canbus/adapter/bean/AirPageUiSet;", "amplifierPageUiSet", "Lcom/hzbhd/canbus/ui_set/AmplifierPageUiSet;", "getAmplifierPageUiSet", "()Lcom/hzbhd/canbus/ui_set/AmplifierPageUiSet;", "settingPageUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "getSettingPageUiSet", "()Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class UiMgr extends AbstractUiMgr {
    private final AirPageUiSet airPageUiSet;
    private final AmplifierPageUiSet amplifierPageUiSet;
    private final SettingPageUiSet settingPageUiSet;

    public UiMgr(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        this.settingPageUiSet = settingUiSet;
        AirPageUiSet airUiSet = getAirUiSet(context);
        this.airPageUiSet = airUiSet;
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        this.amplifierPageUiSet = amplifierPageUiSet;
        UiMgrKt.setParam(0);
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, -124});
        if (settingUiSet != null) {
            settingUiSet.setOnSettingItemSwitchListener(new OnSettingItemSwitchListener() { // from class: com.hzbhd.canbus.car._341.UiMgr$$ExternalSyntheticLambda0
                @Override // com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener
                public final void onSwitch(int i, int i2, int i3) {
                    UiMgr.m732_init_$lambda0(i, i2, i3);
                }
            });
        }
        FrontArea frontArea = airUiSet != null ? airUiSet.getFrontArea() : null;
        if (frontArea != null) {
            frontArea.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._341.UiMgr$$ExternalSyntheticLambda1
                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
                public final void onClickItem(int i) {
                    UiMgr.m733_init_$lambda1(i);
                }
            }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._341.UiMgr$$ExternalSyntheticLambda2
                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
                public final void onClickItem(int i) {
                    UiMgr.m734_init_$lambda2(i);
                }
            }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._341.UiMgr$$ExternalSyntheticLambda3
                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
                public final void onClickItem(int i) {
                    UiMgr.m735_init_$lambda3(i);
                }
            }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._341.UiMgr$$ExternalSyntheticLambda4
                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
                public final void onClickItem(int i) {
                    UiMgr.m736_init_$lambda4(i);
                }
            }});
        }
        FrontArea frontArea2 = airUiSet != null ? airUiSet.getFrontArea() : null;
        if (frontArea2 != null) {
            frontArea2.setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._341.UiMgr.6
                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
                public void onClickLeft() {
                    UiMgrKt.sendAirConditionData(21);
                }

                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
                public void onClickRight() {
                    UiMgrKt.sendAirConditionData(20);
                }
            });
        }
        FrontArea frontArea3 = airUiSet != null ? airUiSet.getFrontArea() : null;
        if (frontArea3 != null) {
            frontArea3.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._341.UiMgr.7
                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
                public void onClickUp() {
                    UiMgrKt.sendAirConditionData(9);
                }

                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
                public void onClickDown() {
                    UiMgrKt.sendAirConditionData(10);
                }
            }, null, new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._341.UiMgr.8
                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
                public void onClickUp() {
                    UiMgrKt.sendAirConditionData(11);
                }

                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
                public void onClickDown() {
                    UiMgrKt.sendAirConditionData(12);
                }
            }});
        }
        FrontArea frontArea4 = airUiSet != null ? airUiSet.getFrontArea() : null;
        if (frontArea4 != null) {
            frontArea4.setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._341.UiMgr.9
                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
                public void onLeftSeatClick() {
                    UiMgrKt.sendAirConditionData(17);
                }

                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
                public void onRightSeatClick() {
                    UiMgrKt.sendAirConditionData(42);
                }
            });
        }
        RearArea rearArea = airUiSet != null ? airUiSet.getRearArea() : null;
        if (rearArea != null) {
            rearArea.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._341.UiMgr$$ExternalSyntheticLambda5
                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
                public final void onClickItem(int i) {
                    UiMgr.m737_init_$lambda5(i);
                }
            }, null, null, null});
        }
        RearArea rearArea2 = airUiSet != null ? airUiSet.getRearArea() : null;
        if (rearArea2 != null) {
            rearArea2.setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._341.UiMgr.11
                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
                public void onClickLeft() {
                    UiMgrKt.sendAirConditionData(46);
                }

                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
                public void onClickRight() {
                    UiMgrKt.sendAirConditionData(45);
                }
            });
        }
        RearArea rearArea3 = airUiSet != null ? airUiSet.getRearArea() : null;
        if (rearArea3 != null) {
            rearArea3.setTempSetViewOnUpDownClickListeners(new AnonymousClass12[]{null, new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._341.UiMgr.12
                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
                public void onClickUp() {
                    UiMgrKt.sendAirConditionData(43);
                }

                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
                public void onClickDown() {
                    UiMgrKt.sendAirConditionData(44);
                }
            }, null});
        }
        RearArea rearArea4 = airUiSet != null ? airUiSet.getRearArea() : null;
        if (rearArea4 != null) {
            rearArea4.setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._341.UiMgr.13
                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
                public void onLeftSeatClick() {
                    UiMgrKt.sendAirConditionData(47);
                }

                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
                public void onRightSeatClick() {
                    UiMgrKt.sendAirConditionData(47);
                }
            });
        }
        if (amplifierPageUiSet != null) {
            amplifierPageUiSet.setOnAmplifierPositionListener(new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._341.UiMgr.14

                /* compiled from: UiMgr.kt */
                @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
                /* renamed from: com.hzbhd.canbus.car._341.UiMgr$14$WhenMappings */
                public /* synthetic */ class WhenMappings {
                    public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                    static {
                        int[] iArr = new int[AmplifierActivity.AmplifierPosition.values().length];
                        iArr[AmplifierActivity.AmplifierPosition.FRONT_REAR.ordinal()] = 1;
                        iArr[AmplifierActivity.AmplifierPosition.LEFT_RIGHT.ordinal()] = 2;
                        $EnumSwitchMapping$0 = iArr;
                    }
                }

                @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
                public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int value) {
                    Intrinsics.checkNotNullParameter(amplifierPosition, "amplifierPosition");
                    Log.i("lyn", new StringBuilder().append(' ').append(value).toString());
                    int iRangeNumber = DataHandleUtils.rangeNumber(value + 7, 0, 14);
                    int i = WhenMappings.$EnumSwitchMapping$0[amplifierPosition.ordinal()];
                    if (i == 1) {
                        UiMgrKt.sendSettingsData(1, MsgMgrKt.reverse(iRangeNumber));
                    } else {
                        if (i != 2) {
                            return;
                        }
                        UiMgrKt.sendSettingsData(2, iRangeNumber);
                    }
                }
            });
        }
        if (amplifierPageUiSet == null) {
            return;
        }
        amplifierPageUiSet.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._341.UiMgr.15

            /* compiled from: UiMgr.kt */
            @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
            /* renamed from: com.hzbhd.canbus.car._341.UiMgr$15$WhenMappings */
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[AmplifierActivity.AmplifierBand.values().length];
                    iArr[AmplifierActivity.AmplifierBand.BASS.ordinal()] = 1;
                    iArr[AmplifierActivity.AmplifierBand.TREBLE.ordinal()] = 2;
                    iArr[AmplifierActivity.AmplifierBand.MIDDLE.ordinal()] = 3;
                    iArr[AmplifierActivity.AmplifierBand.VOLUME.ordinal()] = 4;
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
            public void progress(AmplifierActivity.AmplifierBand amplifierBand, int progress) {
                Intrinsics.checkNotNullParameter(amplifierBand, "amplifierBand");
                Log.i("lyn", new StringBuilder().append(' ').append(progress).toString());
                int iRangeNumber = DataHandleUtils.rangeNumber(progress + 2, 2, 12);
                int iRangeNumber2 = DataHandleUtils.rangeNumber(progress, 0, 63);
                int i = WhenMappings.$EnumSwitchMapping$0[amplifierBand.ordinal()];
                if (i == 1) {
                    UiMgrKt.sendSettingsData(4, iRangeNumber);
                    return;
                }
                if (i == 2) {
                    UiMgrKt.sendSettingsData(5, iRangeNumber);
                } else if (i == 3) {
                    UiMgrKt.sendSettingsData(6, iRangeNumber);
                } else {
                    if (i != 4) {
                        return;
                    }
                    UiMgrKt.sendSettingsData(7, iRangeNumber2);
                }
            }
        });
    }

    public final SettingPageUiSet getSettingPageUiSet() {
        return this.settingPageUiSet;
    }

    public final AirPageUiSet getAirPageUiSet() {
        return this.airPageUiSet;
    }

    public final AmplifierPageUiSet getAmplifierPageUiSet() {
        return this.amplifierPageUiSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-0, reason: not valid java name */
    public static final void m732_init_$lambda0(int i, int i2, int i3) {
        if (i == 0) {
            if (i2 == 0) {
                UiMgrKt.sendSettingsData(8, i3);
                return;
            } else {
                if (i2 != 1) {
                    return;
                }
                UiMgrKt.sendSettingsData(10, i3);
                return;
            }
        }
        if (i != 1) {
            return;
        }
        if (i2 != 0) {
            if (i2 != 1) {
                return;
            }
            UiMgrKt.sendSettingsData(9, i3);
        } else if (i3 == 0) {
            UiMgrKt.sendSettingsData(3, 1);
        } else {
            UiMgrKt.sendSettingsData(3, 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-1, reason: not valid java name */
    public static final void m733_init_$lambda1(int i) {
        if (i == 0) {
            UiMgrKt.sendAirConditionData(35);
            return;
        }
        if (i == 1) {
            UiMgrKt.sendAirConditionData(15);
            return;
        }
        if (i == 2) {
            UiMgrKt.sendAirConditionData(50);
            return;
        }
        if (i == 3) {
            UiMgrKt.sendAirConditionData(37);
        } else {
            if (i != 4) {
                return;
            }
            if (GeneralAirData.power) {
                UiMgrKt.sendAirConditionData(1);
            } else {
                UiMgrKt.sendAirConditionData(20);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-2, reason: not valid java name */
    public static final void m734_init_$lambda2(int i) {
        if (i == 0) {
            UiMgrKt.sendAirConditionData(38);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-3, reason: not valid java name */
    public static final void m735_init_$lambda3(int i) {
        if (i == 0) {
            UiMgrKt.sendAirConditionData(4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-4, reason: not valid java name */
    public static final void m736_init_$lambda4(int i) {
        if (i == 0) {
            UiMgrKt.sendAirConditionData(2);
        } else if (i == 1) {
            UiMgrKt.sendAirConditionData(8);
        } else {
            if (i != 2) {
                return;
            }
            UiMgrKt.sendAirConditionData(16);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-5, reason: not valid java name */
    public static final void m737_init_$lambda5(int i) {
        if (i == 0) {
            UiMgrKt.sendAirConditionData(49);
        }
    }
}
