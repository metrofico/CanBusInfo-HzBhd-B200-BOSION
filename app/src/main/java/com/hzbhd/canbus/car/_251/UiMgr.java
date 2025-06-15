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




public final class UiMgr extends AbstractUiMgr {
    private final AirPageUiSet mAirPageUiSet;
    private final ParkPageUiSet mParkPageUiSet;
    private final SettingHandleListener mSettingHandleListener;
    private final SettingPageUiSet mSettingPageUiSet;

    public UiMgr(Context context) {

        SettingPageUiSet settingUiSet = getSettingUiSet(context);

        this.mSettingPageUiSet = settingUiSet;
        AirPageUiSet airUiSet = getAirUiSet(context);

        this.mAirPageUiSet = airUiSet;
        ParkPageUiSet parkPageUiSet = getParkPageUiSet(context);

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

        String str = frontArea.getLineBtnAction()[0][i];

        this$0.selectAirConditionerBtn(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-6$lambda-5$lambda-2, reason: not valid java name */
    public static final void m333lambda6$lambda5$lambda2(UiMgr this$0, FrontArea frontArea, int i) {

        String str = frontArea.getLineBtnAction()[1][i];

        this$0.selectAirConditionerBtn(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-6$lambda-5$lambda-3, reason: not valid java name */
    public static final void m334lambda6$lambda5$lambda3(UiMgr this$0, FrontArea frontArea, int i) {

        String str = frontArea.getLineBtnAction()[2][i];

        this$0.selectAirConditionerBtn(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-6$lambda-5$lambda-4, reason: not valid java name */
    public static final void m335lambda6$lambda5$lambda4(UiMgr this$0, FrontArea frontArea, int i) {

        String str = frontArea.getLineBtnAction()[3][i];

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
    
    public static final class SettingHandleListener implements OnSettingItemSelectListener, OnSettingItemSwitchListener, OnSettingItemSeekbarSelectListener {
        private final SettingPageUiSet mSettingPageUiSet;

        public SettingHandleListener(SettingPageUiSet mSettingPageUiSet) {

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
