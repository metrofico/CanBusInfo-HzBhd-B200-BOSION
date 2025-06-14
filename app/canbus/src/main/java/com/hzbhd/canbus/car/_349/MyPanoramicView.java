package com.hzbhd.canbus.car._349;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hzbhd.canbus.R;

/* loaded from: classes2.dex */
public class MyPanoramicView extends RelativeLayout {
    static final String LEFT_BACKWARD = "left_backward";
    static final String LEFT_FORWARD = "left_forward";
    static final String LEFT_RADAR = "left_radar";
    static final String LEFT_REVERSE = "left_REVERSE";
    static final String LEFT_STOP = "left_stop";
    static final String RIGHT_RADAR = "right_radar";
    private ImageView mLeftBackWard;
    private ImageView mLeftForward;
    private ImageView mLeftRadar;
    private TextView mLeftReverse;
    private ImageView mLeftStop;
    private LinearLayout mLlAlert;
    private ImageView mRightRadar;
    private TextView mTvAlertMessage;
    private TextView mTvMessage;

    public MyPanoramicView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.layout_panoramice_81_view, this);
        findView();
    }

    private void findView() {
        this.mTvMessage = (TextView) findViewById(R.id.tv_message);
        this.mTvAlertMessage = (TextView) findViewById(R.id.tv_alert_message);
        this.mLeftRadar = (ImageView) findViewById(R.id.iv_left_radar);
        this.mRightRadar = (ImageView) findViewById(R.id.iv_right_radar);
        this.mLeftStop = (ImageView) findViewById(R.id.iv_left_stop);
        this.mLeftForward = (ImageView) findViewById(R.id.iv_left_forward);
        this.mLeftBackWard = (ImageView) findViewById(R.id.iv_left_backward);
        this.mLeftReverse = (TextView) findViewById(R.id.tv_left_reverse);
        this.mLlAlert = (LinearLayout) findViewById(R.id.ll_alert);
    }

    public void showAlertWindow() {
        this.mLlAlert.setVisibility(0);
    }

    public void hideAlertWindow() {
        this.mLlAlert.setVisibility(8);
    }

    public void setMessage(int i) {
        this.mTvMessage.setText(i);
    }

    public void setAlertMessage(int i) {
        this.mTvAlertMessage.setText(i);
    }

    public void setAlertMessage(String str) {
        this.mTvAlertMessage.setText(str);
    }

    public void refreshIcon(String str) {
        setVisible(this.mLeftRadar, "left_radar".equals(str));
        setVisible(this.mRightRadar, "right_radar".equals(str));
        setVisible(this.mLeftForward, "left_forward".equals(str));
        setVisible(this.mLeftBackWard, "left_backward".equals(str));
        setVisible(this.mLeftStop, "left_stop".equals(str));
        setVisible(this.mLeftReverse, LEFT_REVERSE.equals(str));
    }

    private void setVisible(View view, boolean z) {
        if (z) {
            view.setVisibility(0);
        } else {
            view.setVisibility(8);
        }
    }
}
