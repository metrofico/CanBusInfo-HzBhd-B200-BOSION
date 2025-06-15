package com.hzbhd.ui.view.playview;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.TextureView;

import androidx.lifecycle.Lifecycle;

import com.hzbhd.commontools.utils.SystemPropertiesUtils;
import com.hzbhd.ui.util.BaseUtil;
import com.hzbhd.ui.view.playview.PlayView;
import com.hzbhd.util.LogUtil;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;


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


        SystemPropertiesUtils.set("vendor.video.play.withoutdisplay", "true");
        setScalePlayViewInterface(scalePlayViewInterface);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DoubleTexturePlayView(Context context) {
        super(context);

        SystemPropertiesUtils.set("vendor.video.play.withoutdisplay", "true");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DoubleTexturePlayView(Context context, AttributeSet attrs) {
        super(context, attrs);


        SystemPropertiesUtils.set("vendor.video.play.withoutdisplay", "true");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DoubleTexturePlayView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);

        SystemPropertiesUtils.set("vendor.video.play.withoutdisplay", "true");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DoubleTexturePlayView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);

        SystemPropertiesUtils.set("vendor.video.play.withoutdisplay", "true");
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

        return textureView;
    }

    public final TextureView getHardTexureView() {
        if (this.hardTextureView == null) {
            this.hardTextureView = new TextureView(getContext());
        }
        TextureView textureView = this.hardTextureView;

        return textureView;
    }

    @Override // com.hzbhd.ui.view.playview.PlayView
    public void refreshCover(final boolean cover) {
        if (LogUtil.log5()) {
            LogUtil.d("refreshCover: " + cover);
        }
        BaseUtil.INSTANCE.runUi(new Runnable() {
            @Override
            public void run() {
                if (!cover) {
                    checkTextureView();
                } else {
                    removeAllViews();
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

            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {

                boolean z = SystemPropertiesUtils.getBoolean("vendor.video.play.withoutdisplay", true);
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
                SystemPropertiesUtils.set("vendor.video.play.withoutdisplay", "false");
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

                if (LogUtil.log5()) {
                    LogUtil.d("onSurfaceTextureSizeChanged: ");
                }
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {

                if (LogUtil.log5()) {
                    LogUtil.d("[playSurfaceView:onSurfaceTextureDestroyed]:");
                }
                SystemPropertiesUtils.set("vendor.video.play.withoutdisplay", "true");
                DoubleTexturePlayView.this.setTextureVisible(false);
                return false;
            }
        });
        getHardTexureView().setSurfaceTextureListener(new TextureView.SurfaceTextureListener() { // from class: com.hzbhd.ui.view.playview.DoubleTexturePlayView.initSurfaceView.2
            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {

            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {

                DoubleTexturePlayView.this.setTextureVisible(true);
                SystemPropertiesUtils.getBoolean("vendor.video.play.withoutdisplay", true);
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
                SystemPropertiesUtils.set("vendor.video.play.withoutdisplay", "false");
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

                if (LogUtil.log5()) {
                    LogUtil.d("onSurfaceTextureSizeChanged: ");
                }
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {

                if (LogUtil.log5()) {
                    LogUtil.d("[playSurfaceView:onSurfaceTextureDestroyed]:");
                }
                SystemPropertiesUtils.set("vendor.video.play.withoutdisplay", "true");
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
