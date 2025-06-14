package com.hzbhd.canbus.car._72;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.util.TimerUtil;
import java.util.TimerTask;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    private TimerUtil mTimerUtil;
    private byte[] mVehicleSpeedRequestCommand;
    private TimerTask mVehicleSpeedRequestTask;

    public UiMgr(Context context) {
        getDriverDataPageUiSet(context).setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener() { // from class: com.hzbhd.canbus.car._72.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener
            public void onStatusChange(int i) {
                if (i == -1) {
                    UiMgr.this.getTimerUtil().startTimer(UiMgr.this.getVehicleSpeedRequestTask(), 0L, 333L);
                } else if (i == -2) {
                    UiMgr.this.getTimerUtil().stopTimer();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public TimerUtil getTimerUtil() {
        if (this.mTimerUtil == null) {
            this.mTimerUtil = new TimerUtil();
        }
        return this.mTimerUtil;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public TimerTask getVehicleSpeedRequestTask() {
        if (this.mVehicleSpeedRequestTask == null) {
            this.mVehicleSpeedRequestTask = new TimerTask() { // from class: com.hzbhd.canbus.car._72.UiMgr.2
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    CanbusMsgSender.sendMsg(UiMgr.this.getVehicleSpeedRequestCommand());
                }
            };
        }
        return this.mVehicleSpeedRequestTask;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] getVehicleSpeedRequestCommand() {
        if (this.mVehicleSpeedRequestCommand == null) {
            this.mVehicleSpeedRequestCommand = new byte[]{22, -112, 22, 0};
        }
        return this.mVehicleSpeedRequestCommand;
    }
}
