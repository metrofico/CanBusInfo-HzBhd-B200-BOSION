package com.hzbhd.canbus.car._365;

import android.content.Context;
import android.view.MotionEvent;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.PanelKeyPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;




public final class UiMgr extends AbstractUiMgr {
    private final AirPageUiSet mAirPageUiSet;

    public UiMgr(final Context context) {

        AirPageUiSet airUiSet = getAirUiSet(context);

        this.mAirPageUiSet = airUiSet;
        final FrontArea frontArea = airUiSet.getFrontArea();
        frontArea.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._365.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m787lambda4$lambda0(this.f$0, frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._365.UiMgr$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m788lambda4$lambda1(this.f$0, frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._365.UiMgr$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m789lambda4$lambda2(this.f$0, frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._365.UiMgr$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m790lambda4$lambda3(this.f$0, frontArea, i);
            }
        }});
        frontArea.setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._365.UiMgr$1$5
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                this.this$0.sendAirConditionerCmd(12);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                this.this$0.sendAirConditionerCmd(11);
            }
        });
        frontArea.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._365.UiMgr$1$6
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                this.this$0.sendAirConditionerCmd(13);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                this.this$0.sendAirConditionerCmd(14);
            }
        }, null, new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._365.UiMgr$1$7
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                this.this$0.sendAirConditionerCmd(15);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                this.this$0.sendAirConditionerCmd(16);
            }
        }});
        frontArea.setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._365.UiMgr$1$8
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                this.this$0.sendAirConditionerCmd(19);
            }
        }, new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._365.UiMgr$1$9
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                this.this$0.sendAirConditionerCmd(20);
            }
        }, new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._365.UiMgr$1$10
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                this.this$0.sendAirConditionerCmd(23);
            }
        }, new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._365.UiMgr$1$11
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                this.this$0.sendAirConditionerCmd(24);
            }
        }});
        frontArea.setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._365.UiMgr$1$12
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                this.this$0.sendAirConditionerCmd(17);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
                this.this$0.sendAirConditionerCmd(18);
            }
        });
        final PanelKeyPageUiSet panelKeyPageUiSet = getPanelKeyPageUiSet(context);
        panelKeyPageUiSet.setOnPanelKeyPositionListener(new OnPanelKeyPositionListener() { // from class: com.hzbhd.canbus.car._365.UiMgr$2$1
            /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
            @Override // com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener
            public void click(int position) {
                List<String> list = panelKeyPageUiSet.getList();
                String str = list != null ? list.get(position) : null;
                if (str != null) {
                    int iHashCode = str.hashCode();
                    switch (iHashCode) {
                        case -2101038641:
                            if (str.equals("K365_x2A_10")) {
                                UiMgr.m791lambda5$sendCommand(14);
                                break;
                            }
                            break;
                        case -2101038640:
                            if (str.equals("K365_x2A_11")) {
                                UiMgr.m791lambda5$sendCommand(17);
                                break;
                            }
                            break;
                        case -2101038639:
                            if (str.equals("K365_x2A_12")) {
                                UiMgr.m791lambda5$sendCommand(25);
                                break;
                            }
                            break;
                        case -2101038638:
                            if (str.equals("K365_x2A_13")) {
                                UiMgr.m791lambda5$sendCommand(22);
                                break;
                            }
                            break;
                        case -2101038637:
                            if (str.equals("K365_x2A_14")) {
                                UiMgr.m791lambda5$sendCommand(36);
                                break;
                            }
                            break;
                        case -2101038636:
                            if (str.equals("K365_x2A_15")) {
                                UiMgr.m791lambda5$sendCommand(43);
                                break;
                            }
                            break;
                        case -2101038635:
                            if (str.equals("K365_x2A_16")) {
                                UiMgr.m791lambda5$sendCommand(47);
                                break;
                            }
                            break;
                        case -2101038634:
                            if (str.equals("K365_x2A_17")) {
                                UiMgr.m791lambda5$sendCommand(50);
                                break;
                            }
                            break;
                        case -2101038633:
                            if (str.equals("K365_x2A_18")) {
                                UiMgr.m791lambda5$sendCommand(64);
                                break;
                            }
                            break;
                        case -2101038632:
                            if (str.equals("K365_x2A_19")) {
                                UiMgr.m791lambda5$sendCommand(65);
                                break;
                            }
                            break;
                        default:
                            switch (iHashCode) {
                                case -2101038610:
                                    if (str.equals("K365_x2A_20")) {
                                        UiMgr.m791lambda5$sendCommand(80);
                                        break;
                                    }
                                    break;
                                case -2101038609:
                                    if (str.equals("K365_x2A_21")) {
                                        UiMgr.m791lambda5$sendCommand(81);
                                        break;
                                    }
                                    break;
                                case -2101038608:
                                    if (str.equals("K365_x2A_22")) {
                                        UiMgr.m791lambda5$sendCommand(82);
                                        break;
                                    }
                                    break;
                                case -2101038607:
                                    if (str.equals("K365_x2A_23")) {
                                        UiMgr.m791lambda5$sendCommand(83);
                                        break;
                                    }
                                    break;
                                case -2101038606:
                                    if (str.equals("K365_x2A_24")) {
                                        UiMgr.m791lambda5$sendCommand(84);
                                        break;
                                    }
                                    break;
                                default:
                                    switch (iHashCode) {
                                        case -1591796095:
                                            if (str.equals("K365_x2A_1")) {
                                                UiMgr.m791lambda5$sendCommand(1);
                                                break;
                                            }
                                            break;
                                        case -1591796094:
                                            if (str.equals("K365_x2A_2")) {
                                                UiMgr.m791lambda5$sendCommand(2);
                                                break;
                                            }
                                            break;
                                        case -1591796093:
                                            if (str.equals("K365_x2A_3")) {
                                                UiMgr.m791lambda5$sendCommand(3);
                                                break;
                                            }
                                            break;
                                        case -1591796092:
                                            if (str.equals("K365_x2A_4")) {
                                                UiMgr.m791lambda5$sendCommand(5);
                                                break;
                                            }
                                            break;
                                        case -1591796091:
                                            if (str.equals("K365_x2A_5")) {
                                                UiMgr.m791lambda5$sendCommand(6);
                                                break;
                                            }
                                            break;
                                        case -1591796090:
                                            if (str.equals("K365_x2A_6")) {
                                                UiMgr.m791lambda5$sendCommand(10);
                                                break;
                                            }
                                            break;
                                        case -1591796089:
                                            if (str.equals("K365_x2A_7")) {
                                                UiMgr.m791lambda5$sendCommand(11);
                                                break;
                                            }
                                            break;
                                        case -1591796088:
                                            if (str.equals("K365_x2A_8")) {
                                                UiMgr.m791lambda5$sendCommand(12);
                                                break;
                                            }
                                            break;
                                        case -1591796087:
                                            if (str.equals("K365_x2A_9")) {
                                                UiMgr.m791lambda5$sendCommand(13);
                                                break;
                                            }
                                            break;
                                    }
                            }
                    }
                }
            }
        });
        getParkPageUiSet(context).setOnPanoramicItemTouchListener(new OnPanoramicItemTouchListener() { // from class: com.hzbhd.canbus.car._365.UiMgr$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener
            public final void onTouchItem(MotionEvent motionEvent) {
                UiMgr.m785_init_$lambda6(context, motionEvent);
            }
        });
    }

    public final AirPageUiSet getMAirPageUiSet() {
        return this.mAirPageUiSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-4$lambda-0, reason: not valid java name */
    public static final void m787lambda4$lambda0(UiMgr this$0, FrontArea frontArea, int i) {

        String str = frontArea.getLineBtnAction()[0][i];

        this$0.selectAirConditionerBtn(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-4$lambda-1, reason: not valid java name */
    public static final void m788lambda4$lambda1(UiMgr this$0, FrontArea frontArea, int i) {

        String str = frontArea.getLineBtnAction()[1][i];

        this$0.selectAirConditionerBtn(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-4$lambda-2, reason: not valid java name */
    public static final void m789lambda4$lambda2(UiMgr this$0, FrontArea frontArea, int i) {

        String str = frontArea.getLineBtnAction()[2][i];

        this$0.selectAirConditionerBtn(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-4$lambda-3, reason: not valid java name */
    public static final void m790lambda4$lambda3(UiMgr this$0, FrontArea frontArea, int i) {

        String str = frontArea.getLineBtnAction()[3][i];

        this$0.selectAirConditionerBtn(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-5$sendCommand, reason: not valid java name */
    public static final void m791lambda5$sendCommand(int i) {
        byte b = (byte) i;
        CanbusMsgSender.sendMsg(new byte[]{22, 42, b, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, 42, b, 0});
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-6, reason: not valid java name */
    public static final void m785_init_$lambda6(Context context, MotionEvent motionEvent) {

        int i = context.getResources().getDisplayMetrics().widthPixels;
        int i2 = context.getResources().getDisplayMetrics().heightPixels;
        int x = (int) (motionEvent.getX() * (1000.0f / i));
        int y = (int) (motionEvent.getY() * (1024.0f / i2));
        if (motionEvent.getAction() == 0) {
            m792lambda6$send(x, y);
        }
    }

    /* renamed from: lambda-6$send, reason: not valid java name */
    private static final void m792lambda6$send(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, 44, 1, (byte) DataHandleUtils.getMsb(i), (byte) DataHandleUtils.getLsb(i), (byte) DataHandleUtils.getMsb(i2), (byte) DataHandleUtils.getLsb(i2), 0});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendAirConditionerCmd(int d0) {
        byte b = (byte) d0;
        CanbusMsgSender.sendMsg(new byte[]{22, 61, b, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, 61, b, 0});
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
    private final void selectAirConditionerBtn(String btn) {
        switch (btn.hashCode()) {
            case -1470045433:
                if (btn.equals("front_defog")) {
                    sendAirConditionerCmd(5);
                    break;
                }
                break;
            case -631663038:
                if (btn.equals("rear_defog")) {
                    sendAirConditionerCmd(6);
                    break;
                }
                break;
            case 3106:
                if (btn.equals("ac")) {
                    sendAirConditionerCmd(2);
                    break;
                }
                break;
            case 3005871:
                if (btn.equals("auto")) {
                    sendAirConditionerCmd(4);
                    break;
                }
                break;
            case 3545755:
                if (btn.equals("sync")) {
                    sendAirConditionerCmd(3);
                    break;
                }
                break;
            case 106858757:
                if (btn.equals("power")) {
                    sendAirConditionerCmd(1);
                    break;
                }
                break;
            case 756225563:
                if (btn.equals("in_out_cycle")) {
                    sendAirConditionerCmd(7);
                    break;
                }
                break;
        }
    }
}
