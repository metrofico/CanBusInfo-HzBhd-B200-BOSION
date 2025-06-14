package com.hzbhd.canbus.car_cus._304.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._304.data.MyGeneralData;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import java.util.HashMap;

/* loaded from: classes2.dex */
public class MyRadarView extends RelativeLayout {
    private final String TAG;
    private Button mBtnAll;
    private ImageButton mIbFront;
    private ImageButton mIbLeft;
    private ImageButton mIbRear;
    private ImageButton mIbRight;
    private ImageButton mIbVoice;
    private boolean mIsAllView;
    private ImageView mIvWareFrontLeft;
    private ImageView mIvWareFrontLeftMid;
    private ImageView mIvWareFrontRight;
    private ImageView mIvWareFrontRightMid;
    private ImageView mIvWareRearLeft;
    private ImageView mIvWareRearLeftMid;
    private ImageView mIvWareRearRight;
    private ImageView mIvWareRearRightMid;
    private SparseArray<RadarUpdateHelper> mRadarUpdateHelperArray;
    private View mView;

    public MyRadarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = "_304_RadarView";
        this.mIsAllView = true;
        this.mView = LayoutInflater.from(context).inflate(R.layout._304_view_radar, this);
        findViews();
    }

    private void findViews() {
        this.mIbFront = (ImageButton) this.mView.findViewById(R.id.ib_front);
        this.mIbLeft = (ImageButton) this.mView.findViewById(R.id.ib_left);
        this.mIbRight = (ImageButton) this.mView.findViewById(R.id.ib_right);
        this.mIbRear = (ImageButton) this.mView.findViewById(R.id.ib_rear);
        this.mBtnAll = (Button) this.mView.findViewById(R.id.btn_all);
        this.mIbVoice = (ImageButton) this.mView.findViewById(R.id.ib_voice);
        this.mIvWareFrontLeft = (ImageView) this.mView.findViewById(R.id.iv_ware_front_left);
        this.mIvWareFrontLeftMid = (ImageView) this.mView.findViewById(R.id.iv_ware_front_left_mid);
        this.mIvWareFrontRightMid = (ImageView) this.mView.findViewById(R.id.iv_ware_front_right_mid);
        this.mIvWareFrontRight = (ImageView) this.mView.findViewById(R.id.iv_ware_front_right);
        this.mIvWareRearLeft = (ImageView) this.mView.findViewById(R.id.iv_ware_rear_left);
        this.mIvWareRearLeftMid = (ImageView) this.mView.findViewById(R.id.iv_ware_rear_left_mid);
        this.mIvWareRearRightMid = (ImageView) this.mView.findViewById(R.id.iv_ware_rear_right_mid);
        this.mIvWareRearRight = (ImageView) this.mView.findViewById(R.id.iv_ware_rear_right);
    }

    public void initViews(final OnPanoramicItemClickListener onPanoramicItemClickListener) {
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._304.view.MyRadarView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m1135x47ed391c(onPanoramicItemClickListener, view);
            }
        };
        this.mIbFront.setOnClickListener(onClickListener);
        this.mIbLeft.setOnClickListener(onClickListener);
        this.mIbRight.setOnClickListener(onClickListener);
        this.mIbRear.setOnClickListener(onClickListener);
        this.mBtnAll.setOnClickListener(onClickListener);
        this.mIbVoice.setOnClickListener(onClickListener);
        SparseArray<RadarUpdateHelper> sparseArray = new SparseArray<>();
        this.mRadarUpdateHelperArray = sparseArray;
        sparseArray.put(Constant.RadarLocation.FRONT_LEFT.ordinal(), new RadarUpdateHelper(this.mIvWareFrontLeft));
        this.mRadarUpdateHelperArray.put(Constant.RadarLocation.FRONT_MID_LEFT.ordinal(), new RadarUpdateHelper(this.mIvWareFrontLeftMid));
        this.mRadarUpdateHelperArray.put(Constant.RadarLocation.FRONT_MID_RIGHT.ordinal(), new RadarUpdateHelper(this.mIvWareFrontRightMid));
        this.mRadarUpdateHelperArray.put(Constant.RadarLocation.FRONT_RIGHT.ordinal(), new RadarUpdateHelper(this.mIvWareFrontRight));
        this.mRadarUpdateHelperArray.put(Constant.RadarLocation.REAR_LEFT.ordinal(), new RadarUpdateHelper(this.mIvWareRearLeft));
        this.mRadarUpdateHelperArray.put(Constant.RadarLocation.REAR_MID_LEFT.ordinal(), new RadarUpdateHelper(this.mIvWareRearLeftMid));
        this.mRadarUpdateHelperArray.put(Constant.RadarLocation.REAR_MID_RIGHT.ordinal(), new RadarUpdateHelper(this.mIvWareRearRightMid));
        this.mRadarUpdateHelperArray.put(Constant.RadarLocation.REAR_RIGHT.ordinal(), new RadarUpdateHelper(this.mIvWareRearRight));
        int currentCanDiffId = SelectCanTypeUtil.getCurrentCanDiffId();
        Log.i("_304_RadarView", "initViews: differentId <--> " + currentCanDiffId);
        if (currentCanDiffId == 0) {
            this.mIbFront.setVisibility(4);
            this.mIbLeft.setVisibility(4);
            this.mIbRight.setVisibility(4);
            this.mIbRear.setVisibility(4);
            this.mBtnAll.setVisibility(4);
            this.mIbVoice.setVisibility(4);
            this.mIvWareFrontLeft.setVisibility(4);
            this.mIvWareFrontRight.setVisibility(4);
        }
    }

    /* renamed from: lambda$initViews$0$com-hzbhd-canbus-car_cus-_304-view-MyRadarView, reason: not valid java name */
    /* synthetic */ void m1135x47ed391c(OnPanoramicItemClickListener onPanoramicItemClickListener, View view) {
        if (view.equals(this.mIbFront)) {
            onPanoramicItemClickListener.onClickItem(0);
            return;
        }
        if (view.equals(this.mIbRear)) {
            onPanoramicItemClickListener.onClickItem(1);
            return;
        }
        if (view.equals(this.mIbLeft)) {
            onPanoramicItemClickListener.onClickItem(2);
            return;
        }
        if (view.equals(this.mIbRight)) {
            onPanoramicItemClickListener.onClickItem(3);
            return;
        }
        if (view.equals(this.mBtnAll)) {
            Log.i("_304_RadarView", "initViews: mIsAllView <--> " + this.mIsAllView);
            if (this.mIsAllView) {
                onPanoramicItemClickListener.onClickItem(7);
                this.mBtnAll.setText(R.string._304_avm_simgle);
                this.mIsAllView = false;
                return;
            } else {
                onPanoramicItemClickListener.onClickItem(4);
                this.mBtnAll.setText(R.string._304_avm_all);
                this.mIsAllView = true;
                return;
            }
        }
        if (view.equals(this.mIbVoice)) {
            onPanoramicItemClickListener.onClickItem(5);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mBtnAll.setText(R.string._304_avm_all);
        this.mIsAllView = true;
    }

    public void refreshRadar() {
        HashMap<Constant.RadarLocation, Integer> map = MyGeneralData.mRadarLocationMap;
        for (Constant.RadarLocation radarLocation : Constant.RadarLocation.values()) {
            if (map.containsKey(radarLocation)) {
                RadarUpdateHelper radarUpdateHelper = this.mRadarUpdateHelperArray.get(radarLocation.ordinal());
                if (radarUpdateHelper.isDataChange(map.get(radarLocation).intValue())) {
                    radarUpdateHelper.updateRadar();
                }
            }
        }
    }

    private class RadarUpdateHelper {
        private ImageView radar;
        private int value;

        public RadarUpdateHelper(ImageView imageView) {
            this.radar = imageView;
        }

        public void updateRadar() {
            this.radar.setImageLevel(this.value);
        }

        public boolean isDataChange(int i) {
            if (this.value == i) {
                return false;
            }
            this.value = i;
            return true;
        }
    }
}
