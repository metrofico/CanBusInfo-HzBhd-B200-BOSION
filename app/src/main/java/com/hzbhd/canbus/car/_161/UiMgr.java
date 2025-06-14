package com.hzbhd.canbus.car._161;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AirBtnAction;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SetTimeView;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.constant.share.lcd.LcdInfoShare;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlinx.coroutines.scheduling.WorkQueueKt;
import nfore.android.bt.res.NfDef;

/* compiled from: UiMgr.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\u0018\u0000 ,2\u00020\u0001:\u0001,B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0014\u001a\u00020\fH\u0002J\u0010\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u0006H\u0002J\u001d\u0010\u0017\u001a\u00020\u00062\u000e\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0019H\u0002¢\u0006\u0002\u0010\u001aJ\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0010\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0018\u0010\u001f\u001a\u00020\u00062\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010 \u001a\u00020!J\u0010\u0010\"\u001a\u00020#2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0016\u0010$\u001a\u00020\u00062\u0006\u0010%\u001a\u00020\u00062\u0006\u0010&\u001a\u00020\u0006J\u0018\u0010'\u001a\u00020#2\u0006\u0010(\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\u0006H\u0002J\u0010\u0010'\u001a\u00020#2\u0006\u0010*\u001a\u00020!H\u0002J\u0010\u0010+\u001a\u00020#2\u0006\u0010(\u001a\u00020\u0006H\u0002R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lcom/hzbhd/canbus/car/_161/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "Data0", "", "getData0", "()I", "setData0", "(I)V", "m0x82Command", "", "mDifferent", "mHandler", "Landroid/os/Handler;", "getMHandler", "()Landroid/os/Handler;", "mMsgMgr", "Lcom/hzbhd/canbus/car/_161/MsgMgr;", "get0x88Command", "get0x89Command", "item", "getDecimalFrom8Bit", "bit", "", "([Ljava/lang/Integer;)I", "getMsgMgr", "getOpenOrClose", "boolean", "", "getSettingLeftIndexes", "titleSrn", "", "removeSettingItemWithDifferent", "", "resolvedata", "a", "value", "sendAirCommand", "command", "param", LcdInfoShare.MediaInfo.title, "sendAirCommandUpDown", "Companion", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public final class UiMgr extends AbstractUiMgr {
    private static final int AIR_COMMAND_DATA_TYPE = 138;
    private static final String TAG = "_1161_UiMgr";
    private static final String _161_AMPLIFIER_DATA = "_161_amplifier_data";
    public static final int _161_AMPLIFIER_DATA_MID = 9;
    public static final String _161_LCD_MODE_LEFT = "_161_lcd_mode_left";
    public static final String _161_LCD_MODE_RIGHT = "_161_lcd_mode_right";
    private int Data0;
    private final byte[] m0x82Command;
    private final int mDifferent;
    private final Handler mHandler;
    private MsgMgr mMsgMgr;

    private final int getOpenOrClose(boolean z) {
        return !z ? 1 : 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x00b6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public UiMgr(final android.content.Context r24) {
        /*
            Method dump skipped, instructions count: 621
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._161.UiMgr.<init>(android.content.Context):void");
    }

    public final Handler getMHandler() {
        return this.mHandler;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-6$lambda-5$lambda-0, reason: not valid java name */
    public static final void m239lambda6$lambda5$lambda0(UiMgr this$0, FrontArea frontArea, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str = frontArea.getLineBtnAction()[0][i];
        Intrinsics.checkNotNullExpressionValue(str, "lineBtnAction[0][position]");
        this$0.sendAirCommand(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-6$lambda-5$lambda-1, reason: not valid java name */
    public static final void m240lambda6$lambda5$lambda1(UiMgr this$0, FrontArea frontArea, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str = frontArea.getLineBtnAction()[1][i];
        Intrinsics.checkNotNullExpressionValue(str, "lineBtnAction[1][position]");
        this$0.sendAirCommand(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-6$lambda-5$lambda-2, reason: not valid java name */
    public static final void m241lambda6$lambda5$lambda2(UiMgr this$0, FrontArea frontArea, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str = frontArea.getLineBtnAction()[2][i];
        Intrinsics.checkNotNullExpressionValue(str, "lineBtnAction[2][position]");
        this$0.sendAirCommand(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-6$lambda-5$lambda-3, reason: not valid java name */
    public static final void m242lambda6$lambda5$lambda3(UiMgr this$0, FrontArea frontArea, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str = frontArea.getLineBtnAction()[3][i];
        Intrinsics.checkNotNullExpressionValue(str, "lineBtnAction[3][position]");
        this$0.sendAirCommand(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-6$lambda-5$lambda-4, reason: not valid java name */
    public static final void m243lambda6$lambda5$lambda4(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -113, 33});
        CanbusMsgSender.sendMsg(new byte[]{22, -113, 54});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$sendAmplifierCommand(byte[] bArr, Context context, Charset charset, UiMgr uiMgr, Function1<? super byte[], Unit> function1) {
        function1.invoke(bArr);
        CanbusMsgSender.sendMsg(bArr);
        Intrinsics.checkNotNullExpressionValue(charset, "charset");
        SharePreUtil.setStringValue(context, _161_AMPLIFIER_DATA, new String(bArr, charset));
        uiMgr.mMsgMgr.canbusInfoChange(context, bArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00a0  */
    /* renamed from: lambda-59$lambda-12, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m218lambda59$lambda12(com.hzbhd.canbus.ui_set.SettingPageUiSet r18, com.hzbhd.canbus.car._161.UiMgr r19, android.content.Context r20, byte[] r21, java.nio.charset.Charset r22, int r23, int r24, final int r25) {
        /*
            Method dump skipped, instructions count: 1030
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._161.UiMgr.m218lambda59$lambda12(com.hzbhd.canbus.ui_set.SettingPageUiSet, com.hzbhd.canbus.car._161.UiMgr, android.content.Context, byte[], java.nio.charset.Charset, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /* renamed from: lambda-59$lambda-19, reason: not valid java name */
    public static final void m219lambda59$lambda19(SettingPageUiSet settingPageUiSet, UiMgr this$0, byte[] amplifierCommand, Context context, Charset charset, int i, int i2, final int i3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(amplifierCommand, "$amplifierCommand");
        Intrinsics.checkNotNullParameter(context, "$context");
        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        if (titleSrn != null) {
            switch (titleSrn.hashCode()) {
                case -2130367498:
                    if (titleSrn.equals("_94_blind_spot_monitoring")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) i3});
                        break;
                    }
                    break;
                case -2065696610:
                    if (titleSrn.equals("_118_setting_title_13")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 18, (byte) i3});
                        break;
                    }
                    break;
                case -2065696606:
                    if (titleSrn.equals("_118_setting_title_17")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 33, (byte) i3});
                        break;
                    }
                    break;
                case -1970344807:
                    if (titleSrn.equals("_161_speed_3_enable")) {
                        byte[] bArr = this$0.get0x88Command();
                        bArr[2] = (byte) DataHandleUtils.setOneBit(bArr[2], 3, i3);
                        CanbusMsgSender.sendMsg(bArr);
                        break;
                    }
                    break;
                case -1931498484:
                    if (titleSrn.equals("_161_Thursday")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -117, (byte) this$0.resolvedata(3, i3)});
                        break;
                    }
                    break;
                case -1635857494:
                    if (titleSrn.equals("_161_start_stop_disabled")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) i3});
                        break;
                    }
                    break;
                case -1322788373:
                    if (titleSrn.equals("_161_Saturday")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -117, (byte) this$0.resolvedata(1, i3)});
                        break;
                    }
                    break;
                case -1273166600:
                    if (titleSrn.equals("_143_setting_5")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 14, (byte) i3});
                        break;
                    }
                    break;
                case -1273166598:
                    if (titleSrn.equals("_143_setting_7")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 21, (byte) i3});
                        break;
                    }
                    break;
                case -1273166597:
                    if (titleSrn.equals("_143_setting_8")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 28, (byte) i3});
                        break;
                    }
                    break;
                case -1160998181:
                    if (titleSrn.equals("_161_speed_1_enable")) {
                        byte[] bArr2 = this$0.get0x88Command();
                        bArr2[2] = (byte) DataHandleUtils.setOneBit(bArr2[2], 5, i3);
                        CanbusMsgSender.sendMsg(bArr2);
                        break;
                    }
                    break;
                case -835843419:
                    if (titleSrn.equals("_161_decoder_voice")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 35, (byte) i3});
                        break;
                    }
                    break;
                case -823723268:
                    titleSrn.equals("_161_door_auto_lock");
                    break;
                case -813458981:
                    if (titleSrn.equals("_143_setting_20")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 26, (byte) i3});
                        break;
                    }
                    break;
                case -813458980:
                    if (titleSrn.equals("_143_setting_21")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 27, (byte) i3});
                        break;
                    }
                    break;
                case -585729983:
                    if (titleSrn.equals("parkingAssist")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 1, (byte) i3});
                        break;
                    }
                    break;
                case -578117088:
                    if (titleSrn.equals("_283_car_setting_light_4")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 3, (byte) i3});
                        break;
                    }
                    break;
                case -227534472:
                    if (titleSrn.equals("_161_speed_4_enable")) {
                        byte[] bArr3 = this$0.get0x88Command();
                        bArr3[2] = (byte) DataHandleUtils.setOneBit(bArr3[2], 2, i3);
                        CanbusMsgSender.sendMsg(bArr3);
                        break;
                    }
                    break;
                case -120530469:
                    if (titleSrn.equals("_161_Tuesday")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -117, (byte) this$0.resolvedata(5, i3)});
                        break;
                    }
                    break;
                case -43540035:
                    if (titleSrn.equals("_284_setting_item_2B")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 31, (byte) i3});
                        break;
                    }
                    break;
                case 66177732:
                    if (titleSrn.equals("_161_light_assist")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 34, (byte) i3});
                        break;
                    }
                    break;
                case 157539172:
                    if (titleSrn.equals("_161_Wednesday")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -117, (byte) this$0.resolvedata(4, i3)});
                        break;
                    }
                    break;
                case 581812154:
                    if (titleSrn.equals("_161_speed_2_enable")) {
                        byte[] bArr4 = this$0.get0x88Command();
                        bArr4[2] = (byte) DataHandleUtils.setOneBit(bArr4[2], 4, i3);
                        CanbusMsgSender.sendMsg(bArr4);
                        break;
                    }
                    break;
                case 851049255:
                    if (titleSrn.equals("_161_anti_collision")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 32, (byte) i3});
                        break;
                    }
                    break;
                case 1177900689:
                    if (titleSrn.equals("_161_disable_rear_mirror")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 25, (byte) i3});
                        break;
                    }
                    break;
                case 1229285653:
                    if (titleSrn.equals("_81_traction_control_system")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 22, (byte) i3});
                        break;
                    }
                    break;
                case 1255205905:
                    if (titleSrn.equals("_161_Friday")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -117, (byte) this$0.resolvedata(2, i3)});
                        break;
                    }
                    break;
                case 1285394219:
                    if (titleSrn.equals("_143_0xAD_setting5")) {
                        _init_$sendAmplifierCommand(amplifierCommand, context, charset, this$0, new Function1<byte[], Unit>() { // from class: com.hzbhd.canbus.car._161.UiMgr$3$2$7
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(byte[] bArr5) {
                                invoke2(bArr5);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(byte[] sendAmplifierCommand) {
                                Intrinsics.checkNotNullParameter(sendAmplifierCommand, "$this$sendAmplifierCommand");
                                sendAmplifierCommand[7] = (byte) DataHandleUtils.setOneBit(sendAmplifierCommand[7], 7, i3);
                            }
                        });
                        break;
                    }
                    break;
                case 1285394220:
                    if (titleSrn.equals("_143_0xAD_setting6")) {
                        _init_$sendAmplifierCommand(amplifierCommand, context, charset, this$0, new Function1<byte[], Unit>() { // from class: com.hzbhd.canbus.car._161.UiMgr$3$2$8
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(byte[] bArr5) {
                                invoke2(bArr5);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(byte[] sendAmplifierCommand) {
                                Intrinsics.checkNotNullParameter(sendAmplifierCommand, "$this$sendAmplifierCommand");
                                sendAmplifierCommand[7] = (byte) DataHandleUtils.setOneBit(sendAmplifierCommand[7], 6, i3);
                            }
                        });
                        break;
                    }
                    break;
                case 1452988354:
                    if (titleSrn.equals("_161_Monday")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -117, (byte) this$0.resolvedata(6, i3)});
                        break;
                    }
                    break;
                case 1508778175:
                    if (titleSrn.equals("_161_passenger_massage_switch")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 4, (byte) i3});
                        break;
                    }
                    break;
                case 1515275863:
                    if (titleSrn.equals("_161_speed_5_enable")) {
                        byte[] bArr5 = this$0.get0x88Command();
                        bArr5[2] = (byte) DataHandleUtils.setOneBit(bArr5[2], 1, i3);
                        CanbusMsgSender.sendMsg(bArr5);
                        break;
                    }
                    break;
                case 1541392785:
                    if (titleSrn.equals("_161_Preset")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -117, (byte) this$0.resolvedata(7, i3)});
                        break;
                    }
                    break;
                case 1547973850:
                    if (titleSrn.equals("_161_only_unlock_trunk")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 19, (byte) i3});
                        break;
                    }
                    break;
                case 1630304386:
                    if (titleSrn.equals("_161_Sunday")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -117, (byte) this$0.resolvedata(0, i3)});
                        break;
                    }
                    break;
                case 1703657495:
                    if (titleSrn.equals("_161_speed_remember")) {
                        byte[] bArr6 = this$0.get0x88Command();
                        bArr6[2] = (byte) DataHandleUtils.setIntFromByteWithBit(bArr6[2], i3, 6, 2);
                        CanbusMsgSender.sendMsg(bArr6);
                        break;
                    }
                    break;
                case 1827382413:
                    if (titleSrn.equals("_161_driver_massage_switch")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, (byte) i3});
                        break;
                    }
                    break;
                case 1965173886:
                    if (titleSrn.equals("_220_driving_assistance")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 17, (byte) i3});
                        break;
                    }
                    break;
                case 2007067540:
                    if (titleSrn.equals("_161_reversing_radar")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 6, (byte) i3});
                        break;
                    }
                    break;
                case 2026482754:
                    if (titleSrn.equals("_161_rear_wiper")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 2, (byte) i3});
                        break;
                    }
                    break;
                case 2042061586:
                    if (titleSrn.equals("_161_rearview_mirror_adaptive")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 30, (byte) i3});
                        break;
                    }
                    break;
                case 2060120402:
                    if (titleSrn.equals("_161_buzzer")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 39, (byte) i3});
                        break;
                    }
                    break;
                case 2105117362:
                    if (titleSrn.equals("_283_car_setting_pa_5")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 29, (byte) i3});
                        break;
                    }
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /* renamed from: lambda-59$lambda-50, reason: not valid java name */
    public static final void m220lambda59$lambda50(SettingPageUiSet settingPageUiSet, UiMgr this$0, byte[] amplifierCommand, Context context, Charset charset, int i, int i2, final int i3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(amplifierCommand, "$amplifierCommand");
        Intrinsics.checkNotNullParameter(context, "$context");
        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        if (titleSrn != null) {
            int iHashCode = titleSrn.hashCode();
            switch (iHashCode) {
                case -860854301:
                    if (titleSrn.equals("_324_centrik_speaker")) {
                        _init_$sendAmplifierCommand(amplifierCommand, context, charset, this$0, new Function1<byte[], Unit>() { // from class: com.hzbhd.canbus.car._161.UiMgr$3$3$31
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(byte[] bArr) {
                                invoke2(bArr);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(byte[] sendAmplifierCommand) {
                                Intrinsics.checkNotNullParameter(sendAmplifierCommand, "$this$sendAmplifierCommand");
                                sendAmplifierCommand[8] = (byte) DataHandleUtils.setIntFromByteWithBit(sendAmplifierCommand[8], i3 + 3, 4, 4);
                            }
                        });
                        break;
                    }
                    break;
                case -374590123:
                    if (titleSrn.equals("on_off_btn_txt_7")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 5, (byte) i3});
                        break;
                    }
                    break;
                case -208886217:
                    if (titleSrn.equals("light_info")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -115, (byte) i3});
                        break;
                    }
                    break;
                case 747713920:
                    if (titleSrn.equals("_55_0xa6_setting_1")) {
                        _init_$sendAmplifierCommand(amplifierCommand, context, charset, this$0, new Function1<byte[], Unit>() { // from class: com.hzbhd.canbus.car._161.UiMgr$3$3$32
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(byte[] bArr) {
                                invoke2(bArr);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(byte[] sendAmplifierCommand) {
                                Intrinsics.checkNotNullParameter(sendAmplifierCommand, "$this$sendAmplifierCommand");
                                sendAmplifierCommand[8] = (byte) DataHandleUtils.setIntFromByteWithBit(sendAmplifierCommand[8], i3 + 3, 0, 4);
                            }
                        });
                        break;
                    }
                    break;
                case 889438709:
                    if (titleSrn.equals("_161_sche_mile")) {
                        byte[] bArr = this$0.m0x82Command;
                        bArr[3] = (byte) ((i3 >> 8) & 255);
                        bArr[4] = (byte) (i3 & 255);
                        CanbusMsgSender.sendMsg(bArr);
                        this$0.mMsgMgr.updateSettingActivity("_161_sche_mile", Integer.valueOf(i3));
                        break;
                    }
                    break;
                default:
                    switch (iHashCode) {
                        case 216860881:
                            if (titleSrn.equals("_161_speed_cruise_1")) {
                                byte[] bArr2 = this$0.get0x89Command(1);
                                bArr2[3] = (byte) i3;
                                CanbusMsgSender.sendMsg(bArr2);
                                this$0.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._161.UiMgr$$ExternalSyntheticLambda7
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        UiMgr.m221lambda59$lambda50$lambda26(i3);
                                    }
                                }, 120L);
                                break;
                            }
                            break;
                        case 216860882:
                            if (titleSrn.equals("_161_speed_cruise_2")) {
                                byte[] bArr3 = this$0.get0x89Command(1);
                                bArr3[4] = (byte) i3;
                                CanbusMsgSender.sendMsg(bArr3);
                                this$0.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._161.UiMgr$$ExternalSyntheticLambda10
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        UiMgr.m222lambda59$lambda50$lambda28(i3);
                                    }
                                }, 120L);
                                break;
                            }
                            break;
                        case 216860883:
                            if (titleSrn.equals("_161_speed_cruise_3")) {
                                byte[] bArr4 = this$0.get0x89Command(1);
                                bArr4[5] = (byte) i3;
                                CanbusMsgSender.sendMsg(bArr4);
                                this$0.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._161.UiMgr$$ExternalSyntheticLambda12
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        UiMgr.m223lambda59$lambda50$lambda30(i3);
                                    }
                                }, 120L);
                                break;
                            }
                            break;
                        case 216860884:
                            if (titleSrn.equals("_161_speed_cruise_4")) {
                                byte[] bArr5 = this$0.get0x89Command(1);
                                bArr5[6] = (byte) i3;
                                CanbusMsgSender.sendMsg(bArr5);
                                this$0.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._161.UiMgr$$ExternalSyntheticLambda13
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        UiMgr.m224lambda59$lambda50$lambda32(i3);
                                    }
                                }, 120L);
                                break;
                            }
                            break;
                        case 216860885:
                            if (titleSrn.equals("_161_speed_cruise_5")) {
                                byte[] bArr6 = this$0.get0x89Command(1);
                                bArr6[7] = (byte) i3;
                                CanbusMsgSender.sendMsg(bArr6);
                                this$0.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._161.UiMgr$$ExternalSyntheticLambda14
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        UiMgr.m225lambda59$lambda50$lambda34(i3);
                                    }
                                }, 120L);
                                break;
                            }
                            break;
                        case 216860886:
                            if (titleSrn.equals("_161_speed_cruise_6")) {
                                byte[] bArr7 = this$0.get0x89Command(1);
                                bArr7[8] = (byte) i3;
                                CanbusMsgSender.sendMsg(bArr7);
                                this$0.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._161.UiMgr$$ExternalSyntheticLambda15
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        UiMgr.m226lambda59$lambda50$lambda36(i3);
                                    }
                                }, 120L);
                                break;
                            }
                            break;
                        default:
                            switch (iHashCode) {
                                case 525017571:
                                    if (titleSrn.equals("_161_speed_limit_1")) {
                                        byte[] bArr8 = this$0.get0x89Command(2);
                                        bArr8[3] = (byte) i3;
                                        CanbusMsgSender.sendMsg(bArr8);
                                        this$0.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._161.UiMgr$$ExternalSyntheticLambda16
                                            @Override // java.lang.Runnable
                                            public final void run() {
                                                UiMgr.m227lambda59$lambda50$lambda38(i3);
                                            }
                                        }, 120L);
                                        break;
                                    }
                                    break;
                                case 525017572:
                                    if (titleSrn.equals("_161_speed_limit_2")) {
                                        byte[] bArr9 = this$0.get0x89Command(2);
                                        bArr9[4] = (byte) i3;
                                        CanbusMsgSender.sendMsg(bArr9);
                                        this$0.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._161.UiMgr$$ExternalSyntheticLambda17
                                            @Override // java.lang.Runnable
                                            public final void run() {
                                                UiMgr.m228lambda59$lambda50$lambda40(i3);
                                            }
                                        }, 120L);
                                        break;
                                    }
                                    break;
                                case 525017573:
                                    if (titleSrn.equals("_161_speed_limit_3")) {
                                        byte[] bArr10 = this$0.get0x89Command(2);
                                        bArr10[5] = (byte) i3;
                                        CanbusMsgSender.sendMsg(bArr10);
                                        this$0.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._161.UiMgr$$ExternalSyntheticLambda18
                                            @Override // java.lang.Runnable
                                            public final void run() {
                                                UiMgr.m229lambda59$lambda50$lambda42(i3);
                                            }
                                        }, 120L);
                                        break;
                                    }
                                    break;
                                case 525017574:
                                    if (titleSrn.equals("_161_speed_limit_4")) {
                                        byte[] bArr11 = this$0.get0x89Command(2);
                                        bArr11[6] = (byte) i3;
                                        CanbusMsgSender.sendMsg(bArr11);
                                        this$0.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._161.UiMgr$$ExternalSyntheticLambda19
                                            @Override // java.lang.Runnable
                                            public final void run() {
                                                UiMgr.m230lambda59$lambda50$lambda44(i3);
                                            }
                                        }, 120L);
                                        break;
                                    }
                                    break;
                                case 525017575:
                                    if (titleSrn.equals("_161_speed_limit_5")) {
                                        byte[] bArr12 = this$0.get0x89Command(2);
                                        bArr12[7] = (byte) i3;
                                        CanbusMsgSender.sendMsg(bArr12);
                                        this$0.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._161.UiMgr$$ExternalSyntheticLambda8
                                            @Override // java.lang.Runnable
                                            public final void run() {
                                                UiMgr.m231lambda59$lambda50$lambda46(i3);
                                            }
                                        }, 120L);
                                        break;
                                    }
                                    break;
                                case 525017576:
                                    if (titleSrn.equals("_161_speed_limit_6")) {
                                        byte[] bArr13 = this$0.get0x89Command(2);
                                        bArr13[8] = (byte) i3;
                                        CanbusMsgSender.sendMsg(bArr13);
                                        this$0.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._161.UiMgr$$ExternalSyntheticLambda9
                                            @Override // java.lang.Runnable
                                            public final void run() {
                                                UiMgr.m232lambda59$lambda50$lambda48(i3);
                                            }
                                        }, 120L);
                                        break;
                                    }
                                    break;
                                default:
                                    switch (iHashCode) {
                                        case 832315913:
                                            if (titleSrn.equals("_161_speed_remember_1")) {
                                                byte[] bArr14 = this$0.get0x88Command();
                                                bArr14[3] = (byte) i3;
                                                CanbusMsgSender.sendMsg(bArr14);
                                                break;
                                            }
                                            break;
                                        case 832315914:
                                            if (titleSrn.equals("_161_speed_remember_2")) {
                                                byte[] bArr15 = this$0.get0x88Command();
                                                bArr15[4] = (byte) i3;
                                                CanbusMsgSender.sendMsg(bArr15);
                                                break;
                                            }
                                            break;
                                        case 832315915:
                                            if (titleSrn.equals("_161_speed_remember_3")) {
                                                byte[] bArr16 = this$0.get0x88Command();
                                                bArr16[5] = (byte) i3;
                                                CanbusMsgSender.sendMsg(bArr16);
                                                break;
                                            }
                                            break;
                                        case 832315916:
                                            if (titleSrn.equals("_161_speed_remember_4")) {
                                                byte[] bArr17 = this$0.get0x88Command();
                                                bArr17[6] = (byte) i3;
                                                CanbusMsgSender.sendMsg(bArr17);
                                                break;
                                            }
                                            break;
                                        case 832315917:
                                            if (titleSrn.equals("_161_speed_remember_5")) {
                                                byte[] bArr18 = this$0.get0x88Command();
                                                bArr18[7] = (byte) i3;
                                                CanbusMsgSender.sendMsg(bArr18);
                                                break;
                                            }
                                            break;
                                    }
                            }
                    }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-59$lambda-50$lambda-26, reason: not valid java name */
    public static final void m221lambda59$lambda50$lambda26(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -103, 65, (byte) i});
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-59$lambda-50$lambda-28, reason: not valid java name */
    public static final void m222lambda59$lambda50$lambda28(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -103, 66, (byte) i});
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-59$lambda-50$lambda-30, reason: not valid java name */
    public static final void m223lambda59$lambda50$lambda30(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -103, 67, (byte) i});
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-59$lambda-50$lambda-32, reason: not valid java name */
    public static final void m224lambda59$lambda50$lambda32(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -103, 68, (byte) i});
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-59$lambda-50$lambda-34, reason: not valid java name */
    public static final void m225lambda59$lambda50$lambda34(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -103, 69, (byte) i});
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-59$lambda-50$lambda-36, reason: not valid java name */
    public static final void m226lambda59$lambda50$lambda36(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -103, 70, (byte) i});
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-59$lambda-50$lambda-38, reason: not valid java name */
    public static final void m227lambda59$lambda50$lambda38(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -103, -127, (byte) i});
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-59$lambda-50$lambda-40, reason: not valid java name */
    public static final void m228lambda59$lambda50$lambda40(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -103, -126, (byte) i});
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-59$lambda-50$lambda-42, reason: not valid java name */
    public static final void m229lambda59$lambda50$lambda42(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -103, -125, (byte) i});
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-59$lambda-50$lambda-44, reason: not valid java name */
    public static final void m230lambda59$lambda50$lambda44(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -103, -124, (byte) i});
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-59$lambda-50$lambda-46, reason: not valid java name */
    public static final void m231lambda59$lambda50$lambda46(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -103, -123, (byte) i});
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-59$lambda-50$lambda-48, reason: not valid java name */
    public static final void m232lambda59$lambda50$lambda48(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -103, -122, (byte) i});
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /* renamed from: lambda-59$lambda-53, reason: not valid java name */
    public static final void m233lambda59$lambda53(SettingPageUiSet settingPageUiSet, UiMgr this$0, int i, int i2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        if (titleSrn != null) {
            switch (titleSrn.hashCode()) {
                case -1273166602:
                    if (titleSrn.equals("_143_setting_3")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 16, 1});
                        break;
                    }
                    break;
                case -809348667:
                    if (titleSrn.equals("_161_clear_page_1")) {
                        byte[] bArr = this$0.m0x82Command;
                        byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
                        Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(this, size)");
                        bArrCopyOf[2] = (byte) (bArrCopyOf[2] | 64);
                        CanbusMsgSender.sendMsg(bArrCopyOf);
                        break;
                    }
                    break;
                case -809348666:
                    if (titleSrn.equals("_161_clear_page_2")) {
                        byte[] bArr2 = this$0.m0x82Command;
                        byte[] bArrCopyOf2 = Arrays.copyOf(bArr2, bArr2.length);
                        Intrinsics.checkNotNullExpressionValue(bArrCopyOf2, "copyOf(this, size)");
                        bArrCopyOf2[2] = (byte) (bArrCopyOf2[2] | 32);
                        CanbusMsgSender.sendMsg(bArrCopyOf2);
                        break;
                    }
                    break;
                case 1315821039:
                    if (titleSrn.equals("_161_speed_cruise_reset")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -119, 96, 0, 0, 0, 0, 0, 0});
                        break;
                    }
                    break;
                case 1662326273:
                    if (titleSrn.equals("_161_speed_limit_reset")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -119, -96, 0, 0, 0, 0, 0, 0});
                        break;
                    }
                    break;
                case 2052524225:
                    if (titleSrn.equals("_220_reset")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -120, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 0, 0});
                        break;
                    }
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-59$lambda-54, reason: not valid java name */
    public static final String m234lambda59$lambda54(SettingPageUiSet settingPageUiSet, Context context, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(context, "$context");
        if (!Intrinsics.areEqual(settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn(), "on_off_btn_txt_7")) {
            return String.valueOf(i3);
        }
        if (i3 == 0) {
            return CommUtil.getStrByResId(context, "close");
        }
        return String.valueOf(i3 - 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-59$lambda-57$lambda-55, reason: not valid java name */
    public static final void m236lambda59$lambda57$lambda55(int i, int i2, int i3, int i4, int i5) {
        int i6 = (i4 * 60) + i5;
        CanbusMsgSender.sendMsg(new byte[]{22, -125, 1, (byte) (i6 / 256), (byte) (i6 % 256)});
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-59$lambda-57, reason: not valid java name */
    public static final void m235lambda59$lambda57(SettingPageUiSet settingPageUiSet, final UiMgr this$0, Context context, int i, int i2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(context, "$context");
        SetTimeView.TimeResultListener timeResultListener = new SetTimeView.TimeResultListener() { // from class: com.hzbhd.canbus.car._161.UiMgr$$ExternalSyntheticLambda20
            @Override // com.hzbhd.canbus.util.SetTimeView.TimeResultListener
            public final void result(int i3, int i4, int i5, int i6, int i7) {
                UiMgr.m236lambda59$lambda57$lambda55(i3, i4, i5, i6, i7);
            }
        };
        SetTimeView.TimeResultListener timeResultListener2 = new SetTimeView.TimeResultListener() { // from class: com.hzbhd.canbus.car._161.UiMgr$$ExternalSyntheticLambda21
            @Override // com.hzbhd.canbus.util.SetTimeView.TimeResultListener
            public final void result(int i3, int i4, int i5, int i6, int i7) {
                UiMgr.m237lambda59$lambda57$lambda56(this.f$0, i3, i4, i5, i6, i7);
            }
        };
        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        if (Intrinsics.areEqual(titleSrn, "_161_Delay_Charge")) {
            SetTimeView setTimeView = new SetTimeView();
            MsgMgr msgMgr = this$0.getMsgMgr(context);
            setTimeView.showDialog(msgMgr != null ? msgMgr.getCurrentActivity() : null, CommUtil.getStrByResId(context, "_161_Delay_Charge"), false, false, false, true, true, timeResultListener);
        } else if (Intrinsics.areEqual(titleSrn, "_161_Time")) {
            SetTimeView setTimeView2 = new SetTimeView();
            MsgMgr msgMgr2 = this$0.getMsgMgr(context);
            setTimeView2.showDialog(msgMgr2 != null ? msgMgr2.getCurrentActivity() : null, CommUtil.getStrByResId(context, "_161_Time"), false, false, false, true, true, timeResultListener2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-59$lambda-57$lambda-56, reason: not valid java name */
    public static final void m237lambda59$lambda57$lambda56(UiMgr this$0, int i, int i2, int i3, int i4, int i5) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i6 = (i4 * 60) + i5;
        CanbusMsgSender.sendMsg(new byte[]{22, -117, (byte) this$0.Data0, (byte) (i6 / 256), (byte) (i6 % 256)});
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-59$lambda-58, reason: not valid java name */
    public static final void m238lambda59$lambda58(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -113, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, -113, 34});
        CanbusMsgSender.sendMsg(new byte[]{22, -113, 59});
        CanbusMsgSender.sendMsg(new byte[]{22, -113, 61});
        CanbusMsgSender.sendMsg(new byte[]{22, -113, 63});
        CanbusMsgSender.sendMsg(new byte[]{22, -113, 65});
        CanbusMsgSender.sendMsg(new byte[]{22, -113, 80});
        CanbusMsgSender.sendMsg(new byte[]{22, -113, 81});
        CanbusMsgSender.sendMsg(new byte[]{22, -113, 82});
        CanbusMsgSender.sendMsg(new byte[]{22, -113, 83});
        CanbusMsgSender.sendMsg(new byte[]{22, -113, 39});
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /* renamed from: lambda-62$lambda-60, reason: not valid java name */
    public static final void m244lambda62$lambda60(ParkPageUiSet parkPageUiSet, int i) {
        String titleSrn = parkPageUiSet.getPanoramicBtnList().get(i).getTitleSrn();
        if (titleSrn != null) {
            switch (titleSrn.hashCode()) {
                case -2064392171:
                    if (titleSrn.equals("_161_standard_view")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -88, 2});
                        break;
                    }
                    break;
                case -1705162449:
                    if (titleSrn.equals("_250_exit")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -88, 0});
                        break;
                    }
                    break;
                case -1532616569:
                    if (titleSrn.equals("_189_front_view")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -88, 6});
                        break;
                    }
                    break;
                case -1041060661:
                    if (titleSrn.equals("_161_automatic_view")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -88, 5});
                        break;
                    }
                    break;
                case -890108588:
                    if (titleSrn.equals("_189_rear_view")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -88, 8});
                        break;
                    }
                    break;
                case -148718515:
                    if (titleSrn.equals("_161_180_view")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -88, 3});
                        break;
                    }
                    break;
                case 621588864:
                    if (titleSrn.equals("_161_enlarged_view")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -88, 4});
                        break;
                    }
                    break;
                case 1561894793:
                    if (titleSrn.equals("_161_360_view")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -88, 7});
                        break;
                    }
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-62$lambda-61, reason: not valid java name */
    public static final void m245lambda62$lambda61() {
        CanbusMsgSender.sendMsg(new byte[]{22, -113, 64});
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-65$lambda-64, reason: not valid java name */
    public static final void m246lambda65$lambda64(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -113, 51});
        CanbusMsgSender.sendMsg(new byte[]{22, -113, 52});
        CanbusMsgSender.sendMsg(new byte[]{22, -113, 53});
        CanbusMsgSender.sendMsg(new byte[]{22, -113, 59});
        CanbusMsgSender.sendMsg(new byte[]{22, -113, 61});
        CanbusMsgSender.sendMsg(new byte[]{22, -113, 63});
        CanbusMsgSender.sendMsg(new byte[]{22, -113, 65});
        CanbusMsgSender.sendMsg(new byte[]{22, -113, 80});
        CanbusMsgSender.sendMsg(new byte[]{22, -113, 81});
        CanbusMsgSender.sendMsg(new byte[]{22, -113, 82});
        CanbusMsgSender.sendMsg(new byte[]{22, -113, 83});
        CanbusMsgSender.sendMsg(new byte[]{22, -113, 39});
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final void sendAirCommand(String title) {
        int i = 2;
        switch (title.hashCode()) {
            case -1470045433:
                if (title.equals("front_defog")) {
                    sendAirCommand(17, getOpenOrClose(GeneralAirData.max_front));
                    GeneralAirData.max_front = !GeneralAirData.max_front;
                    break;
                }
                break;
            case -1423573049:
                if (title.equals(AirBtnAction.AC_MAX)) {
                    sendAirCommand(3, getOpenOrClose(GeneralAirData.ac_max));
                    break;
                }
                break;
            case -825767279:
                if (title.equals(AirBtnAction.AUTO_WIND_LV)) {
                    int i2 = GeneralAirData.auto_wind_lv;
                    if (i2 == 0) {
                        i = 1;
                    } else if (i2 != 1) {
                        i = 0;
                    }
                    sendAirCommand(9, i);
                    break;
                }
                break;
            case -631663038:
                if (title.equals("rear_defog")) {
                    sendAirCommandUpDown(18);
                    break;
                }
                break;
            case 3106:
                if (title.equals("ac")) {
                    sendAirCommand(2, getOpenOrClose(GeneralAirData.ac));
                    break;
                }
                break;
            case 96835:
                if (title.equals(AirBtnAction.AQS)) {
                    sendAirCommand(13, getOpenOrClose(GeneralAirData.aqs));
                    break;
                }
                break;
            case 3005871:
                if (title.equals("auto")) {
                    sendAirCommand(1, getOpenOrClose(GeneralAirData.auto));
                    break;
                }
                break;
            case 3094652:
                if (title.equals("dual")) {
                    sendAirCommand(11, getOpenOrClose(GeneralAirData.dual));
                    break;
                }
                break;
            case 3496356:
                if (title.equals(AirBtnAction.REAR)) {
                    sendAirCommand(15, getOpenOrClose(GeneralAirData.rear));
                    break;
                }
                break;
            case 106858757:
                if (title.equals("power")) {
                    sendAirCommandUpDown(12);
                    break;
                }
                break;
            case 341572893:
                if (title.equals(AirBtnAction.BLOW_WINDOW)) {
                    sendAirCommand(7, getOpenOrClose(GeneralAirData.front_left_blow_window));
                    break;
                }
                break;
            case 756225563:
                if (title.equals("in_out_cycle")) {
                    sendAirCommandUpDown(14);
                    break;
                }
                break;
            case 1006620906:
                if (title.equals(AirBtnAction.AUTO_WIND_MODE)) {
                    sendAirCommandUpDown(19);
                    break;
                }
                break;
            case 1434490075:
                if (title.equals(AirBtnAction.BLOW_FOOT)) {
                    sendAirCommand(8, getOpenOrClose(GeneralAirData.front_left_blow_foot));
                    break;
                }
                break;
            case 1434539597:
                if (title.equals(AirBtnAction.BLOW_HEAD)) {
                    sendAirCommand(6, getOpenOrClose(GeneralAirData.front_left_blow_head));
                    break;
                }
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendAirCommand(int command, int param) {
        CanbusMsgSender.sendMsg(new byte[]{22, -118, (byte) command, (byte) param});
    }

    private final void sendAirCommandUpDown(int command) {
        final byte[] bArr = {22, -118, (byte) command, 1};
        CanbusMsgSender.sendMsg(bArr);
        bArr[3] = 0;
        this.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._161.UiMgr$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                UiMgr.m247sendAirCommandUpDown$lambda67$lambda66(bArr);
            }
        }, 100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sendAirCommandUpDown$lambda-67$lambda-66, reason: not valid java name */
    public static final void m247sendAirCommandUpDown$lambda67$lambda66(byte[] this_run) {
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        CanbusMsgSender.sendMsg(this_run);
    }

    private final byte[] get0x88Command() {
        byte[] bArr = (byte[]) this.mMsgMgr.getM0x3BDatas().clone();
        bArr[0] = 22;
        bArr[1] = -120;
        bArr[2] = (byte) ((bArr[2] >> 1) & WorkQueueKt.MASK);
        return bArr;
    }

    private final byte[] get0x89Command(int item) {
        byte[] bArr = {22, -119, 0, 0, 0, 0, 0, 0, 0};
        bArr[2] = (byte) DataHandleUtils.setIntFromByteWithBit(16, item, 6, 2);
        System.arraycopy(this.mMsgMgr.getM0x3DDatas(), ((item - 1) * 6) + 2, bArr, 3, 6);
        Log.i(TAG, "getTest: " + DataHandleUtils.bytes2HexString(bArr, 9));
        return bArr;
    }

    private final void removeSettingItemWithDifferent(Context context) {
        if (this.mMsgMgr.getMDifferent() == 32) {
            removeMainPrjBtnByName(context, "air");
        }
        if (this.mMsgMgr.getMDifferent() != 16) {
            removeSettingRightItemByNameList(context, new String[]{"_284_setting_item_2B", "_161_anti_collision", "_161_decoder_voice"});
        }
        if (this.mMsgMgr.getMDifferent() != 19) {
            removeSettingRightItemByNameList(context, new String[]{"_118_setting_title_17", "_161_light_assist"});
        }
        if (!ArraysKt.contains(new Integer[]{7, 13, 16, 18, 19, 24, 25}, Integer.valueOf(this.mMsgMgr.getMDifferent()))) {
            removeSettingRightItemByNameList(context, new String[]{"_161_rear_wiper"});
        }
        if (!ArraysKt.contains(new Integer[]{8, 16}, Integer.valueOf(this.mMsgMgr.getMDifferent()))) {
            removeSettingRightItemByNameList(context, new String[]{"_220_driving_assistance"});
        }
        if (!ArraysKt.contains(new Integer[]{18, 19, 23, 24, 25}, Integer.valueOf(this.mMsgMgr.getMDifferent()))) {
            removeSettingRightItemByNameList(context, new String[]{"temperature_unit"});
        }
        if (this.mMsgMgr.getMDifferent() != 7) {
            removeSettingRightItemByNameList(context, new String[]{"_161_door_auto_lock"});
        }
        if (!ArraysKt.contains(new Integer[]{7, 9, 13, 20}, Integer.valueOf(this.mMsgMgr.getMDifferent()))) {
            removeSettingRightItemByNameList(context, new String[]{"parkingAssist"});
        }
        if (this.mMsgMgr.getMDifferent() != 7) {
            removeSettingRightItemByNameList(context, new String[]{"_161_central_control_door_lock"});
        }
        if (!ArraysKt.contains(new Integer[]{1, 9, 12, 13, 16, 17, 19, 23, 24}, Integer.valueOf(this.mMsgMgr.getMDifferent()))) {
            removeSettingRightItemByNameList(context, new String[]{"_283_car_setting_light_4"});
        }
        if (!ArraysKt.contains(new Integer[]{16, 17, 19, 23, 24, 25}, Integer.valueOf(this.mMsgMgr.getMDifferent()))) {
            removeSettingRightItemByNameList(context, new String[]{"fuel_unit"});
        }
        if (!ArraysKt.contains(new Integer[]{19, 24}, Integer.valueOf(this.mMsgMgr.getMDifferent()))) {
            removeSettingRightItemByNameList(context, new String[]{"_161_emergency_braking"});
        }
        if (this.mMsgMgr.getMDifferent() != 25) {
            removeSettingRightItemByNameList(context, new String[]{"_118_setting_title_13"});
        }
        if (!ArraysKt.contains(new Integer[]{17, 18, 19, 23, 24}, Integer.valueOf(this.mMsgMgr.getMDifferent()))) {
            removeSettingRightItemByNameList(context, new String[]{"on_off_btn_txt_7"});
        }
        if (!ArraysKt.contains(new Integer[]{18, 23, 24}, Integer.valueOf(this.mMsgMgr.getMDifferent()))) {
            removeSettingRightItemByNameList(context, new String[]{"_161_only_unlock_trunk"});
        }
        if (!ArraysKt.contains(new Integer[]{1, 12, 13, 18, 19, 23, 24}, Integer.valueOf(this.mMsgMgr.getMDifferent()))) {
            removeSettingRightItemByNameList(context, new String[]{"_161_reversing_radar"});
        }
        if (!ArraysKt.contains(new Integer[]{1, 12, 17, 18, 19, 23, 24}, Integer.valueOf(this.mMsgMgr.getMDifferent()))) {
            removeSettingRightItemByNameList(context, new String[]{"_143_0x76_d1_b01"});
        }
        if (!ArraysKt.contains(new Integer[]{17, 19, 23, 24}, Integer.valueOf(this.mMsgMgr.getMDifferent()))) {
            removeSettingRightItemByNameList(context, new String[]{"_143_0x76_d0_b45"});
        }
        if (!ArraysKt.contains(new Integer[]{17, 19}, Integer.valueOf(this.mMsgMgr.getMDifferent()))) {
            removeSettingRightItemByNameList(context, new String[]{"outlander_simple_car_set_17"});
        }
        if (!ArraysKt.contains(new Integer[]{19, 24}, Integer.valueOf(this.mMsgMgr.getMDifferent()))) {
            removeSettingRightItemByNameList(context, new String[]{"_161_disable_rear_mirror"});
        }
        if (!ArraysKt.contains(new Integer[]{1, 17, 23}, Integer.valueOf(this.mMsgMgr.getMDifferent()))) {
            removeSettingRightItemByNameList(context, new String[]{"_94_blind_spot_monitoring"});
        }
        if (this.mMsgMgr.getMDifferent() != 17) {
            removeSettingRightItemByNameList(context, new String[]{"_161_start_stop_disabled"});
        }
        if (this.mMsgMgr.getMDifferent() != 17) {
            removeSettingRightItemByNameList(context, new String[]{"_143_setting_5"});
        }
        if (!ArraysKt.contains(new Integer[]{16, 17, 18, 24}, Integer.valueOf(this.mMsgMgr.getMDifferent()))) {
            removeSettingRightItemByNameList(context, new String[]{"_161_door_opening_options"});
        }
        if (!ArraysKt.contains(new Integer[]{18, 19, 24, 25}, Integer.valueOf(this.mMsgMgr.getMDifferent()))) {
            removeSettingRightItemByNameList(context, new String[]{"_143_setting_7"});
        }
        if (!ArraysKt.contains(new Integer[]{18, 19, 24}, Integer.valueOf(this.mMsgMgr.getMDifferent()))) {
            removeSettingRightItemByNameList(context, new String[]{"_81_traction_control_system"});
        }
        if (!ArraysKt.contains(new Integer[]{4, 18, 19, 24}, Integer.valueOf(this.mMsgMgr.getMDifferent()))) {
            removeSettingRightItemByNameList(context, new String[]{"geely_theme_colors"});
        }
        if (this.mMsgMgr.getMDifferent() != 19) {
            removeSettingRightItemByNameList(context, new String[]{"_143_setting_8"});
        }
    }

    public final int getSettingLeftIndexes(Context context, String titleSrn) {
        Intrinsics.checkNotNullParameter(titleSrn, "titleSrn");
        List<SettingPageUiSet.ListBean> list = getPageUiSet(context).getSettingPageUiSet().getList();
        Iterator<SettingPageUiSet.ListBean> it = list.iterator();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (Intrinsics.areEqual(titleSrn, it.next().getTitleSrn())) {
                return i;
            }
        }
        return -1;
    }

    private final MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            MsgMgrInterface canMsgMgr = MsgMgrFactory.getCanMsgMgr(context);
            Intrinsics.checkNotNull(canMsgMgr, "null cannot be cast to non-null type com.hzbhd.canbus.car._161.MsgMgr");
            this.mMsgMgr = (MsgMgr) canMsgMgr;
        }
        return this.mMsgMgr;
    }

    private final int getDecimalFrom8Bit(Integer[] bit) {
        String str = "";
        for (int i = 7; i >= 0; i--) {
            str = str + bit[i] + "";
        }
        return Integer.parseInt(str, CharsKt.checkRadix(2));
    }

    public final int getData0() {
        return this.Data0;
    }

    public final void setData0(int i) {
        this.Data0 = i;
    }

    public final int resolvedata(int a, int value) {
        Integer[] numArr = new Integer[8];
        int i = 0;
        if (a == 7 && value == 0) {
            while (i < 8) {
                numArr[i] = 0;
                i++;
            }
        } else {
            while (i < 8) {
                numArr[i] = 0;
                i++;
            }
            numArr[a] = Integer.valueOf(value);
            numArr[7] = 1;
        }
        int decimalFrom8Bit = getDecimalFrom8Bit(numArr);
        this.Data0 = decimalFrom8Bit;
        return decimalFrom8Bit;
    }
}
