package com.hzbhd.canbus.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hzbhd.canbus.R;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.smartpower.GeneralDzSmartData;
import com.hzbhd.canbus.smartpower.SmartPowerManager;
import com.hzbhd.canbus.smartpower.SmartPowerMsgMgr;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.view.NumberInputView;

import java.util.ArrayList;
import java.util.List;


public class SmartPowerActivity extends Activity implements SeekBar.OnSeekBarChangeListener {
    protected RelativeLayout bottomRelative;
    protected Button btnChooseVersion;
    private Context mContext;
    protected TextView mVersion;
    protected RadioButton radioButtonComfort;
    protected RadioButton radioButtonEconomics;
    protected RadioButton radioButtonOriginal;
    protected RadioButton radioButtonSport;
    protected RadioButton radioButtonTrack;
    protected RadioGroup radioGroup;
    protected SeekBar seekBar;
    protected List<RadioButton> radioButtons = new ArrayList<>();
    protected boolean isClick = true;

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = this;
        setActivityContentView();
        initView();
        initData();
        SmartPowerManager.getInstance().addOnSmartPowerChangeListener(new SmartPowerManager.OnSmartPowerChangeListener() { // from class: com.hzbhd.canbus.activity.SmartPowerActivity$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.smartpower.SmartPowerManager.OnSmartPowerChangeListener
            public void onDataChange(byte[] bArr) {
                m29lambda$onCreate$0$comhzbhdcanbusactivitySmartPowerActivity(bArr);
            }
        });
    }

    void m29lambda$onCreate$0$comhzbhdcanbusactivitySmartPowerActivity(byte[] bArr) {
        SmartPowerMsgMgr.getInstance().canbusInfoChange(this.mContext, bArr, new SmartPowerMsgMgr.OnRefreshUiListener() { // from class: com.hzbhd.canbus.activity.SmartPowerActivity$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.smartpower.SmartPowerMsgMgr.OnRefreshUiListener
            public void onRefreshUi() {
                SmartPowerActivity.this.refreshUi();
            }
        });
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        this.mContext = null;
        super.onDestroy();
    }

    protected void setActivityContentView() {
        setContentView(R.layout.activity_smart_power);
    }

    private void initView() {
        this.seekBar = findViewById(R.id.seekBar);
        this.bottomRelative = findViewById(R.id.bottomRelative);
        this.radioGroup = findViewById(R.id.radioGroup);
        this.radioButtonComfort = findViewById(R.id.radioButton_comfort);
        this.radioButtonOriginal = findViewById(R.id.radioButton_original);
        this.radioButtonSport = findViewById(R.id.radioButton_sport);
        this.radioButtonEconomics = findViewById(R.id.radioButton_economics);
        this.radioButtonTrack = findViewById(R.id.radioButton_track);
        this.mVersion = findViewById(R.id.tv_version);
        this.btnChooseVersion = findViewById(R.id.btn_chooseVersion);
        setEnabled(false);
        this.radioButtons.add(this.radioButtonEconomics);
        this.radioButtons.add(this.radioButtonOriginal);
        this.radioButtons.add(this.radioButtonComfort);
        this.radioButtons.add(this.radioButtonSport);
        this.radioButtons.add(this.radioButtonTrack);
        if (GeneralDzSmartData.isHasData) {
            refreshUi();
        }
    }

    private void initData() {
        this.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.hzbhd.canbus.activity.SmartPowerActivity$$ExternalSyntheticLambda2
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                m27lambda$initData$1$comhzbhdcanbusactivitySmartPowerActivity(radioGroup, i);
            }
        });
        this.seekBar.setOnSeekBarChangeListener(this);
        this.btnChooseVersion.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.activity.SmartPowerActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                m28lambda$initData$3$comhzbhdcanbusactivitySmartPowerActivity(view);
            }
        });
    }

    private void m27lambda$initData$1$comhzbhdcanbusactivitySmartPowerActivity(RadioGroup radioGroup, int checkedId) {
        int mode = 0;
        if (checkedId == R.id.radioButton_comfort) {
            mode = 2;
        } else if (checkedId == R.id.radioButton_original) {
            mode = 1;
        } else if (checkedId == R.id.radioButton_sport) {
            mode = 3;
        } else if (checkedId == R.id.radioButton_track) {
            mode = 4;
        }
        if (this.isClick) {
            sendDzMsg(mode, GeneralDzSmartData.data3, GeneralDzSmartData.data4, GeneralDzSmartData.data5, GeneralDzSmartData.data6);
        }
    }

    private void m28lambda$initData$3$comhzbhdcanbusactivitySmartPowerActivity(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.SmartDialog);
        NumberInputView numberInputView = new NumberInputView(this);
        builder.setView(numberInputView);
        final AlertDialog alertDialogCreate = builder.create();
        alertDialogCreate.show();

        // Set listener for input validation
        numberInputView.setOnOkClickListener(true, "2691", new NumberInputView.OnOkClickListener() {
            @Override
            public void click(String str) throws NumberFormatException {
                lambda$initData$2(alertDialogCreate, str);
            }
        });
    }

    static void lambda$initData$2(AlertDialog alertDialog, String str) throws NumberFormatException {
        int value = Integer.parseInt(str);
        sendDzMsg(21, value % 256, value / 256, GeneralDzSmartData.data5, GeneralDzSmartData.data6);
        alertDialog.dismiss();
    }

    public void refreshUi() {
        setEnabled(true);
        this.isClick = false;
        scaleRadioButton(this.radioButtons.get(GeneralDzSmartData.mode));
        if (GeneralDzSmartData.mode_int >= 0) {
            this.seekBar.setProgress(GeneralDzSmartData.mode_int);
        }
        this.mVersion.setText(getVersionText(GeneralDzSmartData.version) + getPowerModel(GeneralDzSmartData.power_model));
        if (GeneralDzSmartData.mode == 1) {
            this.bottomRelative.setVisibility(View.GONE);
        } else {
            this.bottomRelative.setVisibility(View.VISIBLE);
        }
        this.isClick = true;
        if (GeneralDzSmartData.data7_3) {
            Toast.makeText(this, R.string.smart_input_sigal_fail, Toast.LENGTH_SHORT).show();
        }
        if (GeneralDzSmartData.data7_4) {
            Toast.makeText(this, R.string.smart_output_sigal_fail, Toast.LENGTH_SHORT).show();
        }
    }

    private void setEnabled(boolean z) {
        this.radioButtonEconomics.setEnabled(z);
        this.radioButtonOriginal.setEnabled(z);
        this.radioButtonComfort.setEnabled(z);
        this.radioButtonSport.setEnabled(z);
        this.radioButtonTrack.setEnabled(z);
        this.seekBar.setEnabled(z);
        this.btnChooseVersion.setEnabled(z);
    }

    private String getVersionText(int i) {
        if (i <= 0) {
            return "";
        }
        if (i < 10) {
            return "v0." + i;
        }
        return "v" + (i / 10) + "." + (i % 10);
    }

    private String getPowerModel(int i) {
        return (i != 2000 && i < 1000) ? "-" + i : "";
    }

    private void sendModeValue(int i) {
        int i2 = GeneralDzSmartData.mode;
        boolean z = i2 != 3 && i2 != 4;
        int i3 = (i2 == 3 || i2 == 0) ? 4 : 0;
        if (z) {
            sendDzMsg(i2, DataHandleUtils.setIntFromByteWithBit(GeneralDzSmartData.data3, i + 1, i3, 4), GeneralDzSmartData.data4, GeneralDzSmartData.data5, GeneralDzSmartData.data6);
        } else {
            sendDzMsg(i2, GeneralDzSmartData.data3, DataHandleUtils.setIntFromByteWithBit(GeneralDzSmartData.data4, i + 1, i3, 4), GeneralDzSmartData.data5, GeneralDzSmartData.data6);
        }
    }

    protected void scaleRadioButton(RadioButton radioButton) {
        for (int i = 0; i < this.radioButtons.size(); i++) {
            if (radioButton == this.radioButtons.get(i)) {
                radioButton.setChecked(true);
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) radioButton.getLayoutParams();
                marginLayoutParams.width = (int) getResources().getDimension(R.dimen.dp202);
                marginLayoutParams.height = (int) getResources().getDimension(R.dimen.dp241);
                radioButton.setLayoutParams(marginLayoutParams);
                radioButton.setPadding(0, 0, 0, (int) getResources().getDimension(R.dimen.dp16));
            } else {
                ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) this.radioButtons.get(i).getLayoutParams();
                marginLayoutParams2.width = (int) getResources().getDimension(R.dimen.dp174);
                marginLayoutParams2.height = (int) getResources().getDimension(R.dimen.dp207);
                this.radioButtons.get(i).setLayoutParams(marginLayoutParams2);
                this.radioButtons.get(i).setPadding(0, 0, 0, (int) getResources().getDimension(R.dimen.dp10));
            }
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (z) {
            sendModeValue(i);
        }
    }

    public static void sendDzMsg(int i, int i2, int i3, int i4, int i5) {
        FutureUtil.instance.reportSmartPowerInfo(new byte[]{(byte) i, (byte) i2, (byte) i3, (byte) i4, (byte) i5});
    }
}
