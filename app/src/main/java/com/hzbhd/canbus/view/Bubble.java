package com.hzbhd.canbus.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.hzbhd.canbus.R;
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


public final class Bubble {
    private static final int MOVE_ICON_COUNT = 16;
    private static final String TAG = "Bubble";
    private boolean mIsShowing;
    private View mView;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mWmParam;

    @SuppressLint("WrongConstant")
    public Bubble(Context context) {
        final BubbleUiSet bubbleUiSet;
        Log.i(TAG, "init");
        UiMgrInterfaceUtil canUiMgrUtil = UiMgrFactory.getCanUiMgrUtil(context);
        if (canUiMgrUtil == null || (bubbleUiSet = canUiMgrUtil.getBubbleUiSet(context)) == null) {
            return;
        }
        Log.i(TAG, "have BubbleUiSet");
        Object systemService = context.getSystemService(Context.WINDOW_SERVICE);
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
        final View viewInflate = LayoutInflater.from(context).inflate(R.layout.layout_bubble, null);
        ((ImageView) viewInflate.findViewById(R.id.iv_bubble_icon)).setImageResource(CommUtil.getImgIdByResId(context, bubbleUiSet.getIconDrawable()));
        final int dimension = (int) viewInflate.getResources().getDimension(R.dimen.system_ui_height);
        final Ref.IntRef notActionUpCount = new Ref.IntRef();
        viewInflate.setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.view.Bubble$$ExternalSyntheticLambda0
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                OnBubbleClickListener onBubbleClickListener;
                WindowManager windowManager;
                if (motionEvent.getAction() != 1) {
                    notActionUpCount.element++;
                }
                int action = motionEvent.getAction();
                if (action == 0) {
                    view.findViewById(R.id.iv_bubble_icon).setPressed(true);
                } else if (action != 1) {
                    if (action == 2 && notActionUpCount.element > 16 && (windowManager = mWindowManager) != null) {
                        WindowManager.LayoutParams layoutParams = mWmParam;
                        if (layoutParams != null) {
                            layoutParams.x = ((int) motionEvent.getRawX()) - (viewInflate.getWidth() / 2);
                            layoutParams.y = (((int) motionEvent.getRawY()) - (viewInflate.getHeight() / 2)) - dimension;
                        }
                        windowManager.updateViewLayout(view, layoutParams);
                    }
                } else {
                    view.findViewById(R.id.iv_bubble_icon).setPressed(false);
                    if (notActionUpCount.element <= 16 && (onBubbleClickListener = bubbleUiSet.getOnBubbleClickListener()) != null) {
                        onBubbleClickListener.onClick();
                    }
                    notActionUpCount.element = 0;
                }
                return true;
            }
        });
        this.mView = viewInflate;
    }

    public void updateBubble() {
        if (GeneralBubbleData.isShowBubble) {
            showBubble();
        } else {
            hideBubble();
        }
    }

    private void showBubble() {
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

    private void hideBubble() {
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
