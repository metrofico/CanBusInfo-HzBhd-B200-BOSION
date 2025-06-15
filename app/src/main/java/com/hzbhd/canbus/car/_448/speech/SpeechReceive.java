package com.hzbhd.canbus.car._448.speech;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car._448.air.AirData;
import com.hzbhd.canbus.car._448.speech.SpeechAction;
import com.hzbhd.canbus.car_cus._448.DvrObserver;
import com.hzbhd.canbus.car_cus._448.data.DvrMode;
import com.hzbhd.canbus.receiver.SpeechReceiver;
import com.hzbhd.midware.constant.HotKeyConstant;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import nfore.android.bt.res.NfDef;
import org.apache.log4j.net.SyslogAppender;




public class SpeechReceive {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static IntentFilter intentFilter = new IntentFilter();
    private static final Lazy<SpeechReceive> instance$delegate = LazyKt.lazy(new Function0<SpeechReceive>() { // from class: com.hzbhd.canbus.car._448.speech.SpeechReceive$Companion$instance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final SpeechReceive invoke() {
            return new SpeechReceive();
        }
    });
    private final String TAG = "SLDSpeech";
    private final String speech_to_can = SpeechReceiver.speech_to_can;
    private final String speech_rx_can = SpeechReceiver.speech_rx_can;
    private final String tx_action = "tx_action";
    private final String type = "type";
    private final String value = "value";

    /* compiled from: SpeechReceive.kt */
    
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        private final SpeechReceive getInstance() {
            return (SpeechReceive) SpeechReceive.instance$delegate.getValue();
        }

        public final SpeechReceive get() {
            return getInstance();
        }
    }

    public SpeechReceive() {
        intentFilter.addAction(SpeechReceiver.speech_to_can);
    }

    public final void register(Context context) {

        Log.d(this.TAG, "register: ");
        context.registerReceiver(new BroadcastReceiver() { // from class: com.hzbhd.canbus.car._448.speech.SpeechReceive.register.1
            /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if (intent == null || !intent.getAction().equals(SpeechReceive.this.speech_to_can)) {
                    return;
                }
                String stringExtra = intent.getStringExtra(SpeechReceive.this.tx_action);
                Log.d(SpeechReceive.this.TAG, "onReceive: tx_action_value =" + stringExtra);
                if (stringExtra != null) {
                    boolean z = true;
                    switch (stringExtra.hashCode()) {
                        case -2115768438:
                            if (stringExtra.equals(SpeechAction.SUN_VISOR_OPEN_HALF)) {
                                Log.d("AIR_STATE", "遮阳帘开一半");
                                SpeechSend.sendSpeechTTsBroadcast(context2, "暂不支持此功能");
                                break;
                            }
                            break;
                        case -1871342186:
                            if (stringExtra.equals("rear.right.window.close")) {
                                Log.d("AIR_STATE", "关闭车窗  后右");
                                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 21, 0, 108, 0, 0, 0, 0, 0, 0, 0});
                                break;
                            }
                            break;
                        case -1826242962:
                            if (stringExtra.equals(SpeechAction.COLD_CLOSE)) {
                                SpeechSend.sendSpeechTTsBroadcast(context2, "暂不支持操作制冷");
                                Log.d("AIR_STATE", "关闭制冷");
                                break;
                            }
                            break;
                        case -1391366141:
                            if (stringExtra.equals("skylight.open")) {
                                Log.d("AIR_STATE", "打开天窗");
                                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, 64, 0, 0, 0, 0, 0, 64, 0});
                                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, -64, 0, 0, 0, 0, 0, 0, 0});
                                break;
                            }
                            break;
                        case -1386876687:
                            if (stringExtra.equals("front.right.window.close")) {
                                Log.d("AIR_STATE", "关闭车窗  前右");
                                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 21, 0, 84, 0, 0, 0, 0, 0, 0, 0});
                                break;
                            }
                            break;
                        case -1355728726:
                            if (stringExtra.equals(SpeechAction.DVR_CLOSE)) {
                                Log.d("AIR_STATE", "关闭行车记录仪");
                                DvrObserver.getInstance().dataChange(DvrMode.SPEECH_EXIT_DVR);
                                break;
                            }
                            break;
                        case -1326924100:
                            if (stringExtra.equals(SpeechAction.AC_TEMPERATURE_DEC)) {
                                String typeValue = intent.getStringExtra(SpeechReceive.this.type);
                                String stringExtra2 = intent.getStringExtra(SpeechReceive.this.value);
                                int i = stringExtra2 != null ? Integer.parseInt(stringExtra2) : -1;
                                String str = typeValue;
                                if (!(str == null || str.length() == 0) && i != -1) {

                                    if (!Intrinsics.areEqual(typeValue, SpeechAction.TypeEnum.left.getValue())) {
                                        if (!Intrinsics.areEqual(typeValue, SpeechAction.TypeEnum.right.getValue())) {
                                            if (Intrinsics.areEqual(typeValue, SpeechAction.TypeEnum.empty.getValue())) {
                                                Log.d("AIR_STATE", "空调温度降低" + i);
                                                int i2 = AirData.temp_value - i;
                                                if (i2 < 16) {
                                                    i2 = 16;
                                                }
                                                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, 0, 0, 0, (byte) (i2 - 16), 0, 16, 0, 0});
                                                break;
                                            }
                                        } else {
                                            Log.d("AIR_STATE", "空调温度降低" + i);
                                            int i3 = AirData.temp_value - i;
                                            if (i3 < 16) {
                                                i3 = 16;
                                            }
                                            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, 0, 0, 0, (byte) (i3 - 16), 0, 16, 0, 0});
                                            break;
                                        }
                                    } else {
                                        Log.d("AIR_STATE", "空调温度降低" + i);
                                        int i4 = AirData.temp_value - i;
                                        if (i4 < 16) {
                                            i4 = 16;
                                        }
                                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, 0, 0, 0, (byte) (i4 - 16), 0, 16, 0, 0});
                                        break;
                                    }
                                }
                            }
                            break;
                        case -1326919016:
                            if (stringExtra.equals(SpeechAction.AC_TEMPERATURE_INC)) {
                                String typeValue2 = intent.getStringExtra(SpeechReceive.this.type);
                                String stringExtra3 = intent.getStringExtra(SpeechReceive.this.value);
                                int i5 = stringExtra3 != null ? Integer.parseInt(stringExtra3) : -1;
                                String str2 = typeValue2;
                                if (!(str2 == null || str2.length() == 0) && i5 != -1) {

                                    if (!Intrinsics.areEqual(typeValue2, SpeechAction.TypeEnum.left.getValue())) {
                                        if (!Intrinsics.areEqual(typeValue2, SpeechAction.TypeEnum.right.getValue())) {
                                            if (Intrinsics.areEqual(typeValue2, SpeechAction.TypeEnum.empty.getValue())) {
                                                Log.d("AIR_STATE", "空调温度增加" + i5);
                                                int i6 = AirData.temp_value + i5;
                                                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, 0, 0, 0, (byte) ((i6 <= 31 ? i6 : 31) - 16), 0, 16, 0, 0});
                                                break;
                                            }
                                        } else {
                                            Log.d("AIR_STATE", "空调温度增加" + i5);
                                            int i7 = AirData.temp_value + i5;
                                            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, 0, 0, 0, (byte) ((i7 <= 31 ? i7 : 31) - 16), 0, 16, 0, 0});
                                            break;
                                        }
                                    } else {
                                        Log.d("AIR_STATE", "空调温度增加" + i5);
                                        int i8 = AirData.temp_value + i5;
                                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, 0, 0, 0, (byte) ((i8 <= 31 ? i8 : 31) - 16), 0, 16, 0, 0});
                                        break;
                                    }
                                }
                            }
                            break;
                        case -1226331153:
                            if (stringExtra.equals("ac.mode")) {
                                String typeValue3 = intent.getStringExtra(SpeechReceive.this.type);
                                String str3 = typeValue3;
                                if (str3 != null && str3.length() != 0) {
                                    z = false;
                                }
                                if (!z) {

                                    if (!Intrinsics.areEqual(typeValue3, SpeechAction.WindValueEnum.face.getValue())) {
                                        if (!Intrinsics.areEqual(typeValue3, SpeechAction.WindValueEnum.foot.getValue())) {
                                            if (!Intrinsics.areEqual(typeValue3, SpeechAction.WindValueEnum.facefoot.getValue())) {
                                                if (!Intrinsics.areEqual(typeValue3, SpeechAction.WindValueEnum.defrost.getValue())) {
                                                    if (!Intrinsics.areEqual(typeValue3, SpeechAction.WindValueEnum.footdefrost.getValue())) {
                                                        if (Intrinsics.areEqual(typeValue3, SpeechAction.WindValueEnum.auto.getValue())) {
                                                            Log.d("AIR_STATE", "吹风模式 自动");
                                                            SpeechSend.sendSpeechTTsBroadcast(context2, "暂不支持此功能");
                                                            break;
                                                        }
                                                    } else {
                                                        Log.d("AIR_STATE", "吹风模式 吹脚除霜");
                                                        SpeechSend.sendSpeechTTsBroadcast(context2, "暂不支持此功能");
                                                        break;
                                                    }
                                                } else {
                                                    Log.d("AIR_STATE", "吹风模式 除霜");
                                                    CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 21, 48, 0, 0, 0, 0, 0, 0, 0, 0});
                                                    break;
                                                }
                                            } else {
                                                Log.d("AIR_STATE", "吹风模式 吹面吹脚");
                                                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 21, 40, 0, 0, 0, 0, 0, 0, 0, 0});
                                                break;
                                            }
                                        } else {
                                            Log.d("AIR_STATE", "吹风模式 吹脚");
                                            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 21, 32, 0, 0, 0, 0, 0, 0, 0, 0});
                                            break;
                                        }
                                    } else {
                                        Log.d("AIR_STATE", "吹风模式 吹面");
                                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 21, 36, 0, 0, 0, 0, 0, 0, 0, 0});
                                        break;
                                    }
                                }
                            }
                            break;
                        case -1226270570:
                            if (stringExtra.equals("ac.open")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, 0, 0, 16, 0, 2, 0, 0, 0});
                                Log.d("AIR_STATE", "打开空调");
                                break;
                            }
                            break;
                        case -1151750824:
                            if (stringExtra.equals(SpeechAction.DVR_OPEN)) {
                                Log.d("AIR_STATE", "打开行车记录仪");
                                DvrObserver.getInstance().dataChange(DvrMode.SPEECH_START_DVR);
                                break;
                            }
                            break;
                        case -964646904:
                            if (stringExtra.equals(SpeechAction.AC_DEFROST_FRONT_MAX)) {
                                Log.d("AIR_STATE", "最大前除霜");
                                SpeechSend.sendSpeechTTsBroadcast(context2, "暂不支持此功能");
                                break;
                            }
                            break;
                        case -891288852:
                            if (stringExtra.equals("rear.right.window.open")) {
                                Log.d("AIR_STATE", "打开车窗  后右");
                                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 21, 0, 104, 0, 0, 0, 0, 0, 0, 0});
                                break;
                            }
                            break;
                        case -866529054:
                            if (stringExtra.equals("air.in.out.cycle.off")) {
                                Log.d("AIR_STATE", "切换外循环");
                                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 21, 3, 0, 0, 0, 0, 0, 0, 0, 0});
                                break;
                            }
                            break;
                        case -776229461:
                            if (stringExtra.equals("sun.visor.close")) {
                                Log.d("AIR_STATE", "关闭遮阳帘");
                                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, 0, 0, ByteCompanionObject.MIN_VALUE, 0, ByteCompanionObject.MIN_VALUE, 0, 0, 0});
                                break;
                            }
                            break;
                        case -655752154:
                            if (stringExtra.equals("ac.windlevel.max")) {
                                Log.d("AIR_STATE", "最大风速");
                                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, 0, 0, 0, -16, 0, 0, 0, 0});
                                break;
                            }
                            break;
                        case -655751916:
                            if (stringExtra.equals("ac.windlevel.min")) {
                                Log.d("AIR_STATE", "最小风速");
                                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, 0, 0, 0, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 0});
                                break;
                            }
                            break;
                        case -537609520:
                            if (stringExtra.equals(SpeechAction.AC_LEFT_RIGHT_SYNC_OPEN)) {
                                Log.d("AIR_STATE", "打开空调同步");
                                SpeechSend.sendSpeechTTsBroadcast(context2, "暂不支持此功能");
                                break;
                            }
                            break;
                        case -193868961:
                            if (stringExtra.equals("skylight.close")) {
                                Log.d("AIR_STATE", "关闭天窗");
                                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 0, 0, 64, 0});
                                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, -64, 0, 0, 0, 0, 0, 0, 0});
                                break;
                            }
                            break;
                        case -64220354:
                            if (stringExtra.equals(SpeechAction.SKYLIGHT_OPEN_HALF)) {
                                Log.d("AIR_STATE", "天窗开一半");
                                SpeechSend.sendSpeechTTsBroadcast(context2, "暂不支持此功能");
                                break;
                            }
                            break;
                        case 21742501:
                            if (stringExtra.equals(SpeechAction.DVR_TAKE_PICTURE)) {
                                Log.d("AIR_STATE", "我要拍照");
                                DvrObserver.getInstance().dataChange(DvrMode.SPEECH_TAKE_PICTURE);
                                break;
                            }
                            break;
                        case 33987515:
                            if (stringExtra.equals(SpeechAction.AC_DEFROST_BEHIND_CLOSE)) {
                                Log.d("AIR_STATE", "关闭后除霜");
                                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 21, 0, 2, 0, 0, 0, 0, 0, 0, 0});
                                break;
                            }
                            break;
                        case 160790566:
                            if (stringExtra.equals(SpeechAction.AC_DEFROST_FRONT_OPEN)) {
                                Log.d("AIR_STATE", "打开前除霜除雾");
                                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 21, 48, 0, 0, 0, 0, 0, 0, 0, 0});
                                break;
                            }
                            break;
                        case 292772663:
                            if (stringExtra.equals("rear.left.window.close")) {
                                Log.d("AIR_STATE", "关闭车窗  后左");
                                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 21, 0, 96, 0, 0, 0, 0, 0, 0, 0});
                                break;
                            }
                            break;
                        case 345504582:
                            if (stringExtra.equals("front.left.window.open")) {
                                Log.d("AIR_STATE", "打开车窗  前左");
                                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 21, 0, 68, 0, 0, 0, 0, 0, 0, 0});
                                break;
                            }
                            break;
                        case 436792497:
                            if (stringExtra.equals(SpeechAction.DVR_RECORD_STOP)) {
                                Log.d("AIR_STATE", "停止录像");
                                DvrObserver.getInstance().dataChange(DvrMode.SPEECH_RECORD_STOP);
                                break;
                            }
                            break;
                        case 502782514:
                            if (stringExtra.equals(SpeechAction.AC_LEFT_RIGHT_SYNC_CLOSE)) {
                                Log.d("AIR_STATE", "关闭空调同步");
                                SpeechSend.sendSpeechTTsBroadcast(context2, "暂不支持此功能");
                                break;
                            }
                            break;
                        case 629126444:
                            if (stringExtra.equals("ac.close")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, 0, 0, 32, 0, 2, 0, 0, 0});
                                Log.d("AIR_STATE", "关闭空调");
                                break;
                            }
                            break;
                        case 655652243:
                            if (stringExtra.equals(SpeechAction.DVR_RECORD_START)) {
                                Log.d("AIR_STATE", "开始录像");
                                DvrObserver.getInstance().dataChange(DvrMode.SPEECH_RECORD_START);
                                break;
                            }
                            break;
                        case 678348700:
                            if (stringExtra.equals(SpeechAction.AC_DEFROST_FRONT_CLOSE)) {
                                Log.d("AIR_STATE", "关闭前除霜");
                                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 21, 52, 0, 0, 0, 0, 0, 0, 0, 0});
                                break;
                            }
                            break;
                        case 919236962:
                            if (stringExtra.equals(SpeechAction.SKYLIGHT_CLOSE_HALF)) {
                                Log.d("AIR_STATE", "天窗关一半");
                                SpeechSend.sendSpeechTTsBroadcast(context2, "暂不支持此功能");
                                break;
                            }
                            break;
                        case 925454385:
                            if (stringExtra.equals("front.right.window.open")) {
                                Log.d("AIR_STATE", "打开车窗  前右");
                                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 21, 0, 80, 0, 0, 0, 0, 0, 0, 0});
                                break;
                            }
                            break;
                        case 1146265120:
                            if (stringExtra.equals("ac.windlevel.down")) {
                                Log.d("AIR_STATE", "风量减小");
                                int i9 = AirData.wind_level - 1;
                                if (i9 < 1) {
                                    i9 = 1;
                                }
                                int i10 = i9 == 1 ? 128 : 0;
                                if (i9 == 2) {
                                    i10 = 144;
                                }
                                if (i9 == 3) {
                                    i10 = 160;
                                }
                                if (i9 == 4) {
                                    i10 = SyslogAppender.LOG_LOCAL6;
                                }
                                if (i9 == 5) {
                                    i10 = 192;
                                }
                                if (i9 == 6) {
                                    i10 = HotKeyConstant.K_SOS;
                                }
                                if (i9 == 7) {
                                    i10 = 224;
                                }
                                if (i9 == 8) {
                                    i10 = NfDef.STATE_3WAY_M_HOLD;
                                }
                                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, 0, 0, 0, (byte) i10, 0, 0, 0, 0});
                                break;
                            }
                            break;
                        case 1168724146:
                            if (stringExtra.equals(SpeechAction.AC_HEAT_MAX)) {
                                Log.d("AIR_STATE", "最大制热");
                                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, 0, 0, 0, 15, 0, 16, 0, 0});
                                break;
                            }
                            break;
                        case 1225772921:
                            if (stringExtra.equals("ac.windlevel.to")) {
                                String stringExtra4 = intent.getStringExtra(SpeechReceive.this.type);
                                String stringExtra5 = intent.getStringExtra(SpeechReceive.this.value);
                                int i11 = stringExtra5 != null ? Integer.parseInt(stringExtra5) : -1;
                                String str4 = stringExtra4;
                                if (!(str4 == null || str4.length() == 0) && i11 != -1 && stringExtra4.equals(SpeechAction.TypeEnum.absolute.getValue())) {
                                    Log.d("AIR_STATE", "风量调到" + i11);
                                    int i12 = i11 == 1 ? 128 : 0;
                                    if (i11 == 2) {
                                        i12 = 144;
                                    }
                                    if (i11 == 3) {
                                        i12 = 160;
                                    }
                                    if (i11 == 4) {
                                        i12 = SyslogAppender.LOG_LOCAL6;
                                    }
                                    if (i11 == 5) {
                                        i12 = 192;
                                    }
                                    if (i11 == 6) {
                                        i12 = HotKeyConstant.K_SOS;
                                    }
                                    if (i11 == 7) {
                                        i12 = 224;
                                    }
                                    if (i11 == 8) {
                                        i12 = NfDef.STATE_3WAY_M_HOLD;
                                    }
                                    CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, 0, 0, 0, (byte) i12, 0, 0, 0, 0});
                                    break;
                                }
                            }
                            break;
                        case 1225772953:
                            if (stringExtra.equals("ac.windlevel.up")) {
                                Log.d("AIR_STATE", "风量增大");
                                int i13 = AirData.wind_level + 1;
                                if (i13 > 8) {
                                    i13 = 8;
                                }
                                int i14 = i13 == 1 ? 128 : 0;
                                if (i13 == 2) {
                                    i14 = 144;
                                }
                                if (i13 == 3) {
                                    i14 = 160;
                                }
                                if (i13 == 4) {
                                    i14 = SyslogAppender.LOG_LOCAL6;
                                }
                                if (i13 == 5) {
                                    i14 = 192;
                                }
                                if (i13 == 6) {
                                    i14 = HotKeyConstant.K_SOS;
                                }
                                if (i13 == 7) {
                                    i14 = 224;
                                }
                                if (i13 == 8) {
                                    i14 = NfDef.STATE_3WAY_M_HOLD;
                                }
                                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, 0, 0, 0, (byte) i14, 0, 0, 0, 0});
                                break;
                            }
                            break;
                        case 1358234944:
                            if (stringExtra.equals("all.windows.close")) {
                                Log.d("AIR_STATE", "关闭车窗  全部");
                                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 21, 0, 120, 0, 0, 0, 0, 0, 0, 0});
                                break;
                            }
                            break;
                        case 1360794679:
                            if (stringExtra.equals("sun.visor.open")) {
                                Log.d("AIR_STATE", "打开遮阳帘");
                                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, 0, 0, 64, 0, ByteCompanionObject.MIN_VALUE, 0, 0, 0});
                                break;
                            }
                            break;
                        case 1481217153:
                            if (stringExtra.equals("ac.temperature.to")) {
                                String typeValue4 = intent.getStringExtra(SpeechReceive.this.type);
                                String stringExtra6 = intent.getStringExtra(SpeechReceive.this.value);
                                int i15 = stringExtra6 != null ? Integer.parseInt(stringExtra6) : -1;
                                String str5 = typeValue4;
                                if (!(str5 == null || str5.length() == 0) && i15 != -1) {

                                    if (!Intrinsics.areEqual(typeValue4, SpeechAction.TypeEnum.left.getValue())) {
                                        if (!Intrinsics.areEqual(typeValue4, SpeechAction.TypeEnum.right.getValue())) {
                                            if (Intrinsics.areEqual(typeValue4, SpeechAction.TypeEnum.empty.getValue())) {
                                                Log.d("AIR_STATE", "空调温度调至" + SpeechReceive.this.value);
                                                if (i15 >= 16 && i15 <= 31) {
                                                    CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, 0, 0, 0, (byte) (i15 - 16), 0, 16, 0, 0});
                                                    break;
                                                } else {
                                                    SpeechSend.sendSpeechTTsBroadcast(context2, "暂不支持调至该温度值");
                                                    break;
                                                }
                                            }
                                        } else {
                                            Log.d("AIR_STATE", "空调温度调至" + SpeechReceive.this.value);
                                            if (i15 >= 16 && i15 <= 31) {
                                                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, 0, 0, 0, (byte) (i15 - 16), 0, 16, 0, 0});
                                                break;
                                            } else {
                                                SpeechSend.sendSpeechTTsBroadcast(context2, "暂不支持调至该温度值");
                                                break;
                                            }
                                        }
                                    } else {
                                        Log.d("AIR_STATE", "空调温度调至" + SpeechReceive.this.value);
                                        if (i15 >= 16 && i15 <= 31) {
                                            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, 0, 0, 0, (byte) (i15 - 16), 0, 16, 0, 0});
                                            break;
                                        } else {
                                            SpeechSend.sendSpeechTTsBroadcast(context2, "暂不支持调至该温度值");
                                            break;
                                        }
                                    }
                                }
                            }
                            break;
                        case 1496068108:
                            if (stringExtra.equals("air.in.out.cycle.on")) {
                                Log.d("AIR_STATE", "切换内循环");
                                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 21, 2, 0, 0, 0, 0, 0, 0, 0, 0});
                                break;
                            }
                            break;
                        case 1604017940:
                            if (stringExtra.equals(SpeechAction.COLD_OPEN)) {
                                SpeechSend.sendSpeechTTsBroadcast(context2, "暂不支持操作制冷");
                                Log.d("AIR_STATE", "打开制冷");
                                break;
                            }
                            break;
                        case 1723129227:
                            if (stringExtra.equals(SpeechAction.AC_COOL_MAX)) {
                                Log.d("AIR_STATE", "最大制冷");
                                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, 0, 0, 0, 0, 0, 16, 0, 0});
                                break;
                            }
                            break;
                        case 1745755798:
                            if (stringExtra.equals(SpeechAction.SUN_VISOR_CLOSE_HALF)) {
                                Log.d("AIR_STATE", "遮阳帘关一半");
                                SpeechSend.sendSpeechTTsBroadcast(context2, "暂不支持此功能");
                                break;
                            }
                            break;
                        case 1941120039:
                            if (stringExtra.equals(SpeechAction.AC_DEFROST_BEHIND_OPEN)) {
                                Log.d("AIR_STATE", "打开后除霜");
                                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 21, 0, 3, 0, 0, 0, 0, 0, 0, 0});
                                break;
                            }
                            break;
                        case 1949467947:
                            if (stringExtra.equals("rear.left.window.open")) {
                                Log.d("AIR_STATE", "打开车窗  后左");
                                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 21, 0, 92, 0, 0, 0, 0, 0, 0, 0});
                                break;
                            }
                            break;
                        case 1983837698:
                            if (stringExtra.equals("all.windows.open")) {
                                Log.d("AIR_STATE", "打开车窗  所有");
                                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 21, 0, 116, 0, 0, 0, 0, 0, 0, 0});
                                break;
                            }
                            break;
                        case 2109515900:
                            if (stringExtra.equals("front.left.window.close")) {
                                Log.d("AIR_STATE", "关闭车窗  前左");
                                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 21, 0, 72, 0, 0, 0, 0, 0, 0, 0});
                                break;
                            }
                            break;
                    }
                }
            }
        }, intentFilter);
    }
}
