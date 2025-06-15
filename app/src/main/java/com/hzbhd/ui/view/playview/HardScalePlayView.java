package com.hzbhd.ui.view.playview;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.view.Surface;

import com.hzbhd.commontools.utils.SystemPropertiesUtils;
import com.hzbhd.ui.view.playview.PlayView;
import com.hzbhd.util.LogUtil;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;


public final class HardScalePlayView extends OneTexturePlayView {
    private SurfaceTexture mSurfaceTexture;

    @Override // com.hzbhd.ui.view.playview.OneTexturePlayView
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
        Intrinsics.checkNotNullParameter(surface, "surface");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HardScalePlayView(Context context, PlayView.ScalePlayViewInterface scalePlayViewInterface) {
        super(context, scalePlayViewInterface);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(scalePlayViewInterface, "scalePlayViewInterface");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HardScalePlayView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HardScalePlayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HardScalePlayView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HardScalePlayView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public final SurfaceTexture getMSurfaceTexture() {
        return this.mSurfaceTexture;
    }

    public final void setMSurfaceTexture(SurfaceTexture surfaceTexture) {
        this.mSurfaceTexture = surfaceTexture;
    }

    @Override // com.hzbhd.ui.view.playview.PlayView
    public Surface getPlayerSurface(boolean isSoftDecoder) {
        if (LogUtil.log5()) {
            LogUtil.d("getPlayerSurface: " + this.mSurfaceTexture);
        }
        return new Surface(this.mSurfaceTexture);
    }

    @Override // com.hzbhd.ui.view.playview.OneTexturePlayView
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        Intrinsics.checkNotNullParameter(surface, "surface");
        if (LogUtil.log5()) {
            LogUtil.d("onSurfaceTextureAvailable:");
        }
        if (!Intrinsics.areEqual(this.mSurfaceTexture, surface)) {
            this.mSurfaceTexture = surface;
            PlayView.ScalePlayViewInterface scalePlayViewInterface = getScalePlayViewInterface();
            if (scalePlayViewInterface != null) {
                scalePlayViewInterface.requestSurface();
            }
        }
        SystemPropertiesUtils.set("vendor.video.play.withoutdisplay", "false");
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
        SystemPropertiesUtils.set("vendor.video.play.withoutdisplay", "true");
        return false;
    }
}
