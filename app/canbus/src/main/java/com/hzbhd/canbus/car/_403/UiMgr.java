package com.hzbhd.canbus.car._403;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UiMgr.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u00060\tR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/hzbhd/canbus/car/_403/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mParkPageUiSet", "Lcom/hzbhd/canbus/ui_set/ParkPageUiSet;", "kotlin.jvm.PlatformType", "mSettingHandleListener", "Lcom/hzbhd/canbus/car/_403/UiMgr$SettingHandleListener;", "mSettingPageUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "SettingHandleListener", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class UiMgr extends AbstractUiMgr {
    private final ParkPageUiSet mParkPageUiSet;
    private final SettingHandleListener mSettingHandleListener;
    private final SettingPageUiSet mSettingPageUiSet;

    public UiMgr(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        Intrinsics.checkNotNullExpressionValue(settingUiSet, "getSettingUiSet(context)");
        this.mSettingPageUiSet = settingUiSet;
        SettingHandleListener settingHandleListener = new SettingHandleListener(this, settingUiSet);
        this.mSettingHandleListener = settingHandleListener;
        ParkPageUiSet parkPageUiSet = getParkPageUiSet(context);
        this.mParkPageUiSet = parkPageUiSet;
        parkPageUiSet.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._403.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public final void onClickItem(int i) {
                UiMgr.m820lambda1$lambda0(i);
            }
        });
        settingUiSet.setOnSettingItemSelectListener(settingHandleListener);
        settingUiSet.setOnSettingItemSwitchListener(settingHandleListener);
        settingUiSet.setOnSettingItemSeekbarSelectListener(settingHandleListener);
    }

    /* renamed from: lambda-1$sendPanoramaCmd, reason: not valid java name */
    private static final void m821lambda1$sendPanoramaCmd(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -14, (byte) i, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-1$lambda-0, reason: not valid java name */
    public static final void m820lambda1$lambda0(int i) {
        switch (i) {
            case 0:
                m821lambda1$sendPanoramaCmd(10, 1);
                break;
            case 1:
                m821lambda1$sendPanoramaCmd(10, 2);
                break;
            case 2:
                m821lambda1$sendPanoramaCmd(10, 3);
                break;
            case 3:
                m821lambda1$sendPanoramaCmd(10, 4);
                break;
            case 4:
                m821lambda1$sendPanoramaCmd(10, 5);
                break;
            case 5:
                m821lambda1$sendPanoramaCmd(11, 1);
                break;
            case 6:
                m821lambda1$sendPanoramaCmd(11, 2);
                break;
        }
    }

    /* compiled from: UiMgr.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0016J \u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0016J \u0010\u0010\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0002R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"}, d2 = {"Lcom/hzbhd/canbus/car/_403/UiMgr$SettingHandleListener;", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSelectListener;", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSwitchListener;", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSeekbarSelectListener;", "mSettingPageUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "(Lcom/hzbhd/canbus/car/_403/UiMgr;Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;)V", "getMSettingPageUiSet", "()Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "onClickItem", "", "leftPos", "", "rightPos", "value", "onSwitch", "selectSettingsBtn", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
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

        private static final void selectSettingsBtn$send0x6B(int i, int i2) {
            CanbusMsgSender.sendMsg(new byte[]{22, 107, (byte) i, (byte) i2});
        }

        private static final void selectSettingsBtn$send0x6C(int i, int i2) {
            CanbusMsgSender.sendMsg(new byte[]{22, 108, (byte) i, (byte) i2});
        }

        private static final void selectSettingsBtn$send0x6D(int i, int i2) {
            CanbusMsgSender.sendMsg(new byte[]{22, 109, (byte) i, (byte) i2});
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
                    case 1544023514:
                        if (titleSrn.equals("S403_0x67_10")) {
                            selectSettingsBtn$send0x6C(6, value);
                            break;
                        }
                        break;
                    case 1544023515:
                        if (titleSrn.equals("S403_0x67_11")) {
                            selectSettingsBtn$send0x6C(3, value);
                            break;
                        }
                        break;
                    case 1544023516:
                        if (titleSrn.equals("S403_0x67_12")) {
                            selectSettingsBtn$send0x6C(14, value);
                            break;
                        }
                        break;
                    case 1544023517:
                        if (titleSrn.equals("S403_0x67_13")) {
                            selectSettingsBtn$send0x6C(13, value);
                            break;
                        }
                        break;
                    case 1544023518:
                        if (titleSrn.equals("S403_0x67_14")) {
                            selectSettingsBtn$send0x6C(12, value);
                            break;
                        }
                        break;
                    default:
                        switch (iHashCode) {
                            case 1573826901:
                                if (titleSrn.equals("S403_0x66_1")) {
                                    selectSettingsBtn$send0x6B(6, value);
                                    break;
                                }
                                break;
                            case 1573826902:
                                if (titleSrn.equals("S403_0x66_2")) {
                                    selectSettingsBtn$send0x6B(5, value);
                                    break;
                                }
                                break;
                            case 1573826903:
                                if (titleSrn.equals("S403_0x66_3")) {
                                    selectSettingsBtn$send0x6B(3, value);
                                    break;
                                }
                                break;
                            case 1573826904:
                                if (titleSrn.equals("S403_0x66_4")) {
                                    selectSettingsBtn$send0x6B(2, value);
                                    break;
                                }
                                break;
                            case 1573826905:
                                if (titleSrn.equals("S403_0x66_5")) {
                                    selectSettingsBtn$send0x6B(1, value);
                                    break;
                                }
                                break;
                            default:
                                switch (iHashCode) {
                                    case 1573827862:
                                        if (titleSrn.equals("S403_0x67_1")) {
                                            selectSettingsBtn$send0x6C(5, value);
                                            break;
                                        }
                                        break;
                                    case 1573827863:
                                        if (titleSrn.equals("S403_0x67_2")) {
                                            selectSettingsBtn$send0x6C(4, value);
                                            break;
                                        }
                                        break;
                                    case 1573827864:
                                        if (titleSrn.equals("S403_0x67_3")) {
                                            selectSettingsBtn$send0x6C(2, value);
                                            break;
                                        }
                                        break;
                                    case 1573827865:
                                        if (titleSrn.equals("S403_0x67_4")) {
                                            selectSettingsBtn$send0x6C(1, value);
                                            break;
                                        }
                                        break;
                                    case 1573827866:
                                        if (titleSrn.equals("S403_0x67_5")) {
                                            selectSettingsBtn$send0x6C(11, value);
                                            break;
                                        }
                                        break;
                                    case 1573827867:
                                        if (titleSrn.equals("S403_0x67_6")) {
                                            selectSettingsBtn$send0x6C(10, value);
                                            break;
                                        }
                                        break;
                                    case 1573827868:
                                        if (titleSrn.equals("S403_0x67_7")) {
                                            selectSettingsBtn$send0x6C(9, value);
                                            break;
                                        }
                                        break;
                                    case 1573827869:
                                        if (titleSrn.equals("S403_0x67_8")) {
                                            selectSettingsBtn$send0x6C(8, value);
                                            break;
                                        }
                                        break;
                                    case 1573827870:
                                        if (titleSrn.equals("S403_0x67_9")) {
                                            selectSettingsBtn$send0x6C(7, value);
                                            break;
                                        }
                                        break;
                                    default:
                                        switch (iHashCode) {
                                            case 1573828823:
                                                if (titleSrn.equals("S403_0x68_1")) {
                                                    selectSettingsBtn$send0x6D(5, value);
                                                    break;
                                                }
                                                break;
                                            case 1573828824:
                                                if (titleSrn.equals("S403_0x68_2")) {
                                                    selectSettingsBtn$send0x6D(4, value);
                                                    break;
                                                }
                                                break;
                                            case 1573828825:
                                                if (titleSrn.equals("S403_0x68_3")) {
                                                    selectSettingsBtn$send0x6D(3, value);
                                                    break;
                                                }
                                                break;
                                            case 1573828826:
                                                if (titleSrn.equals("S403_0x68_4")) {
                                                    selectSettingsBtn$send0x6D(2, value);
                                                    break;
                                                }
                                                break;
                                            case 1573828827:
                                                if (titleSrn.equals("S403_0x68_5")) {
                                                    selectSettingsBtn$send0x6D(1, value);
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
