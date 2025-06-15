package com.hzbhd.ui.view.playview;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.view.TextureView;
import com.hzbhd.ui.view.playview.PlayView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;


public abstract class OneTexturePlayView extends PlayView {
    private TextureView textureView;

    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {

    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {

        return false;
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OneTexturePlayView(Context context, PlayView.ScalePlayViewInterface scalePlayViewInterface) {
        super(context);


        setScalePlayViewInterface(scalePlayViewInterface);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OneTexturePlayView(Context context) {
        super(context);

    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OneTexturePlayView(Context context, AttributeSet attrs) {
        super(context, attrs);


    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OneTexturePlayView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);

    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OneTexturePlayView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);

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

        return textureView;
    }

    @Override // com.hzbhd.ui.view.playview.PlayView
    public void initSurfaceView() {
        addView(getTexureView());
        getTexureView().setSurfaceTextureListener(new TextureView.SurfaceTextureListener() { // from class: com.hzbhd.ui.view.playview.OneTexturePlayView.initSurfaceView.1
            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {

                OneTexturePlayView.this.onSurfaceTextureAvailable(surface, width, height);
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

                OneTexturePlayView.this.onSurfaceTextureSizeChanged(surface, width, height);
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {

                return OneTexturePlayView.this.onSurfaceTextureDestroyed(surface);
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {

                OneTexturePlayView.this.onSurfaceTextureUpdated(surface);
            }
        });
    }

    @Override // com.hzbhd.ui.view.playview.PlayView
    public void layoutSurface(int l, int t, int r, int b) {
        getTexureView().layout(l, t, r, b);
    }
}
