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

/* compiled from: UiMgr.kt */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0010\u00101\u001a\u0002022\u0006\u00103\u001a\u000204H\u0002J\u0010\u00105\u001a\u0002022\u0006\u00103\u001a\u000204H\u0002J\u0018\u00106\u001a\u0002022\u0006\u00103\u001a\u0002042\u0006\u00107\u001a\u000204H\u0002J\u0010\u00108\u001a\u0002022\u0006\u0010\u0002\u001a\u00020\u0003H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\n\"\u0004\b\u000f\u0010\fR\u001a\u0010\u0010\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\n\"\u0004\b\u0012\u0010\fR\u001a\u0010\u0013\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001b\u001a\u00020\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\u00020\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001a\u0010'\u001a\u00020(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u000e\u0010-\u001a\u00020.X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u000200X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Lcom/hzbhd/canbus/car/_338/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "airPageUiSet", "Lcom/hzbhd/canbus/adapter/bean/AirPageUiSet;", "onAirBtnClickListenerFrontLeft", "Lcom/hzbhd/canbus/adapter/interfaces/OnAirBtnClickListener;", "getOnAirBtnClickListenerFrontLeft", "()Lcom/hzbhd/canbus/adapter/interfaces/OnAirBtnClickListener;", "setOnAirBtnClickListenerFrontLeft", "(Lcom/hzbhd/canbus/adapter/interfaces/OnAirBtnClickListener;)V", "onAirBtnClickListenerFrontRight", "getOnAirBtnClickListenerFrontRight", "setOnAirBtnClickListenerFrontRight", "onAirBtnClickListenerFrontTop", "getOnAirBtnClickListenerFrontTop", "setOnAirBtnClickListenerFrontTop", "onAirSeatClickListener", "Lcom/hzbhd/canbus/adapter/interfaces/OnAirSeatClickListener;", "getOnAirSeatClickListener", "()Lcom/hzbhd/canbus/adapter/interfaces/OnAirSeatClickListener;", "setOnAirSeatClickListener", "(Lcom/hzbhd/canbus/adapter/interfaces/OnAirSeatClickListener;)V", "onAirTemperatureUpDownClickListenerFrontLeft", "Lcom/hzbhd/canbus/adapter/interfaces/OnAirTemperatureUpDownClickListener;", "onAirWindSpeedUpDownClickListener", "Lcom/hzbhd/canbus/adapter/interfaces/OnAirWindSpeedUpDownClickListener;", "getOnAirWindSpeedUpDownClickListener", "()Lcom/hzbhd/canbus/adapter/interfaces/OnAirWindSpeedUpDownClickListener;", "setOnAirWindSpeedUpDownClickListener", "(Lcom/hzbhd/canbus/adapter/interfaces/OnAirWindSpeedUpDownClickListener;)V", "onPanoramicItemClickListener", "Lcom/hzbhd/canbus/interfaces/OnPanoramicItemClickListener;", "getOnPanoramicItemClickListener", "()Lcom/hzbhd/canbus/interfaces/OnPanoramicItemClickListener;", "setOnPanoramicItemClickListener", "(Lcom/hzbhd/canbus/interfaces/OnPanoramicItemClickListener;)V", "onSettingItemSelectListener", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSelectListener;", "getOnSettingItemSelectListener", "()Lcom/hzbhd/canbus/interfaces/OnSettingItemSelectListener;", "setOnSettingItemSelectListener", "(Lcom/hzbhd/canbus/interfaces/OnSettingItemSelectListener;)V", "parkPageUiSet", "Lcom/hzbhd/canbus/ui_set/ParkPageUiSet;", "settingPageUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "sendAirData", "", "data0", "", "sendPanoramaInfo", "sendSettingInfo", "data1", "updateUiByDifferentCar", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
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
        Intrinsics.checkNotNullExpressionValue(airUiSet, "getAirUiSet(context)");
        this.airPageUiSet = airUiSet;
        ParkPageUiSet parkPageUiSet = getParkPageUiSet(context);
        Intrinsics.checkNotNullExpressionValue(parkPageUiSet, "getParkPageUiSet(context)");
        this.parkPageUiSet = parkPageUiSet;
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        Intrinsics.checkNotNullExpressionValue(settingUiSet, "getSettingUiSet(context)");
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
        Intrinsics.checkNotNullParameter(context, "context");
        if (getCurrentCarId() != 2) {
            this.parkPageUiSet.setShowPanoramic(false);
        }
    }

    public final OnAirBtnClickListener getOnAirBtnClickListenerFrontTop() {
        return this.onAirBtnClickListenerFrontTop;
    }

    public final void setOnAirBtnClickListenerFrontTop(OnAirBtnClickListener onAirBtnClickListener) {
        Intrinsics.checkNotNullParameter(onAirBtnClickListener, "<set-?>");
        this.onAirBtnClickListenerFrontTop = onAirBtnClickListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onAirBtnClickListenerFrontTop$lambda-0, reason: not valid java name */
    public static final void m712onAirBtnClickListenerFrontTop$lambda0(UiMgr this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
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
        Intrinsics.checkNotNullParameter(onAirBtnClickListener, "<set-?>");
        this.onAirBtnClickListenerFrontLeft = onAirBtnClickListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onAirBtnClickListenerFrontLeft$lambda-1, reason: not valid java name */
    public static final void m710onAirBtnClickListenerFrontLeft$lambda1(UiMgr this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i == 0) {
            this$0.sendAirData(6);
        }
    }

    public final OnAirBtnClickListener getOnAirBtnClickListenerFrontRight() {
        return this.onAirBtnClickListenerFrontRight;
    }

    public final void setOnAirBtnClickListenerFrontRight(OnAirBtnClickListener onAirBtnClickListener) {
        Intrinsics.checkNotNullParameter(onAirBtnClickListener, "<set-?>");
        this.onAirBtnClickListenerFrontRight = onAirBtnClickListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onAirBtnClickListenerFrontRight$lambda-2, reason: not valid java name */
    public static final void m711onAirBtnClickListenerFrontRight$lambda2(UiMgr this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i == 0) {
            this$0.sendAirData(23);
        }
    }

    public final OnAirSeatClickListener getOnAirSeatClickListener() {
        return this.onAirSeatClickListener;
    }

    public final void setOnAirSeatClickListener(OnAirSeatClickListener onAirSeatClickListener) {
        Intrinsics.checkNotNullParameter(onAirSeatClickListener, "<set-?>");
        this.onAirSeatClickListener = onAirSeatClickListener;
    }

    public final OnAirWindSpeedUpDownClickListener getOnAirWindSpeedUpDownClickListener() {
        return this.onAirWindSpeedUpDownClickListener;
    }

    public final void setOnAirWindSpeedUpDownClickListener(OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener) {
        Intrinsics.checkNotNullParameter(onAirWindSpeedUpDownClickListener, "<set-?>");
        this.onAirWindSpeedUpDownClickListener = onAirWindSpeedUpDownClickListener;
    }

    public final OnPanoramicItemClickListener getOnPanoramicItemClickListener() {
        return this.onPanoramicItemClickListener;
    }

    public final void setOnPanoramicItemClickListener(OnPanoramicItemClickListener onPanoramicItemClickListener) {
        Intrinsics.checkNotNullParameter(onPanoramicItemClickListener, "<set-?>");
        this.onPanoramicItemClickListener = onPanoramicItemClickListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onPanoramicItemClickListener$lambda-3, reason: not valid java name */
    public static final void m713onPanoramicItemClickListener$lambda3(UiMgr this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
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
        Intrinsics.checkNotNullParameter(onSettingItemSelectListener, "<set-?>");
        this.onSettingItemSelectListener = onSettingItemSelectListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onSettingItemSelectListener$lambda-4, reason: not valid java name */
    public static final void m714onSettingItemSelectListener$lambda4(UiMgr this$0, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
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
