package com.hzbhd.canbus.car._334;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDevicePageStatusListener;
import com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.OriginalBtnAction;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.constant.disc.MpegConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlinx.coroutines.scheduling.WorkQueueKt;


public class UiMgr extends AbstractUiMgr {
    public List<OriginalCarDevicePageUiSet.Item> cdPageList;
    public List<OriginalCarDevicePageUiSet.Item> dvdPageList;
    private final int id;
    private String leftTitle;
    private MsgMgr msgMgr;
    public HashMap<Integer, OriginalDeviceData> pageMap;
    public List<OriginalCarDevicePageUiSet.Item> radioPageList;
    private String rightTitle;
    public HashMap<String, Integer> settingPageIndex;
    private final SettingPageUiSet settingPageUiSet;
    public final boolean haveProblem = false;
    public HashMap<Integer, List<Integer>> cmd_carId_Map = new HashMap<>();

    private int getDownValue(int i) {
        return (i & 255) | 128;
    }

    private byte mediumAndHigh(int i) {
        if (i == 0) {
            return (byte) 0;
        }
        return i == 2 ? (byte) 1 : (byte) 2;
    }

    public UiMgr(final Context context) {
        initIdMap();
        this.id = getCurrentCarId();
        this.msgMgr = getMsgMgr(context);
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        this.settingPageUiSet = settingUiSet;
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._334.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public final void onClickItem(int i, int i2, int i3) {
                this.f$0.m702lambda$new$0$comhzbhdcanbuscar_334UiMgr(i, i2, i3);
            }
        });
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._334.UiMgr.1
            private int day;
            private int month;
            private int year;

            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
            java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
            	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
            	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
            	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
            	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
             */
            /* JADX WARN: Removed duplicated region for block: B:4:0x007a  */
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onClickItem(int r12, int r13, int r14) {
                /*
                    Method dump skipped, instructions count: 836
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._334.UiMgr.AnonymousClass1.onClickItem(int, int, int):void");
            }
        });
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        amplifierPageUiSet.setOnAmplifierPositionListener(new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._334.UiMgr$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
            public final void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
                UiMgr.lambda$new$1(amplifierPosition, i);
            }
        });
        amplifierPageUiSet.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._334.UiMgr$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
            public final void progress(AmplifierActivity.AmplifierBand amplifierBand, int i) {
                this.f$0.m703lambda$new$2$comhzbhdcanbuscar_334UiMgr(amplifierBand, i);
            }
        });
        OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(context);
        originalCarDevicePageUiSet.setOnOriginalCarDevicePageStatusListener(new OnOriginalCarDevicePageStatusListener() { // from class: com.hzbhd.canbus.car._334.UiMgr$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnOriginalCarDevicePageStatusListener
            public final void onStatusChange(int i) {
                this.f$0.m704lambda$new$3$comhzbhdcanbuscar_334UiMgr(context, i);
            }
        });
        originalCarDevicePageUiSet.setOnOriginalTopBtnClickListeners(new OnOriginalTopBtnClickListener() { // from class: com.hzbhd.canbus.car._334.UiMgr$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener
            public final void onClickTopBtnItem(int i) {
                this.f$0.m705lambda$new$4$comhzbhdcanbuscar_334UiMgr(i);
            }
        });
        originalCarDevicePageUiSet.setOnOriginalBottomBtnClickListeners(new OnOriginalBottomBtnClickListener() { // from class: com.hzbhd.canbus.car._334.UiMgr$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener
            public final void onClickBottomBtnItem(int i) {
                this.f$0.m706lambda$new$5$comhzbhdcanbuscar_334UiMgr(i);
            }
        });
        originalCarDevicePageUiSet.setOnOriginalSongItemClickListener(new OnOriginalSongItemClickListener() { // from class: com.hzbhd.canbus.car._334.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener
            public void onSongItemLongClick(int i) {
            }

            @Override // com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener
            public void onSongItemClick(int i) {
                int i2 = i + 1;
                byte b = (byte) (65280 & i2);
                byte b2 = (byte) (i2 & 255);
                CanbusMsgSender.sendMsg(new byte[]{22, -96, 15, b, b2});
                CanbusMsgSender.sendMsg(new byte[]{22, -96, -126, b, b2});
            }
        });
        getParkPageUiSet(context).setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._334.UiMgr$$ExternalSyntheticLambda6
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public final void onClickItem(int i) {
                UiMgr.lambda$new$6(i);
            }
        });
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 112});
        this.settingPageIndex = new HashMap<>();
        this.pageMap = new HashMap<>();
        initSettingPageIndex();
        initOriginalDevice();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /* JADX WARN: Removed duplicated region for block: B:4:0x0077  */
    /* renamed from: lambda$new$0$com-hzbhd-canbus-car-_334-UiMgr, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    /* synthetic */ void m702lambda$new$0$comhzbhdcanbuscar_334UiMgr(int r25, int r26, int r27) {
        /*
            Method dump skipped, instructions count: 3179
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._334.UiMgr.m702lambda$new$0$comhzbhdcanbuscar_334UiMgr(int, int, int):void");
    }

    /* renamed from: com.hzbhd.canbus.car._334.UiMgr$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand;
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition;

        static {
            int[] iArr = new int[AmplifierActivity.AmplifierPosition.values().length];
            $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition = iArr;
            try {
                iArr[AmplifierActivity.AmplifierPosition.LEFT_RIGHT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[AmplifierActivity.AmplifierPosition.FRONT_REAR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[AmplifierActivity.AmplifierBand.values().length];
            $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand = iArr2;
            try {
                iArr2[AmplifierActivity.AmplifierBand.VOLUME.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.TREBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.BASS.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.CUSTOM_2_BASS.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    static /* synthetic */ void lambda$new$1(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
        int i2 = AnonymousClass3.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[amplifierPosition.ordinal()];
        if (i2 == 1) {
            if (i > GeneralAmplifierData.leftRight) {
                CanbusMsgSender.sendMsg(new byte[]{22, -96, 4, 1});
                return;
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, -96, 4, -127});
                return;
            }
        }
        if (i2 != 2) {
            return;
        }
        if (i > GeneralAmplifierData.frontRear) {
            CanbusMsgSender.sendMsg(new byte[]{22, -96, 3, 1});
        } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -96, 3, -127});
        }
    }

    /* renamed from: lambda$new$2$com-hzbhd-canbus-car-_334-UiMgr, reason: not valid java name */
    /* synthetic */ void m703lambda$new$2$comhzbhdcanbuscar_334UiMgr(AmplifierActivity.AmplifierBand amplifierBand, int i) {
        int i2 = AnonymousClass3.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[amplifierBand.ordinal()];
        if (i2 == 1) {
            int i3 = i - GeneralAmplifierData.volume;
            if (i > GeneralAmplifierData.volume) {
                CanbusMsgSender.sendMsg(new byte[]{22, -96, 6, (byte) i3});
                return;
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, -96, 6, (byte) getDownValue(-i3)});
                return;
            }
        }
        if (i2 == 2) {
            int i4 = i - GeneralAmplifierData.bandTreble;
            if (i > GeneralAmplifierData.bandTreble) {
                CanbusMsgSender.sendMsg(new byte[]{22, -96, 2, (byte) i4});
                return;
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, -96, 2, (byte) getDownValue(-i4)});
                return;
            }
        }
        if (i2 == 3) {
            int i5 = i - GeneralAmplifierData.bandBass;
            if (i > GeneralAmplifierData.bandBass) {
                CanbusMsgSender.sendMsg(new byte[]{22, -96, 1, (byte) i5});
                return;
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, -96, 1, (byte) getDownValue(-i5)});
                return;
            }
        }
        if (i2 != 4) {
            return;
        }
        int i6 = i - GeneralAmplifierData.custom2Bass;
        if (i > GeneralAmplifierData.custom2Bass) {
            CanbusMsgSender.sendMsg(new byte[]{22, 5, 1, (byte) i6});
        } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 5, 1, (byte) getDownValue(-i6)});
        }
    }

    /* renamed from: lambda$new$3$com-hzbhd-canbus-car-_334-UiMgr, reason: not valid java name */
    /* synthetic */ void m704lambda$new$3$comhzbhdcanbuscar_334UiMgr(Context context, int i) {
        if (((List) Objects.requireNonNull(this.cmd_carId_Map.get(96))).contains(Integer.valueOf(this.id)) && !((List) Objects.requireNonNull(this.cmd_carId_Map.get(112))).contains(Integer.valueOf(this.id))) {
            this.msgMgr.setCdPage(context);
        } else if (((List) Objects.requireNonNull(this.cmd_carId_Map.get(96))).contains(Integer.valueOf(this.id)) && ((List) Objects.requireNonNull(this.cmd_carId_Map.get(112))).contains(Integer.valueOf(this.id))) {
            this.msgMgr.setRadioPage(context);
        } else {
            this.msgMgr.setRadioPage(context);
        }
    }

    /* renamed from: lambda$new$4$com-hzbhd-canbus-car-_334-UiMgr, reason: not valid java name */
    /* synthetic */ void m705lambda$new$4$comhzbhdcanbuscar_334UiMgr(int i) {
        if (this.msgMgr.pageFlag.equals("CD")) {
            if (!((List) Objects.requireNonNull(this.cmd_carId_Map.get(33488))).contains(Integer.valueOf(this.msgMgr.currentCanDifferentId))) {
                if (i == 0) {
                    if (GeneralOriginalCarDeviceData.rpt) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, 8});
                        return;
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, 6});
                        return;
                    }
                }
                if (i != 1) {
                    return;
                }
                if (GeneralOriginalCarDeviceData.rdm) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, 11});
                    return;
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, 10});
                    return;
                }
            }
            if (i == 0) {
                if (GeneralOriginalCarDeviceData.rpt) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, -120});
                    return;
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, -121});
                    return;
                }
            }
            if (i == 1) {
                if (GeneralOriginalCarDeviceData.rdm) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, -122});
                    return;
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, -123});
                    return;
                }
            }
            if (i != 2) {
                return;
            }
            if (GeneralOriginalCarDeviceData.scan) {
                CanbusMsgSender.sendMsg(new byte[]{22, -126, -118});
                return;
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, -126, -119});
                return;
            }
        }
        if (this.msgMgr.pageFlag.equals("Radio") && i == 0) {
            if (GeneralOriginalCarDeviceData.refresh) {
                CanbusMsgSender.sendMsg(new byte[]{22, -94, 0});
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, -94, 1});
            }
        }
    }

    /* renamed from: lambda$new$5$com-hzbhd-canbus-car-_334-UiMgr, reason: not valid java name */
    /* synthetic */ void m706lambda$new$5$comhzbhdcanbuscar_334UiMgr(int i) {
        if (this.msgMgr.pageFlag.equals("CD")) {
            if (((List) Objects.requireNonNull(this.cmd_carId_Map.get(33488))).contains(Integer.valueOf(this.msgMgr.currentCanDifferentId))) {
                if (i == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, -125});
                    return;
                }
                if (i == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, ByteCompanionObject.MIN_VALUE});
                    return;
                } else if (i == 2) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, -127});
                    return;
                } else {
                    if (i != 3) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, -124});
                    return;
                }
            }
            if (i == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -126, 3});
                return;
            }
            if (i == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -126, 5});
                return;
            }
            if (i == 2) {
                CanbusMsgSender.sendMsg(new byte[]{22, -126, 0});
                return;
            }
            if (i == 3) {
                CanbusMsgSender.sendMsg(new byte[]{22, -126, 1});
                return;
            } else if (i == 4) {
                CanbusMsgSender.sendMsg(new byte[]{22, -126, 4});
                return;
            } else {
                if (i != 5) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -126, 2});
                return;
            }
        }
        if (this.msgMgr.pageFlag.equals("Radio")) {
            if (i == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -94, 8});
                return;
            }
            if (i == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -94, 4});
                return;
            }
            if (i == 2) {
                CanbusMsgSender.sendMsg(new byte[]{22, -94, 2});
                return;
            }
            if (i == 3) {
                CanbusMsgSender.sendMsg(new byte[]{22, -94, 3});
                CanbusMsgSender.sendMsg(new byte[]{22, -94, 5});
                CanbusMsgSender.sendMsg(new byte[]{22, -94, 7});
            } else if (i == 4) {
                CanbusMsgSender.sendMsg(new byte[]{22, -94, 6});
            } else {
                if (i != 5) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -94, 9});
            }
        }
    }

    static /* synthetic */ void lambda$new$6(int i) {
        if (i != 0) {
            return;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -121, 1, 1});
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        if (this.id != 11) {
            removeSettingLeftItemByNameList(context, new String[]{"_334_environmental_lighting_title"});
        }
        if (!((List) Objects.requireNonNull(this.cmd_carId_Map.get(38))).contains(Integer.valueOf(this.id))) {
            removeSettingLeftItemByNameList(context, new String[]{"_334_next_service"});
        }
        if (!((List) Objects.requireNonNull(this.cmd_carId_Map.get(49))).contains(Integer.valueOf(this.id))) {
            removeSettingLeftItemByNameList(context, new String[]{"_334_maintenance_info"});
        }
        if (!((List) Objects.requireNonNull(this.cmd_carId_Map.get(116))).contains(Integer.valueOf(this.id))) {
            removeSettingLeftItemByNameList(context, new String[]{"_334_security_setting_status_info"});
        }
        if (!((List) Objects.requireNonNull(this.cmd_carId_Map.get(64))).contains(Integer.valueOf(this.id))) {
            removeSettingLeftItemByNameList(context, new String[]{"_334_hud"});
        }
        if (this.id != 12) {
            removeSettingRightItemByNameList(context, new String[]{"_334_tail_door"});
        }
        if (!((List) Objects.requireNonNull(this.cmd_carId_Map.get(38))).contains(Integer.valueOf(this.id))) {
            removeSettingRightItemByNameList(context, new String[]{"_334_rear_window_demister"});
            removeSettingRightItemByNameList(context, new String[]{"_334_automatic_lock_time"});
            removeSettingRightItemByNameList(context, new String[]{"_334_rear_vision_mirror"});
            removeSettingRightItemByNameList(context, new String[]{"_334_notification_warning"});
            removeSettingRightItemByNameList(context, new String[]{"_334_Instrument_custom_display"});
        }
        if (!((List) Objects.requireNonNull(this.cmd_carId_Map.get(38))).contains(Integer.valueOf(this.id))) {
            removeMainPrjBtnByName(context, MainAction.TIRE_INFO);
        }
        if (!((List) Objects.requireNonNull(this.cmd_carId_Map.get(65))).contains(Integer.valueOf(this.id))) {
            removeMainPrjBtnByName(context, MainAction.SETTING);
        }
        if (!((List) Objects.requireNonNull(this.cmd_carId_Map.get(96))).contains(Integer.valueOf(this.id)) && !((List) Objects.requireNonNull(this.cmd_carId_Map.get(112))).contains(Integer.valueOf(this.id))) {
            removeMainPrjBtnByName(context, MainAction.ORIGINAL_CAR_DEVICE);
        }
        if (!((List) Objects.requireNonNull(this.cmd_carId_Map.get(112))).contains(Integer.valueOf(this.id))) {
            removeMainPrjBtnByName(context, MainAction.AMPLIFIER);
        }
        int i = this.id;
        if (i == 4 || i == 11) {
            removeMainPrjBtnByName(context, MainAction.ORIGINAL_CAR_DEVICE);
        }
        if (!((List) Objects.requireNonNull(this.cmd_carId_Map.get(38))).contains(Integer.valueOf(this.id))) {
            removeDriveData(context, "_334_update_time");
        }
        if (!((List) Objects.requireNonNull(this.cmd_carId_Map.get(65))).contains(Integer.valueOf(this.id))) {
            removeDriveData(context, "_334_fuel_info_1");
            removeDriveData(context, "_334_fuel_info_9");
        }
        if (((List) Objects.requireNonNull(this.cmd_carId_Map.get(82))).contains(Integer.valueOf(this.id))) {
            return;
        }
        removeDriveData(context, "_334_i_stop_information");
    }

    private MsgMgr getMsgMgr(Context context) {
        if (this.msgMgr == null) {
            this.msgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.msgMgr;
    }

    private void initIdMap() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(11);
        arrayList.add(5);
        arrayList.add(6);
        arrayList.add(7);
        arrayList.add(8);
        arrayList.add(9);
        arrayList.add(12);
        this.cmd_carId_Map.put(33, arrayList);
        this.cmd_carId_Map.put(34, arrayList);
        this.cmd_carId_Map.put(35, arrayList);
        this.cmd_carId_Map.put(40, arrayList);
        this.cmd_carId_Map.put(41, arrayList);
        this.cmd_carId_Map.put(65, arrayList);
        this.cmd_carId_Map.put(80, arrayList);
        this.cmd_carId_Map.put(81, arrayList);
        this.cmd_carId_Map.put(82, arrayList);
        this.cmd_carId_Map.put(Integer.valueOf(WorkQueueKt.MASK), arrayList);
        this.cmd_carId_Map.put(129, arrayList);
        this.cmd_carId_Map.put(144, arrayList);
        this.cmd_carId_Map.put(131, arrayList);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(10);
        arrayList2.add(12);
        this.cmd_carId_Map.put(37, arrayList2);
        this.cmd_carId_Map.put(38, arrayList2);
        this.cmd_carId_Map.put(39, arrayList2);
        this.cmd_carId_Map.put(48, arrayList2);
        this.cmd_carId_Map.put(133, arrayList2);
        this.cmd_carId_Map.put(2215942, arrayList2);
        this.cmd_carId_Map.put(2215952, arrayList2);
        this.cmd_carId_Map.put(33558, arrayList2);
        this.cmd_carId_Map.put(33559, arrayList2);
        this.cmd_carId_Map.put(33560, arrayList2);
        this.cmd_carId_Map.put(33561, arrayList2);
        this.cmd_carId_Map.put(33562, arrayList2);
        this.cmd_carId_Map.put(33563, arrayList2);
        this.cmd_carId_Map.put(10449, arrayList2);
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(2);
        arrayList3.add(4);
        arrayList3.add(11);
        arrayList3.add(8);
        arrayList3.add(12);
        this.cmd_carId_Map.put(36, arrayList3);
        ArrayList arrayList4 = new ArrayList();
        arrayList4.add(5);
        arrayList4.add(6);
        arrayList4.add(7);
        arrayList4.add(8);
        arrayList4.add(9);
        arrayList4.add(11);
        arrayList4.add(12);
        this.cmd_carId_Map.put(64, arrayList4);
        this.cmd_carId_Map.put(116, arrayList4);
        this.cmd_carId_Map.put(132, arrayList4);
        this.cmd_carId_Map.put(Integer.valueOf(MpegConstantsDef.MPEG_INFO_ANGLE_CFM), arrayList4);
        ArrayList arrayList5 = new ArrayList();
        arrayList5.add(1);
        arrayList5.add(3);
        arrayList5.add(4);
        arrayList5.add(11);
        arrayList5.add(5);
        arrayList5.add(6);
        arrayList5.add(7);
        arrayList5.add(8);
        arrayList5.add(9);
        this.cmd_carId_Map.put(96, arrayList5);
        this.cmd_carId_Map.put(97, arrayList5);
        this.cmd_carId_Map.put(98, arrayList5);
        this.cmd_carId_Map.put(99, arrayList5);
        this.cmd_carId_Map.put(130, arrayList5);
        this.cmd_carId_Map.put(Integer.valueOf(HotKeyConstant.K_SLEEP), arrayList5);
        ArrayList arrayList6 = new ArrayList();
        arrayList6.add(5);
        arrayList6.add(6);
        arrayList6.add(7);
        arrayList6.add(8);
        arrayList6.add(9);
        this.cmd_carId_Map.put(112, arrayList6);
        this.cmd_carId_Map.put(113, arrayList6);
        this.cmd_carId_Map.put(114, arrayList6);
        this.cmd_carId_Map.put(115, arrayList6);
        this.cmd_carId_Map.put(160, arrayList6);
        this.cmd_carId_Map.put(Integer.valueOf(MpegConstantsDef.MPEG_INFO_DISCTYPE_CFM), arrayList6);
        this.cmd_carId_Map.put(Integer.valueOf(MpegConstantsDef.MPEG_INFO_AUDIO_CFM), arrayList6);
        ArrayList arrayList7 = new ArrayList();
        arrayList7.add(11);
        this.cmd_carId_Map.put(49, arrayList7);
        this.cmd_carId_Map.put(135, arrayList7);
        ArrayList arrayList8 = new ArrayList();
        arrayList8.add(1);
        arrayList8.add(3);
        arrayList8.add(5);
        arrayList8.add(6);
        arrayList8.add(7);
        arrayList8.add(8);
        arrayList8.add(9);
        this.cmd_carId_Map.put(33488, arrayList8);
    }

    private void initOriginalDevice() {
        ArrayList arrayList = new ArrayList();
        this.cdPageList = arrayList;
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_music", "_334_total_repertoire", null));
        this.cdPageList.add(new OriginalCarDevicePageUiSet.Item("music_music", "_334_current_track", null));
        ArrayList arrayList2 = new ArrayList();
        this.dvdPageList = arrayList2;
        arrayList2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_334_repetition_mode", null));
        this.dvdPageList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_334_random_mode", null));
        this.dvdPageList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_334_scan_mode", null));
        this.dvdPageList.add(new OriginalCarDevicePageUiSet.Item("music_music", "_334_total_repertoire", null));
        this.dvdPageList.add(new OriginalCarDevicePageUiSet.Item("music_music", "_334_current_track", null));
        this.dvdPageList.add(new OriginalCarDevicePageUiSet.Item("music_music", "_334_total_time", null));
        this.dvdPageList.add(new OriginalCarDevicePageUiSet.Item("music_music", "_334_current_time", null));
        ArrayList arrayList3 = new ArrayList();
        this.radioPageList = arrayList3;
        arrayList3.add(new OriginalCarDevicePageUiSet.Item("music_music", "_186_band", null));
        this.radioPageList.add(new OriginalCarDevicePageUiSet.Item("music_music", "_334_current_frequency_point", null));
        if (((List) Objects.requireNonNull(this.cmd_carId_Map.get(33488))).contains(Integer.valueOf(this.id))) {
            this.pageMap.put(0, new OriginalDeviceData(this.cdPageList, new String[]{"up", OriginalBtnAction.PLAY, OriginalBtnAction.PAUSE, "down"}, new String[]{OriginalBtnAction.RPT, OriginalBtnAction.RDM, OriginalBtnAction.SCAN}));
        } else {
            this.pageMap.put(0, new OriginalDeviceData(this.cdPageList, new String[]{OriginalBtnAction.PREV_DISC, "up", OriginalBtnAction.PLAY, OriginalBtnAction.PAUSE, "down", OriginalBtnAction.NEXT_DISC}, new String[]{OriginalBtnAction.RPT, OriginalBtnAction.RDM}));
        }
        this.pageMap.put(1, new OriginalDeviceData(this.dvdPageList, new String[]{OriginalBtnAction.PREV_DISC, "left", OriginalBtnAction.PLAY, OriginalBtnAction.PAUSE, OriginalBtnAction.SELECT_CD, "right", OriginalBtnAction.NEXT_DISC}));
        this.pageMap.put(2, new OriginalDeviceData(this.radioPageList, new String[]{"left", "up", OriginalBtnAction.RADIO_SCAN, OriginalBtnAction.STOP, "down", "right"}, new String[]{OriginalBtnAction.REFRESH}));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int dateDeal(int i, int i2) {
        if (i2 == 2) {
            return ((i % 4 != 0 || i % 100 == 0) && i % 400 != 0) ? 28 : 29;
        }
        switch (i2) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
            default:
                return 31;
            case 2:
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
        }
    }

    public Map<String, Integer> initSettingPageIndex() {
        List<SettingPageUiSet.ListBean> list = this.settingPageUiSet.getList();
        Iterator<SettingPageUiSet.ListBean> it = list.iterator();
        for (int i = 0; i < list.size(); i++) {
            SettingPageUiSet.ListBean next = it.next();
            this.settingPageIndex.put(next.getTitleSrn(), Integer.valueOf(i));
            List<SettingPageUiSet.ListBean.ItemListBean> itemList = next.getItemList();
            Iterator<SettingPageUiSet.ListBean.ItemListBean> it2 = itemList.iterator();
            for (int i2 = 0; i2 < itemList.size(); i2++) {
                this.settingPageIndex.put(it2.next().getTitleSrn(), Integer.valueOf(i2));
            }
        }
        return this.settingPageIndex;
    }

    protected int getDrivingPageIndexes(Context context, String str) {
        List<DriverDataPageUiSet.Page> list = getPageUiSet(context).getDriverDataPageUiSet().getList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getHeadTitleSrn().equals(str)) {
                return i;
            }
        }
        return -1;
    }

    protected int getDrivingItemIndexes(Context context, String str) {
        List<DriverDataPageUiSet.Page> list = getPageUiSet(context).getDriverDataPageUiSet().getList();
        for (int i = 0; i < list.size(); i++) {
            Iterator<DriverDataPageUiSet.Page> it = list.iterator();
            while (it.hasNext()) {
                List<DriverDataPageUiSet.Page.Item> itemList = it.next().getItemList();
                for (int i2 = 0; i2 < itemList.size(); i2++) {
                    if (itemList.get(i2).getTitleSrn().equals(str)) {
                        return i2;
                    }
                }
            }
        }
        return -1;
    }
}
