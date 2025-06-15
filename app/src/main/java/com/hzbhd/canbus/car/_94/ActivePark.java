package com.hzbhd.canbus.car._94;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hzbhd.R;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.constant.share.lcd.LcdInfoShare;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;




public final class ActivePark {
    private final ActiveParkView mActiveParkView;
    private final Handler mHandler;
    private boolean mIsActiveViewOpen;
    private final WindowManager.LayoutParams mLayoutParams;
    private final MyPanoramicView mPanoramicView;
    private final ParkPageUiSet mParkPageUiSet;
    private final TimerUtil mTimerUtil;
    private final WindowManager mWindowManager;

    public ActivePark(Context context) {

        Object systemService = context.getSystemService("window");

        this.mWindowManager = (WindowManager) systemService;
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.type = 2002;
        layoutParams.gravity = 17;
        layoutParams.width = -1;
        layoutParams.height = -1;
        this.mLayoutParams = layoutParams;
        this.mActiveParkView = new ActiveParkView(context);
        this.mPanoramicView = new MyPanoramicView(context);
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mParkPageUiSet = UiMgrFactory.getCanUiMgr(context).getParkPageUiSet(context);
        this.mTimerUtil = new TimerUtil();
    }

    /* renamed from: getPanoramicView, reason: from getter */
    public final MyPanoramicView getMPanoramicView() {
        return this.mPanoramicView;
    }

    public final void update(ActiveParkBeam beam) {

        hideAlertWindow();
        if (beam.getLeftImageResId() == null && beam.getRightImageResId() == null) {
            showAlertWindow();
            setAlertMessage(beam.getMessageResId());
            return;
        }
        ActiveParkView activeParkView = this.mActiveParkView;
        Integer leftImageResId = beam.getLeftImageResId();
        int iIntValue = R.drawable.ford_sync_icon_null;
        activeParkView.setLeftSideImage(leftImageResId != null ? leftImageResId.intValue() : R.drawable.ford_sync_icon_null);
        ActiveParkView activeParkView2 = this.mActiveParkView;
        Integer rightImageResId = beam.getRightImageResId();
        if (rightImageResId != null) {
            iIntValue = rightImageResId.intValue();
        }
        activeParkView2.setRightSideImage(iIntValue);
        MyPanoramicView myPanoramicView = this.mPanoramicView;
        String reverseIcon = beam.getReverseIcon();
        if (reverseIcon == null) {
            reverseIcon = "";
        }
        myPanoramicView.refreshIcon(reverseIcon);
        setTipMessage(beam.getMessageResId());
    }

    public final void countDown(Context context, int messageResId) {

        TimerUtil timerUtil = this.mTimerUtil;
        timerUtil.startTimer(new ActivePark$countDown$1$1(context, messageResId, timerUtil, this), 0L, 1000L);
    }

    public final void stopTimer() {
        this.mTimerUtil.stopTimer();
    }

    public final void setActiveParkActive(boolean isActive) {
        if (isActive) {
            openActiveParkView();
            this.mParkPageUiSet.setShowCusPanoramicView(true);
        } else {
            closeActiveParkView();
            this.mParkPageUiSet.setShowCusPanoramicView(false);
        }
    }

    private final void openActiveParkView() {
        if (this.mIsActiveViewOpen) {
            return;
        }
        this.mWindowManager.addView(this.mActiveParkView, this.mLayoutParams);
        this.mIsActiveViewOpen = true;
    }

    private final void closeActiveParkView() {
        if (this.mIsActiveViewOpen) {
            this.mWindowManager.removeView(this.mActiveParkView);
            this.mIsActiveViewOpen = false;
        }
    }

    private final void showAlertWindow() {
        this.mActiveParkView.showAlertWindow();
        this.mPanoramicView.showAlertWindow();
    }

    private final void hideAlertWindow() {
        this.mActiveParkView.hideAlertWindow();
        this.mPanoramicView.hideAlertWindow();
    }

