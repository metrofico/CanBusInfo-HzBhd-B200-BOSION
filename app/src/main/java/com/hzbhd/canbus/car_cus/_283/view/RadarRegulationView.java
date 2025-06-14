package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class RadarRegulationView extends RelativeLayout {
    private SeekBar colorSeekBar;
    private SeekBar contrastSeekBar;
    private boolean isFirstOpen;
    private SeekBar lightSeekBar;
    private Context mContext;
    private Handler mHandler;
    private View mView;
    private TextView tv_color;
    private TextView tv_contrast;
    private TextView tv_light;

    public RadarRegulationView(Context context) {
        this(context, null);
    }

    public RadarRegulationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RadarRegulationView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.isFirstOpen = true;
        this.mHandler = new Handler(Looper.getMainLooper()) { // from class: com.hzbhd.canbus.car_cus._283.view.RadarRegulationView.4
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what != 0) {
                    return;
                }
                RadarRegulationView.this.setVisibility(8);
            }
        };
        this.mView = LayoutInflater.from(context).inflate(R.layout._283_dialog_radar_regulation, (ViewGroup) this, true);
        this.mContext = context;
        initView();
    }

    private void initView() {
        this.tv_color = (TextView) this.mView.findViewById(R.id.tv_color);
        this.tv_contrast = (TextView) this.mView.findViewById(R.id.tv_contrast);
        this.tv_light = (TextView) this.mView.findViewById(R.id.tv_light);
        this.lightSeekBar = (SeekBar) this.mView.findViewById(R.id.lightSeekBar);
        this.contrastSeekBar = (SeekBar) this.mView.findViewById(R.id.contrastSeekBar);
        this.colorSeekBar = (SeekBar) this.mView.findViewById(R.id.colorSeekBar);
        this.lightSeekBar.setOnSeekBarChangeListener(new MySeekBarChangeListener() { // from class: com.hzbhd.canbus.car_cus._283.view.RadarRegulationView.1
            @Override // com.hzbhd.canbus.car_cus._283.view.RadarRegulationView.MySeekBarChangeListener, android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                RadarRegulationView.this.tv_light.setText(String.valueOf(i));
                MessageSender.sendMsg(new byte[]{22, -14, 10, (byte) i});
                RadarRegulationView.this.hideThis();
            }
        });
        this.contrastSeekBar.setOnSeekBarChangeListener(new MySeekBarChangeListener() { // from class: com.hzbhd.canbus.car_cus._283.view.RadarRegulationView.2
            @Override // com.hzbhd.canbus.car_cus._283.view.RadarRegulationView.MySeekBarChangeListener, android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                RadarRegulationView.this.tv_contrast.setText(String.valueOf(i));
                MessageSender.sendMsg(new byte[]{22, -14, 11, (byte) i});
                RadarRegulationView.this.hideThis();
            }
        });
        this.colorSeekBar.setOnSeekBarChangeListener(new MySeekBarChangeListener() { // from class: com.hzbhd.canbus.car_cus._283.view.RadarRegulationView.3
            @Override // com.hzbhd.canbus.car_cus._283.view.RadarRegulationView.MySeekBarChangeListener, android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                RadarRegulationView.this.tv_color.setText(String.valueOf(i));
                MessageSender.sendMsg(new byte[]{22, -14, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) i});
                RadarRegulationView.this.hideThis();
            }
        });
        hideThis();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideThis() {
        this.mHandler.removeMessages(0);
        this.mHandler.sendEmptyMessageDelayed(0, 3000L);
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        if (i == 0 && this.isFirstOpen) {
            this.tv_color.setText(String.valueOf(GeneralDzData.camera_color));
            this.tv_contrast.setText(String.valueOf(GeneralDzData.camera_contrast));
            this.tv_light.setText(String.valueOf(GeneralDzData.camera_light));
            this.lightSeekBar.setProgress(GeneralDzData.camera_light);
            this.contrastSeekBar.setProgress(GeneralDzData.camera_contrast);
            this.colorSeekBar.setProgress(GeneralDzData.camera_color);
            this.isFirstOpen = false;
        }
    }

    class MySeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {
        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
        }

        MySeekBarChangeListener() {
        }
    }
}
