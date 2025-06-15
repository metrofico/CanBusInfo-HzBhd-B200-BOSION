package com.hzbhd.canbus.car._405;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;




public final class UiMgr extends AbstractUiMgr {
    public UiMgr(Context context) {

        SettingPageUiSet settingUiSet = getSettingUiSet(context);

        SettingHandleListener settingHandleListener = new SettingHandleListener(settingUiSet);
        settingUiSet.setOnSettingItemSelectListener(settingHandleListener);
        settingUiSet.setOnSettingItemSwitchListener(settingHandleListener);
        getParkPageUiSet(context).setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._405.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public final void onClickItem(int i) {
                UiMgr.m833lambda2$lambda1(i);
            }
        });
    }

    /* renamed from: lambda-2$sendPanoramaCmd, reason: not valid java name */
    private static final void m834lambda2$sendPanoramaCmd(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, 74, 1, (byte) i});
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-2$lambda-1, reason: not valid java name */
    public static final void m833lambda2$lambda1(int i) {
        if (i == 0) {
            m834lambda2$sendPanoramaCmd(0);
        } else if (i == 1) {
            m834lambda2$sendPanoramaCmd(1);
        } else {
            if (i != 2) {
                return;
            }
            m834lambda2$sendPanoramaCmd(2);
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
                int iHashCode = titleSrn.hashCode();
                switch (iHashCode) {
                    case -1528225728:
                        if (titleSrn.equals("S405_x87_10")) {
                            sendSettingsCmd(10, param);
                            break;
                        }
                        break;
                    case -1528225727:
                        if (titleSrn.equals("S405_x87_11")) {
                            sendSettingsCmd(15, param);
                            break;
                        }
                        break;
                    case -1528225726:
                        if (titleSrn.equals("S405_x87_12")) {
                            sendSettingsCmd(14, param);
                            break;
                        }
                        break;
                    default:
                        switch (iHashCode) {
                            case 1751817712:
                                if (titleSrn.equals("S405_x87_1")) {
                                    sendSettingsCmd(5, param);
                                    break;
                                }
                                break;
                            case 1751817713:
                                if (titleSrn.equals("S405_x87_2")) {
                                    sendSettingsCmd(4, param);
                                    break;
                                }
                                break;
                            case 1751817714:
                                if (titleSrn.equals("S405_x87_3")) {
                                    sendSettingsCmd(3, param);
                                    break;
                                }
                                break;
                            case 1751817715:
                                if (titleSrn.equals("S405_x87_4")) {
                                    sendSettingsCmd(2, param);
                                    break;
                                }
                                break;
                            case 1751817716:
                                if (titleSrn.equals("S405_x87_5")) {
                                    sendSettingsCmd(9, param);
                                    break;
                                }
                                break;
                            case 1751817717:
                                if (titleSrn.equals("S405_x87_6")) {
                                    sendSettingsCmd(8, param);
                                    break;
                                }
                                break;
                            case 1751817718:
                                if (titleSrn.equals("S405_x87_7")) {
                                    sendSettingsCmd(13, param);
                                    break;
                                }
                                break;
                            case 1751817719:
                                if (titleSrn.equals("S405_x87_8")) {
                                    sendSettingsCmd(12, param);
                                    break;
                                }
                                break;
                            case 1751817720:
                                if (titleSrn.equals("S405_x87_9")) {
                                    sendSettingsCmd(11, param);
                                    break;
                                }
                                break;
                        }
                }
            }
        }

        private final void sendSettingsCmd(int d0, int d1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -116, (byte) d0, (byte) d1});
        }
    }
}
