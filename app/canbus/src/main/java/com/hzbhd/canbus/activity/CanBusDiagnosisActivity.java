package com.hzbhd.canbus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.util.OpenCanBusBuglyView;

/* loaded from: classes.dex */
public class CanBusDiagnosisActivity extends AppCompatActivity {
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_can_bus_diagnosis);
        ((TextView) findViewById(R.id.bugly)).setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.activity.CanBusDiagnosisActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                new OpenCanBusBuglyView().showDialog(CanBusDiagnosisActivity.this);
            }
        });
        ((TextView) findViewById(R.id.can_test)).setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.activity.CanBusDiagnosisActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CanBusDiagnosisActivity.this.startActivity(new Intent(CanBusDiagnosisActivity.this, (Class<?>) TestCanBusActivity.class));
            }
        });
    }
}
