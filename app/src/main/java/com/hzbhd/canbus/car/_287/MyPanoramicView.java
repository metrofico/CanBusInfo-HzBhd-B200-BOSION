package com.hzbhd.canbus.car._287;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;


public class MyPanoramicView extends RelativeLayout implements View.OnClickListener {
    private Button mBtnCamera;
    private Context mContext;

    public MyPanoramicView(Context context) {
        super(context);
        this.mContext = context;
        Button button = (Button) LayoutInflater.from(context).inflate(R.layout.layout_panoramice_21_view, this).findViewById(R.id.ib_panoramic_camera);
        this.mBtnCamera = button;
        button.setOnClickListener(this);
    }

    public MyPanoramicView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MyPanoramicView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.ib_panoramic_camera) {
            return;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -57, 1});
    }
}
