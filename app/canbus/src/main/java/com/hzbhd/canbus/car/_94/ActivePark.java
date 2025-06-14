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
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.constant.share.lcd.LcdInfoShare;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ActivePark.kt */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0003*+,B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\u0016\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u001aJ\u0006\u0010\u001b\u001a\u00020\u000eJ\b\u0010\u001c\u001a\u00020\u0017H\u0002J\b\u0010\u001d\u001a\u00020\u0017H\u0002J\u000e\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020\nJ\u0010\u0010 \u001a\u00020\u00172\u0006\u0010!\u001a\u00020\u001aH\u0002J\u0010\u0010 \u001a\u00020\u00172\u0006\u0010\"\u001a\u00020#H\u0002J\u0010\u0010$\u001a\u00020\u00172\u0006\u0010!\u001a\u00020\u001aH\u0002J\b\u0010%\u001a\u00020\u0017H\u0002J\u0006\u0010&\u001a\u00020\u0017J\u000e\u0010'\u001a\u00020\u00172\u0006\u0010(\u001a\u00020)R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u00100\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lcom/hzbhd/canbus/car/_94/ActivePark;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mActiveParkView", "Lcom/hzbhd/canbus/car/_94/ActivePark$ActiveParkView;", "mHandler", "Landroid/os/Handler;", "mIsActiveViewOpen", "", "mLayoutParams", "Landroid/view/WindowManager$LayoutParams;", "mPanoramicView", "Lcom/hzbhd/canbus/car/_94/ActivePark$MyPanoramicView;", "mParkPageUiSet", "Lcom/hzbhd/canbus/ui_set/ParkPageUiSet;", "kotlin.jvm.PlatformType", "mTimerUtil", "Lcom/hzbhd/canbus/util/TimerUtil;", "mWindowManager", "Landroid/view/WindowManager;", "closeActiveParkView", "", "countDown", "messageResId", "", "getPanoramicView", "hideAlertWindow", "openActiveParkView", "setActiveParkActive", "isActive", "setAlertMessage", "resid", "text", "", "setTipMessage", "showAlertWindow", "stopTimer", "update", "beam", "Lcom/hzbhd/canbus/car/_94/ActivePark$ActiveParkBeam;", "ActiveParkBeam", "ActiveParkView", "MyPanoramicView", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
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
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService("window");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.WindowManager");
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
        Intrinsics.checkNotNullParameter(beam, "beam");
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
        Intrinsics.checkNotNullParameter(context, "context");
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
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\r\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\u000e\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tJ\u0006\u0010\u000f\u001a\u00020\u0006¨\u0006\u0010"}, d2 = {"Lcom/hzbhd/canbus/car/_94/ActivePark$ActiveParkView;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "hideAlertWindow", "", "setAlertMessage", "resid", "", "text", "", "setLeftSideImage", "setRightSideImage", "setTipMessage", "showAlertWindow", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
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
            Intrinsics.checkNotNullParameter(text, "text");
            ((TextView) _$_findCachedViewById(R.id.tv_alert_message)).setText(text);
        }
    }

    /* compiled from: ActivePark.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\n\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\tJ\u000e\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fJ\u0016\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u0006¨\u0006\u0016"}, d2 = {"Lcom/hzbhd/canbus/car/_94/ActivePark$MyPanoramicView;", "Landroid/widget/RelativeLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "hideAlertWindow", "", "refreshIcon", LcdInfoShare.MediaInfo.name, "", "setAlertMessage", "resid", "", "text", "setTipMessage", "setVisible", "view", "Landroid/view/View;", "visible", "", "showAlertWindow", "Companion", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
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
            Intrinsics.checkNotNullParameter(text, "text");
            ((TextView) _$_findCachedViewById(R.id.tv_alert_message)).setText(text);
        }

        public final void refreshIcon(String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            ImageView iv_left_radar = (ImageView) _$_findCachedViewById(R.id.iv_left_radar);
            Intrinsics.checkNotNullExpressionValue(iv_left_radar, "iv_left_radar");
            setVisible(iv_left_radar, Intrinsics.areEqual(LEFT_RADAR, name));
            ImageView iv_right_radar = (ImageView) _$_findCachedViewById(R.id.iv_right_radar);
            Intrinsics.checkNotNullExpressionValue(iv_right_radar, "iv_right_radar");
            setVisible(iv_right_radar, Intrinsics.areEqual(RIGHT_RADAR, name));
            ImageView iv_left_forward = (ImageView) _$_findCachedViewById(R.id.iv_left_forward);
            Intrinsics.checkNotNullExpressionValue(iv_left_forward, "iv_left_forward");
            setVisible(iv_left_forward, Intrinsics.areEqual(LEFT_FORWARD, name));
            ImageView iv_left_backward = (ImageView) _$_findCachedViewById(R.id.iv_left_backward);
            Intrinsics.checkNotNullExpressionValue(iv_left_backward, "iv_left_backward");
            setVisible(iv_left_backward, Intrinsics.areEqual(LEFT_BACKWARD, name));
            ImageView iv_left_stop = (ImageView) _$_findCachedViewById(R.id.iv_left_stop);
            Intrinsics.checkNotNullExpressionValue(iv_left_stop, "iv_left_stop");
            setVisible(iv_left_stop, Intrinsics.areEqual(LEFT_STOP, name));
            TextView tv_left_reverse = (TextView) _$_findCachedViewById(R.id.tv_left_reverse);
            Intrinsics.checkNotNullExpressionValue(tv_left_reverse, "tv_left_reverse");
            setVisible(tv_left_reverse, Intrinsics.areEqual(LEFT_REVERSE, name));
        }

        public final void setVisible(View view, boolean visible) {
            Intrinsics.checkNotNullParameter(view, "view");
            if (visible) {
                view.setVisibility(0);
            } else {
                view.setVisibility(8);
            }
        }
    }

    /* compiled from: ActivePark.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000bJ\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000bJ\u000b\u0010\u0015\u001a\u0004\u0018\u00010\bHÆ\u0003J<\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001¢\u0006\u0002\u0010\u0017J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001c\u001a\u00020\bHÖ\u0001R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u0011\u0010\u000b¨\u0006\u001d"}, d2 = {"Lcom/hzbhd/canbus/car/_94/ActivePark$ActiveParkBeam;", "", "messageResId", "", "(I)V", "leftImageResId", "rightImageResId", "reverseIcon", "", "(ILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;)V", "getLeftImageResId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getMessageResId", "()I", "getReverseIcon", "()Ljava/lang/String;", "getRightImageResId", "component1", "component2", "component3", "component4", "copy", "(ILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;)Lcom/hzbhd/canbus/car/_94/ActivePark$ActiveParkBeam;", "equals", "", "other", "hashCode", "toString", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
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
            return this.messageResId == activeParkBeam.messageResId && Intrinsics.areEqual(this.leftImageResId, activeParkBeam.leftImageResId) && Intrinsics.areEqual(this.rightImageResId, activeParkBeam.rightImageResId) && Intrinsics.areEqual(this.reverseIcon, activeParkBeam.reverseIcon);
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
