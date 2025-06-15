package com.hzbhd.canbus.car._462;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.constant.share.ShareConstants;
import com.hzbhd.proxy.share.ShareDataManager;
import com.hzbhd.proxy.share.interfaces.IShareStringListener;


public class UiMgr extends AbstractUiMgr {
    private MyPanoramicView panoramicView;
    float screenHeight;
    float screenWidth;

    public UiMgr(final Context context) {
        this.screenWidth = 0.0f;
        this.screenHeight = 0.0f;
        this.screenWidth = getScreenWidth(context);
        this.screenHeight = getScreenHeight(context);
        getParkPageUiSet(context).setOnPanoramicItemTouchListener(new OnPanoramicItemTouchListener() { // from class: com.hzbhd.canbus.car._462.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener
            public void onTouchItem(MotionEvent motionEvent) {
                if (UiMgr.this.panoramicView == null) {
                    UiMgr.this.panoramicView = new MyPanoramicView(context);
                }
                if (UiMgr.this.panoramicView.manualLock) {
                    if (motionEvent.getAction() == 0) {
                        int x = (int) ((10000.0f / UiMgr.this.screenWidth) * motionEvent.getX());
                        int msb = DataHandleUtils.getMsb(x);
                        int lsb = DataHandleUtils.getLsb(x);
                        int y = (int) ((10000.0f / UiMgr.this.screenHeight) * motionEvent.getY());
                        CanbusMsgSender.sendMsg(new byte[]{22, 24, -38, 65, 40, 1, (byte) lsb, (byte) msb, (byte) DataHandleUtils.getLsb(y), (byte) DataHandleUtils.getMsb(y), 0, 0, 0, 1});
                        return;
                    }
                    if (motionEvent.getAction() == 1) {
                        int x2 = (int) ((10000.0f / UiMgr.this.screenWidth) * motionEvent.getX());
                        int msb2 = DataHandleUtils.getMsb(x2);
                        int lsb2 = DataHandleUtils.getLsb(x2);
                        int y2 = (int) (motionEvent.getY() * (10000.0f / UiMgr.this.screenHeight));
                        CanbusMsgSender.sendMsg(new byte[]{22, 24, -38, 65, 40, 1, (byte) lsb2, (byte) msb2, (byte) DataHandleUtils.getLsb(y2), (byte) DataHandleUtils.getMsb(y2), 0, 0, 0, 1});
                    }
                }
            }
        });
    }

    private static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    private static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public void registerEvent(final Context context) {
        ShareDataManager.getInstance().registerShareStringListener(ShareConstants.SHARE_REQUEST_ALL_DATA, new IShareStringListener() { // from class: com.hzbhd.canbus.car._462.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.proxy.share.interfaces.IShareStringListener
            public final void onString(String str) {
                this.f$0.m881lambda$registerEvent$0$comhzbhdcanbuscar_462UiMgr(context, str);
            }
        });
    }

    /* renamed from: lambda$registerEvent$0$com-hzbhd-canbus-car-_462-UiMgr, reason: not valid java name */
    /* synthetic */ void m881lambda$registerEvent$0$comhzbhdcanbuscar_462UiMgr(Context context, String str) {
        if (str.equals("OPEN_360_PANOROMIC_PAGE")) {
            if (this.panoramicView == null) {
                this.panoramicView = new MyPanoramicView(context);
            }
            this.panoramicView.openManual();
        }
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public View getCusPanoramicView(Context context) {
        if (this.panoramicView == null) {
            this.panoramicView = new MyPanoramicView(context);
        }
        return this.panoramicView;
    }
}
