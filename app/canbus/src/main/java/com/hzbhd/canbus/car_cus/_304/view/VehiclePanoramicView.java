package com.hzbhd.canbus.car_cus._304.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car_cus._304.util.Util;

/* loaded from: classes2.dex */
public class VehiclePanoramicView extends RelativeLayout {
    private ImageView mIvPanoramic;
    private View mView;

    public VehiclePanoramicView(Context context) {
        super(context);
        this.mView = LayoutInflater.from(context).inflate(R.layout._304_view_vehicle_panoramic, this);
        findViews();
        initViews();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.i("ljqdebug", "onAttachedToWindow: ");
        sendEnterPanoramic();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.i("ljqdebug", "onDetachedFromWindow: ");
    }

    private void findViews() {
        this.mIvPanoramic = (ImageView) this.mView.findViewById(R.id.iv_panoramic);
    }

    private void initViews() {
        this.mIvPanoramic.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._304.view.VehiclePanoramicView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m1140xcb77b782(view);
            }
        });
    }

    /* renamed from: lambda$initViews$0$com-hzbhd-canbus-car_cus-_304-view-VehiclePanoramicView, reason: not valid java name */
    /* synthetic */ void m1140xcb77b782(View view) {
        sendEnterPanoramic();
    }

    private void sendEnterPanoramic() {
        Util.sendAvmCommand(1);
        CanbusMsgSender.sendMsg(new byte[]{22, -80, 64, 0});
    }
}
