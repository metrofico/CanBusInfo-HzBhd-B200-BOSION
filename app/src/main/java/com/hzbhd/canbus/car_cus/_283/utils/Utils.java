package com.hzbhd.canbus.car_cus._283.utils;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.car_cus._283.view.CarImageView;

/* loaded from: classes2.dex */
public class Utils {
    public static int[] calculatePopWindowPos(View view, View view2) {
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        view.getLocationOnScreen(iArr2);
        int height = view.getHeight();
        int screenHeight = getScreenHeight(view.getContext());
        int screenWidth = getScreenWidth(view.getContext());
        view2.measure(0, 0);
        int measuredHeight = view2.getMeasuredHeight();
        int measuredWidth = view2.getMeasuredWidth();
        int i = iArr2[1];
        if ((screenHeight - i) - height < measuredHeight) {
            iArr[0] = screenWidth - measuredWidth;
            iArr[1] = i - measuredHeight;
        } else {
            iArr[0] = screenWidth - measuredWidth;
            iArr[1] = i + height;
        }
        return iArr;
    }

    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static void setCarResidu(CarImageView carImageView, String str) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) carImageView.getLayoutParams();
        layoutParams.leftMargin = getStartMargin(str);
        carImageView.setLayoutParams(layoutParams);
    }

    private static int getStartMargin(String str) throws NumberFormatException {
        try {
            int i = Integer.parseInt(str);
            if (i > 650) {
                return 30;
            }
            return (650 - i) + 30;
        } catch (Exception e) {
            e.printStackTrace();
            return 30;
        }
    }
}
