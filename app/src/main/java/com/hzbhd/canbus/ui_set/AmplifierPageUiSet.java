package com.hzbhd.canbus.ui_set;

import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierCreateAndDestroyListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierResetPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AmplifierPageUiSet.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\"\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0011\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0017\"\u0004\b\u001b\u0010\u0019R\u001a\u0010\u001c\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0017\"\u0004\b\u001d\u0010\u0019R\u001a\u0010\u001e\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0017\"\u0004\b\u001f\u0010\u0019R\u001a\u0010 \u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0017\"\u0004\b!\u0010\u0019R\u001a\u0010\"\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0017\"\u0004\b#\u0010\u0019R\u001a\u0010$\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0017\"\u0004\b%\u0010\u0019R\u001a\u0010&\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0017\"\u0004\b'\u0010\u0019R\u001a\u0010(\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0017\"\u0004\b)\u0010\u0019R\u001a\u0010*\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u0017\"\u0004\b+\u0010\u0019R\u001a\u0010,\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u0017\"\u0004\b-\u0010\u0019R\u001a\u0010.\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u0017\"\u0004\b/\u0010\u0019R\u001a\u00100\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\u0017\"\u0004\b1\u0010\u0019R\u001a\u00102\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u0017\"\u0004\b3\u0010\u0019R\u001a\u00104\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u0017\"\u0004\b5\u0010\u0019R\u001a\u00106\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010\u0017\"\u0004\b7\u0010\u0019R$\u00108\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u000109X\u0086\u000e¢\u0006\u0010\n\u0002\u0010>\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\u001c\u0010?\u001a\u0004\u0018\u00010@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010B\"\u0004\bC\u0010DR\u001c\u0010E\u001a\u0004\u0018\u00010FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JR\u001c\u0010K\u001a\u0004\u0018\u00010LX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010N\"\u0004\bO\u0010PR\u001c\u0010Q\u001a\u0004\u0018\u00010RX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bS\u0010T\"\u0004\bU\u0010VR\u001c\u0010W\u001a\u0004\u0018\u00010XX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bY\u0010Z\"\u0004\b[\u0010\\R\u001a\u0010]\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b^\u0010\u0006\"\u0004\b_\u0010\bR\u001a\u0010`\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\ba\u0010\u0006\"\u0004\bb\u0010\bR\u001a\u0010c\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bd\u0010\u0006\"\u0004\be\u0010\bR\u001a\u0010f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bg\u0010\u0006\"\u0004\bh\u0010\b¨\u0006i"}, d2 = {"Lcom/hzbhd/canbus/ui_set/AmplifierPageUiSet;", "", "()V", "balanceRange", "", "getBalanceRange", "()I", "setBalanceRange", "(I)V", "bandRange", "getBandRange", "setBandRange", "custom2Title", "", "getCustom2Title", "()Ljava/lang/String;", "setCustom2Title", "(Ljava/lang/String;)V", "customTitle", "getCustomTitle", "setCustomTitle", "isCanBalanceControl", "", "()Z", "setCanBalanceControl", "(Z)V", "isCanRateControl", "setCanRateControl", "isCanSeekBarMinPlus", "setCanSeekBarMinPlus", "isCustom2Enabled", "setCustom2Enabled", "isCustomEnabled", "setCustomEnabled", "isHaveBandMiddle", "setHaveBandMiddle", "isHaveCustom", "setHaveCustom", "isHaveCustom2", "setHaveCustom2", "isHaveMegaBass", "setHaveMegaBass", "isHaveRateControl", "setHaveRateControl", "isHaveVolumeControl", "setHaveVolumeControl", "isHighEnabled", "setHighEnabled", "isLowEnabled", "setLowEnabled", "isMiddleEnabled", "setMiddleEnabled", "isRlControl", "setRlControl", "isVolumeEnabled", "setVolumeEnabled", "lineBtnAction", "", "getLineBtnAction", "()[Ljava/lang/String;", "setLineBtnAction", "([Ljava/lang/String;)V", "[Ljava/lang/String;", "onAirBtnClickListeners", "Lcom/hzbhd/canbus/adapter/interfaces/OnAirBtnClickListener;", "getOnAirBtnClickListeners", "()Lcom/hzbhd/canbus/adapter/interfaces/OnAirBtnClickListener;", "setOnAirBtnClickListeners", "(Lcom/hzbhd/canbus/adapter/interfaces/OnAirBtnClickListener;)V", "onAmplifierCreateAndDestroyListener", "Lcom/hzbhd/canbus/interfaces/OnAmplifierCreateAndDestroyListener;", "getOnAmplifierCreateAndDestroyListener", "()Lcom/hzbhd/canbus/interfaces/OnAmplifierCreateAndDestroyListener;", "setOnAmplifierCreateAndDestroyListener", "(Lcom/hzbhd/canbus/interfaces/OnAmplifierCreateAndDestroyListener;)V", "onAmplifierPositionListener", "Lcom/hzbhd/canbus/interfaces/OnAmplifierPositionListener;", "getOnAmplifierPositionListener", "()Lcom/hzbhd/canbus/interfaces/OnAmplifierPositionListener;", "setOnAmplifierPositionListener", "(Lcom/hzbhd/canbus/interfaces/OnAmplifierPositionListener;)V", "onAmplifierResetPositionListener", "Lcom/hzbhd/canbus/interfaces/OnAmplifierResetPositionListener;", "getOnAmplifierResetPositionListener", "()Lcom/hzbhd/canbus/interfaces/OnAmplifierResetPositionListener;", "setOnAmplifierResetPositionListener", "(Lcom/hzbhd/canbus/interfaces/OnAmplifierResetPositionListener;)V", "onAmplifierSeekBarListener", "Lcom/hzbhd/canbus/interfaces/OnAmplifierSeekBarListener;", "getOnAmplifierSeekBarListener", "()Lcom/hzbhd/canbus/interfaces/OnAmplifierSeekBarListener;", "setOnAmplifierSeekBarListener", "(Lcom/hzbhd/canbus/interfaces/OnAmplifierSeekBarListener;)V", "seekBarMax", "getSeekBarMax", "setSeekBarMax", "seekBarMaxCustom2", "getSeekBarMaxCustom2", "setSeekBarMaxCustom2", "seekBarVolumeMax", "getSeekBarVolumeMax", "setSeekBarVolumeMax", "volumeRange", "getVolumeRange", "setVolumeRange", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class AmplifierPageUiSet {
    private boolean isHaveCustom;
    private boolean isHaveCustom2;
    private boolean isHaveMegaBass;
    private boolean isHaveVolumeControl;
    private String[] lineBtnAction;
    private OnAirBtnClickListener onAirBtnClickListeners;
    private OnAmplifierCreateAndDestroyListener onAmplifierCreateAndDestroyListener;
    private OnAmplifierPositionListener onAmplifierPositionListener;
    private OnAmplifierResetPositionListener onAmplifierResetPositionListener;
    private OnAmplifierSeekBarListener onAmplifierSeekBarListener;
    private int volumeRange;
    private int seekBarMax = 10;
    private int bandRange = 14;
    private int balanceRange = 14;
    private int seekBarVolumeMax = 10;
    private boolean isHaveRateControl = true;
    private boolean isCanRateControl = true;
    private boolean isCanBalanceControl = true;
    private boolean isCanSeekBarMinPlus = true;
    private boolean isRlControl = true;
    private boolean isHaveBandMiddle = true;
    private boolean isVolumeEnabled = true;
    private boolean isHighEnabled = true;
    private boolean isMiddleEnabled = true;
    private boolean isLowEnabled = true;
    private boolean isCustomEnabled = true;
    private boolean isCustom2Enabled = true;
    private String customTitle = "";
    private String custom2Title = "";
    private int seekBarMaxCustom2 = 1;

    public final int getSeekBarMax() {
        return this.seekBarMax;
    }

    public final void setSeekBarMax(int i) {
        this.seekBarMax = i;
    }

    public final int getBandRange() {
        return this.bandRange;
    }

    public final void setBandRange(int i) {
        this.bandRange = i;
    }

    public final int getBalanceRange() {
        return this.balanceRange;
    }

    public final void setBalanceRange(int i) {
        this.balanceRange = i;
    }

    public final int getSeekBarVolumeMax() {
        return this.seekBarVolumeMax;
    }

    public final void setSeekBarVolumeMax(int i) {
        this.seekBarVolumeMax = i;
    }

    public final int getVolumeRange() {
        return this.volumeRange;
    }

    public final void setVolumeRange(int i) {
        this.volumeRange = i;
    }

    /* renamed from: isHaveVolumeControl, reason: from getter */
    public final boolean getIsHaveVolumeControl() {
        return this.isHaveVolumeControl;
    }

    public final void setHaveVolumeControl(boolean z) {
        this.isHaveVolumeControl = z;
    }

    /* renamed from: isHaveRateControl, reason: from getter */
    public final boolean getIsHaveRateControl() {
        return this.isHaveRateControl;
    }

    public final void setHaveRateControl(boolean z) {
        this.isHaveRateControl = z;
    }

    /* renamed from: isCanRateControl, reason: from getter */
    public final boolean getIsCanRateControl() {
        return this.isCanRateControl;
    }

    public final void setCanRateControl(boolean z) {
        this.isCanRateControl = z;
    }

    /* renamed from: isCanBalanceControl, reason: from getter */
    public final boolean getIsCanBalanceControl() {
        return this.isCanBalanceControl;
    }

    public final void setCanBalanceControl(boolean z) {
        this.isCanBalanceControl = z;
    }

    /* renamed from: isCanSeekBarMinPlus, reason: from getter */
    public final boolean getIsCanSeekBarMinPlus() {
        return this.isCanSeekBarMinPlus;
    }

    public final void setCanSeekBarMinPlus(boolean z) {
        this.isCanSeekBarMinPlus = z;
    }

    /* renamed from: isRlControl, reason: from getter */
    public final boolean getIsRlControl() {
        return this.isRlControl;
    }

    public final void setRlControl(boolean z) {
        this.isRlControl = z;
    }

    public final String[] getLineBtnAction() {
        return this.lineBtnAction;
    }

    public final void setLineBtnAction(String[] strArr) {
        this.lineBtnAction = strArr;
    }

    public final OnAirBtnClickListener getOnAirBtnClickListeners() {
        return this.onAirBtnClickListeners;
    }

    public final void setOnAirBtnClickListeners(OnAirBtnClickListener onAirBtnClickListener) {
        this.onAirBtnClickListeners = onAirBtnClickListener;
    }

    public final OnAmplifierSeekBarListener getOnAmplifierSeekBarListener() {
        return this.onAmplifierSeekBarListener;
    }

    public final void setOnAmplifierSeekBarListener(OnAmplifierSeekBarListener onAmplifierSeekBarListener) {
        this.onAmplifierSeekBarListener = onAmplifierSeekBarListener;
    }

    public final OnAmplifierPositionListener getOnAmplifierPositionListener() {
        return this.onAmplifierPositionListener;
    }

    public final void setOnAmplifierPositionListener(OnAmplifierPositionListener onAmplifierPositionListener) {
        this.onAmplifierPositionListener = onAmplifierPositionListener;
    }

    public final OnAmplifierResetPositionListener getOnAmplifierResetPositionListener() {
        return this.onAmplifierResetPositionListener;
    }

    public final void setOnAmplifierResetPositionListener(OnAmplifierResetPositionListener onAmplifierResetPositionListener) {
        this.onAmplifierResetPositionListener = onAmplifierResetPositionListener;
    }

    public final OnAmplifierCreateAndDestroyListener getOnAmplifierCreateAndDestroyListener() {
        return this.onAmplifierCreateAndDestroyListener;
    }

    public final void setOnAmplifierCreateAndDestroyListener(OnAmplifierCreateAndDestroyListener onAmplifierCreateAndDestroyListener) {
        this.onAmplifierCreateAndDestroyListener = onAmplifierCreateAndDestroyListener;
    }

    /* renamed from: isHaveBandMiddle, reason: from getter */
    public final boolean getIsHaveBandMiddle() {
        return this.isHaveBandMiddle;
    }

    public final void setHaveBandMiddle(boolean z) {
        this.isHaveBandMiddle = z;
    }

    /* renamed from: isHaveMegaBass, reason: from getter */
    public final boolean getIsHaveMegaBass() {
        return this.isHaveMegaBass;
    }

    public final void setHaveMegaBass(boolean z) {
        this.isHaveMegaBass = z;
    }

    /* renamed from: isHaveCustom, reason: from getter */
    public final boolean getIsHaveCustom() {
        return this.isHaveCustom;
    }

    public final void setHaveCustom(boolean z) {
        this.isHaveCustom = z;
    }

    /* renamed from: isHaveCustom2, reason: from getter */
    public final boolean getIsHaveCustom2() {
        return this.isHaveCustom2;
    }

    public final void setHaveCustom2(boolean z) {
        this.isHaveCustom2 = z;
    }

    /* renamed from: isVolumeEnabled, reason: from getter */
    public final boolean getIsVolumeEnabled() {
        return this.isVolumeEnabled;
    }

    public final void setVolumeEnabled(boolean z) {
        this.isVolumeEnabled = z;
    }

    /* renamed from: isHighEnabled, reason: from getter */
    public final boolean getIsHighEnabled() {
        return this.isHighEnabled;
    }

    public final void setHighEnabled(boolean z) {
        this.isHighEnabled = z;
    }

    /* renamed from: isMiddleEnabled, reason: from getter */
    public final boolean getIsMiddleEnabled() {
        return this.isMiddleEnabled;
    }

    public final void setMiddleEnabled(boolean z) {
        this.isMiddleEnabled = z;
    }

    /* renamed from: isLowEnabled, reason: from getter */
    public final boolean getIsLowEnabled() {
        return this.isLowEnabled;
    }

    public final void setLowEnabled(boolean z) {
        this.isLowEnabled = z;
    }

    /* renamed from: isCustomEnabled, reason: from getter */
    public final boolean getIsCustomEnabled() {
        return this.isCustomEnabled;
    }

    public final void setCustomEnabled(boolean z) {
        this.isCustomEnabled = z;
    }

    /* renamed from: isCustom2Enabled, reason: from getter */
    public final boolean getIsCustom2Enabled() {
        return this.isCustom2Enabled;
    }

    public final void setCustom2Enabled(boolean z) {
        this.isCustom2Enabled = z;
    }

    public final String getCustomTitle() {
        return this.customTitle;
    }

    public final void setCustomTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.customTitle = str;
    }

    public final String getCustom2Title() {
        return this.custom2Title;
    }

    public final void setCustom2Title(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.custom2Title = str;
    }

    public final int getSeekBarMaxCustom2() {
        return this.seekBarMaxCustom2;
    }

    public final void setSeekBarMaxCustom2(int i) {
        this.seekBarMaxCustom2 = i;
    }
}
