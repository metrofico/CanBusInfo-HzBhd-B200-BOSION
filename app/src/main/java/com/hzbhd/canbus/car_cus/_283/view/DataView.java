package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.internal.view.SupportMenu;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.canbus.car_cus._283.utils.Utils;
import com.hzbhd.canbus.util.SharePreUtil;

/* loaded from: classes2.dex */
public class DataView extends RelativeLayout {
    private CarImageView carImageView;
    private Context mContext;
    private View mView;
    private ImageView rediduImage;
    private TextView tv_mileage;
    private TextView tv_oil;
    private TextView tv_residu;
    private TextView tv_speed;
    private TextView tv_time;

    public DataView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        this.mView = LayoutInflater.from(context).inflate(R.layout._283_data_view, this);
        initView();
    }

    private void initView() {
        this.tv_time = (TextView) this.mView.findViewById(R.id.tv_time);
        this.tv_speed = (TextView) this.mView.findViewById(R.id.tv_speed);
        this.tv_oil = (TextView) this.mView.findViewById(R.id.tv_oil);
        this.tv_residu = (TextView) this.mView.findViewById(R.id.tv_residu);
        this.tv_mileage = (TextView) this.mView.findViewById(R.id.tv_mileage);
        this.carImageView = (CarImageView) this.mView.findViewById(R.id.carImageView);
        this.rediduImage = (ImageView) this.mView.findViewById(R.id.rediduImage);
    }

    public void refreshUi(int i) {
        String dataString;
        String dataString2;
        String dataString3;
        String dataString4;
        String dataString5;
        String str;
        String str2;
        String str3 = "";
        if (i == 1) {
            dataString = getDataString("launch_oil", GeneralDzData.launch_oil);
            dataString2 = getDataString("launch_residu", GeneralDzData.launch_residu);
            dataString3 = getDataString("launch_mileage", GeneralDzData.launch_mileage);
            dataString4 = getDataString("launch_time", GeneralDzData.launch_time);
            dataString5 = getDataString("launch_speed", GeneralDzData.launch_speed);
        } else if (i == 2) {
            dataString = getDataString("longtime_oil", GeneralDzData.longtime_oil);
            dataString2 = getDataString("longtime_residu", GeneralDzData.longtime_residu);
            dataString3 = getDataString("longtime_mileage", GeneralDzData.longtime_mileage);
            dataString4 = getDataString("longtime_time", GeneralDzData.longtime_time);
            dataString5 = getDataString("longtime_speed", GeneralDzData.longtime_speed);
        } else if (i == 3) {
            dataString = getDataString("refuel_oil", GeneralDzData.refuel_oil);
            dataString2 = getDataString("refuel_residu", GeneralDzData.refuel_residu);
            dataString3 = getDataString("refuel_mileage", GeneralDzData.refuel_mileage);
            dataString4 = getDataString("refuel_time", GeneralDzData.refuel_time);
            dataString5 = getDataString("refuel_speed", GeneralDzData.refuel_speed);
        } else {
            str2 = "";
            dataString3 = str2;
            str = dataString3;
            dataString5 = str;
            this.tv_time.setText(str3 + "  min");
            this.tv_speed.setText(dataString5 + "  km/h");
            this.tv_oil.setText(str2 + "  L/100km");
            this.tv_mileage.setText(dataString3 + "  km");
            this.tv_residu.setText(str + "  km");
            Utils.setCarResidu(this.carImageView, str);
            setTextRed(str);
        }
        String str4 = dataString4;
        str = dataString2;
        str2 = dataString;
        str3 = str4;
        this.tv_time.setText(str3 + "  min");
        this.tv_speed.setText(dataString5 + "  km/h");
        this.tv_oil.setText(str2 + "  L/100km");
        this.tv_mileage.setText(dataString3 + "  km");
        this.tv_residu.setText(str + "  km");
        Utils.setCarResidu(this.carImageView, str);
        setTextRed(str);
    }

    private void setTextRed(String str) {
        try {
            if (Integer.parseInt(str) <= 40) {
                this.tv_residu.setTextColor(SupportMenu.CATEGORY_MASK);
                this.rediduImage.setImageResource(R.drawable.dz_vehicle_information_bottom_icon_oil_r);
            } else {
                this.tv_residu.setTextColor(-1);
                this.rediduImage.setImageResource(R.drawable.dz_vehicle_information_bottom_icon_oil);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getDataString(String str, String str2) {
        if (!str2.equals("0")) {
            SharePreUtil.setStringValue(this.mContext, str, str2);
            return str2;
        }
        return SharePreUtil.getStringValue(this.mContext, str, str2);
    }
}
