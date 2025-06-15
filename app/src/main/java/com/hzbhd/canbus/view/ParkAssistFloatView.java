package com.hzbhd.canbus.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hzbhd.R;
import com.hzbhd.canbus.adapter.util.Util;
import com.hzbhd.canbus.util.CommUtil;

/* loaded from: classes2.dex */
public class ParkAssistFloatView {
    private static final int MAX_CMD_RANGE = 39;
    public static final String REFRESH_UI_BUNDLE_KEY = "PARK_ASSIST_REFRESH_UI_BUNDLE_KEY";
    private static final int TYPE_CAR_IMG_IN_LEFT = 1;
    private static final int TYPE_CAR_IMG_IN_RIGHT = 2;
    private static final int TYPE_NO_CAR_IMG = 0;
    private static final int TYPE_PARKASSIT_DISABLE = 3;
    private ImageView img_ford_pa_tips_icon;
    private ImageView img_left_ford_pa;
    private ImageView img_right_ford_pa;
    private final Context mContext;
    private LinearLayout mFloatLayout;
    private WindowManager mWindowManager;
    private TextView text_ford_pa_tips;
    private WindowManager.LayoutParams wmParams;
    private boolean mHaveAdded = false;
    private final int[] carInLeftIds = {2, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 35, 37};
    private final int[] carInRightIds = {4, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 27, 36, 38};
    private final int[] noCarIds = {6, 29, 30, 31, 32, 33, 34, 39};
    private final int[] iconStopIds = {11, 12, 13, 14, 19, 20, 23, 24};
    private final int[] iconForwardIds = {9, 10, 21, 22, 35, 36};
    private final int[] iconBackwardIds = {15, 16, 17, 18, 25, 26, 37, 38};
    private final Runnable mRunnable = new Runnable() { // from class: com.hzbhd.canbus.view.ParkAssistFloatView.1
        @Override // java.lang.Runnable
        public void run() {
            ParkAssistFloatView.this.dismissView();
        }
    };

    public ParkAssistFloatView(Context context) {
        this.mContext = context;
        initFloatView();
        initWidegets();
    }

    private int[] getCarInLeftIds() {
        return this.carInLeftIds;
    }

    private int[] getCarInRightIds() {
        return this.carInRightIds;
    }

    private int[] getNoCarIds() {
        return this.noCarIds;
    }

    private int[] getIconStopIds() {
        return this.iconStopIds;
    }

    private int[] getIconForwardIds() {
        return this.iconForwardIds;
    }

    private int[] getIconBackwardIds() {
        return this.iconBackwardIds;
    }

    private String cmdHexString(byte b) {
        return "0x" + Util.byte2HexString(b);
    }

    private int getTipsImageResId(byte b) {
        for (int i = 0; i < getIconStopIds().length; i++) {
            if (b == getIconStopIds()[i]) {
                return R.drawable.ford_pa_stop;
            }
        }
        for (int i2 = 0; i2 < getIconForwardIds().length; i2++) {
            if (b == getIconForwardIds()[i2]) {
                return R.drawable.ford_pa_forward;
            }
        }
        for (int i3 = 0; i3 < getIconBackwardIds().length; i3++) {
            if (b == getIconBackwardIds()[i3]) {
                return R.drawable.ford_pa_backward;
            }
        }
        return R.drawable.ford_sync_icon_null;
    }

    private int getCmdType(byte b) {
        for (int i = 0; i < getCarInLeftIds().length; i++) {
            if (b == getCarInLeftIds()[i]) {
                return 1;
            }
        }
        for (int i2 = 0; i2 < getCarInRightIds().length; i2++) {
            if (b == getCarInRightIds()[i2]) {
                return 2;
            }
        }
        for (int i3 = 0; i3 < getNoCarIds().length; i3++) {
            if (b == getNoCarIds()[i3]) {
                return 0;
            }
        }
        return 3;
    }

