package com.hzbhd.canbus.car_cus._277.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.util.CommUtil;

/* loaded from: classes2.dex */
public class EnergyFeedbackActivity extends AbstractBaseActivity implements View.OnClickListener {
    private static boolean mIsDowntime = false;
    private static int mStrength = 50;
    private final int STRENGTH_STEP = 10;
    private ImageView mEnergyBar;
    private ImageButton mEnergyDecrease;
    private Button mEnergyDowntime;
    private ImageButton mEnergyIncrease;
    private TextView mEnergyText;

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout._277_activity_energy_feedback);
        findViews();
        initViews();
    }

    private void findViews() {
        this.mEnergyBar = (ImageView) findViewById(R.id.iv_energy_bar);
        this.mEnergyText = (TextView) findViewById(R.id.tv_energy_text);
        this.mEnergyDecrease = (ImageButton) findViewById(R.id.ib_energy_decrease);
        this.mEnergyIncrease = (ImageButton) findViewById(R.id.ib_energy_increase);
        this.mEnergyDowntime = (Button) findViewById(R.id.btn_energy_downtime);
    }

    private void initViews() {
        this.mEnergyDecrease.setOnClickListener(this);
        this.mEnergyIncrease.setOnClickListener(this);
        this.mEnergyDowntime.setOnClickListener(this);
        refreshUi(null);
    }

    @Override // com.hzbhd.canbus.interfaces.MgrRefreshUiInterface
    public void refreshUi(Bundle bundle) {
        if (isShouldRefreshUi()) {
            dataProcessing();
            refreshEnergyText();
            refreshEnergyBar();
            refreshENergyDowntime();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_energy_downtime /* 2131362021 */:
                mIsDowntime = !mIsDowntime;
                break;
            case R.id.ib_energy_decrease /* 2131362390 */:
                int i = mStrength - 10;
                mStrength = i;
                if (i < 0) {
                    mStrength = 0;
                    break;
                }
                break;
            case R.id.ib_energy_increase /* 2131362391 */:
                int i2 = mStrength + 10;
                mStrength = i2;
                if (i2 > 100) {
                    mStrength = 100;
                    break;
                }
                break;
        }
        refreshUi(null);
    }

    private void dataProcessing() {
        if (mStrength < 0) {
            mStrength = 0;
        }
        if (mStrength > 100) {
            mStrength = 100;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, 60, 0, (byte) mStrength, mIsDowntime ? (byte) 1 : (byte) 0, 0, 0});
    }

    private void refreshEnergyText() {
        this.mEnergyText.setText(mStrength + "%");
    }

    private void refreshEnergyBar() {
        if (mStrength - 1 >= 0) {
            this.mEnergyBar.setVisibility(0);
            this.mEnergyBar.setImageResource(CommUtil.getImgIdByResId(this, "energy_feedback_strength_" + (((mStrength - 1) / 10) + 1)));
            return;
        }
        this.mEnergyBar.setVisibility(4);
    }

    private void refreshENergyDowntime() {
        this.mEnergyDowntime.setText(mIsDowntime ? R.string.geely_e6_energy_item_3C_d2_b0_1 : R.string.geely_e6_energy_item_3C_d2_b0_0);
    }
}
