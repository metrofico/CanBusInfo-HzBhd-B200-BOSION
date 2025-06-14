package com.hzbhd.canbus.car_cus._448.view;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.hzbhd.canbus.R;
import com.hzbhd.proxy.camera.manager.CameraManager;

/* loaded from: classes2.dex */
public class VideoInfoView extends LinearLayout {
    private boolean VIDEO_VIEW_SHOW_TAG;
    private TextureView textureView;
    private int videoChannel;
    private View view;

    public VideoInfoView(Context context) {
        this(context, null);
    }

    public VideoInfoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VideoInfoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.videoChannel = 2;
        this.VIDEO_VIEW_SHOW_TAG = false;
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.__448_view_video_info, (ViewGroup) this, true);
        this.view = viewInflate;
        this.textureView = (TextureView) viewInflate.findViewById(R.id.video_sur);
    }

    public void startVideoView() {
        TextureView textureView;
        if (this.VIDEO_VIEW_SHOW_TAG || (textureView = this.textureView) == null) {
            return;
        }
        textureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() { // from class: com.hzbhd.canbus.car_cus._448.view.VideoInfoView.1
            @Override // android.view.TextureView.SurfaceTextureListener
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                return false;
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
                CameraManager.INSTANCE.startPreview(VideoInfoView.this.videoChannel, new Surface(surfaceTexture));
                VideoInfoView.this.VIDEO_VIEW_SHOW_TAG = true;
            }
        });
    }

    public void stopVideoView() {
        CameraManager.INSTANCE.stopPreview(this.videoChannel);
        this.VIDEO_VIEW_SHOW_TAG = false;
    }
}
