package com.hzbhd.canbus.car._324;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.util.DataHandleUtils;


public class Id324AuxVideoActivity extends AppCompatActivity {
    private VideoInfoView videoInfoView;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_id324_aux_video);
        VideoInfoView videoInfoView = (VideoInfoView) findViewById(R.id.videoView);
        this.videoInfoView = videoInfoView;
        videoInfoView.startVideoView();
        findViewById(R.id.xyView).setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.car._324.Id324AuxVideoActivity.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    Id324AuxVideoActivity.this.sendXy((int) motionEvent.getX(), (int) motionEvent.getY(), 1);
                    return true;
                }
                if (motionEvent.getAction() != 1) {
                    return false;
                }
                Id324AuxVideoActivity.this.sendXy((int) motionEvent.getX(), (int) motionEvent.getY(), 0);
                return true;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendXy(int i, int i2, int i3) {
        CanbusMsgSender.sendMsg(new byte[]{22, -104, (byte) i3, (byte) DataHandleUtils.getMsb(i), (byte) DataHandleUtils.getLsb(i), (byte) DataHandleUtils.getMsb(i2), (byte) DataHandleUtils.getLsb(i2), 0});
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        this.videoInfoView.startVideoView();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.videoInfoView.stopVideoView();
    }
}
