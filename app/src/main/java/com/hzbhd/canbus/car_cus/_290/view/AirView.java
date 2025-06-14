package com.hzbhd.canbus.car_cus._290.view;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._290.GeneralCwData;
import com.hzbhd.canbus.car_cus._290.MessageSender;
import com.hzbhd.canbus.car_cus._290.adapter.TemLvAdapter;

/* loaded from: classes2.dex */
public class AirView extends RelativeLayout implements View.OnClickListener, ViewInterface {
    private static final String TAG = "AirView";
    private BtnView mBtnBottom0;
    private BtnView mBtnBottom1;
    private BtnView mBtnBottom2;
    private BtnView mBtnBottom3;
    private BtnView mBtnBottom4;
    private BtnView mBtnBottom5;
    private BtnView mBtnCenter;
    private BtnView mBtnLeft0;
    private BtnView mBtnLeft1;
    private BtnView mBtnLeft2;
    private BtnView mBtnLeft3;
    private BtnView mBtnRight0;
    private BtnView mBtnRight1;
    private BtnView mBtnRight2;
    private BtnView mBtnRight3;
    private BtnView mBtnViewTemperatureAdd;
    private BtnView mBtnViewTemperatureSub;
    private BtnView mBtnWindPowerAdd;
    private BtnView mBtnWindPowerSub;
    private Context mContext;
    private ImageView mIvWindPower;
    private RecyclerView mRv;
    private int[] mTemArray;
    private TemLvAdapter mTemLvAdpter;
    private TextView mTvError;
    private View mView;

    @Override // com.hzbhd.canbus.car_cus._290.view.ViewInterface
    public void onDestroy() {
    }

