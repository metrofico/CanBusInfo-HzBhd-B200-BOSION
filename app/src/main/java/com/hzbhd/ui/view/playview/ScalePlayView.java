package com.hzbhd.ui.view.playview;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.lifecycle.Lifecycle;

import com.hzbhd.commontools.utils.SystemPropertiesUtils;
import com.hzbhd.ui.view.playview.PlayView;
import com.hzbhd.util.LogUtil;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;


public final class ScalePlayView extends OneTexturePlayView {
    private Surface mHardSurface;
    private SurfaceTexture mHardTexture;
    private boolean mIsSoftDecoder;
    private Surface mSoftSurface;
    private SurfaceTexture mSoftTexture;
    private SurfaceTexture mSurfaceTexture;

    @Override // com.hzbhd.ui.view.playview.OneTexturePlayView
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScalePlayView(Context context, PlayView.ScalePlayViewInterface scalePlayViewInterface) {
        super(context, scalePlayViewInterface);


    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScalePlayView(Context context) {
        super(context);

    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScalePlayView(Context context, AttributeSet attrs) {
        super(context, attrs);


    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScalePlayView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);

    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScalePlayView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);

    }

    @Override
    // com.hzbhd.ui.view.playview.PlayView, com.hzbhd.ui.view.lifecycle.BaseLifeRelativeLayout, android.widget.RelativeLayout, android.view.ViewGroup
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

            if (z) {
                this.mSurfaceTexture = getSoftSurfaceTexture();
            }
            if (!Intrinsics.areEqual(getTexureView().getSurfaceTexture(), this.mSurfaceTexture) && (surfaceTexture2 = this.mSurfaceTexture) != null) {
                getTexureView().setSurfaceTexture(surfaceTexture2);
            }
            return z;
        }

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

        boolean zChangeSurface = changeSurface();
        if (LogUtil.log5()) {
            LogUtil.d("onSurfaceTextureAvailable: " + zChangeSurface);
        }
        if (zChangeSurface && (scalePlayViewInterface = getScalePlayViewInterface()) != null) {
            scalePlayViewInterface.requestSurface();
        }
        SystemPropertiesUtils.set("vendor.video.play.withoutdisplay", "false");
    }

    @Override // com.hzbhd.ui.view.playview.OneTexturePlayView
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

        if (LogUtil.log5()) {
            LogUtil.d("onSurfaceTextureSizeChanged: ");
        }
    }

    @Override // com.hzbhd.ui.view.playview.OneTexturePlayView
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {

        if (LogUtil.log5()) {
            LogUtil.d("[playSurfaceView:onSurfaceTextureDestroyed]:");
        }
        SystemPropertiesUtils.set("vendor.video.play.withoutdisplay", "true");
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
