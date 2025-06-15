package com.hzbhd.canbus.car._317;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AirBtnAction;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import nfore.android.bt.res.NfDef;




public final class UiMgr extends AbstractUiMgr {
    private int mAirAddition;
    private MsgMgr mMsgMgr;

    public UiMgr(final Context context) {

        this.mAirAddition = 204;
        MsgMgrInterface canMsgMgr = MsgMgrFactory.getCanMsgMgr(context);

        this.mMsgMgr = (MsgMgr) canMsgMgr;
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        amplifierPageUiSet.setOnAmplifierPositionListener(new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._317.UiMgr$1$1

            /* compiled from: UiMgr.kt */
            
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

                int i = WhenMappings.$EnumSwitchMapping$0[amplifierPosition.ordinal()];
                if (i == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, (byte) (value + 11)});
                } else {
                    if (i != 2) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 2, (byte) (value + 11)});
                }
            }
        });
        amplifierPageUiSet.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._317.UiMgr$1$2

            /* compiled from: UiMgr.kt */
            
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
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 8, (byte) progress});
                }
            }
        });
        final SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._317.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public final void onClickItem(int i, int i2, int i3) {
                UiMgr.m522lambda5$lambda1(settingUiSet, this, context, i, i2, i3);
            }
        });
        settingUiSet.setOnSettingItemSwitchListener(new OnSettingItemSwitchListener() { // from class: com.hzbhd.canbus.car._317.UiMgr$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener
            public final void onSwitch(int i, int i2, int i3) {
                UiMgr.m523lambda5$lambda2(settingUiSet, i, i2, i3);
            }
        });
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._317.UiMgr$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public final void onClickItem(int i, int i2, int i3) {
                UiMgr.m524lambda5$lambda3(settingUiSet, i, i2, i3);
            }
        });
        settingUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._317.UiMgr$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
            public final void onConfirmClick(int i, int i2) {
                UiMgr.m525lambda5$lambda4(settingUiSet, i, i2);
            }
        });
        final FrontArea frontArea = getAirUiSet(context).getFrontArea();
        frontArea.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._317.UiMgr$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m518lambda11$lambda10$lambda6(this.f$0, frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._317.UiMgr$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m519lambda11$lambda10$lambda7(this.f$0, frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._317.UiMgr$$ExternalSyntheticLambda6
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m520lambda11$lambda10$lambda8(this.f$0, frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._317.UiMgr$$ExternalSyntheticLambda7
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m521lambda11$lambda10$lambda9(this.f$0, frontArea, i);
            }
        }});
        frontArea.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._317.UiMgr$3$1$5
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                this.this$0.sendAirCommand(9);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                this.this$0.sendAirCommand(10);
            }
        }, null, new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._317.UiMgr$3$1$6
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                this.this$0.sendAirCommand(11);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                this.this$0.sendAirCommand(12);
            }
        }});
        frontArea.setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._317.UiMgr$3$1$7
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                this.this$0.sendAirCommand(21);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                this.this$0.sendAirCommand(20);
            }
        });
        int currentCarId = getCurrentCarId();
        if (currentCarId != 1) {
            removeFrontAirButtonByName(context, AirBtnAction.REAR);
        }
        if (currentCarId != 2) {
            removeMainPrjBtnByName(context, MainAction.TIRE_INFO);
            removeMainPrjBtnByName(context, MainAction.DRIVE_DATA);
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
    /* renamed from: lambda-5$lambda-1, reason: not valid java name */
    public static final void m522lambda5$lambda1(SettingPageUiSet settingPageUiSet, UiMgr this$0, Context context, int i, int i2, int i3) {


        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        if (titleSrn != null) {
            switch (titleSrn.hashCode()) {
                case -1144939717:
                    if (titleSrn.equals("outlander_simple_car_set_10")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) i3});
                        break;
                    }
                    break;
                case -1144939716:
                    if (titleSrn.equals("outlander_simple_car_set_11")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) i3});
                        break;
                    }
                    break;
                case -1144939715:
                    if (titleSrn.equals("outlander_simple_car_set_12")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 14, (byte) i3});
                        break;
                    }
                    break;
                case -1144939710:
                    if (titleSrn.equals("outlander_simple_car_set_17")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, (byte) i3});
                        break;
                    }
                    break;
                case 1750604168:
                    if (titleSrn.equals("_283_choose")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -18, (byte) i3, 0});
                        MsgMgr msgMgr = this$0.mMsgMgr;

                        msgMgr.updateSettingItem("_283_choose", Integer.valueOf(i3));
                        SharePreUtil.setIntValue(context, MsgMgr.SHARE_317_LEFT_RIGHT_HAND, i3);
                        break;
                    }
                    break;
                case 1902729116:
                    if (titleSrn.equals("outlander_simple_car_set_8")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 10, (byte) i3});
                        break;
                    }
                    break;
                case 1902729117:
                    if (titleSrn.equals("outlander_simple_car_set_9")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 11, (byte) i3});
                        break;
                    }
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-5$lambda-2, reason: not valid java name */
    public static final void m523lambda5$lambda2(SettingPageUiSet settingPageUiSet, int i, int i2, int i3) {
        if (Intrinsics.areEqual(settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn(), "amplifier_switch")) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 9, (byte) i3});
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-5$lambda-3, reason: not valid java name */
    public static final void m524lambda5$lambda3(SettingPageUiSet settingPageUiSet, int i, int i2, int i3) {
        if (Intrinsics.areEqual(settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn(), "_103_punch")) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 7, (byte) (i3 + 3 + 2)});
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
    /* renamed from: lambda-5$lambda-4, reason: not valid java name */
    public static final void m525lambda5$lambda4(SettingPageUiSet settingPageUiSet, int i, int i2) {
        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        if (titleSrn != null) {
            switch (titleSrn.hashCode()) {
                case -1144939713:
                    if (titleSrn.equals("outlander_simple_car_set_14")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 16, 0});
                        break;
                    }
                    break;
                case -1144939712:
                    if (titleSrn.equals("outlander_simple_car_set_15")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 17, 0});
                        break;
                    }
                    break;
                case -1144939711:
                    if (titleSrn.equals("outlander_simple_car_set_16")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 18, 0});
                        break;
                    }
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-11$lambda-10$lambda-6, reason: not valid java name */
    public static final void m518lambda11$lambda10$lambda6(UiMgr this$0, FrontArea frontArea, int i) {

        String str = frontArea.getLineBtnAction()[0][i];

        this$0.sendAirCommand(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-11$lambda-10$lambda-7, reason: not valid java name */
    public static final void m519lambda11$lambda10$lambda7(UiMgr this$0, FrontArea frontArea, int i) {

        String str = frontArea.getLineBtnAction()[1][i];

        this$0.sendAirCommand(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-11$lambda-10$lambda-8, reason: not valid java name */
    public static final void m520lambda11$lambda10$lambda8(UiMgr this$0, FrontArea frontArea, int i) {

        String str = frontArea.getLineBtnAction()[2][i];

        this$0.sendAirCommand(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-11$lambda-10$lambda-9, reason: not valid java name */
    public static final void m521lambda11$lambda10$lambda9(UiMgr this$0, FrontArea frontArea, int i) {

        String str = frontArea.getLineBtnAction()[3][i];

        this$0.sendAirCommand(str);
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
    private final void sendAirCommand(String action) {
        switch (action.hashCode()) {
            case -631663038:
                if (action.equals("rear_defog")) {
                    sendAirCommand(4);
                    break;
                }
                break;
            case -597744666:
                if (action.equals("blow_positive")) {
                    sendAirCommand(17);
                    break;
                }
                break;
            case -246396018:
                if (action.equals(AirBtnAction.MAX_FRONT)) {
                    sendAirCommand(38);
                    break;
                }
                break;
            case 3106:
                if (action.equals("ac")) {
                    sendAirCommand(16);
                    break;
                }
                break;
            case 3005871:
                if (action.equals("auto")) {
                    sendAirCommand(2);
                    break;
                }
                break;
            case 3496356:
                if (action.equals(AirBtnAction.REAR)) {
                    sendAirCommand(37);
                    break;
                }
                break;
            case 3545755:
                if (action.equals("sync")) {
                    sendAirCommand(8);
                    break;
                }
                break;
            case 106858757:
                if (action.equals("power")) {
                    sendAirCommand(1);
                    break;
                }
                break;
            case 756225563:
                if (action.equals("in_out_cycle")) {
                    sendAirCommand(35);
                    break;
                }
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendAirCommand(int event) {
        CanbusMsgSender.sendMsg(new byte[]{22, 51, (byte) event, (byte) getAdditionalParam(), 0, 0});
    }

    private final int getAdditionalParam() {
        int i = this.mAirAddition;
        if (i == 34) {
            this.mAirAddition = 204;
        } else if (i == 204) {
            this.mAirAddition = 34;
        }
        return this.mAirAddition;
    }
}
