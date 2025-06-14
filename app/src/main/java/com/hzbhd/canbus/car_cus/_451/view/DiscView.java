package com.hzbhd.canbus.car_cus._451.view;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hzbhd.R;

/* loaded from: classes2.dex */
public class DiscView extends LinearLayout {
    private Handler handler;
    private ImageView img;
    private boolean imgTag;
    private boolean isTwinkle;
    private TextView txt;
    private View view;

    public DiscView(Context context) {
        this(context, null);
    }

    public DiscView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DiscView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.isTwinkle = false;
        this.imgTag = true;
        this.handler = new Handler(Looper.getMainLooper()) { // from class: com.hzbhd.canbus.car_cus._451.view.DiscView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what == 65535) {
                    if (DiscView.this.isTwinkle) {
                        if (DiscView.this.imgTag) {
                            DiscView.this.img.setVisibility(8);
                            DiscView.this.imgTag = false;
                        } else {
                            DiscView.this.img.setVisibility(0);
                            DiscView.this.imgTag = true;
                        }
                        Message message2 = new Message();
                        message2.what = 65535;
                        DiscView.this.handler.sendMessageDelayed(message2, 500L);
                        return;
                    }
                    DiscView.this.img.setVisibility(0);
                }
            }
        };
        View viewInflate = LayoutInflater.from(context).inflate(R.layout._451_view_disc, (ViewGroup) this, true);
        this.view = viewInflate;
        this.txt = (TextView) viewInflate.findViewById(R.id.txt);
        this.img = (ImageView) this.view.findViewById(R.id.img);
    }

    public void setText(String str) {
        this.txt.setText(str);
    }

    public void setTwinkle(boolean z) {
        this.isTwinkle = z;
        Message message = new Message();
        message.what = 65535;
        this.handler.sendMessageDelayed(message, 500L);
    }
}
