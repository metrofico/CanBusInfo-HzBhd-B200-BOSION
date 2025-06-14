package com.hzbhd.canbus.car_cus._467.drive.viiew;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car_cus._467.drive.data.DriveData;
import com.hzbhd.canbus.comm.ScreenConfig;
import com.hzbhd.canbus.interfaces.ActionDo;
import com.hzbhd.ui.util.BaseUtil;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* loaded from: classes2.dex */
public class DriveDateView extends RelativeLayout implements ActionDo {
    private TextView a2;
    private TextView a5;
    private TextView b2;
    private TextView b5;
    private TextView c2;
    private TextView c5;
    private TextView d2;
    private TextView d5;
    private TextView tempEvaporation;
    private TextView tempIn;
    private TextView tempOut;
    private View view;

    public DriveDateView(Context context) {
        this(context, null);
    }

    public DriveDateView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DriveDateView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (ScreenConfig.screenWidth == 1024 && ScreenConfig.screenHeight == 600) {
            this.view = LayoutInflater.from(context).inflate(R.layout._467_view_repair1024x600, (ViewGroup) this, true);
        } else {
            this.view = LayoutInflater.from(context).inflate(R.layout._467_view_repair1280x720, (ViewGroup) this, true);
        }
        this.a2 = (TextView) this.view.findViewById(R.id.a2);
        this.a5 = (TextView) this.view.findViewById(R.id.a5);
        this.b2 = (TextView) this.view.findViewById(R.id.b2);
        this.b5 = (TextView) this.view.findViewById(R.id.b5);
        this.c2 = (TextView) this.view.findViewById(R.id.c2);
        this.c5 = (TextView) this.view.findViewById(R.id.c5);
        this.d2 = (TextView) this.view.findViewById(R.id.d2);
        this.d5 = (TextView) this.view.findViewById(R.id.d5);
        this.tempOut = (TextView) this.view.findViewById(R.id.tempOut);
        this.tempIn = (TextView) this.view.findViewById(R.id.tempIn);
        this.tempEvaporation = (TextView) this.view.findViewById(R.id.tempEvaporation);
        updateUI();
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 33, 0});
    }

    @Override // com.hzbhd.canbus.interfaces.ActionDo
    public void todo(List<Object> list) {
        updateUI();
    }

    private void updateUI() {
        BaseUtil.INSTANCE.runUi(new Function0<Unit>() { // from class: com.hzbhd.canbus.car_cus._467.drive.viiew.DriveDateView.1
            @Override // kotlin.jvm.functions.Function0
            public Unit invoke() {
                DriveDateView.this.a2.setText(DriveData.dataA2);
                DriveDateView.this.a5.setText(DriveData.dataA5);
                DriveDateView.this.b2.setText(DriveData.dataB2);
                DriveDateView.this.b5.setText(DriveData.dataB5);
                DriveDateView.this.c2.setText(DriveData.dataC2);
                DriveDateView.this.c5.setText(DriveData.dataC5);
                DriveDateView.this.d2.setText(DriveData.dataD2);
                DriveDateView.this.d5.setText(DriveData.dataD5);
                DriveDateView.this.tempOut.setText(DriveData.tempOut);
                DriveDateView.this.tempIn.setText(DriveData.tempIn);
                DriveDateView.this.tempEvaporation.setText(DriveData.tempEvaporation);
                return null;
            }
        });
    }
}
