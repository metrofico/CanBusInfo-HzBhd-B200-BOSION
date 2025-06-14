package com.hzbhd.canbus.car._362;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.car._350.MsgMgrKt;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AirBtnAction;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UiMgr.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\u0010\u0005\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0002J\u0018\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0002J\u001c\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\n\u0010\u0015\u001a\u00020\u0018\"\u00020\u0019H\u0002J\u0010\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u0014H\u0002J\u0010\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001d"}, d2 = {"Lcom/hzbhd/canbus/car/_362/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "airPageUiSet", "Lcom/hzbhd/canbus/adapter/bean/AirPageUiSet;", "getAirPageUiSet", "()Lcom/hzbhd/canbus/adapter/bean/AirPageUiSet;", "amplifierPageUiSet", "Lcom/hzbhd/canbus/ui_set/AmplifierPageUiSet;", "getAmplifierPageUiSet", "()Lcom/hzbhd/canbus/ui_set/AmplifierPageUiSet;", "settingPageUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "getSettingPageUiSet", "()Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "send83", "", "data0", "", "data1", "send84", "send8B", "", "", "sendE0", "d0", "sendE2", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class UiMgr extends AbstractUiMgr {
    private final AirPageUiSet airPageUiSet;
    private final AmplifierPageUiSet amplifierPageUiSet;
    private final SettingPageUiSet settingPageUiSet;

    public UiMgr(final Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        Intrinsics.checkNotNull(settingUiSet);
        this.settingPageUiSet = settingUiSet;
        AirPageUiSet airUiSet = getAirUiSet(context);
        Intrinsics.checkNotNull(airUiSet);
        this.airPageUiSet = airUiSet;
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        Intrinsics.checkNotNullExpressionValue(amplifierPageUiSet, "getAmplifierPageUiSet(context)");
        this.amplifierPageUiSet = amplifierPageUiSet;
        send8B(1, new byte[0]);
        FrontArea frontArea = airUiSet.getFrontArea();
        if (frontArea != null) {
            frontArea.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._362.UiMgr$$ExternalSyntheticLambda0
                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
                public final void onClickItem(int i) throws InterruptedException {
                    UiMgr.m774_init_$lambda0(this.f$0, i);
                }
            }, null, null, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._362.UiMgr$$ExternalSyntheticLambda1
                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
                public final void onClickItem(int i) throws InterruptedException {
                    UiMgr.m775_init_$lambda1(this.f$0, i);
                }
            }});
        }
        FrontArea frontArea2 = airUiSet.getFrontArea();
        if (frontArea2 != null) {
            frontArea2.setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._362.UiMgr.3
                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
                public void onClickLeft() throws InterruptedException {
                    UiMgr.this.sendE0(9);
                }

                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
                public void onClickRight() throws InterruptedException {
                    UiMgr.this.sendE0(10);
                }
            });
        }
        FrontArea frontArea3 = airUiSet.getFrontArea();
        if (frontArea3 != null) {
            frontArea3.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._362.UiMgr.4
                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
                public void onClickUp() throws InterruptedException {
                    UiMgr.this.sendE0(3);
                }

                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
                public void onClickDown() throws InterruptedException {
                    UiMgr.this.sendE0(2);
                }
            }, null, new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._362.UiMgr.5
                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
                public void onClickUp() throws InterruptedException {
                    UiMgr.this.sendE0(5);
                }

                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
                public void onClickDown() throws InterruptedException {
                    UiMgr.this.sendE0(4);
                }
            }});
        }
        FrontArea frontArea4 = airUiSet.getFrontArea();
        if (frontArea4 != null) {
            frontArea4.setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._362.UiMgr.6
                private int i;
                private final int[] mode = {32, 33};

                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
                public void onLeftSeatClick() {
                }

                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
                public void onRightSeatClick() {
                }

                public final int[] getMode() {
                    return this.mode;
                }

                public final int getI() {
                    return this.i;
                }

                public final void setI(int i) {
                    this.i = i;
                }
            });
        }
        FrontArea frontArea5 = airUiSet.getFrontArea();
        if (frontArea5 != null) {
            frontArea5.setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._362.UiMgr.7
                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
                public void onClickMin() {
                }

                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
                public void onClickPlus() throws InterruptedException {
                    UiMgr.this.sendE0(11);
                }
            }, new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._362.UiMgr.8
                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
                public void onClickMin() {
                }

                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
                public void onClickPlus() throws InterruptedException {
                    UiMgr.this.sendE0(13);
                }
            }});
        }
        settingUiSet.setOnSettingItemSwitchListener(new OnSettingItemSwitchListener() { // from class: com.hzbhd.canbus.car._362.UiMgr$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener
            public final void onSwitch(int i, int i2, int i3) {
                UiMgr.m776_init_$lambda2(this.f$0, i, i2, i3);
            }
        });
        settingUiSet.setOnSettingItemClickListener(new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._362.UiMgr$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
            public final void onClickItem(int i, int i2) {
                UiMgr.m777_init_$lambda3(this.f$0, i, i2);
            }
        });
        settingUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._362.UiMgr$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
            public final void onConfirmClick(int i, int i2) {
                UiMgr.m778_init_$lambda4(this.f$0, context, i, i2);
            }
        });
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._362.UiMgr$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public final void onClickItem(int i, int i2, int i3) {
                UiMgr.m779_init_$lambda5(this.f$0, i, i2, i3);
            }
        });
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._362.UiMgr$$ExternalSyntheticLambda6
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public final void onClickItem(int i, int i2, int i3) {
                UiMgr.m780_init_$lambda6(this.f$0, i, i2, i3);
            }
        });
        amplifierPageUiSet.setOnAmplifierPositionListener(new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._362.UiMgr.14

            /* compiled from: UiMgr.kt */
            @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
            /* renamed from: com.hzbhd.canbus.car._362.UiMgr$14$WhenMappings */
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
                int iRangeNumber = DataHandleUtils.rangeNumber(value + 7, 0, 14);
                int i = WhenMappings.$EnumSwitchMapping$0[amplifierPosition.ordinal()];
                if (i == 1) {
                    UiMgr.this.send84(1, MsgMgrKt.reverse$default(iRangeNumber, 0, 2, null));
                } else {
                    if (i != 2) {
                        return;
                    }
                    UiMgr.this.send84(2, iRangeNumber);
                }
            }
        });
        amplifierPageUiSet.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._362.UiMgr.15

            /* compiled from: UiMgr.kt */
            @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
            /* renamed from: com.hzbhd.canbus.car._362.UiMgr$15$WhenMappings */
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
                int iRangeNumber = DataHandleUtils.rangeNumber(progress + 2, 2, 12);
                int iRangeNumber2 = DataHandleUtils.rangeNumber(progress, 0, 63);
                int i = WhenMappings.$EnumSwitchMapping$0[amplifierBand.ordinal()];
                if (i == 1) {
                    UiMgr.this.send84(4, iRangeNumber);
                    return;
                }
                if (i == 2) {
                    UiMgr.this.send84(5, iRangeNumber);
                } else if (i == 3) {
                    UiMgr.this.send84(6, iRangeNumber);
                } else {
                    if (i != 4) {
                        return;
                    }
                    UiMgr.this.send84(7, iRangeNumber2);
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
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /* renamed from: _init_$lambda-0, reason: not valid java name */
    public static final void m774_init_$lambda0(UiMgr this$0, int i) throws InterruptedException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str = this$0.airPageUiSet.getFrontArea().getLineBtnAction()[0][i];
        if (str != null) {
            switch (str.hashCode()) {
                case -92674103:
                    if (str.equals(AirBtnAction.FRONT_WINDOW_HEAT)) {
                        this$0.sendE0(22);
                        break;
                    }
                    break;
                case 106858757:
                    if (str.equals("power")) {
                        this$0.sendE0(1);
                        break;
                    }
                    break;
                case 395882750:
                    if (str.equals(AirBtnAction.NEGATIVE_ION)) {
                        this$0.sendE0(17);
                        break;
                    }
                    break;
                case 756225563:
                    if (str.equals("in_out_cycle")) {
                        this$0.sendE0(25);
                        break;
                    }
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-1, reason: not valid java name */
    public static final void m775_init_$lambda1(UiMgr this$0, int i) throws InterruptedException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str = this$0.airPageUiSet.getFrontArea().getLineBtnAction()[3][i];
        if (str != null) {
            int iHashCode = str.hashCode();
            if (iHashCode == -246396018) {
                if (str.equals(AirBtnAction.MAX_FRONT)) {
                    this$0.sendE0(19);
                }
            } else if (iHashCode == 3106) {
                if (str.equals("ac")) {
                    this$0.sendE0(23);
                }
            } else if (iHashCode == 3005871 && str.equals("auto")) {
                this$0.sendE0(21);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /* renamed from: _init_$lambda-2, reason: not valid java name */
    public static final void m776_init_$lambda2(UiMgr this$0, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String titleSrn = this$0.settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        if (titleSrn != null) {
            switch (titleSrn.hashCode()) {
                case -1417652657:
                    if (titleSrn.equals("S362_powerAmplifierInfo_1")) {
                        this$0.send84(3, i3 != 1 ? 1 : 8);
                        break;
                    }
                    break;
                case 213507944:
                    if (titleSrn.equals("S362_drivingModeAndAssistanceInfo_1")) {
                        this$0.send8B(3, new byte[0]);
                        break;
                    }
                    break;
                case 213507946:
                    if (titleSrn.equals("S362_drivingModeAndAssistanceInfo_3")) {
                        this$0.send8B(4, new byte[0]);
                        break;
                    }
                    break;
                case 213507947:
                    if (titleSrn.equals("S362_drivingModeAndAssistanceInfo_4")) {
                        this$0.send8B(5, new byte[0]);
                        break;
                    }
                    break;
                case 213507948:
                    if (titleSrn.equals("S362_drivingModeAndAssistanceInfo_5")) {
                        this$0.send8B(9, 1);
                        break;
                    }
                    break;
                case 213507950:
                    if (titleSrn.equals("S362_drivingModeAndAssistanceInfo_7")) {
                        this$0.send8B(8, new byte[0]);
                        break;
                    }
                    break;
                case 465785566:
                    if (titleSrn.equals("S362_vehicleSettings_1")) {
                        this$0.send83(16, i3);
                        break;
                    }
                    break;
                case 465785567:
                    if (titleSrn.equals("S362_vehicleSettings_2")) {
                        this$0.send83(17, i3);
                        break;
                    }
                    break;
                case 465785568:
                    if (titleSrn.equals("S362_vehicleSettings_3")) {
                        this$0.send83(18, i3);
                        break;
                    }
                    break;
                case 465785569:
                    if (titleSrn.equals("S362_vehicleSettings_4")) {
                        this$0.send83(19, i3);
                        break;
                    }
                    break;
                case 1168298533:
                    if (titleSrn.equals("S361_d0b7")) {
                        this$0.send83(4, i3);
                        break;
                    }
                    break;
                case 1168299491:
                    if (titleSrn.equals("S361_d1b4")) {
                        this$0.send83(3, i3);
                        break;
                    }
                    break;
                case 1168299492:
                    if (titleSrn.equals("S361_d1b5")) {
                        this$0.send83(2, i3);
                        break;
                    }
                    break;
                case 1168299493:
                    if (titleSrn.equals("S361_d1b6")) {
                        this$0.send83(1, i3);
                        break;
                    }
                    break;
                case 1168299494:
                    if (titleSrn.equals("S361_d1b7")) {
                        this$0.send83(0, i3);
                        break;
                    }
                    break;
                case 1168300454:
                    if (titleSrn.equals("S361_d2b6")) {
                        this$0.send83(14, i3);
                        break;
                    }
                    break;
                case 1168300455:
                    if (titleSrn.equals("S361_d2b7")) {
                        this$0.send83(13, i3);
                        break;
                    }
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-3, reason: not valid java name */
    public static final void m777_init_$lambda3(UiMgr this$0, int i, int i2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-4, reason: not valid java name */
    public static final void m778_init_$lambda4(UiMgr this$0, Context context, int i, int i2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(context, "$context");
        String titleSrn = this$0.settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        if (Intrinsics.areEqual(titleSrn, "S362_car_1")) {
            this$0.sendE2(0);
        } else if (Intrinsics.areEqual(titleSrn, "S362_car_2")) {
            this$0.sendE2(1);
        }
        this$0.removeSettingLeftItemByNameList(context, new String[]{"S361_car_title"});
        MsgMgrInterface canMsgMgr = MsgMgrFactory.getCanMsgMgr(context);
        Intrinsics.checkNotNull(canMsgMgr, "null cannot be cast to non-null type com.hzbhd.canbus.car._362.MsgMgr");
        ((MsgMgr) canMsgMgr).returnClick();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /* renamed from: _init_$lambda-5, reason: not valid java name */
    public static final void m779_init_$lambda5(UiMgr this$0, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String titleSrn = this$0.settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        if (titleSrn != null) {
            switch (titleSrn.hashCode()) {
                case -833271458:
                    if (titleSrn.equals("S362_vehicleSpeedInfo_1")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -118, (byte) (i3 * 10)});
                        break;
                    }
                    break;
                case 896493878:
                    if (titleSrn.equals("S362_carSpeedInfo_1")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -119, (byte) (i3 * 10)});
                        break;
                    }
                    break;
                case 1748428644:
                    if (titleSrn.equals("S361_d0b6t4")) {
                        this$0.send83(6, i3);
                        break;
                    }
                    break;
                case 1749346397:
                    if (titleSrn.equals("S361_d1b0t2")) {
                        this$0.send83(5, i3);
                        break;
                    }
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* renamed from: _init_$lambda-6, reason: not valid java name */
    public static final void m780_init_$lambda6(UiMgr this$0, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String titleSrn = this$0.settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        if (titleSrn != null) {
            switch (titleSrn.hashCode()) {
                case 213507945:
                    if (titleSrn.equals("S362_drivingModeAndAssistanceInfo_2")) {
                        if (i3 == 0) {
                            this$0.send8B(1, new byte[0]);
                            break;
                        } else if (i3 == 1) {
                            this$0.send8B(2, new byte[0]);
                            break;
                        } else if (i3 == 2) {
                            this$0.send8B(0, new byte[0]);
                            break;
                        }
                    }
                    break;
                case 213507949:
                    if (titleSrn.equals("S362_drivingModeAndAssistanceInfo_6")) {
                        this$0.send8B(7, (byte) i3);
                        break;
                    }
                    break;
                case 1168300453:
                    if (titleSrn.equals("S361_d2b5")) {
                        this$0.send83(15, i3);
                        break;
                    }
                    break;
                case 1748422875:
                    if (titleSrn.equals("S361_d0b0t1")) {
                        this$0.send83(7, i3);
                        break;
                    }
                    break;
                case 1748424799:
                    if (titleSrn.equals("S361_d0b2t3")) {
                        this$0.send83(12, i3);
                        break;
                    }
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void send84(int data0, int data1) {
        CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte) data0, (byte) data1});
    }

    private final void send83(int data0, int data1) {
        CanbusMsgSender.sendMsg(new byte[]{22, -125, (byte) data0, (byte) data1});
    }

    private final void send8B(int data0, byte... data1) {
        CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -117, (byte) data0}, data1));
    }

    private final void sendE2(int data0) {
        CanbusMsgSender.sendMsg(new byte[]{22, -117, (byte) data0});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendE0(int d0) throws InterruptedException {
        byte b = (byte) d0;
        CanbusMsgSender.sendMsg(new byte[]{22, -32, b, 1});
        Thread.sleep(100L);
        CanbusMsgSender.sendMsg(new byte[]{22, -32, b, 0});
    }
}
