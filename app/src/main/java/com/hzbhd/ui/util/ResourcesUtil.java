package com.hzbhd.ui.util;

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

/* compiled from: ResourcesUtil.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fJ\u001a\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0011\u001a\u00020\u0012J\"\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00032\b\u0010\u0016\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0017\u001a\u0004\u0018\u00010\u000fJ\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u00038F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/hzbhd/ui/util/ResourcesUtil;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "carSystemContext", "getCarSystemContext", "()Landroid/content/Context;", "mCarSystemUIContext", "mContext", "mPackageManager", "Landroid/content/pm/PackageManager;", "getDrawable", "Landroid/graphics/drawable/Drawable;", "packageName", "", "idName", "id", "", "getPackageView", "Landroid/view/View;", "packageContext", "type", LcdInfoShare.MediaInfo.name, "getResources", "Landroid/content/res/Resources;", "Companion", "java-base_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class ResourcesUtil {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String PACKAGE_CAR_SYSTEMUI = "com.hzbhd.carsystemui";
    private static ResourcesUtil mResourcesUtil;
    private final Context mCarSystemUIContext;
    private final Context mContext;
    private final PackageManager mPackageManager;

    public /* synthetic */ ResourcesUtil(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

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

    public final Context getCarSystemContext() throws PackageManager.NameNotFoundException {
        if (this.mCarSystemUIContext == null) {
            try {
                this.mContext.createPackageContext(PACKAGE_CAR_SYSTEMUI, 3);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this.mCarSystemUIContext;
    }

    /* compiled from: ResourcesUtil.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/hzbhd/ui/util/ResourcesUtil$Companion;", "", "()V", "PACKAGE_CAR_SYSTEMUI", "", "mResourcesUtil", "Lcom/hzbhd/ui/util/ResourcesUtil;", "getResourcesUtil", "context", "Landroid/content/Context;", "java-base_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ResourcesUtil getResourcesUtil(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (ResourcesUtil.mResourcesUtil == null) {
                ResourcesUtil.mResourcesUtil = new ResourcesUtil(context, null);
            }
            return ResourcesUtil.mResourcesUtil;
        }
    }
}
