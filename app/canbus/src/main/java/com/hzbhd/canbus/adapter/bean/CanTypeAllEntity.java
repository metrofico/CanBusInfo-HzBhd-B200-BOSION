package com.hzbhd.canbus.adapter.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class CanTypeAllEntity implements Parcelable {
    public static final Parcelable.Creator<CanTypeAllEntity> CREATOR = new Parcelable.Creator<CanTypeAllEntity>() { // from class: com.hzbhd.canbus.adapter.bean.CanTypeAllEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CanTypeAllEntity createFromParcel(Parcel parcel) {
            return new CanTypeAllEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CanTypeAllEntity[] newArray(int i) {
            return new CanTypeAllEntity[i];
        }
    };
    private int baud_rate;
    private int can_different_id;
    private int can_type_id;
    private String car_category;
    private String car_model;
    private String description;
    private int each_can_id;
    String english_car_category;
    String english_car_model;
    String english_protocol_company;
    String english_years;
    private int is_app_handle_key;
    private int is_show_app;
    private int is_use_new_app;
    private int is_use_new_camera;
    private int pack_type;
    private String protocol_company;
    private boolean selected;
    private String years;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public CanTypeAllEntity() {
    }

    public CanTypeAllEntity(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, String str9) {
        this.protocol_company = str;
        this.car_category = str2;
        this.car_model = str3;
        this.years = str4;
        this.english_protocol_company = str5;
        this.english_car_category = str6;
        this.english_car_model = str7;
        this.english_years = str8;
        this.can_type_id = i;
        this.can_different_id = i2;
        this.each_can_id = i3;
        this.baud_rate = i4;
        this.is_app_handle_key = i5;
        this.pack_type = i6;
        this.is_show_app = i7;
        this.is_use_new_camera = i8;
        this.is_use_new_app = i9;
        this.description = str9;
    }

    public CanTypeAllEntity(Parcel parcel) {
        this.protocol_company = parcel.readString();
        this.car_category = parcel.readString();
        this.car_model = parcel.readString();
        this.years = parcel.readString();
        this.english_protocol_company = parcel.readString();
        this.english_car_category = parcel.readString();
        this.english_car_model = parcel.readString();
        this.english_years = parcel.readString();
        this.can_type_id = parcel.readInt();
        this.can_different_id = parcel.readInt();
        this.each_can_id = parcel.readInt();
        this.baud_rate = parcel.readInt();
        this.is_app_handle_key = parcel.readInt();
        this.pack_type = parcel.readInt();
        this.is_show_app = parcel.readInt();
        this.is_use_new_camera = parcel.readInt();
        this.is_use_new_app = parcel.readInt();
        this.description = parcel.readString();
    }

    public int getIs_use_new_app() {
        return this.is_use_new_app;
    }

    public void setIs_use_new_app(int i) {
        this.is_use_new_app = i;
    }

    public int getIs_use_new_camera() {
        return this.is_use_new_camera;
    }

    public void setIs_use_new_camera(int i) {
        this.is_use_new_camera = i;
    }

    public int getCan_type_id() {
        return this.can_type_id;
    }

    public void setCan_type_id(int i) {
        this.can_type_id = i;
    }

    public int getCan_different_id() {
        return this.can_different_id;
    }

    public void setCan_different_id(int i) {
        this.can_different_id = i;
    }

    public int getEach_can_id() {
        return this.each_can_id;
    }

    public void setEach_can_id(int i) {
        this.each_can_id = i;
    }

    public int getBaud_rate() {
        return this.baud_rate;
    }

    public void setBaud_rate(int i) {
        this.baud_rate = i;
    }

    public int getIs_app_handle_key() {
        return this.is_app_handle_key;
    }

    public void setIs_app_handle_key(int i) {
        this.is_app_handle_key = i;
    }

    public int getPack_type() {
        return this.pack_type;
    }

    public void setPack_type(int i) {
        this.pack_type = i;
    }

    public int getIs_show_app() {
        return this.is_show_app;
    }

    public void setIs_show_app(int i) {
        this.is_show_app = i;
    }

    public String getProtocol_company() {
        return this.protocol_company;
    }

    public void setProtocol_company(String str) {
        this.protocol_company = str;
    }

    public String getCar_category() {
        return this.car_category;
    }

    public void setCar_category(String str) {
        this.car_category = str;
    }

    public String getCar_model() {
        return this.car_model;
    }

    public void setCar_model(String str) {
        this.car_model = str;
    }

    public String getYears() {
        return this.years;
    }

    public void setYears(String str) {
        this.years = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setSelected(boolean z) {
        this.selected = z;
    }

    public String getEnglish_protocol_company() {
        return this.english_protocol_company;
    }

    public void setEnglish_protocol_company(String str) {
        this.english_protocol_company = str;
    }

    public String getEnglish_car_category() {
        return this.english_car_category;
    }

    public void setEnglish_car_category(String str) {
        this.english_car_category = str;
    }

    public String getEnglish_car_model() {
        return this.english_car_model;
    }

    public void setEnglish_car_model(String str) {
        this.english_car_model = str;
    }

    public String getEnglish_years() {
        return this.english_years;
    }

    public void setEnglish_years(String str) {
        this.english_years = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.protocol_company);
        parcel.writeString(this.car_category);
        parcel.writeString(this.car_model);
        parcel.writeString(this.years);
        parcel.writeString(this.english_protocol_company);
        parcel.writeString(this.english_car_category);
        parcel.writeString(this.english_car_model);
        parcel.writeString(this.english_years);
        parcel.writeInt(this.can_type_id);
        parcel.writeInt(this.can_different_id);
        parcel.writeInt(this.each_can_id);
        parcel.writeInt(this.baud_rate);
        parcel.writeInt(this.is_app_handle_key);
        parcel.writeInt(this.pack_type);
        parcel.writeInt(this.is_show_app);
        parcel.writeInt(this.is_use_new_camera);
        parcel.writeInt(this.is_use_new_app);
        parcel.writeString(this.description);
    }

    public void readFromParcel(Parcel parcel) {
        this.protocol_company = parcel.readString();
        this.car_category = parcel.readString();
        this.car_model = parcel.readString();
        this.years = parcel.readString();
        this.english_protocol_company = parcel.readString();
        this.english_car_category = parcel.readString();
        this.english_car_model = parcel.readString();
        this.english_years = parcel.readString();
        this.can_type_id = parcel.readInt();
        this.can_different_id = parcel.readInt();
        this.each_can_id = parcel.readInt();
        this.baud_rate = parcel.readInt();
        this.is_app_handle_key = parcel.readInt();
        this.pack_type = parcel.readInt();
        this.is_show_app = parcel.readInt();
        this.is_use_new_camera = parcel.readInt();
        this.is_use_new_app = parcel.readInt();
        this.description = parcel.readString();
    }
}
