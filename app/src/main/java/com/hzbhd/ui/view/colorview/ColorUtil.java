package com.hzbhd.ui.view.colorview;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.hzbhd.config.use.UIDefault;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ColorUtil.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\u0018\u0000 *2\u00020\u0001:\u0002)*B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0007J\u000e\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0005J\u000e\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\fJ\u000e\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\fJ\u000e\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0005J.\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u0007J&\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u0007J\u0016\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"J\u0018\u0010#\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0007H\u0002J(\u0010#\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010$\u001a\u00020\u00072\u0006\u0010%\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u0007H\u0002J0\u0010#\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010'\u001a\u00020\u00072\u0006\u0010$\u001a\u00020\u00072\u0006\u0010%\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u0007H\u0002J\u0016\u0010(\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010!\u001a\u00020\"R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/hzbhd/ui/view/colorview/ColorUtil;", "", "()V", "colorViewInterfaces", "Ljava/util/ArrayList;", "Lcom/hzbhd/ui/view/colorview/ColorUtil$ColorViewInterface;", "currColor_n", "", "currColor_p", "currTextColor_n", "currTextColor_p", "mContext", "Landroid/content/Context;", "BackGroundChangeColor", "", "bg", "Landroid/graphics/drawable/Drawable;", "color", "addColorInterface", "colorViewInterface", "getsaveColor", "context", "init", "removeColorInterface", "saveColor", "color_n", "color_p", "textColor_n", "textColor_p", "setCurrColor", "textviewStateCahnge", "textView", "Landroid/widget/TextView;", "isPress", "", "viewBackGroundChange", "r", "g", "b", "a", "viewStateChange", "ColorViewInterface", "Companion", "commonview-base_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes3.dex */
public final class ColorUtil {
    private static final int COLOR_A2 = -14072952;
    public static final String COLOR_TEXT_VIEW_COLOR_N = "color_text_view_color_n";
    public static final String COLOR_TEXT_VIEW_COLOR_P = "color_text_view_color_p";
    public static final String COLOR_VIEW_COLOR_N = "color_view_color_n";
    public static final String COLOR_VIEW_COLOR_P = "color_view_color_p";
    private static final int brightness = 63;
    private static final float saturation = 0.0f;
    private static final float saturation2 = 0.0f;
    private final ArrayList<ColorViewInterface> colorViewInterfaces = new ArrayList<>();
    private int currColor_n;
    private int currColor_p;
    private int currTextColor_n;
    private int currTextColor_p;
    private Context mContext;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final ColorUtil instance = new ColorUtil();
    private static final int DEFAULT_COLOR = UIDefault.INSTANCE.getColorViewColor();

