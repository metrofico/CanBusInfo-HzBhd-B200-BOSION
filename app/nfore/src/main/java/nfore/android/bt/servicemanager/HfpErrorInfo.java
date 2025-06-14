package nfore.android.bt.servicemanager;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes3.dex */
public class HfpErrorInfo implements Parcelable {
    public static final Parcelable.Creator<HfpErrorInfo> CREATOR = new Parcelable.Creator<HfpErrorInfo>() { // from class: nfore.android.bt.servicemanager.HfpErrorInfo.1
        @Override // android.os.Parcelable.Creator
        public HfpErrorInfo createFromParcel(Parcel parcel) {
            return new HfpErrorInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public HfpErrorInfo[] newArray(int i) {
            return new HfpErrorInfo[i];
        }
    };
    private int errorCode;
    private String errorDesc;
    private String errorResponse;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HfpErrorInfo() {
    }

    public HfpErrorInfo(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void readFromParcel(Parcel parcel) {
        this.errorCode = parcel.readInt();
        this.errorResponse = parcel.readString();
        this.errorDesc = parcel.readString();
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(int i) {
        this.errorCode = i;
    }

    public String getErrorResponse() {
        return this.errorResponse;
    }

    public void setErrorResponse(String str) {
        this.errorResponse = str;
    }

    public String getErrorDesc() {
        return this.errorDesc;
    }

    public void setErrorDesc(String str) {
        this.errorDesc = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.errorCode);
        parcel.writeString(this.errorResponse);
        parcel.writeString(this.errorDesc);
    }
}
