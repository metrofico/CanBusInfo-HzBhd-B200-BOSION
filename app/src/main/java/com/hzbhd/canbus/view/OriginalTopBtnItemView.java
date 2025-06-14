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
        button.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.view.OriginalTopBtnItemView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (OriginalTopBtnItemView.this.mOnItemClickListener != null) {
                    OriginalTopBtnItemView.this.mOnItemClickListener.onClick();
                }
            }
        });
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1780016412:
                if (str.equals(OriginalBtnAction.REAR_CDC_RIGHT)) {
                    c = 0;
                    break;
                }
                break;
            case -1715975548:
                if (str.equals(OriginalBtnAction.SELECT_CD)) {
                    c = 1;
                    break;
                }
                break;
            case -1415634215:
                if (str.equals(OriginalBtnAction.RDM_DISC)) {
                    c = 2;
                    break;
                }
                break;
            case -1415569083:
                if (str.equals(OriginalBtnAction.RDM_FOLD)) {
                    c = 3;
                    break;
                }
                break;
            case -1406322208:
                if (str.equals(OriginalBtnAction.AUTO_P)) {
                    c = 4;
                    break;
                }
                break;
            case -1315390686:
                if (str.equals(OriginalBtnAction.EXIT_CD)) {
                    c = 5;
                    break;
                }
                break;
            case -1268966290:
                if (str.equals(OriginalBtnAction.FOLDER)) {
                    c = 6;
                    break;
                }
                break;
            case -1183792455:
                if (str.equals(OriginalBtnAction.INSERT)) {
                    c = 7;
                    break;
                }
                break;
            case -854288633:
                if (str.equals(OriginalBtnAction.REAR_CDC)) {
                    c = '\b';
                    break;
                }
                break;
            case -150466416:
                if (str.equals(OriginalBtnAction.USB_BOX)) {
                    c = '\t';
                    break;
                }
                break;
            case -136075545:
                if (str.equals(OriginalBtnAction.DISC_SCAN)) {
                    c = '\n';
                    break;
                }
                break;
            case -57830694:
                if (str.equals(OriginalBtnAction.REAR_CDC_DOWN)) {
                    c = 11;
                    break;
                }
                break;
            case -57602497:
                if (str.equals(OriginalBtnAction.REAR_CDC_LEFT)) {
                    c = '\f';
                    break;
                }
                break;
            case -57572457:
                if (str.equals(OriginalBtnAction.REAR_CDC_MENU)) {
                    c = '\r';
                    break;
                }
                break;
            case 3116:
                if (str.equals(OriginalBtnAction.AM)) {
                    c = 14;
                    break;
                }
                break;
            case 3169:
                if (str.equals(OriginalBtnAction.CD)) {
                    c = 15;
                    break;
                }
                break;
            case 3271:
                if (str.equals(OriginalBtnAction.FM)) {
                    c = 16;
                    break;
                }
                break;
            case 3681:
                if (str.equals(OriginalBtnAction.ST)) {
                    c = 17;
                    break;
                }
                break;
            case 96964:
                if (str.equals("aux")) {
                    c = 18;
                    break;
                }
                break;
            case 98338:
                if (str.equals(OriginalBtnAction.CDC)) {
                    c = 19;
                    break;
                }
                break;
            case 99858:
                if (str.equals(OriginalBtnAction.DVD)) {
                    c = 20;
                    break;
                }
                break;
            case 101450:
                if (str.equals(OriginalBtnAction.FM1)) {
                    c = 21;
                    break;
                }
                break;
            case 101451:
                if (str.equals(OriginalBtnAction.FM2)) {
                    c = 22;
                    break;
                }
                break;
            case 108272:
                if (str.equals(OriginalBtnAction.MP3)) {
                    c = 23;
                    break;
                }
                break;
            case 112763:
                if (str.equals(OriginalBtnAction.RDM)) {
                    c = 24;
                    break;
                }
                break;
            case 112769:
                if (str.equals(OriginalBtnAction.RDS)) {
                    c = 25;
                    break;
                }
                break;
            case 113142:
                if (str.equals(OriginalBtnAction.RPT)) {
                    c = 26;
                    break;
                }
                break;
            case 117835:
                if (str.equals(OriginalBtnAction.WMA)) {
                    c = 27;
                    break;
                }
                break;
            case 3327275:
                if (str.equals("lock")) {
                    c = 28;
                    break;
                }
                break;
            case 3373990:
                if (str.equals(OriginalBtnAction.NAVI)) {
                    c = 29;
                    break;
                }
                break;
            case 3524221:
                if (str.equals(OriginalBtnAction.SCAN)) {
                    c = 30;
                    break;
                }
                break;
            case 92923732:
                if (str.equals(OriginalBtnAction.AM_ST)) {
                    c = 31;
                    break;
                }
                break;
            case 106858757:
                if (str.equals("power")) {
                    c = ' ';
                    break;
                }
                break;
            case 108270587:
                if (str.equals("radio")) {
                    c = '!';
                    break;
                }
                break;
            case 109250952:
                if (str.equals(OriginalBtnAction.SCANE)) {
                    c = Typography.quote;
                    break;
                }
                break;
            case 293915491:
                if (str.equals(OriginalBtnAction.FOLD_ADD)) {
                    c = '#';
                    break;
                }
                break;
            case 293933314:
                if (str.equals(OriginalBtnAction.FOLD_SUB)) {
                    c = Typography.dollar;
                    break;
                }
                break;
            case 436485570:
                if (str.equals(OriginalBtnAction.RPT_TRACK)) {
                    c = '%';
                    break;
                }
                break;
            case 844879422:
                if (str.equals(OriginalBtnAction.RPT_DISC)) {
                    c = Typography.amp;
                    break;
                }
                break;
            case 844944554:
                if (str.equals(OriginalBtnAction.RPT_FOLD)) {
                    c = '\'';
                    break;
                }
                break;
            case 1042163292:
                if (str.equals(OriginalBtnAction.PRESET_SELECT)) {
                    c = '(';
                    break;
                }
                break;
            case 1062723499:
                if (str.equals(OriginalBtnAction.RDM_OFF)) {
                    c = ')';
                    break;
                }
                break;
            case 1085444827:
                if (str.equals(OriginalBtnAction.REFRESH)) {
                    c = '*';
                    break;
                }
                break;
            case 1142446977:
                if (str.equals(OriginalBtnAction.PRESET_STORE)) {
                    c = '+';
                    break;
                }
                break;
            case 1412737958:
                if (str.equals(OriginalBtnAction.RPT_OFF)) {
                    c = ',';
                    break;
                }
                break;
            case 1602773140:
                if (str.equals(OriginalBtnAction.AUX_INSERT)) {
                    c = '-';
                    break;
                }
                break;
            case 1863625236:
                if (str.equals(OriginalBtnAction.REAR_CDC_OK)) {
                    c = '.';
                    break;
                }
                break;
            case 1863625427:
                if (str.equals(OriginalBtnAction.REAR_CDC_UP)) {
                    c = '/';
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                this.mBtn.setText(R.string.rear_cdc_right);
                break;
            case 1:
                this.mBtn.setText(R.string.select_cd);
                break;
            case 2:
                this.mBtn.setText(R.string.rdm_disc);
                break;
            case 3:
                this.mBtn.setText(R.string.rdm_fold);
                break;
            case 4:
                this.mBtn.setText(R.string.auto_p);
                break;
            case 5:
                this.mBtn.setText(R.string.exit_cd);
                break;
            case 6:
                this.mBtn.setText(R.string.folder);
                break;
            case 7:
                this.mBtn.setText(R.string.insert);
                break;
            case '\b':
                this.mBtn.setText(R.string.rear_cdc);
                break;
            case '\t':
                this.mBtn.setText(R.string.usb_box);
                break;
            case '\n':
                this.mBtn.setText(R.string.disc_scan);
                break;
            case 11:
                this.mBtn.setText(R.string.rear_cdc_down);
                break;
            case '\f':
                this.mBtn.setText(R.string.rear_cdc_left);
                break;
            case '\r':
                this.mBtn.setText(R.string.rear_cdc_menu);
                break;
            case 14:
                this.mBtn.setText(R.string.am);
                break;
            case 15:
                this.mBtn.setText(R.string.cd);
                break;
            case 16:
                this.mBtn.setText(R.string.fm);
                break;
            case 17:
                this.mBtn.setText(R.string.st);
                break;
            case 18:
                this.mBtn.setText(R.string.aux);
                break;
            case 19:
                this.mBtn.setText(R.string.cdc);
                break;
            case 20:
                this.mBtn.setText(R.string.dvd);
                break;
            case 21:
                this.mBtn.setText(R.string.fm1);
                break;
            case 22:
                this.mBtn.setText(R.string.fm2);
                break;
            case 23:
                this.mBtn.setText(R.string.mp3);
                break;
            case 24:
                this.mBtn.setText(R.string.rdm);
                break;
            case 25:
                this.mBtn.setText(R.string.rds);
                break;
            case 26:
                this.mBtn.setText(R.string.rpt);
                break;
            case 27:
                this.mBtn.setText(R.string.wma);
                break;
            case 28:
                this.mBtn.setText(R.string.original_button_lock);
                break;
            case 29:
                this.mBtn.setText(R.string.navi);
                break;
            case 30:
                this.mBtn.setText(R.string.scan);
                break;
            case 31:
                this.mBtn.setText(R.string.am_st);
                break;
            case ' ':
                this.mBtn.setText(R.string._55_0xa4_top_btn_power);
                break;
            case '!':
                this.mBtn.setText(R.string.radio);
                break;
            case '\"':
                this.mBtn.setText(R.string.scane);
                break;
            case '#':
                this.mBtn.setText(R.string.fold_add);
                break;
            case '$':
                this.mBtn.setText(R.string.fold_sub);
                break;
            case '%':
                this.mBtn.setText(R.string.rpt_track);
                break;
            case '&':
                this.mBtn.setText(R.string.rpt_disc);
                break;
            case '\'':
                this.mBtn.setText(R.string.rpt_fold);
                break;
            case '(':
                this.mBtn.setText(R.string.preset_select);
                break;
            case ')':
                this.mBtn.setText(R.string.rdm_off);
                break;
            case '*':
                this.mBtn.setText(R.string.refresh);
                break;
            case '+':
                this.mBtn.setText(R.string.preset_store);
                break;
            case ',':
                this.mBtn.setText(R.string.rpt_off);
                break;
            case '-':
                this.mBtn.setText(R.string.aux_insert);
                break;
            case '.':
                this.mBtn.setText(R.string.rear_cdc_ok);
                break;
            case '/':
                this.mBtn.setText(R.string.rear_cdc_up);
                break;
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
