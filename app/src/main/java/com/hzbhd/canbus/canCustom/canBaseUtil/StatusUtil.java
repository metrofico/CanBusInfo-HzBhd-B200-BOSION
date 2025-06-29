package com.hzbhd.canbus.canCustom.canBaseUtil;

import android.content.res.Resources;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

/* loaded from: classes.dex */
public class StatusUtil {
    public static void immersiveStatusView(AppCompatActivity appCompatActivity, boolean z, boolean z2, int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = appCompatActivity.getWindow();
            if (z) {
                window.addFlags(Integer.MIN_VALUE);
                if (z2) {
                    window.getDecorView().setSystemUiVisibility(9472);
                } else {
                    window.getDecorView().setSystemUiVisibility(1280);
                }
            } else if (z2) {
                appCompatActivity.getWindow().getDecorView().setSystemUiVisibility(8192);
            }
            window.setStatusBarColor(i);
        }
    }

    public static void immersiveNavigationBar(AppCompatActivity appCompatActivity, boolean z, int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = appCompatActivity.getWindow();
            if (z) {
                window.getDecorView().setSystemUiVisibility(512);
                window.addFlags(Integer.MIN_VALUE);
            }
            window.setNavigationBarColor(i);
        }
    }

    public static void hideNavigationBar(AppCompatActivity appCompatActivity) {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = appCompatActivity.getWindow();
            window.addFlags(Integer.MIN_VALUE);
            window.getDecorView().setSystemUiVisibility(1282);
        }
    }

    public static void fullScreen(AppCompatActivity appCompatActivity, boolean enableFullScreen) {
        Window window = appCompatActivity.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();

        if (enableFullScreen) {
            attributes.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            window.setAttributes(attributes);
            window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        } else {
            attributes.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
            window.setAttributes(attributes);
            window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }

    public static void setStatusBar(AppCompatActivity appCompatActivity) {
        if (Build.VERSION.SDK_INT >= 23) {
            appCompatActivity.getWindow().addFlags(67108864);
            appCompatActivity.getWindow().getDecorView().setSystemUiVisibility(8192);
        }
    }

    public static void hideStatusBar(AppCompatActivity appCompatActivity) {
        if (Build.VERSION.SDK_INT >= 23) {
            appCompatActivity.getWindow().setFlags(1024, 1024);
            appCompatActivity.getWindow().getDecorView().setSystemUiVisibility(8192);
        }
    }

    public static int getStatusBarHeight(AppCompatActivity appCompatActivity) {
        Resources resources = appCompatActivity.getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("status_bar_height", "dimen", "android"));
    }

    public static int getNavigationBarHeight(AppCompatActivity appCompatActivity) {
        Resources resources = appCompatActivity.getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("navigation_bar_height", "dimen", "android"));
    }
}
