package com.hzbhd.canbus.car._280;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;


public class UiMgr extends AbstractUiMgr {
    static final String _280_AMPLIFIER_BALANCE = "_280_amplifier_balance";
    static final String _280_AMPLIFIER_BASS = "_280_amplifier_bass";
    static final String _280_AMPLIFIER_FADE = "_280_amplifier_fade";
    static final String _280_AMPLIFIER_MIDDLE = "_280_amplifier_middle";
    static final String _280_AMPLIFIER_TREBLE = "_280_amplifier_treble";
    private int data1;
    private int data2;
    private int data3;
    private int data4;
    private int data5;
    private Context mContext;
    private MsgMgr msgMgr;
    private final int VEHICLE_TYPE_AUTO_AC = 1;
    private final int VEHICLE_TYPE_MANUAL_AC = 2;
    private final int MSG_SEND_AIR_CMD_UP = 0;
    private Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.hzbhd.canbus.car._280.UiMgr.6
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -32, (byte) message.arg1, 0});
            }
        }
    };
    private int mDifferent = getCurrentCarId();

    public UiMgr(final Context context) {
        this.msgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        this.mContext = context;
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        amplifierPageUiSet.setOnAmplifierPositionListener(new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._280.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
            public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
                int i2 = AnonymousClass7.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[amplifierPosition.ordinal()];
                if (i2 == 1) {
                    UiMgr.this.data1 = i + 10;
                    SharePreUtil.setIntValue(context, UiMgr._280_AMPLIFIER_FADE, UiMgr.this.data1);
                    UiMgr.this.msgMgr.initAmplifierData(context);
                } else if (i2 == 2) {
                    UiMgr.this.data2 = i + 10;
                    SharePreUtil.setIntValue(context, UiMgr._280_AMPLIFIER_BALANCE, UiMgr.this.data2);
                    UiMgr.this.msgMgr.initAmplifierData(context);
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, (byte) UiMgr.this.data1, (byte) UiMgr.this.data2, (byte) UiMgr.this.data3, (byte) UiMgr.this.data4, (byte) UiMgr.this.data5});
            }
        });
        amplifierPageUiSet.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._280.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
            public void progress(AmplifierActivity.AmplifierBand amplifierBand, int i) {
                int i2 = AnonymousClass7.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[amplifierBand.ordinal()];
                if (i2 == 1) {
                    int i3 = i + 10;
                    CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, (byte) UiMgr.this.data1, (byte) UiMgr.this.data2, (byte) i3, (byte) UiMgr.this.data4, (byte) UiMgr.this.data5});
                    UiMgr.this.data3 = i3;
                    SharePreUtil.setIntValue(context, UiMgr._280_AMPLIFIER_TREBLE, UiMgr.this.data3);
                    UiMgr.this.msgMgr.initAmplifierData(context);
                    return;
                }
                if (i2 == 2) {
                    int i4 = i + 10;
                    CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, (byte) UiMgr.this.data1, (byte) UiMgr.this.data2, (byte) UiMgr.this.data3, (byte) i4, (byte) UiMgr.this.data5});
                    UiMgr.this.data4 = i4;
                    SharePreUtil.setIntValue(context, UiMgr._280_AMPLIFIER_MIDDLE, UiMgr.this.data4);
                    UiMgr.this.msgMgr.initAmplifierData(context);
                    return;
                }
                if (i2 != 3) {
                    return;
                }
                int i5 = i + 10;
                CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, (byte) UiMgr.this.data1, (byte) UiMgr.this.data2, (byte) UiMgr.this.data3, (byte) UiMgr.this.data4, (byte) i5});
                UiMgr.this.data5 = i5;
                SharePreUtil.setIntValue(context, UiMgr._280_AMPLIFIER_BASS, UiMgr.this.data5);
                UiMgr.this.msgMgr.initAmplifierData(context);
            }
        });
        final AirPageUiSet airUiSet = getAirUiSet(context);
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._280.UiMgr.3
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                UiMgr.this.sendAirCommand(3);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                UiMgr.this.sendAirCommand(2);
            }
        }, null, new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._280.UiMgr.4
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                UiMgr.this.sendAirCommand(5);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                UiMgr.this.sendAirCommand(4);
            }
        }});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._280.UiMgr.5
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr.this.sendAirCommand(9);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr.this.sendAirCommand(10);
            }
        });
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._280.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                this.f$0.m341lambda$new$0$comhzbhdcanbuscar_280UiMgr(airUiSet, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._280.UiMgr$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                this.f$0.m342lambda$new$1$comhzbhdcanbuscar_280UiMgr(airUiSet, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._280.UiMgr$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                this.f$0.m343lambda$new$2$comhzbhdcanbuscar_280UiMgr(airUiSet, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._280.UiMgr$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                this.f$0.m344lambda$new$3$comhzbhdcanbuscar_280UiMgr(airUiSet, i);
            }
        }});
        if (this.mDifferent == 2) {
            airUiSet.getFrontArea().setWindMaxLevel(4);
            removeFrontAirButtonByName(context, "dual");
            removeFrontAirButtonByName(context, "blow_positive");
            removeFrontAirButtonByName(context, "auto");
        }
    }

    /* renamed from: com.hzbhd.canbus.car._280.UiMgr$7, reason: invalid class name */
    static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand;
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition;

        static {
            int[] iArr = new int[AmplifierActivity.AmplifierBand.values().length];
            $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand = iArr;
            try {
                iArr[AmplifierActivity.AmplifierBand.TREBLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.MIDDLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.BASS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[AmplifierActivity.AmplifierPosition.values().length];
            $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition = iArr2;
            try {
                iArr2[AmplifierActivity.AmplifierPosition.FRONT_REAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[AmplifierActivity.AmplifierPosition.LEFT_RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* renamed from: lambda$new$0$com-hzbhd-canbus-car-_280-UiMgr, reason: not valid java name */
    /* synthetic */ void m341lambda$new$0$comhzbhdcanbuscar_280UiMgr(AirPageUiSet airPageUiSet, int i) {
        sendAirCommand(airPageUiSet.getFrontArea().getLineBtnAction()[0][i]);
    }

    /* renamed from: lambda$new$1$com-hzbhd-canbus-car-_280-UiMgr, reason: not valid java name */
    /* synthetic */ void m342lambda$new$1$comhzbhdcanbuscar_280UiMgr(AirPageUiSet airPageUiSet, int i) {
        sendAirCommand(airPageUiSet.getFrontArea().getLineBtnAction()[1][i]);
    }

    /* renamed from: lambda$new$2$com-hzbhd-canbus-car-_280-UiMgr, reason: not valid java name */
    /* synthetic */ void m343lambda$new$2$comhzbhdcanbuscar_280UiMgr(AirPageUiSet airPageUiSet, int i) {
        sendAirCommand(airPageUiSet.getFrontArea().getLineBtnAction()[2][i]);
    }

    /* renamed from: lambda$new$3$com-hzbhd-canbus-car-_280-UiMgr, reason: not valid java name */
    /* synthetic */ void m344lambda$new$3$comhzbhdcanbuscar_280UiMgr(AirPageUiSet airPageUiSet, int i) {
        sendAirCommand(airPageUiSet.getFrontArea().getLineBtnAction()[3][i]);
    }

    private void sendAirCommand(String str) {
        str.hashCode();
        switch (str) {
            case "front_defog":
                sendAirCommand(19);
                break;
            case "rear_defog":
                sendAirCommand(20);
                break;
            case "blow_positive":
                sendAirCommand(7);
                break;
            case "blow_negative":
                sendAirCommand(8);
                break;
            case "ac":
                sendAirCommand(23);
                break;
            case "auto":
                sendAirCommand(21);
                break;
            case "dual":
                sendAirCommand(16);
                break;
            case "power":
                sendAirCommand(1);
                break;
            case "in_out_cycle":
                sendAirCommand(25);
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirCommand(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -32, (byte) i, 1});
        Message messageObtain = Message.obtain();
        messageObtain.what = 0;
        messageObtain.arg1 = i;
        this.mHandler.sendMessageDelayed(messageObtain, 100L);
    }
}
