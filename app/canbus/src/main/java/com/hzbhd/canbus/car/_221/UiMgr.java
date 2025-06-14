package com.hzbhd.canbus.car._221;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.PanelKeyPageUiSet;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    private Context mContext;
    private OnAirTemperatureUpDownClickListener mOnUpDownClickListener = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._221.UiMgr.4
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirCommand(13);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirCommand(14);
        }
    };

    public UiMgr(Context context) {
        this.mContext = context;
        final AirPageUiSet airUiSet = getAirUiSet(context);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._221.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                this.f$0.m309lambda$new$0$comhzbhdcanbuscar_221UiMgr(airUiSet, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._221.UiMgr$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                this.f$0.m310lambda$new$1$comhzbhdcanbuscar_221UiMgr(airUiSet, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._221.UiMgr$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                this.f$0.m311lambda$new$2$comhzbhdcanbuscar_221UiMgr(airUiSet, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._221.UiMgr$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                this.f$0.m312lambda$new$3$comhzbhdcanbuscar_221UiMgr(airUiSet, i);
            }
        }});
        FrontArea frontArea = airUiSet.getFrontArea();
        OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListener = this.mOnUpDownClickListener;
        frontArea.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{onAirTemperatureUpDownClickListener, null, onAirTemperatureUpDownClickListener});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._221.UiMgr.1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr.this.sendAirCommand(12);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr.this.sendAirCommand(11);
            }
        });
        getParkPageUiSet(this.mContext).setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._221.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public void onClickItem(int i) {
                if (i == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -14, 10, 1});
                }
                if (i == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -14, 10, 2});
                }
                if (i == 2) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -14, 10, 3});
                }
                if (i == 3) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -14, 6, 0});
                }
            }
        });
        final PanelKeyPageUiSet panelKeyPageUiSet = getPanelKeyPageUiSet(context);
        panelKeyPageUiSet.setOnPanelKeyPositionListener(new OnPanelKeyPositionListener() { // from class: com.hzbhd.canbus.car._221.UiMgr.3
            @Override // com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener
            public void click(int i) {
                String str = panelKeyPageUiSet.getList().get(i);
                str.hashCode();
                if (str.equals("_221_panel_btn_rear_camera_open")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -14, 6, 1});
                }
            }
        });
    }

    /* renamed from: lambda$new$0$com-hzbhd-canbus-car-_221-UiMgr, reason: not valid java name */
    /* synthetic */ void m309lambda$new$0$comhzbhdcanbuscar_221UiMgr(AirPageUiSet airPageUiSet, int i) {
        sendAirCommand(airPageUiSet.getFrontArea().getLineBtnAction()[0][i]);
    }

    /* renamed from: lambda$new$1$com-hzbhd-canbus-car-_221-UiMgr, reason: not valid java name */
    /* synthetic */ void m310lambda$new$1$comhzbhdcanbuscar_221UiMgr(AirPageUiSet airPageUiSet, int i) {
        sendAirCommand(airPageUiSet.getFrontArea().getLineBtnAction()[1][i]);
    }

    /* renamed from: lambda$new$2$com-hzbhd-canbus-car-_221-UiMgr, reason: not valid java name */
    /* synthetic */ void m311lambda$new$2$comhzbhdcanbuscar_221UiMgr(AirPageUiSet airPageUiSet, int i) {
        sendAirCommand(airPageUiSet.getFrontArea().getLineBtnAction()[2][i]);
    }

    /* renamed from: lambda$new$3$com-hzbhd-canbus-car-_221-UiMgr, reason: not valid java name */
    /* synthetic */ void m312lambda$new$3$comhzbhdcanbuscar_221UiMgr(AirPageUiSet airPageUiSet, int i) {
        sendAirCommand(airPageUiSet.getFrontArea().getLineBtnAction()[3][i]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.hzbhd.canbus.car._221.UiMgr$5] */
    public void sendAirCommand(final int i) {
        new Thread() { // from class: com.hzbhd.canbus.car._221.UiMgr.5
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                CanbusMsgSender.sendMsg(new byte[]{22, 61, (byte) i, 1});
                try {
                    sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                CanbusMsgSender.sendMsg(new byte[]{22, 61, (byte) i, 0});
            }
        }.start();
    }

    private void sendAirCommand(String str) {
        str.hashCode();
        switch (str) {
            case "front_defog":
                sendAirCommand(5);
                break;
            case "ac":
                sendAirCommand(2);
                break;
            case "power":
                sendAirCommand(1);
                break;
            case "in_out_cycle":
                sendAirCommand(7);
                break;
            case "blow_head_foot":
                sendAirCommand(27);
                break;
            case "blow_foot":
                sendAirCommand(29);
                break;
            case "blow_head":
                sendAirCommand(26);
                break;
            case "blow_window_foot":
                sendAirCommand(28);
                break;
        }
    }
}
