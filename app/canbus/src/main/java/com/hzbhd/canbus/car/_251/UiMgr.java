package com.hzbhd.canbus.car._251;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AirBtnAction;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UiMgr.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0017B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\"\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u0016H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/hzbhd/canbus/car/_251/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mAirPageUiSet", "Lcom/hzbhd/canbus/adapter/bean/AirPageUiSet;", "mParkPageUiSet", "Lcom/hzbhd/canbus/ui_set/ParkPageUiSet;", "mSettingHandleListener", "Lcom/hzbhd/canbus/car/_251/UiMgr$SettingHandleListener;", "mSettingPageUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "selectAirConditionerBtn", "", "btn", "", "sendAirConditionerCmd", "dIndex", "", "bIndex", NotificationCompat.CATEGORY_STATUS, "", "SettingHandleListener", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class UiMgr extends AbstractUiMgr {
    private final AirPageUiSet mAirPageUiSet;
    private final ParkPageUiSet mParkPageUiSet;
    private final SettingHandleListener mSettingHandleListener;
    private final SettingPageUiSet mSettingPageUiSet;

    public UiMgr(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        Intrinsics.checkNotNullExpressionValue(settingUiSet, "getSettingUiSet(context)");
        this.mSettingPageUiSet = settingUiSet;
        AirPageUiSet airUiSet = getAirUiSet(context);
        Intrinsics.checkNotNullExpressionValue(airUiSet, "getAirUiSet(context)");
        this.mAirPageUiSet = airUiSet;
        ParkPageUiSet parkPageUiSet = getParkPageUiSet(context);
        Intrinsics.checkNotNullExpressionValue(parkPageUiSet, "getParkPageUiSet(context)");
        this.mParkPageUiSet = parkPageUiSet;
        SettingHandleListener settingHandleListener = new SettingHandleListener(settingUiSet);
        this.mSettingHandleListener = settingHandleListener;
        airUiSet.setOnAirPageStatusListener(new OnAirPageStatusListener() { // from class: com.hzbhd.canbus.car._251.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener
            public final void onStatusChange(int i) {
                UiMgr.m331lambda6$lambda0(i);
            }
        });
        final FrontArea frontArea = airUiSet.getFrontArea();
        frontArea.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._251.UiMgr$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m332lambda6$lambda5$lambda1(this.f$0, frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._251.UiMgr$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m333lambda6$lambda5$lambda2(this.f$0, frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._251.UiMgr$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m334lambda6$lambda5$lambda3(this.f$0, frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._251.UiMgr$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m335lambda6$lambda5$lambda4(this.f$0, frontArea, i);
            }
        }});
        frontArea.setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._251.UiMgr$1$2$5
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr.sendAirConditionerCmd$default(this.this$0, 1, 0, false, 4, null);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr.sendAirConditionerCmd$default(this.this$0, 1, 1, false, 4, null);
            }
        });
        frontArea.setTempSetViewOnUpDownClickListeners(new UiMgr$1$2$6[]{new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._251.UiMgr$1$2$6
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                UiMgr.sendAirConditionerCmd$default(this.this$0, 3, 1, false, 4, null);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                UiMgr.sendAirConditionerCmd$default(this.this$0, 3, 0, false, 4, null);
            }
        }, null, null});
        frontArea.setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._251.UiMgr$1$2$7
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                UiMgr.sendAirConditionerCmd$default(this.this$0, 0, 6, false, 4, null);
            }
        });
        settingUiSet.setOnSettingItemSelectListener(settingHandleListener);
        settingUiSet.setOnSettingItemSwitchListener(settingHandleListener);
        settingUiSet.setOnSettingItemSeekbarSelectListener(settingHandleListener);
        settingUiSet.setOnSettingPageStatusListener(new OnSettingPageStatusListener() { // from class: com.hzbhd.canbus.car._251.UiMgr$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.interfaces.OnSettingPageStatusListener
            public final void onStatusChange(int i) {
                UiMgr.m336lambda8$lambda7(i);
            }
        });
        parkPageUiSet.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._251.UiMgr$$ExternalSyntheticLambda6
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public final void onClickItem(int i) {
                UiMgr.m329lambda10$lambda9(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-6$lambda-0, reason: not valid java name */
    public static final void m331lambda6$lambda0(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 35});
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-6$lambda-5$lambda-1, reason: not valid java name */
    public static final void m332lambda6$lambda5$lambda1(UiMgr this$0, FrontArea frontArea, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str = frontArea.getLineBtnAction()[0][i];
        Intrinsics.checkNotNullExpressionValue(str, "this.lineBtnAction[0][it]");
        this$0.selectAirConditionerBtn(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-6$lambda-5$lambda-2, reason: not valid java name */
    public static final void m333lambda6$lambda5$lambda2(UiMgr this$0, FrontArea frontArea, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str = frontArea.getLineBtnAction()[1][i];
        Intrinsics.checkNotNullExpressionValue(str, "this.lineBtnAction[1][it]");
        this$0.selectAirConditionerBtn(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-6$lambda-5$lambda-3, reason: not valid java name */
    public static final void m334lambda6$lambda5$lambda3(UiMgr this$0, FrontArea frontArea, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str = frontArea.getLineBtnAction()[2][i];
        Intrinsics.checkNotNullExpressionValue(str, "this.lineBtnAction[2][it]");
        this$0.selectAirConditionerBtn(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-6$lambda-5$lambda-4, reason: not valid java name */
    public static final void m335lambda6$lambda5$lambda4(UiMgr this$0, FrontArea frontArea, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str = frontArea.getLineBtnAction()[3][i];
        Intrinsics.checkNotNullExpressionValue(str, "this.lineBtnAction[3][it]");
        this$0.selectAirConditionerBtn(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-8$lambda-7, reason: not valid java name */
    public static final void m336lambda8$lambda7(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 41});
    }

    /* renamed from: lambda-10$sendPanoramaCmd, reason: not valid java name */
    private static final void m330lambda10$sendPanoramaCmd(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -121, (byte) i});
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-10$lambda-9, reason: not valid java name */
    public static final void m329lambda10$lambda9(int i) {
        if (i == 0) {
            m330lambda10$sendPanoramaCmd(5);
            return;
        }
        if (i == 1) {
            m330lambda10$sendPanoramaCmd(6);
        } else if (i == 2) {
            m330lambda10$sendPanoramaCmd(7);
        } else {
            if (i != 3) {
                return;
            }
            m330lambda10$sendPanoramaCmd(8);
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
                    sendAirConditionerCmd(0, 4, GeneralAirData.front_defog);
                    break;
                }
                break;
            case -1423573049:
                if (btn.equals(AirBtnAction.AC_MAX)) {
                    sendAirConditionerCmd(0, 0, GeneralAirData.ac_max);
                    break;
                }
                break;
            case -631663038:
                if (btn.equals("rear_defog")) {
                    sendAirConditionerCmd(1, 2, GeneralAirData.rear_defog);
                    break;
                }
                break;
            case 3106:
                if (btn.equals("ac")) {
                    sendAirConditionerCmd(0, 1, GeneralAirData.ac);
                    break;
                }
                break;
            case 3005871:
                if (btn.equals("auto")) {
                    sendAirConditionerCmd(0, 5, GeneralAirData.auto);
                    break;
                }
                break;
            case 3496356:
                if (btn.equals(AirBtnAction.REAR)) {
                    sendAirConditionerCmd(5, 6, GeneralAirData.rear);
                    break;
                }
                break;
            case 106858757:
                if (btn.equals("power")) {
                    sendAirConditionerCmd(0, 7, GeneralAirData.power);
                    break;
                }
                break;
            case 756225563:
                if (btn.equals("in_out_cycle")) {
                    sendAirConditionerCmd(2, 0, GeneralAirData.in_out_cycle);
                    break;
                }
                break;
        }
    }

    static /* synthetic */ void sendAirConditionerCmd$default(UiMgr uiMgr, int i, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            z = false;
        }
        uiMgr.sendAirConditionerCmd(i, i2, z);
    }

    private final void sendAirConditionerCmd(int dIndex, int bIndex, boolean status) {
        byte[] bArr = {0, 0, 0, 0, 0, 0};
        if (status) {
            bArr[dIndex] = (byte) DataHandleUtils.setIntByteWithBit(bArr[dIndex], bIndex, true);
            CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -57}, bArr));
        } else {
            bArr[dIndex] = (byte) DataHandleUtils.setIntByteWithBit(bArr[dIndex], bIndex, true);
            CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -57}, bArr));
        }
    }

    /* compiled from: UiMgr.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0016J \u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\fH\u0016J \u0010\u0011\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\fH\u0002J\u0018\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\fH\u0002R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0016"}, d2 = {"Lcom/hzbhd/canbus/car/_251/UiMgr$SettingHandleListener;", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSelectListener;", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSwitchListener;", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSeekbarSelectListener;", "mSettingPageUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "(Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;)V", "getMSettingPageUiSet", "()Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "onClickItem", "", "leftPos", "", "rightPos", "progressORvalue", "onSwitch", "value", "selectSettingsBtn", "param", "sendSettingsCmd", "d0", "d1", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
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
                switch (titleSrn.hashCode()) {
                    case 856192894:
                        if (titleSrn.equals("S251_Other_1")) {
                            sendSettingsCmd(1, param);
                            break;
                        }
                        break;
                    case 856192895:
                        if (titleSrn.equals("S251_Other_2")) {
                            sendSettingsCmd(2, param);
                            break;
                        }
                        break;
                    case 856192896:
                        if (titleSrn.equals("S251_Other_3")) {
                            sendSettingsCmd(3, param);
                            break;
                        }
                        break;
                    case 856192897:
                        if (titleSrn.equals("S251_Other_4")) {
                            sendSettingsCmd(4, param);
                            break;
                        }
                        break;
                    case 856192898:
                        if (titleSrn.equals("S251_Other_5")) {
                            sendSettingsCmd(5, param);
                            break;
                        }
                        break;
                    case 856192899:
                        if (titleSrn.equals("S251_Other_6")) {
                            sendSettingsCmd(7, param);
                            break;
                        }
                        break;
                    case 856192900:
                        if (titleSrn.equals("S251_Other_7")) {
                            sendSettingsCmd(8, param);
                            break;
                        }
                        break;
                    case 856192901:
                        if (titleSrn.equals("S251_Other_8")) {
                            sendSettingsCmd(9, param);
                            break;
                        }
                        break;
                }
            }
        }

        private final void sendSettingsCmd(int d0, int d1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -105, (byte) d0, (byte) d1});
        }
    }
}