    /* compiled from: ColorUtil.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lcom/hzbhd/ui/view/colorview/ColorUtil$ColorViewInterface;", "", "onColorChange", "", "commonview-base_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public interface ColorViewInterface {
        void onColorChange();
    }

    public ColorUtil() {
        int i = DEFAULT_COLOR;
        this.currColor_n = i;
        this.currColor_p = i;
        this.currTextColor_n = -1;
        this.currTextColor_p = ViewCompat.MEASURED_STATE_MASK;
    }

    public final void addColorInterface(ColorViewInterface colorViewInterface) {
        Intrinsics.checkNotNullParameter(colorViewInterface, "colorViewInterface");
        if (this.colorViewInterfaces.contains(colorViewInterface)) {
            return;
        }
        this.colorViewInterfaces.add(colorViewInterface);
    }

    public final void removeColorInterface(ColorViewInterface colorViewInterface) {
        Intrinsics.checkNotNullParameter(colorViewInterface, "colorViewInterface");
        if (this.colorViewInterfaces.contains(colorViewInterface)) {
            return;
        }
        this.colorViewInterfaces.remove(colorViewInterface);
    }

    public final void setCurrColor(int currColor_n, int currColor_p, int textColor_n, int textColor_p) {
        this.currColor_n = currColor_n;
        this.currColor_p = currColor_p;
        this.currTextColor_n = textColor_n;
        this.currTextColor_p = textColor_p;
        int size = this.colorViewInterfaces.size();
        for (int i = 0; i < size; i++) {
            this.colorViewInterfaces.get(i).onColorChange();
        }
    }

    private final void viewBackGroundChange(Drawable bg, int color) {
        viewBackGroundChange(bg, (16711680 & color) >> 16, (65280 & color) >> 8, color & 255);
    }

    private final void viewBackGroundChange(Drawable bg, int r, int g, int b) {
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(new float[]{1.0f, 0.0f, 0.0f, 0.0f, r - 63, 0.0f, 1.0f, 0.0f, 0.0f, g - 63, 0.0f, 0.0f, 1.0f, 0.0f, b - 63, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f});
        bg.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
    }

    public final void BackGroundChangeColor(Drawable bg, int color) {
        Intrinsics.checkNotNullParameter(bg, "bg");
        viewBackGroundChange(bg, ((-16777216) & color) >> 24, (16711680 & color) >> 16, (65280 & color) >> 8, color & 255);
    }

    private final void viewBackGroundChange(Drawable bg, int a, int r, int g, int b) {
        Log.d("sswang", "viewBackGroundChange: " + a + " , " + r + " , " + g + " , " + b);
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(new float[]{1.0f, 0.0f, 0.0f, 0.0f, r - 63, 0.0f, 1.0f, 0.0f, 0.0f, g - 63, 0.0f, 0.0f, 1.0f, 0.0f, b - 63, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, a});
        bg.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
    }

    public final void viewStateChange(Drawable bg, boolean isPress) {
        Intrinsics.checkNotNullParameter(bg, "bg");
        if (isPress) {
            viewBackGroundChange(bg, instance.currColor_p);
        } else {
            viewBackGroundChange(bg, instance.currColor_n);
        }
    }

    public final void textviewStateCahnge(TextView textView, boolean isPress) {
        Intrinsics.checkNotNullParameter(textView, "textView");
        if (isPress) {
            textView.setTextColor(this.currTextColor_p);
        } else {
            textView.setTextColor(this.currTextColor_n);
        }
    }

    public final void init(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.mContext = context;
        Intrinsics.checkNotNull(context);
        ContentResolver contentResolver = context.getContentResolver();
        int i = DEFAULT_COLOR;
        int i2 = Settings.System.getInt(contentResolver, COLOR_VIEW_COLOR_N, i);
        Context context2 = this.mContext;
        Intrinsics.checkNotNull(context2);
        int i3 = Settings.System.getInt(context2.getContentResolver(), COLOR_VIEW_COLOR_P, i);
        Context context3 = this.mContext;
        Intrinsics.checkNotNull(context3);
        int i4 = Settings.System.getInt(context3.getContentResolver(), COLOR_TEXT_VIEW_COLOR_N, i);
        Context context4 = this.mContext;
        Intrinsics.checkNotNull(context4);
        setCurrColor(i2, i3, i4, Settings.System.getInt(context4.getContentResolver(), COLOR_TEXT_VIEW_COLOR_P, i));
        context.getContentResolver().registerContentObserver(Settings.System.getUriFor(COLOR_VIEW_COLOR_N), false, new ContentObserver(new Handler(Looper.getMainLooper())) { // from class: com.hzbhd.ui.view.colorview.ColorUtil.init.1
            @Override // android.database.ContentObserver
            public void onChange(boolean selfChange) {
                Context context5 = ColorUtil.this.mContext;
                Intrinsics.checkNotNull(context5);
                int i5 = Settings.System.getInt(context5.getContentResolver(), ColorUtil.COLOR_VIEW_COLOR_N, ColorUtil.DEFAULT_COLOR);
                Context context6 = ColorUtil.this.mContext;
                Intrinsics.checkNotNull(context6);
                int i6 = Settings.System.getInt(context6.getContentResolver(), ColorUtil.COLOR_VIEW_COLOR_P, ColorUtil.DEFAULT_COLOR);
                Context context7 = ColorUtil.this.mContext;
                Intrinsics.checkNotNull(context7);
                int i7 = Settings.System.getInt(context7.getContentResolver(), ColorUtil.COLOR_TEXT_VIEW_COLOR_N, ColorUtil.DEFAULT_COLOR);
                Context context8 = ColorUtil.this.mContext;
                Intrinsics.checkNotNull(context8);
                ColorUtil.this.setCurrColor(i5, i6, i7, Settings.System.getInt(context8.getContentResolver(), ColorUtil.COLOR_TEXT_VIEW_COLOR_P, ColorUtil.DEFAULT_COLOR));
            }
        });
    }

    public final void saveColor(Context context, int color_n, int color_p, int textColor_n, int textColor_p) {
        Intrinsics.checkNotNullParameter(context, "context");
        Settings.System.putInt(context.getContentResolver(), COLOR_VIEW_COLOR_N, color_n);
        Settings.System.putInt(context.getContentResolver(), COLOR_VIEW_COLOR_P, color_p);
        Settings.System.putInt(context.getContentResolver(), COLOR_TEXT_VIEW_COLOR_N, textColor_n);
        Settings.System.putInt(context.getContentResolver(), COLOR_TEXT_VIEW_COLOR_P, textColor_p);
    }

    public final int getsaveColor(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return Settings.System.getInt(context.getContentResolver(), COLOR_VIEW_COLOR_N, DEFAULT_COLOR);
    }

    /* compiled from: ColorUtil.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0004J\u000e\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/hzbhd/ui/view/colorview/ColorUtil$Companion;", "", "()V", "COLOR_A2", "", "COLOR_TEXT_VIEW_COLOR_N", "", "COLOR_TEXT_VIEW_COLOR_P", "COLOR_VIEW_COLOR_N", "COLOR_VIEW_COLOR_P", "DEFAULT_COLOR", "brightness", "instance", "Lcom/hzbhd/ui/view/colorview/ColorUtil;", "saturation", "", "saturation2", "convertToARGB", "color", "convertToRGB", "commonview-base_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String convertToARGB(int color) {
            String hexString = Integer.toHexString(Color.alpha(color));
            String hexString2 = Integer.toHexString(Color.red(color));
            String hexString3 = Integer.toHexString(Color.green(color));
            String hexString4 = Integer.toHexString(Color.blue(color));
            if (hexString.length() == 1) {
                hexString = '0' + hexString;
            }
            if (hexString2.length() == 1) {
                hexString2 = '0' + hexString2;
            }
            if (hexString3.length() == 1) {
                hexString3 = '0' + hexString3;
            }
            if (hexString4.length() == 1) {
                hexString4 = '0' + hexString4;
            }
            return '#' + hexString + hexString2 + hexString3 + hexString4;
        }

        public final String convertToRGB(int color) {
            String hexString = Integer.toHexString(Color.red(color));
            String hexString2 = Integer.toHexString(Color.green(color));
            String hexString3 = Integer.toHexString(Color.blue(color));
            if (hexString.length() == 1) {
                hexString = '0' + hexString;
            }
            if (hexString2.length() == 1) {
                hexString2 = '0' + hexString2;
            }
            if (hexString3.length() == 1) {
                hexString3 = '0' + hexString3;
            }
            return '#' + hexString + hexString2 + hexString3;
        }
    }
}
