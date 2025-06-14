package com.hzbhd.canbus.car._311;

import android.content.Context;
import android.view.MotionEvent;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionTouchListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.PanelKeyPageUiSet;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    private OnPanelKeyPositionListener mOnPanelKeyPositionListener = new OnPanelKeyPositionListener() { // from class: com.hzbhd.canbus.car._311.UiMgr.1
        @Override // com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener
        public void click(int i) {
            if (UiMgr.this.getCurrentCarId() != 0) {
                if (i == 0) {
                    UiMgr.this.SendMsg(22);
                } else {
                    if (i != 1) {
                        return;
                    }
                    UiMgr.this.SendMsg(21);
                    return;
                }
            }
            switch (i) {
                case 0:
                    UiMgr.this.SendMsg(8);
                    break;
                case 1:
                    UiMgr.this.SendMsg(9);
                    break;
                case 2:
                    UiMgr.this.SendMsg(10);
                    break;
                case 3:
                    UiMgr.this.SendMsg(11);
                    break;
                case 4:
                    UiMgr.this.SendMsg(12);
                    break;
                case 5:
                    UiMgr.this.SendMsg(13);
                    break;
                case 6:
                    UiMgr.this.SendMsg(14);
                    break;
                case 7:
                    UiMgr.this.SendMsg(15);
                    break;
                case 8:
                    UiMgr.this.SendMsg(16);
                    break;
                case 9:
                    UiMgr.this.SendMsg(4);
                    break;
                case 10:
                    UiMgr.this.SendMsg(1);
                    break;
                case 11:
                    UiMgr.this.SendMsg(5);
                    break;
                case 12:
                    UiMgr.this.SendMsg(6);
                    break;
                case 13:
                    UiMgr.this.SendMsg(7);
                    break;
                case 14:
                    UiMgr.this.SendMsg(3);
                    break;
                case 15:
                    UiMgr.this.SendMsg(2);
                    break;
            }
        }
    };
    private OnPanelKeyPositionTouchListener mOnPanelKeyPositionTouchListener = new OnPanelKeyPositionTouchListener() { // from class: com.hzbhd.canbus.car._311.UiMgr.2
        @Override // com.hzbhd.canbus.interfaces.OnPanelKeyPositionTouchListener
        public void onTouch(int i, MotionEvent motionEvent) {
            if (UiMgr.this.getCurrentCarId() != 0) {
                if (i == 0) {
                    if (motionEvent.getAction() == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 6, 22});
                    }
                    if (motionEvent.getAction() == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 6, 0});
                    }
                    return;
                }
                if (i != 1) {
                    return;
                }
                if (motionEvent.getAction() == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 6, 21});
                }
                if (motionEvent.getAction() == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 6, 0});
                    return;
                }
                return;
            }
            switch (i) {
                case 0:
                    if (motionEvent.getAction() == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 6, 8});
                    }
                    if (motionEvent.getAction() == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 6, 0});
                        break;
                    }
                    break;
                case 1:
                    if (motionEvent.getAction() == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 6, 9});
                    }
                    if (motionEvent.getAction() == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 6, 0});
                        break;
                    }
                    break;
                case 2:
                    if (motionEvent.getAction() == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 6, 10});
                    }
                    if (motionEvent.getAction() == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 6, 0});
                        break;
                    }
                    break;
                case 3:
                    if (motionEvent.getAction() == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 6, 11});
                    }
                    if (motionEvent.getAction() == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 6, 0});
                        break;
                    }
                    break;
                case 4:
                    if (motionEvent.getAction() == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 6, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED});
                    }
                    if (motionEvent.getAction() == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 6, 0});
                        break;
                    }
                    break;
                case 5:
                    if (motionEvent.getAction() == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 6, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED});
                    }
                    if (motionEvent.getAction() == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 6, 0});
                        break;
                    }
                    break;
                case 6:
                    if (motionEvent.getAction() == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 6, 14});
                    }
                    if (motionEvent.getAction() == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 6, 0});
                        break;
                    }
                    break;
                case 7:
                    if (motionEvent.getAction() == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 6, 15});
                    }
                    if (motionEvent.getAction() == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 6, 0});
                        break;
                    }
                    break;
                case 8:
                    if (motionEvent.getAction() == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 6, 16});
                    }
                    if (motionEvent.getAction() == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 6, 0});
                        break;
                    }
                    break;
                case 9:
                    if (motionEvent.getAction() == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 6, 4});
                    }
                    if (motionEvent.getAction() == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 6, 0});
                        break;
                    }
                    break;
                case 10:
                    if (motionEvent.getAction() == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 6, 1});
                    }
                    if (motionEvent.getAction() == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 6, 0});
                        break;
                    }
                    break;
                case 11:
                    if (motionEvent.getAction() == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 6, 5});
                    }
                    if (motionEvent.getAction() == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 6, 0});
                        break;
                    }
                    break;
                case 12:
                    if (motionEvent.getAction() == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 6, 6});
                    }
                    if (motionEvent.getAction() == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 6, 0});
                        break;
                    }
                    break;
                case 13:
                    if (motionEvent.getAction() == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 6, 7});
                    }
                    if (motionEvent.getAction() == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 6, 0});
                        break;
                    }
                    break;
                case 14:
                    if (motionEvent.getAction() == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 6, 3});
                    }
                    if (motionEvent.getAction() == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 6, 0});
                        break;
                    }
                    break;
                case 15:
                    if (motionEvent.getAction() == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 6, 2});
                    }
                    if (motionEvent.getAction() == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 6, 0});
                        break;
                    }
                    break;
            }
        }
    };
    private String[] str = {"panel_btn_num1", "panel_btn_num2", "panel_btn_num3", "panel_btn_num4", "panel_btn_num5", "panel_btn_num6", "panel_btn_num7", "panel_btn_num8", "panel_btn_num9", "panel_btn_left", "panel_btn_ok", "panel_btn_right", "panel_btn_fmam", "panel_btn_cdmp3", "panel_btn_bc"};

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
        panelKeyPageUiSet.setOnPanelKeyPositionListener(this.mOnPanelKeyPositionListener);
        panelKeyPageUiSet.setOnPanelKeyPositionTouchListener(this.mOnPanelKeyPositionTouchListener);
        if (getCurrentCarId() == 1) {
            panelKeyPageUiSet.setCount(2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void SendMsg(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, 6, (byte) i});
    }
}
