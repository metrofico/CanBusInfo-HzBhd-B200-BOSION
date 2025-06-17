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

import java.util.HashMap;
import java.util.Map;

import kotlin.text.Typography;

/* loaded from: classes2.dex */
public class BtnItemView extends RelativeLayout {
    private String mAction;
    private boolean mCanClick;
    private ImageButton mIb;
    private ImageView mIv;
    private OnItemClickListener mOnItemClickListener;
    private final Map<String, Integer> actionToResourceMap = new HashMap<>() {{
        // Mapear las acciones a los recursos correspondientes
        put(AirBtnAction.REAR_BLOW_POSITIVE, R.drawable.ic_air_f_pwzhx_n);
        put(AirBtnAction.RIGHT_SET_SEAT_COLD, R.drawable.ic_air_f_seath2_n);
        put(AirBtnAction.RIGHT_SET_SEAT_HEAT, R.drawable.ic_air_f_seath3_n);
        put(AirBtnAction.STEERING_WHEEL_HEATING, R.drawable.ic_air_f_wheelhot_n);
        put(AirBtnAction.POLLRN_REMOVAL, R.drawable.ic_air_f_nanoe_n);
        put(AirBtnAction.SMALL_WIND_LIGHT, R.drawable.ic_air_f_autosmallw_n);
        put(AirBtnAction.WINDSHIELD_DEICING, R.drawable.ic_air_f_clearice_n);
        put("front_defog", R.drawable.ic_air_f_fwindo_n);
        put(AirBtnAction.AUTO_MANUAL, R.drawable.ic_air_f_auto_n);
        put(AirBtnAction.AC_MAX, R.drawable.ic_air_f_acmax_n);
        put(AirBtnAction.AUTO_2, R.drawable.ic_air_f_auto2_n);
        put(AirBtnAction.AUTO_F, R.drawable.ic_air_f_autof_n);
        put(AirBtnAction.AUTO_R, R.drawable.ic_air_f_autor_n);
        put(AirBtnAction.CLEAN_AIR, R.drawable.ic_air_f_cleanair_n);
        put(AirBtnAction.AC_AUTO, R.drawable.ic_air_f_autoac_n);
        put(AirBtnAction.AC_ECON, R.drawable.ic_air_f_ac_n);
        put("ac", R.drawable.ic_air_f_ac_n);
        put(AirBtnAction.AUTO_CYCLE, R.drawable.auto_in_out_cycle);
        put(AirBtnAction.AUTO_DEFOG, R.drawable.ic_air_f_autodefog_n);
        put(AirBtnAction.AMB, R.drawable.ic_air_f_amb_n);
        put(AirBtnAction.AQS, R.drawable.ic_air_f_aqs_n);
        put(AirBtnAction.ECO, R.drawable.ic_air_f_eco_n);
        put(AirBtnAction.ION, R.drawable.ic_air_f_ion_n);
        put("off", R.drawable.ic_air_f_off_n);
        put(AirBtnAction.LEFT_SEAT_HEAT, R.drawable.ic_air_f_seath1_n);
        put("auto", R.drawable.ic_air_f_auto_n);
        put("dual", R.drawable.ic_air_f_dual_n);
        put(AirBtnAction.ECON, R.drawable.ic_air_f_econ_n);
        put(AirBtnAction.FAST, R.drawable.ic_air_f_fast_n);
        put(AirBtnAction.MONO, R.drawable.ic_air_f_mono_n);
        put(AirBtnAction.REAR, R.drawable.ic_air_f_rear_n);
        put(AirBtnAction.REST, R.drawable.ic_air_f_rest_n);
        put(AirBtnAction.SOFT, R.drawable.ic_air_f_soft_n);
        put("sync", R.drawable.ic_air_f_sync_n);
        put(AirBtnAction.SYNC_TEMPERATURE, R.drawable.ic_air_f_synctemp_n);
        put(AirBtnAction.IN_OUT_AUTO_CYCLE, R.drawable.ic_air_f_carin_n);
        put(AirBtnAction.CLIMATE, R.drawable.ic_air_f_climate_n);
        put(AirBtnAction.AUTO_WIND_MODE, R.drawable.ic_air_f_windm_n);
        put(AirBtnAction.BLOW_HEAD_FOOT, R.drawable.ic_air_f_pw9_n);
        put(AirBtnAction.REAR_AC, R.drawable.ic_air_f_pw3_n);
        put(AirBtnAction.AUTO_WIND_LIGHT, R.drawable.ic_air_f_winl_n);
        put(AirBtnAction.AUTO_WIND_STRONG, R.drawable.ic_air_f_windst_n);
        put(AirBtnAction.BLOW_FOOT, R.drawable.ic_air_f_pw3_n);
        put(AirBtnAction.BLOW_HEAD, R.drawable.ic_air_f_pw2_n);
        put(AirBtnAction.BLOW_WINDOW, R.drawable.ic_air_f_pw6_n);
        put(AirBtnAction.HYBRID_AC, R.drawable.ic_air_f_hybrid_n);
        put(AirBtnAction.LEFT_RIGHT_TEM_SYNC, R.drawable.ic_air_f_sync_n);
    }};

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
        this.mIv = viewInflate.findViewById(R.id.iv_item);
        ImageButton imageButton = viewInflate.findViewById(R.id.ibt_item);
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
        updateImageResource(str);
    }

    private void updateImageResource(String action) {
        Integer resourceId = actionToResourceMap.get(action);
        if (resourceId != null) {
            mIv.setImageResource(resourceId);
        }
        actionToResourceMap.clear();
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