    public void refreshUi(Bundle bundle) {
        byte b = bundle.getByte(REFRESH_UI_BUNDLE_KEY);
        int cmdType = getCmdType(b);
        int tipsImageResId = getTipsImageResId(b);
        if (cmdType == 0) {
            this.img_left_ford_pa.setVisibility(View.INVISIBLE);
            this.img_right_ford_pa.setVisibility(View.INVISIBLE);
            this.img_ford_pa_tips_icon.setImageResource(tipsImageResId);
            this.text_ford_pa_tips.setText(CommUtil.getStrIdByResId(this.mContext, "ford_park_assist_" + cmdHexString(b)));
        } else if (cmdType == 1) {
            this.img_left_ford_pa.setVisibility(View.VISIBLE);
            this.img_right_ford_pa.setVisibility(View.INVISIBLE);
            this.img_ford_pa_tips_icon.setImageResource(tipsImageResId);
            this.img_left_ford_pa.setImageResource(CommUtil.getImgIdByResId(this.mContext, "ford_pa_" + cmdHexString(b)));
            this.text_ford_pa_tips.setText(CommUtil.getStrIdByResId(this.mContext, "ford_park_assist_" + cmdHexString(b)));
        } else if (cmdType == 2) {
            this.img_left_ford_pa.setVisibility(View.INVISIBLE);
            this.img_right_ford_pa.setVisibility(View.VISIBLE);
            this.img_ford_pa_tips_icon.setImageResource(tipsImageResId);
            this.img_right_ford_pa.setImageResource(CommUtil.getImgIdByResId(this.mContext, "ford_pa_" + cmdHexString(b)));
            this.text_ford_pa_tips.setText(CommUtil.getStrIdByResId(this.mContext, "ford_park_assist_" + cmdHexString(b)));
        } else {
            dismissCanbusAirInfoPanel();
        }
        if (!this.mHaveAdded) {
            this.mWindowManager.addView(this.mFloatLayout, this.wmParams);
            this.mHaveAdded = true;
        }
        this.mFloatLayout.removeCallbacks(this.mRunnable);
        this.mFloatLayout.postDelayed(this.mRunnable, 5000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissView() {
        LinearLayout linearLayout;
        WindowManager windowManager = this.mWindowManager;
        if (windowManager == null || (linearLayout = this.mFloatLayout) == null) {
            return;
        }
        windowManager.removeView(linearLayout);
        this.mHaveAdded = false;
    }

    @SuppressLint("WrongConstant")
    private void initFloatView() {
        this.wmParams = new WindowManager.LayoutParams();
        this.mWindowManager = (WindowManager) this.mContext.getSystemService(Context.WINDOW_SERVICE);
        this.wmParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
        this.wmParams.format = 1;
        this.wmParams.windowAnimations = android.R.style.Animation_Toast;
        this.wmParams.gravity = 17;
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.mContext);
        this.wmParams.width = -2;
        this.wmParams.height = -2;
        this.mFloatLayout = (LinearLayout) layoutInflaterFrom.inflate(R.layout.ford_park_assist_flow_window, null);
    }

    private void initWidegets() {
        this.img_left_ford_pa = this.mFloatLayout.findViewById(R.id.img_left_ford_pa);
        this.img_right_ford_pa = this.mFloatLayout.findViewById(R.id.img_right_ford_pa);
        this.text_ford_pa_tips = this.mFloatLayout.findViewById(R.id.text_ford_pa_tips);
        this.img_ford_pa_tips_icon = this.mFloatLayout.findViewById(R.id.img_ford_pa_tips_icon);
    }

    private void dismissCanbusAirInfoPanel() {
        LinearLayout linearLayout;
        WindowManager windowManager = this.mWindowManager;
        if (windowManager == null || !this.mHaveAdded || (linearLayout = this.mFloatLayout) == null) {
            return;
        }
        windowManager.removeView(linearLayout);
        this.mHaveAdded = false;
    }
}
