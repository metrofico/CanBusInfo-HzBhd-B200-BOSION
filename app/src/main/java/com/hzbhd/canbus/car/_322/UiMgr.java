package com.hzbhd.canbus.car._322;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.bean.RearArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AirBtnAction;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalBtnAction;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.constant.share.lcd.LcdInfoShare;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import nfore.android.bt.res.NfDef;




public final class UiMgr extends AbstractUiMgr {
    private static final int FRONT_AIR_DATA_TYPE = 149;
    private static final int REAR_AIR_DATA_TYPE = 150;
    private static final String TAG = "_322_UiMgr";
    private final Handler mHandler;
    private final MsgMgr mMsgMgr;

    /* JADX WARN: Type inference failed for: r2v4, types: [T, byte[]] */
    public UiMgr(Context context) {

        MsgMgrInterface canMsgMgr = MsgMgrFactory.getCanMsgMgr(context);

        this.mMsgMgr = (MsgMgr) canMsgMgr;
        this.mHandler = new Handler(Looper.getMainLooper());
        AirPageUiSet airUiSet = getAirUiSet(context);
        final FrontArea frontArea = airUiSet.getFrontArea();
        frontArea.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._322.UiMgr$$ExternalSyntheticLambda7
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m608lambda9$lambda4$lambda0(this.f$0, frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._322.UiMgr$$ExternalSyntheticLambda10
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m609lambda9$lambda4$lambda1(this.f$0, frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._322.UiMgr$$ExternalSyntheticLambda11
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m610lambda9$lambda4$lambda2(this.f$0, frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._322.UiMgr$$ExternalSyntheticLambda12
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m611lambda9$lambda4$lambda3(this.f$0, frontArea, i);
            }
        }});
        frontArea.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._322.UiMgr$1$1$5
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                this.this$0.sendAirCommand(149, 19);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                this.this$0.sendAirCommand(149, 18);
            }
        }, null, new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._322.UiMgr$1$1$6
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                this.this$0.sendAirCommand(149, 21);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                this.this$0.sendAirCommand(149, 20);
            }
        }});
        frontArea.setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._322.UiMgr$1$1$7
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                this.this$0.sendAirCommand(149, 16);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                this.this$0.sendAirCommand(149, 17);
            }
        });
        frontArea.setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._322.UiMgr$1$1$8
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                this.this$0.sendAirCommand(149, 32);
            }
        }, new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._322.UiMgr$1$1$9
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                this.this$0.sendAirCommand(149, 34);
            }
        }, new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._322.UiMgr$1$1$10
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                this.this$0.sendAirCommand(149, 33);
            }
        }, new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._322.UiMgr$1$1$11
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                this.this$0.sendAirCommand(149, 35);
            }
        }});
        final RearArea rearArea = airUiSet.getRearArea();
        rearArea.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._322.UiMgr$$ExternalSyntheticLambda13
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m612lambda9$lambda8$lambda5(this.f$0, rearArea, i);
            }
        }});
        rearArea.setTempSetViewOnUpDownClickListeners(new UiMgr$1$2$3[]{null, new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._322.UiMgr$1$2$3
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                this.this$0.sendAirCommand(150, 15);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                this.this$0.sendAirCommand(150, 14);
            }
        }, null});
        rearArea.setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._322.UiMgr$1$2$5
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                this.this$0.sendAirCommand(150, 12);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                this.this$0.sendAirCommand(150, 13);
            }
        });
        final SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._322.UiMgr$$ExternalSyntheticLambda14
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public final void onClickItem(int i, int i2, int i3) {
                UiMgr.m597lambda19$lambda12(settingUiSet, this, i, i2, i3);
            }
        });
        settingUiSet.setOnSettingItemSwitchListener(new OnSettingItemSwitchListener() { // from class: com.hzbhd.canbus.car._322.UiMgr$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener
            public final void onSwitch(int i, int i2, int i3) {
                UiMgr.m598lambda19$lambda14(settingUiSet, this, i, i2, i3);
            }
        });
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._322.UiMgr$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public final void onClickItem(int i, int i2, int i3) {
                UiMgr.m599lambda19$lambda15(settingUiSet, this, i, i2, i3);
            }
        });
        settingUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._322.UiMgr$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
            public final void onConfirmClick(int i, int i2) {
                UiMgr.m600lambda19$lambda18(settingUiSet, this, i, i2);
            }
        });
        final ParkPageUiSet parkPageUiSet = getParkPageUiSet(context);
        parkPageUiSet.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._322.UiMgr$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public final void onClickItem(int i) {
                UiMgr.m602lambda21$lambda20(parkPageUiSet, i);
            }
        });
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        amplifierPageUiSet.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._322.UiMgr$4$1

            /* compiled from: UiMgr.kt */
            
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[AmplifierActivity.AmplifierBand.values().length];
                    iArr[AmplifierActivity.AmplifierBand.VOLUME.ordinal()] = 1;
                    iArr[AmplifierActivity.AmplifierBand.BASS.ordinal()] = 2;
                    iArr[AmplifierActivity.AmplifierBand.MIDDLE.ordinal()] = 3;
                    iArr[AmplifierActivity.AmplifierBand.TREBLE.ordinal()] = 4;
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
            public void progress(AmplifierActivity.AmplifierBand amplifierBand, int progress) {

                int i = WhenMappings.$EnumSwitchMapping$0[amplifierBand.ordinal()];
                if (i == 1) {
                    byte[] amplifierData = this.this$0.getAmplifierData();
                    amplifierData[2] = (byte) DataHandleUtils.setIntFromByteWithBit(amplifierData[2], progress, 0, 5);
                    CanbusMsgSender.sendMsg(amplifierData);
                    return;
                }
                if (i == 2) {
                    byte[] amplifierData2 = this.this$0.getAmplifierData();
                    amplifierData2[5] = (byte) (progress + 3);
                    CanbusMsgSender.sendMsg(amplifierData2);
                } else if (i == 3) {
                    byte[] amplifierData3 = this.this$0.getAmplifierData();
                    amplifierData3[6] = (byte) (progress + 3);
                    CanbusMsgSender.sendMsg(amplifierData3);
                } else {
                    if (i != 4) {
                        return;
                    }
                    byte[] amplifierData4 = this.this$0.getAmplifierData();
                    amplifierData4[7] = (byte) (progress + 3);
                    CanbusMsgSender.sendMsg(amplifierData4);
                }
            }
        });
        amplifierPageUiSet.setOnAmplifierPositionListener(new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._322.UiMgr$4$2

            /* compiled from: UiMgr.kt */
            
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[AmplifierActivity.AmplifierPosition.values().length];
                    iArr[AmplifierActivity.AmplifierPosition.LEFT_RIGHT.ordinal()] = 1;
                    iArr[AmplifierActivity.AmplifierPosition.FRONT_REAR.ordinal()] = 2;
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
            public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int value) {

                int i = WhenMappings.$EnumSwitchMapping$0[amplifierPosition.ordinal()];
                if (i == 1) {
                    byte[] amplifierData = this.this$0.getAmplifierData();
                    amplifierData[3] = (byte) (value + 7 + 3);
                    CanbusMsgSender.sendMsg(amplifierData);
                } else {
                    if (i != 2) {
                        return;
                    }
                    byte[] amplifierData2 = this.this$0.getAmplifierData();
                    amplifierData2[4] = (byte) ((-value) + 7 + 3);
                    CanbusMsgSender.sendMsg(amplifierData2);
                }
            }
        });
        final OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(context);
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new byte[0];
        originalCarDevicePageUiSet.setOnOriginalTopBtnClickListeners(new OnOriginalTopBtnClickListener() { // from class: com.hzbhd.canbus.car._322.UiMgr$$ExternalSyntheticLambda8
            @Override // com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener
            public final void onClickTopBtnItem(int i) {
                UiMgr.m604lambda34$lambda24(originalCarDevicePageUiSet, this, i);
            }
        });
        originalCarDevicePageUiSet.setOnOriginalBottomBtnClickListeners(new OnOriginalBottomBtnClickListener() { // from class: com.hzbhd.canbus.car._322.UiMgr$$ExternalSyntheticLambda9
            @Override // com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener
            public final void onClickBottomBtnItem(int i) {
                UiMgr.m605lambda34$lambda33(this.f$0, originalCarDevicePageUiSet, booleanRef, objectRef, i);
            }
        });
        originalCarDevicePageUiSet.setOnOriginalSongItemClickListener(new OnOriginalSongItemClickListener() { // from class: com.hzbhd.canbus.car._322.UiMgr$5$3
            @Override // com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener
            public void onSongItemClick(int position) {
                CanbusMsgSender.sendMsg(new byte[]{22, -95, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) (position + 1)});
            }

            @Override // com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener
            public void onSongItemLongClick(int position) {
                CanbusMsgSender.sendMsg(new byte[]{22, -95, 11, (byte) (position + 1)});
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-9$lambda-4$lambda-0, reason: not valid java name */
    public static final void m608lambda9$lambda4$lambda0(UiMgr this$0, FrontArea frontArea, int i) {

        String str = frontArea.getLineBtnAction()[0][i];

        this$0.sendAirCommand(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-9$lambda-4$lambda-1, reason: not valid java name */
    public static final void m609lambda9$lambda4$lambda1(UiMgr this$0, FrontArea frontArea, int i) {

        String str = frontArea.getLineBtnAction()[1][i];

        this$0.sendAirCommand(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-9$lambda-4$lambda-2, reason: not valid java name */
    public static final void m610lambda9$lambda4$lambda2(UiMgr this$0, FrontArea frontArea, int i) {

        String str = frontArea.getLineBtnAction()[2][i];

        this$0.sendAirCommand(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-9$lambda-4$lambda-3, reason: not valid java name */
    public static final void m611lambda9$lambda4$lambda3(UiMgr this$0, FrontArea frontArea, int i) {

        String str = frontArea.getLineBtnAction()[3][i];

        this$0.sendAirCommand(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-9$lambda-8$lambda-5, reason: not valid java name */
    public static final void m612lambda9$lambda8$lambda5(UiMgr this$0, RearArea rearArea, int i) {

        String str = rearArea.getLineBtnAction()[3][i];

        this$0.sendAirCommand(str);
    }

    /* renamed from: lambda-19$sendBackrestCommand, reason: not valid java name */
    private static final void m601lambda19$sendBackrestCommand(int i, int i2) {
        byte[] bArr = new byte[4];
        int i3 = 0;
        bArr[0] = 22;
        bArr[1] = -64;
        bArr[2] = (byte) i;
        if (i2 > 0) {
            i3 = 1;
        } else if (i2 < 0) {
            i3 = 2;
        }
        bArr[3] = (byte) i3;
        CanbusMsgSender.sendMsg(bArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /* renamed from: lambda-19$lambda-12, reason: not valid java name */
    public static final void m597lambda19$lambda12(SettingPageUiSet settingPageUiSet, UiMgr this$0, int i, int i2, int i3) {

        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        if (titleSrn != null) {
            switch (titleSrn.hashCode()) {
                case -2088909201:
                    if (titleSrn.equals("_94_driver_cushion")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -64, 8, (byte) i3});
                        break;
                    }
                    break;
                case -2065696358:
                    if (titleSrn.equals("_118_setting_title_97")) {
                        byte[] amplifierData = this$0.getAmplifierData();
                        amplifierData[8] = (byte) DataHandleUtils.setIntFromByteWithBit(amplifierData[8], i3, 0, 2);
                        CanbusMsgSender.sendMsg(amplifierData);
                        break;
                    }
                    break;
                case -1693206700:
                    if (titleSrn.equals("_94_temperature_unit")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -105, 84, (byte) i3});
                        break;
                    }
                    break;
                case -922538678:
                    if (titleSrn.equals("ford_range_unit")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -105, 82, (byte) (1 - i3)});
                        break;
                    }
                    break;
                case -434902758:
                    if (titleSrn.equals("_304_atoms_lamp_setup")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -105, 97, (byte) (i3 + 1)});
                        break;
                    }
                    break;
                case 102584022:
                    if (titleSrn.equals("language_setup")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -105, 83, (byte) ((i3 * 7) + 1)});
                        break;
                    }
                    break;
                case 378909815:
                    if (titleSrn.equals("_94_passenger_cushion")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -64, 10, (byte) i3});
                        break;
                    }
                    break;
                case 388217829:
                    if (titleSrn.equals("_94_passenger_backrest")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -64, 9, (byte) i3});
                        break;
                    }
                    break;
                case 1195239661:
                    if (titleSrn.equals("_94_driver_backrest")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -64, 7, (byte) i3});
                        break;
                    }
                    break;
                case 1575463461:
                    if (titleSrn.equals("_279_sound")) {
                        byte[] amplifierData2 = this$0.getAmplifierData();
                        amplifierData2[8] = (byte) DataHandleUtils.setIntFromByteWithBit(amplifierData2[8], i3, 2, 2);
                        CanbusMsgSender.sendMsg(amplifierData2);
                        break;
                    }
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-19$lambda-14, reason: not valid java name */
    public static final void m598lambda19$lambda14(SettingPageUiSet settingPageUiSet, UiMgr this$0, int i, int i2, int i3) {

        if (Intrinsics.areEqual(settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn(), "_197_amplifier_mute")) {
            byte[] amplifierData = this$0.getAmplifierData();
            amplifierData[2] = (byte) DataHandleUtils.setOneBit(amplifierData[2], 7, i3);
            CanbusMsgSender.sendMsg(amplifierData);
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
    /* renamed from: lambda-19$lambda-15, reason: not valid java name */
    public static final void m599lambda19$lambda15(SettingPageUiSet settingPageUiSet, UiMgr this$0, int i, int i2, int i3) {

        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        if (titleSrn != null) {
            switch (titleSrn.hashCode()) {
                case -522237854:
                    if (titleSrn.equals("_94_driver_low")) {
                        m601lambda19$sendBackrestCommand(3, this$0.mMsgMgr.getDriver().getLow() - i3);
                        break;
                    }
                    break;
                case -522237098:
                    if (titleSrn.equals("_94_driver_mid")) {
                        m601lambda19$sendBackrestCommand(2, this$0.mMsgMgr.getDriver().getMiddle() - i3);
                        break;
                    }
                    break;
                case 587065676:
                    if (titleSrn.equals("_94_passenger_high")) {
                        m601lambda19$sendBackrestCommand(4, this$0.mMsgMgr.getPassenger().getHigh() - i3);
                        break;
                    }
                    break;
                case 988772970:
                    if (titleSrn.equals("_94_passenger_low")) {
                        m601lambda19$sendBackrestCommand(6, this$0.mMsgMgr.getPassenger().getLow() - i3);
                        break;
                    }
                    break;
                case 988773726:
                    if (titleSrn.equals("_94_passenger_mid")) {
                        m601lambda19$sendBackrestCommand(5, this$0.mMsgMgr.getPassenger().getMiddle() - i3);
                        break;
                    }
                    break;
                case 990370388:
                    if (titleSrn.equals("_94_driver_high")) {
                        m601lambda19$sendBackrestCommand(1, this$0.mMsgMgr.getDriver().getHigh() - i3);
                        break;
                    }
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-19$lambda-18, reason: not valid java name */
    public static final void m600lambda19$lambda18(SettingPageUiSet settingPageUiSet, UiMgr this$0, int i, int i2) {

        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        if (Intrinsics.areEqual(titleSrn, "_322_all_reset")) {
            byte[] amplifierData = this$0.getAmplifierData();
            amplifierData[8] = (byte) DataHandleUtils.setOneBit(amplifierData[8], 7, 1);
            CanbusMsgSender.sendMsg(amplifierData);
        } else if (Intrinsics.areEqual(titleSrn, "_322_sound_reset")) {
            byte[] amplifierData2 = this$0.getAmplifierData();
            amplifierData2[8] = (byte) DataHandleUtils.setOneBit(amplifierData2[8], 6, 1);
            CanbusMsgSender.sendMsg(amplifierData2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-21$lambda-20, reason: not valid java name */
    public static final void m602lambda21$lambda20(ParkPageUiSet parkPageUiSet, int i) {
        String titleSrn = parkPageUiSet.getPanoramicBtnList().get(i).getTitleSrn();
        if (Intrinsics.areEqual(titleSrn, "_220_normal")) {
            CanbusMsgSender.sendMsg(new byte[]{22, -105, 96, 1});
        } else if (Intrinsics.areEqual(titleSrn, "overlook")) {
            CanbusMsgSender.sendMsg(new byte[]{22, -105, 96, 2});
        }
    }

    /* renamed from: lambda-34$checkFastStatus, reason: not valid java name */
    private static final void m603lambda34$checkFastStatus(Ref.BooleanRef booleanRef, Ref.ObjectRef<byte[]> objectRef, byte[] bArr) {
        Log.i(TAG, "2 checkFastStatus: " + booleanRef.element);
        if (booleanRef.element) {
            bArr = objectRef.element;
            bArr[3] = 0;
        }
        CanbusMsgSender.sendMsg(bArr);
        if (!(objectRef.element.length == 0)) {
            booleanRef.element = !booleanRef.element;
        }
        Log.i(TAG, "1 checkFastStatus: " + booleanRef.element);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /* renamed from: lambda-34$lambda-24, reason: not valid java name */
    public static final void m604lambda34$lambda24(OriginalCarDevicePageUiSet originalCarDevicePageUiSet, UiMgr this$0, int i) {

        String str = originalCarDevicePageUiSet.getRowTopBtnAction()[i];
        if (str != null) {
            int iHashCode = str.hashCode();
            if (iHashCode == 3116) {
                if (str.equals(OriginalBtnAction.AM)) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -95, 5, 1});
                }
                return;
            }
            if (iHashCode == 3271) {
                if (str.equals(OriginalBtnAction.FM)) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -95, 5, 2});
                    return;
                }
                return;
            }
            if (iHashCode == 112763) {
                if (str.equals(OriginalBtnAction.RDM)) {
                    CanbusMsgSender.sendMsg(GeneralOriginalCarDeviceData.rdm ? new byte[]{22, -93, 7, 1} : new byte[]{22, -93, 5, 1});
                    return;
                }
                return;
            }
            if (iHashCode == 113142) {
                if (str.equals(OriginalBtnAction.RPT)) {
                    CanbusMsgSender.sendMsg(GeneralOriginalCarDeviceData.rpt ? new byte[]{22, -93, 8, 1} : new byte[]{22, -93, 6, 1});
                    return;
                }
                return;
            }
            if (iHashCode == 3016245 && str.equals("band")) {
                String mOriginalRadioBand = this$0.mMsgMgr.getMOriginalRadioBand();
                switch (mOriginalRadioBand.hashCode()) {
                    case 64901:
                        if (mOriginalRadioBand.equals("AM1")) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -95, 6, 2});
                            break;
                        }
                        break;
                    case 64902:
                        if (mOriginalRadioBand.equals("AM2")) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -95, 6, 1});
                            break;
                        }
                        break;
                    case 69706:
                        if (mOriginalRadioBand.equals("FM1")) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -95, 6, 4});
                            break;
                        }
                        break;
                    case 69707:
                        if (mOriginalRadioBand.equals("FM2")) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -95, 6, 5});
                            break;
                        }
                        break;
                    case 2076557404:
                        if (mOriginalRadioBand.equals("FM-AST")) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -95, 6, 3});
                            break;
                        }
                        break;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v13, types: [T, byte[]] */
    /* JADX WARN: Type inference failed for: r10v21, types: [T, byte[]] */
    /* renamed from: lambda-34$lambda-33, reason: not valid java name */
    public static final void m605lambda34$lambda33(UiMgr this$0, OriginalCarDevicePageUiSet originalCarDevicePageUiSet, Ref.BooleanRef isFastStatus, Ref.ObjectRef fastCommand, int i) {
        String str;
        byte[] bArr;
        byte[] bArr2;



        int mOriginalDeviceStatus = this$0.mMsgMgr.getMOriginalDeviceStatus();
        if (mOriginalDeviceStatus == 1) {
            String str2 = originalCarDevicePageUiSet.getRowBottomBtnAction()[i];
            if (str2 != null) {
                int iHashCode = str2.hashCode();
                if (iHashCode == -841905119) {
                    if (str2.equals(OriginalBtnAction.PREV_DISC)) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -95, 4, 1});
                    }
                    return;
                } else if (iHashCode == 3739) {
                    if (str2.equals("up")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -95, 2, 1});
                        return;
                    }
                    return;
                } else if (iHashCode == 3089570) {
                    if (str2.equals("down")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -95, 1, 1});
                        return;
                    }
                    return;
                } else {
                    if (iHashCode == 1216748385 && str2.equals(OriginalBtnAction.NEXT_DISC)) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -95, 3, 1});
                        return;
                    }
                    return;
                }
            }
            return;
        }
        if (mOriginalDeviceStatus == 2 && (str = originalCarDevicePageUiSet.getRowBottomBtnAction()[i]) != null) {
            long j = 100;
            switch (str.hashCode()) {
                case 3739:
                    if (str.equals("up")) {
                        if (isFastStatus.element) {
                            byte[] bArr3 = (byte[]) fastCommand.element;
                            bArr3[3] = 0;
                            bArr = bArr3;
                        } else {
                            ?? r10 = {22, -93, 10, 1};
                            fastCommand.element = r10;
                            bArr = r10;
                        }
                        CanbusMsgSender.sendMsg(bArr);
                        isFastStatus.element = !isFastStatus.element;
                        break;
                    }
                    break;
                case 3089570:
                    if (str.equals("down")) {
                        if (isFastStatus.element) {
                            byte[] bArr4 = (byte[]) fastCommand.element;
                            bArr4[3] = 0;
                            bArr2 = bArr4;
                        } else {
                            ?? r102 = {22, -93, 9, 1};
                            fastCommand.element = r102;
                            bArr2 = r102;
                        }
                        CanbusMsgSender.sendMsg(bArr2);
                        isFastStatus.element = !isFastStatus.element;
                        break;
                    }
                    break;
                case 3317767:
                    if (str.equals("left")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -93, 1, 1});
                        break;
                    }
                    break;
                case 3443508:
                    if (str.equals(OriginalBtnAction.PLAY)) {
                        Handler handler = this$0.mHandler;
                        Runnable runnable = new Runnable() { // from class: com.hzbhd.canbus.car._322.UiMgr$$ExternalSyntheticLambda0
                            @Override // java.lang.Runnable
                            public final void run() {
                                UiMgr.m606lambda34$lambda33$lambda27();
                            }
                        };
                        if (isFastStatus.element) {
                            byte[] bArr5 = (byte[]) fastCommand.element;
                            bArr5[3] = 0;
                            Unit unit = Unit.INSTANCE;
                            CanbusMsgSender.sendMsg(bArr5);
                        } else {
                            j = 0;
                        }
                        handler.postDelayed(runnable, j);
                        isFastStatus.element = false;
                        break;
                    }
                    break;
                case 106440182:
                    if (str.equals(OriginalBtnAction.PAUSE)) {
                        Handler handler2 = this$0.mHandler;
                        Runnable runnable2 = new Runnable() { // from class: com.hzbhd.canbus.car._322.UiMgr$$ExternalSyntheticLambda6
                            @Override // java.lang.Runnable
                            public final void run() {
                                UiMgr.m607lambda34$lambda33$lambda29();
                            }
                        };
                        if (isFastStatus.element) {
                            byte[] bArr6 = (byte[]) fastCommand.element;
                            bArr6[3] = 0;
                            Unit unit2 = Unit.INSTANCE;
                            CanbusMsgSender.sendMsg(bArr6);
                        } else {
                            j = 0;
                        }
                        handler2.postDelayed(runnable2, j);
                        isFastStatus.element = false;
                        break;
                    }
                    break;
                case 108511772:
                    if (str.equals("right")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -93, 2, 1});
                        break;
                    }
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-34$lambda-33$lambda-27, reason: not valid java name */
    public static final void m606lambda34$lambda33$lambda27() {
        CanbusMsgSender.sendMsg(new byte[]{22, -93, 3, 1});
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-34$lambda-33$lambda-29, reason: not valid java name */
    public static final void m607lambda34$lambda33$lambda29() {
        CanbusMsgSender.sendMsg(new byte[]{22, -93, 4, 1});
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public View getCusPanoramicView(Context context) {

        return this.mMsgMgr.getActivePark(context).getMBackActiveParkView();
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
            case -1786872896:
                if (title.equals(AirBtnAction.STEERING_WHEEL_HEATING)) {
                    sendAirCommand(149, 13);
                    break;
                }
                break;
            case -1470045433:
                if (title.equals("front_defog")) {
                    sendAirCommand(149, 5);
                    break;
                }
                break;
            case -1423573049:
                if (title.equals(AirBtnAction.AC_MAX)) {
                    sendAirCommand(149, 2);
                    break;
                }
                break;
            case -712865050:
                if (title.equals(AirBtnAction.REAR_LOCK)) {
                    sendAirCommand(150, 31);
                    break;
                }
                break;
            case -631663038:
                if (title.equals("rear_defog")) {
                    sendAirCommand(149, 6);
                    break;
                }
                break;
            case -620266838:
                if (title.equals(AirBtnAction.REAR_POWER)) {
                    sendAirCommand(150, 0);
                    break;
                }
                break;
            case -246396018:
                if (title.equals(AirBtnAction.MAX_FRONT)) {
                    sendAirCommand(149, 12);
                    break;
                }
                break;
            case -92674103:
                if (title.equals(AirBtnAction.FRONT_WINDOW_HEAT)) {
                    sendAirCommand(149, 14);
                    break;
                }
                break;
            case 3106:
                if (title.equals("ac")) {
                    sendAirCommand(149, 1);
                    break;
                }
                break;
            case 3005871:
                if (title.equals("auto")) {
                    sendAirCommand(149, 4);
                    break;
                }
                break;
            case 3094652:
                if (title.equals("dual")) {
                    sendAirCommand(149, 7);
                    break;
                }
                break;
            case 106858757:
                if (title.equals("power")) {
                    sendAirCommand(149, 0);
                    break;
                }
                break;
            case 756225563:
                if (title.equals("in_out_cycle")) {
                    sendAirCommand(149, 3);
                    break;
                }
                break;
            case 1434490075:
                if (title.equals(AirBtnAction.BLOW_FOOT)) {
                    sendAirCommand(149, 9);
                    break;
                }
                break;
            case 1434539597:
                if (title.equals(AirBtnAction.BLOW_HEAD)) {
                    sendAirCommand(149, 8);
                    break;
                }
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendAirCommand(int dataType, int command) {
        final byte[] bArr = {22, (byte) dataType, (byte) command, 1};
        CanbusMsgSender.sendMsg(bArr);
        this.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._322.UiMgr$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                UiMgr.m613sendAirCommand$lambda36$lambda35(bArr);
            }
        }, 100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sendAirCommand$lambda-36$lambda-35, reason: not valid java name */
    public static final void m613sendAirCommand$lambda36$lambda35(byte[] this_run) {

        this_run[3] = 0;
        CanbusMsgSender.sendMsg(this_run);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final byte[] getAmplifierData() {
        byte[] bArr = (byte[]) this.mMsgMgr.getMAmplifierData().clone();
        bArr[0] = 22;
        bArr[1] = -109;
        byte b = bArr[8];
        bArr[8] = (byte) DataHandleUtils.setIntFromByteWithBit(b, ((b >> 2) & 3) + 1, 2, 2);
        return bArr;
    }
}
