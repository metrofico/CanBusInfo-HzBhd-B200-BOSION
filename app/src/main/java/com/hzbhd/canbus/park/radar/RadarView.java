package com.hzbhd.canbus.park.radar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hzbhd.R;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.park.BackCameraUiService;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.SharePreUtil;

import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class RadarView extends RelativeLayout {
    private final String TAG;
    private TextView b_l_m_txt;
    private TextView b_l_txt;
    private TextView b_r_m_txt;
    private TextView b_r_txt;
    private TextView back_radar_left_mid_small_txt;
    private TextView back_radar_left_small_txt;
    private TextView back_radar_right_mid_small_txt;
    private TextView back_radar_right_small_txt;
    private View bottom_radar;
    private TextView f_l_m_txt;
    private TextView f_l_txt;
    private TextView f_r_m_txt;
    private TextView f_r_txt;
    private TextView front_radar_left_mid_small_txt;
    private TextView front_radar_left_small_txt;
    private TextView front_radar_right_mid_small_txt;
    private TextView front_radar_right_small_txt;
    private ImageButton full_screen_btn;
    private ImageButton hide_btn;
    private TextView left_radar_front_mid_small_txt;
    private TextView left_radar_front_small_txt;
    private TextView left_radar_rear_mid_small_txt;
    private TextView left_radar_rear_small_txt;
    private final Context mContext;
    private TextView mFullOneDistanceTv;
    private TextView mFullUnitTv;
    private TextView mLeftTopTisTv;
    private final View.OnClickListener mOnClickListener;
    private TextView mOneDistanceTv;
    private SparseArray<RadarData> mRadarDataArray;
    private TextView mSmallUnitTv;
    private final View mView;
    private TextView mWarningTisTv;
    private ImageButton pull_btn;
    private TextView r_l_m_txt;
    private TextView r_l_txt;
    private TextView r_r_m_txt;
    private TextView r_r_txt;
    private ImageButton radar_camera_sw;
    private View radar_distan_front;
    private View radar_distan_rear;
    private View radar_distance_front_small;
    private View radar_distance_left_small;
    private View radar_distance_rear_small;
    private View radar_distance_right_small;
    private View radar_full_screen;
    private View radar_small_screen;
    private TextView right_radar_front_mid_small_txt;
    private TextView right_radar_front_small_txt;
    private TextView right_radar_rear_mid_small_txt;
    private TextView right_radar_rear_small_txt;
    private TextView t_l_m_txt;
    private TextView t_l_txt;
    private TextView t_r_m_txt;
    private TextView t_r_txt;
    private View top_radar;

    public RadarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = "RadarView";
        this.mOnClickListener = new View.OnClickListener() { // from class: com.hzbhd.canbus.park.radar.RadarView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int id = view.getId();

                if (id == R.id.full_screen_btn /* 2131362295 */) {
                    RadarView.this.fullScreenRadarView();
                } else if (id == R.id.hide_btn /* 2131362370 */) {
                    RadarView.this.hideRadarView();
                } else if (id == R.id.pull_btn /* 2131362999 */ || id == R.id.radar_camera_sw /* 2131363010 */) {
                    RadarView.this.showRadarView();
                }
            }
        };
        this.mContext = context;
        this.mView = LayoutInflater.from(context).inflate(R.layout.view_radar, this);
        initViews();
        initData();
    }

    public void refreshText() {
        TextView textView = this.mLeftTopTisTv;
        if (textView != null) {
            textView.setText(R.string.str_parking_system);
        }
        TextView textView2 = this.mWarningTisTv;
        if (textView2 != null) {
            textView2.setText(R.string.str_warning);
        }
    }

    public void showRadarView() {
        LogUtil.showLog("RadarView", "showRadarView");
        this.pull_btn.setVisibility(View.GONE);
        this.radar_full_screen.setVisibility(View.GONE);
        this.hide_btn.setVisibility(View.VISIBLE);
        this.radar_small_screen.setVisibility(View.VISIBLE);
        this.full_screen_btn.setVisibility(View.VISIBLE);
        SharePreUtil.setBoolValue(this.mContext, BackCameraUiService.SHARE_IS_SHOW_RADAR, true);
    }

    public void hideRadarView() {
        LogUtil.showLog("RadarView", "hideRadarView");
        this.pull_btn.setVisibility(View.VISIBLE);
        this.hide_btn.setVisibility(View.GONE);
        this.radar_small_screen.setVisibility(View.GONE);
        this.full_screen_btn.setVisibility(View.GONE);
        this.radar_full_screen.setVisibility(View.GONE);
        SharePreUtil.setBoolValue(this.mContext, BackCameraUiService.SHARE_IS_SHOW_RADAR, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fullScreenRadarView() {
        LogUtil.showLog("RadarView", "fullScreenRadarView");
        this.pull_btn.setVisibility(View.GONE);
        this.hide_btn.setVisibility(View.GONE);
        this.radar_small_screen.setVisibility(View.GONE);
        this.full_screen_btn.setVisibility(View.GONE);
        this.radar_full_screen.setVisibility(View.VISIBLE);
    }

    public void refreshLocation(HashMap<Constant.RadarLocation, int[]> map) {
        LogUtil.showLog("RadarView", "refreshLocation");
        if (map == null) {
            LogUtil.showLog("refreshDistance locationMap==null");
            return;
        }
        for (Constant.RadarLocation radarLocation : Constant.RadarLocation.values()) {
            if (map.containsKey(radarLocation)) {
                RadarData radarData = this.mRadarDataArray.get(radarLocation.ordinal());
                if (radarData.isDataChange(map.get(radarLocation)[View.VISIBLE])) {
                    radarData.setRadarImage();
                }
            }
        }
        hideDistanceView();
    }

    public void refreshDistance(HashMap<Constant.RadarLocation, Integer> map) {
        if (map == null) {
            LogUtil.showLog("refreshDistance distMap==null");
            return;
        }
        showDistanceView();
        Iterator<Constant.RadarLocation> it = map.keySet().iterator();
        while (it.hasNext()) {
            this.mRadarDataArray.get(it.next().ordinal()).setRadarImage(1);
        }
        for (Constant.RadarLocation radarLocation : Constant.RadarLocation.values()) {
            if (map.get(radarLocation) != null) {
                setRadarDistanceNum(radarLocation, map.get(radarLocation).intValue());
            }
        }
    }

    private void setRadarDistanceNum(Constant.RadarLocation radarLocation, int i) {
        if (radarLocation == Constant.RadarLocation.FRONT_LEFT) {
            setViewVisibleOrGone(this.front_radar_left_small_txt, this.f_l_txt, i);
        } else if (radarLocation == Constant.RadarLocation.FRONT_MID_LEFT) {
            setViewVisibleOrGone(this.front_radar_left_mid_small_txt, this.f_l_m_txt, i);
        } else if (radarLocation == Constant.RadarLocation.FRONT_MID_RIGHT) {
            setViewVisibleOrGone(this.front_radar_right_mid_small_txt, this.f_r_m_txt, i);
        } else if (radarLocation == Constant.RadarLocation.FRONT_RIGHT) {
            setViewVisibleOrGone(this.front_radar_right_small_txt, this.f_r_txt, i);
        } else if (radarLocation == Constant.RadarLocation.REAR_LEFT) {
            setViewVisibleOrGone(this.back_radar_left_small_txt, this.r_l_txt, i);
        } else if (radarLocation == Constant.RadarLocation.REAR_MID_LEFT) {
            setViewVisibleOrGone(this.back_radar_left_mid_small_txt, this.r_l_m_txt, i);
        } else if (radarLocation == Constant.RadarLocation.REAR_MID_RIGHT) {
            setViewVisibleOrGone(this.back_radar_right_mid_small_txt, this.r_r_m_txt, i);
        } else if (radarLocation == Constant.RadarLocation.REAR_RIGHT) {
            setViewVisibleOrGone(this.back_radar_right_small_txt, this.r_r_txt, i);
        } else if (radarLocation == Constant.RadarLocation.LEFT_FRONT) {
            setViewVisibleOrGone(this.left_radar_front_small_txt, this.b_l_txt, i);
        } else if (radarLocation == Constant.RadarLocation.LEFT_MID_FRONT) {
            setViewVisibleOrGone(this.left_radar_front_mid_small_txt, this.b_l_m_txt, i);
        } else if (radarLocation == Constant.RadarLocation.LEFT_MID_REAR) {
            setViewVisibleOrGone(this.left_radar_rear_mid_small_txt, this.b_r_m_txt, i);
        } else if (radarLocation == Constant.RadarLocation.LEFT_REAR) {
            setViewVisibleOrGone(this.left_radar_rear_small_txt, this.b_r_txt, i);
        } else if (radarLocation == Constant.RadarLocation.RIGHT_MID_FRONT) {
            setViewVisibleOrGone(this.right_radar_front_mid_small_txt, this.t_l_m_txt, i);
        } else if (radarLocation == Constant.RadarLocation.RIGHT_FRONT) {
            setViewVisibleOrGone(this.right_radar_front_small_txt, this.t_l_txt, i);
        } else if (radarLocation == Constant.RadarLocation.RIGHT_MID_REAR) {
            setViewVisibleOrGone(this.right_radar_rear_mid_small_txt, this.t_r_m_txt, i);
        } else if (radarLocation == Constant.RadarLocation.RIGHT_REAR) {
            setViewVisibleOrGone(this.right_radar_rear_small_txt, this.t_r_txt, i);
        }
    }

    private void setViewVisibleOrGone(TextView textView, TextView textView2, int i) {
        if (isDistanceVisible(i)) {
            textView.setVisibility(View.INVISIBLE);
            textView2.setVisibility(android.view.View.INVISIBLE);
        } else {
            textView.setVisibility(View.VISIBLE);
            textView2.setVisibility(View.VISIBLE);
            textView2.setText(String.valueOf(i));
            textView.setText(String.valueOf(i));
        }
    }

    private void initViews() {
        this.radar_small_screen = this.mView.findViewById(R.id.radar_small_screen);
        this.radar_full_screen = this.mView.findViewById(R.id.radar_full_screen);
        this.hide_btn = this.mView.findViewById(R.id.hide_btn);
        this.pull_btn = this.mView.findViewById(R.id.pull_btn);
        this.full_screen_btn = this.mView.findViewById(R.id.full_screen_btn);
        this.radar_camera_sw = this.mView.findViewById(R.id.radar_camera_sw);
        this.hide_btn.setOnClickListener(this.mOnClickListener);
        this.pull_btn.setOnClickListener(this.mOnClickListener);
        this.full_screen_btn.setOnClickListener(this.mOnClickListener);
        this.radar_camera_sw.setOnClickListener(this.mOnClickListener);
        this.hide_btn.setFocusable(false);
        this.pull_btn.setFocusable(false);
        this.full_screen_btn.setFocusable(false);
        this.radar_camera_sw.setFocusable(false);
        this.front_radar_left_small_txt = this.mView.findViewById(R.id.front_radar_left_small_txt);
        this.front_radar_left_mid_small_txt = this.mView.findViewById(R.id.front_radar_left_mid_small_txt);
        this.front_radar_right_mid_small_txt = this.mView.findViewById(R.id.front_radar_right_mid_small_txt);
        this.front_radar_right_small_txt = this.mView.findViewById(R.id.front_radar_right_small_txt);
        this.back_radar_left_small_txt = this.mView.findViewById(R.id.back_radar_left_small_txt);
        this.back_radar_left_mid_small_txt = this.mView.findViewById(R.id.back_radar_left_mid_small_txt);
        this.back_radar_right_mid_small_txt = this.mView.findViewById(R.id.back_radar_right_mid_small_txt);
        this.back_radar_right_small_txt = this.mView.findViewById(R.id.back_radar_right_small_txt);
        this.left_radar_front_small_txt = this.mView.findViewById(R.id.left_radar_front_small_txt);
        this.left_radar_front_mid_small_txt = this.mView.findViewById(R.id.left_radar_front_mid_small_txt);
        this.left_radar_rear_mid_small_txt = this.mView.findViewById(R.id.left_radar_rear_mid_small_txt);
        this.left_radar_rear_small_txt = this.mView.findViewById(R.id.left_radar_rear_small_txt);
        this.right_radar_front_mid_small_txt = this.mView.findViewById(R.id.right_radar_front_mid_small_txt);
        this.right_radar_front_small_txt = this.mView.findViewById(R.id.right_radar_front_small_txt);
        this.right_radar_rear_mid_small_txt = this.mView.findViewById(R.id.right_radar_rear_mid_small_txt);
        this.right_radar_rear_small_txt = this.mView.findViewById(R.id.right_radar_rear_small_txt);
        this.f_l_txt = this.mView.findViewById(R.id.f_l_txt);
        this.f_l_m_txt = this.mView.findViewById(R.id.f_l_m_txt);
        this.f_r_m_txt = this.mView.findViewById(R.id.f_r_m_txt);
        this.f_r_txt = this.mView.findViewById(R.id.f_r_txt);
        this.r_l_txt = this.mView.findViewById(R.id.r_l_txt);
        this.r_l_m_txt = this.mView.findViewById(R.id.r_l_m_txt);
        this.r_r_m_txt = this.mView.findViewById(R.id.r_r_m_txt);
        this.r_r_txt = this.mView.findViewById(R.id.r_r_txt);
        this.b_l_txt = this.mView.findViewById(R.id.b_l_txt);
        this.b_l_m_txt = this.mView.findViewById(R.id.b_l_m_txt);
        this.b_r_m_txt = this.mView.findViewById(R.id.b_r_m_txt);
        this.b_r_txt = this.mView.findViewById(R.id.b_r_txt);
        this.t_l_m_txt = this.mView.findViewById(R.id.t_l_m_txt);
        this.t_l_txt = this.mView.findViewById(R.id.t_l_txt);
        this.t_r_m_txt = this.mView.findViewById(R.id.t_r_m_txt);
        this.t_r_txt = this.mView.findViewById(R.id.t_r_txt);
        this.radar_distan_rear = this.mView.findViewById(R.id.radar_distan_rear);
        this.radar_distan_front = this.mView.findViewById(R.id.radar_distan_front);
        this.top_radar = this.mView.findViewById(R.id.top_radar);
        this.bottom_radar = this.mView.findViewById(R.id.bottom_radar);
        this.radar_distance_left_small = this.mView.findViewById(R.id.radar_distance_left_small);
        this.radar_distance_right_small = this.mView.findViewById(R.id.radar_distance_right_small);
        this.mSmallUnitTv = this.mView.findViewById(R.id.tv_unit);
        this.mOneDistanceTv = this.mView.findViewById(R.id.tv_one_dist);
        this.mFullUnitTv = this.mView.findViewById(R.id.tv_distance_unit);
        this.mFullOneDistanceTv = this.mView.findViewById(R.id.tv_one_distance);
        this.mLeftTopTisTv = this.mView.findViewById(R.id.tv_left_top_tis);
        this.mWarningTisTv = this.mView.findViewById(R.id.tv_bottom_warning_tis);
    }

    public void setSmallRadarViewWidth() {
        int dimension = (int) getResources().getDimension(R.dimen.radar_small_view_width);
        ViewGroup.LayoutParams layoutParams = this.radar_small_screen.getLayoutParams();
        layoutParams.width = dimension;
        this.radar_small_screen.setLayoutParams(layoutParams);
    }

    public void showDistanceView() {
        this.radar_distan_rear.setVisibility(View.VISIBLE);
        this.radar_distan_front.setVisibility(View.VISIBLE);
        this.radar_distance_left_small.setVisibility(View.VISIBLE);
        this.radar_distance_right_small.setVisibility(View.VISIBLE);
        this.top_radar.setVisibility(View.VISIBLE);
        this.bottom_radar.setVisibility(View.VISIBLE);
        this.mSmallUnitTv.setVisibility(View.VISIBLE);
        this.mFullUnitTv.setVisibility(View.VISIBLE);
    }

    private void hideDistanceView() {
        this.radar_distan_rear.setVisibility(View.GONE);
        this.radar_distan_front.setVisibility(View.GONE);
        this.radar_distance_left_small.setVisibility(View.GONE);
        this.radar_distance_right_small.setVisibility(View.GONE);
        this.top_radar.setVisibility(View.GONE);
        this.bottom_radar.setVisibility(View.GONE);
        this.front_radar_left_small_txt.setVisibility(View.INVISIBLE);
        this.front_radar_left_mid_small_txt.setVisibility(View.INVISIBLE);
        this.front_radar_right_mid_small_txt.setVisibility(View.INVISIBLE);
        this.front_radar_right_small_txt.setVisibility(View.INVISIBLE);
        this.back_radar_left_small_txt.setVisibility(View.INVISIBLE);
        this.back_radar_left_mid_small_txt.setVisibility(View.INVISIBLE);
        this.back_radar_right_mid_small_txt.setVisibility(View.INVISIBLE);
        this.back_radar_right_small_txt.setVisibility(View.INVISIBLE);
        this.left_radar_front_small_txt.setVisibility(View.INVISIBLE);
        this.left_radar_front_mid_small_txt.setVisibility(View.INVISIBLE);
        this.left_radar_rear_mid_small_txt.setVisibility(View.INVISIBLE);
        this.left_radar_rear_small_txt.setVisibility(View.INVISIBLE);
        this.right_radar_front_mid_small_txt.setVisibility(View.INVISIBLE);
        this.right_radar_front_small_txt.setVisibility(View.INVISIBLE);
        this.right_radar_rear_mid_small_txt.setVisibility(View.INVISIBLE);
        this.right_radar_rear_small_txt.setVisibility(View.INVISIBLE);
        this.mSmallUnitTv.setVisibility(View.INVISIBLE);
        this.mFullUnitTv.setVisibility(View.INVISIBLE);
    }

    public void setOneRadarDitance(String str) {
        TextView textView = this.mOneDistanceTv;
        if (textView != null) {
            textView.setText(str);
        }
        TextView textView2 = this.mFullOneDistanceTv;
        if (textView2 != null) {
            textView2.setText(str);
        }
    }

    public void hideOneRadarDistance() {
        TextView textView = this.mOneDistanceTv;
        if (textView != null) {
            textView.setText("");
        }
        TextView textView2 = this.mFullOneDistanceTv;
        if (textView2 != null) {
            textView2.setText("");
        }
    }

    private boolean isDistanceVisible(int i) {
        if (GeneralParkData.radar_distance_disable != null) {
            for (int i2 : GeneralParkData.radar_distance_disable) {
                if (i2 == i) {
                    return true;
                }
            }
        }
        return false;
    }

    private class RadarData {
        private final Drawable[] fullImages;
        private final ImageView ivFull;
        private final ImageView ivSmall;
        private final Drawable[] smallImages;
        private int value;

        public RadarData(String str, String str2, ImageView imageView, ImageView imageView2) {
            Drawable[] drawableArr = new Drawable[11];
            this.fullImages = drawableArr;
            drawableArr[View.VISIBLE] = RadarView.this.mContext.getDrawable(R.color.transparent);
            int i = 1;
            int i2 = 1;
            while (true) {
                Drawable[] drawableArr2 = this.fullImages;
                if (i2 >= drawableArr2.length) {
                    break;
                }
                drawableArr2[i2] = RadarView.this.mContext.getDrawable(CommUtil.getImgIdByResId(RadarView.this.mContext, str + i2));
                i2++;
            }
            Drawable[] drawableArr3 = new Drawable[11];
            this.smallImages = drawableArr3;
            drawableArr3[View.VISIBLE] = RadarView.this.mContext.getDrawable(R.color.transparent);
            while (true) {
                Drawable[] drawableArr4 = this.smallImages;
                if (i < drawableArr4.length) {
                    drawableArr4[i] = RadarView.this.mContext.getDrawable(CommUtil.getImgIdByResId(RadarView.this.mContext, str2 + i));
                    i++;
                } else {
                    this.ivFull = imageView;
                    this.ivSmall = imageView2;
                    return;
                }
            }
        }

        public boolean isDataChange(int i) {
            if (this.value == i) {
                return false;
            }
            this.value = i;
            return true;
        }

        public void setRadarImage() {
            setRadarImage(this.value);
        }

        public void setRadarImage(int i) {
            this.ivFull.setImageDrawable(this.fullImages[i]);
            this.ivSmall.setImageDrawable(this.smallImages[i]);
        }
    }

    private void initData() {
        SparseArray<RadarData> sparseArray = new SparseArray<>();
        this.mRadarDataArray = sparseArray;
        sparseArray.put(Constant.RadarLocation.FRONT_LEFT.ordinal(), new RadarData("front_radar_left_", "front_radar_left_small_", this.mView.findViewById(R.id.front_radar_left), this.mView.findViewById(R.id.front_radar_left_small)));
        this.mRadarDataArray.append(Constant.RadarLocation.FRONT_MID_LEFT.ordinal(), new RadarData("front_radar_left_mid_", "front_radar_left_mid_small_", this.mView.findViewById(R.id.front_radar_left_mid), this.mView.findViewById(R.id.front_radar_left_mid_small)));
        this.mRadarDataArray.append(Constant.RadarLocation.FRONT_MID_RIGHT.ordinal(), new RadarData("front_radar_right_mid_", "front_radar_right_mid_small_", this.mView.findViewById(R.id.front_radar_right_mid), this.mView.findViewById(R.id.front_radar_right_mid_small)));
        this.mRadarDataArray.append(Constant.RadarLocation.FRONT_RIGHT.ordinal(), new RadarData("front_radar_right_", "front_radar_right_small_", this.mView.findViewById(R.id.front_radar_right), this.mView.findViewById(R.id.front_radar_right_small)));
        this.mRadarDataArray.append(Constant.RadarLocation.FRONT_LEFT_PROBE.ordinal(), new RadarData("_01_zc_zqx_", "_01_zcs_zqx_", this.mView.findViewById(R.id.front_radar_left_probe), this.mView.findViewById(R.id.front_radar_left_small_probe)));
        this.mRadarDataArray.append(Constant.RadarLocation.FRONT_RIGHT_PROBE.ordinal(), new RadarData("_01_zc_zq_", "_01_zcs_zq_", this.mView.findViewById(R.id.front_radar_right_probe), this.mView.findViewById(R.id.front_radar_right_small_probe)));
        this.mRadarDataArray.append(Constant.RadarLocation.REAR_LEFT.ordinal(), new RadarData("back_radar_left_", "back_radar_left_small_", this.mView.findViewById(R.id.back_radar_left), this.mView.findViewById(R.id.back_radar_left_small)));
        this.mRadarDataArray.append(Constant.RadarLocation.REAR_MID_LEFT.ordinal(), new RadarData("back_radar_left_mid_", "back_radar_left_mid_small_", this.mView.findViewById(R.id.back_radar_left_mid), this.mView.findViewById(R.id.back_radar_left_mid_small)));
        this.mRadarDataArray.append(Constant.RadarLocation.REAR_MID_RIGHT.ordinal(), new RadarData("back_radar_right_mid_", "back_radar_right_mid_small_", this.mView.findViewById(R.id.back_radar_right_mid), this.mView.findViewById(R.id.back_radar_right_mid_small)));
        this.mRadarDataArray.append(Constant.RadarLocation.REAR_RIGHT.ordinal(), new RadarData("back_radar_right_", "back_radar_right_small_", this.mView.findViewById(R.id.back_radar_right), this.mView.findViewById(R.id.back_radar_right_small)));
        this.mRadarDataArray.append(Constant.RadarLocation.REAR_LEFT_PROBE.ordinal(), new RadarData("_01_zcs_yhx_", "_01_zc_yhx_", this.mView.findViewById(R.id.back_radar_left_probe), this.mView.findViewById(R.id.back_radar_left_probe_small)));
        this.mRadarDataArray.append(Constant.RadarLocation.REAR_RIGHT_PROBE.ordinal(), new RadarData("_01_zcs_yh_", "_01_zc_yh_", this.mView.findViewById(R.id.back_radar_right_probe), this.mView.findViewById(R.id.back_radar_right_probe_small)));
        this.mRadarDataArray.append(Constant.RadarLocation.LEFT_FRONT.ordinal(), new RadarData("left_radar_front_", "left_radar_front_small_", this.mView.findViewById(R.id.left_radar_front), this.mView.findViewById(R.id.left_radar_front_small)));
        this.mRadarDataArray.append(Constant.RadarLocation.LEFT_MID_FRONT.ordinal(), new RadarData("left_radar_front_mid_", "left_radar_front_mid_small_", this.mView.findViewById(R.id.left_radar_front_mid), this.mView.findViewById(R.id.left_radar_front_mid_small)));
        this.mRadarDataArray.append(Constant.RadarLocation.LEFT_MID_REAR.ordinal(), new RadarData("left_radar_rear_mid_", "left_radar_rear_mid_small_", this.mView.findViewById(R.id.left_radar_rear_mid), this.mView.findViewById(R.id.left_radar_rear_mid_small)));
        this.mRadarDataArray.append(Constant.RadarLocation.LEFT_REAR.ordinal(), new RadarData("left_radar_rear_", "left_radar_rear_small_", this.mView.findViewById(R.id.left_radar_rear), this.mView.findViewById(R.id.left_radar_rear_small)));
        this.mRadarDataArray.append(Constant.RadarLocation.RIGHT_FRONT.ordinal(), new RadarData("right_radar_front_", "right_radar_front_small_", this.mView.findViewById(R.id.right_radar_front), this.mView.findViewById(R.id.right_radar_front_mid_small)));
        this.mRadarDataArray.append(Constant.RadarLocation.RIGHT_MID_FRONT.ordinal(), new RadarData("right_radar_front_mid_", "right_radar_front_mid_small_", this.mView.findViewById(R.id.right_radar_front_mid), this.mView.findViewById(R.id.right_radar_front_small)));
        this.mRadarDataArray.append(Constant.RadarLocation.RIGHT_MID_REAR.ordinal(), new RadarData("right_radar_rear_mid_", "right_radar_rear_mid_small_", this.mView.findViewById(R.id.right_radar_rear_mid), this.mView.findViewById(R.id.right_radar_rear_mid_small)));
        this.mRadarDataArray.append(Constant.RadarLocation.RIGHT_REAR.ordinal(), new RadarData("right_radar_rear_", "right_radar_rear_small_", this.mView.findViewById(R.id.right_radar_rear), this.mView.findViewById(R.id.right_radar_rear_small)));
    }
}
