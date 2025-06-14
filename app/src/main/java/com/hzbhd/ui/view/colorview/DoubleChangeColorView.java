package com.hzbhd.ui.view.colorview;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.AttributeSet;
import com.hzbhd.ui.view.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DoubleChangeColorView.kt */
@Metadata(d1 = {"\u0000/\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0014\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0002J\b\u0010\u0018\u001a\u00020\u0017H\u0014J\b\u0010\u0019\u001a\u00020\u0017H\u0002R\u000e\u0010\n\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0003X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0015¨\u0006\u001a"}, d2 = {"Lcom/hzbhd/ui/view/colorview/DoubleChangeColorView;", "Lcom/hzbhd/ui/view/colorview/ColorView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "id_d1", "id_d2", "id_n1", "id_n2", "id_p1", "id_p2", "id_s1", "id_s2", "mContext", "mSettingsContentObserver", "com/hzbhd/ui/view/colorview/DoubleChangeColorView$mSettingsContentObserver$1", "Lcom/hzbhd/ui/view/colorview/DoubleChangeColorView$mSettingsContentObserver$1;", "init", "", "onFinishInflate", "updateAllColor", "commonview-base_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes3.dex */
public final class DoubleChangeColorView extends ColorView {
    private int id_d1;
    private int id_d2;
    private int id_n1;
    private int id_n2;
    private int id_p1;
    private int id_p2;
    private int id_s1;
    private int id_s2;
    private Context mContext;
    private final DoubleChangeColorView$mSettingsContentObserver$1 mSettingsContentObserver;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.hzbhd.ui.view.colorview.DoubleChangeColorView$mSettingsContentObserver$1] */
    public DoubleChangeColorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        final Handler handler = new Handler(Looper.getMainLooper());
        this.mSettingsContentObserver = new ContentObserver(handler) { // from class: com.hzbhd.ui.view.colorview.DoubleChangeColorView$mSettingsContentObserver$1
            @Override // android.database.ContentObserver
            public void onChange(boolean selfChange) {
                super.onChange(selfChange);
                this.this$0.updateAllColor();
            }
        };
        init(context, attrs);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r0v3, types: [com.hzbhd.ui.view.colorview.DoubleChangeColorView$mSettingsContentObserver$1] */
    public DoubleChangeColorView(Context context, AttributeSet attrs, int i) {
        super(context, attrs, i);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        final Handler handler = new Handler(Looper.getMainLooper());
        this.mSettingsContentObserver = new ContentObserver(handler) { // from class: com.hzbhd.ui.view.colorview.DoubleChangeColorView$mSettingsContentObserver$1
            @Override // android.database.ContentObserver
            public void onChange(boolean selfChange) {
                super.onChange(selfChange);
                this.this$0.updateAllColor();
            }
        };
        init(context, attrs);
    }

    private final void init(Context context, AttributeSet attrs) {
        this.mContext = context;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.double_color_attr);
        this.id_n1 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.double_color_attr_double_n1, 0);
        this.id_p1 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.double_color_attr_double_p1, 0);
        this.id_s1 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.double_color_attr_double_s1, 0);
        this.id_d1 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.double_color_attr_double_d1, 0);
        this.id_n2 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.double_color_attr_double_n2, 0);
        this.id_p2 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.double_color_attr_double_p2, 0);
        this.id_s2 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.double_color_attr_double_s2, 0);
        this.id_d2 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.double_color_attr_double_d2, 0);
        if (this.id_n1 != 0 || this.id_p1 != 0 || this.id_s1 != 0 || this.id_d1 != 0) {
            setBackground(new PressedDrawable(context, this.id_n1, this.id_p1, this.id_s1, this.id_d1));
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mContext.getContentResolver().registerContentObserver(Settings.System.getUriFor("changeAllColor"), false, this.mSettingsContentObserver);
        updateAllColor();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateAllColor() {
        int i = Settings.System.getInt(this.mContext.getContentResolver(), "changeAllColor", 0);
        if (i == 0) {
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "context");
            setBackground(new PressedDrawable(context, this.id_n1, this.id_p1, this.id_s1, this.id_d1));
        } else {
            if (i != 1) {
                return;
            }
            Context context2 = getContext();
            Intrinsics.checkNotNullExpressionValue(context2, "context");
            setBackground(new PressedDrawable(context2, this.id_n2, this.id_p2, this.id_s2, this.id_d2));
        }
    }
}
