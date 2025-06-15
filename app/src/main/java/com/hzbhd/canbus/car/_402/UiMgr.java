package com.hzbhd.canbus.car._402;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;




public final class UiMgr extends AbstractUiMgr {
    /* JADX WARN: Type inference failed for: r0v2, types: [T, int[]] */
    public UiMgr(Context context) {

        final FrontArea frontArea = getAirUiSet(context).getFrontArea();
        frontArea.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._402.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m813lambda4$lambda0(frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._402.UiMgr$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m814lambda4$lambda1(frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._402.UiMgr$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m815lambda4$lambda2(frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._402.UiMgr$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m816lambda4$lambda3(frontArea, i);
            }
        }});
        frontArea.setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._402.UiMgr$1$5
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr.m818lambda4$send(12);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr.m818lambda4$send(11);
            }
        });
        frontArea.setTempSetViewOnUpDownClickListeners(new UiMgr$1$6[]{new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._402.UiMgr$1$6
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                UiMgr.m818lambda4$send(13);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                UiMgr.m818lambda4$send(14);
            }
        }, null, null});
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new int[]{26, 27, 28, 29};
        final Ref.IntRef intRef = new Ref.IntRef();
        frontArea.setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._402.UiMgr$1$7
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                int[] iArr = objectRef.element;
                int i = intRef.element;
                intRef.element = i + 1;
                UiMgr.m818lambda4$send(iArr[i % 4]);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
                int[] iArr = objectRef.element;
                int i = intRef.element;
                intRef.element = i + 1;
                UiMgr.m818lambda4$send(iArr[i % 4]);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-4$send, reason: not valid java name */
    public static final void m818lambda4$send(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, 61, (byte) i, 1});
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* renamed from: lambda-4$selectAirPageBtn, reason: not valid java name */
    private static final void m817lambda4$selectAirPageBtn(String str) {
        switch (str.hashCode()) {
            case -1470045433:
                if (str.equals("front_defog")) {
                    m818lambda4$send(5);
                    break;
                }
                break;
            case -631663038:
                if (str.equals("rear_defog")) {
                    m818lambda4$send(6);
                    break;
                }
                break;
            case 3106:
                if (str.equals("ac")) {
                    m818lambda4$send(2);
                    break;
                }
                break;
            case 106858757:
                if (str.equals("power")) {
                    m818lambda4$send(1);
                    break;
                }
                break;
            case 756225563:
                if (str.equals("in_out_cycle")) {
                    m818lambda4$send(7);
                    break;
                }
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-4$lambda-0, reason: not valid java name */
    public static final void m813lambda4$lambda0(FrontArea frontArea, int i) {
        String str = frontArea.getLineBtnAction()[0][i];

        m817lambda4$selectAirPageBtn(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-4$lambda-1, reason: not valid java name */
    public static final void m814lambda4$lambda1(FrontArea frontArea, int i) {
        String str = frontArea.getLineBtnAction()[1][i];

        m817lambda4$selectAirPageBtn(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-4$lambda-2, reason: not valid java name */
    public static final void m815lambda4$lambda2(FrontArea frontArea, int i) {
        String str = frontArea.getLineBtnAction()[2][i];

        m817lambda4$selectAirPageBtn(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-4$lambda-3, reason: not valid java name */
    public static final void m816lambda4$lambda3(FrontArea frontArea, int i) {
        String str = frontArea.getLineBtnAction()[3][i];

        m817lambda4$selectAirPageBtn(str);
    }
}
