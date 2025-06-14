package com.hzbhd.canbus.car._324;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.bean.RearArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.car._324.MsgMgr;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDeviceBackPressedListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDevicePageStatusListener;
import com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AirBtnAction;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalBtnAction;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.PanelKeyPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.constant.share.lcd.LcdInfoShare;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import nfore.android.bt.res.NfDef;

/* compiled from: UiMgr.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/hzbhd/canbus/car/_324/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mHandler", "Landroid/os/Handler;", "mMsgMgr", "Lcom/hzbhd/canbus/car/_324/MsgMgr;", "sendAirCommand", "", "dataType", "", "command", LcdInfoShare.MediaInfo.title, "", "Companion", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class UiMgr extends AbstractUiMgr {
    private static final int DATATYPE_FRONTAIR = 113;
    private static final int DATATYPE_REARAIR = 114;
    private static final String TAG = "_324_UiMgr";
    private final Handler mHandler;
    private final MsgMgr mMsgMgr;

    public UiMgr(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        MsgMgrInterface canMsgMgr = MsgMgrFactory.getCanMsgMgr(context);
        Intrinsics.checkNotNull(canMsgMgr, "null cannot be cast to non-null type com.hzbhd.canbus.car._324.MsgMgr");
        this.mMsgMgr = (MsgMgr) canMsgMgr;
        this.mHandler = new Handler(Looper.getMainLooper());
        AirPageUiSet airUiSet = getAirUiSet(context);
        final FrontArea frontArea = airUiSet.getFrontArea();
        frontArea.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._324.UiMgr$$ExternalSyntheticLambda8
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m642lambda9$lambda4$lambda0(this.f$0, frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._324.UiMgr$$ExternalSyntheticLambda12
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m643lambda9$lambda4$lambda1(this.f$0, frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._324.UiMgr$$ExternalSyntheticLambda13
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m644lambda9$lambda4$lambda2(this.f$0, frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._324.UiMgr$$ExternalSyntheticLambda14
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m645lambda9$lambda4$lambda3(this.f$0, frontArea, i);
            }
        }});
        frontArea.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._324.UiMgr$1$1$5
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                this.this$0.sendAirCommand(113, 12);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                this.this$0.sendAirCommand(113, 13);
            }
        }, null, new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._324.UiMgr$1$1$6
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                this.this$0.sendAirCommand(113, 14);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                this.this$0.sendAirCommand(113, 15);
            }
        }});
        frontArea.setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._324.UiMgr$1$1$7
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                this.this$0.sendAirCommand(113, 7);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                this.this$0.sendAirCommand(113, 6);
            }
        });
        frontArea.setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._324.UiMgr$1$1$8
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                this.this$0.sendAirCommand(113, 36);
            }
        }, new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._324.UiMgr$1$1$9
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                this.this$0.sendAirCommand(113, 37);
            }
        }, null, null});
        final RearArea rearArea = airUiSet.getRearArea();
        rearArea.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._324.UiMgr$$ExternalSyntheticLambda15
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m646lambda9$lambda8$lambda5(this.f$0, rearArea, i);
            }
        }, null, null, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._324.UiMgr$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m647lambda9$lambda8$lambda6(this.f$0, rearArea, i);
            }
        }});
        rearArea.setTempSetViewOnUpDownClickListeners(new UiMgr$1$2$3[]{null, new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._324.UiMgr$1$2$3
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                this.this$0.sendAirCommand(114, 67);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                this.this$0.sendAirCommand(114, 68);
            }
        }, null});
        rearArea.setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._324.UiMgr$1$2$5
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                this.this$0.sendAirCommand(114, 70);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                this.this$0.sendAirCommand(114, 69);
            }
        });
        rearArea.setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._324.UiMgr$1$2$6
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                this.this$0.sendAirCommand(114, 80);
            }
        }, new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._324.UiMgr$1$2$7
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                this.this$0.sendAirCommand(114, 81);
            }
        }, null, null});
        final SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._324.UiMgr$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public final void onClickItem(int i, int i2, int i3) {
                UiMgr.m631lambda13$lambda10(settingUiSet, i, i2, i3);
            }
        });
        settingUiSet.setOnSettingItemSwitchListener(new OnSettingItemSwitchListener() { // from class: com.hzbhd.canbus.car._324.UiMgr$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener
            public final void onSwitch(int i, int i2, int i3) {
                UiMgr.m632lambda13$lambda11(settingUiSet, i, i2, i3);
            }
        });
        settingUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._324.UiMgr$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
            public final void onConfirmClick(int i, int i2) {
                UiMgr.m633lambda13$lambda12(settingUiSet, i, i2);
            }
        });
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        amplifierPageUiSet.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._324.UiMgr$3$1

            /* compiled from: UiMgr.kt */
            @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[AmplifierActivity.AmplifierBand.values().length];
                    iArr[AmplifierActivity.AmplifierBand.BASS.ordinal()] = 1;
                    iArr[AmplifierActivity.AmplifierBand.TREBLE.ordinal()] = 2;
                    iArr[AmplifierActivity.AmplifierBand.MIDDLE.ordinal()] = 3;
                    iArr[AmplifierActivity.AmplifierBand.VOLUME.ordinal()] = 4;
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
            public void progress(AmplifierActivity.AmplifierBand amplifierBand, int progress) {
                Intrinsics.checkNotNullParameter(amplifierBand, "amplifierBand");
                int i = WhenMappings.$EnumSwitchMapping$0[amplifierBand.ordinal()];
                if (i == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 4, (byte) (progress + 2)});
                    return;
                }
                if (i == 2) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 5, (byte) (progress + 2)});
                } else if (i == 3) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 6, (byte) (progress + 2)});
                } else {
                    if (i != 4) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 7, (byte) progress});
                }
            }
        });
        amplifierPageUiSet.setOnAmplifierPositionListener(new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._324.UiMgr$3$2

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
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, (byte) (7 - value)});
                } else {
                    if (i != 2) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 2, (byte) (value + 7)});
                }
            }
        });
        final OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(context);
        originalCarDevicePageUiSet.setOnOriginalCarDevicePageStatusListener(new OnOriginalCarDevicePageStatusListener() { // from class: com.hzbhd.canbus.car._324.UiMgr$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.interfaces.OnOriginalCarDevicePageStatusListener
            public final void onStatusChange(int i) {
                UiMgr.m634lambda21$lambda16(this.f$0, i);
            }
        });
        originalCarDevicePageUiSet.setOnOriginalCarDeviceBackPressedListener(new OnOriginalCarDeviceBackPressedListener() { // from class: com.hzbhd.canbus.car._324.UiMgr$$ExternalSyntheticLambda9
            @Override // com.hzbhd.canbus.interfaces.OnOriginalCarDeviceBackPressedListener
            public final void onBackPressed() {
                UiMgr.m636lambda21$lambda17();
            }
        });
        originalCarDevicePageUiSet.setOnOriginalTopBtnClickListeners(new OnOriginalTopBtnClickListener() { // from class: com.hzbhd.canbus.car._324.UiMgr$$ExternalSyntheticLambda10
            @Override // com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener
            public final void onClickTopBtnItem(int i) {
                UiMgr.m637lambda21$lambda18(originalCarDevicePageUiSet, i);
            }
        });
        final UiMgr$4$fastParams$1 uiMgr$4$fastParams$1 = new UiMgr$4$fastParams$1();
        originalCarDevicePageUiSet.setOnOriginalBottomBtnClickListeners(new OnOriginalBottomBtnClickListener() { // from class: com.hzbhd.canbus.car._324.UiMgr$$ExternalSyntheticLambda11
            @Override // com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener
            public final void onClickBottomBtnItem(int i) {
                UiMgr.m638lambda21$lambda20(originalCarDevicePageUiSet, uiMgr$4$fastParams$1, i);
            }
        });
        originalCarDevicePageUiSet.setOnOriginalSongItemClickListener(new OnOriginalSongItemClickListener() { // from class: com.hzbhd.canbus.car._324.UiMgr$4$5
            @Override // com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener
            public void onSongItemClick(int position) {
                int mOrigiSource = this.this$0.mMsgMgr.getMOrigiSource();
                if (mOrigiSource != MsgMgr.OriginalSource.TUNER.getValue()) {
                    if (mOrigiSource == MsgMgr.OriginalSource.DISC.getValue()) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -118, 6, (byte) (position + 1)});
                    }
                } else {
                    byte[] bArr = new byte[4];
                    bArr[0] = 22;
                    bArr[1] = -118;
                    bArr[2] = (byte) (this.this$0.mMsgMgr.getMOrigiRadioBand().getIsFm() ? 5 : 4);
                    bArr[3] = (byte) (position + 1);
                    CanbusMsgSender.sendMsg(bArr);
                }
            }

            @Override // com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener
            public void onSongItemLongClick(int position) {
                if (this.this$0.mMsgMgr.getMOrigiSource() == MsgMgr.OriginalSource.TUNER.getValue()) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -118, 3, (byte) (position + 1)});
                }
            }
        });
        final PanelKeyPageUiSet panelKeyPageUiSet = getPanelKeyPageUiSet(context);
        panelKeyPageUiSet.setOnPanelKeyPositionListener(new OnPanelKeyPositionListener() { // from class: com.hzbhd.canbus.car._324.UiMgr$5$1
            /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
            java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
            	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
            	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
            	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
            	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
             */
            @Override // com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener
            public void click(int position) {
                List<String> list = panelKeyPageUiSet.getList();
                String str = list != null ? list.get(position) : null;
                if (str != null) {
                    int iHashCode = str.hashCode();
                    if (iHashCode == 3524221) {
                        if (str.equals(OriginalBtnAction.SCAN)) {
                            UiMgr.m640lambda24$sendCommand(this, 18);
                        }
                        return;
                    }
                    switch (iHashCode) {
                        case -1629801603:
                            if (str.equals("_309_panel_11")) {
                                UiMgr.m640lambda24$sendCommand(this, 16);
                                break;
                            }
                            break;
                        case -1629801602:
                            if (str.equals("_309_panel_12")) {
                                UiMgr.m640lambda24$sendCommand(this, 17);
                                break;
                            }
                            break;
                        default:
                            switch (iHashCode) {
                                case -1299500236:
                                    if (str.equals("_309_panel_1")) {
                                        UiMgr.m640lambda24$sendCommand(this, 1);
                                        break;
                                    }
                                    break;
                                case -1299500235:
                                    if (str.equals("_309_panel_2")) {
                                        UiMgr.m640lambda24$sendCommand(this, 2);
                                        break;
                                    }
                                    break;
                                case -1299500234:
                                    if (str.equals("_309_panel_3")) {
                                        UiMgr.m640lambda24$sendCommand(this, 3);
                                        break;
                                    }
                                    break;
                                case -1299500233:
                                    if (str.equals("_309_panel_4")) {
                                        UiMgr.m640lambda24$sendCommand(this, 4);
                                        break;
                                    }
                                    break;
                                case -1299500232:
                                    if (str.equals("_309_panel_5")) {
                                        UiMgr.m640lambda24$sendCommand(this, 5);
                                        break;
                                    }
                                    break;
                                case -1299500231:
                                    if (str.equals("_309_panel_6")) {
                                        UiMgr.m640lambda24$sendCommand(this, 6);
                                        break;
                                    }
                                    break;
                                case -1299500230:
                                    if (str.equals("_309_panel_7")) {
                                        UiMgr.m640lambda24$sendCommand(this, 7);
                                        break;
                                    }
                                    break;
                                case -1299500229:
                                    if (str.equals("_309_panel_8")) {
                                        UiMgr.m640lambda24$sendCommand(this, 8);
                                        break;
                                    }
                                    break;
                                case -1299500228:
                                    if (str.equals("_309_panel_9")) {
                                        UiMgr.m640lambda24$sendCommand(this, 9);
                                        break;
                                    }
                                    break;
                            }
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-9$lambda-4$lambda-0, reason: not valid java name */
    public static final void m642lambda9$lambda4$lambda0(UiMgr this$0, FrontArea frontArea, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str = frontArea.getLineBtnAction()[0][i];
        Intrinsics.checkNotNullExpressionValue(str, "lineBtnAction[0][position]");
        this$0.sendAirCommand(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-9$lambda-4$lambda-1, reason: not valid java name */
    public static final void m643lambda9$lambda4$lambda1(UiMgr this$0, FrontArea frontArea, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str = frontArea.getLineBtnAction()[1][i];
        Intrinsics.checkNotNullExpressionValue(str, "lineBtnAction[1][position]");
        this$0.sendAirCommand(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-9$lambda-4$lambda-2, reason: not valid java name */
    public static final void m644lambda9$lambda4$lambda2(UiMgr this$0, FrontArea frontArea, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str = frontArea.getLineBtnAction()[2][i];
        Intrinsics.checkNotNullExpressionValue(str, "lineBtnAction[2][position]");
        this$0.sendAirCommand(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-9$lambda-4$lambda-3, reason: not valid java name */
    public static final void m645lambda9$lambda4$lambda3(UiMgr this$0, FrontArea frontArea, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str = frontArea.getLineBtnAction()[3][i];
        Intrinsics.checkNotNullExpressionValue(str, "lineBtnAction[3][position]");
        this$0.sendAirCommand(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-9$lambda-8$lambda-5, reason: not valid java name */
    public static final void m646lambda9$lambda8$lambda5(UiMgr this$0, RearArea rearArea, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str = rearArea.getLineBtnAction()[0][i];
        Intrinsics.checkNotNullExpressionValue(str, "lineBtnAction[0][position]");
        this$0.sendAirCommand(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-9$lambda-8$lambda-6, reason: not valid java name */
    public static final void m647lambda9$lambda8$lambda6(UiMgr this$0, RearArea rearArea, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str = rearArea.getLineBtnAction()[3][i];
        Intrinsics.checkNotNullExpressionValue(str, "lineBtnAction[3][position]");
        this$0.sendAirCommand(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-13$lambda-10, reason: not valid java name */
    public static final void m631lambda13$lambda10(SettingPageUiSet settingPageUiSet, int i, int i2, int i3) {
        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        if (Intrinsics.areEqual(titleSrn, "_143_0xAD_setting7")) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 17, (byte) i3});
        } else if (Intrinsics.areEqual(titleSrn, "_143_0xAD_setting4")) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 18, (byte) i3});
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-13$lambda-11, reason: not valid java name */
    public static final void m632lambda13$lambda11(SettingPageUiSet settingPageUiSet, int i, int i2, int i3) {
        if (Intrinsics.areEqual(settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn(), "_186_asl")) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, (byte) ((i3 * 7) + 1)});
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-13$lambda-12, reason: not valid java name */
    public static final void m633lambda13$lambda12(SettingPageUiSet settingPageUiSet, int i, int i2) {
        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        if (Intrinsics.areEqual(titleSrn, "reset")) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 19, 1});
        } else if (Intrinsics.areEqual(titleSrn, "_324_reset_mileage")) {
            CanbusMsgSender.sendMsg(new byte[]{22, -116, 1, 1});
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-21$lambda-16, reason: not valid java name */
    public static final void m634lambda21$lambda16(UiMgr this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 1, 1});
            this$0.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._324.UiMgr$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    UiMgr.m635lambda21$lambda16$lambda15();
                }
            }, 100L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-21$lambda-16$lambda-15, reason: not valid java name */
    public static final void m635lambda21$lambda16$lambda15() {
        CanbusMsgSender.sendMsg(new byte[]{22, -113, 2, 1, 0});
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-21$lambda-17, reason: not valid java name */
    public static final void m636lambda21$lambda17() {
        CanbusMsgSender.sendMsg(new byte[]{22, -118, 1, 0});
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /* renamed from: lambda-21$lambda-18, reason: not valid java name */
    public static final void m637lambda21$lambda18(OriginalCarDevicePageUiSet originalCarDevicePageUiSet, int i) {
        String str = originalCarDevicePageUiSet.getRowTopBtnAction()[i];
        if (str != null) {
            switch (str.hashCode()) {
                case -1415634215:
                    if (str.equals(OriginalBtnAction.RDM_DISC)) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -118, 9, !GeneralOriginalCarDeviceData.rdm_disc ? 1 : 0});
                        break;
                    }
                    break;
                case -136075545:
                    if (str.equals(OriginalBtnAction.DISC_SCAN)) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -118, 10, !GeneralOriginalCarDeviceData.disc_scan ? 1 : 0});
                        break;
                    }
                    break;
                case 112763:
                    if (str.equals(OriginalBtnAction.RDM)) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -118, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, !GeneralOriginalCarDeviceData.rdm ? 1 : 0});
                        break;
                    }
                    break;
                case 113142:
                    if (str.equals(OriginalBtnAction.RPT)) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -118, 11, !GeneralOriginalCarDeviceData.rpt ? 1 : 0});
                        break;
                    }
                    break;
                case 3524221:
                    if (str.equals(OriginalBtnAction.SCAN)) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -118, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, !GeneralOriginalCarDeviceData.scan ? 1 : 0});
                        break;
                    }
                    break;
                case 844879422:
                    if (str.equals(OriginalBtnAction.RPT_DISC)) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -118, 8, !GeneralOriginalCarDeviceData.rpt_disc ? 1 : 0});
                        break;
                    }
                    break;
            }
        }
    }

    /* renamed from: lambda-21$sendFastCommand, reason: not valid java name */
    private static final void m639lambda21$sendFastCommand(UiMgr$4$fastParams$1 uiMgr$4$fastParams$1, int i) {
        byte[] command = uiMgr$4$fastParams$1.getCommand();
        if (uiMgr$4$fastParams$1.getIsFasting()) {
            command[3] = 0;
        } else {
            command[2] = (byte) i;
            command[3] = 1;
        }
        CanbusMsgSender.sendMsg(command);
        uiMgr$4$fastParams$1.setFasting(!uiMgr$4$fastParams$1.getIsFasting());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-21$lambda-20, reason: not valid java name */
    public static final void m638lambda21$lambda20(OriginalCarDevicePageUiSet originalCarDevicePageUiSet, UiMgr$4$fastParams$1 fastParams, int i) {
        Intrinsics.checkNotNullParameter(fastParams, "$fastParams");
        String str = originalCarDevicePageUiSet.getRowBottomBtnAction()[i];
        if (Intrinsics.areEqual(str, "down")) {
            m639lambda21$sendFastCommand(fastParams, 14);
        } else if (Intrinsics.areEqual(str, "up")) {
            m639lambda21$sendFastCommand(fastParams, 15);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-24$sendCommand, reason: not valid java name */
    public static final void m640lambda24$sendCommand(UiMgr uiMgr, int i) {
        final byte[] bArr = {22, 116, (byte) i, 1};
        CanbusMsgSender.sendMsg(bArr);
        uiMgr.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._324.UiMgr$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                UiMgr.m641lambda24$sendCommand$lambda23$lambda22(bArr);
            }
        }, 100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-24$sendCommand$lambda-23$lambda-22, reason: not valid java name */
    public static final void m641lambda24$sendCommand$lambda23$lambda22(byte[] this_run) {
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        this_run[3] = 0;
        CanbusMsgSender.sendMsg(this_run);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterative(DepthRegionTraversal.java:31)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visit(SwitchOverStringVisitor.java:60)
     */
    private final void sendAirCommand(String title) {
        switch (title.hashCode()) {
            case -1274277292:
                if (title.equals(AirBtnAction.CLEAN_AIR)) {
                    sendAirCommand(113, 38);
                    break;
                }
                break;
            case -1142919397:
                if (title.equals(AirBtnAction.REAR_BLOW_HEAD_FOOT)) {
                    sendAirCommand(114, 73);
                    break;
                }
                break;
            case -713186454:
                if (title.equals(AirBtnAction.REAR_AUTO)) {
                    sendAirCommand(114, 65);
                    break;
                }
                break;
            case -712865050:
                if (title.equals(AirBtnAction.REAR_LOCK)) {
                    sendAirCommand(114, 64);
                    break;
                }
                break;
            case -620266838:
                if (title.equals(AirBtnAction.REAR_POWER)) {
                    sendAirCommand(114, 66);
                    break;
                }
                break;
            case -597744666:
                if (title.equals("blow_positive")) {
                    sendAirCommand(113, 16);
                    break;
                }
                break;
            case -480367616:
                if (title.equals(AirBtnAction.REAR_BLOW_FOOT)) {
                    sendAirCommand(114, 74);
                    break;
                }
                break;
            case -480318094:
                if (title.equals(AirBtnAction.REAR_BLOW_HEAD)) {
                    sendAirCommand(114, 72);
                    break;
                }
                break;
            case -424438238:
                if (title.equals(AirBtnAction.BLOW_NEGATIVE)) {
                    sendAirCommand(113, 17);
                    break;
                }
                break;
            case -246396018:
                if (title.equals(AirBtnAction.MAX_FRONT)) {
                    sendAirCommand(113, 4);
                    break;
                }
                break;
            case -92674103:
                if (title.equals(AirBtnAction.FRONT_WINDOW_HEAT)) {
                    sendAirCommand(113, 41);
                    break;
                }
                break;
            case 3106:
                if (title.equals("ac")) {
                    sendAirCommand(113, 8);
                    break;
                }
                break;
            case 3005871:
                if (title.equals("auto")) {
                    sendAirCommand(113, 1);
                    break;
                }
                break;
            case 3094652:
                if (title.equals("dual")) {
                    sendAirCommand(113, 2);
                    break;
                }
                break;
            case 3496356:
                if (title.equals(AirBtnAction.REAR)) {
                    sendAirCommand(113, 5);
                    break;
                }
                break;
            case 106858757:
                if (title.equals("power")) {
                    sendAirCommand(113, 10);
                    break;
                }
                break;
            case 109854462:
                if (title.equals(AirBtnAction.SWING)) {
                    sendAirCommand(113, 40);
                    break;
                }
                break;
            case 341572893:
                if (title.equals(AirBtnAction.BLOW_WINDOW)) {
                    sendAirCommand(113, 33);
                    break;
                }
                break;
            case 395882750:
                if (title.equals(AirBtnAction.NEGATIVE_ION)) {
                    sendAirCommand(113, 39);
                    break;
                }
                break;
            case 756225563:
                if (title.equals("in_out_cycle")) {
                    sendAirCommand(113, 3);
                    break;
                }
                break;
            case 1006620906:
                if (title.equals(AirBtnAction.AUTO_WIND_MODE)) {
                    sendAirCommand(113, 9);
                    break;
                }
                break;
            case 1018451744:
                if (title.equals(AirBtnAction.BLOW_HEAD_FOOT)) {
                    sendAirCommand(113, 18);
                    break;
                }
                break;
            case 1434490075:
                if (title.equals(AirBtnAction.BLOW_FOOT)) {
                    sendAirCommand(113, 35);
                    break;
                }
                break;
            case 1434539597:
                if (title.equals(AirBtnAction.BLOW_HEAD)) {
                    sendAirCommand(113, 34);
                    break;
                }
                break;
            case 1568764496:
                if (title.equals(AirBtnAction.BLOW_WINDOW_FOOT)) {
                    sendAirCommand(113, 19);
                    break;
                }
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendAirCommand(int dataType, int command) {
        final byte[] bArr = {22, (byte) dataType, (byte) command, 1};
        CanbusMsgSender.sendMsg(bArr);
        this.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._324.UiMgr$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                UiMgr.m648sendAirCommand$lambda26$lambda25(bArr);
            }
        }, 100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sendAirCommand$lambda-26$lambda-25, reason: not valid java name */
    public static final void m648sendAirCommand$lambda26$lambda25(byte[] this_run) {
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        this_run[3] = 0;
        CanbusMsgSender.sendMsg(this_run);
    }
}
