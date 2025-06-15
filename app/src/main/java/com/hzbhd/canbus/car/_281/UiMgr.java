package com.hzbhd.canbus.car._281;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AirBtnAction;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;




public final class UiMgr extends AbstractUiMgr {
    private final AirPageUiSet mAirPageUiSet;

    public UiMgr(Context context) {

        AirPageUiSet airUiSet = getAirUiSet(context);

        this.mAirPageUiSet = airUiSet;
        final FrontArea frontArea = airUiSet.getFrontArea();
        frontArea.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._281.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m347lambda4$lambda0(this.f$0, frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._281.UiMgr$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m348lambda4$lambda1(this.f$0, frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._281.UiMgr$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m349lambda4$lambda2(this.f$0, frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._281.UiMgr$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m350lambda4$lambda3(this.f$0, frontArea, i);
            }
        }});
        frontArea.setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._281.UiMgr$1$5
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                this.this$0.sendACmd(14);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                this.this$0.sendACmd(13);
            }
        });
        frontArea.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._281.UiMgr$1$6
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                this.this$0.sendACmd(1);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                this.this$0.sendACmd(2);
            }
        }, null, new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._281.UiMgr$1$7
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                this.this$0.sendACmd(3);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                this.this$0.sendACmd(4);
            }
        }});
        frontArea.setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._281.UiMgr$1$8
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                this.this$0.sendACmd(11);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
                this.this$0.sendACmd(11);
            }
        });
    }

    public final AirPageUiSet getMAirPageUiSet() {
        return this.mAirPageUiSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-4$lambda-0, reason: not valid java name */
    public static final void m347lambda4$lambda0(UiMgr this$0, FrontArea frontArea, int i) {

        String str = frontArea.getLineBtnAction()[0][i];

        this$0.selectABtn(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-4$lambda-1, reason: not valid java name */
    public static final void m348lambda4$lambda1(UiMgr this$0, FrontArea frontArea, int i) {

        String str = frontArea.getLineBtnAction()[1][i];

        this$0.selectABtn(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-4$lambda-2, reason: not valid java name */
    public static final void m349lambda4$lambda2(UiMgr this$0, FrontArea frontArea, int i) {

        String str = frontArea.getLineBtnAction()[2][i];

        this$0.selectABtn(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-4$lambda-3, reason: not valid java name */
    public static final void m350lambda4$lambda3(UiMgr this$0, FrontArea frontArea, int i) {

        String str = frontArea.getLineBtnAction()[3][i];

        this$0.selectABtn(str);
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
    private final void selectABtn(String btn) {
        switch (btn.hashCode()) {
            case -1761278488:
                if (btn.equals(AirBtnAction.POLLRN_REMOVAL)) {
                    sendACmd(16);
                    return;
                }
                break;
            case -1470045433:
                if (btn.equals("front_defog")) {
                    sendACmd(18);
                    return;
                }
                break;
            case -1274277292:
                if (btn.equals(AirBtnAction.CLEAN_AIR)) {
                    sendACmd(10);
                    return;
                }
                break;
            case -246396018:
                if (btn.equals(AirBtnAction.MAX_FRONT)) {
                    sendACmd(8);
                    return;
                }
                break;
            case 3106:
                if (btn.equals("ac")) {
                    sendACmd(17);
                    return;
                }
                break;
            case 3005871:
                if (btn.equals("auto")) {
                    sendACmd(6);
                    return;
                }
                break;
            case 3094652:
                if (btn.equals("dual")) {
                    sendACmd(12);
                    return;
                }
                break;
            case 3496356:
                if (btn.equals(AirBtnAction.REAR)) {
                    sendACmd(9);
                    return;
                }
                break;
            case 106858757:
                if (btn.equals("power")) {
                    sendACmd(5);
                    return;
                }
                break;
            case 109854462:
                if (btn.equals(AirBtnAction.SWING)) {
                    sendACmd(15);
                    return;
                }
                break;
            case 349917670:
                if (btn.equals(AirBtnAction.CYCLE_IN_OUT_CLOSE)) {
                    sendACmd(7);
                    return;
                }
                break;
        }
        sendACmd(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendACmd(int d0) {
        CanbusMsgSender.sendMsg(new byte[]{22, -31, (byte) d0, 1});
    }
}
