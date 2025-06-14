package com.hzbhd.canbus.car._364;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.InitUtilsKt;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UiMgr.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u000eB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00060\tR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000f"}, d2 = {"Lcom/hzbhd/canbus/car/_364/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mContext", "getMContext", "()Landroid/content/Context;", "mSettingHandleListener", "Lcom/hzbhd/canbus/car/_364/UiMgr$SettingHandleListener;", "mSettingPageUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "getMSettingPageUiSet", "()Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "SettingHandleListener", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class UiMgr extends AbstractUiMgr {
    private final Context mContext;
    private final SettingHandleListener mSettingHandleListener;
    private final SettingPageUiSet mSettingPageUiSet;

    public UiMgr(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.mContext = context;
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        Intrinsics.checkNotNullExpressionValue(settingUiSet, "getSettingUiSet(context)");
        this.mSettingPageUiSet = settingUiSet;
        SettingHandleListener settingHandleListener = new SettingHandleListener(this, settingUiSet);
        this.mSettingHandleListener = settingHandleListener;
        settingUiSet.setOnSettingItemSelectListener(settingHandleListener);
        settingUiSet.setOnSettingItemSwitchListener(settingHandleListener);
        settingUiSet.setOnSettingItemClickListener(new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._364.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
            public final void onClickItem(int i, int i2) {
                UiMgr.m783lambda1$lambda0(this.f$0, i, i2);
            }
        });
    }

    public final Context getMContext() {
        return this.mContext;
    }

    public final SettingPageUiSet getMSettingPageUiSet() {
        return this.mSettingPageUiSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-1$lambda-0, reason: not valid java name */
    public static final void m783lambda1$lambda0(UiMgr this$0, int i, int i2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (Intrinsics.areEqual(this$0.mSettingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn(), "_396_setting54")) {
            CanbusMsgSender.sendMsg(new byte[]{22, 26, 0});
        }
    }

    /* compiled from: UiMgr.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u000b\b\u0086\u0004\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J \u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\fH\u0016J \u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\fH\u0016J \u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\fH\u0002J\u0018\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\fH\u0002R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001d\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001b"}, d2 = {"Lcom/hzbhd/canbus/car/_364/UiMgr$SettingHandleListener;", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSelectListener;", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSwitchListener;", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSeekbarSelectListener;", "mSettingPageUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "(Lcom/hzbhd/canbus/car/_364/UiMgr;Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;)V", "getMSettingPageUiSet", "()Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "map", "", "", "", "getMap", "()Ljava/util/Map;", "onClickItem", "", "leftPos", "rightPos", "progressORvalue", "onSwitch", "value", "selectSettingsBtn", "param", "sendAirSettingsCmd", "d0", "d1", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public final class SettingHandleListener implements OnSettingItemSelectListener, OnSettingItemSwitchListener, OnSettingItemSeekbarSelectListener {
        private final SettingPageUiSet mSettingPageUiSet;
        private final Map<String, Integer> map;
        final /* synthetic */ UiMgr this$0;

        public SettingHandleListener(UiMgr uiMgr, SettingPageUiSet mSettingPageUiSet) {
            Intrinsics.checkNotNullParameter(mSettingPageUiSet, "mSettingPageUiSet");
            this.this$0 = uiMgr;
            this.mSettingPageUiSet = mSettingPageUiSet;
            this.map = MapsKt.mapOf(TuplesKt.to("S364_Language_1", 1), TuplesKt.to("S364_Language_2", 2), TuplesKt.to("S364_Language_3", 3), TuplesKt.to("S364_Language_4", 4), TuplesKt.to("S364_Language_5", 5), TuplesKt.to("S364_Language_6", 6), TuplesKt.to("S364_Language_7", 7), TuplesKt.to("S364_Language_8", 15), TuplesKt.to("S364_Language_9", 8), TuplesKt.to("S364_Language_10", 9), TuplesKt.to("S364_Language_11", 10), TuplesKt.to("S364_Language_12", 11), TuplesKt.to("S364_Language_13", 12), TuplesKt.to("S364_Language_14", 13), TuplesKt.to("S364_Language_15", 14), TuplesKt.to("S364_Language_16", 16));
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
                if (iHashCode == 94883461) {
                    if (titleSrn.equals("S314_Language_0")) {
                        Map<String, Integer> map = this.map;
                        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = InitUtilsKt.getMSettingItemIndex().get("S314_Language_0");
                        Intrinsics.checkNotNull(itemListBean);
                        Integer num = map.get(itemListBean.getValueSrnArray().get(param));
                        Intrinsics.checkNotNull(num);
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte) num.intValue()});
                        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = InitUtilsKt.getMSettingItemIndex().get("S314_Language_0");
                        if (itemListBean2 != null) {
                            itemListBean2.setValue(itemListBean2.getValueSrnArray().get(param));
                        }
                        MsgMgrInterface canMsgMgr = MsgMgrFactory.getCanMsgMgr(this.this$0.getMContext());
                        Intrinsics.checkNotNull(canMsgMgr, "null cannot be cast to non-null type com.hzbhd.canbus.car._364.MsgMgr");
                        ((MsgMgr) canMsgMgr).updateSettingActivity(null);
                    }
                    return;
                }
                switch (iHashCode) {
                    case 193467355:
                        if (titleSrn.equals("S364_AIR_1")) {
                            sendAirSettingsCmd(1, param);
                            break;
                        }
                        break;
                    case 193467356:
                        if (titleSrn.equals("S364_AIR_2")) {
                            sendAirSettingsCmd(2, param);
                            break;
                        }
                        break;
                    case 193467357:
                        if (titleSrn.equals("S364_AIR_3")) {
                            sendAirSettingsCmd(3, param);
                            break;
                        }
                        break;
                    case 193467358:
                        if (titleSrn.equals("S364_AIR_4")) {
                            sendAirSettingsCmd(4, param);
                            break;
                        }
                        break;
                    case 193467359:
                        if (titleSrn.equals("S364_AIR_5")) {
                            sendAirSettingsCmd(5, param);
                            break;
                        }
                        break;
                    case 193467360:
                        if (titleSrn.equals("S364_AIR_6")) {
                            sendAirSettingsCmd(6, param);
                            break;
                        }
                        break;
                    case 193467361:
                        if (titleSrn.equals("S364_AIR_7")) {
                            sendAirSettingsCmd(7, param);
                            break;
                        }
                        break;
                    case 193467362:
                        if (titleSrn.equals("S364_AIR_8")) {
                            sendAirSettingsCmd(8, param);
                            break;
                        }
                        break;
                    case 193467363:
                        if (titleSrn.equals("S364_AIR_9")) {
                            sendAirSettingsCmd(9, param);
                            break;
                        }
                        break;
                    default:
                        switch (iHashCode) {
                            case 1702520757:
                                if (titleSrn.equals("S364_AIR_10")) {
                                    sendAirSettingsCmd(10, param);
                                    break;
                                }
                                break;
                            case 1702520758:
                                if (titleSrn.equals("S364_AIR_11")) {
                                    sendAirSettingsCmd(11, param);
                                    break;
                                }
                                break;
                            case 1702520759:
                                if (titleSrn.equals("S364_AIR_12")) {
                                    sendAirSettingsCmd(12, param);
                                    break;
                                }
                                break;
                            case 1702520760:
                                if (titleSrn.equals("S364_AIR_13")) {
                                    sendAirSettingsCmd(13, param);
                                    break;
                                }
                                break;
                        }
                }
            }
        }

        public final Map<String, Integer> getMap() {
            return this.map;
        }

        private final void sendAirSettingsCmd(int d0, int d1) {
            CanbusMsgSender.sendMsg(new byte[]{22, 58, (byte) d0, (byte) d1});
        }
    }
}
