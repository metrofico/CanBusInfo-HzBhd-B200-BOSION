package com.hzbhd.canbus.car._404;

import android.content.Context;
import android.view.MotionEvent;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UiMgr.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u000eB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \u0007*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u00060\u000bR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/hzbhd/canbus/car/_404/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mAirPageUiSet", "Lcom/hzbhd/canbus/adapter/bean/AirPageUiSet;", "kotlin.jvm.PlatformType", "mParkPageUiSet", "Lcom/hzbhd/canbus/ui_set/ParkPageUiSet;", "mSettingHandleListener", "Lcom/hzbhd/canbus/car/_404/UiMgr$SettingHandleListener;", "mSettingPageUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "SettingHandleListener", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class UiMgr extends AbstractUiMgr {
    private final AirPageUiSet mAirPageUiSet;
    private final ParkPageUiSet mParkPageUiSet;
    private final SettingHandleListener mSettingHandleListener;
    private final SettingPageUiSet mSettingPageUiSet;

    public UiMgr(final Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        Intrinsics.checkNotNullExpressionValue(settingUiSet, "getSettingUiSet(context)");
        this.mSettingPageUiSet = settingUiSet;
        SettingHandleListener settingHandleListener = new SettingHandleListener(this, settingUiSet);
        this.mSettingHandleListener = settingHandleListener;
        AirPageUiSet airUiSet = getAirUiSet(context);
        this.mAirPageUiSet = airUiSet;
        ParkPageUiSet parkPageUiSet = getParkPageUiSet(context);
        this.mParkPageUiSet = parkPageUiSet;
        final FrontArea frontArea = airUiSet.getFrontArea();
        frontArea.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._404.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m825lambda4$lambda0(frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._404.UiMgr$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m826lambda4$lambda1(frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._404.UiMgr$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m827lambda4$lambda2(frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._404.UiMgr$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m828lambda4$lambda3(frontArea, i);
            }
        }});
        frontArea.setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._404.UiMgr$1$5
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr.m830lambda4$send(12);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr.m830lambda4$send(11);
            }
        });
        frontArea.setTempSetViewOnUpDownClickListeners(new UiMgr$1$6[]{new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._404.UiMgr$1$6
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                UiMgr.m830lambda4$send(13);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                UiMgr.m830lambda4$send(14);
            }
        }, null, null});
        frontArea.setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._404.UiMgr$1$7
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                UiMgr.m830lambda4$send(21);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
                UiMgr.m830lambda4$send(22);
            }
        });
        frontArea.setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._404.UiMgr$1$8
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                UiMgr.m830lambda4$send(17);
            }
        }, new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._404.UiMgr$1$9
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                UiMgr.m830lambda4$send(18);
            }
        }, null, null});
        parkPageUiSet.setOnPanoramicItemTouchListener(new OnPanoramicItemTouchListener() { // from class: com.hzbhd.canbus.car._404.UiMgr$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener
            public final void onTouchItem(MotionEvent motionEvent) {
                UiMgr.m823_init_$lambda6(context, motionEvent);
            }
        });
        settingUiSet.setOnSettingItemSelectListener(settingHandleListener);
        settingUiSet.setOnSettingItemSwitchListener(settingHandleListener);
        settingUiSet.setOnSettingItemSeekbarSelectListener(settingHandleListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-4$send, reason: not valid java name */
    public static final void m830lambda4$send(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, 61, (byte) i, 1});
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* renamed from: lambda-4$selectAirPageBtn, reason: not valid java name */
    private static final void m829lambda4$selectAirPageBtn(String str) {
        switch (str.hashCode()) {
            case -1470045433:
                if (str.equals("front_defog")) {
                    m830lambda4$send(5);
                    break;
                }
                break;
            case -631663038:
                if (str.equals("rear_defog")) {
                    m830lambda4$send(6);
                    break;
                }
                break;
            case 3106:
                if (str.equals("ac")) {
                    m830lambda4$send(2);
                    break;
                }
                break;
            case 3005871:
                if (str.equals("auto")) {
                    m830lambda4$send(4);
                    break;
                }
                break;
            case 756225563:
                if (str.equals("in_out_cycle")) {
                    m830lambda4$send(7);
                    break;
                }
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-4$lambda-0, reason: not valid java name */
    public static final void m825lambda4$lambda0(FrontArea frontArea, int i) {
        String str = frontArea.getLineBtnAction()[0][i];
        Intrinsics.checkNotNullExpressionValue(str, "this.lineBtnAction[0][it]");
        m829lambda4$selectAirPageBtn(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-4$lambda-1, reason: not valid java name */
    public static final void m826lambda4$lambda1(FrontArea frontArea, int i) {
        String str = frontArea.getLineBtnAction()[1][i];
        Intrinsics.checkNotNullExpressionValue(str, "this.lineBtnAction[1][it]");
        m829lambda4$selectAirPageBtn(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-4$lambda-2, reason: not valid java name */
    public static final void m827lambda4$lambda2(FrontArea frontArea, int i) {
        String str = frontArea.getLineBtnAction()[2][i];
        Intrinsics.checkNotNullExpressionValue(str, "this.lineBtnAction[2][it]");
        m829lambda4$selectAirPageBtn(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-4$lambda-3, reason: not valid java name */
    public static final void m828lambda4$lambda3(FrontArea frontArea, int i) {
        String str = frontArea.getLineBtnAction()[3][i];
        Intrinsics.checkNotNullExpressionValue(str, "this.lineBtnAction[3][it]");
        m829lambda4$selectAirPageBtn(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-6, reason: not valid java name */
    public static final void m823_init_$lambda6(Context context, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(context, "$context");
        int i = context.getResources().getDisplayMetrics().widthPixels;
        int i2 = context.getResources().getDisplayMetrics().heightPixels;
        int x = (int) (motionEvent.getX() * (160.0f / i));
        int y = (int) (motionEvent.getY() * (90.0f / i2));
        if (motionEvent.getAction() == 0) {
            m831lambda6$send5(x, y);
        }
    }

    /* renamed from: lambda-6$send-5, reason: not valid java name */
    private static final void m831lambda6$send5(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, 44, 1, (byte) DataHandleUtils.getMsb(i), (byte) DataHandleUtils.getLsb(i), (byte) DataHandleUtils.getMsb(i2), (byte) DataHandleUtils.getLsb(i2), 0});
    }

    /* compiled from: UiMgr.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0016J \u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0016J \u0010\u0010\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0002R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"}, d2 = {"Lcom/hzbhd/canbus/car/_404/UiMgr$SettingHandleListener;", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSelectListener;", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSwitchListener;", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSeekbarSelectListener;", "mSettingPageUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "(Lcom/hzbhd/canbus/car/_404/UiMgr;Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;)V", "getMSettingPageUiSet", "()Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "onClickItem", "", "leftPos", "", "rightPos", "value", "onSwitch", "selectSettingsBtn", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public final class SettingHandleListener implements OnSettingItemSelectListener, OnSettingItemSwitchListener, OnSettingItemSeekbarSelectListener {
        private final SettingPageUiSet mSettingPageUiSet;
        final /* synthetic */ UiMgr this$0;

        public SettingHandleListener(UiMgr uiMgr, SettingPageUiSet mSettingPageUiSet) {
            Intrinsics.checkNotNullParameter(mSettingPageUiSet, "mSettingPageUiSet");
            this.this$0 = uiMgr;
            this.mSettingPageUiSet = mSettingPageUiSet;
        }

        public final SettingPageUiSet getMSettingPageUiSet() {
            return this.mSettingPageUiSet;
        }

        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int leftPos, int rightPos, int value) {
            selectSettingsBtn(leftPos, rightPos, value);
        }

        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener
        public void onSwitch(int leftPos, int rightPos, int value) {
            selectSettingsBtn(leftPos, rightPos, value);
        }

        private static final void selectSettingsBtn$send(int i, int i2) {
            CanbusMsgSender.sendMsg(new byte[]{22, 111, (byte) i, (byte) i2, -1, -1});
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
        java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
        	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
        	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
         */
        private final void selectSettingsBtn(int leftPos, int rightPos, int value) {
            String titleSrn = this.mSettingPageUiSet.getList().get(leftPos).getItemList().get(rightPos).getTitleSrn();
            if (titleSrn != null) {
                int iHashCode = titleSrn.hashCode();
                switch (iHashCode) {
                    case 75675904:
                        if (titleSrn.equals("S404_0x61_d0_1")) {
                            selectSettingsBtn$send(1, value);
                            break;
                        }
                        break;
                    case 75675905:
                        if (titleSrn.equals("S404_0x61_d0_2")) {
                            selectSettingsBtn$send(2, value);
                            break;
                        }
                        break;
                    case 75675906:
                        if (titleSrn.equals("S404_0x61_d0_3")) {
                            selectSettingsBtn$send(3, value);
                            break;
                        }
                        break;
                    case 75675907:
                        if (titleSrn.equals("S404_0x61_d0_4")) {
                            selectSettingsBtn$send(4, value);
                            break;
                        }
                        break;
                    default:
                        switch (iHashCode) {
                            case 75676865:
                                if (titleSrn.equals("S404_0x61_d1_1")) {
                                    selectSettingsBtn$send(5, value);
                                    break;
                                }
                                break;
                            case 75676866:
                                if (titleSrn.equals("S404_0x61_d1_2")) {
                                    selectSettingsBtn$send(6, value);
                                    break;
                                }
                                break;
                            case 75676867:
                                if (titleSrn.equals("S404_0x61_d1_3")) {
                                    selectSettingsBtn$send(7, value);
                                    break;
                                }
                                break;
                            default:
                                switch (iHashCode) {
                                    case 75677826:
                                        if (titleSrn.equals("S404_0x61_d2_1")) {
                                            selectSettingsBtn$send(8, value);
                                            break;
                                        }
                                        break;
                                    case 75677827:
                                        if (titleSrn.equals("S404_0x61_d2_2")) {
                                            selectSettingsBtn$send(9, value);
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
