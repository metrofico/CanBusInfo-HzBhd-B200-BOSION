package com.hzbhd.canbus.vm.listener;

import com.hzbhd.canbus.vm.Vm;
import com.hzbhd.canbus.vm.util.BhdWindowManager;
import com.hzbhd.commontools.utils.SystemPropertiesUtils;
import com.hzbhd.constant.share.ShareConstants;
import com.hzbhd.proxy.share.ShareDataManager;
import com.hzbhd.proxy.share.interfaces.IShareIntListener;
import com.hzbhd.util.HandlerThreadUtilKt;
import com.hzbhd.util.LogUtil;


public final class ReverseListener {
    private Runnable actionBefortViewInit;
    private int sysReverse;
    private int mReverseState = -1;
    private int userReverse = -1;

    public ReverseListener() {
        this.sysReverse = -1;
        this.sysReverse = ShareDataManager.getInstance().registerShareIntListener(ShareConstants.SHARE_MISC_REVERSE, new IShareIntListener() { // from class: com.hzbhd.canbus.vm.listener.ReverseListener$$ExternalSyntheticLambda0
            @Override // com.hzbhd.proxy.share.interfaces.IShareIntListener
            public final void onInt(int i) {

                sysReverse = i;
                updateReverse();
            }
        });
        updateReverse();
    }

    public final Runnable getActionBefortViewInit() {
        return this.actionBefortViewInit;
    }

    public final void setActionBefortViewInit(Runnable function0) {
        this.actionBefortViewInit = function0;
    }

    public final int getUserReverse() {
        return this.userReverse;
    }

    public final void setUserReverse(int i) {
        this.userReverse = i;
    }

    public final int getSysReverse() {
        return this.sysReverse;
    }

    public final void setSysReverse(int i) {
        this.sysReverse = i;
    }


    public final boolean isReversing() {
        return this.mReverseState == 1;
    }

    private final void updateReverse() {
        int i = (this.sysReverse == 1 || this.userReverse == 1) ? 1 : 0;
        if (LogUtil.log5()) {
            LogUtil.d("reverseState : " + i + ", mIsCarBackGpioLevel=" + this.mReverseState);
        }
        if (this.mReverseState != i) {
            if (LogUtil.log5()) {
                LogUtil.d("GpioOperator=detectCarBackState: mIsCarBackGpioLevel=" + this.mReverseState + ", level=" + i);
            }
            this.mReverseState = i;
            if (i == 0) {
                if (LogUtil.log5()) {
                    LogUtil.d("stop camera ");
                }
                BhdWindowManager.INSTANCE.removeReverseView();
                SystemPropertiesUtils.set("BackCameraUI", "false");
                HandlerThreadUtilKt.runUi(new Runnable() {
                    @Override
                    public void run() {
                        Vm.getVm().getData().getReverse().isReverse().setValue(false);
                    }
                });

                return;
            }
            if (i != 1) {
                return;
            }
            if (LogUtil.log5()) {
                LogUtil.d("start camera ");
            }
            Runnable function0 = this.actionBefortViewInit;
            if (function0 != null) {
                function0.run();
            }
            BhdWindowManager.INSTANCE.addReverseView();
            SystemPropertiesUtils.set("BackCameraUI", "true");
            HandlerThreadUtilKt.runUi(new Runnable() {
                @Override
                public void run() {
                    Vm.getVm().getData().getReverse().isReverse().setValue(true);
                }
            });

        }
    }
}
