package com.hzbhd.canbus.car._361;

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
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import java.util.LinkedHashSet;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UiMgr.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0002J\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\bH\u0002J\u0010\u0010\f\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\bH\u0002J\u0010\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\bH\u0002J\u0018\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0002J\u0018\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\bH\u0002J\u0012\u0010\u0012\u001a\u00020\u00062\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016¨\u0006\u0013"}, d2 = {"Lcom/hzbhd/canbus/car/_361/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "sendAFrame", "", "d0", "", "d1", "sendAirConditionData", "data0", "sendAnotherAirConditionData", "sendCarFrame", "i", "sendSFrame", "sendSettingsData", "data1", "updateUiByDifferentCar", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class UiMgr extends AbstractUiMgr {
    public UiMgr(final Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        Intrinsics.checkNotNullExpressionValue(amplifierPageUiSet, "getAmplifierPageUiSet(context)");
        final SettingPageUiSet settingUiSet = getSettingUiSet(context);
        Intrinsics.checkNotNullExpressionValue(settingUiSet, "getSettingUiSet(context)");
        AirPageUiSet airUiSet = getAirUiSet(context);
        Intrinsics.checkNotNullExpressionValue(airUiSet, "getAirUiSet(context)");
        amplifierPageUiSet.setOnAmplifierPositionListener(new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._361.UiMgr.1

            /* compiled from: UiMgr.kt */
            @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
            /* renamed from: com.hzbhd.canbus.car._361.UiMgr$1$WhenMappings */
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
                    UiMgr.this.sendSettingsData(1, com.hzbhd.canbus.car._350.MsgMgrKt.reverse$default(iRangeNumber, 0, 2, null));
                } else {
                    if (i != 2) {
                        return;
                    }
                    UiMgr.this.sendSettingsData(2, iRangeNumber);
                }
            }
        });
        amplifierPageUiSet.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._361.UiMgr.2

            /* compiled from: UiMgr.kt */
            @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
            /* renamed from: com.hzbhd.canbus.car._361.UiMgr$2$WhenMappings */
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
                    UiMgr.this.sendSettingsData(4, iRangeNumber);
                    return;
                }
                if (i == 2) {
                    UiMgr.this.sendSettingsData(5, iRangeNumber);
                } else if (i == 3) {
                    UiMgr.this.sendSettingsData(6, iRangeNumber);
                } else {
                    if (i != 4) {
                        return;
                    }
                    UiMgr.this.sendSettingsData(7, iRangeNumber2);
                }
            }
        });
        settingUiSet.setOnSettingItemSwitchListener(new OnSettingItemSwitchListener() { // from class: com.hzbhd.canbus.car._361.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener
            public final void onSwitch(int i, int i2, int i3) {
                UiMgr.m766_init_$lambda0(settingUiSet, this, i, i2, i3);
            }
        });
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._361.UiMgr$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public final void onClickItem(int i, int i2, int i3) {
                UiMgr.m767_init_$lambda1(settingUiSet, this, i, i2, i3);
            }
        });
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._361.UiMgr$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public final void onClickItem(int i, int i2, int i3) {
                UiMgr.m768_init_$lambda2(settingUiSet, this, i, i2, i3);
            }
        });
        settingUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._361.UiMgr$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
            public final void onConfirmClick(int i, int i2) {
                UiMgr.m769_init_$lambda3(settingUiSet, this, context, i, i2);
            }
        });
        FrontArea frontArea = airUiSet.getFrontArea();
        if (frontArea != null) {
            frontArea.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._361.UiMgr$$ExternalSyntheticLambda4
                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
                public final void onClickItem(int i) throws InterruptedException {
                    UiMgr.m770_init_$lambda4(this.f$0, i);
                }
            }, null, null, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._361.UiMgr$$ExternalSyntheticLambda5
                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
                public final void onClickItem(int i) throws InterruptedException {
                    UiMgr.m771_init_$lambda5(this.f$0, i);
                }
            }});
        }
        FrontArea frontArea2 = airUiSet.getFrontArea();
        if (frontArea2 != null) {
            frontArea2.setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._361.UiMgr.9
                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
                public void onClickLeft() throws InterruptedException {
                    UiMgr.this.sendAirConditionData(2);
                }

                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
                public void onClickRight() throws InterruptedException {
                    UiMgr.this.sendAirConditionData(1);
                }
            });
        }
        FrontArea frontArea3 = airUiSet.getFrontArea();
        if (frontArea3 != null) {
            frontArea3.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._361.UiMgr.10
                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
                public void onClickUp() throws InterruptedException {
                    UiMgr.this.sendAirConditionData(12);
                }

                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
                public void onClickDown() throws InterruptedException {
                    UiMgr.this.sendAirConditionData(13);
                }
            }, null, new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._361.UiMgr.11
                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
                public void onClickUp() throws InterruptedException {
                    UiMgr.this.sendAirConditionData(16);
                }

                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
                public void onClickDown() throws InterruptedException {
                    UiMgr.this.sendAirConditionData(17);
                }
            }});
        }
        FrontArea frontArea4 = airUiSet.getFrontArea();
        if (frontArea4 != null) {
            frontArea4.setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._361.UiMgr.12
                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
                public void onLeftSeatClick() throws InterruptedException {
                    UiMgr.this.sendAirConditionData(22);
                }

                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
                public void onRightSeatClick() throws InterruptedException {
                    UiMgr.this.sendAirConditionData(22);
                }
            });
        }
        FrontArea frontArea5 = airUiSet.getFrontArea();
        if (frontArea5 == null) {
            return;
        }
        frontArea5.setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._361.UiMgr.13
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() throws InterruptedException {
                UiMgr.this.sendAnotherAirConditionData(3);
            }
        }, new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._361.UiMgr.14
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() throws InterruptedException {
                UiMgr.this.sendAnotherAirConditionData(4);
            }
        }});
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        removeSettingRightItemByNameList(context, new String[]{"S361_d30t1", "S361_2_d4b0"});
        UiMgrKt.setRemove0(true);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        CollectionsKt.addAll(linkedHashSet, new Integer[]{2, 3});
        if (!linkedHashSet.contains(Integer.valueOf(getCurrentCarId()))) {
            removeSettingRightItemByNameList(context, new String[]{"S361_d1b7", "S361_d1b6", "S361_d1b5", "S361_d1b4", "S361_d2b6"});
            UiMgrKt.setRemove1(true);
        }
        if (getCurrentCarId() != 1) {
            removeSettingRightItemByNameList(context, new String[]{"S361_d0b7", "S361_d1b0t2", "S361_d0b0t1", "S361_d2b7", "S361_d2b5", "S361_1_d2b0", "S361_1_d2b1"});
            UiMgrKt.setRemove2(true);
        }
        if (getCurrentCarId() != 7) {
            removeSettingRightItemByNameList(context, new String[]{"S361_1_d1b0"});
            UiMgrKt.setRemove3(true);
        }
        if (getCurrentCarId() != 3) {
            removeSettingRightItemByNameList(context, new String[]{"S361_d4b0t3", "S361_d4b4t5"});
            UiMgrKt.setRemove4(true);
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
    /* renamed from: _init_$lambda-0, reason: not valid java name */
    public static final void m766_init_$lambda0(SettingPageUiSet settingPageUiSet, UiMgr this$0, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(settingPageUiSet, "$settingPageUiSet");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        if (titleSrn != null) {
            switch (titleSrn.hashCode()) {
                case -1517428852:
                    if (titleSrn.equals("S361_2_d2b0t3")) {
                        this$0.sendAFrame(3, i3 == 1 ? 8 : 1);
                        break;
                    }
                    break;
                case 331802606:
                    if (titleSrn.equals("S361_1_d2b0")) {
                        this$0.sendSFrame(37, i3);
                        break;
                    }
                    break;
                case 331802607:
                    if (titleSrn.equals("S361_1_d2b1")) {
                        this$0.sendSFrame(38, i3);
                        break;
                    }
                    break;
                case 360433679:
                    if (titleSrn.equals("S361_2_d4b0")) {
                        this$0.sendAFrame(9, i3);
                        break;
                    }
                    break;
                case 1168298533:
                    if (titleSrn.equals("S361_d0b7")) {
                        this$0.sendSFrame(4, i3);
                        break;
                    }
                    break;
                case 1168299491:
                    if (titleSrn.equals("S361_d1b4")) {
                        this$0.sendSFrame(3, i3);
                        break;
                    }
                    break;
                case 1168299492:
                    if (titleSrn.equals("S361_d1b5")) {
                        this$0.sendSFrame(2, i3);
                        break;
                    }
                    break;
                case 1168299493:
                    if (titleSrn.equals("S361_d1b6")) {
                        this$0.sendSFrame(1, i3);
                        break;
                    }
                    break;
                case 1168299494:
                    if (titleSrn.equals("S361_d1b7")) {
                        this$0.sendSFrame(0, i3);
                        break;
                    }
                    break;
                case 1168300451:
                    if (titleSrn.equals("S361_d2b3")) {
                        this$0.sendSFrame(17, i3);
                        break;
                    }
                    break;
                case 1168300452:
                    if (titleSrn.equals("S361_d2b4")) {
                        this$0.sendSFrame(16, i3);
                        break;
                    }
                    break;
                case 1168300454:
                    if (titleSrn.equals("S361_d2b6")) {
                        this$0.sendSFrame(14, i3);
                        break;
                    }
                    break;
                case 1168300455:
                    if (titleSrn.equals("S361_d2b7")) {
                        this$0.sendSFrame(13, i3);
                        break;
                    }
                    break;
                case 1168301415:
                    if (titleSrn.equals("S361_d3b6")) {
                        this$0.sendSFrame(19, i3);
                        break;
                    }
                    break;
                case 1168301416:
                    if (titleSrn.equals("S361_d3b7")) {
                        this$0.sendSFrame(18, i3);
                        break;
                    }
                    break;
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
    /* renamed from: _init_$lambda-1, reason: not valid java name */
    public static final void m767_init_$lambda1(SettingPageUiSet settingPageUiSet, UiMgr this$0, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(settingPageUiSet, "$settingPageUiSet");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        if (titleSrn != null) {
            switch (titleSrn.hashCode()) {
                case 331801645:
                    if (titleSrn.equals("S361_1_d1b0")) {
                        this$0.sendSFrame(12, i3);
                        break;
                    }
                    break;
                case 1168300453:
                    if (titleSrn.equals("S361_d2b5")) {
                        this$0.sendSFrame(15, i3);
                        break;
                    }
                    break;
                case 1748422875:
                    if (titleSrn.equals("S361_d0b0t1")) {
                        this$0.sendSFrame(7, i3);
                        break;
                    }
                    break;
                case 1748424799:
                    if (titleSrn.equals("S361_d0b2t3")) {
                        this$0.sendSFrame(12, i3);
                        break;
                    }
                    break;
                case 1752116961:
                    if (titleSrn.equals("S361_d4b0t3")) {
                        this$0.sendSFrame(21, i3 + 1);
                        break;
                    }
                    break;
                case 1752120807:
                    if (titleSrn.equals("S361_d4b4t5")) {
                        this$0.sendSFrame(22, i3 + 1);
                        break;
                    }
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-2, reason: not valid java name */
    public static final void m768_init_$lambda2(SettingPageUiSet settingPageUiSet, UiMgr this$0, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(settingPageUiSet, "$settingPageUiSet");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        if (Intrinsics.areEqual(titleSrn, "S361_d1b0t2")) {
            this$0.sendSFrame(5, i3);
        } else if (Intrinsics.areEqual(titleSrn, "S361_d0b6t4")) {
            this$0.sendSFrame(6, i3);
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
    /* renamed from: _init_$lambda-3, reason: not valid java name */
    public static final void m769_init_$lambda3(SettingPageUiSet settingPageUiSet, UiMgr this$0, Context context, int i, int i2) {
        Intrinsics.checkNotNullParameter(settingPageUiSet, "$settingPageUiSet");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(context, "$context");
        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        if (titleSrn != null) {
            int iHashCode = titleSrn.hashCode();
            switch (iHashCode) {
                case 1765565998:
                    if (titleSrn.equals("S361_car_10")) {
                        this$0.sendCarFrame(12);
                        break;
                    }
                    break;
                case 1765565999:
                    if (titleSrn.equals("S361_car_11")) {
                        this$0.sendCarFrame(16);
                        break;
                    }
                    break;
                case 1765566000:
                    if (titleSrn.equals("S361_car_12")) {
                        this$0.sendCarFrame(17);
                        break;
                    }
                    break;
                case 1765566001:
                    if (titleSrn.equals("S361_car_13")) {
                        this$0.sendCarFrame(18);
                        break;
                    }
                    break;
                case 1765566002:
                    if (titleSrn.equals("S361_car_14")) {
                        this$0.sendCarFrame(80);
                        break;
                    }
                    break;
                default:
                    switch (iHashCode) {
                        case 1858069057:
                            if (titleSrn.equals("S361_car_0")) {
                                this$0.sendCarFrame(0);
                                break;
                            }
                            break;
                        case 1858069058:
                            if (titleSrn.equals("S361_car_1")) {
                                this$0.sendCarFrame(1);
                                break;
                            }
                            break;
                        case 1858069059:
                            if (titleSrn.equals("S361_car_2")) {
                                this$0.sendCarFrame(2);
                                break;
                            }
                            break;
                        case 1858069060:
                            if (titleSrn.equals("S361_car_3")) {
                                this$0.sendCarFrame(3);
                                break;
                            }
                            break;
                        case 1858069061:
                            if (titleSrn.equals("S361_car_4")) {
                                this$0.sendCarFrame(5);
                                break;
                            }
                            break;
                        case 1858069062:
                            if (titleSrn.equals("S361_car_5")) {
                                this$0.sendCarFrame(6);
                                break;
                            }
                            break;
                        case 1858069063:
                            if (titleSrn.equals("S361_car_6")) {
                                this$0.sendCarFrame(8);
                                break;
                            }
                            break;
                        case 1858069064:
                            if (titleSrn.equals("S361_car_7")) {
                                this$0.sendCarFrame(9);
                                break;
                            }
                            break;
                        case 1858069065:
                            if (titleSrn.equals("S361_car_8")) {
                                this$0.sendCarFrame(10);
                                break;
                            }
                            break;
                        case 1858069066:
                            if (titleSrn.equals("S361_car_9")) {
                                this$0.sendCarFrame(11);
                                break;
                            }
                            break;
                    }
            }
        }
        this$0.removeSettingLeftItemByNameList(context, new String[]{"S361_car_title"});
        MsgMgrInterface canMsgMgr = MsgMgrFactory.getCanMsgMgr(context);
        Intrinsics.checkNotNull(canMsgMgr, "null cannot be cast to non-null type com.hzbhd.canbus.car._361.MsgMgr");
        ((MsgMgr) canMsgMgr).returnClick();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-4, reason: not valid java name */
    public static final void m770_init_$lambda4(UiMgr this$0, int i) throws InterruptedException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i == 0) {
            this$0.sendAirConditionData(4);
        } else if (i == 1) {
            this$0.sendAirConditionData(25);
        } else {
            if (i != 4) {
                return;
            }
            this$0.sendAirConditionData(19);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-5, reason: not valid java name */
    public static final void m771_init_$lambda5(UiMgr this$0, int i) throws InterruptedException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i == 1) {
            this$0.sendAirConditionData(23);
        } else {
            if (i != 2) {
                return;
            }
            this$0.sendAirConditionData(3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendAirConditionData(int data0) throws InterruptedException {
        byte b = (byte) data0;
        CanbusMsgSender.sendMsg(new byte[]{22, -117, b, 1});
        Thread.sleep(100L);
        CanbusMsgSender.sendMsg(new byte[]{22, -117, b, 0});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendAnotherAirConditionData(int data0) throws InterruptedException {
        byte b = (byte) data0;
        CanbusMsgSender.sendMsg(new byte[]{22, -116, b, 1});
        Thread.sleep(100L);
        CanbusMsgSender.sendMsg(new byte[]{22, -116, b, 0});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendSettingsData(int data0, int data1) {
        CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte) data0, (byte) data1});
    }

    private final void sendSFrame(int d0, int d1) {
        CanbusMsgSender.sendMsg(new byte[]{22, -125, (byte) d0, (byte) d1});
    }

    private final void sendAFrame(int d0, int d1) {
        CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte) d0, (byte) d1});
    }

    private final void sendCarFrame(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -115, (byte) i});
    }
}
