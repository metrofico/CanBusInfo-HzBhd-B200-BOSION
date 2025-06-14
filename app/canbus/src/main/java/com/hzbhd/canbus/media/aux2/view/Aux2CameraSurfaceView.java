package com.hzbhd.canbus.media.aux2.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Surface;
import com.hzbhd.canbus.media.aux2.manager.Aux2Manager;
import com.hzbhd.proxy.camera.manager.CameraManager;

/* loaded from: classes2.dex */
public class Aux2CameraSurfaceView extends CameraSurfaceView {
    public Aux2CameraSurfaceView(Context context) {
        super(context);
    }

    public Aux2CameraSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public Aux2CameraSurfaceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public Aux2CameraSurfaceView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    @Override // com.hzbhd.canbus.media.aux2.view.CameraSurfaceView
    public void startPreview(Surface surface) {
        CameraManager.INSTANCE.startPreview(Aux2Manager.getInstance().getCameraFlag(), surface);
    }

    @Override // com.hzbhd.canbus.media.aux2.view.CameraSurfaceView
    public void stopPreview() {
        CameraManager.INSTANCE.stopPreview(Aux2Manager.getInstance().getCameraFlag());
    }
}
