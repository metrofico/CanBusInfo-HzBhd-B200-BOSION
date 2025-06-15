package com.hzbhd.canbus.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.hzbhd.R;
import com.hzbhd.canbus.park.radar.PKeyRadarView;

/* loaded from: classes2.dex */
public class PKeyUtil {
    private static PKeyUtil pKeyUtil;
    private boolean addTag = false;
    private final WindowManager.LayoutParams layoutParams;
    private final Context mContext;
    private final WindowManager mWindowManager;
    private final PKeyRadarView pKeyRadarView;
    private final View view;

    public static PKeyUtil getInstance(Context context) {
        if (pKeyUtil == null) {
            pKeyUtil = new PKeyUtil(context);
        }
        return pKeyUtil;
    }

    @SuppressLint("WrongConstant")
    private PKeyUtil(Context context) {
        this.mContext = context;
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.layoutParams = layoutParams;
        layoutParams.gravity = 80;
        this.layoutParams.format = 1;
        this.layoutParams.type = 2003;
        this.layoutParams.flags = 16777512;
        this.layoutParams.x = 0;
        this.layoutParams.y = 0;
        this.layoutParams.width = -1;
        this.layoutParams.height = -1;
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.view_p_key_util, null);
        this.view = viewInflate;
        this.pKeyRadarView = viewInflate.findViewById(R.id.p_key_radar);
    }

    public void show() {
        if (this.addTag) {
            return;
        }
        this.mWindowManager.addView(this.view, this.layoutParams);
        this.addTag = true;
    }

    public void hide() {
        if (this.addTag) {
            this.mWindowManager.removeView(this.view);
            this.addTag = false;
        }
    }

    public void refreshFrontRadar(int i, int i2, int i3, int i4, int i5) {
        PKeyRadarView pKeyRadarView = this.pKeyRadarView;
        if (pKeyRadarView != null) {
            pKeyRadarView.refreshFrontRadar(i, i2, i3, i4, i5);
        }
    }

    public void refreshRearRadar(int i, int i2, int i3, int i4, int i5) {
        PKeyRadarView pKeyRadarView = this.pKeyRadarView;
        if (pKeyRadarView != null) {
            pKeyRadarView.refreshRearRadar(i, i2, i3, i4, i5);
        }
    }
}