    private final void setAlertMessage(int resid) {
        this.mActiveParkView.setAlertMessage(resid);
        this.mPanoramicView.setAlertMessage(resid);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setAlertMessage(String text) {
        this.mActiveParkView.setAlertMessage(text);
        this.mPanoramicView.setAlertMessage(text);
    }

    private final void setTipMessage(int resid) {
        this.mActiveParkView.setTipMessage(resid);
        this.mPanoramicView.setTipMessage(resid);
    }

    /* compiled from: ActivePark.kt */
    
    public static final class ActiveParkView extends LinearLayout {
        public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

        public void _$_clearFindViewByIdCache() {
            this._$_findViewCache.clear();
        }

        public View _$_findCachedViewById(int i) {
            Map<Integer, View> map = this._$_findViewCache;
            View view = map.get(Integer.valueOf(i));
            if (view != null) {
                return view;
            }
            View viewFindViewById = findViewById(i);
            if (viewFindViewById == null) {
                return null;
            }
            map.put(Integer.valueOf(i), viewFindViewById);
            return viewFindViewById;
        }

        public ActiveParkView(Context context) {
            super(context);
            LayoutInflater.from(context).inflate(R.layout.layout_active_park_81_view, this);
        }

        public final void showAlertWindow() {
            ((LinearLayout) _$_findCachedViewById(R.id.ll_alert)).setVisibility(0);
        }

        public final void hideAlertWindow() {
            ((LinearLayout) _$_findCachedViewById(R.id.ll_alert)).setVisibility(8);
        }

        public final void setLeftSideImage(int resid) {
            ((ImageView) _$_findCachedViewById(R.id.iv_left_side)).setImageResource(resid);
        }

        public final void setRightSideImage(int resid) {
            ((ImageView) _$_findCachedViewById(R.id.iv_right_side)).setImageResource(resid);
        }

        public final void setTipMessage(int resid) {
            ((TextView) _$_findCachedViewById(R.id.tv_message)).setText(resid);
        }

        public final void setAlertMessage(int resid) {
            ((TextView) _$_findCachedViewById(R.id.tv_alert_message)).setText(resid);
        }

        public final void setAlertMessage(String text) {

            ((TextView) _$_findCachedViewById(R.id.tv_alert_message)).setText(text);
        }
    }

    /* compiled from: ActivePark.kt */
    
    public static final class MyPanoramicView extends RelativeLayout {
        public static final String LEFT_BACKWARD = "left_backward";
        public static final String LEFT_FORWARD = "left_forward";
        public static final String LEFT_RADAR = "left_radar";
        public static final String LEFT_REVERSE = "left_reverse";
        public static final String LEFT_STOP = "left_stop";
        public static final String RIGHT_RADAR = "right_radar";
        public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

        public void _$_clearFindViewByIdCache() {
            this._$_findViewCache.clear();
        }

        public View _$_findCachedViewById(int i) {
            Map<Integer, View> map = this._$_findViewCache;
            View view = map.get(Integer.valueOf(i));
            if (view != null) {
                return view;
            }
            View viewFindViewById = findViewById(i);
            if (viewFindViewById == null) {
                return null;
            }
            map.put(Integer.valueOf(i), viewFindViewById);
            return viewFindViewById;
        }

        public MyPanoramicView(Context context) {
            super(context);
            LayoutInflater.from(context).inflate(R.layout.layout_panoramice_81_view, this);
        }

        public final void showAlertWindow() {
            ((LinearLayout) _$_findCachedViewById(R.id.ll_alert)).setVisibility(0);
        }

        public final void hideAlertWindow() {
            ((LinearLayout) _$_findCachedViewById(R.id.ll_alert)).setVisibility(8);
        }

        public final void setTipMessage(int resid) {
            ((TextView) _$_findCachedViewById(R.id.tv_message)).setText(resid);
        }

        public final void setAlertMessage(int resid) {
            ((TextView) _$_findCachedViewById(R.id.tv_alert_message)).setText(resid);
        }

        public final void setAlertMessage(String text) {

            ((TextView) _$_findCachedViewById(R.id.tv_alert_message)).setText(text);
        }

        public final void refreshIcon(String name) {

            ImageView iv_left_radar = (ImageView) _$_findCachedViewById(R.id.iv_left_radar);


            ImageView iv_right_radar = (ImageView) _$_findCachedViewById(R.id.iv_right_radar);


            ImageView iv_left_forward = (ImageView) _$_findCachedViewById(R.id.iv_left_forward);


            ImageView iv_left_backward = (ImageView) _$_findCachedViewById(R.id.iv_left_backward);


            ImageView iv_left_stop = (ImageView) _$_findCachedViewById(R.id.iv_left_stop);


            TextView tv_left_reverse = (TextView) _$_findCachedViewById(R.id.tv_left_reverse);


        }

        public final void setVisible(View view, boolean visible) {

            if (visible) {
                view.setVisibility(0);
            } else {
                view.setVisibility(8);
            }
        }
    }

    /* compiled from: ActivePark.kt */
    
    public static final /* data */ class ActiveParkBeam {
        private final Integer leftImageResId;
        private final int messageResId;
        private final String reverseIcon;
        private final Integer rightImageResId;

        public static /* synthetic */ ActiveParkBeam copy$default(ActiveParkBeam activeParkBeam, int i, Integer num, Integer num2, String str, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = activeParkBeam.messageResId;
            }
            if ((i2 & 2) != 0) {
                num = activeParkBeam.leftImageResId;
            }
            if ((i2 & 4) != 0) {
                num2 = activeParkBeam.rightImageResId;
            }
            if ((i2 & 8) != 0) {
                str = activeParkBeam.reverseIcon;
            }
            return activeParkBeam.copy(i, num, num2, str);
        }

        /* renamed from: component1, reason: from getter */
        public final int getMessageResId() {
            return this.messageResId;
        }

        /* renamed from: component2, reason: from getter */
        public final Integer getLeftImageResId() {
            return this.leftImageResId;
        }

        /* renamed from: component3, reason: from getter */
        public final Integer getRightImageResId() {
            return this.rightImageResId;
        }

        /* renamed from: component4, reason: from getter */
        public final String getReverseIcon() {
            return this.reverseIcon;
        }

        public final ActiveParkBeam copy(int messageResId, Integer leftImageResId, Integer rightImageResId, String reverseIcon) {
            return new ActiveParkBeam(messageResId, leftImageResId, rightImageResId, reverseIcon);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ActiveParkBeam)) {
                return false;
            }
            ActiveParkBeam activeParkBeam = (ActiveParkBeam) other;

        }

        public int hashCode() {
            int iHashCode = Integer.hashCode(this.messageResId) * 31;
            Integer num = this.leftImageResId;
            int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
            Integer num2 = this.rightImageResId;
            int iHashCode3 = (iHashCode2 + (num2 == null ? 0 : num2.hashCode())) * 31;
            String str = this.reverseIcon;
            return iHashCode3 + (str != null ? str.hashCode() : 0);
        }

        public String toString() {
            return "ActiveParkBeam(messageResId=" + this.messageResId + ", leftImageResId=" + this.leftImageResId + ", rightImageResId=" + this.rightImageResId + ", reverseIcon=" + this.reverseIcon + ')';
        }

        public ActiveParkBeam(int i, Integer num, Integer num2, String str) {
            this.messageResId = i;
            this.leftImageResId = num;
            this.rightImageResId = num2;
            this.reverseIcon = str;
        }

        public final Integer getLeftImageResId() {
            return this.leftImageResId;
        }

        public final int getMessageResId() {
            return this.messageResId;
        }

        public final String getReverseIcon() {
            return this.reverseIcon;
        }

        public final Integer getRightImageResId() {
            return this.rightImageResId;
        }

        public ActiveParkBeam(int i) {
            this(i, null, null, null);
        }
    }
}
