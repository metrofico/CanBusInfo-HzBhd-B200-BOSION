package com.hzbhd.canbus.car_cus._436.view.modularView;

import android.content.Context;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car_cus._436.Interface.MdNotifyListener;
import com.hzbhd.canbus.car_cus._436.Interface.MdOnClickListener;
import com.hzbhd.canbus.car_cus._436.data.GeneralDvrState;
import com.hzbhd.canbus.car_cus._436.util.DvrSender;
import com.hzbhd.canbus.car_cus._436.view.childView.ItemDialogAlert;

/* loaded from: classes2.dex */
public class DvrStateView extends LinearLayout implements View.OnClickListener, MdNotifyListener {
    TextView connect_txt;
    Button format_sd_btn;
    TextView lock_txt;
    Context mContext;
    TextView sd_txt;
    TextView tag_txt;
    TextView time_txt;
    TextView version_txt;
    View view;

    @Override // com.hzbhd.canbus.car_cus._436.Interface.MdNotifyListener
    public void updateUi() {
        refreshUi();
    }

    public DvrStateView(Context context) {
        this(context, null);
    }

    public DvrStateView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DvrStateView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
        this.view = LayoutInflater.from(context).inflate(R.layout._436_dvr_state_view, (ViewGroup) this, true);
        initData();
    }

    private void initData() {
        this.sd_txt = (TextView) this.view.findViewById(R.id.sd_txt);
        Button button = (Button) this.view.findViewById(R.id.format_sd_btn);
        this.format_sd_btn = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.view.modularView.DvrStateView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onClick(view);
            }
        });
        this.connect_txt = (TextView) this.view.findViewById(R.id.connect_txt);
        this.lock_txt = (TextView) this.view.findViewById(R.id.lock_txt);
        this.tag_txt = (TextView) this.view.findViewById(R.id.tag_txt);
        this.time_txt = (TextView) this.view.findViewById(R.id.time_txt);
        this.version_txt = (TextView) this.view.findViewById(R.id.version_txt);
        refreshUi();
    }

    public void refreshUi() {
        if (GeneralDvrState.sd == 0) {
            this.sd_txt.setText(this.mContext.getString(R.string._436_DVR_State_sd) + this.mContext.getString(R.string._436_DVR_State_sd0));
            this.format_sd_btn.setVisibility(8);
        } else if (GeneralDvrState.sd == 1) {
            this.sd_txt.setText(this.mContext.getString(R.string._436_DVR_State_sd) + this.mContext.getString(R.string._436_DVR_State_sd1));
            this.format_sd_btn.setVisibility(8);
        } else if (GeneralDvrState.sd == 2) {
            this.sd_txt.setText(this.mContext.getString(R.string._436_DVR_State_sd) + this.mContext.getString(R.string._436_DVR_State_sd2));
            this.format_sd_btn.setVisibility(8);
        } else if (GeneralDvrState.sd == 3) {
            this.sd_txt.setText(this.mContext.getString(R.string._436_DVR_State_sd) + this.mContext.getString(R.string._436_DVR_State_sd3));
            this.format_sd_btn.setVisibility(0);
        }
        if (GeneralDvrState.formatSdFail) {
            this.format_sd_btn.setVisibility(0);
        }
        if (GeneralDvrState.lock) {
            this.lock_txt.setText(this.mContext.getString(R.string._436_DVR_State_lock) + this.mContext.getString(R.string._436_DVR_State_lock0));
        } else {
            this.lock_txt.setText(this.mContext.getString(R.string._436_DVR_State_lock) + this.mContext.getString(R.string._436_DVR_State_lock1));
        }
        this.tag_txt.setText(this.mContext.getString(R.string._436_DVR_State_tag) + GeneralDvrState.tag);
        this.time_txt.setText(this.mContext.getString(R.string._436_DVR_State_time) + GeneralDvrState.date + "  " + GeneralDvrState.time);
        this.version_txt.setText(this.mContext.getString(R.string._436_DVR_State_version) + GeneralDvrState.version);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.format_sd_btn) {
            return;
        }
        ItemDialogAlert itemDialogAlert = new ItemDialogAlert();
        Context context = this.mContext;
        itemDialogAlert.showDialog(context, context.getString(R.string._436_DVR_State_sd_alert), new MdOnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.view.modularView.DvrStateView.1
            @Override // com.hzbhd.canbus.car_cus._436.Interface.MdOnClickListener
            public void clickPosition(int i) throws RemoteException {
                DvrStateView.this.format_sd_btn.setVisibility(8);
                DvrSender.send(new byte[]{91, 0});
            }
        });
    }
}
