package com.hzbhd.ui.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import com.hzbhd.constant.share.lcd.LcdInfoShare;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import nfore.android.bt.res.NfDef;


public final class ResourcesUtil {

    private static final String PACKAGE_CAR_SYSTEMUI = "com.hzbhd.carsystemui";
    private static ResourcesUtil mResourcesUtil;
    private Context mCarSystemUIContext = getCarSystemContext();
    private final Context mContext;
    private final PackageManager mPackageManager;


    private ResourcesUtil(Context context) {
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "context.applicationContext");
        this.mContext = applicationContext;
        PackageManager packageManager = context.getPackageManager();
        Intrinsics.checkNotNullExpressionValue(packageManager, "context.packageManager");
        this.mPackageManager = packageManager;
    }

    public final Resources getResources(String packageName) {
        try {
            return this.mPackageManager.getResourcesForApplication(packageName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public final Drawable getDrawable(String packageName, String idName) throws Resources.NotFoundException {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Resources resources = getResources(packageName);
        Log.d("ResourcesUtil", "getDrawable: " + packageName + " : " + idName);
        Intrinsics.checkNotNull(resources);
        Drawable drawable = resources.getDrawable(resources.getIdentifier(idName, "drawable", packageName), null);
        Intrinsics.checkNotNullExpressionValue(drawable, "resources!!.getDrawable(…ble\", packageName), null)");
        return drawable;
    }

    public final Drawable getDrawable(String packageName, int id) {
        Resources resources = getResources(packageName);
        try {
            Intrinsics.checkNotNull(resources);
            Drawable drawableForDensity = resources.getDrawableForDensity(id, 480, null);
            if (drawableForDensity == null) {
                drawableForDensity = resources.getDrawableForDensity(id, NfDef.BT_SCAN_START, null);
            }
            if (drawableForDensity == null) {
                drawableForDensity = resources.getDrawableForDensity(id, NfDef.STATE_3WAY_M_HOLD, null);
            }
            return drawableForDensity == null ? resources.getDrawableForDensity(id, 120, null) : drawableForDensity;
        } catch (Exception unused) {
            return null;
        }
    }

    public final View getPackageView(Context packageContext, String type, String name) {
        Intrinsics.checkNotNullParameter(packageContext, "packageContext");
        View viewInflate = View.inflate(packageContext, packageContext.getResources().getIdentifier(packageContext.getPackageName(), type, name), null);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflate(packageContext,\n…e),\n                null)");
        return viewInflate;
    }

    @SuppressLint("WrongConstant")
    public final Context getCarSystemContext() {
        if (this.mCarSystemUIContext == null) {
            try {
                mCarSystemUIContext = this.mContext.createPackageContext(PACKAGE_CAR_SYSTEMUI, 3);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this.mCarSystemUIContext;
    }

    public final ResourcesUtil getResourcesUtil(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (ResourcesUtil.mResourcesUtil == null) {
            ResourcesUtil.mResourcesUtil = new ResourcesUtil(context);
        }
        return ResourcesUtil.mResourcesUtil;
    }
}
