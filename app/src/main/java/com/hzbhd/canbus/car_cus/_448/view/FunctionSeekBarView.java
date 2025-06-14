package com.hzbhd.canbus.car_cus._448.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._448.Interface.ActionCallback;

/* loaded from: classes2.dex */
public class FunctionSeekBarView extends LinearLayout {
    private SeekBar my_seek;
    private TextView title_txt;
    private TextView value_txt;
    private View view;

    public FunctionSeekBarView(Context context) {
        this(context, null);
    }

    public FunctionSeekBarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FunctionSeekBarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.__448_view_function_seekbar, (ViewGroup) this, true);
        this.view = viewInflate;
        this.title_txt = (TextView) viewInflate.findViewById(R.id.title_txt);
        this.value_txt = (TextView) this.view.findViewById(R.id.value_txt);
        this.my_seek = (SeekBar) this.view.findViewById(R.id.my_seek);
    }

    public void setMax(int i) {
        this.my_seek.setMax(i);
    }

    public void setTitleStr(String str) {
        this.title_txt.setText(str);
    }

    public void setValue(int i) {
        this.value_txt.setText(String.valueOf(i));
        this.my_seek.setProgress(i);
    }

    public void getAction(final ActionCallback actionCallback) {
        this.my_seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.hzbhd.canbus.car_cus._448.view.FunctionSeekBarView.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                actionCallback.toDo(Integer.valueOf(i));
                FunctionSeekBarView.this.value_txt.setText(String.valueOf(i));
            }
        });
    }
}
