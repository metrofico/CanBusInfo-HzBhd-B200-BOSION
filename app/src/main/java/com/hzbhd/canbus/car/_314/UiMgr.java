package com.hzbhd.canbus.car._314;

import android.content.Context;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.bean.RearArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AirBtnAction;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.PanelKeyPageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.InitUtilsKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;




public final class UiMgr extends AbstractUiMgr {
    private final AirPageUiSet mAirPageUiSet;
    private final AmplifierHandleListener mAmplifierHandleListener;
    private final AmplifierPageUiSet mAmplifierPageUiSet;
    private final Context mContext;
    private final OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
    private final ParkPageUiSet mParkPageUiSet;
    private final SettingHandleListener mSettingHandleListener;
    private final SettingPageUiSet mSettingPageUiSet;

    public UiMgr(Context context) {

        SettingPageUiSet settingUiSet = getSettingUiSet(context);

        this.mSettingPageUiSet = settingUiSet;
        AirPageUiSet airUiSet = getAirUiSet(context);

        this.mAirPageUiSet = airUiSet;
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        this.mAmplifierPageUiSet = amplifierPageUiSet;
        SettingHandleListener settingHandleListener = new SettingHandleListener(this, settingUiSet);
        this.mSettingHandleListener = settingHandleListener;
        AmplifierHandleListener amplifierHandleListener = new AmplifierHandleListener();
        this.mAmplifierHandleListener = amplifierHandleListener;
        ParkPageUiSet parkPageUiSet = getParkPageUiSet(context);
        this.mParkPageUiSet = parkPageUiSet;
        OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(context);
        this.mOriginalCarDevicePageUiSet = originalCarDevicePageUiSet;
        this.mContext = context;
        parkPageUiSet.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._314.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public final void onClickItem(int i) {
                UiMgr.m501lambda1$lambda0(i);
            }
        });
        final FrontArea frontArea = airUiSet.getFrontArea();
        frontArea.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._314.UiMgr$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m507lambda12$lambda6$lambda2(this.f$0, frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._314.UiMgr$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m508lambda12$lambda6$lambda3(this.f$0, frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._314.UiMgr$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m509lambda12$lambda6$lambda4(this.f$0, frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._314.UiMgr$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m510lambda12$lambda6$lambda5(this.f$0, frontArea, i);
            }
        }});
        frontArea.setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._314.UiMgr$2$1$5
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                this.this$0.sendAirConditionerCmd(12);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                this.this$0.sendAirConditionerCmd(11);
            }
        });
        frontArea.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._314.UiMgr$2$1$6
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                this.this$0.sendAirConditionerCmd(13);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                this.this$0.sendAirConditionerCmd(14);
            }
        }, null, new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._314.UiMgr$2$1$7
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                this.this$0.sendAirConditionerCmd(15);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                this.this$0.sendAirConditionerCmd(16);
            }
        }});
        frontArea.setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._314.UiMgr$2$1$8
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                this.this$0.sendAirConditionerCmd(17);
            }
        }, new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._314.UiMgr$2$1$9
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                this.this$0.sendAirConditionerCmd(18);
            }
        }, new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._314.UiMgr$2$1$10
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                this.this$0.sendAirConditionerCmd(23);
            }
        }, new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._314.UiMgr$2$1$11
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                this.this$0.sendAirConditionerCmd(24);
            }
        }});
        frontArea.setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._314.UiMgr$2$1$12
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                this.this$0.sendAirConditionerCmd(21);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
                this.this$0.sendAirConditionerCmd(36);
            }
        });
        final RearArea rearArea = airUiSet.getRearArea();
        rearArea.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._314.UiMgr$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m504lambda12$lambda11$lambda7(this.f$0, rearArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._314.UiMgr$$ExternalSyntheticLambda6
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m505lambda12$lambda11$lambda8(this.f$0, rearArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._314.UiMgr$$ExternalSyntheticLambda7
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m506lambda12$lambda11$lambda9(this.f$0, rearArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._314.UiMgr$$ExternalSyntheticLambda8
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m503lambda12$lambda11$lambda10(this.f$0, rearArea, i);
            }
        }});
        rearArea.setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._314.UiMgr$2$2$5
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                this.this$0.sendAirConditionerCmd(43);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                this.this$0.sendAirConditionerCmd(42);
            }
        });
        rearArea.setTempSetViewOnUpDownClickListeners(new UiMgr$2$2$6[]{null, new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._314.UiMgr$2$2$6
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                this.this$0.sendAirConditionerCmd(32);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                this.this$0.sendAirConditionerCmd(33);
            }
        }, null});
        rearArea.setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._314.UiMgr$2$2$7
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                this.this$0.sendAirConditionerCmd(62);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
                this.this$0.sendAirConditionerCmd(62);
            }
        });
        settingUiSet.setOnSettingItemSelectListener(settingHandleListener);
        settingUiSet.setOnSettingItemSwitchListener(settingHandleListener);
        settingUiSet.setOnSettingItemSeekbarSelectListener(settingHandleListener);
        settingUiSet.setOnSettingItemClickListener(settingHandleListener);
        amplifierPageUiSet.setOnAmplifierPositionListener(amplifierHandleListener);
        amplifierPageUiSet.setOnAmplifierSeekBarListener(amplifierHandleListener);
        originalCarDevicePageUiSet.setOnOriginalBottomBtnClickListeners(new OnOriginalBottomBtnClickListener() { // from class: com.hzbhd.canbus.car._314.UiMgr$$ExternalSyntheticLambda9
            @Override // com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener
            public final void onClickBottomBtnItem(int i) {
                UiMgr.m511lambda16$lambda15(i);
            }
        });
        final PanelKeyPageUiSet panelKeyPageUiSet = getPanelKeyPageUiSet(context);
        panelKeyPageUiSet.setOnPanelKeyPositionListener(new OnPanelKeyPositionListener() { // from class: com.hzbhd.canbus.car._314.UiMgr$6$1
            public final void send(int d0) {
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new UiMgr$6$1$send$1(d0, null), 3, null);
            }

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
                    switch (iHashCode) {
                        case -592252941:
                            if (str.equals("S314_x2A_10")) {
                                send(23);
                                break;
                            }
                            break;
                        case -592252940:
                            if (str.equals("S314_x2A_11")) {
                                send(24);
                                break;
                            }
                            break;
                        case -592252939:
                            if (str.equals("S314_x2A_12")) {
                                send(25);
                                break;
                            }
                            break;
                        case -592252938:
                            if (str.equals("S314_x2A_13")) {
                                send(26);
                                break;
                            }
                            break;
                        case -592252937:
                            if (str.equals("S314_x2A_14")) {
                                send(27);
                                break;
                            }
                            break;
                        case -592252936:
                            if (str.equals("S314_x2A_15")) {
                                send(28);
                                break;
                            }
                            break;
                        case -592252935:
                            if (str.equals("S314_x2A_16")) {
                                send(29);
                                break;
                            }
                            break;
                        case -592252934:
                            if (str.equals("S314_x2A_17")) {
                                send(30);
                                break;
                            }
                            break;
                        case -592252933:
                            if (str.equals("S314_x2A_18")) {
                                send(36);
                                break;
                            }
                            break;
                        case -592252932:
                            if (str.equals("S314_x2A_19")) {
                                send(49);
                                break;
                            }
                            break;
                        default:
                            switch (iHashCode) {
                                case -592252910:
                                    if (str.equals("S314_x2A_20")) {
                                        send(51);
                                        break;
                                    }
                                    break;
                                case -592252909:
                                    if (str.equals("S314_x2A_21")) {
                                        send(69);
                                        break;
                                    }
                                    break;
                                case -592252908:
                                    if (str.equals("S314_x2A_22")) {
                                        send(70);
                                        break;
                                    }
                                    break;
                                case -592252907:
                                    if (str.equals("S314_x2A_23")) {
                                        send(77);
                                        break;
                                    }
                                    break;
                                case -592252906:
                                    if (str.equals("S314_x2A_24")) {
                                        send(78);
                                        break;
                                    }
                                    break;
                                case -592252905:
                                    if (str.equals("S314_x2A_25")) {
                                        send(86);
                                        break;
                                    }
                                    break;
                                case -592252904:
                                    if (str.equals("S314_x2A_26")) {
                                        send(87);
                                        break;
                                    }
                                    break;
                                case -592252903:
                                    if (str.equals("S314_x2A_27")) {
                                        send(88);
                                        break;
                                    }
                                    break;
                                default:
                                    switch (iHashCode) {
                                        case 119442397:
                                            if (str.equals("S314_x2A_1")) {
                                                send(1);
                                                break;
                                            }
                                            break;
                                        case 119442398:
                                            if (str.equals("S314_x2A_2")) {
                                                send(6);
                                                break;
                                            }
                                            break;
                                        case 119442399:
                                            if (str.equals("S314_x2A_3")) {
                                                send(16);
                                                break;
                                            }
                                            break;
                                        case 119442400:
                                            if (str.equals("S314_x2A_4")) {
                                                send(17);
                                                break;
                                            }
                                            break;
                                        case 119442401:
                                            if (str.equals("S314_x2A_5")) {
                                                send(18);
                                                break;
                                            }
                                            break;
                                        case 119442402:
                                            if (str.equals("S314_x2A_6")) {
                                                send(19);
                                                break;
                                            }
                                            break;
                                        case 119442403:
                                            if (str.equals("S314_x2A_7")) {
                                                send(20);
                                                break;
                                            }
                                            break;
                                        case 119442404:
                                            if (str.equals("S314_x2A_8")) {
                                                send(21);
                                                break;
                                            }
                                            break;
                                        case 119442405:
                                            if (str.equals("S314_x2A_9")) {
                                                send(22);
                                                break;
                                            }
                                            break;
                                    }
                            }
                    }
                }
            }
        });
    }

    /* renamed from: lambda-1$sendPanoramaCmd, reason: not valid java name */
    private static final void m502lambda1$sendPanoramaCmd(int i) {
        byte b = (byte) i;
        CanbusMsgSender.sendMsg(new byte[]{22, -3, b, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, -3, b, 0});
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-1$lambda-0, reason: not valid java name */
    public static final void m501lambda1$lambda0(int i) {
        switch (i) {
            case 0:
                m502lambda1$sendPanoramaCmd(1);
                break;
            case 1:
                m502lambda1$sendPanoramaCmd(17);
                break;
            case 2:
                m502lambda1$sendPanoramaCmd(20);
                break;
            case 3:
                m502lambda1$sendPanoramaCmd(21);
                break;
            case 4:
                m502lambda1$sendPanoramaCmd(22);
                break;
            case 5:
                m502lambda1$sendPanoramaCmd(24);
                break;
            case 6:
                m502lambda1$sendPanoramaCmd(25);
                break;
            case 7:
                m502lambda1$sendPanoramaCmd(26);
                break;
            case 8:
                m502lambda1$sendPanoramaCmd(27);
                break;
            case 9:
                m502lambda1$sendPanoramaCmd(28);
                break;
            case 10:
                m502lambda1$sendPanoramaCmd(29);
                break;
            case 11:
                m502lambda1$sendPanoramaCmd(30);
                break;
            case 12:
                m502lambda1$sendPanoramaCmd(31);
                break;
            case 13:
                m502lambda1$sendPanoramaCmd(32);
                break;
            case 14:
                m502lambda1$sendPanoramaCmd(33);
                break;
            case 15:
                m502lambda1$sendPanoramaCmd(34);
                break;
            case 16:
                m502lambda1$sendPanoramaCmd(35);
                break;
            case 17:
                m502lambda1$sendPanoramaCmd(36);
                break;
            case 18:
                m502lambda1$sendPanoramaCmd(37);
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-12$lambda-6$lambda-2, reason: not valid java name */
    public static final void m507lambda12$lambda6$lambda2(UiMgr this$0, FrontArea frontArea, int i) {

        String str = frontArea.getLineBtnAction()[0][i];

        this$0.selectAirConditionerBtn(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-12$lambda-6$lambda-3, reason: not valid java name */
    public static final void m508lambda12$lambda6$lambda3(UiMgr this$0, FrontArea frontArea, int i) {

        String str = frontArea.getLineBtnAction()[1][i];

        this$0.selectAirConditionerBtn(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-12$lambda-6$lambda-4, reason: not valid java name */
    public static final void m509lambda12$lambda6$lambda4(UiMgr this$0, FrontArea frontArea, int i) {

        String str = frontArea.getLineBtnAction()[2][i];

        this$0.selectAirConditionerBtn(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-12$lambda-6$lambda-5, reason: not valid java name */
    public static final void m510lambda12$lambda6$lambda5(UiMgr this$0, FrontArea frontArea, int i) {

        String str = frontArea.getLineBtnAction()[3][i];

        this$0.selectAirConditionerBtn(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-12$lambda-11$lambda-7, reason: not valid java name */
    public static final void m504lambda12$lambda11$lambda7(UiMgr this$0, RearArea rearArea, int i) {

        String str = rearArea.getLineBtnAction()[0][i];

        this$0.selectAirConditionerBtn(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-12$lambda-11$lambda-8, reason: not valid java name */
    public static final void m505lambda12$lambda11$lambda8(UiMgr this$0, RearArea rearArea, int i) {

        String str = rearArea.getLineBtnAction()[1][i];

        this$0.selectAirConditionerBtn(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-12$lambda-11$lambda-9, reason: not valid java name */
    public static final void m506lambda12$lambda11$lambda9(UiMgr this$0, RearArea rearArea, int i) {

        String str = rearArea.getLineBtnAction()[2][i];

        this$0.selectAirConditionerBtn(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-12$lambda-11$lambda-10, reason: not valid java name */
    public static final void m503lambda12$lambda11$lambda10(UiMgr this$0, RearArea rearArea, int i) {

        String str = rearArea.getLineBtnAction()[3][i];

        this$0.selectAirConditionerBtn(str);
    }

    /* renamed from: lambda-16$send, reason: not valid java name */
    private static final void m512lambda16$send(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -84, (byte) i, (byte) i2});
    }

    /* renamed from: lambda-16$send$default, reason: not valid java name */
    static /* synthetic */ void m513lambda16$send$default(int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 0;
        }
        m512lambda16$send(i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-16$lambda-15, reason: not valid java name */
    public static final void m511lambda16$lambda15(int i) {
        if (i == 0) {
            m513lambda16$send$default(4, 0, 2, null);
            return;
        }
        if (i == 1) {
            m512lambda16$send(1, 0);
        } else if (i == 2) {
            m512lambda16$send(1, 1);
        } else {
            if (i != 3) {
                return;
            }
            m513lambda16$send$default(5, 0, 2, null);
        }
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
    private final void selectAirConditionerBtn(String btn) {
        switch (btn.hashCode()) {
            case -1470045433:
                if (btn.equals("front_defog")) {
                    sendAirConditionerCmd(5);
                    break;
                }
                break;
            case -713186454:
                if (btn.equals(AirBtnAction.REAR_AUTO)) {
                    sendAirConditionerCmd(58);
                    break;
                }
                break;
            case -712865050:
                if (btn.equals(AirBtnAction.REAR_LOCK)) {
                    sendAirConditionerCmd(34);
                    break;
                }
                break;
            case -631663038:
                if (btn.equals("rear_defog")) {
                    sendAirConditionerCmd(6);
                    break;
                }
                break;
            case -620266838:
                if (btn.equals(AirBtnAction.REAR_POWER)) {
                    sendAirConditionerCmd(46);
                    break;
                }
                break;
            case 3106:
                if (btn.equals("ac")) {
                    sendAirConditionerCmd(2);
                    break;
                }
                break;
            case 3005871:
                if (btn.equals("auto")) {
                    sendAirConditionerCmd(4);
                    break;
                }
                break;
            case 3094652:
                if (btn.equals("dual")) {
                    sendAirConditionerCmd(41);
                    break;
                }
                break;
            case 99489345:
                if (btn.equals(AirBtnAction.IN_OUT_AUTO_CYCLE)) {
                    sendAirConditionerCmd(7);
                    break;
                }
                break;
            case 106858757:
                if (btn.equals("power")) {
                    sendAirConditionerCmd(1);
                    break;
                }
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendAirConditionerCmd(int d0) {
        byte b = (byte) d0;
        CanbusMsgSender.sendMsg(new byte[]{22, 61, b, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, 61, b, 0});
    }

    /* compiled from: UiMgr.kt */
    
    public final class SettingHandleListener implements OnSettingItemSelectListener, OnSettingItemSwitchListener, OnSettingItemSeekbarSelectListener, OnSettingItemClickListener {
        private final SettingPageUiSet mSettingPageUiSet;
        final /* synthetic */ UiMgr this$0;

        public SettingHandleListener(UiMgr uiMgr, SettingPageUiSet mSettingPageUiSet) {

            this.this$0 = uiMgr;
            this.mSettingPageUiSet = mSettingPageUiSet;
        }

        public final SettingPageUiSet getMSettingPageUiSet() {
            return this.mSettingPageUiSet;
        }

        @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
        public void onClickItem(int leftPos, int rightPos) {
            selectSettingsBtn$default(this, leftPos, rightPos, null, 4, null);
        }

        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int leftPos, int rightPos, int progressORvalue) {
            selectSettingsBtn(leftPos, rightPos, Integer.valueOf(progressORvalue));
        }

        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener
        public void onSwitch(int leftPos, int rightPos, int value) {
            selectSettingsBtn(leftPos, rightPos, Integer.valueOf(value));
        }

        static /* synthetic */ void selectSettingsBtn$default(SettingHandleListener settingHandleListener, int i, int i2, Integer num, int i3, Object obj) {
            if ((i3 & 4) != 0) {
                num = null;
            }
            settingHandleListener.selectSettingsBtn(i, i2, num);
        }

        private static final void selectSettingsBtn$sendOtherData(int i, int i2) {
            CanbusMsgSender.sendMsg(new byte[]{22, 110, (byte) i, (byte) i2, -1, -1});
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
        java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
        	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
        	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
         */
        private final void selectSettingsBtn(int leftPos, int rightPos, Integer param) {
            if (param == null) {
                if (Intrinsics.areEqual(this.mSettingPageUiSet.getList().get(leftPos).getItemList().get(rightPos).getTitleSrn(), "D314_Other_18")) {
                    selectSettingsBtn$sendOtherData(1, 0);
                    Toast.makeText(this.this$0.mContext, "Success!", 0).show();
                }
                return;
            }
            String titleSrn = this.mSettingPageUiSet.getList().get(leftPos).getItemList().get(rightPos).getTitleSrn();
            if (titleSrn != null) {
                int iHashCode = titleSrn.hashCode();
                switch (iHashCode) {
                    case -1726079825:
                        if (titleSrn.equals("S314_setting1_1")) {
                            sendSettingsCmd(4, param.intValue());
                            break;
                        }
                        break;
                    case -1726079824:
                        if (titleSrn.equals("S314_setting1_2")) {
                            sendSettingsCmd(5, param.intValue());
                            break;
                        }
                        break;
                    case -1726079823:
                        if (titleSrn.equals("S314_setting1_3")) {
                            sendSettingsCmd(6, param.intValue());
                            break;
                        }
                        break;
                    case -1726079822:
                        if (titleSrn.equals("S314_setting1_4")) {
                            sendSettingsCmd(7, param.intValue());
                            break;
                        }
                        break;
                    default:
                        switch (iHashCode) {
                            case -1726078864:
                                if (titleSrn.equals("S314_setting2_1")) {
                                    sendSettingsCmd(8, param.intValue());
                                    break;
                                }
                                break;
                            case -1726078863:
                                if (titleSrn.equals("S314_setting2_2")) {
                                    sendSettingsCmd(10, param.intValue());
                                    break;
                                }
                                break;
                            case -1726078862:
                                if (titleSrn.equals("S314_setting2_3")) {
                                    sendSettingsCmd(11, param.intValue());
                                    break;
                                }
                                break;
                            case -1726078861:
                                if (titleSrn.equals("S314_setting2_4")) {
                                    sendSettingsCmd(13, param.intValue());
                                    break;
                                }
                                break;
                            case -1726078860:
                                if (titleSrn.equals("S314_setting2_5")) {
                                    sendSettingsCmd(14, param.intValue());
                                    break;
                                }
                                break;
                            case -1726078859:
                                if (titleSrn.equals("S314_setting2_6")) {
                                    sendSettingsCmd(15, param.intValue());
                                    break;
                                }
                                break;
                            case -1726078858:
                                if (titleSrn.equals("S314_setting2_7")) {
                                    sendSettingsCmd(16, param.intValue());
                                    break;
                                }
                                break;
                            default:
                                switch (iHashCode) {
                                    case -1726077902:
                                        if (titleSrn.equals("S314_setting3_2")) {
                                            sendSettingsCmd(22, param.intValue());
                                            break;
                                        }
                                        break;
                                    case -1726077901:
                                        if (titleSrn.equals("S314_setting3_3")) {
                                            sendSettingsCmd(23, param.intValue());
                                            break;
                                        }
                                        break;
                                    case -1726077900:
                                        if (titleSrn.equals("S314_setting3_4")) {
                                            sendSettingsCmd(24, param.intValue());
                                            break;
                                        }
                                        break;
                                    case -1726077899:
                                        if (titleSrn.equals("S314_setting3_5")) {
                                            sendSettingsCmd(25, param.intValue());
                                            break;
                                        }
                                        break;
                                    case -1726077898:
                                        if (titleSrn.equals("S314_setting3_6")) {
                                            sendSettingsCmd(26, param.intValue());
                                            break;
                                        }
                                        break;
                                    default:
                                        switch (iHashCode) {
                                            case -1726076942:
                                                if (titleSrn.equals("S314_setting4_1")) {
                                                    sendSettingsCmd(27, param.intValue());
                                                    break;
                                                }
                                                break;
                                            case -1726076941:
                                                if (titleSrn.equals("S314_setting4_2")) {
                                                    sendSettingsCmd(28, param.intValue());
                                                    break;
                                                }
                                                break;
                                            case -1726076940:
                                                if (titleSrn.equals("S314_setting4_3")) {
                                                    sendSettingsCmd(29, param.intValue());
                                                    break;
                                                }
                                                break;
                                            case -1726076939:
                                                if (titleSrn.equals("S314_setting4_4")) {
                                                    sendSettingsCmd(30, param.intValue());
                                                    break;
                                                }
                                                break;
                                            case -1726076938:
                                                if (titleSrn.equals("S314_setting4_5")) {
                                                    sendSettingsCmd(31, param.intValue());
                                                    break;
                                                }
                                                break;
                                            case -1726076937:
                                                if (titleSrn.equals("S314_setting4_6")) {
                                                    sendSettingsCmd(33, param.intValue());
                                                    break;
                                                }
                                                break;
                                            default:
                                                switch (iHashCode) {
                                                    case -1726075981:
                                                        if (titleSrn.equals("S314_setting5_1")) {
                                                            sendSettingsCmd(34, param.intValue());
                                                            break;
                                                        }
                                                        break;
                                                    case -1726075980:
                                                        if (titleSrn.equals("S314_setting5_2")) {
                                                            sendSettingsCmd(35, param.intValue());
                                                            break;
                                                        }
                                                        break;
                                                    case -1726075979:
                                                        if (titleSrn.equals("S314_setting5_3")) {
                                                            sendSettingsCmd(36, param.intValue());
                                                            break;
                                                        }
                                                        break;
                                                    case -1726075978:
                                                        if (titleSrn.equals("S314_setting5_4")) {
                                                            sendSettingsCmd(37, param.intValue());
                                                            break;
                                                        }
                                                        break;
                                                    default:
                                                        int i = 17;
                                                        switch (iHashCode) {
                                                            case -984333157:
                                                                if (titleSrn.equals("D314_Other_10")) {
                                                                    selectSettingsBtn$sendOtherData(11, param.intValue());
                                                                    break;
                                                                }
                                                                break;
                                                            case -984333156:
                                                                if (titleSrn.equals("D314_Other_11")) {
                                                                    selectSettingsBtn$sendOtherData(12, param.intValue());
                                                                    break;
                                                                }
                                                                break;
                                                            case -984333155:
                                                                if (titleSrn.equals("D314_Other_12")) {
                                                                    selectSettingsBtn$sendOtherData(13, param.intValue());
                                                                    break;
                                                                }
                                                                break;
                                                            case -984333154:
                                                                if (titleSrn.equals("D314_Other_13")) {
                                                                    selectSettingsBtn$sendOtherData(14, param.intValue());
                                                                    break;
                                                                }
                                                                break;
                                                            case -984333153:
                                                                if (titleSrn.equals("D314_Other_14")) {
                                                                    selectSettingsBtn$sendOtherData(15, param.intValue());
                                                                    break;
                                                                }
                                                                break;
                                                            case -984333152:
                                                                if (titleSrn.equals("D314_Other_15")) {
                                                                    selectSettingsBtn$sendOtherData(16, param.intValue());
                                                                    break;
                                                                }
                                                                break;
                                                            case -984333151:
                                                                if (titleSrn.equals("D314_Other_16")) {
                                                                    selectSettingsBtn$sendOtherData(17, param.intValue());
                                                                    break;
                                                                }
                                                                break;
                                                            case -984333150:
                                                                if (titleSrn.equals("D314_Other_17")) {
                                                                    selectSettingsBtn$sendOtherData(18, param.intValue());
                                                                    break;
                                                                }
                                                                break;
                                                            default:
                                                                switch (iHashCode) {
                                                                    case -832009892:
                                                                        if (titleSrn.equals("S314_radar_1")) {
                                                                            sendSettingsCmd(1, param.intValue());
                                                                            break;
                                                                        }
                                                                        break;
                                                                    case -832009891:
                                                                        if (titleSrn.equals("S314_radar_2")) {
                                                                            sendSettingsCmd(2, param.intValue());
                                                                            break;
                                                                        }
                                                                        break;
                                                                    case -832009890:
                                                                        if (titleSrn.equals("S314_radar_3")) {
                                                                            sendSettingsCmd(3, param.intValue());
                                                                            break;
                                                                        }
                                                                        break;
                                                                    case -832009889:
                                                                        if (titleSrn.equals("S314_radar_4")) {
                                                                            sendSettingsCmd(32, param.intValue());
                                                                            break;
                                                                        }
                                                                        break;
                                                                    default:
                                                                        switch (iHashCode) {
                                                                            case -480066374:
                                                                                if (titleSrn.equals("S314_DSPSettings_1")) {
                                                                                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 10, (byte) param.intValue()});
                                                                                    break;
                                                                                }
                                                                                break;
                                                                            case -480066373:
                                                                                if (titleSrn.equals("S314_DSPSettings_2")) {
                                                                                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 9, (byte) param.intValue()});
                                                                                    break;
                                                                                }
                                                                                break;
                                                                            case -480066372:
                                                                                if (titleSrn.equals("S314_DSPSettings_3")) {
                                                                                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 7, (byte) param.intValue()});
                                                                                    break;
                                                                                }
                                                                                break;
                                                                            default:
                                                                                switch (iHashCode) {
                                                                                    case 94883461:
                                                                                        if (titleSrn.equals("S314_Language_0")) {
                                                                                            byte[] bArr = new byte[4];
                                                                                            bArr[0] = 22;
                                                                                            bArr[1] = -102;
                                                                                            bArr[2] = 1;
                                                                                            int iIntValue = param.intValue();
                                                                                            if (iIntValue == 1) {
                                                                                                i = 1;
                                                                                            } else if (iIntValue == 2) {
                                                                                                i = 2;
                                                                                            } else if (iIntValue == 3) {
                                                                                                i = 3;
                                                                                            } else if (iIntValue != 4) {
                                                                                                i = iIntValue != 5 ? 0 : 18;
                                                                                            }
                                                                                            bArr[3] = (byte) i;
                                                                                            CanbusMsgSender.sendMsg(bArr);
                                                                                            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = InitUtilsKt.getMSettingItemIndex().get("S314_Language_0");
                                                                                            if (itemListBean != null) {
                                                                                                itemListBean.setValue(itemListBean.getValueSrnArray().get(param.intValue()));
                                                                                            }
                                                                                            MsgMgrInterface canMsgMgr = MsgMgrFactory.getCanMsgMgr(this.this$0.mContext);

                                                                                            ((MsgMgr) canMsgMgr).updateSettingsData();
                                                                                            break;
                                                                                        }
                                                                                        break;
                                                                                    case 1076625973:
                                                                                        if (titleSrn.equals("D314_Other_1")) {
                                                                                            selectSettingsBtn$sendOtherData(2, param.intValue());
                                                                                            break;
                                                                                        }
                                                                                        break;
                                                                                    case 1076625974:
                                                                                        if (titleSrn.equals("D314_Other_2")) {
                                                                                            selectSettingsBtn$sendOtherData(5, param.intValue());
                                                                                            break;
                                                                                        }
                                                                                        break;
                                                                                    case 1076625975:
                                                                                        if (titleSrn.equals("D314_Other_3")) {
                                                                                            selectSettingsBtn$sendOtherData(6, param.intValue());
                                                                                            break;
                                                                                        }
                                                                                        break;
                                                                                    case 1076625976:
                                                                                        if (titleSrn.equals("D314_Other_4")) {
                                                                                            selectSettingsBtn$sendOtherData(7, param.intValue());
                                                                                            break;
                                                                                        }
                                                                                        break;
                                                                                    case 1076625977:
                                                                                        if (titleSrn.equals("D314_Other_5")) {
                                                                                            selectSettingsBtn$sendOtherData(3, param.intValue());
                                                                                            break;
                                                                                        }
                                                                                        break;
                                                                                    case 1076625978:
                                                                                        if (titleSrn.equals("D314_Other_6")) {
                                                                                            selectSettingsBtn$sendOtherData(4, param.intValue());
                                                                                            break;
                                                                                        }
                                                                                        break;
                                                                                    case 1076625979:
                                                                                        if (titleSrn.equals("D314_Other_7")) {
                                                                                            selectSettingsBtn$sendOtherData(8, param.intValue());
                                                                                            break;
                                                                                        }
                                                                                        break;
                                                                                    case 1076625980:
                                                                                        if (titleSrn.equals("D314_Other_8")) {
                                                                                            selectSettingsBtn$sendOtherData(9, param.intValue());
                                                                                            break;
                                                                                        }
                                                                                        break;
                                                                                    case 1076625981:
                                                                                        if (titleSrn.equals("D314_Other_9")) {
                                                                                            selectSettingsBtn$sendOtherData(10, param.intValue());
                                                                                            break;
                                                                                        }
                                                                                        break;
                                                                                    case 1729297831:
                                                                                        if (titleSrn.equals("S314_SettingForAir_1")) {
                                                                                            sendAirSettingsData(3, param.intValue());
                                                                                            break;
                                                                                        }
                                                                                        break;
                                                                                    case 1729297832:
                                                                                        if (titleSrn.equals("S314_SettingForAir_2")) {
                                                                                            sendAirSettingsData(11, param.intValue());
                                                                                            break;
                                                                                        }
                                                                                        break;
                                                                                    case 1729297833:
                                                                                        if (titleSrn.equals("S314_SettingForAir_3")) {
                                                                                            sendAirSettingsData(21, param.intValue());
                                                                                            break;
                                                                                        }
                                                                                        break;
                                                                                    case 1729297834:
                                                                                        if (titleSrn.equals("S314_SettingForAir_4")) {
                                                                                            sendAirSettingsData(22, param.intValue());
                                                                                            break;
                                                                                        }
                                                                                        break;
                                                                                    case 1729297835:
                                                                                        if (titleSrn.equals("S314_SettingForAir_5")) {
                                                                                            sendAirSettingsData(23, param.intValue());
                                                                                            break;
                                                                                        }
                                                                                        break;
                                                                                    default:
                                                                                        switch (iHashCode) {
                                                                                            case 746231576:
                                                                                                if (titleSrn.equals("S314_display_1")) {
                                                                                                    sendSettingsCmd(17, param.intValue());
                                                                                                    break;
                                                                                                }
                                                                                                break;
                                                                                            case 746231577:
                                                                                                if (titleSrn.equals("S314_display_2")) {
                                                                                                    sendSettingsCmd(18, param.intValue());
                                                                                                    break;
                                                                                                }
                                                                                                break;
                                                                                            case 746231578:
                                                                                                if (titleSrn.equals("S314_display_3")) {
                                                                                                    sendSettingsCmd(19, param.intValue());
                                                                                                    break;
                                                                                                }
                                                                                                break;
                                                                                            case 746231579:
                                                                                                if (titleSrn.equals("S314_display_4")) {
                                                                                                    sendSettingsCmd(20, param.intValue());
                                                                                                    break;
                                                                                                }
                                                                                                break;
                                                                                        }
                                                                                }
                                                                        }
                                                                }
                                                        }
                                                }
                                        }
                                }
                        }
                }
            }
        }

        private final void sendAirSettingsData(int d0, int d1) {
            CanbusMsgSender.sendMsg(new byte[]{22, 59, (byte) d0, (byte) d1});
        }

        private final void sendSettingsCmd(int cmd, int param) {
            CanbusMsgSender.sendMsg(new byte[]{22, 111, (byte) cmd, (byte) param, -1, -1});
        }
    }

    /* compiled from: UiMgr.kt */
    
    public static final class AmplifierHandleListener implements OnAmplifierPositionListener, OnAmplifierSeekBarListener {

        /* compiled from: UiMgr.kt */
        
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;
            public static final /* synthetic */ int[] $EnumSwitchMapping$1;

            static {
                int[] iArr = new int[AmplifierActivity.AmplifierPosition.values().length];
                iArr[AmplifierActivity.AmplifierPosition.LEFT.ordinal()] = 1;
                iArr[AmplifierActivity.AmplifierPosition.RIGHT.ordinal()] = 2;
                iArr[AmplifierActivity.AmplifierPosition.REAR.ordinal()] = 3;
                iArr[AmplifierActivity.AmplifierPosition.FRONT.ordinal()] = 4;
                $EnumSwitchMapping$0 = iArr;
                int[] iArr2 = new int[AmplifierActivity.AmplifierBand.values().length];
                iArr2[AmplifierActivity.AmplifierBand.VOLUME_Plus.ordinal()] = 1;
                iArr2[AmplifierActivity.AmplifierBand.VOLUME_Min.ordinal()] = 2;
                iArr2[AmplifierActivity.AmplifierBand.BASS_Plus.ordinal()] = 3;
                iArr2[AmplifierActivity.AmplifierBand.BASS_Min.ordinal()] = 4;
                iArr2[AmplifierActivity.AmplifierBand.MIDDLE_Plus.ordinal()] = 5;
                iArr2[AmplifierActivity.AmplifierBand.MIDDLE_Min.ordinal()] = 6;
                iArr2[AmplifierActivity.AmplifierBand.TREBLE_Plus.ordinal()] = 7;
                iArr2[AmplifierActivity.AmplifierBand.TREBLE_Min.ordinal()] = 8;
                $EnumSwitchMapping$1 = iArr2;
            }
        }

        @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
        public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int value) {

            int i = WhenMappings.$EnumSwitchMapping$0[amplifierPosition.ordinal()];
            if (i == 1) {
                sendAmplifierCmd(2, 255);
                return;
            }
            if (i == 2) {
                sendAmplifierCmd(2, 1);
            } else if (i == 3) {
                sendAmplifierCmd(3, 255);
            } else {
                if (i != 4) {
                    return;
                }
                sendAmplifierCmd(3, 1);
            }
        }

        @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
        public void progress(AmplifierActivity.AmplifierBand amplifierBand, int progress) {

            switch (WhenMappings.$EnumSwitchMapping$1[amplifierBand.ordinal()]) {
                case 1:
                    sendAmplifierCmd(1, 1);
                    break;
                case 2:
                    sendAmplifierCmd(1, 255);
                    break;
                case 3:
                    sendAmplifierCmd(4, 1);
                    break;
                case 4:
                    sendAmplifierCmd(4, 255);
                    break;
                case 5:
                    sendAmplifierCmd(5, 1);
                    break;
                case 6:
                    sendAmplifierCmd(5, 255);
                    break;
                case 7:
                    sendAmplifierCmd(6, 1);
                    break;
                case 8:
                    sendAmplifierCmd(6, 255);
                    break;
            }
        }

        private final void sendAmplifierCmd(int cmd, int param) {
            CanbusMsgSender.sendMsg(new byte[]{22, -83, (byte) cmd, (byte) param});
        }
    }
}
