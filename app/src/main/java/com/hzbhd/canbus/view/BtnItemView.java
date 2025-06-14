package com.hzbhd.canbus.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_set.AirBtnAction;
import kotlin.text.Typography;

/* loaded from: classes2.dex */
public class BtnItemView extends RelativeLayout {
    private String mAction;
    private boolean mCanClick;
    private ImageButton mIb;
    private ImageView mIv;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onClick();
    }

    public BtnItemView(Context context, String str, boolean z) {
        super(context);
        initViews(context, str, z);
    }

    public BtnItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private void initViews(Context context, String str, boolean z) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.view_air_line_btn_item, this);
        this.mIv = (ImageView) viewInflate.findViewById(R.id.iv_item);
        ImageButton imageButton = (ImageButton) viewInflate.findViewById(R.id.ibt_item);
        this.mIb = imageButton;
        imageButton.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.view.BtnItemView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (BtnItemView.this.mOnItemClickListener != null) {
                    BtnItemView.this.mOnItemClickListener.onClick();
                }
            }
        });
        this.mCanClick = z;
        this.mIb.setEnabled(z);
        if (this.mCanClick) {
            this.mIb.setBackgroundResource(R.drawable.air_line_btn_off_enable_selector);
        } else {
            this.mIb.setBackgroundResource(R.drawable.air_line_btn_off_disable_selector);
        }
        this.mAction = str;
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -2052939637:
                if (str.equals(AirBtnAction.REAR_BLOW_POSITIVE)) {
                    c = 0;
                    break;
                }
                break;
            case -1878365090:
                if (str.equals(AirBtnAction.RIGHT_SET_SEAT_COLD)) {
                    c = 1;
                    break;
                }
                break;
            case -1878226070:
                if (str.equals(AirBtnAction.RIGHT_SET_SEAT_HEAT)) {
                    c = 2;
                    break;
                }
                break;
            case -1786872896:
                if (str.equals(AirBtnAction.STEERING_WHEEL_HEATING)) {
                    c = 3;
                    break;
                }
                break;
            case -1761278488:
                if (str.equals(AirBtnAction.POLLRN_REMOVAL)) {
                    c = 4;
                    break;
                }
                break;
            case -1640478633:
                if (str.equals(AirBtnAction.SMALL_WIND_LIGHT)) {
                    c = 5;
                    break;
                }
                break;
            case -1604835367:
                if (str.equals(AirBtnAction.WINDSHIELD_DEICING)) {
                    c = 6;
                    break;
                }
                break;
            case -1470045433:
                if (str.equals("front_defog")) {
                    c = 7;
                    break;
                }
                break;
            case -1428679594:
                if (str.equals(AirBtnAction.AUTO_MANUAL)) {
                    c = '\b';
                    break;
                }
                break;
            case -1423573049:
                if (str.equals(AirBtnAction.AC_MAX)) {
                    c = '\t';
                    break;
                }
                break;
            case -1406322270:
                if (str.equals(AirBtnAction.AUTO_2)) {
                    c = '\n';
                    break;
                }
                break;
            case -1406322218:
                if (str.equals(AirBtnAction.AUTO_F)) {
                    c = 11;
                    break;
                }
                break;
            case -1406322206:
                if (str.equals(AirBtnAction.AUTO_R)) {
                    c = '\f';
                    break;
                }
                break;
            case -1274277292:
                if (str.equals(AirBtnAction.CLEAN_AIR)) {
                    c = '\r';
                    break;
                }
                break;
            case -1181429844:
                if (str.equals(AirBtnAction.AC_AUTO)) {
                    c = 14;
                    break;
                }
                break;
            case -1181328134:
                if (str.equals(AirBtnAction.AC_ECON)) {
                    c = 15;
                    break;
                }
                break;
            case -1142919397:
                if (str.equals(AirBtnAction.REAR_BLOW_HEAD_FOOT)) {
                    c = 16;
                    break;
                }
                break;
            case -1081415738:
                if (str.equals(AirBtnAction.MANUAL)) {
                    c = 17;
                    break;
                }
                break;
            case -1073935955:
                if (str.equals(AirBtnAction.THREE_ZONE)) {
                    c = 18;
                    break;
                }
                break;
            case -1039745817:
                if (str.equals(AirBtnAction.NORMAL)) {
                    c = 19;
                    break;
                }
                break;
            case -769927883:
                if (str.equals(GeneralAmplifierData.bose_center)) {
                    c = 20;
                    break;
                }
                break;
            case -713186454:
                if (str.equals(AirBtnAction.REAR_AUTO)) {
                    c = 21;
                    break;
                }
                break;
            case -713097673:
                if (str.equals(AirBtnAction.REAR_DUAL)) {
                    c = 22;
                    break;
                }
                break;
            case -712865050:
                if (str.equals(AirBtnAction.REAR_LOCK)) {
                    c = 23;
                    break;
                }
                break;
            case -712646570:
                if (str.equals(AirBtnAction.REAR_SYNC)) {
                    c = 24;
                    break;
                }
                break;
            case -631663038:
                if (str.equals("rear_defog")) {
                    c = 25;
                    break;
                }
                break;
            case -620266838:
                if (str.equals(AirBtnAction.REAR_POWER)) {
                    c = 26;
                    break;
                }
                break;
            case -597744666:
                if (str.equals("blow_positive")) {
                    c = 27;
                    break;
                }
                break;
            case -480367616:
                if (str.equals(AirBtnAction.REAR_BLOW_FOOT)) {
                    c = 28;
                    break;
                }
                break;
            case -480318094:
                if (str.equals(AirBtnAction.REAR_BLOW_HEAD)) {
                    c = 29;
                    break;
                }
                break;
            case -479653111:
                if (str.equals(AirBtnAction.LEFT_SET_SEAT_COLD)) {
                    c = 30;
                    break;
                }
                break;
            case -479514091:
                if (str.equals(AirBtnAction.LEFT_SET_SEAT_HEAT)) {
                    c = 31;
                    break;
                }
                break;
            case -424438238:
                if (str.equals(AirBtnAction.BLOW_NEGATIVE)) {
                    c = ' ';
                    break;
                }
                break;
            case -386400856:
                if (str.equals(AirBtnAction.SEAT_STEERING_WHEEL_SYNCHRONIZATION)) {
                    c = '!';
                    break;
                }
                break;
            case -246396018:
                if (str.equals(AirBtnAction.MAX_FRONT)) {
                    c = Typography.quote;
                    break;
                }
                break;
            case -148776258:
                if (str.equals(AirBtnAction.BIG_WIND_LIGHT)) {
                    c = '#';
                    break;
                }
                break;
            case -92674103:
                if (str.equals(AirBtnAction.FRONT_WINDOW_HEAT)) {
                    c = Typography.dollar;
                    break;
                }
                break;
            case -54617514:
                if (str.equals(AirBtnAction.AUTO_CYCLE)) {
                    c = '%';
                    break;
                }
                break;
            case -54286835:
                if (str.equals(AirBtnAction.AUTO_DEFOG)) {
                    c = Typography.amp;
                    break;
                }
                break;
            case 3106:
                if (str.equals("ac")) {
                    c = '\'';
                    break;
                }
                break;
            case 96694:
                if (str.equals(AirBtnAction.AMB)) {
                    c = '(';
                    break;
                }
                break;
            case 96835:
                if (str.equals(AirBtnAction.AQS)) {
                    c = ')';
                    break;
                }
                break;
            case 100241:
                if (str.equals(AirBtnAction.ECO)) {
                    c = '*';
                    break;
                }
                break;
            case 104456:
                if (str.equals(AirBtnAction.ION)) {
                    c = '+';
                    break;
                }
                break;
            case 109935:
                if (str.equals("off")) {
                    c = ',';
                    break;
                }
                break;
            case 1782194:
                if (str.equals(AirBtnAction.LEFT_SEAT_HEAT)) {
                    c = '-';
                    break;
                }
                break;
            case 3005871:
                if (str.equals("auto")) {
                    c = '.';
                    break;
                }
                break;
            case 3094652:
                if (str.equals("dual")) {
                    c = '/';
                    break;
                }
                break;
            case 3107581:
                if (str.equals(AirBtnAction.ECON)) {
                    c = '0';
                    break;
                }
                break;
            case 3135580:
                if (str.equals(AirBtnAction.FAST)) {
                    c = '1';
                    break;
                }
                break;
            case 3357411:
                if (str.equals(AirBtnAction.MONO)) {
                    c = '2';
                    break;
                }
                break;
            case 3496356:
                if (str.equals(AirBtnAction.REAR)) {
                    c = '3';
                    break;
                }
                break;
            case 3496916:
                if (str.equals(AirBtnAction.REST)) {
                    c = '4';
                    break;
                }
                break;
            case 3535914:
                if (str.equals(AirBtnAction.SOFT)) {
                    c = '5';
                    break;
                }
                break;
            case 3545755:
                if (str.equals("sync")) {
                    c = '6';
                    break;
                }
                break;
            case 88944080:
                if (str.equals(AirBtnAction.SYNC_TEMPERATURE)) {
                    c = '7';
                    break;
                }
                break;
            case 99489345:
                if (str.equals(AirBtnAction.IN_OUT_AUTO_CYCLE)) {
                    c = '8';
                    break;
                }
                break;
            case 106858757:
                if (str.equals("power")) {
                    c = '9';
                    break;
                }
                break;
            case 109329021:
                if (str.equals(AirBtnAction.SETUP)) {
                    c = ':';
                    break;
                }
                break;
            case 109854462:
                if (str.equals(AirBtnAction.SWING)) {
                    c = ';';
                    break;
                }
                break;
            case 341572893:
                if (str.equals(AirBtnAction.BLOW_WINDOW)) {
                    c = Typography.less;
                    break;
                }
                break;
            case 349917670:
                if (str.equals(AirBtnAction.CYCLE_IN_OUT_CLOSE)) {
                    c = '=';
                    break;
                }
                break;
            case 395882750:
                if (str.equals(AirBtnAction.NEGATIVE_ION)) {
                    c = Typography.greater;
                    break;
                }
                break;
            case 407601476:
                if (str.equals(AirBtnAction.MAX_COOL)) {
                    c = '?';
                    break;
                }
                break;
            case 407740395:
                if (str.equals(AirBtnAction.MAX_HEAT)) {
                    c = '@';
                    break;
                }
                break;
            case 756225563:
                if (str.equals("in_out_cycle")) {
                    c = 'A';
                    break;
                }
                break;
            case 860813349:
                if (str.equals(AirBtnAction.CLIMATE)) {
                    c = 'B';
                    break;
                }
                break;
            case 1006620906:
                if (str.equals(AirBtnAction.AUTO_WIND_MODE)) {
                    c = 'C';
                    break;
                }
                break;
            case 1018451744:
                if (str.equals(AirBtnAction.BLOW_HEAD_FOOT)) {
                    c = 'D';
                    break;
                }
                break;
            case 1080820893:
                if (str.equals(AirBtnAction.REAR_AC)) {
                    c = 'E';
                    break;
                }
                break;
            case 1139377839:
                if (str.equals(AirBtnAction.AUTO_WIND_LIGHT)) {
                    c = 'F';
                    break;
                }
                break;
            case 1171871774:
                if (str.equals(AirBtnAction.AUTO_WIND_STRONG)) {
                    c = 'G';
                    break;
                }
                break;
            case 1252031879:
                if (str.equals(AirBtnAction.RIGHT_SEAT_HEAT)) {
                    c = 'H';
                    break;
                }
                break;
            case 1434490075:
                if (str.equals(AirBtnAction.BLOW_FOOT)) {
                    c = 'I';
                    break;
                }
                break;
            case 1434539597:
                if (str.equals(AirBtnAction.BLOW_HEAD)) {
                    c = 'J';
                    break;
                }
                break;
            case 1438998804:
                if (str.equals(AirBtnAction.AUTO_1_2)) {
                    c = 'K';
                    break;
                }
                break;
            case 1568764496:
                if (str.equals(AirBtnAction.BLOW_WINDOW_FOOT)) {
                    c = 'L';
                    break;
                }
                break;
            case 1574763845:
                if (str.equals(AirBtnAction.HYBRID_AC)) {
                    c = 'M';
                    break;
                }
                break;
            case 1753330393:
                if (str.equals(AirBtnAction.LEFT_RIGHT_TEM_SYNC)) {
                    c = 'N';
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 27:
                this.mIv.setImageResource(R.drawable.ic_air_f_pwzhx_n);
                break;
            case 1:
                this.mIv.setImageResource(R.drawable.ic_air_f_seath2_n);
                break;
            case 2:
                this.mIv.setImageResource(R.drawable.ic_air_f_seath3_n);
                break;
            case 3:
                this.mIv.setImageResource(R.drawable.ic_air_f_wheelhot_n);
                break;
            case 4:
                this.mIv.setImageResource(R.drawable.ic_air_f_nanoe_n);
                break;
            case 5:
                this.mIv.setImageResource(R.drawable.ic_air_f_autosmallw_n);
                break;
            case 6:
                this.mIv.setImageResource(R.drawable.ic_air_f_clearice_n);
                break;
            case 7:
                this.mIv.setImageResource(R.drawable.ic_air_f_fwindo_n);
                break;
            case '\b':
            case '.':
            case 'K':
                this.mIv.setImageResource(R.drawable.ic_air_f_auto_n);
                break;
            case '\t':
                this.mIv.setImageResource(R.drawable.ic_air_f_acmax_n);
                break;
            case '\n':
                this.mIv.setImageResource(R.drawable.ic_air_f_auto2_n);
                break;
            case 11:
                this.mIv.setImageResource(R.drawable.ic_air_f_autof_n);
                break;
            case '\f':
                this.mIv.setImageResource(R.drawable.ic_air_f_autor_n);
                break;
            case '\r':
                this.mIv.setImageResource(R.drawable.ic_air_f_cleanair_n);
                break;
            case 14:
                this.mIv.setImageResource(R.drawable.ic_air_f_autoac_n);
                break;
            case 15:
            case '\'':
            case 'E':
                this.mIv.setImageResource(R.drawable.ic_air_f_ac_n);
                break;
            case 16:
            case 'D':
                this.mIv.setImageResource(R.drawable.ic_air_f_pw9_n);
                break;
            case 17:
                this.mIv.setImageResource(R.drawable.ic_air_f_man_n);
                break;
            case 18:
                this.mIv.setImageResource(R.drawable.ic_air_f_3zone_n);
                break;
            case 19:
                this.mIv.setImageResource(R.drawable.ic_air_f_normal_n);
                break;
            case 20:
                this.mIv.setImageResource(R.drawable.ic_air_f_bose_n);
                break;
            case 21:
                this.mIv.setImageResource(R.drawable.ic_air_f_autor_n);
                break;
            case 22:
                this.mIv.setImageResource(R.drawable.ic_air_f_dual_n);
                break;
            case 23:
                this.mIv.setImageResource(R.drawable.ic_air_f_rearl_n);
                break;
            case 24:
            case '6':
                this.mIv.setImageResource(R.drawable.ic_air_f_sync_n);
                break;
            case 25:
                this.mIv.setImageResource(R.drawable.ic_air_f_rwindo_n);
                break;
            case 26:
                this.mIv.setImageResource(R.drawable.ic_air_f_power_n);
                break;
            case 28:
                this.mIv.setImageResource(R.drawable.ic_air_f_pw3_n);
                break;
            case 29:
                this.mIv.setImageResource(R.drawable.ic_air_f_pw2_n);
                break;
            case 30:
                this.mIv.setImageResource(R.drawable.ic_air_f_seath1_n);
                break;
            case 31:
                this.mIv.setImageResource(R.drawable.ic_air_f_seath4_n);
                break;
            case ' ':
                this.mIv.setImageResource(R.drawable.ic_air_f_pwnix_n);
                break;
            case '!':
                this.mIv.setImageResource(R.drawable.ic_air_f_seatwheel_n);
                break;
            case '\"':
                this.mIv.setImageResource(R.drawable.ic_air_f_maxfornt_n);
                break;
            case '#':
                this.mIv.setImageResource(R.drawable.ic_air_f_autobigw_n);
                break;
            case '$':
                this.mIv.setImageResource(R.drawable.ic_air_f_heat_n);
                break;
            case '%':
                this.mIv.setImageResource(R.drawable.auto_in_out_cycle);
                break;
            case '&':
                this.mIv.setImageResource(R.drawable.ic_air_f_autodefog_n);
                break;
            case '(':
                this.mIv.setImageResource(R.drawable.ic_air_f_amb_n);
                break;
            case ')':
                this.mIv.setImageResource(R.drawable.ic_air_f_aqs_n);
                break;
            case '*':
                this.mIv.setImageResource(R.drawable.ic_air_f_eco_n);
                break;
            case '+':
                this.mIv.setImageResource(R.drawable.ic_air_f_ion_n);
                break;
            case ',':
                this.mIv.setImageResource(R.drawable.ic_air_f_off_n);
                break;
            case '-':
                this.mIv.setImageResource(R.drawable.ic_air_t_zuoweijiare_n);
                break;
            case '/':
                this.mIv.setImageResource(R.drawable.ic_air_f_dual_n);
                break;
            case '0':
                this.mIv.setImageResource(R.drawable.ic_air_f_econ_n);
                break;
            case '1':
                this.mIv.setImageResource(R.drawable.ic_air_f_fast_n);
                break;
            case '2':
                this.mIv.setImageResource(R.drawable.ic_air_f_mono_n);
                break;
            case '3':
                this.mIv.setImageResource(R.drawable.ic_air_f_rear_n);
                break;
            case '4':
                this.mIv.setImageResource(R.drawable.ic_air_f_rest_n);
                break;
            case '5':
                this.mIv.setImageResource(R.drawable.ic_air_f_soft_n);
                break;
            case '7':
                this.mIv.setImageResource(R.drawable.ic_air_f_synctemp_n);
                break;
            case '8':
                this.mIv.setImageResource(R.drawable.ic_air_f_carin_n);
                break;
            case '9':
                this.mIv.setImageResource(R.drawable.ic_air_f_power_n);
                break;
            case ':':
                this.mIv.setImageResource(R.drawable.ic_air_f_set_n);
                break;
            case ';':
                this.mIv.setImageResource(R.drawable.ic_air_f_swing_n);
                break;
            case '<':
                this.mIv.setImageResource(R.drawable.ic_air_f_pw4_n);
                break;
            case '=':
                this.mIv.setImageResource(R.drawable.ic_air_f_carin_n);
                break;
            case '>':
                this.mIv.setImageResource(R.drawable.ic_air_f_fuliz_n);
                break;
            case '?':
                this.mIv.setImageResource(R.drawable.ic_air_f_maxc_n);
                break;
            case '@':
                this.mIv.setImageResource(R.drawable.ic_air_f_maxheat_n);
                break;
            case 'A':
                this.mIv.setImageResource(R.drawable.ic_air_f_carin_n);
                break;
            case 'B':
                this.mIv.setImageResource(R.drawable.ic_air_f_climate_n);
                break;
            case 'C':
                this.mIv.setImageResource(R.drawable.ic_air_f_windm_n);
                break;
            case 'F':
                this.mIv.setImageResource(R.drawable.ic_air_f_winl_n);
                break;
            case 'G':
                this.mIv.setImageResource(R.drawable.ic_air_f_windst_n);
                break;
            case 'H':
                this.mIv.setImageResource(R.drawable.ic_air_t_zuoweijiare_n);
                break;
            case 'I':
                this.mIv.setImageResource(R.drawable.ic_air_f_pw3_n);
                break;
            case 'J':
                this.mIv.setImageResource(R.drawable.ic_air_f_pw2_n);
                break;
            case 'L':
                this.mIv.setImageResource(R.drawable.ic_air_f_pw6_n);
                break;
            case 'M':
                this.mIv.setImageResource(R.drawable.ic_air_f_hybrid_n);
                break;
            case 'N':
                this.mIv.setImageResource(R.drawable.ic_air_f_sync_n);
                break;
        }
    }

    public void turn(boolean z) {
        if (z) {
            if (this.mCanClick) {
                this.mIb.setBackgroundResource(R.drawable.air_line_btn_on_enable_selector);
                return;
            } else {
                this.mIb.setBackgroundResource(R.drawable.air_line_btn_on_disable_selector);
                return;
            }
        }
        if (this.mCanClick) {
            this.mIb.setBackgroundResource(R.drawable.air_line_btn_off_enable_selector);
        } else {
            this.mIb.setBackgroundResource(R.drawable.air_line_btn_off_disable_selector);
        }
    }

    public void setImageResource(int i) {
        this.mIv.setImageResource(i);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
