package com.hzbhd.canbus.media.aux2.view;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;

import androidx.lifecycle.Lifecycle;

import com.hzbhd.ui.view.lifecycle.BaseLifeFrameLayout;
import com.hzbhd.util.LogUtil;

import java.util.LinkedHashMap;
import java.util.Map;

import kotlin.jvm.internal.Intrinsics;


public abstract class CameraSurfaceView extends BaseLifeFrameLayout {
    public Map<Integer, View> _$_findViewCache;
    private Surface mSurface;
    private TextureView textureView;

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(i);
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i);
        if (viewFindViewById == null) {
            return null;
        }
        map.put(i, viewFindViewById);
        return viewFindViewById;
    }

    public abstract void startPreview(Surface surface);

    public abstract void stopPreview();

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CameraSurfaceView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this._$_findViewCache = new LinkedHashMap<>();
        TextureView textureView = new TextureView(getContext());
        this.textureView = textureView;
        addView(textureView);
        this.textureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() { // from class: com.hzbhd.canbus.media.aux2.view.CameraSurfaceView.1
            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
                Intrinsics.checkNotNullParameter(surface, "surface");
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {
                Intrinsics.checkNotNullParameter(surface, "surface");
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                Intrinsics.checkNotNullParameter(surface, "surface");
                CameraSurfaceView.this.setMSurface(new Surface(surface));
                CameraSurfaceView.this.refreshPreView();
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                Intrinsics.checkNotNullParameter(surface, "surface");
                CameraSurfaceView.this.setMSurface(null);
                CameraSurfaceView.this.refreshPreView();
                return true;
            }
        });
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CameraSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        this._$_findViewCache = new LinkedHashMap();
        TextureView textureView = new TextureView(getContext());
        this.textureView = textureView;
        addView(textureView);
        this.textureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() { // from class: com.hzbhd.canbus.media.aux2.view.CameraSurfaceView.1
            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
                Intrinsics.checkNotNullParameter(surface, "surface");
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {
                Intrinsics.checkNotNullParameter(surface, "surface");
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                Intrinsics.checkNotNullParameter(surface, "surface");
                CameraSurfaceView.this.setMSurface(new Surface(surface));
                CameraSurfaceView.this.refreshPreView();
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                Intrinsics.checkNotNullParameter(surface, "surface");
                CameraSurfaceView.this.setMSurface(null);
                CameraSurfaceView.this.refreshPreView();
                return true;
            }
        });
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CameraSurfaceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this._$_findViewCache = new LinkedHashMap();
        TextureView textureView = new TextureView(getContext());
        this.textureView = textureView;
        addView(textureView);
        this.textureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() { // from class: com.hzbhd.canbus.media.aux2.view.CameraSurfaceView.1
            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
                Intrinsics.checkNotNullParameter(surface, "surface");
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {
                Intrinsics.checkNotNullParameter(surface, "surface");
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                Intrinsics.checkNotNullParameter(surface, "surface");
                CameraSurfaceView.this.setMSurface(new Surface(surface));
                CameraSurfaceView.this.refreshPreView();
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                CameraSurfaceView.this.setMSurface(null);
                CameraSurfaceView.this.refreshPreView();
                return true;
            }
        });
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CameraSurfaceView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this._$_findViewCache = new LinkedHashMap();
        TextureView textureView = new TextureView(getContext());
        this.textureView = textureView;
        addView(textureView);
        this.textureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() { // from class: com.hzbhd.canbus.media.aux2.view.CameraSurfaceView.1
            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                CameraSurfaceView.this.setMSurface(new Surface(surface));
                CameraSurfaceView.this.refreshPreView();
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                CameraSurfaceView.this.setMSurface(null);
                CameraSurfaceView.this.refreshPreView();
                return true;
            }
        });
    }

    protected final Surface getMSurface() {
        return this.mSurface;
    }

    protected final void setMSurface(Surface surface) {
        this.mSurface = surface;
    }

    @Override // com.hzbhd.ui.view.lifecycle.BaseLifeFrameLayout
    public void onLifeCycleChange(Lifecycle.State state) {
        super.onLifeCycleChange(state);
        refreshPreView();
    }

    public void refreshPreView() {
        Surface surface;
        if (LogUtil.log5()) {
            LogUtil.d("refreshPreView: " + getLifecycle().getCurrentState() + ',' + (this.mSurface != null));
        }
        if (getLifecycle().getCurrentState() == Lifecycle.State.RESUMED && (surface = this.mSurface) != null) {
            startPreview(surface);
            return;
        }
        stopPreview();
        Surface surface2 = this.mSurface;
        if (surface2 != null) {
            clearSurface(surface2);
        }
    }

    public void clearSurface(Surface surface) {
        EGLDisplay eGLDisplayEglGetDisplay = EGL14.eglGetDisplay(0);
        int[] iArr = new int[2];
        EGL14.eglInitialize(eGLDisplayEglGetDisplay, iArr, 0, iArr, 1);
        EGLConfig[] eGLConfigArr = new EGLConfig[1];
        EGL14.eglChooseConfig(eGLDisplayEglGetDisplay, new int[]{12324, 8, 12323, 8, 12322, 8, 12321, 8, 12352, 4, 12344, 0, 12344}, 0, eGLConfigArr, 0, 1, new int[1], 0);
        EGLConfig eGLConfig = eGLConfigArr[0];
        EGLContext eGLContextEglCreateContext = EGL14.eglCreateContext(eGLDisplayEglGetDisplay, eGLConfig, EGL14.EGL_NO_CONTEXT, new int[]{12440, 2, 12344}, 0);
        EGLSurface eGLSurfaceEglCreateWindowSurface = EGL14.eglCreateWindowSurface(eGLDisplayEglGetDisplay, eGLConfig, surface, new int[]{12344}, 0);
        EGL14.eglMakeCurrent(eGLDisplayEglGetDisplay, eGLSurfaceEglCreateWindowSurface, eGLSurfaceEglCreateWindowSurface, eGLContextEglCreateContext);
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        GLES20.glClear(16384);
        EGL14.eglSwapBuffers(eGLDisplayEglGetDisplay, eGLSurfaceEglCreateWindowSurface);
        EGL14.eglDestroySurface(eGLDisplayEglGetDisplay, eGLSurfaceEglCreateWindowSurface);
        EGL14.eglMakeCurrent(eGLDisplayEglGetDisplay, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_CONTEXT);
        EGL14.eglDestroyContext(eGLDisplayEglGetDisplay, eGLContextEglCreateContext);
        EGL14.eglTerminate(eGLDisplayEglGetDisplay);
    }
}
