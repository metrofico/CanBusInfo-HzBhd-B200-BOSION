package nfore.android.bt.servicemanager;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes3.dex */
public class HfpStatusInfo implements Parcelable {
    public static final Parcelable.Creator<HfpStatusInfo> CREATOR = new Parcelable.Creator<HfpStatusInfo>() { // from class: nfore.android.bt.servicemanager.HfpStatusInfo.1
        @Override // android.os.Parcelable.Creator
        public HfpStatusInfo createFromParcel(Parcel parcel) {
            return new HfpStatusInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public HfpStatusInfo[] newArray(int i) {
            return new HfpStatusInfo[i];
        }
    };
    private int battchgState;
    private int callState;
    private int callbackState;
    private int callheldState;
    private int callsetupState;
    private String deviceAddress;
    private String deviceName;
    private int mainState;
    private int roamState;
    private int scoState;
    private int serviceState;
    private int signalState;
    private int singleType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HfpStatusInfo() {
    }

    public HfpStatusInfo(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void readFromParcel(Parcel parcel) {
        this.deviceAddress = parcel.readString();
        this.deviceName = parcel.readString();
        this.mainState = parcel.readInt();
        this.scoState = parcel.readInt();
        this.serviceState = parcel.readInt();
        this.callState = parcel.readInt();
        this.callsetupState = parcel.readInt();
        this.callheldState = parcel.readInt();
        this.signalState = parcel.readInt();
        this.roamState = parcel.readInt();
        this.battchgState = parcel.readInt();
        this.callbackState = parcel.readInt();
        this.singleType = parcel.readInt();
    }

    public String getDeviceAddress() {
        return this.deviceAddress;
    }

    public void setDeviceAddress(String str) {
        this.deviceAddress = str;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public void setDeviceName(String str) {
        this.deviceName = str;
    }

    public int getMainState() {
        return this.mainState;
    }

    public void setMainState(int i) {
        this.mainState = i;
    }

    public int getScoState() {
        return this.scoState;
    }

    public void setScoState(int i) {
        this.scoState = i;
    }

    public int getServiceState() {
        return this.serviceState;
    }

    public void setServiceState(int i) {
        this.serviceState = i;
    }

    public int getCallState() {
        return this.callState;
    }

    public void setCallState(int i) {
        this.callState = i;
    }

    public int getCallsetupState() {
        return this.callsetupState;
    }

    public void setCallsetupState(int i) {
        this.callsetupState = i;
    }

    public int getCallheldState() {
        return this.callheldState;
    }

    public void setCallheldState(int i) {
        this.callheldState = i;
    }

    public int getSignalState() {
        return this.signalState;
    }

    public void setSignalState(int i) {
        this.signalState = i;
    }

    public int getRoamState() {
        return this.roamState;
    }

    public void setRoamState(int i) {
        this.roamState = i;
    }

    public int getBattchgState() {
        return this.battchgState;
    }

    public void setBattchgState(int i) {
        this.battchgState = i;
    }

    public int getCallbackState() {
        return this.callbackState;
    }

    public void setCallbackState(int i) {
        this.callbackState = i;
    }

    public int getSingleType() {
        return this.singleType;
    }

    public void setSingleType(int i) {
        this.singleType = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.deviceAddress);
        parcel.writeString(this.deviceName);
        parcel.writeInt(this.mainState);
        parcel.writeInt(this.scoState);
        parcel.writeInt(this.serviceState);
        parcel.writeInt(this.callState);
        parcel.writeInt(this.callsetupState);
        parcel.writeInt(this.callheldState);
        parcel.writeInt(this.signalState);
        parcel.writeInt(this.roamState);
        parcel.writeInt(this.battchgState);
        parcel.writeInt(this.callbackState);
        parcel.writeInt(this.singleType);
    }
}
