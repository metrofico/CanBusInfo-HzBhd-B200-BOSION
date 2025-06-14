package com.hzbhd.canbus.car._309;

import android.content.Context;
import android.view.MotionEvent;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.car._309.UiMgr;
import com.hzbhd.canbus.interfaces.OnAmplifierCreateAndDestroyListener;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionTouchListener;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.util.TimerUtil;
import java.util.TimerTask;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    private String[] mAirBtnListFrontBottom;
    private String[] mAirBtnListFrontLeft;
    private String[] mAirBtnListFrontRight;
    private String[] mAirBtnListFrontTop;
    private Context mContext;
    private TimerUtil timerUtil = new TimerUtil();
    OnAmplifierCreateAndDestroyListener MOnAmplifierCreateAndDestroyListener = new OnAmplifierCreateAndDestroyListener() { // from class: com.hzbhd.canbus.car._309.UiMgr.1
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierCreateAndDestroyListener
        public void create() {
            CanbusMsgSender.sendMsg(new byte[]{22, -54, 1, 0, 0, 0, 0, 0, 0, 0});
        }

        @Override // com.hzbhd.canbus.interfaces.OnAmplifierCreateAndDestroyListener
        public void destroy() {
            CanbusMsgSender.sendMsg(new byte[]{22, -54, 0, 0, 0, 0, 0, 0, 0, 0});
        }
    };
    OnAirWindSpeedUpDownClickListener mOnAirWindSpeedUpDownClickListenerFront = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._309.UiMgr.2
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            UiMgr.sendAirCmd(7);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            UiMgr.sendAirCmd(6);
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._309.UiMgr.3
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.sendAirCmd(12);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.sendAirCmd(13);
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._309.UiMgr.4
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.sendAirCmd(14);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.sendAirCmd(15);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._309.UiMgr.5
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.sendAirCommand(uiMgr.mAirBtnListFrontTop[i]);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._309.UiMgr.6
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.sendAirCommand(uiMgr.mAirBtnListFrontLeft[i]);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._309.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.sendAirCommand(uiMgr.mAirBtnListFrontRight[i]);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._309.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.sendAirCommand(uiMgr.mAirBtnListFrontBottom[i]);
        }
    };
    OnPanelKeyPositionTouchListener mOnPanelKeyPositionTouchListener = new AnonymousClass9();

    /* JADX INFO: Access modifiers changed from: private */
    public byte getByte(int i) {
        int i2;
        if (i >= 1 && i <= 9) {
            return (byte) i;
        }
        if (i >= 10 && i <= 19) {
            i2 = i + 6;
        } else {
            if (i < 20 || i > 23) {
                return (byte) 0;
            }
            i2 = i + 12;
        }
        return (byte) i2;
    }

    public UiMgr(Context context) {
        this.mContext = context;
        getPanelKeyPageUiSet(context).setOnPanelKeyPositionTouchListener(this.mOnPanelKeyPositionTouchListener);
        AirPageUiSet airUiSet = getAirUiSet(context);
        String[][] lineBtnAction = airUiSet.getFrontArea().getLineBtnAction();
        this.mAirBtnListFrontTop = lineBtnAction[0];
        this.mAirBtnListFrontLeft = lineBtnAction[1];
        this.mAirBtnListFrontRight = lineBtnAction[2];
        this.mAirBtnListFrontBottom = lineBtnAction[3];
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.mOnAirWindSpeedUpDownClickListenerFront);
        getAmplifierPageUiSet(context).setOnAmplifierCreateAndDestroyListener(this.MOnAmplifierCreateAndDestroyListener);
    }

    /* renamed from: com.hzbhd.canbus.car._309.UiMgr$9, reason: invalid class name */
    class AnonymousClass9 implements OnPanelKeyPositionTouchListener {
        AnonymousClass9() {
        }

        @Override // com.hzbhd.canbus.interfaces.OnPanelKeyPositionTouchListener
        public void onTouch(int i, MotionEvent motionEvent) {
            final byte b = UiMgr.this.getByte(i);
            if (motionEvent.getAction() == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, 116, b, 1});
                UiMgr.this.timerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._309.UiMgr.9.1
                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        CanbusMsgSender.sendMsg(new byte[]{22, 116, b, 2});
                    }
                }, 600L, 50L);
            }
            if (motionEvent.getAction() == 1) {
                UiMgr.this.timerUtil.stopTimer();
                new Thread(new Runnable() { // from class: com.hzbhd.canbus.car._309.UiMgr$9$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() throws InterruptedException {
                        UiMgr.AnonymousClass9.lambda$onTouch$0(b);
                    }
                }).start();
            }
        }

        static /* synthetic */ void lambda$onTouch$0(byte b) throws InterruptedException {
            try {
                Thread.sleep(50L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            CanbusMsgSender.sendMsg(new byte[]{22, 116, b, 0});
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirCommand(String str) {
        str.hashCode();
        switch (str) {
            case "front_defog":
                sendAirCmd(33);
                break;
            case "rear_defog":
                sendAirCmd(5);
                break;
            case "blow_positive":
                sendAirCmd(9);
                break;
            case "ac":
                sendAirCmd(8);
                break;
            case "auto":
                sendAirCmd(1);
                break;
            case "dual":
                sendAirCmd(2);
                break;
            case "power":
                sendAirCmd(10);
                break;
            case "in_out_cycle":
                if (GeneralAirData.in_out_cycle) {
                    sendAirCmd(38);
                    break;
                } else {
                    sendAirCmd(39);
                    break;
                }
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.hzbhd.canbus.car._309.UiMgr$10] */
    public static void sendAirCmd(final int i) {
        new Thread() { // from class: com.hzbhd.canbus.car._309.UiMgr.10
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte) i, 1});
                try {
                    sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte) i, 0});
            }
        }.start();
    }
}
