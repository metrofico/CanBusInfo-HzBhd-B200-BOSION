package com.hzbhd.canbus.car_cus._448.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._448.Interface.ActionCallback;

/* loaded from: classes2.dex */
public class FileItemView extends LinearLayout {
    private LinearLayout all_action_lin;
    private TextView file_name;
    private ImageView lock_img;
    private TextView palying_txt;
    private ImageView type_img;
    private View view;

    public FileItemView(Context context) {
        this(context, null);
    }

    public FileItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FileItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.__448_view_file_item, (ViewGroup) this, true);
        this.view = viewInflate;
        this.file_name = (TextView) viewInflate.findViewById(R.id.file_name);
        this.palying_txt = (TextView) this.view.findViewById(R.id.palying_txt);
        this.type_img = (ImageView) this.view.findViewById(R.id.type_img);
        this.lock_img = (ImageView) this.view.findViewById(R.id.lock_img);
        this.all_action_lin = (LinearLayout) this.view.findViewById(R.id.all_action_lin);
    }

    public void setData(int i, String str, int i2) {
        if (!str.equals("NONE")) {
            this.all_action_lin.setVisibility(0);
        }
        this.file_name.setText(str);
        this.type_img.setBackgroundResource(i);
        this.lock_img.setBackgroundResource(i2);
    }

    public void getAction(final ActionCallback actionCallback) {
        this.all_action_lin.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._448.view.FileItemView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                actionCallback.toDo(null);
            }
        });
    }

    public void setSelect(boolean z) {
        if (z) {
            this.all_action_lin.setBackgroundResource(R.drawable.__448___ph8_dvr_list_p);
        } else {
            this.all_action_lin.setBackgroundResource(R.color.transparent);
        }
    }

    public void setPlaying(boolean z) {
        if (z) {
            this.palying_txt.setVisibility(0);
        } else {
            this.palying_txt.setVisibility(8);
        }
    }
}
