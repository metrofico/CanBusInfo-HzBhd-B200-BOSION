package com.hzbhd.canbus.car_cus._273.view;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car_cus._273.MainActivity;

/* loaded from: classes2.dex */
public class MonitorView extends RelativeLayout implements ViewInterface, View.OnClickListener {
    private View bgView;
    private Button mCenterCamera;
    private Context mContext;
    private Button mFronCamera;
    private MainActivity mMainActivity;
    private View mView;
    private RelativeLayout monitorRelative;

    @Override // com.hzbhd.canbus.car_cus._273.view.ViewInterface
    public void onDestroy() {
    }

    @Override // com.hzbhd.canbus.car_cus._273.view.ViewInterface
    public void refreshUi(Bundle bundle) {
    }

    public MonitorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        this.mView = LayoutInflater.from(context).inflate(R.layout._273_view_monitor, this);
        findViews();
    }

    private void findViews() {
        this.monitorRelative = (RelativeLayout) this.mView.findViewById(R.id.monitorRelative);
        this.mFronCamera = (Button) this.mView.findViewById(R.id.monitor_front_camera);
        this.mCenterCamera = (Button) this.mView.findViewById(R.id.monitor_center_camera);
        this.bgView = this.mView.findViewById(R.id.monitor_bgView);
        this.mFronCamera.setOnClickListener(this);
        this.mCenterCamera.setOnClickListener(this);
    }

    public void addSurfaceView(SurfaceView surfaceView) {
        this.monitorRelative.removeView(surfaceView);
        this.monitorRelative.addView(surfaceView);
    }

    public void removeSurfaceView(SurfaceView surfaceView) {
        this.monitorRelative.removeView(surfaceView);
    }

    public void setManiActivity(MainActivity mainActivity) {
        this.mMainActivity = mainActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.bgView.setVisibility(0);
        this.bgView.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car_cus._273.view.MonitorView.1
            @Override // java.lang.Runnable
            public void run() {
                MonitorView.this.bgView.setVisibility(8);
            }
        }, 500L);
        switch (view.getId()) {
            case R.id.monitor_center_camera /* 2131362875 */:
                this.mMainActivity.showCenterCamera();
                break;
            case R.id.monitor_front_camera /* 2131362876 */:
                this.mMainActivity.showFrontCamera();
                break;
        }
    }
}
