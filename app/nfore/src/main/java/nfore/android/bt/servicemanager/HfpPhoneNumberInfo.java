package nfore.android.bt.servicemanager;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes3.dex */
public class HfpPhoneNumberInfo implements Parcelable {
    public static final Parcelable.Creator<HfpPhoneNumberInfo> CREATOR = new Parcelable.Creator<HfpPhoneNumberInfo>() { // from class: nfore.android.bt.servicemanager.HfpPhoneNumberInfo.1
        @Override // android.os.Parcelable.Creator
        public HfpPhoneNumberInfo createFromParcel(Parcel parcel) {
            return new HfpPhoneNumberInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public HfpPhoneNumberInfo[] newArray(int i) {
            return new HfpPhoneNumberInfo[i];
        }
    };
    private int dir;
    private int idx;
    private int mode;
    private int mpty;
    private String number;
    private int status;
    private int type;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HfpPhoneNumberInfo() {
    }

    public HfpPhoneNumberInfo(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void readFromParcel(Parcel parcel) {
        this.idx = parcel.readInt();
        this.dir = parcel.readInt();
        this.status = parcel.readInt();
        this.mode = parcel.readInt();
        this.mpty = parcel.readInt();
        this.type = parcel.readInt();
        this.number = parcel.readString();
    }

    public int getIdx() {
        return this.idx;
    }

    public void setIdx(int i) {
        this.idx = i;
    }

    public int getDir() {
        return this.dir;
    }

    public void setDir(int i) {
        this.dir = i;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public int getMode() {
        return this.mode;
    }

    public void setMode(int i) {
        this.mode = i;
    }

    public int getMpty() {
        return this.mpty;
    }

    public void setMpty(int i) {
        this.mpty = i;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String str) {
        this.number = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.idx);
        parcel.writeInt(this.dir);
        parcel.writeInt(this.status);
        parcel.writeInt(this.mode);
        parcel.writeInt(this.mpty);
        parcel.writeInt(this.type);
        parcel.writeString(this.number);
    }

    public HfpPhoneNumberInfo clone() {
        HfpPhoneNumberInfo hfpPhoneNumberInfo = new HfpPhoneNumberInfo();
        hfpPhoneNumberInfo.setIdx(this.idx);
        hfpPhoneNumberInfo.setDir(this.dir);
        hfpPhoneNumberInfo.setStatus(this.status);
        hfpPhoneNumberInfo.setMode(this.mode);
        hfpPhoneNumberInfo.setMpty(this.mpty);
        hfpPhoneNumberInfo.setType(this.type);
        hfpPhoneNumberInfo.setNumber(this.number);
        return hfpPhoneNumberInfo;
    }
}
