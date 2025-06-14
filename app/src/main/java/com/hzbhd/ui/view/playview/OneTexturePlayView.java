package com.hzbhd.ui.view.playview;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.view.TextureView;
import com.hzbhd.ui.view.playview.PlayView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OneTexturePlayView.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rB)\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\f¢\u0006\u0002\u0010\u000fJ\b\u0010\u0016\u001a\u00020\u0011H\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016J(\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\fH\u0016J \u0010\u001e\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\f2\u0006\u0010\"\u001a\u00020\fH\u0016J\u0010\u0010#\u001a\u00020$2\u0006\u0010\u001f\u001a\u00020 H\u0016J \u0010%\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\f2\u0006\u0010\"\u001a\u00020\fH\u0016J\u0010\u0010&\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020 H\u0016R\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006'"}, d2 = {"Lcom/hzbhd/ui/view/playview/OneTexturePlayView;", "Lcom/hzbhd/ui/view/playview/PlayView;", "context", "Landroid/content/Context;", "scalePlayViewInterface", "Lcom/hzbhd/ui/view/playview/PlayView$ScalePlayViewInterface;", "(Landroid/content/Context;Lcom/hzbhd/ui/view/playview/PlayView$ScalePlayViewInterface;)V", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "textureView", "Landroid/view/TextureView;", "getTextureView", "()Landroid/view/TextureView;", "setTextureView", "(Landroid/view/TextureView;)V", "getTexureView", "initSurfaceView", "", "layoutSurface", "l", "t", "r", "b", "onSurfaceTextureAvailable", "surface", "Landroid/graphics/SurfaceTexture;", "width", "height", "onSurfaceTextureDestroyed", "", "onSurfaceTextureSizeChanged", "onSurfaceTextureUpdated", "commonview-base_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes3.dex */
public abstract class OneTexturePlayView extends PlayView {
    private TextureView textureView;

    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        Intrinsics.checkNotNullParameter(surface, "surface");
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        Intrinsics.checkNotNullParameter(surface, "surface");
        return false;
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
        Intrinsics.checkNotNullParameter(surface, "surface");
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
        Intrinsics.checkNotNullParameter(surface, "surface");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OneTexturePlayView(Context context, PlayView.ScalePlayViewInterface scalePlayViewInterface) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(scalePlayViewInterface, "scalePlayViewInterface");
        setScalePlayViewInterface(scalePlayViewInterface);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OneTexturePlayView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OneTexturePlayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OneTexturePlayView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OneTexturePlayView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public final TextureView getTextureView() {
        return this.textureView;
    }

    public final void setTextureView(TextureView textureView) {
        this.textureView = textureView;
    }

    @Override // com.hzbhd.ui.view.playview.PlayView
    public TextureView getTexureView() {
        if (this.textureView == null) {
            this.textureView = new TextureView(getContext());
        }
        TextureView textureView = this.textureView;
        Intrinsics.checkNotNull(textureView);
        return textureView;
    }

    @Override // com.hzbhd.ui.view.playview.PlayView
    public void initSurfaceView() {
        addView(getTexureView());
        getTexureView().setSurfaceTextureListener(new TextureView.SurfaceTextureListener() { // from class: com.hzbhd.ui.view.playview.OneTexturePlayView.initSurfaceView.1
            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                Intrinsics.checkNotNullParameter(surface, "surface");
                OneTexturePlayView.this.onSurfaceTextureAvailable(surface, width, height);
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
                Intrinsics.checkNotNullParameter(surface, "surface");
                OneTexturePlayView.this.onSurfaceTextureSizeChanged(surface, width, height);
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                Intrinsics.checkNotNullParameter(surface, "surface");
                return OneTexturePlayView.this.onSurfaceTextureDestroyed(surface);
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {
                Intrinsics.checkNotNullParameter(surface, "surface");
                OneTexturePlayView.this.onSurfaceTextureUpdated(surface);
            }
        });
    }

    @Override // com.hzbhd.ui.view.playview.PlayView
    public void layoutSurface(int l, int t, int r, int b) {
        getTexureView().layout(l, t, r, b);
    }
}
