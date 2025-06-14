package com.hzbhd.ui.view;

import android.graphics.Paint;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: MyRadioButton.kt */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroid/graphics/Paint;", "invoke"}, k = 3, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes3.dex */
final class MyRadioButton$mDrawablePaint$2 extends Lambda implements Function0<Paint> {
    public static final MyRadioButton$mDrawablePaint$2 INSTANCE = new MyRadioButton$mDrawablePaint$2();

    MyRadioButton$mDrawablePaint$2() {
        super(0);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final Paint invoke() {
        Paint paint = new Paint();
        paint.setAntiAlias(false);
        paint.setFilterBitmap(false);
        return paint;
    }
}
