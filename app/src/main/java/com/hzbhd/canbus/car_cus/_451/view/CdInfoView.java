package com.hzbhd.canbus.car_cus._451.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hzbhd.canbus.R;

/* loaded from: classes2.dex */
public class CdInfoView extends LinearLayout {
    private TextView cd_number;
    private TextView song_number;
    private TextView time;
    private View view;

    public CdInfoView(Context context) {
        this(context, null);
    }

    public CdInfoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CdInfoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View viewInflate = LayoutInflater.from(context).inflate(R.layout._451_view_cd_info, (ViewGroup) this, true);
        this.view = viewInflate;
        this.cd_number = (TextView) viewInflate.findViewById(R.id.cd_number);
        this.song_number = (TextView) this.view.findViewById(R.id.song_number);
        this.time = (TextView) this.view.findViewById(R.id.time);
    }

    public void setData(String str, String str2, String str3) {
        this.cd_number.setText(str);
        this.song_number.setText(str2);
        this.time.setText(str3);
    }
}
