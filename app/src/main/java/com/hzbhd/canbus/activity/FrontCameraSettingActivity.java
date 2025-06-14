package com.hzbhd.canbus.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hzbhd.R;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.util.SharePreUtil;

/* loaded from: classes.dex */
public class FrontCameraSettingActivity extends AppCompatActivity {
    @Override
    // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_front_camera_setting);
        initView();
    }

    private void initView() {
        final TextView textView = (TextView) findViewById(R.id.is_open_F_Camera_textview);
        if (SharePreUtil.getBoolValue(this, Constant.SHARE_IS_OPEN_FRONT_CAMERA, false)) {
            textView.setText(R.string.Factory_f_camera_str2);
        } else {
            textView.setText(R.string.Factory_f_camera_str3);
        }
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.activity.FrontCameraSettingActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (SharePreUtil.getBoolValue(FrontCameraSettingActivity.this, Constant.SHARE_IS_OPEN_FRONT_CAMERA, false)) {
                    SharePreUtil.setBoolValue(FrontCameraSettingActivity.this, Constant.SHARE_IS_OPEN_FRONT_CAMERA, false);
                    textView.setText(R.string.Factory_f_camera_str3);
                } else {
                    SharePreUtil.setBoolValue(FrontCameraSettingActivity.this, Constant.SHARE_IS_OPEN_FRONT_CAMERA, true);
                    textView.setText(R.string.Factory_f_camera_str2);
                }
            }
        });
    }
}
