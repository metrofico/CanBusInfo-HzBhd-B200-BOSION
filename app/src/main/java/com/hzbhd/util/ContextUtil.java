package com.hzbhd.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.graphics.PointF;
import android.view.WindowManager;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ContextUtil.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 J&\u0010!\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020\u00182\u0006\u0010#\u001a\u00020\u00182\u0006\u0010$\u001a\u00020\u00182\u0006\u0010%\u001a\u00020\u0018R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001b\u0010\f\u001a\u00020\r8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c¨\u0006&"}, d2 = {"Lcom/hzbhd/util/ContextUtil;", "", "()V", "PX_X_MAX", "", "PX_Y_MAX", "maxSize", "Landroid/graphics/PointF;", "getMaxSize", "()Landroid/graphics/PointF;", "maxSize$delegate", "Lkotlin/Lazy;", "screenSize", "Landroid/graphics/Point;", "getScreenSize", "()Landroid/graphics/Point;", "screenSize$delegate", "textColor", "", "getTextColor", "()J", "setTextColor", "(J)V", "textSize", "", "getTextSize", "()I", "setTextSize", "(I)V", "init", "", "context", "Landroid/content/Context;", "setSize", "x", "y", "xMax", "yMax", "thread_util_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes3.dex */
public final class ContextUtil {
    public static final float PX_X_MAX = 1920.0f;
    public static final float PX_Y_MAX = 1080.0f;
    public static final ContextUtil INSTANCE = new ContextUtil();

    /* renamed from: screenSize$delegate, reason: from kotlin metadata */
    private static final Lazy screenSize = LazyKt.lazy(new Function0<Point>() { // from class: com.hzbhd.util.ContextUtil$screenSize$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Point invoke() {
            return new Point(1920, 1080);
        }
    });

    /* renamed from: maxSize$delegate, reason: from kotlin metadata */
    private static final Lazy maxSize = LazyKt.lazy(new Function0<PointF>() { // from class: com.hzbhd.util.ContextUtil$maxSize$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final PointF invoke() {
            return new PointF(1920.0f, 1080.0f);
        }
    });
    private static long textColor = 4294967295L;
    private static int textSize = 18;

    private ContextUtil() {
    }

    public final Point getScreenSize() {
        return (Point) screenSize.getValue();
    }

    public final PointF getMaxSize() {
        return (PointF) maxSize.getValue();
    }

    public final long getTextColor() {
        return textColor;
    }

    public final void setTextColor(long j) {
        textColor = j;
    }

    public final int getTextSize() {
        return textSize;
    }

    public final void setTextSize(int i) {
        textSize = i;
    }

    public final void init(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences sharedPreferences = context.getSharedPreferences("share", 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "context.getSharedPrefere…e\", Context.MODE_PRIVATE)");
        ContextUtilKt.setBaseShare(sharedPreferences);
        ContextUtilKt.setBaseContext(context);
        ((WindowManager) context.getSystemService(WindowManager.class)).getDefaultDisplay().getRealSize(getScreenSize());
    }

    public final void setSize(int x, int y, int xMax, int yMax) {
        getScreenSize().x = x;
        getScreenSize().y = y;
        getMaxSize().x = xMax;
        getMaxSize().y = yMax;
    }
}
