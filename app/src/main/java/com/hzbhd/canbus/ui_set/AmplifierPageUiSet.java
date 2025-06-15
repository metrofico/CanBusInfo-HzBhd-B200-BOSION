package com.hzbhd.canbus.ui_set;

import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierCreateAndDestroyListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierResetPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;


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

        this.customTitle = str;
    }

    public final String getCustom2Title() {
        return this.custom2Title;
    }

    public final void setCustom2Title(String str) {

        this.custom2Title = str;
    }

    public final int getSeekBarMaxCustom2() {
        return this.seekBarMaxCustom2;
    }

    public final void setSeekBarMaxCustom2(int i) {
        this.seekBarMaxCustom2 = i;
    }
}
