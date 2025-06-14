package nfore.android.bt.pbap;

import android.database.Cursor;
import java.io.Serializable;
import java.util.Set;

/* loaded from: classes3.dex */
public class VCardPack implements Serializable {
    private String HistoryDate;
    private String HistoryTime;
    private int _id;
    private String cellPhone_Address;
    private String firstName;
    private String first_CityNameAddress;
    private String first_CountryAddress;
    private String first_FederalStateAddress;
    private String first_StreetAddress;
    private String first_ZipCodeAddress;
    private String fullName;
    private String lastName;
    private Set<PhoneInfo> phoneNumbers;
    private String second_CityNameAddress;
    private String second_CountryAddress;
    private String second_FederalStateAddress;
    private String second_StreetAddress;
    private String second_ZipCodeAddress;
    private String storageType;

    public int get_id() {
        return this._id;
    }

    public void set_id(int i) {
        this._id = i;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String str) {
        this.fullName = str;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String str) {
        this.firstName = str;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String str) {
        this.lastName = str;
    }

    public String getFirst_StreetAddress() {
        return this.first_StreetAddress;
    }

    public void setFirst_StreetAddress(String str) {
        this.first_StreetAddress = str;
    }

    public String getFirst_CityNameAddress() {
        return this.first_CityNameAddress;
    }

    public void setFirst_CityNameAddress(String str) {
        this.first_CityNameAddress = str;
    }

    public String getFirst_FederalStateAddress() {
        return this.first_FederalStateAddress;
    }

    public void setFirst_FederalStateAddress(String str) {
        this.first_FederalStateAddress = str;
    }

    public String getFirst_ZipCodeAddress() {
        return this.first_ZipCodeAddress;
    }

    public void setFirst_ZipCodeAddress(String str) {
        this.first_ZipCodeAddress = str;
    }

    public String getFirst_CountryAddress() {
        return this.first_CountryAddress;
    }

    public void setFirst_CountryAddress(String str) {
        this.first_CountryAddress = str;
    }

    public String getSecond_StreetAddress() {
        return this.second_StreetAddress;
    }

    public void setSecond_StreetAddress(String str) {
        this.second_StreetAddress = str;
    }

    public String getSecond_CityNameAddress() {
        return this.second_CityNameAddress;
    }

    public void setSecond_CityNameAddress(String str) {
        this.second_CityNameAddress = str;
    }

    public String getSecond_FederalStateAddress() {
        return this.second_FederalStateAddress;
    }

    public void setSecond_FederalStateAddress(String str) {
        this.second_FederalStateAddress = str;
    }

    public String getSecond_ZipCodeAddress() {
        return this.second_ZipCodeAddress;
    }

    public void setSecond_ZipCodeAddress(String str) {
        this.second_ZipCodeAddress = str;
    }

    public String getSecond_CountryAddress() {
        return this.second_CountryAddress;
    }

    public void setSecond_CountryAddress(String str) {
        this.second_CountryAddress = str;
    }

    public String getCellPhone_Address() {
        return this.cellPhone_Address;
    }

    public void setCellPhone_Address(String str) {
        this.cellPhone_Address = str;
    }

    public Set<PhoneInfo> getPhoneNumbers() {
        return this.phoneNumbers;
    }

    public void setPhoneNumbers(Set<PhoneInfo> set) {
        this.phoneNumbers = set;
    }

    public String getStorageType() {
        return this.storageType;
    }

    public void setStorageType(String str) {
        this.storageType = str;
    }

    public VCardPack() {
    }

    public VCardPack(Cursor cursor) {
        int columnIndex = cursor.getColumnIndex("_id");
        int columnIndex2 = cursor.getColumnIndex("FullName");
        int columnIndex3 = cursor.getColumnIndex("FirstName");
        int columnIndex4 = cursor.getColumnIndex("LastName");
        int columnIndex5 = cursor.getColumnIndex("First_StreetAddress");
        int columnIndex6 = cursor.getColumnIndex("First_CityNameAddress");
        int columnIndex7 = cursor.getColumnIndex("First_FederalStateAddress");
        int columnIndex8 = cursor.getColumnIndex("First_ZipCodeAddress");
        int columnIndex9 = cursor.getColumnIndex("First_CountryAddress");
        int columnIndex10 = cursor.getColumnIndex("Second_StreetAddress");
        int columnIndex11 = cursor.getColumnIndex("Second_CityNameAddress");
        int columnIndex12 = cursor.getColumnIndex("Second_FederalStateAddress");
        int columnIndex13 = cursor.getColumnIndex("Second_ZipCodeAddress");
        int columnIndex14 = cursor.getColumnIndex("Second_CountryAddress");
        int columnIndex15 = cursor.getColumnIndex("CellPhone_Address");
        int columnIndex16 = cursor.getColumnIndex("StorageType");
        set_id(cursor.getInt(columnIndex));
        setFullName(cursor.getString(columnIndex2));
        setFirstName(cursor.getString(columnIndex3));
        setLastName(cursor.getString(columnIndex4));
        setFirst_StreetAddress(cursor.getString(columnIndex5));
        setFirst_CityNameAddress(cursor.getString(columnIndex6));
        setFirst_FederalStateAddress(cursor.getString(columnIndex7));
        setFirst_ZipCodeAddress(cursor.getString(columnIndex8));
        setFirst_CountryAddress(cursor.getString(columnIndex9));
        setSecond_StreetAddress(cursor.getString(columnIndex10));
        setSecond_CityNameAddress(cursor.getString(columnIndex11));
        setSecond_FederalStateAddress(cursor.getString(columnIndex12));
        setSecond_ZipCodeAddress(cursor.getString(columnIndex13));
        setSecond_CountryAddress(cursor.getString(columnIndex14));
        setCellPhone_Address(cursor.getString(columnIndex15));
        setStorageType(cursor.getString(columnIndex16));
    }

    public String getHistoryDate() {
        return this.HistoryDate;
    }

    public void setHistoryDate(String str) {
        this.HistoryDate = str;
    }

    public String getHistoryTime() {
        return this.HistoryTime;
    }

    public void setHistoryTime(String str) {
        this.HistoryTime = str;
    }
}
