package com.hzbhd.canbus.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hzbhd.canbus.R;
import com.hzbhd.canbus.activity.OnStarActivity;
import com.hzbhd.canbus.config.CanbusConfig;
import com.hzbhd.canbus.view.VerticalProgressBar;

/* loaded from: classes2.dex */
public class OnStartNavigationFragment extends BaseFragment implements View.OnClickListener {
    public static final int BUICK_GL8_2017_XINBAS = 71;
    public static final int GM_XINBAS = 62;
    private TextView gm_onstar_navi_cancel;
    private TextView gm_onstar_navi_destination;
    private TextView gm_onstar_navi_direction;
    private TextView gm_onstar_navi_next_status;
    private TextView gm_onstar_navi_prev_status;
    private TextView gm_onstar_navi_preview;
    private VerticalProgressBar gm_onstar_navi_progress;
    private TextView gm_onstar_navi_repeat;
    private View gm_onstar_navi_travel_distance_bg;
    private TextView gm_onstar_navi_travel_distance_next;
    private TextView gm_onstar_navi_travel_distance_range;
    private View gm_onstar_navi_travel_info_bg;
    private TextView gm_onstar_navi_travel_info_end;
    private TextView gm_onstar_navi_travel_info_normal;
    private TextView gm_onstar_navi_update;
    private TextView gm_onstar_navi_xinbus_distance;
    private View gm_onstar_navi_xinbus_distance_bg;
    private TextView gm_onstar_navi_xinbus_road_info;
    private View gm_onstar_navi_xinbus_road_info_bg;
    private final int[] loadImg = {R.drawable.gm_onstar_navi_direction_0, R.drawable.gm_onstar_navi_direction_1, R.drawable.gm_onstar_navi_direction_2, R.drawable.gm_onstar_navi_direction_3, R.drawable.gm_onstar_navi_direction_4, R.drawable.gm_onstar_navi_direction_5, R.drawable.gm_onstar_navi_direction_6, R.drawable.gm_onstar_navi_direction_7, R.drawable.gm_onstar_navi_direction_8, R.drawable.gm_onstar_navi_direction_9, R.drawable.gm_onstar_navi_direction_a, R.drawable.gm_onstar_navi_direction_b, R.drawable.gm_onstar_navi_direction_c, R.drawable.gm_onstar_navi_direction_d, R.drawable.gm_onstar_navi_direction_e, R.drawable.gm_onstar_navi_direction_f, R.drawable.gm_onstar_navi_direction_10, R.drawable.gm_onstar_navi_direction_11, R.drawable.gm_onstar_navi_direction_12, R.drawable.gm_onstar_navi_direction_13, R.drawable.gm_onstar_navi_direction_14, R.drawable.gm_onstar_navi_direction_15, R.drawable.gm_onstar_navi_direction_16, R.drawable.gm_onstar_navi_direction_17, R.drawable.gm_onstar_navi_direction_f, R.drawable.gm_onstar_navi_direction_19, R.drawable.gm_onstar_navi_direction_1a, R.drawable.gm_onstar_navi_direction_1b, R.drawable.gm_onstar_navi_direction_1c, R.drawable.gm_onstar_navi_direction_1d, R.drawable.gm_onstar_navi_direction_1e, R.drawable.gm_onstar_navi_direction_1f, R.drawable.gm_onstar_navi_direction_20};
    private OnStarActivity mActivity;
    private int mCanType;
    private View mView;

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    public void refreshUi(Bundle bundle) {
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mView == null) {
            this.mView = layoutInflater.inflate(R.layout.fragment_on_star_navi, viewGroup, false);
            findViews();
            initViews();
        }
        ViewGroup viewGroup2 = (ViewGroup) this.mView.getParent();
        if (viewGroup2 != null) {
            viewGroup2.removeView(this.mView);
        }
        return this.mView;
    }

    private void findViews() {
        this.gm_onstar_navi_travel_info_bg = this.mView.findViewById(R.id.gm_onstar_navi_travel_info_bg);
        this.gm_onstar_navi_travel_distance_bg = this.mView.findViewById(R.id.gm_onstar_navi_travel_distance_bg);
        this.gm_onstar_navi_xinbus_road_info_bg = this.mView.findViewById(R.id.gm_onstar_navi_xinbus_road_info_bg);
        this.gm_onstar_navi_xinbus_distance_bg = this.mView.findViewById(R.id.gm_onstar_navi_xinbus_distance_bg);
        this.gm_onstar_navi_direction = this.mView.findViewById(R.id.gm_onstar_navi_direction);
        this.gm_onstar_navi_progress = this.mView.findViewById(R.id.gm_onstar_navi_progress);
        this.gm_onstar_navi_travel_info_end = this.mView.findViewById(R.id.gm_onstar_navi_travel_info_end);
        this.gm_onstar_navi_travel_info_normal = this.mView.findViewById(R.id.gm_onstar_navi_travel_info_normal);
        this.gm_onstar_navi_travel_distance_next = this.mView.findViewById(R.id.gm_onstar_navi_travel_distance_next);
        this.gm_onstar_navi_travel_distance_range = this.mView.findViewById(R.id.gm_onstar_navi_travel_distance_range);
        this.gm_onstar_navi_repeat = this.mView.findViewById(R.id.gm_onstar_navi_repeat);
        this.gm_onstar_navi_preview = this.mView.findViewById(R.id.gm_onstar_navi_preview);
        this.gm_onstar_navi_prev_status = this.mView.findViewById(R.id.gm_onstar_navi_prev_status);
        this.gm_onstar_navi_next_status = this.mView.findViewById(R.id.gm_onstar_navi_next_status);
        this.gm_onstar_navi_destination = this.mView.findViewById(R.id.gm_onstar_navi_destination);
        this.gm_onstar_navi_update = this.mView.findViewById(R.id.gm_onstar_navi_update);
        this.gm_onstar_navi_cancel = this.mView.findViewById(R.id.gm_onstar_navi_cancel);
        this.gm_onstar_navi_xinbus_road_info = this.mView.findViewById(R.id.gm_onstar_navi_xinbus_road_info);
        this.gm_onstar_navi_xinbus_distance = this.mView.findViewById(R.id.gm_onstar_navi_xinbus_distance);
    }

    private void initViews() {
        this.mActivity = (OnStarActivity) getActivity();
        this.mCanType = CanbusConfig.INSTANCE.getCanType();
        this.gm_onstar_navi_repeat.setOnClickListener(this);
        this.gm_onstar_navi_preview.setOnClickListener(this);
        this.gm_onstar_navi_prev_status.setOnClickListener(this);
        this.gm_onstar_navi_next_status.setOnClickListener(this);
        this.gm_onstar_navi_destination.setOnClickListener(this);
        this.gm_onstar_navi_update.setOnClickListener(this);
        this.gm_onstar_navi_cancel.setOnClickListener(this);
        int i = this.mCanType;
        if (i == 62 || i == 71) {
            this.gm_onstar_navi_travel_info_bg.setVisibility(View.GONE);
            this.gm_onstar_navi_travel_distance_bg.setVisibility(View.GONE);
            this.gm_onstar_navi_xinbus_road_info_bg.setVisibility(View.VISIBLE);
            this.gm_onstar_navi_xinbus_distance_bg.setVisibility(View.VISIBLE);
        } else {
            this.gm_onstar_navi_travel_info_bg.setVisibility(View.VISIBLE);
            this.gm_onstar_navi_travel_distance_bg.setVisibility(View.VISIBLE);
            this.gm_onstar_navi_xinbus_road_info_bg.setVisibility(View.GONE);
            this.gm_onstar_navi_xinbus_distance_bg.setVisibility(View.GONE);
        }
        refreshUi(null);
    }
}
