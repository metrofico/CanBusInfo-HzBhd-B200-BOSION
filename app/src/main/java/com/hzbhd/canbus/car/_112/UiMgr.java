package com.hzbhd.canbus.car._112;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.car._112.MsgMgr;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSetTextListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AirBtnAction;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.OriginalBtnAction;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UiMgr.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/hzbhd/canbus/car/_112/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mHandler", "Landroid/os/Handler;", "mMsgMgr", "Lcom/hzbhd/canbus/car/_112/MsgMgr;", "removeSettingItems", "", "Companion", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public final class UiMgr extends AbstractUiMgr {
    private static final String TAG = "_112_UiMgr";
    private final Handler mHandler;
    private final MsgMgr mMsgMgr;

    public UiMgr(final Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        MsgMgrInterface canMsgMgr = MsgMgrFactory.getCanMsgMgr(context);
        Intrinsics.checkNotNull(canMsgMgr, "null cannot be cast to non-null type com.hzbhd.canbus.car._112.MsgMgr");
        this.mMsgMgr = (MsgMgr) canMsgMgr;
        this.mHandler = new Handler(Looper.getMainLooper());
        final FrontArea frontArea = getAirUiSet(context).getFrontArea();
        frontArea.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._112.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m102lambda6$lambda5$lambda1(frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._112.UiMgr$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m103lambda6$lambda5$lambda2(frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._112.UiMgr$$ExternalSyntheticLambda6
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m104lambda6$lambda5$lambda3(frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._112.UiMgr$$ExternalSyntheticLambda7
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m105lambda6$lambda5$lambda4(frontArea, i);
            }
        }});
        frontArea.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._112.UiMgr$1$1$5
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                UiMgr.m106lambda6$sendAirCommand(4);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                UiMgr.m106lambda6$sendAirCommand(5);
            }
        }, null, new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._112.UiMgr$1$1$6
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                UiMgr.m106lambda6$sendAirCommand(20);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                UiMgr.m106lambda6$sendAirCommand(21);
            }
        }});
        frontArea.setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._112.UiMgr$1$1$7
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr.m106lambda6$sendAirCommand(7);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr.m106lambda6$sendAirCommand(6);
            }
        });
        frontArea.setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._112.UiMgr$1$1$8
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                UiMgr.m106lambda6$sendAirCommand(17);
            }
        }, new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._112.UiMgr$1$1$9
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                UiMgr.m106lambda6$sendAirCommand(18);
            }
        }, new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._112.UiMgr$1$1$10
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                UiMgr.m106lambda6$sendAirCommand(22);
            }
        }, new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._112.UiMgr$1$1$11
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                UiMgr.m106lambda6$sendAirCommand(23);
            }
        }});
        final SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._112.UiMgr$$ExternalSyntheticLambda8
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public final void onClickItem(int i, int i2, int i3) {
                UiMgr.m97lambda13$lambda7(settingUiSet, i, i2, i3);
            }
        });
        settingUiSet.setOnSettingItemSwitchListener(new OnSettingItemSwitchListener() { // from class: com.hzbhd.canbus.car._112.UiMgr$$ExternalSyntheticLambda9
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener
            public final void onSwitch(int i, int i2, int i3) {
                UiMgr.m98lambda13$lambda8(settingUiSet, this, i, i2, i3);
            }
        });
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._112.UiMgr$$ExternalSyntheticLambda10
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public final void onClickItem(int i, int i2, int i3) {
                UiMgr.m99lambda13$lambda9(settingUiSet, i, i2, i3);
            }
        });
        settingUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._112.UiMgr$$ExternalSyntheticLambda11
            @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
            public final void onConfirmClick(int i, int i2) {
                UiMgr.m94lambda13$lambda10(settingUiSet, i, i2);
            }
        });
        settingUiSet.setOnSettingItemSeekbarSetTextListener(new OnSettingItemSeekbarSetTextListener() { // from class: com.hzbhd.canbus.car._112.UiMgr$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSetTextListener
            public final String onSetText(int i, int i2, int i3) {
                return UiMgr.m95lambda13$lambda11(settingUiSet, context, i, i2, i3);
            }
        });
        settingUiSet.setOnSettingItemClickListener(new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._112.UiMgr$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
            public final void onClickItem(int i, int i2) {
                UiMgr.m96lambda13$lambda12(settingUiSet, context, i, i2);
            }
        });
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        amplifierPageUiSet.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._112.UiMgr$3$1

            /* compiled from: UiMgr.kt */
            @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[AmplifierActivity.AmplifierBand.values().length];
                    iArr[AmplifierActivity.AmplifierBand.VOLUME.ordinal()] = 1;
                    iArr[AmplifierActivity.AmplifierBand.BASS.ordinal()] = 2;
                    iArr[AmplifierActivity.AmplifierBand.TREBLE.ordinal()] = 3;
                    iArr[AmplifierActivity.AmplifierBand.MIDDLE.ordinal()] = 4;
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
            public void progress(AmplifierActivity.AmplifierBand amplifierBand, int progress) {
                Intrinsics.checkNotNullParameter(amplifierBand, "amplifierBand");
                int i = WhenMappings.$EnumSwitchMapping$0[amplifierBand.ordinal()];
                if (i == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 2, (byte) progress});
                    return;
                }
                if (i == 2) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 5, (byte) (progress + 1)});
                } else if (i == 3) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 6, (byte) (progress + 1)});
                } else {
                    if (i != 4) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 7, (byte) (progress + 1)});
                }
            }
        });
        amplifierPageUiSet.setOnAmplifierPositionListener(new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._112.UiMgr$3$2

            /* compiled from: UiMgr.kt */
            @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[AmplifierActivity.AmplifierPosition.values().length];
                    iArr[AmplifierActivity.AmplifierPosition.FRONT_REAR.ordinal()] = 1;
                    iArr[AmplifierActivity.AmplifierPosition.LEFT_RIGHT.ordinal()] = 2;
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
            public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int value) {
                Intrinsics.checkNotNullParameter(amplifierPosition, "amplifierPosition");
                int i = WhenMappings.$EnumSwitchMapping$0[amplifierPosition.ordinal()];
                if (i == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, (byte) (10 - value)});
                } else {
                    if (i != 2) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 4, (byte) (value + 10)});
                }
            }
        });
        final OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(context);
        originalCarDevicePageUiSet.setOnOriginalTopBtnClickListeners(new OnOriginalTopBtnClickListener() { // from class: com.hzbhd.canbus.car._112.UiMgr$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener
            public final void onClickTopBtnItem(int i) {
                UiMgr.m100lambda17$lambda15(originalCarDevicePageUiSet, i);
            }
        });
        originalCarDevicePageUiSet.setOnOriginalBottomBtnClickListeners(new OnOriginalBottomBtnClickListener() { // from class: com.hzbhd.canbus.car._112.UiMgr$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener
            public final void onClickBottomBtnItem(int i) {
                UiMgr.m101lambda17$lambda16(originalCarDevicePageUiSet, i);
            }
        });
        originalCarDevicePageUiSet.setOnOriginalSongItemClickListener(new OnOriginalSongItemClickListener() { // from class: com.hzbhd.canbus.car._112.UiMgr$4$3
            @Override // com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener
            public void onSongItemLongClick(int position) {
            }

            @Override // com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener
            public void onSongItemClick(int position) {
                CanbusMsgSender.sendMsg(new byte[]{22, -56, 16, (byte) (position + 1)});
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-6$sendAirCommand, reason: not valid java name */
    public static final void m106lambda6$sendAirCommand(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -126, 1, (byte) i});
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* renamed from: lambda-6$sendAirCommand-0, reason: not valid java name */
    private static final void m107lambda6$sendAirCommand0(String str) {
        switch (str.hashCode()) {
            case -1786872896:
                if (str.equals(AirBtnAction.STEERING_WHEEL_HEATING)) {
                    m106lambda6$sendAirCommand(24);
                    break;
                }
                break;
            case -1470045433:
                if (str.equals("front_defog")) {
                    m106lambda6$sendAirCommand(12);
                    break;
                }
                break;
            case -1423573049:
                if (str.equals(AirBtnAction.AC_MAX)) {
                    m106lambda6$sendAirCommand(15);
                    break;
                }
                break;
            case -631663038:
                if (str.equals("rear_defog")) {
                    m106lambda6$sendAirCommand(14);
                    break;
                }
                break;
            case 3106:
                if (str.equals("ac")) {
                    m106lambda6$sendAirCommand(1);
                    break;
                }
                break;
            case 3005871:
                if (str.equals("auto")) {
                    m106lambda6$sendAirCommand(2);
                    break;
                }
                break;
            case 3545755:
                if (str.equals("sync")) {
                    m106lambda6$sendAirCommand(13);
                    break;
                }
                break;
            case 106858757:
                if (str.equals("power")) {
                    m106lambda6$sendAirCommand(16);
                    break;
                }
                break;
            case 756225563:
                if (str.equals("in_out_cycle")) {
                    m106lambda6$sendAirCommand(3);
                    break;
                }
                break;
            case 1018451744:
                if (str.equals(AirBtnAction.BLOW_HEAD_FOOT)) {
                    m106lambda6$sendAirCommand(9);
                    break;
                }
                break;
            case 1434490075:
                if (str.equals(AirBtnAction.BLOW_FOOT)) {
                    m106lambda6$sendAirCommand(10);
                    break;
                }
                break;
            case 1434539597:
                if (str.equals(AirBtnAction.BLOW_HEAD)) {
                    m106lambda6$sendAirCommand(8);
                    break;
                }
                break;
            case 1568764496:
                if (str.equals(AirBtnAction.BLOW_WINDOW_FOOT)) {
                    m106lambda6$sendAirCommand(11);
                    break;
                }
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-6$lambda-5$lambda-1, reason: not valid java name */
    public static final void m102lambda6$lambda5$lambda1(FrontArea frontArea, int i) {
        String str = frontArea.getLineBtnAction()[0][i];
        Intrinsics.checkNotNullExpressionValue(str, "lineBtnAction[0][position]");
        m107lambda6$sendAirCommand0(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-6$lambda-5$lambda-2, reason: not valid java name */
    public static final void m103lambda6$lambda5$lambda2(FrontArea frontArea, int i) {
        String str = frontArea.getLineBtnAction()[1][i];
        Intrinsics.checkNotNullExpressionValue(str, "lineBtnAction[1][position]");
        m107lambda6$sendAirCommand0(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-6$lambda-5$lambda-3, reason: not valid java name */
    public static final void m104lambda6$lambda5$lambda3(FrontArea frontArea, int i) {
        String str = frontArea.getLineBtnAction()[2][i];
        Intrinsics.checkNotNullExpressionValue(str, "lineBtnAction[2][position]");
        m107lambda6$sendAirCommand0(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-6$lambda-5$lambda-4, reason: not valid java name */
    public static final void m105lambda6$lambda5$lambda4(FrontArea frontArea, int i) {
        String str = frontArea.getLineBtnAction()[3][i];
        Intrinsics.checkNotNullExpressionValue(str, "lineBtnAction[3][position]");
        m107lambda6$sendAirCommand0(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-13$lambda-7, reason: not valid java name */
    public static final void m97lambda13$lambda7(SettingPageUiSet settingPageUiSet, int i, int i2, int i3) {
        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        if (titleSrn != null) {
            int i4 = 0;
            switch (titleSrn.hashCode()) {
                case -2065696607:
                    if (titleSrn.equals("_118_setting_title_16")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 52, (byte) i3});
                        return;
                    }
                    return;
                case -2065696577:
                    if (titleSrn.equals("_118_setting_title_25")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -44, (byte) i3});
                        return;
                    }
                    return;
                case -2065696573:
                    if (titleSrn.equals("_118_setting_title_29")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -88, (byte) i3});
                        return;
                    }
                    return;
                case -2065696547:
                    if (!titleSrn.equals("_118_setting_title_34")) {
                        return;
                    }
                    break;
                case -2065696395:
                    if (titleSrn.equals("_118_setting_title_81")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 8, (byte) i3});
                        return;
                    }
                    return;
                case -1475383431:
                    if (titleSrn.equals("hiworld_jeep_123_0x62_data2_10")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 32, (byte) (i3 * 30)});
                        return;
                    }
                    return;
                case -1475383367:
                    if (titleSrn.equals("hiworld_jeep_123_0x62_data2_32")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 33, (byte) (i3 * 30)});
                        return;
                    }
                    return;
                case -1475383303:
                    if (titleSrn.equals("hiworld_jeep_123_0x62_data2_54")) {
                        byte[] bArr = new byte[4];
                        bArr[0] = 22;
                        bArr[1] = -58;
                        bArr[2] = 65;
                        if (i3 == 1) {
                            i4 = 3;
                        } else if (i3 == 2) {
                            i4 = 20;
                        } else if (i3 == 3) {
                            i4 = 40;
                        }
                        bArr[3] = (byte) i4;
                        CanbusMsgSender.sendMsg(bArr);
                        return;
                    }
                    return;
                case -1160841604:
                    if (titleSrn.equals("remote_door_unlock")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 60, (byte) i3});
                        return;
                    }
                    return;
                case -1082386052:
                    if (titleSrn.equals("hiworld_jeep_123_0x60_data1_65")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 55, (byte) i3});
                        return;
                    }
                    return;
                case -970933316:
                    if (titleSrn.equals("_112_headunit_power_off_mode")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 67, (byte) i3});
                        return;
                    }
                    return;
                case -910636385:
                    if (titleSrn.equals("_112_aux1_power")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 97, (byte) i3});
                        return;
                    }
                    return;
                case -556746442:
                    if (titleSrn.equals("hiworld_jeep_123_0xC1_data1")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, (byte) i3});
                        return;
                    }
                    return;
                case -556746441:
                    if (titleSrn.equals("hiworld_jeep_123_0xC1_data2")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 5, (byte) i3});
                        return;
                    }
                    return;
                case -556746440:
                    if (titleSrn.equals("hiworld_jeep_123_0xC1_data3")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, (byte) i3});
                        return;
                    }
                    return;
                case -392737078:
                    if (titleSrn.equals("hiworld_jeep_123_0xCA_0X01")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, (byte) i3});
                        return;
                    }
                    return;
                case -392737071:
                    if (titleSrn.equals("hiworld_jeep_123_0xCA_0X08")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 7, (byte) i3});
                        return;
                    }
                    return;
                case -58415930:
                    if (titleSrn.equals("seat_auto_heat")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -112, (byte) i3});
                        return;
                    }
                    return;
                case -23132704:
                    if (titleSrn.equals("_112_aux2_power")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 100, (byte) i3});
                        return;
                    }
                    return;
                case 102584022:
                    if (titleSrn.equals("language_setup")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, (byte) (i3 + 1)});
                        return;
                    }
                    return;
                case 864370977:
                    if (titleSrn.equals("_112_aux3_power")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 103, (byte) i3});
                        return;
                    }
                    return;
                case 1141851576:
                    if (titleSrn.equals("_112_offset_dist_alarm")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -98, (byte) i3});
                        return;
                    }
                    return;
                case 1170379802:
                    if (!titleSrn.equals("hiworld_jeep_123_0x43_data3_0")) {
                        return;
                    }
                    break;
                case 1170379803:
                    if (titleSrn.equals("hiworld_jeep_123_0x43_data3_1")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -85, (byte) i3});
                        return;
                    }
                    return;
                case 1180290621:
                    if (titleSrn.equals("_118_setting_title_9")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 64, (byte) (i3 * 30)});
                        return;
                    }
                    return;
                case 1751874658:
                    if (titleSrn.equals("_112_aux4_power")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 106, (byte) i3});
                        return;
                    }
                    return;
                case 1922035701:
                    if (titleSrn.equals("hiworld_jeep_123_0x43_data3_54")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -82, (byte) i3});
                        return;
                    }
                    return;
                case 1922035765:
                    if (titleSrn.equals("hiworld_jeep_123_0x43_data3_76")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -96, (byte) i3});
                        return;
                    }
                    return;
                case 1922065364:
                    if (titleSrn.equals("hiworld_jeep_123_0x43_data4_10")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, (byte) i3});
                        return;
                    }
                    return;
                case 1922095155:
                    if (titleSrn.equals("hiworld_jeep_123_0x43_data5_10")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, (byte) i3});
                        return;
                    }
                    return;
                case 1922095219:
                    if (titleSrn.equals("hiworld_jeep_123_0x43_data5_32")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -94, (byte) i3});
                        return;
                    }
                    return;
                case 1989254415:
                    if (titleSrn.equals("_176_car_setting_title_22")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -87, (byte) i3});
                        return;
                    }
                    return;
                case 2048963168:
                    if (titleSrn.equals("_112_aux1_type")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 96, (byte) i3});
                        return;
                    }
                    return;
                case 2066915788:
                    if (titleSrn.equals("_112_offset_dist_alarm_vol")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -97, (byte) i3});
                        return;
                    }
                    return;
                case 2077592319:
                    if (titleSrn.equals("_112_aux2_type")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 99, (byte) i3});
                        return;
                    }
                    return;
                case 2106221470:
                    if (titleSrn.equals("_112_aux3_type")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 102, (byte) i3});
                        return;
                    }
                    return;
                case 2134850621:
                    if (titleSrn.equals("_112_aux4_type")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 105, (byte) i3});
                        return;
                    }
                    return;
                default:
                    return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -86, (byte) i3});
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
    /* renamed from: lambda-13$lambda-8, reason: not valid java name */
    public static final void m98lambda13$lambda8(SettingPageUiSet settingPageUiSet, UiMgr this$0, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        if (titleSrn != null) {
            switch (titleSrn.hashCode()) {
                case -2065696605:
                    if (titleSrn.equals("_118_setting_title_18")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 56, (byte) i3});
                        break;
                    }
                    break;
                case -2065696604:
                    if (titleSrn.equals("_118_setting_title_19")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 66, (byte) i3});
                        break;
                    }
                    break;
                case -2065696578:
                    if (titleSrn.equals("_118_setting_title_24")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -48, (byte) i3});
                        break;
                    }
                    break;
                case -2065696576:
                    if (titleSrn.equals("_118_setting_title_26")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -47, (byte) i3});
                        break;
                    }
                    break;
                case -2065696575:
                    if (titleSrn.equals("_118_setting_title_27")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -46, (byte) i3});
                        break;
                    }
                    break;
                case -2065696574:
                    if (titleSrn.equals("_118_setting_title_28")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -45, (byte) i3});
                        break;
                    }
                    break;
                case -2065696551:
                    if (titleSrn.equals("_118_setting_title_30")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -89, (byte) i3});
                        break;
                    }
                    break;
                case -2065696544:
                    if (titleSrn.equals("_118_setting_title_37")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -64, (byte) i3});
                        break;
                    }
                    break;
                case -2065696543:
                    if (titleSrn.equals("_118_setting_title_38")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -63, (byte) i3});
                        break;
                    }
                    break;
                case -2065696453:
                    if (titleSrn.equals("_118_setting_title_65")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -99, (byte) i3});
                        break;
                    }
                    break;
                case -2065696423:
                    if (titleSrn.equals("_118_setting_title_74")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 50, (byte) i3});
                        break;
                    }
                    break;
                case -2065696359:
                    if (titleSrn.equals("_118_setting_title_96")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 10, (byte) i3});
                        break;
                    }
                    break;
                case -1802151965:
                    if (titleSrn.equals("_112_rear_mirror_dimmer")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 39, (byte) i3});
                        break;
                    }
                    break;
                case -1713301036:
                    if (titleSrn.equals("_213_car_set14_2_0")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 53, (byte) i3});
                        break;
                    }
                    break;
                case -1710160041:
                    if (titleSrn.equals("hiworld_jeep_123_0x62_data3_0")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 38, (byte) i3});
                        break;
                    }
                    break;
                case -1710160038:
                    if (titleSrn.equals("hiworld_jeep_123_0x62_data3_3")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 36, (byte) i3});
                        break;
                    }
                    break;
                case -1710160037:
                    if (titleSrn.equals("hiworld_jeep_123_0x62_data3_4")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 35, (byte) i3});
                        break;
                    }
                    break;
                case -1280342097:
                    if (titleSrn.equals("_112_rear_park_assist")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -16, (byte) i3});
                        break;
                    }
                    break;
                case -79129585:
                    if (titleSrn.equals("_112_adaptive_high_beam")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 44, (byte) i3});
                        break;
                    }
                    break;
                case 16407673:
                    if (titleSrn.equals("_112_horn_remote_start")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 59, (byte) i3});
                        break;
                    }
                    break;
                case 97333442:
                    if (titleSrn.equals("amplifier_switch")) {
                        this$0.mMsgMgr.setAmplifierSwitch(i3);
                        break;
                    }
                    break;
                case 190266838:
                    if (titleSrn.equals("_112_auto_anti_glare")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 40, (byte) i3});
                        break;
                    }
                    break;
                case 279525107:
                    if (titleSrn.equals("_112_aux4_recalled")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 107, (byte) i3});
                        break;
                    }
                    break;
                case 476038612:
                    if (titleSrn.equals("_112_aux3_recalled")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 104, (byte) i3});
                        break;
                    }
                    break;
                case 672552117:
                    if (titleSrn.equals("_112_aux2_recalled")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 101, (byte) i3});
                        break;
                    }
                    break;
                case 869065622:
                    if (titleSrn.equals("_112_aux1_recalled")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 98, (byte) i3});
                        break;
                    }
                    break;
                case 1170380765:
                    if (titleSrn.equals("hiworld_jeep_123_0x43_data4_2")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -91, (byte) i3});
                        break;
                    }
                    break;
                case 1170380766:
                    if (titleSrn.equals("hiworld_jeep_123_0x43_data4_3")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -92, (byte) i3});
                        break;
                    }
                    break;
                case 1170380768:
                    if (titleSrn.equals("hiworld_jeep_123_0x43_data4_5")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -81, (byte) i3});
                        break;
                    }
                    break;
                case 1170380769:
                    if (titleSrn.equals("hiworld_jeep_123_0x43_data4_6")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -90, (byte) i3});
                        break;
                    }
                    break;
                case 1170381728:
                    if (titleSrn.equals("hiworld_jeep_123_0x43_data5_4")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte) i3});
                        break;
                    }
                    break;
                case 1180290616:
                    if (titleSrn.equals("_118_setting_title_4")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -83, (byte) i3});
                        break;
                    }
                    break;
                case 1299617032:
                    if (titleSrn.equals("_250_welcome_light")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 42, (byte) i3});
                        break;
                    }
                    break;
                case 1319326522:
                    if (titleSrn.equals("_112_auto_park_assist")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -15, (byte) i3});
                        break;
                    }
                    break;
                case 1533915521:
                    if (titleSrn.equals("_112_auto_suspension")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -43, (byte) i3});
                        break;
                    }
                    break;
                case 1614758555:
                    if (titleSrn.equals("_112_cornering_lights")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 37, (byte) i3});
                        break;
                    }
                    break;
                case 1904746963:
                    if (titleSrn.equals("hiworld_jeep_123_0x60_data1_0")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 48, (byte) i3});
                        break;
                    }
                    break;
                case 1904746964:
                    if (titleSrn.equals("hiworld_jeep_123_0x60_data1_1")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 49, (byte) i3});
                        break;
                    }
                    break;
                case 1904746966:
                    if (titleSrn.equals("hiworld_jeep_123_0x60_data1_3")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 54, (byte) i3});
                        break;
                    }
                    break;
                case 1904746967:
                    if (titleSrn.equals("hiworld_jeep_123_0x60_data1_4")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 58, (byte) i3});
                        break;
                    }
                    break;
                case 2019759060:
                    if (titleSrn.equals("_276_setting_0")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, (byte) i3});
                        break;
                    }
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-13$lambda-9, reason: not valid java name */
    public static final void m99lambda13$lambda9(SettingPageUiSet settingPageUiSet, int i, int i2, int i3) {
        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        if (titleSrn != null) {
            int iHashCode = titleSrn.hashCode();
            if (iHashCode == -1475383239) {
                if (titleSrn.equals("hiworld_jeep_123_0x62_data2_76")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 41, (byte) i3});
                }
            } else if (iHashCode == -903809344) {
                if (titleSrn.equals("speed_linkage_volume_adjustment")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 9, (byte) i3});
                }
            } else if (iHashCode == 1508683421 && titleSrn.equals("hiworld_jeep_123_0x62_data3_765")) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, 43, (byte) i3});
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
    /* renamed from: lambda-13$lambda-10, reason: not valid java name */
    public static final void m94lambda13$lambda10(SettingPageUiSet settingPageUiSet, int i, int i2) {
        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        if (titleSrn != null) {
            switch (titleSrn.hashCode()) {
                case -877393279:
                    if (titleSrn.equals("vm_golf7_vehicle_setup_factory_setup")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 8, -86});
                        break;
                    }
                    break;
                case -702865629:
                    if (titleSrn.equals("_112_reset_trip_a")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 10, 1});
                        break;
                    }
                    break;
                case -702865628:
                    if (titleSrn.equals("_112_reset_trip_b")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 11, 1});
                        break;
                    }
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-13$lambda-11, reason: not valid java name */
    public static final String m95lambda13$lambda11(SettingPageUiSet settingPageUiSet, Context context, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(context, "$context");
        if (!Intrinsics.areEqual(settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn(), "speed_linkage_volume_adjustment")) {
            return String.valueOf(i3);
        }
        if (i3 == 0) {
            return CommUtil.getStrByResId(context, "close");
        }
        return String.valueOf(i3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-13$lambda-12, reason: not valid java name */
    public static final void m96lambda13$lambda12(SettingPageUiSet settingPageUiSet, Context context, int i, int i2) {
        Intrinsics.checkNotNullParameter(context, "$context");
        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        if (titleSrn != null) {
            int iHashCode = titleSrn.hashCode();
            if (iHashCode == -625317761) {
                if (titleSrn.equals("high_config")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -54, 1});
                    MsgMgrInterface canMsgMgr = MsgMgrFactory.getCanMsgMgr(context);
                    Intrinsics.checkNotNull(canMsgMgr, "null cannot be cast to non-null type com.hzbhd.canbus.car._112.MsgMgr");
                    ((MsgMgr) canMsgMgr).myToast("Select car2 success!");
                    return;
                }
                return;
            }
            if (iHashCode == -217893235) {
                if (titleSrn.equals("low_config")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -54, 0});
                    MsgMgrInterface canMsgMgr2 = MsgMgrFactory.getCanMsgMgr(context);
                    Intrinsics.checkNotNull(canMsgMgr2, "null cannot be cast to non-null type com.hzbhd.canbus.car._112.MsgMgr");
                    ((MsgMgr) canMsgMgr2).myToast("Select car1 success!");
                    return;
                }
                return;
            }
            if (iHashCode == 53216746 && titleSrn.equals("_112_xCA")) {
                CanbusMsgSender.sendMsg(new byte[]{22, -54, 2});
                MsgMgrInterface canMsgMgr3 = MsgMgrFactory.getCanMsgMgr(context);
                Intrinsics.checkNotNull(canMsgMgr3, "null cannot be cast to non-null type com.hzbhd.canbus.car._112.MsgMgr");
                ((MsgMgr) canMsgMgr3).myToast("Select car3 success!");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-17$lambda-15, reason: not valid java name */
    public static final void m100lambda17$lambda15(OriginalCarDevicePageUiSet originalCarDevicePageUiSet, int i) {
        String str = originalCarDevicePageUiSet.getRowTopBtnAction()[i];
        if (Intrinsics.areEqual(str, OriginalBtnAction.RDM)) {
            byte[] bArr = new byte[4];
            bArr[0] = 22;
            bArr[1] = -56;
            bArr[2] = (byte) (GeneralOriginalCarDeviceData.rdm ? 10 : 9);
            bArr[3] = 0;
            CanbusMsgSender.sendMsg(bArr);
            return;
        }
        if (Intrinsics.areEqual(str, OriginalBtnAction.RPT)) {
            byte[] bArr2 = new byte[4];
            bArr2[0] = 22;
            bArr2[1] = -56;
            bArr2[2] = (byte) (GeneralOriginalCarDeviceData.rpt ? 15 : 14);
            bArr2[3] = 0;
            CanbusMsgSender.sendMsg(bArr2);
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
    /* renamed from: lambda-17$lambda-16, reason: not valid java name */
    public static final void m101lambda17$lambda16(OriginalCarDevicePageUiSet originalCarDevicePageUiSet, int i) {
        String str = originalCarDevicePageUiSet.getRowBottomBtnAction()[i];
        if (str != null) {
            switch (str.hashCode()) {
                case 3739:
                    if (str.equals("up")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -56, 6, 0});
                        break;
                    }
                    break;
                case 3089570:
                    if (str.equals("down")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -56, 5, 0});
                        break;
                    }
                    break;
                case 3317767:
                    if (str.equals("left")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -56, 3, 0});
                        break;
                    }
                    break;
                case 3443508:
                    if (str.equals(OriginalBtnAction.PLAY)) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -56, 2, 0});
                        break;
                    }
                    break;
                case 106440182:
                    if (str.equals(OriginalBtnAction.PAUSE)) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -56, 1, 0});
                        break;
                    }
                    break;
                case 108511772:
                    if (str.equals("right")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -56, 4, 0});
                        break;
                    }
                    break;
            }
        }
    }

    private final void removeSettingItems(Context context) {
        int currentCarId = getCurrentCarId();
        Map mapMapOf = MapsKt.mapOf(TuplesKt.to("language_setup", new int[]{1, 2, 3, 4, 5, 6, 7}), TuplesKt.to("hiworld_jeep_123_0xC1_data3", new int[]{1, 2, 3, 4, 6, 7}), TuplesKt.to("hiworld_jeep_123_0xCA_0X01", new int[]{1, 2, 3, 4, 5, 6, 7}), TuplesKt.to("hiworld_jeep_123_0xC1_data1", new int[]{1, 2, 3, 4, 5, 6, 7}), TuplesKt.to("hiworld_jeep_123_0xC1_data2", new int[]{1, 2, 3, 4, 5, 6, 7}), TuplesKt.to("_276_setting_0", new int[]{5}), TuplesKt.to("hiworld_jeep_123_0xCA_0X08", new int[]{2, 4, 6, 7}), TuplesKt.to("_118_setting_title_81", new int[]{6}), TuplesKt.to("_112_reset_trip_a", new int[]{3, 5}), TuplesKt.to("_112_reset_trip_b", new int[]{3, 5}), TuplesKt.to("hiworld_jeep_123_0x62_data2_10", new int[]{1, 2, 3, 4, 6, 7}), TuplesKt.to("hiworld_jeep_123_0x62_data2_32", new int[]{1, 6}), TuplesKt.to("hiworld_jeep_123_0x62_data3_4", new int[]{1, 2, 3, 4, 6, 7}), TuplesKt.to("hiworld_jeep_123_0x62_data3_3", new int[]{1, 2, 4, 5, 6, 7}), TuplesKt.to("_112_cornering_lights", new int[]{1, 2, 3, 4, 7}), TuplesKt.to("hiworld_jeep_123_0x62_data3_0", new int[]{1, 6}), TuplesKt.to("_112_rear_mirror_dimmer", new int[]{1, 6}), TuplesKt.to("_112_auto_anti_glare", new int[]{1}), TuplesKt.to("hiworld_jeep_123_0x62_data2_76", new int[]{2, 3, 4}), TuplesKt.to("_250_welcome_light", new int[]{2, 3, 4}), TuplesKt.to("hiworld_jeep_123_0x62_data3_765", new int[]{4}), TuplesKt.to("_112_adaptive_high_beam", new int[]{6}), TuplesKt.to("hiworld_jeep_123_0x60_data1_0", new int[]{1, 2, 3, 4, 6, 7}), TuplesKt.to("hiworld_jeep_123_0x60_data1_1", new int[]{1, 2, 4, 6}), TuplesKt.to("_118_setting_title_74", new int[]{1, 2, 3, 4, 6, 7}), TuplesKt.to("_118_setting_title_16", new int[]{1, 4, 6}), TuplesKt.to("_213_car_set14_2_0", new int[]{5}), TuplesKt.to("hiworld_jeep_123_0x60_data1_3", new int[]{1, 2, 4, 6, 7}), TuplesKt.to("hiworld_jeep_123_0x60_data1_65", new int[]{1, 2, 4, 6, 7}), TuplesKt.to("_118_setting_title_18", new int[]{1, 6}), TuplesKt.to("hiworld_jeep_123_0x60_data1_4", new int[]{1}), TuplesKt.to("_112_horn_remote_start", new int[]{2, 4}), TuplesKt.to("remote_door_unlock", new int[]{2, 4, 7}), TuplesKt.to("_118_setting_title_9", new int[]{1, 2, 3, 6, 7}), TuplesKt.to("hiworld_jeep_123_0x62_data2_54", new int[]{1, 6}), TuplesKt.to("_118_setting_title_19", new int[]{1, 6}), TuplesKt.to("_112_headunit_power_off_mode", new int[]{6}), TuplesKt.to("seat_auto_heat", new int[]{1, 4, 6}), TuplesKt.to("_118_setting_title_65", new int[]{6}), TuplesKt.to("_112_offset_dist_alarm", new int[]{4}), TuplesKt.to("_112_offset_dist_alarm_vol", new int[]{4}), TuplesKt.to("hiworld_jeep_123_0x43_data3_76", new int[]{1, 3, 4, 6, 7}), TuplesKt.to("hiworld_jeep_123_0x43_data5_10", new int[]{1, 6}), TuplesKt.to("hiworld_jeep_123_0x43_data5_32", new int[]{1, 3, 4, 6, 7}), TuplesKt.to("hiworld_jeep_123_0x43_data5_4", new int[]{1, 6}), TuplesKt.to("hiworld_jeep_123_0x43_data4_3", new int[]{1, 2, 3, 4, 6, 7}), TuplesKt.to("hiworld_jeep_123_0x43_data4_2", new int[]{1}), TuplesKt.to("hiworld_jeep_123_0x43_data4_6", new int[]{1, 6}), TuplesKt.to("_118_setting_title_30", new int[]{6}), TuplesKt.to("_118_setting_title_29", new int[]{6}), TuplesKt.to("_176_car_setting_title_22", new int[]{1, 4}), TuplesKt.to("hiworld_jeep_123_0x43_data3_0", new int[]{1, 3, 4}), TuplesKt.to("_118_setting_title_34", new int[]{1, 3, 4}), TuplesKt.to("hiworld_jeep_123_0x43_data3_1", new int[]{1, 3, 4}), TuplesKt.to("hiworld_jeep_123_0x43_data4_10", new int[]{1, 4}), TuplesKt.to("_118_setting_title_4", new int[]{1, 6}), TuplesKt.to("hiworld_jeep_123_0x43_data3_54", new int[]{1, 4}), TuplesKt.to("hiworld_jeep_123_0x43_data4_5", new int[]{1, 2, 3, 4, 6}), TuplesKt.to("_112_rear_park_assist", new int[]{1}), TuplesKt.to("_112_auto_park_assist", new int[]{1}), TuplesKt.to("_118_setting_title_37", new int[]{1, 2, 4}), TuplesKt.to("_118_setting_title_38", new int[]{1, 2, 4, 7}), TuplesKt.to("_118_setting_title_24", new int[]{1}), TuplesKt.to("_118_setting_title_26", new int[]{1, 6}), TuplesKt.to("_118_setting_title_27", new int[]{1, 6}), TuplesKt.to("_118_setting_title_28", new int[]{1, 6}), TuplesKt.to("_118_setting_title_25", new int[]{1, 6}), TuplesKt.to("_112_auto_suspension", new int[]{6}));
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : mapMapOf.entrySet()) {
            if (!ArraysKt.contains((int[]) entry.getValue(), currentCarId % 10)) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        Set setKeySet = linkedHashMap.keySet();
        if (setKeySet.size() > 0) {
            Object[] array = setKeySet.toArray(new String[0]);
            Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            removeSettingRightItemByNameList(context, (String[]) array);
        }
        Unit unit = Unit.INSTANCE;
        String[] strArr = new String[1];
        strArr[0] = ArraysKt.contains(new int[]{1, 3, 4}, currentCarId) ? "_118_setting_title_34" : "hiworld_jeep_123_0x43_data3_0";
        removeSettingRightItemByNameList(context, strArr);
        if (currentCarId != MsgMgr.VehicleType.JEEP_GRAND_CHEROKEE_2014.getValue()) {
            removeSettingLeftItemByNameList(context, new String[]{"_112_aux_switches"});
        }
        boolean boolValue = SharePreUtil.getBoolValue(context, MsgMgr.SHARE_112_IS_HAVE_AMPLIFIER, true);
        Log.i(TAG, "removeSettingItems: SHARE_112_IS_HAVE_AMPLIFIER " + boolValue);
        Unit unit2 = Unit.INSTANCE;
        if (boolValue) {
            return;
        }
        Log.i(TAG, "removeSettingItems: remove");
        removeMainPrjBtnByName(context, MainAction.AMPLIFIER);
        removeSettingLeftItemByNameList(context, new String[]{"amplifier_setting"});
    }
}
