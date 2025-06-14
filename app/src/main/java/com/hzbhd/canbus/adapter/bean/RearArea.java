package com.hzbhd.canbus.adapter.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;

/* loaded from: classes.dex */
public class RearArea implements Parcelable {
    public static final Parcelable.Creator<RearArea> CREATOR = new Parcelable.Creator<RearArea>() { // from class: com.hzbhd.canbus.adapter.bean.RearArea.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RearArea createFromParcel(Parcel parcel) {
            return new RearArea(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RearArea[] newArray(int i) {
            return new RearArea[i];
        }
    };
    private String[] disableBtnArray;
    private boolean isAllBtnCanClick;
    private boolean isCanSetSeatCold;
    private boolean isCanSetSeatHeat;
    private boolean isCanSetTemp;
    private boolean isCanSetWindSpeed;
    private boolean isShowCenterWheel;
    private boolean isShowLeftWheel;
    private boolean isShowRightWheel;
    private boolean isShowSeatCold;
    private boolean isShowSeatHeat;
    private String[][] lineBtnAction;
    private OnAirBtnClickListener[] onAirBtnClickListeners;
    private OnAirPageStatusListener onAirPageStatusListener;
    private OnAirSeatClickListener onAirSeatClickListener;
    private String[] seatColdSrnArray;
    private OnAirSeatHeatColdMinPlusClickListener[] seatHeatColdClickListeners;
    private String[] seatHeatSrnArray;
    private OnAirWindSpeedUpDownClickListener setWindSpeedViewOnClickListener;
    private OnAirTemperatureUpDownClickListener[] tempSetViewOnUpDownClickListeners;
    private int windMaxLevel;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected RearArea(Parcel parcel) {
        this.isAllBtnCanClick = parcel.readByte() != 0;
        this.isShowLeftWheel = parcel.readByte() != 0;
        this.isShowCenterWheel = parcel.readByte() != 0;
        this.isShowRightWheel = parcel.readByte() != 0;
        this.isCanSetTemp = parcel.readByte() != 0;
        this.windMaxLevel = parcel.readInt();
        this.isCanSetWindSpeed = parcel.readByte() != 0;
        this.isShowSeatHeat = parcel.readByte() != 0;
        this.isCanSetSeatHeat = parcel.readByte() != 0;
        this.isShowSeatCold = parcel.readByte() != 0;
        this.isCanSetSeatCold = parcel.readByte() != 0;
        this.seatHeatSrnArray = parcel.createStringArray();
        this.seatColdSrnArray = parcel.createStringArray();
        this.disableBtnArray = parcel.createStringArray();
    }

    public String[] getDisableBtnArray() {
        return this.disableBtnArray;
    }

    public void setDisableBtnArray(String[] strArr) {
        this.disableBtnArray = strArr;
    }

    public OnAirSeatClickListener getOnAirSeatClickListener() {
        return this.onAirSeatClickListener;
    }

    public void setOnAirSeatClickListener(OnAirSeatClickListener onAirSeatClickListener) {
        this.onAirSeatClickListener = onAirSeatClickListener;
    }

    public String[] getDisableBtnS() {
        return this.disableBtnArray;
    }

    public void setDisableBtnS(String[] strArr) {
        this.disableBtnArray = strArr;
    }

    public OnAirPageStatusListener getOnAirPageStatusListener() {
        return this.onAirPageStatusListener;
    }

    public void setOnAirPageStatusListener(OnAirPageStatusListener onAirPageStatusListener) {
        this.onAirPageStatusListener = onAirPageStatusListener;
    }

    public OnAirSeatHeatColdMinPlusClickListener[] getSeatHeatColdClickListeners() {
        return this.seatHeatColdClickListeners;
    }

    public void setSeatHeatColdClickListeners(OnAirSeatHeatColdMinPlusClickListener[] onAirSeatHeatColdMinPlusClickListenerArr) {
        this.seatHeatColdClickListeners = onAirSeatHeatColdMinPlusClickListenerArr;
    }

    public OnAirBtnClickListener[] getOnAirBtnClickListeners() {
        return this.onAirBtnClickListeners;
    }

    public void setOnAirBtnClickListeners(OnAirBtnClickListener[] onAirBtnClickListenerArr) {
        this.onAirBtnClickListeners = onAirBtnClickListenerArr;
    }

    public OnAirTemperatureUpDownClickListener[] getTempSetViewOnUpDownClickListeners() {
        return this.tempSetViewOnUpDownClickListeners;
    }

    public void setTempSetViewOnUpDownClickListeners(OnAirTemperatureUpDownClickListener[] onAirTemperatureUpDownClickListenerArr) {
        this.tempSetViewOnUpDownClickListeners = onAirTemperatureUpDownClickListenerArr;
    }

    public OnAirWindSpeedUpDownClickListener getSetWindSpeedViewOnClickListener() {
        return this.setWindSpeedViewOnClickListener;
    }

    public void setSetWindSpeedViewOnClickListener(OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener) {
        this.setWindSpeedViewOnClickListener = onAirWindSpeedUpDownClickListener;
    }

    public void setLineBtnAction(String[][] strArr) {
        this.lineBtnAction = strArr;
    }

    public void setWindMaxLevel(int i) {
        this.windMaxLevel = i;
    }

    public void setShowLeftWheel(boolean z) {
        this.isShowLeftWheel = z;
    }

    public void setShowCenterWheel(boolean z) {
        this.isShowCenterWheel = z;
    }

    public void setShowRightWheel(boolean z) {
        this.isShowRightWheel = z;
    }

    public boolean isCanSetSeatHeat() {
        return this.isCanSetSeatHeat;
    }

    public void setCanSetSeatHeat(boolean z) {
        this.isCanSetSeatHeat = z;
    }

    public boolean isCanSetSeatCold() {
        return this.isCanSetSeatCold;
    }

    public void setCanSetSeatCold(boolean z) {
        this.isCanSetSeatCold = z;
    }

    public void setCanSetTemp(boolean z) {
        this.isCanSetTemp = z;
    }

    public boolean isCanSetTemp() {
        return this.isCanSetTemp;
    }

    public String[][] getLineBtnAction() {
        return this.lineBtnAction;
    }

    public int getWindMaxLevel() {
        return this.windMaxLevel;
    }

    public boolean isShowLeftWheel() {
        return this.isShowLeftWheel;
    }

    public boolean isShowCenterWheel() {
        return this.isShowCenterWheel;
    }

    public boolean isShowRightWheel() {
        return this.isShowRightWheel;
    }

    public boolean isAllBtnCanClick() {
        return this.isAllBtnCanClick;
    }

    public void setAllBtnCanClick(boolean z) {
        this.isAllBtnCanClick = z;
    }

    public boolean isShowSeatHeat() {
        return this.isShowSeatHeat;
    }

    public void setShowSeatHeat(boolean z) {
        this.isShowSeatHeat = z;
    }

    public boolean isShowSeatCold() {
        return this.isShowSeatCold;
    }

    public void setShowSeatCold(boolean z) {
        this.isShowSeatCold = z;
    }

    public String[] getSeatHeatSrnArray() {
        return this.seatHeatSrnArray;
    }

    public void setSeatHeatSrnArray(String[] strArr) {
        this.seatHeatSrnArray = strArr;
    }

    public String[] getSeatColdSrnArray() {
        return this.seatColdSrnArray;
    }

    public void setSeatColdSrnArray(String[] strArr) {
        this.seatColdSrnArray = strArr;
    }

    public boolean isCanSetWindSpeed() {
        return this.isCanSetWindSpeed;
    }

    public void setCanSetWindSpeed(boolean z) {
        this.isCanSetWindSpeed = z;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.isAllBtnCanClick ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isShowLeftWheel ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isShowCenterWheel ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isShowRightWheel ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isCanSetTemp ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.windMaxLevel);
        parcel.writeByte(this.isCanSetWindSpeed ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isShowSeatHeat ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isCanSetSeatHeat ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isShowSeatCold ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isCanSetSeatCold ? (byte) 1 : (byte) 0);
        parcel.writeStringArray(this.seatHeatSrnArray);
        parcel.writeStringArray(this.seatColdSrnArray);
        parcel.writeStringArray(this.disableBtnArray);
    }
}
