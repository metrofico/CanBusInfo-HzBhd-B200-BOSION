package com.hzbhd.ui.view.playview;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.os.SystemProperties;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.lifecycle.Lifecycle;
import com.hzbhd.ui.view.playview.PlayView;
import com.hzbhd.util.LogUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ScalePlayView.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rB)\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\f¢\u0006\u0002\u0010\u000fJ\b\u0010+\u001a\u00020\u001dH\u0002J\b\u0010,\u001a\u00020-H\u0014J\b\u0010.\u001a\u00020\u0011H\u0003J\b\u0010/\u001a\u00020\u0017H\u0003J\u0012\u00100\u001a\u0004\u0018\u00010\u00112\u0006\u00101\u001a\u00020\u001dH\u0016J\b\u00102\u001a\u00020\u0011H\u0003J\b\u00103\u001a\u00020\u0017H\u0003J\u0010\u00104\u001a\u0002052\u0006\u00106\u001a\u000207H\u0016J \u00108\u001a\u0002052\u0006\u00109\u001a\u00020\u00172\u0006\u0010:\u001a\u00020\f2\u0006\u0010;\u001a\u00020\fH\u0016J\u0010\u0010<\u001a\u00020\u001d2\u0006\u00109\u001a\u00020\u0017H\u0016J \u0010=\u001a\u0002052\u0006\u00109\u001a\u00020\u00172\u0006\u0010:\u001a\u00020\f2\u0006\u0010;\u001a\u00020\fH\u0016J\u0010\u0010>\u001a\u0002052\u0006\u00109\u001a\u00020\u0017H\u0016R\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001c\u0010\"\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0013\"\u0004\b$\u0010\u0015R\u001c\u0010%\u001a\u0004\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0019\"\u0004\b'\u0010\u001bR\u001c\u0010(\u001a\u0004\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u0019\"\u0004\b*\u0010\u001b¨\u0006?"}, d2 = {"Lcom/hzbhd/ui/view/playview/ScalePlayView;", "Lcom/hzbhd/ui/view/playview/OneTexturePlayView;", "context", "Landroid/content/Context;", "scalePlayViewInterface", "Lcom/hzbhd/ui/view/playview/PlayView$ScalePlayViewInterface;", "(Landroid/content/Context;Lcom/hzbhd/ui/view/playview/PlayView$ScalePlayViewInterface;)V", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "mHardSurface", "Landroid/view/Surface;", "getMHardSurface", "()Landroid/view/Surface;", "setMHardSurface", "(Landroid/view/Surface;)V", "mHardTexture", "Landroid/graphics/SurfaceTexture;", "getMHardTexture", "()Landroid/graphics/SurfaceTexture;", "setMHardTexture", "(Landroid/graphics/SurfaceTexture;)V", "mIsSoftDecoder", "", "getMIsSoftDecoder", "()Z", "setMIsSoftDecoder", "(Z)V", "mSoftSurface", "getMSoftSurface", "setMSoftSurface", "mSoftTexture", "getMSoftTexture", "setMSoftTexture", "mSurfaceTexture", "getMSurfaceTexture", "setMSurfaceTexture", "changeSurface", "generateDefaultLayoutParams", "Landroid/view/ViewGroup$LayoutParams;", "getHardSurface", "getHardSurfaceTexture", "getPlayerSurface", "isSoftDecoder", "getSoftSurface", "getSoftSurfaceTexture", "onLifeCycleChange", "", "state", "Landroidx/lifecycle/Lifecycle$State;", "onSurfaceTextureAvailable", "surface", "width", "height", "onSurfaceTextureDestroyed", "onSurfaceTextureSizeChanged", "onSurfaceTextureUpdated", "commonview-base_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes3.dex */
public final class ScalePlayView extends OneTexturePlayView {
    private Surface mHardSurface;
    private SurfaceTexture mHardTexture;
    private boolean mIsSoftDecoder;
    private Surface mSoftSurface;
    private SurfaceTexture mSoftTexture;
    private SurfaceTexture mSurfaceTexture;

    @Override // com.hzbhd.ui.view.playview.OneTexturePlayView
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
        Intrinsics.checkNotNullParameter(surface, "surface");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScalePlayView(Context context, PlayView.ScalePlayViewInterface scalePlayViewInterface) {
        super(context, scalePlayViewInterface);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(scalePlayViewInterface, "scalePlayViewInterface");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScalePlayView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScalePlayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScalePlayView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScalePlayView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override // com.hzbhd.ui.view.playview.PlayView, com.hzbhd.ui.view.lifecycle.BaseLifeRelativeLayout, android.widget.RelativeLayout, android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new RelativeLayout.LayoutParams(-1, -1);
    }

    public final SurfaceTexture getMSoftTexture() {
        return this.mSoftTexture;
    }

    public final void setMSoftTexture(SurfaceTexture surfaceTexture) {
        this.mSoftTexture = surfaceTexture;
    }

    public final SurfaceTexture getMHardTexture() {
        return this.mHardTexture;
    }

    public final void setMHardTexture(SurfaceTexture surfaceTexture) {
        this.mHardTexture = surfaceTexture;
    }

    public final Surface getMSoftSurface() {
        return this.mSoftSurface;
    }

    public final void setMSoftSurface(Surface surface) {
        this.mSoftSurface = surface;
    }

    public final Surface getMHardSurface() {
        return this.mHardSurface;
    }

    public final void setMHardSurface(Surface surface) {
        this.mHardSurface = surface;
    }

    public final SurfaceTexture getMSurfaceTexture() {
        return this.mSurfaceTexture;
    }

    public final void setMSurfaceTexture(SurfaceTexture surfaceTexture) {
        this.mSurfaceTexture = surfaceTexture;
    }

    public final boolean getMIsSoftDecoder() {
        return this.mIsSoftDecoder;
    }

    public final void setMIsSoftDecoder(boolean z) {
        this.mIsSoftDecoder = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0011  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final android.view.Surface getSoftSurface() {
        /*
            r3 = this;
            android.view.Surface r0 = r3.mSoftSurface
            if (r0 == 0) goto L11
            android.graphics.SurfaceTexture r0 = r3.mSoftTexture
            if (r0 == 0) goto L11
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            boolean r0 = r0.isReleased()
            if (r0 == 0) goto L2c
        L11:
            android.graphics.SurfaceTexture r0 = new android.graphics.SurfaceTexture
            r1 = 0
            r0.<init>(r1)
            r3.mSoftTexture = r0
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            r1 = 1024(0x400, float:1.435E-42)
            r2 = 600(0x258, float:8.41E-43)
            r0.setDefaultBufferSize(r1, r2)
            android.view.Surface r0 = new android.view.Surface
            android.graphics.SurfaceTexture r1 = r3.mSoftTexture
            r0.<init>(r1)
            r3.mSoftSurface = r0
        L2c:
            boolean r0 = com.hzbhd.util.LogUtil.log5()
            if (r0 == 0) goto L51
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "getSoftSurface: isRelease "
            java.lang.StringBuilder r0 = r0.append(r1)
            android.graphics.SurfaceTexture r1 = r3.mSoftTexture
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            boolean r1 = r1.isReleased()
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.hzbhd.util.LogUtil.d(r0)
        L51:
            android.view.Surface r0 = r3.mSoftSurface
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.ui.view.playview.ScalePlayView.getSoftSurface():android.view.Surface");
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x000d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final android.graphics.SurfaceTexture getSoftSurfaceTexture() {
        /*
            r1 = this;
            android.graphics.SurfaceTexture r0 = r1.mSoftTexture
            if (r0 == 0) goto Ld
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            boolean r0 = r0.isReleased()
            if (r0 == 0) goto L10
        Ld:
            r1.getSoftSurface()
        L10:
            android.graphics.SurfaceTexture r0 = r1.mSoftTexture
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.ui.view.playview.ScalePlayView.getSoftSurfaceTexture():android.graphics.SurfaceTexture");
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0011  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final android.view.Surface getHardSurface() {
        /*
            r3 = this;
            android.view.Surface r0 = r3.mHardSurface
            if (r0 == 0) goto L11
            android.graphics.SurfaceTexture r0 = r3.mHardTexture
            if (r0 == 0) goto L11
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            boolean r0 = r0.isReleased()
            if (r0 == 0) goto L2c
        L11:
            android.graphics.SurfaceTexture r0 = new android.graphics.SurfaceTexture
            r1 = 0
            r0.<init>(r1)
            r3.mHardTexture = r0
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            r1 = 1024(0x400, float:1.435E-42)
            r2 = 600(0x258, float:8.41E-43)
            r0.setDefaultBufferSize(r1, r2)
            android.view.Surface r0 = new android.view.Surface
            android.graphics.SurfaceTexture r1 = r3.mHardTexture
            r0.<init>(r1)
            r3.mHardSurface = r0
        L2c:
            boolean r0 = com.hzbhd.util.LogUtil.log5()
            if (r0 == 0) goto L51
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "getHardSurface: isRelease "
            java.lang.StringBuilder r0 = r0.append(r1)
            android.graphics.SurfaceTexture r1 = r3.mHardTexture
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            boolean r1 = r1.isReleased()
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.hzbhd.util.LogUtil.d(r0)
        L51:
            android.view.Surface r0 = r3.mHardSurface
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.ui.view.playview.ScalePlayView.getHardSurface():android.view.Surface");
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x000d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final android.graphics.SurfaceTexture getHardSurfaceTexture() {
        /*
            r1 = this;
            android.graphics.SurfaceTexture r0 = r1.mHardTexture
            if (r0 == 0) goto Ld
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            boolean r0 = r0.isReleased()
            if (r0 == 0) goto L10
        Ld:
            r1.getHardSurface()
        L10:
            android.graphics.SurfaceTexture r0 = r1.mHardTexture
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.ui.view.playview.ScalePlayView.getHardSurfaceTexture():android.graphics.SurfaceTexture");
    }

    private final boolean changeSurface() {
        SurfaceTexture surfaceTexture;
        SurfaceTexture surfaceTexture2;
        if (getLifecycle().getCurrentState() != Lifecycle.State.RESUMED || !getTexureView().isAvailable()) {
            return false;
        }
        if (this.mIsSoftDecoder) {
            boolean z = !Intrinsics.areEqual(this.mSurfaceTexture, getSoftSurfaceTexture());
            if (z) {
                this.mSurfaceTexture = getSoftSurfaceTexture();
            }
            if (!Intrinsics.areEqual(getTexureView().getSurfaceTexture(), this.mSurfaceTexture) && (surfaceTexture2 = this.mSurfaceTexture) != null) {
                getTexureView().setSurfaceTexture(surfaceTexture2);
            }
            return z;
        }
        boolean z2 = !Intrinsics.areEqual(this.mSurfaceTexture, getHardSurfaceTexture());
        if (z2) {
            this.mSurfaceTexture = getHardSurfaceTexture();
        }
        if (!Intrinsics.areEqual(getTexureView().getSurfaceTexture(), this.mSurfaceTexture) && (surfaceTexture = this.mSurfaceTexture) != null) {
            getTexureView().setSurfaceTexture(surfaceTexture);
        }
        return z2;
    }

    @Override // com.hzbhd.ui.view.lifecycle.BaseLifeRelativeLayout
    public void onLifeCycleChange(Lifecycle.State state) {
        PlayView.ScalePlayViewInterface scalePlayViewInterface;
        Intrinsics.checkNotNullParameter(state, "state");
        super.onLifeCycleChange(state);
        boolean zChangeSurface = changeSurface();
        if (LogUtil.log5()) {
            LogUtil.d("onLifeCycleChange: " + state + " , changeSurface = " + zChangeSurface);
        }
        if (state == Lifecycle.State.RESUMED) {
            if (!zChangeSurface || (scalePlayViewInterface = getScalePlayViewInterface()) == null) {
                return;
            }
            scalePlayViewInterface.requestSurface();
            return;
        }
        SurfaceTexture surfaceTexture = this.mSurfaceTexture;
        if (surfaceTexture != null) {
            surfaceTexture.release();
        }
        this.mSurfaceTexture = null;
    }

    @Override // com.hzbhd.ui.view.playview.OneTexturePlayView
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        PlayView.ScalePlayViewInterface scalePlayViewInterface;
        Intrinsics.checkNotNullParameter(surface, "surface");
        boolean zChangeSurface = changeSurface();
        if (LogUtil.log5()) {
            LogUtil.d("onSurfaceTextureAvailable: " + zChangeSurface);
        }
        if (zChangeSurface && (scalePlayViewInterface = getScalePlayViewInterface()) != null) {
            scalePlayViewInterface.requestSurface();
        }
        SystemProperties.set("vendor.video.play.withoutdisplay", "false");
    }

    @Override // com.hzbhd.ui.view.playview.OneTexturePlayView
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
        Intrinsics.checkNotNullParameter(surface, "surface");
        if (LogUtil.log5()) {
            LogUtil.d("onSurfaceTextureSizeChanged: ");
        }
    }

    @Override // com.hzbhd.ui.view.playview.OneTexturePlayView
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        Intrinsics.checkNotNullParameter(surface, "surface");
        if (LogUtil.log5()) {
            LogUtil.d("[playSurfaceView:onSurfaceTextureDestroyed]:");
        }
        SystemProperties.set("vendor.video.play.withoutdisplay", "true");
        return false;
    }

    @Override // com.hzbhd.ui.view.playview.PlayView
    public Surface getPlayerSurface(boolean isSoftDecoder) {
        this.mIsSoftDecoder = isSoftDecoder;
        if (isSoftDecoder) {
            this.mSoftTexture = null;
            Surface softSurface = getSoftSurface();
            changeSurface();
            return softSurface;
        }
        this.mHardTexture = null;
        Surface hardSurface = getHardSurface();
        changeSurface();
        return hardSurface;
    }
}
