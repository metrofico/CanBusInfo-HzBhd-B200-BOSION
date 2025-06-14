package com.hzbhd.canbus.media.aux2.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.canCustom.canBaseUtil.StatusUtil;
import com.hzbhd.canbus.media.aux2.action.ActionCallback;
import com.hzbhd.canbus.media.aux2.action.ActionTag;
import com.hzbhd.canbus.media.aux2.manager.Aux2Manager;
import com.hzbhd.canbus.media.aux2.view.Aux2CameraSurfaceView;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.proxy.sourcemanager.SourceManager;

/* loaded from: classes2.dex */
public class Aux2Activity extends AppCompatActivity {
    public static final String SCALE_X_TAG = "0xFFFF";
    public static final String SCALE_Y_TAG = "0xDDDD";
    ActionCallback actionCallback = new ActionCallback<ActionTag.TAG, String>() { // from class: com.hzbhd.canbus.media.aux2.activity.Aux2Activity.1
        @Override // com.hzbhd.canbus.media.aux2.action.ActionCallback
        public void actionDo(ActionTag.TAG tag, String str) {
            if (tag.equals(ActionTag.TAG.EXIT)) {
                Aux2Activity.this.finish();
            }
        }
    };

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        StatusUtil.fullScreen(this, true);
        setContentView(R.layout.activity_aux2_media);
        Intent intent = getIntent();
        initView(intent.getIntExtra(SCALE_X_TAG, 1280), intent.getIntExtra(SCALE_Y_TAG, 720));
        open();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        CanbusMsgSender.sendMsg(new byte[]{22, -110, 1, 0});
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        close();
        CanbusMsgSender.sendMsg(new byte[]{22, -110, 0, 0});
    }

    public void open() {
        SourceManager.getInstance().requestAudioChannel(SourceConstantsDef.SOURCE_ID.AUX2, SourceConstantsDef.DISP_ID.disp_main, null);
        Aux2Manager.getInstance().registerExitListener(this.actionCallback);
    }

    public void close() {
        SourceManager.getInstance().releaseAudioChannel(SourceConstantsDef.SOURCE_ID.AUX2, SourceConstantsDef.DISP_ID.disp_main);
        Aux2Manager.getInstance().unRegisterExitListener();
    }

    private void initView(int i, int i2) {
        Aux2CameraSurfaceView aux2CameraSurfaceView = (Aux2CameraSurfaceView) findViewById(R.id.video_sur);
        int i3 = Resources.getSystem().getDisplayMetrics().heightPixels;
        int i4 = Resources.getSystem().getDisplayMetrics().widthPixels;
        float f = i * 0.1f;
        float f2 = i2 * 0.1f;
        if (i3 > i4) {
            float f3 = i4 / f;
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) aux2CameraSurfaceView.getLayoutParams();
            layoutParams.height = (int) (f2 * f3);
            layoutParams.width = i4;
            aux2CameraSurfaceView.setLayoutParams(layoutParams);
            return;
        }
        float f4 = i3 / f2;
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) aux2CameraSurfaceView.getLayoutParams();
        layoutParams2.height = i3;
        layoutParams2.width = (int) (f * f4);
        aux2CameraSurfaceView.setLayoutParams(layoutParams2);
    }
}
