package com.hzbhd.canbus.car._350;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UiMgr.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018J\u000e\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018J\u0018\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018H\u0002J\u0018\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018H\u0002J\u0012\u0010\u001d\u001a\u00020\u00162\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u001e"}, d2 = {"Lcom/hzbhd/canbus/car/_350/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "airPageUiSet", "Lcom/hzbhd/canbus/adapter/bean/AirPageUiSet;", "getAirPageUiSet", "()Lcom/hzbhd/canbus/adapter/bean/AirPageUiSet;", "amplifierPageUiSet", "Lcom/hzbhd/canbus/ui_set/AmplifierPageUiSet;", "getAmplifierPageUiSet", "()Lcom/hzbhd/canbus/ui_set/AmplifierPageUiSet;", "parkPageUiSet", "Lcom/hzbhd/canbus/ui_set/ParkPageUiSet;", "getParkPageUiSet", "()Lcom/hzbhd/canbus/ui_set/ParkPageUiSet;", "settingPageUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "getSettingPageUiSet", "()Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "sendAFrame", "", "d0", "", "d1", "sendAirConditionerFrame", "sendPFrame", "sendSFrame", "updateUiByDifferentCar", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class UiMgr extends AbstractUiMgr {
    private final AirPageUiSet airPageUiSet;
    private final AmplifierPageUiSet amplifierPageUiSet;
    private final ParkPageUiSet parkPageUiSet;
    private final SettingPageUiSet settingPageUiSet;

    public UiMgr(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        Intrinsics.checkNotNullExpressionValue(settingUiSet, "getSettingUiSet(context)");
        this.settingPageUiSet = settingUiSet;
        AirPageUiSet airUiSet = getAirUiSet(context);
        Intrinsics.checkNotNullExpressionValue(airUiSet, "getAirUiSet(context)");
        this.airPageUiSet = airUiSet;
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        Intrinsics.checkNotNullExpressionValue(amplifierPageUiSet, "getAmplifierPageUiSet(context)");
        this.amplifierPageUiSet = amplifierPageUiSet;
        ParkPageUiSet parkPageUiSet = getParkPageUiSet(context);
        Intrinsics.checkNotNullExpressionValue(parkPageUiSet, "getParkPageUiSet(context)");
        this.parkPageUiSet = parkPageUiSet;
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
        settingUiSet.setOnSettingItemSwitchListener(new OnSettingItemSwitchListener() { // from class: com.hzbhd.canbus.car._350.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener
            public final void onSwitch(int i, int i2, int i3) {
                UiMgr.m754_init_$lambda0(this.f$0, i, i2, i3);
            }
        });
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._350.UiMgr$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public final void onClickItem(int i, int i2, int i3) {
                UiMgr.m755_init_$lambda1(this.f$0, i, i2, i3);
            }
        });
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._350.UiMgr$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public final void onClickItem(int i, int i2, int i3) {
                UiMgr.m756_init_$lambda2(this.f$0, i, i2, i3);
            }
        });
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._350.UiMgr$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m757_init_$lambda3(this.f$0, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._350.UiMgr$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m758_init_$lambda4(this.f$0, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._350.UiMgr$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m759_init_$lambda5(this.f$0, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._350.UiMgr$$ExternalSyntheticLambda6
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m760_init_$lambda6(this.f$0, i);
            }
        }});
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._350.UiMgr.8
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                UiMgr.this.sendAirConditionerFrame(3);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                UiMgr.this.sendAirConditionerFrame(2);
            }
        }, null, new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._350.UiMgr.9
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                UiMgr.this.sendAirConditionerFrame(5);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                UiMgr.this.sendAirConditionerFrame(4);
            }
        }});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._350.UiMgr.10
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr.this.sendAirConditionerFrame(9);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr.this.sendAirConditionerFrame(10);
            }
        });
        airUiSet.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._350.UiMgr.11
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                UiMgr.this.sendAirConditionerFrame(36);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
                UiMgr.this.sendAirConditionerFrame(37);
            }
        });
        airUiSet.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._350.UiMgr$$ExternalSyntheticLambda7
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m761_init_$lambda7(this.f$0, i);
            }
        }, null, null, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._350.UiMgr$$ExternalSyntheticLambda8
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m762_init_$lambda8(this.f$0, i);
            }
        }});
        airUiSet.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._350.UiMgr.14
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                UiMgr.this.sendAirConditionerFrame(39);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                UiMgr.this.sendAirConditionerFrame(38);
            }
        }, null, new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._350.UiMgr.15
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                UiMgr.this.sendAirConditionerFrame(47);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                UiMgr.this.sendAirConditionerFrame(46);
            }
        }});
        airUiSet.getRearArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._350.UiMgr.16
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr.this.sendAirConditionerFrame(40);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr.this.sendAirConditionerFrame(41);
            }
        });
        airUiSet.getRearArea().setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._350.UiMgr.17
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                UiMgr.this.sendAirConditionerFrame(43);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
                UiMgr.this.sendAirConditionerFrame(43);
            }
        });
        amplifierPageUiSet.setOnAmplifierPositionListener(new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._350.UiMgr.18

            /* compiled from: UiMgr.kt */
            @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
            /* renamed from: com.hzbhd.canbus.car._350.UiMgr$18$WhenMappings */
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
                    UiMgr.this.sendAFrame(1, MsgMgrKt.reverse$default(iRangeNumber, 0, 2, null));
                } else {
                    if (i != 2) {
                        return;
                    }
                    UiMgr.this.sendAFrame(2, iRangeNumber);
                }
            }
        });
        amplifierPageUiSet.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._350.UiMgr.19

            /* compiled from: UiMgr.kt */
            @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
            /* renamed from: com.hzbhd.canbus.car._350.UiMgr$19$WhenMappings */
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
                    UiMgr.this.sendAFrame(4, iRangeNumber);
                    return;
                }
                if (i == 2) {
                    UiMgr.this.sendAFrame(5, iRangeNumber);
                } else if (i == 3) {
                    UiMgr.this.sendAFrame(6, iRangeNumber);
                } else {
                    if (i != 4) {
                        return;
                    }
                    UiMgr.this.sendAFrame(7, iRangeNumber2);
                }
            }
        });
        final int i = context.getResources().getDisplayMetrics().widthPixels;
        final int i2 = context.getResources().getDisplayMetrics().heightPixels;
        parkPageUiSet.setOnPanoramicItemTouchListener(new OnPanoramicItemTouchListener() { // from class: com.hzbhd.canbus.car._350.UiMgr$$ExternalSyntheticLambda9
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener
            public final void onTouchItem(MotionEvent motionEvent) {
                UiMgr.m763_init_$lambda9(i, i2, this, motionEvent);
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

    public final ParkPageUiSet getParkPageUiSet() {
        return this.parkPageUiSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /* renamed from: _init_$lambda-0, reason: not valid java name */
    public static final void m754_init_$lambda0(UiMgr this$0, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String titleSrn = this$0.settingPageUiSet.getList().get(i).getTitleSrn();
        String titleSrn2 = this$0.settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        if (titleSrn != null) {
            switch (titleSrn.hashCode()) {
                case 1940889716:
                    if (titleSrn.equals("_350_s_0") && Intrinsics.areEqual(titleSrn2, "_350_s_0_0")) {
                        this$0.sendSFrame(4, i3);
                        break;
                    }
                    break;
                case 1940889717:
                    if (titleSrn.equals("_350_s_1") && titleSrn2 != null) {
                        switch (titleSrn2.hashCode()) {
                            case 1179214566:
                                if (titleSrn2.equals("_350_s_1_0")) {
                                    this$0.sendSFrame(0, i3);
                                    break;
                                }
                                break;
                            case 1179214567:
                                if (titleSrn2.equals("_350_s_1_1")) {
                                    this$0.sendSFrame(1, i3);
                                    break;
                                }
                                break;
                            case 1179214568:
                                if (titleSrn2.equals("_350_s_1_2")) {
                                    this$0.sendSFrame(2, i3);
                                    break;
                                }
                                break;
                            case 1179214569:
                                if (titleSrn2.equals("_350_s_1_3")) {
                                    this$0.sendSFrame(3, i3);
                                    break;
                                }
                                break;
                        }
                    }
                    break;
                case 1940889718:
                    if (titleSrn.equals("_350_s_2") && titleSrn2 != null) {
                        switch (titleSrn2.hashCode()) {
                            case 1179215527:
                                if (titleSrn2.equals("_350_s_2_0")) {
                                    this$0.sendSFrame(13, i3);
                                    break;
                                }
                                break;
                            case 1179215528:
                                if (titleSrn2.equals("_350_s_2_1")) {
                                    this$0.sendSFrame(14, i3);
                                    break;
                                }
                                break;
                            case 1179215530:
                                if (titleSrn2.equals("_350_s_2_3")) {
                                    this$0.sendSFrame(16, i3);
                                    break;
                                }
                                break;
                            case 1179215531:
                                if (titleSrn2.equals("_350_s_2_4")) {
                                    this$0.sendSFrame(17, i3);
                                    break;
                                }
                                break;
                        }
                    }
                    break;
                case 1940889719:
                    if (titleSrn.equals("_350_s_3")) {
                        if (!Intrinsics.areEqual(titleSrn2, "_350_s_3_0")) {
                            if (Intrinsics.areEqual(titleSrn2, "_350_s_3_1")) {
                                this$0.sendSFrame(19, i3);
                                break;
                            }
                        } else {
                            this$0.sendSFrame(18, i3);
                            break;
                        }
                    }
                    break;
                case 1940889721:
                    if (titleSrn.equals("_350_s_5") && Intrinsics.areEqual(titleSrn2, "_350_s_5_0")) {
                        this$0.sendSFrame(22, i3);
                        break;
                    }
                    break;
                case 1940889722:
                    if (titleSrn.equals("_350_s_6")) {
                        if (!Intrinsics.areEqual(titleSrn2, "_350_s_6_0")) {
                            if (Intrinsics.areEqual(titleSrn2, "_350_s_6_1")) {
                                this$0.sendAFrame(9, i3);
                                break;
                            }
                        } else {
                            this$0.sendAFrame(3, i3 == 1 ? 8 : 1);
                            break;
                        }
                    }
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* renamed from: _init_$lambda-1, reason: not valid java name */
    public static final void m755_init_$lambda1(UiMgr this$0, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String titleSrn = this$0.settingPageUiSet.getList().get(i).getTitleSrn();
        String titleSrn2 = this$0.settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        if (titleSrn != null) {
            switch (titleSrn.hashCode()) {
                case 1940889716:
                    if (titleSrn.equals("_350_s_0")) {
                        if (!Intrinsics.areEqual(titleSrn2, "_350_s_0_2")) {
                            if (Intrinsics.areEqual(titleSrn2, "_350_s_0_3")) {
                                this$0.sendSFrame(7, i3);
                                break;
                            }
                        } else {
                            this$0.sendSFrame(12, i3);
                            break;
                        }
                    }
                    break;
                case 1940889718:
                    if (titleSrn.equals("_350_s_2")) {
                        if (!Intrinsics.areEqual(titleSrn2, "_350_s_2_2")) {
                            if (Intrinsics.areEqual(titleSrn2, "_350_s_2_5")) {
                                this$0.sendSFrame(35, i3 + 1);
                                break;
                            }
                        } else {
                            this$0.sendSFrame(15, i3);
                            break;
                        }
                    }
                    break;
                case 1940889719:
                    if (titleSrn.equals("_350_s_3") && Intrinsics.areEqual(titleSrn2, "_350_s_3_2")) {
                        this$0.sendSFrame(34, i3);
                        break;
                    }
                    break;
                case 1940889720:
                    if (titleSrn.equals("_350_s_4") && Intrinsics.areEqual(titleSrn2, "_350_s_4_2")) {
                        this$0.sendSFrame(37, i3);
                        break;
                    }
                    break;
                case 1940889721:
                    if (titleSrn.equals("_350_s_5") && Intrinsics.areEqual(titleSrn2, "_350_s_5_1")) {
                        this$0.sendSFrame(23, i3);
                        break;
                    }
                    break;
                case 1940889723:
                    if (titleSrn.equals("_350_s_7") && Intrinsics.areEqual(titleSrn2, "_350_s_7_0")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 96, (byte) i3});
                        break;
                    }
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* renamed from: _init_$lambda-2, reason: not valid java name */
    public static final void m756_init_$lambda2(UiMgr this$0, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String titleSrn = this$0.settingPageUiSet.getList().get(i).getTitleSrn();
        String titleSrn2 = this$0.settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        if (titleSrn != null) {
            switch (titleSrn.hashCode()) {
                case 1940889716:
                    if (titleSrn.equals("_350_s_0") && Intrinsics.areEqual(titleSrn2, "_350_s_0_1")) {
                        this$0.sendSFrame(6, i3);
                        break;
                    }
                    break;
                case 1940889717:
                    if (titleSrn.equals("_350_s_1") && Intrinsics.areEqual(titleSrn2, "_350_s_1_4")) {
                        this$0.sendSFrame(5, i3);
                        break;
                    }
                    break;
                case 1940889720:
                    if (titleSrn.equals("_350_s_4") && Intrinsics.areEqual(titleSrn2, "_350_s_4_1")) {
                        this$0.sendSFrame(38, i3);
                        break;
                    }
                    break;
                case 1940889721:
                    if (titleSrn.equals("_350_s_5") && Intrinsics.areEqual(titleSrn2, "_350_s_5_3")) {
                        this$0.sendSFrame(21, i3);
                        break;
                    }
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-3, reason: not valid java name */
    public static final void m757_init_$lambda3(UiMgr this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i == 0) {
            this$0.sendAirConditionerFrame(25);
            return;
        }
        if (i == 1) {
            this$0.sendAirConditionerFrame(28);
        } else {
            if (i != 2) {
                return;
            }
            if (GeneralAirData.power) {
                this$0.sendAirConditionerFrame(1);
            } else {
                this$0.sendAirConditionerFrame(10);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-4, reason: not valid java name */
    public static final void m758_init_$lambda4(UiMgr this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i == 0) {
            this$0.sendAirConditionerFrame(26);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-5, reason: not valid java name */
    public static final void m759_init_$lambda5(UiMgr this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i == 0) {
            this$0.sendAirConditionerFrame(32);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-6, reason: not valid java name */
    public static final void m760_init_$lambda6(UiMgr this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i == 0) {
            this$0.sendAirConditionerFrame(23);
            return;
        }
        if (i == 1) {
            this$0.sendAirConditionerFrame(21);
        } else if (i == 2) {
            this$0.sendAirConditionerFrame(16);
        } else {
            if (i != 4) {
                return;
            }
            this$0.sendAirConditionerFrame(27);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-7, reason: not valid java name */
    public static final void m761_init_$lambda7(UiMgr this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i == 0) {
            this$0.sendAirConditionerFrame(42);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-8, reason: not valid java name */
    public static final void m762_init_$lambda8(UiMgr this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i == 0) {
            this$0.sendAirConditionerFrame(45);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-9, reason: not valid java name */
    public static final void m763_init_$lambda9(int i, int i2, UiMgr this$0, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int x = (int) (motionEvent.getX() * (255.0f / i));
        int y = (int) (motionEvent.getY() * (255.0f / i2));
        if (motionEvent.getAction() == 0) {
            this$0.sendPFrame(x, y);
        } else if (motionEvent.getAction() == 1) {
            this$0.sendPFrame(0, 0);
        }
        Log.i("lyn", " x:" + x + ", y:" + y + " \n width:" + i + ", high:" + i2);
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        removeMainPrjBtnByName(context, MainAction.ORIGINAL_CAR_DEVICE);
        removeSettingRightItemByNameList(context, new String[]{"_350_s_3_3", "_350_s_4_0", "_350_s_5_2"});
        removeSettingLeftItemByNameList(context, new String[]{"_350_s_7"});
    }

    private final void sendSFrame(int d0, int d1) {
        CanbusMsgSender.sendMsg(new byte[]{22, -125, (byte) d0, (byte) d1});
    }

    public final void sendAFrame(int d0, int d1) {
        CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte) d0, (byte) d1});
    }

    private final void sendPFrame(int d0, int d1) {
        CanbusMsgSender.sendMsg(new byte[]{22, -122, (byte) d0, (byte) d1});
        Log.i("lyn", d0 + "  " + d1);
    }

    public final void sendAirConditionerFrame(int d0) {
        byte b = (byte) d0;
        CanbusMsgSender.sendMsg(new byte[]{22, -32, b, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, -32, b, 0});
    }
}
