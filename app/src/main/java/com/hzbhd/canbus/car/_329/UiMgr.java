package com.hzbhd.canbus.car._329;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.interfaces.OnBubbleClickListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AirBtnAction;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.constant.share.lcd.LcdInfoShare;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import nfore.android.bt.res.NfDef;




public final class UiMgr extends AbstractUiMgr {
    public static final String SHARE_329_IS_SUPPORT_PANORAMIC = "share_329_is_support_panoramic";
    private static final String TAG = "_329_UiMgr";
    private final Handler mHandler;
    private final MsgMgr mMsgMgr;

    public UiMgr(final Context context) {

        this.mHandler = new Handler(Looper.getMainLooper());
        MsgMgrInterface canMsgMgr = MsgMgrFactory.getCanMsgMgr(context);

        this.mMsgMgr = (MsgMgr) canMsgMgr;
        final byte[] bArr = {22, -3, 0, 0};
        final FrontArea frontArea = getAirUiSet(context).getFrontArea();
        frontArea.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._329.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m696lambda5$lambda4$lambda0(this.f$0, frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._329.UiMgr$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m697lambda5$lambda4$lambda1(this.f$0, frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._329.UiMgr$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m698lambda5$lambda4$lambda2(this.f$0, frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._329.UiMgr$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m699lambda5$lambda4$lambda3(this.f$0, frontArea, i);
            }
        }});
        frontArea.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._329.UiMgr$1$1$5
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                this.this$0.sendAirCommand(13);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                this.this$0.sendAirCommand(14);
            }
        }, null, new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._329.UiMgr$1$1$6
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                this.this$0.sendAirCommand(15);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                this.this$0.sendAirCommand(16);
            }
        }});
        frontArea.setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._329.UiMgr$1$1$7
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                this.this$0.sendAirCommand(12);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                this.this$0.sendAirCommand(11);
            }
        });
        frontArea.setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._329.UiMgr$1$1$8
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                this.this$0.sendAirCommand(17);
            }
        }, new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._329.UiMgr$1$1$9
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                this.this$0.sendAirCommand(18);
            }
        }, null, null});
        final SettingPageUiSet settingUiSet = getSettingUiSet(context);
        final byte[] bArr2 = {22, -116, 0, 0};
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._329.UiMgr$$ExternalSyntheticLambda6
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public final void onClickItem(int i, int i2, int i3) {
                UiMgr.m689lambda24$lambda12(settingUiSet, bArr2, context, this, i, i2, i3);
            }
        });
        settingUiSet.setOnSettingItemSwitchListener(new OnSettingItemSwitchListener() { // from class: com.hzbhd.canbus.car._329.UiMgr$$ExternalSyntheticLambda7
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener
            public final void onSwitch(int i, int i2, int i3) {
                UiMgr.m690lambda24$lambda17(settingUiSet, bArr2, i, i2, i3);
            }
        });
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._329.UiMgr$$ExternalSyntheticLambda8
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public final void onClickItem(int i, int i2, int i3) {
                UiMgr.m691lambda24$lambda20(settingUiSet, bArr2, i, i2, i3);
            }
        });
        settingUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._329.UiMgr$$ExternalSyntheticLambda9
            @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
            public final void onConfirmClick(int i, int i2) {
                UiMgr.m692lambda24$lambda21(settingUiSet, i, i2);
            }
        });
        settingUiSet.setOnSettingItemClickListener(new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._329.UiMgr$$ExternalSyntheticLambda10
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
            public final void onClickItem(int i, int i2) {
                UiMgr.m693lambda24$lambda23(settingUiSet, bArr, i, i2);
            }
        });
        final ParkPageUiSet parkPageUiSet = getParkPageUiSet(context);
        parkPageUiSet.setShowPanoramic(SharePreUtil.getIntValue(context, SHARE_329_IS_SUPPORT_PANORAMIC, 0) == 1);
        if (parkPageUiSet.isShowPanoramic()) {
            parkPageUiSet.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._329.UiMgr$$ExternalSyntheticLambda1
                @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
                public final void onClickItem(int i) {
                    UiMgr.m694lambda36$lambda32(parkPageUiSet, bArr, i);
                }
            });
            final int i = context.getResources().getDisplayMetrics().widthPixels;
            final int i2 = context.getResources().getDisplayMetrics().heightPixels;
            final byte[] bArr3 = {22, 44, 0, 0, 0, 0, 0, 2};
            parkPageUiSet.setOnPanoramicItemTouchListener(new OnPanoramicItemTouchListener() { // from class: com.hzbhd.canbus.car._329.UiMgr$$ExternalSyntheticLambda2
                @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener
                public final void onTouchItem(MotionEvent motionEvent) {
                    UiMgr.m695lambda36$lambda35(i, i2, bArr3, motionEvent);
                }
            });
        }
        getBubbleUiSet(context).setOnBubbleClickListener(new OnBubbleClickListener() { // from class: com.hzbhd.canbus.car._329.UiMgr$4$1
            @Override // com.hzbhd.canbus.interfaces.OnBubbleClickListener
            public void onClick() {
                byte[] bArr4 = bArr;
                bArr4[2] = 1;
                bArr4[3] = 1;
                CanbusMsgSender.sendMsg(bArr4);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-5$lambda-4$lambda-0, reason: not valid java name */
    public static final void m696lambda5$lambda4$lambda0(UiMgr this$0, FrontArea frontArea, int i) {

        String str = frontArea.getLineBtnAction()[0][i];

        this$0.sendAirCommand(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-5$lambda-4$lambda-1, reason: not valid java name */
    public static final void m697lambda5$lambda4$lambda1(UiMgr this$0, FrontArea frontArea, int i) {

        String str = frontArea.getLineBtnAction()[1][i];

        this$0.sendAirCommand(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-5$lambda-4$lambda-2, reason: not valid java name */
    public static final void m698lambda5$lambda4$lambda2(UiMgr this$0, FrontArea frontArea, int i) {

        String str = frontArea.getLineBtnAction()[2][i];

        this$0.sendAirCommand(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-5$lambda-4$lambda-3, reason: not valid java name */
    public static final void m699lambda5$lambda4$lambda3(UiMgr this$0, FrontArea frontArea, int i) {

        String str = frontArea.getLineBtnAction()[3][i];

        this$0.sendAirCommand(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /* renamed from: lambda-24$lambda-12, reason: not valid java name */
    public static final void m689lambda24$lambda12(SettingPageUiSet settingPageUiSet, byte[] m0x8CCommand, Context context, UiMgr this$0, int i, int i2, int i3) {



        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        if (titleSrn != null) {
            switch (titleSrn.hashCode()) {
                case -463273934:
                    if (titleSrn.equals("_329_setting_10")) {
                        m0x8CCommand[2] = 3;
                        m0x8CCommand[3] = (byte) (i3 + 1);
                        CanbusMsgSender.sendMsg(m0x8CCommand);
                        break;
                    }
                    break;
                case -14944322:
                    if (titleSrn.equals("_329_setting_1")) {
                        m0x8CCommand[2] = 7;
                        m0x8CCommand[3] = (byte) i3;
                        CanbusMsgSender.sendMsg(m0x8CCommand);
                        break;
                    }
                    break;
                case -14944321:
                    if (titleSrn.equals("_329_setting_2")) {
                        m0x8CCommand[2] = 8;
                        m0x8CCommand[3] = (byte) i3;
                        CanbusMsgSender.sendMsg(m0x8CCommand);
                        break;
                    }
                    break;
                case -14944320:
                    if (titleSrn.equals("_329_setting_3")) {
                        m0x8CCommand[2] = 9;
                        m0x8CCommand[3] = (byte) i3;
                        CanbusMsgSender.sendMsg(m0x8CCommand);
                        break;
                    }
                    break;
                case -14944318:
                    if (titleSrn.equals("_329_setting_5")) {
                        m0x8CCommand[2] = 11;
                        m0x8CCommand[3] = (byte) i3;
                        CanbusMsgSender.sendMsg(m0x8CCommand);
                        break;
                    }
                    break;
                case -14944314:
                    if (titleSrn.equals("_329_setting_9")) {
                        m0x8CCommand[2] = 6;
                        m0x8CCommand[3] = (byte) (i3 + 1);
                        CanbusMsgSender.sendMsg(m0x8CCommand);
                        break;
                    }
                    break;
                case 712683749:
                    if (titleSrn.equals("support_panorama")) {
                        SharePreUtil.setIntValue(context, SHARE_329_IS_SUPPORT_PANORAMIC, i3);
                        this$0.mMsgMgr.updateSettingItem("support_panorama", Integer.valueOf(i3));
                        this$0.mMsgMgr.updateBubble(context, i3 == 1);
                        break;
                    }
                    break;
            }
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
    /* renamed from: lambda-24$lambda-17, reason: not valid java name */
    public static final void m690lambda24$lambda17(SettingPageUiSet settingPageUiSet, byte[] m0x8CCommand, int i, int i2, int i3) {

        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        if (titleSrn != null) {
            switch (titleSrn.hashCode()) {
                case -14944319:
                    if (titleSrn.equals("_329_setting_4")) {
                        m0x8CCommand[2] = 10;
                        m0x8CCommand[3] = (byte) i3;
                        CanbusMsgSender.sendMsg(m0x8CCommand);
                        break;
                    }
                    break;
                case -14944317:
                    if (titleSrn.equals("_329_setting_6")) {
                        m0x8CCommand[2] = NfDef.AVRCP_EVENT_ID_UIDS_CHANGED;
                        m0x8CCommand[3] = (byte) i3;
                        CanbusMsgSender.sendMsg(m0x8CCommand);
                        break;
                    }
                    break;
                case -14944316:
                    if (titleSrn.equals("_329_setting_7")) {
                        m0x8CCommand[2] = NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED;
                        m0x8CCommand[3] = (byte) i3;
                        CanbusMsgSender.sendMsg(m0x8CCommand);
                        break;
                    }
                    break;
                case -14944315:
                    if (titleSrn.equals("_329_setting_8")) {
                        m0x8CCommand[2] = 14;
                        m0x8CCommand[3] = (byte) i3;
                        CanbusMsgSender.sendMsg(m0x8CCommand);
                        break;
                    }
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-24$lambda-20, reason: not valid java name */
    public static final void m691lambda24$lambda20(SettingPageUiSet settingPageUiSet, byte[] m0x8CCommand, int i, int i2, int i3) {

        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        if (Intrinsics.areEqual(titleSrn, "_329_setting_11")) {
            m0x8CCommand[2] = 4;
            m0x8CCommand[3] = (byte) i3;
            CanbusMsgSender.sendMsg(m0x8CCommand);
        } else if (Intrinsics.areEqual(titleSrn, "_329_setting_12")) {
            m0x8CCommand[2] = 5;
            m0x8CCommand[3] = (byte) i3;
            CanbusMsgSender.sendMsg(m0x8CCommand);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-24$lambda-21, reason: not valid java name */
    public static final void m692lambda24$lambda21(SettingPageUiSet settingPageUiSet, int i, int i2) {
        settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-24$lambda-23, reason: not valid java name */
    public static final void m693lambda24$lambda23(SettingPageUiSet settingPageUiSet, byte[] m0xFDCommand, int i, int i2) {

        if (Intrinsics.areEqual(settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn(), "_55_0xE8_data4")) {
            m0xFDCommand[2] = 1;
            m0xFDCommand[3] = 1;
            CanbusMsgSender.sendMsg(m0xFDCommand);
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
    /* renamed from: lambda-36$lambda-32, reason: not valid java name */
    public static final void m694lambda36$lambda32(ParkPageUiSet parkPageUiSet, byte[] m0xFDCommand, int i) {

        String titleSrn = parkPageUiSet.getPanoramicBtnList().get(i).getTitleSrn();
        if (titleSrn != null) {
            switch (titleSrn.hashCode()) {
                case -1875869257:
                    if (titleSrn.equals("_194_front")) {
                        m0xFDCommand[2] = 3;
                        m0xFDCommand[3] = 4;
                        CanbusMsgSender.sendMsg(m0xFDCommand);
                        break;
                    }
                    break;
                case -1865062998:
                    if (titleSrn.equals("_194_right")) {
                        m0xFDCommand[2] = 3;
                        m0xFDCommand[3] = 7;
                        CanbusMsgSender.sendMsg(m0xFDCommand);
                        break;
                    }
                    break;
                case -129385052:
                    if (titleSrn.equals("_194_2d")) {
                        m0xFDCommand[2] = 4;
                        m0xFDCommand[3] = 1;
                        CanbusMsgSender.sendMsg(m0xFDCommand);
                        break;
                    }
                    break;
                case -129385021:
                    if (titleSrn.equals("_194_3d")) {
                        m0xFDCommand[2] = 4;
                        m0xFDCommand[3] = 2;
                        CanbusMsgSender.sendMsg(m0xFDCommand);
                        break;
                    }
                    break;
                case 216558544:
                    if (titleSrn.equals("_194_exit")) {
                        m0xFDCommand[2] = 1;
                        m0xFDCommand[3] = 0;
                        CanbusMsgSender.sendMsg(m0xFDCommand);
                        break;
                    }
                    break;
                case 216748729:
                    if (titleSrn.equals("_194_left")) {
                        m0xFDCommand[2] = 3;
                        m0xFDCommand[3] = 6;
                        CanbusMsgSender.sendMsg(m0xFDCommand);
                        break;
                    }
                    break;
                case 216927318:
                    if (titleSrn.equals("_194_rear")) {
                        m0xFDCommand[2] = 3;
                        m0xFDCommand[3] = 5;
                        CanbusMsgSender.sendMsg(m0xFDCommand);
                        break;
                    }
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-36$lambda-35, reason: not valid java name */
    public static final void m695lambda36$lambda35(int i, int i2, byte[] mTouchCommand, MotionEvent motionEvent) {

        Pair pair = new Pair(Float.valueOf((motionEvent.getX() * 800) / i), Float.valueOf((motionEvent.getY() * 480) / i2));
        int action = motionEvent.getAction();
        int i3 = 1;
        if (action == 0) {
            i3 = 2;
        } else if (action != 1) {
            return;
        }
        mTouchCommand[2] = (byte) i3;
        mTouchCommand[3] = (byte) ((((int) ((Number) pair.getFirst()).floatValue()) >> 8) & 255);
        mTouchCommand[4] = (byte) (((int) ((Number) pair.getFirst()).floatValue()) & 255);
        mTouchCommand[5] = (byte) ((((int) ((Number) pair.getSecond()).floatValue()) >> 8) & 255);
        mTouchCommand[6] = (byte) (((int) ((Number) pair.getSecond()).floatValue()) & 255);
        CanbusMsgSender.sendMsg(mTouchCommand);
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
    private final void sendAirCommand(String title) {
        switch (title.hashCode()) {
            case -1470045433:
                if (title.equals("front_defog")) {
                    sendAirCommand(5);
                    break;
                }
                break;
            case -631663038:
                if (title.equals("rear_defog")) {
                    sendAirCommand(6);
                    break;
                }
                break;
            case -597744666:
                if (title.equals("blow_positive")) {
                    sendAirCommand(21);
                    break;
                }
                break;
            case -424438238:
                if (title.equals(AirBtnAction.BLOW_NEGATIVE)) {
                    sendAirCommand(22);
                    break;
                }
                break;
            case 3106:
                if (title.equals("ac")) {
                    sendAirCommand(2);
                    break;
                }
                break;
            case 3005871:
                if (title.equals("auto")) {
                    sendAirCommand(4);
                    break;
                }
                break;
            case 3094652:
                if (title.equals("dual")) {
                    sendAirCommand(41);
                    break;
                }
                break;
            case 106858757:
                if (title.equals("power")) {
                    sendAirCommand(1);
                    break;
                }
                break;
            case 756225563:
                if (title.equals("in_out_cycle")) {
                    sendAirCommand(7);
                    break;
                }
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendAirCommand(int command) {
        CanbusMsgSender.sendMsg(new byte[]{22, 61, (byte) command, 1});
    }
}
