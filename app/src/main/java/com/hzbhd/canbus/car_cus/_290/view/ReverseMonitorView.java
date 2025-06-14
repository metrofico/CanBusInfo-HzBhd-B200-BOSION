package com.hzbhd.canbus.car_cus._290.view;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.R;

/* loaded from: classes2.dex */
public class ReverseMonitorView extends RelativeLayout implements ViewInterface {
    private Context mContext;
    private View mView;
    private RelativeLayout reverseRelative;

    private void initViews() {
    }

    @Override // com.hzbhd.canbus.car_cus._290.view.ViewInterface
    public void onDestroy() {
    }

    @Override // com.hzbhd.canbus.car_cus._290.view.ViewInterface
    public void refreshUi(Bundle bundle) {
    }

    public ReverseMonitorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        this.mView = LayoutInflater.from(context).inflate(R.layout._273_view_reverse_monitor, this);
        findViews();
        initViews();
    }

    private void findViews() {
        this.reverseRelative = (RelativeLayout) this.mView.findViewById(R.id.reverse_relative);
    }

    public void addSurfaceView(SurfaceView surfaceView) {
        this.reverseRelative.removeView(surfaceView);
        this.reverseRelative.addView(surfaceView);
    }

    public void removeSurfaceView(SurfaceView surfaceView) {
        this.reverseRelative.removeView(surfaceView);
    }
}
