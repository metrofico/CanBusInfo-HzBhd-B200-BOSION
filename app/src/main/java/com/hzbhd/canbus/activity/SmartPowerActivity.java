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
import com.hzbhd.R;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.smartpower.GeneralDzSmartData;
import com.hzbhd.canbus.smartpower.SmartPowerManager;
import com.hzbhd.canbus.smartpower.SmartPowerMsgMgr;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.view.NumberInputView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
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
    protected List<RadioButton> radioButtons = new ArrayList();
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
            public final void onDataChange(byte[] bArr) {
                this.f$0.m29lambda$onCreate$0$comhzbhdcanbusactivitySmartPowerActivity(bArr);
            }
        });
    }

    /* renamed from: lambda$onCreate$0$com-hzbhd-canbus-activity-SmartPowerActivity, reason: not valid java name */
    /* synthetic */ void m29lambda$onCreate$0$comhzbhdcanbusactivitySmartPowerActivity(byte[] bArr) {
        SmartPowerMsgMgr.getInstance().canbusInfoChange(this.mContext, bArr, new SmartPowerMsgMgr.OnRefreshUiListener() { // from class: com.hzbhd.canbus.activity.SmartPowerActivity$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.smartpower.SmartPowerMsgMgr.OnRefreshUiListener
            public final void onRefreshUi() {
                this.f$0.refreshUi();
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
        this.seekBar = (SeekBar) findViewById(R.id.seekBar);
        this.bottomRelative = (RelativeLayout) findViewById(R.id.bottomRelative);
        this.radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        this.radioButtonComfort = (RadioButton) findViewById(R.id.radioButton_comfort);
        this.radioButtonOriginal = (RadioButton) findViewById(R.id.radioButton_original);
        this.radioButtonSport = (RadioButton) findViewById(R.id.radioButton_sport);
        this.radioButtonEconomics = (RadioButton) findViewById(R.id.radioButton_economics);
        this.radioButtonTrack = (RadioButton) findViewById(R.id.radioButton_track);
        this.mVersion = (TextView) findViewById(R.id.tv_version);
        this.btnChooseVersion = (Button) findViewById(R.id.btn_chooseVersion);
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
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                this.f$0.m27lambda$initData$1$comhzbhdcanbusactivitySmartPowerActivity(radioGroup, i);
            }
        });
        this.seekBar.setOnSeekBarChangeListener(this);
        this.btnChooseVersion.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.activity.SmartPowerActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m28lambda$initData$3$comhzbhdcanbusactivitySmartPowerActivity(view);
            }
        });
    }

    /* renamed from: lambda$initData$1$com-hzbhd-canbus-activity-SmartPowerActivity, reason: not valid java name */
    /* synthetic */ void m27lambda$initData$1$comhzbhdcanbusactivitySmartPowerActivity(RadioGroup radioGroup, int i) {
        int i2 = 0;
        switch (i) {
            case R.id.radioButton_comfort /* 2131363029 */:
                i2 = 2;
                break;
            case R.id.radioButton_original /* 2131363033 */:
                i2 = 1;
                break;
            case R.id.radioButton_sport /* 2131363035 */:
                i2 = 3;
                break;
            case R.id.radioButton_track /* 2131363037 */:
                i2 = 4;
                break;
        }
        if (this.isClick) {
            sendDzMsg(i2, GeneralDzSmartData.data3, GeneralDzSmartData.data4, GeneralDzSmartData.data5, GeneralDzSmartData.data6);
        }
    }

    /* renamed from: lambda$initData$3$com-hzbhd-canbus-activity-SmartPowerActivity, reason: not valid java name */
    /* synthetic */ void m28lambda$initData$3$comhzbhdcanbusactivitySmartPowerActivity(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.SmartDialog);
        NumberInputView numberInputView = new NumberInputView(this);
        builder.setView(numberInputView);
        final AlertDialog alertDialogCreate = builder.create();
        alertDialogCreate.show();
        numberInputView.setOnOkClickListener(true, "2691", new NumberInputView.OnOkClickListener() { // from class: com.hzbhd.canbus.activity.SmartPowerActivity$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.view.NumberInputView.OnOkClickListener
            public final void click(String str) throws NumberFormatException {
                SmartPowerActivity.lambda$initData$2(alertDialogCreate, str);
            }
        });
    }

    static /* synthetic */ void lambda$initData$2(AlertDialog alertDialog, String str) throws NumberFormatException {
        int i = Integer.parseInt(str);
        sendDzMsg(21, i % 256, i / 256, GeneralDzSmartData.data5, GeneralDzSmartData.data6);
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
            this.bottomRelative.setVisibility(8);
        } else {
            this.bottomRelative.setVisibility(0);
        }
        this.isClick = true;
        if (GeneralDzSmartData.data7_3) {
            Toast.makeText(this, R.string.smart_input_sigal_fail, 0).show();
        }
        if (GeneralDzSmartData.data7_4) {
            Toast.makeText(this, R.string.smart_output_sigal_fail, 0).show();
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
        boolean z = (i2 == 3 || i2 == 4) ? false : true;
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
