package com.hzbhd.canbus.car_cus._439.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car_cus._439.drive.util.NotifyDriveDate;
import com.hzbhd.canbus.car_cus._439.drive.viiew.DriveDateView;

/* loaded from: classes2.dex */
public class ID439DriveDataActivity extends AppCompatActivity {
    DriveDateView repair;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_id439_drive_date);
        this.repair = (DriveDateView) findViewById(R.id.repair);
        NotifyDriveDate.getInstance().register(this.repair);
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 33, 0});
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        NotifyDriveDate.getInstance().unregister(this.repair);
    }
}
