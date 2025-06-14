package com.hzbhd.canbus.adapter.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTempTouchListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;

/* loaded from: classes.dex */
public class FrontArea implements Parcelable {
    public static final Parcelable.Creator<FrontArea> CREATOR = new Parcelable.Creator<FrontArea>() { // from class: com.hzbhd.canbus.adapter.bean.FrontArea.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FrontArea createFromParcel(Parcel parcel) {
            return new FrontArea(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FrontArea[] newArray(int i) {
            return new FrontArea[i];
        }
    };
    private String[] disableBtnArray;
    private boolean isAllBtnCanClick;
    private boolean isCanSetCenterTemp;
    private boolean isCanSetLeftTemp;
    private boolean isCanSetRightTemp;
    private boolean isCanSetSeatCold;
    private boolean isCanSetSeatHeat;
    private boolean isCanSetWindSpeed;
    private boolean isHaveLeftRightWindSpeed;
    private boolean isShowCenterWheel;
    private boolean isShowLeftWheel;
    private boolean isShowPmValue;
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
    private OnAirWindSpeedUpDownClickListener setRightWindSpeedViewOnClickListener;
    private OnAirWindSpeedUpDownClickListener setWindSpeedViewOnClickListener;
    private String[] smallWindowStatusArray;
    private OnAirTemperatureUpDownClickListener[] tempSetViewOnUpDownClickListeners;
    private OnAirTempTouchListener[] tempTouchListeners;
    private int windMaxLevel;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected FrontArea(Parcel parcel) {
        this.lineBtnAction = new String[][]{parcel.createStringArray(), parcel.createStringArray(), parcel.createStringArray(), parcel.createStringArray()};
        this.isAllBtnCanClick = parcel.readByte() != 0;
        this.isShowLeftWheel = parcel.readByte() != 0;
        this.isShowCenterWheel = parcel.readByte() != 0;
        this.isShowRightWheel = parcel.readByte() != 0;
        this.isCanSetLeftTemp = parcel.readByte() != 0;
        this.isCanSetCenterTemp = parcel.readByte() != 0;
        this.isCanSetRightTemp = parcel.readByte() != 0;
        this.windMaxLevel = parcel.readInt();
        this.isCanSetWindSpeed = parcel.readByte() != 0;
        this.isHaveLeftRightWindSpeed = parcel.readByte() != 0;
        this.isShowSeatHeat = parcel.readByte() != 0;
        this.isCanSetSeatHeat = parcel.readByte() != 0;
        this.isShowSeatCold = parcel.readByte() != 0;
        this.isCanSetSeatCold = parcel.readByte() != 0;
        this.isShowPmValue = parcel.readByte() != 0;
        this.seatHeatSrnArray = parcel.createStringArray();
        this.seatColdSrnArray = parcel.createStringArray();
        this.disableBtnArray = parcel.createStringArray();
        this.smallWindowStatusArray = parcel.createStringArray();
    }

    public void setHaveLeftRightWindSpeed(boolean z) {
        this.isHaveLeftRightWindSpeed = z;
    }

    public boolean getIsHaveLeftRightWindSpeed() {
        return this.isHaveLeftRightWindSpeed;
    }

    public String[] getDisableBtnArray() {
        return this.disableBtnArray;
    }

    public void setDisableBtnArray(String[] strArr) {
        this.disableBtnArray = strArr;
    }

    public OnAirWindSpeedUpDownClickListener getSetRightWindSpeedViewOnClickListener() {
        return this.setRightWindSpeedViewOnClickListener;
    }

    public void setSetRightWindSpeedViewOnClickListener(OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener) {
        this.setRightWindSpeedViewOnClickListener = onAirWindSpeedUpDownClickListener;
    }

    public boolean isShowPmValue() {
        return this.isShowPmValue;
    }

    public void setShowPmValue(boolean z) {
        this.isShowPmValue = z;
    }

    public String[] getDisableBtnS() {
        return this.disableBtnArray;
    }

    public void setDisableBtnS(String[] strArr) {
        this.disableBtnArray = strArr;
    }

    public OnAirSeatClickListener getOnAirSeatClickListener() {
        return this.onAirSeatClickListener;
    }

    public void setOnAirSeatClickListener(OnAirSeatClickListener onAirSeatClickListener) {
        this.onAirSeatClickListener = onAirSeatClickListener;
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

    public OnAirTempTouchListener[] getTempTouchListeners() {
        return this.tempTouchListeners;
    }

    public void setTempTouchListeners(OnAirTempTouchListener[] onAirTempTouchListenerArr) {
        this.tempTouchListeners = onAirTempTouchListenerArr;
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

    public boolean isCanSetCenterTemp() {
        return this.isCanSetCenterTemp;
    }

    public void setCanSetCenterTemp(boolean z) {
        this.isCanSetCenterTemp = z;
    }

    public boolean isCanSetRightTemp() {
        return this.isCanSetRightTemp;
    }

    public void setCanSetRightTemp(boolean z) {
        this.isCanSetRightTemp = z;
    }

    public void setCanSetLeftTemp(boolean z) {
        this.isCanSetLeftTemp = z;
    }

    public boolean isCanSetLeftTemp() {
        return this.isCanSetLeftTemp;
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

    public String[] getSmallWindowStatusArray() {
        return this.smallWindowStatusArray;
    }

    public void setSmallWindowStatusArray(String[] strArr) {
        this.smallWindowStatusArray = strArr;
    }

    public boolean isCanSetWindSpeed() {
        return this.isCanSetWindSpeed;
    }

    public void setCanSetWindSpeed(boolean z) {
        this.isCanSetWindSpeed = z;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(this.lineBtnAction[0]);
        parcel.writeStringArray(this.lineBtnAction[1]);
        parcel.writeStringArray(this.lineBtnAction[2]);
        parcel.writeStringArray(this.lineBtnAction[3]);
        parcel.writeByte(this.isAllBtnCanClick ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isShowLeftWheel ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isShowCenterWheel ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isShowRightWheel ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isCanSetLeftTemp ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isCanSetCenterTemp ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isCanSetRightTemp ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.windMaxLevel);
        parcel.writeByte(this.isCanSetWindSpeed ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isHaveLeftRightWindSpeed ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isShowSeatHeat ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isCanSetSeatHeat ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isShowSeatCold ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isCanSetSeatCold ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isShowPmValue ? (byte) 1 : (byte) 0);
        parcel.writeStringArray(this.seatHeatSrnArray);
        parcel.writeStringArray(this.seatColdSrnArray);
        parcel.writeStringArray(this.disableBtnArray);
        parcel.writeStringArray(this.smallWindowStatusArray);
    }
}
