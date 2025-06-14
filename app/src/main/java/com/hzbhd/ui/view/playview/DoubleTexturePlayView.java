package com.hzbhd.ui.view.playview;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.os.SystemProperties;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.TextureView;
import androidx.lifecycle.Lifecycle;
import com.hzbhd.ui.util.BaseUtil;
import com.hzbhd.ui.view.playview.PlayView;
import com.hzbhd.util.LogUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DoubleTexturePlayView.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rB)\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\f¢\u0006\u0002\u0010\u000fJ\b\u0010+\u001a\u00020,H\u0002J\u0006\u0010-\u001a\u00020\u0011J\u0012\u0010.\u001a\u0004\u0018\u00010/2\u0006\u00100\u001a\u00020\u001dH\u0016J\u0006\u00101\u001a\u00020\u0011J\b\u00102\u001a\u00020\u0011H\u0016J\b\u00103\u001a\u00020,H\u0016J\b\u00104\u001a\u00020,H\u0016J(\u00105\u001a\u00020,2\u0006\u00106\u001a\u00020\f2\u0006\u00107\u001a\u00020\f2\u0006\u00108\u001a\u00020\f2\u0006\u00109\u001a\u00020\fH\u0016J\u0010\u0010:\u001a\u00020,2\u0006\u0010;\u001a\u00020<H\u0016J\u0010\u0010=\u001a\u00020,2\u0006\u0010>\u001a\u00020\u001dH\u0016R\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001c\u0010\"\u001a\u0004\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0019\"\u0004\b$\u0010\u001bR\u001c\u0010%\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0013\"\u0004\b'\u0010\u0015R\u001a\u0010(\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u001f\"\u0004\b*\u0010!¨\u0006?"}, d2 = {"Lcom/hzbhd/ui/view/playview/DoubleTexturePlayView;", "Lcom/hzbhd/ui/view/playview/PlayView;", "context", "Landroid/content/Context;", "scalePlayViewInterface", "Lcom/hzbhd/ui/view/playview/PlayView$ScalePlayViewInterface;", "(Landroid/content/Context;Lcom/hzbhd/ui/view/playview/PlayView$ScalePlayViewInterface;)V", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "hardTextureView", "Landroid/view/TextureView;", "getHardTextureView", "()Landroid/view/TextureView;", "setHardTextureView", "(Landroid/view/TextureView;)V", "mHardSurfaceTexture", "Landroid/graphics/SurfaceTexture;", "getMHardSurfaceTexture", "()Landroid/graphics/SurfaceTexture;", "setMHardSurfaceTexture", "(Landroid/graphics/SurfaceTexture;)V", "mIsSoftDecoder", "", "getMIsSoftDecoder", "()Z", "setMIsSoftDecoder", "(Z)V", "mSoftSurfaceTexture", "getMSoftSurfaceTexture", "setMSoftSurfaceTexture", "softTextureView", "getSoftTextureView", "setSoftTextureView", "textureVisible", "getTextureVisible", "setTextureVisible", "checkTextureView", "", "getHardTexureView", "getPlayerSurface", "Landroid/view/Surface;", "isSoftDecoder", "getSoftTexureView", "getTexureView", "initCoverView", "initSurfaceView", "layoutSurface", "l", "t", "r", "b", "onLifeCycleChange", "state", "Landroidx/lifecycle/Lifecycle$State;", "refreshCover", "cover", "commonview-base_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes3.dex */
public class DoubleTexturePlayView extends PlayView {
    private TextureView hardTextureView;
    private SurfaceTexture mHardSurfaceTexture;
    private boolean mIsSoftDecoder;
    private SurfaceTexture mSoftSurfaceTexture;
    private TextureView softTextureView;
    private boolean textureVisible;

    @Override // com.hzbhd.ui.view.playview.PlayView
    public void initCoverView() {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DoubleTexturePlayView(Context context, PlayView.ScalePlayViewInterface scalePlayViewInterface) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(scalePlayViewInterface, "scalePlayViewInterface");
        SystemProperties.set("vendor.video.play.withoutdisplay", "true");
        setScalePlayViewInterface(scalePlayViewInterface);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DoubleTexturePlayView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        SystemProperties.set("vendor.video.play.withoutdisplay", "true");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DoubleTexturePlayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        SystemProperties.set("vendor.video.play.withoutdisplay", "true");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DoubleTexturePlayView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        SystemProperties.set("vendor.video.play.withoutdisplay", "true");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DoubleTexturePlayView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        Intrinsics.checkNotNullParameter(context, "context");
        SystemProperties.set("vendor.video.play.withoutdisplay", "true");
    }

    public final TextureView getSoftTextureView() {
        return this.softTextureView;
    }

    public final void setSoftTextureView(TextureView textureView) {
        this.softTextureView = textureView;
    }

    public final TextureView getHardTextureView() {
        return this.hardTextureView;
    }

    public final void setHardTextureView(TextureView textureView) {
        this.hardTextureView = textureView;
    }

    public final SurfaceTexture getMSoftSurfaceTexture() {
        return this.mSoftSurfaceTexture;
    }

    public final void setMSoftSurfaceTexture(SurfaceTexture surfaceTexture) {
        this.mSoftSurfaceTexture = surfaceTexture;
    }

    public final SurfaceTexture getMHardSurfaceTexture() {
        return this.mHardSurfaceTexture;
    }

    public final void setMHardSurfaceTexture(SurfaceTexture surfaceTexture) {
        this.mHardSurfaceTexture = surfaceTexture;
    }

    public final boolean getMIsSoftDecoder() {
        return this.mIsSoftDecoder;
    }

    public final void setMIsSoftDecoder(boolean z) {
        this.mIsSoftDecoder = z;
    }

    public final boolean getTextureVisible() {
        return this.textureVisible;
    }

    public final void setTextureVisible(boolean z) {
        this.textureVisible = z;
    }

    @Override // com.hzbhd.ui.view.playview.PlayView
    public TextureView getTexureView() {
        if (this.mIsSoftDecoder) {
            return getSoftTexureView();
        }
        return getHardTexureView();
    }

    public final TextureView getSoftTexureView() {
        if (this.softTextureView == null) {
            this.softTextureView = new TextureView(getContext());
        }
        TextureView textureView = this.softTextureView;
        Intrinsics.checkNotNull(textureView);
        return textureView;
    }

    public final TextureView getHardTexureView() {
        if (this.hardTextureView == null) {
            this.hardTextureView = new TextureView(getContext());
        }
        TextureView textureView = this.hardTextureView;
        Intrinsics.checkNotNull(textureView);
        return textureView;
    }

    @Override // com.hzbhd.ui.view.playview.PlayView
    public void refreshCover(final boolean cover) {
        if (LogUtil.log5()) {
            LogUtil.d("refreshCover: " + cover);
        }
        BaseUtil.INSTANCE.runUi(new Function0<Unit>() { // from class: com.hzbhd.ui.view.playview.DoubleTexturePlayView.refreshCover.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                if (!cover) {
                    this.checkTextureView();
                } else {
                    this.removeAllViews();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkTextureView() {
        if (getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
            if (this.mIsSoftDecoder) {
                if (getSoftTexureView().getParent() == null) {
                    addView(getSoftTexureView());
                }
            } else if (getHardTexureView().getParent() == null) {
                addView(getHardTexureView());
            }
        }
    }

    @Override // com.hzbhd.ui.view.lifecycle.BaseLifeRelativeLayout
    public void onLifeCycleChange(Lifecycle.State state) {
        Intrinsics.checkNotNullParameter(state, "state");
        super.onLifeCycleChange(state);
        if (LogUtil.log5()) {
            LogUtil.d("onLifeCycleChange: " + state);
        }
        if (state == Lifecycle.State.RESUMED) {
            checkTextureView();
        } else {
            removeAllViews();
        }
    }

    @Override // com.hzbhd.ui.view.playview.PlayView
    public void initSurfaceView() {
        getSoftTexureView().setSurfaceTextureListener(new TextureView.SurfaceTextureListener() { // from class: com.hzbhd.ui.view.playview.DoubleTexturePlayView.initSurfaceView.1
            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {
                Intrinsics.checkNotNullParameter(surface, "surface");
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                Intrinsics.checkNotNullParameter(surface, "surface");
                boolean z = SystemProperties.getBoolean("vendor.video.play.withoutdisplay", true);
                if (LogUtil.log5()) {
                    LogUtil.d("onSurfaceTextureAvailable:withoutdisplay " + z + "  " + DoubleTexturePlayView.this.getMSoftSurfaceTexture());
                }
                DoubleTexturePlayView.this.setTextureVisible(true);
                if (!Intrinsics.areEqual(DoubleTexturePlayView.this.getMSoftSurfaceTexture(), surface)) {
                    DoubleTexturePlayView.this.setMSoftSurfaceTexture(surface);
                    PlayView.ScalePlayViewInterface scalePlayViewInterface = DoubleTexturePlayView.this.getScalePlayViewInterface();
                    if (scalePlayViewInterface != null) {
                        scalePlayViewInterface.requestSurface();
                    }
                }
                SystemProperties.set("vendor.video.play.withoutdisplay", "false");
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
                Intrinsics.checkNotNullParameter(surface, "surface");
                if (LogUtil.log5()) {
                    LogUtil.d("onSurfaceTextureSizeChanged: ");
                }
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                Intrinsics.checkNotNullParameter(surface, "surface");
                if (LogUtil.log5()) {
                    LogUtil.d("[playSurfaceView:onSurfaceTextureDestroyed]:");
                }
                SystemProperties.set("vendor.video.play.withoutdisplay", "true");
                DoubleTexturePlayView.this.setTextureVisible(false);
                return false;
            }
        });
        getHardTexureView().setSurfaceTextureListener(new TextureView.SurfaceTextureListener() { // from class: com.hzbhd.ui.view.playview.DoubleTexturePlayView.initSurfaceView.2
            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {
                Intrinsics.checkNotNullParameter(surface, "surface");
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                Intrinsics.checkNotNullParameter(surface, "surface");
                DoubleTexturePlayView.this.setTextureVisible(true);
                SystemProperties.getBoolean("vendor.video.play.withoutdisplay", true);
                if (LogUtil.log5()) {
                    LogUtil.d("onSurfaceTextureAvailable: " + DoubleTexturePlayView.this.getMHardSurfaceTexture());
                }
                if (!Intrinsics.areEqual(DoubleTexturePlayView.this.getMHardSurfaceTexture(), surface)) {
                    DoubleTexturePlayView.this.setMHardSurfaceTexture(surface);
                    PlayView.ScalePlayViewInterface scalePlayViewInterface = DoubleTexturePlayView.this.getScalePlayViewInterface();
                    if (scalePlayViewInterface != null) {
                        scalePlayViewInterface.requestSurface();
                    }
                }
                SystemProperties.set("vendor.video.play.withoutdisplay", "false");
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
                Intrinsics.checkNotNullParameter(surface, "surface");
                if (LogUtil.log5()) {
                    LogUtil.d("onSurfaceTextureSizeChanged: ");
                }
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                Intrinsics.checkNotNullParameter(surface, "surface");
                if (LogUtil.log5()) {
                    LogUtil.d("[playSurfaceView:onSurfaceTextureDestroyed]:");
                }
                SystemProperties.set("vendor.video.play.withoutdisplay", "true");
                DoubleTexturePlayView.this.setTextureVisible(false);
                return false;
            }
        });
    }

    @Override // com.hzbhd.ui.view.playview.PlayView
    public Surface getPlayerSurface(boolean isSoftDecoder) {
        this.mIsSoftDecoder = isSoftDecoder;
        if (LogUtil.log5()) {
            LogUtil.d("getPlayerSurface: isSoftDecoder " + isSoftDecoder + " textureVisible " + this.textureVisible + " lifecycle.currentState " + getLifecycle().getCurrentState());
        }
        if (getLifecycle().getCurrentState() == Lifecycle.State.RESUMED && this.textureVisible) {
            if (isSoftDecoder) {
                if (LogUtil.log5()) {
                    StringBuilder sbAppend = new StringBuilder().append("getPlayerSurface: softTextureView?.isAvailable ");
                    TextureView textureView = this.softTextureView;
                    LogUtil.d(sbAppend.append(textureView != null ? Boolean.valueOf(textureView.isAvailable()) : null).toString());
                }
                TextureView textureView2 = this.softTextureView;
                if (textureView2 != null && textureView2.isAvailable()) {
                    return new Surface(this.mSoftSurfaceTexture);
                }
            } else {
                if (LogUtil.log5()) {
                    StringBuilder sbAppend2 = new StringBuilder().append("getPlayerSurface: hardTextureView?.isAvailable ");
                    TextureView textureView3 = this.hardTextureView;
                    LogUtil.d(sbAppend2.append(textureView3 != null ? Boolean.valueOf(textureView3.isAvailable()) : null).toString());
                }
                TextureView textureView4 = this.hardTextureView;
                if (textureView4 != null && textureView4.isAvailable()) {
                    return new Surface(this.mHardSurfaceTexture);
                }
            }
        }
        return null;
    }

    @Override // com.hzbhd.ui.view.playview.PlayView
    public void layoutSurface(int l, int t, int r, int b) {
        getSoftTexureView().layout(l, t, r, b);
        getHardTexureView().layout(l, t, r, b);
    }
}
