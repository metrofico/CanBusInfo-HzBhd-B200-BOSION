package com.hzbhd.canbus.car._163;

import android.content.Context;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AirBtnAction;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.InitUtilsKt;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UiMgr.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0016B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0010\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u00060\fR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/hzbhd/canbus/car/_163/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mAirPageUiSet", "Lcom/hzbhd/canbus/adapter/bean/AirPageUiSet;", "mContext", "mParkPageUiSet", "Lcom/hzbhd/canbus/ui_set/ParkPageUiSet;", "kotlin.jvm.PlatformType", "mSettingHandleListener", "Lcom/hzbhd/canbus/car/_163/UiMgr$SettingHandleListener;", "mSettingPageUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "selectAirConditionerBtn", "", "btn", "", "sendAirConditionerCmd", "d0", "", "SettingHandleListener", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public final class UiMgr extends AbstractUiMgr {
    private final AirPageUiSet mAirPageUiSet;
    private final Context mContext;
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
        this.mParkPageUiSet = parkPageUiSet;
        this.mContext = context;
        SettingHandleListener settingHandleListener = new SettingHandleListener();
        this.mSettingHandleListener = settingHandleListener;
        settingUiSet.setOnSettingItemSelectListener(settingHandleListener);
        settingUiSet.setOnSettingItemSwitchListener(settingHandleListener);
        settingUiSet.setOnSettingItemSeekbarSelectListener(settingHandleListener);
        settingUiSet.setOnSettingConfirmDialogListener(settingHandleListener);
        final FrontArea frontArea = airUiSet.getFrontArea();
        frontArea.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._163.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m251lambda6$lambda5$lambda1(this.f$0, frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._163.UiMgr$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m252lambda6$lambda5$lambda2(this.f$0, frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._163.UiMgr$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m253lambda6$lambda5$lambda3(this.f$0, frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._163.UiMgr$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m254lambda6$lambda5$lambda4(this.f$0, frontArea, i);
            }
        }});
        frontArea.setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._163.UiMgr$2$1$5
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                this.this$0.sendAirConditionerCmd(12);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                this.this$0.sendAirConditionerCmd(11);
            }
        });
        frontArea.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._163.UiMgr$2$1$6
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                this.this$0.sendAirConditionerCmd(13);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                this.this$0.sendAirConditionerCmd(14);
            }
        }, null, new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._163.UiMgr$2$1$7
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                this.this$0.sendAirConditionerCmd(15);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                this.this$0.sendAirConditionerCmd(16);
            }
        }});
        frontArea.setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._163.UiMgr$2$1$8
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                this.this$0.sendAirConditionerCmd(21);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
                this.this$0.sendAirConditionerCmd(22);
            }
        });
        parkPageUiSet.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._163.UiMgr$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public final void onClickItem(int i) {
                UiMgr.m250_init_$lambda7(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-6$lambda-5$lambda-1, reason: not valid java name */
    public static final void m251lambda6$lambda5$lambda1(UiMgr this$0, FrontArea frontArea, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str = frontArea.getLineBtnAction()[0][i];
        Intrinsics.checkNotNullExpressionValue(str, "this.lineBtnAction[0][it]");
        this$0.selectAirConditionerBtn(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-6$lambda-5$lambda-2, reason: not valid java name */
    public static final void m252lambda6$lambda5$lambda2(UiMgr this$0, FrontArea frontArea, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str = frontArea.getLineBtnAction()[1][i];
        Intrinsics.checkNotNullExpressionValue(str, "this.lineBtnAction[1][it]");
        this$0.selectAirConditionerBtn(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-6$lambda-5$lambda-3, reason: not valid java name */
    public static final void m253lambda6$lambda5$lambda3(UiMgr this$0, FrontArea frontArea, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str = frontArea.getLineBtnAction()[2][i];
        Intrinsics.checkNotNullExpressionValue(str, "this.lineBtnAction[2][it]");
        this$0.selectAirConditionerBtn(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-6$lambda-5$lambda-4, reason: not valid java name */
    public static final void m254lambda6$lambda5$lambda4(UiMgr this$0, FrontArea frontArea, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str = frontArea.getLineBtnAction()[3][i];
        Intrinsics.checkNotNullExpressionValue(str, "this.lineBtnAction[3][it]");
        this$0.selectAirConditionerBtn(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-7, reason: not valid java name */
    public static final void m250_init_$lambda7(int i) {
        int i2;
        if (i == 0) {
            i2 = 5;
        } else if (i == 1) {
            i2 = 6;
        } else if (i == 2) {
            i2 = 7;
        } else if (i != 3) {
            return;
        } else {
            i2 = 8;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -14, 16, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendAirConditionerCmd(int d0) {
        byte b = (byte) d0;
        CanbusMsgSender.sendMsg(new byte[]{22, 61, b, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, 61, b, 0});
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
            case -631663038:
                if (btn.equals("rear_defog")) {
                    sendAirConditionerCmd(6);
                    break;
                }
                break;
            case -54617514:
                if (btn.equals(AirBtnAction.AUTO_CYCLE)) {
                    sendAirConditionerCmd(59);
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
            case 3135580:
                if (btn.equals(AirBtnAction.FAST)) {
                    sendAirConditionerCmd(61);
                    break;
                }
                break;
            case 3496356:
                if (btn.equals(AirBtnAction.REAR)) {
                    sendAirConditionerCmd(46);
                    break;
                }
                break;
            case 3535914:
                if (btn.equals(AirBtnAction.SOFT)) {
                    sendAirConditionerCmd(60);
                    break;
                }
                break;
            case 3545755:
                if (btn.equals("sync")) {
                    sendAirConditionerCmd(3);
                    break;
                }
                break;
            case 106858757:
                if (btn.equals("power")) {
                    sendAirConditionerCmd(1);
                    break;
                }
                break;
            case 756225563:
                if (btn.equals("in_out_cycle")) {
                    sendAirConditionerCmd(7);
                    break;
                }
                break;
        }
    }

    /* compiled from: UiMgr.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\n\b\u0086\u0004\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J \u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\tH\u0016J\u0018\u0010\u0011\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\tH\u0016J \u0010\u0012\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\tH\u0016J)\u0010\u0014\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\t2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\tH\u0002¢\u0006\u0002\u0010\u0016R\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/hzbhd/canbus/car/_163/UiMgr$SettingHandleListener;", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSelectListener;", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSwitchListener;", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSeekbarSelectListener;", "Lcom/hzbhd/canbus/interfaces/OnConfirmDialogListener;", "(Lcom/hzbhd/canbus/car/_163/UiMgr;)V", "language", "", "", "", "getLanguage", "()Ljava/util/Map;", "onClickItem", "", "leftPos", "rightPos", "progressORvalue", "onConfirmClick", "onSwitch", "value", "selectSettingsBtn", "param", "(IILjava/lang/Integer;)V", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public final class SettingHandleListener implements OnSettingItemSelectListener, OnSettingItemSwitchListener, OnSettingItemSeekbarSelectListener, OnConfirmDialogListener {
        private final Map<String, Integer> language = MapsKt.mapOf(TuplesKt.to("English", 1), TuplesKt.to("简体中文", 2), TuplesKt.to("Deutsch", 3), TuplesKt.to("Italiano", 4), TuplesKt.to("Francais", 5), TuplesKt.to("Svenska", 6), TuplesKt.to("Espanol", 7), TuplesKt.to("Nederlands", 8), TuplesKt.to("Portugues", 9), TuplesKt.to("Norsk", 11), TuplesKt.to("Suomi", 12), TuplesKt.to("Dansk", 13), TuplesKt.to("Eyynvlka", 14), TuplesKt.to("auell", 15), TuplesKt.to("Turkce", 16), TuplesKt.to("Pyccknn", 18), TuplesKt.to("Romana", 20), TuplesKt.to("Ykpaihcbka", 22), TuplesKt.to("Polski", 23), TuplesKt.to("Slovencia", 24), TuplesKt.to("Cesky", 25), TuplesKt.to("Magyar", 26), TuplesKt.to("Srpski", 28), TuplesKt.to("PortuguesdoBrasil", 29), TuplesKt.to("Hrvatski", 31), TuplesKt.to("bbnrapckn", 32), TuplesKt.to("Eesti", 33), TuplesKt.to("Vlaams", 34), TuplesKt.to("NNNy", 35), TuplesKt.to("印度语", 36), TuplesKt.to("uule", 37), TuplesKt.to("Latviesu", 38), TuplesKt.to("Lietuviu", 39), TuplesKt.to("Slovenscina", 40), TuplesKt.to("America_latina", 41));

        public SettingHandleListener() {
        }

        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int leftPos, int rightPos, int progressORvalue) {
            selectSettingsBtn(leftPos, rightPos, Integer.valueOf(progressORvalue));
        }

        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener
        public void onSwitch(int leftPos, int rightPos, int value) {
            selectSettingsBtn(leftPos, rightPos, Integer.valueOf(value));
        }

        @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
        public void onConfirmClick(int leftPos, int rightPos) {
            selectSettingsBtn$default(this, leftPos, rightPos, null, 4, null);
        }

        static /* synthetic */ void selectSettingsBtn$default(SettingHandleListener settingHandleListener, int i, int i2, Integer num, int i3, Object obj) {
            if ((i3 & 4) != 0) {
                num = null;
            }
            settingHandleListener.selectSettingsBtn(i, i2, num);
        }

        private static final void selectSettingsBtn$sendSettingsCmd(int i, Integer num) {
            byte[] bArr = new byte[6];
            bArr[0] = 22;
            bArr[1] = 111;
            bArr[2] = (byte) i;
            if (num != null) {
                bArr[3] = (byte) num.intValue();
                bArr[4] = -1;
                bArr[5] = -1;
                CanbusMsgSender.sendMsg(bArr);
            }
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
            String titleSrn = UiMgr.this.mSettingPageUiSet.getList().get(leftPos).getItemList().get(rightPos).getTitleSrn();
            if (titleSrn != null) {
                int iHashCode = titleSrn.hashCode();
                switch (iHashCode) {
                    case -1400265026:
                        if (titleSrn.equals("_163_setting_10")) {
                            selectSettingsBtn$sendSettingsCmd(10, param);
                            break;
                        }
                        break;
                    case -1400265025:
                        if (titleSrn.equals("_163_setting_11")) {
                            Intrinsics.checkNotNull(param);
                            selectSettingsBtn$sendSettingsCmd(11, Integer.valueOf(param.intValue() + 2));
                            break;
                        }
                        break;
                    case -1400265024:
                        if (titleSrn.equals("_163_setting_12")) {
                            selectSettingsBtn$sendSettingsCmd(12, param);
                            break;
                        }
                        break;
                    case -1400265023:
                        if (titleSrn.equals("_163_setting_13")) {
                            selectSettingsBtn$sendSettingsCmd(13, param);
                            break;
                        }
                        break;
                    case -1400265022:
                        if (titleSrn.equals("_163_setting_14")) {
                            selectSettingsBtn$sendSettingsCmd(14, param);
                            break;
                        }
                        break;
                    case -1400265021:
                        if (titleSrn.equals("_163_setting_15")) {
                            selectSettingsBtn$sendSettingsCmd(15, param);
                            break;
                        }
                        break;
                    case -1400265020:
                        if (titleSrn.equals("_163_setting_16")) {
                            selectSettingsBtn$sendSettingsCmd(16, param);
                            break;
                        }
                        break;
                    case -1400265019:
                        if (titleSrn.equals("_163_setting_17")) {
                            selectSettingsBtn$sendSettingsCmd(17, param);
                            break;
                        }
                        break;
                    case -1400265018:
                        if (titleSrn.equals("_163_setting_18")) {
                            selectSettingsBtn$sendSettingsCmd(18, param);
                            break;
                        }
                        break;
                    case -1400265017:
                        if (titleSrn.equals("_163_setting_19")) {
                            selectSettingsBtn$sendSettingsCmd(19, param);
                            break;
                        }
                        break;
                    default:
                        switch (iHashCode) {
                            case -1400264995:
                                if (titleSrn.equals("_163_setting_20")) {
                                    selectSettingsBtn$sendSettingsCmd(20, param);
                                    break;
                                }
                                break;
                            case -1400264994:
                                if (titleSrn.equals("_163_setting_21")) {
                                    selectSettingsBtn$sendSettingsCmd(21, param);
                                    break;
                                }
                                break;
                            case -1400264993:
                                if (titleSrn.equals("_163_setting_22")) {
                                    selectSettingsBtn$sendSettingsCmd(22, param);
                                    break;
                                }
                                break;
                            case -1400264992:
                                if (titleSrn.equals("_163_setting_23")) {
                                    selectSettingsBtn$sendSettingsCmd(23, param);
                                    break;
                                }
                                break;
                            case -1400264991:
                                if (titleSrn.equals("_163_setting_24")) {
                                    selectSettingsBtn$sendSettingsCmd(24, param);
                                    break;
                                }
                                break;
                            case -1400264990:
                                if (titleSrn.equals("_163_setting_25")) {
                                    selectSettingsBtn$sendSettingsCmd(25, param);
                                    break;
                                }
                                break;
                            case -1400264989:
                                if (titleSrn.equals("_163_setting_26")) {
                                    selectSettingsBtn$sendSettingsCmd(26, param);
                                    break;
                                }
                                break;
                            case -1400264988:
                                if (titleSrn.equals("_163_setting_27")) {
                                    selectSettingsBtn$sendSettingsCmd(27, param);
                                    break;
                                }
                                break;
                            case -1400264987:
                                if (titleSrn.equals("_163_setting_28")) {
                                    selectSettingsBtn$sendSettingsCmd(28, param);
                                    break;
                                }
                                break;
                            case -1400264986:
                                if (titleSrn.equals("_163_setting_29")) {
                                    selectSettingsBtn$sendSettingsCmd(29, param);
                                    break;
                                }
                                break;
                            default:
                                switch (iHashCode) {
                                    case -1400264964:
                                        if (titleSrn.equals("_163_setting_30")) {
                                            selectSettingsBtn$sendSettingsCmd(30, param);
                                            break;
                                        }
                                        break;
                                    case -1324002571:
                                        if (titleSrn.equals("S163_x2A_1")) {
                                            Intrinsics.checkNotNull(param);
                                            CanbusMsgSender.sendMsg(new byte[]{22, 42, (byte) param.intValue(), 0});
                                            break;
                                        }
                                        break;
                                    case -490097043:
                                        if (titleSrn.equals("S163_Language_0")) {
                                            MsgMgrInterface canMsgMgr = MsgMgrFactory.getCanMsgMgr(UiMgr.this.mContext);
                                            Intrinsics.checkNotNull(canMsgMgr, "null cannot be cast to non-null type com.hzbhd.canbus.car._163.MsgMgr");
                                            MsgMgr msgMgr = (MsgMgr) canMsgMgr;
                                            Intrinsics.checkNotNull(param);
                                            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = InitUtilsKt.getMSettingItemIndex().get("S163_Language_0");
                                            if (itemListBean != null) {
                                                itemListBean.setValue(itemListBean.getValueSrnArray().get(param.intValue()));
                                                Integer num = this.language.get(itemListBean.getValue());
                                                Intrinsics.checkNotNull(num);
                                                CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte) num.intValue()});
                                            }
                                            msgMgr.updateSettingData();
                                            break;
                                        }
                                        break;
                                    case 376901766:
                                        if (titleSrn.equals("_163_drive_data5")) {
                                            CanbusMsgSender.sendMsg(new byte[]{22, 27, 2, 2, 1, -1});
                                            Toast.makeText(UiMgr.this.mContext, "Success!", 0).show();
                                            break;
                                        }
                                        break;
                                    case 748741536:
                                        if (titleSrn.equals("S18_Car_0")) {
                                            Intrinsics.checkNotNull(param);
                                            CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte) (param.intValue() + 16), 49});
                                            break;
                                        }
                                        break;
                                    default:
                                        switch (iHashCode) {
                                            case -1391072538:
                                                if (titleSrn.equals("S163_CarInfo2_1")) {
                                                    selectSettingsBtn$sendSettingsCmd(53, param);
                                                    break;
                                                }
                                                break;
                                            case -1391072537:
                                                if (titleSrn.equals("S163_CarInfo2_2")) {
                                                    selectSettingsBtn$sendSettingsCmd(54, param);
                                                    break;
                                                }
                                                break;
                                            case -1391072536:
                                                if (titleSrn.equals("S163_CarInfo2_3")) {
                                                    selectSettingsBtn$sendSettingsCmd(55, param);
                                                    break;
                                                }
                                                break;
                                            case -1391072535:
                                                if (titleSrn.equals("S163_CarInfo2_4")) {
                                                    selectSettingsBtn$sendSettingsCmd(56, param);
                                                    break;
                                                }
                                                break;
                                            case -1391072534:
                                                if (titleSrn.equals("S163_CarInfo2_5")) {
                                                    selectSettingsBtn$sendSettingsCmd(57, param);
                                                    break;
                                                }
                                                break;
                                            case -1391072533:
                                                if (titleSrn.equals("S163_CarInfo2_6")) {
                                                    selectSettingsBtn$sendSettingsCmd(58, param);
                                                    break;
                                                }
                                                break;
                                            case -1391072532:
                                                if (titleSrn.equals("S163_CarInfo2_7")) {
                                                    selectSettingsBtn$sendSettingsCmd(59, param);
                                                    break;
                                                }
                                                break;
                                            case -1391072531:
                                                if (titleSrn.equals("S163_CarInfo2_8")) {
                                                    selectSettingsBtn$sendSettingsCmd(60, param);
                                                    break;
                                                }
                                                break;
                                            case -1391072530:
                                                if (titleSrn.equals("S163_CarInfo2_9")) {
                                                    Intrinsics.checkNotNull(param);
                                                    selectSettingsBtn$sendSettingsCmd(60, Integer.valueOf(param.intValue() * 5));
                                                    break;
                                                }
                                                break;
                                            default:
                                                switch (iHashCode) {
                                                    case -1323878602:
                                                        if (titleSrn.equals("S163_x6F_1")) {
                                                            selectSettingsBtn$sendSettingsCmd(25, 1);
                                                            break;
                                                        }
                                                        break;
                                                    case -1323878601:
                                                        if (titleSrn.equals("S163_x6F_2")) {
                                                            selectSettingsBtn$sendSettingsCmd(31, 1);
                                                            break;
                                                        }
                                                        break;
                                                    case -1323878600:
                                                        if (titleSrn.equals("S163_x6F_3")) {
                                                            selectSettingsBtn$sendSettingsCmd(48, 1);
                                                            break;
                                                        }
                                                        break;
                                                    case -1323878599:
                                                        if (titleSrn.equals("S163_x6F_4")) {
                                                            selectSettingsBtn$sendSettingsCmd(49, 1);
                                                            break;
                                                        }
                                                        break;
                                                    case -1323878598:
                                                        if (titleSrn.equals("S163_x6F_5")) {
                                                            selectSettingsBtn$sendSettingsCmd(50, 1);
                                                            break;
                                                        }
                                                        break;
                                                    case -1323878597:
                                                        if (titleSrn.equals("S163_x6F_6")) {
                                                            selectSettingsBtn$sendSettingsCmd(51, 1);
                                                            break;
                                                        }
                                                        break;
                                                    case -1323878596:
                                                        if (titleSrn.equals("S163_x6F_7")) {
                                                            selectSettingsBtn$sendSettingsCmd(52, 1);
                                                            break;
                                                        }
                                                        break;
                                                    default:
                                                        switch (iHashCode) {
                                                            case -1015001166:
                                                                if (titleSrn.equals("_163_setting_1")) {
                                                                    selectSettingsBtn$sendSettingsCmd(1, param);
                                                                    break;
                                                                }
                                                                break;
                                                            case -1015001165:
                                                                if (titleSrn.equals("_163_setting_2")) {
                                                                    selectSettingsBtn$sendSettingsCmd(2, param);
                                                                    break;
                                                                }
                                                                break;
                                                            case -1015001164:
                                                                if (titleSrn.equals("_163_setting_3")) {
                                                                    selectSettingsBtn$sendSettingsCmd(3, param);
                                                                    break;
                                                                }
                                                                break;
                                                            case -1015001163:
                                                                if (titleSrn.equals("_163_setting_4")) {
                                                                    selectSettingsBtn$sendSettingsCmd(4, param);
                                                                    break;
                                                                }
                                                                break;
                                                            case -1015001162:
                                                                if (titleSrn.equals("_163_setting_5")) {
                                                                    selectSettingsBtn$sendSettingsCmd(5, param);
                                                                    break;
                                                                }
                                                                break;
                                                            case -1015001161:
                                                                if (titleSrn.equals("_163_setting_6")) {
                                                                    selectSettingsBtn$sendSettingsCmd(6, param);
                                                                    break;
                                                                }
                                                                break;
                                                            case -1015001160:
                                                                if (titleSrn.equals("_163_setting_7")) {
                                                                    selectSettingsBtn$sendSettingsCmd(7, param);
                                                                    break;
                                                                }
                                                                break;
                                                            case -1015001159:
                                                                if (titleSrn.equals("_163_setting_8")) {
                                                                    selectSettingsBtn$sendSettingsCmd(8, param);
                                                                    break;
                                                                }
                                                                break;
                                                            case -1015001158:
                                                                if (titleSrn.equals("_163_setting_9")) {
                                                                    selectSettingsBtn$sendSettingsCmd(9, param);
                                                                    break;
                                                                }
                                                                break;
                                                            default:
                                                                switch (iHashCode) {
                                                                    case -173575668:
                                                                        if (titleSrn.equals("S163_CarInfo2_12")) {
                                                                            selectSettingsBtn$sendSettingsCmd(61, param);
                                                                            break;
                                                                        }
                                                                        break;
                                                                    case -173575667:
                                                                        if (titleSrn.equals("S163_CarInfo2_13")) {
                                                                            selectSettingsBtn$sendSettingsCmd(62, param);
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

        public final Map<String, Integer> getLanguage() {
            return this.language;
        }
    }
}
