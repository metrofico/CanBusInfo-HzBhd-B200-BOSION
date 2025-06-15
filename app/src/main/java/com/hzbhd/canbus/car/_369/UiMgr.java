package com.hzbhd.canbus.car._369;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;




public final class UiMgr extends AbstractUiMgr {
    private final AirPageUiSet mAirPageUiSet;
    private final SettingPageUiSet mSettingPageUiSet;

    public UiMgr(Context context) {

        AirPageUiSet airUiSet = getAirUiSet(context);

        this.mAirPageUiSet = airUiSet;
        SettingPageUiSet settingUiSet = getSettingUiSet(context);

        this.mSettingPageUiSet = settingUiSet;
        final FrontArea frontArea = airUiSet.getFrontArea();
        frontArea.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._369.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m797lambda4$lambda0(frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._369.UiMgr$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m798lambda4$lambda1(frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._369.UiMgr$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m799lambda4$lambda2(frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._369.UiMgr$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m800lambda4$lambda3(frontArea, i);
            }
        }});
        frontArea.setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._369.UiMgr$1$5
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr.m803lambda4$send0x3BData(11, 2);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr.m803lambda4$send0x3BData(11, 1);
            }
        });
        frontArea.setTempSetViewOnUpDownClickListeners(new UiMgr$1$6[]{new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._369.UiMgr$1$6
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                UiMgr.m803lambda4$send0x3BData(12, 1);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                UiMgr.m803lambda4$send0x3BData(12, 2);
            }
        }, null, null});
        frontArea.setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._369.UiMgr$1$7
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                UiMgr.m803lambda4$send0x3BData(15, 255);
            }
        });
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._369.UiMgr$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public final void onClickItem(int i, int i2, int i3) {
                UiMgr.m804lambda7$lambda5(this.f$0, i, i2, i3);
            }
        });
        settingUiSet.setOnSettingItemSwitchListener(new OnSettingItemSwitchListener() { // from class: com.hzbhd.canbus.car._369.UiMgr$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener
            public final void onSwitch(int i, int i2, int i3) {
                UiMgr.m805lambda7$lambda6(this.f$0, i, i2, i3);
            }
        });
    }

    public final AirPageUiSet getMAirPageUiSet() {
        return this.mAirPageUiSet;
    }

    public final SettingPageUiSet getMSettingPageUiSet() {
        return this.mSettingPageUiSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-4$send0x3BData, reason: not valid java name */
    public static final void m803lambda4$send0x3BData(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, 59, (byte) i, (byte) i2});
    }

    /* renamed from: lambda-4$selectAirPageBtn$default, reason: not valid java name */
    static /* synthetic */ void m802lambda4$selectAirPageBtn$default(String str, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 255;
        }
        m801lambda4$selectAirPageBtn(str, i);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterative(DepthRegionTraversal.java:31)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visit(SwitchOverStringVisitor.java:60)
     */
    /* renamed from: lambda-4$selectAirPageBtn, reason: not valid java name */
    private static final void m801lambda4$selectAirPageBtn(String str, int i) {
        switch (str.hashCode()) {
            case -1470045433:
                if (str.equals("front_defog")) {
                    m803lambda4$send0x3BData(5, i);
                    break;
                }
                break;
            case -631663038:
                if (str.equals("rear_defog")) {
                    m803lambda4$send0x3BData(6, i);
                    break;
                }
                break;
            case 3106:
                if (str.equals("ac")) {
                    m803lambda4$send0x3BData(2, i);
                    break;
                }
                break;
            case 3005871:
                if (str.equals("auto")) {
                    m803lambda4$send0x3BData(4, i);
                    break;
                }
                break;
            case 106858757:
                if (str.equals("power")) {
                    m803lambda4$send0x3BData(16, i);
                    break;
                }
                break;
            case 756225563:
                if (str.equals("in_out_cycle")) {
                    m803lambda4$send0x3BData(7, i);
                    break;
                }
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-4$lambda-0, reason: not valid java name */
    public static final void m797lambda4$lambda0(FrontArea frontArea, int i) {
        String str = frontArea.getLineBtnAction()[0][i];

        m802lambda4$selectAirPageBtn$default(str, 0, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-4$lambda-1, reason: not valid java name */
    public static final void m798lambda4$lambda1(FrontArea frontArea, int i) {
        String str = frontArea.getLineBtnAction()[1][i];

        m802lambda4$selectAirPageBtn$default(str, 0, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-4$lambda-2, reason: not valid java name */
    public static final void m799lambda4$lambda2(FrontArea frontArea, int i) {
        String str = frontArea.getLineBtnAction()[2][i];

        m802lambda4$selectAirPageBtn$default(str, 0, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-4$lambda-3, reason: not valid java name */
    public static final void m800lambda4$lambda3(FrontArea frontArea, int i) {
        String str = frontArea.getLineBtnAction()[3][i];

        m802lambda4$selectAirPageBtn$default(str, 0, 2, null);
    }

    /* renamed from: lambda-7$sendItemData, reason: not valid java name */
    private static final void m807lambda7$sendItemData(int i, int i2, int i3) {
        CanbusMsgSender.sendMsg(new byte[]{22, (byte) i, (byte) i2, (byte) i3});
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /* renamed from: lambda-7$select, reason: not valid java name */
    private static final void m806lambda7$select(UiMgr uiMgr, int i, int i2, int i3) {
        String titleSrn = uiMgr.mSettingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        if (titleSrn != null) {
            int iHashCode = titleSrn.hashCode();
            if (iHashCode == 893177037) {
                if (titleSrn.equals("S369_RemoteControl_1")) {
                    m807lambda7$sendItemData(107, 8, i3);
                }
                return;
            }
            switch (iHashCode) {
                case -1395302420:
                    if (titleSrn.equals("S369_Light_1")) {
                        m807lambda7$sendItemData(108, 11, i3);
                        break;
                    }
                    break;
                case -1395302419:
                    if (titleSrn.equals("S369_Light_2")) {
                        m807lambda7$sendItemData(108, 12, i3);
                        break;
                    }
                    break;
                case -1395302418:
                    if (titleSrn.equals("S369_Light_3")) {
                        m807lambda7$sendItemData(108, 3, i3);
                        break;
                    }
                    break;
                case -1395302417:
                    if (titleSrn.equals("S369_Light_4")) {
                        m807lambda7$sendItemData(108, 4, i3);
                        break;
                    }
                    break;
                case -1395302416:
                    if (titleSrn.equals("S369_Light_5")) {
                        m807lambda7$sendItemData(108, 5, i3);
                        break;
                    }
                    break;
                case -1395302415:
                    if (titleSrn.equals("S369_Light_6")) {
                        m807lambda7$sendItemData(108, 6, i3);
                        break;
                    }
                    break;
                case -1395302414:
                    if (titleSrn.equals("S369_Light_7")) {
                        m807lambda7$sendItemData(108, 7, i3);
                        break;
                    }
                    break;
                case -1395302413:
                    if (titleSrn.equals("S369_Light_8")) {
                        m807lambda7$sendItemData(108, 8, i3);
                        break;
                    }
                    break;
                default:
                    switch (iHashCode) {
                        case 337002528:
                            if (titleSrn.equals("S369_Air_1")) {
                                m807lambda7$sendItemData(58, 1, i3);
                                break;
                            }
                            break;
                        case 337002529:
                            if (titleSrn.equals("S369_Air_2")) {
                                m807lambda7$sendItemData(58, 4, i3);
                                break;
                            }
                            break;
                        case 337002530:
                            if (titleSrn.equals("S369_Air_3")) {
                                m807lambda7$sendItemData(58, 16, i3);
                                break;
                            }
                            break;
                        default:
                            switch (iHashCode) {
                                case 1921938073:
                                    if (titleSrn.equals("S369_LightTime_1")) {
                                        m807lambda7$sendItemData(108, 9, i3);
                                        break;
                                    }
                                    break;
                                case 1921938074:
                                    if (titleSrn.equals("S369_LightTime_2")) {
                                        m807lambda7$sendItemData(108, 10, i3);
                                        break;
                                    }
                                    break;
                                default:
                                    switch (iHashCode) {
                                        case 1948502716:
                                            if (titleSrn.equals("S369_Door_1")) {
                                                m807lambda7$sendItemData(106, 6, i3);
                                                break;
                                            }
                                            break;
                                        case 1948502717:
                                            if (titleSrn.equals("S369_Door_2")) {
                                                m807lambda7$sendItemData(106, 3, i3);
                                                break;
                                            }
                                            break;
                                        case 1948502718:
                                            if (titleSrn.equals("S369_Door_3")) {
                                                m807lambda7$sendItemData(106, 7, i3);
                                                break;
                                            }
                                            break;
                                    }
                            }
                    }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-7$lambda-5, reason: not valid java name */
    public static final void m804lambda7$lambda5(UiMgr this$0, int i, int i2, int i3) {

        m806lambda7$select(this$0, i, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-7$lambda-6, reason: not valid java name */
    public static final void m805lambda7$lambda6(UiMgr this$0, int i, int i2, int i3) {

        m806lambda7$select(this$0, i, i2, i3);
    }
}
