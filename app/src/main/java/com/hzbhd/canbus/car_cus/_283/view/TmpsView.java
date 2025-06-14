package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car_cus._283.DialogUtils;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.canbus.car_cus._283.adapter.TmpsAdapter;
import com.hzbhd.canbus.car_cus._283.bean.TmpsChooseBean;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class TmpsView extends RelativeLayout implements View.OnClickListener {
    private static final int redColor = 17170454;
    private static final int whiteColor = 17170443;
    private int[] image;
    private ImageView imageView;
    private LinearLayout linearChooseTmps;
    private List<TmpsChooseBean> lists;
    private Context mContext;
    private View mView;
    private int[] strs;
    private TextView textView;
    private TextView tv_left_front_reality;
    private TextView tv_left_front_reference;
    private TextView tv_left_rear_reality;
    private TextView tv_left_rear_reference;
    private TextView tv_right_front_reality;
    private TextView tv_right_front_reference;
    private TextView tv_right_rear_reality;
    private TextView tv_right_rear_reference;
    private TextView tv_tips;
    private TextView tv_unit;
    private int[] units;

    public TmpsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.lists = new ArrayList();
        this.units = new int[]{R.string._283_tmp_unit_1, R.string._283_tmp_unit_2, R.string._283_tmp_unit_3};
        this.strs = new int[]{R.string._283_tmps_choose_title_1, R.string._283_tmps_choose_title_2, R.string._283_tmps_choose_title_3};
        this.image = new int[]{R.drawable.dz_tpms_set_images_standard, R.drawable.dz_tpms_set_images_standard, R.drawable.dz_tpms_set_images_full};
        this.mContext = context;
        this.mView = LayoutInflater.from(context).inflate(R.layout._283_tmps_view, this);
        initView();
        new Handler().postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.view.TmpsView$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m1101lambda$new$0$comhzbhdcanbuscar_cus_283viewTmpsView();
            }
        }, 1000L);
    }

    private void initView() {
        this.linearChooseTmps = (LinearLayout) this.mView.findViewById(R.id.linearChooseTmps);
        this.imageView = (ImageView) this.mView.findViewById(R.id.imageView);
        this.textView = (TextView) this.mView.findViewById(R.id.textView);
        this.tv_unit = (TextView) this.mView.findViewById(R.id.tv_unit);
        this.tv_left_front_reality = (TextView) this.mView.findViewById(R.id.tv_left_front_reality);
        this.tv_left_rear_reality = (TextView) this.mView.findViewById(R.id.tv_left_rear_reality);
        this.tv_right_front_reality = (TextView) this.mView.findViewById(R.id.tv_right_front_reality);
        this.tv_right_rear_reality = (TextView) this.mView.findViewById(R.id.tv_right_rear_reality);
        this.tv_left_front_reference = (TextView) this.mView.findViewById(R.id.tv_left_front_reference);
        this.tv_left_rear_reference = (TextView) this.mView.findViewById(R.id.tv_left_rear_reference);
        this.tv_right_front_reference = (TextView) this.mView.findViewById(R.id.tv_right_front_reference);
        this.tv_right_rear_reference = (TextView) this.mView.findViewById(R.id.tv_right_rear_reference);
        this.tv_tips = (TextView) this.mView.findViewById(R.id.tv_tips);
        this.linearChooseTmps.setOnClickListener(this);
        this.lists.add(new TmpsChooseBean(this.image[0], this.mContext.getString(this.strs[0])));
        this.lists.add(new TmpsChooseBean(this.image[1], this.mContext.getString(this.strs[1])));
        this.lists.add(new TmpsChooseBean(this.image[2], this.mContext.getString(this.strs[2])));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.linearChooseTmps) {
            return;
        }
        DialogUtils.chooseTmps(this.mContext, this.linearChooseTmps, this.lists, new TmpsAdapter.OnItemClickListener() { // from class: com.hzbhd.canbus.car_cus._283.view.TmpsView$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.car_cus._283.adapter.TmpsAdapter.OnItemClickListener
            public final void click(int i) {
                CanbusMsgSender.sendMsg(new byte[]{22, 75, 4, (byte) i});
            }
        });
    }

    /* renamed from: refreshUi, reason: merged with bridge method [inline-methods] */
    public void m1101lambda$new$0$comhzbhdcanbuscar_cus_283viewTmpsView() {
        int i = GeneralDzData.tmpsChoose;
        if (i < 0 || i >= this.lists.size()) {
            return;
        }
        this.textView.setText(this.lists.get(i).getText());
        this.imageView.setImageResource(this.lists.get(i).getImage());
    }

    public void refreshUiData() {
        this.tv_unit.setText(this.units[GeneralDzData.tmpsUnit]);
        this.tv_tips.setText(getTips());
        this.tv_tips.setTextColor(getTipsTextColor());
        this.tv_left_front_reality.setText(String.valueOf(GeneralDzData.left_front_tmps_reality));
        this.tv_right_front_reality.setText(String.valueOf(GeneralDzData.right_front_tmps_reality));
        this.tv_left_rear_reality.setText(String.valueOf(GeneralDzData.left_rear_tmps_reality));
        this.tv_right_rear_reality.setText(String.valueOf(GeneralDzData.right_rear_tmps_reality));
        this.tv_left_front_reality.setTextColor(getTipsTextColor(GeneralDzData.left_front_tmps_exception));
        this.tv_right_front_reality.setTextColor(getTipsTextColor(GeneralDzData.right_front_tmps_exception));
        this.tv_left_rear_reality.setTextColor(getTipsTextColor(GeneralDzData.left_rear_tmps_exception));
        this.tv_right_rear_reality.setTextColor(getTipsTextColor(GeneralDzData.right_rear_tmps_exception));
        this.tv_left_front_reference.setText(String.valueOf(GeneralDzData.left_front_tmps_reference));
        this.tv_right_front_reference.setText(String.valueOf(GeneralDzData.right_front_tmps_reference));
        this.tv_left_rear_reference.setText(String.valueOf(GeneralDzData.left_rear_tmps_reference));
        this.tv_right_rear_reference.setText(String.valueOf(GeneralDzData.right_rear_tmps_reference));
    }

    private int getTipsTextColor(boolean z) {
        if (z) {
            return ContextCompat.getColor(this.mContext, 17170454);
        }
        return ContextCompat.getColor(this.mContext, 17170443);
    }

    private int getTipsTextColor() {
        if (GeneralDzData.left_front_tmps_exception || GeneralDzData.right_front_tmps_exception || GeneralDzData.left_rear_tmps_exception || GeneralDzData.right_rear_tmps_exception) {
            return ContextCompat.getColor(this.mContext, 17170454);
        }
        return ContextCompat.getColor(this.mContext, 17170443);
    }

    private String getTips() {
        StringBuilder sb = new StringBuilder();
        if (GeneralDzData.left_front_tmps_exception) {
            sb.append(this.mContext.getString(R.string._283_tmps_tips_1));
            sb.append("\n");
        }
        if (GeneralDzData.right_front_tmps_exception) {
            sb.append(this.mContext.getString(R.string._283_tmps_tips_2));
            sb.append("\n");
        }
        if (GeneralDzData.left_rear_tmps_exception) {
            sb.append(this.mContext.getString(R.string._283_tmps_tips_3));
            sb.append("\n");
        }
        if (GeneralDzData.right_rear_tmps_exception) {
            sb.append(this.mContext.getString(R.string._283_tmps_tips_4));
            sb.append("\n");
        }
        String string = sb.toString();
        return !TextUtils.isEmpty(string) ? string : this.mContext.getString(R.string._283_tmps_tips_0);
    }
}
