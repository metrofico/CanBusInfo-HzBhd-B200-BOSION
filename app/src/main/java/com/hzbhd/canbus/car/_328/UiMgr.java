package com.hzbhd.canbus.car._328;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnBackCameraStatusListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import nfore.android.bt.res.NfDef;

/* compiled from: UiMgr.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/hzbhd/canbus/car/_328/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mMsgMgr", "Lcom/hzbhd/canbus/car/_328/MsgMgr;", "Companion", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class UiMgr extends AbstractUiMgr {
    public static final String SHARE_IS_SUPPORT_PANORAMIC = "share_is_support_panoramic";
    private static final String TAG = "_330_UiMgr";
    private final MsgMgr mMsgMgr;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final byte[] panoramicCommand = {22, -57, 1};

    public UiMgr(final Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        MsgMgrInterface canMsgMgr = MsgMgrFactory.getCanMsgMgr(context);
        Intrinsics.checkNotNull(canMsgMgr, "null cannot be cast to non-null type com.hzbhd.canbus.car._328.MsgMgr");
        this.mMsgMgr = (MsgMgr) canMsgMgr;
        final SettingPageUiSet settingUiSet = getSettingUiSet(context);
        final byte[] bArr = {22, -125, -1, 0};
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._328.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public final void onClickItem(int i, int i2, int i3) {
                UiMgr.m655lambda37$lambda29(settingUiSet, bArr, context, this, i, i2, i3);
            }
        });
        settingUiSet.setOnSettingItemSwitchListener(new OnSettingItemSwitchListener() { // from class: com.hzbhd.canbus.car._328.UiMgr$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener
            public final void onSwitch(int i, int i2, int i3) {
                UiMgr.m656lambda37$lambda30(settingUiSet, this, i, i2, i3);
            }
        });
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._328.UiMgr$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public final void onClickItem(int i, int i2, int i3) {
                UiMgr.m657lambda37$lambda31(settingUiSet, i, i2, i3);
            }
        });
        settingUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._328.UiMgr$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
            public final void onConfirmClick(int i, int i2) {
                UiMgr.m658lambda37$lambda35(settingUiSet, bArr, i, i2);
            }
        });
        settingUiSet.setOnSettingItemClickListener(new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._328.UiMgr$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
            public final void onClickItem(int i, int i2) {
                UiMgr.m659lambda37$lambda36(settingUiSet, i, i2);
            }
        });
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        amplifierPageUiSet.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._328.UiMgr$2$1

            /* compiled from: UiMgr.kt */
            @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
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
                Intrinsics.checkNotNullParameter(amplifierBand, "amplifierBand");
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
        amplifierPageUiSet.setOnAmplifierPositionListener(new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._328.UiMgr$2$2

            /* compiled from: UiMgr.kt */
            @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
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
                Intrinsics.checkNotNullParameter(amplifierPosition, "amplifierPosition");
                int i = WhenMappings.$EnumSwitchMapping$0[amplifierPosition.ordinal()];
                if (i == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, (byte) (11 - value)});
                } else {
                    if (i != 2) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 2, (byte) (value + 11)});
                }
            }
        });
        final ParkPageUiSet parkPageUiSet = getParkPageUiSet(context);
        parkPageUiSet.setOnBackCameraStatusListener(new OnBackCameraStatusListener() { // from class: com.hzbhd.canbus.car._328.UiMgr$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.interfaces.OnBackCameraStatusListener
            public final void addViewToWindows() {
                UiMgr.m660lambda41$lambda39(parkPageUiSet, context);
            }
        });
        parkPageUiSet.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._328.UiMgr$$ExternalSyntheticLambda6
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public final void onClickItem(int i) {
                UiMgr.m661lambda41$lambda40(parkPageUiSet, i);
            }
        });
    }

    /* compiled from: UiMgr.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/hzbhd/canbus/car/_328/UiMgr$Companion;", "", "()V", "SHARE_IS_SUPPORT_PANORAMIC", "", "TAG", "panoramicCommand", "", "getPanoramicCommand", "()[B", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final byte[] getPanoramicCommand() {
            return UiMgr.panoramicCommand;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /* renamed from: lambda-37$lambda-29, reason: not valid java name */
    public static final void m655lambda37$lambda29(SettingPageUiSet settingPageUiSet, byte[] m0x83Command, Context context, UiMgr this$0, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(m0x83Command, "$m0x83Command");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        if (titleSrn != null) {
            int iHashCode = titleSrn.hashCode();
            switch (iHashCode) {
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
                default:
                    int i4 = 128;
                    switch (iHashCode) {
                        case -1144939710:
                            if (titleSrn.equals("outlander_simple_car_set_17")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, (byte) i3});
                                break;
                            }
                            break;
                        case -78472347:
                            if (titleSrn.equals("_328_00")) {
                                m0x83Command[2] = 0;
                                m0x83Command[3] = (byte) (i3 + 1);
                                CanbusMsgSender.sendMsg(m0x83Command);
                                break;
                            }
                            break;
                        case -78472345:
                            if (titleSrn.equals("_328_02")) {
                                if (i3 != 0) {
                                    if (i3 == 1) {
                                        i4 = 192;
                                    }
                                }
                                m0x83Command[3] = (byte) i4;
                                m0x83Command[2] = 2;
                                CanbusMsgSender.sendMsg(m0x83Command);
                                break;
                            }
                            break;
                        case -78472265:
                            if (titleSrn.equals("_328_1c")) {
                                m0x83Command[2] = 28;
                                m0x83Command[3] = (byte) (i3 + 1);
                                CanbusMsgSender.sendMsg(m0x83Command);
                                break;
                            }
                            break;
                        case 712683749:
                            if (titleSrn.equals("support_panorama")) {
                                SharePreUtil.setIntValue(context, SHARE_IS_SUPPORT_PANORAMIC, i3);
                                this$0.mMsgMgr.updateSettings("support_panorama", Integer.valueOf(i3));
                                break;
                            }
                            break;
                        case 808378399:
                            if (titleSrn.equals("_165_eco_mode")) {
                                m0x83Command[2] = 22;
                                m0x83Command[3] = (byte) ((i3 << 4) | 128);
                                CanbusMsgSender.sendMsg(m0x83Command);
                                break;
                            }
                            break;
                        default:
                            switch (iHashCode) {
                                case -609928118:
                                    if (titleSrn.equals("_103_car_setting_title_10")) {
                                        m0x83Command[2] = 10;
                                        m0x83Command[3] = (byte) ((i3 << 4) | 128);
                                        CanbusMsgSender.sendMsg(m0x83Command);
                                        break;
                                    }
                                    break;
                                case -609928117:
                                    if (titleSrn.equals("_103_car_setting_title_11")) {
                                        m0x83Command[2] = 11;
                                        m0x83Command[3] = (byte) ((i3 << 4) | 128);
                                        CanbusMsgSender.sendMsg(m0x83Command);
                                        break;
                                    }
                                    break;
                                case -609928116:
                                    if (titleSrn.equals("_103_car_setting_title_12")) {
                                        m0x83Command[2] = NfDef.AVRCP_EVENT_ID_UIDS_CHANGED;
                                        m0x83Command[3] = (byte) ((i3 << 4) | 128);
                                        CanbusMsgSender.sendMsg(m0x83Command);
                                        break;
                                    }
                                    break;
                                case -609928115:
                                    if (titleSrn.equals("_103_car_setting_title_13")) {
                                        m0x83Command[2] = NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED;
                                        m0x83Command[3] = (byte) ((i3 << 4) | 128);
                                        CanbusMsgSender.sendMsg(m0x83Command);
                                        break;
                                    }
                                    break;
                                case -609928114:
                                    if (titleSrn.equals("_103_car_setting_title_14")) {
                                        m0x83Command[2] = 14;
                                        m0x83Command[3] = (byte) ((i3 << 4) | 128);
                                        CanbusMsgSender.sendMsg(m0x83Command);
                                        break;
                                    }
                                    break;
                                case -609928113:
                                    if (titleSrn.equals("_103_car_setting_title_15")) {
                                        m0x83Command[2] = 15;
                                        m0x83Command[3] = (byte) ((i3 << 4) | 128);
                                        CanbusMsgSender.sendMsg(m0x83Command);
                                        break;
                                    }
                                    break;
                                case -609928112:
                                    if (titleSrn.equals("_103_car_setting_title_16")) {
                                        m0x83Command[2] = 16;
                                        m0x83Command[3] = (byte) ((i3 << 4) | 128);
                                        CanbusMsgSender.sendMsg(m0x83Command);
                                        break;
                                    }
                                    break;
                                case -609928111:
                                    if (titleSrn.equals("_103_car_setting_title_17")) {
                                        m0x83Command[2] = 17;
                                        m0x83Command[3] = (byte) ((i3 << 4) | 128);
                                        CanbusMsgSender.sendMsg(m0x83Command);
                                        break;
                                    }
                                    break;
                                case -609928110:
                                    if (titleSrn.equals("_103_car_setting_title_18")) {
                                        m0x83Command[2] = 18;
                                        m0x83Command[3] = (byte) ((i3 << 4) | 128);
                                        CanbusMsgSender.sendMsg(m0x83Command);
                                        break;
                                    }
                                    break;
                                case -609928109:
                                    if (titleSrn.equals("_103_car_setting_title_19")) {
                                        m0x83Command[2] = 19;
                                        m0x83Command[3] = (byte) ((i3 << 4) | 128);
                                        CanbusMsgSender.sendMsg(m0x83Command);
                                        break;
                                    }
                                    break;
                                default:
                                    switch (iHashCode) {
                                        case -609928087:
                                            if (titleSrn.equals("_103_car_setting_title_20")) {
                                                m0x83Command[2] = 20;
                                                m0x83Command[3] = (byte) ((i3 << 4) | 128);
                                                CanbusMsgSender.sendMsg(m0x83Command);
                                                break;
                                            }
                                            break;
                                        case -609928086:
                                            if (titleSrn.equals("_103_car_setting_title_21")) {
                                                m0x83Command[2] = 21;
                                                m0x83Command[3] = (byte) ((i3 << 4) | 128);
                                                CanbusMsgSender.sendMsg(m0x83Command);
                                                break;
                                            }
                                            break;
                                        case -609928085:
                                            if (titleSrn.equals("_103_car_setting_title_22")) {
                                                m0x83Command[2] = 23;
                                                m0x83Command[3] = (byte) ((i3 << 4) | 128);
                                                CanbusMsgSender.sendMsg(m0x83Command);
                                                break;
                                            }
                                            break;
                                        case -609928084:
                                            if (titleSrn.equals("_103_car_setting_title_23")) {
                                                m0x83Command[2] = 24;
                                                m0x83Command[3] = (byte) ((i3 << 4) | 128);
                                                CanbusMsgSender.sendMsg(m0x83Command);
                                                break;
                                            }
                                            break;
                                        case -609928083:
                                            if (titleSrn.equals("_103_car_setting_title_24")) {
                                                m0x83Command[2] = 25;
                                                m0x83Command[3] = (byte) ((i3 << 4) | 128);
                                                CanbusMsgSender.sendMsg(m0x83Command);
                                                break;
                                            }
                                            break;
                                        case -609928082:
                                            if (titleSrn.equals("_103_car_setting_title_25")) {
                                                m0x83Command[2] = 26;
                                                m0x83Command[3] = (byte) ((i3 << 4) | 128);
                                                CanbusMsgSender.sendMsg(m0x83Command);
                                                break;
                                            }
                                            break;
                                        case -609928081:
                                            if (titleSrn.equals("_103_car_setting_title_26")) {
                                                m0x83Command[2] = 27;
                                                m0x83Command[3] = (byte) ((i3 << 4) | 128);
                                                CanbusMsgSender.sendMsg(m0x83Command);
                                                break;
                                            }
                                            break;
                                        default:
                                            switch (iHashCode) {
                                                case 118872230:
                                                    if (titleSrn.equals("_103_car_setting_title_1")) {
                                                        m0x83Command[2] = 1;
                                                        m0x83Command[3] = (byte) ((i3 << 4) | 128);
                                                        CanbusMsgSender.sendMsg(m0x83Command);
                                                        break;
                                                    }
                                                    break;
                                                case 118872231:
                                                    if (titleSrn.equals("_103_car_setting_title_2")) {
                                                        m0x83Command[2] = 3;
                                                        m0x83Command[3] = (byte) ((i3 << 4) | 128);
                                                        CanbusMsgSender.sendMsg(m0x83Command);
                                                        break;
                                                    }
                                                    break;
                                                case 118872232:
                                                    if (titleSrn.equals("_103_car_setting_title_3")) {
                                                        m0x83Command[2] = 4;
                                                        m0x83Command[3] = (byte) ((i3 << 4) | 128);
                                                        CanbusMsgSender.sendMsg(m0x83Command);
                                                        break;
                                                    }
                                                    break;
                                                case 118872233:
                                                    if (titleSrn.equals("_103_car_setting_title_4")) {
                                                        m0x83Command[2] = 5;
                                                        m0x83Command[3] = (byte) ((i3 << 4) | 128);
                                                        CanbusMsgSender.sendMsg(m0x83Command);
                                                        break;
                                                    }
                                                    break;
                                                case 118872234:
                                                    if (titleSrn.equals("_103_car_setting_title_5")) {
                                                        m0x83Command[2] = 6;
                                                        m0x83Command[3] = (byte) ((i3 << 4) | 128);
                                                        CanbusMsgSender.sendMsg(m0x83Command);
                                                        break;
                                                    }
                                                    break;
                                                case 118872235:
                                                    if (titleSrn.equals("_103_car_setting_title_6")) {
                                                        m0x83Command[2] = 7;
                                                        m0x83Command[3] = (byte) ((i3 << 4) | 128);
                                                        CanbusMsgSender.sendMsg(m0x83Command);
                                                        break;
                                                    }
                                                    break;
                                                case 118872236:
                                                    if (titleSrn.equals("_103_car_setting_title_7")) {
                                                        m0x83Command[2] = 8;
                                                        m0x83Command[3] = (byte) ((i3 << 4) | 128);
                                                        CanbusMsgSender.sendMsg(m0x83Command);
                                                        break;
                                                    }
                                                    break;
                                                case 118872237:
                                                    if (titleSrn.equals("_103_car_setting_title_8")) {
                                                        m0x83Command[2] = 9;
                                                        m0x83Command[3] = (byte) ((i3 << 4) | 128);
                                                        CanbusMsgSender.sendMsg(m0x83Command);
                                                        break;
                                                    }
                                                    break;
                                                default:
                                                    switch (iHashCode) {
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
                            }
                    }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-37$lambda-30, reason: not valid java name */
    public static final void m656lambda37$lambda30(SettingPageUiSet settingPageUiSet, UiMgr this$0, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (Intrinsics.areEqual(settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn(), "amplifier_switch")) {
            this$0.mMsgMgr.setAmplifierSwitch(i3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-37$lambda-31, reason: not valid java name */
    public static final void m657lambda37$lambda31(SettingPageUiSet settingPageUiSet, int i, int i2, int i3) {
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
    /* renamed from: lambda-37$lambda-35, reason: not valid java name */
    public static final void m658lambda37$lambda35(SettingPageUiSet settingPageUiSet, byte[] m0x83Command, int i, int i2) {
        Intrinsics.checkNotNullParameter(m0x83Command, "$m0x83Command");
        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        if (titleSrn != null) {
            int iHashCode = titleSrn.hashCode();
            switch (iHashCode) {
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
                default:
                    switch (iHashCode) {
                        case -78472264:
                            if (titleSrn.equals("_328_1d")) {
                                m0x83Command[2] = 29;
                                m0x83Command[3] = 1;
                                CanbusMsgSender.sendMsg(m0x83Command);
                                break;
                            }
                            break;
                        case -78472263:
                            if (titleSrn.equals("_328_1e")) {
                                m0x83Command[2] = 30;
                                m0x83Command[3] = 1;
                                CanbusMsgSender.sendMsg(m0x83Command);
                                break;
                            }
                            break;
                        case -78472262:
                            if (titleSrn.equals("_328_1f")) {
                                m0x83Command[2] = 31;
                                m0x83Command[3] = 1;
                                CanbusMsgSender.sendMsg(m0x83Command);
                                break;
                            }
                            break;
                    }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-37$lambda-36, reason: not valid java name */
    public static final void m659lambda37$lambda36(SettingPageUiSet settingPageUiSet, int i, int i2) {
        if (Intrinsics.areEqual(settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn(), "_23_enter_panoramic")) {
            CanbusMsgSender.sendMsg(panoramicCommand);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-41$lambda-39, reason: not valid java name */
    public static final void m660lambda41$lambda39(ParkPageUiSet parkPageUiSet, Context context) {
        Intrinsics.checkNotNullParameter(context, "$context");
        parkPageUiSet.setShowPanoramic(SharePreUtil.getIntValue(context, SHARE_IS_SUPPORT_PANORAMIC, 0) == 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-41$lambda-40, reason: not valid java name */
    public static final void m661lambda41$lambda40(ParkPageUiSet parkPageUiSet, int i) {
        if (Intrinsics.areEqual(parkPageUiSet.getPanoramicBtnList().get(i).getTitleSrn(), "_328_panoramic_switch")) {
            CanbusMsgSender.sendMsg(panoramicCommand);
        }
    }
}
