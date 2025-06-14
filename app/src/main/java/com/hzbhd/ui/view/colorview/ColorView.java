package com.hzbhd.ui.view.colorview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;
import com.hzbhd.ui.view.R;
import com.hzbhd.ui.view.colorview.ColorUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ColorView.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002B\u0019\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010!\u001a\u00020\"H\u0014J\u001a\u0010#\u001a\u00020\"2\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002J\b\u0010$\u001a\u00020\"H\u0014J\b\u0010%\u001a\u00020\"H\u0016J\b\u0010&\u001a\u00020\"H\u0014J\u0010\u0010'\u001a\u00020\"2\u0006\u0010(\u001a\u00020)H\u0016R\u001a\u0010\u000b\u001a\u00020\tX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\tX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000fR\u001a\u0010\u0013\u001a\u00020\tX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\r\"\u0004\b\u0015\u0010\u000fR\u001a\u0010\u0016\u001a\u00020\tX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\r\"\u0004\b\u0018\u0010\u000fR\u001a\u0010\u0019\u001a\u00020\u001aX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u000e\u0010\u001e\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/hzbhd/ui/view/colorview/ColorView;", "Landroid/widget/TextView;", "Lcom/hzbhd/ui/view/colorview/ColorUtil$ColorViewInterface;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "id_d", "getId_d", "()I", "setId_d", "(I)V", "id_n", "getId_n", "setId_n", "id_p", "getId_p", "setId_p", "id_s", "getId_s", "setId_s", "isColorImage", "", "()Z", "setColorImage", "(Z)V", "mix_type", "n_mix_color", "p_mix_color", "drawableStateChanged", "", "init", "onAttachedToWindow", "onColorChange", "onDetachedFromWindow", "setBackground", "background", "Landroid/graphics/drawable/Drawable;", "commonview-base_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes3.dex */
public class ColorView extends TextView implements ColorUtil.ColorViewInterface {
    private int id_d;
    private int id_n;
    private int id_p;
    private int id_s;
    private boolean isColorImage;
    private int mix_type;
    private int n_mix_color;
    private int p_mix_color;

    /* renamed from: isColorImage, reason: from getter */
    protected final boolean getIsColorImage() {
        return this.isColorImage;
    }

    protected final void setColorImage(boolean z) {
        this.isColorImage = z;
    }

    protected final int getId_n() {
        return this.id_n;
    }

    protected final void setId_n(int i) {
        this.id_n = i;
    }

    protected final int getId_p() {
        return this.id_p;
    }

    protected final void setId_p(int i) {
        this.id_p = i;
    }

    protected final int getId_s() {
        return this.id_s;
    }

    protected final void setId_s(int i) {
        this.id_s = i;
    }

    protected final int getId_d() {
        return this.id_d;
    }

    protected final void setId_d(int i) {
        this.id_d = i;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ColorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        init(context, attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ColorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        init(context, attributeSet);
    }

    private final void init(Context context, AttributeSet attrs) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.color_attr);
        boolean z = typedArrayObtainStyledAttributes.getBoolean(R.styleable.color_attr_is_color_view, false);
        this.isColorImage = z;
        if (z) {
            this.mix_type = typedArrayObtainStyledAttributes.getInt(R.styleable.color_attr_mix_type, 0);
            this.n_mix_color = typedArrayObtainStyledAttributes.getColor(R.styleable.color_attr_n_mix_color, 0);
            this.p_mix_color = typedArrayObtainStyledAttributes.getColor(R.styleable.color_attr_p_mix_color, 0);
        } else {
            this.id_n = typedArrayObtainStyledAttributes.getResourceId(R.styleable.color_attr_n, 0);
            this.id_p = typedArrayObtainStyledAttributes.getResourceId(R.styleable.color_attr_p, 0);
            this.id_s = typedArrayObtainStyledAttributes.getResourceId(R.styleable.color_attr_s, 0);
            int resourceId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.color_attr_d, 0);
            this.id_d = resourceId;
            if (this.id_n != 0 || this.id_p != 0 || this.id_s != 0 || resourceId != 0) {
                setBackground(new PressedDrawable(context, this.id_n, this.id_p, this.id_s, this.id_d));
            }
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.isColorImage) {
            ColorUtil.instance.addColorInterface(this);
        }
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.isColorImage) {
            ColorUtil.instance.removeColorInterface(this);
        }
    }

    @Override // com.hzbhd.ui.view.colorview.ColorUtil.ColorViewInterface
    public void onColorChange() {
        if (getBackground() == null || !this.isColorImage) {
            return;
        }
        ColorUtil colorUtil = ColorUtil.instance;
        Drawable current = getBackground().getCurrent();
        Intrinsics.checkNotNullExpressionValue(current, "background.current");
        colorUtil.viewStateChange(current, isPressed());
    }

    @Override // android.view.View
    public void setBackground(Drawable background) {
        Intrinsics.checkNotNullParameter(background, "background");
        super.setBackground(background);
        if (this.isColorImage) {
            ColorUtil.instance.viewStateChange(background, isPressed());
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (!this.isColorImage || getBackground() == null) {
            return;
        }
        ColorUtil colorUtil = ColorUtil.instance;
        Drawable current = getBackground().getCurrent();
        Intrinsics.checkNotNullExpressionValue(current, "background.current");
        colorUtil.viewStateChange(current, isPressed());
    }
}
