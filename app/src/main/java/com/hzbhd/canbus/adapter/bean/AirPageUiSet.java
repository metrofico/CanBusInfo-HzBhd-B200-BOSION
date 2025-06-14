package com.hzbhd.canbus.adapter.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.hzbhd.canbus.adapter.interfaces.OnAirInitListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;

/* loaded from: classes.dex */
public class AirPageUiSet implements Parcelable {
    public static final Parcelable.Creator<AirPageUiSet> CREATOR = new Parcelable.Creator<AirPageUiSet>() { // from class: com.hzbhd.canbus.adapter.bean.AirPageUiSet.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AirPageUiSet createFromParcel(Parcel parcel) {
            return new AirPageUiSet(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AirPageUiSet[] newArray(int i) {
            return new AirPageUiSet[i];
        }
    };
    private FrontArea frontArea;
    private boolean isHaveRearArea;
    private OnAirInitListener onAirInitListener;
    private OnAirPageStatusListener onAirPageStatusListener;
    private RearArea rearArea;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public AirPageUiSet(Parcel parcel) {
        this.isHaveRearArea = false;
        this.frontArea = (FrontArea) parcel.readTypedObject(FrontArea.CREATOR);
        this.rearArea = (RearArea) parcel.readTypedObject(RearArea.CREATOR);
        this.isHaveRearArea = parcel.readByte() != 0;
    }

    public OnAirPageStatusListener getOnAirPageStatusListener() {
        return this.onAirPageStatusListener;
    }

    public void setOnAirPageStatusListener(OnAirPageStatusListener onAirPageStatusListener) {
        this.onAirPageStatusListener = onAirPageStatusListener;
    }

    public OnAirInitListener getOnAirInitListener() {
        return this.onAirInitListener;
    }

    public void setOnAirInitListener(OnAirInitListener onAirInitListener) {
        this.onAirInitListener = onAirInitListener;
    }

    public FrontArea getFrontArea() {
        return this.frontArea;
    }

    public void setFrontArea(FrontArea frontArea) {
        this.frontArea = frontArea;
    }

    public RearArea getRearArea() {
        return this.rearArea;
    }

    public void setRearArea(RearArea rearArea) {
        this.rearArea = rearArea;
    }

    public boolean isHaveRearArea() {
        return this.isHaveRearArea;
    }

    public void setHaveRearArea(boolean z) {
        this.isHaveRearArea = z;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedObject(this.frontArea, 0);
        parcel.writeTypedObject(this.rearArea, 0);
        parcel.writeByte(this.isHaveRearArea ? (byte) 1 : (byte) 0);
    }
}
