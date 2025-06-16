package com.hzbhd.canbus.adapter.bean;

import android.os.Parcel;
import android.os.Parcelable;

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

    public CanTypeAllEntity(String protocol_company, String car_category, String car_model, String years, String english_protocol_company, String english_car_category, String english_car_model, String english_years, int can_type_id, int can_different_id, int each_can_id, int baud_rate, int is_app_handle_key, int pack_type, int is_show_app, int is_use_new_camera, int is_use_new_app, String description) {
        this.protocol_company = protocol_company;
        this.car_category = car_category;
        this.car_model = car_model;
        this.years = years;
        this.english_protocol_company = english_protocol_company;
        this.english_car_category = english_car_category;
        this.english_car_model = english_car_model;
        this.english_years = english_years;
        this.can_type_id = can_type_id;
        this.can_different_id = can_different_id;
        this.each_can_id = each_can_id;
        this.baud_rate = baud_rate;
        this.is_app_handle_key = is_app_handle_key;
        this.pack_type = pack_type;
        this.is_show_app = is_show_app;
        this.is_use_new_camera = is_use_new_camera;
        this.is_use_new_app = is_use_new_app;
        this.description = description;
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