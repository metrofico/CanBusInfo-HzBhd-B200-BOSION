package com.hzbhd.canbus.car._338;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;




public final class UiMgr extends AbstractUiMgr {
    private final AirPageUiSet airPageUiSet;
    private OnAirBtnClickListener onAirBtnClickListenerFrontLeft;
    private OnAirBtnClickListener onAirBtnClickListenerFrontRight;
    private OnAirBtnClickListener onAirBtnClickListenerFrontTop;
    private OnAirSeatClickListener onAirSeatClickListener;
    private OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerFrontLeft;
    private OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener;
    private OnPanoramicItemClickListener onPanoramicItemClickListener;
    private OnSettingItemSelectListener onSettingItemSelectListener;
    private final ParkPageUiSet parkPageUiSet;
    private final SettingPageUiSet settingPageUiSet;

    public UiMgr(Context context) {
        AirPageUiSet airUiSet = getAirUiSet(context);

        this.airPageUiSet = airUiSet;
        ParkPageUiSet parkPageUiSet = getParkPageUiSet(context);

        this.parkPageUiSet = parkPageUiSet;
        SettingPageUiSet settingUiSet = getSettingUiSet(context);

        this.settingPageUiSet = settingUiSet;
        this.onAirTemperatureUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._338.UiMgr$onAirTemperatureUpDownClickListenerFrontLeft$1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                this.this$0.sendAirData(13);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                this.this$0.sendAirData(14);
            }
        };
        this.onAirBtnClickListenerFrontTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._338.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m712onAirBtnClickListenerFrontTop$lambda0(this.f$0, i);
            }
        };
        this.onAirBtnClickListenerFrontLeft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._338.UiMgr$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m710onAirBtnClickListenerFrontLeft$lambda1(this.f$0, i);
            }
        };
        this.onAirBtnClickListenerFrontRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._338.UiMgr$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m711onAirBtnClickListenerFrontRight$lambda2(this.f$0, i);
            }
        };
        this.onAirSeatClickListener = new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._338.UiMgr$onAirSeatClickListener$1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                this.this$0.sendAirData(17);
            }
        };
        this.onAirWindSpeedUpDownClickListener = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._338.UiMgr$onAirWindSpeedUpDownClickListener$1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                this.this$0.sendAirData(12);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                this.this$0.sendAirData(11);
            }
        };
        this.onPanoramicItemClickListener = new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._338.UiMgr$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public final void onClickItem(int i) {
                UiMgr.m713onPanoramicItemClickListener$lambda3(this.f$0, i);
            }
        };
        this.onSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._338.UiMgr$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public final void onClickItem(int i, int i2, int i3) {
                UiMgr.m714onSettingItemSelectListener$lambda4(this.f$0, i, i2, i3);
            }
        };
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.onAirBtnClickListenerFrontTop, this.onAirBtnClickListenerFrontLeft, this.onAirBtnClickListenerFrontRight, null});
        airUiSet.getFrontArea().setOnAirSeatClickListener(this.onAirSeatClickListener);
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.onAirWindSpeedUpDownClickListener);
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.onAirTemperatureUpDownClickListenerFrontLeft, null, null});
        parkPageUiSet.setOnPanoramicItemClickListener(this.onPanoramicItemClickListener);
        settingUiSet.setOnSettingItemSelectListener(this.onSettingItemSelectListener);
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {

        if (getCurrentCarId() != 2) {
            this.parkPageUiSet.setShowPanoramic(false);
        }
    }

    public final OnAirBtnClickListener getOnAirBtnClickListenerFrontTop() {
        return this.onAirBtnClickListenerFrontTop;
    }

    public final void setOnAirBtnClickListenerFrontTop(OnAirBtnClickListener onAirBtnClickListener) {

        this.onAirBtnClickListenerFrontTop = onAirBtnClickListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onAirBtnClickListenerFrontTop$lambda-0, reason: not valid java name */
    public static final void m712onAirBtnClickListenerFrontTop$lambda0(UiMgr this$0, int i) {

        if (i == 0) {
            this$0.sendAirData(21);
        } else if (i == 1) {
            this$0.sendAirData(1);
        } else {
            if (i != 2) {
                return;
            }
            this$0.sendAirData(2);
        }
    }

    public final OnAirBtnClickListener getOnAirBtnClickListenerFrontLeft() {
        return this.onAirBtnClickListenerFrontLeft;
    }

    public final void setOnAirBtnClickListenerFrontLeft(OnAirBtnClickListener onAirBtnClickListener) {

        this.onAirBtnClickListenerFrontLeft = onAirBtnClickListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onAirBtnClickListenerFrontLeft$lambda-1, reason: not valid java name */
    public static final void m710onAirBtnClickListenerFrontLeft$lambda1(UiMgr this$0, int i) {

        if (i == 0) {
            this$0.sendAirData(6);
        }
    }

    public final OnAirBtnClickListener getOnAirBtnClickListenerFrontRight() {
        return this.onAirBtnClickListenerFrontRight;
    }

    public final void setOnAirBtnClickListenerFrontRight(OnAirBtnClickListener onAirBtnClickListener) {

        this.onAirBtnClickListenerFrontRight = onAirBtnClickListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onAirBtnClickListenerFrontRight$lambda-2, reason: not valid java name */
    public static final void m711onAirBtnClickListenerFrontRight$lambda2(UiMgr this$0, int i) {

        if (i == 0) {
            this$0.sendAirData(23);
        }
    }

    public final OnAirSeatClickListener getOnAirSeatClickListener() {
        return this.onAirSeatClickListener;
    }

    public final void setOnAirSeatClickListener(OnAirSeatClickListener onAirSeatClickListener) {

        this.onAirSeatClickListener = onAirSeatClickListener;
    }

    public final OnAirWindSpeedUpDownClickListener getOnAirWindSpeedUpDownClickListener() {
        return this.onAirWindSpeedUpDownClickListener;
    }

    public final void setOnAirWindSpeedUpDownClickListener(OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener) {

        this.onAirWindSpeedUpDownClickListener = onAirWindSpeedUpDownClickListener;
    }

    public final OnPanoramicItemClickListener getOnPanoramicItemClickListener() {
        return this.onPanoramicItemClickListener;
    }

    public final void setOnPanoramicItemClickListener(OnPanoramicItemClickListener onPanoramicItemClickListener) {

        this.onPanoramicItemClickListener = onPanoramicItemClickListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onPanoramicItemClickListener$lambda-3, reason: not valid java name */
    public static final void m713onPanoramicItemClickListener$lambda3(UiMgr this$0, int i) {

        if (i == 0) {
            this$0.sendPanoramaInfo(1);
            return;
        }
        if (i == 1) {
            this$0.sendPanoramaInfo(4);
        } else if (i == 2) {
            this$0.sendPanoramaInfo(3);
        } else {
            if (i != 3) {
                return;
            }
            this$0.sendPanoramaInfo(2);
        }
    }

    public final OnSettingItemSelectListener getOnSettingItemSelectListener() {
        return this.onSettingItemSelectListener;
    }

    public final void setOnSettingItemSelectListener(OnSettingItemSelectListener onSettingItemSelectListener) {

        this.onSettingItemSelectListener = onSettingItemSelectListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onSettingItemSelectListener$lambda-4, reason: not valid java name */
    public static final void m714onSettingItemSelectListener$lambda4(UiMgr this$0, int i, int i2, int i3) {

        if (i == 0 && i2 == 0) {
            this$0.sendSettingInfo(1, i3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendAirData(int data0) {
        CanbusMsgSender.sendMsg(new byte[]{22, -88, (byte) data0, 1});
    }

    private final void sendPanoramaInfo(int data0) {
        CanbusMsgSender.sendMsg(new byte[]{22, -89, (byte) data0});
    }

    private final void sendSettingInfo(int data0, int data1) {
        CanbusMsgSender.sendMsg(new byte[]{22, -125, (byte) data0, (byte) data1});
    }
}
