package nfore.android.bt.pbap;

import java.io.Serializable;

/* loaded from: classes3.dex */
public class PhoneInfo implements Serializable {
    private String calledHistoryDate;
    private String calledHistoryTime;
    private String phoneNumber;
    private String phoneType;
    private String phoneTypeName;

    public String getPhoneType() {
        return this.phoneType;
    }

    public void setPhoneType(String str) {
        this.phoneType = str;
    }

    public String getPhoneTypeName() {
        return this.phoneTypeName;
    }

    public void setPhoneTypeName(String str) {
        this.phoneTypeName = str;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String str) {
        this.phoneNumber = str;
    }

    public String getCalledHistoryDate() {
        return this.calledHistoryDate;
    }

    public void setCalledHistoryDate(String str) {
        this.calledHistoryDate = str;
    }

    public String getCalledHistoryTime() {
        return this.calledHistoryTime;
    }

    public void setCalledHistoryTime(String str) {
        this.calledHistoryTime = str;
    }
}