    public AirView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        this.mView = LayoutInflater.from(context).inflate(R.layout._290_view_ac, this);
        findViews();
        initViews();
    }

    @Override // com.hzbhd.canbus.car_cus._290.view.ViewInterface
    public void refreshUi(Bundle bundle) {
        this.mBtnLeft0.setSelect(GeneralCwData.air_sb);
        this.mBtnLeft1.setSelect(GeneralCwData.air_dh);
        this.mBtnLeft2.setSelect(GeneralCwData.air_sj);
        this.mBtnLeft3.setSelect(GeneralCwData.air_ck);
        this.mBtnRight0.setSelect(GeneralCwData.air_a);
        this.mBtnRight1.setSelect(GeneralCwData.air_b);
        this.mBtnRight2.setSelect(GeneralCwData.air_hfw);
        this.mBtnRight3.setSelect(GeneralCwData.air_hfn);
        this.mBtnBottom0.setSelect(GeneralCwData.air_auto);
        this.mBtnBottom1.setSelect(GeneralCwData.air_cold);
        this.mBtnBottom2.setSelect(GeneralCwData.air_light);
        this.mBtnBottom3.setSelect(GeneralCwData.air_wind);
        if (GeneralCwData.air_in_out) {
            this.mBtnBottom4.setSelectIcon(R.drawable.cw_kt02_base_ic_loopout_p);
            this.mBtnBottom4.setNoSelectIcon(R.drawable.cw_kt02_base_ic_loopout_n);
        } else {
            this.mBtnBottom4.setSelectIcon(R.drawable.cw_kt02_base_ic_loopin_p);
            this.mBtnBottom4.setNoSelectIcon(R.drawable.cw_kt02_base_ic_loopin_n);
        }
        this.mBtnBottom5.setSelect(GeneralCwData.air_window_wind);
        this.mBtnCenter.setSelect(GeneralCwData.air_power);
        if (GeneralCwData.air_temperature != 0) {
            selectTemperature();
        }
        selectWindPower();
        if (!TextUtils.isEmpty(GeneralCwData.error)) {
            this.mTvError.setText("Err  " + GeneralCwData.error);
        } else {
            this.mTvError.setText("");
        }
    }

    private void findViews() {
        this.mRv = (RecyclerView) this.mView.findViewById(R.id.rv_tem);
        this.mTvError = (TextView) this.mView.findViewById(R.id.tv_error);
        this.mBtnLeft0 = (BtnView) this.mView.findViewById(R.id.cbv_left_btn_0);
        this.mBtnLeft1 = (BtnView) this.mView.findViewById(R.id.cbv_left_btn_1);
        this.mBtnLeft2 = (BtnView) this.mView.findViewById(R.id.cbv_left_btn_2);
        this.mBtnLeft3 = (BtnView) this.mView.findViewById(R.id.cbv_left_btn_3);
        this.mBtnRight0 = (BtnView) this.mView.findViewById(R.id.cbv_right_btn_0);
        this.mBtnRight1 = (BtnView) this.mView.findViewById(R.id.cbv_right_btn_1);
        this.mBtnRight2 = (BtnView) this.mView.findViewById(R.id.cbv_right_btn_2);
        this.mBtnRight3 = (BtnView) this.mView.findViewById(R.id.cbv_right_btn_3);
        this.mBtnBottom0 = (BtnView) this.mView.findViewById(R.id.cbv_bottom_btn_0);
        this.mBtnBottom1 = (BtnView) this.mView.findViewById(R.id.cbv_bottom_btn_1);
        this.mBtnBottom2 = (BtnView) this.mView.findViewById(R.id.cbv_bottom_btn_2);
        this.mBtnBottom3 = (BtnView) this.mView.findViewById(R.id.cbv_bottom_btn_3);
        this.mBtnBottom4 = (BtnView) this.mView.findViewById(R.id.cbv_bottom_btn_4);
        this.mBtnBottom5 = (BtnView) this.mView.findViewById(R.id.cbv_bottom_btn_5);
        this.mBtnCenter = (BtnView) this.mView.findViewById(R.id.iv_center);
        this.mBtnViewTemperatureAdd = (BtnView) this.mView.findViewById(R.id.cbv_temperature_add);
        this.mBtnViewTemperatureSub = (BtnView) this.mView.findViewById(R.id.cbv_temperature_sub);
        this.mBtnWindPowerAdd = (BtnView) this.mView.findViewById(R.id.cbv_wind_power_add);
        this.mBtnWindPowerSub = (BtnView) this.mView.findViewById(R.id.cbv_wind_power_sub);
        this.mIvWindPower = (ImageView) this.mView.findViewById(R.id.iv_wind_power);
        this.mBtnLeft0.setOnClickListener(this);
        this.mBtnLeft1.setOnClickListener(this);
        this.mBtnLeft2.setOnClickListener(this);
        this.mBtnLeft3.setOnClickListener(this);
        this.mBtnRight0.setOnClickListener(this);
        this.mBtnRight1.setOnClickListener(this);
        this.mBtnRight2.setOnClickListener(this);
        this.mBtnRight3.setOnClickListener(this);
        this.mBtnBottom0.setOnClickListener(this);
        this.mBtnBottom1.setOnClickListener(this);
        this.mBtnBottom2.setOnClickListener(this);
        this.mBtnBottom3.setOnClickListener(this);
        this.mBtnBottom4.setOnClickListener(this);
        this.mBtnBottom5.setOnClickListener(this);
        this.mBtnCenter.setOnClickListener(this);
        this.mBtnViewTemperatureAdd.setOnClickListener(this);
        this.mBtnViewTemperatureSub.setOnClickListener(this);
        this.mBtnWindPowerAdd.setOnClickListener(this);
        this.mBtnWindPowerSub.setOnClickListener(this);
    }

    private void initViews() {
        this.mTemArray = new int[7];
        this.mTemLvAdpter = new TemLvAdapter(this.mContext, this.mTemArray);
        this.mRv.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.mRv.setAdapter(this.mTemLvAdpter);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id != R.id.iv_center) {
            switch (id) {
                case R.id.cbv_bottom_btn_0 /* 2131362110 */:
                    this.mBtnBottom0.setSelect(!r13.isSelect());
                    GeneralCwData.air_auto = this.mBtnBottom0.isSelect();
                    msgSender(4, 0);
                    break;
                case R.id.cbv_bottom_btn_1 /* 2131362111 */:
                    this.mBtnBottom1.setSelect(!r13.isSelect());
                    GeneralCwData.air_cold = this.mBtnBottom1.isSelect();
                    msgSender(1, 0);
                    break;
                case R.id.cbv_bottom_btn_2 /* 2131362112 */:
                    this.mBtnBottom2.setSelect(!r13.isSelect());
                    GeneralCwData.air_light = this.mBtnBottom2.isSelect();
                    msgSender(2, 0);
                    break;
                case R.id.cbv_bottom_btn_3 /* 2131362113 */:
                    this.mBtnBottom3.setSelect(!r13.isSelect());
                    GeneralCwData.air_wind = this.mBtnBottom3.isSelect();
                    msgSender(0, 2);
                    break;
                case R.id.cbv_bottom_btn_4 /* 2131362114 */:
                    if (GeneralCwData.air_in_out) {
                        this.mBtnBottom4.setSelectIcon(R.drawable.cw_kt02_base_ic_loopout_p);
                        this.mBtnBottom4.setNoSelectIcon(R.drawable.cw_kt02_base_ic_loopout_n);
                        GeneralCwData.air_in_out = false;
                        break;
                    } else {
                        this.mBtnBottom4.setSelectIcon(R.drawable.cw_kt02_base_ic_loopin_p);
                        this.mBtnBottom4.setNoSelectIcon(R.drawable.cw_kt02_base_ic_loopin_n);
                        GeneralCwData.air_in_out = true;
                        break;
                    }
                case R.id.cbv_bottom_btn_5 /* 2131362115 */:
                    this.mBtnBottom5.setSelect(!r13.isSelect());
                    GeneralCwData.air_window_wind = this.mBtnBottom5.isSelect();
                    msgSender(2, 2);
                    break;
                case R.id.cbv_left_btn_0 /* 2131362116 */:
                    if (!this.mBtnLeft1.isSelect()) {
                        this.mBtnLeft0.setSelect(!r13.isSelect());
                        GeneralCwData.air_sb = this.mBtnLeft0.isSelect();
                        MessageSender.showCommonSwitch(6, 4, this.mBtnLeft0.isSelect());
                        break;
                    }
                    break;
                case R.id.cbv_left_btn_1 /* 2131362117 */:
                    if (this.mBtnLeft0.isSelect()) {
                        this.mBtnLeft1.setSelect(!r13.isSelect());
                        GeneralCwData.air_dh = this.mBtnLeft1.isSelect();
                        MessageSender.showCommonSwitch(6, 6, this.mBtnLeft1.isSelect());
                        break;
                    }
                    break;
                case R.id.cbv_left_btn_2 /* 2131362118 */:
                    this.mBtnLeft2.setSelect(!r13.isSelect());
                    GeneralCwData.air_sj = this.mBtnLeft2.isSelect();
                    MessageSender.showCommonSwitch(7, 4, this.mBtnLeft2.isSelect());
                    break;
                case R.id.cbv_left_btn_3 /* 2131362119 */:
                    this.mBtnLeft3.setSelect(!r13.isSelect());
                    GeneralCwData.air_ck = this.mBtnLeft3.isSelect();
                    MessageSender.showCommonSwitch(7, 6, this.mBtnLeft3.isSelect());
                    break;
                case R.id.cbv_right_btn_0 /* 2131362120 */:
                    if (!this.mBtnRight1.isSelect()) {
                        this.mBtnRight0.setSelect(!r13.isSelect());
                        GeneralCwData.air_a = this.mBtnRight0.isSelect();
                        MessageSender.showCommonSwitch(6, 0, this.mBtnRight0.isSelect());
                        break;
                    }
                    break;
                case R.id.cbv_right_btn_1 /* 2131362121 */:
                    if (this.mBtnRight0.isSelect()) {
                        this.mBtnRight1.setSelect(!r13.isSelect());
                        GeneralCwData.air_b = this.mBtnRight1.isSelect();
                        MessageSender.showCommonSwitch(6, 2, this.mBtnRight1.isSelect());
                        break;
                    }
                    break;
                case R.id.cbv_right_btn_2 /* 2131362122 */:
                    this.mBtnRight2.setSelect(!r13.isSelect());
                    GeneralCwData.air_hfw = this.mBtnRight2.isSelect();
                    MessageSender.showCommonSwitch(7, 0, this.mBtnRight2.isSelect());
                    break;
                case R.id.cbv_right_btn_3 /* 2131362123 */:
                    this.mBtnRight3.setSelect(!r13.isSelect());
                    GeneralCwData.air_hfn = this.mBtnRight3.isSelect();
                    MessageSender.showCommonSwitch(7, 2, this.mBtnRight3.isSelect());
                    break;
                case R.id.cbv_temperature_add /* 2131362124 */:
                    int i = GeneralCwData.air_temperature + 1;
                    if (i <= 52 && i >= 16) {
                        GeneralCwData.air_temperature++;
                        Log.w(TAG, "---------发送温度can---------------->" + GeneralCwData.air_temperature);
                        msgSender(3, 0);
                        selectTemperature();
                        break;
                    }
                    break;
                case R.id.cbv_temperature_sub /* 2131362125 */:
                    int i2 = GeneralCwData.air_temperature - 1;
                    if (i2 <= 52 && i2 >= 16) {
                        GeneralCwData.air_temperature--;
                        Log.w(TAG, "---------发送温度can---------------->" + GeneralCwData.air_temperature);
                        msgSender(3, 2);
                        selectTemperature();
                        break;
                    }
                    break;
                case R.id.cbv_wind_power_add /* 2131362126 */:
                    int i3 = GeneralCwData.air_wind_power + 1;
                    if (i3 <= 7 && i3 >= 0) {
                        GeneralCwData.air_wind_power++;
                        Log.w(TAG, "---------发送风量can---------------->" + GeneralCwData.air_wind_power);
                        msgSender(0, 4);
                        selectWindPower();
                        break;
                    }
                    break;
                case R.id.cbv_wind_power_sub /* 2131362127 */:
                    int i4 = GeneralCwData.air_wind_power - 1;
                    if (i4 <= 7 && i4 >= 0) {
                        GeneralCwData.air_wind_power--;
                        Log.w(TAG, "---------发送风量can---------------->" + GeneralCwData.air_wind_power);
                        msgSender(0, 6);
                        selectWindPower();
                        break;
                    }
                    break;
            }
            return;
        }
        this.mBtnCenter.setSelect(!r13.isSelect());
        GeneralCwData.air_power = this.mBtnCenter.isSelect();
        msgSender(0, 0);
    }

    private void msgSender(int i, int i2) {
        MessageSender.showAirSender(i, i2, true);
        MessageSender.showAirSender(i, i2, false);
    }

    private void selectWindPower() {
        switch (GeneralCwData.air_wind_power) {
            case 0:
                this.mIvWindPower.setImageResource(R.drawable.cw_kt02_mid_img_wind0);
                break;
            case 1:
                this.mIvWindPower.setImageResource(R.drawable.cw_kt02_mid_img_wind1);
                break;
            case 2:
                this.mIvWindPower.setImageResource(R.drawable.cw_kt02_mid_img_wind2);
                break;
            case 3:
                this.mIvWindPower.setImageResource(R.drawable.cw_kt02_mid_img_wind3);
                break;
            case 4:
                this.mIvWindPower.setImageResource(R.drawable.cw_kt02_mid_img_wind4);
                break;
            case 5:
                this.mIvWindPower.setImageResource(R.drawable.cw_kt02_mid_img_wind5);
                break;
            case 6:
                this.mIvWindPower.setImageResource(R.drawable.cw_kt02_mid_img_wind6);
                break;
            case 7:
                this.mIvWindPower.setImageResource(R.drawable.cw_kt02_mid_img_wind7);
                break;
        }
    }

    private void selectTemperature() {
        this.mTemArray = getTemperatureList(GeneralCwData.air_temperature);
        this.mTemLvAdpter.notifyDataSetChanged();
    }

    private int[] getTemperatureList(int i) {
        int length = this.mTemArray.length / 2;
        int i2 = 0;
        while (true) {
            int[] iArr = this.mTemArray;
            if (i2 >= iArr.length) {
                return iArr;
            }
            iArr[i2] = (i + length) - i2;
            i2++;
        }
    }
}
