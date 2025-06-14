package com.hzbhd.canbus.car._407;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.bean.RearArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AirBtnAction;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UiMgr.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0005B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0006"}, d2 = {"Lcom/hzbhd/canbus/car/_407/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "SettingHandleListener", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class UiMgr extends AbstractUiMgr {
    public UiMgr(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        Intrinsics.checkNotNullExpressionValue(settingUiSet, "this");
        SettingHandleListener settingHandleListener = new SettingHandleListener(settingUiSet);
        settingUiSet.setOnSettingItemSwitchListener(settingHandleListener);
        settingUiSet.setOnSettingItemSelectListener(settingHandleListener);
        settingUiSet.setOnSettingItemSeekbarSelectListener(settingHandleListener);
        AirPageUiSet airUiSet = getAirUiSet(context);
        final FrontArea frontArea = airUiSet.getFrontArea();
        frontArea.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._407.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m844lambda11$lambda5$lambda1(frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._407.UiMgr$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m845lambda11$lambda5$lambda2(frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._407.UiMgr$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m846lambda11$lambda5$lambda3(frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._407.UiMgr$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m847lambda11$lambda5$lambda4(frontArea, i);
            }
        }});
        frontArea.setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._407.UiMgr$2$1$5
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr.m849lambda11$sendAirConditionerCmd(12);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr.m849lambda11$sendAirConditionerCmd(11);
            }
        });
        frontArea.setTempSetViewOnUpDownClickListeners(new UiMgr$2$1$6[]{new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._407.UiMgr$2$1$6
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                UiMgr.m849lambda11$sendAirConditionerCmd(13);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                UiMgr.m849lambda11$sendAirConditionerCmd(14);
            }
        }, null, null});
        frontArea.setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._407.UiMgr$2$1$7
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                UiMgr.m849lambda11$sendAirConditionerCmd(21);
            }
        });
        final RearArea rearArea = airUiSet.getRearArea();
        rearArea.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._407.UiMgr$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m840lambda11$lambda10$lambda6(rearArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._407.UiMgr$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m841lambda11$lambda10$lambda7(rearArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._407.UiMgr$$ExternalSyntheticLambda6
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m842lambda11$lambda10$lambda8(rearArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._407.UiMgr$$ExternalSyntheticLambda7
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m843lambda11$lambda10$lambda9(rearArea, i);
            }
        }});
        getParkPageUiSet(context).setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._407.UiMgr$$ExternalSyntheticLambda8
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public final void onClickItem(int i) {
                UiMgr.m850lambda13$lambda12(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-11$sendAirConditionerCmd, reason: not valid java name */
    public static final void m849lambda11$sendAirConditionerCmd(int i) {
        byte b = (byte) i;
        CanbusMsgSender.sendMsg(new byte[]{22, 61, b, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, 61, b, 0});
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* renamed from: lambda-11$selectAirConditionerBtn, reason: not valid java name */
    private static final void m848lambda11$selectAirConditionerBtn(String str) {
        switch (str.hashCode()) {
            case -1470045433:
                if (str.equals("front_defog")) {
                    m849lambda11$sendAirConditionerCmd(5);
                    break;
                }
                break;
            case -1423573049:
                if (str.equals(AirBtnAction.AC_MAX)) {
                    m849lambda11$sendAirConditionerCmd(30);
                    break;
                }
                break;
            case -631663038:
                if (str.equals("rear_defog")) {
                    m849lambda11$sendAirConditionerCmd(6);
                    break;
                }
                break;
            case -620266838:
                if (str.equals(AirBtnAction.REAR_POWER)) {
                    m849lambda11$sendAirConditionerCmd(46);
                    break;
                }
                break;
            case 3106:
                if (str.equals("ac")) {
                    m849lambda11$sendAirConditionerCmd(2);
                    break;
                }
                break;
            case 3005871:
                if (str.equals("auto")) {
                    m849lambda11$sendAirConditionerCmd(4);
                    break;
                }
                break;
            case 106858757:
                if (str.equals("power")) {
                    m849lambda11$sendAirConditionerCmd(1);
                    break;
                }
                break;
            case 756225563:
                if (str.equals("in_out_cycle")) {
                    m849lambda11$sendAirConditionerCmd(7);
                    break;
                }
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-11$lambda-5$lambda-1, reason: not valid java name */
    public static final void m844lambda11$lambda5$lambda1(FrontArea frontArea, int i) {
        String str = frontArea.getLineBtnAction()[0][i];
        Intrinsics.checkNotNullExpressionValue(str, "this.lineBtnAction[0][it]");
        m848lambda11$selectAirConditionerBtn(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-11$lambda-5$lambda-2, reason: not valid java name */
    public static final void m845lambda11$lambda5$lambda2(FrontArea frontArea, int i) {
        String str = frontArea.getLineBtnAction()[1][i];
        Intrinsics.checkNotNullExpressionValue(str, "this.lineBtnAction[1][it]");
        m848lambda11$selectAirConditionerBtn(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-11$lambda-5$lambda-3, reason: not valid java name */
    public static final void m846lambda11$lambda5$lambda3(FrontArea frontArea, int i) {
        String str = frontArea.getLineBtnAction()[2][i];
        Intrinsics.checkNotNullExpressionValue(str, "this.lineBtnAction[2][it]");
        m848lambda11$selectAirConditionerBtn(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-11$lambda-5$lambda-4, reason: not valid java name */
    public static final void m847lambda11$lambda5$lambda4(FrontArea frontArea, int i) {
        String str = frontArea.getLineBtnAction()[3][i];
        Intrinsics.checkNotNullExpressionValue(str, "this.lineBtnAction[3][it]");
        m848lambda11$selectAirConditionerBtn(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-11$lambda-10$lambda-6, reason: not valid java name */
    public static final void m840lambda11$lambda10$lambda6(RearArea rearArea, int i) {
        String str = rearArea.getLineBtnAction()[0][i];
        Intrinsics.checkNotNullExpressionValue(str, "this.lineBtnAction[0][it]");
        m848lambda11$selectAirConditionerBtn(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-11$lambda-10$lambda-7, reason: not valid java name */
    public static final void m841lambda11$lambda10$lambda7(RearArea rearArea, int i) {
        String str = rearArea.getLineBtnAction()[1][i];
        Intrinsics.checkNotNullExpressionValue(str, "this.lineBtnAction[1][it]");
        m848lambda11$selectAirConditionerBtn(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-11$lambda-10$lambda-8, reason: not valid java name */
    public static final void m842lambda11$lambda10$lambda8(RearArea rearArea, int i) {
        String str = rearArea.getLineBtnAction()[2][i];
        Intrinsics.checkNotNullExpressionValue(str, "this.lineBtnAction[2][it]");
        m848lambda11$selectAirConditionerBtn(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-11$lambda-10$lambda-9, reason: not valid java name */
    public static final void m843lambda11$lambda10$lambda9(RearArea rearArea, int i) {
        String str = rearArea.getLineBtnAction()[3][i];
        Intrinsics.checkNotNullExpressionValue(str, "this.lineBtnAction[3][it]");
        m848lambda11$selectAirConditionerBtn(str);
    }

    /* renamed from: lambda-13$sendPanoramaCmd, reason: not valid java name */
    private static final void m851lambda13$sendPanoramaCmd(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, 74, 1, (byte) i});
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-13$lambda-12, reason: not valid java name */
    public static final void m850lambda13$lambda12(int i) {
        if (i == 0) {
            m851lambda13$sendPanoramaCmd(0);
        } else if (i == 1) {
            m851lambda13$sendPanoramaCmd(1);
        } else {
            if (i != 2) {
                return;
            }
            m851lambda13$sendPanoramaCmd(2);
        }
    }

    /* compiled from: UiMgr.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0016J \u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\fH\u0016J \u0010\u0011\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\fH\u0002R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/hzbhd/canbus/car/_407/UiMgr$SettingHandleListener;", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSelectListener;", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSwitchListener;", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSeekbarSelectListener;", "mSettingPageUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "(Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;)V", "getMSettingPageUiSet", "()Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "onClickItem", "", "leftPos", "", "rightPos", "progressORvalue", "onSwitch", "value", "selectSettingsBtn", "param", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class SettingHandleListener implements OnSettingItemSelectListener, OnSettingItemSwitchListener, OnSettingItemSeekbarSelectListener {
        private final SettingPageUiSet mSettingPageUiSet;

        public SettingHandleListener(SettingPageUiSet mSettingPageUiSet) {
            Intrinsics.checkNotNullParameter(mSettingPageUiSet, "mSettingPageUiSet");
            this.mSettingPageUiSet = mSettingPageUiSet;
        }

        public final SettingPageUiSet getMSettingPageUiSet() {
            return this.mSettingPageUiSet;
        }

        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int leftPos, int rightPos, int progressORvalue) {
            selectSettingsBtn(leftPos, rightPos, progressORvalue);
        }

        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener
        public void onSwitch(int leftPos, int rightPos, int value) {
            selectSettingsBtn(leftPos, rightPos, value);
        }

        private static final void selectSettingsBtn$sendSettingsCmd(int i, int i2) {
            CanbusMsgSender.sendMsg(new byte[]{22, -116, (byte) i, (byte) i2});
        }

        private static final void selectSettingsBtn$sendAnotherSettingsCmd(int i, int i2) {
            CanbusMsgSender.sendMsg(new byte[]{22, -4, (byte) i, (byte) i2});
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
        java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
        	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
        	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
         */
        private final void selectSettingsBtn(int leftPos, int rightPos, int param) {
            String titleSrn = this.mSettingPageUiSet.getList().get(leftPos).getItemList().get(rightPos).getTitleSrn();
            if (titleSrn != null) {
                int iHashCode = titleSrn.hashCode();
                switch (iHashCode) {
                    case -768171052:
                        if (titleSrn.equals("S407_x78_1")) {
                            selectSettingsBtn$sendSettingsCmd(30, param);
                            break;
                        }
                        break;
                    case -768171051:
                        if (titleSrn.equals("S407_x78_2")) {
                            selectSettingsBtn$sendSettingsCmd(31, param);
                            break;
                        }
                        break;
                    case -768171050:
                        if (titleSrn.equals("S407_x78_3")) {
                            selectSettingsBtn$sendSettingsCmd(32, param);
                            break;
                        }
                        break;
                    case -768171049:
                        if (titleSrn.equals("S407_x78_4")) {
                            selectSettingsBtn$sendSettingsCmd(26, param);
                            break;
                        }
                        break;
                    case -768171048:
                        if (titleSrn.equals("S407_x78_5")) {
                            selectSettingsBtn$sendSettingsCmd(27, param);
                            break;
                        }
                        break;
                    case -768171047:
                        if (titleSrn.equals("S407_x78_6")) {
                            selectSettingsBtn$sendSettingsCmd(28, param);
                            break;
                        }
                        break;
                    case -768171046:
                        if (titleSrn.equals("S407_x78_7")) {
                            selectSettingsBtn$sendSettingsCmd(29, param);
                            break;
                        }
                        break;
                    default:
                        switch (iHashCode) {
                            case -768142222:
                                if (titleSrn.equals("S407_x87_1")) {
                                    selectSettingsBtn$sendSettingsCmd(5, param);
                                    break;
                                }
                                break;
                            case -768142221:
                                if (titleSrn.equals("S407_x87_2")) {
                                    selectSettingsBtn$sendSettingsCmd(4, param);
                                    break;
                                }
                                break;
                            case -768142220:
                                if (titleSrn.equals("S407_x87_3")) {
                                    selectSettingsBtn$sendSettingsCmd(3, param);
                                    break;
                                }
                                break;
                            case -768142219:
                                if (titleSrn.equals("S407_x87_4")) {
                                    selectSettingsBtn$sendSettingsCmd(2, param);
                                    break;
                                }
                                break;
                            case -768142218:
                                if (titleSrn.equals("S407_x87_5")) {
                                    selectSettingsBtn$sendSettingsCmd(1, param);
                                    break;
                                }
                                break;
                            case -768142217:
                                if (titleSrn.equals("S407_x87_6")) {
                                    selectSettingsBtn$sendSettingsCmd(16, param);
                                    break;
                                }
                                break;
                            case -768142216:
                                if (titleSrn.equals("S407_x87_7")) {
                                    selectSettingsBtn$sendSettingsCmd(9, param);
                                    break;
                                }
                                break;
                            case -768142215:
                                if (titleSrn.equals("S407_x87_8")) {
                                    selectSettingsBtn$sendSettingsCmd(8, param);
                                    break;
                                }
                                break;
                            case -768142214:
                                if (titleSrn.equals("S407_x87_9")) {
                                    selectSettingsBtn$sendSettingsCmd(7, param);
                                    break;
                                }
                                break;
                            default:
                                switch (iHashCode) {
                                    case -767753017:
                                        if (titleSrn.equals("S407_xE9_1")) {
                                            selectSettingsBtn$sendAnotherSettingsCmd(1, param);
                                            break;
                                        }
                                        break;
                                    case -767753016:
                                        if (titleSrn.equals("S407_xE9_2")) {
                                            selectSettingsBtn$sendAnotherSettingsCmd(2, param);
                                            break;
                                        }
                                        break;
                                    case -767753015:
                                        if (titleSrn.equals("S407_xE9_3")) {
                                            selectSettingsBtn$sendAnotherSettingsCmd(3, param);
                                            break;
                                        }
                                        break;
                                    default:
                                        switch (iHashCode) {
                                            case 1957394942:
                                                if (titleSrn.equals("S407_x87_10")) {
                                                    selectSettingsBtn$sendSettingsCmd(6, param);
                                                    break;
                                                }
                                                break;
                                            case 1957394943:
                                                if (titleSrn.equals("S407_x87_11")) {
                                                    selectSettingsBtn$sendSettingsCmd(13, param);
                                                    break;
                                                }
                                                break;
                                            case 1957394944:
                                                if (titleSrn.equals("S407_x87_12")) {
                                                    selectSettingsBtn$sendSettingsCmd(11, param);
                                                    break;
                                                }
                                                break;
                                            case 1957394945:
                                                if (titleSrn.equals("S407_x87_13")) {
                                                    selectSettingsBtn$sendSettingsCmd(18, param);
                                                    break;
                                                }
                                                break;
                                            case 1957394946:
                                                if (titleSrn.equals("S407_x87_14")) {
                                                    selectSettingsBtn$sendSettingsCmd(19, param);
                                                    break;
                                                }
                                                break;
                                            case 1957394947:
                                                if (titleSrn.equals("S407_x87_15")) {
                                                    selectSettingsBtn$sendSettingsCmd(20, param);
                                                    break;
                                                }
                                                break;
                                            case 1957394948:
                                                if (titleSrn.equals("S407_x87_16")) {
                                                    selectSettingsBtn$sendSettingsCmd(23, param);
                                                    break;
                                                }
                                                break;
                                            case 1957394949:
                                                if (titleSrn.equals("S407_x87_17")) {
                                                    selectSettingsBtn$sendSettingsCmd(24, param);
                                                    break;
                                                }
                                                break;
                                            case 1957394950:
                                                if (titleSrn.equals("S407_x87_18")) {
                                                    selectSettingsBtn$sendSettingsCmd(15, param);
                                                    break;
                                                }
                                                break;
                                            case 1957394951:
                                                if (titleSrn.equals("S407_x87_19")) {
                                                    selectSettingsBtn$sendSettingsCmd(14, param);
                                                    break;
                                                }
                                                break;
                                            default:
                                                switch (iHashCode) {
                                                    case 1957394973:
                                                        if (titleSrn.equals("S407_x87_20")) {
                                                            selectSettingsBtn$sendSettingsCmd(21, param);
                                                            break;
                                                        }
                                                        break;
                                                    case 1957394974:
                                                        if (titleSrn.equals("S407_x87_21")) {
                                                            selectSettingsBtn$sendSettingsCmd(22, param);
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
