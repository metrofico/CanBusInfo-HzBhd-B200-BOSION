package com.hzbhd.canbus.car_cus._283.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.canbus.car_cus._283.MessageSender;

/* loaded from: classes2.dex */
public class AirCleanActivity extends AbstractBaseActivity implements View.OnClickListener {
    private int[] airCleanImages = {R.drawable.dz_air_purification_1, R.drawable.dz_air_purification_2, R.drawable.dz_air_purification_3, R.drawable.dz_air_purification_4, R.drawable.dz_air_purification_5, R.drawable.dz_air_purification_6, R.drawable.dz_air_purification_7, R.drawable.dz_air_purification_8, R.drawable.dz_air_purification_9, R.drawable.dz_air_purification_10};
    private ImageView iv_back;
    private ImageView iv_click_isSelect;
    private ImageView iv_progress;
    private ImageView tv_isSelect;
    private TextView tv_tips;

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout._283_activity_air_clean);
        getWindow().setFlags(1024, 1024);
        initView();
    }

    private void initView() {
        this.iv_click_isSelect = (ImageView) findViewById(R.id.iv_click_isSelect);
        this.tv_isSelect = (ImageView) findViewById(R.id.tv_isSelect);
        this.iv_back = (ImageView) findViewById(R.id.iv_back);
        this.iv_progress = (ImageView) findViewById(R.id.iv_progress);
        this.tv_tips = (TextView) findViewById(R.id.tv_tips);
        this.iv_back.setOnClickListener(this);
        this.iv_click_isSelect.setOnClickListener(this);
    }

    @Override // com.hzbhd.canbus.interfaces.MgrRefreshUiInterface
    public void refreshUi(Bundle bundle) {
        if (GeneralDzData.air_clean) {
            this.tv_isSelect.setImageResource(R.drawable.dz_radio_set_list_selected);
            this.tv_isSelect.setTag("true");
        } else {
            this.tv_isSelect.setImageResource(R.drawable.dz_radio_set_list_not_selected);
            this.tv_isSelect.setTag("false");
        }
        if (GeneralDzData.air_clean) {
            int i = GeneralDzData.air_clean_progress;
            if (i == 0) {
                this.iv_progress.setImageDrawable(null);
                this.tv_tips.setText(getString(R.string._283_air_clean_door_open));
                return;
            } else if (i >= 1 && i <= 10) {
                this.iv_progress.setImageResource(this.airCleanImages[i - 1]);
                this.tv_tips.setText(getString(R.string._283_air_clean));
                return;
            } else {
                if (i == 15) {
                    this.iv_progress.setImageDrawable(null);
                    this.tv_tips.setText(getString(R.string._283_air_clean_none));
                    return;
                }
                return;
            }
        }
        this.iv_progress.setImageDrawable(null);
        this.tv_tips.setText(getString(R.string._283_air_clean_tips1));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_back) {
            onBackPressed();
        } else {
            if (id != R.id.iv_click_isSelect) {
                return;
            }
            if ("true".equals(this.tv_isSelect.getTag())) {
                MessageSender.sendMsg(new byte[]{22, 58, 32, 0});
            } else {
                MessageSender.sendMsg(new byte[]{22, 58, 32, 1});
            }
        }
    }

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        MessageSender.sendMsg(new byte[]{22, 58, 32, 0});
    }
}
