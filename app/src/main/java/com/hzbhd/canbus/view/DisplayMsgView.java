package com.hzbhd.canbus.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.ui_datas.GeneralDisplayMsgData;

/* loaded from: classes2.dex */
public class DisplayMsgView {
    private Context mContext;
    private LinearLayout mFloatView;
    private WindowManager.LayoutParams mLayoutParams;
    private TextView mTv;
    private WindowManager mWindowManager;
    private boolean isShowing = false;
    private Runnable mRunnable = new Runnable() { // from class: com.hzbhd.canbus.view.DisplayMsgView.1
        @Override // java.lang.Runnable
        public void run() {
            DisplayMsgView.this.dismissView();
        }
    };

    public DisplayMsgView(Context context) {
        this.mContext = context;
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        initView();
    }

    private void initView() {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(this.mContext).inflate(R.layout.display_message_panel, (ViewGroup) null);
        this.mFloatView = linearLayout;
        this.mTv = (TextView) linearLayout.findViewById(R.id.tv_display_msg);
    }

    public void refreshUi() {
        if (GeneralDisplayMsgData.displayMsg == null) {
            return;
        }
        this.mTv.setText(GeneralDisplayMsgData.displayMsg);
        if (this.mLayoutParams == null) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            this.mLayoutParams = layoutParams;
            layoutParams.type = 2002;
            this.mLayoutParams.gravity = 17;
            this.mLayoutParams.width = -2;
            this.mLayoutParams.height = -2;
        }
        if (!this.isShowing) {
            this.mWindowManager.addView(this.mFloatView, this.mLayoutParams);
            this.isShowing = true;
        }
        this.mFloatView.removeCallbacks(this.mRunnable);
        this.mFloatView.postDelayed(this.mRunnable, 2000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissView() {
        LinearLayout linearLayout;
        WindowManager windowManager = this.mWindowManager;
        if (windowManager == null || (linearLayout = this.mFloatView) == null) {
            return;
        }
        windowManager.removeView(linearLayout);
        this.isShowing = false;
    }
}
