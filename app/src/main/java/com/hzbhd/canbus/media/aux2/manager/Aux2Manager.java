package com.hzbhd.canbus.media.aux2.manager;

import android.content.Context;
import android.content.Intent;

import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.media.aux2.action.ActionCallback;
import com.hzbhd.canbus.media.aux2.action.ActionTag;
import com.hzbhd.canbus.media.aux2.activity.Aux2Activity;

/* loaded from: classes2.dex */
public class Aux2Manager {
    private ActionCallback actionCallback;
    private int cameraFlag;

    private static class Holder {
        private static final Aux2Manager INSTANCE = new Aux2Manager();

        private Holder() {
        }
    }

    public static Aux2Manager getInstance() {
        return Holder.INSTANCE;
    }

    private Aux2Manager() {
        this.cameraFlag = 6;
    }

    public void registerExitListener(ActionCallback actionCallback) {
        this.actionCallback = actionCallback;
    }

    public void unRegisterExitListener() {
        this.actionCallback = null;
    }

    public void openAux2(Context context, int i, int i2) {
        Intent intent = new Intent();
        intent.setComponent(HzbhdComponentName.Aux2Activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Aux2Activity.SCALE_X_TAG, i);
        intent.putExtra(Aux2Activity.SCALE_Y_TAG, i2);
        context.startActivity(intent);
    }

    public void exitAux2(ActionTag.TAG tag, String str) {
        ActionCallback actionCallback = this.actionCallback;
        if (actionCallback != null) {
            actionCallback.actionDo(tag, str);
        }
    }

    public int getCameraFlag() {
        return this.cameraFlag;
    }

    public void setCameraFlag(int i) {
        this.cameraFlag = i;
    }
}
