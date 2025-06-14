package com.hzbhd.canbus.car_cus._439.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import com.hzbhd.canbus.R;

/* loaded from: classes2.dex */
public class ID439MainActivity extends Activity {
    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_id439_main);
        final ImageView imageView = (ImageView) findViewById(R.id.drive);
        imageView.setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.car_cus._439.activity.ID439MainActivity.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    imageView.setBackgroundResource(R.drawable.__439__shuju_p);
                    return true;
                }
                if (motionEvent.getAction() != 1) {
                    return false;
                }
                imageView.setBackgroundResource(R.drawable.__439__shuju_n);
                ID439MainActivity.this.startActivity(new Intent(ID439MainActivity.this, (Class<?>) ID439DriveDataActivity.class));
                return true;
            }
        });
        final ImageView imageView2 = (ImageView) findViewById(R.id.setting);
        imageView2.setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.car_cus._439.activity.ID439MainActivity.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    imageView2.setBackgroundResource(R.drawable.__439__shewzhi_p);
                    return true;
                }
                if (motionEvent.getAction() != 1) {
                    return false;
                }
                imageView2.setBackgroundResource(R.drawable.__439__shewzhi_n);
                ID439MainActivity.this.startActivity(new Intent(ID439MainActivity.this, (Class<?>) ID439SettingActivity.class));
                return true;
            }
        });
    }
}
