package com.hzbhd.canbus.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import com.hzbhd.R;
import com.hzbhd.canbus.interfaces.OnBubbleClickListener;
import com.hzbhd.canbus.ui_datas.GeneralBubbleData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_mgr.UiMgrInterfaceUtil;
import com.hzbhd.canbus.ui_set.BubbleUiSet;
import com.hzbhd.canbus.util.CommUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: Bubble.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u000eH\u0002J\u0006\u0010\u0010\u001a\u00020\u000eR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/hzbhd/canbus/view/Bubble;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mIsShowing", "", "mView", "Landroid/view/View;", "mWindowManager", "Landroid/view/WindowManager;", "mWmParam", "Landroid/view/WindowManager$LayoutParams;", "hideBubble", "", "showBubble", "updateBubble", "Companion", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class Bubble {
    private static final int MOVE_ICON_COUNT = 16;
    private static final String TAG = "Bubble";
    private boolean mIsShowing;
    private View mView;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mWmParam;

    public Bubble(Context context) {
        final BubbleUiSet bubbleUiSet;
        Intrinsics.checkNotNullParameter(context, "context");
        Log.i(TAG, "init");
        UiMgrInterfaceUtil canUiMgrUtil = UiMgrFactory.getCanUiMgrUtil(context);
        if (canUiMgrUtil == null || (bubbleUiSet = canUiMgrUtil.getBubbleUiSet(context)) == null) {
            return;
        }
        Log.i(TAG, "have BubbleUiSet");
        Object systemService = context.getSystemService("window");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.WindowManager");
        this.mWindowManager = (WindowManager) systemService;
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.type = 2001;
        layoutParams.format = 1;
        layoutParams.flags = 8;
        layoutParams.gravity = 8388659;
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.y = 300;
        this.mWmParam = layoutParams;
        final View viewInflate = LayoutInflater.from(context).inflate(R.layout.layout_bubble, (ViewGroup) null);
        ((ImageView) viewInflate.findViewById(R.id.iv_bubble_icon)).setImageResource(CommUtil.getImgIdByResId(context, bubbleUiSet.getIconDrawable()));
        final int dimension = (int) viewInflate.getResources().getDimension(R.dimen.system_ui_height);
        final Ref.IntRef intRef = new Ref.IntRef();
        viewInflate.setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.view.Bubble$$ExternalSyntheticLambda0
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return Bubble.m1179lambda4$lambda3$lambda2(intRef, viewInflate, this, bubbleUiSet, dimension, view, motionEvent);
            }
        });
        this.mView = viewInflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-4$lambda-3$lambda-2, reason: not valid java name */
    public static final boolean m1179lambda4$lambda3$lambda2(Ref.IntRef notActionUpCount, View view, Bubble this$0, BubbleUiSet this_run, int i, View view2, MotionEvent motionEvent) {
        OnBubbleClickListener onBubbleClickListener;
        WindowManager windowManager;
        Intrinsics.checkNotNullParameter(notActionUpCount, "$notActionUpCount");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        if (motionEvent.getAction() != 1) {
            notActionUpCount.element++;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            ((ImageView) view.findViewById(R.id.iv_bubble_icon)).setPressed(true);
        } else if (action != 1) {
            if (action == 2 && notActionUpCount.element > 16 && (windowManager = this$0.mWindowManager) != null) {
                WindowManager.LayoutParams layoutParams = this$0.mWmParam;
                if (layoutParams != null) {
                    layoutParams.x = ((int) motionEvent.getRawX()) - (view2.getWidth() / 2);
                    layoutParams.y = (((int) motionEvent.getRawY()) - (view2.getHeight() / 2)) - i;
                    Unit unit = Unit.INSTANCE;
                } else {
                    layoutParams = null;
                }
                windowManager.updateViewLayout(view, layoutParams);
            }
        } else {
            ((ImageView) view.findViewById(R.id.iv_bubble_icon)).setPressed(false);
            if (notActionUpCount.element <= 16 && (onBubbleClickListener = this_run.getOnBubbleClickListener()) != null) {
                onBubbleClickListener.onClick();
            }
            notActionUpCount.element = 0;
        }
        return true;
    }

    public final void updateBubble() {
        if (GeneralBubbleData.isShowBubble) {
            showBubble();
        } else {
            hideBubble();
        }
    }

    private final void showBubble() {
        Log.i(TAG, "showBubble: mIsShowing " + this.mIsShowing);
        if (this.mIsShowing) {
            return;
        }
        Log.i(TAG, "showBubble: mWindowManager ----{}" + this.mWindowManager);
        WindowManager windowManager = this.mWindowManager;
        if (windowManager != null) {
            windowManager.addView(this.mView, this.mWmParam);
        }
        this.mIsShowing = true;
    }

    private final void hideBubble() {
        Log.i(TAG, "hideBubble: mIsShowing " + this.mIsShowing);
        if (this.mIsShowing) {
            Log.i(TAG, "hideBubble: mWindowManager ----{}" + this.mWindowManager);
            WindowManager windowManager = this.mWindowManager;
            if (windowManager != null) {
                windowManager.removeView(this.mView);
            }
            this.mIsShowing = false;
        }
    }
}
