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
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CameraSurfaceView.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB)\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t¢\u0006\u0002\u0010\fJ\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000eH\u0016J\u0010\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u0016H\u0016J\u0010\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000eH&J\b\u0010\u001d\u001a\u00020\u0016H&R\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/hzbhd/canbus/media/aux2/view/CameraSurfaceView;", "Lcom/hzbhd/ui/view/lifecycle/BaseLifeFrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "mSurface", "Landroid/view/Surface;", "getMSurface", "()Landroid/view/Surface;", "setMSurface", "(Landroid/view/Surface;)V", "textureView", "Landroid/view/TextureView;", "clearSurface", "", "surface", "onLifeCycleChange", "state", "Landroidx/lifecycle/Lifecycle$State;", "refreshPreView", "startPreview", "stopPreview", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public abstract class CameraSurfaceView extends BaseLifeFrameLayout {
    public Map<Integer, View> _$_findViewCache;
    private Surface mSurface;
    private TextureView textureView;

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i);
        if (viewFindViewById == null) {
            return null;
        }
        map.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    public abstract void startPreview(Surface surface);

    public abstract void stopPreview();

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CameraSurfaceView(Context context) {
        super(context);
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
                Intrinsics.checkNotNullParameter(surface, "surface");
                CameraSurfaceView.this.setMSurface(null);
                CameraSurfaceView.this.refreshPreView();
                return true;
            }
        });
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CameraSurfaceView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
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
                Intrinsics.checkNotNullParameter(surface, "surface");
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
        Intrinsics.checkNotNullParameter(state, "state");
        super.onLifeCycleChange(state);
        refreshPreView();
    }

    public void refreshPreView() {
        Surface surface;
        if (LogUtil.log5()) {
            LogUtil.d("refreshPreView: " + getLifecycle().getCurrentState() + ',' + (this.mSurface != null));
        }
        if (getLifecycle().getCurrentState() == Lifecycle.State.RESUMED && (surface = this.mSurface) != null) {
            Intrinsics.checkNotNull(surface);
            startPreview(surface);
            return;
        }
        stopPreview();
        Surface surface2 = this.mSurface;
        if (surface2 != null) {
            Intrinsics.checkNotNull(surface2);
            clearSurface(surface2);
        }
    }

    public void clearSurface(Surface surface) {
        Intrinsics.checkNotNullParameter(surface, "surface");
        EGLDisplay eGLDisplayEglGetDisplay = EGL14.eglGetDisplay(0);
        Intrinsics.checkNotNullExpressionValue(eGLDisplayEglGetDisplay, "eglGetDisplay(EGL14.EGL_DEFAULT_DISPLAY)");
        int[] iArr = new int[2];
        EGL14.eglInitialize(eGLDisplayEglGetDisplay, iArr, 0, iArr, 1);
        EGLConfig[] eGLConfigArr = new EGLConfig[1];
        EGL14.eglChooseConfig(eGLDisplayEglGetDisplay, new int[]{12324, 8, 12323, 8, 12322, 8, 12321, 8, 12352, 4, 12344, 0, 12344}, 0, eGLConfigArr, 0, 1, new int[1], 0);
        EGLConfig eGLConfig = eGLConfigArr[0];
        EGLContext eGLContextEglCreateContext = EGL14.eglCreateContext(eGLDisplayEglGetDisplay, eGLConfig, EGL14.EGL_NO_CONTEXT, new int[]{12440, 2, 12344}, 0);
        Intrinsics.checkNotNullExpressionValue(eGLContextEglCreateContext, "eglCreateContext(display…GL_NONE\n            ), 0)");
        EGLSurface eGLSurfaceEglCreateWindowSurface = EGL14.eglCreateWindowSurface(eGLDisplayEglGetDisplay, eGLConfig, surface, new int[]{12344}, 0);
        Intrinsics.checkNotNullExpressionValue(eGLSurfaceEglCreateWindowSurface, "eglCreateWindowSurface(d…GL_NONE\n            ), 0)");
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
