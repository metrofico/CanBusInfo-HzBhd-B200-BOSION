package com.hzbhd.canbus.car._60;

import android.content.Context;
import android.view.MotionEvent;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionTouchListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.PanelKeyPageUiSet;
import com.hzbhd.canbus.util.TimerUtil;
import java.util.TimerTask;


public class UiMgr extends AbstractUiMgr {
    private OnPanelKeyPositionTouchListener mOnPanelKeyPositionTouchListener = new OnPanelKeyPositionTouchListener() { // from class: com.hzbhd.canbus.car._60.UiMgr$$ExternalSyntheticLambda0
        @Override // com.hzbhd.canbus.interfaces.OnPanelKeyPositionTouchListener
        public final void onTouch(int i, MotionEvent motionEvent) {
            m918lambda$new$0$comhzbhdcanbuscar_60UiMgr(i, motionEvent);
        }
    };
    private String[] str = {"panel_btn_num1", "panel_btn_num2", "panel_btn_num3", "panel_btn_num4", "panel_btn_num5", "panel_btn_num6", "panel_btn_num7", "panel_btn_num8", "panel_btn_num9", "panel_btn_left", "panel_btn_ok", "panel_btn_right", "panel_btn_fmam", "panel_btn_cdmp3", "panel_btn_bc"};
    private final TimerUtil mTimerUtil = new TimerUtil();

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        int currentCarId = getCurrentCarId();
        if (currentCarId == 0) {
            removePanelBtnKeyByName(context, "panel_btn_clock");
        } else if (currentCarId == 1) {
            int i = 0;
            while (true) {
                String[] strArr = this.str;
                if (i >= strArr.length) {
                    break;
                }
                removePanelBtnKeyByName(context, strArr[i]);
                i++;
            }
        }
        removeMainPrjBtnByName(context, MainAction.DRIVE_DATA);
    }

    public UiMgr(Context context) {
        PanelKeyPageUiSet panelKeyPageUiSet = getPanelKeyPageUiSet(context);
        panelKeyPageUiSet.setOnPanelKeyPositionTouchListener(this.mOnPanelKeyPositionTouchListener);
        if (getCurrentCarId() == 1) {
            panelKeyPageUiSet.setCount(2);
        }
    }

    /* renamed from: lambda$new$0$com-hzbhd-canbus-car-_60-UiMgr, reason: not valid java name */
    /* synthetic */ void m918lambda$new$0$comhzbhdcanbuscar_60UiMgr(int i, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action != 1) {
                return;
            }
            onKeyUp();
            return;
        }
        if (getCurrentCarId() != 0) {
            if (i == 0) {
                onKeyDown(22);
            } else {
                if (i != 1) {
                    return;
                }
                onKeyDown(21);
                return;
            }
        }
        switch (i) {
            case 0:
                onKeyDown(8);
                break;
            case 1:
                onKeyDown(9);
                break;
            case 2:
                onKeyDown(10);
                break;
            case 3:
                onKeyDown(11);
                break;
            case 4:
                onKeyDown(12);
                break;
            case 5:
                onKeyDown(13);
                break;
            case 6:
                onKeyDown(14);
                break;
            case 7:
                onKeyDown(15);
                break;
            case 8:
                onKeyDown(16);
                break;
            case 9:
                onKeyDown(4);
                break;
            case 10:
                onKeyDown(1);
                break;
            case 11:
                onKeyDown(5);
                break;
            case 12:
                onKeyDown(6);
                break;
            case 13:
                onKeyDown(7);
                break;
            case 14:
                onKeyDown(3);
                break;
            case 15:
                onKeyDown(2);
                break;
        }
    }

    private void onKeyDown(final int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, 6, (byte) i});
        this.mTimerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._60.UiMgr.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                CanbusMsgSender.sendMsg(new byte[]{22, 6, (byte) i});
            }
        }, 300L, 200L);
    }

    private void onKeyUp() {
        this.mTimerUtil.stopTimer();
        CanbusMsgSender.sendMsg(new byte[]{22, 6, 0});
    }
}
