package com.hzbhd.canbus.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.hzbhd.canbus.R;
import com.hzbhd.canbus.ui_set.OriginalBtnAction;

import kotlin.text.Typography;

/* loaded from: classes2.dex */
public class OriginalTopBtnItemView extends RelativeLayout {
    private Button mBtn;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onClick();
    }

    public OriginalTopBtnItemView(Context context, String str) {
        super(context);
        initViews(context, str);
    }

    public OriginalTopBtnItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private void initViews(Context context, String str) {
        Button button = (Button) LayoutInflater.from(context).inflate(R.layout.view_original_top_line_btn_item, this).findViewById(R.id.btn_top);
        this.mBtn = button;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (OriginalTopBtnItemView.this.mOnItemClickListener != null) {
                    OriginalTopBtnItemView.this.mOnItemClickListener.onClick();
                }
            }
        });

        // Usar un enfoque directo con if-else para asignar el texto
        if (str.equals(OriginalBtnAction.REAR_CDC_RIGHT)) {
            this.mBtn.setText(R.string.rear_cdc_right);
        } else if (str.equals(OriginalBtnAction.SELECT_CD)) {
            this.mBtn.setText(R.string.select_cd);
        } else if (str.equals(OriginalBtnAction.RDM_DISC)) {
            this.mBtn.setText(R.string.rdm_disc);
        } else if (str.equals(OriginalBtnAction.RDM_FOLD)) {
            this.mBtn.setText(R.string.rdm_fold);
        } else if (str.equals(OriginalBtnAction.AUTO_P)) {
            this.mBtn.setText(R.string.auto_p);
        } else if (str.equals(OriginalBtnAction.EXIT_CD)) {
            this.mBtn.setText(R.string.exit_cd);
        } else if (str.equals(OriginalBtnAction.FOLDER)) {
            this.mBtn.setText(R.string.folder);
        } else if (str.equals(OriginalBtnAction.INSERT)) {
            this.mBtn.setText(R.string.insert);
        } else if (str.equals(OriginalBtnAction.REAR_CDC)) {
            this.mBtn.setText(R.string.rear_cdc);
        } else if (str.equals(OriginalBtnAction.USB_BOX)) {
            this.mBtn.setText(R.string.usb_box);
        } else if (str.equals(OriginalBtnAction.DISC_SCAN)) {
            this.mBtn.setText(R.string.disc_scan);
        } else if (str.equals(OriginalBtnAction.REAR_CDC_DOWN)) {
            this.mBtn.setText(R.string.rear_cdc_down);
        } else if (str.equals(OriginalBtnAction.REAR_CDC_LEFT)) {
            this.mBtn.setText(R.string.rear_cdc_left);
        } else if (str.equals(OriginalBtnAction.REAR_CDC_MENU)) {
            this.mBtn.setText(R.string.rear_cdc_menu);
        } else if (str.equals(OriginalBtnAction.AM)) {
            this.mBtn.setText(R.string.am);
        } else if (str.equals(OriginalBtnAction.CD)) {
            this.mBtn.setText(R.string.cd);
        } else if (str.equals(OriginalBtnAction.FM)) {
            this.mBtn.setText(R.string.fm);
        } else if (str.equals(OriginalBtnAction.ST)) {
            this.mBtn.setText(R.string.st);
        } else if (str.equals("aux")) {
            this.mBtn.setText(R.string.aux);
        } else if (str.equals(OriginalBtnAction.CDC)) {
            this.mBtn.setText(R.string.cdc);
        } else if (str.equals(OriginalBtnAction.DVD)) {
            this.mBtn.setText(R.string.dvd);
        } else if (str.equals(OriginalBtnAction.FM1)) {
            this.mBtn.setText(R.string.fm1);
        } else if (str.equals(OriginalBtnAction.FM2)) {
            this.mBtn.setText(R.string.fm2);
        } else if (str.equals(OriginalBtnAction.MP3)) {
            this.mBtn.setText(R.string.mp3);
        } else if (str.equals(OriginalBtnAction.RDM)) {
            this.mBtn.setText(R.string.rdm);
        } else if (str.equals(OriginalBtnAction.RDS)) {
            this.mBtn.setText(R.string.rds);
        } else if (str.equals(OriginalBtnAction.RPT)) {
            this.mBtn.setText(R.string.rpt);
        } else if (str.equals(OriginalBtnAction.WMA)) {
            this.mBtn.setText(R.string.wma);
        } else if (str.equals("lock")) {
            this.mBtn.setText(R.string.original_button_lock);
        } else if (str.equals(OriginalBtnAction.NAVI)) {
            this.mBtn.setText(R.string.navi);
        } else if (str.equals(OriginalBtnAction.SCAN)) {
            this.mBtn.setText(R.string.scan);
        } else if (str.equals(OriginalBtnAction.AM_ST)) {
            this.mBtn.setText(R.string.am_st);
        } else if (str.equals("power")) {
            this.mBtn.setText(R.string._55_0xa4_top_btn_power);
        } else if (str.equals("radio")) {
            this.mBtn.setText(R.string.radio);
        } else if (str.equals(OriginalBtnAction.SCANE)) {
            this.mBtn.setText(R.string.scane);
        } else if (str.equals(OriginalBtnAction.FOLD_ADD)) {
            this.mBtn.setText(R.string.fold_add);
        } else if (str.equals(OriginalBtnAction.FOLD_SUB)) {
            this.mBtn.setText(R.string.fold_sub);
        } else if (str.equals(OriginalBtnAction.RPT_TRACK)) {
            this.mBtn.setText(R.string.rpt_track);
        } else if (str.equals(OriginalBtnAction.RPT_DISC)) {
            this.mBtn.setText(R.string.rpt_disc);
        } else if (str.equals(OriginalBtnAction.RPT_FOLD)) {
            this.mBtn.setText(R.string.rpt_fold);
        } else if (str.equals(OriginalBtnAction.PRESET_SELECT)) {
            this.mBtn.setText(R.string.preset_select);
        } else if (str.equals(OriginalBtnAction.RDM_OFF)) {
            this.mBtn.setText(R.string.rdm_off);
        } else if (str.equals(OriginalBtnAction.REFRESH)) {
            this.mBtn.setText(R.string.refresh);
        } else if (str.equals(OriginalBtnAction.PRESET_STORE)) {
            this.mBtn.setText(R.string.preset_store);
        } else if (str.equals(OriginalBtnAction.RPT_OFF)) {
            this.mBtn.setText(R.string.rpt_off);
        } else if (str.equals(OriginalBtnAction.AUX_INSERT)) {
            this.mBtn.setText(R.string.aux_insert);
        } else if (str.equals(OriginalBtnAction.REAR_CDC_OK)) {
            this.mBtn.setText(R.string.rear_cdc_ok);
        } else if (str.equals(OriginalBtnAction.REAR_CDC_UP)) {
            this.mBtn.setText(R.string.rear_cdc_up);
        }
    }


    public void turn(boolean z) {
        if (z) {
            this.mBtn.setBackgroundResource(R.drawable.music_top_ic_p);
        } else {
            this.mBtn.setBackgroundResource(R.drawable.original_row_btn_top_selector);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
